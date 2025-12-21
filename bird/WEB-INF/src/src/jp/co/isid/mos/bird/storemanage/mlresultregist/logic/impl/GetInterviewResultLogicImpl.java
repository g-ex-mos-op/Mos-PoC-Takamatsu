package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnInterviewResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnInterviewResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetInterviewResultLogic;

/**
 * 面接情報取得ロジック 
 * @author xnkusama
 */
public class GetInterviewResultLogicImpl implements GetInterviewResultLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L05";
    
    TrnInterviewResultDao mlrrTrnInterviewResultDao;
    
    /**
     * 面接対象者一覧を取得する
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public List execute(MlResultRegistDto dto) throws ApplicationException {
        List listEntryStaff;
        //マスターライセンス面接結果の取得
        if (dto.isCondOnlyEntry()) {
            // エントリー者のみ表示の場合
            listEntryStaff = getMlrrTrnInterviewResultDao().getResult(
                    dto.getEntryYear(),
                    dto.getEntryKai(),
                    dto.getCondCompanyCd(),
                    dto.getCondSibuCd(),
                    dto.getCondOnerCd(),
                    dto.getCondMiseCd(),
                    dto.getCondType());
        }
        else {
            // エントリーしていないスタッフも表示
            listEntryStaff = getMlrrTrnInterviewResultDao()
                                    .getAllStaffResult(dto.getEntryCd(),
                                                       dto.getEntryYear(),
                                                       dto.getEntryKai(),
                                                       dto.getCondCompanyCd(),
                                                       dto.getCondSibuCd(),
                                                       dto.getCondOnerCd(),
                                                       dto.getCondMiseCd());
        }
        //登録対象の存在確認
        if (listEntryStaff == null || listEntryStaff.isEmpty()) {
            throw new NotExistException("対象者");
        }
        
        
        // 今回結果チェックボックス用の値をセット
        for (Iterator ite = listEntryStaff.iterator(); ite.hasNext();) {
            TrnInterviewResult trnInterviewResult = (TrnInterviewResult) ite.next();
            trnInterviewResult.setSub2Chk1ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk1Result()));
            trnInterviewResult.setSub2Chk2ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk2Result()));
            trnInterviewResult.setSub2Chk3ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk3Result()));
            trnInterviewResult.setSub2Chk4ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk4Result()));
            trnInterviewResult.setSub2Chk5ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk5Result()));
            trnInterviewResult.setSub2Chk6ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk6Result()));
            trnInterviewResult.setSub2Chk7ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk7Result()));
            trnInterviewResult.setSub2Chk8ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk8Result()));
            trnInterviewResult.setSub2Chk9ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk9Result()));
            trnInterviewResult.setSub2Chk10ResultForChkbox(getBolResult(
                    trnInterviewResult.getSub3Result(),
                    trnInterviewResult.getSub2Chk10Result()));
        }
        
        return listEntryStaff;
    }

    private boolean getBolResult(String sub3Result, String sub2ChkResult) {
        return MlResultRegistCommon.RESULT_EXEMPT.equals(sub3Result)
                || MlResultRegistCommon.RESULT_PASS.equals(sub2ChkResult)
                || MlResultRegistCommon.RESULT_EXEMPT.equals(sub2ChkResult);
    }
    
    public TrnInterviewResultDao getMlrrTrnInterviewResultDao() {
        return mlrrTrnInterviewResultDao;
    }

    public void setMlrrTrnInterviewResultDao(
            TrnInterviewResultDao mlrrTrnInterviewResultDao) {
        this.mlrrTrnInterviewResultDao = mlrrTrnInterviewResultDao;
    }

}
