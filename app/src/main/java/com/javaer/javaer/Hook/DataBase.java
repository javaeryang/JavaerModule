package com.javaer.javaer.Hook;

import android.content.ContentValues;

import com.javaer.javaer.DataBase.MainDB;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import version.Version;

/**
 * Created by javaer on 2018/7/1.
 */

public class DataBase {

    public static com.javaer.javaer.DataBase.MainDB MainDB;

    public static void hookDataBase(ClassLoader classLoader){
        XposedHelpers.findAndHookMethod(Version.SQLiteDatabase,
                classLoader, Version.DataBaseInsert,
                String.class,
                String.class,
                ContentValues.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        if (MainDB == null){
                            MainDB = new MainDB(param.thisObject);
                        }
                        String table = (String) param.args[0];
                        String column = (String) param.args[1];
                        ContentValues values = (ContentValues) param.args[2];

                    }
                }
        );
    }
}
