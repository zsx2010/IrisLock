package com.ImaginationUnlimited.library.utils;

import android.database.Cursor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 资源关闭工具类
 *
 * @author wangheng on 2016-03-21 17:10
 */
public class CloseableUtils {
//    public static void close(Closeable res){
//        try {
//            if(res != null) {
//                res.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void close(Cursor cursor){
        try {
            if(cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(InputStream res){
        try {
            if(res != null) {
                res.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(OutputStream res){
        try {
            if(res != null) {
                res.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
