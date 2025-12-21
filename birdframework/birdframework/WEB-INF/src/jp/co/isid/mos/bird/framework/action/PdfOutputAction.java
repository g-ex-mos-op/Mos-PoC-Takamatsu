/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;


/**
 * PDF出力アクション Interface
 * @author xnkusama
 */
public interface PdfOutputAction {
    /**
     * HttpServletResponse設定処理
     * @param httpServletResponse
     */
//    public void setHttpServletResponse(HttpServletResponse httpServletResponse);

    /**
     * HttpServletResponse取得処理
     * @return HttpServletResponse
     */
//    public HttpServletResponse getHttpServletResponse();

    /**
     * CSV出力用ロジック設定処理
     * @param downloadLogic
     */
//    public void setPdfOutputLogic(final PdfOutputLogic downloadLogic);

    /**
     * CSV出力用DTO設定処理
     * @param downloadDto
     */
//    public void setPdfOutputDto(final PdfOutputDto pdfOutputDto);

    /**
     * PDFダウンロードアクション
     * @return
     */
    public String downloadPDF();

    /**
     * PDFファイル保存アクション
     * @return
     */
    public String savePDF();

    /**
     * PDFファイル作成処理
     * 処理内容は画面ロジックで定義します
     * @return
     */
    public String createPDF();
}