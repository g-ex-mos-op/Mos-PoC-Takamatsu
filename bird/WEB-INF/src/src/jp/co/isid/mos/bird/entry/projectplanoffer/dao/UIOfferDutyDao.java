/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferDutyPersonInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferDutyDao {
    
    public static final Class BEAN = UIOfferDutyPersonInfo.class;
    
    public static final String insertDuty_ARGS = "uiOfferDutyPersonInfo";
    public static final String updateDuty_ARGS = "uiOfferDutyPersonInfo";
    public static final String deleteDuty_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd, atesakiKbn";
    
    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertDuty(UIOfferDutyPersonInfo uiOfferDutyPersonInfo);

    /**
     * エントリーマスタ管理の更新(updateEntry)
     * @param uiEntryMst
     */
    public int updateDuty(UIOfferDutyPersonInfo uiOfferDutyPersonInfo);

    /**
     * エントリーマスタ管理の削除（論理削除）
     */
    public int deleteDuty(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String atesakiKbn);

}
