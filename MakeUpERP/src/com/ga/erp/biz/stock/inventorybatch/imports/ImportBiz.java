package com.ga.erp.biz.stock.inventorybatch.imports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.FileUploadUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class ImportBiz extends ACLBiz{

  /**
   * @param userACL
   */
  public ImportBiz(UserACL userACL) {
    super(userACL);
  }
  
  public static final String EXCEL_PATTERN = ".xls";
  public static final String CSV_PATTERN = ".csv";
  private final int commDataBeginRow = 4; //第一行盘点单数据所在位置
  private final int billInfoRows = 3; //盘点单信息所占行数
  private final int commInfoCnt = 5;  //盘点商品显示字段数
  
  public String importBatchComm(Map<String,Object> valueMap) {
    String filePath = "";
    String msg = "";
    try {      
      if(GaUtil.isNullStr(valueMap.get("FILEPATH") + "")){
        throw new BizException("请上传文件");
      }
      Long batchId = Long.parseLong(valueMap.get("INVENTORY_BATCH_ID") + "");
      FileUploadUtil.uploadSave(valueMap, "FILEPATH", "batch", batchId);
      filePath = FileUploadUtil.getPicFilePath("batch") + "/" + valueMap.get("FILEPATH").toString();
      if(!new File(filePath).exists()){
        throw new BizException("文件不存在");
      }
      String extStr = filePath.substring(filePath.lastIndexOf("."), filePath.length());
      if(extStr.equals(EXCEL_PATTERN)){
        msg = importExcel(filePath, batchId);
      } else {
        msg = importCsv(filePath, batchId);
      }
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"导入盘点商品失败",ex);
    } finally {
      File file = new File(filePath);
      if(file.exists()) {
        file.delete();
      }
    }
    return msg;
  }
  
  private String importExcel(String filePath, Long batchId) {
    int sucItemCnt = 0; //成功条数
    int errCnt = 0;  //错误数
    StringBuilder errContent = new StringBuilder(""); //错误内容       
    Workbook rwb;
    try {
      InputStream is = new FileInputStream(filePath);
      rwb = Workbook.getWorkbook(is);
      Sheet sheet = rwb.getSheet(0);
      if(sheet.getRows() < 1){
        throw new BizException("未找到数据，请确认所传文件数据有效");
      }
      String batchNum = sheet.getCell(1, 0).getContents();
      if(isBatchNumInvalid(batchId, batchNum)){
        throw new BizException("数据与批次不对应");
      }
      Map<String, String> commInfoMap = new HashMap<String, String>();
      int billErr = 0;
      String billNum = "";
      String invetoryCommId = "";
      String commName = "";
      String realInnage = "";
      for (int i = commDataBeginRow; i < sheet.getRows(); i++) {
    	if(sheet.getCell(0, i).getContents().trim().equals("盘点流水号")){
    	  billNum = sheet.getCell(1, i).getContents().trim();
    	  if(!GaUtil.isNumber(billNum)){
      		errCnt++;
      		errContent.append(errCnt + ". 第" + (i + 1) +"行盘点单号不存在，" + billNum+ "；<br/>");
      		billErr++;  
    		if(billErr >= 3){
    		  errContent.append(errCnt + ". " + (i + 1) +"错误过多，请确认上传模板正确性；<br/>");	
    		 }
    	  } 
    		i += billInfoRows; //跳到盘点商品数据行
    		if(i >= sheet.getRows()){
    		  break;    //避免最后一行因错误操作无盘点商品越界错误
    		}
    	}

        /**********************************读取盘点商品数据*************************************************/
    	  invetoryCommId = sheet.getCell(0, i).getContents().trim();
        commName = sheet.getCell(1, i).getContents().trim();
        if(GaUtil.isValidStr(invetoryCommId) && GaUtil.isValidStr(commName)){//循环完盘点商品数据进入下一个循环
          if(!GaUtil.isNumber(invetoryCommId)){
            errCnt++;
            errContent.append(errCnt + ". 第" + (i + 1) +"行盘点商品号不存在,商品名：" + commName+ ";<br/>");
          } else {
            if(isCommInvalid(invetoryCommId)){
              errCnt++;
              errContent.append(errCnt + ". 第" + (i + 1) +"行盘点商品号有误,：" + invetoryCommId+ ";<br/>");
            }
          }
          realInnage = sheet.getCell(4, i).getContents().trim();
          if(!GaUtil.isNumber(realInnage)){
            errCnt++;
            errContent.append(errCnt + ". 第" + (i + 1) +"行盘点数据不存在,商品名：" + commName+ ";<br/>");
          } 
          if(GaUtil.isNullStr(errContent.toString())){
            if(commInfoMap.get(invetoryCommId) != null){ //已经存在该商品则累加
              realInnage = (Long.parseLong(realInnage) + Long.parseLong(commInfoMap.get(invetoryCommId) + "")) + "";
            }
            commInfoMap.put(invetoryCommId, realInnage);
          }
          /**********************************读取盘点商品数据*************************************************/
        
         }
        }
      if(GaUtil.isNullStr(errContent.toString()) && commInfoMap.size() != 0){ //修改数据
        sucItemCnt = importCommData(batchId, commInfoMap, EXCEL_PATTERN);
      } else {
        throw new BizException("数据错误,请检查：<br/>" + errContent);
      }
      rwb.close();
    } catch (BiffException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
     }
    return "成功条数:" + sucItemCnt + ";";
  } 
  
  private String importCsv(String filePath, Long batchId) {
    int errCnt = 0;  //错误数
    StringBuilder errContent = new StringBuilder("");  //错误内容
    int sucItemCnt = 0; //成功条数
    try {
      CsvUtil util = new CsvUtil(filePath);
      List<String> content = util.getCommList();
      String[] commInfo; 
      Map<String, String> commInfoMap = new HashMap<String, String>();
      String invetoryCommId = "";
      String realInnage = "";
      for (String item : content) {
        commInfo = item.split(",");
        if(commInfo.length != commInfoCnt){
          errContent.append(errCnt + ". 数据不足,请确认盘点数据已经填写完毕;");
        }
        invetoryCommId = util.getContent(commInfo, 0);
        if(isCommInvalid(invetoryCommId)){
          errContent.append(errCnt + ". 盘点商品号有误：" + invetoryCommId);
        }
        realInnage = util.getContent(commInfo, 4);
        if(!GaUtil.isNumber(realInnage)){
          errContent.append(errCnt + ". 盘点数据有误：" + realInnage);
        }
        commInfoMap.put(invetoryCommId, realInnage);
      }
      if(GaUtil.isNullStr(errContent.toString())){ //修改数据
        sucItemCnt = importCommData(batchId, commInfoMap, CSV_PATTERN);
      } else {
        throw new BizException("数据错误,请检查：<br/>" + errContent);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "成功条数" + sucItemCnt + ";";
  } 
  
  /***
   * 修改盘点数据，生成盘点差异单
   * @param batchId
   * @param commInfo  key: 盘点库存  value:盘点商品ID
   * @return
   */
  private int importCommData(Long batchId, Map<String, String> commInfo, String type){
    int sucItemCnt = 0;
    try {
      Boolean isFirst = true;
      Long diffBillId = null;
      DbUtils.begin();
      String invetoryCommId = "";
      String realInnage = "";
      for (Map.Entry<String, String> map: commInfo.entrySet()) {
        invetoryCommId = map.getKey();
        realInnage = map.getValue();
        DbUtils.execute("update INVENTORY_COMM set INVENTORY_INNAGE = ? where INVENTORY_COMM_ID = ?", realInnage, invetoryCommId);
        Long stockInnage = getStockCnt(invetoryCommId);
        if(!(realInnage.equals(stockInnage + ""))){//若实际盘点数与系统有区别则生成盘点差异单
        if(isFirst){
          addDiffBill(batchId, type);
          isFirst = false;
          diffBillId = GaUtil.getLastId();
        }
        addDiffComm(realInnage, invetoryCommId, diffBillId);
        }
        sucItemCnt++;
        }
     DbUtils.commit(); 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DbUtils.rollback();
    }
    return sucItemCnt;
  }

  
  /**
   * 判断盘点商品号是否无效
   * */
  private boolean isCommInvalid(String invetoryCommId){
    String sql = "select count(*) from INVENTORY_COMM where INVENTORY_COMM_ID = ?";
    return DbUtils.queryLong(sql, invetoryCommId) == 0L;
  }
  
  private boolean isBatchNumInvalid(Long batchId, String batchNum){
    String sql = "select count(*) from INVENTORY_BATCH where INVENTORY_BATCH_ID = ? AND INVENTORY_BATCH_NUM = ?";
    return DbUtils.queryLong(sql, batchId, batchNum) == 0L;
  }
  
  private Long getStockCnt(String invetoryCommId){
    return DbUtils.queryLong("select STOCK_INNAGE from INVENTORY_COMM where INVENTORY_COMM_ID = ?", invetoryCommId);
  }
  
  private void addDiffBill(Long batchId, String type){
    String diffBillNum = "212"; //TODO 系统生成差异单号 
    String note = "系统导入" + (type.equals(EXCEL_PATTERN) ? "EXCEL" : "盘点机") + "数据生成";
    DbUtils.update("insert into INVENTORY_DIFF_BILL (INVENTORY_BATCH_ID,BILL_NUM,CREATE_TIME,OP_ID,STATE,NOTE)" +
    		" values (?,?,NOW(),?,1,?)", batchId, diffBillNum, this.userACL.getUserID(), note);
  }
  
  private Map<String, Object> queryCommMap(String commId){
	  return DbUtils.queryMap(DbUtils.getConnection(), "select * from INVENTORY_COMM where INVENTORY_COMM_ID = ?", commId);
  }
  
  private void addDiffComm(String realInnage, String commId, Long diffBillId){
	  Map<String, Object> commMap = queryCommMap(commId); 
	  if(commMap == null){
	    throw new BizException("未找到盘点商品信息");
	  }
	  String sql = "insert into DIFF_BILL_COMM (COMM_ID,STOCK_ID,INVENTORY_DIFF_BILL_ID,STOCK_CNT,INVENTORY_CNT," +
		  	"DIFF_CNT) values (?,?,?,?,?,?)";
	  Long stockCnt = Long.parseLong(commMap.get("STOCK_INNAGE") + "");
	  DbUtils.update(sql, commMap.get(""),commMap.get("COMMODITY_ID"),commMap.get("STOCK_ID"),diffBillId,stockCnt,
		  	realInnage,Long.parseLong(realInnage) - stockCnt);
  }
  
}
