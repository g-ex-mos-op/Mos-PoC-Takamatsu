package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnInterviewResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIInterviewDtDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnInterviewResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIInterviewDt;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.UpdateInterviewLogic;

/**
 * 面接データ更新ロジック
 * @author xnkusama
 */
public class UpdateInterviewLogicImpl implements UpdateInterviewLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L09";
    
    private TrnInterviewResultDao mlrrTrnInterviewResultDao;
    private UIMlResultDao mlrrUIMlResultDao;
    private UIInterviewDtDao mlrrUIInterviewDtDao;
    
    /**
     * 面接データを更新
     * @param MlResultRegistDto dto
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException {
        List listInterviewResult = dto.getListInterviewResult();
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        int maxCategory = dto.getListMstInterview().size();

        for (Iterator ite = listInterviewResult.iterator(); ite.hasNext();) {

            TrnInterviewResult trnInterviewResult = (TrnInterviewResult) ite.next();

            // 面接免除、受験不可のスタッフは処理対象外
            if (trnInterviewResult.getSub3Result().equals(MlResultRegistCommon.RESULT_EXEMPT) ||
                trnInterviewResult.getSub3Result().equals(MlResultRegistCommon.RESULT_NOENTRY) ||
                trnInterviewResult.getInterviewChk().equals("3") ) {
                continue;
            }

            // マスターライセンス結果状況 更新
            UIMlResult uiMlResult = new UIMlResult();
            uiMlResult.setStaffId(trnInterviewResult.getStaffId());
            uiMlResult.setTotalLastYear(dto.getEntryYear());
            uiMlResult.setTotalLastKai(dto.getEntryKai());
            uiMlResult.setSub3Result(trnInterviewResult.getHantei());
            uiMlResult.setLastUser(dto.getLoginUserId());
            uiMlResult.setLastPgm(LOGIC_ID.substring(0, 7));
            uiMlResult.setLastTmsp(currentTimestamp);
            getMlrrUIMlResultDao().updateInterviewResult(uiMlResult);

            // マスターライセンス面接結果 更新
            // カテゴリごとの結果をセット
            setCategoryResult(trnInterviewResult, maxCategory);
            // 面接官省略対応
            trnInterviewResult.setAssesser((trnInterviewResult.getAssesser() == null) ? "" : trnInterviewResult.getAssesser());
            // 更新USER、PGM、TMSPセット
            trnInterviewResult.setLastUser(dto.getLoginUserId());
            trnInterviewResult.setLastPgm(LOGIC_ID.substring(0, 7));
            if (trnInterviewResult.isInsertFlg()) {
                trnInterviewResult.setFirstUser(dto.getLoginUserId());
                trnInterviewResult.setFirstPgm(LOGIC_ID.substring(0, 7));
                trnInterviewResult.setFirstTmsp(currentTimestamp);
                // insert
                getMlrrTrnInterviewResultDao().insertInterviewResult(trnInterviewResult);
            } else {
                // update
                getMlrrTrnInterviewResultDao().updateInterviewResult(trnInterviewResult);
            }

            // 面接日登録
            UIInterviewDt uiInterviewDt = new UIInterviewDt();
            uiInterviewDt.setEntryYear(trnInterviewResult.getEntryYear());
            uiInterviewDt.setEntryKai(trnInterviewResult.getEntryKai());
            uiInterviewDt.setStaffId(trnInterviewResult.getStaffId());
            String interviewDt = trnInterviewResult.getInterviewDt();
            if (interviewDt == null) {
                interviewDt = "";
            }
            uiInterviewDt.setInterviewDt(interviewDt);
            uiInterviewDt.setLastUser(dto.getLoginUserId());
            uiInterviewDt.setLastPgm(LOGIC_ID.substring(0, 7));
            uiInterviewDt.setLastTmsp(currentTimestamp);
            int resultCnt = getMlrrUIInterviewDtDao().updateInterviewDt(uiInterviewDt);
            if (resultCnt == 0) {
                // 新規登録
                uiInterviewDt.setEntryYear(trnInterviewResult.getEntryYear());
                uiInterviewDt.setEntryKai(trnInterviewResult.getEntryKai());
                uiInterviewDt.setStaffId(trnInterviewResult.getStaffId());
                uiInterviewDt.setFirstUser(dto.getLoginUserId());
                uiInterviewDt.setFirstPgm(LOGIC_ID.substring(0, 7));
                uiInterviewDt.setFirstTmsp(currentTimestamp);
                uiInterviewDt.setYobi1("");
                uiInterviewDt.setYobi2("");
                uiInterviewDt.setYobi3("");
                getMlrrUIInterviewDtDao().insertInterviewDt(uiInterviewDt);
            }
        }
    }

    /**
     * カテゴリごとの結果をセット
     * @param entity
     * @param maxCategory
     */
    private void setCategoryResult(TrnInterviewResult entity, int maxCategory) {

        entity.setSub2Chk1Result(getResult(entity.getSub3Result(), entity.getSub2Chk1Result(), entity.isSub2Chk1ResultForChkbox()));
        if (maxCategory >= 2) {
            entity.setSub2Chk2Result(getResult(entity.getSub3Result(), entity.getSub2Chk2Result(), entity.isSub2Chk2ResultForChkbox()));
        }
        if (maxCategory >= 3) {
            entity.setSub2Chk3Result(getResult(entity.getSub3Result(), entity.getSub2Chk3Result(), entity.isSub2Chk3ResultForChkbox()));
        }
        if (maxCategory >= 4) {
            entity.setSub2Chk4Result(getResult(entity.getSub3Result(), entity.getSub2Chk4Result(), entity.isSub2Chk4ResultForChkbox()));
        }
        if (maxCategory >= 5) {
            entity.setSub2Chk5Result(getResult(entity.getSub3Result(), entity.getSub2Chk5Result(), entity.isSub2Chk5ResultForChkbox()));
        }
        if (maxCategory >= 6) {
            entity.setSub2Chk6Result(getResult(entity.getSub3Result(), entity.getSub2Chk6Result(), entity.isSub2Chk6ResultForChkbox()));
        }
        if (maxCategory >= 7) {
            entity.setSub2Chk7Result(getResult(entity.getSub3Result(), entity.getSub2Chk7Result(), entity.isSub2Chk7ResultForChkbox()));
        }
        if (maxCategory >= 8) {
            entity.setSub2Chk8Result(getResult(entity.getSub3Result(), entity.getSub2Chk8Result(), entity.isSub2Chk8ResultForChkbox()));
        }
        if (maxCategory >= 9) {
            entity.setSub2Chk9Result(getResult(entity.getSub3Result(), entity.getSub2Chk9Result(), entity.isSub2Chk9ResultForChkbox()));
        }
        if (maxCategory >= 10) {
            entity.setSub2Chk10Result(getResult(entity.getSub3Result(), entity.getSub2Chk10Result(), entity.isSub2Chk10ResultForChkbox()));
        }
    }
    
    /**
     * 合否の取得
     * ・前回が合格、または免除の場合-->免除
     * ・上記以外で今回合格の場合    -->合格
     * ・その他 --> 不合格
     * @param lastResult
     * @param result
     * @return
     */
    private String getResult(String sub3Result, String sub2ChkResult, boolean sub2ChkResultForChkbox) {
        String result = null;
        if (sub3Result.equals(MlResultRegistCommon.RESULT_EXEMPT) || sub2ChkResult.equals(MlResultRegistCommon.RESULT_EXEMPT)) {
            result = MlResultRegistCommon.RESULT_EXEMPT;
        } else if (sub2ChkResultForChkbox) {
            result = MlResultRegistCommon.RESULT_PASS;
        } else {
            result = MlResultRegistCommon.RESULT_FAILURE;
        }
        return result;
    }
    
    public TrnInterviewResultDao getMlrrTrnInterviewResultDao() {
        return mlrrTrnInterviewResultDao;
    }

    public void setMlrrTrnInterviewResultDao(
            TrnInterviewResultDao mlrrTrnInterviewResultDao) {
        this.mlrrTrnInterviewResultDao = mlrrTrnInterviewResultDao;
    }

    public UIMlResultDao getMlrrUIMlResultDao() {
        return mlrrUIMlResultDao;
    }

    public void setMlrrUIMlResultDao(UIMlResultDao mlrrUIMlResultDao) {
        this.mlrrUIMlResultDao = mlrrUIMlResultDao;
    }

    public UIInterviewDtDao getMlrrUIInterviewDtDao() {
        return mlrrUIInterviewDtDao;
    }

    public void setMlrrUIInterviewDtDao(UIInterviewDtDao mlrrUIInterviewDtDao) {
        this.mlrrUIInterviewDtDao = mlrrUIInterviewDtDao;
    }
        
}