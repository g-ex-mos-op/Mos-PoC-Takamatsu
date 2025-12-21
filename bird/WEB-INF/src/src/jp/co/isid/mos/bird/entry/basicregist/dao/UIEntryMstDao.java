/*
 * 作成日: 2006/5/31
 */
package jp.co.isid.mos.bird.entry.basicregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryMst;

/**
 * エントリーマスタ管理（UIEntryMstDao）
 * @author xyuchida
 */
public interface UIEntryMstDao {

    public static final Class BEAN = UIEntryMst.class;

    public static final String updateEntry_PERSISTENT_PROPS 
            = "entryTitle, entryPlace, numberLimit, placeLimit,"
            + "note, spareFlg1, spareFlg2, sakujoFlg, lastUser, lastPgm";
    public static final String getEntry_ARGS = "entryCd, fromYear";
    public static final String getEntryCount_ARGS = "entryCd, fromYear";
    public static final String getMaxEntryKai_ARGS  = "entryCd, entryYear";
    public static final String getMaxEntryKai_SQL
            = "select decimal(max(ENTRY_KAI)) from BR17ENTL where ENTRY_CD = /*entryCd*/'01' and ENTRY_YEAR = /*entryYear*/'2006'";

    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertEntry(UIEntryMst uiEntryMst);

    /**
     * エントリーマスタ管理の更新(updateEntry)
     * @param uiEntryMst
     */
    public int updateEntry(UIEntryMst uiEntryMst);

    /**
     * エントリーマスタ管理の取得(getEntry)
     * @param entryCd エントリーコード
     * @param fromYear 抽出対象年FROM
     * @return List 検索結果
     */
    public List getEntry(String entryCd, String fromYear);

    /**
     * エントリーマスタ管理の件数取得(getEntryCount)
     * @param entryCd エントリーコード
     * @param fromYear 抽出対象年FROM
     * @return int 件数
     */
    public int getEntryCount(String entryCd, String fromYear);

    /**
     * エントリー回のMAX値取得(getMaxEntryKai)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @return int エントリ回MAX値
     */
    public int getMaxEntryKai(String entryCd, String entryYear);
}
