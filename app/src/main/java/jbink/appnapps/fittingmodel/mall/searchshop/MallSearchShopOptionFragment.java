package jbink.appnapps.fittingmodel.mall.searchshop;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.mall.recommend.MallRecommendFragment;

/**
 * Created by user on 2016-11-15.
 */
public class MallSearchShopOptionFragment extends Fragment implements View.OnClickListener{

    public MallSearchShopOptionFragment newInstance(){
        MallSearchShopOptionFragment fragment = new MallSearchShopOptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_shop_search_option, container, false);
        ((Button)rootView.findViewById(R.id.search_option_complete)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.search_option_init)).setOnClickListener(this);
        return rootView;
    }

    public void fragmentReplace(int reqNewFragmentIndex) {
        Fragment newFragment = null;
        newFragment = getFragment(reqNewFragmentIndex);
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);


        transaction.replace(R.id.content_search_option, newFragment, "main"+String.valueOf(reqNewFragmentIndex));
        transaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;
        switch (idx) {
            case 0:
                newFragment = new MallRecommendFragment().newInstance();
                break;
        }
        return newFragment;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_option_init :
                getFragmentManager().popBackStack();
                break;
            case R.id.search_option_complete :
                fragmentReplace(0);
                break;
        }
    }
}
