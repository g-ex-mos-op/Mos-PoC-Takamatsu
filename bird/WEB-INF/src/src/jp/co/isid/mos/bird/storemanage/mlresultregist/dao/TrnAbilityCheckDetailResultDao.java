/*
 * 作成日: 2006/07/26
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckDetailResult;

/**
 * マスターライセンス能力チェック明細結果DAO
 * 
 * @author xyuchida
 */
public interface TrnAbilityCheckDetailResultDao {

    public static final Class BEAN = TrnAbilityCheckDetailResult.class;

    public static final String selectDetailResult_ARGS = "entryYear, entryKai, staffId";

    public static final String updateDetailResult_PERSISTENT_PROPS = "koumokuResult, lastUser, lastPgm";

    /**
     * 能力チェック明細結果取得
     * 
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param staffId スタッフID
     * @return 能力チェック明細結果List
     */
    public List selectDetailResult(String entryYear, String entryKai, String staffId);

    /**
     * 能力チェック明細結果登録
     * 
     * @param entity ML能力チェック明細結果
     */
    public void insertDetailResult(TrnAbilityCheckDetailResult entity);

    /**
     * 能力チェック明細結果更新
     * 
     * @param entity ML能力チェック明細結果
     */
    public void updateDetailResult(TrnAbilityCheckDetailResult entity);
}
