/*
 * 作成日: 2006/08/02
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnAbilityCheckResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryResultLogic;

/**
 * マスターライセンス能力チェック前回結果取得ロジック 
 * 
 * @author xyuchida
 */
public class GetAbilityCheckLastCategoryResultLogicImpl implements
        GetAbilityCheckCategoryResultLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L11";

    TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao;

    public TrnAbilityCheckResultDao getMlrrTrnAbilityCheckResultDao() {
        return mlrrTrnAbilityCheckResultDao;
    }
    public void setMlrrTrnAbilityCheckResultDao(
            TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao) {
        this.mlrrTrnAbilityCheckResultDao = mlrrTrnAbilityCheckResultDao;
    }

    /**
     * マスターライセンス能力チェック前回結果取得
     * 
     * @param mlResultRegistDto
     * @return 能力チェック前回結果
     */
    public TrnAbilityCheckResult execute(MlResultRegistDto mlResultRegistDto)
            throws ApplicationException {
        return getMlrrTrnAbilityCheckResultDao().getLastResult(
                mlResultRegistDto.getEntryYear(),
                mlResultRegistDto.getEntryKai(),
                mlResultRegistDto.getSelectAbilityCheckStaffId());
    }
}
