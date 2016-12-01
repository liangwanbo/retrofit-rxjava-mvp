package com.myprojiect.myapplication.Wight;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @date： 2016/5/24.
 * @FileName: com.myprojiect.myapplication.Wight.GlideUtils.java
 * @author: liangwanbo
 * @Description:
 */
public class GlideUtils {
    /**
     * 加载普通的图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context,String url,ImageView imageView)
    {
        Glide.with(context)
                .load(url)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }



    /**
     * 加载圆角的图片
     * @param context
     * @param url
     * @param corner
     * @param imageView
     */
    public static void loadRoundedCorners(Context context,String url,int corner,ImageView imageView){
        Glide.with(context)
                .load(url).bitmapTransform(new RoundedCornersTransformation(context, corner,0))
                .into(imageView);
    }

    /**
     * 设置模糊图片(默认方式)
     * @param context
     * @param url
     * @param imageView
     */


    public static  void loadBlurImage(Context context,String url,ImageView imageView){

        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context)).into(imageView);
    }

    /**
     * 加载带圆角的模糊图片
     * @param context
     * @param url
     * @param corner
     * @param imageView
     */
    public static  void loadBlurImage(Context context,String url,int corner,ImageView imageView){
        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context), new RoundedCornersTransformation(context,corner,0)).into(imageView);
    }

}
