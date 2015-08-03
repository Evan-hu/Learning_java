package com.ga.erp.biz.member.vipLoyaltyAn;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class VipLoyaltyAnDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private VipLoyaltyAnFormView formView;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new VipLoyaltyAnFormView("VipLoyaltyAnDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");   
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "MEMBER_ID");
		    
		  
	}

   @Override
	public Layout initLayout() {
			FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
			this.formView.getDataForm().setLayout(formLayOut);
			ViewPageLayout layout = new ViewPageLayout(this);
			layout.addControl(this.formView);
		    return layout;
		 
   }

    public void pageLoad(Long memberId) {
    	
	   try {   
		   System.out.println(memberId);
				 VipLoyaltyAnBiz biz = new VipLoyaltyAnBiz(this.getUserACL()); 
				 for(DbField field:this.formView.getFieldList()){
					 field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
				 }
				  this.formView.bindData(biz.queryMemberDetail(memberId, this.formView.getFieldList()));
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"“≥√Êº”‘ÿ“Ï≥£");
		   
		    }
    }
	 
}
