package cc.moredo.oa.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	/**
	 * MD5加密 生成32位md5码
	 * @param str 待加密字符串
	 * @return 返回32位md5码
	 * @throws Exception
	 */
	public static String getMD5(String str) {
		
		byte[] md5Bytes = null;
		try {
			byte[] byteArray = str.getBytes("UTF-8");
			md5Bytes = MessageDigest.getInstance("MD5").digest(byteArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		//hexValue.toString().substring(8, 24); 16位的加密
		return hexValue.toString();
	}

	/**
	 * SHA加密 生成40位SHA码
	 * @param str 待加密字符串
	 * @return 返回40位SHA码
	 * @throws Exception
	 */
	public static String getSHA1(String str) {
		byte[] shaBytes = null;
		try {
			byte[] byteArray = str.getBytes("UTF-8");
			shaBytes = MessageDigest.getInstance("SHA").digest(byteArray);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < shaBytes.length; i++) {
			int val = ((int) shaBytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	public static void main(String[] args) {
		
		System.out.println(md5("hh2585018hh"));
	}

}
