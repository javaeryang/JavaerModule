package com.javaer.javaer.DataBase;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import static de.robv.android.xposed.XposedHelpers.callMethod;

/**
 * Created by javaer on 2018/7/1.
 */

public class MainDB {

    private Object SQLDB;

    public MainDB(Object SQLDB) {
        this.SQLDB = SQLDB;
    }

    public Cursor rawQuery(String query){
        return rawQuery(query,null);
    }

    public Cursor rawQuery(String query, String[] args){
        return (Cursor) callMethod(SQLDB,"rawQuery",query,args);
    }

    /**
     * 获取全部微信运动的用户列表
     * @return
     */
    public List<String> getAllRankInfoUsers(String rankID){
        List<String> list = new ArrayList<>();
        Cursor cursor = rawQuery("select * from HardDeviceRankInfo where rankID = ?", new String[]{rankID});
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            list.add(username);
        }
        return list;
    }

    /**
     * 获取全部最近联系人
     * @return
     */
    public List<String> getAllConversation(){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = rawQuery("select * from rconversation");
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            list.add(username);
        }
        cursor.close();
        return list;
    }
}
