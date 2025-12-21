package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

public class NewsRegistDownloadTenpuLogicImpl implements DownloadLogic {
    
    
    public static final int MODE_ATTACH_FILE1 = 1;
    public static final int MODE_ATTACH_FILE2 = 2;
    public static final int MODE_ATTACH_FILE3 = 3;


    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        InformRegistDto dto = (InformRegistDto) downloadDto;
        UINews entity = (UINews) dto.getSearchResultList().get(dto.getDownloadIndex());
        
        String fileName = "";
        
        switch (entity.getDownloadAttachIndex()) {
            case MODE_ATTACH_FILE1:
                fileName = entity.getAttachFl1();
                break;
            case MODE_ATTACH_FILE2:
                fileName = entity.getAttachFl2();
                break;
            case MODE_ATTACH_FILE3:
                fileName = entity.getAttachFl3();
                break;
            default:
                break;
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
        InformRegistDto dto = (InformRegistDto) downloadDto;
        UINews entity = (UINews) dto.getSearchResultList().get(dto.getDownloadIndex());
        
        String path = BirdContext.getProperty("fileProperty", "filePathOshirase") + File.separator;
        
        String fileName = entity.getRegDate() + "_" + entity.getSeq() + "_";
        switch (entity.getDownloadAttachIndex()) {
            case MODE_ATTACH_FILE1:
                fileName += "2_" + getFileName(downloadDto);
                break;
            case MODE_ATTACH_FILE2:
                fileName += "3_" + getFileName(downloadDto);
                break;
            case MODE_ATTACH_FILE3:
                fileName += "4_" + getFileName(downloadDto);
                break;
            default:
                break;
        }

        
        return path + fileName;
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
