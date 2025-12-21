package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import jp.co.isid.mos.bird.bill.buyinglistview.dto.BuyingListViewDto;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuPDFInfo;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.PDFDownloadSeikyuLogic;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

public class PDFDownloadSeikyuLogicImpl implements PDFDownloadSeikyuLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L05";

    //ファイルの拡張子
    private static final String FILE_KIND = ".pdf";
    
    // ファイルセパレーター
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
    	BuyingListViewDto dto = (BuyingListViewDto) downloadDto;
        
        int index = dto.getDownloadIndex();
        
        UISeikyuPDFInfo entity = (UISeikyuPDFInfo) dto.getBuyingDataList().get(index);
        
        String fileName = entity.getPdfName() + FILE_KIND;
        
        return fileName;
    }
    
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
    	
    	//BuyingListViewDto dto = (BuyingListViewDto) downloadDto;

        String path = BirdContext.getProperty("fileProperty", "filePathSeikyu");
        
        return path + FILE_SEPARATOR + getFileName(downloadDto);
    }
}
