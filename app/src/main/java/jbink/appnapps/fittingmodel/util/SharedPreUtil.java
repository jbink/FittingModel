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
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_AUTO_LOGIN, true);
	}

/*************************************************************************************************/
	//가게이름
	static public String PREF_SHOP_NAME = "shop_name";
	/**
	 * 가게이름
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setShopName(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_SHOP_NAME, value);
		editor.commit();
	}

	/**
	 * 사업자명
	 *
	 *  default -> true
	 */
	static public String getShopName(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_SHOP_NAME, "");
	}

/*************************************************************************************************/
	//사업자번호
	static public String PREF_BUSINESS_NUMBER = "business_number";
	/**
	 * 사업자번호
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setBusinessNumber(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_BUSINESS_NUMBER, value);
		editor.commit();
	}

	/**
	 * 사업자번호
	 *
	 *  default -> true
	 */
	static public String getBusinessNumber(Context ctx) {
//		return "L9d1XqDdX14=";
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_BUSINESS_NUMBER, "");
	}

/*************************************************************************************************/
	//사업자명
	static public String PREF_BUSINESS_NAME = "business_number";
	/**
	 * 사업자명
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setBusinessName(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_BUSINESS_NAME, value);
		editor.commit();
	}

	/**
	 * 사업자명
	 *
	 *  default -> true
	 */
	static public String getBusinessName(Context ctx) {
//		return "L9d1XqDdX14=";
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_BUSINESS_NAME, "");
	}

/*************************************************************************************************/

	//주소
	static public String PREF_ADDRESS = "shop_address";
	/**
	 * 주소
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setAddress(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_ADDRESS, value);
		editor.commit();
	}

	/**
	 * 주소
	 *
	 *  default -> true
	 */
	static public String getAddress(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_ADDRESS, "");
	}

/*************************************************************************************************/




}
