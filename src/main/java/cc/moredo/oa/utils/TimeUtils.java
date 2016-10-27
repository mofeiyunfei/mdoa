package cc.moredo.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 操作用户的工具类
 * @author guohao
 *
 */
public class TimeUtils {

	/*
	 * 将Date类型转化为固定格式的字符串
	 */
	public static String dateToString(Date date,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String dateStr = format.format(date);
		return dateStr;
	}
	
	public static Date stringToDate(String dateStr,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	
	
	
}
