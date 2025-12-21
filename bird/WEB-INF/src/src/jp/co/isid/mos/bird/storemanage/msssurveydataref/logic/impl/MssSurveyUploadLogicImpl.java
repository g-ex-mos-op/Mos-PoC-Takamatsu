/*
 * 作成日: 2006/08/15
 *
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dao.UIMssBatchDao;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyUploadDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.UIMssBatch;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * ファイルアップロードロジック
 * @author inazawa
 */
public class MssSurveyUploadLogicImpl implements UploadLogic {
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    
    private  UIMssBatchDao UIMssBatchDao;

    /** ファイルタイプ：項目マスタ */
    private static final String UPLOAD_FILE_TYPE_MSPM = "JAVA.MSPM.DAT";
    /** ファイルタイプ：評価データ */
    private static final String UPLOAD_FILE_TYPE_MSPD = "JAVA.MSPD.DAT";
    /** ファイルタイプ：調査データ */
    private static final String UPLOAD_FILE_TYPE_MSPH = "JAVA.MSPH.DAT";
    /*LogicID*/
    public static final String LOGIC_ID  = "BSM012L03";

    private static final String PGM_ID_TORI = "BMSPTORI";
    private static final String PGM_ID_SIBU = "BMSPSIBU";
    private static final String[] PGM_ID = {PGM_ID_TORI, PGM_ID_SIBU};
    private static final String STAT_OK = "0";

    
    /**
     * ファイル名取得処理
     */
    public String getFileName(UploadDto uploadDto) {

        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;
        String fileName = "";
        String fileUpdateNm = "";
        if (dto.getUploadedFile() != null) {
            String file = dto.getUploadedFile().getName();
            fileName = file.substring(file.lastIndexOf(FILE_SEPARATOR) + 1);
        } else {
            fileName = dto.getUploadFileName();
        }
        fileName = dto.getUploadFileName();
        dto.setUpdateFlg(0);
        //1.項目マスタ2.評価データ3.調査データ
        if(dto.getFileFlg() == 1){
            fileUpdateNm = UPLOAD_FILE_TYPE_MSPM;
            dto.setUploadFileKoumokuName(fileName);
        }else if(dto.getFileFlg() == 2){    
            fileUpdateNm = UPLOAD_FILE_TYPE_MSPD;
            dto.setUploadFileHyoukaName(fileName);
        }else if(dto.getFileFlg() == 3){    
            fileUpdateNm = UPLOAD_FILE_TYPE_MSPH;
            dto.setUploadFileChousaName(fileName);
        }
        return fileUpdateNm;
    }

    /**
     * ファイル フルパス取得処理
     * @param UploadDto
     * @return String
     */
    public String getFileFullPath(UploadDto uploadDto) {
        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;
        String file = BirdContext.getProperty("fileProperty", "upLoadPathMss");
        file += FILE_SEPARATOR;
        file += getFileName(dto);
     
        return file;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileName(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileName(UploadDto uploadDto) {

        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;

        return dto.getTempFileName();   
        }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileFullPath(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileFullPath(UploadDto uploadDto) {
        
        
        String file = BirdContext.getProperty("fileProperty", "upLoadPathMss");
        file += FILE_SEPARATOR;
        file += getTempFileName(uploadDto);
        return file;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#validate()
     */
    public void validate(final UploadDto uploadDto) throws ApplicationException {
        UploadedFile upFile = uploadDto.getUploadedFile();
        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;

        if (upFile == null) {
            throw new NotSelectedException("ファイル");
        }

        
        // ファイル名称のサイズチェック
        LengthVerifier lent = new LengthVerifier(90);
        if (!lent.validate( uploadDto.getUploadFileName() ) ) {
            throw new GenericCommentException("ファイル名は全角９０文字まで", "uploadFile", null);
        }
        
        //禁則文字対応
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if ( ! defJap.validate( uploadDto.getUploadFileName() ) ) {
            throw new InvalidInputException("ファイル名");
        }
        // ファイル存在チェック（空のファイルは不可）
        if (upFile.getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFile", null);
        }
        if (upFile == null) {
            throw new NotSelectedException("ファイル");
        }
        //取り込みと支部洗換えの結合ステータス取得
        UIMssBatch uIMssBatch = getUIMssBatchDao().selectJobName(PGM_ID,"01");
        String statKbn = uIMssBatch.getStatKbn().trim();
        if(!statKbn.equals(STAT_OK)){
            throw new CannotExecuteWithReasonException("処理中","アップロード","「処理可能」になるまでしばらくお待ち下さい");
        }
        dto.setFileCheck(0);
        dto.setConfMsg("");
        deleteFile(dto);
        dto.setRegistFlg(2);

        fileCheck(dto);
    }
    
    

    private void fileCheck(UploadDto uploadDto) {
        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;
        String path = BirdContext.getProperty("fileProperty", "upLoadPathMss");
        String filePth =  path;

        File dir = new File(filePth);
        File pdfFiles[] = dir.listFiles();
        if(pdfFiles != null && pdfFiles.length > 0){
            for(int i =0;i<pdfFiles.length;i++){
                if(dto.getFileFlg() == 1 && pdfFiles[i].getName().equals(UPLOAD_FILE_TYPE_MSPM)){
                    dto.setFileCheck(1);
                    dto.setConfMsg("サーバーにファイルが存在します。上書きしますか？");
                    break;
                }else if(dto.getFileFlg() == 2 && pdfFiles[i].getName().equals(UPLOAD_FILE_TYPE_MSPD)){
                    dto.setFileCheck(1);
                    dto.setConfMsg("サーバーにファイルが存在します。上書きしますか？");
                    break;
                }else if(dto.getFileFlg() == 3 && pdfFiles[i].getName().equals(UPLOAD_FILE_TYPE_MSPH)){
                    dto.setFileCheck(1);
                    dto.setConfMsg("サーバーにファイルが存在します。上書きしますか？");
                    break;
                }
            }
        }
    }

    private void deleteFile(UploadDto uploadDto) {
        MssSuerveyUploadDto dto = (MssSuerveyUploadDto) uploadDto;
        String path = BirdContext.getProperty("fileProperty", "upLoadPathMss");
        String filePth =  path +  FILE_SEPARATOR;
        filePth += FILE_SEPARATOR;

        //1.項目マスタ2.評価データ3.調査データ
        if(dto.getFileFlg() == 1){
            filePth += UPLOAD_FILE_TYPE_MSPM;
        }else if(dto.getFileFlg() == 2){
            filePth += UPLOAD_FILE_TYPE_MSPD;
        }else if(dto.getFileFlg() == 3){
            filePth += UPLOAD_FILE_TYPE_MSPH;
        }
        File dir = new File(filePth);
        File pdfFiles[] = dir.listFiles();
        if(pdfFiles != null && pdfFiles.length != 0 ){
            for(int i=0; i<pdfFiles.length; i++){
                pdfFiles[i].delete();
            }
        }
    }
    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getPageKeyTemporary()
     */
    public String getPageKeyTemporary(final UploadDto uploadDto) {
        return null;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getPageKey()
     */
    public String getPageKey(final UploadDto uploadDto) {
        MssSuerveyUploadDto dto  = (MssSuerveyUploadDto)uploadDto;
        dto.setRegistFlg(1);
        return null;
    }
    public UIMssBatchDao getUIMssBatchDao() {
        return UIMssBatchDao;
    }

    public void setUIMssBatchDao(UIMssBatchDao UIMssBatchDao) {
        this.UIMssBatchDao = UIMssBatchDao;
    }

}