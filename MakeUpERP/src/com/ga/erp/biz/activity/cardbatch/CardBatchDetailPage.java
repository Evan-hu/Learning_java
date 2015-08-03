package com.ga.erp.biz.activity.cardbatch;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class CardBatchDetailPage extends UnitPage {
  
  private CardBatchFormView editBatchFormView;

  @Override
  public void initController() throws Exception {
    
    this.editBatchFormView = new CardBatchFormView("editBatchForm", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.editBatchFormView, PageEvent.PARAMTYPE_QUERYVALUE, "CARD_BATCH_ID");
    
    this.editBatchFormView.regEditSaveEvent("updateBatchInfoBtn", "updateBatchInfoOfCard", this, false);
    
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout=new FormLayout("", this.editBatchFormView.getDataForm(), 2);
    formLayout.mergeCellField("auto", 4, 0, 1, "NOTE");
    this.editBatchFormView.getDataForm().setLayout(formLayout);
    layout.addControl(this.editBatchFormView);
    return layout;
  }
  
  public void pageLoad(Long batchID){
    try {
      BatchBiz batchBiz=new BatchBiz(this.getUserACL());
      Map<String, Object> result=batchBiz.queryBatchInfoOfCard(batchID,this.editBatchFormView.getFieldList());
      this.editBatchFormView.bindData(result);
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public ActionResult updateBatchInfoOfCard(Map<String, Object> vMap){
    try {
      if(null==vMap.get("CARD_BATCH_ID") || "".equalsIgnoreCase(""+vMap.get("CARD_BATCH_ID"))){
        throw  new BizException("请选择需要编辑的批次！");
      }
      StringBuffer buffer=new StringBuffer("");
      int count=1;
      if("".equalsIgnoreCase((String)vMap.get("BEGIN_TIME"))){
        buffer.append("<br>"+count+",请选择开始时间；");
        count++;
      }
      if("".equalsIgnoreCase((String)vMap.get("END_TIME"))){
        buffer.append("<br>"+count+",请选择结束时间；");
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
      if("".equalsIgnoreCase((String)vMap.get("NAME"))){
        buffer.append("<br>"+count+",请填写名称；");
        count++;
      }
      if(null==vMap.get("STATE")){
        buffer.append("<br>"+count+",请选择状态；");
        count++;
      }
      if(null==vMap.get("TYPE")){
        buffer.append("<br>"+count+",请选择类型；");
        count++;
      }
      if(0 !=buffer.length()){
        throw new BizException(buffer.toString());
      }
      BatchBiz batchBiz=new BatchBiz(this.getUserACL());
      batchBiz.updateBatchInfoOfCard(vMap);
      return createReturnJSON(true, "修改成功", true, false, "batchListView", "");
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"保存信息失败");
    }
  }

}
