package com.ga.click.page;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.Page;
import org.apache.click.util.Bindable;
import org.json.JSONObject;
import com.ga.click.control.GaConstant;
import com.ga.click.util.ClickUtil;

public abstract class CompisePage extends Page {

  @Bindable
  protected String initJS = "";
  
  public CompisePage() {
    super();
  }
  private Map<String,String> divMap = new HashMap<String,String>();
  private Map<String,Map<String,Object>> divParamMap = new HashMap<String,Map<String,Object>>();
  public void loadDivPage(String divID,String pageUrl,Map<String,Object> param) {
    if (pageUrl.indexOf("?") >0) {
      pageUrl = pageUrl + "$" + GaConstant.FIXPARAM_DIVMODE + "="+divID;
    } else {
      pageUrl = pageUrl + "?" + GaConstant.FIXPARAM_DIVMODE + "="+divID;
    }
    this.divMap.put(divID, pageUrl);
    if (param != null) {
      this.divParamMap.put(divID, param); 
    }    
  }
  
  @Override
  public void onRender() {
    // TODO Auto-generated method stub
    try {
      /**
       * ×éºÏjsº¯Êý
       */
       StringBuffer buffer = new StringBuffer();
       buffer.append("<script language=\"javascript\">\r\n");
       for(String key : this.divMap.keySet()) {
         String url = this.divMap.get(key);
         Map<String,Object> paramMap = this.divParamMap.get(key);      
         String json = "";
         if (paramMap != null) {
           JSONObject jsonObj = ClickUtil.getJSONObject(paramMap);
           if (jsonObj != null) {
             json = jsonObj.toString().replace("\"","'");
           }
         }
         buffer.append("LoadDivPage(\"").append(key)
         .append("\",\"").append(url)
         .append("\",\"").append(json).append("\");\r\n");
       }
       buffer.append("</script>\r\n");
       this.initJS = buffer.toString();
    } catch(Exception ex) {
      
    } 

  }



  @Override
  public abstract String getTemplate();
  
}
