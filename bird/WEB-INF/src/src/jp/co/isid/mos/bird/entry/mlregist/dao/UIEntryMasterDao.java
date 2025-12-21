/*
 * 作成日: 2006/5/31
 */
package jp.co.isid.mos.bird.entry.mlregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;

/**
 * エントリーマスタ管理DAO
 * 
 * @author xyuchida
 */
public interface UIEntryMasterDao {

    public static final Class BEAN = UIEntryMaster.class;

    public static final String updateEntry_PERSISTENT_PROPS 
            = "entryTitle, entryPlace, numberLimit, placeLimit, note, spareFlg1, spareFlg2, sakujoFlg, lastUser, lastPgm";
    public static final String getEntryMasterList_ARGS = "entryCd, fromYear, toYear";
    public static final String getEntryMasterCount_ARGS = "entryCd, fromYear, toYear";
    public static final String getMaxEntryKai_ARGS  = "entryCd, entryYear";
    public static final String getMaxEntryKai_SQL
            = "select decimal(max(ENTRY_KAI)) from BR17ENTL where ENTRY_CD = /*entryCd*/'01' and ENTRY_YEAR = /*entryYear*/'2006'";

    /**
     * エントリーマスタ管理の登録
     * @param uiEntryMaster エントリーマスタ管理Entity
     */
    public int insertEntry(UIEntryMaster uiEntryMaster);

    /**
     * エントリーマスタ管理の更新
     * @param uiEntryMaster エントリーマスタ管理Entity
     */
    public int updateEntry(UIEntryMaster uiEntryMaster);

    /**
     * エントリーマスタ管理の取得
     * @param entryCd エントリーコード
     * @param fromYear 抽出対象年FROM
     * @param toYear 抽出対象年TO
     * @return List 検索結果
     */
    public List getEntryMasterList(String entryCd, String fromYear, String toYear);

    /**
     * エントリーマスタ管理の件数取得
     * @param entryCd エントリーコード
     * @param fromYear 抽出対象年FROM
     * @param toYear 抽出対象年TO
     * @return int 件数
     */
    public int getEntryMasterCount(String entryCd, String fromYear, String toYear);

    /**
     * エントリー回最大値取得
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @return int エントリ回最大値
     */
    public int getMaxEntryKai(String entryCd, String entryYear);
}
