package jbink.appnapps.fittingmodel.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import jbink.appnapps.fittingmodel.LoginActivity;
import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.ranking.MallRankingFragment;
import jbink.appnapps.fittingmodel.mall.recommend.MallRecommendFragment;
import jbink.appnapps.fittingmodel.mall.searchshop.MallSearchFragment;
import jbink.appnapps.fittingmodel.model.entire.ModelEntireFragment;
import jbink.appnapps.fittingmodel.model.interest.ModelInterestFragment;
import jbink.appnapps.fittingmodel.model.ranking.ModelRankingFragment;
import jbink.appnapps.fittingmodel.model.recommend.ModelRecommendFragment;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;

/**
 * Created by user on 2016-11-14.
 */
public class ModelMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Context mContext;
    DrawerLayout mDrawer;

    //상단 탭
    ImageButton[] mIbtnTabs;
    TextView[] mTvTabs;

    TextView mTvName, mTvCategory, mTvIntroduce, mTvBusinessNumber, mTvAddrss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_main);
        mContext = ModelMainActivity.this;

        layoutBinding();
        setTabsBack(0);
    }

    private void layoutBinding() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.model_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView)toolbar.findViewById(R.id.model_main_txt_title)).setTypeface(GlobalValues.getBoldFont(mContext));

        mIbtnTabs = new ImageButton[]{
                (ImageButton)(findViewById(R.id.model_main_tab_ibtn_0)),
                (ImageButton)(findViewById(R.id.model_main_tab_ibtn_1)),
                (ImageButton)(findViewById(R.id.model_main_tab_ibtn_2)),
                (ImageButton)(findViewById(R.id.model_main_tab_ibtn_3)),
                (ImageButton)(findViewById(R.id.model_main_tab_ibtn_4))
        };
        mTvTabs = new TextView[]{
                (TextView)(findViewById(R.id.model_main_tab_txt_0)),
                (TextView)(findViewById(R.id.model_main_tab_txt_1)),
                (TextView)(findViewById(R.id.model_main_tab_txt_2)),
                (TextView)(findViewById(R.id.model_main_tab_txt_3)),
                (TextView)(findViewById(R.id.model_main_tab_txt_4))
        };

        mDrawer = (DrawerLayout) findViewById(R.id.model_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.icon_list);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.model_drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.model_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        mTvName = (TextView)headerLayout.findViewById(R.id.model_nav_name);
        mTvName.setTypeface(GlobalValues.getFont(mContext));
        mTvName.setText(SharedPreUtil.getModelName(mContext));
        mTvCategory = (TextView)headerLayout.findViewById(R.id.model_nav_category);
        mTvCategory.setTypeface(GlobalValues.getFont(mContext));
        mTvCategory.setText(SharedPreUtil.getModelCategory(mContext));
//        mTvUrl = (TextView)headerLayout.findViewById(R.id.mall_nav_shop_url);
//        mTvUrl.setTypeface(GlobalValues.getFont(mContext));
//        mTvUrl.setText(SharedPreUtil.getUrl(mContext));
        mTvIntroduce = (TextView)headerLayout.findViewById(R.id.model_nav_introduce);
        mTvIntroduce.setTypeface(GlobalValues.getFont(mContext));
        mTvIntroduce.setText(SharedPreUtil.getModelIntroduce(mContext));
        mTvAddrss = (TextView)headerLayout.findViewById(R.id.model_nav_address);
        mTvAddrss.setTypeface(GlobalValues.getFont(mContext));
        mTvAddrss.setText(SharedPreUtil.getModelAddress(mContext));

        fragmentReplace(0);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        if (id == R.id.model_logout) {
            SharedPreUtil.setAutoLogin(mContext, false);
            intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.model_withdraw) {
            SharedPreUtil.setAutoLogin(mContext, false);
            SharedPreUtil.setModelCategory(mContext, "");
            SharedPreUtil.setModelAddress(mContext, "");
            SharedPreUtil.setModelIntroduce(mContext, "");
            SharedPreUtil.setModelName(mContext, "");
            SharedPreUtil.setModelPicUrl(mContext, "");
            SharedPreUtil.setId(mContext, "");
            SharedPreUtil.setPw(mContext, "");
        }
//        else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.model_main_ibtn_search_shop :
//                Intent intent = new Intent(mContext, MallSearchShopOptionFragment.class);
//                startActivity(intent);
                break;
            case R.id.model_main_tab_layout_0 :
            case R.id.model_main_tab_ibtn_0 :
            case R.id.model_main_tab_txt_0 :
                setTabsBack(0); fragmentReplace(0);
                break;
            case R.id.model_main_tab_layout_1 :
            case R.id.model_main_tab_ibtn_1 :
            case R.id.model_main_tab_txt_1 :
                setTabsBack(1); fragmentReplace(1);
                break;
            case R.id.model_main_tab_layout_2 :
            case R.id.model_main_tab_ibtn_2 :
            case R.id.model_main_tab_txt_2 :
                setTabsBack(2); fragmentReplace(2);
                break;
            case R.id.model_main_tab_layout_3 :
            case R.id.model_main_tab_ibtn_3 :
            case R.id.model_main_tab_txt_3 :
                setTabsBack(3); fragmentReplace(3);
                break;
            case R.id.model_main_tab_layout_4 :
            case R.id.model_main_tab_ibtn_4 :
            case R.id.model_main_tab_txt_4 :
                setTabsBack(4);  fragmentReplace(4);
                break;

        }
    }

    public void setTabsBack(int index){
        for(int i=0 ; i<mIbtnTabs.length ; i++){
            mIbtnTabs[i].setBackgroundResource(R.drawable.icon_01 + i);
            mTvTabs[i].setTypeface(GlobalValues.getLightFont(mContext));
        }
        mIbtnTabs[index].setBackgroundResource(R.drawable.icon_sel_01 + index);
        mTvTabs[index].setTypeface(GlobalValues.getBoldFont(mContext));
    }


    public void fragmentReplace(int reqNewFragmentIndex) {
        Fragment newFragment = null;
        newFragment = getFragment(reqNewFragmentIndex);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.model_content_frame, newFragment, "main" + String.valueOf(reqNewFragmentIndex));
        transaction.commitAllowingStateLoss();

        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
        switch (idx) {
            case 0:
                newFragment = new ModelRecommendFragment().newInstance();
                break;
            case 1:
                newFragment = new MallSearchFragment().newInstance();
                break;
            case 2:
                newFragment = new ModelEntireFragment().newInstance();
                break;
            case 3:
                newFragment = new ModelInterestFragment().newInstance();
                break;
            case 4://모델랭킹
                newFragment = new ModelRankingFragment().newInstance();
                break;
//            case 3:
//                newFragment = new MyPageFragment().newInstance(page);
//                break;
        }
        return newFragment;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("종료")
                    .setMessage("어플을 종료 하시겠습니까?")
                    .setNegativeButton("아니요", null)
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }
                    }).show();
        }
    }
}
