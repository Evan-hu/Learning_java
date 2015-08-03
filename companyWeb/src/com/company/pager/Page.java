package com.company.pager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.company.shop.util.CommonUtil;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> {

	private int pageNo = 1;// 页码，默认是第一页
	private int pageSize = 6; //每页显示的记录数，默认是15
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private List<T> results;// 对应的当前页记录
	private String url;
	private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象

	public Page(Integer pageNo, Integer pageSize, String url) {
		super();
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 15 : pageSize;
		this.pageSize = pageSize < 1 ? 15 : pageSize;
		this.pageNo = pageNo < 1 ? 1 : pageNo;
		this.url = url;
	}

	public Page() {
		super();
	}

	public Page(Integer pageNo, String url) {
		super();
		pageNo = pageNo == null ? 1 : pageNo;
		this.pageNo = pageNo < 1 ? 1 : pageNo;
		this.url = url;
	}

	/**
	 * 检查指定的参数是否存在
	 * 
	 * @param param
	 * @return
	 */
	public boolean hasParam(String param) {
		if (params == null || params.isEmpty()) {
			return false;
		}
		return params.containsKey(param) && params.get(param) != null;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}

	/*
	 * 数据库分页
	 */
	public void setResults(List<T> results) {
		this.results = results;
	}

	public String getParamStr(String name) {
		return params.get(name).toString();
	}

	public Object getParam(String name) {
		return params.get(name);
	}

	public String optParamStr(String name, String defalutVal) {
		if (hasParam(name)) {
			return getParamStr(name);
		}
		else if(defalutVal == null){
			return null;
		}
		addParam(name, defalutVal);
		return defalutVal;
	}

	public Double optParamDouble(String name, Double defalutVal) {
		if (hasParam(name) && CommonUtil.isNumber(getParamStr(name))) {
			return Double.valueOf(getParamStr(name));
		}
		if(defalutVal == null){
			return null;
		}
		addParam(name, defalutVal);
		return defalutVal;
	}
	
	public Integer optParamInt(String name, Integer defalutVal) {
		if (hasParam(name) && CommonUtil.isNumber(getParamStr(name))) {
			return Integer.valueOf(getParamStr(name));
		}
		if(defalutVal == null){
			return null;
		}
		addParam(name, defalutVal);
		return defalutVal;
	}
	
	public Long optParamLong(String name, Long defalutVal) {
		if (hasParam(name)) {
			return Long.valueOf(getParamStr(name));
		}
		else if(defalutVal == null){
			return null;
		}
		addParam(name, defalutVal);
		return defalutVal;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String name, Object value) {
		params.put(name, value);
	}

	public void removeParam(String name) {
		params.remove(name);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAllUrl() {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		boolean is = url.indexOf("?") > -1 ? true : false;
		StringBuffer sb = new StringBuffer(url);
		
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() != null) {
				if (is) {
					sb.append("&");
				} else {
					sb.append("?");
					is = true;
				}
				String value = "";
		        try {
		          value = URLEncoder.encode(URLEncoder.encode(entry.getValue().toString(),"utf-8"),"utf-8");
		        } catch (UnsupportedEncodingException e) {
		          e.printStackTrace();
		        }
				sb.append("params[%27").append(entry.getKey()).append("%27]")
						.append("=").append(value);
			}
		}
		return sb.toString();
	}

	public boolean isFirst() {
		return pageNo <= 1;
	}

	public boolean isLast() {
		return pageNo >= totalPage;
	}
	
	/**
	 * 内存分页
	 * @param dataList
	 */
	public void setDataList(List<T> dataList){
		if(dataList != null){
			setTotalRecord(dataList.size());
		    int offset = (getPageNo() - 1) * getPageSize();
		    results = new ArrayList<T>();
		    for (int i = offset; i < offset + getPageSize() && i < dataList.size(); i++) {
		    	results.add(dataList.get(i));
			}
		}
	}

}
