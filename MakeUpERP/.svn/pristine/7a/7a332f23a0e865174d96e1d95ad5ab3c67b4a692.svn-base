package com.ga.click.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;

public class LookupDataSet {
	
  private CachedRowSet lookupDataSet;
  private Map<String,Object> lookupDataMap;
  private String keyField;
  private String resultField;
  private int type = 1;
  
  public LookupDataSet(CachedRowSet lookupDataSet, String keyField, String resultField) {
    this.lookupDataSet = lookupDataSet;
    this.keyField = keyField;
    this.resultField = resultField;
    this.type = 1;
  }
  public LookupDataSet(Map<String,Object> dataMap) {
    lookupDataMap = dataMap;
    this.type = 2;
  }
  public List<String> getKeyList() {
    List<String> keyList = new ArrayList<String>();
    try {
      if (this.type == 2 && this.lookupDataMap != null) {
        for(String key : this.lookupDataMap.keySet()) {
          keyList.add(key);
        }
      } else if (this.type == 1 && this.lookupDataSet != null) {
        if (lookupDataSet.first()) {
          do {
            keyList.add(lookupDataSet.getString(keyField));
          } while (lookupDataSet.next());
        }
      } 
      return keyList;
    } catch(Exception ex) {
      return null;
    }
  }
  
  /**
   * 根据值找名称
   * @param value
   * @return
   */
  public Object find(Object value) {
    try {        
      if (this.type == 1) {
        //根据记录集查找 {
          if (lookupDataSet.first()) {
            do {
              if (lookupDataSet.getObject(keyField).toString().equals(value.toString())) {
                return lookupDataSet.getObject(resultField);
              }
            } while (lookupDataSet.next());
          }
          return null;
      } else { 
        //根据MAP查找
        if (this.lookupDataMap == null) return null;
        return this.lookupDataMap.get(value);
      }
    }
    catch (Exception ex) {
      return null;
    }  
  }
public Map<String, Object> getLookupDataMap() {
	return lookupDataMap;
}
  
}