package com.luairan.util;
/**
 * 缓存策略
 * @author luairan
 *
 */
public enum LoadType {
	/**
	 * 先从 内存 文件 网络 获取数据 并使用 内存缓存 文件缓存 
	 */
	ALL(0x00011111),
	/**
	 * 先从 网络 并使用 无缓存 
	 */
	NET_NOCACHE(0x00010000),
	/**
	 * 先从 网络 并使用 内存缓存 
	 */
	NET_MEMORYCACHE(0x00010010),
	/**
	 * 先从 网络 并使用 文件缓存 
	 */
	NET_FILECACHE(0x00010001),
	/**
	 * 先从网络  并使用 内存缓存,文件缓存 
	 */
	NET_ALLCACHE(0x00010011),
	/**
	 * 先从 内存 网络 获取数据 并使用 内存缓存 
	 */
	MEMORY_NET_MEMORYCACHE(0x00011010),
	/**
	 * 先从 内存 网络 获取数据 并使用 内存缓存 文件缓存 
	 */
	MEMORY_NET_ALLCACHE(0x00011011),
	/**
	 * 先从 内存 网络 获取数据 并使用 文件缓存 
	 */
	MEMORY_NET_FILECACHE(0x00011001),
	/**
	 * 先从 文件 网络 获取数据 并使用 内存缓存 文件缓存 
	 */
	FILE_NET_ALLCACHE(0x00010111),
	/**
	 * 先从 文件 网络 获取数据 并使用 内存缓存 
	 */
	FILE_NET_MEMORYCACHE(0x00010110),
	/**
	 * 先从 文件 网络  获取数据 并使用 文件缓存 
	 */
	FILE_NET_FILECACHE(0x00010101),
	/**
	 * 先从  网络 内存 文件 获取数据 并使用 内存缓存 
	 */
	FILE_MEMORY_NET_MEMORYCACHE(0x00011110),
	/**
	 * 先从 网络 内存 文件 获取数据 并使用 文件缓存 
	 */
	FILE_MEMORY_NET_FILECACHE(0x00011101);
	LoadType(int type) {
		this.type = type;
	}
	/**
	 * 获取具体的值
	 * @return 具体的值
	 */
	public int getLoadType() {
		return type;
	}
	/**
	 * 能不能进行做某件事
	 * @param iner 能做的类型
	 * @return
	 */
	public boolean canDo(LoadTypeIner iner) {
		return (this.type & iner.getCanType()) == iner.getCanType();
	}

	private int type;
}
