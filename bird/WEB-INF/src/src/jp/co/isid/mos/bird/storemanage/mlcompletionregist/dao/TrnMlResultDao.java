/*
 * 作成日: 2006/08/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.TrnMlResult;

/**
 * マスターライセンス結果状況履歴DAO
 * 
 * @author xyuchida
 */
public interface TrnMlResultDao {

    public static final Class BEAN = TrnMlResult.class;

    public static final String selectReservation_ARGS = "staffId, entryYearBefore";
    public static final String updateWaitForIssue_ARGS = "entity";

    /**
     * 保留レコード取得
     * 
     * @param staffId スタッフID
     * @param entryYearBefore エントリー年 - 1
     * @return 保留レコード
     */
    public TrnMlResult selectReservation(String staffId, String entryYearBefore);

    /**
     * ライセンス発行待ち更新
     * 
     * @param entity マスターライセンス結果状況履歴Entity
     */
    public void updateWaitForIssue(TrnMlResult entity);
}
