package jbink.appnapps.fittingmodel.model.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import jbink.appnapps.fittingmodel.model.detail.ModelDetailActivity;

/**
 * Created by user on 2016-11-14.
 */
public class ModelRecommendFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<ModelCommonData> mDataCommon = new ArrayList<ModelCommonData>();
    List<ModelCommonData> mDataRecommend = new ArrayList<ModelCommonData>();

    public ModelRecommendFragment newInstance(){
        ModelRecommendFragment fragment = new ModelRecommendFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_model_recommend, container, false);
        mListView = ((ListView)rootView.findViewById(R.id.model_frag_recommend_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ModelDetailActivity.class);
                startActivity(intent);
            }
        });

        mDataCommon =  ModelDBTable.getInstance(getActivity()).queryAllDatas();
        Log.d("where", "사이즈 : "+mDataCommon.size());
        for(int i=0 ; i<mDataCommon.size() ; i++){
            if ("true".equals(mDataCommon.get(i).getRecommend())){
                mDataRecommend.add(mDataCommon.get(i));
            }
        }

//        mDataCommon.add(new ModelCommonData("", "(주)모모스타일", "120,000원", "피팅모델분 구합니다.", "2016.11.14(월), 4시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "(주)나비웨딩", "100,000원", "단아한 웨딩모델 구합니다.", "2016.11.26(토), 2시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "(주)클래식스토어", "100,000원", "클래식한 분위기 쇼핑몰입니다.", "2016.11.25(금), 8시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "(주)뷰티플웨딩", "80,000원", "웨딩드레스 잘 어울리시는 분", "2016.12.1(목), 3시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "(주)스카이웨딩", "70,000원", "야외촬영 웨딩드레스 모델분 구합니다.", "2016.11.30(수), 7시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "(주)샤랄라공주", "70,000원", "피팅모델분 구합니다.", "2016.12.10(토), 2시간", "false"));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", "false"));
//        mDataCommon.add(new ModelCommonData("", "", "", "", "", "false"));

        mListView.setAdapter(new ModelCommonAdapter(getActivity(), mDataRecommend));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
