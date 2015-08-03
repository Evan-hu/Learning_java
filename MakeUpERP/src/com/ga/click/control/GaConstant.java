package com.ga.click.control;

/**
 * 记录系统编码的常量.前后台均存在该该记录,且相同.
 * 内部静态类区分模块.其中包含该模块的系统编码常量.
 * 如果有多模块共用的系统编码常量,可将其放在内部静态类外
 */
public class GaConstant {
  
  /*****文件上传路径的指定******/
  
  public static final String ROOT_FILE = "E:/xiaoyz/NewIY/Src/ShopMgr/WebRoot";
  public static final String UP_LOAD_COMMODITY_INFO_FILE = "photo/commodityInfo/";
  public static final String UP_LOAD_ACTIVITY_INFO_FILE = "photo/activityInfo/";
  public static final String UP_LOAD_SECKILL_INFO_FILE = "photo/seckill/";
  public static final String UP_LOAD_AD_FILE = "photo/show/";
  /***********/
  /*******JS前段验证定义（若需要扩展验证表达式，请修改jquery.validationEngine.js）***********/
  
  public static final String VALIDAT_ONLYLETTERSP = "onlyLetterSp";//只能输入英文字母  
  public static final String VALIDAT_INTEGER = "integer";//只能输入整数  
  public static final String VALIDAT_NUMBER = "number";//只能输入浮点数  
  public static final String VALIDAT_TELEPHONE = "telephone";//电话号码验证  
  public static final String VALIDAT_EMAIL = "email";//EMAIL验证
    
  
  /***********指定在线编辑器的模式********************/
  public static final String EDITOR_MODE_MFULL = "mfull";
  public static final String EDITOR_MODE_SIMPLE = "simple";
  public static final String EDITOR_MODE_MINI = "mini";
  /***固定参数定义***/
  public static final String FIXPARAM_ROWSELECT = "rowselect";
  public static final String FIXPARAM_PAGESIZE = "numPerPage";
  public static final String FIXPARAM_PAGENO = "pageNum";
  public static final String FIXPARAM_PAGE_ORDERF = "pageOrderFiled";
  public static final String FIXPARAM_PAGE_ORDERT = "pageOrderType";
  public static final String FIXPARAM_EDITMODE = "editMode";
  public static final String FIXPARAM_LOOKUPMODE = "lookupMode";
  public static final String FIXPARAM_LOOKUPCALLBACK = "callback_";
  public static final String FIXPARAM_LOOKUPCID = "cid_";
  public static final String FIXPARAM_DIVMODE = "divMode";
  public static final String FIXPARAM_MULTIFORM = "_multiform";
  public static final String FIXPARAM_ROWDATA = "_rowdata";
  public static final String FIXPARAM_MULTISELECT = "_ids";
  public static final String FIXPARAM_EXPORTMARK = "_export";
  public static final String FIXPARAM_ACTIONID = "_actionid";
  public static final String FIXPARAM_WINDOWNAV= "_windownav";
  public static final String FIXPARAM_PREWINDOWNAV= "_prewindownav";
  public static final String FIXPARAM_PARENTTABLE= "_parenttable";
  public static final int DEFAULT_PAGESIZE = 10;
  
  
  /****控件显示方式****/
  public static final int INPUTMODE_READONLY = 1;
  public static final int INPUTMODE_HIDDEN = 2;
  public static final int INPUTMODE_NORMAL = 3;
  /***窗口显示方式***/
  public static final String NAVTYPE_TAB = "navTab";    //在主窗口中打开
  public static final String NAVTYPE_DIALOG = "dialog"; //在弹出窗口中打开
  public static final String NAVTYPE_DIV = "div"; //在指定层中打开
  public static final String NAVTYPE_AJAX = "ajax"; //在指定层中打开
  public static final String NAVTYPE_CUSTOM = "custom"; //在前端js中直接编写函数
  public static final String NAVTYPE_MVC = "mvc"; //新架构
  /***编辑模式定义****/
  public static final int EDITMODE_NEW = 1;
  public static final int EDITMODE_EDIT = 2;
  public static final int EDITMODE_DISP = 3;
  public static final int EDITMODE_ALL = 4;
  /***自动布局显示类型***/
  public static final int LAYOUT_AUTO_NOWARP = 1; //输出NOWARP
  public static final int LAYOUT_AUTO_LINE = 2;   //输出换行分隔符
  public static final int LAYOUT_AUTO_BOTH = 3;   //都输出
  
  /***数据类型定义***/
  public static final int DT_INT = 1;
  public static final int DT_DATETIME = 2;
  public static final int DT_STRING = 3;
  public static final int DT_PASSWORD = 5;
  public static final int DT_DOUBLE = 6;
  public static final int DT_LONG = 7;
  public static final int DT_MONEY = 8;
  
  /**标签类型:text,select......**/
  public static final int INPUT_NUMTEXT = 1;//数字类型
  public static final int INPUT_DATETIME = 2;//日期
  public static final int INPUT_TEXT = 3;//字符串
  public static final int INPUT_PASSWORD = 4;//密码
  public static final int INPUT_SELECT = 5;//下拉框选择
  public static final int INPUT_RADIOGROUP = 6; //单选框
  public static final int INPUT_TEXTAREA = 7;//文本域
  public static final int INPUT_UPLOAD = 8; //文件上传控件
  public static final int INPUT_CHECKLIST = 9; //复选框
  public static final int INPUT_TEXTTREE = 10;//树状选择  
  public static final int INPUT_HIDDEN = 11;//隐藏
  public static final int INPUT_POPSELECT = 12;//弹出选择
  public static final int INPUT_REFCOMBOX = 13;//关联选择框
  public static final int INPUT_SELECTLIST = 14;//列表框
  public static final int INPUT_TREE = 15;//树控件
  public static final int INPUT_MONEY= 16;//货币输入
  public static final int INPUT_REQUEST = 20;//来源于request
  public static final int INPUT_SESSION = 21;//来源于session
  public static final int INPUT_ROWOBJ = 22;//来源于ROWOBJ
  public static final int INPUT_LABLE = 90; //标签控件
  
  /****查询操作符****/
  public static final int QUERY_EQUALS = 1;          // ==
  public static final int QUERY_GREATER_THAN = 2;    // >
  public static final int QUERY_LESS_THAN = 3;       // <
  public static final int QUERY_GREATER_EQUALS = 4;  // >=
  public static final int QUERY_LESS_EQUALS = 5;     // <=
  public static final int QUERY_LIKE = 6;            // LIKE
  public static final int QUERY_IN = 7;              // IN
  public static final int QUERY_BETWEEN = 8;//between and
  public static final int QUERY_CUSTOM = 9;//自定义操作符

  
  public static final String[] QUERY_OPERA = {"","=",">", "<", ">=", "<=", " like ", " in ",""};
  
  /*****格式化类型******************/
  public static final int FORMAT_DATE = 1;     // 日期格式化
  public static final int FORMAT_DOUBLE = 2;    // 小数格式化
  public static final int FORMAT_LOOKUP = 3;    // 键值对匹配 
  public static final int FORMAT_CUSTOM = 4;    // 自定义格式化 
  
  /****视图类型*******/
  public static final int VIEWTYPE_LIST = 1;
  public static final int VIEWTYPE_FORM = 2;
  public static final int VIEWTYPE_TREE = 3;
  public static final int VIEWTYPE_QUERY = 4;
  
  
  /******固定按钮名称************/
  public static final String ACTION_NEWNAV = "newnav";
  public static final String ACTION_NEWDIALOG = "newdialog";
}
