package jp.co.isid.mos.bird.bizreport.common.dto;

import jp.co.isid.mos.bird.common.code.TaishoJoken;

/**
 * 日報共通DTO【結果情報】クラス
 * 
 * 作成日:2013/01/17
 * @author xkinu
 *
 */
public class NipoRefResultDto {
 
	private String searchListType = "";
    /**
     * 対象店舗数
     */
    private int tenpoCount = 0;

    /** 換算済判断フラグ 2013/02追加 */
    private boolean kansanFlg = false;
    /**
     * 該当データ存在判断フラグ
     */
    private boolean existDataFlg = false;
    
    /**
     * 対象店舗数を取得します。
     * @return 対象店舗数
     */
    public int getTenpoCount() {
        return tenpoCount;
    }
 
    /**
     * 対象店舗数を設定します。
     * @param tenpoCount 対象店舗数
     */
    public void setTenpoCount(int tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
    /**
     * 支部別売上情報クリア
     */
    public void clear() {
        setTenpoCount(0);
        setExistDataFlg(false);
        setKansanFlg(false);
    }
	/**
	 * @return existDataFlg を戻します。
	 */
	public boolean isExistDataFlg() {
		return existDataFlg;
	}

	/**
	 * @param existDataFlg 設定する existDataFlg。
	 */
	public void setExistDataFlg(boolean existDataFlg) {
		this.existDataFlg = existDataFlg;
	}

	/**
	 * @return クラス変数searchType を戻します。
	 */
	public String getSearchListType() {
		return searchListType;
	}

	/**
	 * 検索タイプを設定します。
	 * jp.co.isid.mos.bird.common.code.TaishoJokenの定数支部と店舗を設定すると、
	 * isSibuList()やisMiseList()のメソッドで判断できます。
	 * 
	 * @param searchListType を クラス変数searchTypeへ設定します。
	 */
	public void setSearchListType(String searchType) {
		this.searchListType = searchType;
	}
	/**
	 * 支部一覧データか否か
	 * jp.co.isid.mos.bird.common.code.TaishoJokenの定数支部使用
	 * @return
	 */
	public boolean isSearchTypeSibuList() {
		return TaishoJoken.CODE_SIBU.equals(getSearchListType());
	}
	/**
	 * 店舗一覧データか否か
	 * jp.co.isid.mos.bird.common.code.TaishoJokenの定数店舗使用
	 * @return
	 */
	public boolean isSearchTypeMiseList() {
		return TaishoJoken.CODE_MISE.equals(getSearchListType());
	}

	/**
	 * 換算済判断フラグ
	 * @param kansanFlg を クラス変数kansanFlgへ設定します。
	 */
	public void setKansanFlg(boolean kansanFlg) {
		this.kansanFlg = kansanFlg;
	}

	/**
	 * 換算済判断フラグ
	 * @return クラス変数kansanFlg を戻します。
	 */
	public boolean isKansanFlg() {
		return kansanFlg;
	}
}