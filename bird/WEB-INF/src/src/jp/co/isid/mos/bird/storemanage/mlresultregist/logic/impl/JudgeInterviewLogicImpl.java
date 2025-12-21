package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnInterviewResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.JudgeInterviewLogic;

/**
 * 面接判定ロジック
 * @author xnkusama
 */
public class JudgeInterviewLogicImpl implements JudgeInterviewLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L07";
    
    /**
     * 面接結果の判定
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException {
        List listInterviewResult = dto.getListInterviewResult();
        int maxCategory = dto.getListMstInterview().size();
        
        for (Iterator ite = listInterviewResult.iterator(); ite.hasNext();) {
            int cntGogaku = 0;
            TrnInterviewResult trnInterviewResult = (TrnInterviewResult) ite.next();
            
            if (trnInterviewResult.isSub2Chk1ResultForChkbox()) {
                cntGogaku++;
            }
            if (maxCategory >= 2) {
                if (trnInterviewResult.isSub2Chk2ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 3) {
                if (trnInterviewResult.isSub2Chk3ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 4) {
                if (trnInterviewResult.isSub2Chk4ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 5) {
                if (trnInterviewResult.isSub2Chk5ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 6) {
                if (trnInterviewResult.isSub2Chk6ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 7) {
                if (trnInterviewResult.isSub2Chk7ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 8) {
                if (trnInterviewResult.isSub2Chk8ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 9) {
                if (trnInterviewResult.isSub2Chk9ResultForChkbox()) {
                    cntGogaku++;
                }
            }
            if (maxCategory >= 10) {
                if (trnInterviewResult.isSub2Chk10ResultForChkbox()) {
                    cntGogaku++;
                }
            }

            if (trnInterviewResult.getSub3Result().equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                trnInterviewResult.setHantei("2");
            } else if (trnInterviewResult.getSub3Result().equals(MlResultRegistCommon.RESULT_NOENTRY)) {
                trnInterviewResult.setHantei("3");
            } else if (trnInterviewResult.getInterviewChk().equals("3")) {
                trnInterviewResult.setHantei("4");
            } else if (cntGogaku >= maxCategory) {
                trnInterviewResult.setHantei("1");
            } else if (isNull(trnInterviewResult.getAssesser()) && isNull(trnInterviewResult.getInterviewDt())) {
                trnInterviewResult.setHantei("9");
            } else {
                trnInterviewResult.setHantei("0");
            }
            
        }
    }
    /**
     * null、空文字判定
     * @param value
     * @return
     */
    private boolean isNull(String value) {
        return (value == null || "".equals(value.trim())) ? true : false;
    }

}
