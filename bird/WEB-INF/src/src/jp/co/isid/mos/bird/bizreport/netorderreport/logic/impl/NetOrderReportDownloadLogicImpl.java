package jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.isid.mos.bird.bizreport.netorderreport.dto.SearchDto;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 * ファイル名取得LOGIC
 * @author zzw
 *
 */
public class NetOrderReportDownloadLogicImpl implements DownloadLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BBR019L02";

    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    //中間ファイル名
    private static final String FILENAME_TEMP_EXCEL = "temp" + FILE_SEPARATOR + "temp_excel2pdf";
    private static final String FILENAME_TEMP_PDF = "temp" + FILE_SEPARATOR + "temp_excel2pdf";

    //ファイル拡張子
    private static final String FILENAME_EXT_EXCEL = ".xlsx";
    private static final String FILENAME_EXT_PDF = ".pdf";

    //プロパティ定義のキー
    private static final String KEY_TEMPLATE_FILE_PATH = "filePathNetOrderReport";
    private static final String KEY_TEMPLATE_FILE_FOR_EXCEL = "fileNameNetOrderReportExcel";
    private static final String KEY_TEMPLATE_FILE_FOR_PDF = "fileNameNetOrderReportPdf";


    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
    	String fileName = "";
    	SearchDto searchDto = (SearchDto) downloadDto;
    	if (searchDto.isDownloadExcelFlg()) {
    		fileName = BirdContext.getProperty("fileProperty", KEY_TEMPLATE_FILE_FOR_EXCEL);
    	} else if (searchDto.isDownloadPdfFlg()) {
    		fileName = BirdContext.getProperty("fileProperty", KEY_TEMPLATE_FILE_FOR_PDF);
    	}
        return fileName.trim();
    }

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getPath(final DownloadDto downloadDto) {
        String filePath = BirdContext.getProperty("fileProperty", KEY_TEMPLATE_FILE_PATH);
        return filePath.trim();
    }

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
    	SearchDto searchDto = (SearchDto) downloadDto;
    	return getPath(downloadDto) + FILE_SEPARATOR + getFileName(downloadDto);
    }

    /**
     * EXCEL2PDF変換前のEXCELのTEMPファイル名とEXCEL2PDF変換後PDFのTEMPファイル名取得処理
     * @param downloadDto
     * @return
     */
    public void getTempFileName(final DownloadDto downloadDto) {
    	String currentTimestamp = getCurrentTimestamp();

    	String excelFileNameTemp = getPath(downloadDto) + FILE_SEPARATOR + FILENAME_TEMP_EXCEL + currentTimestamp + FILENAME_EXT_EXCEL;
    	String pdfFileNameTemp = getPath(downloadDto) + FILE_SEPARATOR + FILENAME_TEMP_PDF + currentTimestamp + FILENAME_EXT_PDF;
    	SearchDto searchDto = (SearchDto) downloadDto;
    	searchDto.setExcelFileNameTemp(excelFileNameTemp);
    	searchDto.setPdfFileNameTemp(pdfFileNameTemp);

    }

    /**
     * システム日時ミリ秒まで取得処理
     * @return String
     */
    public String getCurrentTimestamp() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	return sdf.format(dt);
    }

    /**
     * 事前チェック処理<p>
     * ファイルの存在チェックや権限チェックを行って下さい。<br>
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
     */
    public void validate(final DownloadDto downloadDto) throws ApplicationException {

    }

    /**
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
    public String getContentType(final DownloadDto downloadDto) {
    	//EXCEL
   		return "application/vnd.ms-excel";
    }

}
