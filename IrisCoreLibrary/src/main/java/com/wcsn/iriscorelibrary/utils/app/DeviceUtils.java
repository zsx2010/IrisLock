package com.wcsn.iriscorelibrary.utils.app;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.ImaginationUnlimited.library.app.CoreLibrary;
import com.ImaginationUnlimited.library.utils.log.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 *  设备信息工具类
 *
 *  @author wengheng
 *
 */
public class DeviceUtils {


    /**
     * 得到手机IMEI
     * @param context context
     * @return imei
     */
    public static String getImei(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if(tm != null) {
                String deviceId = tm.getDeviceId();
                if( ! TextUtils.isEmpty(deviceId))
                    return deviceId;
            }
        } catch(Exception e) {
            Logger.d(e.getMessage());
        }
        // return getMac(context);
        return "";
    }

    /**
     * 得到Mac地址
     * @param context context
     * @return mac地址
     */
    public static String getMac(Context context) {
        String mac = null;
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();
            if(info != null) {
                mac = info.getMacAddress();
                return mac;
            }
        }
        return UUID.randomUUID().toString();

    }

    /**
     * 获取androidid
     * @param context context
     * @return 如果AndroidID不为空，返回AndroidID，否则返回随机的UUID
     */
    public static String getAndroidId(Context context) {
        String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if(androidId != null)
            return androidId;
        return UUID.randomUUID().toString();
    }

    /**
     * 获取CPU序列号
     * @return CPU序列号(16位) 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            // 读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            // 查找CPU序列号
            for(int i = 1; i < 100; i ++ ) {
                str = input.readLine();
                if(str != null) {
                    // 查找到序列号所在行
                    if(str.indexOf("Serial") > - 1) {
                        // 提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        // 去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    // 文件结尾
                    break;
                }
            }
        } catch(IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * 获取设备型号
     * @return 设备型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备品牌
     * @return 设备品牌
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取OS版本号
     * @return OS版本号
     */
    public static String getOSversion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 打开拨号界面
     * @param number 拨打电话
     * @param context Context
     */
    public static void dial(String number, Context context) {
        Class<TelephonyManager> c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[])null);
            getITelephonyMethod.setAccessible(true);
            TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            Object iTelephony;
            iTelephony = getITelephonyMethod.invoke(tManager, (Object[])null);
            Method dial = iTelephony.getClass().getDeclaredMethod("dial", String.class);
            dial.invoke(iTelephony, number);
        } catch(Exception e) {
           Logger.i(e.getMessage());
        }
    }


    /**
     * getSystemAvaialbeMemorySize:获得系统可用内存信息. <br/>
     * @param context context
     * @return 可用内存
     */
    public static long getSystemAvaialbeMemory(Context context) {
        // 获得MemoryInfo对象
        MemoryInfo memoryInfo = new MemoryInfo();
        // 获得系统可用内存，保存在MemoryInfo对象上
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }

    /**
     * SDK版本大于Android 2.2
     * @return 运行时os版本是否大于2.2
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * SDK版本大于Android 2.3
     * @return 运行时os版本是否大于2.3
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * SDK版本大于Android3.0
     * @return 运行时os版本是否大于3.0
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * SDK版本大于Android3.1
     * @return 运行时os版本是否大于3.1
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * SDK版本大于Android4.0
     * @return 运行时os版本是否大于4.0
     */
    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * SDK版本大于Android4.0.3
     * @return 运行时os版本是否大于4.0.3
     */
    public static boolean hasIceCreamSandwichMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }

    /**
     * SDK版本大于Android4.1()
     * @return 运行时os版本是否大于4.1
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * SDK版本大于Android4.4(19)
     * @return 运行时os版本是否大于4.4
     */
    public static boolean hasKITKAT() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
    /**
     * 
     * hasKITKAT_WATCH:（20）Android 4.4W: KitKat for watches. <br/>
     *
     * @author wangheng
     * @return 运行时os版本是否大于4.4M
     */
    public static boolean hasKITKAT_WATCH(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }
    /**
     * 
     * hasL:是否大于等于LOLLIPOP（21）. <br/>
     *
     * @author wangheng
     * @return 运行时os版本是否大于5.0
     */
    public static boolean hasLOLLIPOP(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
    
    /**
     * getScreenWidth:得到屏幕宽度(像素点数). <br/>
     * 
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        return getDisplayMetrics(CoreLibrary.getInstance().getContext()).widthPixels;
    }

    /**
     * getScreenHeight:得到屏幕高度(像素点数). <br/>
     * 
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        return getDisplayMetrics(CoreLibrary.getInstance().getContext()).heightPixels;
    }

    /**
     * getDensity:得到像素密度 <br/>
     * 
     * @return 像素密度
     */
    public static float getDensity() {
        return getDisplayMetrics(CoreLibrary.getInstance().getContext()).density;
    }

    /**
     * getDensityDpi:得到每英寸的像素点数<br/>
     * 
     * @return 每英寸的像素点数
     */
    public static int getDensityDpi() {
        return getDisplayMetrics(CoreLibrary.getInstance().getContext()).densityDpi;
    }

    /**
     * 
     * getDisplayMetrics:返回DisplayMetrics对象，以方便得到屏幕相关信息. <br/>
     *
     * @return  返回DisplayMetrics对象
     */
    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        try{
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            if (display != null) {
                display.getMetrics(dm);
            } else {
                dm.setToDefaults();
            }
        }catch(Exception e){
            e.printStackTrace();
            Logger.e(String.valueOf(e.getMessage()));
        }
        return dm;
    }

    /**
     * 返回注册的网络运营商的名字  例如：中国移动
     * @return 网络运营商的名字
     */
    public static String getNetworkOperatorName() {
        try {
            TelephonyManager tm = (TelephonyManager) CoreLibrary.getInstance().getContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                String netOperator = tm.getNetworkOperatorName();
                if (!TextUtils.isEmpty(netOperator))
                    return netOperator;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d(e.getMessage());
        }
        return "";
    }

    /**
     * 获取SIM卡运营商的名字 例如： CMCC
     * @return IM卡运营商的名字
     */
    public static String getSimOperatorName() {
        try {
            TelephonyManager tm = (TelephonyManager) CoreLibrary.getInstance().getContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                String simOperator = tm.getSimOperatorName();
                if (!TextUtils.isEmpty(simOperator))
                    return simOperator;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d(e.getMessage());
        }
        return "";
    }

    /**
     * 是否挂载了外部存储设备
     * @return 是否挂载了外部存储设备
     */
    public static boolean isExternalStorageMounted(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
