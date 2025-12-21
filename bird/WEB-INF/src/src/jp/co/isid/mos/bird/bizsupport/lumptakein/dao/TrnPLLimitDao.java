/*
 * 作成日: 2006/03/20
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLLimit;

/**
 * P/Lデータ上下限値DAO
 * 
 * @author xyuchida
 */
public interface TrnPLLimitDao {

    public static final Class BEAN = TrnPLLimit.class;

    public static final String getPLLimit_SQL
        = "select rtrim(BT19.COLUMN_NAME) as COLUMN_NAME, BT19.LIMIT_FLG, BT19.LIMIT from BT19PLLM as BT19";

    /**
     * P/Lデータ上下限値取得
     * @return P/Lデータ上下限値リスト
     */
    public List getPLLimit();
}
