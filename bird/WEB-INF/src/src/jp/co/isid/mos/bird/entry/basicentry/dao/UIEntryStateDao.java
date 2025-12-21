package jp.co.isid.mos.bird.entry.basicentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;

/**
 * 研修エントリー状況を取得する
 * @author kusama
 */
public interface UIEntryStateDao {

    public static final Class BEAN = UIEntryState.class;
    public static final String getEntry_ARGS  = "entryCd, entryYear, entryKai, companyCd, onerCd";
    public static final String insertEntry_ARGS = "uiEntryState";
    public static final String updateEntry_ARGS = "uiEntryState";
    public static final String deleteEntry_ARGS = "uiEntryState";
//    public static final String updateEntry_PERSISTENT_PROPS 
//            = "entryCd, entryYear, entryKai, staffId, onerCd, "
//            + "empExpYear, empExpMonth, paExpYear, paExpMonth, job,"
//            + "guideKbn, guideName, guideZip, guideAdrs1, guideAdrs2,"
//            + "guideAdrs3, otherFlg1, otherFlg2, otherFlg3, bossName,"
//            + "bossGroup, bossJobType, bossComment, lastUser, lastPgm";

    /**
     * 研修エントリー状況の新規登録
     * @param UIEntryState
     */
    public int insertEntry(UIEntryState uiEntryState);

    /**
     * 研修エントリー状況の更新
     * @param UIEntryState
     */
    public int updateEntry(UIEntryState uiEntryState);
    
    /**
     * 研修エントリー状況の削除
     * @param UIEntryState
     */
    public void deleteEntry(UIEntryState uiEntryState);

    /**
     * 研修エントリー状況の取得(getEntry)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param staffId オーナーコード
     * @return List 検索結果
     */
    public List getEntry(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}