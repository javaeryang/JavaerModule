package preference;

import com.javaer.javaer.Main;

import java.util.Random;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by Administrator on 2018/3/6.
 */

public class PreferenceUtil {
    private static XSharedPreferences instance = null;
    private static XSharedPreferences WXinstance = null;

    public static boolean noForbid = true;//未禁止 = 真
    public static String je_kp = "";

    private static XSharedPreferences getInstance(){
        if (instance == null){
            instance = new XSharedPreferences(Main.MM_PKG, Main.Javaer_PKG);
            instance.makeWorldReadable();
        }else {
            instance.reload();
        }
        return instance;
    }

    private static XSharedPreferences getWXInstance(){
        if (WXinstance == null){
            WXinstance = new XSharedPreferences(Main.MM_PKG, Main.MM_PKG+"_preference");
            WXinstance.makeWorldReadable();
        }else {
            WXinstance.reload();
        }
        return WXinstance;
    }

    public static String selfWXID(){//获取自己wxid
        return getWXInstance().getString("login_weixin_username", "");
    }
    public static String selfWECHAT(){//微信号
        return getWXInstance().getString("login_user_name","");
    }
    public static String selfQQ(){//绑定的QQ
        return getWXInstance().getString("last_login_bind_qq", "");
    }
    public static String selfMAIL(){//绑定的邮箱
        return getWXInstance().getString("last_login_bind_email", "");
    }
    public static String selfPHONE(){//绑定的手机号
        return getWXInstance().getString("last_login_bind_mobile", "");
    }

    public static boolean isChatManage(){//群聊管理
        return getInstance().getBoolean("chat_manage", false);
    }

    //----

    public static boolean isEnableEditableForwardSns(){//可编辑式转发朋友圈
        return getInstance().getBoolean("editable_forward_sns_timeline", false);
    }

    public static boolean isEnableForward(){//转发
        return getInstance().getBoolean("chat_forward_to_chat", false);
    }

    public static boolean isEnableRecordAutoForward(){//记录自动转发
        return getInstance().getBoolean("message_record_auto_forward", false);
    }

    public static boolean isEnableQuickForwardSns(){//快速转发朋友圈
        return getInstance().getBoolean("quick_forward_sns_timeline", false);
    }



    //----

    public static String getRecordAutoForwardUser(){//获取最近的User
        return getInstance().getString("record_auto_forward_user", "");
    }

    public static long getRecordStartTime(){//获取开始时间
        return getInstance().getLong("record_auto_forward_start_time", 0);
    }

    public static long getRecordEndTime(){//获取结束时间
        return getInstance().getLong("record_auto_forward_end_time", 0);
    }

    //----

    public static boolean isSendManageOrder(){//发送管理员指令
        return getInstance().getBoolean("send_manage_order", false);
    }

    public static boolean isAvoidBeAT(){//阻止[有人@我]提示
        return getInstance().getBoolean("avoid_be_at", false);
    }

    public static boolean isForbid(){//踢出私自添加群成员者
        return getInstance().getBoolean("forbid_add_private", false);
    }

    //----
    public static boolean isEnableRobot(){//图灵机器人
        return getInstance().getBoolean("v_tuling_robot", false);
    }

    public static String getTulingKey(){//读取图灵key
        return getInstance().getString("tuling_robot_key", "");
    }

    public static boolean isRobotDelayReply(){//开启机器人延迟回复
        return getInstance().getBoolean("robot_reply_delay", false);
    }

    public static int getRobotDelayTime(){//设置机器人延迟时间
        return Integer.parseInt(getInstance().getString("time_robot_reply_delay", "2"));
    }

    public static boolean isEnableRobotReplyTroop(){//是否允许机器人回复群聊
        return getInstance().getBoolean("enable_robot_reply_troop", false);
    }

    public static boolean isAutoReply(){//自动回复
        return getInstance().getBoolean("v_auto_reply", false);
    }

    public static boolean isEnableReplyTroop(){//允许回复群聊
        return getInstance().getBoolean("enable_auto_reply_troop", true);
    }

    public static boolean isEnableAutoReplyDelay(){//是否延迟
        return getInstance().getBoolean("is_reply_delay", false);
    }

    public static int getReplyDelay(){//设置回复延迟
        return Integer.parseInt(getInstance().getString("time_reply_delay", "3"));
    }

    public static boolean isReplySignature(){//是否回复签名
        return getInstance().getBoolean("auto_reply_signature", false);
    }

    public static String getReplySignature(){//读取签名
        return getInstance().getString("reply_signature", "[本消息是自动回复]");
    }

    //----

    public static boolean isMutiAccept(){//手动批量接受好友
        return getInstance().getBoolean("muti_accept", false);
    }

    public static boolean isAutoAccept(){//自动接受好友请求开关
        return getInstance().getBoolean("auto_accept", false);
    }

    public static boolean isReplyAfterAccept(){//接受好友后自动回复
        return getInstance().getBoolean("reply_after_accept", false);
    }

    public static String replyText(){//回复文字
        return getInstance().getString("reply_text", "");
    }

    public static boolean isDelayAccept(){//回复延迟
        return getInstance().getBoolean("reply_delay", false);
    }

    public static int delayAcceptTime(){//延迟时间
        return Integer.parseInt(getInstance().getString("reply_delay_time", "2"));
    }

    public static boolean isOneClick(){//运动点赞开关
        return getInstance().getBoolean("one_click", false);
    }

    //----
    public static boolean isAutoConfirmLogin(){//电脑登录自动确认
        return getInstance().getBoolean("auto_confirm_login", false);
    }

    public static boolean isShowWxid(){//显示微信ID
        return getInstance().getBoolean("show_wxid", false);
    }

    public static boolean isCustomSplash(){//启动图
        return getInstance().getBoolean("custom_splash", false);
    }

    public static boolean isCustomRing(){//铃声开关
        return getInstance().getBoolean("custom_ringtone", false);
    }

    public static boolean isCustomArea(){//自定义地区
        return getInstance().getBoolean("custom_area", false);
    }

    public static String customText(){//地区文字
        return getInstance().getString("area_text", "");
    }

    public static boolean isCustomEmoji(){//表情上限
        return getInstance().getBoolean("custom_emoji_num", false);
    }

    public static String customEmojiSize(){//上限数量
        return getInstance().getString("emoji_max", "300");
    }

    public static boolean isControlRandom(){//自定义骰子
        return getInstance().getBoolean("control_random", false);
    }

    public static int randomMode(){//9002-->单发, 9001-->连发
        return getInstance().getInt("random_mode", 9002);
    }

    public static int randomMode0(){//猜拳随机模式9002-->单发, 9001-->连发
        return getInstance().getInt("random_mode_0", 9002);
    }

    public static String multiSend(){//骰子连发模式
        return getInstance().getString("multi_send", "1");
    }

    public static String multiSend0(){//猜拳连发模式
        return getInstance().getString("multi_send_0", "1");
    }

    public static int randomResult1(){//猜拳
        return Integer.parseInt(getInstance().getString("random_result_0", "0"));
    }

    public static int randomResult2(){//骰子
        return Integer.parseInt(getInstance().getString("random_result_1", "0"));
    }

    public static boolean isFakeVoiceLength(){//开启语音误报
        return getInstance().getBoolean("is_fake_voice_length", false);
    }

    public static long fakeVoiceLength(){//故意误报时长
        String s = getInstance().getString("fake_voice_length", "1");
        return Integer.parseInt(s) * 1000;
    }

    public static boolean isFakeLocation(){//开启虚拟定位
        return getInstance().getBoolean("is_fake_location", false);
    }

    public static double latitude(){//返回纬度
        String lan = getInstance().getString("fake_location", "39.90469,116.40717").replace("，", ",");
        return Double.parseDouble(lan.split(",")[0]);
    }

    public static double longitude(){//返回纬度
        String lon = getInstance().getString("fake_location", "39.90469,116.40717").replace("，", ",");
        return Double.parseDouble(lon.split(",")[1]);
    }

    public static boolean isSnsInfoRevoke(){//朋友圈动态撤回开关
        return getInstance().getBoolean("sns_info_revoke", false);
    }

    public static boolean isSnsMomentRevoke(){//朋友圈评论撤回
        return getInstance().getBoolean("sns_moment_revoke", false);
    }

    //----
    public static boolean isTextToVoice(){//文本消息转语音开关
        return getInstance().getBoolean("text_to_voice", false);
    }

    public static boolean isPlaySender(){//播报发送人
        return getInstance().getBoolean("play_sender", false);
    }

    //----
    public static boolean isLuckyMoney(){//抢红包开关
        return getInstance().getBoolean("lucky_money", false);
    }

    public static boolean noSelfLucky(){//不抢自己发的
        return getInstance().getBoolean("no_self", false);
    }

    public static boolean noPrivateLucky(){//不抢私聊
        return getInstance().getBoolean("no_private", false);
    }

    public static boolean noContainLucky(){//不抢包含关键词
        return getInstance().getBoolean("no_contain", false);
    }

    public static String noContainWords(){//关键词
        return getInstance().getString("no_contain_text", "");
    }

    public static boolean autoTransfer(){//自动接收转账开关
        return getInstance().getBoolean("auto_transfer", false);
    }

    public static boolean isTransferReply(){//收到转账自动回复
        return getInstance().getBoolean("transfer_reply", false);
    }

    public static String transferReplyWords(){//设置收到转账回复语
        return getInstance().getString("transfer_words", "[收到]");
    }

    public static boolean isPlayTransferDetail(){//播报收账详情
        return getInstance().getBoolean("play_transfer_detail", false);
    }

    public static boolean isPlayTransferSender(){//播报发送人
        return getInstance().getBoolean("play_transfer_sender", false);
    }

    public static boolean isTransferDelay(){//随机延迟
        return getInstance().getBoolean("transfer_delay", false);
    }

    public static int transferRandomDelay(){//延迟时间
        String delay = getInstance().getString("transfer_random_delay", "");
        if (delay.equals(""))
            return 0;
        String[] dly = delay.split("-");
        int i = new Random().nextInt(Integer.parseInt(dly[1]) - Integer.parseInt(dly[0]) + 1) + Integer.parseInt(dly[0]);
        return i;
    }

    public static String sayHelloText(){//打招呼用语
        return getInstance().getString("say_hello_text", "你好");
    }

    public static int sayHelloDelay(){//延迟
        return Integer.parseInt(getInstance().getString("say_hello_delay", "15"));
    }

    public static int sayHelloMaxNum(){//限制最大数
        return Integer.parseInt(getInstance().getString("max_num", "15"));
    }

    public static boolean isAddRadarFriend(){//添加雷达好友
        return getInstance().getBoolean("add_radar_friend", false);
    }

    public static boolean isAddChatroomFriend(){//添加群成员为好友
        return getInstance().getBoolean("add_chatroom_friend", false);
    }

    public static boolean isAddNearbyFriend(){//添加附近人
        return getInstance().getBoolean("add_nearby_friend", false);
    }

    public static boolean isLabelNewFriend(){//按日期标签新加好友
        return getInstance().getBoolean("label_for_new_friends", false);
    }

    public static boolean isQuickRead(){//快捷已读
        return getInstance().getBoolean("quick_read", false);
    }

    public static boolean isQuickUnRead(){//快捷未读
        return getInstance().getBoolean("quick_unread", true);
    }

    public static boolean isQuickClearConversation(){//清空聊天
        return getInstance().getBoolean("quick_clear_conversation", false);
    }

    public static int searchMode(){//搜索僵尸粉模式0-->发消息,1-->建群
        return getInstance().getInt("search_mode", 1);
    }

    public static boolean isSearchingDead(){//是否在搜索僵尸粉---数据库插入时判断
        return getInstance().getBoolean("is_searching_dead", false);
    }

    public static boolean searchDeadFans(){//搜索僵尸粉
        return getInstance().getBoolean("search_dead_fans", false);
    }

    public static String searchChatroom(){//搜索僵尸粉使用的chatroom
        return getInstance().getString("search_dead_chatroom", "");
    }

    public static String searchDeadText(){//发送僵尸粉文字
        return getInstance().getString("search_dead_text", "Hi");
    }

    public static int searchDeadDelay(){//延时
        return Integer.parseInt(getInstance().getString("search_dead_delay", "5"));
    }

    public static boolean autoDeleteFans(){//自动删除找到的僵尸粉
        return getInstance().getBoolean("auto_delete_fans", false);
    }

    public static String deadFans(){//僵尸粉列表
        return getInstance().getString("dead_fans", "");
    }

    //
    public static boolean isBlockSnsAD(){//屏蔽朋友圈广告
        return getInstance().getBoolean("block_sns_ad", false);
    }

    public static boolean isSnsAutoLike(){//自动点赞
        return getInstance().getBoolean("sns_auto_like", false);
    }

    public static boolean isSnsAutoComment(){//自动评论
        return getInstance().getBoolean("sns_auto_comment", false);
    }

    public static String snsAutoCommentText(){
        return getInstance().getString("sns_auto_comment_text", "朋友圈自动评论");
    }
}
