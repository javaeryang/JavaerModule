package version;

/**
 * Created by javaer on 2018/7/1.
 */

public class Version {

    public static String SQLiteDatabase = "com.tencent.wcdb.database.SQLiteDatabase";
    public static String DataBaseInsert = "insertWithOnConflict";
    public static String addTextOptionMenu = "addTextOptionMenu";
    public static String launcherUI = "com.tencent.mm.ui.LauncherUI";
    public static String launcherUI_MenuCreate = "onCreateOptionsMenu";
    public static String launcherUI_onMenuSelected = "onOptionsItemSelected";
    public static String ExdeviceRankInfoUI = "com.tencent.mm.plugin.exdevice.ui.ExdeviceRankInfoUI";//微信运动界面UI



    public static String ExdeviceRankInfoUI_bm = "bk"; //6.6.6  "MicroMsg.ExdeviceRankInfoStg", "hy: info is null. abort"
    public static String ExdeviceRankInfoUI_String_Field_lZO = "iFs";

    public static String ring_Class = "com.tencent.mm.plugin.voip.video.i";
    public static String ring_Class_Method = "n";

    public static String Message_Clear = "com.tencent.mm.model.c";//特征("voiceremind/")-----------------------------
    public static String Message_Clear_Method = "FW";//(返回一个类实例)从最长的方法起第七个方法
    public static String Message_Class_Method_1 = "Ys";//(标为已读)上面方法返回的类(接口)里的(从返回一个类对象的方法往下数第三个)
    public static String Message_Class_Method_2 = "Yt";//(标为未读)上面方法返回的类(接口)里的(从返回一个类对象的方法往下数第四个)
    public static String Message_Class_Method_YH = "YH";//清空

    public static void init(int version){
        switch (version){

        }
    }
}
