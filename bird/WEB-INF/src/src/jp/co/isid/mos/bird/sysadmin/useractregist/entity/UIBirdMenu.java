package jp.co.isid.mos.bird.sysadmin.useractregist.entity;

/**
 * 表示用メニューエンティティ
 * 
 * 作成日:2009/12/21
 * @author xkinu
 *
 */
public class UIBirdMenu extends CtlUserAct {
    
    public static final String TABLE = "BR02BMNU";
    
    public static final String motoMenuId_COLUMN = "MOTO_MENU";
    
    public static final String motoSubMenuId_COLUMN = "MOTO_SUB_MENU";
    
    public static final String menuName_COLUMN = "MENU_NAME";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";
    
    public static final String sortSeq_COLUMN = "SORT_SEQ";

    public static final String enableFlg_COLUMN = "ENABLE_FLG";

    public static final String extraFlg_COLUMN = "EXTRA_FLG";
    /** 隠しメニュー */
    public static final String hiddenFlg_COLUMN = "HIDDEN_FLG";
    
    public static final String checkFlg_COLUMN = "";
    
    public static final String checkFlgBefor_COLUMN = "";
    
    /**
     * 元メニューＩＤ
     */
    private String motoMenuId;
    
    /**
     * 元サブメニューＩＤ
     */
    private String motoSubMenuId;
    
    /**
     * メニュー名
     */
    private String menuName;
    
    /**
     * サブメニュー名
     */
    private String subMenuName;
    
    /**
     * ソート順
     */
    private String sortSeq;

    /**
     * 使用可能フラグ(初期フラグ)
     */
    private String enableFlg;

    /**
     * 上限拡張フラグ
     */
    private String extraFlg;

    /**
     * 選択フラグ
     */
    private boolean checkFlg = false;
    /**
     * 選択フラグ
     */
    private boolean checkFlgBefor = false;
    
    /** 隠しメニュー */
    private String hiddenFlg;
    
    ///////////////////以下、セッター・ゲッター////////////////
    
    /**
	 * @return hiddenFlg を戻します。
	 */
	public String getHiddenFlg() {
		return hiddenFlg;
	}
	/**
	 * @param hiddenFlg を クラス変数hiddenFlgへ設定します。
	 */
	public void setHiddenFlg(String hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}
    
    /**
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * メニュー名を設定します。
     * @param menuName メニュー名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * サブメニュー名を取得します。
     * @return サブメニュー名
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * サブメニュー名を設定します。
     * @param subMenuName サブメニュー名
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
    
    /**
     * ソート順を取得します。
     * @return ソート順
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * ソート順を設定します。
     * @param sortSeq ソート順
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getCheckFlg() {
        return checkFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param checkFlg 選択フラグ
     */
    public void setCheckFlg(boolean checkFlg) {
        this.checkFlg = checkFlg;
    }
    /**
     * @return enableFlg を戻します。
     */
    public String getEnableFlg() {
        return enableFlg;
    }
    /**
     * @param enableFlg 設定する enableFlg。
     */
    public void setEnableFlg(String enableFlg) {
        this.enableFlg = enableFlg;
    }
    /**
     * @return extraFlg を戻します。
     */
    public String getExtraFlg() {
        return extraFlg;
    }
    /**
     * @param extraFlg 設定する extraFlg。
     */
    public void setExtraFlg(String extraFlg) {
        this.extraFlg = extraFlg;
    }
	/**
	 * @return motoMenuId を戻します。
	 */
	public String getMotoMenuId() {
		return motoMenuId;
	}
	/**
	 * @param motoMenuId を クラス変数motoMenuIdへ設定します。
	 */
	public void setMotoMenuId(String motoMenuId) {
		this.motoMenuId = motoMenuId;
	}
	/**
	 * @return motoSubMenuId を戻します。
	 */
	public String getMotoSubMenuId() {
		return motoSubMenuId;
	}
	/**
	 * @param motoSubMenuId を クラス変数motoSubMenuIdへ設定します。
	 */
	public void setMotoSubMenuId(String motoSubMenuId) {
		this.motoSubMenuId = motoSubMenuId;
	}
	/**
	 * @return クラス変数checkFlgBefor を戻します。
	 */
	public boolean isCheckFlgBefor() {
		return checkFlgBefor;
	}
	/**
	 * @param checkFlgBefor を クラス変数checkFlgBeforへ設定します。
	 */
	public void setCheckFlgBefor(boolean checkFlgBefor) {
		this.checkFlgBefor = checkFlgBefor;
		setCheckFlg(checkFlgBefor);
	}
    
}
