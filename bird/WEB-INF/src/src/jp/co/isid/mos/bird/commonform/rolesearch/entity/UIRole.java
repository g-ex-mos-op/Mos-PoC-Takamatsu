/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.entity;

/**
 * @author xyuchida
 *
 */
public class UIRole {
    
    public static final String TABLE = "BR03ROLE";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    public static final String roleName_COLUMN = "ROLE_NAME";
    
    public static final String bunruiCd_COLUMN = "BUNRUI_CD";
    
    public static final String description_COLUMN = "DESCRIPTION";
    
    public static final String roleSortSeq_COLUMN = "ROLE_SORT_SEQ";
    
    public static final String bunruiName_COLUMN = "BUNRUI_NAME";
    
    public static final String bunruiSortSeq_COLUMN = "BUNRUI_SORT_SEQ";
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ロール名称
     */
    private String roleName;
    
    /**
     * 分類コード
     */
    private String bunruiCd;
    
    /**
     * 説明
     */
    private String description;
    
    /**
     * ロールソート順
     */
    private String roleSortSeq;
    
    /**
     * 分類名称
     */
    private String bunruiName;
    
    /**
     * 分類ソート順
     */
    private String bunruiSortSeq;

    /**
     * 選択状態
     */
    private boolean checkedRole;

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
     * 分類コードを取得します。
     * @return 分類コード
     */
    public String getBunruiCd() {
        return bunruiCd;
    }
    /**
     * 分類コードを設定します。
     * @param bunruiCd 分類コード
     */
    public void setBunruiCd(String bunruiCd) {
        this.bunruiCd = bunruiCd;
    }
    
    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getDescription() {
        return description;
    }
    /**
     * 説明を設定します。
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * ロールソート順を取得します。
     * @return ロールソート順
     */
    public String getRoleSortSeq() {
        return roleSortSeq;
    }
    /**
     * ロールソート順を設定します。
     * @param roleSortSeq ロールソート順
     */
    public void setRoleSortSeq(String roleSortSeq) {
        this.roleSortSeq = roleSortSeq;
    }
    
    /**
     * 分類名称を取得します。
     * @return 分類名称
     */
    public String getBunruiName() {
        return bunruiName;
    }
    /**
     * 分類名称を設定します。
     * @param bunruiName 分類名称
     */
    public void setBunruiName(String bunruiName) {
        this.bunruiName = bunruiName;
    }
    
    /**
     * 分類ソート順を取得します。
     * @return 分類ソート順
     */
    public String getBunruiSortSeq() {
        return bunruiSortSeq;
    }
    /**
     * 分類ソート順を設定します。
     * @param bunruiSortSeq 分類ソート順
     */
    public void setBunruiSortSeq(String bunruiSortSeq) {
        this.bunruiSortSeq = bunruiSortSeq;
    }

    /**
     * 選択状態を取得します。
     * @return 選択状態
     */
    public boolean isCheckedRole() {
		return checkedRole;
	}
    /**
     * 選択状態を設定します。
     * @param bunruiSortSeq 選択状態
     */
    public void setCheckedRole(boolean checkedRole) {
		this.checkedRole = checkedRole;
	}
}
