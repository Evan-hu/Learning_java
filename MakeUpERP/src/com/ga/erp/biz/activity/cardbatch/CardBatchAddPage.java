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
        buffer.append("<br>"+count+",�������������ƣ�");
        count++;
      }
      if(null==vMap.get("TYPE")){
        buffer.append("<br>"+count+",��ѡ���������ͣ�");
        count++;
      }
      if(0==Double.valueOf(""+vMap.get("MIN_MONEY"))){
        buffer.append("<br>"+count+",��������С������");
        count++;
      }
      if(0>Double.valueOf(""+vMap.get("MIN_MONEY"))){
        buffer.append("<br>"+count+",��С��������Ϊ����");
        count++;
      }
      if(0>Double.valueOf(""+vMap.get("SAVE_MONEY"))){
        buffer.append("<br>"+count+",�Żݽ���Ϊ����");
        count++;
      }
      if(null==vMap.get("STATE")){
        buffer.append("<br>"+count+",��ѡ��״̬��");
        count++;
      }
      if("".equalsIgnoreCase((String)vMap.get("BEGIN_TIME"))){
        buffer.append("<br>"+count+",��ѡ��ʼʱ�䣻");
        count++;
      }
      if("".equalsIgnoreCase((String)vMap.get("END_TIME"))){
        buffer.append("<br>"+count+",��ѡ�����ʱ�䣻");
        count++;
      }
      if(null==vMap.get("AUTO_COUNT") || "".equalsIgnoreCase(""+vMap.get("AUTO_COUNT"))){
        buffer.append("<br>"+count+",���������������������Ҫ�����롰0����");
        count++;
      }
      if(0 != buffer.length()){
        throw new BizException(buffer.toString());
      }
      BatchBiz batchBiz=new BatchBiz(this.getUserACL());
      batchBiz.addBatchInfoOfCard(vMap, this.addBatchFormView.getFuncMap());
      return createReturnJSON(true, "���ӳɹ�", true, false, "batchListView", "");
    }catch (BizException e) {
      throw e;
    } 
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"�����Ϣʧ��");
    }
  }

}
