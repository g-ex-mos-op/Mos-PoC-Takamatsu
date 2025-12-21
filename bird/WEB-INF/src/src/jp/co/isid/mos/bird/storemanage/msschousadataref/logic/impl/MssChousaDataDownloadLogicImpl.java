package jp.co.isid.mos.bird.storemanage.msschousadataref.logic.impl;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MssFileInfo;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.UITabData;
public class MssChousaDataDownloadLogicImpl implements DownloadLogic {

    /*LogicID*/
    public static final String LOGIC_ID =    "BSM011L04";

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
        MssChousaDataRefDto dto = (MssChousaDataRefDto) downloadDto;
        UITabData tabu = (UITabData)dto.getTabList().get(dto.getDownloadIndex());
        MssFileInfo  entity = (MssFileInfo) tabu.getListData().get(dto.getListIndex());
        String name = entity.getFileName();
        if(name != null){
            name = name.trim();
        }
 
        
        return name ;
    }
    
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
        
        MssChousaDataRefDto dto = (MssChousaDataRefDto) downloadDto;
        int index = dto.getDownloadIndex();
        MssFileInfo  entity = (MssFileInfo) dto.getFileList().get(index);
        String nendo = entity.getNendo();
        String path = "";
        path = BirdContext.getProperty("fileProperty", "filePathMss");
        path = path + FILE_SEPARATOR + nendo + FILE_SEPARATOR;
        
        
        return path + getFileName(downloadDto);
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
        return CONTENT_TYPE_PDF;
    }
}
