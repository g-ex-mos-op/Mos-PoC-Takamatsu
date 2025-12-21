package jp.co.isid.mos.bird.commonform.functionsearch.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

public class UIFunction {
    
    public static final String TABLE = "BR02BMNU";
    
    public static final String menuId_COLUMN = "MENU_ID";
    
    public static final String menuName_COLUMN = "MENU_NAME";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";

    public static final String sortSeq_COLUMN = "SORT_SEQ";
    
    public static final String typeName_COLUMN = "TYPE_NAME";

    /**
     * 機能カテゴリID
     */
    private String menuId;
    
    /**
     * 機能カテゴリ名
     */
    private String menuName;
    
    /**
     * 機能ID
     */
    private String subMenuId;
    
    /**
     * 機能名
     */
    private String subMenuName;

    /**
     * ソート順
     */
    private String sortSeq;

    /**
     * 選択チェックボックス
     */
    private boolean checkFlg;

    private String typeName;
    /**
	 * @return クラス変数typeName を戻します。
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName を クラス変数typeNameへ設定します。
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
     * 機能カテゴリIDを取得します。
     * @return 機能カテゴリID
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * 機能カテゴリIDを設定します。
     * @param menuId 機能カテゴリID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * 機能カテゴリ名を取得します。
     * @return 機能カテゴリ名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * 機能カテゴリ名を設定します。
     * @param menuName 機能カテゴリ名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * 機能IDを取得します。
     * @return 機能ID
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * 機能IDを設定します。
     * @param subMenuId 機能ID
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }
    
    /**
     * 機能名を取得します。
     * @return 機能名
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * 機能名を設定します。
     * @param subMenuName 機能名
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    /**
     * ソート順の設定
     * @return sortSeq を戻します。
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * ソート順の設定
     * @param sortSeq sortSeq を設定。
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }

    /**
     * 選択チェックボックスの設定
     * @return check を戻します。
     */
    public boolean isCheckFlg() {
        return checkFlg;
    }
    /**
     * 選択チェックボックスの設定
     * @param check check を設定。
     */
    public void setCheckFlg(boolean checkFlg) {
        this.checkFlg = checkFlg;
    }
    /**
     * 
     * @return
     */
    public String getLabelMenuNameTopTypeName() {
    	if(CommonUtil.isNull(getTypeName())) {
    		return getMenuName();
    	}
    	return "("+getTypeName()+")"+getMenuName();
    }
    /**
     * 
     * @return
     */
    public String getLabelMenuNameBottomTypeName() {
    	if(CommonUtil.isNull(getTypeName())) {
    		return getMenuName();
    	}
    	return getMenuName()+"("+getTypeName()+")";
    }
    public void setLabelMenuName(String name) {
    	
    }
}
