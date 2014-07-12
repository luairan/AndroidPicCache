package com.luairan.util;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.luairan.interfa.WebConnectionInterface;

public class WebUtil implements WebConnectionInterface{

	@Override
	public InputStream getInputStreamFromURL(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStreamFromURL(String url) {
		InputStream is=null;
		HttpGet httpRequest = new HttpGet(url);
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(entity);
			is=bufferedHttpEntity.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
}
