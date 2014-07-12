package com.luairan.inputstream_tools;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.luairan.util.ObjectLoader.ConvertInputStream;
/**
 * 从输入流获取Bitmap
 * @author luairan
 *
 */
public class BitmapFromInputStream implements ConvertInputStream{

	@Override
	public Object getDataFromInputStream(InputStream is) {
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		return bitmap;
	}
}
