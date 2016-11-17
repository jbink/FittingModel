package jbink.appnapps.fittingmodel.mall.searchshop;

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
public class MallSearchShopAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<MallSearchShopData> mRowList;
    Context mContext;

    public MallSearchShopAdapter(Context context, List<MallSearchShopData> mRowList) {
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

        final MallSearchShopData data = mRowList.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.mall_row_search_shop, viewGroup, false);
            holder = new ViewHolder();

            holder.iv_picture = (ImageView)convertView.findViewById(R.id.mall_row_search_shop_img);
            holder.tv_name = (TextView)convertView.findViewById(R.id.mall_row_search_shop_txt_name);
            holder.tv_content = (TextView)convertView.findViewById(R.id.mall_row_search_shop_txt_content);

            holder.tv_name.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_content.setTypeface(GlobalValues.getBoldFont(mContext));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }






        return convertView;
    }

    private class ViewHolder{
        ImageView iv_picture;
        TextView tv_name;
        TextView tv_content;
    }
}
