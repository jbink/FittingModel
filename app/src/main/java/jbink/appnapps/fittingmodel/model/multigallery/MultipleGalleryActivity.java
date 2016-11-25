package jbink.appnapps.fittingmodel.model.multigallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import jbink.appnapps.fittingmodel.R;
import jbink.appnapps.fittingmodel.util.GlobalValues;
import jbink.appnapps.fittingmodel.util.ImageUtil;


public class MultipleGalleryActivity extends Activity {

	private final int MAX_SELECT_PICTURE = 10;

	Context mContext;

	GridView gridGallery;
	Handler handler;
	MultipleGalleryAdapter adapter;

	ImageView imgNoMedia;
	Button btnGalleryOk;

	String action;
	private ImageLoader imageLoader;
	
	Spinner mSpinner;
	AlbumSpinnerAdapter mAdapterSpinner;
	
	ArrayList<MultipleGalleryData> mDataGallery;
	ArrayList<AlbumData> mDataAlbum;
	
	TextView mTvTotalCount;
	int mIntTotalCount = 0;

	TextView mTvSelectSpinnerTxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.multiple_select_gallery);
		GlobalValues.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));

		mContext = MultipleGalleryActivity.this;



//		action = getIntent().getAction();
//		if (action == null) {
//			finish();
//		}
		initImageLoader();
		init();
	}

	private void initImageLoader() {
		try {
			String CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.temp_tmp";
			new File(CACHE_DIR).mkdirs();

			File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
					CACHE_DIR);

			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
					.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
					.bitmapConfig(Bitmap.Config.RGB_565).build();
			ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
					getBaseContext())
					.defaultDisplayImageOptions(defaultOptions)
					.memoryCache(new WeakMemoryCache());

			ImageLoaderConfiguration config = builder.build();
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);

		} catch (Exception e) {

		}
	}

	private void init() {
		mSpinner = (Spinner)findViewById(R.id.album_spinner);
		mTvTotalCount = (TextView)findViewById(R.id.txt_total_count_1);
		mTvTotalCount.setText("0");
		mTvTotalCount.setTypeface(GlobalValues.getFont(mContext));
		((TextView)findViewById(R.id.txt_total_count_0)).setTypeface(GlobalValues.getFont(mContext));
		((TextView)findViewById(R.id.txt_total_count_0)).setText("총 ");
		((TextView)findViewById(R.id.txt_total_count_2)).setTypeface(GlobalValues.getFont(mContext));
		((TextView)findViewById(R.id.txt_total_count_2)).setText("개");

		mTvSelectSpinnerTxt = (TextView)findViewById(R.id.album_spinner_txt);
		mTvSelectSpinnerTxt.setTypeface(GlobalValues.getFont(mContext));
		
		mSpinner.setOnItemSelectedListener(mItemClickListener);

		handler = new Handler();
		gridGallery = (GridView) findViewById(R.id.gridGallery);
//		gridGallery.setFastScrollEnabled(true);
		adapter = new MultipleGalleryAdapter(getApplicationContext(), imageLoader, this, true);
		PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader, true, true);
		gridGallery.setOnScrollListener(listener);
		gridGallery.setVerticalScrollBarEnabled(false);
//		gridGallery.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); 

//		if (action.equalsIgnoreCase(Action.ACTION_MULTIPLE_PICK)) {

			findViewById(R.id.llBottomContainer).setVisibility(View.VISIBLE);
			gridGallery.setOnItemClickListener(mItemMulClickListener);
			adapter.setMultiplePick(true);

//		} else if (action.equalsIgnoreCase(Action.ACTION_PICK)) {
//
//			findViewById(R.id.llBottomContainer).setVisibility(View.GONE);
//			gridGallery.setOnItemClickListener(mItemSingleClickListener);
//			adapter.setMultiplePick(false);
//
//		}

		gridGallery.setAdapter(adapter);
		imgNoMedia = (ImageView) findViewById(R.id.imgNoMedia);

		btnGalleryOk = (Button) findViewById(R.id.btnGalleryOk);
		btnGalleryOk.setTypeface(GlobalValues.getFont(mContext));
		btnGalleryOk.setOnClickListener(mOkClickListener);

		new Thread() {

			@Override
			public void run() {
				Looper.prepare();
				handler.post(new Runnable() {

					@Override
					public void run() {
						mDataGallery = getGalleryPhotos();
						adapter.addAll(mDataGallery, 0);
						checkImageStatus();
					}
				});
				Looper.loop();
			};

		}.start();

	}

	private void checkImageStatus() {
		if (adapter.isEmpty()) {
			imgNoMedia.setVisibility(View.VISIBLE);
		} else {
			imgNoMedia.setVisibility(View.GONE);
		}
	}

	View.OnClickListener mOkClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			ArrayList<MultipleGalleryData> selected = adapter.getSelected();

			String[] allPath = new String[selected.size()];
			for (int i = 0; i < allPath.length; i++) {
				allPath[i] = selected.get(i).sdcardPath;
			}

			Intent data = new Intent().putExtra("all_path", allPath);
			setResult(RESULT_OK, data);
			finish();

		}
	};
	AdapterView.OnItemClickListener mItemMulClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			MultipleGalleryData data = (MultipleGalleryData)l.getAdapter().getItem(position);
			if (data.isSeleted == true) {
				mIntTotalCount--;
			} else {
				mIntTotalCount++;
			}
			if(mIntTotalCount == MAX_SELECT_PICTURE+1){
				mIntTotalCount--;
				Toast.makeText(mContext, "사진은 최대 "+MAX_SELECT_PICTURE+"장까지 가능합니다.", Toast.LENGTH_SHORT).show();
				return;
			}
			for(int i=0 ; i<mDataAlbum.size() ; i++){
				if(data.albumname.equals(mDataAlbum.get(i).getName())){
//					mAdapterSpinner.changeCount(i, data.isSeleted);
					if (data.isSeleted == true) {
						mDataAlbum.get(i).setCount(mDataAlbum.get(i).getCount()-1);
					} else {
						mDataAlbum.get(i).setCount(mDataAlbum.get(i).getCount()+1);
					}
				}
			}
			mTvTotalCount.setText("" + mIntTotalCount);
			adapter.changeSelection(v, position);
			
//			mAdapterSpinner.removeAllData();
//			mAdapterSpinner.addItems(mDataAlbum);
	
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
//			        adapter.notifyDataSetChanged();
					mAdapterSpinner.notifyDataSetChanged();
				}
			});

		}
	};

	AdapterView.OnItemClickListener mItemSingleClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			MultipleGalleryData item = adapter.getItem(position);
			Intent data = new Intent().putExtra("single_path", item.sdcardPath);
			setResult(RESULT_OK, data);
			finish();
		}
	};
	
	//스피너 클릭 리스너 - 앨범명에 따라 분류
	OnItemSelectedListener mItemClickListener = new OnItemSelectedListener() {


		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//			((TextView)((RelativeLayout)((LinearLayout)parent.getChildAt(0)).getChildAt(0)).getChildAt(0)).setTextColor(Color.WHITE);
			ArrayList<MultipleGalleryData> tempData = new ArrayList<MultipleGalleryData>();
			if(position == 0){
				adapter.clear();
				adapter.addAll(mDataGallery, -1);
				return;
			}
			AlbumData data = (AlbumData)parent.getAdapter().getItem(position);
			mTvSelectSpinnerTxt.setText(data.getName());
			for(int i=0 ; i<mDataGallery.size() ; i++){
				if(data.getName().equals(mDataGallery.get(i).albumname)){
					tempData.add(mDataGallery.get(i));
				}
			}
			adapter.clear();
			adapter.addAll(tempData, -1);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	};

	private ArrayList<MultipleGalleryData> getGalleryPhotos() {
		ArrayList<MultipleGalleryData> galleryList = new ArrayList<MultipleGalleryData>();

		
		
		 Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		    String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
//		    final String orderBy = MediaStore.Images.Media.DATE_TAKEN;

		    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

		    ArrayList<String> ids = new ArrayList<String>();
		    mDataAlbum = new ArrayList<AlbumData>();
		    mDataAlbum.add(new AlbumData("전체", 0));
//		    mAlbumsList.clear();
		    if (cursor != null) {
		        while (cursor.moveToNext()) {
//		            Album album = new Album();

		            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
		            String id = cursor.getString(columnIndex);

		            if (!ids.contains(id)) {
		                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//		                album.name = cursor.getString(columnIndex);
		                
		                ids.add(id);
		                mDataAlbum.add(new AlbumData(cursor.getString(columnIndex), 0));

		            } else {
//		            	Log.d("value", "111앨범명 : "+cursor.getString(columnIndex));
//		            	mAlbumsList.get(ids.indexOf(album.id)).count++;
		            }
		        }
		        cursor.close();
				mAdapterSpinner = new AlbumSpinnerAdapter(this, mDataAlbum);
				mSpinner.setAdapter(mAdapterSpinner);
		    }
		        
		        
		
		
		
		
		try {
			final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME , MediaStore.Images.Media._ID };
			final String orderBy = MediaStore.Images.Media._ID;

			Cursor imagecursor = managedQuery(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
					null, null, orderBy);

			if (imagecursor != null && imagecursor.getCount() > 0) {

				while (imagecursor.moveToNext()) {
					MultipleGalleryData item = new MultipleGalleryData();

					int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
					int columnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
					int titleIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.TITLE);
					item.sdcardPath = imagecursor.getString(dataColumnIndex);
					item.albumname = imagecursor.getString(columnIndex);
					item.degree = ImageUtil.getPhotoOrientationDegree(item.sdcardPath);

//					Log.d("value", "PATH : "+item.sdcardPath);
//					Log.d("value", "ORIENTAITION : "+ ImageUtil.getPhotoOrientationDegree(item.sdcardPath));

					galleryList.add(item);
					
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// show newest photo at beginning of the list
		Collections.reverse(galleryList);
		return galleryList;
	}

}
