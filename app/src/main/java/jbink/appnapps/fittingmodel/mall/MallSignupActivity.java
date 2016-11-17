package jbink.appnapps.fittingmodel.mall;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.CustomPopup;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-16.
 */
public class MallSignupActivity extends AppCompatActivity {
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_signup);

        mContext = MallSignupActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.mall_signup_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.mall_signup_tv_auth :
                ArrayList<String> dd = new ArrayList<String>();
                dd.add("aaa");
                dd.add("bbb");
                dd.add("ccc");
                dd.add("ddd");
                Intent intent1 = new Intent(mContext, CustomPopup.class);
                intent1.putStringArrayListExtra("datas", dd);
                startActivity(intent1);
                break;
            case R.id.mall_signup_btn_next :
                Intent intent = new Intent(mContext, MallSignup2Activity.class);
                startActivity(intent);
                break;
        }
    }

}
