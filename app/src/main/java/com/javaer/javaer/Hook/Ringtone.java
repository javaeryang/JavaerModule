package com.javaer.javaer.Hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import preference.PreferenceUtil;
import version.Version;

import static version.Version.ring_Class_Method;

/**
 * Created by javaer on 2018/7/1.
 */

public class Ringtone {
    public static void hookRingtone(ClassLoader classLoader){
        XposedHelpers.findAndHookMethod(Version.ring_Class,
                classLoader,
                ring_Class_Method,
                int.class, int.class,
                boolean.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        if (PreferenceUtil.isCustomRing()){
                            param.args[0] = 0;
                        }
                    }
                });
    }
}
