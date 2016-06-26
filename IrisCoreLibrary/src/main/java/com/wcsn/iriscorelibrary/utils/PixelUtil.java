package com.wcsn.iriscorelibrary.utils;

import com.ImaginationUnlimited.library.app.CoreLibrary;
import com.ImaginationUnlimited.library.utils.log.Logger;


/**
 * 像素工具
 *
 * @author wangheng
 */
public class PixelUtil {
    /**
     * dip转换成px
     *
     * @param dipValue dip值
     * @return px
     */
    public static int dip2px(float dipValue) {
        try {
            final float scale = CoreLibrary.getInstance().getContext()
                    .getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("PixelUtil", "dip2px occur Exception::" + e);
        }
        return (int) dipValue;
    }

    /**
     * px 转换成dip
     *
     * @param pxValue 像素值
     * @return dip
     */
    public static int px2dip(float pxValue) {
        final float scale = CoreLibrary.getInstance().getContext()
                .getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * PX转换成SP
     *
     * @param pxValue 像素值
     * @return sp
     */
    public static int px2sp(float pxValue) {
        final float fontScale = CoreLibrary.getInstance().getContext().
                getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * SP转换为PX
     *
     * @param spValue sp值
     * @return px
     */
    public static int sp2px(float spValue) {
        final float fontScale = CoreLibrary.getInstance().getContext()
                .getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
