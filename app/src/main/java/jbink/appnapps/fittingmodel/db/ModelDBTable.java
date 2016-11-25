package jbink.appnapps.fittingmodel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import jbink.appnapps.fittingmodel.model.common.ModelCommonData;

public class ModelDBTable {

	private Context mContext;

	private final String TABLE_NAME = ModelDBTableInfo.TABLE_NAME;

	private volatile static ModelDBTable sIntance = null;
	private final DBHelper mHelper;

	public static ModelDBTable getInstance(Context context){
		if(sIntance == null){
			synchronized ((ModelDBTable.class)) {
				sIntance = new ModelDBTable(context);
			}
		}
		return sIntance;
	}

	private ModelDBTable(Context context){
		mHelper = new DBHelper(context);
		mContext = context;
	}
	
	public synchronized long InsertGroup(ModelCommonData model){
		final ContentValues values = new ContentValues();
		values.put(ModelDBTableInfo.FIELD_MODEL_PICTURE, model.getPicture());
		values.put(ModelDBTableInfo.FIELD_MODEL_NAME, model.getName());
		values.put(ModelDBTableInfo.FIELD_MODEL_CONTENT, model.getContent());
		values.put(ModelDBTableInfo.FIELD_MODEL_AGE, model.getAge());
		values.put(ModelDBTableInfo.FIELD_MODEL_CATEGORY_1, model.getCategory1());
		values.put(ModelDBTableInfo.FIELD_MODEL_CATEGORY_2, model.getCategory2());
		values.put(ModelDBTableInfo.FIELD_MODEL_TIME, model.getTime());
		values.put(ModelDBTableInfo.FIELD_MODEL_ADDRESS, model.getAddress());
		values.put(ModelDBTableInfo.FIELD_MODEL_BOOKMARK,  model.getBookmark());
		values.put(ModelDBTableInfo.FIELD_MODEL_RECOMMEND,  model.getRecommend());
//		values.put(ModelDBTableInfo.FIELD_REPORTER_ID, reporter_id);
//		values.put(ModelDBTableInfo.FIELD_TEXT, txt);
		
		return mHelper.insert(TABLE_NAME, values);
	}
	public synchronized long UpdateGroup(ModelCommonData model){
		final ContentValues values = new ContentValues();
		values.put(ModelDBTableInfo.FIELD_MODEL_PICTURE, model.getPicture());
		values.put(ModelDBTableInfo.FIELD_MODEL_NAME, model.getName());
		values.put(ModelDBTableInfo.FIELD_MODEL_CONTENT, model.getContent());
		values.put(ModelDBTableInfo.FIELD_MODEL_AGE, model.getAge());
		values.put(ModelDBTableInfo.FIELD_MODEL_CATEGORY_1, model.getCategory1());
		values.put(ModelDBTableInfo.FIELD_MODEL_CATEGORY_2, model.getCategory2());
		values.put(ModelDBTableInfo.FIELD_MODEL_TIME, model.getTime());
		values.put(ModelDBTableInfo.FIELD_MODEL_ADDRESS, model.getAddress());
		values.put(ModelDBTableInfo.FIELD_MODEL_BOOKMARK,  model.getBookmark());
		values.put(ModelDBTableInfo.FIELD_MODEL_RECOMMEND,  model.getRecommend());

		return mHelper.update(TABLE_NAME, values, ModelDBTableInfo.FIELD_MODEL_NAME + " = ?", new String[] { model.getName() });
	}

	public long suidUpdateDB(ModelCommonData model){
		final ContentValues values = new ContentValues();
		values.put(ModelDBTableInfo.FIELD_MODEL_NAME, model.getName());
		return mHelper.update(ModelDBTableInfo.TABLE_NAME, values, ModelDBTableInfo.FIELD_MODEL_NAME + " = ?", new String[] { String.valueOf(model.getName()) });
	}
	
//	public String suidQueryDB(String mall_name){
//		Cursor cursor = null;
//		try{
//			cursor = mHelper.query(ModelDBTableInfo.TABLE_NAME, new String[]{ModelDBTableInfo.FIELD_GROUP_NAME}, ModelDBTableInfo.FIELD_GROUP_NAME + " = ? ", new String[] { mall_name } , null);
//			if(cursor != null && cursor.moveToFirst())
//				return cursor.getString(cursor.getColumnIndex(ModelDBTableInfo.FIELD_GROUP_NAME));
//			else
//				return null;
//		}finally{
//			if(cursor != null)
//				cursor.close();
//		}
//	}
	
//	public boolean isQueryDB(String mall_name){
//		Cursor cursor = null;
//		try{
//			cursor = mHelper.query(ModelDBTableInfo.TABLE_NAME, new String[]{ModelDBTableInfo.FIELD_GROUP_NAME}, ModelDBTableInfo.FIELD_GROUP_NAME + " = ? ", new String[] { mall_name } , null);
//			if(cursor != null && cursor.moveToFirst())
//				return true;
//			else
//				return false;
//		}finally{
//			if(cursor != null)
//				cursor.close();
//		}
//	}
	
	public List<ModelCommonData> queryAllDatas(){
		Cursor cursor = null;
		List<ModelCommonData> items = new ArrayList<ModelCommonData>();
		try{
			cursor = mHelper.query(ModelDBTableInfo.TABLE_NAME, null, null, null, null);
			
			final int pictureIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_PICTURE);
			final int nameIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_NAME);
			final int contentIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_CONTENT);
			final int ageIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_AGE);
			final int category1Index = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_CATEGORY_1);
			final int category2Index = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_CATEGORY_2);
			final int timeIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_TIME);
			final int addressIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_ADDRESS);
			final int bookmarkIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_BOOKMARK);
			final int recommendIndex = cursor.getColumnIndex(ModelDBTableInfo.FIELD_MODEL_RECOMMEND);

			
			if(cursor != null && cursor.moveToFirst()){
				do{
					final String pic = cursor.getString(pictureIndex);
					final String name = cursor.getString(nameIndex);
					final String content = cursor.getString(contentIndex);
					final String age = cursor.getString(ageIndex);
					final String category1 = cursor.getString(category1Index);
					final String category2 = cursor.getString(category2Index);
					final String time = cursor.getString(timeIndex);
					final String address = cursor.getString(addressIndex);
					final String bookmark = cursor.getString(bookmarkIndex);
					final String recommend = cursor.getString(recommendIndex);
					items.add(new ModelCommonData(pic, name, content, age, category1, category2, time, address, bookmark, recommend));
					
				}while(cursor.moveToNext());
			}
//				return cursor.getString(cursor.getColumnIndex(ModelDBTableInfo.FIELD_TEXT));
//			else
//				return null;
		}finally{
			if(cursor != null)
				cursor.close();
		}
		return items;
	}
	
	public long deleteGroup(String mall){
		return mHelper.delete(ModelDBTableInfo.TABLE_NAME, ModelDBTableInfo.FIELD_MODEL_NAME + " = ?", new String[]{ mall });
	}

	public long delete(){
		return mHelper.delete(ModelDBTableInfo.TABLE_NAME, null, null);
	}
	
	public Cursor queryAllData(){
		return mHelper.query(ModelDBTableInfo.TABLE_NAME, null, null, null, null);
	}
	
	class ModelDBTableInfo implements BaseColumns{
		static final String TABLE_NAME = "fit_model";
		static final String FIELD_MODEL_PICTURE = "model_picture";
		static final String FIELD_MODEL_NAME = "model_name";
		static final String FIELD_MODEL_CONTENT = "model_content";
		static final String FIELD_MODEL_AGE = "model_age";
		static final String FIELD_MODEL_CATEGORY_1 = "model_category1";
		static final String FIELD_MODEL_CATEGORY_2 = "model_category2";
		static final String FIELD_MODEL_TIME = "model_time";
		static final String FIELD_MODEL_ADDRESS = "model_address";
		static final String FIELD_MODEL_BOOKMARK = "model_bookmark";
		static final String FIELD_MODEL_RECOMMEND = "model_recommend";
//		static final String FIELD_REPORTER_ID = "reporter_id";
	}
}
