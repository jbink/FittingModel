package jbink.appnapps.fittingmodel.model.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.searchshop.FragmentChangeListener;
import jbink.appnapps.fittingmodel.mall.searchshop.MallSearchShopOptionFragment;

/**
 * Created by user on 2016-11-14.
 */
public class ModelSearchFragment extends Fragment implements View.OnClickListener{

    GridView mGridView;
    List<ModelSearchData> mDataSearchModel = new ArrayList<ModelSearchData>();

    public ModelSearchFragment newInstance(){
        ModelSearchFragment fragment = new ModelSearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_mall_search, container, false);
        mGridView = ((GridView)rootView.findViewById(R.id.mall_frag_search_gridview));

        mDataSearchModel.add(new ModelSearchData("model01", "유아,어린이 모델", "3~10세의 영유아, 어린이모델"));
        mDataSearchModel.add(new ModelSearchData("model02", "청소년 모델", "11~19세 청소년모델"));
        mDataSearchModel.add(new ModelSearchData("model03", "피팅모델", "20~40세 다양한 피팅모델"));
        mDataSearchModel.add(new ModelSearchData("model04", "주부, 중년 모델", "40세이상 중년층 모델"));
        mDataSearchModel.add(new ModelSearchData("model05", "부분 모델", "피부, 손, 발, 헤어, 다리등 부분모델"));
        mDataSearchModel.add(new ModelSearchData("model06", "외국인 모델", "개성있는 외국인모델"));

        mGridView.setAdapter(new ModelSearchAdapter(getActivity(), mDataSearchModel));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentReplace(0);
//                showOtherFragment();
            }

        });


        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    public void fragmentReplace(int reqNewFragmentIndex) {
        Fragment newFragment = null;
        newFragment = getFragment(reqNewFragmentIndex);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_search, newFragment,  "content_search");
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
        switch (idx) {
            case 0:
                newFragment = new ModelSearchShopOptionFragment().newInstance();
                break;
        }
        return newFragment;
    }

    public void showOtherFragment(){
        Fragment fr = new MallSearchShopOptionFragment();
        FragmentChangeListener fc = (FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}
