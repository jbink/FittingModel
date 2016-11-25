package jbink.appnapps.fittingmodel.mall.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.db.MallDBTable;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-14.
 */
public class MallCommonAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<MallCommonData> mRowList;
    Context mContext;

    public MallCommonAdapter(Context context, List<MallCommonData> mRowList) {
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

        final MallCommonData data = mRowList.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.mall_row_common, viewGroup, false);
            holder = new ViewHolder();

            holder.iv_picture = (ImageView)convertView.findViewById(R.id.mall_row_common_img_pic);
            holder.tv_shop = (TextView)convertView.findViewById(R.id.mall_row_common_txt_shop);
            holder.tv_cost = (TextView)convertView.findViewById(R.id.mall_row_common_txt_cost);
            holder.tv_content = (TextView)convertView.findViewById(R.id.mall_row_common_txt_content);
            holder.tv_time = (TextView)convertView.findViewById(R.id.mall_row_common_txt_time);
            holder.iv_boolmark = (ImageView)convertView.findViewById(R.id.mall_row_common_img_bookmark);

            holder.tv_shop.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_cost.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_content.setTypeface(GlobalValues.getLightFont(mContext));
            holder.tv_time.setTypeface(GlobalValues.getFont(mContext));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if("true".equals(data.getBookmark())){
            holder.iv_boolmark.setBackgroundResource(R.drawable.bookmark_on);
        }else {
            holder.iv_boolmark.setBackgroundResource(R.drawable.bookmark);
        }

        Log.d("where", "id : "+data.getPicture());
        int id = mContext.getResources().getIdentifier("shop0"+data.getPicture(), "drawable", mContext.getPackageName());
//        int id = mContext.getResources().getIdentifier("shop0"+(position+1), "drawable", mContext.getPackageName());
        holder.iv_picture.setBackgroundResource(id);

        holder.tv_shop.setText(data.getShop());
        holder.tv_shop.setTypeface(GlobalValues.getBoldFont(mContext));
        holder.tv_content.setText(data.getContent());
        holder.tv_content.setTypeface(GlobalValues.getFont(mContext));
        holder.tv_cost.setText(data.getCost());
        holder.tv_cost.setTypeface(GlobalValues.getBoldFont(mContext));
        holder.tv_time.setText(data.getTime());
        holder.tv_time.setTypeface(GlobalValues.getFont(mContext));

        holder.iv_boolmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("true".equals(data.getBookmark())){
                    data.setBookmark("false");
                }else{
                    data.setBookmark("true");
                }
                MallDBTable.getInstance(mContext).UpdateGroup(data);
                notifyDataSetChanged();
            }
        });
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
