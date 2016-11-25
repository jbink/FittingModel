package jbink.appnapps.fittingmodel.model.ranking;

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
public class ModelRankingFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<ModelRankingData> mDataRanking = new ArrayList<ModelRankingData>();

    public ModelRankingFragment newInstance(){
        ModelRankingFragment fragment = new ModelRankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_mall_ranking, container, false);
        mListView = ((ListView)rootView.findViewById(R.id.mall_frag_ranking_listview));

        mDataRanking.add(new ModelRankingData(1, "", "서진아", "테마의류, 웨딩", 92, 98));
        mDataRanking.add(new ModelRankingData(2, "", "심미진", "드레스피팅모델", 89, 87));
        mDataRanking.add(new ModelRankingData(3, "", "박주미", "교복모델합니다", 83, 70));
        mDataRanking.add(new ModelRankingData(4, "", "김은혁", "아동복모델", 77, 67));
        mDataRanking.add(new ModelRankingData(5, "", "남철", "남성캐주얼 모델", 65, 70));
        mDataRanking.add(new ModelRankingData(6, "", "cindy", "드레스피팅모델 전문", 77, 65));
//        mDataRanking.add(new ModelRankingData(7, "", "", "", 0, 0));
//        mDataRanking.add(new ModelRankingData(8, "", "", "", 0, 0));
//        mDataRanking.add(new ModelRankingData(9, "", "", "", 0, 0));

        mListView.setAdapter(new ModelRankingAdapter(getActivity(), mDataRanking));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
