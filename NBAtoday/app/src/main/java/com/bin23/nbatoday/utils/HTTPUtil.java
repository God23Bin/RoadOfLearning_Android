package com.bin23.nbatoday.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jsoup.Jsoup;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPUtil {
    public static String getJsonConetent(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            byte[] buf = new byte[1024];
            int hasReadLen = 0;
            while ((hasReadLen = is.read(buf)) != -1) {
                baos.write(buf, 0, hasReadLen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    /**
     * 获取网落图片资源
     * @param path
     * @return
     */
    public static Bitmap getHttpBitmap(String path){
        URL myFileURL;
        Bitmap bitmap = null;
        try{
            myFileURL = new URL(path);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
//            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
//            conn.setConnectTimeout(6000);
//            //连接设置获得数据流
//            conn.setDoInput(true);
//            //不使用缓存
//            conn.setUseCaches(false);
            //这句可有可无，没有影响
            conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            if (is == null){
                throw new RuntimeException("stream is null");
            }else{
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int hasReadLen = 0;
                    while ((hasReadLen = is.read(buf)) != -1) {
                        baos.write(buf, 0, hasReadLen);
                    }
                    byte[] data = baos.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                is.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

//    public static void getRealPlayUrl(String viedoPageUrl){
//        Jsoup.connect(viedoPageUrl);
//    }

}
