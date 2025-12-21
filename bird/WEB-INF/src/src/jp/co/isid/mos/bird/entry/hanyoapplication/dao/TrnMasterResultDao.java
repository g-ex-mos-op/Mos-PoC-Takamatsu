/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.TrnMasterResult;



/**
 * マスターライセンス結果状況
 * @author itamoto
 */
public interface TrnMasterResultDao {

    public static final Class BEAN = TrnMasterResult.class;
    public static final String getCount_ARGS = "staffId";
    public static final String insertEntry_ARGS = "trnMasterResult";
    public static final String updateEntry_ARGS = "staffId";

    /**
     * マスターライセンス結果状況の新規登録(insertEntry)
     * @param trnMasterResult
     */
    public int insertEntry(TrnMasterResult trnMasterResult);

    /**
     * マスターライセンス結果状況の取得(getEntryDate)
     * @param staffId
     * @return int 検索結果
     */
    public int getCount(String staffId);

    /**
     * マスターライセンス結果状況の更新
     * 　　削除処理時にコース情報をクリアするのに使用する
     * @param staffId
     */
    public void updateEntry(String staffId);
}