package jbink.appnapps.fittingmodel.model.ranking;

import android.content.Context;
import android.util.Log;
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
public class ModelRankingAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<ModelRankingData> mRowList;
    Context mContext;

    public ModelRankingAdapter(Context context, List<ModelRankingData> mRowList) {
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

        final ModelRankingData data = mRowList.get(position);
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

        holder.tv_rank.setText(String.valueOf(data.getRank()));
        holder.tv_shop.setText(data.getShop());
        holder.tv_content.setText(data.getContent());
        holder.tv_match.setText(String.valueOf(data.getMatch()) + "%");
        holder.tv_review.setText(String.valueOf(data.getReview()) + "점");

        holder.tv_rank.setTypeface(GlobalValues.getFont(mContext));
        holder.tv_content.setTypeface(GlobalValues.getFont(mContext));
        holder.tv_match.setTypeface(GlobalValues.getFont(mContext));
        holder.tv_review.setTypeface(GlobalValues.getFont(mContext));
        holder.tv_rank.setTypeface(GlobalValues.getFont(mContext));
        Log.d("where", "" + data.getMatch());

        holder.pb_match.setProgress(data.getMatch());
        holder.pb_review.setProgress(data.getReview());

        int id = mContext.getResources().getIdentifier("shop0"+(position+1), "drawable", mContext.getPackageName());
        holder.iv_picture.setBackgroundResource(id);




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
