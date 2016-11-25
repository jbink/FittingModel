package jbink.appnapps.fittingmodel.mall.searchshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.common.MallCommonAdapter;
import jbink.appnapps.fittingmodel.mall.common.MallCommonData;
import jbink.appnapps.fittingmodel.mall.detail.MallDetailActivity;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-25.
 */
public class MallSearchShopResultActivity extends AppCompatActivity{

    Context mContext;
    ListView mListView;
    List<MallCommonData> mDataCommon = new ArrayList<MallCommonData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_search_result);

        mContext = MallSearchShopResultActivity.this;

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

        mListView = ((ListView)findViewById(R.id.mall_search_result_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, MallDetailActivity.class);
                startActivity(intent);
            }
        });

//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));

        mListView.setAdapter(new MallCommonAdapter(mContext, mDataCommon));
    }
}
