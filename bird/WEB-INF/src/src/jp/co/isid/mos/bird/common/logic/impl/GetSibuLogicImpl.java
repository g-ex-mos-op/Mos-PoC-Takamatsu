/*
 * 作成日: 2006/04/20
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.logic.GetSibuLogic;

/**
 * 支部の取得
 * @author xytamura
 */
public class GetSibuLogicImpl implements GetSibuLogic {

    private MstSibuDao mstSibuDao;
    
    /**
     * 支部の取得
     * @param companyCd 企業コード
     * @param sibuCd 支部コード
     * @return 支部
     */
    public List execute(String companyCd, String sibuCd){
        List sibu = null;
        //企業コードと支部コードで取得
        if(companyCd != null && sibuCd != null){
            sibu = getMstSibuDao().getSibu(companyCd, sibuCd);
        //企業コードor全件取得
        }else{
            sibu =getMstSibuDao().getAllSibu(companyCd);                
        }
        return sibu;           
    }

    
    /**
     * @return mstSibuDao を戻します。
     */
    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }
    
    /**
     * @param mstSibuDao mstSibuDao を設定。
     */
    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }
}
