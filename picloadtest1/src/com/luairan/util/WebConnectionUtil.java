package com.luairan.util;
	
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.luairan.interfa.WebConnectionInterface;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WebConnectionUtil implements WebConnectionInterface {
	/* (non-Javadoc)
	 * @see com.luairan.util.WebConnectionInterface#getBitmapFromURL(java.net.URL)
	 */

	public  Bitmap getBitmapFromURL(URL url){
		try {
			HttpURLConnection httpUrlConnection=(HttpURLConnection) url.openConnection();
			
			httpUrlConnection.setDoInput(true);
			
			httpUrlConnection.setConnectTimeout(6000);
			
			httpUrlConnection.setUseCaches(false);
			
			httpUrlConnection.setInstanceFollowRedirects(true);
			
			httpUrlConnection.connect();
			
			InputStream is=httpUrlConnection.getInputStream();
			
			return BitmapFactory.decodeStream(is);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.WebConnectionInterface#getInPutStream(java.net.URL)
	 */
	@Override
	public  InputStream getInputStreamFromURL(URL url){
	
	try {
		HttpURLConnection httpUrlConnection=(HttpURLConnection) url.openConnection();
		
		httpUrlConnection.setDoInput(true);
		
		httpUrlConnection.setConnectTimeout(6000);
		
		httpUrlConnection.setUseCaches(false);
		
		httpUrlConnection.setInstanceFollowRedirects(true);
		
		httpUrlConnection.connect();
	
		
		
		return httpUrlConnection.getInputStream();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public InputStream getInputStreamFromURL(String url) {
		try {
			return getInputStreamFromURL(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
