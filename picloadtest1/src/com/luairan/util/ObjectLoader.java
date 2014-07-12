package com.luairan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.luairan.interfa.FileCacheInterface;
import com.luairan.interfa.MemoryCacheInterface;
import com.luairan.interfa.ObjectLoaderInterface;
import com.luairan.interfa.WebConnectionInterface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ObjectLoader implements ObjectLoaderInterface {
	/**
	 * 文件缓存
	 */
	FileCacheInterface fileCache = null;
	/**
	 * 内存缓存
	 */
	MemoryCacheInterface memoryCache = null;
	/**
	 * 网络连接
	 */
	WebConnectionInterface webConnection = null;
	/**
	 * 线程池
	 */
	private ExecutorService executorService;
	/**
	 * 线程池大小
	 */
	private int threadPoolsize=5;
	/**
	 * 初始实例化 ObjectLoader
	 * @param fileCache 文件缓存类
	 * @param memoryCache 内存缓存类
	 * @param webConnection 网络连接类
	 */
	public ObjectLoader(FileCacheInterface fileCache,MemoryCacheInterface memoryCache,WebConnectionInterface webConnection) {
		this.fileCache = fileCache;
		this.memoryCache = memoryCache;
		this.webConnection = webConnection;
		// 设置线程池大小为5
		executorService = Executors.newFixedThreadPool(threadPoolsize);
	}
	/**
	 * 初始化ObjectLoader 并设置同时进行缓存任务的个数
	 * @param fileCache 文件缓存类
	 * @param memoryCache 内存缓存类
	 * @param webConnection 网络连接类
	 * @param threadPoolSize 同时进行缓存任务的个数
	 */
	public ObjectLoader(FileCacheInterface fileCache,MemoryCacheInterface memoryCache,WebConnectionInterface webConnection,int threadPoolSize) {
		this.fileCache = fileCache;
		this.memoryCache = memoryCache;
		this.webConnection = webConnection;
		this.threadPoolsize=threadPoolSize;
		executorService = Executors.newFixedThreadPool(threadPoolsize);
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.ObjectLoaderInterface#setViewInfo(java.lang.String, com.luairan.util.LoadType, android.content.Context, com.luairan.util.ObjectLoader.ConvertInputStream, com.luairan.util.ObjectLoader.SetView)
	 */
	@Override
	public void setViewInfo(String url, LoadType type, Context context, ConvertInputStream cis,SetView set) {
		Object obj = null;
		if (type.canDo(LoadTypeIner.CAN_READ_MEMORY)) {
			obj = memoryCache.get(url.hashCode()+url.length()+"");
		}
		Activity ac = (Activity)context;
		ac.runOnUiThread(new SetViewClass(obj, set));
		syncFileCacheAndNetDownload(url, type, context, cis, set);
	}
	/**
	 * 从文件和网络异步加载图片
	 * @param url 网络连接地址
	 * @param type 加载内容以及获取内容的类型
	 * @param context  context当前对象
	 * @param cis  转换输入流内部接口
	 * @param set  设置内容接口
	 */
	private void syncFileCacheAndNetDownload(String url, LoadType type,
			Context context, ConvertInputStream cis, SetView set) {
		ViewLoaderExecute s = new ViewLoaderExecute(new ViewLoader(url, type,
				context), cis, set);
		executorService.execute(s);
	}
	/**
	 * 承载 装入数据
	 * 
	 * @author luairan
	 * 
	 */
	public class ViewLoader {
		String info;
		LoadType type;
		Context c;
		public ViewLoader(String info, LoadType type, Context c) {
			this.info = info;
			this.type = type;
			this.c = c;
		}
	}

	public class BitmapFromInputStream implements ConvertInputStream{

		@Override
		public Object getDataFromInputStream(InputStream is) {
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		}
	}
	
	
	/**
	 * 类型转换接口
	 * @author luairan
	 *
	 */
	public interface ConvertInputStream {
		/**
		 * 从输入流获取数据特型
		 * @param is
		 * @return
		 */
		Object getDataFromInputStream(InputStream is);
	}

	/**
	 * 异步文件查找和文件下载
	 * @author luairan
	 *
	 */
	private class ViewLoaderExecute implements Runnable {
		ViewLoader viewLoader;
		ConvertInputStream con;
		SetView set;
		public ViewLoaderExecute(ViewLoader viewLoader, ConvertInputStream con,
				SetView set) {
			this.viewLoader = viewLoader;
			this.con = con;
			this.set = set;
		}

		@Override
		public void run() {
			
			
			InputStream is =null;
			Object obj = null;
			
			if (viewLoader.type.canDo(LoadTypeIner.CAN_READ_FILE)) {
				is = fileCache.getInputStreamFromFileName(viewLoader.info
						.hashCode() + viewLoader.info.length() + "");
			}
			
			if (is == null&& viewLoader.type.canDo(LoadTypeIner.CAN_NET)&&NetWorkHelper.isNetworkAvailable(viewLoader.c)) {
				
				is = webConnection.getInputStreamFromURL(viewLoader.info);
				if (is!=null&&viewLoader.type.canDo(LoadTypeIner.CAN_WRITE_FILE)) {
					fileCache.saveInputStreamAsFile(is, viewLoader.info.hashCode()
							+ viewLoader.info.length() + "");
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					is = fileCache.getInputStreamFromFileName(viewLoader.info
							.hashCode() + viewLoader.info.length() + "");
				}
			}
			if(is!=null)
			obj = con.getDataFromInputStream(is);
			if (obj == null)
				return;
			if (viewLoader.type.canDo(LoadTypeIner.CAN_WRITE_MEMORY))
				memoryCache.put(viewLoader.info.hashCode() + viewLoader.info.length()+ "", obj);
			
			Activity ac = (Activity) viewLoader.c;

			ac.runOnUiThread(new SetViewClass(obj, set));

		}
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.ObjectLoaderInterface#refreshAll()
	 */
	@Override
	public void refreshAll(){
		memoryCache.clear();
		fileCache.clear();
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.ObjectLoaderInterface#refreshMemoryCache()
	 */
	@Override
	public void refreshMemoryCache(){
		memoryCache.clear();
	}
	/* (non-Javadoc)
	 * @see com.luairan.util.ObjectLoaderInterface#refreshFileCache()
	 */
	@Override
	public void refreshFileCache(){
		fileCache.clear();
	}
	
	/**
	 * 对url网络以及缓存得到的数据 进行 处理（设置为View的文字或背景）
	 * @author luairan
	 *
	 */
	public interface SetView {
		/**
		 * 对从网络或缓存得到的对象进行处理
		 * @param obj  对从网络或缓存得到的对象
		 */
		public void setView(Object obj);
	}
	/**
	 * 在Activity主线程进行缓存得到对象的处理
	 * @author luairan
	 *
	 */
	private class SetViewClass implements Runnable {
		Object obj;
		SetView set;
		public SetViewClass(Object obj, SetView set) {
			this.obj = obj;
			this.set = set;
		}
		
		@Override
		public void run() {
			set.setView(obj);
		}

	}
}
