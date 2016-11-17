package jbink.appnapps.fittingmodel.mall.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-14.
 */
public class MallRecommendAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<MallRecommendData> mRowList;
    Context mContext;

    public MallRecommendAdapter(Context context, List<MallRecommendData> mRowList) {
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

        final MallRecommendData data = mRowList.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.mall_row_recommend, viewGroup, false);
            holder = new ViewHolder();

            holder.iv_picture = (ImageView)convertView.findViewById(R.id.mall_row_recommend_img_pic);
            holder.tv_shop = (TextView)convertView.findViewById(R.id.mall_row_recommend_txt_shop);
            holder.tv_cost = (TextView)convertView.findViewById(R.id.mall_row_recommend_txt_cost);
            holder.tv_content = (TextView)convertView.findViewById(R.id.mall_row_recommend_txt_content);
            holder.tv_time = (TextView)convertView.findViewById(R.id.mall_row_recommend_txt_time);
            holder.iv_boolmark = (ImageView)convertView.findViewById(R.id.mall_row_recommend_img_bookmark);

            holder.tv_shop.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_cost.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_content.setTypeface(GlobalValues.getLightFont(mContext));
            holder.tv_time.setTypeface(GlobalValues.getFont(mContext));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }






        return convertView;
    }

    private class ViewHolder{
        ImageView iv_picture;
        TextView tv_shop;
        TextView tv_cost;
        TextView tv_content;
        TextView tv_time;
        ImageView iv_boolmark;
    }
}
