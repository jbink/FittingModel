package jbink.appnapps.fittingmodel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import jbink.appnapps.fittingmodel.db.MallDBTable;
import jbink.appnapps.fittingmodel.db.ModelDBTable;
import jbink.appnapps.fittingmodel.mall.MallMainActivity;
import jbink.appnapps.fittingmodel.mall.MallSignupActivity;
import jbink.appnapps.fittingmodel.mall.common.MallCommonData;
import jbink.appnapps.fittingmodel.model.ModelMainActivity;
import jbink.appnapps.fittingmodel.model.ModelSignupActivity;
import jbink.appnapps.fittingmodel.model.common.ModelCommonData;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.SharedPreUtil;
import jbink.appnapps.mylibrary.PermissionListener;
import jbink.appnapps.mylibrary.jbinkPermission;

/**
 * Created by user on 2016-11-11.
 */
public class LoginActivity extends AppCompatActivity {
    private final int REQ_LOGIN = 111;
    private final int REQ_MALL_SIGNUP = 112;
    private final int REQ_MODEL_SIGNUP = 113;

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;

        new jbinkPermission(mContext)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(R.string.permission_msg)
                .setRationaleConfirmText("확인")
                .setPermission( Manifest.permission.READ_PHONE_STATE)
                .check();


        //앱 처음 실행시 DB 생성
        if(SharedPreUtil.getFirstPlay(mContext) == true){
            Log.d("where", "처음실행");
            SharedPreUtil.setFirstPlay(mContext, false);
            //추천몰
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("1", "(주)모모스타일", "120,000원", "피팅모델분 구합니다.", "2016.11.14(월), 4시간", "false", "true"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("2", "(주)나비웨딩", "100,000원", "단아한 웨딩모델 구합니다.", "2016.11.26(토), 2시간", "false", "true"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("3", "클래식스토어", "100,000원", "클래식한 분위기 쇼핑몰입니다.", "2016.11.25(금), 8시간", "false", "true"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("5", "(주)뷰티플웨딩", "80,000원", "웨딩드레스 잘 어울리시는 분", "2016.12.1(목), 3시간", "false", "true"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("6", "샤랄라공주", "70,000원", "피팅모델분 구합니다.", "2016.12.10(토), 2시간", "false", "true"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("7", "(주)스카이웨딩", "70,000원", "야외촬영 웨딩드레스 모델분 구합니다.", "2016.11.30(수), 7시간", "false", "true"));

            //전체모델
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("7", "쿠라쿠라", "90,000원", "피팅모델분 구합니다.", "2016.11.14(월), 4시간", "false", "false"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("8", "피라지오", "100,000원", "단아한 웨딩모델 구합니다.", "2016.11.26(토), 2시간", "false", "false"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("9", "가나다라", "50,000원", "클래식한 분위기 쇼핑몰입니다.", "2016.11.25(금), 8시간", "false", "false"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("10", "나이스가이", "60,000원", "웨딩드레스 잘 어울리시는 분", "2016.12.1(목), 3시간", "false", "false"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("11", "(주)스누피", "70,000원", "야외촬영 웨딩드레스 모델분 구합니다.", "2016.11.30(수), 7시간", "false", "false"));
            MallDBTable.getInstance(mContext).InsertGroup(new MallCommonData("12", "스테들러", "70,000원", "피팅모델분 구합니다.", "2016.12.10(토), 2시간", "false", "false"));

            //추천몰
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("1", "김서진", "피팅모델 자신있어요", "20대중반", "모던시크", "섹시", "2016.11.14(월)", "서울 마포구 상암동", "false", "true"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("2", "이아현", "쇼핑모델 경력있습니다", "20대후반", "스타일", "베이직", "2016.12.1(목)", "서울 은평구 응암동", "false", "true"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("3", "박수진", "개성이 넘치는 모델", "10대후반", "개성", "빈티지", "2017.1.7(금)", "서울 광진구 자양동", "false", "true"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("4", "윤민서", "유니크한 피팅모델", "30대중반", "모던시크", "청순", "2016.11.14(월)", "서울 강남구 청담동", "false", "true"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("5", "백지연", "초보입니다", "20대중반", "스포티", "유니크", "2016.11.14(월)", "서울 강동구 천호동", "false", "true"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("6", "정지영", "온라인쇼핑몰 모델 다수경력", "30대초반", "럭셔리", "섹시", "2016.11.14(월)", "서울 광진구 자양동", "false", "true"));

            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("7", "최강희", "피팅모델 자신있어요", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("8", "이정현", "웨딩드레스모델", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("9", "김러브", "몸매가 좋아요", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("10", "박소현", "피팅모델 자신있어요", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("11", "이이경", "피팅모델 자신있어요", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
            ModelDBTable.getInstance(mContext).InsertGroup(new ModelCommonData("12", "홍진서", "피팅모델 자신있어요", "20대중반", "모던시크", "빈티지", "2016.11.14(월)", "서울 광진구 자양동", "false", "false"));
        }else{

//            SharedPreUtil.setFirstPlay(mContext, true);
//            Log.d("where", "DB삭제");
//            MallDBTable.getInstance(mContext).delete();
        }

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));


        if(SharedPreUtil.getAutoLogin(mContext) == true){
            Intent intent;
            if(GlobalValues.MALL.equals(SharedPreUtil.getLoginType(mContext))){
                intent = new Intent(mContext, MallMainActivity.class);
                startActivity(intent);
            }else if(GlobalValues.MODEL.equals(SharedPreUtil.getLoginType(mContext))){
                intent = new Intent(mContext, ModelMainActivity.class);
                startActivity(intent);
            }
            //이미 로그인을 했고 자동로그인 기능으로 바로 로그인
//            Toast.makeText(mContext, "자동로그인", Toast.LENGTH_SHORT).show();
//            finish();
        }
    }
    
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(mContext, "GRATE", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> arrayList) {
            Toast.makeText(mContext, "FAIL", Toast.LENGTH_SHORT).show();
        }
    };

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.login_btn_login :
                intent = new Intent(mContext, PopupLoginActivity.class);
                startActivityForResult(intent, REQ_LOGIN);
                break;
            case R.id.login_btn_mall :
                intent = new Intent(mContext, MallSignupActivity.class);
                startActivityForResult(intent, REQ_MALL_SIGNUP);
                break;
            case R.id.login_btn_model :
                intent = new Intent(mContext, ModelSignupActivity.class);
                startActivityForResult(intent, REQ_MODEL_SIGNUP);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        if(requestCode == REQ_LOGIN){
            if(resultCode == RESULT_OK){
                if(GlobalValues.MALL.equals(SharedPreUtil.getLoginType(mContext))){
                    intent = new Intent(mContext, MallMainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(GlobalValues.MODEL.equals(SharedPreUtil.getLoginType(mContext))){
                    intent = new Intent(mContext, ModelMainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }else if(requestCode == REQ_MALL_SIGNUP){
            if(resultCode == RESULT_OK){
                finish();
            }
        }else if(requestCode == REQ_MODEL_SIGNUP){
            if(resultCode == RESULT_OK){
                finish();
            }
        }


    }
}
