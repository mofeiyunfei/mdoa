package cc.moredo.oa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 关于IO的工具类
 * @author guohao
 *
 */
public class MdIOUtils {
	/*
	 * 将输入流InputStream转化为字符串
	 */
	public static String inputStreamToString(InputStream in){
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();   
		String line = null;   
		try {   
			System.out.println("-----shishi----"+reader.toString());
			while ((line = reader.readLine()) != null) {   
				sb.append(line);   
			}   
		} catch (IOException e) {   
			e.printStackTrace();   
		} finally {
			try {
				in.close();   
			} catch (IOException e) {   
				e.printStackTrace();   
			}
		}
		return sb.toString();   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
