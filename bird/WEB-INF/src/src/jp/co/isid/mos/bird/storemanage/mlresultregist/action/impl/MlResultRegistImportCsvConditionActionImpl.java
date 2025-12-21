/*
 * 作成日: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistImportCsvConditionAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckCsvLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.RegistCsvLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.TakeCsvLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl.CheckIssueLicenseCsvLogicImpl;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl.CheckUpdateLicenseCsvLogicImpl;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * CSV取込条件Action
 * 
 * @author xyuchida
 */
public class MlResultRegistImportCsvConditionActionImpl implements MlResultRegistImportCsvConditionAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A61";
    public static final String confirm_ACTION_ID    = "BSM008A62";
    public static final String back_ACTION_ID       = "BSM008A63";

    private MlResultRegistDto mlResultRegistDto;
    private TakeCsvLogic takeResultCsvLogic;
    private TakeCsvLogic takeIssuLicenseCsvLogic;
    private TakeCsvLogic takeUpdateLicenseCsvLogic;
    private CheckCsvLogic checkResultCsvLogic;
    private CheckIssueLicenseCsvLogicImpl checkIssueLicenseCsvLogic;
    private CheckUpdateLicenseCsvLogicImpl checkUpdateLicenseCsvLogic;
    private RegistCsvLogic registResultCsvLogic;
    private RegistCsvLogic registIssueLicenseCsvLogic;
    private RegistCsvLogic registUpdateLicenseCsvLogic;

    /**
     * @return mlResultRegistDto を戻します。
     */
    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }
    /**
     * @param mlResultRegistDto mlResultRegistDto を設定。
     */
    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }
    /**
     * @return takeResultCsvLogic を戻します。
     */
    public TakeCsvLogic getTakeResultCsvLogic() {
        return takeResultCsvLogic;
    }
    /**
     * @param takeResultCsvLogic takeResultCsvLogic を設定。
     */
    public void setTakeResultCsvLogic(TakeCsvLogic takeResultCsvLogic) {
        this.takeResultCsvLogic = takeResultCsvLogic;
    }
    /**
     * @return takeIssuLicenseCsvLogic を戻します。
     */
    public TakeCsvLogic getTakeIssuLicenseCsvLogic() {
        return takeIssuLicenseCsvLogic;
    }
    /**
     * @param takeIssuLicenseCsvLogic takeIssuLicenseCsvLogic を設定。
     */
    public void setTakeIssuLicenseCsvLogic(TakeCsvLogic takeIssuLicenseCsvLogic) {
        this.takeIssuLicenseCsvLogic = takeIssuLicenseCsvLogic;
    }
    /**
     * @return takeUpdateLicenseCsvLogic を戻します。
     */
    public TakeCsvLogic getTakeUpdateLicenseCsvLogic() {
        return takeUpdateLicenseCsvLogic;
    }
    /**
     * @param takeUpdateLicenseCsvLogic takeUpdateLicenseCsvLogic を設定。
     */
    public void setTakeUpdateLicenseCsvLogic(
            TakeCsvLogic takeUpdateLicenseCsvLogic) {
        this.takeUpdateLicenseCsvLogic = takeUpdateLicenseCsvLogic;
    }
    /**
     * @return checkResultCsvLogic を戻します。
     */
    public CheckCsvLogic getCheckResultCsvLogic() {
        return checkResultCsvLogic;
    }
    /**
     * @param checkResultCsvLogic checkResultCsvLogic を設定。
     */
    public void setCheckResultCsvLogic(CheckCsvLogic checkResultCsvLogic) {
        this.checkResultCsvLogic = checkResultCsvLogic;
    }
    /**
     * @return checkIssueLicenseCsvLogic を戻します。
     */
    public CheckIssueLicenseCsvLogicImpl getCheckIssueLicenseCsvLogic() {
        return checkIssueLicenseCsvLogic;
    }
    /**
     * @param checkIssueLicenseCsvLogic checkIssueLicenseCsvLogic を設定。
     */
    public void setCheckIssueLicenseCsvLogic(
            CheckIssueLicenseCsvLogicImpl checkIssueLicenseCsvLogic) {
        this.checkIssueLicenseCsvLogic = checkIssueLicenseCsvLogic;
    }
    /**
     * @return checkUpdateLicenseCsvLogic を戻します。
     */
    public CheckUpdateLicenseCsvLogicImpl getCheckUpdateLicenseCsvLogic() {
        return checkUpdateLicenseCsvLogic;
    }
    /**
     * @param checkUpdateLicenseCsvLogic checkUpdateLicenseCsvLogic を設定。
     */
    public void setCheckUpdateLicenseCsvLogic(
            CheckUpdateLicenseCsvLogicImpl checkUpdateLicenseCsvLogic) {
        this.checkUpdateLicenseCsvLogic = checkUpdateLicenseCsvLogic;
    }
    /**
     * @return registResultCsvLogic を戻します。
     */
    public RegistCsvLogic getRegistResultCsvLogic() {
        return registResultCsvLogic;
    }
    /**
     * @param registResultCsvLogic registResultCsvLogic を設定。
     */
    public void setRegistResultCsvLogic(RegistCsvLogic registResultCsvLogic) {
        this.registResultCsvLogic = registResultCsvLogic;
    }
    /**
     * @return registIssueLicenseCsvLogic を戻します。
     */
    public RegistCsvLogic getRegistIssueLicenseCsvLogic() {
        return registIssueLicenseCsvLogic;
    }
    /**
     * @param registIssueLicenseCsvLogic registIssueLicenseCsvLogic を設定。
     */
    public void setRegistIssueLicenseCsvLogic(
            RegistCsvLogic registIssueLicenseCsvLogic) {
        this.registIssueLicenseCsvLogic = registIssueLicenseCsvLogic;
    }
    /**
     * @return registUpdateLicenseCsvLogic を戻します。
     */
    public RegistCsvLogic getRegistUpdateLicenseCsvLogic() {
        return registUpdateLicenseCsvLogic;
    }
    /**
     * @param registUpdateLicenseCsvLogic registUpdateLicenseCsvLogic を設定。
     */
    public void setRegistUpdateLicenseCsvLogic(
            RegistCsvLogic registUpdateLicenseCsvLogic) {
        this.registUpdateLicenseCsvLogic = registUpdateLicenseCsvLogic;
    }

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }

    /**
     * 受験結果CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importResultCsv() {

        // CSVファイルチェック
        validateCsvFile();

        // CSV取込
        List csvRecordList = getTakeResultCsvLogic().execute(getMlResultRegistDto().getUploadedFile());

        // CSVデータチェック
        getCheckResultCsvLogic().execute(csvRecordList, getMlResultRegistDto());

        getMlResultRegistDto().setCsvRecordList(csvRecordList);
        // エラー無し判定
        if (!getMlResultRegistDto().isExistCsvError()) {
            // CSV登録
            getRegistResultCsvLogic().execute(csvRecordList, getMlResultRegistDto());
        }

        // CSV取込結果画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CSVIMPORT_RESULT;
    }

    /**
     * ライセンス発行CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importIssuLicenseCsv() {

        // CSVファイルチェック
        validateCsvFile();

        // CSV取込
        List csvRecordList = getTakeIssuLicenseCsvLogic().execute(getMlResultRegistDto().getUploadedFile());

        // CSVデータチェック
        getCheckIssueLicenseCsvLogic().execute(csvRecordList, getMlResultRegistDto());

        getMlResultRegistDto().setCsvRecordList(csvRecordList);
        // エラー無し判定
        if (!getMlResultRegistDto().isExistCsvError()) {
            // CSV登録
            getRegistIssueLicenseCsvLogic().execute(csvRecordList, getMlResultRegistDto());
        }

        // CSV取込結果画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CSVIMPORT_RESULT;
    }

    /**
     * ライセンス更新CSV取込
     * 
     * @return 画面遷移情報
     */
    public String importUpdateLicenseCsv() {

        // CSVファイルチェック
        validateCsvFile();

        // CSV取込
        List csvRecordList = getTakeUpdateLicenseCsvLogic().execute(getMlResultRegistDto().getUploadedFile());

        // CSVデータチェック
        getCheckUpdateLicenseCsvLogic().execute(csvRecordList, getMlResultRegistDto());

        getMlResultRegistDto().setCsvRecordList(csvRecordList);
        // エラー無し判定
        if (!getMlResultRegistDto().isExistCsvError()) {
            // CSV登録
            getRegistUpdateLicenseCsvLogic().execute(csvRecordList, getMlResultRegistDto());
        }

        // CSV取込結果画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CSVIMPORT_RESULT;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {
        // 条件画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CONDITION;
    }

    /**
     * CSVファイルチェック
     */
    private void validateCsvFile() {
        // CSVファイル拡張子チェック
        UploadedFile uploadedFile = getMlResultRegistDto().getUploadedFile();
        if (uploadedFile == null
                || !uploadedFile.getName().toLowerCase().endsWith(".csv")) {
            throw new InvalidInputException("CSVファイル", "uploadedFile", null);
        }
    }
}
