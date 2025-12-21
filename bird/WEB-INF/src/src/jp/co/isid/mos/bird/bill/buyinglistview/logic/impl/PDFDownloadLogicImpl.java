package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import jp.co.isid.mos.bird.bill.buyinglistview.dto.BuyingListViewDto;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuPDFInfo;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.PDFDownloadNohinshoLogic;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.PDFDownloadSeikyuLogic;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class PDFDownloadLogicImpl implements DownloadLogic{
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L04";
    
    // 納品日・伝票番号順
    private static final String INFO_SEIKYU = "1";
    
    // 納品書順
    private static final String INFO_SEIKYU_NOHINSHO = "2";
    
    // 納品書順PDFダウンロード可能基準発効日
    private String nohinPdfBaseHakkoDate;
    
    
    /**
     * 納品日・伝票番号順のPDFダウンロードロジック
     */
    private PDFDownloadSeikyuLogic downloadSeikyuLogic;
    
    /**
     * 納品書順のPDFダウンロードロジック
     */    
    private PDFDownloadNohinshoLogic downloadNohinshoLogic;
    
    /**
     * 納品日・伝票番号順のPDFダウンロードロジックを取得します<br>
     * @return downloadSeikyuLogic 納品日・伝票番号順のPDFダウンロードロジック
     */
    public PDFDownloadSeikyuLogic getDownloadSeikyuLogic() {
		return downloadSeikyuLogic;
	}

    /**
     * 納品日・伝票番号順のPDFダウンロードロジックを設定します<br>
     * @param PDFDownloadSeikyuLogic downloadSeikyuLogic 納品日・伝票番号順のPDFダウンロードロジック
     */
    public void setDownloadSeikyuLogic(PDFDownloadSeikyuLogic downloadSeikyuLogic) {
		this.downloadSeikyuLogic = downloadSeikyuLogic;
	}
    
    /**
     * 納品書順のPDFダウンロードロジックを取得します<br>
     * @return downloadNohinshoLogic 納品書順のPDFダウンロードロジック
     */
    public PDFDownloadNohinshoLogic getDownloadNohinshoLogic() {
		return downloadNohinshoLogic;
	}

    /**
     * 納品書順のPDFダウンロードロジックを設定します<br>
     * @param PDFDownloadNohinshoLogic downloadNohinshoLogic 納品書順のPDFダウンロードロジック
     */
    public void setDownloadNohinshoLogic(PDFDownloadNohinshoLogic downloadNohinshoLogic) {
		this.downloadNohinshoLogic = downloadNohinshoLogic;
	}
    
    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
    	BuyingListViewDto dto = (BuyingListViewDto) downloadDto;
    	
    	String fileName = "";
        
	    if (INFO_SEIKYU.equals(dto.getCondKbn())) {
	    	//納品日・伝票番号
	    	fileName = getDownloadSeikyuLogic().getFileName(dto);
	    	
	    }
	    else if (INFO_SEIKYU_NOHINSHO.equals(dto.getCondKbn())) {
	    	//納品書
	    	fileName = getDownloadNohinshoLogic().getFileName(dto);
	    }
	        
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
    	
    	BuyingListViewDto dto = (BuyingListViewDto) downloadDto;

        String fullPath = "";
        
        if (INFO_SEIKYU.equals(dto.getCondKbn())) {
        	//納品日・伝票番号
        	fullPath = getDownloadSeikyuLogic().getFileFullPath(dto);
        }
        else if (INFO_SEIKYU_NOHINSHO.equals(dto.getCondKbn())) {
        	//納品書
            if (INFO_SEIKYU_NOHINSHO.equals(dto.getCondKbn())) {
                //--- 2006/10/19 add start 2006/09以前の納品書順PDFはダウンロード不可
                // 発行日が2006/10/01以前は不可
                int index = dto.getDownloadIndex();
                UISeikyuPDFInfo entity = (UISeikyuPDFInfo) dto.getBuyingDataList().get(index);
                String hakkoDate = entity.getHakkoDt();
                if (hakkoDate.compareTo(getNohinPdfBaseHakkoDate()) < 0) {
                    throw new GenericMessageException("納品書順のご提供は２００６年９月以降となります。");
                }
                //--- 2006/10/19 add end
            }
            
        	fullPath = getDownloadNohinshoLogic().getFileFullPath(dto);
        }
        
        return fullPath;
    }
    
    
    /**
     * 事前チェック処理<p>
     * ファイルの存在チェックや権限チェックを行って下さい。<br>
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
     */
    public void validate(final DownloadDto downloadDto) throws ApplicationException {
//--- 2006/10/19 add start 2006/09以前の納品書順PDFはダウンロード不可
        // 発行日が2006/10/01以前は不可
        BuyingListViewDto dto = (BuyingListViewDto) downloadDto;
        if (INFO_SEIKYU_NOHINSHO.equals(dto.getCondKbn())) {
            int index = dto.getDownloadIndex();
            UISeikyuPDFInfo entity = (UISeikyuPDFInfo) dto.getBuyingDataList().get(index);
            String hakkoDate = entity.getHakkoDt();
            if (hakkoDate.compareTo(getNohinPdfBaseHakkoDate()) < 0) {
                throw new GenericMessageException("納品書順のご提供は２００６年９月以降のデータをなります。");
            }
        }
//--- 2006/10/19 add end
    }
    
    /** 
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
    public String getContentType(final DownloadDto downloadDto) {
        return CONTENT_TYPE_PDF;
    }

    public String getNohinPdfBaseHakkoDate() {
        return nohinPdfBaseHakkoDate;
    }

    public void setNohinPdfBaseHakkoDate(String nohinPdfBaseHakkoDate) {
        this.nohinPdfBaseHakkoDate = nohinPdfBaseHakkoDate;
    }
}
