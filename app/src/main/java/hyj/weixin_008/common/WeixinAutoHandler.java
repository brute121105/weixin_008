package hyj.weixin_008.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */

public class WeixinAutoHandler {
    public static  Map<String, String> record = new HashMap<String, String>();
    private static WeixinAutoHandler weixinAutoHandler = new WeixinAutoHandler();
    private WeixinAutoHandler(){}
    public static WeixinAutoHandler getInstance(){
        return weixinAutoHandler;
    }
    //暂停
    public static boolean IS_PAUSE=false;
    public static boolean IS_START_SERVICE=false;
    public static boolean IS_NEXT_NONE=false;

}
