package com.myprojiect.myapplication.Text;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface LoginView extends MvpView{

    void onSucess(String s);
    void onFile(String e);
    void showprogress();
    void hideprogress();


}
