/*
 * 作成日: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * CSVチェックロジック
 * 
 * @author xyuchida
 */
public interface CheckCsvLogic {

    /**
     * CSVチェック
     * 
     * @param csvList CSVレコードList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto);
}
