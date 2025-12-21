/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryDate;


/**
 * エントリー日付管理（UIEntryDateDao）
 * @author itamoto
 */
public interface UIEntryDateDao {

    public static final Class BEAN = UIEntryDate.class;
    public static final String getEntryDate_ARGS  = "entryCd, joinCharacterList";
    public static final String insertEntryDate_ARGS  = "uiEntryDate";
    public static final String updateEntryDate_ARGS  = "uiEntryDate";
    public static final String deleteEntryDate_ARGS  = "uiEntryDate";
    public static final String updateEntryDate_PERSISTENT_PROPS = "fromDt, toDt, lastUser, lastPgm";

    //public static final String getKey_ARGS  = "entryCdList, fromYear, toYear";
    public static final String getKey_ARGS  = "entryCdList, fromYear";

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
     * エントリー日付管理の削除（論理削除）
     */
    public int deleteEntryDate(UIEntryDate uiEntryDate);

    
//---20070115 マスタライセンスPh4対応---start---
//    /**
//     * 表示可能データKey項目の取得(getKey)
//     * @param entryCdList エントリーコードリスト
//     * @param fromYear 表示範囲From年
//     * @param toYear 表示範囲To年
//     * @return List 検索結果
//     */
//    public List getKey(List entryCdList, String fromYear, String toYear);

    /**
     * 表示可能データKey項目の取得(getKey)
     * @param entryCdList エントリーコードリスト
     * @param fromYear 表示範囲From年
     * @return List 検索結果
     */
    public List getKey(List entryCdList, String fromYear);
//  ---20070115 マスタライセンスPh4対応--- end ---
    
    /**
     * エントリー日付管理の取得(getEntryDate)
     * @param entryCd エントリーコード
     * @param joinCharacterList 連結文字(エントリー年 + エントリー回)　←複数
     * @return List 検索結果
     */
    public List getEntryDate(String entryCd, List joinCharacterList);
}
