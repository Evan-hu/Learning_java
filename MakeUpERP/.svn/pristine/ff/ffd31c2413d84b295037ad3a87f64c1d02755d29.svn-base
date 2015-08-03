package com.ga.erp.biz.activity.cardbatch;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class CardBatchAddPage extends UnitPage {
  
  private FormView addBatchFormView;

  @Override
  public void initController() throws Exception {
    
    this.addBatchFormView = new CardBatchFormView("addBatchForm", this.modelMap);
    
    this.regPageLoadEvent("pageLoad");
    
    this.addBatchFormView.regAddSaveEvent("addBatchBtn", "addBatchInfoOfCard", this, false);
    
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout=new FormLayout("", this.addBatchFormView.getDataForm(), 2);
    formLayout.mergeCellField("auto", 4, 0, 1, "AUTO_COUNT");
    formLayout.mergeCellField("auto", 5, 0, 1, "NOTE");
    this.addBatchFormView.getDataForm().setLayout(formLayout);
    layout.addControl(this.addBatchFormView);
    return layout;
  }
  
  public void pageLoad(ModelMap modelMap){
    return;
  }
  
  public ActionResult addBatchInfoOfCard(Map<String, Object> vMap){
    try {
      StringBuffer buffer=new StringBuffer("");
      int count=1;
      if("".equalsIgnoreCase((String)vMap.get("NAME"))){
        buffer.append("<br>"+count+",请输入批次名称；");
        count++;
      }
      if(null==vMap.get("TYPE")){
        buffer.append("<br>"+count+",请选择批次类型；");
        count++;
      }
      if(0==Double.valueOf(""+vMap.get("MIN_MONEY"))){
        buffer.append("<br>"+count+",请输入最小订单金额；");
        count++;
      }
      if(0>Double.valueOf(""+vMap.get("MIN_MONEY"))){
        buffer.append("<br>"+count+",最小订单金额不能为负；");
        count++;
      }
      if(0>Double.valueOf(""+vMap.get("SAVE_MONEY"))){
        buffer.append("<br>"+count+",优惠金额不能为负；");
        count++;
      }
      if(null==vMap.get("STATE")){
        buffer.append("<br>"+count+",请选择状态；");
        count++;
      }
      if("".equalsIgnoreCase((String)vMap.get("BEGIN_TIME"))){
        buffer.append("<br>"+count+",请选择开始时间；");
        count++;
      }
      if("".equalsIgnoreCase((String)vMap.get("END_TIME"))){
        buffer.append("<br>"+count+",请选择结束时间；");
        count++;
      }
      if(null==vMap.get("AUTO_COUNT") || "".equalsIgnoreCase(""+vMap.get("AUTO_COUNT"))){
        buffer.append("<br>"+count+",请输入产生数量，若不需要请输入“0”；");
        count++;
      }
      if(0 != buffer.length()){
        throw new BizException(buffer.toString());
      }
      BatchBiz batchBiz=new BatchBiz(this.getUserACL());
      batchBiz.addBatchInfoOfCard(vMap, this.addBatchFormView.getFuncMap());
      return createReturnJSON(true, "增加成功", true, false, "batchListView", "");
    }catch (BizException e) {
      throw e;
    } 
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"添加信息失败");
    }
  }

}
