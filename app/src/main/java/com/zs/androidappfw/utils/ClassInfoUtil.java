package com.zs.androidappfw.utils;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseActivity;
import com.zs.androidappfw.ui.fundamental.lang.ClassDetailsAct;
import com.zs.androidappfw.ui.fundamental.lang.Utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringJoiner;

public class ClassInfoUtil {

    public static final String KEY_CLASS = "CLASS_KEY";
    public static final String VALUE_CLASS = "VALUE_CLASS";

    private static final int GROUP_LEN = 2;

    public static void displayClassInfo(BaseActivity activity, LinearLayout layout, Class<?> clz) {
        LinkedHashMap<String, String> map = ClassInfoUtil.getFieldMap(clz);
        for (String key: map.keySet()) {
            String value = map.get(key);
            TextView left = ViewUtil.createStartTv(activity, layout);
            left.setText(key);

            if (!ClassInfoUtil.VALUE_CLASS.equals(value)) {
                left.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                left.setPadding(50, 0, 0, 0);

                TextView right = ViewUtil.createStartTv(activity, layout);
                right.setText(value);
                right.setPadding(50, 0, 0, 0);
                right.setTextColor(Color.BLUE);
            } else {
                left.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                left.setTextColor(Color.MAGENTA);
                left.setGravity(Gravity.CENTER);
            }
            ViewUtil.addDivider(activity, layout, Color.BLACK);
        }
    }

    public static LinkedHashMap<String, String> getFieldMap(Class<?> clz) {
        Class<?>[] declaredClasses = clz.getDeclaredClasses();
        Class<?>[] classes;
        if (declaredClasses.length == 0) {
            classes = new Class<?>[1];
            classes[0] = clz;
        } else {
            classes = new Class<?>[1 + declaredClasses.length];
            classes[0] = clz;
            System.arraycopy(declaredClasses, 0, classes, 1, declaredClasses.length);
        }

        List<LinkedHashMap<String, String>> list = new ArrayList<>(classes.length);
        for (Class<?> clazz : classes) {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put(clazz.getName(), VALUE_CLASS);
            list.add(map);
        }
        for (int i = 0; i < classes.length; i++) {
            fillFieldMap(classes[i], list.get(i));

            if (i > 0) {
                for (String key : list.get(i).keySet()) {
                    list.get(0).put(key, list.get(i).get(key));
                }
            }
        }
        return list.get(0);
    }

    private static void fillFieldMap(Class<?> clz, LinkedHashMap<String, String> map) {
        Field[] fields = clz.getDeclaredFields();
        Arrays.sort(fields, new FieldComparator());
        for (Field field : fields) {
            if ((field.getModifiers() & Modifier.PRIVATE) > 0) continue;
            if ((field.getModifiers() & Modifier.STATIC) == 0) continue;
            String name = field.toGenericString().replace(clz.getName() + ".", "");
            for (Class<?>[] classes: Utils.JAVA_LANG_ALL) {
                for (Class<?> clazz : classes) {
                    name = name.replace(clazz.getName(), clazz.getSimpleName());
                }
            }
            try {
                map.put(name, Objects.requireNonNull(field.get(field)).toString());
            } catch (IllegalAccessException | IllegalArgumentException e) {
                map.put(name, "unknown");
            }
        }
    }

    /************************* Class Common Information *************************/

    public static void displayClass(BaseActivity activity, LinearLayout linearLayout, @NonNull Class<?> clz) {
        displayClassMetadata(activity, linearLayout, clz);
        displayConstructor(activity, linearLayout, clz);
        displayField(activity, linearLayout, clz);
        displayMethod(activity, linearLayout, clz);
    }

    private static void displayClassMetadata(BaseActivity activity, LinearLayout linearLayout,
            @NonNull Class<?> cls) {
        ViewUtil.addDivider(activity, linearLayout, Color.BLACK);

        TextView superClassTv = ViewUtil.createStartTv(activity, linearLayout);

        StringBuilder superClassSb = new StringBuilder();
        Class<?> clazz = cls;
        while ((clazz != null)/* && (clazz != Object.class)*/) {
            superClassSb.append("--> ").append(clazz.getName()).append("\n");
            clazz = clazz.getSuperclass();
        }
        if (superClassSb.length() == 0) {
            superClassSb.append("--> ").append(Object.class.getName()).append("\n");
        }
        String title = "---->> " + classModifierToString(cls.getModifiers()) + "\n" + superClassSb;
        superClassTv.setText(title);
    }

    private static String classModifierToString(int mod) {
        StringJoiner sj = new StringJoiner(" ");

        if ((mod & Modifier.PUBLIC) != 0)        sj.add("public");
        if ((mod & Modifier.PROTECTED) != 0)     sj.add("protected");
        if ((mod & Modifier.PRIVATE) != 0)       sj.add("private");
        if (sj.length() == 0)                    sj.add("default");

        /* Canonical order */
        if ((mod & Modifier.ABSTRACT) != 0)      sj.add("abstract");
        if ((mod & Modifier.STATIC) != 0)        sj.add("static");
        if ((mod & Modifier.FINAL) != 0)         sj.add("final");
        if ((mod & Modifier.STRICT) != 0)        sj.add("strictfp");

        if ((mod & Modifier.INTERFACE) != 0) {
            sj.add("interface");
        } else if ((mod & 0x00004000/*enum*/) != 0) {
            sj.add("enum");
        } else {
            sj.add("class");
        }

        return sj.toString();
    }

    public static void displayConstructor(BaseActivity activity, LinearLayout linearLayout, Class<?> cls) {
        LinkedHashMap<String, String> map = getConstructorMap(cls, Utils.JAVA_LANG_ALL);
        displayGroupTitle(activity, linearLayout, R.string.title_java_lang_constructor);
        displayGroup(activity, linearLayout, map);
    }

    private static LinkedHashMap<String, String> getConstructorMap(Class<?> cls, Class<?>[][] filters) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        Arrays.sort(constructors, new ConstructorComparator());
        for (Constructor<?> constructor : constructors) {
            String name = constructor.toGenericString().replace(cls.getName() + ".", "");
            for (Class<?>[] classes: filters) {
                for (Class<?> clazz : classes) {
                    name = name.replace(clazz.getName(), clazz.getSimpleName());
                }
            }
            map.put(name, "----");
        }
        return map;
    }

    private static class ConstructorComparator implements Comparator<Constructor<?>> {
        @Override
        public int compare(Constructor<?> c1, Constructor<?> c2) {
            int accessResult = getAccessPriority(c1.getModifiers(), c2.getModifiers());
            if (accessResult != 0) return accessResult;

            return c1.getParameterCount() - c2.getParameterCount();
        }
    }

    public static void displayField(BaseActivity activity, LinearLayout linearLayout, Class<?> cls) {
        LinkedHashMap<String, String> map = getFieldMap(cls, Utils.JAVA_LANG_ALL);
        displayGroupTitle(activity, linearLayout, R.string.title_java_lang_field);
        displayGroup(activity, linearLayout, map);
    }

    private static LinkedHashMap<String, String> getFieldMap(Class<?> clz, Class<?>[][] filters) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        Field[] fields = clz.getDeclaredFields();
        Arrays.sort(fields, new FieldComparator());
        for (Field field : fields) {
            String name = field.toGenericString().replace(clz.getName() + ".", "");
            for (Class<?>[] classes: filters) {
                for (Class<?> clazz : classes) {
                    name = name.replace(clazz.getName(), clazz.getSimpleName());
                }
            }
            try {
                map.put(name, Objects.requireNonNull(field.get(field)).toString());
            } catch (IllegalAccessException | IllegalArgumentException e) {
                map.put(name, "unknown");
            }
        }
        return map;
    }

    private static class FieldComparator implements Comparator<Field> {
        @Override
        public int compare(Field f1, Field f2) {
            int f1m = f1.getModifiers();
            int f2m = f2.getModifiers();

            int accessResult = getAccessPriority(f1m, f2m);
            if (accessResult != 0) return accessResult;

            int staticResult = (f1m & Modifier.STATIC) - (f2m & Modifier.STATIC);
            if (staticResult != 0) return -1 * staticResult;

            int finalResult = (f1m & Modifier.FINAL) - (f2m & Modifier.FINAL);
            if (finalResult != 0) return -1 * finalResult;

            int volatileResult = (f1m & Modifier.VOLATILE) - (f2m & Modifier.VOLATILE);
            if (volatileResult != 0) return -1 * volatileResult;

            int transientResult = (f1m & Modifier.TRANSIENT) - (f2m & Modifier.TRANSIENT);
            if (transientResult != 0) return -1 * transientResult;

            int typeResult = f1.getType().getName().compareTo(f2.getType().getName());
            if (typeResult != 0) return typeResult;

            return f1.getName().compareTo(f2.getName());
        }
    }

    public static void displayMethod(BaseActivity activity, LinearLayout linearLayout, Class<?> cls) {
        LinkedHashMap<String, String> map = getMethodMap(cls, Utils.JAVA_LANG_ALL);
        displayGroupTitle(activity, linearLayout, R.string.title_java_lang_method);
        displayGroup(activity, linearLayout, map);
    }

    public static void displayGroupTitle(BaseActivity activity, LinearLayout linearLayout) {
        displayGroupTitle(activity, linearLayout, "---- Content ----");
    }

    public static void displayGroupTitle(BaseActivity activity, LinearLayout linearLayout,
            int titleResId) {
        TextView groupTitle = createGroupTitle(activity, linearLayout);
        groupTitle.setText(titleResId);
    }

    public static void displayGroupTitle(BaseActivity activity, LinearLayout linearLayout,
            String title) {
        TextView groupTitle = createGroupTitle(activity, linearLayout);
        groupTitle.setText(title);
    }

    private static TextView createGroupTitle(BaseActivity activity, LinearLayout linearLayout) {
        TextView groupTitle = new TextView(activity);
        linearLayout.addView(groupTitle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 140);
        groupTitle.setLayoutParams(layoutParams);
        groupTitle.setGravity(Gravity.CENTER);
        groupTitle.setTextColor(Color.WHITE);
        groupTitle.setBackgroundColor(Color.MAGENTA);
        groupTitle.setTypeface(FontUtil.getTypeClass(activity));
        return groupTitle;
    }

    public static void displayGroup(BaseActivity activity, LinearLayout linearLayout,
            LinkedHashMap<String, String> map) {
        int i = 0;
        String leftText;
        for (String key: map.keySet()) {
            String value = map.get(key);
            TextView left = ViewUtil.createStartTv(activity, linearLayout);
            left.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            if (!TextUtils.isEmpty(value)) {
                TextView right = ViewUtil.createStartTv(activity, linearLayout);
                right.setText(value);
                right.setTextColor(Color.BLUE);
                leftText = String.format(Locale.getDefault(), "%d  %s", ++i, key);
            } else {
                left.setPadding(0, 0, 0, 0);
                left.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                left.setTextColor(Color.RED);
                i = 0;
                leftText = key;
            }
            left.setText(leftText);
            ViewUtil.addDivider(activity, linearLayout, Color.BLACK);
        }
        ViewUtil.addDivider(activity, linearLayout, Color.RED);
    }

    private static LinkedHashMap<String, String> getMethodMap(Class<?> clz, Class<?>[][] filters) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        Method[] methods = clz.getDeclaredMethods();
        Arrays.sort(methods, new MethodComparator());
        for (Method method : methods) {
            String name = method.toGenericString().replace(clz.getName() + ".", "");
            for (Class<?>[] classes: filters) {
                for (Class<?> clazz : classes) {
                    name = name.replace(clazz.getName(), clazz.getSimpleName());
                }
            }
            String value = getStaticMethodValue(method);
            map.put(name, value);
        }
        return map;
    }

    private static String getStaticMethodValue(Method method) {
        String result = "----";
        Object tmp;
        int targetModifier = Modifier.PUBLIC | Modifier.STATIC;
        if (((method.getModifiers() & targetModifier) == targetModifier)
                && (method.getParameterCount() == 0)) {
            Class<?> returnType = method.getReturnType();
            try {
                tmp = method.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                tmp = null;
            }
            if (tmp != null) {
                if (returnType.equals(String.class)) {
                    result = (String) tmp;
                } else if (returnType.equals(File.class)) {
                    File file = (File) tmp;
                    result = file.getAbsolutePath();
                } else if (tmp instanceof Number) {
                    return String.valueOf(tmp);
                } else if (returnType.equals(Character.class)) {
                    result = String.valueOf((Character) tmp);
                } else if (returnType.equals(Boolean.class)) {
                    result = String.valueOf((Boolean) tmp);
                }
            }
        }

        return result;
    }

    static class MethodComparator implements Comparator<Method> {
        @Override
        public int compare(Method m1, Method m2) {
            int m1m = m1.getModifiers();
            int m2m = m2.getModifiers();

            int accessResult = getAccessPriority(m1m, m2m);
            if (accessResult != 0) return accessResult;

            int staticResult = (m1m & Modifier.STATIC) - (m2m & Modifier.STATIC);
            if (staticResult != 0) return -1 * staticResult;

            int finalResult = (m1m & Modifier.FINAL) - (m2m & Modifier.FINAL);
            if (finalResult != 0) return -1 * finalResult;

            int syncResult = (m1m & Modifier.SYNCHRONIZED) - (m2m & Modifier.SYNCHRONIZED);
            if (syncResult != 0) return -1 * syncResult;

            int nativeResult = (m1m & Modifier.NATIVE) - (m2m & Modifier.NATIVE);
            if (nativeResult != 0) return nativeResult;

            int abstractResult = (m1m & Modifier.ABSTRACT) - (m2m & Modifier.ABSTRACT);
            if (abstractResult != 0) return abstractResult;

            int nameResult = m1.getName().compareTo(m2.getName());
            if (nameResult != 0) return nameResult;

            return m1.getParameterCount() - m2.getParameterCount();
        }
    }

    private static int getAccessPriority(int m1, int m2) {
        int access = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
        int accessResult1 = (m1 & access);
        int accessResult2 = (m2 & access);
        if ((accessResult1 != 0) && (accessResult2 != 0)) {
            if (accessResult1 != accessResult2) {
                if (accessResult1 == Modifier.PUBLIC) {
                    return -1;
                } else if (accessResult2 == Modifier.PUBLIC) {
                    return 1;
                } else if (accessResult1 == Modifier.PROTECTED) {
                    return -1;
                } else if (accessResult2 == Modifier.PROTECTED) {
                    return 1;
                }
            }
        } else if (accessResult1 != 0) {
            if (accessResult1 == Modifier.PRIVATE) {
                return 1;
            } else {
                return -1;
            }
        } else if (accessResult2 != 0) {
            if (accessResult2 == Modifier.PRIVATE) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static void displayPackage(BaseActivity activity, LinearLayout linearLayout, Class<?>[][] packages) {
        for (Class<?>[] classes : packages) {
            int groups = classes.length / GROUP_LEN;
            if ((classes.length % GROUP_LEN) > 0) groups += 1;
            for (int i = 0; i < groups; i++) {
                LinearLayout rowLl = new LinearLayout(activity);
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 140);
                rowLl.setLayoutParams(llParams);
                rowLl.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.addView(rowLl);
                for (int j = 0; (j < GROUP_LEN) && ((i * GROUP_LEN + j) < classes.length); j++) {
                    Button button = new Button(activity);
                    rowLl.addView(button);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 140, 1);
                    layoutParams.leftMargin = 5;
                    layoutParams.rightMargin = 5;
                    layoutParams.topMargin = 5;
                    layoutParams.bottomMargin = 5;
                    layoutParams.gravity = View.TEXT_ALIGNMENT_GRAVITY;
                    button.setLayoutParams(layoutParams);
                    button.setText(classes[i * GROUP_LEN + j].getSimpleName());
                    button.setAllCaps(false);
                    button.setTextSize(14);
                    button.setBackground(AppCompatResources.getDrawable(activity, R.drawable.act_btn_shape));

                    int modifier = classes[i * GROUP_LEN + j].getModifiers();
//                    LUtil.i("Utils", classes[i * GROUP_LEN + j].getName(), "0x", Integer.toHexString(modifier));
                    if (((modifier & Modifier.ABSTRACT) > 0) && ((modifier & Modifier.INTERFACE) > 0)) {
                        button.setTypeface(FontUtil.getTypeInterface(activity));
                    } else if (((modifier & Modifier.FINAL) > 0) && ((modifier & 0x00004000/*enum*/) > 0)) {
                        button.setTypeface(FontUtil.getTypeEnum(activity));
                        button.setTextColor(Color.GREEN);
                    } else if ((modifier & Modifier.ABSTRACT) > 0) {
                        button.setTypeface(FontUtil.getTypeAbstract(activity));
                    } else {
                        button.setTypeface(FontUtil.getTypeClass(activity));
                    }

                    button.setOnClickListener(new OnClickListener(activity, classes[i * GROUP_LEN + j].getName()));
                }
            }
        }
    }

    private static class OnClickListener implements View.OnClickListener {

        private final BaseActivity mActivity;
        private final String mClazz;

        OnClickListener(BaseActivity activity, String clazz) {
            mActivity = activity;
            mClazz = clazz;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mActivity, ClassDetailsAct.class);
            intent.putExtra(KEY_CLASS, mClazz);
            mActivity.startActivity(intent);
        }
    }
}
