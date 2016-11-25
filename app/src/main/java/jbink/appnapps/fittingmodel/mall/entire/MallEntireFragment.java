package jbink.appnapps.fittingmodel.mall.entire;

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
import jbink.appnapps.fittingmodel.mall.common.MallCommonAdapter;
import jbink.appnapps.fittingmodel.mall.common.MallCommonData;
import jbink.appnapps.fittingmodel.mall.detail.MallDetailActivity;

/**
 * Created by user on 2016-11-14.
 */
public class MallEntireFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<MallCommonData> mDataCommon = new ArrayList<MallCommonData>();

    public MallEntireFragment newInstance(){
        MallEntireFragment fragment = new MallEntireFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_mall_recommend, container, false);
        mListView = ((ListView)rootView.findViewById(R.id.mall_frag_recommend_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MallDetailActivity.class);
                startActivity(intent);
            }
        });

        mDataCommon =  MallDBTable.getInstance(getActivity()).queryAllDatas();

//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", false));

        mListView.setAdapter(new MallCommonAdapter(getActivity(), mDataCommon));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
