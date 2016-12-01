package com.myprojiect.myapplication.netutil;


import com.myprojiect.myapplication.Cookie.CookiesManager;
import com.myprojiect.myapplication.Cookie.LogInterceptor;
import com.myprojiect.myapplication.common.Constan;
import com.myprojiect.myapplication.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * Created by liangwanbo on 2016/11/18.
 */
public class RetrofitHttp {


    private static final int DEFAULT_TIMEOUT = 5;

    private static  RetrofitHttp retrofitHttp;

    public RetrofitHttp(){

    }

    //获取单利
    public static RetrofitHttp getInstance(){
        if(retrofitHttp==null){
            synchronized (RetrofitHttp.class){
                if(retrofitHttp==null){
                    retrofitHttp=new RetrofitHttp();
                }
            }

        }

        return retrofitHttp;
    }




// .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
   /* public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }*/


    /**
     * Created by liangwanbo on 2016/11/18.
     *
     */


    public static OkHttpClient getokhttp(){

        LogInterceptor interceptor = new LogInterceptor();
        interceptor.setLevel(LogInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .cookieJar(new CookiesManager(retrofit.getContext()))//设置Cookie
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS).build();

        return client;
    }




    /**
     * Created by liangwanbo on 2016/11/18.
     *
     */


    public Retrofit initRetrofitWithHeader(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(getokhttp())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constan.url)
                .build();
        return retrofit;
    }

}
