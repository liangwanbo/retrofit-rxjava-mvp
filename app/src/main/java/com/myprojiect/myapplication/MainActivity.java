package com.myprojiect.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.ssc)
    Button ssc;
    @Bind(R.id.abb)
    Button abb;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.textzhujie)
    TextView textzhujie;
    @Bind(R.id.retlive)
    RelativeLayout retlive;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.logininfo)
    TextView logininfo;
    @Bind(R.id.texttd)
    Button texttd;
    @Bind(R.id.live)
    Button live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences shar = getSharedPreferences("NMCR", 0);
        String info = shar.getString("info", "");
        logininfo.setText(info);

    }

    @OnClick({R.id.ssc, R.id.abb, R.id.retlive, R.id.texttd,R.id.live})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ssc:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.abb:
                Intent intents = new Intent(MainActivity.this, endliveActivity.class);
                startActivity(intents);

                break;
            case R.id.retlive:

                Toast.makeText(MainActivity.this, "点击了" + "retlive", Toast.LENGTH_LONG).show();
                break;

            case R.id.texttd:

                Intent intt = new Intent(MainActivity.this, ScallActivity.class);
                startActivity(intt);
                break;

            case R.id.live:


                break;
        }
    }


}
