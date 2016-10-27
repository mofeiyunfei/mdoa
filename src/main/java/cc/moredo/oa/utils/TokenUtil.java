package cc.moredo.oa.utils;

import java.util.UUID;


public class TokenUtil {
	
	/*
	 * 1.生成token的方法
	 */
	public static String generateToken(){
		String uuid =UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

}
