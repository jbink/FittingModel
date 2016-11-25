package jbink.appnapps.fittingmodel.model.common;

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
import jbink.appnapps.fittingmodel.db.ModelDBTable;
import jbink.appnapps.fittingmodel.util.GlobalValues;

/**
 * Created by user on 2016-11-14.
 */
public class ModelCommonAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<ModelCommonData> mRowList;
    Context mContext;

    public ModelCommonAdapter(Context context, List<ModelCommonData> mRowList) {
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

        final ModelCommonData data = mRowList.get(position);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.model_row_common, viewGroup, false);
            holder = new ViewHolder();

            holder.iv_picture = (ImageView)convertView.findViewById(R.id.model_row_common_img_pic);
            holder.tv_name = (TextView)convertView.findViewById(R.id.model_row_common_txt_name);
            holder.tv_age = (TextView)convertView.findViewById(R.id.model_row_common_txt_age);
            holder.tv_content = (TextView)convertView.findViewById(R.id.model_row_common_txt_content);
            holder.tv_category1 = (TextView)convertView.findViewById(R.id.model_row_common_txt_category_1);
            holder.tv_category2 = (TextView)convertView.findViewById(R.id.model_row_common_txt_category_2);
            holder.tv_address = (TextView)convertView.findViewById(R.id.model_row_common_txt_address);
            holder.tv_time = (TextView)convertView.findViewById(R.id.model_row_common_txt_time);
            holder.iv_boolmark = (ImageView)convertView.findViewById(R.id.model_row_common_img_bookmark);

            holder.tv_name.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_age.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_category1.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_category2.setTypeface(GlobalValues.getBoldFont(mContext));
            holder.tv_content.setTypeface(GlobalValues.getLightFont(mContext));
            holder.tv_time.setTypeface(GlobalValues.getFont(mContext));
            holder.tv_address.setTypeface(GlobalValues.getFont(mContext));

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
        int id = mContext.getResources().getIdentifier("model0"+data.getPicture(), "drawable", mContext.getPackageName());
//        int id = mContext.getResources().getIdentifier("shop0"+(position+1), "drawable", mContext.getPackageName());
        holder.iv_picture.setBackgroundResource(id);

        holder.tv_name.setText(data.getName());
        holder.tv_content.setText(data.getContent());
        holder.tv_age.setText(data.getAge());
        holder.tv_time.setText(data.getTime());
        holder.tv_category1.setText(data.getCategory1());
        holder.tv_category2.setText(data.getCategory2());
        holder.tv_address.setText(data.getAddress());

        holder.iv_boolmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("true".equals(data.getBookmark())){
                    data.setBookmark("false");
                }else{
                    data.setBookmark("true");
                }
                ModelDBTable.getInstance(mContext).UpdateGroup(data);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder{
        ImageView iv_picture;
        TextView tv_name;
        TextView tv_content;
        TextView tv_age;
        TextView tv_category1;
        TextView tv_category2;
        TextView tv_time;
        TextView tv_address;
        ImageView iv_boolmark;
    }
}
