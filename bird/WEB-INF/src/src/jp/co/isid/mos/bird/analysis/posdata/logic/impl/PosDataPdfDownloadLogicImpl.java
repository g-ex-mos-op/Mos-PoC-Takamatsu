package jp.co.isid.mos.bird.analysis.posdata.logic.impl;

import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class PosDataPdfDownloadLogicImpl implements DownloadLogic {
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BDT001L03";
    
    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    
    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
	public String getFileName(final DownloadDto downloadDto) {
		
		PosDataFormDto dto = (PosDataFormDto) downloadDto;
		
		return dto.getPdfFileName();
	}
	
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
	public String getFileFullPath(final DownloadDto downloadDto) {
		
		PosDataFormDto dto = (PosDataFormDto) downloadDto;
		
		String path = BirdContext.getProperty("fileProperty","filePathPos");

		return path + FILE_SEPARATOR + dto.getPdfFileName();
	}
	
    /** 
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
    public String getContentType(final DownloadDto downloadDto) {
    	
    	return CONTENT_TYPE_PDF;
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
    
}
