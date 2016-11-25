package jbink.appnapps.fittingmodel.model.multigallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.R;

public class AlbumSpinnerAdapter extends BaseAdapter {
	
	LayoutInflater mInflater;
	List<AlbumData> mRowList;
	Context mContext;
//	Typeface font;

	public AlbumSpinnerAdapter(Context context, List<AlbumData> mRowList) {
		super();
		mContext = context;
		this.mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mRowList = mRowList;
//		font = Typeface.createFromAsset(mContext.getAssets(), "fonts/BareunDotum2.ttf");
	}
	
	public void addItems(List<AlbumData> items){
		mRowList.addAll(items);
	}
	
	public void addOneItem(AlbumData item){
		mRowList.add(item);
	}
	
	public void removeAllData(){
		mRowList = new ArrayList<AlbumData>();
	}

	@Override
	public int getCount() {
		if(mRowList == null)
			return 0;
		return mRowList.size();
	}

	@Override
	public AlbumData getItem(int position) {
		if(position >= 0 && position < mRowList.size())
			return mRowList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		final AlbumData data = mRowList.get(position);
		
		
		if(convertView == null){
			//지정폰트
			
			
			convertView = mInflater.inflate(R.layout.row_spinner_item, parent, false);
			holder = new ViewHolder();
			holder.album_name = (TextView)convertView.findViewById(R.id.row_spinner_name);
//			holder.album_name.setTypeface(font);
			holder.album_name.setTextSize(18);
//			holder.album_name.setTypeface(font);
			holder.album_count = (TextView)convertView.findViewById(R.id.row_spinner_count);
//			holder.album_count.setTypeface(font);

			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.album_name.setText(data.getName());
		if(data.getCount() != 0){
			holder.album_count.setVisibility(View.VISIBLE);
			holder.album_count.setText(""+data.getCount());
		}
		else if(data.getCount() == 0)
			holder.album_count.setVisibility(View.INVISIBLE);
		return convertView;
	}
	
	
	
	
	private class ViewHolder{
		TextView album_name;
		TextView album_count;
		
	}

}
