/*
 * 作成日: 2006/08/02
 *
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.MssOnerPointRankRefAction;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dto.MssOnerPointRankCsvDto;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.SearchLogic;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssOnerPointRankRefAction.SCREEN_ID+"L05";
    /*【ロジック】データ検索ロジック*/
    private SearchLogic searchLogic;
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "POINTONER.csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        try{
            // DTO
            MssOnerPointRankCsvDto dto = (MssOnerPointRankCsvDto) csvOutputDto;
            //CSVデータ構築 & リターン
            return dto.getCsvList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
	}
    /**
     * データ検索ロジック取得処理
     * @return searchLogic を戻します。
     */
    public SearchLogic getMssOnerPointRankSearchLogic() {
        return searchLogic;
    }
    /**
     * データ検索ロジック設定処理
     * @param searchLogic を設定。
     */
    public void setMssOnerPointRankSearchLogic(SearchLogic logic) {
        this.searchLogic = logic;
    }
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("オーナー別獲得ポイントランク 画面DTO");
        }
	}
}
