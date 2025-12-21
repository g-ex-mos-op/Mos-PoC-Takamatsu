/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * ユーザー一覧DTO
 *
 * @author xyuchida
 * 更新日:2009/04/23 xkinu ユーザー一覧CSV対応 CsvOutputDtoをimplementsしました。
 *
 */
public class RoleSearchUserListDto implements CsvOutputDto {

	/**
	 * コンストラクタ
	 *
	 */
	public RoleSearchUserListDto() {
		this(DEFAULT_MAX_PAGE_COUNT);
	}

	/**
	 * コンストラクタ
	 *
	 * @param maxPageCount ページ表示件数
	 */
	public RoleSearchUserListDto(int maxPageCount) {
		setMaxPageCount((maxPageCount > 0) ? maxPageCount : DEFAULT_MAX_PAGE_COUNT);
		setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
	}

	private static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
	private static final int DEFAULT_MAX_PAGE_COUNT = 30;

	/**
	 * 選択中ロールコード
	 */
	private String selectedRoleCd;

	/**
	 * ロール名称
	 */
	private String roleName;

	/**
	 * 分類名
	 */
	private String bunruiName;

	/**
	 * ユーザロールリスト
	 */
	private List userRoleList = new ArrayList();

	private int currentPageNumber;
	private int count;
	private int maxPageCount;

	/**
     * ロールコードを取得します。
     *
     * @return ロールコード
     */
	public String getSelectedRoleCd() {
		return selectedRoleCd;
	}

    /**
     * ロールコードを設定します。
     *
     * @param roleName ロールコード
     */
	public void setSelectedRoleCd(String selectedRoleCd) {
		this.selectedRoleCd = selectedRoleCd;
	}

	/**
     * ロール名称を取得します。
     *
     * @return ロール名称
     */
	public String getRoleName() {
		return roleName;
	}

    /**
     * ロール名称を設定します。
     *
     * @param roleName ロール名称
     */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
     * 分類名を取得します。
     *
     * @return 分類名
     */
	public String getBunruiName() {
		return bunruiName;
	}

    /**
     * 分類名を設定します。
     *
     * @param bunruiName 分類名
     */
	public void setBunruiName(String bunruiName) {
		this.bunruiName = bunruiName;
	}

	/**
     * ユーザロールリストを取得します。
     *
     * @return ユーザロールリスト
     */
	public List getUserRoleList() {
		return userRoleList;
	}

    /**
     * ユーザロールリストを設定します。
     *
     * @param userRoleList ユーザロールリスト
     */
	public void setUserRoleList(List userRoleList) {
		this.userRoleList = userRoleList;
	}

	/**
     * ユーザロールリスト件数を取得します。
     *
     * @return ユーザロールリスト件数
     */
	public int getUserRoleListSize() {
		return (userRoleList == null) ? 0 : userRoleList.size();
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		if (currentPageNumber >= getFirstPageNumber() && currentPageNumber <= getLastPageNumber()) {
			this.currentPageNumber = currentPageNumber;
		}
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		if (count >= 0) {
			this.count = count;
		}
	}

	public int getMaxPageCount() {
		return maxPageCount;
	}
	public void setMaxPageCount(int maxPageCount) {
		if (maxPageCount > 0) {
			this.maxPageCount = maxPageCount;
		}
	}

	public int getFirstPageNumber() {
		return 1;
	}
	public int getLastPageNumber() {
		return (getCount() == 0) ? 1 : (getCount() - 1) / getMaxPageCount() + 1;
	}
	public int getPreviousPageNumber() {
		return (isExistPreviousPage()) ? getCurrentPageNumber() - 1 : getCurrentPageNumber();
	}
	public int getNextPageNumber() {
		return (isExistNextPage()) ? getCurrentPageNumber() + 1 : getCurrentPageNumber();
	}
	public int getPageFirstRecordNumber() {
		return (getCurrentPageNumber() - 1) * getMaxPageCount();
	}
	public boolean isFirstPage() {
		return getCurrentPageNumber() == getFirstPageNumber();
	}
	public boolean isLastPage() {
		return getCurrentPageNumber() == getLastPageNumber();
	}
	public boolean isExistPreviousPage() {
		return getCurrentPageNumber() > getFirstPageNumber();
	}
	public boolean isExistNextPage() {
		return getCurrentPageNumber() < getLastPageNumber();
	}
	public List getDirectLinkList() {
		List directLinkList = new ArrayList();
		for (int i = getFirstPageNumber(), n = getLastPageNumber(); i <= n; i++) {
			directLinkList.add(new Integer(i));
		}
		return directLinkList;
	}
}
