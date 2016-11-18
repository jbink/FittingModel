package jbink.appnapps.fittingmodel.mall;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-16.
 */
public class MallSignup2Activity extends AppCompatActivity {

    Context mContext;

    RelativeLayout[] mLayoutCategory;
    LinearLayout[] mLayoutSubCategory;
    TextView[] mTvArrow;

    ImageButton[] mIbtnChild, mIbtnMan, mIbtnWoman, mIbtnMrs, mIbtnTheme, mIbtnSport;
    TextView[] mTvChild, mTvMan, mTvWoman, mTvMrs, mTvTheme, mTvSport;

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



    public void onClick(View v){
        switch (v.getId()){
            case R.id.mall_signup_2_tv_auth_num :
                break;
            case R.id.category_sel_child_0 :
                setLayoutVisible(0);
                break;
            case R.id.category_sel_man_1 :
                setLayoutVisible(1);
                break;
            case R.id.category_sel_woman_2 :
                setLayoutVisible(2);
                break;
            case R.id.category_sel_mrs_3 :
                setLayoutVisible(3);
                break;
            case R.id.category_sel_theme_4 :
                setLayoutVisible(4);
                break;
            case R.id.category_sel_sport_5 :
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
            case R.id.category_child_ibtn_0 :
            case R.id.category_child_txt_0 :
                setSubMenuTextColor(mTvChild[0].getId(), mTvChild[1].getId(), mTvChild[2].getId());
                setSubMenuIbtnColor(mIbtnChild[0].getId(), mIbtnChild[1].getId(), mIbtnChild[2].getId());
                break;
            case R.id.category_child_ibtn_1 :
            case R.id.category_child_txt_1 :
                setSubMenuTextColor(mTvChild[1].getId(), mTvChild[0].getId(), mTvChild[2].getId());
                setSubMenuIbtnColor(mIbtnChild[1].getId(), mIbtnChild[0].getId(), mIbtnChild[2].getId());
                break;
            case R.id.category_child_ibtn_2 :
            case R.id.category_child_txt_2 :
                setSubMenuTextColor(mTvChild[2].getId(), mTvChild[1].getId(), mTvChild[0].getId());
                setSubMenuIbtnColor(mIbtnChild[2].getId(), mIbtnChild[1].getId(), mIbtnChild[0].getId());
                break;
            case R.id.category_man_ibtn_0 :
            case R.id.category_man_txt_0 :
                setSubMenuTextColor(mTvMan[0].getId(), mTvMan[1].getId(), mTvMan[2].getId());
                setSubMenuIbtnColor(mIbtnMan[0].getId(), mIbtnMan[1].getId(), mIbtnMan[2].getId());
            case R.id.category_man_ibtn_1 :
            case R.id.category_man_txt_1 :
                setSubMenuTextColor(mTvMan[1].getId(), mTvMan[0].getId(), mTvMan[2].getId());
                setSubMenuIbtnColor(mIbtnMan[1].getId(), mIbtnMan[0].getId(), mIbtnMan[2].getId());
                break;
            case R.id.category_man_ibtn_2 :
            case R.id.category_man_txt_2 :
                setSubMenuTextColor(mTvMan[2].getId(), mTvMan[1].getId(), mTvMan[0].getId());
                setSubMenuIbtnColor(mIbtnMan[2].getId(), mIbtnMan[1].getId(), mIbtnMan[0].getId());
                break;
            case R.id.category_woman_ibtn_0 :
            case R.id.category_woman_txt_0 :
                setSubMenuTextColor(mTvWoman[0].getId(), mTvWoman[1].getId(), mTvWoman[2].getId());
                setSubMenuTextColor(mIbtnWoman[0].getId(), mIbtnWoman[1].getId(), mIbtnWoman[2].getId());
            case R.id.category_woman_ibtn_1 :
            case R.id.category_woman_txt_1 :
                setSubMenuTextColor(mTvWoman[1].getId(), mTvWoman[0].getId(), mTvWoman[2].getId());
                setSubMenuIbtnColor(mIbtnWoman[1].getId(), mIbtnWoman[0].getId(), mIbtnWoman[2].getId());
                break;
            case R.id.category_woman_ibtn_2 :
            case R.id.category_woman_txt_2 :
                setSubMenuTextColor(mTvWoman[2].getId(), mTvWoman[1].getId(), mTvWoman[0].getId());
                setSubMenuIbtnColor(mIbtnWoman[2].getId(), mIbtnWoman[1].getId(), mIbtnWoman[0].getId());
                break;
            case R.id.category_mrs_ibtn_0 :
            case R.id.category_mrs_txt_0 :
                setSubMenuTextColor(mTvMrs[0].getId(), mTvMrs[1].getId(), mTvMrs[2].getId());
                setSubMenuIbtnColor(mIbtnMrs[0].getId(), mIbtnMrs[1].getId(), mIbtnMrs[2].getId());
            case R.id.category_mrs_ibtn_1 :
            case R.id.category_mrs_txt_1 :
                setSubMenuTextColor(mTvMrs[1].getId(), mTvMrs[0].getId(), mTvMrs[2].getId());
                setSubMenuIbtnColor(mIbtnMrs[1].getId(), mIbtnMrs[0].getId(), mIbtnMrs[2].getId());
                break;
            case R.id.category_mrs_ibtn_2 :
            case R.id.category_mrs_txt_2 :
                setSubMenuTextColor(mTvMrs[2].getId(), mTvMrs[1].getId(), mTvMrs[0].getId());
                setSubMenuIbtnColor(mIbtnMrs[2].getId(), mIbtnMrs[1].getId(), mIbtnMrs[0].getId());
                break;
            case R.id.category_theme_ibtn_0 :
            case R.id.category_theme_txt_0 :
                setSubMenuTextColor(mTvTheme[0].getId(), mTvTheme[1].getId(), mTvTheme[2].getId());
                setSubMenuIbtnColor(mIbtnTheme[0].getId(), mIbtnTheme[1].getId(), mIbtnTheme[2].getId());
            case R.id.category_theme_ibtn_1 :
            case R.id.category_theme_txt_1 :
                setSubMenuTextColor(mTvTheme[1].getId(), mTvTheme[0].getId(), mTvTheme[2].getId());
                setSubMenuIbtnColor(mIbtnTheme[1].getId(), mIbtnTheme[0].getId(), mIbtnTheme[2].getId());
                break;
            case R.id.category_theme_ibtn_2 :
            case R.id.category_theme_txt_2 :
                setSubMenuTextColor(mTvTheme[2].getId(), mTvTheme[1].getId(), mTvTheme[0].getId());
                setSubMenuIbtnColor(mIbtnTheme[2].getId(), mIbtnTheme[1].getId(), mIbtnTheme[0].getId());
                break;
            case R.id.category_sport_ibtn_0 :
            case R.id.category_sport_txt_0 :
                setSubMenuTextColor(mTvSport[0].getId(), mTvSport[1].getId(), mTvSport[2].getId());
                setSubMenuIbtnColor(mIbtnSport[0].getId(), mIbtnSport[1].getId(), mIbtnSport[2].getId());
            case R.id.category_sport_ibtn_1 :
            case R.id.category_sport_txt_1 :
                setSubMenuTextColor(mTvSport[1].getId(), mTvSport[0].getId(), mTvSport[2].getId());
                setSubMenuIbtnColor(mIbtnSport[1].getId(), mIbtnSport[0].getId(), mIbtnSport[2].getId());
                break;
            case R.id.category_sport_ibtn_2 :
            case R.id.category_sport_txt_2 :
                setSubMenuTextColor(mTvSport[2].getId(), mTvSport[1].getId(), mTvSport[0].getId());
                setSubMenuIbtnColor(mIbtnSport[2].getId(), mIbtnSport[1].getId(), mIbtnSport[0].getId());
                break;
        }
    }

    public void setSubMenuTextColor(int selected_id, int other_1, int other_2){
        ((TextView)findViewById(selected_id)).setTextColor(ContextCompat.getColor(mContext, R.color.title_bg_color));
        ((TextView)findViewById(other_1)).setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        ((TextView)findViewById(other_2)).setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
    }
    public void setSubMenuIbtnColor(int selected_id, int other_1, int other_2){
        ((ImageButton)findViewById(selected_id)).setBackgroundResource(R.drawable.btn_category_on);
        ((ImageButton)findViewById(other_1)).setBackgroundResource(R.drawable.btn_category);
        ((ImageButton)findViewById(other_2)).setBackgroundResource(R.drawable.btn_category);
    }



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
        mIbtnChild = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_child_ibtn_0),
                (ImageButton)findViewById(R.id.category_child_ibtn_1),
                (ImageButton)findViewById(R.id.category_child_ibtn_2)
        };
        mTvChild = new TextView[]{
                (TextView)findViewById(R.id.category_child_txt_0),
                (TextView)findViewById(R.id.category_child_txt_1),
                (TextView)findViewById(R.id.category_child_txt_2)
        };
        mIbtnMan = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_man_ibtn_0),
                (ImageButton)findViewById(R.id.category_man_ibtn_1),
                (ImageButton)findViewById(R.id.category_man_ibtn_2)
        };
        mTvMan = new TextView[]{
                (TextView)findViewById(R.id.category_man_txt_0),
                (TextView)findViewById(R.id.category_man_txt_1),
                (TextView)findViewById(R.id.category_man_txt_2)
        };
        mIbtnWoman = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_woman_ibtn_0),
                (ImageButton)findViewById(R.id.category_woman_ibtn_1),
                (ImageButton)findViewById(R.id.category_woman_ibtn_2)
        };
        mTvWoman = new TextView[]{
                (TextView)findViewById(R.id.category_woman_txt_0),
                (TextView)findViewById(R.id.category_woman_txt_1),
                (TextView)findViewById(R.id.category_woman_txt_2)
        };
        mIbtnMrs = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_mrs_ibtn_0),
                (ImageButton)findViewById(R.id.category_mrs_ibtn_1),
                (ImageButton)findViewById(R.id.category_mrs_ibtn_2)
        };
        mTvMrs = new TextView[]{
                (TextView)findViewById(R.id.category_mrs_txt_0),
                (TextView)findViewById(R.id.category_mrs_txt_1),
                (TextView)findViewById(R.id.category_mrs_txt_2)
        };
        mIbtnTheme = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_theme_ibtn_0),
                (ImageButton)findViewById(R.id.category_theme_ibtn_1),
                (ImageButton)findViewById(R.id.category_theme_ibtn_2)
        };
        mTvTheme = new TextView[]{
                (TextView)findViewById(R.id.category_theme_txt_0),
                (TextView)findViewById(R.id.category_theme_txt_1),
                (TextView)findViewById(R.id.category_theme_txt_2)
        };
        mIbtnSport = new ImageButton[]{
                (ImageButton)findViewById(R.id.category_sport_ibtn_0),
                (ImageButton)findViewById(R.id.category_sport_ibtn_1),
                (ImageButton)findViewById(R.id.category_sport_ibtn_2)
        };
        mTvSport = new TextView[]{
                (TextView)findViewById(R.id.category_sport_txt_0),
                (TextView)findViewById(R.id.category_sport_txt_1),
                (TextView)findViewById(R.id.category_sport_txt_2)
        };
    }
}
