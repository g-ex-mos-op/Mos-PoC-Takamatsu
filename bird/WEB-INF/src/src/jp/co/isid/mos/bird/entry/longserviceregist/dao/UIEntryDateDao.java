/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryDate;


/**
 * エントリー日付管理（UIEntryDateDao）
 * @author itamoto
 */
public interface UIEntryDateDao {

    public static final Class BEAN = UIEntryDate.class;
    
    /**
     * 永年勤続日付マスタ情報リスト取得時のパラメータ
     */
    public static final String getEntryDate_ARGS = "entryCd, entryYear, entryKai";

    /**
     * エントリー日付管理の新規登録パラメータ
     */
    public static final String insertEntryDate_ARGS = "uiEntryDate";
    
    /**
     * エントリー日付管理の更新パラメータ
     */
    public static final String update_PERSISTENT_PROPS = "fromDt, ToDt, lastUser, lastPgm";
    
    /**
     * エントリー日付管理の更新パラメータ
     */
    public static final String updateEntryDateKey_ARGS = "uiEntryDate,newEntryYear,newEntryKai,lastTmsp";
    
    /**
     * エントリー日付管理の取得
     * @param entryCd エントリーコード
     * @param entryYear 
     * @param entryKai 
     * @return List 検索結果
     */
    public List getEntryDate(String entryCd, String entryYear, String entryKai);

    /**
     * エントリー日付管理の新規登録
     * @param uiEntryDate
     */
    public int insertEntryDate(UIEntryDate uiEntryDate);

    /**
     * エントリー日付管理の更新
     * @param uiEntryDate
     */
    public int update(UIEntryDate uiEntryDate);
    
    /**
     * エントリー日付管理の更新（キーを更新する場合）
     * @param uiEntryDate
     * @param oldEntryYear
     * @param oldEntryKai
     */
    public int updateEntryDateKey(UIEntryDate uiEntryDate,String newEntryYear,String newEntryKai,Timestamp lastTmsp);
}
