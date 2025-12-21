/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntryState;

/**
 * @author xlee
 *
 */
public interface UIOfferEntryStateDao {
    
    public static final Class BEAN = UIOfferEntryState.class;
    
    public static final String deleteEntryState_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertEntryState(UIOfferEntryState uiOfferEntryState);


    /**
     * エントリーマスタ管理の削除（論理削除）
     */
    public int deleteEntryState(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}
