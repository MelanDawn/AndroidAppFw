package com.zs.androidappfw.cellular;

public final class CellularConstants {
    // Do not modify without upating the HAL bt_hf.h files.

    // match up with bthf_connection_state_t enum of bt_hf.h
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    static final int CONNECTION_STATE_CONNECTING = 1;
    static final int CONNECTION_STATE_CONNECTED = 2;
    static final int CONNECTION_STATE_SLC_CONNECTED = 3;
    static final int CONNECTION_STATE_DISCONNECTING = 4;

    // match up with bthf_audio_state_t enum of bt_hf.h
    static final int AUDIO_STATE_DISCONNECTED = 0;
    static final int AUDIO_STATE_CONNECTING = 1;
    static final int AUDIO_STATE_CONNECTED = 2;
    static final int AUDIO_STATE_DISCONNECTING = 3;

    // match up with bthf_vr_state_t enum of bt_hf.h
    static final int VR_STATE_STOPPED = 0;
    static final int VR_STATE_STARTED = 1;

    // match up with bthf_volume_type_t enum of bt_hf.h
    static final int VOLUME_TYPE_SPK = 0;
    static final int VOLUME_TYPE_MIC = 1;

    // match up with bthf_network_state_t enum of bt_hf.h
    static final int NETWORK_STATE_NOT_AVAILABLE = 0;
    static final int NETWORK_STATE_AVAILABLE = 1;

    // match up with bthf_service_type_t enum of bt_hf.h
    static final int SERVICE_TYPE_HOME = 0;
    static final int SERVICE_TYPE_ROAMING = 1;

    // match up with bthf_at_response_t of bt_hf.h
    static final int AT_RESPONSE_ERROR = 0;
    static final int AT_RESPONSE_OK = 1;

    // match up with bthf_call_state_t of bt_hf.h
    static final int CALL_STATE_ACTIVE = 0;
    static final int CALL_STATE_HELD = 1;
    static final int CALL_STATE_DIALING = 2;
    static final int CALL_STATE_ALERTING = 3;
    static final int CALL_STATE_INCOMING = 4;
    static final int CALL_STATE_WAITING = 5;
    static final int CALL_STATE_IDLE = 6;
    static final int CALL_STATE_DISCONNECTED = 7;

    // match up with bthf_hf_ind_type_t of bt_hf.h
    static final int HF_INDICATOR_ENHANCED_DRIVER_SAFETY = 1;
    public static final int HF_INDICATOR_BATTERY_LEVEL_STATUS = 2;

    // match up with bthf_wbs_config_t of bt_hf.h
    static final int BTHF_WBS_NONE = 0;
    static final int BTHF_WBS_NO = 1;
    static final int BTHF_WBS_YES = 2;

    // match up with bthf_swb_config_t of bt_hf.h
    static final int BTHF_SWB_NONE = 0;
    static final int BTHF_SWB_NO = 1;
    static final int BTHF_SWB_YES = 2;
}
