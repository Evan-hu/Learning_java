package com.ga.click.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.page.QueryPageData;


public abstract class MTablePage  extends UnitPage {

	private static final long serialVersionUID = 1L;
	protected FormView detailView;	
	protected ListView listView;
	
	
	
//	public abstract MTableUtil initMUtil();
//
	@Override
	public void initController() throws Exception {
		// TODO Auto-generated method stub
		 //初始化主从表的列表视图和明细视图
		  this.listView = initListView();
		  this.listView.setMTableMode(true);
		  this.detailView = initDetailView();
		  		  
		  //注册初始化事件,包括数据加载和数据绑定
		  PageEvent event = this.regPageLoadEvent("pageLoad");
		  event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYMAP);
		  event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
		  
		  //注册页面按钮事件
		  //子表新建\修改事件		  
	      Map<String,Object>paramMap = new HashMap<String,Object>();
	      paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_EDIT);
		  ActionButton action = new ActionButton(this.getClass(),"mtableSaveAction","保存",this.getSelfUrl(),paramMap);
		  action.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,this.modelMap.getPreNavInfoStr());
		  //SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr(), true, this.detailView,this.listView);
		 
		  this.regPageActionButton(action,this.detailView,this.listView);
		  event = this.regPageEvent(action, "saveAll");
		  event.addEventParam(this.listView, PageEvent.PARAMTYPE_REQUESTMAP);		  
		  event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYMAP);
		  event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
		  event.addEventParam(this.listView, PageEvent.PARAMTYPE_REQUEST,"mtable_del");
	}
	
	/**
	 * 初始子表列表视图,包括创建ListView对象以及设置ListView对象添加\删除\修改的各按钮
	 * @return
	 */
	public abstract ListView initListView();
	public abstract FormView initDetailView();
	
	/**
	 * 需重载的列表查询处理
	 * @param formParam 相关联的明细表单view所定义的查询参数
	 * @param pageParam 列表的自定义查询表单
	 * @return
	 */
	public abstract PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam);
	
	/**
	 * 需重载的明细查询处理
	 * @param paramMap 明细表单view所定义的查询参数
	 * @return
	 */
	public abstract Map<String,Object> loadDetailData(Map<String,Object> paramMap);
	
	public abstract void saveData(int editMode,Map<String,Object> formData,List<Map<String,Object>> listData,String delListID);
	
	@Override
	public Layout initLayout() {		
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
		this.detailView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);				
		layout.addControl(this.detailView);
		layout.addControl(this.listView);
	    return layout;
	}

	/**
	 * 初始化事件
	 * 1.将request的参数转为继承子类所需要的查询参数对象
	 * 2.调用子类重载的列表查询和明细查询获取数据
	 * 3.绑定数据到相应视图
	 * @param requestMap
	 */
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		try {			
			//调用主表查询
			Map<String,Object> detailData =  loadDetailData(formQuery);
			this.detailView.bindData(detailData);
			//调用从表数据查询
			pageQuery.GetPageParam().setPageSize(5);
			PageResult listData =  loadListData(formQuery,pageQuery);
			this.listView.bindData(listData);
	    } catch(BizException ex) {
	      throw ex;
	    } catch(Exception e) {
	      throw new BizException(BizException.SYSTEM,"页面加载异常");
	    }
	}
	
	public ActionResult saveAll(Map<String,String[]> requestMap,Map<String,Object> formParam,QueryPageData pageQuery,String delIDList) {						
		try {			
			//解析参数
			List<Map<String,Object>> listData = this.listView.parseListValue(this.listView.getFieldList());
			Map<String,Object> detailData = new HashMap<String,Object>();
	        for (DbField field : this.detailView.getFieldList()) {
	          if (field.getInputType() > 0) {
	            //作为输入数据放入
	            Object value = detailView.parseDbFieldInputData(field);
	            detailData.put(field.getFieldCode(), value);
	          }
	        }
	        //执行保存
	        saveData(this.detailView.viewEditMode,detailData,listData,delIDList);
	        //刷新返回
	    	Map<String,Object> getFormData =  loadDetailData(formParam);
			this.detailView.bindData(getFormData);
			//调用从表数据查询
			pageQuery.GetPageParam().setPageSize(5);
			PageResult getListData =  loadListData(formParam,pageQuery);
			this.listView.bindData(getListData);
			return this.linkView(true,"",this.listView,this.detailView);
	       
	    } catch(BizException ex) {
	      throw ex;
	    } catch(Exception e) {
	      throw new BizException(BizException.SYSTEM,"页面加载异常");
	    }
	}		
}
