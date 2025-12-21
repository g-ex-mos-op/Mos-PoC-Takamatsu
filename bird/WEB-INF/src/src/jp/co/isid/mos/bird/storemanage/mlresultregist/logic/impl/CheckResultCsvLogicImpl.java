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
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvErrorInfo;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckCsvLogic;

/**
 * 受験結果CSVチェックロジック
 * 
 * @author xyuchida
 */
public class CheckResultCsvLogicImpl implements CheckCsvLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L17";

    private static final int MAX_COLUMN_NUM = 3;

    private UIMlEntryStatusDao uiMlEntryStatusDao;
    private List mlrrTotalResultValueList;
    private List mlrrSub2ResultValueList;

    public UIMlEntryStatusDao getUiMlEntryStatusDao() {
        return uiMlEntryStatusDao;
    }
    public void setUiMlEntryStatusDao(UIMlEntryStatusDao uiMlEntryStatusDao) {
        this.uiMlEntryStatusDao = uiMlEntryStatusDao;
    }
    public List getMlrrSub2ResultValueList() {
        return mlrrSub2ResultValueList;
    }
    public void setMlrrSub2ResultValueList(List mlrrSub2ResultValueList) {
        this.mlrrSub2ResultValueList = mlrrSub2ResultValueList;
    }
    public List getMlrrTotalResultValueList() {
        return mlrrTotalResultValueList;
    }
    public void setMlrrTotalResultValueList(List mlrrTotalResultValueList) {
        this.mlrrTotalResultValueList = mlrrTotalResultValueList;
    }

    /**
     * CSVチェック
     * 
     * @param csvList CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto) {

        List examNoList = new ArrayList();
        List duplicateList = new ArrayList();

        // 受験番号リスト取得(存在チェック用)
        List existExamNoList = getUiMlEntryStatusDao().selectExamNoList(
                mlResultRegistDto.getEntryCd(), mlResultRegistDto.getEntryYear(), mlResultRegistDto.getEntryKai());
        // ライセンス発行済受験番号リスト取得
        List licensedExamNoList = createLicensedExamNoList(mlResultRegistDto);

        // 全レコード処理
        for (Iterator it = csvList.iterator(); it.hasNext();) {
            UICsvResult uiCsvResult = (UICsvResult) it.next();

            // カラム数チェック
            if (!checkColumnNum(uiCsvResult.getUiCsvErrorInfo().getInputData())) {
                uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_FORMAT);

            } else {
                // 項目チェック
                checkItem(uiCsvResult);
                // 受験番号存在チェック
                checkExistExamNo(uiCsvResult, existExamNoList);
                // ライセンス発行済受験番号チェック
                if (licensedExamNoList.contains(uiCsvResult.getExamNo())) {
                    uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_LICENSED, (Object[]) null, false);
                }

                // 重複受験番号リスト作成
                if (!uiCsvResult.getUiCsvErrorInfo().isExistError()) {
                    String examNo = uiCsvResult.getExamNo();
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
            UICsvResult uiCsvResult = (UICsvResult) it.next();
            // 重複チェック
            if (duplicateList.contains(uiCsvResult.getExamNo())) {
                uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_DUPLICATE, "受験番号", false);
            }
        }
    }

    private void checkItem(UICsvResult uiCsvResult) {

        UICsvErrorInfo uiCsvErrorInfo = uiCsvResult.getUiCsvErrorInfo();

        // 受験番号
        String examNo = uiCsvResult.getExamNo();
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

        // 総合結果
        String totalResult = uiCsvResult.getTotalResult();
        // 必須
        if (!checkRequired(totalResult)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_REQUIRED, "総合結果", false);
        }
        // 桁数
        if (!checkLength(totalResult, 1)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_LENGTH, "総合結果", false);
        }
        // 形式
        CodeVerifier totalResultCodeVerifier = new CodeVerifier(1);
        totalResultCodeVerifier.setNullable(false);
        if (!totalResultCodeVerifier.validate(totalResult)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "総合結果", false);
        }
        // 設定値
        if (!getMlrrTotalResultValueList().contains(totalResult)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "総合結果", false);
        }

        // 筆記テスト結果
        String sub2Result = uiCsvResult.getSub2Result();
        // 必須
        if (!checkRequired(sub2Result)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_REQUIRED, "筆記テスト結果", false);
        }
        // 桁数
        if (!checkLength(sub2Result, 1)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_LENGTH, "筆記テスト結果", false);
        }
        // 形式
        CodeVerifier sub2ResultCodeVerifier = new CodeVerifier(1);
        sub2ResultCodeVerifier.setNullable(false);
        if (!sub2ResultCodeVerifier.validate(sub2Result)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "筆記テスト結果", false);
        }
        // 設定値
        if (!getMlrrSub2ResultValueList().contains(sub2Result)) {
            uiCsvErrorInfo.setError(UICsvErrorInfo.ERRORCODE_INVALID, "筆記テスト結果", false);
        }
    }

    private void checkExistExamNo(UICsvResult uiCsvResult, List existExamNoList) {
        // 受験番号検索
        boolean exist = false;
        if (existExamNoList != null) {
            for (Iterator it = existExamNoList.iterator(); it.hasNext();) {
                UIMlEntryStatus uiMlEntryStatus = (UIMlEntryStatus) it.next();
                if (uiMlEntryStatus.getExamNo().equals(uiCsvResult.getExamNo())) {
                    exist = true;
                    break;
                }
            }
        }
        // 受験番号存在チェック
        if (!exist) {
            uiCsvResult.getUiCsvErrorInfo().setError(UICsvErrorInfo.ERRORCODE_NOTEXISTS, "受験番号", false);
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
}
