/*
 * 作成日: 2006/06/07
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dto;

/**
 * @author xyuchida
 */
public class StaffRegistIFDto {

    /**
     * 編集モード
     *  = 1 : 新規
     *  = 2 : 更新
     */
    private int editMode;

    /**
     * スタッフID
     * 
     * 編集モード = 更新 の場合は必須
     */
    private String staffId;

    /**
     * 企業コード
     * 
     * 編集モード = 新規 の場合は必須
     */
    private String companyCd;

    /**
     * オーナーコード
     * 
     * 編集モード = 新規 の場合は必須
     */
    private String onerCd;

    /**
     * 初期処理フラグ
     */
    private boolean initialFlag;
// add start 2007/02/20 MLPh4 他画面からの遷移処理
    /**
     * 他画面遷移判断フラグ
     */
    private boolean otherScreenFlg;
// add end
    /**
     * 遷移元情報
     */
    private String navigationCase;
    /**
     * 「163:教育エントリー管理」ロールを所有するユーザが否か判断値
     * 
     * true:所有するユーザ(一括ダウンロードボタン表示)
     * false:所有しないユーザ(一括ダウンロードボタン非表示)
     */
    private boolean haveKyoikuRole = false;
    /**
     * アクションフラグ
     *  = true : 登録
     *  = false : 戻る
     */
    private boolean actionFlg;

    public int getEditMode() {
        return editMode;
    }
    public void setEditMode(int editMode) {
        this.editMode = editMode;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public boolean isInitialFlag() {
        return initialFlag;
    }
    public void setInitialFlag(boolean initialFlag) {
        this.initialFlag = initialFlag;
    }
    public String getNavigationCase() {
        return navigationCase;
    }
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }
    public boolean isActionFlg() {
        return actionFlg;
    }
    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
    }
//  add start 2007/02/20 MLPh4 他画面からの遷移処理
    public boolean isOtherScreenFlg() {
        return otherScreenFlg;
    }
    public void setOtherScreenFlg(boolean otherScreenFlg) {
        this.otherScreenFlg = otherScreenFlg;
    }
//    add end
	public boolean isHaveKyoikuRole() {
		return haveKyoikuRole;
	}
	public void setHaveKyoikuRole(boolean haveKyoikuRole) {
		this.haveKyoikuRole = haveKyoikuRole;
	}
}
