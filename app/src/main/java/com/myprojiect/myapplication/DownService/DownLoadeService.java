package com.myprojiect.myapplication.DownService;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;

/**
 * Created by liangwanbo on 2016/11/15.
 * App自动跟新的下载和安装服务，在通知栏里通知下载进度
 */

public class DownLoadeService extends Service {


    private BroadcastReceiver receiver;
    /**系统下载管理器*/
    private DownloadManager dm;
    /**系统下载器分配的唯一下载任务id，可以通过这个id查询或者处理下载任务*/
    private long enqueue;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /**新建一个广播接受者*/
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                /**当系统下载完成，就提示安装*/
                install(context);
                /**销毁当前的service*/
                stopSelf();
            }
        };




        /**过滤器，用来监听系统下载完成时的广播*/
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        /**注册广播*/
        registerReceiver(receiver, filter);
        /**开始下载*/
        startDownload(intent.getStringExtra("downUrl"));
        return Service.START_STICKY;
    }

    private void startDownload(String downUrl) {
        /**获得系统下载器*/
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        /**设置下载地址*/
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
        /**设置下载文件的类型*/
        request.setMimeType("application/vnd.android.package-archive");
        /**设置下载存放的文件夹和文件名字*/
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "XXXXXXX.apk");
        /**设置下载时或者下载完成时，通知栏是否显示*/        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("炎黄直播");
        /**执行下载，并返回任务唯一id*/
        enqueue = dm.enqueue(request);
    }

    /**安装*/
    public static void install(Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "XXXXXXX.apk")), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

  /*  public static void install(Context context ) {
        install(context,true);
    }*/
    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
