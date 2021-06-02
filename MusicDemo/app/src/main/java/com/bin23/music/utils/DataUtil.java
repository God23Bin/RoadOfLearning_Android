package com.bin23.music.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Datautils {

    private static StringBuilder sb;
    private static AssetManager am;
    private static InputStream is;
    private static InputStreamReader isr;
    private static BufferedReader br;
    /**
     * 读取资源文件中的数据
     *
     */
    public static String getJsonFromAssets(Context context, String filename){
        /**
         * 1.StringBuilder 存放读取出的数据
         * 2.AssetManager  资源管理器, Open方法打开指定的资源文件 ，返回InputStream
           3.InputStreamReader （字节到字符的桥接器） BufferedReader (存放读取字符的缓冲区）
         * 4.循环利用 bufferedReader 的  readLine 方法读取每一行的数据，并且把读取出来的数据放入到StringBuilder里面
         * 5. 返回读取出来的所有数据
         */
        // * 1.StringBuilder 存放读取出的数据
        sb = new StringBuilder();
        // AssetManager  资源管理器
        am = context.getAssets();
        // Open方法打开指定的资源文件 ，返回InputStream
        try {
            is = am.open(filename);
            // InputStreamReader （字节到字符的桥接器） BufferedReader (存放读取字符的缓冲区）
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            // 循环利用BufferedReader的readLine 方法 读取每一行的数据
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
