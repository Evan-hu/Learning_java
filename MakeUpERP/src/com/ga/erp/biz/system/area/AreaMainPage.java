package com.ga.erp.biz.system.area;

import java.sql.ResultSet;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
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
import com.ga.erp.biz.supplier.SupplierBiz;
import com.ga.erp.biz.supplier.SupplierListView;
import com.ga.erp.biz.system.area.catalogue.CatalogueBiz;
import com.ga.erp.biz.system.area.catalogue.CatalogueListView;

public class AreaMainPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  private TreeView treeView;
	private FormView areaFormView;
	private ListView catalogueListView;
  private ListView supplierListView;

	@Override
	public void initController() throws Exception {
		this.treeView = new TreeView("areaTree", this.modelMap);
		this.areaFormView = new AreaFormView("areaFormView", this.modelMap);
		this.catalogueListView = new CatalogueListView("catalogueListView",this.modelMap);
		this.catalogueListView.showPage(false);
		this.catalogueListView.showQuery(false);
		
		this.supplierListView = new SupplierListView("supplierList",this.modelMap);
		
		// 注册加载事件(不需任何参数)
		PageEvent loadMethod = this.regPageLoadEvent("pageLoad");
		// loadMethod.addEventParam(catalogueListView,
		// PageEvent.PARAMTYPE_PAGEQUERY);
		// 树点击事件(将树节点点击事件以按钮方式定义,id必须同树视图ID一致)
		ActionButton action = treeView.regClickEvent(this.getSelfUrl(),"getSortDetail", this);
		SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr(),this.areaFormView, this.catalogueListView ,this.supplierListView);
		action.getEvent().addEventParam(this.areaFormView,PageEvent.PARAMTYPE_QUERYVALUE, "AREA_ID");

		// 新建子分类按钮(不触发事件)
		action = this.areaFormView.regAddEventByOpenDialog("newArea", "新建子地区",
				"/system/area_detail.htm", this);
		SubmitTool.submitToDialog(action, "newAreaDialog", "新建子地区", 640, 350,
				this.modelMap.getPreNavInfoStr());
		action.bindViewParam(this.areaFormView, "P_ID", "AREA_ID", false);
		action.bindViewParam(this.areaFormView, "P_NAME", "AREA_NAME", false);
		// 分类保存事件
		this.areaFormView.regEditSaveEvent("saveArea", "saveArea", this, false);

		action = this.catalogueListView.regAddAction("addCatalogue", "新建",
				"/system/catalogue_detail.htm");
		SubmitTool.submitToDialog(action, "addCatalogue", "新建", 800, 230,
				this.modelMap.getPreNavInfoStr());
		action.bindViewParam(this.areaFormView, "AREA_ID", "AREA_ID", false);
		action.setHidden(true);

		action = this.catalogueListView.regEditAction("editCatalogue",
				"查看 /编辑", "/system/catalogue_detail.htm");
		SubmitTool.submitToDialog(action, "editCatalogue", "查看/编辑", 800, 290,
				this.modelMap.getPreNavInfoStr());
		action.bindViewParam(this.areaFormView, "AREA_ID", "AREA_ID", false);
		action.setHidden(true);

		loadMethod = this.regListViewLoadEvent(this.catalogueListView,"reloadCatalogueList");
		loadMethod.addEventParam(this.catalogueListView,PageEvent.PARAMTYPE_QUERYVALUE, "AREA_ID");
		loadMethod.addEventParam(this.supplierListView, PageEvent.PARAMTYPE_PAGEQUERY);
		
		loadMethod = this.regListViewLoadEvent(this.supplierListView,"reloadSupplyList");
    loadMethod.addEventParam(this.areaFormView,PageEvent.PARAMTYPE_QUERYVALUE, "AREA_ID");
    
	}

	/**
	 * 页面初始化加载
	 * 
	 * @param modelMap
	 */
	public void pageLoad(ModelMap modelMap) {
		try {
			AreaBiz biz = new AreaBiz(this.getUserACL());
			ResultSet dataList = biz.queryAreaList(1L);
			// 将查询结果转为node;
			TreeNode treeNode = ClickUtil.getTreeNode(dataList, "AREA_ID",
					"P_ID", "AREA_NAME", "AREA_ID", "AREA_ID,AREA_NAME", 1L);
			// 绑定视图数据
			this.treeView.bindData(treeNode, this.getSelfUrl(), false);
			this.treeView.getTreeControl().setExpandLv(1);

			// Map<String,Object> detailInfo =
			// biz.queryPurviewDetail(this.purviewFormView.getFieldList(),1L);
			// this.purviewFormView.bindData(detailInfo);
			this.areaFormView.bindNull();
			this.catalogueListView.bindNull();
			this.supplierListView.bindNull();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "查询地区明细异常");
		}
	}

	/**
	 * 获取指定的明细信息 (刷新所有明细显示区域)
	 * 
	 * @param id
	 * @return
	 */
	public ActionResult getSortDetail(Long areaID) {
		try {
			this.catalogueListView.getActionByID("addCatalogue").setHidden(false);
			this.catalogueListView.getActionByID("editCatalogue").setHidden(false);
			// 相关查询
			AreaBiz biz = new AreaBiz(this.getUserACL());
			CatalogueBiz catalBiz = new CatalogueBiz(this.getUserACL());
			SupplierBiz sBiz = new SupplierBiz(this.getUserACL());
			Map<String, Object> detailInfo = biz.queryAreaDetail(this.areaFormView.getFieldList(), areaID);
			// 绑定视图
			this.areaFormView.bindData(detailInfo);
			// 设置视图控件高度
			ClickUtil.setControlLayoutH(this.areaFormView.getViewControl(), 50);
			// 因为此事件只初始化了一个sortFormView视图,因此只有sortFormView解析了参数，而其他列表视图的单独刷新需要相关参数，因此通过下面语句获取参数并进行传递
			// 返回视图解析结
			// 绑定采购目录数据
			this.catalogueListView.bindData(catalBiz.queryCatalogueList(areaID,this.catalogueListView.getFieldList()));
			this.supplierListView.bindData(sBiz.querySupplierList(new QueryPageData(),this.supplierListView.getFieldList(),areaID, ""));
			return this.linkView(this.areaFormView, this.catalogueListView,this.supplierListView);
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "查询地区详细异常");
		}
	}

	/**
	 * 保存地区处理
	 * 
	 * @param viewMap
	 *            视图的参数
	 * @return
	 */
	public ActionResult saveArea(Map<String, Object> viewMap) {
		try {
			// 相关查询
			AreaBiz biz = new AreaBiz(this.getUserACL());
			Long purviewId = (Long) viewMap.get("AREA_ID");
			Long chkID = (Long) viewMap.get("P_ID");
			if (biz.isSelfChild(purviewId, chkID)) {
				throw new BizException("不能选中自己下级作为上级");
			}
			biz.updatePurview(viewMap, this.areaFormView.getFuncMap(),this.areaFormView.getPKField().getFieldCode());
			return this.createReturnJSON(true, "保存地区信息成功,重新加载本页面！", true,false, "", "");
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "保存地区信息异常！");
		}
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout treeLayout = new ViewPageLayout(this);
		treeLayout.addControl("地区列表", this.treeView);
		treeLayout.setLeftPanel("", "地区列表");
		treeLayout.addControl("", "地区详细", "地区信息", "", this.areaFormView);
		treeLayout.addControl("", "地区详细", "采购目录", "", this.catalogueListView);
		treeLayout.addControl("", "地区详细", "地区供应商", "", this.supplierListView);
		treeLayout.setLeftWidth("", 25);
		treeLayout.setControlLayoutH(this.treeView.getViewControl(), 50);
		treeLayout.setControlLayoutH(this.areaFormView.getViewControl(), 50);
		return treeLayout;
	}

	public ActionResult reloadCatalogueList(Long areaId,QueryPageData pageData) {
		try {
			CatalogueBiz biz = new CatalogueBiz(this.getUserACL());
			this.catalogueListView.bindData(biz.queryCatalogueList(areaId,this.catalogueListView.getFieldList()));
			return this.linkView(this.catalogueListView);
		} catch (BizException e) {
			throw e;
		} catch (Exception ex) {
			throw new BizException("加载页面异常");
		}
	}

	public ActionResult reloadSupplyList(Long areaId,QueryPageData pageData) {
    try {
      SupplierBiz sBiz = new SupplierBiz(this.getUserACL());
      this.supplierListView.bindData(sBiz.querySupplierList(pageData,this.supplierListView.getFieldList(),areaId, ""));
      return this.linkView(this.supplierListView);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
}
