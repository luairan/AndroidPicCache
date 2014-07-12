package com.luairan.interfa;

/**
 * 内存缓存接口
 * 
 * @author luairan
 * 
 */
public interface MemoryCacheInterface {

	/**
	 * 将内容放入缓存
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void put(String key, Object value);

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public abstract Object get(String key);

	/**
	 * 移除某项数据
	 * 
	 * @param key
	 */
	public abstract void remove(String key);

	/**
	 * 缓存项目数
	 * 
	 * @return
	 */
	public abstract int size();

	/**
	 * 缓存是否包含
	 * 
	 * @param key
	 * @return
	 */
	public abstract boolean contains(String key);

	/**
	 * 检查缓存并进行整理
	 */
	public abstract void checkMemory();
	/**
	 * 清空缓存
	 * @return 
	 */
	void clear();
}