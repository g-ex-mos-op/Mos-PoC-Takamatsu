/**
 * 
 */
package jp.co.isid.mos.bird.commonform.rolesearch.action.impl;

import java.io.IOException;
import java.util.Iterator;

import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchRoleListDto;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchUserListDto;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 作成日:2009/04/23
 * @author xkinu
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl {
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = "BCO004A11";
	/**
	 * 選択ロールコード
	 */
	private String selectRoleCd;
	/**
	 * ユーザリストDTO
	 */
	private RoleSearchUserListDto roleSearchUserListDto;
	/**
	 * ロールリストDTO
	 */
	private RoleSearchRoleListDto roleSearchRoleListDto;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
		// 今回選択されたロールコードを選択中ロールコードとして保持
		getRoleSearchUserListDto().setSelectedRoleCd(getSelectRoleCd());
		// 分類名、ロール名設定
		for (Iterator it = getRoleSearchRoleListDto().getRoleList().iterator(); it.hasNext();) {
			UIRole uIRole = (UIRole) it.next();
			if (uIRole.getRoleCd().equals(getSelectRoleCd())) {
				getRoleSearchUserListDto().setBunruiName(uIRole.getBunruiName());
				getRoleSearchUserListDto().setRoleName(uIRole.getRoleName());
				break;
			}
		}

		// TODO 自動生成されたメソッド・スタブ
		super.downloadCsv();
	}
	/**
	 * @return roleSearchUserListDto を戻します。
	 */
	public RoleSearchUserListDto getRoleSearchUserListDto() {
		return roleSearchUserListDto;
	}
	/**
	 * @param roleSearchUserListDto を クラス変数roleSearchUserListDtoへ設定します。
	 */
	public void setRoleSearchUserListDto(RoleSearchUserListDto roleSearchUserListDto) {
		this.roleSearchUserListDto = roleSearchUserListDto;
	}
	/**
	 * @return selectRoleCd を戻します。
	 */
	public String getSelectRoleCd() {
		return selectRoleCd;
	}
	/**
	 * @param selectRoleCd を クラス変数selectRoleCdへ設定します。
	 */
	public void setSelectRoleCd(String selectRoleCd) {
		this.selectRoleCd = selectRoleCd;
	}
	/**
	 * @return roleSearchRoleListDto を戻します。
	 */
	public RoleSearchRoleListDto getRoleSearchRoleListDto() {
		return roleSearchRoleListDto;
	}
	/**
	 * @param roleSearchRoleListDto を クラス変数roleSearchRoleListDtoへ設定します。
	 */
	public void setRoleSearchRoleListDto(RoleSearchRoleListDto roleSearchRoleListDto) {
		this.roleSearchRoleListDto = roleSearchRoleListDto;
	}

}
