package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnAbilityCheckResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryResultLogic;

/**
 * マスターライセンス能力チェック結果取得ロジック 
 * @author xnkusama
 */
public class GetAbilityCheckCategoryResultLogicImpl implements GetAbilityCheckCategoryResultLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L03";
    
    TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao;
    
    /**
     * マスターライセンス能力チェック結果取得
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public TrnAbilityCheckResult execute(MlResultRegistDto dto) throws ApplicationException {

        List listResult = getMlrrTrnAbilityCheckResultDao().getResult(
                dto.getEntryYear(),
                dto.getEntryKai(),
                dto.getSelectAbilityCheckStaffId());

        TrnAbilityCheckResult entityAbility = null;
        if (listResult != null && listResult.size() > 0) {
            entityAbility = (TrnAbilityCheckResult) listResult.get(0);
            if (isNull(entityAbility.getAssesser())) {
                //アセッサーにログインユーザー名称をセット
                entityAbility.setAssesser(dto.getLoginUserName());
            }
        }

        return entityAbility;
    }

    public TrnAbilityCheckResultDao getMlrrTrnAbilityCheckResultDao() {
        return mlrrTrnAbilityCheckResultDao;
    }

    public void setMlrrTrnAbilityCheckResultDao(
            TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao) {
        this.mlrrTrnAbilityCheckResultDao = mlrrTrnAbilityCheckResultDao;
    }

    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }
}