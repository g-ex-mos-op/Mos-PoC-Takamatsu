/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;

import java.io.IOException;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ファイルダウンロードアクション用 Interface
 * @author xnkusama
 */
public interface FileDownloadAction {
//    /**
//     * ダウンロードロジック設定処理
//     * @param downloadLogic
//     */
//    public void setDownloadLogic(final DownloadLogic downloadLogic);
//
//    /**
//     * HttpResponse設定処理
//     * @param response
//     */
//    public void setResponse(final HttpServletResponse response);
//
//    /**
//     * ダウンロード用DTO設定処理
//     * @param downloadDto
//     */
//    public void setDownloadDto(final DownloadDto downloadDto);
//
    /**
     * ダウンロード メイン処理
     * @throws IOException
     * @throws ApplicationException
     */
    public void download() throws ApplicationException;
}