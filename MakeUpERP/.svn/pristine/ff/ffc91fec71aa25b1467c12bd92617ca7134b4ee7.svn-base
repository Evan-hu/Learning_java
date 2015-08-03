package com.ga.erp.biz.stock.shelf;

import java.sql.ResultSet;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.TreeView;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.stock.stockcomm.StockCommBiz;
import com.ga.erp.biz.stock.stockcomm.StockCommListView;

@SuppressWarnings("serial")
public class ShelfMainPage extends UnitPage{
	
  private TreeView shelfSelectView;
  private FormView shelfForm;
  private ListView shelfCommList;
  
  @Override
  public void initController() throws Exception {
	  
	this.shelfSelectView = new ShelfSeleView("shelfTree",this.modelMap);
	this.shelfForm = new ShelfFormView("shelfForm",this.modelMap);  
	this.shelfCommList = new StockCommListView("shelfCommList",this.modelMap);  
	this.regPageLoadEvent("pageLoad");

    ActionButton action = shelfSelectView.regClickEvent(this.getSelfUrl(), "getShelfDetail", this);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr(),this.shelfForm, this.shelfCommList);
    action.getEvent().addEventParam(this.shelfForm,PageEvent.PARAMTYPE_QUERYVALUE,"SHELF_ID");
    
    action  = this.shelfForm.regAddEventByOpenDialog("newArticleShlef","新增货架","/stock/shelf_detail.htm", this);
    SubmitTool.submitToDialog(action, "newShelfDlg","新增货架",800,250,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.shelfForm,"pID","SHELF_ID",true);
    action.bindViewParam(this.shelfForm,"pName","SHELF_NUM",true);
    
    action = this.shelfCommList.regAddAction("newshelfCommList","添加库存商品","/stock/comm_detail.htm");
    SubmitTool.submitToDialog(action,"newshelfCommList", "添加库存商品",800,300,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.shelfForm,"ShelfID","SHELF_ID",true);
    action.bindViewParam(this.shelfForm,"ShelfNum","SHELF_NUM",true);//数据绑定到action
//    action.addParam("RELOAD_VIEW", this.shelfCommList.getViewID());
    
    action = this.shelfCommList.regEditAction("editockComm","查看/编辑", "/stock/comm_detail.htm");
    SubmitTool.submitToDialog(action, "editockComm", "查看/编辑",800,350,this.modelMap.getPreNavInfoStr());
//    action.addParam("RELOAD_VIEW", this.shelfCommList.getViewID());
    
    this.shelfForm.regEditSaveEvent("saveShelf", "saveShelf", this,false);
    
    shelfCommList.regStateAction(this.getSelfUrl(), this,"STOCK_COMM");
    
    PageEvent event = this.regListViewLoadEvent(this.shelfCommList, "reloadShelfCommList");
    event.addEventParam(this.shelfCommList,PageEvent.PARAMTYPE_PAGEQUERY); 
    event.addEventParam(this.shelfForm, PageEvent.PARAMTYPE_QUERYVALUE,"SHELF_ID");
    
  }
  
  @Override
  public Layout initLayout() {
	  
	ViewPageLayout treeLayout = new ViewPageLayout(this);
	treeLayout.addControl("货架列表", this.shelfSelectView);
	FormLayout formLayout = new FormLayout("", this.shelfForm.getDataForm(), 2);
	this.shelfForm.getDataForm().setLayout(formLayout);
	treeLayout.addControl("","货架明细", "货架明细","", this.shelfForm);
	treeLayout.addControl("","货架明细", "货架商品","", this.shelfCommList);
	treeLayout.setLeftPanel("", "货架列表");
	treeLayout.setLeftWidth("", 20);
	treeLayout.setControlLayoutH(this.shelfSelectView.getViewControl(),50);
	
    return treeLayout;
  }
  
  public void pageLoad(ModelMap modelMap) {
    try {
      ShelfBiz biz = new ShelfBiz(this.getUserACL());
      ResultSet dataList = biz.querySortList(1L);	
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SHELF_ID", "P_SHELF_ID", "SHELF_NUM", 
    		  "SHELF_ID", "SHELF_ID,SHELF_NUM", 1L);
      
      this.shelfSelectView.bindData(treeNode,this.getSelfUrl(),false);
      this.shelfSelectView.getTreeControl().setExpandLv(1); 
      this.shelfForm.bindNull();
      this.shelfCommList.bindNull();
      
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult getShelfDetail(Long shelfId) {
      try {
    	ShelfBiz biz = new ShelfBiz(this.getUserACL());

    	Map<String,Object> shelfDetailMap = biz.queryShelfDetail(this.shelfForm.getFieldList(),shelfId);
        this.shelfForm.bindData(shelfDetailMap);
        
        StockCommBiz sBiz=new StockCommBiz(this.getUserACL());
        PageResult result = sBiz.queryStockCommList(new QueryPageData(), this.shelfCommList.getFieldList(), shelfId, null,null);
        this.shelfCommList.bindData(result);

        FormLayout formLayout = new FormLayout("", this.shelfForm.getDataForm(), 2);
        this.shelfForm.getDataForm().setLayout(formLayout);
      
        return this.linkView(this.shelfForm, this.shelfCommList);
      } catch (BizException ex) {
          throw ex;
      }catch (Exception e) {
        e.printStackTrace();
        throw new BizException("获取货架详情失败");
      }
  }
  
  public ActionResult saveShelf(Map<String, Object> sortMap){
    try {
    	ShelfBiz biz = new ShelfBiz(this.getUserACL());
      String sortId = sortMap.get("SHELF_ID") + "";
      String chkSortId = sortMap.get("P_SHELF_ID") + "";
      
      if(sortId.equals("")){
        throw new BizException("请选择要更新的货架");
      }
      if(biz.isSelfChild(sortId, chkSortId)){
        throw new BizException("不能选择自己以及子类作为父类");
      }
      biz.saveShelf(sortMap, false);
      return this.createReturnJSON(true, "修改货架成功,重新加载本页面", true, true, "shelfForm","");
    } catch (BizException ex) {
       throw ex;
    }catch (Exception e) {
      e.printStackTrace();;
      throw new BizException("保存货架异常");
    }
  }
  
  public ActionResult reloadShelfCommList(QueryPageData pageData, Long shelfId) {
	 try {
	      StockCommBiz sBiz = new StockCommBiz(this.getUserACL());
	      PageResult result = sBiz.queryStockCommList(this.shelfCommList.getViewPageQuery(), this.shelfCommList.getFieldList(), shelfId,null,null);
	      this.shelfCommList.bindData(result);
	      ClickUtil.setControlLayoutH(this.shelfCommList.getViewControl(), 150);
	      return this.linkView(this.shelfCommList);
	    } catch(BizException e) {
	      throw e;
	    } catch(Exception ex) {
	      throw new BizException("加载页面异常");
	    }
	  }
  
}
