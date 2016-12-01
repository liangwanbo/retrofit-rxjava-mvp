package com.myprojiect.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ScallActivity extends Activity implements View.OnClickListener{

    private ImageView image_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scallview_layout);
        init();
    }

    public void init(){
        image_a=(ImageView)findViewById(R.id.image_a);
        image_a.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.image_a:
                Intent intent = new Intent(ScallActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }

    }
}
