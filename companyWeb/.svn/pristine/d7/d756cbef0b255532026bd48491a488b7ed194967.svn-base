package com.company.shop.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.company.pager.Page;

public class ConvertHelper {
	private static final Log logger = LogFactory.getLog(ConvertHelper.class);
	
	public final static String RESULT_TAG = "result";
	public final static String MESSAGE_TAG = "message";
    
	//状�?
	public final static int RESULT_SUCCESS_STATE = 1;
	public final static int RESULT_FAILED_STATE = 0;
	public final static int RESULT_NO_LOGIN_STATE = -200;
	
	public static String createNoLoginJson(){
		StringBuffer json = new StringBuffer();
		json.append("{\"");
		json.append(RESULT_TAG).append("\":").append(RESULT_NO_LOGIN_STATE);
		json.append(",\"");
		json.append(MESSAGE_TAG).append("\":").append("对不起，未登陆");
		json.append("}");
		return json.toString();
	}
	
	public static Map<String, Object> createNoLoginMap(){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(RESULT_TAG, RESULT_NO_LOGIN_STATE);
		map.put(MESSAGE_TAG, "对不起，未登陆");
		return map;
	}
	
	public static Map<String, Object> createMap(int result){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESULT_TAG, result);
		return map;
	}
	
	public static Map<String, Object> createFailMap(String message){
		Map<String, Object> map = createMap(RESULT_FAILED_STATE);
		map.put(MESSAGE_TAG, message);
		return map;
	}
	
	public static Map<String, Object> createSuccessMap(String message){
		Map<String, Object> map = createSuccessMap();
		map.put(MESSAGE_TAG, message);
		return map;
	}
	
	
	public static Map<String, Object> createSuccessMap(){
		return createMap(RESULT_SUCCESS_STATE);
	}
	
	/**
	 * bean list转化�?map list
	 * @param t
	 * @return
	 */
	public static <T> List<Map<String, Object>> beansToMaps(List<T> beans, ConvertFilter<T> convertFilter){
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(beans != null && convertFilter != null) {		
			Map<String, Object> m;
			for (T t : beans) {
				m = new HashMap<String, Object>();
				convertFilter.fiter(t, m);
				maps.add(m);
			}
		}
		return maps;
	}
	
	public static <T> Map<String, Object> pageToMap(Page<T> page, ConvertFilter<T> convertFilter){
		if(page != null){
			Map<String, Object> map = createMap(RESULT_SUCCESS_STATE);
			map.put("totalPage", page.getTotalPage());
			map.put("totalRecord", page.getTotalRecord());
			if(convertFilter == null || page.getResults() == null || page.getResults().isEmpty()){
				map.put("dataList", "[]");
			}
			else{
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (T t : page.getResults()) {
					Map<String, Object> target = new HashMap<String, Object>();
					convertFilter.fiter(t, target);
					list.add(target);
				}
				map.put("dataList", list);
			}
			return map;
		}
		return createMap(RESULT_FAILED_STATE);
	}
	
	/**
	 * List转化成Map
	 * @param lists
	 * @param convertFilter
	 * @return
	 */
	public static <T> Map<String, Object> createMap(List<T> lists,ConvertFilter<T> convertFilter){
		Map<String, Object> map = createMap(RESULT_SUCCESS_STATE);
		if(lists == null || lists.isEmpty()){
			map.put("dataList", "[]");
		}else{
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			for (T t : lists) {
				Map<String, Object> target = new HashMap<String, Object>();
				convertFilter.fiter(t, target);
				resultList.add(target);
			}
			map.put("dataList", resultList);
		}
		return map;
	}
	
	public static <T> Map<String, Object> pageToMap(Page<T> page){
		if(page != null){
			Map<String, Object> map = createMap(RESULT_SUCCESS_STATE);
			map.put("totalPage", page.getTotalPage());
			map.put("totalRecord", page.getTotalRecord());
			map.put("dataList", page.getResults());
			return map;
		}
		return createMap(RESULT_FAILED_STATE);
	}
	
	public static <T> Page<T> mapToPage(Map<String, Object> map){
		Page<T> page = new Page<T>();
		if(map != null){
			logger.debug(map);
			for (Map.Entry<String,Object>  e : map.entrySet()) {
				if(e.getValue() != null){
					if("pageSize".equals(e.getKey())){
						page.setPageSize(Integer.parseInt(map.get("pageSize").toString()));
					}
					else if("pageNo".equals(e.getKey())){
						page.setPageNo(Integer.parseInt(map.get("pageNo").toString()));
					}
					else{
						page.addParam(e.getKey(), e.getValue());
					}
				}
			}
		}
		return page;
	}
}
