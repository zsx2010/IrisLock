package com.wcsn.irislock.utils.image;


import com.wcsn.irislock.image.FrescoLoader;

/**
 * Created by someHui on 16/4/22.
 * TODO may return other Loader which most fit the TARGET
 */
public class ImageLoaderFactory {
    private static ImageLoaderWrapper sInstance;

    private ImageLoaderFactory() {

    }
    public static <T,K extends ImageLoaderWrapper.ImageOption>  ImageLoaderWrapper<T,K>  getLoader(T t) {
        if (sInstance == null) {
            synchronized (ImageLoaderFactory.class) {
                if (sInstance == null) {
                    sInstance = new FrescoLoader();
                }
            }
        }
        return sInstance;
    }

    public static ImageLoaderWrapper.ImageOption newOption(int resizeW,int resizeH){

        return getLoader(null).newOption(resizeW,resizeH);
    }

}
