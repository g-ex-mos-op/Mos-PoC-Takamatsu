package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnAbilityCheckDetailResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckMeisaiInfoLogic;

/**
 * 能力チェック結果情報明細取得ロジック 
 * @author xnkusama
 */
public class GetAbilityCheckMeisaiInfoLogicImpl implements GetAbilityCheckMeisaiInfoLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L04";
    
    // マスターライセンス能力チェック明細結果DAO
    private TrnAbilityCheckDetailResultDao trnAbilityCheckDetailResultDao;

    public TrnAbilityCheckDetailResultDao getTrnAbilityCheckDetailResultDao() {
        return trnAbilityCheckDetailResultDao;
    }
    public void setTrnAbilityCheckDetailResultDao(
            TrnAbilityCheckDetailResultDao trnAbilityCheckDetailResultDao) {
        this.trnAbilityCheckDetailResultDao = trnAbilityCheckDetailResultDao;
    }

    /**
     * 能力チェック結果情報明細を取得する
     * 
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public List execute(MlResultRegistDto dto) throws ApplicationException {
        // マスターライセンス能力チェック明細結果取得
        return getTrnAbilityCheckDetailResultDao().selectDetailResult(
                dto.getEntryYear(), dto.getEntryKai(), dto.getSelectAbilityCheckStaffId());
    }
}
