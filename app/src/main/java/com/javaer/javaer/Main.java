package com.javaer.javaer;

import android.app.Application;
import android.content.Context;

import com.javaer.javaer.Log.Vlog;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import version.Version;

/**
 * Created by javaer on 2018/7/1.
 */

public class Main implements IXposedHookLoadPackage{

    public static String MM_PKG = "com.tencent.mm";
    public static String Javaer_PKG = "com.javaer.javaer";
    public static int VERSION = 0;
    public static ClassLoader GlobalLoader;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(MM_PKG)){

            XposedHelpers.findAndHookMethod(Application.class,
                    "onCreate",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Context context = (Context) param.thisObject;

                            int version = context.getPackageManager().getPackageInfo(MM_PKG, 0).versionCode;

                            if (0 == VERSION){
                                VERSION = version;
                                Version.init(VERSION);
                                Vlog.log("cv>>>>>"+VERSION);
                            }

                            Vlog.log("wechat version >>>>> "+version);

                            GlobalLoader = lpparam.classLoader;

                            HookInit.init(GlobalLoader);
                        }
                    }
            );
        }
    }
}
