package com.huixue.study;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*设置不显示标题栏*/
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();





        /*点击底部导航栏*/
        RelativeLayout home=(RelativeLayout)findViewById(R.id.tab_main_home_layout);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"点击了首页",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
