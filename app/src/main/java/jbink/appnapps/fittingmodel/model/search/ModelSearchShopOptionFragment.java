package jbink.appnapps.fittingmodel.model.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import jbink.appnapps.fittingmodel.R;

/**
 * Created by user on 2016-11-15.
 */
public class ModelSearchShopOptionFragment extends Fragment implements View.OnClickListener{

    TextView[] mTvCategory, mTvSex, mTvStyle;
    HashMap<Integer, Integer> mMapCategoryIds = new HashMap<>();
    HashMap<Integer, Integer> mMapSexIds = new HashMap<>();
    HashMap<Integer, Integer> mMapAgeIds = new HashMap<>();

    public ModelSearchShopOptionFragment newInstance(){
        ModelSearchShopOptionFragment fragment = new ModelSearchShopOptionFragment();
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
        View rootView = inflater.inflate(R.layout.frag_model_search_option, container, false);
        ((Button)rootView.findViewById(R.id.model_search_option_complete)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.model_search_option_init)).setOnClickListener(this);

        layoutBinding(rootView);

        return rootView;
    }

    private void layoutBinding(View rootView) {
        mTvCategory = new TextView[3];
        for(int i=0 ; i<mTvCategory.length ; i++){
            int id = getActivity().getResources().getIdentifier("frag_model_search_category_"+i, "id", getActivity().getPackageName());
            mTvCategory[i] = (TextView)rootView.findViewById(id);
            mTvCategory[i].setOnClickListener(categoryClick);
            mMapCategoryIds.put(mTvCategory[i].getId(), i);
        }
        mTvSex = new TextView[2];
        for(int i=0 ; i<mTvSex.length ; i++){
            int id = getActivity().getResources().getIdentifier("frag_model_search_sex_"+i, "id", getActivity().getPackageName());
            mTvSex[i] = (TextView)rootView.findViewById(id);
            mTvSex[i].setOnClickListener(sexClick);
            mMapSexIds.put(mTvSex[i].getId(), i);
        }
        mTvStyle = new TextView[11];
        for(int i=0 ; i<mTvStyle.length ; i++){
            int id = getActivity().getResources().getIdentifier("frag_model_search_style_"+i, "id", getActivity().getPackageName());
            mTvStyle[i] = (TextView)rootView.findViewById(id);
            mTvStyle[i].setOnClickListener(styleClick);
            mMapAgeIds.put(mTvStyle[i].getId(), i);
        }
    }

    public void setMenuTextColor(int selected_id, TextView[] views){
        for(int i=0 ; i<views.length ; i++){
            views[i].setBackgroundResource(R.drawable.btn_select_back);
            views[i].setTextColor(ContextCompat.getColor(getActivity(), R.color.color_7b7b7b));
        }
        views[selected_id].setBackgroundResource(R.drawable.btn_select_back_on);
        views[selected_id].setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
    }

//    public void fragmentReplace(int reqNewFragmentIndex) {
//        Fragment newFragment = null;
//        newFragment = getFragment(reqNewFragmentIndex);
//        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.addToBackStack(null);
//
//
//        transaction.replace(R.id.content_search_option, newFragment, "content_search_option");
//        transaction.commitAllowingStateLoss();
//    }
//
//    private Fragment getFragment(int idx) {
//        Fragment newFragment = null;
//        switch (idx) {
//            case 0:
//                Log.d("where", "dddddddd111");
//                newFragment = new MallRecommendFragment().newInstance();
//                break;
//        }
//        return newFragment;
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.model_search_option_init :
                getFragmentManager().popBackStack();
                break;
            case R.id.model_search_option_complete :
                Intent intent = new Intent(getActivity(), ModelSearchResultActivity.class);
                startActivity(intent);
//                fragmentReplace(0);
                break;
        }
    }

    View.OnClickListener categoryClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setMenuTextColor(mMapCategoryIds.get(v.getId()), mTvCategory);
        }
    };
    View.OnClickListener sexClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setMenuTextColor(mMapSexIds.get(v.getId()), mTvSex);
        }
    };
    View.OnClickListener styleClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setMenuTextColor(mMapAgeIds.get(v.getId()), mTvStyle);
        }
    };

}
