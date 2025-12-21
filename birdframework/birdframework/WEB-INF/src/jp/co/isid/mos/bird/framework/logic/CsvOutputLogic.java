package jp.co.isid.mos.bird.framework.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * CVSダウンロード用 ロジックInterface
 * @author xnkusama
 */
public interface CsvOutputLogic {

    /* ファイル名取得 */
    public String getFileName(final CsvOutputDto csvOutputDto);
    
//    /* データ格納クラス */
//    public CsvOutputEntity getCsvOutputEntity(final CsvOutputDto csvOutputDto);
    
//    /* データ作成処理（DB検索処理等）*/
//    public List createData(final CsvOutputDto csvOutputDto) throws ApplicationException;
    
    /* 出力データ取得処理 */
    public List getOutputData(final CsvOutputDto csvOutputDto);

    /* 事前条件チェック処理 */
    public void validate(final CsvOutputDto csvOutputDto);
}
