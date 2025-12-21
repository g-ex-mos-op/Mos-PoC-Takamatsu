package jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl;

import jp.co.isid.mos.bird.bizreport.netorderreport.dto.SearchDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 * ネット注文帳票Pdfダウンロード
 * @author zzw
 *
 */
public class NetOrderReportPdfLogicImpl implements DownloadLogic{

    /* ロジックID */
    public static final String LOGIC_ID = "BBR019L04";

    /** DTO【条件部情報】 */
    private SearchDto searchDto;
    /** ファイル名取得LOGIC */
    private NetOrderReportDownloadLogicImpl netorderReportDownloadLogic;

	/**
	 * PDFファイル名取得
	 */
	public String getFileName(DownloadDto downloadDto) {
		SearchDto searchDto = (SearchDto) downloadDto;
		return searchDto.getFileName(searchDto);
	}

	/**
	 * ダウンロードファイルのフルパス取得処理
	 * @return String ファイル名
	 */
	public String getFileFullPath(DownloadDto downloadDto) {
		SearchDto searchDto = (SearchDto) downloadDto;
		return searchDto.getPdfFileNameTemp();
	}

	/**
	 * 事前チェック処理
     * ファイルの存在チェックや権限チェックを行って下さい。
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
	 */
	public void validate(DownloadDto downloadDto) throws ApplicationException {

	}

	/**
	 * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
	public String getContentType(DownloadDto downloadDto) {
		//PDF
		return CONTENT_TYPE_PDF;
	}

	/**
	 * ファイル名取得LOGICを取得する
	 * @return ファイル名取得LOGIC
	 */
	public NetOrderReportDownloadLogicImpl getNetorderReportDownloadLogic() {
		return netorderReportDownloadLogic;
	}

	/**
	 * ファイル名取得LOGICを設定する
	 * @param netorderReportDownloadLogic ファイル名取得LOGIC
	 */
	public void setNetorderReportDownloadLogic(NetOrderReportDownloadLogicImpl netorderReportDownloadLogic) {
		this.netorderReportDownloadLogic = netorderReportDownloadLogic;
	}
}
