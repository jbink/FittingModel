package jbink.appnapps.fittingmodel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import jbink.appnapps.fittingmodel.db.MallDBTable.MallDBTableInfo;
import jbink.appnapps.fittingmodel.db.ModelDBTable.ModelDBTableInfo;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "FittingModel";
	private static final int DB_VERSION = 1;
	
	private final SQLiteDatabase mDB;

	DBHelper(Context context) {
		this(context, DB_NAME, DB_VERSION );
	}
	
	private DBHelper(Context context, String name, int version) {
		super(context, name, null, version);
		mDB = getWritableDatabase();
	}
	
		

	@Override
	public void onCreate(SQLiteDatabase db) {
		makeMallTableInfo(db);
		makeModelTableInfo(db);
//		makeReporterTableInfo(db);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion < 2){
		}
		if(oldVersion < 3){
			
		}
	}
	
	private void makeMallTableInfo(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + MallDBTableInfo.TABLE_NAME + " ");
		sb.append("(");
		sb.append(MallDBTableInfo._ID		+ " INTEGER PRIMARY KEY AUTOINCREMENT, " );
		sb.append(MallDBTableInfo.FIELD_MALL_PICTURE		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_NAME		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_COST		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_CONTENT		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_TIME		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_BOOKMARK		+ " TEXT," );
		sb.append(MallDBTableInfo.FIELD_MALL_RECOMMEND		+ " TEXT" );
		sb.append(")");
		
		try{
			db.execSQL(sb.toString());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	private void makeModelTableInfo(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + ModelDBTableInfo.TABLE_NAME + " ");
		sb.append("(");
		sb.append(ModelDBTableInfo._ID		+ " INTEGER PRIMARY KEY AUTOINCREMENT, " );
		sb.append(ModelDBTableInfo.FIELD_MODEL_PICTURE		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_NAME		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_CONTENT		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_AGE		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_CATEGORY_1		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_CATEGORY_2		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_TIME		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_ADDRESS		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_BOOKMARK		+ " TEXT," );
		sb.append(ModelDBTableInfo.FIELD_MODEL_RECOMMEND		+ " TEXT" );
		sb.append(")");

		try{
			db.execSQL(sb.toString());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	synchronized long insert(String tableName, ContentValues values){
		return mDB.insert(tableName, null, values);
	}
	
	synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String orderBy){
		return mDB.query(tableName, columns, selection, selectionArgs, null, null, orderBy);
	}
	
	synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String orderBy, String limit){
		return mDB.query(tableName, columns, selection, selectionArgs, null, null, orderBy, limit);
	}
	
	synchronized int delete(String tableName, String whereClause, String[] whereArgs){
		return mDB.delete(tableName, whereClause, whereArgs);
	}
	
	synchronized int update(String tableName, ContentValues values, String whereClause, String[] whereArgs){
		return mDB.update(tableName, values, whereClause, whereArgs);
	}
	

}
