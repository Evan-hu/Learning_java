package com.ga.erp.biz.activity.scorerule;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class ScoreRuleDetailPage extends UnitPage {
	
  private FormView ruleForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.ruleForm = new ScoreRuleFormView("ruleForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.ruleForm, PageEvent.PARAMTYPE_QUERYVALUE, "SCORE_RULE_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveRule","保存",this.getSelfUrl(),null);
    action.bindForm(this.ruleForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.ruleForm.regAction(action);
    
    event = this.regPageEvent(action, "saveRule");
    event.addEventParam(this.ruleForm, PageEvent.PARAMTYPE_DATAMAP);
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayOut = new FormLayout("",this.ruleForm.getDataForm(),2);
	this.ruleForm.getDataForm().setLayout(formLayOut);
	ViewPageLayout layout = new ViewPageLayout(this);
	layout.addControl(this.ruleForm);
    return layout;
  }

  public void pageLoad(Long ruleId) {
	try { 
	  if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
		  this.ruleForm.bindData(this.ruleForm.getViewParam());
	  }else if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  ScoreRuleBiz biz = new ScoreRuleBiz(this.getUserACL()); 
		  this.ruleForm.bindData(biz.queryRuleDetail(ruleId, this.ruleForm.getFieldList()));
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveRule(Map<String, Object> valueMap) {
	  ScoreRuleBiz biz = new ScoreRuleBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveRule(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveRule(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "ruleList", "");
  }
}
