package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.common.dao.MstMiseDao;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 店舗マスタ取得
 * @author Aspac
 *
 */
public class GetMiseLogicImpl implements GetMiseLogic {

    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L08";
    
    
    private MstMiseDao mstMiseDao;

    /**
     * 店マスタ情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店マスタ情報
     */
    public MstMise execute(String companyCd, String miseCd) {
        
        //店舗コードチェック
        
        //必須チェック
        if (UriMaintenanceCommon.isNull(miseCd)) {
            throw new GenericMessageException("店舗コードは必須です。");
        }
        //半角数字
        CodeVerifier codeVerifier = new CodeVerifier();
        if (!codeVerifier.validate(miseCd)) {
            throw new GenericMessageException("店舗コードが不正です。");
        }
        
        return getMstMiseDao().selectMise(companyCd, miseCd);
    }

    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }

    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
}
