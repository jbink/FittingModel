package jbink.appnapps.fittingmodel.model.search;

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
import jbink.appnapps.fittingmodel.db.ModelDBTable;
import jbink.appnapps.fittingmodel.mall.detail.MallDetailActivity;
import jbink.appnapps.fittingmodel.model.common.ModelCommonAdapter;
import jbink.appnapps.fittingmodel.model.common.ModelCommonData;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-25.
 */
public class ModelSearchResultActivity extends AppCompatActivity{

    Context mContext;
    ListView mListView;
    List<ModelCommonData> mDataCommon = new ArrayList<ModelCommonData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_search_result);

        mContext = ModelSearchResultActivity.this;

        GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.bg_background_login));

        mListView = ((ListView)findViewById(R.id.model_search_result_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, MallDetailActivity.class);
                startActivity(intent);
            }
        });

        mDataCommon =  ModelDBTable.getInstance(mContext).queryAllDatas();
//        Log.d("where", "사이즈 : "+mDataCommon.size());
//        for(int i=0 ; i<mDataCommon.size() ; i++){
//            if ("true".equals(mDataCommon.get(i).getRecommend())){
//                mDataRecommend.add(mDataCommon.get(i));
//            }
//        }

//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));

        mListView.setAdapter(new ModelCommonAdapter(mContext, mDataCommon));
    }
}
