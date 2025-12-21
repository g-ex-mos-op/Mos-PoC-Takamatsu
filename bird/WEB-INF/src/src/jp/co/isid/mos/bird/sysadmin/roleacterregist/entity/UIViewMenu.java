package jp.co.isid.mos.bird.sysadmin.roleacterregist.entity;

public class UIViewMenu {
    
    public static final String TABLE = "BR05ACTR";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    public static final String roleName_COLUMN = "ROLE_NAME";
    
    public static final String menuId1_COLUMN = "MENU_ID1";
    
    public static final String menuName1_COLUMN = "MENU_NAME1";
    
    public static final String subMenuId1_COLUMN = "SUB_MENU_ID1";
    
    public static final String subMenuName1_COLUMN = "SUB_MENU_NAME1";
    
    public static final String checkFlg1_COLUMN = "CHECK_FLG1";
    
    public static final String menuId2_COLUMN = "MENU_ID2";
    
    public static final String menuName2_COLUMN = "MENU_NAME2";
    
    public static final String subMenuId2_COLUMN = "SUB_MENU_ID2";
    
    public static final String subMenuName2_COLUMN = "SUB_MENU_NAME2";
    
    public static final String checkFlg2_COLUMN = "CHECK_FLG2";
    
    public static final String menuId3_COLUMN = "MENU_ID3";
    
    public static final String menuName3_COLUMN = "MENU_NAME3";
    
    public static final String subMenuId3_COLUMN = "SUB_MENU_ID3";
    
    public static final String subMenuName3_COLUMN = "SUB_MENU_NAME3";
    
    public static final String checkFlg3_COLUMN = "CHECK_FLG3";
    
    public static final String menuId4_COLUMN = "MENU_ID4";
    
    public static final String menuName4_COLUMN = "MENU_NAME4";
    
    public static final String subMenuId4_COLUMN = "SUB_MENU_ID4";
    
    public static final String subMenuName4_COLUMN = "SUB_MENU_NAME4";
    
    public static final String checkFlg4_COLUMN = "CHECK_FLG4";
    
    public static final String menuId5_COLUMN = "MENU_ID5";
    
    public static final String menuName5_COLUMN = "MENU_NAME5";
    
    public static final String subMenuId5_COLUMN = "SUB_MENU_ID5";
    
    public static final String subMenuName5_COLUMN = "SUB_MENU_NAME5";
    
    public static final String checkFlg5_COLUMN = "CHECK_FLG5";
    
    /* コンストラクター(ViewMenu = 1のとき) */
    public UIViewMenu(
    		   String   roleCd
			 , String   roleName
			 , String   menuId1
			 , String   subMenuId1
			 , boolean checkFlg1
             , boolean checkFlg1Limit) {
    	
    	this.roleCd         = roleCd;
    	this.roleName       = roleName;
    	this.menuId1        = menuId1;
    	this.subMenuId1     = subMenuId1;
    	this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
    }
    
    /* コンストラクター(ViewMenu = 2のとき) */
    public UIViewMenu(
    		   String    roleCd
    		 , String    roleName
			 , String    menuId1
			 , String    subMenuId1
			 , boolean  checkFlg1
             , boolean  checkFlg1Limit
			 , String    menuId2
			 , String    subMenuId2
             , boolean  checkFlg2
             , boolean  checkFlg2Limit) {
    	
    	this.roleCd         =   roleCd;
    	this.roleName       =   roleName;
    	this.menuId1        =   menuId1;
    	this.subMenuId1     =   subMenuId1;
    	this.checkFlg1      =   checkFlg1;
        this.checkFlg1Limit =   checkFlg1Limit;
    	this.menuId2        =   menuId2;
    	this.subMenuId2     =   subMenuId2;
    	this.checkFlg2      =   checkFlg2;
        this.checkFlg2Limit =   checkFlg2Limit;
    }
    
    /* コンストラクター(ViewMenu = 3のとき) */
    public UIViewMenu(
    		     String    roleCd
			   , String    roleName
			   , String    menuId1
			   , String    subMenuId1
			   , boolean  checkFlg1
               , boolean  checkFlg1Limit
			   , String    menuId2
			   , String    subMenuId2
			   , boolean  checkFlg2
               , boolean  checkFlg2Limit
			   , String    menuId3
			   , String    subMenuId3
               , boolean  checkFlg3
               , boolean  checkFlg3Limit) {
    	
    	this.roleCd         =   roleCd;
    	this.roleName       =   roleName;
    	this.menuId1        =   menuId1;
    	this.subMenuId1     =   subMenuId1;
    	this.checkFlg1      =   checkFlg1;
        this.checkFlg1Limit =   checkFlg1Limit;
    	this.menuId2        =   menuId2;
    	this.subMenuId2     =   subMenuId2;
    	this.checkFlg2      =   checkFlg2;
        this.checkFlg2Limit =   checkFlg2Limit;
    	this.menuId3        =   menuId3;
    	this.subMenuId3     =   subMenuId3;
    	this.checkFlg3      =   checkFlg3;
        this.checkFlg3Limit =   checkFlg3Limit;

    }
    
    /* コンストラクター(ViewMenu = 4のとき) */
    public UIViewMenu(
    		     String    roleCd
			   , String    roleName
			   , String    menuId1
			   , String    subMenuId1
			   , boolean  checkFlg1
               , boolean  checkFlg1Limit
			   , String    menuId2
			   , String    subMenuId2
			   , boolean  checkFlg2
               , boolean  checkFlg2Limit
			   , String    menuId3
			   , String    subMenuId3
			   , boolean  checkFlg3
               , boolean  checkFlg3Limit
			   , String    menuId4
			   , String    subMenuId4
               , boolean  checkFlg4
               , boolean  checkFlg4Limit) {
    	
    	this.roleCd         =   roleCd;
    	this.roleName       =   roleName;
    	this.menuId1        =   menuId1;
    	this.subMenuId1     =   subMenuId1;
    	this.checkFlg1      =   checkFlg1;
        this.checkFlg1Limit =   checkFlg1Limit;
    	this.menuId2        =   menuId2;
    	this.subMenuId2     =   subMenuId2;
    	this.checkFlg2      =   checkFlg2;
        this.checkFlg2Limit =   checkFlg2Limit;
    	this.menuId3        =   menuId3;
    	this.subMenuId3     =   subMenuId3;
    	this.checkFlg3      =   checkFlg3;
        this.checkFlg3Limit =   checkFlg3Limit;
    	this.menuId4        =   menuId4;
    	this.subMenuId4     =   subMenuId4;
    	this.checkFlg4      =   checkFlg4;
        this.checkFlg4Limit =   checkFlg4Limit;

    }
    
    /* コンストラクター(ViewMenu = 5のとき) */
    public UIViewMenu(
    		     String    roleCd
			   , String    roleName
			   , String    menuId1
			   , String    subMenuId1
			   , boolean  checkFlg1
               , boolean  checkFlg1Limit
			   , String    menuId2
			   , String    subMenuId2
			   , boolean  checkFlg2
               , boolean  checkFlg2Limit
			   , String    menuId3
			   , String    subMenuId3
			   , boolean  checkFlg3
               , boolean  checkFlg3Limit
			   , String    menuId4
			   , String    subMenuId4
			   , boolean  checkFlg4
               , boolean  checkFlg4Limit
			   , String    menuId5
			   , String    subMenuId5
               , boolean  checkFlg5
               , boolean  checkFlg5Limit) {
    	
    	this.roleCd         =   roleCd;
    	this.roleName       =   roleName;
    	this.menuId1        =   menuId1;
    	this.subMenuId1     =   subMenuId1;
    	this.checkFlg1      =   checkFlg1;
        this.checkFlg1Limit =   checkFlg1Limit;
    	this.menuId2        =   menuId2;
    	this.subMenuId2     =   subMenuId2;
    	this.checkFlg2      =   checkFlg2;
        this.checkFlg2Limit =   checkFlg2Limit;
    	this.menuId3        =   menuId3;
    	this.subMenuId3     =   subMenuId3;
    	this.checkFlg3      =   checkFlg3;
        this.checkFlg3Limit =   checkFlg3Limit;
    	this.menuId4        =   menuId4;
    	this.subMenuId4     =   subMenuId4;
    	this.checkFlg4      =   checkFlg4;
        this.checkFlg4Limit =   checkFlg4Limit;
    	this.menuId5        =   menuId5;
    	this.subMenuId5     =   subMenuId5;
    	this.checkFlg5      =   checkFlg5;    	
        this.checkFlg5Limit =   checkFlg5Limit;      
    }
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ロール名称
     */
    private String roleName;
    
    /**
     * 1番目メニューコード
     */
    private String menuId1;
    
    /**
     * 1番目サブメニューコード
     */
    private String subMenuId1;
    
    
    /**
     * 1番目チェックフラグ(初期)
     */
    private boolean checkFlg1;
    /**
     * 1番目チェックフラグ(上限)
     */
    private boolean checkFlg1Limit;
    
    /**
     * 2番目メニューコード
     */
    private String menuId2;
       
    /**
     * 2番目サブメニューコード
     */
    private String subMenuId2;
    
    /**
     * 2番目チェックフラグ(初期)
     */
    private boolean checkFlg2;
    /**
     * 2番目チェックフラグ(上限)
     */
    private boolean checkFlg2Limit;
    
    /**
     * 3番目メニューコード
     */
    private String menuId3;
    
    
    /**
     * 3番目サブメニューコード
     */
    private String subMenuId3;    
    
    /**
     * 3番目チェックフラグ(初期)
     */
    private boolean checkFlg3;
    /**
     * 3番目チェックフラグ(上限)
     */
    private boolean checkFlg3Limit;
    
    /**
     * 4番目メニューコード
     */
    private String menuId4;
       
    /**
     * 4番目サブメニューコード
     */
    private String subMenuId4;
    
    /**
     * 4番目チェックフラグ(初期)
     */
    private boolean checkFlg4;
    /**
     * 4番目チェックフラグ(上限)
     */
    private boolean checkFlg4Limit;
    
    /**
     * 5番目メニューコード
     */
    private String menuId5;
    
    /**
     * 5番目サブメニューコード
     */
    private String subMenuId5;
    
    /**
     * 5番目チェックフラグ(初期)
     */
    private boolean checkFlg5;
    /**
     * 5番目チェックフラグ(上限)
     */
    private boolean checkFlg5Limit;

    /////////////////////////////////以下、セッター・ゲッター//////////////////////////////////
    
    /**
     * ロールコードを取得します。
     * @return ロールコード
     */
    public String getRoleCd() {
        return roleCd;
    }
    /**
     * ロールコードを設定します。
     * @param roleCd ロールコード
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
    /**
     * ロール名称を取得します。
     * @return ロール名称
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * ロール名称を設定します。
     * @param roleName ロール名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * 1番目メニューコードを取得します。
     * @return 1番目メニューコード
     */
    public String getMenuId1() {
        return menuId1;
    }
    /**
     * 1番目メニューコードを設定します。
     * @param menuId1 1番目メニューコード
     */
    public void setMenuId1(String menuId1) {
        this.menuId1 = menuId1;
    }
    
    /**
     * 1番目サブメニューコードを取得します。
     * @return 1番目サブメニューコード
     */
    public String getSubMenuId1() {
        return subMenuId1;
    }
    /**
     * 1番目サブメニューコードを設定します。
     * @param subMenuId1 1番目サブメニューコード
     */
    public void setSubMenuId1(String subMenuId1) {
        this.subMenuId1 = subMenuId1;
    }
    
    /**
     * 1番目チェックフラグを取得します。
     * @return 1番目チェックフラグ
     */
    public boolean getCheckFlg1() {
        return checkFlg1;
    }
    /**
     * 1番目チェックフラグを設定します。
     * @param checkFlg1 1番目チェックフラグ
     */
    public void setCheckFlg1(boolean checkFlg1) {
        this.checkFlg1 = checkFlg1;
    }

    /**
     * 1番目チェックフラグ(上限)を取得します。
     * @return 1番目チェックフラグ(上限)
     */
    public boolean getCheckFlg1Limit() {
        return checkFlg1Limit;
    }
    /**
     * 1番目チェックフラグ(上限)を設定します。
     * @param checkFlg1Limit 1番目チェックフラグ(上限)
     */
    public void setCheckFlg1Limit(boolean checkFlg1Limit) {
        this.checkFlg1Limit = checkFlg1Limit;
    }

    
    /**
     * 2番目メニューコードを取得します。
     * @return 2番目メニューコード
     */
    public String getMenuId2() {
        return menuId2;
    }
    /**
     * 2番目メニューコードを設定します。
     * @param menuId2 2番目メニューコード
     */
    public void setMenuId2(String menuId2) {
        this.menuId2 = menuId2;
    }
    
    /**
     * 2番目サブメニューコードを取得します。
     * @return 2番目サブメニューコード
     */
    public String getSubMenuId2() {
        return subMenuId2;
    }
    /**
     * 2番目サブメニューコードを設定します。
     * @param subMenuId2 2番目サブメニューコード
     */
    public void setSubMenuId2(String subMenuId2) {
        this.subMenuId2 = subMenuId2;
    }
    
    /**
     * 2番目チェックフラグを取得します。
     * @return 2番目チェックフラグ
     */
    public boolean getCheckFlg2() {
        return checkFlg2;
    }
    /**
     * 2番目チェックフラグを設定します。
     * @param checkFlg2 2番目チェックフラグ
     */
    public void setCheckFlg2(boolean checkFlg2) {
        this.checkFlg2 = checkFlg2;
    }

    /**
     * 2番目チェックフラグ(上限)を取得します。
     * @return 2番目チェックフラグ(上限)
     */
    public boolean getCheckFlg2Limit() {
        return checkFlg2Limit;
    }
    /**
     * 2番目チェックフラグ(上限)を設定します。
     * @param checkFlg2Limit 2番目チェックフラグ(上限)
     */
    public void setCheckFlg2Limit(boolean checkFlg2Limit) {
        this.checkFlg2Limit = checkFlg2Limit;
    }

    
    /**
     * 3番目メニューコードを取得します。
     * @return 3番目メニューコード
     */
    public String getMenuId3() {
        return menuId3;
    }
    /**
     * 3番目メニューコードを設定します。
     * @param menuId3 3番目メニューコード
     */
    public void setMenuId3(String menuId3) {
        this.menuId3 = menuId3;
    }

    /**
     * 3番目サブメニューコードを取得します。
     * @return 3番目サブメニューコード
     */
    public String getSubMenuId3() {
        return subMenuId3;
    }
    /**
     * 3番目サブメニューコードを設定します。
     * @param subMenuId3 3番目サブメニューコード
     */
    public void setSubMenuId3(String subMenuId3) {
        this.subMenuId3 = subMenuId3;
    }

    /**
     * 3番目チェックフラグを取得します。
     * @return 3番目チェックフラグ
     */
    public boolean getCheckFlg3() {
        return checkFlg3;
    }
    /**
     * 3番目チェックフラグを設定します。
     * @param checkFlg3 3番目チェックフラグ
     */
    public void setCheckFlg3(boolean checkFlg3) {
        this.checkFlg3 = checkFlg3;
    }

    /**
     * 3番目チェックフラグ(上限)を取得します。
     * @return 3番目チェックフラグ(上限)
     */
    public boolean getCheckFlg3Limit() {
        return checkFlg3Limit;
    }
    /**
     * 3番目チェックフラグ(上限)を設定します。
     * @param checkFlg3Limit 3番目チェックフラグ(上限)
     */
    public void setCheckFlg3Limit(boolean checkFlg3Limit) {
        this.checkFlg3Limit = checkFlg3Limit;
    }

    /**
     * 4番目メニューコードを取得します。
     * @return 4番目メニューコード
     */
    public String getMenuId4() {
        return menuId4;
    }
    /**
     * 4番目メニューコードを設定します。
     * @param menuId4 4番目メニューコード
     */
    public void setMenuId4(String menuId4) {
        this.menuId4 = menuId4;
    }

    /**
     * 4番目サブメニューコードを取得します。
     * @return 4番目サブメニューコード
     */
    public String getSubMenuId4() {
        return subMenuId4;
    }
    /**
     * 4番目サブメニューコードを設定します。
     * @param subMenuId4 4番目サブメニューコード
     */
    public void setSubMenuId4(String subMenuId4) {
        this.subMenuId4 = subMenuId4;
    }
      
    /**
     * 4番目チェックフラグを取得します。
     * @return 4番目チェックフラグ
     */
    public boolean getCheckFlg4() {
        return checkFlg4;
    }
    /**
     * 4番目チェックフラグを設定します。
     * @param checkFlg4 4番目チェックフラグ
     */
    public void setCheckFlg4(boolean checkFlg4) {
        this.checkFlg4 = checkFlg4;
    }

    /**
     * 4番目チェックフラグ(上限)を取得します。
     * @return 4番目チェックフラグ(上限)
     */
    public boolean getCheckFlg4Limit() {
        return checkFlg4Limit;
    }
    /**
     * 4番目チェックフラグ(上限)を設定します。
     * @param checkFlg4Limit 4番目チェックフラグ(上限)
     */
    public void setCheckFlg4Limit(boolean checkFlg4Limit) {
        this.checkFlg4Limit = checkFlg4Limit;
    }

    /**
     * 5番目メニューコードを取得します。
     * @return 5番目メニューコード
     */
    public String getMenuId5() {
        return menuId5;
    }
    /**
     * 5番目メニューコードを設定します。
     * @param menuId5 5番目メニューコード
     */
    public void setMenuId5(String menuId5) {
        this.menuId5 = menuId5;
    }
  
    /**
     * 5番目サブメニューコードを取得します。
     * @return 5番目サブメニューコード
     */
    public String getSubMenuId5() {
        return subMenuId5;
    }
    /**
     * 5番目サブメニューコードを設定します。
     * @param subMenuId5 5番目サブメニューコード
     */
    public void setSubMenuId5(String subMenuId5) {
        this.subMenuId5 = subMenuId5;
    }
     
    /**
     * 5番目チェックフラグを取得します。
     * @return 5番目チェックフラグ
     */
    public boolean getCheckFlg5() {
        return checkFlg5;
    }
    /**
     * 5番目チェックフラグを設定します。
     * @param checkFlg5 5番目チェックフラグ
     */
    public void setCheckFlg5(boolean checkFlg5) {
        this.checkFlg5 = checkFlg5;
    }

    /**
     * 5番目チェックフラグ(上限)を取得します。
     * @return 5番目チェックフラグ(上限)
     */
    public boolean getCheckFlg5Limit() {
        return checkFlg5Limit;
    }
    /**
     * 5番目チェックフラグ(上限)を設定します。
     * @param checkFlg5Limit 5番目チェックフラグ(上限)
     */
    public void setCheckFlg5Limit(boolean checkFlg5Limit) {
        this.checkFlg5Limit = checkFlg5Limit;
    }

}
