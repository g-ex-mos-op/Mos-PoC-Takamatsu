/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.entity.CtlPLLimit;

/**
 * P/L上下限チェック改DAO
 * 
 * @author xyuchida
 */
public interface CtlPLLimitDao {

    public static final Class BEAN = CtlPLLimit.class;

    public static final String updatePLLimitList_PERSISTENT_PROPS = "limitType, limitMax, limitMin, lastUser, lastPgm";
    public static final String updatePLLimit_PERSISTENT_PROPS = "limitType, limitMax, limitMin, lastUser, lastPgm";

    /**
     * P/L上下限更新
     * 
     * @param plLimitList P/L上下限チェック改List
     */
    public void updatePLLimitList(List plLimitList);

    /**
     * P/L上下限更新
     * 
     * @param ctlPLLimit P/L上下限チェック改
     */
    public void updatePLLimit(CtlPLLimit ctlPLLimit);

    /**
     * P/L上下限取得
     * 
     * @param plType PLの種類
     * @return P/L上下限チェック改List
     */
    public List selectPLLimit(String plType);
}
