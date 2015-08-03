/**
 * Copyright (c) 2013 GA
 * All right reserved.
 */
package com.ga.erp.biz.stock.inventorybatch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ga.click.dbutil.DbUtils;
import com.ga.click.util.FileUploadUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 类描述
 * @author cs
 * @create_time 2014-5-1 下午3:39:24
 * @project MakeUpERP
 */
public class ExportBiz {
  
/*************************************导出Excel*********************************************/
  
  
  public int beginRow = 3; //Excel开始行

  public String exportBatchComm(String type, Long id){
    
    String relativeFilePath = "/tempUpload/" + new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + type;
    String absoluteFilePath = FileUploadUtil.getSiteRoot() + relativeFilePath;
    Map<String, Object> batchMap = queryBatchMap(id);
    List<Map<String, Object>> bilList = queryBatchBillList(id);
    if(type.equals(".csv")){
      exportCSV(bilList, absoluteFilePath);
    } else {
      exportExcel(batchMap, bilList, absoluteFilePath);
    }
    return relativeFilePath; 
  }

  private void exportExcel(Map<String, Object> batchMap, List<Map<String, Object>> bilMapList, String filePath){
    WritableWorkbook book = null;
    try {
      book = Workbook.createWorkbook(new File(filePath));
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    WritableSheet sheet = book.createSheet("盘点单数据", 0);   //单元表名
    for (int i = 0; i < 6; i++) {
      sheet.setColumnView(i, 22);
    }
    try {
      addTitle(sheet,batchMap);
      String billId = "";
      List<Map<String, Object>> commList = new ArrayList<Map<String,Object>>();
      for (Map<String, Object> map : bilMapList) {
        billId = map.get("INVENTORY_BILL_ID") + "";
        beginRow++;
        addBill(sheet, map, beginRow);
        addBatchCommTitle(sheet, beginRow);
        commList = queryBillCommList(billId);
        for (Map<String, Object> commMap : commList) {
          addBatchComm(sheet, commMap, beginRow);
          beginRow++;   
        }
      }
    } catch (RowsExceededException e1) {
      e1.printStackTrace();
    } catch (WriteException e1) {
      e1.printStackTrace();
    }  
    try {
      book.write();
      book.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (WriteException e) {
      e.printStackTrace();
    }
  }
  
  /*************************************导出Excel*********************************************/
  
  private void exportCSV(List<Map<String, Object>> billMapList, String filePath){
    StringBuffer buffer = new StringBuffer("");
    List<Map<String, Object>> commList = new ArrayList<Map<String,Object>>();
    String billId = "";
    for (Map<String, Object> billsMap : billMapList) {
     buffer.append("盘点流水号,创建人,创建时间,备注\n");
     buffer.append(billsMap.get("INVENTORY_FLOW_NUM")+",")
           .append(billsMap.get("TRUENAME") + ",")
           .append(billsMap.get("CREATE_TIME") + ",")
           .append(billsMap.get("NOTE") + "\n");
     
     billId = billsMap.get("INVENTORY_BILL_ID") + "";
     commList = queryBillCommList(billId);
     
     buffer.append("盘点商品号,商品名,商品条码,系统库存,实际库存\n");
     
     for (Map<String, Object> commMap : commList) {
       buffer.append(commMap.get("INVENTORY_COMM_ID")+",")
       .append(commMap.get("COMMODITY_NAME") + ",")
       .append(commMap.get("CODE") + ",")
       .append(commMap.get("STOCK_INNAGE") + "\n");
     }
    }
    try {
      FileWriter fw = new FileWriter(filePath);
      fw.write(buffer.toString());
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private Map<String, Object> queryBatchMap(Long batchId){
    String sql = "SELECT\n" + 
                 " ib.INVENTORY_BATCH_NUM,\n" + 
                 " CASE\n" + 
                 " ib.SCOPE \n" + 
                 "  WHEN 1 \n" + 
                 "  THEN '全场盘点' \n" + 
                 "  WHEN 2 \n" + 
                 "  THEN '单品盘点' \n" + 
                 "    WHEN 3 \n" + 
                 "    THEN '分类盘点' \n" + 
                 "  ELSE '品牌盘点' \n" + 
                 " END SCOPE,\n" + 
                 "   DATE_FORMAT(ib.CREATE_TIME,'%Y-%m-%d %h:%i:%s') CREATE_TIME,\n" + 
                 "   ib.NOTE,\n" + 
                 " o.TRUENAME \n" + 
                 "  FROM\n" + 
                 "  INVENTORY_BATCH ib \n" + 
                 " JOIN OP o \n" + 
                 "   ON ib.OP_ID = o.OP_ID where ib.INVENTORY_BATCH_ID = ?";
    return DbUtils.queryMap(DbUtils.getConnection(), sql, batchId);
  }
  
  private List<Map<String, Object>> queryBatchBillList(Long batchId){
    
    String sql = "SELECT\n" +  
                 " ib.INVENTORY_BILL_ID,\n" + 
                 " ib.INVENTORY_FLOW_NUM,\n" + 
                 " DATE_FORMAT(ib.CREATE_TIME,'%Y-%m-%d %h:%i:%s') CREATE_TIME,\n" + 
                 " ib.NOTE,\n" + 
                 " o.TRUENAME \n" + 
                 " FROM\n" + 
                 " INVENTORY_BILL ib \n" + 
                 " JOIN OP o \n" + 
                 "     ON ib.OP_ID = o.OP_ID \n" + 
                 "   WHERE ib.INVENTORY_BATCH_ID = ?";
    
    return DbUtils.queryMapList(DbUtils.getConnection(), sql, batchId);
    
  }
  
  private List<Map<String, Object>> queryBillCommList(String billId){
    String sql = "SELECT\n" +
        "        ic.INVENTORY_COMM_ID,\n" +
        "        c.COMMODITY_NAME,\n" +
        "        c.CODE,\n" +
        "        ic.STOCK_INNAGE \n" +
        "        FROM\n" +
        "       INVENTORY_COMM ic\n" + 
        "          JOIN COMMODITY c \n" +
        "            ON ic.COMMODITY_ID = c.COMMODITY_ID\n" + 
        "          WHERE ic.INVENTORY_BILL_ID = ?";
    return DbUtils.queryMapList(DbUtils.getConnection(), sql, billId);
  }
  
  private WritableCellFormat getNormalCellFormat(int fontSize, Colour bgcolor, Boolean isBold){
    WritableFont wfc = new WritableFont(WritableFont.ARIAL, fontSize, isBold ? WritableFont.BOLD : WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
    WritableCellFormat cellFormat = new WritableCellFormat(wfc);
    try {
      cellFormat.setAlignment(Alignment.CENTRE);
      cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
      if(bgcolor != null){
        cellFormat.setBackground(bgcolor);
      }
    } catch (WriteException e) {
      e.printStackTrace();
      
    }  
    return cellFormat;
  }
  
  public WritableCellFormat batchTitle = getNormalCellFormat(8,Colour.LIGHT_GREEN,true);
  public WritableCellFormat batchContent = getNormalCellFormat(10,Colour.LIGHT_GREEN,false);
  public WritableCellFormat billTitle = getNormalCellFormat(8,Colour.GRAY_50,true);
  public WritableCellFormat billContent = getNormalCellFormat(10,Colour.GRAY_50,false);
  public WritableCellFormat normalTitle = getNormalCellFormat(8,null,true);
  public WritableCellFormat normalContent = getNormalCellFormat(10,null,false);
  
  private void addTitle(WritableSheet sheet, Map<String, Object> batchMap) throws WriteException {
    sheet.addCell(new Label(0, 0, "盘点批次编码", batchTitle));
    sheet.addCell(new Label(1, 0, batchMap.get("INVENTORY_BATCH_NUM") + "", batchContent));
    sheet.addCell(new Label(2, 0, "盘点范围", batchTitle));  
    sheet.addCell(new Label(3, 0, batchMap.get("SCOPE") + "", batchContent));  
    sheet.addCell(new Label(0, 1, "创建人", batchTitle));  
    sheet.addCell(new Label(1, 1, batchMap.get("TRUENAME") + "", batchContent));  
    sheet.addCell(new Label(2, 1, "创建时间", batchTitle));  
    sheet.addCell(new Label(3, 1, batchMap.get("CREATE_TIME") + "", batchContent));  
    sheet.addCell(new Label(0, 2, "备注", batchTitle));  
    sheet.addCell(new Label(1, 2, batchMap.get("NOTE") + "", batchContent));  
  }
  
  private void addBill(WritableSheet sheet, Map<String, Object> billsMap, int row) throws WriteException {
    sheet.addCell(new Label(0, row, "盘点流水号", billTitle));
    sheet.addCell(new Label(1, row, billsMap.get("INVENTORY_FLOW_NUM") + "", billContent));
    sheet.addCell(new Label(2, row, "创建人", billTitle));  
    sheet.addCell(new Label(3, row, billsMap.get("TRUENAME") + "", billContent));  
    sheet.addCell(new Label(0, row + 1, "创建时间", billTitle));  
    sheet.addCell(new Label(1, row + 1, billsMap.get("CREATE_TIME") + "", billContent));  
    sheet.addCell(new Label(2, row + 1, "备注", billTitle));  
    sheet.addCell(new Label(3, row + 1, billsMap.get("NOTE") + "", billContent));
    beginRow += 2;
  }
  
  private void addBatchCommTitle(WritableSheet sheet, int row) throws WriteException {
    sheet.addCell(new Label(0, row, "盘点商品号", normalTitle));
    sheet.addCell(new Label(1, row, "商品名", normalTitle));
    sheet.addCell(new Label(2, row, "商品条码", normalTitle));  
    sheet.addCell(new Label(3, row, "系统库存", normalTitle));  
    sheet.addCell(new Label(4, row, "实际库存", normalTitle));  
    beginRow++;
  }
  
  private void addBatchComm(WritableSheet sheet, Map<String, Object> batchCommMap, int row) throws WriteException {
    sheet.addCell(new Label(0, row, batchCommMap.get("INVENTORY_COMM_ID") + "", normalContent));
    sheet.addCell(new Label(1, row, batchCommMap.get("COMMODITY_NAME") + "", normalContent));
    sheet.addCell(new Label(2, row, batchCommMap.get("CODE") + "", normalContent));  
    sheet.addCell(new Label(3, row, batchCommMap.get("STOCK_INNAGE") + "", normalContent));  
  }

}
