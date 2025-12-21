/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.basicregist.dao;

import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryDate;

/**
 * エントリー日付管理（UIEntryDateDao）
 * @author itamoto
 */
public interface UIEntryDateDao {

    public static final Class BEAN = UIEntryDate.class;

    public static final String updateEntryDate_PERSISTENT_PROPS = "fromDt, toDt, lastUser, lastPgm";
    public static final String getEntryDate_ARGS  = "entryCd, entryYear, entryKai, usertypeCd, dayKbn";
    
    /**
     * エントリー日付管理の新規登録(insertEntryDate)
     * @param uiEntryDate
     */
    public int insertEntryDate(UIEntryDate uiEntryDate);

    /**
     * エントリー日付管理の更新(updateEntryDate)
     * @param uiEntryDate
     */
    public int updateEntryDate(UIEntryDate uiEntryDate);
    
    /**
     * エントリー日付管理の取得(getEntryDate)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param usertypeCd ユーザタイプコード
     * @param dayKbn 日付区分
     * @return UIEntryDate 検索結果
     */
    public UIEntryDate getEntryDate(String entryCd, String entryYear, String entryKai, String usertypeCd, String dayKbn);
}
