package jbink.appnapps.fittingmodel.mall.guin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-21.
 */
public class MallGuinActivity extends AppCompatActivity{
    private final int STYLE_LENGTH = 10;

    Context mContext;
    TextView[] mTvStyles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_guin);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.mall_guin_btn_ok :
                break;
            case R.id.mall_guin_tv_style_0 :
                break;
        }
    }

    public void setSubMenuTextColor(int selected_id){
        for(int i=0 ; i<mTvStyles.length ; i++){
            mTvStyles[i].setBackgroundResource(R.drawable.btn_category);
            mTvStyles[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mTvStyles[selected_id].setBackgroundResource(R.drawable.btn_category_on);
        mTvStyles[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.white));
    }

    private void layoutBinding(int idx){
        mTvStyles = new TextView[STYLE_LENGTH];
        for(int i=0 ; i<mTvStyles.length ; i++){
            int txtTv = getResources().getIdentifier("mall_guin_tv_style_"+i, "id", getPackageName());
            mTvStyles[i] = (TextView)findViewById(txtTv);
        }
    }
}
