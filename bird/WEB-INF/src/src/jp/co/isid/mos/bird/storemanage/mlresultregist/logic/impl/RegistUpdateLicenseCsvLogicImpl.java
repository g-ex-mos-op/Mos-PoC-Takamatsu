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

import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvUpdateLicense;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistCsvLogic;

/**
 * ライセンス更新CSV登録
 * 
 * @author xyuchida
 */
public class RegistUpdateLicenseCsvLogicImpl implements RegistCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L22";

    private UIMlLicenseeDao uiMlLicenseeDao;

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
     * ライセンス更新CSV登録
     * 
     * @param csvList ライセンス更新CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto) {

        // ユーザID取得
        String userId = mlResultRegistDto.getLoginUserId();
        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        for (Iterator it = csvList.iterator(); it.hasNext();) {

            UICsvUpdateLicense uiCsvUpdateLicense = (UICsvUpdateLicense) it.next();

            // エラー判定
            if (uiCsvUpdateLicense.getUiCsvErrorInfo().isExistError()) {
                continue;
            }

            // Entity生成
            UIMlLicensee uiMlLicensee = new UIMlLicensee();
            // ライセンス更新情報設定
            uiMlLicensee.setLicenseNo(uiCsvUpdateLicense.getLicenseNo());
            uiMlLicensee.setLicenseUpDt(uiCsvUpdateLicense.getLicenseUpDt());
            // ライセンス有効期限設定
            uiMlLicensee.setLicenseValidDt(getLicenseValidDt(uiCsvUpdateLicense.getLicenseUpDt()));
            uiMlLicensee.setExpireFlg("0");
            // 共通項目設定
            uiMlLicensee.setLastUser(userId);
            uiMlLicensee.setLastPgm(MlResultRegistCommon.VIEW_ID);
            uiMlLicensee.setLastTmsp(currentTimestamp);

            // insert
            getUiMlLicenseeDao().updateLicense(uiMlLicensee);
        }
    }

    private String getLicenseValidDt(String licenseUpDt) {
        String validUpDtYear = null;
        try {
            validUpDtYear = DateManager.getNextYear(licenseUpDt.substring(0, 4), 3);
        } catch (Exception e) {
        }
        return formatDate(Integer.parseInt(validUpDtYear), 3);
    }

    private String formatDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        return formatter.format(calendar.getTime());
    }
}
