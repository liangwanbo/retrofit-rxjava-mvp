package com.myprojiect.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myprojiect.myapplication.Text.LoginPerstener;
import com.myprojiect.myapplication.Text.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/22.
 */

public class endliveActivity extends Activity implements LoginView {

    @Bind(R.id.textc)
    TextView textc;
    private LoginPerstener loginPerstener;
    @Bind(R.id.bnt_tuichi)
    Button bntTuichi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endlive_view);
        ButterKnife.bind(this);
        loginPerstener = new LoginPerstener(this, this);


    }

    @OnClick(R.id.bnt_tuichi)
    public void onClick() {
        loginPerstener.Endlive();

    }

    @Override
    public void onSucess(String s) {
        textc.setText(s);
    }

    @Override
    public void onFile(String e) {
        textc.setText(e);
    }

    @Override
    public void showprogress() {
        Toast.makeText(endliveActivity.this,"开始加载",Toast.LENGTH_LONG).show();

    }

    @Override
    public void hideprogress() {

    }
}
