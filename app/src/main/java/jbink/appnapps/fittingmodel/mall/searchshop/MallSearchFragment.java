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

        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));
        mDataSearchShop.add(new MallSearchShopData("", "", ""));

        mGridView.setAdapter(new MallSearchShopAdapter(getActivity(), mDataSearchShop));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentReplace(0);
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
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);


        transaction.replace(R.id.content_search, newFragment,  "main"+String.valueOf(reqNewFragmentIndex));
        transaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
        switch (idx) {
            case 0:
                Log.d("where", "zmfd");
                newFragment = new MallSearchShopOptionFragment().newInstance();
                break;
        }
        return newFragment;
    }
}
