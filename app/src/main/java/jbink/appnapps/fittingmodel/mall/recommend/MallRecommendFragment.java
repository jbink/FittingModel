package jbink.appnapps.fittingmodel.mall.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;

/**
 * Created by user on 2016-11-14.
 */
public class MallRecommendFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<MallRecommendData> mDataRecommend = new ArrayList<MallRecommendData>();

    public MallRecommendFragment newInstance(){
        MallRecommendFragment fragment = new MallRecommendFragment();
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

        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));
        mDataRecommend.add(new MallRecommendData("", "", "", "", "", true));

        mListView.setAdapter(new MallRecommendAdapter(getActivity(), mDataRecommend));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
