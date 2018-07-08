package com.javaer.javaer;

import com.javaer.javaer.Hook.DataBase;
import com.javaer.javaer.Hook.Launcher;
import com.javaer.javaer.Hook.Ringtone;
import com.javaer.javaer.Hook.SportClick;

/**
 * Created by javaer on 2018/7/1.
 */

public class HookInit {
    public static void init(ClassLoader classLoader){


        try {
            Launcher.hookLauncher(classLoader);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }


        try {
            DataBase.hookDataBase(classLoader);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        try{
            SportClick.hookExdeviceRankInfoUI(classLoader);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        try{
            Ringtone.hookRingtone(classLoader);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }
}
