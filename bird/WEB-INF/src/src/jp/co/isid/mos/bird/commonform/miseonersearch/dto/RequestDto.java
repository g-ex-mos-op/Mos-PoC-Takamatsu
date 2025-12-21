/**
 * 
 */
package jp.co.isid.mos.bird.commonform.miseonersearch.dto;

import java.util.List;

/**
 * [共通]総合検索の店・オーナー検索画面
 * Request用DTO
 * 
 * 作成日:2008/11/20
 * @author xkinu
 *
 */
public class RequestDto {
	/**
	 * 自画面SessionDTO
	 */
	private SessionDto miseOnerSearchSesDto;
    /** ウィンドウID */
    private int windowId;
    /**
     * [条件項目]メニューコード
     */ 
    private String searchWord;
    /**
     * 決定対象メニュー情報
     */
    private List listSearchData = null;
    
    private int selectedIndex=-1;
    
	/**
	 * @return selectedIndex を戻します。
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex を クラス変数selectedIndexへ設定します。
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}
	/**
	 * @param listSearchData を クラス変数listSearchDataへ設定します。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * クリア処理
	 *
	 */
	public void clear() {
		setSearchWord(null);
		setListSearchData(null);
	}
	/**
	 * @return searchWord を戻します。
	 */
	public String getSearchWord() {
		return searchWord;
	}
	/**
	 * @param searchWord を クラス変数searchWordへ設定します。
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * 検索結果件数取得処理
	 * 
	 * @return
	 */
	public int getSearchedDataCnt() {
		if(getListSearchData() != null) {
			return getListSearchData().size();
		}
		return 0;
	}
	/**
	 * @return userTypeCd を戻します。
	 */
	public String getUserTypeCd() {
		return getMiseOnerSearchSesDto().getBirdUserInfo().getMstUser().getUserTypeCd();
	}
	/**
	 * @return UserId を戻します。
	 */
	public String getUserId() {
		return getMiseOnerSearchSesDto().getBirdUserInfo().getUserID();
	}
	/**
	 * @return sysDate を戻します。
	 */
	public String getSysDate() {
		return getMiseOnerSearchSesDto().getBirdDateInfo().getSysDate();
	}
	/**
	 * @return userTypeCd を戻します。
	 */
	public boolean isLimitFlg() {
		return getMiseOnerSearchSesDto().getBirdUserInfo().isLimit();
	}
	/**
	 * @return miseOnerSearchSesDto を戻します。
	 */
	public SessionDto getMiseOnerSearchSesDto() {
		return miseOnerSearchSesDto;
	}
	/**
	 * @param miseOnerSearchSesDto を クラス変数miseOnerSearchSesDtoへ設定します。
	 */
	public void setMiseOnerSearchSesDto(SessionDto miseOnerSearchSesDto) {
		this.miseOnerSearchSesDto = miseOnerSearchSesDto;
	}
}
