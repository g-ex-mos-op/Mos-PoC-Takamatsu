/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvRecord;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.TakeCsvLogic;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 抽象CSV取込ロジック
 * 
 * @author xyuchida
 */
public abstract class AbstractTakeCsvLogicImpl implements TakeCsvLogic {

    private static final String MESSAGE_INVALID_CSV = "CSVファイル";

    /**
     * CSV取込
     * 
     * @param uploadedFile アップロードファイル
     * @return CSVレコードList
     */
    public List execute(UploadedFile uploadedFile) {

        // アップロードファイルチェック
        if (uploadedFile == null) {
            throw new InvalidInputException(MESSAGE_INVALID_CSV);
        }

        // CSVデータ読込
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    uploadedFile.getInputStream(),
                    CsvInputUtil.OPTION_CSV_NO_DATA_HEAD_ROW);
        } catch (IOException e) {
            throw new InvalidInputException(MESSAGE_INVALID_CSV);
        }

        // CSV検証
        if (!validate(csvArray)) {
            throw new InvalidInputException(MESSAGE_INVALID_CSV);
        }

        List csvRecordList = new ArrayList();
        for (int i = 0; i < csvArray.length; i++) {
            // Entityに格納
            csvRecordList.add(storeEntity(csvArray[i]));
        }

        // 入力データ取得
        getInputData(uploadedFile, csvRecordList);

        return csvRecordList;
    }

    /**
     * CSVチェック
     * 
     * @param csvArray CSV配列
     * @return チェック結果
     */
    protected abstract boolean validate(String[][] csvArray);

    /**
     * Entity格納
     * 
     * @param csvRecord CSVレコード配列
     * @return Entity
     */
    protected abstract Object storeEntity(String[] csvRecord);

    protected void getInputData(UploadedFile uploadedFile, List csvRecordList) {
        BufferedReader reader = null;
        try {
            // 入力CSVファイル読込
            reader = new BufferedReader(new InputStreamReader(uploadedFile.getInputStream()));
            String line = null;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                if (i < csvRecordList.size()) {
                    // 入力データ設定
                    ((UICsvRecord) csvRecordList.get(i)).getUiCsvErrorInfo().setInputData(line);
                }
            }

        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * カラム数チェック
     * 
     * @param csvArray CSV配列
     * @param num カラム数
     * @return チェック結果
     */
    protected boolean checkColumnNum(String[][] csvArray, int num) {
        // 0件を許可としてチェック
        return checkColumnNum(csvArray, num, true);
    }

    /**
     * カラム数チェック
     * 
     * @param csvArray CSV配列
     * @param num カラム数
     * @param empty 0件を許可するか true:許可 false:不可
     * @return チェック結果
     */
    protected boolean checkColumnNum(String[][] csvArray, int num, boolean empty) {

        boolean result = false;
        // 0件判定
        if (csvArray == null || csvArray.length == 0) {
            result = empty;

        // カラム数判定
        } else if (csvArray[0].length == num) {
            result = true;
        }

        return result;
    }
}
