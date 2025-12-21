package jp.co.isid.mos.bird.storemanage.mssonerpointref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランクCSV用　Dto
 * 
 * @author xkinu
 */
public class MssOnerPointRankCsvDto  extends MssOnerPointRankDto implements CsvOutputDto{
    /** CSV対象データ */
    private List csvList;
    /**
     * CSV対象リスト 取得処理
     * 
     * @return csvList を戻します。
     */
    public void setCsvList(List list) {
        csvList = list;
    }
    /**
     * CSV対象リスト 取得処理
     * 
     * @return csvList を戻します。
     */
    public List getCsvList() {
        return csvList;
    }
}