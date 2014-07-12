package com.luairan.interfa;

import android.content.Context;

import com.luairan.util.LoadType;
import com.luairan.util.ObjectLoader.ConvertInputStream;
import com.luairan.util.ObjectLoader.SetView;

public interface ObjectLoaderInterface {

	/**
	 * 从网络获取内容并进行缓存，之后设置View的内容
	 * @param url 网络连接地址
	 * @param type 加载内容以及获取内容的类型
	 * @param context  context当前对象
	 * @param cis  转换输入流内部接口
	 * @param set  设置内容接口
	 */
	public abstract void setViewInfo(String url, LoadType type,
			Context context, ConvertInputStream cis, SetView set);

	/**
	 * 清空所有缓存
	 */
	public abstract void refreshAll();

	/**
	 * 清空内存缓存
	 */
	public abstract void refreshMemoryCache();

	/**
	 * 清空文件缓存
	 */
	public abstract void refreshFileCache();

}