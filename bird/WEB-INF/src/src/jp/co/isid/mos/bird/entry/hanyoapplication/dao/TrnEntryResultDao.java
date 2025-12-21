package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.TrnEntryResult;

/**
 * 研修結果状況を取得する
 * @author kusama
 */
public interface TrnEntryResultDao {

    public static final Class BEAN = TrnEntryResult.class;
    public static final String getCount_ARGS = "entryCd, entryYear, entryKai, staffId";
    public static final String getNewInfo_ARGS = "entryCd, entryYear, staffId";
    public static final String insertEntry_ARGS = "trnEntryResult";
    public static final String deleteTrnEntryResult_ARGS = "entryCd, entryYear, entryKai, staffId";
    
    /**
     * 研修結果状況の新規登録(insertEntry)
     * @param trnEntryResult
     */
    public int insertEntry(TrnEntryResult trnEntryResult);

    /**
     * 研修結果状況の件数取得(getEntry)
     * @param entryCd エントリーコード
     * @param staffId スタッフID
     * @return int 検索結果
     */
    public int getCount(String entryCd, String entryYear, String entryKai, String staffId);

    /**
     * 研修結果状況の削除
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param staffId
     * @return
     */
    public int deleteTrnEntryResult(String entryCd, String entryYear, String entryKai, String staffId);
    
    /**
     * 指定年度の直近研修の結果取得
     */
    public List getNewInfo(String entryCd, String entryYear, String staffId);
}