package jbink.appnapps.fittingmodel.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {

	//SharedPreference 이름
	static public String SHARED_PREF_NAME = "FittingModel";

	/*************************************************************************************************/
	//처음실행
	static public String PREF_FIRST_PLAY = "first_play";
	/**
	 * 처음실행
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setFirstPlay(Context ctx, boolean value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putBoolean(PREF_FIRST_PLAY, value);
		editor.commit();
	}

	/**
	 * 처음실행
	 *
	 *  default -> true
	 */
	static public boolean getFirstPlay(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_FIRST_PLAY, true);
	}


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

		static public String PREF_PASSWORD = "app_password";
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
	static public String PREF_ID = "app_id";
	/**
	 * 아이디저장
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setId(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_ID, value);
		editor.commit();
	}

	/**
	 * 아이디저장
	 *
	 *  default -> true
	 */
	static public String getId(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_ID, "");
	}
/*************************************************************************************************/
	//몰, 모델 구분
	static public String PREF_TYPE = "app_login_type";
	/**
	 * 아이디저장
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setLoginType(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_TYPE, value);
		editor.commit();
	}

	/**
	 * 아이디저장
	 *
	 *  default -> true
	 */
	static public String getLoginType(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_TYPE, "");
	}
//
// PW
//	static public String PREF_MALL_PASSWORD = "mall_password";
//	/**
//	 * PW
//	 *
//	 *  ctx
//	 *  value = Member UID
//	 */
//	static public void setMallPw(Context ctx, String value) {
//		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
//		editor.putString(PREF_MALL_PASSWORD, value);
//		editor.commit();
//	}
//
//	/**
//	 * PW
//	 *
//	 *  default -> null
//	 */
//	static public String getMallPw(Context ctx) {
//		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MALL_PASSWORD, null);
//	}
///*************************************************************************************************/
//	//아이디저장
//	static public String PREF_MALL_ID = "mall_id";
//	/**
//	 * 아이디저장
//	 *
//	 *  ctx
//	 *  value = Member UID
//	 */
//	static public void setMallId(Context ctx, String value) {
//		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
//		editor.putString(PREF_MALL_ID, value);
//		editor.commit();
//	}
//
//	/**
//	 * 아이디저장
//	 *
//	 *  default -> true
//	 */
//	static public String getMallId(Context ctx) {
//		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MALL_ID, "");
//	}
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
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_AUTO_LOGIN, false);
	}
/*************************************************************************************************/
	//자동로그인이 가능한지 check
	static public String PREF_AUTO_LOGIN_CHECK = "auto_login_check";
	/**
	 * 자동로그인 가능한지
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setAutoLoginCheck(Context ctx, boolean value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putBoolean(PREF_AUTO_LOGIN_CHECK, value);
		editor.commit();
	}

	/**
	 * 자동로그인이 가능한지
	 *
	 *  default -> false
	 */
	static public boolean getAutoLoginCheck(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(PREF_AUTO_LOGIN_CHECK, false);
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
	 * 가게이름
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
	static public String PREF_BUSINESS_NAME = "business_name";
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
	static public String PREF_SHOP_ADDRESS = "shop_address";
	/**
	 * 주소
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setShopAddress(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_SHOP_ADDRESS, value);
		editor.commit();
	}

	/**
	 * 주소
	 *
	 *  default -> true
	 */
	static public String getShopAddress(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_SHOP_ADDRESS, "");
	}

/*************************************************************************************************/

	//URL
	static public String PREF_SHOP_URL = "shop_url";
	/**
	 * URL
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setShopUrl(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_SHOP_URL, value);
		editor.commit();
	}

	/**
	 * URL
	 *
	 *  default -> true
	 */
	static public String getShopUrl(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_SHOP_URL, "");
	}

/*************************************************************************************************/

	//카테고리
	static public String PREF_SHOP_CATEGORY = "shop_category";
	/**
	 * 카테고리
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setShopCategory(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_SHOP_CATEGORY, value);
		editor.commit();
	}

	/**
	 * 카테고리
	 *
	 *  default -> true
	 */
	static public String getShopCategory(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_SHOP_CATEGORY, "");
	}

/*************************************************************************************************/

	//가게소개
	static public String PREF_SHOP_INTRODUCE = "shop_introduce";
	/**
	 * 가게소개
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setShopIntroduce(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_SHOP_INTRODUCE, value);
		editor.commit();
	}

	/**
	 * 가게소개
	 *
	 *  default -> true
	 */
	static public String getShopIntroduce(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_SHOP_INTRODUCE, "");
	}

/*************************************************************************************************/

////////////////////////////////////////////////////////////////////////////////////////////////////
	/*  모델 part */

	/*************************************************************************************************/
//	// PW
//	static public String PREF_MODEL_PASSWORD = "model_password";
//	/**
//	 * PW
//	 *
//	 *  ctx
//	 *  value = Member UID
//	 */
//	static public void setModelPw(Context ctx, String value) {
//		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
//		editor.putString(PREF_MODEL_PASSWORD, value);
//		editor.commit();
//	}
//
//	/**
//	 * PW
//	 *
//	 *  default -> null
//	 */
//	static public String getModelPw(Context ctx) {
//		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_PASSWORD, null);
//	}
//	/*************************************************************************************************/
//	//아이디저장
//	static public String PREF_MODEL_ID = "model_id";
//	/**
//	 * 아이디저장
//	 *
//	 *  ctx
//	 *  value = Member UID
//	 */
//	static public void setModelId(Context ctx, String value) {
//		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
//		editor.putString(PREF_MODEL_ID, value);
//		editor.commit();
//	}
//
//	/**
//	 * 아이디저장
//	 *
//	 *  default -> ""
//	 */
//	static public String getModelId(Context ctx) {
//		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_ID, "");
//	}

	/*************************************************************************************************/
	//모델이름
	static public String PREF_MODEL_NAME = "model_name";
	/**
	 * 모델이름
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setModelName(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_MODEL_NAME, value);
		editor.commit();
	}

	/**
	 * 모델이름
	 *
	 *  default -> true
	 */
	static public String getModelName(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_NAME, "");
	}


	/*************************************************************************************************/

	//주소
	static public String PREF_MODEL_ADDRESS = "model_address";
	/**
	 * 주소
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setModelAddress(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_MODEL_ADDRESS, value);
		editor.commit();
	}

	/**
	 * 주소
	 *
	 *  default -> true
	 */
	static public String getModelAddress(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_ADDRESS, "");
	}

	/*************************************************************************************************/

	//카테고리
	static public String PREF_MODEL_CATEGORY = "model_category";
	/**
	 * 카테고리
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setModelCategory(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_MODEL_CATEGORY, value);
		editor.commit();
	}

	/**
	 * 카테고리
	 *
	 *  default -> true
	 */
	static public String getModelCategory(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_CATEGORY, "");
	}

	/*************************************************************************************************/

	//모델 간단소개
	static public String PREF_MODEL_INTRODUCE = "model_introduce";
	/**
	 * 모델 간단소개
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setModelIntroduce(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_MODEL_INTRODUCE, value);
		editor.commit();
	}

	/**
	 * 모델 간단소개
	 *
	 *  default -> true
	 */
	static public String getModelIntroduce(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_INTRODUCE, "");
	}

	/*************************************************************************************************/

	//URL
	static public String PREF_MODEL_PIC_URL = "model_pic_url";
	/**
	 * 사진URL
	 *
	 *  ctx
	 *  value = Member UID
	 */
	static public void setModelPicUrl(Context ctx, String value) {
		SharedPreferences.Editor editor = ctx.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
		editor.putString(PREF_MODEL_PIC_URL, value);
		editor.commit();
	}

	/**
	 * 사진 URL
	 *
	 *  default -> true
	 */
	static public String getModelPicUrl(Context ctx) {
		return ctx.getSharedPreferences(SHARED_PREF_NAME, 0).getString(PREF_MODEL_PIC_URL, "");
	}

/*************************************************************************************************/


}
