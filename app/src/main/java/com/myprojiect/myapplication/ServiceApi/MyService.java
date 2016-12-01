package com.myprojiect.myapplication.ServiceApi;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by liangwanbo on 2016/11/18.
 * 在项目中由于后台返回的数据有可能缺失取值的字段
 * 在这里未做其他的返回值封装，请自行修改和扩展
 * 文件的上传可用@Multipart和@MapPart的批量文件上传
 * 在这里不再做介绍
 *
 *
 * Observable<String>订阅的值可以是对象和其他类型
 */

public interface MyService {

    /**
     * 以查询参数为请求体
     */
    @POST("login")
    Observable<String> getLogin(@Query("phone") String phone,@Query("password") String password);


    /**
     * 结束直播
     */
    @POST("liveRoom/overLive")
    Observable<String> Endlive();

    /**
     * 使用表单和Map集合结合使用的请求体
     */

    @FormUrlEncoded
    @POST("login")
    Observable<String> getloginthree(@FieldMap Map<String,String> map);

    /**
     * Map集合的请求体
     */

    //此方法未写代码调用
    @POST("login")
    Observable<String> getzcLogin(@QueryMap Map<String,String> map);

    /**
     * 直播首页列表
     */
    @POST("liveRoom/list")
    Observable<String> getlivelist(@QueryMap Map<String,String> map);

    @POST("liveRoom/list")
    Observable<String> getlivelists(@Query("startPage") String startPage,@Query("pageNum") String pageNum);

    /**
     * 老师信息
     *
     *
     */
    @GET("teacher/getTeacherIndex")
    Observable<String> getTeacherInfo(@Query("userId") String userId);


    /**
     * 添加关注
     * @follower==roomid
     */
    @GET("follow/insertFollowComment")
    Observable<String> guanzhu(@Query("follower") String follower);

    /**
     * 删除视屏
     *
     */

    @GET("video/deleteVideo")
    Observable<String> deleteVedio(@Query("ids") String ids);



}
