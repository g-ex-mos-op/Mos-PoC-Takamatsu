/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntrustInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferEntrustInfoDao {
    
    public static final Class BEAN = UIOfferEntrustInfo.class;
    
    public static final String getOfferEntrustInfo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
	/**
	 * 申込責任者情報取得
	 * @param entryCd　エントリーコード
	 * @param entryYear　エントリー年
	 * @param entryKai　エントリー回
	 * @param companyCd　会社コード
	 * @param onerCd　オーナーコード
	 * @return
	 */
    public UIOfferEntrustInfo getOfferEntrustInfo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
}
