package com.zs.androidappfw.cellular;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SignalStrengthUpdateRequest;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionManager.OnSubscriptionsChangedListener;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.telephony.emergency.EmergencyNumber;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.utils.PermissionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class CellularActivity extends BaseTitleActivity {

    private TelephonyManager mTelephonyMgr;
    private SubscriptionManager mSubscriptionMgr;

    private final HashMap<BluetoothDevice, Integer> mDeviceEventMap = new HashMap<>();
    private PhoneStateListener mPhoneStateListener;
    private OnSubscriptionsChangedListener mOnSubscriptionsChangedListener;
    private SignalStrengthUpdateRequest mSignalStrengthUpdateRequest;
    private final Object mPhoneStateListenerLock = new Object();

    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cellular);

        mHandler = new Handler(Looper.getMainLooper());
        mTelephonyMgr = getSystemService(TelephonyManager.class);
        mSubscriptionMgr = SubscriptionManager.from(CellularActivity.this);
        mOnSubscriptionsChangedListener = new SubscriptionChangedListenerImpl();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mSubscriptionMgr.addOnSubscriptionsChangedListener(
                    command -> mHandler.post(command), mOnSubscriptionsChangedListener);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            mSubscriptionMgr.addOnSubscriptionsChangedListener(mOnSubscriptionsChangedListener);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            SignalThresholdInfo info = new SignalThresholdInfo.Builder()
//                    .setThresholds(new int[] {1})
//                    .setRadioAccessNetworkType(AccessNetworkConstants.AccessNetworkType.NGRAN)
//                    .setSignalMeasurementType(SignalThresholdInfo.SIGNAL_MEASUREMENT_TYPE_RSSI)
//                    .build();
//            List<SignalThresholdInfo> list = new ArrayList<SignalThresholdInfo>();
//            list.add(info);
//            mSignalStrengthUpdateRequest = new SignalStrengthUpdateRequest.Builder()
//                    .setSignalThresholdInfos(list)
//                    .setReportingRequestedWhileIdle(true)
//                    .build();
        }
        simplePrintSubscription();
        simplePrintTelephony();
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_cellular;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptionMgr.removeOnSubscriptionsChangedListener(mOnSubscriptionsChangedListener);
    }

    private ServiceState mServiceState;

    // HFP 1.6 CIND service value
    private int mCindService = CellularConstants.NETWORK_STATE_NOT_AVAILABLE;
    // Number of active (foreground) calls
    private int mNumActive;
    // Current Call Setup State
    private int mCallState = CellularConstants.CALL_STATE_IDLE;
    // Number of held (background) calls
    private int mNumHeld;
    // HFP 1.6 CIND signal value
    private int mCindSignal;
    // HFP 1.6 CIND roam value
    private int mCindRoam = CellularConstants.SERVICE_TYPE_HOME;
    // HFP 1.6 CIND battchg value
    private int mCindBatteryCharge;

    private class SubscriptionChangedListenerImpl extends OnSubscriptionsChangedListener {
        SubscriptionChangedListenerImpl() {
            super();
        }

        @Override
        public void onSubscriptionsChanged() {
            synchronized (mDeviceEventMap) {
                int simState = mTelephonyMgr.getSimState();
                if (simState != TelephonyManager.SIM_STATE_READY) {
                    mServiceState = null;
                    mCindSignal = 0;
                    mCindService = CellularConstants.NETWORK_STATE_NOT_AVAILABLE;
                    sendDeviceStateChanged();
                }
                stopListenForPhoneState();
                startListenForPhoneState();
            }
        }
    }


    private void startListenForPhoneState() {
        synchronized (mPhoneStateListenerLock) {
            if (mPhoneStateListener != null) {
                LUtil.w(mTag, "startListenForPhoneState, already listening");
                return;
            }
            int events = getTelephonyEventsToListen();
            if (events == PhoneStateListener.LISTEN_NONE) {
                LUtil.w(mTag, "startListenForPhoneState, no event to listen");
                return;
            }
            int subId = SubscriptionManager.getDefaultSubscriptionId();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (!SubscriptionManager.isValidSubscriptionId(subId)) {
                    // Will retry listening for phone state in onSubscriptionsChanged() callback
                    LUtil.w(mTag, "startListenForPhoneState, invalid subscription ID " + subId);
                    return;
                }
                mPhoneStateListener = new PhoneStateListenerImpl(command -> mHandler.post(command));
            }
            LUtil.i(mTag, "startListenForPhoneState(), subId=" + subId
                    + ", enabled_events=" + Integer.toHexString(events));
            mTelephonyMgr.listen(mPhoneStateListener, events);
            if ((events & PhoneStateListener.LISTEN_SIGNAL_STRENGTHS) != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    mTelephonyMgr.setSignalStrengthUpdateRequest(mSignalStrengthUpdateRequest);
                }
            }
        }
    }

    private int getTelephonyEventsToListen() {
        synchronized (mDeviceEventMap) {
//            return PhoneStateListener.LISTEN_SERVICE_STATE | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS;
            return mDeviceEventMap.values()
                    .stream()
                    .reduce(PhoneStateListener.LISTEN_NONE, (a, b) -> a | b);
        }
    }

    private void stopListenForPhoneState() {
        synchronized (mPhoneStateListenerLock) {
//            mTelephonyMgr.clearSignalStrengthUpdateRequest(mSignalStrengthUpdateRequest);
            if (mPhoneStateListener == null) {
                LUtil.i(mTag, "stopListenForPhoneState(), no listener indicates nothing is listening");
                return;
            }
            LUtil.i(mTag, "stopListenForPhoneState(), stopping listener, enabled_events="
                    + getTelephonyEventsToListen());
            mTelephonyMgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
            mPhoneStateListener = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("AndroidFrameworkRequiresPermission")
    private class PhoneStateListenerImpl extends PhoneStateListener {
        PhoneStateListenerImpl(Executor executor) {
            super(executor);
        }

        @Override
        public synchronized void onServiceStateChanged(ServiceState serviceState) {
            mServiceState = serviceState;
            int cindService = (serviceState.getState() == ServiceState.STATE_IN_SERVICE)
                    ? CellularConstants.NETWORK_STATE_AVAILABLE
                    : CellularConstants.NETWORK_STATE_NOT_AVAILABLE;
            int newRoam = serviceState.getRoaming() ? CellularConstants.SERVICE_TYPE_ROAMING
                    : CellularConstants.SERVICE_TYPE_HOME;

            if (cindService == mCindService && newRoam == mCindRoam) {
                // De-bounce the state change
                return;
            }
            mCindService = cindService;
            mCindRoam = newRoam;
            sendDeviceStateChanged();
        }

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            int prevSignal = mCindSignal;
            if (mCindService == CellularConstants.NETWORK_STATE_NOT_AVAILABLE) {
                mCindSignal = 0;
            } else {
                mCindSignal = signalStrength.getLevel() + 1;
            }
            // +CIND "signal" indicator is always between 0 to 5
            mCindSignal = Integer.max(Integer.min(mCindSignal, 5), 0);
            // This results in a lot of duplicate messages, hence this check
            if (prevSignal != mCindSignal) {
                sendDeviceStateChanged();
            }
        }
    }

    private synchronized void sendDeviceStateChanged() {
        // When out of service, send signal strength as 0. Some devices don't
        // use the service indicator, but only the signal indicator
        int signal = mCindService == CellularConstants.NETWORK_STATE_AVAILABLE ? mCindSignal : 0;

        LUtil.d(mTag, "sendDeviceStateChanged. mService=" + mCindService
                + " mSignal=" + mCindSignal + " mRoam=" + mCindRoam
                + " mBatteryCharge=" + mCindBatteryCharge);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private class A extends TelephonyCallback {

    }

    private void simplePrintSubscription() {
        LUtil.d(mTag, "--- Print Subscription Info start ---");
        LUtil.d(mTag, mSubscriptionMgr.getActiveSubscriptionInfoCountMax());

        if (!PermissionUtil.checkPhonePermission(this)) {
            return;
        }
        LUtil.d(mTag, mSubscriptionMgr.getActiveSubscriptionInfoCount());
        LUtil.d(mTag, mSubscriptionMgr.getActiveSubscriptionInfo(0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            LUtil.d(mTag, mSubscriptionMgr.getAccessibleSubscriptionInfoList());
//            LUtil.d(TAG, mSubscriptionMgr.getSubscriptionPlans(0));
        }
        LUtil.d(mTag, mSubscriptionMgr.getActiveSubscriptionInfoList());
        LUtil.d(mTag, mSubscriptionMgr.getActiveSubscriptionInfoForSimSlotIndex(0));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            LUtil.d(mTag, mSubscriptionMgr.getCompleteActiveSubscriptionInfoList());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            LUtil.d(mTag, mSubscriptionMgr.getDeviceToDeviceStatusSharingContacts(0));
            LUtil.d(mTag, mSubscriptionMgr.getDeviceToDeviceStatusSharingPreference(0));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LUtil.d(mTag, mSubscriptionMgr.getOpportunisticSubscriptions());
            int[] ids = mSubscriptionMgr.getSubscriptionIds(0);
            if (ids != null) LUtil.d(mTag, ids.length);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            LUtil.d(mTag, mSubscriptionMgr.getPhoneNumber(0));
            LUtil.d(mTag, mSubscriptionMgr.getPhoneNumber(0,
                    SubscriptionManager.PHONE_NUMBER_SOURCE_UICC));
        }


        LUtil.d(mTag, "--- Print Subscription Info stop ---");
    }

    private void simplePrintTelephony() {
        LUtil.d(mTag, "--- Print Telephony Info start ---");

        if (!PermissionUtil.checkPhonePermission(this)) {
            return;
        }

        LUtil.d(mTag, mTelephonyMgr.getDataActivity());
        LUtil.d(mTag, mTelephonyMgr.getDataState());
        LUtil.d(mTag, mTelephonyMgr.getDataNetworkType());
        LUtil.d(mTag, mTelephonyMgr.getDeviceSoftwareVersion());

//        LUtil.d(TAG, mTelephonyMgr.getForbiddenPlmn());
        LUtil.d(mTag, mTelephonyMgr.getGroupIdLevel1());
//        LUtil.d(TAG, mTelephonyMgr.getIccAuthentication(1, 2, "3"));
        LUtil.d(mTag, mTelephonyMgr.getLine1Number());

        LUtil.d(mTag, mTelephonyMgr.getNetworkCountryIso());
        LUtil.d(mTag, mTelephonyMgr.getNetworkOperator());
        LUtil.d(mTag, mTelephonyMgr.getNetworkOperatorName());
        LUtil.d(mTag, mTelephonyMgr.getNetworkSpecifier());
        LUtil.d(mTag, mTelephonyMgr.getNetworkType());

        LUtil.d(mTag, mTelephonyMgr.getPhoneType());
        LUtil.d(mTag, mTelephonyMgr.getPhoneCount());

        LUtil.d(mTag, mTelephonyMgr.getServiceState());

        LUtil.d(mTag, mTelephonyMgr.getSimCountryIso());
        LUtil.d(mTag, mTelephonyMgr.getSimOperator());
        LUtil.d(mTag, mTelephonyMgr.getSimOperatorName());
//        LUtil.d(TAG, mTelephonyMgr.getSimSerialNumber());

        LUtil.d(mTag, "getSimState " + mTelephonyMgr.getSimState());
        LUtil.d(mTag, "getSimState 0: " + mTelephonyMgr.getSimState(0));
        LUtil.d(mTag, "getSimState 1: " + mTelephonyMgr.getSimState(1));
        LUtil.d(mTag, "getSimState 2: " + mTelephonyMgr.getSimState(2));
        LUtil.d(mTag, "getSimState 3: " + mTelephonyMgr.getSimState(3));

        LUtil.d(mTag, mTelephonyMgr.getVisualVoicemailPackageName());
        LUtil.d(mTag, mTelephonyMgr.getVoiceMailAlphaTag());
        LUtil.d(mTag, mTelephonyMgr.getVoiceMailNumber());
        LUtil.d(mTag, mTelephonyMgr.getVoiceNetworkType());
//        LUtil.d(TAG, mTelephonyMgr.getVoicemailRingtoneUri(mTelephonyMgr.getPhoneAccountHandle()));

//        LUtil.d(TAG, mTelephonyMgr.getDeviceId());
//        LUtil.d(TAG, mTelephonyMgr.getDeviceId(0));
//        LUtil.d(TAG, mTelephonyMgr.getDeviceId(1));
//        LUtil.d(TAG, mTelephonyMgr.getDeviceId(2));
//        LUtil.d(TAG, mTelephonyMgr.getDeviceId(-1));

//        LUtil.d(TAG, mTelephonyMgr.getImei());
//        LUtil.d(TAG, mTelephonyMgr.getImei(0));
//        LUtil.d(TAG, mTelephonyMgr.getImei(1));
//        LUtil.d(TAG, mTelephonyMgr.getImei(2));
//        LUtil.d(TAG, mTelephonyMgr.getImei(3));

//        LUtil.d(TAG, mTelephonyMgr.getMeid());
//        LUtil.d(TAG, mTelephonyMgr.getMeid(0));
//        LUtil.d(TAG, mTelephonyMgr.getMeid(1));
//        LUtil.d(TAG, mTelephonyMgr.getMeid(2));
//        LUtil.d(TAG, mTelephonyMgr.getMeid(3));

//        LUtil.d(TAG, mTelephonyMgr.getSubscriberId());
        LUtil.d(mTag, mTelephonyMgr.getCallState());

        LUtil.d(mTag, mTelephonyMgr.getAllCellInfo());
        LUtil.d(mTag, mTelephonyMgr.getCellLocation());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            LUtil.d(mTag, mTelephonyMgr.getSignalStrength());
//            LUtil.d(TAG, mTelephonyMgr.getNai());
            LUtil.d(mTag, mTelephonyMgr.getSimCarrierId());
            LUtil.d(mTag, mTelephonyMgr.getSimCarrierIdName());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            LUtil.d(TAG, mTelephonyMgr.getUiccCardsInfo());
            LUtil.d(mTag, mTelephonyMgr.getTypeAllocationCode());
            LUtil.d(mTag, mTelephonyMgr.getTypeAllocationCode(0));
            LUtil.d(mTag, mTelephonyMgr.getTypeAllocationCode(1));
            LUtil.d(mTag, mTelephonyMgr.getTypeAllocationCode(2));
            LUtil.d(mTag, mTelephonyMgr.getCardIdForDefaultEuicc());
            LUtil.d(mTag, mTelephonyMgr.getPreferredOpportunisticDataSubscription());
            LUtil.d(mTag, mTelephonyMgr.getSimSpecificCarrierId());
            LUtil.d(mTag, mTelephonyMgr.getSimSpecificCarrierIdName());
            Map<Integer, List<EmergencyNumber>> map =  mTelephonyMgr.getEmergencyNumberList();
            for (Integer key : map.keySet()) {
                LUtil.d(mTag, "EmergencyNumber --", key);
                List<EmergencyNumber> list = map.get(key);
                if (list != null) {
                    for (EmergencyNumber number : list) {
                        LUtil.d(mTag, number.getCountryIso(), number.getMnc(), number.getNumber());
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            LUtil.d(mTag, mTelephonyMgr.getSubscriptionId());
//            LUtil.d(TAG, mTelephonyMgr.getSubscriptionId(mTelephonyMgr.getPhoneAccountHandle()));
            LUtil.d(mTag, mTelephonyMgr.getActiveModemCount());
            LUtil.d(mTag, mTelephonyMgr.getSupportedModemCount());
            LUtil.d(mTag, mTelephonyMgr.getNetworkCountryIso(0));
            //TODO
//            LUtil.d(TAG, mTelephonyMgr.getNetworkCountryIso(1));
//            LUtil.d(TAG, mTelephonyMgr.getNetworkSelectionMode());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            LUtil.d(TAG, mTelephonyMgr.getCallComposerStatus());
            LUtil.d(mTag, mTelephonyMgr.getCallStateForSubscription());
            LUtil.d(mTag, mTelephonyMgr.getEquivalentHomePlmns());
//            LUtil.d(TAG, mTelephonyMgr.getPhoneAccountHandle());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            LUtil.d(TAG, mTelephonyMgr.getSupportedRadioAccessFamily());
            LUtil.d(mTag, mTelephonyMgr.getServiceState(TelephonyManager.INCLUDE_LOCATION_DATA_FINE));
        }

        LUtil.d(mTag, "--- Print Telephony Info stop ---");
    }
}
