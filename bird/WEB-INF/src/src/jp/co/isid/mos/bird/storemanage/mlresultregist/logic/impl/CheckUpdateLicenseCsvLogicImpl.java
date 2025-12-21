/*
 * 作成日: 2006/07/24
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvErrorInfo;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvUpdateLicense;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckCsvLogic;

/**
 * ライセンス更新CSVチェックロジック
 * 
 * @author xyuchida
 */
public class CheckUpdateLicenseCsvLogicImpl implements CheckCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L19";

    private static final int MAX_COLUMN_NUM = 2;

    private UIMlLicenseeDao uiMlLicenseeDao;

    public UIMlLicenseeDao getUiMlLicenseeDao() {
        return uiMlLicenseeDao;
    }
    public void setUiMlLicenseeDao(UIMlLicenseeDao uiMlLicenseeDao) {
        this.uiMlLicenseeDao = uiMlLicenseeDao;
    }

    /**
     * CSVチェック
     * 
     * @param csvList CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto) {

        List licenseNoList = new ArrayList();
        List duplicateList = new ArrayList();

        // ライセンス番号リスト取得(非存在チェック用)
        List existLicenseNoList = getUiMlLicenseeDao().selectLicenseNoList();

        // 全レコード処理
        for (Iterator it = csvList.iterator(); it.hasNext();) {
            UICsvUpdateLicense uiCsvUpdateLicense = (UICsvUpdateLicense) it.next();

            // カラム数チェック
            if (!checkColumnNum(uiCsvUpdateLicense.getUiCsvErrorInfo().getInputData())) {
                uiCsvUpdateLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_FORMAT);

            } else {
                // 項目チェック
                checkItem(uiCsvUpdateLicense);
                // ライセンス番号存在チェック
                checkExistLicenseNo(uiCsvUpdateLicense, existLicenseNoList);

                // 重複ライセンス番号リスト作成
                if (!uiCsvUpdateLicense.getUiCsvErrorInfo().isExistError()) {
                    String licenseNo = uiCsvUpdateLicense.getLicenseNo();
                    // 未設定は重複チェック対象外
                    if (licenseNo != null && licenseNo.trim().length() > 0) {
                        // ライセンス番号重複チェック
                        if (licenseNoList.contains(licenseNo)) {
                            // 重複ライセンス番号リストに追加
                            duplicateList.add(licenseNo);
                        } else {
                            licenseNoList.add(licenseNo);
                        }
                    }
                }
            }
        }

        // 重複エラー設定
        for (Iterator it = csvList.iterator(); it.hasNext();) {
            UICsvUpdateLicense uiCsvResult = (UICsvUpdateLicense) it.next();
            // 重複チェック
            if (duplicateList.contains(uiCsvResult.getLicenseNo())) {
                uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_DUPLICATE, "ライセンス番号", false);
            }
        }
    }

    private void checkItem(UICsvUpdateLicense uiCsvUpdateLicense) {

        UICsvErrorInfo uiCsvErrorInfo = uiCsvUpdateLicense.getUiCsvErrorInfo();

        // ライセンス番号
        String licenseNo = uiCsvUpdateLicense.getLicenseNo();
        // 必須
        if (!checkRequired(licenseNo)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_REQUIRED, "ライセンス番号", false);
        }
        // 桁数
        if (!checkLength(licenseNo, 10)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_LENGTH, "ライセンス番号", false);
        }
        // 形式
        CodeVerifier licenseNoCodeVerifier = new CodeVerifier(10);
        licenseNoCodeVerifier.setNullable(false);
        if (!licenseNoCodeVerifier.validate(licenseNo)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "ライセンス番号", false);
        }

        // ライセンス更新年月
        String licenseUpDt = uiCsvUpdateLicense.getLicenseUpDt();
        // 必須
        if (!checkRequired(licenseUpDt)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_REQUIRED, "ライセンス更新年月", false);
        }
        // 桁数
        if (!checkLength(licenseUpDt, 8)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_LENGTH, "ライセンス更新年月", false);
        }
        // 形式
        DateVerifier licenseUpDtDateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        licenseUpDtDateVerifier.setNullable(false);
        if (!licenseUpDtDateVerifier.validate(licenseUpDt)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "ライセンス更新年月", false);
        }
    }

    private void checkExistLicenseNo(UICsvUpdateLicense uiCsvUpdateLicense, List existLicenseNoList) {
        // ライセンス番号検索
        boolean exist = false;
        if (existLicenseNoList != null) {
            for (Iterator it = existLicenseNoList.iterator(); it.hasNext();) {
                UIMlLicensee uiMlLicensee = (UIMlLicensee) it.next();
                if (uiMlLicensee.getLicenseNo().equals(uiCsvUpdateLicense.getLicenseNo())) {
                    exist = true;
                    break;
                }
            }
        }
        // ライセンス番号存在チェック
        if (!exist) {
            uiCsvUpdateLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_NOTEXISTS, "ライセンス番号", false);
        }
    }

    private boolean checkColumnNum(String inputData) {
        String[] csvRecord = CsvInputUtil.parseCSVRowData(inputData);
        return csvRecord.length == MAX_COLUMN_NUM;
    }

    private boolean checkRequired(String item) {
        return item != null && item.trim().length() > 0;
    }

    private boolean checkLength(String item, int length) {
        return item == null || item.getBytes().length <= length;
    }
}
