package com.haili.living.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class ImageUtils
{
	
	public static byte[] readInputStream(InputStream in) throws IOException
	{
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int len = 0;
		while ((len = in.read(buffer)) != -1)
		{
			out.write(buffer, 0, len);
		}
		out.close();
		return out.toByteArray();
	}

	public static Bitmap zoom(Bitmap bitmapOrg, int newWidth, int newHeight)
	{
		if (null == bitmapOrg)
		{
			return null;
		}

		int width = bitmapOrg.getWidth();
		int height = bitmapOrg.getHeight();
		Bitmap resizedBitmap = null;

		// if( newWidth > width || newHeight > height){
		// resizedBitmap=bitmapOrg.copy(Bitmap.Config.ARGB_8888, true);
		// return resizedBitmap;
		// }

		// 计算缩放率，新尺寸除原始尺寸
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();

		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);

		resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, true);

		return resizedBitmap;
	}
	
	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size = 1024;
		try
		{
			byte[] bytes = new byte[buffer_size];
			for (;;)
			{
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex)
		{
		}
	}
	public static int readPictureDegree(String path) {
	    int degree  = 0;
	    try {
	        ExifInterface exifInterface = new ExifInterface(path);
	        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
	        switch (orientation) {
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
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return degree;
	}
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
	    //旋转图片 动作
	    Matrix matrix = new Matrix();;
	    matrix.postRotate(angle);
	    // 创建新的图片
	    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
	            bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	    return resizedBitmap;
	}


}
