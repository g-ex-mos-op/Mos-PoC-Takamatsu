/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.action.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.action.RoleSearchAction;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchDto;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchRoleListDto;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchUserListDto;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.CtlRoleBunrui;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.commonform.rolesearch.logic.SearchRoleBunruiLogic;
import jp.co.isid.mos.bird.commonform.rolesearch.logic.SearchRoleLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xyuchida
 *
 */
public class RoleSearchActionImpl implements RoleSearchAction {

	// アクションID定義
	public static final String initialize_ACTION_ID = "BCO004A01";
	public static final String changeTab_ACTION_ID = "BCO004A02";
	public static final String select_ACTION_ID = "BCO004A03";
	public static final String cancel_ACTION_ID = "BCO004A04";

    /**
     * 初期処理フラグ
     */
    private boolean initialFlag;

	/**
	 * 選択タブ分類コード
	 */
	private String selectBunruiCd;

	/**
	 * 選択ロールコード
	 */
	private String selectRoleCd;

    /**
	 * ロール選択DTO
	 */
	private RoleSearchDto roleSearchDto;

	/**
	 * ロールリストDTO
	 */
	private RoleSearchRoleListDto roleSearchRoleListDto;

	/**
	 * ユーザリストDTO
	 */
	private RoleSearchUserListDto roleSearchUserListDto;

	/**
	 * ロール分類リスト取得ロジック
	 */
	private SearchRoleBunruiLogic searchRoleBunruiLogic;

	/**
	 * ロールリスト取得ロジック
	 */
	private SearchRoleLogic searchRoleLogic;

    /**
     * 初期処理フラグを取得します。
     * 
     * @return 初期処理フラグ
     */
    public boolean isInitialFlag() {
        return initialFlag;
    }

    /**
     * 初期処理フラグを設定します。
     * 
     * @param initialFlag 初期処理フラグ
     */
    public void setInitialFlag(boolean initialFlag) {
        this.initialFlag = initialFlag;
    }

	/**
     * 選択タブ分類コードを取得します。
     * 
     * @return 選択タブ分類コード
     */
	public String getSelectBunruiCd() {
		return selectBunruiCd;
	}

    /**
     * 選択タブ分類コードを設定します。
     * 
     * @param selectBunruiCd 選択タブ分類コード
     */
	public void setSelectBunruiCd(String selectBunruiCd) {
		this.selectBunruiCd = selectBunruiCd;
	}

	/**
     * 選択ロールコードを取得します。
     * 
     * @return 選択ロールコード
     */
	public String getSelectRoleCd() {
		return selectRoleCd;
	}

    /**
     * 選択ロールコードを設定します。
     * 
     * @param selectRoleCd 選択ロールコード
     */
	public void setSelectRoleCd(String selectRoleCd) {
		this.selectRoleCd = selectRoleCd;
	}
    /**
     * ロール選択DTOを取得します。
     * 
     * @return ロール選択DTO
     */
	public RoleSearchDto getRoleSearchDto() {
		return roleSearchDto;
	}

    /**
     * ロール選択DTOを設定します。
     * 
     * @param roleSearchDto ロール選択DTO
     */
	public void setRoleSearchDto(RoleSearchDto roleSearchDto) {
		this.roleSearchDto = roleSearchDto;
	}

	/**
     * ロールリストDTOを取得します。
     * 
     * @return ロールリストDTO
     */
	public RoleSearchRoleListDto getRoleSearchRoleListDto() {
		return roleSearchRoleListDto;
	}

    /**
     * ロールリストDTOを設定します。
     * 
     * @param roleSearchRoleListDto ロールリストDTO
     */
	public void setRoleSearchRoleListDto(
			RoleSearchRoleListDto roleSearchRoleListDto) {
		this.roleSearchRoleListDto = roleSearchRoleListDto;
	}

	/**
     * ユーザリストDTOを取得します。
     * 
     * @return ユーザリストDTO
     */
	public RoleSearchUserListDto getRoleSearchUserListDto() {
		return roleSearchUserListDto;
	}

    /**
     * ユーザリストDTOを設定します。
     * 
     * @param roleSearchUserListDto ユーザリストDTO
     */
	public void setRoleSearchUserListDto(
			RoleSearchUserListDto roleSearchUserListDto) {
		this.roleSearchUserListDto = roleSearchUserListDto;
	}

	/**
     * ロール分類リスト取得ロジックを取得します。
     * 
     * @return ロール分類リスト取得ロジック
     */
	public SearchRoleBunruiLogic getSearchRoleBunruiLogic() {
		return searchRoleBunruiLogic;
	}

    /**
     * ロール分類リスト取得ロジックを設定します。
     * 
     * @param searchRoleBunruiLogic ロール分類リスト取得ロジック
     */
	public void setSearchRoleBunruiLogic(
			SearchRoleBunruiLogic searchRoleBunruiLogic) {
		this.searchRoleBunruiLogic = searchRoleBunruiLogic;
	}

	/**
     * ロールリスト取得ロジックを取得します。
     * 
     * @return ロールリスト取得ロジック
     */
	public SearchRoleLogic getSearchRoleLogic() {
		return searchRoleLogic;
	}

    /**
     * ロールリスト取得ロジックを設定します。
     * 
     * @param searchRoleLogic ロールリスト取得ロジック
     */
	public void setSearchRoleLogic(SearchRoleLogic searchRoleLogic) {
		this.searchRoleLogic = searchRoleLogic;
	}

    /**
	 * 初期処理
	 * 
	 * @return 画面遷移情報
	 */
	public String initialize() {

		// 初期処理判定
		if (!isInitialFlag()) {

			// 初期化
			getRoleSearchRoleListDto().getResultRoleCdSet().clear();
			getRoleSearchUserListDto().getUserRoleList().clear();

			// 分類リスト取得
			List bunruiList = getSearchRoleBunruiLogic().execute();
			if (bunruiList == null || bunruiList.size() <= 0) {
				throw new FtlSystemException("ロール選択");
			}
			getRoleSearchRoleListDto().setBunruiList(bunruiList);

			// 先頭の分類を選択中分類コードとして保持
			CtlRoleBunrui ctlRoleBunrui = (CtlRoleBunrui) bunruiList.get(0);
			getRoleSearchRoleListDto().setSelectedBunruiCd(ctlRoleBunrui.getBunruiCd());

			// ロールリスト取得
			getRoleSearchRoleListDto().setRoleList(getSearchRoleLogic().execute(ctlRoleBunrui.getBunruiCd()));

			// 初期処理フラグ更新
	        setInitialFlag(true);
		}

		return null;
	}

	/**
	 * タブ切替
	 * 
	 * @return 画面遷移情報
	 */
	public String changeTab() {

		// タブ切替判定
		if (!getSelectBunruiCd().equals(getRoleSearchRoleListDto().getSelectedBunruiCd())) {

			// ロールチェック状態保存
			saveCheckState();

			// ロールリスト取得
			getRoleSearchRoleListDto().setRoleList(getSearchRoleLogic().execute(getSelectBunruiCd()));

			// ロール選択状態読込
			loadCheckState();

			// 今回選択された分類コードを選択中分類コードとして保持
			getRoleSearchRoleListDto().setSelectedBunruiCd(getSelectBunruiCd());

			// ユーザ一覧初期化
			getRoleSearchUserListDto().getUserRoleList().clear();
		}

		return null;
	}
	/**
	 * 決定
	 * 
	 * @return 画面遷移情報
	 */
	public String select() {

		// ロールチェック状態保存
		saveCheckState();

        // ロール選択を必須とする
        List roleList = Arrays.asList(getRoleSearchRoleListDto().getResultRoleCdSet().toArray());
        if (roleList == null || roleList.size() <= 0) {
            throw new NotNullException("ロール選択");
        }
		// ロール情報読込
        getRoleSearchDto().setResultRoleList(getSearchRoleLogic().execute(roleList));

        // アクションフラグ設定
        getRoleSearchDto().setActionFlg(true);

        // 呼出元画面へ遷移
        return getRoleSearchDto().getNavigationCase();
	}

	/**
	 * 取消
	 * 
	 * @return 画面遷移情報
	 */
	public String cancel() {

        // アクションフラグ設定
        getRoleSearchDto().setActionFlg(false);

        // 呼出元画面へ遷移
        return getRoleSearchDto().getNavigationCase();
	}

	/**
	 * ロールチェック状態保存
	 * 
	 */
	private void saveCheckState() {

		for (Iterator it = getRoleSearchRoleListDto().getRoleList().iterator(); it.hasNext();) {
			UIRole uIRole = (UIRole) it.next();
			if (uIRole.isCheckedRole()) {
				getRoleSearchRoleListDto().getResultRoleCdSet().add(uIRole.getRoleCd());
			} else {
				getRoleSearchRoleListDto().getResultRoleCdSet().remove(uIRole.getRoleCd());
			}
		}
	}

	/**
	 * ロールチェック状態読込
	 * 
	 */
	private void loadCheckState() {

		for (Iterator it = getRoleSearchRoleListDto().getRoleList().iterator(); it.hasNext();) {
			UIRole uIRole = (UIRole) it.next();
			if (getRoleSearchRoleListDto().getResultRoleCdSet().contains(uIRole.getRoleCd())) {
				uIRole.setCheckedRole(true);
			}
		}
	}
}
