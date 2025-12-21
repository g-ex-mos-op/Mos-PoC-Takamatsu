/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.config.pllimitregist.entity.CtlPLLimit;
import jp.co.isid.mos.bird.config.pllimitregist.logic.GetPLLimitLogic;

/**
 * P/L上下限取得ロジック
 * 
 * @author xyuchida
 */
public class GetPLLimitLogicImpl implements GetPLLimitLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BCF005L01";

    /**
     * P/L上下限チェック改DAO
     */
    private CtlPLLimitDao ctlPLLimitDao;

    public CtlPLLimitDao getCtlPLLimitDao() {
        return ctlPLLimitDao;
    }
    public void setCtlPLLimitDao(CtlPLLimitDao ctlPLLimitDao) {
        this.ctlPLLimitDao = ctlPLLimitDao;
    }

    /**
     * P/L上下限取得
     * 
     * @param plType P/Lタイプ
     * @return P/L上下限リスト
     */
    public List execute(String plType) {

        // P/L上下限取得
        List plLimitList = getCtlPLLimitDao().selectPLLimit(plType);

        for (Iterator it = plLimitList.iterator(); it.hasNext();) {
            CtlPLLimit ctlPLLimit = (CtlPLLimit) it.next();

            // 設定タイプ未設定
            if (ctlPLLimit.getLimitType() == null || ctlPLLimit.getLimitType().trim().length() == 0) {
                // デフォルトとして金額を設定
                ctlPLLimit.setLimitType(PlLimitRegistConstants.LIMIT_TYPE_SUM);
            }
            // 構成比指定不可フラグON
            if (ctlPLLimit.getKoseihiFlg().equals(PlLimitRegistConstants.KOSEIHI_FLG_ON)) {
                // 強制的に金額を設定
                ctlPLLimit.setLimitType(PlLimitRegistConstants.LIMIT_TYPE_SUM);
            }

            // 整数値補正
            ctlPLLimit.setLimitMax(ctlPLLimit.getLimitMax().replaceAll("\\.00", ""));
            ctlPLLimit.setLimitMin(ctlPLLimit.getLimitMin().replaceAll("\\.00", ""));
        }

        return plLimitList;
    }
}
