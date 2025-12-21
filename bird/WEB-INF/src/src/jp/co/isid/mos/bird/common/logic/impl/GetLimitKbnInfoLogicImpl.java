package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.CodInfoLimitInfoDao;
import jp.co.isid.mos.bird.common.logic.GetLimitKbnInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 情報制限区分の取得ロジック
 * @author xnkusama
 */
public class GetLimitKbnInfoLogicImpl implements GetLimitKbnInfoLogic {

    /* 情報制限区分DAO */
    private CodInfoLimitInfoDao codInfoLimitInfoDao;
    
    /**
     * 情報制限区分を取得する
     * @return List
     * @exception ApplicationException 
     */
    public List execute() throws ApplicationException {
        // 情報制限区分
        List listLimitInfo = getCodInfoLimitInfoDao().getInfoLimitKbn();

        if (listLimitInfo == null || listLimitInfo.size() == 0) {
            throw new NotExistException("情報制限");
        }
        
        return listLimitInfo;
    }

    /**
     * 情報制限区分DAO設定処理
     * @param codInfoLimitInfoDao codInfoLimitInfoDao を設定。
     */
    public void setCodInfoLimitInfoDao(CodInfoLimitInfoDao codInfoLimitInfoDao) {
        this.codInfoLimitInfoDao = codInfoLimitInfoDao;
    }

    /**
     * 情報制限区分DAO取得処理
     * @return codInfoLimitInfoDao を戻します。
     */
    public CodInfoLimitInfoDao getCodInfoLimitInfoDao() {
        return codInfoLimitInfoDao;
    }   
}