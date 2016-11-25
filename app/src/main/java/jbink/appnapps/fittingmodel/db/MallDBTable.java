package jbink.appnapps.fittingmodel.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import jbink.appnapps.fittingmodel.mall.common.MallCommonData;

public class MallDBTable {
	
	private Context mContext;
	
	private final String TABLE_NAME = MallDBTableInfo.TABLE_NAME;
	
	private volatile static MallDBTable sIntance = null;
	private final DBHelper mHelper;
	
	public static MallDBTable getInstance(Context context){
		if(sIntance == null){
			synchronized ((MallDBTable.class)) {
				sIntance = new MallDBTable(context);
			}
		}
		return sIntance;
	}
	
	private MallDBTable(Context context){
		mHelper = new DBHelper(context);
		mContext = context;
	}
	
	public synchronized long InsertGroup(MallCommonData mall){
		final ContentValues values = new ContentValues();
		values.put(MallDBTableInfo.FIELD_MALL_PICTURE, mall.getPicture());
		values.put(MallDBTableInfo.FIELD_MALL_NAME, mall.getShop());
		values.put(MallDBTableInfo.FIELD_MALL_COST, mall.getCost());
		values.put(MallDBTableInfo.FIELD_MALL_CONTENT, mall.getContent());
		values.put(MallDBTableInfo.FIELD_MALL_TIME, mall.getTime());
		values.put(MallDBTableInfo.FIELD_MALL_BOOKMARK,  mall.getBookmark());
		values.put(MallDBTableInfo.FIELD_MALL_RECOMMEND,  mall.getRecommend());
//		values.put(MallDBTableInfo.FIELD_REPORTER_ID, reporter_id);
//		values.put(MallDBTableInfo.FIELD_TEXT, txt);
		
		return mHelper.insert(TABLE_NAME, values);
	}
	public synchronized long UpdateGroup(MallCommonData mall){
		final ContentValues values = new ContentValues();
		values.put(MallDBTableInfo.FIELD_MALL_PICTURE, mall.getPicture());
		values.put(MallDBTableInfo.FIELD_MALL_NAME, mall.getShop());
		values.put(MallDBTableInfo.FIELD_MALL_COST, mall.getCost());
		values.put(MallDBTableInfo.FIELD_MALL_CONTENT, mall.getContent());
		values.put(MallDBTableInfo.FIELD_MALL_TIME, mall.getTime());
		values.put(MallDBTableInfo.FIELD_MALL_BOOKMARK,  mall.getBookmark());
		values.put(MallDBTableInfo.FIELD_MALL_RECOMMEND,  mall.getRecommend());

		return mHelper.update(TABLE_NAME, values, MallDBTableInfo.FIELD_MALL_NAME + " = ?", new String[] { mall.getShop() });
	}

	public long suidUpdateDB(MallCommonData mall){
		final ContentValues values = new ContentValues();
		values.put(MallDBTableInfo.FIELD_MALL_NAME, mall.getShop());
		return mHelper.update(MallDBTableInfo.TABLE_NAME, values, MallDBTableInfo.FIELD_MALL_NAME + " = ?", new String[] { String.valueOf(mall.getShop()) });
	}
	
//	public String suidQueryDB(String mall_name){
//		Cursor cursor = null;
//		try{
//			cursor = mHelper.query(MallDBTableInfo.TABLE_NAME, new String[]{MallDBTableInfo.FIELD_GROUP_NAME}, MallDBTableInfo.FIELD_GROUP_NAME + " = ? ", new String[] { mall_name } , null);
//			if(cursor != null && cursor.moveToFirst())
//				return cursor.getString(cursor.getColumnIndex(MallDBTableInfo.FIELD_GROUP_NAME));
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
//			cursor = mHelper.query(MallDBTableInfo.TABLE_NAME, new String[]{MallDBTableInfo.FIELD_GROUP_NAME}, MallDBTableInfo.FIELD_GROUP_NAME + " = ? ", new String[] { mall_name } , null);
//			if(cursor != null && cursor.moveToFirst())
//				return true;
//			else
//				return false;
//		}finally{
//			if(cursor != null)
//				cursor.close();
//		}
//	}
	
	public List<MallCommonData> queryAllDatas(){
		Cursor cursor = null;
		List<MallCommonData> items = new ArrayList<MallCommonData>();
		try{
			cursor = mHelper.query(MallDBTableInfo.TABLE_NAME, null, null, null, null);
			
			final int pictureIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_PICTURE);
			final int nameIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_NAME);
			final int costIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_COST);
			final int contentIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_CONTENT);
			final int timeIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_TIME);
			final int bookmarkIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_BOOKMARK);
			final int recommendIndex = cursor.getColumnIndex(MallDBTableInfo.FIELD_MALL_RECOMMEND);

			
			if(cursor != null && cursor.moveToFirst()){
				do{
					final String pic = cursor.getString(pictureIndex);
					final String name = cursor.getString(nameIndex);
					final String cost = cursor.getString(costIndex);
					final String content = cursor.getString(contentIndex);
					final String time = cursor.getString(timeIndex);
					final String bookmark = cursor.getString(bookmarkIndex);
					final String recommend = cursor.getString(recommendIndex);
					items.add(new MallCommonData(pic, name, cost, content, time, bookmark, recommend));
					
				}while(cursor.moveToNext());
			}
//				return cursor.getString(cursor.getColumnIndex(MallDBTableInfo.FIELD_TEXT));
//			else
//				return null;
		}finally{
			if(cursor != null)
				cursor.close();
		}
		return items;
	}
	
	public long deleteGroup(String mall){
		return mHelper.delete(MallDBTableInfo.TABLE_NAME, MallDBTableInfo.FIELD_MALL_NAME + " = ?", new String[]{ mall });
	}

	public long delete(){
		return mHelper.delete(MallDBTableInfo.TABLE_NAME, null, null);
	}
	
	public Cursor queryAllData(){
		return mHelper.query(MallDBTableInfo.TABLE_NAME, null, null, null, null);
	}
	
	class MallDBTableInfo implements BaseColumns{
		static final String TABLE_NAME = "fit_mall";
		static final String FIELD_MALL_PICTURE = "mall_picture";
		static final String FIELD_MALL_NAME = "mall_name";
		static final String FIELD_MALL_COST = "mall_cost";
		static final String FIELD_MALL_CONTENT = "mall_content";
		static final String FIELD_MALL_TIME = "mall_time";
		static final String FIELD_MALL_BOOKMARK = "mall_bookmark";
		static final String FIELD_MALL_RECOMMEND = "mall_recommend";
//		static final String FIELD_REPORTER_ID = "reporter_id";
	}
}
