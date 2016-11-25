package jbink.appnapps.fittingmodel.mall.ranking;

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
public class MallRankingFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<MallRankingData> mDataRanking = new ArrayList<MallRankingData>();

    public MallRankingFragment newInstance(){
        MallRankingFragment fragment = new MallRankingFragment();
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

        mDataRanking.add(new MallRankingData(1, "", "(주)웨딩슈", "테마의류, 웨딩", 92, 98));
        mDataRanking.add(new MallRankingData(2, "", "(주)클래식스토어", "여성의류, 캐주얼", 89, 87));
        mDataRanking.add(new MallRankingData(3, "", "(주)모모스타일", "여성의류, 캐주얼", 83, 70));
        mDataRanking.add(new MallRankingData(4, "", "(주)프렌치시크", "여성의류, 정장", 77, 67));
        mDataRanking.add(new MallRankingData(5, "", "(주)젠틀맨", "남성의류, 정장", 68, 60));
        mDataRanking.add(new MallRankingData(6, "", "(주)고은한복", "테마의류, 한복", 65, 70));
//        mDataRanking.add(new MallRankingData(7, "", "", "", 0, 0));
//        mDataRanking.add(new MallRankingData(8, "", "", "", 0, 0));
//        mDataRanking.add(new MallRankingData(9, "", "", "", 0, 0));

        mListView.setAdapter(new MallRankingAdapter(getActivity(), mDataRanking));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
