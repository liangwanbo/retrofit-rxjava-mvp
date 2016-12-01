package com.myprojiect.myapplication.netutil;

import com.myprojiect.myapplication.ServiceApi.MyService;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 62523 on 2016/11/18.
 *
 * 一般用第一种，初次封装比较简单后期会维护更新
 */
public class RetrofitTask {



    /**
     * 一种写法
     *
     *
     */
    public void getlogin(Subscriber<String> subscriber,String phone,String password){
        MyService myService=RetrofitHttp.getInstance().initRetrofitWithHeader().create(MyService.class);
        Observable<String> observable=myService.getLogin(phone,password);
        Subscriber(observable,subscriber);

    }

    /**
     * 结束直播
     *
     */

    public void getendlive(Subscriber<String> subscriber){
        MyService myService=RetrofitHttp.getInstance().initRetrofitWithHeader().create(MyService.class);
        Observable<String> observable=myService.Endlive();
        Subscriber(observable,subscriber);
    }



    /**
     * 以Map结合为请求体
     *
     *
     */

    public void getMapLogin(Subscriber<String> subscriber, Map<String,String> map){
        MyService myService=RetrofitHttp.getInstance().initRetrofitWithHeader().create(MyService.class);
        Observable<String> observable=myService.getloginthree(map);
        Subscriber(observable,subscriber);

    }


    /**
     * 另一种写法适用于多个列表的页面
     *
     *
     */

    public void Getdata(Subscriber<String> subscriber,int type,String phone,String password){

        MyService myService=RetrofitHttp.getInstance().initRetrofitWithHeader().create(MyService.class);
        Observable<String> observable=null;
       switch (type){
           case 0:
               observable=myService.getLogin(phone,password);
               break;

       }

        if(observable!=null){
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);

        }

    }




    public void Subscriber(Observable<String> observable,Subscriber<String> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
