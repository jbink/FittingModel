package jbink.appnapps.fittingmodel.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {

	//SharedPreference 이름
	static public String SHARED_PREF_NAME = "FittingModel";
/*************************************************************************************************/
	//TokenID
	static public String PREF_TOKEN_ID = "token_id";
	/**
	 * Token ID
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setTokenID(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_TOKEN_ID, value);
		editor.commit();
	}

	/**
	 * Token ID
	 *
	 *  default -> null
	 */
	static public String getTokenID(Context ctx) {
//		return "L9d1XqDdX14=";
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_TOKEN_ID, null);
	}
/*************************************************************************************************/
	// EMAIL(ID)
	static public String PREF_EMAIL = "email_id";
	/**
	 * EMAIL(ID)
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setEmail(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_EMAIL, value);
		editor.commit();
	}

	/**
	 * EMAIL(ID)
	 *
	 *  default -> null
	 */
	static public String getEmail(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_EMAIL, null);
	}
/*************************************************************************************************/
	// PW
	static public String PREF_PASSWORD = "password";
	/**
	 * PW
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setPw(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_PASSWORD, value);
		editor.commit();
	}

	/**
	 * PW
	 *
	 *  default -> null
	 */
	static public String getPw(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_PASSWORD, null);
	}
/*************************************************************************************************/
	//아이디저장
	static public String PREF_SAVE_ID = "save_id";
	/**
	 * 아이디저장
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setSaveId(Context ctx, boolean value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putBoolean(PREF_SAVE_ID, value);
		editor.commit();
	}

	/**
	 * 아이디저장
	 *
	 *  default -> true
	 */
	static public boolean getSaveId(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_SAVE_ID, true);
	}
/*************************************************************************************************/
	//자동로그인
	static public String PREF_AUTO_LOGIN = "auto_login";
	/**
	 * 자동로그인
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setAutoLogin(Context ctx, boolean value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putBoolean(PREF_AUTO_LOGIN, value);
		editor.commit();
	}

	/**
	 * 자동로그인
	 *
	 *  default -> true
	 */
	static public boolean getAutoLogin(Context ctx) {
//		return "L9d1XqDdX14=";
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_AUTO_LOGIN, true);
	}


/*************************************************************************************************/
	//푸쉬카운트
	static public String PREF_PUSH_COUNT = "push_count";
	/**
	 * 푸쉬카운트
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setPushCount(Context ctx, int value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putInt(PREF_PUSH_COUNT, value);
		editor.commit();
	}

	/**
	 * 푸쉬카운트
	 *
	 *  default -> 0
	 */
	static public int getPushCount(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getInt(PREF_PUSH_COUNT, 0);
	}








	//App Version
	static public String PREF_APP_VERSION = "app_version";
	/**
	 * App Version
	 * @param ctx
	 * @param value = App Version
	 */
	static public void setAppVersion(Context ctx, int value){
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putInt(PREF_APP_VERSION, value);
		editor.commit();
	}
	/**
	 * App Version
	 * @return
	 * default -> 0
	 */
	static public int getAppVersion(Context ctx){
//		return "L9d1XqDdX14=";
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getInt(PREF_APP_VERSION, 0);
	}


}
