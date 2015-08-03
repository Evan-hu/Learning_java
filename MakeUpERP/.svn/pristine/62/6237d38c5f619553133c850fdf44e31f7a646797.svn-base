package com.ga.click.control.table;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.Context;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import org.json.JSONObject;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.DataPropertyUtils;
import com.ga.click.util.ExcelExport;
import com.ga.click.util.FileUploadUtil;
import com.ga.click.util.GaUtil;

public class ListTable extends AbstractControl {

	private static final long serialVersionUID = 1L;
	private Map<String, List<DbField>> dispFieldMap = null;
	private Map<String, DbField> allFieldList = new HashMap<String, DbField>();
	private DbField pkField = null;
	private String rowSelect;
	private ResultSet resultSet = null;
	private List<Map<String, Object>> resultList = null;
	private int editMode = GaConstant.EDITMODE_DISP;
	private String tableID = "";
	private boolean multiSelect = false;
	private boolean dispDeleRow = true;
	private String mouseOverJS = "";
	private boolean isMTableMode = false; //是否主从表模式

	private int tableWidth = 0; // 当前宽度
	private int tableMaxWidth = 1080; // 最大表格宽为1000
	
	
	private boolean isExport = false;

	public ListTable(String tableID, List<DbField> dbFieldList) {
		this(tableID, dbFieldList, GaConstant.EDITMODE_DISP);
	}

	public void setMultiSelect(boolean isMultiSelect) {
		multiSelect = isMultiSelect;
	}

	public void setTableMaxWidth(int tableMaxWidth) {
		this.tableMaxWidth = tableMaxWidth;
	}

	public void setMouseOverJS(String js) {
		this.mouseOverJS = js;
	}

	public boolean isExport() {
		return isExport;
	}

	public void setExport(boolean isExport) {
		this.isExport = isExport;
	}

	/**
	 * 设置是否显示删除行
	 * 
	 * @param dispDeleRow
	 */
	public void setDispDeleRow(boolean dispDeleRow) {
		this.dispDeleRow = dispDeleRow;
	}

	public ListTable(String tableID, List<DbField> dbFieldList, int editMode) {
		this.setId(tableID);
		this.tableID = tableID;
		boolean haveWidth = false;
		if (dbFieldList != null) {
			this.dispFieldMap = new LinkedHashMap<String, List<DbField>>();
			for (DbField field : dbFieldList) {
				if (field.isPK())
					this.pkField = field;
				if (field.isDisplay()) {
					String mainFieldCode = field.getFieldCode();
					if (!GaUtil.isNullStr(field.getColumnMergeCode())) {
						mainFieldCode = field.getColumnMergeCode();
					}
					List<DbField> getDbList = this.dispFieldMap.get(mainFieldCode);
					if (getDbList == null) {
						getDbList = new ArrayList<DbField>();
						this.dispFieldMap.put(mainFieldCode, getDbList);
					}
					getDbList.add(field);
					// 计算表格宽度
					this.tableWidth = field.getColumnWidth() + this.tableWidth;
				}
				this.allFieldList.put(field.getFieldCode(), field);
			}
		}
		this.editMode = editMode;
	}

	/**
	 * 设置显示的结果记录集
	 * 
	 * @param rs
	 *            查询结果集
	 */
	public void setDataSource(PageResult rs) {
		if (rs != null) {
			if (rs.getDataType() == 1) {
				this.resultList = rs.getListData();
			} else {
				this.resultSet = rs.getData();
			}
		}
	}

	public void addActionColumn() {

	}

	public void setColumnAction() {

	}

	public void setRowSelect(String rowSelectJS) {
		this.rowSelect = rowSelectJS;
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return "table";
	}

	/**
	 * 生成table显示HTML 包括header, body等
	 */
	@Override
	public void render(HtmlStringBuffer buffer) {
		if (this.isExport) {
			this.export(buffer);
			return;
		}
		// Render table start.
		if (this.editMode != GaConstant.EDITMODE_DISP  || this.isMTableMode) {
			// buffer.appendAttribute("addButton", "添加行");
			buffer.append("<form id=\"").append(this.tableID).append("\" name=\"").append(this.tableID).append("\" method=\"post\">");
		}

		buffer.elementStart(getTag());
		buffer.appendAttribute("id", getId());
		// 计算是否自动宽度
		if (this.editMode == GaConstant.EDITMODE_DISP) {
			if (this.tableWidth < this.tableMaxWidth) {
				buffer.appendAttribute("width", "100%");
			} else {
				buffer.appendAttribute("width", this.tableWidth + "px");
			}
		} else {
			buffer.appendAttribute("width", this.tableWidth + "px");
		}

		if (this.editMode != GaConstant.EDITMODE_DISP) {
			// buffer.appendAttribute("addButton", "添加行");
			buffer.appendAttribute("class", "list nowrap itemDetail");
		} else {
			buffer.appendAttribute("class", "list");
		}
		buffer.closeTag();
		buffer.append("\n");
		//输出PK字段
		if (this.isMTableMode) {
			for(DbField  field : this.allFieldList.values()) {
				if (field.isPK()) {
					buffer.append(" <input type ='hidden' name='pkfield' id='pkfield' value='")
						.append(field.getFieldCode())
						.append("' />");
					buffer.append(" <input type ='hidden' name='mtable_del' id='mtable_del' value=''");
				}
			}
		}
		// 生成表头相关数据
		renderHeader(buffer);
		// 生成表body相关数据
		renderBodyRows(buffer);
		// Render table end.
		buffer.elementEnd(getTag());
		if (this.editMode != GaConstant.EDITMODE_DISP  || this.isMTableMode) {
			buffer.append("</form>");
		}
		buffer.append("\n");
	}

	/**
	 * 执行数据导出处理
	 * 
	 * @param buffer
	 */
	public void export(HtmlStringBuffer buffer) {
		try {
			// 创建导出文件
			SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd.HHmm.sssss");
			String name = "/tempUpload/" + formatter.format(new Date()) + ".xlsx";
			String filePath = FileUploadUtil.getSiteRoot() + name;
			ExcelExport e = new ExcelExport(filePath);
			// 表头;
			int col = 0;
			int row = 0;
			e.createRow(row);
			row++;
			List<String> exportFieldList = new ArrayList<String>();
			DbField field = null;
			for (String getFieldCode : this.dispFieldMap.keySet()) {
				exportFieldList.add(getFieldCode);
				e.setCell(col, this.allFieldList.get(getFieldCode).getFieldName());
				col++;
				// 解析联合字段
				field = this.allFieldList.get(getFieldCode);
				if (field.getColumnDecorator() != null) {
					if (field.getColumnDecorator().getLinkeFieldCode() != null) {
						for (String linkCode : field.getColumnDecorator().getLinkeFieldCode()) {
							DbField linkField = this.allFieldList.get(linkCode);
							if (linkField != null) {
								exportFieldList.add(linkCode);
								e.setCell(col, linkField.getFieldName());
								col++;
							}
						}
					}
				}
			}
			// 数据导出
			if (resultSet != null) {
				// 处理resultset数据
				for (int i = 0; resultSet.next(); ++i) {
					// 设置行绑定的字段
					e.createRow(row);
					col = 0;
					for (String fieldCode : exportFieldList) {
						Object columnValue = ClickUtil.getRowData(fieldCode, resultSet);
						String value = ClickUtil.getDisplayValue(this.allFieldList.get(fieldCode), columnValue);
						e.setCell(col, value);
						col++;
					}
					row++;
				}
			} else if (this.resultList != null) {
				for (Map<String, Object> rowdata : this.resultList) {
					// 设置行绑定的字段
					e.createRow(row);
					col = 0;
					for (String fieldCode : exportFieldList) {
						field = this.allFieldList.get(fieldCode);
						Object columnValue = ClickUtil.getRowData(field.getFieldCode(false), rowdata);
						String value = ClickUtil.getDisplayValue(this.allFieldList.get(fieldCode), columnValue);
						if (value.startsWith("<")) {
							value = GaUtil.HtmlText(value);
						}
						e.setCell(col, value);
						col++;
					}
					// System.out.println("导出行数:"+row);
					row++;
				}
			}
			e.exportXLS();
			throw new BizException("导出完成:" + name);
		} catch (BizException e) {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BizException("执行导出异常");
		}
	}

	/**
	 * 渲染表格列头
	 * 
	 * @param buffer
	 */
	private void renderHeader(HtmlStringBuffer buffer) {
		buffer.append("<thead align=\"center\">\n  <tr>\n  ");
		if (this.editMode != GaConstant.EDITMODE_DISP && dispDeleRow) {
			buffer.append("<th type=\"del\" width=\"60px\">操作</th>");
		} else {
			if (this.multiSelect) {
				buffer.append("<th width=\"22px\"><input type=\"checkbox\" group=\"_ids\" class=\"checkboxCtrl\"></th>");
			}
		}
		String divId = this.getId().substring(0, this.getId().length() - 4);
		boolean noSort = true;
		for (String fieldCode : this.dispFieldMap.keySet()) {
			DbField field = this.allFieldList.get(fieldCode);
			if (field.isDisplay()) {
				buffer.append("    <th id= \"" + divId + "_th_" + field.getFieldCode(false) + "\" style=\"cursor:pointer; \"");
				if (buffer.toString().indexOf("tabsContent") < 0 && buffer.toString().indexOf("<div><div id=") < 0) {
					noSort = false;
					buffer.appendAttribute("onclick", "doFormDescPost('" + divId + "','" + field.getFieldCode(false) + "','DESC')");// 增加排序图标和事件
				}
				if (field.isPK()) {
					buffer.append(" pk='1' ");
				}
				if (field.getColumnWidth() > 0) {
					buffer.appendAttribute("width", field.getColumnWidth() + "px");
				} else if (field.getColumnWidth() == -1) {
					buffer.append(" style=\"display:none;\"");
				}
				if (field.getDefaultValue() != null) {
					buffer.appendAttribute("defaultval", field.getDefaultValue());
				}
				if (this.editMode != GaConstant.EDITMODE_DISP) {
					// 列编辑状态
					if (field.getColumnWidth() == -1) {
						buffer.appendAttribute("size", "display:none");
					} else {
						buffer.appendAttribute("size", field.getInputCustomStyle());
					}
					// 看是否图片显示
					if (field.getColumnDecorator() != null && field.getColumnDecorator().isImageDecorator()) {
						String style = " width=" + field.getColumnDecorator().getImgWidth() + "px";
						style = style + " height=" + field.getColumnDecorator().getImgHeight() + "px";
						buffer.appendAttribute("type", "attach");
						buffer.appendAttribute("lookupPk", style);
					}
					int inputMode = field.getInputMode(editMode);
					if (inputMode == GaConstant.INPUTMODE_NORMAL) {
						// 有编辑控件设置
						switch (field.getInputType()) {
						case GaConstant.INPUT_DATETIME:
							buffer.appendAttribute("type", "date");
							buffer.appendAttribute("format", field.getFormatPattern());
							buffer.appendAttribute("name", "items[#index#]." + field.getFieldCode());
							break;
						case GaConstant.INPUT_SELECT:
							buffer.appendAttribute("type", "select");
							buffer.appendAttribute("name", "items[#index#]." + field.getFieldCode());
							String keyList = "";
							String vList = "";
							for (String key : field.getLookupData().getKeyList()) {
								Object v = field.getLookupData().find(key);
								if (v != null) {
									keyList = keyList + "," + key;
									vList = vList + "," + v.toString();
								}
							}
							keyList = keyList.substring(1);
							vList = vList.substring(1);
							buffer.appendAttribute("postField", keyList);
							buffer.appendAttribute("suggestFields", vList);
							break;
						case GaConstant.INPUT_POPSELECT:
							String linkchar = "?";
							if (!GaUtil.isNullStr(field.getPopPageUrl())) {
								if (field.getPopPageUrl().indexOf("?") > 0) {
									linkchar = "&";
								}
								String lookupUrl = field.getPopPageUrl() + linkchar + "lookupMode=" + field.getPopSelectFields();
								buffer.appendAttribute("type", "lookup");
								buffer.appendAttribute("lookupGroup", "items[#index#]." + field.getPopID());
								buffer.appendAttribute("lookupUrl", lookupUrl);
								buffer.appendAttribute("name", "items[#index#]." + field.getPopID() + "." + field.getPopBindField());
							} else {
								if (field.getColumnDecorator() == null || !field.getColumnDecorator().isImageDecorator()) {
									buffer.appendAttribute("type", "text");
								}
								buffer.appendAttribute("lookupGroup", "items[#index#]." + field.getPopID());
								buffer.appendAttribute("name", "items[#index#]." + field.getPopID() + "." + field.getPopBindField());
							}
							break;
						default:
							buffer.appendAttribute("type", "text");
							buffer.appendAttribute("name", "items[#index#]." + field.getFieldCode());
						}
					} else {
						switch (field.getInputType()) {
						case GaConstant.INPUT_POPSELECT:
							String linkchar = "?";
							if (!GaUtil.isNullStr(field.getPopPageUrl())) {
								if (field.getPopPageUrl().indexOf("?") > 0) {
									linkchar = "&";
								}
								String lookupUrl = field.getPopPageUrl() + linkchar + "lookupMode=" + field.getPopSelectFields();
								buffer.appendAttribute("type", "lookup");
								buffer.appendAttribute("fieldClass", "readonly");
								buffer.appendAttribute("lookupGroup", "items[#index#]." + field.getPopID());
								buffer.appendAttribute("lookupUrl", lookupUrl);
								buffer.appendAttribute("name", "items[#index#]." + field.getPopID() + "." + field.getPopBindField());
							} else {
								buffer.appendAttribute("type", "text");
								buffer.appendAttribute("fieldClass", "readonly");
								buffer.appendAttribute("lookupGroup", "items[#index#]." + field.getPopID());
								buffer.appendAttribute("name", "items[#index#]." + field.getPopID() + "." + field.getPopBindField());
							}
							break;
						default:
							// 不允许编辑的输入控件
							buffer.appendAttribute("type", "hidden");
							buffer.appendAttribute("fieldClass", "readonly");
							buffer.appendAttribute("name", "items[#index#]." + field.getFieldCode());
						}
					}
				}
				buffer.append(">");
				buffer.append(field.getFieldName());
				if (!noSort) {
					buffer.append("&nbsp;");
					// 根据排序字段增加对应的提示图标
					buffer.append("&nbsp;<img src=\"/click/column-sortable-dark.gif\"/>");
				}
				buffer.append("</th>\n");
			}
		}
		buffer.append("  </tr>\n</thead>\n");
		buffer.append("<script>setListDescShow('" + divId + "','" + this.getId() + "');</script>");
	}

	/**
	 * 渲染行区域
	 * 
	 * @param buffer
	 */
	private void renderBodyRows(HtmlStringBuffer buffer) {
		// 生成绑定字段列表
		List<String> bindFieldCodeList = new ArrayList<String>();
		for (DbField dbField : this.allFieldList.values()) {
			if (dbField.isBindRowData()) {
				bindFieldCodeList.add(dbField.getFieldCode(false));
			}
		}
		buffer.append("<tbody>\n");
		// 遍历渲染行数
		if (resultSet != null) {
			// 处理resultset数据
			try {
				for (int i = 0; resultSet.next(); ++i) {
					// 设置行绑定的字段
					if (this.editMode == GaConstant.EDITMODE_DISP) {
						// 显示模式
						String rowSB = "";
						JSONObject rowJson = new JSONObject();
						if (bindFieldCodeList != null) {
							for (String str : bindFieldCodeList) {
								try {
									Object vo = resultSet.getObject(str);
									DbField chkField = this.allFieldList.get(str);
									if (chkField != null && vo != null && chkField.getDataType() == GaConstant.DT_MONEY) {
										// money需要换算一次
										BigDecimal money = (BigDecimal) vo;
										vo = money.divide(new BigDecimal(100));
									}
									if (vo != null) {
										String info = vo.toString();
										info = info.replace("'", "‘");
										rowJson.put(str, info);
									} else {
										rowJson.put(str, "");
									}
								} catch (Exception e) {
								}
							}
						}
						buffer.append("<tr rel=\"" + rowJson.toString().replace("\"", "'") + "\"");
						buffer.append("target=\"row_data\"");
						if (!GaUtil.isNullStr(this.rowSelect)) {
							buffer.append("rowselect=\"");
							buffer.append(this.rowSelect);
							buffer.append("\"");
						}
						if (!GaUtil.isNullStr(this.mouseOverJS)) {
							String[] js = this.mouseOverJS.split(",");
							if (js != null && js.length == 2) {
								buffer.append("in=\"").append(js[0]).append("\" out=\"").append(js[1]).append("\"");
							}
						}
						buffer.append(">");
						//
						
						
						renderBodyRowColumns(resultSet, buffer, i);
						buffer.append("</tr>\n");
					} else {
						// 显示编辑模式
						// if (this.editMode == NoahConstant.EDITMODE_EDIT) {
						buffer.append("<tr>");
						if (dispDeleRow) {
							buffer.append("<td><a class=\"btnDel\" href=\"javascript:void(0)\" onclick=\"").append("delTableRow(this);")
									.append("\">删除</a></td>");
						}
						renderBodyRowColumns(resultSet, buffer, i);
						buffer.append("</tr>\n");
						// }
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (this.resultList != null) {
			// 处理list数据
			try {
				int i = 0;
				for (Map<String, Object> row : this.resultList) {
					if (this.editMode == GaConstant.EDITMODE_DISP) {
						// 设置行绑定的字段
						String rowSB = "";
						JSONObject rowJson = new JSONObject();
						if (bindFieldCodeList != null) {
							for (String str : bindFieldCodeList) {
								try {
									Object vo = row.get(str);
									DbField chkField = this.allFieldList.get(str);
									if (chkField != null && vo != null && chkField.getDataType() == GaConstant.DT_MONEY) {
										// money需要换算一次
										BigDecimal money = (BigDecimal) vo;
										vo = money.divide(new BigDecimal(100));
									}
									if (vo != null) {
										String info = vo.toString();
										info = info.replace("'", "‘");
										rowJson.put(str, info);
									} else {
										rowJson.put(str, "");
									}
								} catch (Exception e) {
								}
							}
						}
						// else {
						// sb = getColumnList().get(0).getName() + "," +
						// getColumnList().get(0).getProperty(resultSet) + "||";
						// }
						buffer.append("<tr rel=\"" + rowJson.toString().replace("\"", "'") + "\"");
						buffer.append("target=\"row_data\"");
						if (!GaUtil.isNullStr(this.rowSelect)) {
							buffer.append("rowselect=\"");
							buffer.append(this.rowSelect);
							buffer.append("\"");
						}
						// if (!NoahUtil.isNullStr(this.mouseOverJS)) {
						// String[] js = this.mouseOverJS.split(",");
						// if (js != null && js.length == 2) {
						// buffer.append("in=\"").append(js[0])
						// .append("\" out=\"").append(js[1]).append("\"");
						// }
						// }
						buffer.append(">");
						if (this.isMTableMode) {
							//输出PK的值
							for(DbField chkField : this.allFieldList.values()) {
								String v = "";
								if (chkField.isPK()) {
									//取PK的值
									try {
										Object vo =  row.get(chkField.getFieldCode());										
										if (chkField != null && vo != null && chkField.getDataType() == GaConstant.DT_MONEY) {
											// money需要换算一次
											BigDecimal money = (BigDecimal) vo;
											vo = money.divide(new BigDecimal(100));
										}
										if (vo != null) {
											v= vo.toString();
										} 
									} catch (Exception e) {
									}
									String pk = " <input type='hidden' name='items["+i+"]."+chkField.getFieldCode()+"' pk='1'";
									pk = pk + " value='"+v+"' />";
									buffer.append(pk);
									break;
								}
							}
						}
						
						renderBodyRowColumns(row, buffer, i);
						buffer.append("</tr>\n");
					} else {
						// 显示编辑模式
						// if (this.editMode == NoahConstant.EDITMODE_EDIT) {
						buffer.append("<tr>");
						if (dispDeleRow) {
							buffer.append("<td><a class=\"btnDel\" href=\"javascript:void(0)\" onclick=\"").append("delTableRow(this);")
									.append("\">删除</a></td>");
						}
						renderBodyRowColumns(row, buffer, i);
						buffer.append("</tr>\n");
						// }
					}
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		buffer.append("</tbody>\n");
	}

	/**
	 * 根据记录集渲染每列
	 * 
	 * @param resultSet
	 * @param buffer
	 * @param rowIndex
	 */
	protected void renderBodyRowColumns(ResultSet resultSet, HtmlStringBuffer buffer, int rowIndex) {
		Context context = this.getContext();
		// 先输出复选框
		if (this.editMode == GaConstant.EDITMODE_DISP && this.multiSelect) {
			String value = "";
			if (this.pkField != null) {
				value = DataPropertyUtils.getValue(resultSet, this.pkField.getFieldCode(false), null).toString();
			}
			buffer.append("<td><input name=\"_ids\" value=\"").append(value).append("\"  type=\"checkbox\"></td>");
		}
		for (String fieldCode : this.dispFieldMap.keySet()) {
			ListColumn column = new ListColumn(this.editMode, fieldCode, this.dispFieldMap.get(fieldCode), this.allFieldList);
			column.setMTableMode(this.isMTableMode);
			column.renderTableData(resultSet, buffer, rowIndex, context);
		}
	}

	protected void renderBodyRowColumns(Map<String, Object> rowData, HtmlStringBuffer buffer, int rowIndex) {
		Context context = this.getContext();
		// 先输出复选框
		if (this.editMode == GaConstant.EDITMODE_DISP && this.multiSelect) {
			String value = "";
			if (this.pkField != null) {
				if (this.resultSet != null) {
					value = DataPropertyUtils.getValue(resultSet, this.pkField.getFieldCode(false), null).toString();
				} else if (this.resultList != null) {
					value = DataPropertyUtils.getValue(rowData, this.pkField.getFieldCode(false), null).toString();
				}
			}
			buffer.append("<td><input name=\"_ids\" value=\"").append(value).append("\"  type=\"checkbox\"></td>");
		}
		for (String fieldCode : this.dispFieldMap.keySet()) {
			ListColumn column = new ListColumn(this.editMode, fieldCode, this.dispFieldMap.get(fieldCode), this.allFieldList);
			column.setMTableMode(this.isMTableMode);
			column.renderTableData(rowData, buffer, rowIndex, context);
		}
	}

	/**
	 * 选项没有数据时的显示行
	 * 
	 * @param buffer
	 */
	protected void renderBodyNoRows(HtmlStringBuffer buffer) {
		buffer.append("<tr><td align=\"center\" colspan=\"");
		buffer.append(dispFieldMap.keySet().size());
		buffer.append(">");
		buffer.append("没有数据");
		buffer.append("</td></tr>\n");
	}

	public void setMTableMode(boolean isMTableMode) {
		this.isMTableMode = isMTableMode;
	}
	
	
}
