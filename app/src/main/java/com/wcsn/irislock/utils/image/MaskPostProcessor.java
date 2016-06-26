package com.wcsn.irislock.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * Created by suiyue on 16/5/24.
 */
public class MaskPostProcessor extends BasePostprocessor {
    private static final String TAG = "MaskPostProcessor";
    private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context context;
    private int maskId;

    static {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    public MaskPostProcessor(Context context, int maskId) {
        this.context = context.getApplicationContext();
        Log.e(TAG, context.getPackageName());
        this.maskId = maskId;
    }


    @Override
    public void process(Bitmap dest, Bitmap source) {

        Log.e(TAG, source.getWidth()+"");
//        source = ImageProcessor.createSquareBitmap(source, PixelUtil.dip2px(100f));
        Log.e(TAG, source.getWidth()+"");
        int width = source.getWidth();
        Bitmap result =
                Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Drawable mask = getMaskDrawable(context, maskId);
        Canvas canvas = new Canvas(result);
        mask.setBounds(0, 0, width, width);
        mask.draw(canvas);
        canvas.drawBitmap(source, 0, 0, mPaint);
        dest.setHasAlpha(true);
        super.process(dest, result);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey("mask=" + context.getResources().getResourceEntryName(maskId));
    }

    public static Drawable getMaskDrawable(Context context, int maskId) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getDrawable(maskId);
        } else {
            drawable = context.getResources().getDrawable(maskId);
        }

        if (drawable == null) {
            throw new IllegalArgumentException("maskId is invalid");
        }

        return drawable;
    }

}
