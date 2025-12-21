/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;

import java.io.IOException;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * CSV出力アクション用Interface
 * @author xnkusama
 */
public interface CsvOutputAction {
//    /**
//     * HttpServletResponse設定処理
//     * @param httpServletResponse
//     */
//    public void setResponse(HttpServletResponse httpServletResponse);
//
//    /**
//     * CSV出力用ロジック設定処理
//     * @param downloadLogic
//     */
//    public void setCsvOutputLogic(final CsvOutputLogic downloadLogic);
//
//    /**
//     * CSV出力用DTO設定処理
//     * @param downloadDto
//     */
//    public void setCsvOutputDto(final CsvOutputDto csvOutputDto);
//
    /**
     * ダウンロード メイン処理
     * @throws IOException
     * @throws ApplicationException
     */
    public void downloadCsv() throws IOException, ApplicationException;
}