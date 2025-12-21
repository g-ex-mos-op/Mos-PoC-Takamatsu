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
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlEntryStatusDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.UIMlLicenseeDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvErrorInfo;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvIssueLicense;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckCsvLogic;

/**
 * ライセンス発行CSVチェックロジック
 * 
 * @author xyuchida
 */
public class CheckIssueLicenseCsvLogicImpl implements CheckCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L18";

    private static final int MAX_COLUMN_NUM = 2;

    private UIMlEntryStatusDao uiMlEntryStatusDao;
    private UIMlLicenseeDao uiMlLicenseeDao;

    public UIMlEntryStatusDao getUiMlEntryStatusDao() {
        return uiMlEntryStatusDao;
    }
    public void setUiMlEntryStatusDao(UIMlEntryStatusDao uiMlEntryStatusDao) {
        this.uiMlEntryStatusDao = uiMlEntryStatusDao;
    }
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

        List examNoList = new ArrayList();
        List duplicateList = new ArrayList();

/*     
 *      20060830　削除　
 *      
        // 受験番号リスト取得(存在チェック用)
        List existExamNoList = getUiMlEntryStatusDao().selectExamNoList(
                mlResultRegistDto.getEntryCd(), mlResultRegistDto.getEntryYear(), mlResultRegistDto.getEntryKai());
 */
        
//20060830 追加 start ----------------------------------------------------------------------------- 
        // ここ二年の受験番号リスト取得(存在チェック用)
        String entryYear = mlResultRegistDto.getEntryYear();
        List existExamNoList = getUiMlEntryStatusDao().selectTwoYearExamNoList(
                        mlResultRegistDto.getEntryCd(), entryYear, getLastYear(entryYear));
//20060830 追加 end ----------------------------------------------------------------------------- 
 
/*     
 *      20060830　削除 
 *      
     // ライセンス番号リスト取得(非存在チェック用)
        List existLicenseNoList = getUiMlLicenseeDao().selectLicenseNoList();

        // ライセンス発行済受験番号リスト取得
        List licensedExamNoList = createLicensedExamNoList(mlResultRegistDto);        
 */

        // 全レコード処理
        for (Iterator it = csvList.iterator(); it.hasNext();) {
            UICsvIssueLicense uiCsvIssueLicense = (UICsvIssueLicense) it.next();

            // カラム数チェック
            if (!checkColumnNum(uiCsvIssueLicense.getUiCsvErrorInfo().getInputData())) {
                uiCsvIssueLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_FORMAT);

            } else {
                // 項目チェック
                checkItem(uiCsvIssueLicense);
                // 受験番号存在チェック
                checkExistExamNo(uiCsvIssueLicense, existExamNoList);

/*     
 *      2006/08/30　削除
 *      
             // ライセンス番号非存在チェック
                checkExistLicenseNo(uiCsvIssueLicense, existLicenseNoList);
                // ライセンス発行済受験番号チェック
                if (licensedExamNoList.contains(uiCsvIssueLicense.getExamNo())) {
                    uiCsvIssueLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_LICENSED, (Object[]) null, false);
                }                
 */
                // 重複受験番号リスト作成
                if (!uiCsvIssueLicense.getUiCsvErrorInfo().isExistError()) {
                    String examNo = uiCsvIssueLicense.getExamNo();
                    // 未設定は重複チェック対象外
                    if (examNo != null && examNo.trim().length() > 0) {
                        // 受験番号重複チェック
                        if (examNoList.contains(examNo)) {
                            // 重複受験番号リストに追加
                            duplicateList.add(examNo);
                        } else {
                            examNoList.add(examNo);
                        }
                    }
                }
            }
        }

        // 重複エラー設定
        for (Iterator it = csvList.iterator(); it.hasNext();) {
            UICsvIssueLicense uiCsvResult = (UICsvIssueLicense) it.next();
            // 重複チェック
            if (duplicateList.contains(uiCsvResult.getExamNo())) {
                uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_DUPLICATE, "受験番号", false);
            }
        }
    }

    private void checkItem(UICsvIssueLicense uiCsvIssueLicense) {

        UICsvErrorInfo uiCsvErrorInfo = uiCsvIssueLicense.getUiCsvErrorInfo();

        // 受験番号
        String examNo = uiCsvIssueLicense.getExamNo();
        // 必須
        if (!checkRequired(examNo)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_REQUIRED, "受験番号", false);
        }
        // 桁数
        if (!checkLength(examNo, 5)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_LENGTH, "受験番号", false);
        }
        // 形式
        CodeVerifier examNoCodeVerifier = new CodeVerifier(5);
        examNoCodeVerifier.setNullable(false);
        if (!examNoCodeVerifier.validate(examNo)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "受験番号", false);
        }

        // ライセンス番号
        String licenseNo = uiCsvIssueLicense.getLicenseNo();
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
    }

    private void checkExistExamNo(UICsvIssueLicense uiCsvIssueLicense, List existExamNoList) {
        // 受験番号検索
        boolean exist = false;
        if (existExamNoList != null) {
            for (Iterator it = existExamNoList.iterator(); it.hasNext();) {
                UIMlEntryStatus uiMlEntryStatus = (UIMlEntryStatus) it.next();
                if (uiMlEntryStatus.getExamNo().equals(uiCsvIssueLicense.getExamNo())) {
                    exist = true;
                    break;
                }
            }
        }
        // 受験番号存在チェック
        if (!exist) {
            uiCsvIssueLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_NOTEXISTS, "受験番号", false);
        }
    }

    private void checkExistLicenseNo(UICsvIssueLicense uiCsvIssueLicense, List existLicenseNoList) {
        // ライセンス番号検索
        boolean exist = false;
        if (existLicenseNoList != null) {
            for (Iterator it = existLicenseNoList.iterator(); it.hasNext();) {
                UIMlLicensee uiMlLicensee = (UIMlLicensee) it.next();
                if (uiMlLicensee.getLicenseNo().equals(uiCsvIssueLicense.getLicenseNo())) {
                    exist = true;
                    break;
                }
            }
        }
        // ライセンス番号非存在チェック
        if (exist) {
            uiCsvIssueLicense.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_EXISTS, "ライセンス番号", false);
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

    private List createLicensedExamNoList(MlResultRegistDto mlResultRegistDto) {
        List licensedExamNoList = new ArrayList();
        List licensedStaffList = getUiMlEntryStatusDao().selectLicensedStaffList(
                mlResultRegistDto.getEntryCd(),
                mlResultRegistDto.getEntryYear(),
                mlResultRegistDto.getEntryKai(),
                mlResultRegistDto.getSysDate());
        for (Iterator it = licensedStaffList.iterator(); it.hasNext();) {
            UIMlEntryStatus uiMlEntryStatus = (UIMlEntryStatus) it.next();
            licensedExamNoList.add(uiMlEntryStatus.getExamNo());
        }
        return licensedExamNoList;
    }

//  20060830 追加 start ----------------------------------------------------------------------------- 
    private String getLastYear( String thisYearStr){
        String lastYearStr;
        int lastYear = Integer.parseInt(thisYearStr) - 1;
        lastYearStr = lastYear + "";
        return lastYearStr;
        
    }
//  20060830 追加 end ----------------------------------------------------------------------------- 
}
