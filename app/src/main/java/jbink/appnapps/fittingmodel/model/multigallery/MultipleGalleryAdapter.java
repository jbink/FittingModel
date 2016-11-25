package jbink.appnapps.fittingmodel.model.multigallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import jbink.appnapps.fittingmodel.R;

public class MultipleGalleryAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater infalter;
	private ArrayList<MultipleGalleryData> AllData = new ArrayList<MultipleGalleryData>();
	private ArrayList<MultipleGalleryData> data = new ArrayList<MultipleGalleryData>();
	ImageLoader imageLoader;

	private boolean isActionMultiplePick;
	Activity mActivity;
	boolean isShow;
	public MultipleGalleryAdapter(Context c, ImageLoader imageLoader, Activity mActivity, boolean show) {
		infalter = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = c;
		this.imageLoader = imageLoader;
		// clearCache();
		this.mActivity = mActivity;
		isShow = show;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public MultipleGalleryData getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setMultiplePick(boolean isMultiplePick) {
		this.isActionMultiplePick = isMultiplePick;
	}

	public void selectAll(boolean selection) {
		for (int i = 0; i < data.size(); i++) {
			data.get(i).isSeleted = selection;

		}
		notifyDataSetChanged();
	}

	public boolean isAllSelected() {
		boolean isAllSelected = true;

		for (int i = 0; i < data.size(); i++) {
			if (!data.get(i).isSeleted) {
				isAllSelected = false;
				break;
			}
		}

		return isAllSelected;
	}

	public boolean isAnySelected() {
		boolean isAnySelected = false;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).isSeleted) {
				isAnySelected = true;
				break;
			}
		}

		return isAnySelected;
	}

	public ArrayList<MultipleGalleryData> getSelected() {
		ArrayList<MultipleGalleryData> dataT = new ArrayList<MultipleGalleryData>();

		for (int i = 0; i < AllData.size(); i++) {
			if (AllData.get(i).isSeleted) {
				dataT.add(AllData.get(i));
			}
		}

		return dataT;
	}

	public void addAll(ArrayList<MultipleGalleryData> files, int init) {

		try {
			this.data.clear();
			if(init == 0){
				this.AllData.addAll(files);
			}
				
			this.data.addAll(files);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyDataSetChanged();
	}
	
	public void sortAlbum(String name){
		ArrayList<MultipleGalleryData> dataT = new ArrayList<MultipleGalleryData>();
		for (int i = 0; i < data.size(); i++) {
			if (name.equals(data.get(i).albumname)) {
				dataT.add(data.get(i));
			}
		}
		
		notifyDataSetChanged();
			
	}

	public void changeSelection(View v, int position) {

		if (data.get(position).isSeleted) {
			data.get(position).isSeleted = false;
		} else {
			data.get(position).isSeleted = true;
		}

		((ViewHolder) v.getTag()).imgQueueMultiSelected.setSelected(data.get(position).isSeleted);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			if(isShow){
				convertView = infalter.inflate(R.layout.multiple_select_gallery_item, null);
			}else{
				convertView = infalter.inflate(R.layout.multiple_select_gallery_item, null);
			}
			holder = new ViewHolder();
			holder.imgQueue = (ImageView) convertView.findViewById(R.id.imgQueue);
			holder.imgQueue2 = (ImageView) convertView.findViewById(R.id.imgQueue2);

			holder.imgQueueMultiSelected = (ImageView) convertView.findViewById(R.id.imgQueueMultiSelected);

			if (isActionMultiplePick) {
				holder.imgQueueMultiSelected.setVisibility(View.VISIBLE);
			} else {
				holder.imgQueueMultiSelected.setVisibility(View.GONE);
			}

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imgQueue.setTag(position);


		try {

			imageLoader.displayImage("file://" + data.get(position).sdcardPath,
					holder.imgQueue, new ImageLoadingListener() {

				@Override
				public void onLoadingStarted(String arg0, View arg1) {
					holder.imgQueue.setImageResource(R.color.white);
				}

				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				}

				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap bitmap) {
							if (!isRate) {//여기검사
								int degree = data.get(position).degree;
								if(degree != 0){
									bitmap = rotateImage(bitmap, degree);
								}
								int width = bitmap.getWidth();
								int height = bitmap.getHeight();
								holder.imgQueue.setImageBitmap(bitmap);
//								if (width > height) {//사용하지 않음
//									holder.imgQueue.setImageBitmap(resizeBitmapImage(bitmap));
//								} else {
//									holder.imgQueue.setImageBitmap(resizeBitmapImageHeight(bitmap));
//								}
							} else {
								Bitmap resizeResult = Bitmap.createScaledBitmap(bitmap, 640, 480, true);
								holder.imgQueue.setImageBitmap(resizeResult);
							}

				}

				@Override
				public void onLoadingCancelled(String arg0, View arg1) {
				}
			});

			if (isActionMultiplePick) {

				holder.imgQueueMultiSelected
				.setSelected(data.get(position).isSeleted);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;
	}

	public class ViewHolder {
		ImageView imgQueue, imgQueue2;
		ImageView imgQueueMultiSelected;
	}

	public void clearCache() {
		imageLoader.clearDiscCache();
		imageLoader.clearMemoryCache();
	}

	public void clear() {
		data.clear();
		notifyDataSetChanged();
	}

	// 이미지 회전 함수
	public Bitmap rotateImage(Bitmap src, float degree) {

		// Matrix 객체 생성
		Matrix matrix = new Matrix();
		// 회전 각도 셋팅
		matrix.postRotate(degree);
		// 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(),src.getHeight(), matrix, true);
	}

	//리사이징 이미지 - width
	public Bitmap resizeBitmapImage(Bitmap source) 
	{ 
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		//		int maxWidth = displayMetrics.widthPixels;
		//		int maxHeight = displayMetrics.heightPixels;

		int width = source.getWidth(); 
		int height = source.getHeight(); 
		int newWidth = width; 
		int newHeight = height; 
		float rate = 0.0f;
		int maxWidth =640;
		if(width > maxWidth){
			rate = (float)maxWidth / (float)width;
		}
		else if(width == maxWidth){
			rate = 1.0f;
		}
		else{
			rate = (float)maxWidth / (float)width;
			//        	rate = (float)width / (float)maxWidth;
		}
		newWidth = (int)(rate*width);
		newHeight = (int)(rate*height);

		Log.e("newWidth", String.valueOf(newWidth));
		Log.e("newHeight", String.valueOf(newHeight));
		return Bitmap.createScaledBitmap(source, newWidth, newHeight, true); 
	}

	//리사이징 이미지 - height
	public Bitmap resizeBitmapImageHeight(Bitmap source) 
	{ 
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		//		int maxWidth = displayMetrics.widthPixels;
		//		int maxHeight = displayMetrics.heightPixels;

		int width = source.getWidth(); 
		int height = source.getHeight(); 
		int newWidth = width; 
		int newHeight = height; 
		float rate = 0.0f;
		int maxWidth =480;
		if(height > maxWidth){
			rate = (float)maxWidth / (float)height;
		}
		else if(height == maxWidth){
			rate = 1.0f;
		}
		else{
			rate = (float)maxWidth / (float)height;
			//        	rate = (float)width / (float)maxWidth;
		}
		newWidth = (int)(rate*width);
		newHeight = (int)(rate*height);

		Log.e("newWidth", String.valueOf(newWidth));
		Log.e("newHeight", String.valueOf(newHeight));
		return Bitmap.createScaledBitmap(source, newWidth, newHeight, true); 
	}
	
	public boolean isRate;
	public void setRate(boolean rate){
		isRate = rate;
		notifyDataSetChanged();
	}
	
}
