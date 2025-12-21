/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvIssueLicense;

/**
 * ライセンス発行CSV取込ロジック
 * 
 * @author xyuchida
 */
public class TakeIssuLicenseCsvLogicImpl extends AbstractTakeCsvLogicImpl {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L15";

    private static final int MAX_COLUMN_NUM = 2;

    private CodeFormatter mlrrExamNoFormatter;
    private CodeFormatter mlrrLicenseNoFormatter;

    public CodeFormatter getMlrrExamNoFormatter() {
        return mlrrExamNoFormatter;
    }
    public void setMlrrExamNoFormatter(CodeFormatter mlrrExamNoFormatter) {
        this.mlrrExamNoFormatter = mlrrExamNoFormatter;
    }
    public CodeFormatter getMlrrLicenseNoFormatter() {
        return mlrrLicenseNoFormatter;
    }
    public void setMlrrLicenseNoFormatter(CodeFormatter mlrrLicenseNoFormatter) {
        this.mlrrLicenseNoFormatter = mlrrLicenseNoFormatter;
    }
    /**
     * CSVチェック
     * 
     * @param csvArray CSV配列
     * @return チェック結果
     */
    protected boolean validate(String[][] csvArray) {
        // レコード数チェック
        return csvArray != null && csvArray.length > 0;
    }

    /**
     * Entity格納
     * 
     * @param csvRecord CSVレコード配列
     * @return ライセンス発行CSVレコード
     */
    protected Object storeEntity(String[] csvRecord) {
        UICsvIssueLicense uiCsvIssueLicense = new UICsvIssueLicense();
        if (csvRecord != null && csvRecord.length >= MAX_COLUMN_NUM) {
            uiCsvIssueLicense.setExamNo(getMlrrExamNoFormatter().format(csvRecord[0], false));
            uiCsvIssueLicense.setLicenseNo(getMlrrLicenseNoFormatter().format(csvRecord[1], false));
        }
        return uiCsvIssueLicense;
    }
}
