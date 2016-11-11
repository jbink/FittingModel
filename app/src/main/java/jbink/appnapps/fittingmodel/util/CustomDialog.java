package jbink.appnapps.fittingmodel.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import jbink.appnapps.fittingmodel.R;


public class CustomDialog extends Dialog {
	
	String mLeftText = null;
	String mRightText = null;
	
	Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();    
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
         
        setContentView(R.layout.custom_dialog);
         
        setLayout();
        setTitle(mTitle);
        setContent(mContent);
        
        setClickListener(mLeftClickListener , mRightClickListener);
	}

	public CustomDialog(Context context) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
    }

     
    public CustomDialog(Context context, String title, String content, View.OnClickListener singleListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mLeftClickListener = singleListener;
    }
    
    public CustomDialog(Context context, String title, String content, View.OnClickListener singleListener, String lBtnText) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
        this.mContent = content;
        this.mLeftText = lBtnText;
    }
     
    public CustomDialog(Context context , String title , String content , 
            View.OnClickListener leftListener , View.OnClickListener rightListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
    }
     
    private void setTitle(String title){
        mTitleView.setText(title);
    }
    
    public CustomDialog(Context context , String title , String content , 
            View.OnClickListener leftListener,  View.OnClickListener rightListener, String lBtnText, String rBtnText) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
        this.mLeftText = lBtnText;
        this.mRightText = rBtnText;
    }
     
    private void setContent(String content){
        mContentView.setText(Html.fromHtml(content));
    }
     
    private void setClickListener(View.OnClickListener left , View.OnClickListener right){
        if(left!=null && right!=null){
        	
        	if(!TextUtils.isEmpty(mLeftText))
        		mLeftButton.setText(mLeftText);
        	if(!TextUtils.isEmpty(mRightText))
        		mRightButton.setText(mRightText);
        	
        	mLeftButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
        }else if(left!=null && right==null){
        	if(!TextUtils.isEmpty(mLeftText))
        		mLeftButton.setText(mLeftText);
        	mLeftButton.setBackgroundColor(Color.rgb(0x00, 0x68, 0x38));//006838
        	mLeftButton.setOnClickListener(left);
        	mRightButton.setVisibility(View.GONE);
        }else {
             
        }
    }
     
    private TextView mTitleView;
    private TextView mContentView;
    private Button mLeftButton;
    private Button mRightButton;
    private String mTitle;
    private String mContent;
     
    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;
     
    /*
     * Layout
     */
    private void setLayout(){
    	//지정폰트
    	
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mTitleView.setTypeface(GlobalValues.getFont(mContext));
        mContentView = (TextView) findViewById(R.id.tv_content);
        mContentView.setTypeface(GlobalValues.getFont(mContext));
        mLeftButton = (Button) findViewById(R.id.bt_left);
        mLeftButton.setTypeface(GlobalValues.getFont(mContext));
        mRightButton = (Button) findViewById(R.id.bt_right);
        mRightButton.setTypeface(GlobalValues.getFont(mContext));
    }

//	@Override
//	public void onBackPressed() {
//	}


}
