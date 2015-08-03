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
  private final int commDataBeginRow = 4; //��һ���̵㵥��������λ��
  private final int billInfoRows = 3; //�̵㵥��Ϣ��ռ����
  private final int commInfoCnt = 5;  //�̵���Ʒ��ʾ�ֶ���
  
  public String importBatchComm(Map<String,Object> valueMap) {
    String filePath = "";
    String msg = "";
    try {      
      if(GaUtil.isNullStr(valueMap.get("FILEPATH") + "")){
        throw new BizException("���ϴ��ļ�");
      }
      Long batchId = Long.parseLong(valueMap.get("INVENTORY_BATCH_ID") + "");
      FileUploadUtil.uploadSave(valueMap, "FILEPATH", "batch", batchId);
      filePath = FileUploadUtil.getPicFilePath("batch") + "/" + valueMap.get("FILEPATH").toString();
      if(!new File(filePath).exists()){
        throw new BizException("�ļ�������");
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
       throw new BizException(BizException.SYSTEM,"�����̵���Ʒʧ��",ex);
    } finally {
      File file = new File(filePath);
      if(file.exists()) {
        file.delete();
      }
    }
    return msg;
  }
  
  private String importExcel(String filePath, Long batchId) {
    int sucItemCnt = 0; //�ɹ�����
    int errCnt = 0;  //������
    StringBuilder errContent = new StringBuilder(""); //��������       
    Workbook rwb;
    try {
      InputStream is = new FileInputStream(filePath);
      rwb = Workbook.getWorkbook(is);
      Sheet sheet = rwb.getSheet(0);
      if(sheet.getRows() < 1){
        throw new BizException("δ�ҵ����ݣ���ȷ�������ļ�������Ч");
      }
      String batchNum = sheet.getCell(1, 0).getContents();
      if(isBatchNumInvalid(batchId, batchNum)){
        throw new BizException("���������β���Ӧ");
      }
      Map<String, String> commInfoMap = new HashMap<String, String>();
      int billErr = 0;
      String billNum = "";
      String invetoryCommId = "";
      String commName = "";
      String realInnage = "";
      for (int i = commDataBeginRow; i < sheet.getRows(); i++) {
    	if(sheet.getCell(0, i).getContents().trim().equals("�̵���ˮ��")){
    	  billNum = sheet.getCell(1, i).getContents().trim();
    	  if(!GaUtil.isNumber(billNum)){
      		errCnt++;
      		errContent.append(errCnt + ". ��" + (i + 1) +"���̵㵥�Ų����ڣ�" + billNum+ "��<br/>");
      		billErr++;  
    		if(billErr >= 3){
    		  errContent.append(errCnt + ". " + (i + 1) +"������࣬��ȷ���ϴ�ģ����ȷ�ԣ�<br/>");	
    		 }
    	  } 
    		i += billInfoRows; //�����̵���Ʒ������
    		if(i >= sheet.getRows()){
    		  break;    //�������һ�������������̵���ƷԽ�����
    		}
    	}

        /**********************************��ȡ�̵���Ʒ����*************************************************/
    	  invetoryCommId = sheet.getCell(0, i).getContents().trim();
        commName = sheet.getCell(1, i).getContents().trim();
        if(GaUtil.isValidStr(invetoryCommId) && GaUtil.isValidStr(commName)){//ѭ�����̵���Ʒ���ݽ�����һ��ѭ��
          if(!GaUtil.isNumber(invetoryCommId)){
            errCnt++;
            errContent.append(errCnt + ". ��" + (i + 1) +"���̵���Ʒ�Ų�����,��Ʒ����" + commName+ ";<br/>");
          } else {
            if(isCommInvalid(invetoryCommId)){
              errCnt++;
              errContent.append(errCnt + ". ��" + (i + 1) +"���̵���Ʒ������,��" + invetoryCommId+ ";<br/>");
            }
          }
          realInnage = sheet.getCell(4, i).getContents().trim();
          if(!GaUtil.isNumber(realInnage)){
            errCnt++;
            errContent.append(errCnt + ". ��" + (i + 1) +"���̵����ݲ�����,��Ʒ����" + commName+ ";<br/>");
          } 
          if(GaUtil.isNullStr(errContent.toString())){
            if(commInfoMap.get(invetoryCommId) != null){ //�Ѿ����ڸ���Ʒ���ۼ�
              realInnage = (Long.parseLong(realInnage) + Long.parseLong(commInfoMap.get(invetoryCommId) + "")) + "";
            }
            commInfoMap.put(invetoryCommId, realInnage);
          }
          /**********************************��ȡ�̵���Ʒ����*************************************************/
        
         }
        }
      if(GaUtil.isNullStr(errContent.toString()) && commInfoMap.size() != 0){ //�޸�����
        sucItemCnt = importCommData(batchId, commInfoMap, EXCEL_PATTERN);
      } else {
        throw new BizException("���ݴ���,���飺<br/>" + errContent);
      }
      rwb.close();
    } catch (BiffException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
     }
    return "�ɹ�����:" + sucItemCnt + ";";
  } 
  
  private String importCsv(String filePath, Long batchId) {
    int errCnt = 0;  //������
    StringBuilder errContent = new StringBuilder("");  //��������
    int sucItemCnt = 0; //�ɹ�����
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
          errContent.append(errCnt + ". ���ݲ���,��ȷ���̵������Ѿ���д���;");
        }
        invetoryCommId = util.getContent(commInfo, 0);
        if(isCommInvalid(invetoryCommId)){
          errContent.append(errCnt + ". �̵���Ʒ������" + invetoryCommId);
        }
        realInnage = util.getContent(commInfo, 4);
        if(!GaUtil.isNumber(realInnage)){
          errContent.append(errCnt + ". �̵���������" + realInnage);
        }
        commInfoMap.put(invetoryCommId, realInnage);
      }
      if(GaUtil.isNullStr(errContent.toString())){ //�޸�����
        sucItemCnt = importCommData(batchId, commInfoMap, CSV_PATTERN);
      } else {
        throw new BizException("���ݴ���,���飺<br/>" + errContent);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "�ɹ�����" + sucItemCnt + ";";
  } 
  
  /***
   * �޸��̵����ݣ������̵���쵥
   * @param batchId
   * @param commInfo  key: �̵���  value:�̵���ƷID
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
        if(!(realInnage.equals(stockInnage + ""))){//��ʵ���̵�����ϵͳ�������������̵���쵥
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
   * �ж��̵���Ʒ���Ƿ���Ч
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
    String diffBillNum = "212"; //TODO ϵͳ���ɲ��쵥�� 
    String note = "ϵͳ����" + (type.equals(EXCEL_PATTERN) ? "EXCEL" : "�̵��") + "��������";
    DbUtils.update("insert into INVENTORY_DIFF_BILL (INVENTORY_BATCH_ID,BILL_NUM,CREATE_TIME,OP_ID,STATE,NOTE)" +
    		" values (?,?,NOW(),?,1,?)", batchId, diffBillNum, this.userACL.getUserID(), note);
  }
  
  private Map<String, Object> queryCommMap(String commId){
	  return DbUtils.queryMap(DbUtils.getConnection(), "select * from INVENTORY_COMM where INVENTORY_COMM_ID = ?", commId);
  }
  
  private void addDiffComm(String realInnage, String commId, Long diffBillId){
	  Map<String, Object> commMap = queryCommMap(commId); 
	  if(commMap == null){
	    throw new BizException("δ�ҵ��̵���Ʒ��Ϣ");
	  }
	  String sql = "insert into DIFF_BILL_COMM (COMM_ID,STOCK_ID,INVENTORY_DIFF_BILL_ID,STOCK_CNT,INVENTORY_CNT," +
		  	"DIFF_CNT) values (?,?,?,?,?,?)";
	  Long stockCnt = Long.parseLong(commMap.get("STOCK_INNAGE") + "");
	  DbUtils.update(sql, commMap.get(""),commMap.get("COMMODITY_ID"),commMap.get("STOCK_ID"),diffBillId,stockCnt,
		  	realInnage,Long.parseLong(realInnage) - stockCnt);
  }
  
}
