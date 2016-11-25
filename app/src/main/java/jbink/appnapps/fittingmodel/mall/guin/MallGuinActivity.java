package jbink.appnapps.fittingmodel.mall.guin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.MallMainActivity;
import jbink.appnapps.fittingmodel.util.CustomPopup;
import jbink.appnapps.fittingmodel.util.DatePaickerActivity;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;

/**
 * Created by user on 2016-11-21.
 */
public class MallGuinActivity extends AppCompatActivity{
    private final int STYLE_LENGTH = 10;
    private final int REQ_DATE_START = 7778;
    private final int REQ_DATE_END = 7779;
    private final int REQ_TIME = 7775;

    Context mContext;
    TextView[] mTvStyles;
    TextView[] mTvSex;
    TextView mTvAges, mTvPay;
    TextView mTvTime, mTvStartDate, mTvEndDate;
    HashMap<Integer, Integer> mMapIds = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_guin);

        mContext = MallGuinActivity.this;

        layoutBinding();
        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

    }

    public void onClick(View v){
        ArrayList<String> dd;
        switch (v.getId()){
            case R.id.mall_guin_btn_ok :
                Intent intent = new Intent(mContext, MallMainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mall_guin_tv_style_0 :
                break;
            case R.id.mall_guin_tv_sex_man :
                setSexBackground(0);
                break;
            case R.id.mall_guin_tv_sex_woman :
                setSexBackground(1);
                break;
            case R.id.mall_guin_tv_start_date :
                intent = new Intent(mContext, DatePaickerActivity.class);
                startActivityForResult(intent, REQ_DATE_START);
                break;
            case R.id.mall_guin_tv_end_date :
                intent = new Intent(mContext, DatePaickerActivity.class);
                startActivityForResult(intent, REQ_DATE_END);
                break;
            case R.id.mall_guin_tv_time :
                dd = new ArrayList<String>();
                dd.add("평일 오전");
                dd.add("평일 오후");
                dd.add("평일 종일");
                dd.add("주말 오전");
                dd.add("주말 오후");
                dd.add("주말 종일");
                dd.add("상관없음");
                intent = new Intent(mContext, CustomPopup.class);
                intent.putStringArrayListExtra("datas", dd);
                startActivityForResult(intent, REQ_TIME);
                break;
            case R.id.mall_guin_tv_age :
                dd = new ArrayList<String>();
                dd.add("유아");
                dd.add("초등학생");
                dd.add("중학생");
                dd.add("고등학생");
                dd.add("20대 초반");
                dd.add("20대 중반");
                dd.add("20대 후반");
                dd.add("30대 초반");
                dd.add("30대 중반");
                dd.add("30대 후반");
                dd.add("40대 초반");
                dd.add("40대 중반");
                dd.add("40대 후반");
                dd.add("50대 초반");
                dd.add("50대 중반");
                dd.add("50대 후반");
                dd.add("60대 초반");
                dd.add("60대 중반");
                dd.add("60대 후반");
                Intent intent1 = new Intent(mContext, CustomPopup.class);
                intent1.putStringArrayListExtra("datas", dd);
                startActivityForResult(intent1, GlobalValues.POPUP_REQUEST);
                break;
            case R.id.mall_guin_tv_pay :
                ArrayList<String> dd2 = new ArrayList<String>();
                dd2.add("시급");
                dd2.add("일급");
                dd2.add("주급");
                dd2.add("월급");
                Intent intent2 = new Intent(mContext, CustomPopup.class);
                intent2.putStringArrayListExtra("datas", dd2);
                startActivityForResult(intent2, 1561);
                break;
        }
    }
    public void onStyleClick(View v){
        setSubMenuTextColor(mMapIds.get(v.getId()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GlobalValues.POPUP_REQUEST){
            if(resultCode == GlobalValues.POPUP_RESULT){
                mTvAges.setText(data.getStringExtra("returnValue"));
            }
        }else if(requestCode == 1561){
            if(resultCode == GlobalValues.POPUP_RESULT){
                mTvPay.setText(data.getStringExtra("returnValue"));
            }
        }else if (requestCode == REQ_DATE_START){
            if(resultCode == RESULT_OK){
                mTvStartDate.setText(data.getStringExtra("date"));
            }
        }
        else if (requestCode == REQ_DATE_END){
            if(resultCode == RESULT_OK) {
                mTvEndDate.setText(data.getStringExtra("date"));
            }
        }
        else if (requestCode == REQ_TIME){
            if(resultCode == GlobalValues.POPUP_RESULT) {
                mTvTime.setText(data.getStringExtra("returnValue"));
            }
        }
    }



    public void setSubMenuTextColor(int selected_id){
        for(int i=0 ; i<mTvStyles.length ; i++){
            mTvStyles[i].setBackgroundResource(R.drawable.btn_select_category);
            mTvStyles[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mTvStyles[selected_id].setBackgroundResource(R.drawable.btn_select_category_on);
        mTvStyles[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.white));
    }
    public void setSexBackground(int selected_id){
        for(int i=0 ; i<mTvSex.length ; i++){
            mTvSex[i].setBackgroundResource(R.drawable.btn_select_category);
            mTvSex[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mTvSex[selected_id].setBackgroundResource(R.drawable.btn_select_category_on);
        mTvSex[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.white));
    }

    private void layoutBinding(){
        mTvSex = new TextView[]{
                (TextView) findViewById(R.id.mall_guin_tv_sex_man),
                (TextView) findViewById(R.id.mall_guin_tv_sex_woman)
        };
        mTvStyles = new TextView[STYLE_LENGTH];
        for(int i=0 ; i<mTvStyles.length ; i++){
            int txtTv = getResources().getIdentifier("mall_guin_tv_style_"+i, "id", getPackageName());
            mTvStyles[i] = (TextView)findViewById(txtTv);
            mTvStyles[i].setTypeface(GlobalValues.getFont(mContext));
            mMapIds.put(mTvStyles[i].getId(), i);
        }
        for(int i=0 ; i<8  ; i++) {
            int txtId = getResources().getIdentifier("mall_guin_txt_" + i, "id", getPackageName());
            ((TextView) findViewById(txtId)).setTypeface(GlobalValues.getFont(mContext));
        }
        mTvAges = (TextView) findViewById(R.id.mall_guin_tv_age);
        mTvAges.setTypeface(GlobalValues.getFont(mContext));
        mTvPay = (TextView) findViewById(R.id.mall_guin_tv_pay);
        mTvPay.setTypeface(GlobalValues.getFont(mContext));
        mTvTime = (TextView) findViewById(R.id.mall_guin_tv_time);
        mTvTime.setTypeface(GlobalValues.getFont(mContext));
        mTvStartDate = (TextView) findViewById(R.id.mall_guin_tv_start_date);
        mTvStartDate.setTypeface(GlobalValues.getFont(mContext));
        mTvEndDate = (TextView) findViewById(R.id.mall_guin_tv_end_date);
        mTvEndDate.setTypeface(GlobalValues.getFont(mContext));

        ((TextView) findViewById(R.id.mall_guin_tv_shop_name)).setText(SharedPreUtil.getShopName(mContext));
        ((TextView) findViewById(R.id.mall_guin_txt_category)).setText(getIntent().getStringExtra("category"));
        ((TextView) findViewById(R.id.mall_guin_txt_category)).setTypeface(GlobalValues.getFont(mContext));
        ((TextView) findViewById(R.id.mall_guin_txt_category_sub)).setText(getIntent().getStringExtra("category_sub"));
        ((TextView) findViewById(R.id.mall_guin_txt_category_sub)).setTypeface(GlobalValues.getFont(mContext));
    }
}
