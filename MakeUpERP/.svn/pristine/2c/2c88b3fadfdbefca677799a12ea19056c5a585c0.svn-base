package com.ga.click.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/** */  
/** 
* 生成导出Excel文件对象 
*  
* @author John.Zhu 
*/  
public class ExcelExport {  
  
// 设置cell编码解决中文高位字节截断  
  
// 定制日期格式  
private static String DATE_FORMAT = "yyyy-mm-dd"; // "m/d/yy h:mm"  
  
// 定制浮点数格式  
private static String NUMBER_FORMAT = " #,##0.00 ";  
  
private String xlsFileName;  
  
private SXSSFWorkbook workbook;  
  
private Sheet sheet;  
  
private Row row;  
  
/** */  
/** 
* 初始化Excel 
*  
* @param fileName 
*            导出文件名 
*/  
public ExcelExport(String fileName) {  
   this.xlsFileName = fileName;  
   this.workbook = new SXSSFWorkbook(1000);  
   this.sheet = workbook.createSheet();  
}  
  
/** */  
/** 
* 导出Excel文件 
*  
* @throws XLSException 
*/  
public void exportXLS() throws Exception {  
  FileOutputStream fOut = null;
   try {  
     fOut = new FileOutputStream(xlsFileName);  
     workbook.write(fOut);  
   }  
   catch (FileNotFoundException e) {  
    throw new Exception(" 生成导出Excel文件出错! ", e);  
   }  
   catch (IOException e) {  
    throw new Exception(" 写入Excel文件出错! ", e);  
   }  finally {
     try {
       if (fOut != null) fOut.close();
     } catch(Exception e) {
       
     }
   }
  
}  
  
/** */  
/** 
* 增加一行 
*  
* @param index 
*            行号 
*/  
public void createRow(int index) {  
   this.row = this.sheet.createRow(index);  
}  
  
/** */  
/** 
* 设置单元格 
*  
* @param index 
*            列号 
* @param value 
*            单元格填充值 
*/  
public void setCell(int index, String value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_STRING);
   cell.setCellValue(value);  
}  
  
/** */  
/** 
* 设置单元格 
*  
* @param index 
*            列号 
* @param value 
*            单元格填充值 
*/  
//public void setCell(int index, Calendar value) {  
//   Cell cell = this.row.createCell(index);  
//   cell.setCellValue(value.getTime());  
//   CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式  
//   cellStyle.setDataFormat(XSSFDataFormat); // 设置cell样式为定制的日期格式  
//   cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式  
//}  
  
/** */  
/** 
* 设置单元格 
*  
* @param index 
*            列号 
* @param value 
*            单元格填充值 
*/  
public void setCell(int index, int value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_NUMERIC);  
   cell.setCellValue(value);  
}  
  
/** */  
/** 
* 设置单元格 
*  
* @param index 
*            列号 
* @param value 
*            单元格填充值 
*/  
public void setCell(int index, double value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_NUMERIC);  
   cell.setCellValue(value);  
   CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式  
   DataFormat format = workbook.createDataFormat();  
   cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式  
   cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式  
}  
  
public static void main(String[] args) {  
   System.out.println(" 开始导出Excel文件 ");  
   ExcelExport e = new ExcelExport("d:/test.xls");  
   e.createRow(0);  
   e.setCell(0, " 编号 ");  
   e.setCell(1, " 名称 ");  
   e.setCell(2, " 日期 ");  
   e.setCell(3, " 金额 ");  
   e.createRow(1);  
   e.setCell(0, 1);  
   e.setCell(1, " 工商银行 ");  
//   e.setCell(2, Calendar.getInstance());  
   e.setCell(3, 111123.99);  
   e.createRow(2);  
   e.setCell(0, 2);  
   e.setCell(1, " 招商银行 ");  
//   e.setCell(2, Calendar.getInstance());  
   e.setCell(3, 222456.88);  
   try {  
    e.exportXLS();  
    System.out.println(" 导出Excel文件[成功] ");  
   }  
   catch (Exception e1) {  
    System.out.println(" 导出Excel文件[失败] ");  
    e1.printStackTrace();  
   }  
}  
  
}   