package jbink.appnapps.fittingmodel.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.CustomDialog;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;

/**
 * Created by user on 2016-11-23.
 */
public class ModelSignupActivity extends AppCompatActivity{
    private final int REQ_MODEL_SIGNUP_1 = 20001;

    Context mContext;
    TextView mTvClause, mTvAuthNum, mTvPhoneNum;
    EditText mEdtPw;

    boolean mBoolAuth = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_signup);

        mContext = ModelSignupActivity.this;
        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

        layoutBinding();
    }

    private void layoutBinding() {
        mTvClause = (TextView)findViewById(R.id.model_signup_txt_clause);
        mTvClause.setTypeface(GlobalValues.getFont(mContext));
        mTvAuthNum = (TextView)findViewById(R.id.model_signup_tv_auth_num);
        mTvAuthNum.setTypeface(GlobalValues.getFont(mContext));
        mTvPhoneNum = (TextView)findViewById(R.id.model_signup_tv_phone_num);
        mTvPhoneNum.setTypeface(GlobalValues.getFont(mContext));
        mTvPhoneNum.setText(GlobalValues.getMyPhoneNumber(mContext));
        String text = "로그인/회원가입시 이용약관 및 개인정보취급방침 및 위치정보이용약관에 동의하게 됩니다.";
        Spannable span = new SpannableString(text);
//        span.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(mContext, "스팬", Toast.LENGTH_SHORT).show();
//            }
//        }, 10, 14 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//
        span.setSpan(new UnderlineSpan(), 10, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(0xff2e86cb), 10, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        span.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(mContext, "스팬2", Toast.LENGTH_SHORT).show();
//            }
//        }, 17, 25 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//
        span.setSpan(new UnderlineSpan(), 17, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(0xff2e86cb), 17, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        span.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(mContext, "스팬2", Toast.LENGTH_SHORT).show();
//            }
//        }, 28, 36 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//
        span.setSpan(new UnderlineSpan(), 28, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(0xff2e86cb), 28, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvClause.append(span);
        mTvClause.setMovementMethod(LinkMovementMethod.getInstance());

        mEdtPw = (EditText)findViewById(R.id.model_signup_edt_pw);
        mEdtPw.setTypeface(GlobalValues.getFont(mContext));


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.model_signup_btn_next :
                if(mBoolAuth == false){
                    Toast.makeText(mContext, "인증 절차를 거치셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(emptyEdittext(mEdtPw.getText().toString())){
                    Toast.makeText(mContext, "모든 값을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreUtil.setId(mContext, mTvPhoneNum.getText().toString());
                SharedPreUtil.setPw(mContext, mEdtPw.getText().toString());

                Intent intent = new Intent(mContext, ModelSignup2Activity.class);
                startActivityForResult(intent, REQ_MODEL_SIGNUP_1);
                break;
            case R.id.model_signup_btn_auth_num :
                authNumDialog("인증번호");
                break;
        }
    }

    private boolean emptyEdittext(String check){
        if (TextUtils.isEmpty(check))
            return true;
        else
            return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_MODEL_SIGNUP_1){
            if(resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
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
                        mBoolAuth = true;
                        authNumDlg.dismiss();
                        mTvAuthNum.setText(content);
                    }
                })
                .leftText("확인")
                .build();
        authNumDlg.show();
    }


}
