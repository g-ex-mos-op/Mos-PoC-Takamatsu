/*
 * 作成日: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistCsvLogic;

/**
 * 受験結果CSV登録
 * 
 * @author xyuchida
 */
public class RegistResultCsvLogicImpl implements RegistCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L20";

    private UIMlResultDao uiMlResultDao;
    private UIMlLicenseeDao uiMlLicenseeDao;

    /**
     * @return uiMlResultDao を戻します。
     */
    public UIMlResultDao getUiMlResultDao() {
        return uiMlResultDao;
    }
    /**
     * @param uiMlResultDao uiMlResultDao を設定。
     */
    public void setUiMlResultDao(UIMlResultDao uiMlResultDao) {
        this.uiMlResultDao = uiMlResultDao;
    }
    /**
     * @return uiMlLicenseeDao を戻します。
     */
    public UIMlLicenseeDao getUiMlLicenseeDao() {
        return uiMlLicenseeDao;
    }
    /**
     * @param uiMlLicenseeDao uiMlLicenseeDao を設定。
     */
    public void setUiMlLicenseeDao(UIMlLicenseeDao uiMlLicenseeDao) {
        this.uiMlLicenseeDao = uiMlLicenseeDao;
    }

    /**
     * 受験結果CSV登録
     * 
     * @param csvList 受験結果CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto) {

        // ユーザID取得
        String userId = mlResultRegistDto.getLoginUserId();
        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        for (Iterator it = csvList.iterator(); it.hasNext();) {

            UICsvResult uiCsvResult = (UICsvResult) it.next();

            // エラー判定
            if (uiCsvResult.getUiCsvErrorInfo().isExistError()) {
                continue;
            }

            // Entity生成
            UIMlResult uiMlResult = new UIMlResult();
            // 受験結果設定
            uiMlResult.setTotalLastYear(mlResultRegistDto.getEntryYear());
            uiMlResult.setTotalLastKai(mlResultRegistDto.getEntryKai());
            uiMlResult.setExamNo(uiCsvResult.getExamNo());
            uiMlResult.setTotalResult(uiCsvResult.getTotalResult());
            uiMlResult.setSub2Result(uiCsvResult.getSub2Result());
            // 共通項目設定
            uiMlResult.setLastUser(userId);
            uiMlResult.setLastPgm(MlResultRegistCommon.VIEW_ID);
            uiMlResult.setLastTmsp(currentTimestamp);

            // update
            getUiMlResultDao().updateTotalResult(uiMlResult);

            if (uiCsvResult.getTotalResult().equals(MlResultRegistCommon.TOTAL_RESULT_WAIT)) {
                // ライセンス発行待ち登録
                registLicenseWait(uiCsvResult, mlResultRegistDto);
            }
        }
    }

    private void registLicenseWait(UICsvResult uiCsvResult, MlResultRegistDto mlResultRegistDto) {

        // ユーザID取得
        String userId = mlResultRegistDto.getLoginUserId();
        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // Entity生成
        UIMlLicensee uiMlLicensee = new UIMlLicensee();
        // ライセンス情報設定
        uiMlLicensee.setLicenseNo("");
        uiMlLicensee.setLicenseDt("");
        uiMlLicensee.setLicenseUpDt("");
        uiMlLicensee.setLicenseValidDt("");
        uiMlLicensee.setExpireFlg(MlResultRegistCommon.EXPIRE_FLG_WAIT);
        // 共通項目設定
        uiMlLicensee.setLastUser(userId);
        uiMlLicensee.setLastPgm(MlResultRegistCommon.VIEW_ID);
        uiMlLicensee.setLastTmsp(currentTimestamp);
        // スタッフID取得
        UIMlResult staffIdResult = getUiMlResultDao().selectTwoYearStaffId(
                uiCsvResult.getExamNo(), mlResultRegistDto.getEntryYear(), "");
        uiMlLicensee.setStaffId(staffIdResult.getStaffId());
        uiMlLicensee.setLicenseKbn(staffIdResult.getLicenseKbn());

        // update
        int result = getUiMlLicenseeDao().updateIssueLicense(
                uiCsvResult.getExamNo(),
                mlResultRegistDto.getEntryYear(),
                mlResultRegistDto.getEntryKai(),
                uiMlLicensee);
        if (result == 0) {
            // 共通項目設定
            uiMlLicensee.setFirstUser(userId);
            uiMlLicensee.setFirstPgm(MlResultRegistCommon.VIEW_ID);
            uiMlLicensee.setFirstTmsp(currentTimestamp);
            // insert
            getUiMlLicenseeDao().insertLicense(
                    uiCsvResult.getExamNo(),
                    mlResultRegistDto.getEntryYear(),
                    mlResultRegistDto.getEntryKai(),
                    uiMlLicensee);
        }
    }
}
