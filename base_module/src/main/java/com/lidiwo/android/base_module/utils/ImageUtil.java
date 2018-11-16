package com.lidiwo.android.base_module.utils;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 17:18
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class ImageUtil {
    private ImageUtil(){
    }

    private static class ImageHolder{
        private final static ImageUtil INSTANCE=new ImageUtil();
    }

    public static ImageUtil with(){
        return ImageHolder.INSTANCE;
    }






}
