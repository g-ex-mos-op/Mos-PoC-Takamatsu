package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;

/**
 * 関連ファイルのダウンロード
 * @author xytamura
 */
public class NewsRegistDownloadKanrenLogicImpl implements DownloadLogic {
    

    // 情報種別：連絡
    private static final String INFO_SHU_CONTACT = "01";
    // 情報種別：フォーム
    private static final String INFO_SHU_FORM = "04";
    // 情報種別：通達
    private static final String INFO_SHU_TUTAU = "02";
    // 情報種別：文書
    private static final String INFO_SHU_BUNSHO = "03";
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
        InformRegistDto dto = (InformRegistDto) downloadDto;
        int index = dto.getDownloadKanrenIndex();
        
        UIDocSearch entity = (UIDocSearch) dto.getListKanrenBunsho().get(index);
        
        String fileName = entity.getRegDate() + "_" + entity.getSeq() + "_1_";
        
        fileName += entity.getFileName();
        
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
        InformRegistDto dto = (InformRegistDto) downloadDto;
        int index = dto.getDownloadKanrenIndex();
        UIDocSearch entity = (UIDocSearch) dto.getListKanrenBunsho().get(index);
        
        String infoShu = entity.getInfoShu();
        
        String path = "";
        if (INFO_SHU_CONTACT.equals(infoShu)) {
            path = BirdContext.getProperty("fileProperty", "filePathRenraku");
        }
        else if (INFO_SHU_BUNSHO.equals(infoShu)) {
            path = BirdContext.getProperty("fileProperty", "filePathBunsho");
        }
        else if (INFO_SHU_FORM.equals(infoShu)) {
            path = BirdContext.getProperty("fileProperty", "filePathForm");
        }
        else if (INFO_SHU_TUTAU.equals(infoShu)) {
            path = BirdContext.getProperty("fileProperty", "filePathTutatu");
        }
        //path = path + FILE_SEPARATOR + DIR_NAME + FILE_SEPARATOR;
        return path + FILE_SEPARATOR + getFileName(downloadDto);
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
        //return CONTENT_TYPE_PDF;
        return "text/plain";
    }
}
