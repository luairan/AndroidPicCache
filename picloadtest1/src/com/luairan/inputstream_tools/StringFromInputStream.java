package com.luairan.inputstream_tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.luairan.util.ObjectLoader.ConvertInputStream;

/**
 * 从输入流获取String对象
 * @author luairan
 *
 */
public class StringFromInputStream implements ConvertInputStream{

	@Override
	public Object getDataFromInputStream(InputStream is) {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}