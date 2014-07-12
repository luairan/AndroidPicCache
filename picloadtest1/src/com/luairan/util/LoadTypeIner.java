package com.luairan.util;
/**
 * 缓存的能力
 * @author luairan
 *
 */
public enum LoadTypeIner {
	/**
	 * 可以读取内存缓存
	 */
	CAN_READ_MEMORY(0x00001000), 
	/**
	 * 可以读取文件缓存
	 */
	CAN_READ_FILE(0x00000100), 
	/**
	 * 可网络下载
	 */
	CAN_NET(0x00010000),
	/**
	 * 可以写入内存缓存
	 */
	CAN_WRITE_MEMORY(0x00000010),
	/**
	 * 可以读取文件缓存
	 */
	CAN_WRITE_FILE(0x00000001);
	LoadTypeIner(int type) {
		this.type = type;
	}
	/**
	 * 获取能力的具体指
	 * @return
	 */
	public int getCanType() {
		return type;
	}
	private int type;
}
