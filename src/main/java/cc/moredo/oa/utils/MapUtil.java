package cc.moredo.oa.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 该类是为了 统一JSON通信数据标准格式
 * 统一封装成Map类型，再用spring框架 自动转成JSON
 * @author mofeiyunfei
 * 
 */
public class MapUtil {
	
	private static JSONObject jo = new JSONObject();
	/**
	 * 
	 * @param pageNum 第几页
	 * @param pages 总页数
	 * @param data 数据
	 * @param code 状态码
	 * @param msg 提示信息
	 * @return Map集合
	 */
	public static Map<String,Object> jsonFormatMap(Integer pageNum,Integer pages,Object data,Integer code,String msg){
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("pageNum", pageNum);
		 map.put("pages", pages);
		 map.put("data", data);
		 map.put("code", code);
		 map.put("msg", msg);
		
		return map;
	}
	
	public static Map<String,Object> jsonFormatMap(Object data,Integer code,String msg){
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("data", data);
		 map.put("code", code);
		 map.put("msg", msg);
		
		return map;
	}
	
	public static JSONObject jsonFormat(Object obj,Integer code,String msg){
		jo.put("data", obj);
		jo.put("code", code);
		jo.put("msg", msg);
		return jo;
	}


}
