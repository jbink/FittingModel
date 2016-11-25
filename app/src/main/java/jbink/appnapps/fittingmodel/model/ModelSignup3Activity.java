package jbink.appnapps.fittingmodel.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.model.multigallery.MultipleGalleryActivity;
import jbink.appnapps.fittingmodel.model.multigallery.SingleMediaScanner;
import jbink.appnapps.fittingmodel.util.CustomDialog;
import jbink.appnapps.fittingmodel.util.CustomPopup;
import jbink.appnapps.fittingmodel.util.DatePaickerActivity;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.ImageUtil;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;

/**
 * Created by user on 2016-11-23.
 */
public class ModelSignup3Activity extends AppCompatActivity{

    private final int CATEGORY_LENGTH = 29;
    private final int STYLE_LENGTH = 11;
    private final int REQ_PAY = 7775;
    private final int REQ_MODEL_SIGNUP_3 = 20003;

    Context mContext;
    TextView mTvPay;

    LinearLayout[] mLayoutSubCategory;
    TextView[] mTvArrow;

    ImageButton[] mIbtnSubCategory;
    String mStrCategory = null;
    TextView[] mTvSubCategory;
    String mStrCategorySub = null;
    Map<Integer, Integer> mMapIds = new HashMap<>();

    TextView[] mTvStyles;
    HashMap<Integer, Integer> mMapIdStyle = new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_signup_3);

        mContext = ModelSignup3Activity.this;
        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));

        layoutBinding();
    }

    private void layoutBinding() {

        mTvPay = (TextView)findViewById(R.id.model_signup_3_tv_pay);
        mTvPay.setTypeface(GlobalValues.getFont(mContext));

        mTvArrow = new TextView[6];
        for(int i=0 ; i<mTvArrow.length ; i++) {
            int id = getResources().getIdentifier("category_model_txt_arrow_" + i, "id", getPackageName());
            mTvArrow[i] = (TextView) findViewById(id);
        }
        mLayoutSubCategory = new LinearLayout[6];
        for(int i=0 ; i<mTvArrow.length ; i++) {
            int id = getResources().getIdentifier("category_model_layout_child_" + i, "id", getPackageName());
            mLayoutSubCategory[i] = (LinearLayout) findViewById(id);
        }

        mIbtnSubCategory = new ImageButton[CATEGORY_LENGTH];
        mTvSubCategory = new TextView[CATEGORY_LENGTH];
        for(int i=0 ; i<mIbtnSubCategory.length ; i++){
            int ibtn = getResources().getIdentifier("category_model_sub_ibtn_"+i, "id", getPackageName());
            mIbtnSubCategory[i] = (ImageButton)findViewById(ibtn);
            int tv = getResources().getIdentifier("category_model_sub_txt_"+i, "id", getPackageName());
            mTvSubCategory[i] = (TextView) findViewById(tv);

            mMapIds.put(mIbtnSubCategory[i].getId(), i);
            mMapIds.put(mTvSubCategory[i].getId(), i);
        }

        mTvStyles = new TextView[STYLE_LENGTH];
        for(int i=0 ; i<mTvStyles.length ; i++){
            int txtTv = getResources().getIdentifier("model_style_tv_style_"+i, "id", getPackageName());
            mTvStyles[i] = (TextView)findViewById(txtTv);
            mTvStyles[i].setTypeface(GlobalValues.getFont(mContext));
            mMapIdStyle.put(mTvStyles[i].getId(), i);
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

    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.category_model_layout_0 :
                setLayoutVisible(0); mStrCategory = "유아, 어린이";
                break;
            case R.id.category_model_layout_1 :
                setLayoutVisible(1); mStrCategory = "청소년";
                break;
            case R.id.category_model_layout_2 :
                setLayoutVisible(2); mStrCategory = "피팅";
                break;
            case R.id.category_model_layout_3 :
                setLayoutVisible(3); mStrCategory = "주부, 중년";
                break;
            case R.id.category_model_layout_4 :
                setLayoutVisible(4); mStrCategory = "부분";
                break;
            case R.id.category_model_layout_5:
                setLayoutVisible(5); mStrCategory = "외국인";
                break;
            case R.id.model_signup_3_btn_ok :
                if(TextUtils.isEmpty(mStrCategory) == true || TextUtils.isEmpty(mStrCategorySub) == true ){
                    Toast.makeText(mContext, "모든 값을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreUtil.setModelCategory(mContext, mStrCategory + " " +mStrCategorySub);

                intent = new Intent(mContext, ModelMainActivity.class);
                startActivity(intent);
                setResult(RESULT_OK);
                finish();

                break;
            case R.id.model_signup_3_tv_pay :
                ArrayList<String> dd = new ArrayList<String>();
                dd.add("시급 30,000원 이상");
                dd.add("시급 50,000원 이상");
                dd.add("시급 70,000원 이상");
                dd.add("시급 100,000원 이상");
                dd.add("상관없음");
                intent = new Intent(mContext, CustomPopup.class);
                intent.putStringArrayListExtra("datas", dd);
                startActivityForResult(intent, REQ_PAY);
                break;
        }
    }

    public void onSubMenuClick(View v){
        setSubMenuTextColor(mMapIds.get(v.getId()));
    }
    public void onStyleClick(View v){
        setStyleMenuTextColor(mMapIdStyle.get(v.getId()));
    }

    public void setStyleMenuTextColor(int selected_id){
        for(int i=0 ; i<mTvStyles.length ; i++){
            mTvStyles[i].setBackgroundResource(R.drawable.btn_select_category);
            mTvStyles[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mTvStyles[selected_id].setBackgroundResource(R.drawable.btn_select_category_on);
        mTvStyles[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.white));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_PAY){
            if(resultCode == GlobalValues.POPUP_RESULT){
                mTvPay.setText(data.getStringExtra("returnValue"));
            }
        }else if(requestCode == REQ_MODEL_SIGNUP_3){
            if(resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private boolean emptyEdittext(String check){
        if (TextUtils.isEmpty(check))
            return true;
        else
            return false;
    }


    private CustomDialog authNumDlg;
    /**
     * @param _title : 해당메세지
     */
    private void authNumDialog( String _title){
        Random r = new Random();
        String title = _title;
        final String content = String.valueOf((r.nextInt(8999)+1000));
        authNumDlg = new CustomDialog.Builder(mContext)
                .title(title)
                .content(content)
                .leftListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authNumDlg.dismiss();
                    }
                })
                .leftText("확인")
                .build();
        authNumDlg.show();
    }
}
