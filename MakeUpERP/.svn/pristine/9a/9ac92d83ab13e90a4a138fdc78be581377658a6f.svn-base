package com.ga.click.mvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class ParamTools {

	public static <T> T parseMapField(Map<String,String> dataMap,String key,Class<T> cls) {
		String v = dataMap.get(key);
		if (v == null || v.equals("")) v = "0";
		Object rtnV;
		String clsName = cls.getName();
		if (clsName.equals(Integer.class.getName())) {
			rtnV = Integer.parseInt(v);
		} else if (clsName.equals(Long.class.getName())) {
			rtnV = Long.parseLong(v);
		} else if (clsName.equals(BigDecimal.class.getName())) {
			rtnV = new BigDecimal(v);
		} else {
			// 按字符串处理		
				rtnV = dataMap.get(key);;
		}		
		return (T) rtnV;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parseRequestField(Map<String,String[]> paramMap,String key,Class<T> cls) {
		String[] getString = paramMap.get(key);
		String clsName = cls.getName();
		String v = "";
		if (getString !=null && getString.length > 0) {
			v=getString[0];
		} else {
			//查看固定的_rowoption中是否有
			getString = paramMap.get("_rowdata");
			if (getString !=null && getString.length > 0) {
				v=getString[0];
				try {
					JSONObject obj = new JSONObject(v);
					Object tv = obj.opt(key);
					if (tv != null) {
						v = tv.toString();
					}
				} catch(Exception x) {
					
				}
			}
		}
		Object rtnV;
		if (clsName.equals(Integer.class.getName())) {
			if (v == null || v.equals("")) {
				rtnV = new Integer(0);
			} else {
				rtnV = Integer.parseInt(v);
			}
		} else if (clsName.equals(Long.class.getName())) {
			if (v == null || v.equals("")) {
				rtnV = new Long(0L);
			} else {
				rtnV = Long.parseLong(v);
			}
		} else if (clsName.equals(BigDecimal.class.getName())) {
			if (v == null || v.equals("")) {
				rtnV = new BigDecimal(0L);
			} else {
				rtnV =new BigDecimal(v);
			}
		} else {
			// 按字符串处理
			if (v == null) {
				rtnV= "";
			} else {
				rtnV = v;
			}
		}		
		return (T) rtnV;
	}
	
	public static com.ga.click.dbutil.PageParam parseRequestPage(Map<String,String[]> paramMap) {
		com.ga.click.dbutil.PageParam pageParam = new com.ga.click.dbutil.PageParam();
		pageParam.setPageNumber(1);
		pageParam.setPageSize(20);
		String[] pageNum =paramMap.get("pageNum");
		if (pageNum != null && pageNum.length >0 && pageNum[0] != null && !pageNum[0].equals("")) {
			pageParam.setPageNumber(Integer.parseInt(pageNum[0]));
		}
		String[] pageSzie =paramMap.get("numPerPage");
		if (pageSzie != null && pageSzie.length >0 && pageSzie[0] != null && !pageSzie[0].equals("")) {
			pageParam.setPageSize(Integer.parseInt(pageSzie[0]));
		}
		pageParam.setPageSize(2);
		return pageParam;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String>  parseRequest(Map<String,String[]> paramMap) {
		Map<String,String>rtn = new HashMap<String,String>();
		for (String key : paramMap.keySet()) {
				String[] getString = paramMap.get(key);
				if (getString != null && getString.length >0) {
					if (key.equals("_rowdata")) {				
							String v=getString[0];
							try {
								JSONObject obj = new JSONObject(v);
								Iterator<String> it = obj.keys();								
								while (it.hasNext()) {
									String key1 = it.next();
									String v1 = obj.optString(key1);
									rtn.put(key1,v1);
								}									
							} catch(Exception x) {							
							}
						} else {
							//普通参数
							rtn.put(key, getString[0]);
						}
					} else {
						rtn.put(key,"");
					}
			}
			return rtn;		
	}
}
