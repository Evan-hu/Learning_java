package com.ga.click.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.click.control.AbstractControl;
import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.DataForm;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class FormLayout extends AbstractLayout{
	
  private static final long serialVersionUID = 1L;
  private String title ="";
  private List<DbField> dbFieldList = null;
  private DataForm dataForm = null;
  private Map<String,Integer> cols = new LinkedHashMap<String,Integer>(); //key:group,v:列数
  private Map<String,List<DbField>> groupFieldList = new LinkedHashMap<String,List<DbField>>(); //k:gourp,v:字段列表
  private Map<String,AbstractControl> specCtrlMap = new LinkedHashMap<String,AbstractControl>(); ;
  private Set<String> hiddenGroupSet = new HashSet<String>();
  
  private  Map<String,Map<String,CellInfo>> cellMap = new LinkedHashMap<String,Map<String,CellInfo>>();
  private Map<String,List<SpecCell>> specCellList = new LinkedHashMap<String,List<SpecCell>>(); //key:字段s;v:行.列
  
  public DbField getField(String fieldCode) {
    for (DbField field : dbFieldList) {
      if (field.getFieldCode().equals(fieldCode)) return field;
    }
    return null;
  }
  
  public FormLayout(String title,DataForm dataForm,int cols) {
    this.title = title;
    this.dbFieldList = dataForm.getDbField();
    this.dataForm = dataForm;
    this.cols.put("auto",cols);
    this.setLayoutH(0);
  }
  
  public FormLayout(String title,DataForm dataForm,int cols,int editMode) {
    this.title = title;
    this.dbFieldList = new ArrayList<DbField>();
    for (DbField field : dataForm.getDbField()) {
      if (field.getInputMode(editMode) != GaConstant.INPUTMODE_HIDDEN) {
        this.dbFieldList.add(field);
      }
    }
    this.dataForm = dataForm;
    this.cols.put("auto",cols);
    this.setLayoutH(0);
  }
  
  public void attachSpecControl(String groupName,AbstractControl uploadCtrl) {
    if (GaUtil.isNullStr(groupName)) groupName = "auto";
    this.specCtrlMap.put(groupName,uploadCtrl);
  }
  
  /**
   * 让指定分组最后输出
   * @param groupName 分组名
   */
  public void mvGroupToLast(String groupName) {
    if (GaUtil.isNullStr(groupName)) groupName = "auto";
    List<DbField> fieldList = this.groupFieldList.get(groupName);
    if (fieldList != null) {
      this.groupFieldList.remove(groupName);
      this.groupFieldList.put(groupName, fieldList);
    }
  }
  /**
   * 设置字段分组,多个字段用,号分隔(不同组在显示上会进行区分)
   * @param groupName
   * @param fields
   * @param cols
   */
  public void setGroupFields(String groupName,String fields,int cols) {
    String[] getList = fields.split(",");
    List<DbField> dbFieldList = this.groupFieldList.get(groupName);
    if (dbFieldList == null) {
      dbFieldList = new ArrayList();
      this.groupFieldList.put(groupName, dbFieldList);
    }
    for(String fieldName : getList) {
      DbField field = this.getField(fieldName);
      if (field != null) {
        dbFieldList.add(field);
      } else {
        throw new BizException(BizException.SYSTEM,"无效字段名:"+fields);
      }
    }
    this.cols.put(groupName,cols);
  }
  
  /**
   * 设置组隐藏
   * @param groupName
   */
  public void setGroupHidden(String groupName) {
    this.hiddenGroupSet.add(groupName);
  }
  
  /**
   * 拆分单元格
   * 注意所有设置的行列好均以为拆分和合并的原始表格为准。
   * @param groupName 字段组名(如未分组则组名设为"auto")
   * @param row 行号(以0开始)
   * @param col 列号(以0开始)
   * @param fields 字段名
   */
  public void splitCellField(String groupName,int row,int col,String fields) {
    List<SpecCell> cellList = this.specCellList.get(groupName);
    if (cellList == null) {
      cellList = new  ArrayList<SpecCell>();
      this.specCellList.put(groupName,cellList);
    }
    SpecCell specCell = new SpecCell(row,col,fields);
    cellList.add(specCell);
  }  
  
  /**
   * 合并单元格
   * 注意所有设置的行列好均以为拆分和合并的原始表格为准。
   * @param groupName 字段组名(如未分组则组名设为"auto")
   * @param row 行号(以0开始)
   * @param startCols 起始列号(以0开始)
   * @param endCols 截止列号(以0开始)
   * @param field 字段名
   */
  public void mergeCellField(String groupName,int row,int startCols,int endCols,String field) {
    List<SpecCell> cellList = this.specCellList.get(groupName);
    if (cellList == null) {
      cellList = new  ArrayList<SpecCell>();
      this.specCellList.put(groupName,cellList);
    }
    SpecCell specCell = new SpecCell(row,startCols,endCols,field);
    cellList.add(specCell);
    //加入分组字段
    List<DbField> dbFieldList = this.groupFieldList.get(groupName);
    if (dbFieldList == null) {
      dbFieldList = new ArrayList();
      this.groupFieldList.put(groupName, dbFieldList);
    }
    DbField dbField = this.getField(field);
    if (field != null) {
      dbFieldList.add(dbField);
    } else {
      throw new BizException(BizException.SYSTEM,"无效字段名");
    }
  }
  /**
   * 执行自动分组
   */
  private void autoGroup() {
    //删除隐藏字段
    Iterator<DbField> it=dbFieldList.iterator();      
    while(it.hasNext()){
      DbField field=it.next();
      Field getField = this.dataForm.getField(field.getFieldCode());
      if (getField ==null || getField.isHidden()) {
        it.remove();
      }
    }
    if (groupFieldList.keySet().size() == 0) {
      this.groupFieldList.put("auto", this.dbFieldList);
    } else {      
      //将剩下的字段分组
      Map<String,DbField> fieldMap = new LinkedHashMap<String,DbField>();
      for(DbField field : this.dbFieldList) {
        fieldMap.put(field.getFieldCode(),field);
      }
      //移除已分组的字段
      for (String key : this.groupFieldList.keySet()) {
        List<DbField> fieldList = this.groupFieldList.get(key);
        it=fieldList.iterator();
        while(it.hasNext()){
          DbField field=it.next();
          Field getField = this.dataForm.getField(field.getFieldCode());
          if (getField ==null || getField.isHidden()) {
            //移除隐藏控件
            it.remove();
          }
          if (fieldMap.get(field.getFieldCode()) != null) {
            fieldMap.remove(field.getFieldCode());
          }
        }
      }
      //剩余的自动分组
      if (fieldMap.size() > 0) {
        DbField[] fields = new DbField[1];
        this.groupFieldList.put("auto",Arrays.asList(fieldMap.values().toArray(fields)));
      }
    }
  }
  
  private void autoLayout() {
    int row =0;int col=0;
    for (String groupName : this.groupFieldList.keySet()) {
      List<DbField> fieldList = new ArrayList<DbField>();
      fieldList.addAll(this.groupFieldList.get(groupName));        
        //根据默认列设置按顺序处理field,先处理常规字段
      int maxCol = this.cols.get(groupName); //当前组的常规列数
      int allField = fieldList.size();
      //处理合并的单元格
      List<SpecCell> chkMergeList = this.getMergeCell(groupName);
      if (chkMergeList != null) {
        for(SpecCell cell : chkMergeList) {
          int start = cell.getCol();
          int end = cell.getEndCol(); 
          allField = allField + end -start + 1;//有疑问 ？
        }
      }
      int maxRow = (allField -1 )/ maxCol + 1;
      Map<String,CellInfo> groupCellMap = new HashMap<String,CellInfo>();
      for (int i=0;i<maxRow; i ++) {
        for (int j =0;j<maxCol;j++) {
          //处理特殊单元格       
          while (!this.checkCell(groupName,i, j, fieldList,groupCellMap)) {
            j ++;
            if (j>=maxCol) {
              i ++;
              j = 0;
            }
          }
          if (i>=maxRow) {
            break;
          }
          //普通单元格
          if (fieldList.size() > 0) {
            //排除特殊字段
            Iterator<DbField> it=fieldList.iterator();
            DbField rmField = null;
            while(it.hasNext()){
              DbField field=it.next();
              if (!this.fieldIsSpec(groupName,field.getFieldCode())) {   
                CellInfo cell = new CellInfo(field);
                String point = i + ","+j; //行列坐标
                groupCellMap.put(point,cell);
                rmField = field;
                break;
              }
            }
            if (rmField == null) {
              String point = i + ","+j; //行列坐标
              CellInfo cell = new CellInfo();
              groupCellMap.put(point,cell);
              fieldList.remove(0);
            } else {
              fieldList.remove(rmField);
            }
          } else {
            String point = i + ","+j; //行列坐标
            CellInfo cell = new CellInfo();
            groupCellMap.put(point,cell);
          }
        }
      }
      //处理单元格拆分
      for (int i=0;i<maxCol;i++) {
        int spliteCount = 1;
        //取最大值
        for (int j=0;j<maxRow;j++) {
          String key = j+"," + i;
          CellInfo cell = groupCellMap.get(key);
          if (cell!= null && cell.getCellType() == 2) {
            int tmpv = cell.getSubCell().size();
            if (spliteCount < tmpv) {
              spliteCount = tmpv;
            }
          }
        }
        //设置cellspan
        for (int j=0;j<maxRow;j++) {
          String key = j+"," + i;
          CellInfo cell = groupCellMap.get(key);
          if (cell != null) {
            cell.setColSpan(spliteCount);
            if (cell.getCellType() == 2) {
              int tmpv = cell.getSubCell().size();
              int fristSpan = spliteCount - tmpv;
              if (fristSpan > 0) {
                cell.getSubCell().get(0).setColSpan(fristSpan + 1);
              }
            }
          }
        }
      }
      //处理合并单元格
      List<SpecCell> mergeList = this.getMergeCell(groupName);
      if (mergeList != null) {
        for(SpecCell cell : mergeList) {
          int start = cell.getCol();
          int end = cell.getEndCol();
          int getRow = cell.getRow();
          //合计colspan
          int allCol = 0;
          for (int i= start ; i<=end ;i++) {
            String key = getRow + "," + i;
            CellInfo cellInfo =  groupCellMap.get(key);
            if (  cellInfo != null) {
              allCol = allCol + cellInfo.getColSpan();
            }
          }
          //取出第一个单元格设置
          CellInfo cellInfo = groupCellMap.get(getRow + "," + start);
          if (cellInfo != null) {
            cellInfo.setColSpan(allCol);
          }
        }
      }
      //放入group
      this.cellMap.put(groupName, groupCellMap);     
    }
  }
  
//  private void autoLayout() {
//    //计算当前的cell数组
//    int row =0;int col=0;
//    for (String groupName : this.groupFieldList.keySet()) {
//      List<DbField> fieldList = this.groupFieldList.get(groupName);
//        //根据默认列设置按顺序处理field,先处理常规字段
//      int maxCol = this.cols.get(groupName); //当前组的常规列数
//      int maxRow = 0;
//      Map<String,CellInfo> groupCellMap = new HashMap<String,CellInfo>();
//      Map<String,String> removeSpecField = new HashMap<String,String>();
//      Iterator<DbField> it=fieldList.iterator();      
//      while(it.hasNext()){
//        DbField field=it.next();
//        //检查当前单元格是否特殊单元格
//        if (removeSpecField.get(field.getFieldCode()) != null) continue;
//        if (!this.fieldIsSpec(groupName,field.getFieldCode())) {          
//          while(!this.checkCell(groupName,row, col, fieldList,groupCellMap,removeSpecField)) {
//            //只有找到普通单元格才结束循环
//            col ++;
//            if (col>=maxCol) {
//              row ++;
//              col = 0;
//            }
//          }
//          //普通单元格
//          maxRow = row;
//          CellInfo cell = new CellInfo(field);
//          String point = row + ","+col; //行列坐标
//          groupCellMap.put(point,cell);
//          col ++;
//          if (col>=maxCol) {
//            row ++;
//            col =0;
//          }
//        }             
//      } 
//      maxRow ++;
//      //设置了所有单元格的值，计算colspan信息
//      //处理单元格拆分
//      for (int i=0;i<maxCol;i++) {
//        int spliteCount = 1;
//        //取最大值
//        for (int j=0;j<maxRow;j++) {
//          String key = j+"," + i;
//          CellInfo cell = groupCellMap.get(key);
//          if (cell!= null && cell.getCellType() == 2) {
//            int tmpv = cell.getSubCell().size();
//            if (spliteCount < tmpv) {
//              spliteCount = tmpv;
//            }
//          }
//        }
//        //设置cellspan
//        for (int j=0;j<maxRow;j++) {
//          String key = j+"," + i;
//          CellInfo cell = groupCellMap.get(key);
//          if (cell != null) {
//            cell.setColSpan(spliteCount);
//            if (cell.getCellType() == 2) {
//              int tmpv = cell.getSubCell().size();
//              int fristSpan = spliteCount - tmpv;
//              if (fristSpan > 0) {
//                cell.getSubCell().get(0).setColSpan(fristSpan + 1);
//              }
//            }
//          }
//        }
//      }
//      //处理合并单元格
//      List<SpecCell> mergeList = this.getMergeCell(groupName);
//      if (mergeList != null) {
//        for(SpecCell cell : mergeList) {
//          int start = cell.getCol();
//          int end = cell.getEndCol();
//          int getRow = cell.getRow();
//          //合计colspan
//          int allCol = 0;
//          for (int i= start ; i<=end ;i++) {
//            String key = getRow + "," + i;
//            CellInfo cellInfo =  groupCellMap.get(key);
//            if (  cellInfo != null) {
//              allCol = allCol + cellInfo.getColSpan();
//            }
//          }
//          //取出第一个单元格设置
//          CellInfo cellInfo = groupCellMap.get(getRow + "," + start);
//          if (cellInfo != null) {
//            cellInfo.setColSpan(allCol);
//          }
//
//        }
//      }
//      //放入group
//      this.cellMap.put(groupName, groupCellMap);
//    }
//  }
  
  /**
   * 检查和处理特殊规格单元格
   * 1.拆分单元格,将拆分字段作为list放入单元格
   * 2.合并单元格,除开始列外，将空对象放入单元格
   * @param row
   * @param col
   * @param fieldList
   * @return 是否普通单元格
   */
  private boolean checkCell(String groupName,int row ,int col,List<DbField> fieldList,Map<String,CellInfo> groupCellMap) {
    SpecCell specCell = this.getSpecCell(groupName,row, col);
    if (specCell != null) {
      if (specCell.getEndCol() == 0) {
        //拆分单元格,创建字段列表，并将相应字段移除
        String splitField = specCell.getFieldCode();
        String[] splitFields = splitField.split(",");
        List<DbField> tmpList = new ArrayList<DbField>();
        for(int i=0;i<splitFields.length;i++) {
          DbField dbField = this.getField(splitFields[i]);
          tmpList.add(dbField);
          fieldList.remove(dbField);
        }
        CellInfo cell = new CellInfo(tmpList);
        String point = row + ","+col; //行列坐标
        groupCellMap.put(point,cell); 
        return false;
      } else {
        //合并单元格
        String mergeField = specCell.getFieldCode();
        if (GaUtil.isNullStr(mergeField) || col != specCell.getCol()) {
          String point = row + ","+col; //被和并列
          CellInfo cell = new CellInfo();
          groupCellMap.put(point,cell);   
        } else {
          DbField dbField = this.getField(mergeField);
          String point = row + ","+col; //行列坐标
          CellInfo cell = new CellInfo(dbField);
          groupCellMap.put(point, cell);            
          fieldList.remove(dbField);
        }
        return false;
      } 
    } else {
      return true;
    }    
  }
  
  
  private SpecCell getSpecCell(String groupName,int row,int col) {
    List<SpecCell> cellList = this.specCellList.get(groupName);
    if (cellList == null) return null;    
    for (SpecCell cell : cellList) {
      if (cell.getRow() == row && (cell.getCol() <= col && cell.getEndCol() >= col)) {
        return cell;
      }
    }
    return null;
  }
  
  private List<SpecCell> getMergeCell(String groupName) {
    List<SpecCell> cellList = this.specCellList.get(groupName);
    if (cellList == null) return null;    
    List<SpecCell> getList = new ArrayList<SpecCell>();
    for (SpecCell cell : cellList) {
      if (cell.getEndCol() > 0) {
        getList.add(cell);
      }
    }
    return getList;
  }
  
  /**
   * 字段是否特殊字段
   * @param fieldCode
   * @return
   */
  private boolean fieldIsSpec(String groupName,String fieldCode) {
    List<SpecCell> cellList = this.specCellList.get(groupName);
    if (cellList == null) return false;
    for(SpecCell cell : cellList) {
      if (cell.isSpec(fieldCode)) {
        return true;
      }
    }
    return false;
  }
  
  
  //执行渲染替换FORM控件本身的渲染
  public void renderMain(HtmlStringBuffer buff) {
    try {
      this.autoGroup(); //自动分组
      this.autoLayout(); //执行布局计算
    } catch(Exception e) {
     throw new BizException(BizException.SYSTEM,"布局定义不合法"); 
    }

    //buff.append("<div class=\"pageContent\" layoutH=\"0\">\r\n");
    buff.append("  <div class=\"tableform1\">\r\n");
    if (!GaUtil.isNullStr(this.title)) {
      buff.append("  <h4>").append(this.title).append("</h4>\r\n");      
    }
    for(String groupName : groupFieldList.keySet()) {
      buff.append("    <div class=\"division\" id=\"").append(groupName).append("\"");
      if (this.hiddenGroupSet.contains(groupName)) {
        buff.append(" style=\"display:none;\"");
      }
      buff.append(">\r\n");
      buff.append("       <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n");
      buff.append("          <tbody>");        
      if (GaUtil.isNullStr(groupName)) groupName = "auto";
      List<DbField> fieldList = groupFieldList.get(groupName);
      int[] cells = getCell(groupName);
      boolean renderSpecCtrl = false;
      if (this.specCtrlMap.size() > 0 && this.specCtrlMap.get(groupName) != null) renderSpecCtrl = true;
      for (int i=0;i<=cells[0];i++) {
        //输出行
        buff.append("          <tr>\r\n");
        for (int j=0;j<=cells[1];j++) {
        //输出列  
          CellInfo getCell = this.getCell(groupName, i, j);
          if (getCell == null) continue;
          List<CellInfo> checkCellList = new ArrayList<CellInfo>();
          if (getCell.getCellType() == 1) {
            checkCellList.add(getCell);
          } else if (getCell.getCellType() == 2) {
            checkCellList.addAll(getCell.getSubCell());
          } else {
            checkCellList = null;
          }
          if (checkCellList != null) {
            for (CellInfo cell :checkCellList) {
              if (cell.getField() != null) {
            	 //if (cell.getField().getFieldVerify().isRequire()) {
            	//	 buff.append("            <th>").append(cell.getField().getFieldName()).append("<font color='red'>*</font></th>\r\n");
            	// } else {
            		 buff.append("            <th>").append(cell.getField().getFieldName()).append("</th>\r\n");
            	 //}
                if (cell.getColSpan() > 1) {
                  buff.append("            <td colspan=\"").append(cell.getColSpan() * 2 -1).append("\">");
                } else {
                  if (renderSpecCtrl) {
                    buff.append("            <td >");
                  } else 
                  {
                    buff.append("            <td>");
                  }
                }                            
                if (cell.getField().getColumnDecorator() != null) {
                 //装饰输出
                  if (cell.getField().getColumnDecorator().isAdd()) {
                    this.dataForm.getControl(cell.getField().getFieldCode()).render(buff);
                  }
                  String value = this.dataForm.getField(cell.getField().getFieldCode()).getValue();
                  buff.append(cell.getField().getColumnDecorator().decorator(value));
                } else {
                  //利用控件输出
                  this.dataForm.getControl(cell.getField().getFieldCode()).render(buff);
                  //输出提示信息
                  if (!GaUtil.isNullStr(cell.getField().getTipInfo())) {
                    buff.append(cell.getField().getTipInfo());
                  }
                }
                buff.append("</td>\r\n");
              }
            }
          }
        }
        //当第一行输出后，判断是否需输出特殊控件
        if (renderSpecCtrl) {
          this.renderSpecCtrl(buff,this.specCtrlMap.get(groupName), cells[0]);
          renderSpecCtrl = false;
        }
        buff.append("          </tr>\r\n");
      }
      buff.append("         <tbody>\r\n");
      buff.append("      </table>\r\n");
      buff.append("    </div>\r\n");
    }
    buff.append("  </div>\r\n");
    //buff.append("</div>\r\n");
  }
  
  private void renderSpecCtrl(HtmlStringBuffer buff,AbstractControl specCtrl,int rows) {
    buff.append("      <td rowspan=\"").append(rows + 1).append("\" ");
    if (specCtrl.getAttributes() != null) {
      for(String key : specCtrl.getAttributes().keySet()) {
        String value =  specCtrl.getAttribute(key);
        buff.append(key).append("=\"").append(value).append("\" ");
      }
    }
    buff.append(">\r\n");
    buff.append("<div id=\"photoUpload\">");
    specCtrl.render(buff);
    buff.append("</div>");
    buff.append("      </td>\r\n");
  }
  
  public int[] getCell(String groupName) {
     Map<String,CellInfo> groupCellMap = this.cellMap.get(groupName);
     int maxRow = 0;
     for (String key : groupCellMap.keySet()) {
       String[] pos = key.split(",");
       int row = Integer.parseInt(pos[0]);
       if (row > maxRow) maxRow = row;
     }
     int maxCol = this.cols.get(groupName);
     return new int[]{maxRow,maxCol - 1};
  }
  
  public CellInfo getCell(String groupName,int row,int col) {
    String key = row + "," + col;
    return this.cellMap.get(groupName).get(key);
  }
  
  /**
   * 单元格信息
   * @author Administrator
   *
   */
  public class CellInfo{ 
    private DbField field = null;
    private int colWidth = 0;
    private int colSpan = 1; 
    private List<CellInfo> subCell = null;
    
    /**
     * 构建空单元格(合并的剩余单元格)
     */
    public CellInfo() {
      
    }
    
    /**
     * 构建普通字段单元格
     * @param field
     */
    public CellInfo(DbField field) {
      this.field = field;
    }
    
    /**
     * 构建拆分单元格
     * @param fieldList
     */
    public CellInfo(List<DbField> fieldList) {
      subCell = new ArrayList<CellInfo>();
      for(DbField field :fieldList) {
        subCell.add(new CellInfo(field));
      }
    }
    
    public int getCellType() {
      if (this.field != null) {
        return 1; //普通单元格
      } else if (subCell != null) {
        return 2; //拆分单元格
      } else {
        return 3; //空单元格
      }
    }

    public DbField getField() {
      return field;
    }
    
    public int getColWidth() {
      return colWidth;
    }
    
    public int getColSpan() {
      return colSpan;
    }
    
    public void setColSpan(int colSpan) {
      this.colSpan = colSpan;
    }

    public List<CellInfo> getSubCell() {
      return subCell;
    }
  }
  
  public class SpecCell {
    private String fieldCode;
    private int row = 0; 
    private int col = 0;
    private int endCol = 0;
    private String checkCode;
    
    public boolean isSpec(String fieldCode) {
      if (checkCode.indexOf(","+fieldCode+",") > -1) {
        return true;
      } else {
        return false;
      }
   }
    public SpecCell (int row,int col,String fields ) {
      this.row = row;
      this.col = col;
      this.fieldCode = fields ;
      checkCode =  ","+fields+",";
    }
    public SpecCell (int row,int col,int endCol,String fields ) {
      this.row = row;
      this.col = col;
      this.endCol = endCol;
      this.fieldCode = fields;
      checkCode = ","+fields+",";
    }
    public String getFieldCode() {
      return fieldCode;
    }
    public int getRow() {
      return row;
    }
    public int getCol() {
      return col;
    }
    public int getEndCol() {
      return endCol;
    }    
  }
  
  /**
   * 渲染按钮区域
   */
  public void renderButton(HtmlStringBuffer buff) {
    if (this.dataForm.getActionButtonList() != null) {
      buff.append("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" class=\"tableAction\">\r\n");
      buff.append("<tbody><tr>\r\n");

      for (ActionButton button : this.dataForm.getActionButtonList()) {    
          buff.append("<td><b class=\"submitBtn\">");
          button.render(buff);
          buff.append("</b></td>");
      }
      buff.append("</tr></tbody>\n");
      buff.append("</table>\n");
  }
  }
}
 
