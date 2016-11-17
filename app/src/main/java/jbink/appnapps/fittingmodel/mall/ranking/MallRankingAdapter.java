package jbink.appnapps.fittingmodel.mall.ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-14.
 */
public class MallRankingAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<MallRankingData> mRowList;
    Context mContext;

    public MallRankingAdapter(Context context, List<MallRankingData> mRowList) {
        mContext = context;
        this.mInflater = (LayoutInflater)mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mRowList = mRowList;
    }

    @Override
    public int getCount() {
        if(mRowList == null)
            return 0;
        return mRowList.size();
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < mRowList.size())
            return mRowList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        final MallRankingData data = mRowList.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.mall_row_ranking, viewGroup, false);
            holder = new ViewHolder();

            holder.tv_rank = (TextView)convertView.findViewById(R.id.mall_row_ranking_txt_rank);
            holder.iv_picture = (ImageView)convertView.findViewById(R.id.mall_row_ranking_img_pic);
            holder.tv_shop = (TextView)convertView.findViewById(R.id.mall_row_ranking_txt_shop);
            holder.tv_content = (TextView)convertView.findViewById(R.id.mall_row_ranking_txt_content);
            holder.tv_match = (TextView)convertView.findViewById(R.id.mall_row_ranking_txt_match_score);
            holder.tv_review = (TextView)convertView.findViewById(R.id.mall_row_ranking_txt_review_score);
            holder.pb_match = (ProgressBar) convertView.findViewById(R.id.mall_row_ranking_progress_match);
            holder.pb_review = (ProgressBar) convertView.findViewById(R.id.mall_row_ranking_progress_review);

            holder.tv_rank.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_shop.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_content.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_match.setTypeface(GlobalValues.getFont(mContext));
            holder.tv_review.setTypeface(GlobalValues.getFont(mContext));

            ((TextView)convertView.findViewById(R.id.mall_row_ranking_txt_match_score_)).setTypeface(GlobalValues.getFont(mContext));
            ((TextView)convertView.findViewById(R.id.mall_row_ranking_txt_review_score_)).setTypeface(GlobalValues.getFont(mContext));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }






        return convertView;
    }

    private class ViewHolder{
        TextView tv_rank;
        ImageView iv_picture;
        TextView tv_shop;
        TextView tv_content;
        TextView tv_match;
        ProgressBar pb_match;
        TextView tv_review;
        ProgressBar pb_review;

    }
}
