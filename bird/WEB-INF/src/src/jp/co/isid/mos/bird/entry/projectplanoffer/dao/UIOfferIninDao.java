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
public interface UIOfferIninDao {
    
    public static final Class BEAN = UIOfferEntrustInfo.class;

    public static final String deleteInin_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    public static final String updateInin_PERSISTENT_PROPS 
		= " entryCd, entryYear, entryKai, companyCd, onerCd, ininMiseCd, ininMiseTotal, ininFName, ininLName, " +
			" lastUser, lastPgm ";
    
    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertInin(UIOfferEntrustInfo uiOfferEntrustInfo);

    /**
     * エントリーマスタ管理の更新(updateEntry)
     * @param uiEntryMst
     */
    public int updateInin(UIOfferEntrustInfo uiOfferEntrustInfo);

    /**
     * エントリーマスタ管理の削除（論理削除）
     */
    public int deleteInin(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}
