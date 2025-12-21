package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;


/**
 * 予算登録CSVファイルエラー情報ダウンロード
 * 
 * @author Aspac
 */
public class BudgetRegistErrorInfoCsvOutputLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS022L04";


    /**
     * ファイル名取得
     * @param csvOutputDto CSV出力用DTO
     * @return ファイル名
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        // テンプレートCSVファイル名
        return "ErrorInfo.csv";
    }

    /**
     * 出力データ取得処理
     * @param csvOutputDto CSV出力用DTO
     * @return CSV出力データリスト
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        BudgetRegistDto budgetRegistDto = (BudgetRegistDto) csvOutputDto;

        return budgetRegistDto.getListErrorInfo();
    }

    /**
     * 事前条件チェック処理
     * @param csvOutputDto CSV出力用DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }
}
