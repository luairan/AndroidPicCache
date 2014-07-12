package com.luairan.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.luairan.interfa.FileCacheInterface;

import android.graphics.Bitmap;
import android.os.Environment;


/**
 * 文件缓存
 * @author MrLu
 *
 */
public class FileCache implements FileCacheInterface {
	/**
	 * 文件缓存所在路径
	 */
	private String path=null;
	public FileCache(){
		//是否有内存卡
		if (FileUtil.hasSDCard()) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+"com.geniusgithub/cachefiles/";// filePath:/sdcard/
		} else {
			path = Environment.getDataDirectory().getAbsolutePath() + "/data/"+"com.geniusgithub/cachefiles/"; // filePath: /data/data/
		}
		//创建目录
		FileUtil.createDirectory(path);
	}

	@Override
	public void saveTextAsFile(String info,String name){
		saveInputStreamAsFile(new ByteArrayInputStream(info.getBytes()), name);
	}
	@Override
	public void saveBitmapAsFile(Bitmap bitmap,String name){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);  
      InputStream is = new ByteArrayInputStream(baos.toByteArray());  
		saveInputStreamAsFile(is, name);
	}

	@Override
	public void saveInputStreamAsFile(InputStream inputStream,String name){
		FileUtil.saveFileFromInputStream(new File(path+name), inputStream);
	}
	

	@Override
	public InputStream getInputStreamFromFile(File file){
		try {
			if(file.exists()&&file.isFile())
			
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public InputStream getInputStreamFromFileName(String fileName){
		return getInputStreamFromFile(new File(path+fileName));
		
	}

	@Override
	public void clear() {
		File s=new File(path);
				File[]a=s.listFiles();
		for(File q:a)
		FileUtil.deleteDirectorysFile(q.getAbsolutePath());
	}
	
}
