package com.luairan.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.luairan.interfa.MemoryCacheInterface;

/**
 * 内存缓存
 * @author luairan
 *
 */
public class MemoryCache implements MemoryCacheInterface {
	
	/**
	 * 对应的Map图片缓存,进行同步
	 */
	private Map<String,Object> objectCache=null;
	/**
	 * 缓存内存个数
	 */
	private int size=0;
	/**
	 * 缓存最大大小 ，默认32条记录
	 */
	private int max=32;
	public MemoryCache(){
		initialMap();
	}
	public MemoryCache (int size){
		max=size;
		initialMap();
	}
	/**
	 * 设置为同步
	 */
	private void initialMap(){
		objectCache=Collections.synchronizedMap(new LinkedHashMap<String,Object>(max, 2.2f, true));
	}
	
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#put(java.lang.String, android.graphics.Bitmap)
	 */
	@Override
	public void put(String key,Object value){
		objectCache.put(key, value);
		size++;
		checkMemory();
	}
	
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#get(java.lang.String)
	 */
	@Override
	public Object get(String key){
		return objectCache.get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#remove(java.lang.String)
	 */
	@Override
	public void remove(String key){
		objectCache.remove(key);
		if(size>0)
		size--;
	}
	
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#size()
	 */
	@Override
	public int size(){
	return size;
	}
	
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String key){
		return objectCache.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.MemoryCacheInterface#checkMemory()
	 */
	@Override
	public  void  checkMemory(){
		if(size>max) {
			Iterator<Entry<String, Object>> iter=objectCache.entrySet().iterator();
			while(iter.hasNext()){
				iter.remove();
				size--;
				if(size<=max) break;
			}
		}
	}
	public void clear (){
		objectCache.clear();
		size=0;
	}
}
