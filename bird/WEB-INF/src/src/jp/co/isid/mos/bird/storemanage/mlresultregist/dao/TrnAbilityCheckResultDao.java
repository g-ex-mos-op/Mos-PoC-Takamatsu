package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;

/**
 * マスターライセンス能力チェック結果
 * @author kusama
 */
public interface TrnAbilityCheckResultDao {

    public static final Class BEAN = TrnAbilityCheckResult.class;

    public static final String getResult_ARGS = "entryYear, entryKai, staffId";
    public static final String getLastResult_ARGS = "entryYear, entryKai, staffId";

    /**
     * エントリー者一覧の取得
     * @param entryCd
     * @param entryYear
     * @param staffId
     * @return
     */
    public List getResult(String entryYear, 
                           String entryKai,
                           String staffId);

    /**
     * 能力チェック前回結果取得
     * @param entryYear 今回エントリー年
     * @param entryKai 今回エントリー回
     * @param staffId スタッフID
     * @return 能力チェック前回結果
     */
    public TrnAbilityCheckResult getLastResult(String entryYear, String entryKai, String staffId);

    /**
     * 能力チェック結果登録
     * @param entity
     */
    public void insertResult(TrnAbilityCheckResult entity);

    /**
     * 能力チェック結果更新
     * @param entity
     */
    public void updateResult(TrnAbilityCheckResult entity);
}