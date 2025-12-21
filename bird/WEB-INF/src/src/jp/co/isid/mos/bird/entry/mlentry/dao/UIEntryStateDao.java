package jp.co.isid.mos.bird.entry.mlentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;

/**
 * マスターライセンスエントリー状況を取得する
 * @author Aspac
 */
public interface UIEntryStateDao {

    public static final Class BEAN = UIEntryState.class;
    public static final String getEntryState_ARGS  = "entryCd, entryYear, entryKai, companyCd, onerCd, entryYearKai, sysDate";
    public static final String insertEntry_ARGS = "uiEntryState";
    public static final String updateEntry_ARGS = "uiEntryState";
    public static final String deleteEntry_ARGS = "uiEntryState";
    public static final String updateEntry_PERSISTENT_PROPS 
            = "entryCd, entryYear, entryKai, staffId, onerCd, "
            + "empExpYear, empExpMonth, paExpYear, paExpMonth, job,"
            + "guideKbn, guideName, guideZip, guideAdrs1, guideAdrs2,"
            + "guideAdrs3, otherFlg1, otherFlg2, otherFlg3, bossName,"
            + "bossGroup, bossJobType, bossComment, lastUser, lastPgm";

    /**
     * マスターライセンスエントリー状況の新規登録
     * @param UIEntryState
     */
    public int insertEntry(UIEntryState uiEntryState);

    /**
     * マスターライセンスエントリー状況の更新
     * @param UIEntryState
     */
    public int updateEntry(UIEntryState uiEntryState);
    
    /**
     * マスターライセンスエントリー状況の削除
     * @param UIEntryState
     */
    public void deleteEntry(UIEntryState uiEntryState);

    
    /**
     * マスターライセンスエントリー状況の取得(getEntryState)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @param miseCd 店コード
     * @param sysdate システム日付
     * @return List 検索結果
     */
    public List getEntryState(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd, String entryYearKai, String sysdate);
    

}