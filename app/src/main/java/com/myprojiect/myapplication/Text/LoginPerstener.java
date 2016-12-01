package com.myprojiect.myapplication.Text;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.myprojiect.myapplication.netutil.RetrofitTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/18.
 */

public class LoginPerstener extends persenter {

    private Context mContext;

    private LoginView mLoginView;
    private RetrofitTask mNewsTask;

    public LoginPerstener(Context mContext,LoginView mLoginView){
        this.mContext=mContext;
        this.mLoginView=mLoginView;
        mNewsTask=new RetrofitTask();

    }



    /**
     * 登录
     */
    public void GetLogin(int type,String phone,String password){
        mNewsTask.Getdata(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mLoginView.showprogress();
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.onFile(e.getMessage());
                mLoginView.hideprogress();
            }

            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    try {
                        JSONObject json=new JSONObject(s);
                        int resultCode = json.getInt("resultCode");
                        String resultMessage = json.getString("resultMessage");
                        if (resultCode != 100) {
                            Toast.makeText(mContext, resultMessage, Toast.LENGTH_LONG).show();
                            mLoginView.onSucess(resultMessage);
                        }else{
                            mLoginView.onSucess(resultMessage);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.e("Login",s);
                    mLoginView.hideprogress();
                }


            }
        },type,phone,password);
    }



    /**
     * 登录2
     *
     */

    public void getdata(String phone,String password){
        mNewsTask.getlogin(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mLoginView.showprogress();
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.onFile(e.getMessage());
                mLoginView.hideprogress();
            }

            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    mLoginView.onSucess(s);
                    mLoginView.hideprogress();
                }

            }
        },phone,password);

    }


    /**
     * 登录3
     *
     */

    public void getloginfour(Map<String,String> map){

        mNewsTask.getMapLogin(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mLoginView.showprogress();
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.onFile(e.getMessage());
                mLoginView.hideprogress();
            }

            @Override
            public void onNext(String s) {

                Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
                if(!TextUtils.isEmpty(s)){
                    try {
                        JSONObject json=new JSONObject(s);
                        int resultCode = json.getInt("resultCode");
                        String resultMessage = json.getString("resultMessage");
                        if (resultCode != 100) {
                            Toast.makeText(mContext, resultMessage, Toast.LENGTH_LONG).show();
                            mLoginView.onSucess(s);
                        }else{
                            mLoginView.onSucess(resultMessage);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.e("Login",s);
                    mLoginView.hideprogress();
                }


            }
        }, map);

    }




    /**
     * 结束直播
     *
     */

    public void Endlive(){
        mNewsTask.getendlive(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mLoginView.onFile(e.getMessage());
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
                if(!TextUtils.isEmpty(s)){
                    mLoginView.onSucess(s);
                    //mLoginView.hideprogress();
                }
            }
        });
    }





    @Override
    public void onDestory() {
        mLoginView=null;
        mContext=null;
        mNewsTask=null;
    }
}
