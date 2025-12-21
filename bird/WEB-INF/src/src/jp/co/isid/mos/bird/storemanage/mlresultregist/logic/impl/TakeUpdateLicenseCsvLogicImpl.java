/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvUpdateLicense;

/**
 * ライセンス更新CSV取込ロジック
 * 
 * @author xyuchida
 */
public class TakeUpdateLicenseCsvLogicImpl extends AbstractTakeCsvLogicImpl {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L16";

    private static final int MAX_COLUMN_NUM = 2;

    private CodeFormatter mlrrLicenseNoFormatter;

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
     * @return ライセンス更新CSVレコード
     */
    protected Object storeEntity(String[] csvRecord) {
        UICsvUpdateLicense uiCsvUpdateLicense = new UICsvUpdateLicense();
        if (csvRecord != null && csvRecord.length >= MAX_COLUMN_NUM) {
            uiCsvUpdateLicense.setLicenseNo(getMlrrLicenseNoFormatter().format(csvRecord[0], false));
            uiCsvUpdateLicense.setLicenseUpDt(csvRecord[1]);
        }
        return uiCsvUpdateLicense;
    }
}
