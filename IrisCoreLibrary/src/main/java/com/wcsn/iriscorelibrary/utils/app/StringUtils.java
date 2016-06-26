package com.wcsn.iriscorelibrary.utils.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangheng
 * @describe 字符串工具类.
 * @date: 2015年4月24日 下午2:58:46 <br/>
 */
public class StringUtils {
    /**
     * isNullOrEmpty:如果字符串为null或者仅仅含有空格则返回true，否则返回false. <br/>
     *
     * @param str 待校验字符串
     *
     * @return 是否为null,空字符串或者仅仅包含空格
     *
     * @author wangheng
     */
    public static boolean isNullOrEmpty(String str) {
        if (null == str) {
            return true;
        }
        return str.trim().length() == 0;
    }

    /**
     * 给定的字符串是否是有效的Email
     * @param email 待校验字符串
     * @return 是否是有效的Email
     */
    public static boolean validateEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

}
