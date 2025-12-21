package jp.co.isid.mos.bird.storemanage.anshinreport.logic.impl;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.entity.FileInfo;
import jp.co.isid.mos.bird.storemanage.anshinreport.entity.ResultInfo;
public class DownloadLogicImpl implements DownloadLogic {

    /*LogicID*/
    public static final String LOGIC_ID =    "BSM018L04";

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
        AnshinReportDto dto = (AnshinReportDto) downloadDto;
        FileInfo  entity = (FileInfo)((ResultInfo) dto.getResultList().get(dto.getResultIndex())).getFileList().get(dto.getListIndex());
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
        
        AnshinReportDto dto = (AnshinReportDto) downloadDto;
        ResultInfo resultInfo = (ResultInfo) dto.getResultList().get(dto.getResultIndex());
        FileInfo  entity = (FileInfo) resultInfo.getFileList().get(dto.getListIndex());
        StringBuffer path = new StringBuffer();
        path.append(BirdContext.getProperty("fileProperty", "filePathAnshin"));
        path.append(FILE_SEPARATOR);
        path.append(resultInfo.getJissiNendo());
        path.append(FILE_SEPARATOR);
        path.append(entity.getFullFileName());
        
        return path.toString();
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
