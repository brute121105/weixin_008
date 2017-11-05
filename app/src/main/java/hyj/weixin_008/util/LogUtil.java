package hyj.weixin_008.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/15.
 */

public class LogUtil {
    //记录日志到sd卡
    public static void d(String tab,String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        String logMsg = dateTime+" "+tab+"hyj---->"+msg;
        //以天为单位生成日志文件
        System.out.println(logMsg);
        FileUtil.writeContent2File("/sdcard/A_hyj_log/","log_"+dateTime.substring(0,13)+":00.txt",logMsg);
    }

    //记录登录账号sd卡
    public static void login(String tab,String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        String logMsg = dateTime+" "+tab+"---->"+msg;
        //以天为单位生成日志文件
        System.out.println(logMsg);
        FileUtil.writeContent2File("/sdcard/A_hyj_login/","loginInfo_"+dateTime.substring(0,10)+".txt",logMsg);
    }
    //记录登录账号sd卡
    public static void reg(String tab,String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        String logMsg = dateTime+" "+tab+"---->"+msg;
        //以天为单位生成日志文件
        System.out.println(logMsg);
        FileUtil.writeContent2File("/sdcard/A_hyj_reg/","reg_"+dateTime.substring(0,10)+".txt",logMsg);
    }
    //记录008数据到sd卡
    public static void log008(String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        //以天为单位生成日志文件
        FileUtil.writeContent2File("/sdcard/A_hyj_008data/","008data.txt",msg);
    }
    //记录008数据到sd卡
    public static void export(String path,String fileName,String msg){
        FileUtil.writeContent2File(path,fileName,msg);
    }
}
