package com.ga.erp.biz.store.storepos;

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

public class PosDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private FormView posForm;

	@Override
	public void initController() throws Exception {

		this.posForm = new PosFormView("posForm", this.modelMap);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.posForm, PageEvent.PARAMTYPE_QUERYVALUE,"POS_ID");

//		ActionButton action = new ActionButton(this.getClass(), "savePos","保存", this.getSelfUrl(), null);
//		action.bindForm(this.posForm.getViewID(), true);
//		SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
//
//		action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
//		this.posForm.regAction(action);
//
//		event = this.regPageEvent(action, "savePos");
//		event.addEventParam(this.posForm, PageEvent.PARAMTYPE_DATAMAP);
		
		//调试MTABLE
		ActionButton action = new ActionButton(this.getClass(), "savePos","保存", this.getSelfUrl(), null);
		action.bindForm(this.posForm.getViewID(), true);
		SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"posListView");

		action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
		this.posForm.regAction(action);		
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayout = new FormLayout("", this.posForm.getDataForm(),2);
		this.posForm.getDataForm().setLayout(formLayout);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.posForm);
		return layout;
	}

	public void pageLoad(Long posId) {
		try {
			Map<String, Object> map = this.posForm.getViewParam();
			if(map.get("STORE_ID") != null){
				this.posForm.getFieldList().remove(3);
			}
			if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
				this.posForm.bindData(map);
			} else {
				PosBiz biz = new PosBiz(this.getUserACL());
				Map<String, Object> valuesMap = biz.queryPosDetail(posId, this.posForm.getFieldList());
				this.posForm.bindData(valuesMap);
			}
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

	public ActionResult savePos(Map<String, Object> valueMap) {
		PosBiz biz = new PosBiz(this.getUserACL());
		if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
			biz.savePos(valueMap, true);
		} else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
			biz.savePos(valueMap, false);
		}
		return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("STORE_NAME") != null ? "posList" : "storePosList", "");
	}
}
