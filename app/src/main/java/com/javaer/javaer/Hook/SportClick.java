package com.javaer.javaer.Hook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import com.javaer.javaer.Log.Vlog;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import preference.PreferenceUtil;
import version.Version;

/**
 * Created by javaer on 2017/12/24.
 */

@SuppressWarnings("ResourceType")
public class SportClick {

    /**
     * 微信运动点赞
     * @param activity 微信运动界面
     * @param wxid 微信ID
     */
    public static void likes(Object activity, String wxid){
        XposedHelpers.callMethod(activity, Version.ExdeviceRankInfoUI_bm, wxid, 1);
    }

    public static void hookExdeviceRankInfoUI(final ClassLoader classLoader){
        XposedHelpers.findAndHookMethod(Version.ExdeviceRankInfoUI,
                classLoader,
                "onCreate",
                Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                        if (!PreferenceUtil.isOneClick()){
                            return;
                        }
                        XposedHelpers.callMethod(param.thisObject, Version.addTextOptionMenu, -1, "一键点赞(Javaer)", new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                String lZO = (String) XposedHelpers.getObjectField(param.thisObject, Version.ExdeviceRankInfoUI_String_Field_lZO);
                                Vlog.log("lZO>>>"+lZO);
                                List<String> ids = DataBase.MainDB.getAllRankInfoUsers(lZO);
                                LikeTask likeTask = new LikeTask(param.thisObject);
                                likeTask.execute(ids);
                                return true;
                            }
                        });
                    }
                }
        );
    }

    static class LikeTask extends AsyncTask<List<String>, Void, Void>{

        Object activity;

        public LikeTask(Object activity) {
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(List<String>[] lists) {
            List<String> ids = lists[0];
            if (ids.size() <= 0){
                return null;
            }
            for (String id : ids){
                SportClick.likes(activity, id);
                Vlog.log("点赞"+id);
            }
            for (int i = ids.size() - 1; i >= 0; i--){
                SportClick.likes(activity, ids.get(i));
                Vlog.log(i+">>点赞"+ids.get(i));
            }
            Vlog.log("点赞完成"+ids.size());
            return null;
        }
    }
}
