package com.myprojiect.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myprojiect.myapplication.Text.LoginPerstener;
import com.myprojiect.myapplication.Text.LoginView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/18.
 */

public class LoginActivity extends Activity implements LoginView{


    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.lines)
    LinearLayout lines;
    @Bind(R.id.bnt_denglu)
    Button bntDenglu;

    private LoginPerstener perstener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ButterKnife.bind(this);
        init();


    }

    public void init(){
        perstener=new LoginPerstener(this,this);
    }

    @OnClick(R.id.bnt_denglu)
    public void onClick() {
       // perstener.GetLogin(0,phone.getText().toString(),password.getText().toString());
       // perstener.getdata(phone.getText().toString(),password.getText().toString());

        Map<String ,String> map=new HashMap<>();
        map.put("phone",phone.getText().toString());
        map.put("password",password.getText().toString());

        perstener.getloginfour(map);
    }

    @Override
    public void onSucess(String s) {

        SharedPreferences shar=getSharedPreferences("NMCR",0);
        SharedPreferences.Editor editor=shar.edit();
        editor.putString("info",s);
        editor.commit();

        Log.e("sucess",s);
        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
        LoginActivity.this.finish();
    }

    @Override
    public void onFile(String e) {

    }

    @Override
    public void showprogress() {
        Toast.makeText(LoginActivity.this,"加载中",Toast.LENGTH_LONG).show();

    }

    @Override
    public void hideprogress() {
        Toast.makeText(LoginActivity.this,"加载结束",Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=perstener){
            perstener.onDestory();
        }
    }
}
