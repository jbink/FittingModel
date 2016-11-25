package jbink.appnapps.fittingmodel.mall.searchshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.MallMainActivity;

/**
 * Created by user on 2016-11-14.
 */
public class MallSearchFragment extends Fragment implements View.OnClickListener{

    GridView mGridView;
    List<MallSearchShopData> mDataSearchShop = new ArrayList<MallSearchShopData>();

    public MallSearchFragment newInstance(){
        MallSearchFragment fragment = new MallSearchFragment();
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

        mDataSearchShop.add(new MallSearchShopData("shop01", "유아동 쇼핑몰", "신생아, 아동, 주니어의류"));
        mDataSearchShop.add(new MallSearchShopData("shop02", "여성의류 쇼핑몰", "캐주얼, 정장, 란제리"));
        mDataSearchShop.add(new MallSearchShopData("shop03", "남성의류 쇼핑몰", "캐주얼, 정장, 란제리"));
        mDataSearchShop.add(new MallSearchShopData("shop04", "미시, 주부쇼핑몰", "캐주얼, 정장, 임부복"));
        mDataSearchShop.add(new MallSearchShopData("shop05", "테마 쇼핑몰", "웨딩, 한복, 파티드레스"));
        mDataSearchShop.add(new MallSearchShopData("shop06", "스포츠 쇼핑몰", "트레이닝, 비치웨어"));
//        mDataSearchShop.add(new MallSearchShopData("", "", ""));

        mGridView.setAdapter(new MallSearchShopAdapter(getActivity(), mDataSearchShop));
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
                Log.d("where", "***********************************************************");
                newFragment = new MallSearchShopOptionFragment().newInstance();
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
