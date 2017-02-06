package mgo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.core.Const;

public class DataLoader {
	public static JSONArray loadArrayData(String fileName) {
		String content = loadFile(fileName);
		JSONArray data = JSON.parseArray(content);
		return data;
	}

	private static String loadFile(String fileName) {
		String result = null;
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (inputStream == null) {
				throw new IllegalArgumentException("File not found in classpath: " + fileName);
			}
			result = IOUtils.toString(inputStream, Const.DEFAULT_ENCODING);
		} catch (IOException e) {
			throw new RuntimeException("Error loading file.", e);
		}
		return result;
	}
}
