/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryDate;



/**
 * エントリー日付管理（UIEntryDateDao）
 * @author kusama
 */
public interface UIEntryDateDao {

    public static final Class BEAN = UIEntryDate.class;
    public static final String getKey_ARGS  = "entryCd, sysDate, sysDatePlusOne, userTypeCd";
    public static final String getEntryDate_ARGS  = "entryCd, joinCharacterList, userTypeCd, sysDate";

    /**
     * 表示可能データKey項目の取得(getKey)
     * @param entryCd エントリーコード
     * @param sysDate システム日付
     * @param sysDatePlusOne システム日付＋１
     * @param userTypeCd ユーザータイプコード
     * @return List 検索結果
     */
    public List getKey(String entryCd, String sysDate, String sysDatePlusOne, String userTypeCd);

    /**
     * エントリー日付管理の取得(getEntryDate)
     * @param entryCd エントリーコード
     * @param joinCharacterList 連結文字(エントリー年 + エントリー回)　←複数
     * @param userTypeCd ユーザータイプコード
     * @param sysDate システム日付
     * @return List 検索結果
     */
    public List getEntryDate(String entryCd, List joinCharacterList, String userTypeCd, String sysDate);
}