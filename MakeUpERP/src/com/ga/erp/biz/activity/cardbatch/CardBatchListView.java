package com.ga.erp.biz.activity.cardbatch;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.DbFieldFormat;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class CardBatchListView extends ListView {

  public CardBatchListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields=new ArrayList<DbField>();
    
    DbField field=new DbField("批次号", "CARD_BATCH_ID", GaConstant.DT_LONG);
    field.setPK(true);
    StringDecorator decorator=new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field=new DbField("批次名称", "NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
    decorator = new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    field.setColumnDisplay(true, 180, false);
    dbFields.add(field);
    
    field=new DbField("数量", "BATCH_COUNT", GaConstant.DT_INT);
    dbFields.add(field);
    
    field=new DbField("开始时间", "BEGIN_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd", false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 11, false);
    decorator=new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field=new DbField("结束时间", "END_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd", false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 12, false);
    decorator=new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field=new DbField("创建人", "TRUENAME", GaConstant.DT_STRING);
    dbFields.add(field);
    
    field=new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd", false));
    decorator=new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field = new DbField("状态", "STATE", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,20,false);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setAliasCode("ca");
    decorator = new StringDecorator();
    decorator.setStringFormat("<center>{value}</center>");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    this.fieldList=dbFields;
  }

}
