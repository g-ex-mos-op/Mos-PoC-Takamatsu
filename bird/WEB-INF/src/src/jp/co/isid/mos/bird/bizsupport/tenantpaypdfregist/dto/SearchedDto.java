/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;


/**
 * テナント入金明細登録画面
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class SearchedDto extends RequestDto implements DownloadDto {
    /**
     * テナント入金明細PDF情報一覧
     */
    private List listSearchData = null;
    
	/**
	 * クリア処理
	 *
	 */
	public void clear() {
		super.clear();
		setSelectedIndex(-1);
		setDownloadIndex(0);
		setListSearchData(null);
	}
    /**
     * セッション情報から検索情報取得
     *
     */
    public void gettingSearchData() {
    	if(getSessionDto().isSearched(getWindowId())) {
	    	setMiseCd(getSessionDto().getMiseCd(getWindowId()));
	    	setTaishoYm(getSessionDto().getTaishoYm(getWindowId()));
	    	setKaisuu(getSessionDto().getKaisuu(getWindowId()));
	    	setListSearchData(getSessionDto().getListSearchData());
    	}
    }
    /**
     * リクエスト情報から検索情報取得
     *
     */
    public void copySearchData(RequestDto requestDto) {
    	setWindowId(requestDto.getWindowId());
    	setMiseCd(requestDto.getMiseCd());
    	setTaishoYm(requestDto.getTaishoYm());
    	setKaisuu(requestDto.getKaisuu());
    }
	/**
	 * テナント入金明細PDF情報一覧取得処理
	 * 
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}

	/**
	 * テナント入金明細PDF情報一覧設定処理
	 * 
	 * @param listSearchData を クラス変数listSearchDataへ設定します。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * テナント入金明細PDF情報一覧の結果有無判断処理
	 * 
	 * @return boolean true:結果有り　false:結果無し
	 */
	public boolean isExistSearchedData() {
		return (getListSearchData() != null 
				&& getListSearchData().isEmpty() == false);
	}
	public void setSearched (boolean searched) {
		getSessionDto().setSearched(getWindowId(), searched);
	}
	public boolean isSearched () {
		return getSessionDto().isSearched(getWindowId());
	}
	public void settingSearchedData() {
		getSessionDto().setMiseCd(getWindowId(), getMiseCd());
		getSessionDto().setTaishoYm(getWindowId(), getTaishoYm());
		getSessionDto().setKaisuu(getWindowId(), getKaisuu());
	}
	public void removeSearchedData() {
		getSessionDto().setMiseCd(getWindowId(), null);
		getSessionDto().setTaishoYm(getWindowId(), null);
		getSessionDto().setKaisuu(getWindowId(), null);
	}
}
