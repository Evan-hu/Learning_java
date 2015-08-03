package com.ga.click.control.extra;

import org.apache.click.control.Option;
import org.apache.click.control.Select;
import com.ga.click.control.LookupDataSet;
import com.ga.click.util.GaUtil;

public class RefCombox  extends Select {
	
  private static final long serialVersionUID = 1L;
  private String refComboxName;
  private String refDataUrl;
  private String refDataMethod;
  private LookupDataSet lookupSet;
  
  /**
   * 构建顶部菜单,显示根节点的直接子节点
   * @param name
   * @param rootNode   * 
   */
  public RefCombox(String name,String label,String refComboxName,String refDataUrl,String refDataMethod,LookupDataSet lookSet) {
    super(name,label);  
    this.refComboxName = refComboxName;
    this.refDataUrl = refDataUrl;
    this.refDataMethod = refDataMethod; 
    this.lookupSet = lookSet;  
    
    this.setAttribute("ref", refComboxName);
    this.setAttribute("class", "combox");
    if (!GaUtil.isNullStr(this.refDataUrl) && !GaUtil.isNullStr(this.refDataMethod)) {
      String link = "?";
      if (this.refDataUrl.indexOf("?") > 0) {
        link = "&";
      }
      link = this.refDataUrl + link+"code={value}&pageAction="+this.refDataMethod;
      this.setAttribute("refUrl", link);
    }
    if (!this.isRequired()) {
      this.add(new Option("","无"));
    }
    if (this.lookupSet  != null) {      
      for(String key: lookupSet.getKeyList()) {    
        this.add(new Option(key, lookupSet.find(key)));
      }     
    }
  }  
}
