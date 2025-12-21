/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dto;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public class RoleSearchDto {

	/**
	 * 遷移元情報
	 */
    private String navigationCase;

    /**
     * アクションフラグ
     * 
     * true:決定 false:戻る
     */
    private boolean actionFlg;

    /**
	 * 結果ロール情報リスト
	 */
	private List resultRoleList;

	/**
     * 遷移元情報を取得します。
     * 
     * @return 遷移元情報
     */
	public String getNavigationCase() {
		return navigationCase;
	}

    /**
     * 遷移元情報を設定します。
     * 
     * @param navigationCase 遷移元情報
     */
	public void setNavigationCase(String navigationCase) {
		this.navigationCase = navigationCase;
	}

    /**
     * アクションフラグを取得します。
     * 
     * @return アクションフラグ
     */
    public boolean isActionFlg() {
        return actionFlg;
    }

    /**
     * アクションフラグを設定します。
     * 
     * @param actionFlg アクションフラグ
     */
    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
    }

	/**
     * 結果ロール情報リストを取得します。
     * 
     * @return 結果ロール情報リスト
     */
	public List getResultRoleList() {
		return resultRoleList;
	}

    /**
     * 結果ロール情報リストを設定します。
     * 
     * @param resultRoleList 結果ロール情報リスト
     */
	public void setResultRoleList(List resultRoleList) {
		this.resultRoleList = resultRoleList;
	}
}
