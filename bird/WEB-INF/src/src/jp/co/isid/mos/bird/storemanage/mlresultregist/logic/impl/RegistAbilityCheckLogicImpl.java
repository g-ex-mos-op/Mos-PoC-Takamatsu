/*
 * 作成日: 2006/07/31
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;

import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnAbilityCheckDetailResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnAbilityCheckResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckDetailResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistAbilityCheckLogic;

/**
 * 能力チェック結果登録ロジック
 * 
 * @author xyuchida
 */
public class RegistAbilityCheckLogicImpl implements RegistAbilityCheckLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L13";

    private UIMlResultDao mlrrUIMlResultDao;
    private TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao;
    private TrnAbilityCheckDetailResultDao mlrrTrnAbilityCheckDetailResultDao;

    public UIMlResultDao getMlrrUIMlResultDao() {
        return mlrrUIMlResultDao;
    }
    public void setMlrrUIMlResultDao(UIMlResultDao mlrrUIMlResultDao) {
        this.mlrrUIMlResultDao = mlrrUIMlResultDao;
    }
    public TrnAbilityCheckResultDao getMlrrTrnAbilityCheckResultDao() {
        return mlrrTrnAbilityCheckResultDao;
    }
    public void setMlrrTrnAbilityCheckResultDao(
            TrnAbilityCheckResultDao mlrrTrnAbilityCheckResultDao) {
        this.mlrrTrnAbilityCheckResultDao = mlrrTrnAbilityCheckResultDao;
    }
    public TrnAbilityCheckDetailResultDao getMlrrTrnAbilityCheckDetailResultDao() {
        return mlrrTrnAbilityCheckDetailResultDao;
    }
    public void setMlrrTrnAbilityCheckDetailResultDao(
            TrnAbilityCheckDetailResultDao mlrrTrnAbilityCheckDetailResultDao) {
        this.mlrrTrnAbilityCheckDetailResultDao = mlrrTrnAbilityCheckDetailResultDao;
    }

    /**
     * 能力チェック結果登録
     * 
     * @param mlResultRegistDto
     */
    public void execute(MlResultRegistDto mlResultRegistDto) {

        // キー項目取得
        String staffId = mlResultRegistDto.getSelectAbilityCheckStaffId();
        String entryYear = mlResultRegistDto.getEntryYear();
        String entryKai = mlResultRegistDto.getEntryKai();
        // 共通情報取得
        String userId = mlResultRegistDto.getLoginUserId();
        String program = MlResultRegistCommon.VIEW_ID;
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        /** マスターライセンス結果状況登録 */
        UIMlResult uiMlResult = new UIMlResult();
        uiMlResult.setStaffId(staffId);
        uiMlResult.setTotalLastYear(entryYear);
        uiMlResult.setTotalLastKai(entryKai);
        uiMlResult.setSub1Result(mlResultRegistDto.getAbilityCheckResult());
        uiMlResult.setLastUser(userId);
        uiMlResult.setLastPgm(program);
        uiMlResult.setLastTmsp(currentTimestamp);
        getMlrrUIMlResultDao().updateAbilityCheckResult(uiMlResult);

        /** マスターライセンス能力チェック結果登録 */
        TrnAbilityCheckResult trnAbilityCheckResult = mlResultRegistDto.getTrnAbilityCheckCategoryResult();
        if (trnAbilityCheckResult.isInsertFlg()) {
            // 共通情報設定
            trnAbilityCheckResult.setFirstUser(userId);
            trnAbilityCheckResult.setFirstPgm(program);
            trnAbilityCheckResult.setFirstTmsp(currentTimestamp);
            trnAbilityCheckResult.setLastUser(userId);
            trnAbilityCheckResult.setLastPgm(program);
            trnAbilityCheckResult.setLastTmsp(currentTimestamp);
            // insert
            getMlrrTrnAbilityCheckResultDao().insertResult(trnAbilityCheckResult);

        } else {
            // 共通情報設定
            trnAbilityCheckResult.setLastUser(userId);
            trnAbilityCheckResult.setLastPgm(program);
            // update
            getMlrrTrnAbilityCheckResultDao().updateResult(trnAbilityCheckResult);
        }

        /** マスターライセンス能力チェック明細結果登録 */
        for (Iterator it = mlResultRegistDto.getAbilityCheckDetailResultList().iterator(); it.hasNext();) {
            TrnAbilityCheckDetailResult entity = (TrnAbilityCheckDetailResult) it.next();
            // 免除のカテゴリは登録対象外
            if (trnAbilityCheckResult.getSub1ChkResult(entity.getCategoryCd())
                    .equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                continue;
            }
            if (entity.isInsertFlg()) {
                // 共通情報設定
                entity.setFirstUser(userId);
                entity.setFirstPgm(program);
                entity.setFirstTmsp(currentTimestamp);
                entity.setLastUser(userId);
                entity.setLastPgm(program);
                entity.setLastTmsp(currentTimestamp);
                // insert
                getMlrrTrnAbilityCheckDetailResultDao().insertDetailResult(entity);

            } else {
                // 共通情報設定
                entity.setLastUser(userId);
                entity.setLastPgm(program);
                // update
                getMlrrTrnAbilityCheckDetailResultDao().updateDetailResult(entity);
            }
        }
    }
}
