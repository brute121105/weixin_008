package hyj.weixin_008.thread;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.accessibility.AccessibilityNodeInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

import hyj.weixin_008.AutoUtil;
import hyj.weixin_008.Constants;
import hyj.weixin_008.common.WeixinAutoHandler;
import hyj.weixin_008.model.AutoChatObj;
import hyj.weixin_008.util.DragImageUtil;
import hyj.weixin_008.util.LogUtil;
import hyj.weixin_008.util.ParseRootUtil;

/**
 * Created by asus on 2017/8/20.
 */

public class DrapImageThread implements Runnable {
    AccessibilityService context;
    Map<String,String> record;
    public DrapImageThread(AccessibilityService context, Map<String,String> record){
        this.context = context;
        this.record = record;
    }
    @Override
    public void run() {
        while (true){
            AutoUtil.sleep(500);
            LogUtil.d("DrapImageThread","拖动方块DrapImageThread..."+Thread.currentThread().getName()+" ");
            AccessibilityNodeInfo root = context.getRootInActiveWindow();
            if(root==null){
                LogUtil.d("DrapImageThread","DrapImageThread root is null");
                AutoUtil.sleep(500);
                continue;
            }

            if(AutoUtil.checkAction(record,"wx方块拖动成功")){
                continue;
            }

            //方块处理
      if(1==1||AutoUtil.checkAction(record,"wx开始安全验证")){
            AccessibilityNodeInfo fkNode = ParseRootUtil.getNodePath(root,"0000000000");
            AccessibilityNodeInfo errorNode = ParseRootUtil.getNodePath(root,"0000000003");

           //判断是否拖动成功
           if(AutoUtil.checkAction(record,"wx拖动方块")){
               if(errorNode!=null){
                   if("请控制拼图块对齐缺口".equals(errorNode.getContentDescription()+"")){
                       AccessibilityNodeInfo refreshNode = ParseRootUtil.getNodePath(root,"0030");
                       //AutoUtil.performClick(refreshNode,record,"wx刷新方块");
                       AutoUtil.clickXY(987,1175);
                       AutoUtil.recordAndLog(record,"wx刷新方块");
                       LogUtil.d("DrapImageThread","wx刷新方块"+refreshNode);
                   }else if((errorNode.getContentDescription()+"").indexOf("只用了")>-1){
                       AutoUtil.recordAndLog(record,"wx方块拖动成功");
                       continue;
                   }else{
                       LogUtil.d("DrapImageThread","等待拖动结果");
                       continue;
                   }
               }
           }
            if(fkNode!=null&&"拖动下方滑块完成拼图".equals(fkNode.getContentDescription().toString())){
                AutoUtil.sleep(1000);
                String  path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+"/Screenshots";
                LogUtil.d("DrapImageThread","path-->"+path);
                File file = new File(path);
                File[] files = file.listFiles();
                //清空文件
                if(files!=null&&files.length>0){
                    LogUtil.d("DrapImageThread","file length-->"+files.length);
                    for(File f:files){
                        LogUtil.d("DrapImageThread","删除："+f.getName());
                        f.delete();
                        LogUtil.d("DrapImageThread","删除完："+f.getName());
                    }
                }
                //截图
                AutoUtil.execShell("input keyevent 120");
                LogUtil.d("DrapImageThread","截图");
                AutoUtil.sleep(2000);

                //获取图片，计算拖动坐标
                File picFile = waitAndGetFile(path);
                String picPath = path+"/"+picFile.getName();
                LogUtil.d("DrapImageThread","picPath:"+picPath);
                Bitmap bi = BitmapFactory.decodeFile(picPath);
                while (bi==null){
                    AutoUtil.sleep(500);
                    bi = BitmapFactory.decodeFile(picPath);
                    LogUtil.d("DrapImageThread","等待获取bitmap");
                }
                String dragStr = DragImageUtil.dragPoint(bi);
                AutoUtil.execShell("input swipe "+dragStr);
                AutoUtil.recordAndLog(record,"wx拖动方块");
                AutoUtil.sleep(1000);

            }
        }

        }
    }

    //轮询获取截图图片
    private File waitAndGetFile(String path){
        File picFile = null;
        while (picFile==null){
            File[] files = new File(path).listFiles();
            if(files!=null&&files.length>0){
                picFile = files[0];
            }else{
                AutoUtil.sleep(1000);
                LogUtil.d("DrapImageThread","等待截图生成");
            }
        }
        return picFile;
    }


}
