package jbink.appnapps.fittingmodel.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import jbink.appnapps.fittingmodel.R;


public class CustomDialog extends Dialog {

    private TextView mTitleView;
    private TextView mContentView;
    private Button mLeftButton;
    private Button mRightButton;
    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    private String mTitle;
    private String mContent;
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

    private void setTitle(String title){
        mTitleView.setText(title);
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
        	mLeftButton.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_select));//006838
        	mLeftButton.setOnClickListener(left);
        	mRightButton.setVisibility(View.GONE);
        }else {
             
        }
    }
     
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

    public static class Builder{
        private Context context;
        private String title;
        private String content;
        private String leftText = null;
        private String rightText = null;

        private View.OnClickListener leftClickListener;
        private View.OnClickListener rightClickListener;

        public Builder(Context _context){
            context = _context;
        }

        public Builder(Context _context, int theme) {
            context = _context;
        }

        public Builder title(String val){
            title = val;
            return this;
        }
        public Builder content(String val){
            content = val;
            return this;
        }
        public Builder leftText(String val){
            leftText = val;
            return this;
        }
        public Builder rightText(String val){
            rightText = val;
            return this;
        }

        public Builder leftListener(View.OnClickListener val){
            leftClickListener = val;
            return this;
        }
        public Builder rightListener(View.OnClickListener val){
            rightClickListener = val;
            return this;
        }

        public CustomDialog build(){
            return new CustomDialog(context, this);
        }
    }

    private CustomDialog(Context context, Builder builder){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        this.mTitle = builder.title;
        this.mContent = builder.content;
        this.mLeftClickListener = builder.leftClickListener;
        this.mRightClickListener = builder.rightClickListener;
        this.mLeftText = builder.leftText;
        this.mRightText = builder.rightText;
    }
}
