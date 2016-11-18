package jbink.appnapps.fittingmodel.mall;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.CustomDialog;
import jbink.appnapps.fittingmodel.util.CustomPopup;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-16.
 */
public class MallSignupActivity extends AppCompatActivity {
    Context mContext;

    EditText mEditShopName;
    TextView mTvShopBuisness;
    EditText[] mEditShopNumber;

    boolean mBoolAuth = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_signup);

        mContext = MallSignupActivity.this;

        layoutBinding();



        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

    }

    private void layoutBinding() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mall_signup_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mEditShopName = (EditText)findViewById(R.id.mall_signup_shop_name);
        mEditShopName.setTypeface(GlobalValues.getFont(mContext));
        mTvShopBuisness = (TextView) findViewById(R.id.mall_signup_txt_business);
        mTvShopBuisness.setTypeface(GlobalValues.getFont(mContext));
        mEditShopNumber = new EditText[]{
                (EditText)findViewById(R.id.mall_signup_shop_number_0),
                (EditText)findViewById(R.id.mall_signup_shop_number_1),
                (EditText)findViewById(R.id.mall_signup_shop_number_2)
        };
        for(int i=0 ; i<mEditShopNumber.length ; i++) {
            mEditShopNumber[i].setTypeface(GlobalValues.getFont(mContext));
        }
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.mall_signup_txt_business :
                ArrayList<String> dd = new ArrayList<String>();
                dd.add("자영업 - 상시근로자 수 5인 미만");
                dd.add("소기업 - 상시근로자 수 10인 미만");
                dd.add("중소기업 - 상시근로자 수 300인 미만");
                dd.add("중견기업 - 상시근로자 수 1000인 미만");
                dd.add("대기업 - 연매출 5조원 이상");
                Intent intent1 = new Intent(mContext, CustomPopup.class);
                intent1.putStringArrayListExtra("datas", dd);
                startActivityForResult(intent1, GlobalValues.POPUP_REQUEST);
                break;
            case R.id.mall_signup_tv_auth :
                for(int i=0 ; i<mEditShopNumber.length ; i++){
                    if(TextUtils.isEmpty(mEditShopNumber[i].getText().toString())){
                        Toast.makeText(mContext, "사업자 번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                notiDialog("성공", "인증 되었습니다.");

                break;
            case R.id.mall_signup_btn_next :
                Intent intent = new Intent(mContext, MallSignup2Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GlobalValues.POPUP_REQUEST){
            if(resultCode == GlobalValues.POPUP_RESULT){
                mTvShopBuisness.setText(data.getStringExtra("returnValue"));
            }
        }
    }

    private CustomDialog notiDlg;
    /**
     * @param _message : 해당메세지
     */
    private void notiDialog( String _message, String _content){
        String message = _message;
        String content = _content;
        notiDlg = new CustomDialog(mContext, message, content,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notiDlg.dismiss();
                        mBoolAuth = true;
                    }
                }, "확인");
        notiDlg.show();
    }
}
