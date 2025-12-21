/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferJoinDao {
    
    public static final Class BEAN = UIOfferJoinPersonInfo.class;
    
    public static final String deleteJoin_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    public static final String updateJoin_PERSISTENT_PROPS 
    		= " entryCd, entryYear, entryKai, companyCd, onerCd, tabNo, miseCd, lNameKj, fNameKj, " +
    		  " lNameKna, fNameKna, sex, age, jigyoFlg, konshinFlg, kyoeiFlg, shukuhakuFlg, " +
    		  " note, absenceReason, lastUser, lastPgm ";
    
    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertJoin(UIOfferJoinPersonInfo uiOfferJoinPersonInfo);

    /**
     * エントリーマスタ管理の更新(updateEntry)
     * @param uiEntryMst
     */
    public int updateJoin(UIOfferJoinPersonInfo uiOfferJoinPersonInfo);

    /**
     * エントリーマスタ管理の削除（論理削除）
     */
    public int deleteJoin(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}
