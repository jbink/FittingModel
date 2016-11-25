package jbink.appnapps.fittingmodel.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import jbink.appnapps.fittingmodel.R;

/**
 * Created by user on 2016-11-23.
 */
public class DatePaickerActivity extends AppCompatActivity{
    DatePicker mDatePicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        mDatePicker = (DatePicker)findViewById(R.id.date_picker);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.date_picker_btn_ok :
                Intent intent = new Intent();
                intent.putExtra("date",""+mDatePicker.getYear() +". "+ (mDatePicker.getMonth()+1)+". "+ mDatePicker.getDayOfMonth());
//                Toast.makeText(DatePaickerActivity.this, ""+mDatePicker.getYear() +". "+ (mDatePicker.getMonth()+1)+". "+ mDatePicker.getDayOfMonth(), Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                finish();
                break;

        }
    }
}
