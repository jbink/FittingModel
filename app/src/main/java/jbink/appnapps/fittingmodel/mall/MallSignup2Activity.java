package jbink.appnapps.fittingmodel.mall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.guin.MallGuinActivity;
import jbink.appnapps.fittingmodel.util.CustomDialog;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-16.
 */
public class MallSignup2Activity extends AppCompatActivity {

    private final int CATEGORY_LENGTH = 17;

    Context mContext;

    RelativeLayout[] mLayoutCategory;
    LinearLayout[] mLayoutSubCategory;
    TextView[] mTvArrow;

//    ImageButton[] mIbtnChild, mIbtnMan, mIbtnWoman, mIbtnMrs, mIbtnTheme, mIbtnSport;
    ImageButton[] mIbtnSubCategory;
    String mStrCategory = null;
//    TextView[] mTvChild, mTvMan, mTvWoman, mTvMrs, mTvTheme, mTvSportSubCategory;
    TextView[] mTvSubCategory;
    String mStrCategorySub = null;

    TextView mTvAuthNum, mTvPhoneNum, mTvShopName;

    EditText mEdtPw, mEdtIntroduce;

    Button aa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_signup_2);

        mContext = MallSignup2Activity.this;


        Toolbar toolbar = (Toolbar) findViewById(R.id.mall_signup_2_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));
        layoutBinding();
    }


    Handler authHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            authNumDialog("인증번호");
        }
    };

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.mall_signup_2_btn_auth_num :
                authHandler.sendEmptyMessageDelayed(0, 1000);
                break;
            case R.id.mall_signup_2_btn_next :
                if(emptyEdittext(mEdtPw.getText().toString())
                        || emptyEdittext(mEdtIntroduce.getText().toString())
                        || emptyEdittext(mStrCategory)
                        || emptyEdittext(mStrCategorySub)){
                    Toast.makeText(mContext, "모든 값을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
//                    return;
                }
                intent = new Intent(mContext, MallGuinActivity.class);
                intent.putExtra("category", mStrCategory);
                intent.putExtra("category_sub", mStrCategorySub);
                startActivity(intent);

                break;
            case R.id.mall_signup_2_btn_ok :
                break;
            case R.id.category_sel_child_0 :
                mStrCategory = "유아동";
                setLayoutVisible(0);
                break;
            case R.id.category_sel_man_1 :
                mStrCategory = "남성의류";
                setLayoutVisible(1);
                break;
            case R.id.category_sel_woman_2 :
                mStrCategory = "여성의류";
                setLayoutVisible(2);
                break;
            case R.id.category_sel_mrs_3 :
                mStrCategory = "미시, 주부";
                setLayoutVisible(3);
                break;
            case R.id.category_sel_theme_4 :
                mStrCategory = "테마";
                setLayoutVisible(4);
                break;
            case R.id.category_sel_sport_5 :
                mStrCategory = "스포츠";
                setLayoutVisible(5);
                break;
        }
    }

    public void setLayoutVisible(int idx){
        if(mLayoutSubCategory[idx].getVisibility() == View.VISIBLE){
            mLayoutSubCategory[idx].setVisibility(View.GONE);
            mTvArrow[idx].setText(getResources().getString(R.string.arrow_bottom));
            return;
        }
        for(int i=0 ; i<mLayoutSubCategory.length ; i++){
            mLayoutSubCategory[i].setVisibility(View.GONE);
            mTvArrow[i].setText(getResources().getString(R.string.arrow_bottom));
        }
        mTvArrow[idx].setText(getResources().getString(R.string.arrow_top));
        mLayoutSubCategory[idx].setVisibility(View.VISIBLE);
    }

    public void onSubMenuClick(View v){
        switch (v.getId()){
            case R.id.category_sub_txt_0 :
            case R.id.category_sub_ibtn_0 :
                setSubMenuTextColor(0); break;
            case R.id.category_sub_txt_1 :
            case R.id.category_sub_ibtn_1 :
                setSubMenuTextColor(1); break;
            case R.id.category_sub_txt_2 :
            case R.id.category_sub_ibtn_2 :
                setSubMenuTextColor(2); break;
            case R.id.category_sub_txt_3 :
            case R.id.category_sub_ibtn_3 :
                setSubMenuTextColor(3); break;
            case R.id.category_sub_txt_4 :
            case R.id.category_sub_ibtn_4 :
                setSubMenuTextColor(4); break;
            case R.id.category_sub_txt_5 :
            case R.id.category_sub_ibtn_5 :
                setSubMenuTextColor(5); break;
            case R.id.category_sub_txt_6 :
            case R.id.category_sub_ibtn_6 :
                setSubMenuTextColor(6); break;
            case R.id.category_sub_txt_7 :
            case R.id.category_sub_ibtn_7 :
                setSubMenuTextColor(7); break;
            case R.id.category_sub_txt_8 :
            case R.id.category_sub_ibtn_8 :
                setSubMenuTextColor(8); break;
            case R.id.category_sub_txt_9 :
            case R.id.category_sub_ibtn_9 :
                setSubMenuTextColor(9); break;
            case R.id.category_sub_txt_10 :
            case R.id.category_sub_ibtn_10 :
                setSubMenuTextColor(10); break;
            case R.id.category_sub_txt_11 :
            case R.id.category_sub_ibtn_11 :
                setSubMenuTextColor(11); break;
            case R.id.category_sub_txt_12 :
            case R.id.category_sub_ibtn_12:
                setSubMenuTextColor(12); break;
            case R.id.category_sub_txt_13 :
            case R.id.category_sub_ibtn_13 :
                setSubMenuTextColor(13); break;
            case R.id.category_sub_txt_14 :
            case R.id.category_sub_ibtn_14 :
                setSubMenuTextColor(14); break;
            case R.id.category_sub_txt_15 :
            case R.id.category_sub_ibtn_15 :
                setSubMenuTextColor(15); break;
            case R.id.category_sub_txt_16 :
            case R.id.category_sub_ibtn_16 :
                setSubMenuTextColor(16); break;
        }
    }

    public void setSubMenuTextColor(int selected_id){
        for(int i=0 ; i<mIbtnSubCategory.length ; i++){
            mIbtnSubCategory[i].setBackgroundResource(R.drawable.btn_category);
            mTvSubCategory[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mIbtnSubCategory[selected_id].setBackgroundResource(R.drawable.btn_category_on);
        mTvSubCategory[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.color_7131ad));

        mStrCategorySub = mTvSubCategory[selected_id].getText().toString();
    }

//    public void setSubMenuTextColor(int selected_id, int other_1, int other_2){
//        ((TextView)findViewById(selected_id)).setTextColor(ContextCompat.getColor(mContext, R.color.title_bg_color));
//        ((TextView)findViewById(other_1)).setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
//        ((TextView)findViewById(other_2)).setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
//    }
//    public void setSubMenuIbtnColor(int selected_id, int other_1, int other_2){
//        ((ImageButton)findViewById(selected_id)).setBackgroundResource(R.drawable.btn_category_on);
//        ((ImageButton)findViewById(other_1)).setBackgroundResource(R.drawable.btn_category);
//        ((ImageButton)findViewById(other_2)).setBackgroundResource(R.drawable.btn_category);
//    }



    private void layoutBinding(){
        mLayoutCategory = new RelativeLayout[]{
                (RelativeLayout)findViewById(R.id.category_sel_child_0),
                (RelativeLayout)findViewById(R.id.category_sel_man_1),
                (RelativeLayout)findViewById(R.id.category_sel_woman_2),
                (RelativeLayout)findViewById(R.id.category_sel_mrs_3),
                (RelativeLayout)findViewById(R.id.category_sel_theme_4),
                (RelativeLayout)findViewById(R.id.category_sel_sport_5)
        };
        mLayoutSubCategory = new LinearLayout[]{
                (LinearLayout)findViewById(R.id.category_layout_child_0),
                (LinearLayout)findViewById(R.id.category_layout_man_1),
                (LinearLayout)findViewById(R.id.category_layout_woman_2),
                (LinearLayout)findViewById(R.id.category_layout_mrs_3),
                (LinearLayout)findViewById(R.id.category_layout_theme_4),
                (LinearLayout)findViewById(R.id.category_layout_sport_5),
        };
        mTvArrow = new TextView[]{
                (TextView)findViewById(R.id.category_txt_arrow_0),
                (TextView)findViewById(R.id.category_txt_arrow_1),
                (TextView)findViewById(R.id.category_txt_arrow_2),
                (TextView)findViewById(R.id.category_txt_arrow_3),
                (TextView)findViewById(R.id.category_txt_arrow_4),
                (TextView)findViewById(R.id.category_txt_arrow_5),
        };
        mIbtnSubCategory = new ImageButton[CATEGORY_LENGTH];
        mTvSubCategory = new TextView[CATEGORY_LENGTH];
        for(int i=0 ; i<mIbtnSubCategory.length ; i++){
            int ibtn = getResources().getIdentifier("category_sub_ibtn_"+i, "id", "jbink.appnapps.fittingmodel");
            mIbtnSubCategory[i] = (ImageButton)findViewById(ibtn);
            int tv = getResources().getIdentifier("category_sub_txt_"+i, "id", "jbink.appnapps.fittingmodel");
            mTvSubCategory[i] = (TextView) findViewById(tv);
        }

        mTvShopName = (TextView)findViewById(R.id.mall_signup_2_tv_shop_name);
        mTvShopName.setTypeface(GlobalValues.getFont(mContext));
        mTvShopName.setText(getIntent().getStringExtra("shop_name"));
        mTvAuthNum = (TextView)findViewById(R.id.mall_signup_2_tv_auth_num);
        mTvAuthNum.setTypeface(GlobalValues.getFont(mContext));
        mTvPhoneNum = (TextView)findViewById(R.id.mall_signup_2_tv_phone_num);
        mTvPhoneNum.setTypeface(GlobalValues.getFont(mContext));
        mTvPhoneNum.setText(GlobalValues.getMyPhoneNumber(mContext));
        mEdtPw = (EditText) findViewById(R.id.mall_signup_2_edt_pw);
        mEdtPw.setTypeface(GlobalValues.getFont(mContext));
        mEdtIntroduce = (EditText) findViewById(R.id.mall_signup_2_edt_introduce);
        mEdtIntroduce.setTypeface(GlobalValues.getFont(mContext));
        for(int i=0 ; i<6 ; i++){
            int txtTv = getResources().getIdentifier("mall_signup_2_tv_"+i, "id", "jbink.appnapps.fittingmodel");
            ((TextView)findViewById(txtTv)).setTypeface(GlobalValues.getFont(mContext));
        }
//        ((TextView)findViewById(R.id.mall_signup_2_tv_0)).setTypeface(GlobalValues.getFont(mContext));
//        ((TextView)findViewById(R.id.mall_signup_2_tv_1)).setTypeface(GlobalValues.getFont(mContext));
//        ((TextView)findViewById(R.id.mall_signup_2_tv_2)).setTypeface(GlobalValues.getFont(mContext));
//        ((TextView)findViewById(R.id.mall_signup_2_tv_3)).setTypeface(GlobalValues.getFont(mContext));
//        ((TextView)findViewById(R.id.mall_signup_2_tv_4)).setTypeface(GlobalValues.getFont(mContext));
//        ((TextView)findViewById(R.id.mall_signup_2_tv_5)).setTypeface(GlobalValues.getFont(mContext));
    }

    private boolean emptyEdittext(String check){
        if (TextUtils.isEmpty(check))
            return true;
        else
            return false;
    }


    private CustomDialog authNumDlg;
    /**
     * @param _message : 해당메세지
     */
    private void authNumDialog( String _message){
        Random r = new Random();
        String message = _message;
        final String content = String.valueOf((r.nextInt(8999)+1000));
        authNumDlg = new CustomDialog(mContext, message, content,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authNumDlg.dismiss();
                        mTvAuthNum.setText(content);
                    }
                }, "확인");
        authNumDlg.show();
    }
















//                (ImageButton)findViewById(R.id.category_sub_ibtn_0),
//        };
//        mIbtnChild = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_child_ibtn_0),
//                (ImageButton)findViewById(R.id.category_child_ibtn_1),
//                (ImageButton)findViewById(R.id.category_child_ibtn_2)
//        };
//        mTvChild = new TextView[]{
//                (TextView)findViewById(R.id.category_child_txt_0),
//                (TextView)findViewById(R.id.category_child_txt_1),
//                (TextView)findViewById(R.id.category_child_txt_2)
//        };
//        mIbtnMan = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_man_ibtn_0),
//                (ImageButton)findViewById(R.id.category_man_ibtn_1),
//                (ImageButton)findViewById(R.id.category_man_ibtn_2)
//        };
//        mTvMan = new TextView[]{
//                (TextView)findViewById(R.id.category_man_txt_0),
//                (TextView)findViewById(R.id.category_man_txt_1),
//                (TextView)findViewById(R.id.category_man_txt_2)
//        };
//        mIbtnWoman = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_woman_ibtn_0),
//                (ImageButton)findViewById(R.id.category_woman_ibtn_1),
//                (ImageButton)findViewById(R.id.category_woman_ibtn_2)
//        };
//        mTvWoman = new TextView[]{
//                (TextView)findViewById(R.id.category_woman_txt_0),
//                (TextView)findViewById(R.id.category_woman_txt_1),
//                (TextView)findViewById(R.id.category_woman_txt_2)
//        };
//        mIbtnMrs = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_mrs_ibtn_0),
//                (ImageButton)findViewById(R.id.category_mrs_ibtn_1),
//                (ImageButton)findViewById(R.id.category_mrs_ibtn_2)
//        };
//        mTvMrs = new TextView[]{
//                (TextView)findViewById(R.id.category_mrs_txt_0),
//                (TextView)findViewById(R.id.category_mrs_txt_1),
//                (TextView)findViewById(R.id.category_mrs_txt_2)
//        };
//        mIbtnTheme = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_theme_ibtn_0),
//                (ImageButton)findViewById(R.id.category_theme_ibtn_1),
//                (ImageButton)findViewById(R.id.category_theme_ibtn_2)
//        };
//        mTvTheme = new TextView[]{
//                (TextView)findViewById(R.id.category_theme_txt_0),
//                (TextView)findViewById(R.id.category_theme_txt_1),
//                (TextView)findViewById(R.id.category_theme_txt_2)
//        };
//        mIbtnSport = new ImageButton[]{
//                (ImageButton)findViewById(R.id.category_sport_ibtn_0),
//                (ImageButton)findViewById(R.id.category_sport_ibtn_1),
//                (ImageButton)findViewById(R.id.category_sport_ibtn_2)
//        };
//        mTvSport = new TextView[]{
//                (TextView)findViewById(R.id.category_sport_txt_0),
//                (TextView)findViewById(R.id.category_sport_txt_1),
//                (TextView)findViewById(R.id.category_sport_txt_2)
//        };

//    case R.id.category_child_ibtn_0 :
//            case R.id.category_child_txt_0 :
//    setSubMenuTextColor(mTvChild[0].getId(), mTvChild[1].getId(), mTvChild[2].getId());
//    setSubMenuIbtnColor(mIbtnChild[0].getId(), mIbtnChild[1].getId(), mIbtnChild[2].getId());
//    break;
//    case R.id.category_child_ibtn_1 :
//            case R.id.category_child_txt_1 :
//    setSubMenuTextColor(mTvChild[1].getId(), mTvChild[0].getId(), mTvChild[2].getId());
//    setSubMenuIbtnColor(mIbtnChild[1].getId(), mIbtnChild[0].getId(), mIbtnChild[2].getId());
//    break;
//    case R.id.category_child_ibtn_2 :
//            case R.id.category_child_txt_2 :
//    setSubMenuTextColor(mTvChild[2].getId(), mTvChild[1].getId(), mTvChild[0].getId());
//    setSubMenuIbtnColor(mIbtnChild[2].getId(), mIbtnChild[1].getId(), mIbtnChild[0].getId());
//    break;
//    case R.id.category_man_ibtn_0 :
//            case R.id.category_man_txt_0 :
//    setSubMenuTextColor(mTvMan[0].getId(), mTvMan[1].getId(), mTvMan[2].getId());
//    setSubMenuIbtnColor(mIbtnMan[0].getId(), mIbtnMan[1].getId(), mIbtnMan[2].getId());
//    case R.id.category_man_ibtn_1 :
//            case R.id.category_man_txt_1 :
//    setSubMenuTextColor(mTvMan[1].getId(), mTvMan[0].getId(), mTvMan[2].getId());
//    setSubMenuIbtnColor(mIbtnMan[1].getId(), mIbtnMan[0].getId(), mIbtnMan[2].getId());
//    break;
//    case R.id.category_man_ibtn_2 :
//            case R.id.category_man_txt_2 :
//    setSubMenuTextColor(mTvMan[2].getId(), mTvMan[1].getId(), mTvMan[0].getId());
//    setSubMenuIbtnColor(mIbtnMan[2].getId(), mIbtnMan[1].getId(), mIbtnMan[0].getId());
//    break;
//    case R.id.category_woman_ibtn_0 :
//            case R.id.category_woman_txt_0 :
//    setSubMenuTextColor(mTvWoman[0].getId(), mTvWoman[1].getId(), mTvWoman[2].getId());
//    setSubMenuTextColor(mIbtnWoman[0].getId(), mIbtnWoman[1].getId(), mIbtnWoman[2].getId());
//    case R.id.category_woman_ibtn_1 :
//            case R.id.category_woman_txt_1 :
//    setSubMenuTextColor(mTvWoman[1].getId(), mTvWoman[0].getId(), mTvWoman[2].getId());
//    setSubMenuIbtnColor(mIbtnWoman[1].getId(), mIbtnWoman[0].getId(), mIbtnWoman[2].getId());
//    break;
//    case R.id.category_woman_ibtn_2 :
//            case R.id.category_woman_txt_2 :
//    setSubMenuTextColor(mTvWoman[2].getId(), mTvWoman[1].getId(), mTvWoman[0].getId());
//    setSubMenuIbtnColor(mIbtnWoman[2].getId(), mIbtnWoman[1].getId(), mIbtnWoman[0].getId());
//    break;
//    case R.id.category_mrs_ibtn_0 :
//            case R.id.category_mrs_txt_0 :
//    setSubMenuTextColor(mTvMrs[0].getId(), mTvMrs[1].getId(), mTvMrs[2].getId());
//    setSubMenuIbtnColor(mIbtnMrs[0].getId(), mIbtnMrs[1].getId(), mIbtnMrs[2].getId());
//    case R.id.category_mrs_ibtn_1 :
//            case R.id.category_mrs_txt_1 :
//    setSubMenuTextColor(mTvMrs[1].getId(), mTvMrs[0].getId(), mTvMrs[2].getId());
//    setSubMenuIbtnColor(mIbtnMrs[1].getId(), mIbtnMrs[0].getId(), mIbtnMrs[2].getId());
//    break;
//    case R.id.category_mrs_ibtn_2 :
//            case R.id.category_mrs_txt_2 :
//    setSubMenuTextColor(mTvMrs[2].getId(), mTvMrs[1].getId(), mTvMrs[0].getId());
//    setSubMenuIbtnColor(mIbtnMrs[2].getId(), mIbtnMrs[1].getId(), mIbtnMrs[0].getId());
//    break;
//    case R.id.category_theme_ibtn_0 :
//            case R.id.category_theme_txt_0 :
//    setSubMenuTextColor(mTvTheme[0].getId(), mTvTheme[1].getId(), mTvTheme[2].getId());
//    setSubMenuIbtnColor(mIbtnTheme[0].getId(), mIbtnTheme[1].getId(), mIbtnTheme[2].getId());
//    case R.id.category_theme_ibtn_1 :
//            case R.id.category_theme_txt_1 :
//    setSubMenuTextColor(mTvTheme[1].getId(), mTvTheme[0].getId(), mTvTheme[2].getId());
//    setSubMenuIbtnColor(mIbtnTheme[1].getId(), mIbtnTheme[0].getId(), mIbtnTheme[2].getId());
//    break;
//    case R.id.category_theme_ibtn_2 :
//            case R.id.category_theme_txt_2 :
//    setSubMenuTextColor(mTvTheme[2].getId(), mTvTheme[1].getId(), mTvTheme[0].getId());
//    setSubMenuIbtnColor(mIbtnTheme[2].getId(), mIbtnTheme[1].getId(), mIbtnTheme[0].getId());
//    break;
//    case R.id.category_sport_ibtn_0 :
//            case R.id.category_sport_txt_0 :
//    setSubMenuTextColor(mTvSport[0].getId(), mTvSport[1].getId(), mTvSport[2].getId());
//    setSubMenuIbtnColor(mIbtnSport[0].getId(), mIbtnSport[1].getId(), mIbtnSport[2].getId());
//    case R.id.category_sport_ibtn_1 :
//            case R.id.category_sport_txt_1 :
//    setSubMenuTextColor(mTvSport[1].getId(), mTvSport[0].getId(), mTvSport[2].getId());
//    setSubMenuIbtnColor(mIbtnSport[1].getId(), mIbtnSport[0].getId(), mIbtnSport[2].getId());
//    break;
//    case R.id.category_sport_ibtn_2 :
//            case R.id.category_sport_txt_2 :
//    setSubMenuTextColor(mTvSport[2].getId(), mTvSport[1].getId(), mTvSport[0].getId());
//    setSubMenuIbtnColor(mIbtnSport[2].getId(), mIbtnSport[1].getId(), mIbtnSport[0].getId());
//    break;
}
