package com.wcsn.irislock.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by suiyue on 2016/5/28 0028.
 */
public class StringUtils {

    /**字符串如果为null或者仅含有空格则返回空
     * @param str 待校验字符串
     * @return 是否为空或仅含有空格
     */
    public static boolean isNullOrEmpty(String str) {
        if (null == str) {
            return true;
        }
        return str.trim().length() == 0;
    }
    /**
     * @param phoneNum 待校验字符串
     * @return 是否为有效电话号码
     */
    public static boolean validatePhoneNum(String phoneNum) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }


}
