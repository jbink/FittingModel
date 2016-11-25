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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class ModelSignup2Activity extends AppCompatActivity{

    private final int REQ_CUSTOM_GALLERY = 7777;
    private final int REQ_DATE_START = 7778;
    private final int REQ_DATE_END = 7779;
    private final int REQ_TIME = 7775;
    private final int REQ_CARRER = 7776;
    private final int REQ_MODEL_SIGNUP_2 = 20002;
    private final static String FOLDER_NAME = "/FittingModel";

    Context mContext;
    TextView mTvTime, mTvCareer, mTvStartDate, mTvEndDate;
    EditText mEdtName, mEdtIntroduce, mEdtFormal, mEdtWeight, mEdtAddress;
    ImageView[] mIvMyPic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_signup_2);

        mContext = ModelSignup2Activity.this;
        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

        layoutBinding();
    }

    private void layoutBinding() {

        mTvTime = (TextView)findViewById(R.id.model_signup_2_tv_time);
        mTvTime.setTypeface(GlobalValues.getFont(mContext));
        mTvCareer = (TextView)findViewById(R.id.model_signup_2_tv_career);
        mTvCareer.setTypeface(GlobalValues.getFont(mContext));
        mTvStartDate = (TextView)findViewById(R.id.model_signup_2_tv_start_date);
        mTvStartDate.setTypeface(GlobalValues.getFont(mContext));
        mTvEndDate = (TextView)findViewById(R.id.model_signup_2_tv_end_date);
        mTvEndDate.setTypeface(GlobalValues.getFont(mContext));

        mEdtName = (EditText)findViewById(R.id.model_signup_2_edt_name);
        mEdtName.setTypeface(GlobalValues.getFont(mContext));
        mEdtIntroduce = (EditText)findViewById(R.id.model_signup_2_edt_introduce);
        mEdtIntroduce.setTypeface(GlobalValues.getFont(mContext));
        mEdtFormal = (EditText)findViewById(R.id.model_signup_2_edt_formal);
        mEdtFormal.setTypeface(GlobalValues.getFont(mContext));
        mEdtWeight = (EditText)findViewById(R.id.model_signup_2_edt_weight);
        mEdtWeight.setTypeface(GlobalValues.getFont(mContext));
        mEdtAddress = (EditText)findViewById(R.id.model_signup_2_edt_address);
        mEdtAddress.setTypeface(GlobalValues.getFont(mContext));

        mIvMyPic = new ImageView[10];
        for(int i=0 ; i<mIvMyPic.length ; i++) {
            int id = getResources().getIdentifier("model_signup_2_pic_" + i, "id", getPackageName());
            mIvMyPic[i] = (ImageView)findViewById(id);
        }

    }

    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.model_signup_2_btn_pic :
                intent = new Intent(mContext, MultipleGalleryActivity.class);
                startActivityForResult(intent, REQ_CUSTOM_GALLERY);
                break;
            case R.id.model_signup_2_tv_start_date :
                intent = new Intent(mContext, DatePaickerActivity.class);
                startActivityForResult(intent, REQ_DATE_START);
                break;
            case R.id.model_signup_2_tv_end_date :
                intent = new Intent(mContext, DatePaickerActivity.class);
                startActivityForResult(intent, REQ_DATE_END);
                break;
            case R.id.model_signup_2_tv_time :
                ArrayList<String> dd = new ArrayList<String>();
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
            case R.id.model_signup_2_tv_career :
                ArrayList<String> dd1 = new ArrayList<String>();
                dd1.add("1년미만");
                dd1.add("1년이상 ~ 2년미만");
                dd1.add("2년이상 ~ 3년미만");
                dd1.add("3년이상 ~ 4년미만");
                dd1.add("4년이상 ~ 5년미만");
                dd1.add("5년이상 ~ 10년미만");
                dd1.add("10년이상");
                intent = new Intent(mContext, CustomPopup.class);
                intent.putStringArrayListExtra("datas", dd1);
                startActivityForResult(intent, REQ_CARRER);
                break;
            case R.id.model_signup_2_btn_ok :
                if(emptyEdittext(mEdtName.getText().toString())
                        || emptyEdittext(mEdtIntroduce.getText().toString())
                        || emptyEdittext(mEdtAddress.getText().toString())
                        || emptyEdittext(mEdtFormal.getText().toString())
                        || emptyEdittext(mEdtWeight.getText().toString())){
                    Toast.makeText(mContext, "모든 값을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreUtil.setModelName(mContext, mEdtName.getText().toString());
                SharedPreUtil.setModelIntroduce(mContext, mEdtIntroduce.getText().toString());
                SharedPreUtil.setModelAddress(mContext, mEdtAddress.getText().toString());

                intent = new Intent(mContext, ModelSignup3Activity.class);
                startActivityForResult(intent, REQ_MODEL_SIGNUP_2);
                break;
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


    /*
    사진 다중 선택에 필요한 method
     */

    private Thread mThread = null;
    private Handler mHandler = new Handler();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CUSTOM_GALLERY){
            if(resultCode == RESULT_CANCELED)
                return;
            String[] all_path = data.getStringArrayExtra("all_path");
            if(all_path.length == 0) {
                Toast.makeText(mContext, "선택 된 사진이 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            JSONArray array = new JSONArray();
            for (int i=0; i < all_path.length; i++){
                ImageLoader.getInstance().displayImage("file:///"+all_path[i], mIvMyPic[i]);
//                array.put(all_path[i]);
            }

//            try {
//                getRotateImgs(array);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }else if (requestCode == REQ_DATE_START){
            if(resultCode == RESULT_OK){
                mTvStartDate.setText(data.getStringExtra("date"));
            }
        }else if (requestCode == REQ_DATE_END){
            if(resultCode == RESULT_OK) {
                mTvEndDate.setText(data.getStringExtra("date"));
            }
        }else if (requestCode == REQ_TIME){
            if(resultCode == GlobalValues.POPUP_RESULT) {
                mTvTime.setText(data.getStringExtra("returnValue"));
            }
        }else if (requestCode == REQ_CARRER){
            if(resultCode == GlobalValues.POPUP_RESULT) {
                mTvCareer.setText(data.getStringExtra("returnValue"));
            }
        }else if(requestCode == REQ_MODEL_SIGNUP_2){
            if(resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }


    public void getRotateImgs(final JSONArray pathArray) {
        if (pathArray == null) {
            return;
        }

        if (mThread != null) {

            if (mThread.isAlive()) {
                mThread.destroy();
            }
        }

        mThread = new Thread(new Runnable() {

            @Override
            public void run() {
                final JSONArray rePathArray = new JSONArray();
                int imgDegre = 0;
                String cropPath = null;
                Bitmap originalBmp = null;

                for (int i = 0; i < pathArray.length(); i++) {
                    String path = null;
                    try {
                        path = pathArray.getString(i);
                    } catch (Exception e) {
                    }
                    if (path != null && path.length() > 0) {
                        imgDegre = ImageUtil.getPhotoOrientationDegree(path);
                        try {
                            if (imgDegre == 0) {
                                if(!TextUtils.isEmpty(path)){
                                    rePathArray.put(path);
                                }
                            } else {
                                originalBmp = ImageUtil.getRotatedBitmap(path, imgDegre);
                                originalBmp = ImageUtil.rotate(originalBmp, 0);

                                byte[] byteArray = bitmapToByteArray(originalBmp);
                                File catureFile = new File(Environment.getExternalStorageDirectory().toString() + FOLDER_NAME + "/" + "Fit_" + System.currentTimeMillis() + ".png");
                                new SingleMediaScanner(mContext, catureFile);
                                cropPath = catureFile.getAbsolutePath();

                                FileOutputStream fos = null;
                                try {
                                    fos = new FileOutputStream(cropPath);
                                    fos.write(byteArray);
                                } catch (Exception e) {
                                    cropPath = null;
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (fos != null)
                                            fos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                rePathArray.put(cropPath);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (originalBmp != null) {
                                originalBmp.recycle();
                            }
                        }
                    }// path != null

                }// for
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 내용
//                        uploadImages(rePathArray);
                    }
                }, 0);
            }
        });
        mThread.start();
    }



    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

}
