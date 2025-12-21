package jp.co.isid.mos.bird.entry.mlentry.dao;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryInfo;

/**
 * エントリー状況（UIEntryDao）
 * @author Aspac
 */
public interface UIEntryDao {

    public static final Class BEAN = UIEntryInfo.class;
    public static final String insertEntry_ARGS  = "uiEntryInfo";
    public static final String updateEntry_ARGS  = "uiEntryInfo";
    public static final String deleteEntry_ARGS  = "entryCd, entryYear, entryKai, staffId";
    public static final String selectEntryInfo_ARGS = "entryYear, entryKai, staffId";
    public static final String updateEntry_PERSISTENT_PROPS 
    = "entryCd, entryYear, entryKai, staffId, companyCd,"
    + "onerCd, examNo, entryPlaceCd, note, abilityChk, examChk, interviewChk,"
    + "empExpYear, empExpMonth, paExpYear, paExpMonth, job,"
    + "firstUser, firstPgm, firstTmsp, lastUser, lastPgm, lastTmsp";

    public static final String deleteEntry_SQL
    = "delete from BT23MLEJ where ENTRY_CD = /*entryCd*/'10' and ENTRY_YEAR = /*entryYear*/'2006' and ENTRY_KAI = /*entryKai*/'003' and STAFF_ID = /*staffId*/'99999901'";
    
    /**
     * マスターライセンスエントリー状況の新規登録
     * @param 
     */
    public int insertEntry(UIEntryInfo uiEntryInfo);

    /**
     * マスターライセンスエントリー状況の更新
     * @param 
     */
    public int updateEntry(UIEntryInfo uiEntryInfo);

    /**
     * マスターライセンスエントリー状況の削除
     * @param 
     */
    public int deleteEntry(String entryCd, String entryYear, String entryKai, String staffId);
    
    
    /**
     * マスターライセンスエントリー状況取得
     * @param
     */
    public UIEntryInfo selectEntryInfo(String entryYear, String entryKai, String staffId);
    
}
