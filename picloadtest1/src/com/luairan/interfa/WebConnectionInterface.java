package com.luairan.interfa;

import java.io.InputStream;
import java.net.URL;


/**
 * 网络连接接口
 * 
 * @author luairan
 * 
 */
public interface WebConnectionInterface {
	/**
	 * 从URL网络地址获取输入流
	 * 
	 * @param url
	 * @return
	 */
	public InputStream getInputStreamFromURL(URL url);

	/**
	 * 从URL网络地址获取输入流
	 * 
	 * @param url
	 * @return
	 */
	public InputStream getInputStreamFromURL(String url);

}