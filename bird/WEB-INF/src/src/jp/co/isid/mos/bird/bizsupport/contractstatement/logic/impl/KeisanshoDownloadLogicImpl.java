package jp.co.isid.mos.bird.bizsupport.contractstatement.logic.impl;

import jp.co.isid.mos.bird.bizsupport.contractstatement.dto.ContractStatementDto;
import jp.co.isid.mos.bird.bizsupport.contractstatement.entity.UICategoryList;
import jp.co.isid.mos.bird.bizsupport.contractstatement.entity.UIKeisanshoList;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class KeisanshoDownloadLogicImpl implements DownloadLogic {
    
    
    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BBS030L02";
    
    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        ContractStatementDto dto = (ContractStatementDto) downloadDto;
        UICategoryList entityCategory = (UICategoryList) dto.getListData().get(dto.getCategoryIndex());
        UIKeisanshoList entity = (UIKeisanshoList) entityCategory.getListData().get(dto.getSelectedIndex());
        return entity.getFileName();
    }
    
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
        ContractStatementDto dto = (ContractStatementDto) downloadDto;
        UICategoryList entityCategory = (UICategoryList) dto.getListData().get(dto.getCategoryIndex());
        UIKeisanshoList entity = (UIKeisanshoList) entityCategory.getListData().get(dto.getSelectedIndex());
        
        return entity.getSaveDir().trim() + entity.getFileName().trim();
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
