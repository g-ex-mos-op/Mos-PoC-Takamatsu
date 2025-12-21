/*
 * 作成日: 2006/07/24
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlResultDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvIssueLicense;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistCsvLogic;

/**
 * ライセンス発行CSV登録
 * 
 * 更新日：2008/05/28 xkinu 
 *         有効期限日の値を'999912'を設定するよう制度が変更されました。
 * 更新日：2008/05/28 xkinu 
 *         マスタライセンスカードの発行を年2回に制度が変更されました。
 *         その為、画面から”ライセンス取得年月”を指定するよう処理を変更しました。
 * 
 * @author xyuchida
 */
public class RegistIssueLicenseCsvLogicImpl implements RegistCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L21";
    
    /*マスタライセンス結果状況 4:発行済み*/
    private static final String ISSUED = "4";

    private UIMlLicenseeDao uiMlLicenseeDao;
    private UIMlResultDao uIMlResultDao; 
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
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(MlResultRegistDto mlResultRegistDto)
    {
        if (isNull(mlResultRegistDto.getLicenseYear())) {
            throw new NotNullException("指定ライセンス取得年月の年", "licenseYear", 0);
        }
        if (isNull(mlResultRegistDto.getLicenseMonth())) {
            throw new NotNullException("指定ライセンス取得年月の月", "licenseMonth", 0);
        }
    }

    /**
     * ライセンス発行CSV登録
     * 
	 * 更新日：2008/05/28 xkinu 
	 *         マスタライセンスカードの発行を年2回に制度が変更されました。
	 *         その為、画面から”ライセンス取得年月”を指定するよう処理を変更しました。
     * @param csvList ライセンス発行CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto) {
    	//事前条件処理
    	validate(mlResultRegistDto);
        // ユーザID取得
        String userId = mlResultRegistDto.getLoginUserId();
        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        for (Iterator it = csvList.iterator(); it.hasNext();) {

            UICsvIssueLicense uiCsvIssueLicense = (UICsvIssueLicense) it.next();

            // エラー判定
            if (uiCsvIssueLicense.getUiCsvErrorInfo().isExistError()) {
                continue;
            }

            // Entity生成
            UIMlLicensee uiMlLicensee = new UIMlLicensee();
            // ライセンス番号設定
            uiMlLicensee.setLicenseNo(uiCsvIssueLicense.getLicenseNo());
            // ライセンス発行 取得年月/更新年月/有効期限設定
            String licenseDt = getLicenseDt(mlResultRegistDto.getEntryYear());
            //ライセンス取得年月（画面から指定された年月を設定します。）
            uiMlLicensee.setLicenseDt(mlResultRegistDto.getReservedLicenseDt());
            //ライセンス更新年月（画面から指定された年月を設定します。）
            uiMlLicensee.setLicenseUpDt(mlResultRegistDto.getReservedLicenseDt());
            uiMlLicensee.setLicenseValidDt(getLicenseValidDt(licenseDt));
            uiMlLicensee.setExpireFlg("0");
            // 共通項目設定
            uiMlLicensee.setLastUser(userId);
            uiMlLicensee.setLastPgm(MlResultRegistCommon.VIEW_ID);
            uiMlLicensee.setLastTmsp(currentTimestamp);
            UIMlResult staffIdResult = getUIMlResultDao().selectTwoYearStaffId(uiCsvIssueLicense.getExamNo(),mlResultRegistDto.getEntryYear(),String.valueOf(Integer.parseInt(mlResultRegistDto.getEntryYear())-1));
            uiMlLicensee.setStaffId(staffIdResult.getStaffId());
            uiMlLicensee.setLicenseKbn(staffIdResult.getLicenseKbn());

            // update
            int result = getUiMlLicenseeDao().updateIssueLicense(
                    uiCsvIssueLicense.getExamNo(),
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
                        uiCsvIssueLicense.getExamNo(),
                        mlResultRegistDto.getEntryYear(),
                        mlResultRegistDto.getEntryKai(),
                        uiMlLicensee);
            }

//add start 2007/02/20  マスタライセンス結果状況履歴の「総合結果」を更新
//           Entity生成
          UIMlResult uiMlResult = new UIMlResult();
          //受験結果設定
          uiMlResult.setTotalResult(ISSUED);
          uiMlResult.setTotalLastYear(mlResultRegistDto.getEntryYear());
          uiMlResult.setExamNo(uiCsvIssueLicense.getExamNo());
          uiMlResult.setTotalLastYear(staffIdResult.getTotalLastYear());
          uiMlResult.setTotalLastKai(staffIdResult.getTotalLastKai());
          //共通項目設定
          uiMlResult.setStaffId(staffIdResult.getStaffId());
          uiMlResult.setLastUser(userId);
          uiMlResult.setLastPgm(MlResultRegistCommon.VIEW_ID);
          uiMlResult.setLastTmsp(currentTimestamp);
//        update
          getUIMlResultDao().updateSynthesisResult(uiMlResult);
//add end
        }
    }

    private String getLicenseDt(String entryYear) {
        String licenseDt = null;
        try {
            licenseDt = DateManager.getNextYear(entryYear, 1);
        } catch (Exception e) {
        }
        return formatDate(Integer.parseInt(licenseDt), 4);
    }
    /**
     * 有効期限日取得処理
     * 
     * 2008/05/28 無条件に'999912'を設定するよう制度が変更されました。
     * 
     * @param licenseUpDt
     * @return
     */
    private String getLicenseValidDt(String licenseUpDt) {
//        String validUpDtYear = null;
//        try {
//            validUpDtYear = DateManager.getNextYear(licenseUpDt.substring(0, 4), 3);
//        } catch (Exception e) {
//        }
//        return formatDate(Integer.parseInt(validUpDtYear), 3);
    	return "999912";
    }

    private String formatDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        //ADD: 2006/08/31 START 現在が31日だった場合に月がずれるバグの修正 by kondo
        calendar.set(Calendar.DATE, 1);
        //ADD: 2006/08/31 END
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        return formatter.format(calendar.getTime());
    }
    public UIMlResultDao getUIMlResultDao() {
        return uIMlResultDao;
    }
    public void setUIMlResultDao(UIMlResultDao mlResultDao) {
        uIMlResultDao = mlResultDao;
    }
    /**
     * String用Null判断処理
     * 
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
