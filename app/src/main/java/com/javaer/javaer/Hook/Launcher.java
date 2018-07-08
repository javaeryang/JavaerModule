package com.javaer.javaer.Hook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.javaer.javaer.AsyncTask.ClearConversationTask;
import com.javaer.javaer.AsyncTask.MarkReadTask;
import com.javaer.javaer.AsyncTask.MarkUnReadTask;
import com.javaer.javaer.UI.BaseDialog;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import preference.PreferenceUtil;
import version.Version;

/**
 * Created by javaer on 2018/7/1.
 */

public class Launcher {

    public static Activity currentActivity;

    public static void hookLauncher(final ClassLoader classLoader){

        XposedHelpers.findAndHookMethod(Version.launcherUI,
                classLoader,
                Version.launcherUI_MenuCreate,
                Menu.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Menu menu = (Menu) param.args[0];
                        menu.add(0, 9901, 0, "Javaer模块");
                        if (PreferenceUtil.isQuickRead()){
                            menu.add(0, 9902, 0, "标为已读");
                        }
                        if (PreferenceUtil.isQuickUnRead()){
                            menu.add(0, 9903, 0, "标为未读");
                        }
                        if (PreferenceUtil.isQuickClearConversation()){
                            menu.add(0, 9904, 0, "清空聊天");
                        }
                    }
                }
        );


        XposedHelpers.findAndHookMethod(Version.launcherUI,
            classLoader,
            Version.launcherUI_onMenuSelected,
            MenuItem.class,
            new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                MenuItem item = (MenuItem) param.args[0];
                int id = item.getItemId();
                Activity activity = (Activity) param.thisObject;
                List<String> wxids = DataBase.MainDB.getAllConversation();
                switch (id){
                    case 9901:
                        showMainDialog(activity);
                        break;
                    case 9902:
                        MarkReadTask markReadTask = new MarkReadTask(classLoader);
                        markReadTask.execute(wxids);
                        break;
                    case 9903:
                        MarkUnReadTask markUnReadTask = new MarkUnReadTask(classLoader);
                        markUnReadTask.execute(wxids);
                        break;
                    case 9904:
                        ClearConversationTask clearConversationTask =
                                new ClearConversationTask(classLoader);
                        clearConversationTask.execute(wxids);
                        break;
                }
            }
        });

        XposedHelpers.findAndHookMethod(Activity.class,
                "onResume",
                Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        currentActivity = (Activity) param.thisObject;
                    }
                }
        );
    }

    public static void showMainDialog(Activity activity){
        BaseDialog dialog = new BaseDialog(activity, "Javaer模块");
        dialog.addSwitch("微信运动一键点赞", "one_click", false);
        dialog.addSwitch("自定微信来去电铃声(手机铃声)", "custom_ringtone", false);
        dialog.addSwitch("快捷标为已读", "quick_read", false);
        dialog.addSwitch("快捷标为未读", "quick_unread", true);
        dialog.addSwitch("清空聊天(保留聊天记录)", "quick_clear_conversation", false);

        dialog.addEdit("id", "javaer", "");
        dialog.show(true, true, null, "", null);
    }
}
