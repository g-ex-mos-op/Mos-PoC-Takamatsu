package jp.co.isid.mos.bird.bill.buyinglistview.logic;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * ダウンロードロジック Interface
 * @author xlee
 */
public interface PDFDownloadNohinshoLogic {
    
    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto);
    
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto);
}
