/*
 * Copyright 2011-2012 by RICHWARE SYSTEMS Corp., All rights reserved.
 * 
 * This software is the confidential and proprietary information of RICHWARE
 * SYSTEMS Corp. ("Confidential Information").
 */
package jbink.appnapps.fittingmodel.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.NinePatchDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtil
{

	public static Bitmap getBitmap(String url)
	{
		URL myFileUrl = null;
		Bitmap bmImg = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		int tryCount = 0;

		if("".equals(url))
		{

		}
		else
		{

			Log.d("url", "getBitmap : " + url);

			while (true)
			{
				tryCount++;

				if(tryCount >= 18)
					break;

				try
				{
					myFileUrl = new URL(url);
					conn = (HttpURLConnection) myFileUrl.openConnection();
					conn.connect();
					conn.setReadTimeout(10 * 1000);

					is = conn.getInputStream();
					if(tryCount < 5)
					{

						bmImg = BitmapFactory.decodeStream(is);

					}
					else if(tryCount < 8)
					{

						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 2;
						options.inJustDecodeBounds = true;

						bmImg = BitmapFactory.decodeStream(is, null, options);

					}
					else if(tryCount < 10)
					{

						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inTempStorage = new byte[64 * 1024];

						bmImg = BitmapFactory.decodeStream(is, null, options);
					}
					else if(tryCount < 12)
					{

						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 4;
						bmImg = BitmapFactory.decodeStream(is, null, options);
					}
					else if(tryCount < 14)
					{

						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 8;

						bmImg = BitmapFactory.decodeStream(is, null, options);
					}
					else
					{

						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inSampleSize = 16;
						bmImg = BitmapFactory.decodeStream(is, null, options);
						Log.d("url", "1/2 - 2: " + url);
					}
				}
				catch (FileNotFoundException e)
				{
					bmImg = null;
					Log.e("imageUtil", "파일을 찾을 수 없습니다.");
					break;
				}
				catch (MalformedURLException e1)
				{
					bmImg = null;
					Log.e("imageUtil", "url을 찾을수가 없습니다.");
					break;
				}
				catch (IOException e1)
				{
					bmImg = null;
					Log.e("imageUtil", e1.getMessage());
					break;
				}
				catch (OutOfMemoryError ex)
				{
					bmImg = null;
					Log.e("imageUtil", "아웃 오브 메모리");
					break;
				}
				catch (Exception e1)
				{
					bmImg = null;
					Log.e("imageUtil", e1.getMessage());
					break;
				}

				finally
				{
					try
					{
						if(is != null)
							is.close();
						if(conn != null)
							conn.disconnect();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

				if(bmImg != null)
					break;
			}

		}

		return bmImg;
	}

	/**
	 * 반사이미지.
	 * 
	 * @param origin
	 * @param x
	 *            //이미지 가로
	 * @param y
	 *            //이미지 세로
	 * @param shadowY
	 * @param shadowMargen
	 * @param imgBorder
	 * @return
	 */
	public static Bitmap addRefractShadow(Bitmap origin, int x, int y, int shadowY, int shadowMargen, int imgBorder)
	{
		if(origin == null)
			return null;

		float scaleX = (float) x / origin.getWidth();
		float scaleY = (float) y / origin.getHeight();

		Matrix matrix = new Matrix();

		matrix.postScale(scaleX, scaleY);

		Bitmap resizeBmp = Bitmap.createBitmap(origin, 0, 0, origin.getWidth(), origin.getHeight(), matrix, true);

		Bitmap bmOriginInBorder = Bitmap.createBitmap(x + (imgBorder * 2), y + (imgBorder * 2), Config.ARGB_8888);

		Log.d("bm", "bmOriginInBorderX=" + bmOriginInBorder.getWidth());
		Log.d("bm", "bmOriginInBorderY=" + bmOriginInBorder.getHeight());
		Canvas borderCanvas = new Canvas(bmOriginInBorder);

		Paint paint = new Paint();
		paint.setAlpha(0);
		borderCanvas.drawRect(0, 0, bmOriginInBorder.getWidth(), bmOriginInBorder.getHeight(), paint);
		borderCanvas.drawBitmap(resizeBmp, imgBorder, imgBorder, null);

		Bitmap bmOrigin = bmOriginInBorder;

		matrix.reset();
		matrix.postScale(1, -1);
		Bitmap bmShadow = Bitmap.createBitmap(bmOrigin, 0, bmOrigin.getHeight() - shadowY, bmOrigin.getWidth(), shadowY, matrix, false);

		Bitmap bmCanvas = Bitmap.createBitmap(bmOrigin.getWidth(), bmOrigin.getHeight() + bmShadow.getHeight() + shadowMargen, bmOrigin.getConfig().ARGB_8888);

		Canvas canvas = new Canvas(bmCanvas);

		canvas.drawBitmap(bmOrigin, 0, 0, null);

		// 그림자 1px단위로 알파값줌
		for (int i = 0; i < shadowY; i++)
		{
			paint.reset();
			paint.setAlpha(200 * (shadowY - i) / shadowY);
			Bitmap temp = Bitmap.createBitmap(bmShadow, 0, i, bmShadow.getWidth(), 1);
			canvas.drawBitmap(temp, 0, bmOrigin.getHeight() + shadowMargen + (temp.getHeight() * i), paint);
		}

		return bmCanvas;
	}

	/**
	 * 회색 그림자.
	 * 
	 * @param origin
	 * @param x
	 *            //이미지 가로
	 * @param y
	 *            //이미지 세로
	 * @param shadowY
	 * @param shadowMargen
	 * @param imgBorder
	 * @return
	 */
	public static Bitmap addShadow(Bitmap origin, int x, int y, int shadowY, int shadowMargen, int imgBorder)
	{

		if(origin == null)
			return null;

		float scaleX = (float) x / origin.getWidth();
		float scaleY = (float) y / origin.getHeight();

		Matrix matrix = new Matrix();

		matrix.postScale(scaleX, scaleY);

		Bitmap resizeBmp = Bitmap.createBitmap(origin, 0, 0, origin.getWidth(), origin.getHeight(), matrix, true);

		Bitmap bmOriginInBorder = Bitmap.createBitmap(x + (imgBorder * 2), y + (imgBorder * 2), Config.ARGB_8888);

		Log.d("bm", "bmOriginInBorderX=" + bmOriginInBorder.getWidth());
		Log.d("bm", "bmOriginInBorderY=" + bmOriginInBorder.getHeight());
		Canvas borderCanvas = new Canvas(bmOriginInBorder);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		borderCanvas.drawRect(0, 0, bmOriginInBorder.getWidth(), bmOriginInBorder.getHeight(), paint);
		borderCanvas.drawBitmap(resizeBmp, imgBorder, imgBorder, null);

		Bitmap bmOrigin = bmOriginInBorder;

		Bitmap bmCanvas = Bitmap.createBitmap(bmOrigin.getWidth(), bmOrigin.getHeight() + shadowY + shadowMargen, bmOrigin.getConfig().ARGB_8888);

		Canvas canvas = new Canvas(bmCanvas);

		LinearGradient gradient = new LinearGradient(0, bmOrigin.getHeight() + shadowMargen, 0, bmCanvas.getHeight(), Color.BLACK, 0xFFffffff, TileMode.CLAMP);
		paint.reset();
		paint.setShader(gradient);
		paint.setAlpha(255 / 3);
		canvas.drawBitmap(bmOrigin, 0, 0, null);
		canvas.drawRect(0, bmOrigin.getHeight() + shadowMargen, bmOrigin.getWidth(), bmCanvas.getHeight(), paint);

		Log.d("bm", "bmCanvasX=" + bmCanvas.getWidth());
		Log.d("bm", "bmCanvasY=" + bmCanvas.getHeight());

		return bmCanvas;
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels)
	{
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap get_ninepatch(Context context, int id, int x, int y)
	{
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);

		byte[] chunk = bitmap.getNinePatchChunk();
		NinePatchDrawable np_drawable = new NinePatchDrawable(bitmap, chunk, new Rect(), null);
		np_drawable.setBounds(0, 0, x, y);

		Bitmap output_bitmap = Bitmap.createBitmap(x, y, Config.ARGB_8888);
		Canvas canvas = new Canvas(output_bitmap);
		np_drawable.draw(canvas);

		return output_bitmap;
	}

	public static Bitmap resizeBitmapImage(Bitmap source, int width, int height)
	{
		// Log.e("imageUtil", "ImageSize", source.getWidth() + "," + source.getHeight());
		return Bitmap.createScaledBitmap(source, width, height, true);
	}

	public static Bitmap getBitmapAddShadow(String url)
	{
		return addRefractShadow(getBitmap(url), 210, 220, 40, 0, 0);
	}

	public static Bitmap getBitmapAsLocal(String pathName)
	{
		return BitmapFactory.decodeFile(pathName);
	}

	public static Bitmap getBitmapResizeAndRound(String url)
	{
		return ImageUtil.getRoundedCornerBitmap(ImageUtil.resizeBitmapImage(ImageUtil.getBitmap(url), 94, 94), 15);
	}

	/**
	 * EXIF정보를 회전각도로 변환하는 메서드
	 * 
	 * @param exifOrientation
	 *            EXIF 회전각
	 * @return 실제 각도
	 */
	public static int exifOrientationToDegrees(int exifOrientation)
	{
		if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
		{
			return 90;
		}
		else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
		{
			return 180;
		}
		else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
		{
			return 270;
		}
		return 0;
	}

	/**
	 * 비트맵을 파일로 저장합니다.
	 * 
	 * @param bitmap
	 *            비트맵 이미지
	 * @param strFilePath
	 *            저장 경로
	 */
	public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath, CompressFormat format)
	{
		Log.e("imageUtil", "SaveBitmapToFileCache (strFilePath) : " + strFilePath);

		File fileCacheItem = new File(strFilePath);
		FileOutputStream fos = null;

		try
		{
			fileCacheItem.createNewFile();
			fos = new FileOutputStream(fileCacheItem);

			bitmap.compress(format, 100, fos);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(fos != null)
					fos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static String uriToMediaPath(Context context, Uri uri)
	{
		String path;
		Cursor c;
		path = null;
		c = null;
		try
		{
			c = context.getContentResolver().query(uri, null, null, null, null);
			c.moveToNext();
			path = c.getString(c.getColumnIndex("_data"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if(c != null)
			c.close();

		return path;
	}

	public static File toFile(String path)
	{
		File file = null;
		if(path != null && !"".equals(path))
			file = new File(path);
		return file;
	}

	public static File toFile(Context context, Uri uri)
	{
		return toFile(uriToMediaPath(context, uri));
	}

	public static String writeImageFile(Context context, byte[] byteArray)
	{
		File fDir = null;
		String imgUri = "";

		try
		{
			if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			{
				fDir = context.getExternalFilesDir(null);
			}
			else
			{
				fDir = context.getFilesDir();
			}

			String localImagePath = "";
			String localImageFileName = "";

			if(fDir != null)
			{
				localImagePath = fDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
				imgUri = SaveByteArrayToFileCache(byteArray, localImagePath);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return imgUri;
	}

	public static String writeImageFile(Context context, byte[] byteArray, int newWidth, int newHeight)
	{
		File fDir = null;
		String imgUri = "";

		try
		{
			if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			{
				fDir = context.getExternalFilesDir(null);
			}
			else
			{
				fDir = context.getFilesDir();
			}

			String localImagePath = "";
			String localImageFileName = "";

			if(fDir != null)
			{
				localImagePath = fDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
				imgUri = SaveByteArrayToFileCache(byteArray, localImagePath, newWidth, newHeight);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return imgUri;
	}

	/**
	 * byte[]를 파일로 저장합니다.
	 * 
	 * @param bytes
	 *            비트맵 이미지
	 * @param strFilePath
	 *            저장 경로
	 */
	public static String SaveByteArrayToFileCache(byte[] bytes, String strFilePath)
	{
		Bitmap bitmap = byteArrayToBitmap(bytes);

		File fileCacheItem = new File(strFilePath);
		OutputStream out = null;

		try
		{

			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);

			bitmap.compress(CompressFormat.JPEG, 100, out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			strFilePath = "";
		}
		finally
		{
			try
			{
				if(out != null)
					out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return strFilePath;
	}

	public static String SaveByteArrayToFileCache(byte[] bytes, String strFilePath, int newWidth, int newHeight)
	{
		Bitmap bitmap = resizeBitmapImage(bytes, newWidth, newHeight);

		File fileCacheItem = new File(strFilePath);
		OutputStream out = null;

		try
		{

			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);

			bitmap.compress(CompressFormat.JPEG, 100, out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			strFilePath = "";
		}
		finally
		{
			try
			{
				if(out != null)
					out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return strFilePath;
	}

	public static byte[] bitmapToByteArray(Bitmap b)
	{
		ByteArrayOutputStream baos = null;
		byte[] byteArray = null;

		try
		{

			baos = new ByteArrayOutputStream();
			b.compress(CompressFormat.JPEG, 100, baos);
			byteArray = baos.toByteArray();

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		finally
		{
			try
			{
				baos.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return byteArray;

	}

	public static Bitmap byteArrayToBitmap(byte[] byteArray)
	{
		Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

		return bitmap;

	}

	public static Bitmap resizeBitmapImage(byte[] byteArray, int width, int height)
	{
		Bitmap source = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		Bitmap resizeBmp = Bitmap.createScaledBitmap(source, width, height, true);
		source.recycle();
		// LogUtil.e("ImageSize", source.getWidth() + "," + source.getHeight());
		return resizeBmp;
	}

	public static float dipToPixel(Context context, int dip)
	{
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dip, context.getResources().getDisplayMetrics());
	}

	/**
	 * 이미지를 회전시킵니다.
	 * 
	 * @param bitmap
	 *            비트맵 이미지
	 * @param degrees
	 *            회전 각도
	 * @return 회전된 이미지
	 */
	public static Bitmap rotate(Bitmap bitmap, int degrees)
	{
		if(degrees != 0 && bitmap != null)
		{
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

			try
			{
				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if(bitmap != converted)
				{
					bitmap.recycle();
					bitmap = converted;
				}
			}
			catch (OutOfMemoryError ex)
			{
				// 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
			}
		}
		return bitmap;
	}

	/**
	 * 이미지 회전 각도를 알아옴.
	 * 
	 * @param filepath
	 * @return 각도
	 */
	public synchronized static int getPhotoOrientationDegree(String filepath)
	{
		int degree = 0;
		ExifInterface exif = null;

		try
		{
			exif = new ExifInterface(filepath);
		}
		catch (IOException e)
		{
		}

		if(exif != null)
		{
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

			if(orientation != -1)
			{
				switch (orientation)
				{
					case ExifInterface.ORIENTATION_ROTATE_90:
						degree = 90;
						break;

					case ExifInterface.ORIENTATION_ROTATE_180:
						degree = 180;
						break;

					case ExifInterface.ORIENTATION_ROTATE_270:
						degree = 270;
						break;
				}

			}
		}
		return degree;
	}

	/**
	 * 이미지를 특정 각도로 회전
	 * @return
	 */
	public synchronized static Bitmap getRotatedBitmap(String path, int degrees)
	{
		if(path == null || path.trim().length() == 0 || path.trim().equals(""))
			return null;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		Bitmap originBitmap = null;
		Bitmap resultBitmap = null;
		int count = 0;

		boolean isSafe = false;

		while (originBitmap == null && !isSafe)
		{
			try
			{
				originBitmap = BitmapFactory.decodeFile(path, opts);
			}
			catch (OutOfMemoryError e)
			{

				count++;

				if(originBitmap != null)
					originBitmap.recycle();

				originBitmap = null;
			}
			catch (Exception e)
			{
				isSafe = true;
			}
			finally
			{
				opts.inSampleSize = 2 * count;
			}
		}

		if(originBitmap != null)
			originBitmap.recycle();

		originBitmap = null;

		if(isSafe)
			return null;

		if(degrees != 0)
		{
			while (resultBitmap == null && count < 5)
			{
				try
				{
					originBitmap = BitmapFactory.decodeFile(path, opts);

					Matrix m = new Matrix();
					m.postRotate(degrees, (float) originBitmap.getWidth() / 2, (float) originBitmap.getHeight() / 2);
					resultBitmap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(), originBitmap.getHeight(), m, true);
				}
				catch (OutOfMemoryError e)
				{

					count++;

					if(resultBitmap != null)
						resultBitmap.recycle();

					if(originBitmap != null)
						originBitmap.recycle();

					resultBitmap = null;
					originBitmap = null;
				}
				finally
				{
					if(resultBitmap == null)
					{
						opts.inSampleSize = 2 * count;
					}
					else
					{
						break;
					}
				}
			}
		}

		if(originBitmap != null)
			originBitmap.recycle();

		originBitmap = null;

		return resultBitmap;
	}

}
