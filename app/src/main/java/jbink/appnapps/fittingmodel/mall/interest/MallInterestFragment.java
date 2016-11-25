package jbink.appnapps.fittingmodel.mall.interest;

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
public class MallInterestFragment extends Fragment implements View.OnClickListener{

    ListView mListView;
    List<MallCommonData> mDataCommon = new ArrayList<MallCommonData>();
    List<MallCommonData> mDataInterest = new ArrayList<MallCommonData>();

    public MallInterestFragment newInstance(){
        MallInterestFragment fragment = new MallInterestFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_mall_interest, container, false);
        mListView = ((ListView)rootView.findViewById(R.id.mall_frag_interest_listview));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MallDetailActivity.class);
                startActivity(intent);
            }
        });

        mDataCommon =  MallDBTable.getInstance(getActivity()).queryAllDatas();
        for(int i=0 ; i<mDataCommon.size() ; i++){
            if ("true".equals(mDataCommon.get(i).getBookmark())){
                mDataInterest.add(mDataCommon.get(i));
            }
        }

//        mDataCommon.add(new MallCommonData("", "(주)모모스타일", "120,000원", "피팅모델분 구합니다.", "2016.11.14(월), 4시간", "false"));
//        mDataCommon.add(new MallCommonData("", "(주)나비웨딩", "100,000원", "단아한 웨딩모델 구합니다.", "2016.11.26(토), 2시간", "false"));
//        mDataCommon.add(new MallCommonData("", "(주)클래식스토어", "100,000원", "클래식한 분위기 쇼핑몰입니다.", "2016.11.25(금), 8시간", "false"));
//        mDataCommon.add(new MallCommonData("", "(주)뷰티플웨딩", "80,000원", "웨딩드레스 잘 어울리시는 분", "2016.12.1(목), 3시간", "false"));
//        mDataCommon.add(new MallCommonData("", "(주)스카이웨딩", "70,000원", "야외촬영 웨딩드레스 모델분 구합니다.", "2016.11.30(수), 7시간", "false"));
//        mDataCommon.add(new MallCommonData("", "(주)샤랄라공주", "70,000원", "피팅모델분 구합니다.", "2016.12.10(토), 2시간", "false"));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", "false"));
//        mDataCommon.add(new MallCommonData("", "", "", "", "", "false"));

        mListView.setAdapter(new MallCommonAdapter(getActivity(), mDataInterest));



        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
