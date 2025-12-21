package jp.co.isid.mos.bird.entry.nationalviewlist.action;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * CSV出力アクション用Interface
 * 
 * @author xkinu
 */
public interface CsvDownloadAction {
    /**
     * HttpServletResponse設定処理
     * @param httpServletResponse
     */
    public void setResponse(HttpServletResponse httpServletResponse);

    /**
     * CSV出力用ロジック設定処理
     * @param downloadLogic
     */
    public void setCsvOutputLogic(final CsvOutputLogic downloadLogic);

    /**
     * CSV出力用DTO設定処理
     * @param downloadDto
     */
    public void setCsvOutputDto(final CsvOutputDto csvOutputDto);

    /**
     * ダウンロード メイン処理
     */
    public void downloadCsv();

}
