/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xyuchida
 *
 */
public class RoleSearchRoleListDto {

	/**
	 * 選択中タブ分類コード
	 */
	private String selectedBunruiCd;

    /**
	 * 結果ロールコードセット
	 */
	private Set resultRoleCdSet= new HashSet();
	/**
	 * 分類リスト
	 */
	private List bunruiList;

	/**
	 * ロールリスト
	 */
	private List roleList;

	/**
     * 選択中タブ分類コードを取得します。
     *
     * @return 選択中タブ分類コード
     */
	public String getSelectedBunruiCd() {
		return selectedBunruiCd;
	}

    /**
     * 選択中タブ分類コードを設定します。
     *
     * @param selectedBunruiCd 選択中タブ分類コード
     */
	public void setSelectedBunruiCd(String selectedBunruiCd) {
		this.selectedBunruiCd = selectedBunruiCd;
	}

	/**
     * 結果ロールコードセットを取得します。
     *
     * @return 結果ロールコードセット
     */
	public Set getResultRoleCdSet() {
		return resultRoleCdSet;
	}

    /**
     * 結果ロールコードセットを設定します。
     *
     * @param resultRoleCdSet 結果ロールコードセット
     */
	public void setResultRoleCdSet(Set resultRoleCdSet) {
		this.resultRoleCdSet = resultRoleCdSet;
	}

	/**
     * 分類リストを取得します。
     *
     * @return 分類リスト
     */
	public List getBunruiList() {
		return bunruiList;
	}

    /**
     * 分類リストを設定します。
     *
     * @param bunruiList 分類リスト
     */
	public void setBunruiList(List bunruiList) {
		this.bunruiList = bunruiList;
	}

	/**
     * ロールリストを取得します。
     *
     * @return ロールリスト
     */
	public List getRoleList() {
		return roleList;
	}

    /**
     * ロールリストを設定します。
     *
     * @param roleList ロールリスト
     */
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}
}
