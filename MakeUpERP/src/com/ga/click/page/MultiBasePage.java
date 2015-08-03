package com.ga.click.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;

public abstract class MultiBasePage extends GaPage {
  
  protected List<ActionButton> toolbarList = new ArrayList<ActionButton>(); //页面操作
  private Map<String,IBiz> bizCache = new HashMap<String,IBiz>();
  
  
  /**
   * 默认构建函数
   * 实现dbfield和bizclass的绑定和初始化
   */
  public MultiBasePage() {
      try {
        this.initNavInfo();            
      } catch (BizException ex) {
          throw ex;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "初始化失败", ex);
      }
  }
  
  
    
  protected IBiz getBizClass(String controlID) {
    if (bizCache.get(controlID) == null) {
      IBiz getBiz = this.initBizClass(controlID);
      bizCache.put(controlID,getBiz);
      return getBiz;
    } else {
      return bizCache.get(controlID);
    }
  }
  
  public void initForm() {
  }
  
  public Layout initLayout() {
    return null;
  } 
  /**
   * 初始化数据字段定义
   * @return
   */
  public abstract List<DbField> initDbField(String controlID);
  
  /**
   * 绑定业务处理类
   * @return
   */
  public abstract IBiz initBizClass(String controlID);
  
}

