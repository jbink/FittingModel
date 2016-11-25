package jbink.appnapps.fittingmodel.model.entire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.db.MallDBTable;
import jbink.appnapps.fittingmodel.db.ModelDBTable;
import jbink.appnapps.fittingmodel.mall.common.MallCommonAdapter;
import jbink.appnapps.fittingmodel.mall.detail.MallDetailActivity;
import jbink.appnapps.fittingmodel.model.common.ModelCommonAdapter;
import jbink.appnapps.fittingmodel.model.common.ModelCommonData;

/**
 * Created by user on 2016-11-14.
 */
public class ModelEntireFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<ModelCommonData> mDataCommon = new ArrayList<ModelCommonData>();

    public ModelEntireFragment newInstance(){
        ModelEntireFragment fragment = new ModelEntireFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_model_interest, container, false);
        mListView = ((ListView)rootView.findViewById(R.id.model_frag_interest_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MallDetailActivity.class);
                startActivity(intent);
            }
        });

        mDataCommon =  ModelDBTable.getInstance(getActivity()).queryAllDatas();

//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", false));

        mListView.setAdapter(new ModelCommonAdapter(getActivity(), mDataCommon));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
