package jp.co.isid.mos.bird.sysadmin.roleacterregist.entity;

public class UIViewRole {
    
    public static final String TABLE = "BR05ACTR";
    
    public static final String menuId_COLUMN = "MENU_ID";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";
    
    public static final String roleCd1_COLUMN = "ROLE_CD1";
    
    public static final String checkFlg1_COLUMN = "CHECK_FLG1";
    
    public static final String roleCd2_COLUMN = "ROLE_CD2";
    
    public static final String checkFlg2_COLUMN = "CHECK_FLG2";
    
    public static final String roleCd3_COLUMN = "ROLE_CD3";
    
    public static final String checkFlg3_COLUMN = "CHECK_FLG3";
    
    public static final String roleCd4_COLUMN = "ROLE_CD4";
    
    public static final String checkFlg4_COLUMN = "CHECK_FLG4";
    
    public static final String roleCd5_COLUMN = "ROLE_CD5";
    
    public static final String checkFlg5_COLUMN = "CHECK_FLG5";
    
    
    /*  コンストラクター(ViewRole = 1のとき*/
    public UIViewRole(
    		  String    menuId
			, String    subMenuId
			, String    subMenuName
			, String    roleCd1
            , boolean  checkFlg1
            , boolean  checkFlg1Limit) {
    	
    	this.menuId         = menuId;
    	this.subMenuId      = subMenuId;
    	this.subMenuName    = subMenuName;
    	this.roleCd1        = roleCd1;
        this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
    }
    /*  コンストラクター(ViewRole = 2のとき*/
    public UIViewRole(
    		  String    menuId
			, String    subMenuId
			, String    subMenuName
			, String    roleCd1
            , boolean  checkFlg1
            , boolean  checkFlg1Limit
			, String    roleCd2
            , boolean  checkFlg2
            , boolean  checkFlg2Limit) {
    	
    	this.menuId         = menuId;
    	this.subMenuId      = subMenuId;
    	this.subMenuName    = subMenuName;
    	this.roleCd1        = roleCd1;
        this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
    	this.roleCd2        = roleCd2;
        this.checkFlg2      = checkFlg2;
        this.checkFlg2Limit = checkFlg2Limit;
    }
    
    /*  コンストラクター(ViewRole = 3のとき*/
    public UIViewRole(
     		  String    menuId
			, String    subMenuId
			, String    subMenuName
			, String    roleCd1
            , boolean  checkFlg1
            , boolean  checkFlg1Limit
			, String    roleCd2
            , boolean  checkFlg2
            , boolean  checkFlg2Limit
			, String    roleCd3
            , boolean  checkFlg3
            , boolean  checkFlg3Limit) {
  	
	  	this.menuId         = menuId;
	  	this.subMenuId      = subMenuId;
	  	this.subMenuName    = subMenuName;
	  	this.roleCd1        = roleCd1;
	  	this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
	  	this.roleCd2        = roleCd2;
        this.checkFlg2      = checkFlg2;
        this.checkFlg2Limit = checkFlg2Limit;
	  	this.roleCd3        = roleCd3;
        this.checkFlg3      = checkFlg3;
        this.checkFlg3Limit = checkFlg3Limit;
  }
    /*  コンストラクター(ViewRole = 4のとき*/
    public UIViewRole(
     		  String    menuId
			, String    subMenuId
			, String    subMenuName
			, String    roleCd1
            , boolean  checkFlg1
            , boolean  checkFlg1Limit
			, String    roleCd2
            , boolean  checkFlg2
            , boolean  checkFlg2Limit
			, String    roleCd3
            , boolean  checkFlg3
            , boolean  checkFlg3Limit
			, String    roleCd4
            , boolean  checkFlg4
            , boolean  checkFlg4Limit) {
  	
	  	this.menuId         = menuId;
	  	this.subMenuId      = subMenuId;
	  	this.subMenuName    = subMenuName;
	  	this.roleCd1        = roleCd1;
	  	this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
	  	this.roleCd2        = roleCd2;
	  	this.checkFlg2      = checkFlg2;
        this.checkFlg2Limit = checkFlg2Limit;
	  	this.roleCd3        = roleCd3;
        this.checkFlg3      = checkFlg3;
        this.checkFlg3Limit = checkFlg3Limit;
	  	this.roleCd4        = roleCd4;
        this.checkFlg4      = checkFlg4;
        this.checkFlg4Limit = checkFlg4Limit;
  }
    /*  コンストラクター(ViewRole = 5のとき*/
    public UIViewRole(
     		  String    menuId
			, String    subMenuId
			, String    subMenuName
			, String    roleCd1
            , boolean  checkFlg1
            , boolean  checkFlg1Limit
			, String    roleCd2
            , boolean  checkFlg2
            , boolean  checkFlg2Limit
			, String    roleCd3
            , boolean  checkFlg3
            , boolean  checkFlg3Limit
			, String    roleCd4
            , boolean  checkFlg4
            , boolean  checkFlg4Limit
			, String    roleCd5
            , boolean  checkFlg5
            , boolean  checkFlg5Limit) {
  	
	  	this.menuId         = menuId;
	  	this.subMenuId      = subMenuId;
	  	this.subMenuName    = subMenuName;
	  	this.roleCd1        = roleCd1;
        this.checkFlg1      = checkFlg1;
        this.checkFlg1Limit = checkFlg1Limit;
	  	this.roleCd2        = roleCd2;
        this.checkFlg2      = checkFlg2;
        this.checkFlg2Limit = checkFlg2Limit;
	  	this.roleCd3        = roleCd3;
        this.checkFlg3      = checkFlg3;
        this.checkFlg3Limit = checkFlg3Limit;
	  	this.roleCd4        = roleCd4;
        this.checkFlg4      = checkFlg4;
        this.checkFlg4Limit = checkFlg4Limit;
	  	this.roleCd5        = roleCd5;
        this.checkFlg5      = checkFlg5;
        this.checkFlg5Limit = checkFlg5Limit;
  }
     
    /**
     * メニューID
     */
    private String menuId;
    
    /**
     * サブメニューID
     */
    private String subMenuId;
    
    /**
     * サブメニュー名称
     */
    private String subMenuName;
    
    /**
     * １番目ロールコード
     */
    private String roleCd1;
    /**
     * １番目チェックフラグ
     */
    private boolean checkFlg1;
    /**
     * １番目(上限)チェックフラグ
     */
    private boolean checkFlg1Limit;


    /**
     * ２番目ロールコード
     */
    private String roleCd2;
    /**
     * ２番目チェックフラグ
     */
    private boolean checkFlg2;
    /**
     * ２番目(上限)チェックフラグ
     */
    private boolean checkFlg2Limit;

    
    /**
     * ３番目ロールコード
     */
    private String roleCd3;
    /**
     * ３番目チェックフラグ
     */
    private boolean checkFlg3;
    /**
     * ３番目(上限)チェックフラグ
     */
    private boolean checkFlg3Limit;

    
    /**
     * ４番目ロールコード
     */
    private String roleCd4;
    /**
     * ４番目チェックフラグ
     */
    private boolean checkFlg4;
    /**
     * ４番目(上限)チェックフラグ
     */
    private boolean checkFlg4Limit;


    /**
     * ５番目ロールコード
     */
    private String roleCd5;
    /**
     * ５番目チェックフラグ
     */
    private boolean checkFlg5;
    /**
     * ５番目(上限)チェックフラグ
     */
    private boolean checkFlg5Limit;
    
    /**
     * メニューIDを取得します。
     * @return メニューID
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * メニューIDを設定します。
     * @param menuId メニューID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * サブメニューIDを取得します。
     * @return サブメニューID
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * サブメニューIDを設定します。
     * @param subMenuId サブメニューID
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }
    
    /**
     * サブメニュー名称を取得します。
     * @return サブメニュー名称
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * サブメニュー名称を設定します。
     * @param subMenuName サブメニュー名称
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
    
    /**
     * １番目ロールコードを取得します。
     * @return １番目ロールコード
     */
    public String getRoleCd1() {
        return roleCd1;
    }
    /**
     * １番目ロールコードを設定します。
     * @param roleCd1 １番目ロールコード
     */
    public void setRoleCd1(String roleCd1) {
        this.roleCd1 = roleCd1;
    }
    
    /**
     * １番目チェックフラグを取得します。
     * @return １番目チェックフラグ
     */
    public boolean getCheckFlg1() {
        return checkFlg1;
    }
    /**
     * １番目チェックフラグを設定します。
     * @param checkFlg1 １番目チェックフラグ
     */
    public void setCheckFlg1(boolean checkFlg1) {
        this.checkFlg1 = checkFlg1;
    }

    /**
     * １番目(上限)チェックフラグを取得します。
     * @return １番目(上限)チェックフラグ
     */
    public boolean getCheckFlg1Limit() {
        return checkFlg1Limit;
    }
    /**
     * １番目(上限)チェックフラグを設定します。
     * @param checkFlg1Limit １番目(上限)チェックフラグ
     */
    public void setCheckFlg1Limit(boolean checkFlg1Limit) {
        this.checkFlg1Limit = checkFlg1Limit;
    }

    /**
     * ２番目ロールコードを取得します。
     * @return ２番目ロールコード
     */
    public String getRoleCd2() {
        return roleCd2;
    }
    /**
     * ２番目ロールコードを設定します。
     * @param roleCd2 ２番目ロールコード
     */
    public void setRoleCd2(String roleCd2) {
        this.roleCd2 = roleCd2;
    }
    
    /**
     * ２番目チェックフラグを取得します。
     * @return ２番目チェックフラグ
     */
    public boolean getCheckFlg2() {
        return checkFlg2;
    }
    /**
     * ２番目チェックフラグを設定します。
     * @param checkFlg2 ２番目チェックフラグ
     */
    public void setCheckFlg2(boolean checkFlg2) {
        this.checkFlg2 = checkFlg2;
    }
    
    /**
     * ２番目(上限)チェックフラグを取得します。
     * @return ２番目(上限)チェックフラグ
     */
    public boolean getCheckFlg2Limit() {
        return checkFlg2Limit;
    }
    /**
     * ２番目(上限)チェックフラグを設定します。
     * @param checkFlg2Limit ２番目(上限)チェックフラグ
     */
    public void setCheckFlg2Limit(boolean checkFlg2Limit) {
        this.checkFlg2Limit = checkFlg2Limit;
    }
    
    /**
     * ３番目ロールコードを取得します。
     * @return ３番目ロールコード
     */
    public String getRoleCd3() {
        return roleCd3;
    }
    /**
     * ３番目ロールコードを設定します。
     * @param roleCd3 ３番目ロールコード
     */
    public void setRoleCd3(String roleCd3) {
        this.roleCd3 = roleCd3;
    }
    
    /**
     * ３番目チェックフラグを取得します。
     * @return ３番目チェックフラグ
     */
    public boolean getCheckFlg3() {
        return checkFlg3;
    }
    /**
     * ３番目チェックフラグを設定します。
     * @param checkFlg3 ３番目チェックフラグ
     */
    public void setCheckFlg3(boolean checkFlg3) {
        this.checkFlg3 = checkFlg3;
    }

    /**
     * ３番目(上限)チェックフラグを取得します。
     * @return ３番目(上限)チェックフラグ
     */
    public boolean getCheckFlg3Limit() {
        return checkFlg3Limit;
    }
    /**
     * ３番目(上限)チェックフラグを設定します。
     * @param checkFlg3Limit ３番目(上限)チェックフラグ
     */
    public void setCheckFlg3Limit(boolean checkFlg3Limit) {
        this.checkFlg3Limit = checkFlg3Limit;
    }

    /**
     * ４番目ロールコードを取得します。
     * @return ４番目ロールコード
     */
    public String getRoleCd4() {
        return roleCd4;
    }
    /**
     * ４番目ロールコードを設定します。
     * @param roleCd4 ４番目ロールコード
     */
    public void setRoleCd4(String roleCd4) {
        this.roleCd4 = roleCd4;
    }
    
    /**
     * ４番目チェックフラグを取得します。
     * @return ４番目チェックフラグ
     */
    public boolean getCheckFlg4() {
        return checkFlg4;
    }
    /**
     * ４番目チェックフラグを設定します。
     * @param checkFlg4 ４番目チェックフラグ
     */
    public void setCheckFlg4(boolean checkFlg4) {
        this.checkFlg4 = checkFlg4;
    }

    /**
     * ４番目(上限)チェックフラグを取得します。
     * @return ４番目(上限)チェックフラグ
     */
    public boolean getCheckFlg4Limit() {
        return checkFlg4Limit;
    }
    /**
     * ４番目(上限)チェックフラグを設定します。
     * @param checkFlg4Limit ４番目(上限)チェックフラグ
     */
    public void setCheckFlg4Limit(boolean checkFlg4Limit) {
        this.checkFlg4Limit = checkFlg4Limit;
    }

    /**
     * ５番目ロールコードを取得します。
     * @return ５番目ロールコード
     */
    public String getRoleCd5() {
        return roleCd5;
    }
    /**
     * ５番目ロールコードを設定します。
     * @param roleCd5 ５番目ロールコード
     */
    public void setRoleCd5(String roleCd5) {
        this.roleCd5 = roleCd5;
    }
    
    /**
     * ５番目チェックフラグを取得します。
     * @return ５番目チェックフラグ
     */
    public boolean getCheckFlg5() {
        return checkFlg5;
    }
    /**
     * ５番目チェックフラグを設定します。
     * @param checkFlg5 ５番目チェックフラグ
     */
    public void setCheckFlg5(boolean checkFlg5) {
        this.checkFlg5 = checkFlg5;
    }

    /**
     * ５番目(上限)チェックフラグを取得します。
     * @return ５番目(上限)チェックフラグ
     */
    public boolean getCheckFlg5Limit() {
        return checkFlg5Limit;
    }
    /**
     * ５番目(上限)チェックフラグを設定します。
     * @param checkFlg5Limit ５番目(上限)チェックフラグ
     */
    public void setCheckFlg5Limit(boolean checkFlg5Limit) {
        this.checkFlg5Limit = checkFlg5Limit;
    }

}
