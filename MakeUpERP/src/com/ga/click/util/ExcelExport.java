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
* ���ɵ���Excel�ļ����� 
*  
* @author John.Zhu 
*/  
public class ExcelExport {  
  
// ����cell���������ĸ�λ�ֽڽض�  
  
// �������ڸ�ʽ  
private static String DATE_FORMAT = "yyyy-mm-dd"; // "m/d/yy h:mm"  
  
// ���Ƹ�������ʽ  
private static String NUMBER_FORMAT = " #,##0.00 ";  
  
private String xlsFileName;  
  
private SXSSFWorkbook workbook;  
  
private Sheet sheet;  
  
private Row row;  
  
/** */  
/** 
* ��ʼ��Excel 
*  
* @param fileName 
*            �����ļ��� 
*/  
public ExcelExport(String fileName) {  
   this.xlsFileName = fileName;  
   this.workbook = new SXSSFWorkbook(1000);  
   this.sheet = workbook.createSheet();  
}  
  
/** */  
/** 
* ����Excel�ļ� 
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
    throw new Exception(" ���ɵ���Excel�ļ�����! ", e);  
   }  
   catch (IOException e) {  
    throw new Exception(" д��Excel�ļ�����! ", e);  
   }  finally {
     try {
       if (fOut != null) fOut.close();
     } catch(Exception e) {
       
     }
   }
  
}  
  
/** */  
/** 
* ����һ�� 
*  
* @param index 
*            �к� 
*/  
public void createRow(int index) {  
   this.row = this.sheet.createRow(index);  
}  
  
/** */  
/** 
* ���õ�Ԫ�� 
*  
* @param index 
*            �к� 
* @param value 
*            ��Ԫ�����ֵ 
*/  
public void setCell(int index, String value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_STRING);
   cell.setCellValue(value);  
}  
  
/** */  
/** 
* ���õ�Ԫ�� 
*  
* @param index 
*            �к� 
* @param value 
*            ��Ԫ�����ֵ 
*/  
//public void setCell(int index, Calendar value) {  
//   Cell cell = this.row.createCell(index);  
//   cell.setCellValue(value.getTime());  
//   CellStyle cellStyle = workbook.createCellStyle(); // �����µ�cell��ʽ  
//   cellStyle.setDataFormat(XSSFDataFormat); // ����cell��ʽΪ���Ƶ����ڸ�ʽ  
//   cell.setCellStyle(cellStyle); // ���ø�cell���ڵ���ʾ��ʽ  
//}  
  
/** */  
/** 
* ���õ�Ԫ�� 
*  
* @param index 
*            �к� 
* @param value 
*            ��Ԫ�����ֵ 
*/  
public void setCell(int index, int value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_NUMERIC);  
   cell.setCellValue(value);  
}  
  
/** */  
/** 
* ���õ�Ԫ�� 
*  
* @param index 
*            �к� 
* @param value 
*            ��Ԫ�����ֵ 
*/  
public void setCell(int index, double value) {  
   Cell cell = this.row.createCell(index);  
   cell.setCellType(Cell.CELL_TYPE_NUMERIC);  
   cell.setCellValue(value);  
   CellStyle cellStyle = workbook.createCellStyle(); // �����µ�cell��ʽ  
   DataFormat format = workbook.createDataFormat();  
   cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // ����cell��ʽΪ���Ƶĸ�������ʽ  
   cell.setCellStyle(cellStyle); // ���ø�cell����������ʾ��ʽ  
}  
  
public static void main(String[] args) {  
   System.out.println(" ��ʼ����Excel�ļ� ");  
   ExcelExport e = new ExcelExport("d:/test.xls");  
   e.createRow(0);  
   e.setCell(0, " ��� ");  
   e.setCell(1, " ���� ");  
   e.setCell(2, " ���� ");  
   e.setCell(3, " ��� ");  
   e.createRow(1);  
   e.setCell(0, 1);  
   e.setCell(1, " �������� ");  
//   e.setCell(2, Calendar.getInstance());  
   e.setCell(3, 111123.99);  
   e.createRow(2);  
   e.setCell(0, 2);  
   e.setCell(1, " �������� ");  
//   e.setCell(2, Calendar.getInstance());  
   e.setCell(3, 222456.88);  
   try {  
    e.exportXLS();  
    System.out.println(" ����Excel�ļ�[�ɹ�] ");  
   }  
   catch (Exception e1) {  
    System.out.println(" ����Excel�ļ�[ʧ��] ");  
    e1.printStackTrace();  
   }  
}  
  
}   