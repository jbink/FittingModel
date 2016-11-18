package jbink.appnapps.fittingmodel.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;

/**
 * Created by user on 2016-11-17.
 */
public class CustomPopup extends AppCompatActivity{

    ListView mListView;
    List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_popup);

        mListView = (ListView)findViewById(R.id.popup_default_list);
        mData =  getIntent().getStringArrayListExtra("datas");

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String returnData = (String)parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.putExtra("returnValue", returnData);
                setResult(GlobalValues.POPUP_RESULT, intent);
                finish();
            }
        });
    }
}
