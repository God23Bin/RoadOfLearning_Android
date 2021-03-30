package com.bin23.simplevideo.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
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
}
