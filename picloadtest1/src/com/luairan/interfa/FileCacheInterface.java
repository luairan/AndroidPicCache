package com.luairan.interfa;

import java.io.File;
import java.io.InputStream;

import android.graphics.Bitmap;

/**
 * 文件缓存接口
 * 
 * @author luairan
 * 
 */
public interface FileCacheInterface {
	/**
	 * 保存文本到本地文件
	 * 
	 * @param info
	 *            要保存的文本
	 * @param filename
	 *            保存的文件名
	 */
	public abstract void saveTextAsFile(String info, String filename);

	/**
	 * 保存输入流到文件
	 * 
	 * @param inputStream
	 *            输入流
	 * @param filename
	 *            文件名
	 */
	public abstract void saveInputStreamAsFile(InputStream inputStream,
			String filename);

	/**
	 * 从文件获取输入流
	 * 
	 * @param file
	 *            文件名
	 * @return InputStream
	 */
	public abstract InputStream getInputStreamFromFile(File file);

	/**
	 * 从文件获取输入流
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public abstract InputStream getInputStreamFromFileName(String fileName);

	/**
	 * 将Bitmap 保存为输入流
	 * 
	 * @param bitmap
	 * @param name
	 */
	public abstract void saveBitmapAsFile(Bitmap bitmap, String name);
	/**
	 * 清空文件缓存
	 */
	public abstract void clear();
}