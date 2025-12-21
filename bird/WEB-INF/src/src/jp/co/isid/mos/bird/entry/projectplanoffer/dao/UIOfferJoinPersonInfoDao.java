/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferJoinPersonInfoDao {
    
    public static final Class BEAN = UIOfferJoinPersonInfo.class;
    
    public static final String getOfferJoinPersonInfo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
	/**
	 * 申込責任者情報取得
	 * @param entryCd　エントリーコード
	 * @param entryYear　エントリー年
	 * @param entryKai　エントリー回
	 * @param companyCd　会社コード
	 * @param onerCd　オーナーコード
	 * @return
	 */
    public List getOfferJoinPersonInfo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
}
