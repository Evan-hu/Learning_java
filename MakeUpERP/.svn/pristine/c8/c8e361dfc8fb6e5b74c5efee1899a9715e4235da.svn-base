package com.ga.click.page;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;

public interface IBiz {
	
    public void setDbFields(List<DbField> dbList);
    public void setValueMap(Map<String,Object> valueMap);
    public Map<String,Object> getValueMap();
    public void setRowData(JSONObject jsonObj);
    public void addParam(String paramName,Object value);
    
    public PageResult queryList(QueryPageData pageData) throws BizException;
    public Map<String,Object> queryDetail() throws BizException;
    public void del() throws BizException;        
    public void add() throws BizException;
    public void save() throws BizException;    
    
//    public PageResult extendQueryList(String methodName,QueryPageData pageData) throws BizException;
//    public Map<String,Object> extendQueryDetail(String methodName) throws BizException;
//    public void extendAction(String methodName) throws BizException;
    
    
}
