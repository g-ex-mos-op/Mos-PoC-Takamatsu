/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvResult;

/**
 * 受験結果CSV取込ロジック
 * 
 * @author xyuchida
 */
public class TakeResultCsvLogicImpl extends AbstractTakeCsvLogicImpl {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L14";

    private static final int MAX_COLUMN_NUM = 3;

    private CodeFormatter mlrrExamNoFormatter;

    public CodeFormatter getMlrrExamNoFormatter() {
        return mlrrExamNoFormatter;
    }
    public void setMlrrExamNoFormatter(CodeFormatter mlrrExamNoFormatter) {
        this.mlrrExamNoFormatter = mlrrExamNoFormatter;
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
     * @return 受験結果CSVレコード
     */
    protected Object storeEntity(String[] csvRecord) {
        UICsvResult uiCsvResult = new UICsvResult();
        if (csvRecord != null && csvRecord.length >= MAX_COLUMN_NUM) {
            uiCsvResult.setExamNo(getMlrrExamNoFormatter().format(csvRecord[0], false));
            uiCsvResult.setTotalResult(csvRecord[1]);
            uiCsvResult.setSub2Result(csvRecord[2]);
        }
        return uiCsvResult;
    }
}
