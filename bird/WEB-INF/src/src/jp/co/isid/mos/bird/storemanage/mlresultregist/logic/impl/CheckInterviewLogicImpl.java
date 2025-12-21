package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.JinmeiVerifier;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnInterviewResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckInterviewLogic;

/**
 * 面接入力チェックロジック
 * @author xnkusama
 */
public class CheckInterviewLogicImpl implements CheckInterviewLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L08";
    
    /**
     * 面接結果の判定
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public void execute(MlResultRegistDto dto) throws ApplicationException {
        DateVerifier dateVerifier = new DateVerifier(DateFormatter.DATE_TYPE_YMD);
        DefaultJapaneseVerifier jinmeiVerifier = new JinmeiVerifier();
        
        int index = 0;
        
        for (Iterator ite = dto.getListInterviewResult().iterator(); ite.hasNext();) {
            TrnInterviewResult trnInterviewResult = (TrnInterviewResult) ite.next();
//add start 2007/02/20 変更一覧
            // 能力チェックボックスの入力チェック
            boolean isCheck = isIntevewItemCheck(trnInterviewResult);
//add end
            //面接日
            if (!isNull(trnInterviewResult.getInterviewDt())) {
                if (!dateVerifier.validate(trnInterviewResult.getInterviewDt().trim())) {
                    throw new InvalidInputException("受験番号" + trnInterviewResult.getExamNo() + "の面接日", "interviewDt", index);
                }
            }else if(isCheck && (!trnInterviewResult.getSub3Result().equals("2") && !trnInterviewResult.getSub3Result().equals("3") && !trnInterviewResult.getInterviewChk().equals("3"))){
                throw new NotNullException("受験番号" + trnInterviewResult.getExamNo() + "の面接日", "interviewDt", index);
            }
            //面接官
            if (!isNull(trnInterviewResult.getAssesser())) {
// 面接官 入力制限をユーザー登録に合わせる
//   ・レングス 20Byte->40byte(DBは80Byteだが入力は40Byte）
//   ・入力可能文字 人名verifierを使用
//                if (!zenkakuVerifier.validate(trnInterviewResult.getAssesser())) {
//                    throw new InvalidInputException("受験番号" + trnInterviewResult.getExamNo() + "の面接官", "assesser", index);
//                }
//                if (trnInterviewResult.getAssesser().length() > 20) {
//                    throw new NotRelevantException("受験番号" + trnInterviewResult.getExamNo() + "の面接官", "全角１０文字以内", "assesser", index);
//                }
                if (!jinmeiVerifier.validate(trnInterviewResult.getAssesser())) {
                    throw new InvalidInputException("受験番号" + trnInterviewResult.getExamNo() + "の面接官", "assesser", index);
                }
                // 文字列長チェック
                if (trnInterviewResult.getAssesser().getBytes().length > 40) {
                    throw new InvalidInputException("受験番号" + trnInterviewResult.getExamNo() + "の面接官", "assesser", index);
                }
            }else if(isCheck && (!trnInterviewResult.getSub3Result().equals("2") && !trnInterviewResult.getSub3Result().equals("3") && !trnInterviewResult.getInterviewChk().equals("3"))){
                throw new NotNullException("受験番号" + trnInterviewResult.getExamNo() + "の面接官","assesser",index);
            }
            
            index++;
        }
    }
    /**
     * 能力チェックボックスの入力チェック
     * @param trnInterviewResult
     * @return true:チェックあり false:チェックなし
     * 2007/02/20
     */
    private boolean isIntevewItemCheck(TrnInterviewResult trnInterviewResult) {
        if(trnInterviewResult.isSub2Chk1ResultForChkbox()){
            return trnInterviewResult.isSub2Chk1ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk2ResultForChkbox()){
            return trnInterviewResult.isSub2Chk2ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk3ResultForChkbox()){
            return trnInterviewResult.isSub2Chk3ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk4ResultForChkbox()){
            return trnInterviewResult.isSub2Chk4ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk5ResultForChkbox()){
            return trnInterviewResult.isSub2Chk5ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk6ResultForChkbox()){
            return trnInterviewResult.isSub2Chk6ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk7ResultForChkbox()){
            return trnInterviewResult.isSub2Chk7ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk8ResultForChkbox()){
            return trnInterviewResult.isSub2Chk8ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk9ResultForChkbox()){
            return trnInterviewResult.isSub2Chk9ResultForChkbox();
        }
        if(trnInterviewResult.isSub2Chk10ResultForChkbox()){
            return trnInterviewResult.isSub2Chk10ResultForChkbox();
        }
        return false;
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
