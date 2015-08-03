/**
 * Copyright (c) 2013 GA
 * All right reserved.
 */
package com.ga.erp.biz.stock.inventorybatch.imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ������
 * 
 * @author cs
 * @create_time 2014-5-2 ����4:57:40
 * @project MakeUpERP
 */
public class CsvUtil {
  
  private BufferedReader bufferedreader = null;
  private List<String> list = new ArrayList<String>();

  public CsvUtil(String filename) throws IOException {
    bufferedreader = new BufferedReader(new FileReader(filename));
    String itemInfo;
    while ((itemInfo = bufferedreader.readLine()) != null) {
      list.add(itemInfo);
    }
    bufferedreader.close();
  }

  public List<String> getList() throws IOException {
    return list;
  }

  public int getRowCnt() {
    return list.size();
  }

  public int getColCnt(int row) {
    if(list.size() < row)
      return 0;
      return list.get(row).toString().split(",").length;
  }

  public String getRow(int row) {
    if(list.size() < row)
      return "";
      return list.get(row).toString();
  }

  public List<String> getCol(int col) {
    List<String> colInfo = new ArrayList<String>();
    for (String content : list) {
      colInfo.add(content);
    }
    return colInfo;
  }

  /**
   * ȡ���� (���㿪ʼ)
   * */
  public String getContent(int row, int col) {
    if(row < 0 || row > getRowCnt() || 
       col < 0 || col > getColCnt(row) ){
      return "";
    }
    return getContent(getRow(row).split(","), col);
  }
  
  public String getContent(String[] rowArr, int col){
    int i = 0; 
    for (String str : rowArr) {
      if(i == col){
        return str;
      }
      i++;
    }
    return "";
  }
  
  private final int billRow = 3; 
  
  /**
   * ��ȡCSV�̵���Ʒ��Ϣ(�����̵㵥��������Ϣ)
   * */
  public List<String> getCommList(){
    List<String> commList = new ArrayList<String>();
    for (int i = billRow; i < list.size(); i++) {
      if(list.get(i).contains("�̵���ˮ��")){//ѭ�����̵���Ʒ���ݽ�����һ��ѭ��
        i += billRow - 1; //�����ȷ��������ѭ��������������ѭ��
      } else {
        commList.add(list.get(i));
      }
    }
    return commList;
  }
  
}