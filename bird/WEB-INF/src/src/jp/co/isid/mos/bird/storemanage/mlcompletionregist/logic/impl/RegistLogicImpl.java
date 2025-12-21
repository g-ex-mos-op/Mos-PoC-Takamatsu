package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.TrnMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.TrnMlResultDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.UIMLStaffDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.UIRenewalStaffDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIRenewalStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.MlCompletionregistLogic;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * マスタライセンス研修修了登録
 * 受験スタッフ修了結果更新 ロジック
 * 更新日：2008/06/19 xnkusama 前提研修時のBT26、BT32への更新処理削除
 * @author xkinu
 */
public class RegistLogicImpl implements MlCompletionregistLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L05";
/* 2008/06/19 delete    
    private static final String TOTAL_RESULT_WAIT = "3";
    private static final String EXPIRE_FLG_VALID = "0";
    private static final String EXPIRE_FLG_WAIT = "2";
    private static final String LICENSE_KBN_NORMAL = "01";
*/    

    /*【DAO】*/
    private UIMLStaffDao uiMLStaffDao;
    private UIRenewalStaffDao uiRenewalStaffDao;
    private TrnMlResultDao trnMlResultDao;
    private TrnMlLicenseeDao trnMlLicenseeDao;

    /**
     * 事前条件処理
     */
    public void validate(MlCompletionregistDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("マスタライセンス研修修了登録 画面DTO");
        }
        // ユーザーID
        String userId = dto.getUserId();
        if (isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
        if (dto.getRegistDataList() == null) {
            throw new NotNullException("受験スタッフデータ");
        }

    }
    /**
     * 修了結果の登録を行う
     * 
     * 登録対象TABLE：BT30ENKJ 研修結果状況（全研修対象）
     *            BT24MLKJ マスターライセンス結果状況(ベーシック・出張・マスターライセンス）
     *            BT26UPJK ライセンス保持者管理(更新研修のみ）
     * 
     * @param MlCompletionregistDto 画面DTO
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException {
        //登録対象データ
        List regdatas = dto.getRegistDataList();
        //タイムスタンプ
	    Timestamp tmspNow = DateManager.getCurrentTimestamp();
	    //ユーザーID
	    String userId = dto.getUserId();
	    for (int i = 0; i < regdatas.size(); i++) {
	    	// 更新研修判断
	    	if(dto.isRenewalEntry()) {
                executeUpdateRenewal((UIRenewalStaff) regdatas.get(i), tmspNow,
						userId, dto.getBirdDateInfo().getSysDate());
//                executeUpdateRenewal( (UIRenewalStaff) regdatas.get(i), tmspNow, userId);
            }
            else {
                executeUpdateMl( (UIMLStaff) regdatas.get(i), tmspNow, userId, dto.getBirdDateInfo().getSysDate());
            }
	    }
        return null;
    }
    /**
     * マスターライセンス系研修登録処理
     * @param data
     */
    private void executeUpdateMl(UIMLStaff entity, Timestamp tmspNow, String userId, String sysDate) {
    	if(entity.getRegFlg().equals("0")) {
            return;
        }
        if(entity.getEntryFlg().equals("0")
                && entity.getCourseStatus().equals("0")
                && (entity.getCompleCourseDt() == null 
                		|| entity.getCompleCourseDt().trim().equals(""))) {
            //BT30ENKJ 研修結果状況削除実行
            if (entity.getDataStatusEt30().equals("UPDATE")) {
            	getUIMLStaffDao().deleteBt30Enkj(entity);
                return;
            }
            else if (entity.getDataStatusEt30().equals("INSERT")) {
                return;
            }
        }
        entity.setLastTmsp(tmspNow);
        entity.setLastUser(userId);
        entity.setLastPgm(SCREEN_ID);
        //登録処理実行
        if(entity.getDataStatusEt30().equals("INSERT")) {
            //新規登録
            entity.setFirstTmsp(tmspNow);
            entity.setFirstUser(userId);
            entity.setFirstPgm(SCREEN_ID);
            try {
                //BT30ENKJ 研修結果状況登録実行
                getUIMLStaffDao().insertBt30Enkj(entity);
            } catch (SQLRuntimeException sqlRunEx) {
                
                if(sqlRunEx.getCause() instanceof SQLException) {
                    SQLException sqlEx = (SQLException) sqlRunEx.getCause();
                    if(sqlEx.getErrorCode()== -803) {
                        //キー重複エラーが発生した場合
                        //BT30ENKJ 研修結果状況更新登録実行
                        getUIMLStaffDao().updateBt30Enkj(entity);
                    }else{
                        throw sqlRunEx;
                    }
                }else{
                    throw sqlRunEx;
                }
                
            }

            // BT24MLKJ→BT32MLKR対応により削除
/*
            try {
                //ベーシック・出張・マスターライセンス等の研修
                //BT24MLKJ マスターライセンス結果状況登録実行
                getUIMLStaffDao().insertBt24mlkj(entity);             
            } catch (SQLRuntimeException sqlRunEx) {
                
                if(sqlRunEx.getCause() instanceof SQLException) {
                    SQLException sqlEx = (SQLException) sqlRunEx.getCause();
                    if(sqlEx.getErrorCode()== -803) {
                        //キー重複エラーが発生した場合
                        //BT24MLKJ マスターライセンス結果状況更新登録実行
//                        getUIMLStaffDao().updateBt24mlkj(entity);                           
                    }else{
                        throw sqlRunEx;
                    }
                }else{
                    throw sqlRunEx;
                }
            }
*/
        } else {
            //更新登録
            //BT30ENKJ 研修結果状況登録実行
            getUIMLStaffDao().updateBt30Enkj(entity);
        	//ベーシック・出張・マスターライセンス等の研修
            // BT24MLKJ→BT32MLKR対応により削除
			//BT24MLKJ マスターライセンス結果状況登録実行
//			getUIMLStaffDao().updateBt24mlkj(entity);
        }
//---2008/06/19 delete BT26,BT32への更新処理削除
//        // ライセンス発行待ち登録
//        updateTotalResult(entity, tmspNow, userId, sysDate);
    }

    /**
     * 更新研修登録処理
     * 
     * @param entity
     * @param tmspNow
     * @param userId
     */
    private void executeUpdateRenewal(UIRenewalStaff entity, Timestamp tmspNow, String userId, String sysDate) {
    	if(entity.getRegFlg().equals("0")) {
            return;
        }
        if(entity.getEntryFlg().equals("0")
                && entity.getCourseStatus().equals("0")
                && (entity.getCompleCourseDt() == null 
                		|| entity.getCompleCourseDt().trim().equals(""))) {
            if (entity.getDataStatusEt30().equals("UPDATE")) {
            	//BT30ENKJ 研修結果状況削除実行
                getUIRenewalStaffDao().deleteBt30Enkj(entity);            
                return;
            }
            else if (entity.getDataStatusEt30().equals("INSERT")) {
                return;
            }
        }
        entity.setLastTmsp(tmspNow);
        entity.setLastUser(userId);
        entity.setLastPgm(SCREEN_ID);
        //登録処理実行
        if(entity.getDataStatusEt30().equals("INSERT")) {
            //新規登録
            entity.setFirstTmsp(tmspNow);
            entity.setFirstUser(userId);
            entity.setFirstPgm(SCREEN_ID);
            try {
                //BT30ENKJ 研修結果状況登録実行
                getUIRenewalStaffDao().insertBt30Enkj(entity);
            } catch (SQLRuntimeException sqlRunEx) {
                
                if(sqlRunEx.getCause() instanceof SQLException) {
                    SQLException sqlEx = (SQLException) sqlRunEx.getCause();
                    if(sqlEx.getErrorCode()== -803) {
                        //キー重複エラーが発生した場合
                        //BT30ENKJ 研修結果状況更新登録実行
                        getUIRenewalStaffDao().updateBt30Enkj(entity);
                    }else{
                        throw sqlRunEx;
                    }
                }else{
                    throw sqlRunEx;
                }
                
            }
         }else{
            //更新登録
            //BT30ENKJ 研修結果状況登録実行
             getUIRenewalStaffDao().updateBt30Enkj(entity);
        }
        //BT26UPJK ライセンス保持者管理更新登録実行
        // 有効期限内の場合のみ登録
        DateFormatter dateFormatter = new DateFormatter(
				DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        sysDate = dateFormatter.format(sysDate,
				DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        if (entity.getLicenseValidDt().compareTo(sysDate) >= 0) {
        	updateBt26upjk(entity);
        }
    }

    /**
     * BT26UPJK ライセンス保持者管理登録実行処理
     * 
     * @param entity
     */
    private void updateBt26upjk(UIRenewalStaff entity) {
//        int renewalTime = getRenewalTime(entity);
        //BT26UPJK ライセンス保持者管理登録実行
//        if(1 == renewalTime) {
            getUIRenewalStaffDao().updateBt26upjk1(entity);
//        }
//        if(2 == renewalTime) {
//            getUIRenewalStaffDao().updateBt26upjk2(entity);
//        }
//        if(3 == renewalTime) {
//            getUIRenewalStaffDao().updateBt26upjk3(entity);
//        }
    }
    /**
     * 回数取得処理
     * 指定エンティティのデータは何回目の更新研修か
     * 
     * @param entity
     * @return
     */
//    private int getRenewalTime(UIRenewalStaff entity) {
//        int timeNum = -1;
//        int renewalYear = Integer.parseInt(entity.getEntryYear());
//        String licenseDt = entity.getLicenseUpDt();
//        if (!isNull(licenseDt)) {
//            //ライセンス取得年月
//            int licenseNendo = Integer.parseInt(DateManager.getCurrentYear(licenseDt));
//            timeNum = renewalYear - (licenseNendo - 1);
//        }
//        return timeNum;
//    }
    /**
     * Nullチェック処理
     * @param str
     * @return
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * マスターライセンス受験者スタッフ情報Dao取得処理
     * 
     * @return uiMLStaffDao を戻します。
     */
    public UIMLStaffDao getUIMLStaffDao() {
        return uiMLStaffDao;
    }
    /**
     * マスターライセンス受験者スタッフ情報Dao設定処理
     * 
     * @param uiMLStaffDao を設定。
     */
    public void setUIMLStaffDao(UIMLStaffDao dao) {
        this.uiMLStaffDao = dao;
    }

    /**
     * 更新研修受験者スタッフ情報Dao取得処理
     * 
     * @return uiRenewalStaffDao を戻します。
     */
    public UIRenewalStaffDao getUIRenewalStaffDao() {
        return uiRenewalStaffDao;
    }
    /**
     * 更新研修受験者スタッフ情報Dao設定処理
     * 
     * @param uiRenewalStaffDao を設定。
     */
    public void setUIRenewalStaffDao(UIRenewalStaffDao dao) {
        this.uiRenewalStaffDao = dao;
    }

    /**
     * マスターライセンス結果状況履歴DAO取得処理
     * 
     * @return trnMlResultDao を戻します。
     */
    public TrnMlResultDao getTrnMlResultDao() {
        return trnMlResultDao;
    }
    /**
     * マスターライセンス結果状況履歴Dao設定処理
     * 
     * @param trnMlResultDao を設定。
     */
    public void setTrnMlResultDao(TrnMlResultDao trnMlResultDao) {
        this.trnMlResultDao = trnMlResultDao;
    }

    /**
     * ライセンス保持者管理DAO取得処理
     * 
     * @return trnMlLicenseeDao を戻します。
     */
    public TrnMlLicenseeDao getTrnMlLicenseeDao() {
        return trnMlLicenseeDao;
    }
    /**
     * ライセンス保持者管理Dao設定処理
     * 
     * @param trnMlLicenseeDao を設定。
     */
    public void setTrnMlLicenseeDao(TrnMlLicenseeDao trnMlLicenseeDao) {
        this.trnMlLicenseeDao = trnMlLicenseeDao;
    }

/*  2008/06/19 delete
    private void updateTotalResult(UIMLStaff entity, Timestamp tmspNow, String userId, String sysDate) {

        // 保留レコード取得
        String entryYearBefore = null;
        try {
            entryYearBefore = DateManager.getPrevYear(entity.getEntryYear(), 1);
        } catch (Exception e) {
        }
        TrnMlResult trnMlResult = getTrnMlResultDao().selectReservation(entity.getStaffId(), entryYearBefore);

        // 保留レコードある場合だけ処理
        if (trnMlResult != null) {

            // 発行待ちに設定
            trnMlResult.setTotalResult(TOTAL_RESULT_WAIT);
            trnMlResult.setLastTmsp(tmspNow);
            trnMlResult.setLastUser(userId);
            trnMlResult.setLastPgm(SCREEN_ID);
            // 総合結果更新
            getTrnMlResultDao().updateWaitForIssue(trnMlResult);

            // 有効ライセンス有無取得
            TrnMlLicensee trnMlLicensee = getTrnMlLicenseeDao().selectLicensee(entity.getStaffId());

            // 未登録
            if (trnMlLicensee == null) {
                trnMlLicensee = new TrnMlLicensee();
                trnMlLicensee.setStaffId(entity.getStaffId());
                trnMlLicensee.setExpireFlg(EXPIRE_FLG_WAIT);
                trnMlLicensee.setLicenseKbn(LICENSE_KBN_NORMAL);
                fillBlankLicensee(trnMlLicensee);
                trnMlLicensee.setFirstUser(userId);
                trnMlLicensee.setFirstPgm(SCREEN_ID);
                trnMlLicensee.setFirstTmsp(tmspNow);
                trnMlLicensee.setLastUser(userId);
                trnMlLicensee.setLastPgm(SCREEN_ID);
                trnMlLicensee.setLastTmsp(tmspNow);
                // insert
                getTrnMlLicenseeDao().insertLicensee(trnMlLicensee);

            // 無効判定
            } else if (trnMlLicensee.getLicenseValidDt().compareTo(sysDate) < 0
                    && !trnMlLicensee.getExpireFlg().trim().equals(EXPIRE_FLG_VALID)) {
                trnMlLicensee.setExpireFlg(EXPIRE_FLG_WAIT);
                trnMlLicensee.setLicenseKbn(LICENSE_KBN_NORMAL);
                fillBlankLicensee(trnMlLicensee);
                trnMlLicensee.setLastUser(userId);
                trnMlLicensee.setLastPgm(SCREEN_ID);
                // update
                getTrnMlLicenseeDao().updateLicensee(trnMlLicensee);
            }
        }
    }
    private void fillBlankLicensee(TrnMlLicensee trnMlLicensee) {
        trnMlLicensee.setLicenseNo("");
        trnMlLicensee.setLicenseDt("");
        trnMlLicensee.setLicenseUpDt("");
        trnMlLicensee.setLicenseValidDt("");
        trnMlLicensee.setRenew1EntYear("");
        trnMlLicensee.setRenew1EntKai("");
        trnMlLicensee.setRenew1Course("");
        trnMlLicensee.setRenew1CourseName("");
        trnMlLicensee.setRenew1Date("");
        trnMlLicensee.setRenew1Status("");
        trnMlLicensee.setRenew2EntYear("");
        trnMlLicensee.setRenew2EntKai("");
        trnMlLicensee.setRenew2Course("");
        trnMlLicensee.setRenew2CourseName("");
        trnMlLicensee.setRenew2Date("");
        trnMlLicensee.setRenew2Status("");
        trnMlLicensee.setRenew3EntYear("");
        trnMlLicensee.setRenew3EntKai("");
        trnMlLicensee.setRenew3Course("");
        trnMlLicensee.setRenew3CourseName("");
        trnMlLicensee.setRenew3Date("");
        trnMlLicensee.setRenew3Status("");
        trnMlLicensee.setRenew4EntYear("");
        trnMlLicensee.setRenew4EntKai("");
        trnMlLicensee.setRenew4Course("");
        trnMlLicensee.setRenew4CourseName("");
        trnMlLicensee.setRenew4Date("");
        trnMlLicensee.setRenew4Status("");
        trnMlLicensee.setRenew5EntYear("");
        trnMlLicensee.setRenew5EntKai("");
        trnMlLicensee.setRenew5Course("");
        trnMlLicensee.setRenew5CourseName("");
        trnMlLicensee.setRenew5Date("");
        trnMlLicensee.setRenew5Status("");
    }
*/    
}
