package jbink.appnapps.fittingmodel.model.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.CustomDialog;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-22.
 */
public class ModelDetailActivity extends AppCompatActivity{
    ViewPager mPager;
    Context mContext;

    ImageView[] mIvIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_detail);

        mContext = ModelDetailActivity.this;

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
        layoutBinding();
    }

    private void layoutBinding() {
        mPager = (ViewPager)findViewById(R.id.model_detail_pager);
        mIvIndex = new ImageView[]{
                (ImageView)findViewById(R.id.home_pager_idx_0),
                (ImageView)findViewById(R.id.home_pager_idx_1),
                (ImageView)findViewById(R.id.home_pager_idx_2),
                (ImageView)findViewById(R.id.home_pager_idx_3),
                (ImageView)findViewById(R.id.home_pager_idx_4),
                (ImageView)findViewById(R.id.home_pager_idx_5)
        };

        ((TextView)findViewById(R.id.model_detail_pager_tv_0)).setText(Html.fromHtml((getResources().getString(R.string.pager_txt_0))));
        ((TextView)findViewById(R.id.model_detail_pager_tv_1)).setText(Html.fromHtml(getResources().getString(R.string.pager_txt_1)));
        ((TextView)findViewById(R.id.model_detail_pager_tv_2)).setText(Html.fromHtml(getResources().getString(R.string.pager_txt_2)));

        for(int i=0 ; i<6 ; i++){
            ImageView iv = new ImageView(this);
            if(i%2 == 0) {
                iv.setBackgroundResource(R.drawable.pager_img_0);
            }else {
                iv.setBackgroundResource(R.drawable.pager_img_0);
            }
            mPager.addView(iv);
        }
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0 ; i<mIvIndex.length ; i++){
                    mIvIndex[i].setBackgroundResource(R.drawable.pager_index);
                }
                mIvIndex[position].setBackgroundResource(R.drawable.pager_index_on);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.setAdapter(new pagerAdapter(getLayoutInflater()));
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.model_detail_btn_back :
                finish();
                break;
            case R.id.model_detail_btn_ok :
                notiDialog();
                break;
        }
    }


    public class pagerAdapter extends PagerAdapter {
        LayoutInflater inflater;

        public pagerAdapter(LayoutInflater inflate) {
            inflater = inflate;
        }

        @Override
        public int getCount() {
            return 6;
        }
        //ViewPager가 현재 보여질 Item(View객체)를 생성할 필요가 있는 때 자동으로 호출
        //쉽게 말해, 스크롤을 통해 현재 보여져야 하는 View를 만들어냄.
        //첫번째 파라미터 : ViewPager
        //두번째 파라미터 : ViewPager가 보여줄 View의 위치(가장 처음부터 0,1,2,3...)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            //새로운 View 객체를 Layoutinflater를 이용해서 생성
            //만들어질 View의 설계는 res폴더>>layout폴더>>viewpater_childview.xml 레이아웃 파일 사용
            view = inflater.inflate(R.layout.row_viewpager, null);

            //만들어진 View안에 있는 ImageView 객체 참조
            //위에서 inflated 되어 만들어진 view로부터 findViewById()를 해야 하는 것에 주의.
            ImageView img = (ImageView) view.findViewById(R.id.row_img_view_pager);

            //ImageView에 현재 position 번째에 해당하는 이미지를 보여주기 위한 작업
            //현재 position에 해당하는 이미지를 setting
            int id = getResources().getIdentifier("pager_img_"+ position, "drawable", "jbink.appnapps.fittingmodel");
            img.setBackgroundResource(id);

            //ViewPager에 만들어 낸 View 추가
            container.addView(view);
            //Image가 세팅된 View를 리턴
            return view;

        }

        //화면에 보이지 않은 View는파쾨를 해서 메모리를 관리함.
        //첫번째 파라미터 : ViewPager
        //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
        //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //ViewPager에서 보이지 않는 View는 제거
            //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
            container.removeView((View) object);
        }


        //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
        @Override
        public boolean isViewFromObject(View v, Object obj) {
            return v == obj;
        }


    }




    private CustomDialog notiDlg;
    private void notiDialog(){
        Random r = new Random();
        String title = "알림";
        final String content = "김서진 님이 (주)코코스토리 모델구인을 수락했습니다.\n\n시작일:2016.4.21(목), 13:00~16:00(3시간)";
        notiDlg = new CustomDialog.Builder(mContext)
                .title(title)
                .content(content)
                .leftListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notiDlg.dismiss();
                        finish();
                    }
                })
                .leftText("확인")
                .build();
        notiDlg.show();
    }

}
