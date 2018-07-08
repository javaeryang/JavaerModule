package com.javaer.javaer.AsyncTask;

import android.os.AsyncTask;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;
import version.Version;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MarkReadTask extends AsyncTask<List<String>, Void, Void>{

    ClassLoader classLoader;

    public MarkReadTask(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected Void doInBackground(List<String>[] lists) {
        List<String> wxids = lists[0];
        Class cls = XposedHelpers.findClass(Version.Message_Clear, classLoader);
        Object asInstance = XposedHelpers.callStaticMethod(cls, Version.Message_Clear_Method);
        try{
            for (int i = 0; i < wxids.size(); i ++){
                XposedHelpers.callMethod(asInstance, Version.Message_Class_Method_1, wxids.get(i));
                Thread.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
