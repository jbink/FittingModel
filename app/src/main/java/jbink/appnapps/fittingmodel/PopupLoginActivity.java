package jbink.appnapps.fittingmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;

/**
 * Created by user on 2016-11-17.
 */
public class PopupLoginActivity extends AppCompatActivity{

    Context mContext;
    TextView mTvPhone;
    EditText mEdtPw;
    Button mBtnCheck;
    boolean mBoolCheck = false;

    ImageButton[] mIbtnType;
    TextView[] mTvType;
    boolean mBoolType = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_login);

        mContext = PopupLoginActivity.this;

        mBoolCheck = SharedPreUtil.getAutoLogin(mContext);

        layoutBinding();


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_btn_back :
                finish();
                break;
            case R.id.login_btn_login :
                if(mBoolType == false){
                    Toast.makeText(mContext, "가입 유형을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                validationLogin();
                break;
            case R.id.login_btn_check :
            case R.id.login_txt_check :
                if(mBoolCheck == true) {
                    mBoolCheck = false;
                    SharedPreUtil.setAutoLoginCheck(mContext, mBoolCheck);
                    mBtnCheck.setBackgroundResource(R.drawable.checkbox_up);
                }else {
                    mBoolCheck = true;
                    SharedPreUtil.setAutoLoginCheck(mContext, mBoolCheck);
                    mBtnCheck.setBackgroundResource(R.drawable.checkbox_selected);
                }
                break;
            case R.id.login_popup_ibtn_mall :
            case R.id.login_popup_txt_mall :
                mBoolType = true;
                setSubMenuTextColor(0);
                SharedPreUtil.setLoginType(mContext, GlobalValues.MALL);
                break;
            case R.id.login_popup_ibtn_model :
            case R.id.login_popup_txt_model :
                mBoolType = true;
                setSubMenuTextColor(1);
                SharedPreUtil.setLoginType(mContext, GlobalValues.MODEL);
                break;
        }
    }

    private void validationLogin(){
        Intent intent;
        String id = mTvPhone.getText().toString();
        String pw = mEdtPw.getText().toString();
        if(id.equals(SharedPreUtil.getId(mContext))){
            if(pw.equals(SharedPreUtil.getPw(mContext))){
                if(SharedPreUtil.getAutoLoginCheck(mContext) == true){
                    SharedPreUtil.setAutoLogin(mContext, true);
                }else{
                    SharedPreUtil.setAutoLogin(mContext, false);
                }
                setResult(RESULT_OK);
                finish();
            }else{
                Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.",  Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(mContext, "일치하는 ID가 없습니다.",  Toast.LENGTH_SHORT).show();
            finish();
        }
//        if(id.equals(SharedPreUtil.getMallId(mContext))){
//            if(pw.equals(SharedPreUtil.getMallPw(mContext))){
//                if(SharedPreUtil.getAutoLogin(mContext) == true){
//                    SharedPreUtil.setAutoLoginCheck(mContext, true);
//                }else{
//                    SharedPreUtil.setAutoLoginCheck(mContext, false);
//                }
//                intent = new Intent();
//                intent.putExtra("type", "mall");
//                setResult(RESULT_OK, intent);
//                finish();
//            }else{
//                Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.",  Toast.LENGTH_SHORT).show();
//            }
//        }else if(id.equals(SharedPreUtil.getModelId(mContext))){
//            if(pw.equals(SharedPreUtil.getModelPw(mContext))){
//                if(SharedPreUtil.getAutoLogin(mContext) == true){
//                    SharedPreUtil.setAutoLoginCheck(mContext, true);
//                }else{
//                    SharedPreUtil.setAutoLoginCheck(mContext, false);
//                }
//                intent = new Intent();
//                intent.putExtra("type", "model");
//                setResult(RESULT_OK, intent);
//                finish();
//            }else{
//                Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.",  Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(mContext, "일치하는 ID가 없습니다.",  Toast.LENGTH_SHORT).show();
//            finish();
//        }
    }

    private void layoutBinding() {
        mTvPhone = (TextView)findViewById(R.id.login_tv_phone);
        mTvPhone.setText(GlobalValues.getMyPhoneNumber(mContext));
        mTvPhone.setTypeface(GlobalValues.getFont(mContext));

        mEdtPw = (EditText)findViewById(R.id.login_edt_pw);
        mEdtPw.setTypeface(GlobalValues.getFont(mContext));
        mBtnCheck = (Button) findViewById(R.id.login_btn_check);

        if(mBoolCheck == true)
            mBtnCheck.setBackgroundResource(R.drawable.checkbox_selected);
        else
            mBtnCheck.setBackgroundResource(R.drawable.checkbox_up);

        ((TextView)findViewById(R.id.login_popup_txt_0)).setTypeface(GlobalValues.getFont(mContext));
        ((TextView)findViewById(R.id.login_popup_txt_1)).setTypeface(GlobalValues.getFont(mContext));
        ((TextView)findViewById(R.id.login_popup_title)).setTypeface(GlobalValues.getFont(mContext));
        ((TextView)findViewById(R.id.login_txt_check)).setTypeface(GlobalValues.getFont(mContext));
        ((Button)findViewById(R.id.login_btn_back)).setTypeface(GlobalValues.getFont(mContext));
        ((Button)findViewById(R.id.login_btn_login)).setTypeface(GlobalValues.getFont(mContext));

        mIbtnType = new ImageButton[]{
                (ImageButton)findViewById(R.id.login_popup_ibtn_mall),
                (ImageButton)findViewById(R.id.login_popup_ibtn_model),
        };
        mTvType = new TextView[]{
                (TextView)findViewById(R.id.login_popup_txt_mall),
                (TextView)findViewById(R.id.login_popup_txt_model),
        };
    }

    public void setSubMenuTextColor(int selected_id){
        for(int i=0 ; i<mIbtnType.length ; i++){
            mIbtnType[i].setBackgroundResource(R.drawable.btn_category);
            mTvType[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_7b7b7b));
        }
        mIbtnType[selected_id].setBackgroundResource(R.drawable.btn_category_on);
        mTvType[selected_id].setTextColor(ContextCompat.getColor(mContext, R.color.color_7131ad));
    }
}
