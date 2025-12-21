package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodCourse;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIRenewalStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.MlCompletionregistLogic;

/**
 * マスタライセンス研修修了登録
 * 登録内容のチェック ロジック
 * 
 * @author xkinu
 */
public class CheckContentsLogicImpl implements MlCompletionregistLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L01";
    
    /**
     * 事前条件処理
     */
    public void validate(MlCompletionregistDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("マスタライセンス研修修了登録 画面DTO");
        }
        if (dto.getRegistDataList() == null) {
            throw new NotNullException("受験スタッフ情報");
        }

    }
    /**
     * 登録内容のチェックを行う
     * @param MlCompletionregistDto 画面DTO
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException {
        if(dto.isRenewalEntry()) {
            checkRenewal(dto);
        }
        else {
            checkMlicense(dto);
        }
        return null;
    }

    /**
     * マスターライセンス系研修　項目チェック処理
     * 
     * @param dto
     */
    private void checkMlicense(MlCompletionregistDto dto) {
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        //BigDecimal dec0 = new BigDecimal("0");
        //BigDecimal dec100 = new BigDecimal("100");
        List registData = dto.getRegistDataList();
        for (int i=0; i<registData.size(); i++) {
            UIMLStaff data = (UIMLStaff)registData.get(i);
            //エントリーフラグ 0:未エントリー、1:エントリー済み
            String entryFlg = data.getEntryFlg();
            //年月
            String compleCourseDt = data.getCompleCourseDt();
            //年月チェック
            if(isNull(compleCourseDt)) {
                if("1".equals(entryFlg)){
                    //エントリー済みのスタッフのみ必須チェックを行う
                    throw new NotNullException("年月", "compleCourseDt", i);
                }else if("0".equals(entryFlg)){
//                    if(dto.isTripEntry()) {
                        //出張研修の場合はこの時点ではチェックなし。
//                    }else{
                        //修了（予定）コース状況　0：未修了　1:修了  2.予定
                        String courseStatus = data.getCourseStatus();
                        if("1".equals(courseStatus)) {
                            //E0507
                            throw new NoInputException("修了状況に「修了」が選択されています。年月", "compleCourseDt", i);
                        }
//                    }
//                    //データステータス　'INSERT' or 'UPDATE'
//                    String dataStatus = data.getDataStatus();
                }
            }else if(!dateVerifier.validate(compleCourseDt)){
                throw new InvalidInputException("年月", "compleCourseDt", i);
            }

            /* TODO 出張研修の点数は、後のフェーズにて仕様検討の上改修を行う
            //出張研修の場合
            if(dto.isTripEntry()) {
                //点数
                BigDecimal complePoint = data.getComplePoint();
                if(complePoint == null) {                          
                    //点数チェック
                    if("1".equals(entryFlg)){
                        //E0506
                        throw new NotNullException("点数", "complePoint", i);
                    }else if("0".equals(entryFlg)){
                        if(!isNull(compleCourseDt)) {
                            // E0507
                            throw new NoInputException("点数", "complePoint", i);
                        }
                        //「未修了」に設定
                        data.setCourseStatus("0");
                    }
                }else{
                    if(complePoint.toString().length() > 3 
                         || complePoint.compareTo(dec100) > 0 
                        || complePoint.compareTo(dec0) < 0)
                    {
                        throw new InvalidInputException("点数", "complePoint", i);
                    }
                    if(complePoint.compareTo(dec0) > 0 && isNull(compleCourseDt)) {
                        //年月が空なのに、点数が入力されている場合
                        //E0507
                        throw new NoInputException("点数が入力されている為、年月", "compleCourseDt", i);
                    }
                    BigDecimal dec80 = new BigDecimal("80");
                    if(complePoint.compareTo(dec80) >= 0 ) {
                        //80点以上の場合は「修了」に設定
                        data.setCourseStatus("1");
                    }else{
                        //「未修了」に設定
                        data.setCourseStatus("0");
                    }
                }
            }*/
        }
        
    	
    }
    /**
     * 更新研修　項目チェック処理
     * @param dto
     */
    private void checkRenewal(MlCompletionregistDto dto) {
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        List registData = dto.getRegistDataList();
        for (int i=0; i<registData.size(); i++) {
            UIRenewalStaff data = (UIRenewalStaff)registData.get(i);
            //エントリーフラグ 0:未エントリー、1:エントリー済み
            //年月
            String compleCourseDt = data.getCompleCourseDt();
            //年月チェック
            if(isNull(compleCourseDt)) {
                if(data.getChangeRegFlg()){
                    //エントリー済みのスタッフのみ必須チェックを行う
                    throw new NotNullException("年月", "compleCourseDt", i);
                }else{
                    //修了（予定）コース状況　0：未修了　1:修了  2.予定
                    String courseStatus = data.getCourseStatus();
                    if("1".equals(courseStatus)) {
                        //E0507
                        throw new NoInputException("修了状況に「修了」が選択されています。年月", "compleCourseDt", i);
                    }
//                    //データステータス　'INSERT' or 'UPDATE'
//                    String dataStatus = data.getDataStatus();
                }
            }else if(!dateVerifier.validate(compleCourseDt)){
                throw new InvalidInputException("年月", "compleCourseDt", i);
            }

            // コース名NULL時の処理
//            if (isNull(data.getCompleCourseName())) {
                for (Iterator j = dto.getCourseList()
    					.iterator(); j.hasNext();) {
                	CodCourse codCourse = (CodCourse) j.next();
                	if (codCourse.getCourseCd().equals(data.getCompleCourseCd())) {
                		data.setCompleCourseName(codCourse.getCourseName());
                		break;
                	}
    			}
//            }
        }
    }
    /**
     * StringオブジェクトNull(又は空)判断処理
     * 
     * @param str
     * @return
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
