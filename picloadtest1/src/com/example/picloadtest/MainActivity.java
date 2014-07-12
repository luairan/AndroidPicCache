package com.example.picloadtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.luairan.inputstream_tools.BitmapFromInputStream;
import com.luairan.interfa.ObjectLoaderInterface;
import com.luairan.util.FileCache;
import com.luairan.util.FileUtil;
import com.luairan.util.LoadType;
import com.luairan.util.MemoryCache;
import com.luairan.util.ObjectLoader;
import com.luairan.util.ObjectLoader.SetView;
import com.luairan.util.WebConnectionUtil;
import com.luairan.util.WebUtil;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv=null;
	private ImageView iv1=null;
	private ImageView iv2=null;
	private Handler handler=null;
	FileCache aaa=new FileCache();
	private ObjectLoaderInterface sss=new ObjectLoader(aaa, new MemoryCache(), new WebUtil(),1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView) findViewById(R.id.imagepic);
		iv1=(ImageView) findViewById(R.id.imagepic1);
		iv2=(ImageView) findViewById(R.id.imagepic2);
		sss.setViewInfo("http://www.sinaimg.cn/uc/myshow/blog/misc/gif/E___0173EN00SIGT.gif", LoadType.ALL, this,new BitmapFromInputStream(),new SetView() {
			
			@Override
			public void setView(Object obj) {
				iv.setImageBitmap((Bitmap)obj);
				
			}
		} );
sss.setViewInfo("http://www.sinaimg.cn/uc/myshow/blog/misc/gif/E___0173EN00SIGT.gif", LoadType.ALL, this,new BitmapFromInputStream(),new SetView() {
			
			@Override
			public void setView(Object obj) {
				iv1.setImageBitmap((Bitmap)obj);
				
			}
		} );
sss.setViewInfo("http://www.sinaimg.cn/uc/myshow/blog/misc/gif/E___0173EN00SIGT.gif", LoadType.ALL, this,new BitmapFromInputStream(),new SetView() {
	
	@Override
	public void setView(Object obj) {
		iv2.setImageBitmap((Bitmap)obj);
		
	}
} );

		sss.refreshFileCache();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
