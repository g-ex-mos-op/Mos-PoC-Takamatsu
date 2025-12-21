/*
 * 作成日: 2006/01/31
 */
package jp.co.isid.mos.bird.office.formregist.logic.impl;

import java.io.File;
import java.io.FilenameFilter;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.logic.DeleteUploadLogic;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * ファイル登録ロジック
 * @author xytamura
 */
public class UploadFormLogicImpl implements DeleteUploadLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L13";
    
    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties()
            .getProperty("file.separator");

    private static final String KEY_FILE = "filePathForm";

    /**
     * ファイル名取得処理
     */
    public String getFileName(UploadDto uploadDto) {

        FormRegistFormDto dto = (FormRegistFormDto) uploadDto;
        UIFormInfo entity = dto.getEditEntity();
        String fileName = "";
        if (dto.getUploadedFile() != null) {
            String file = dto.getUploadedFile().getName();
            fileName = file.substring(file.lastIndexOf(FILE_SEPARATOR) + 1);
        } else {
            fileName = dto.getUploadFileName();
        }
        fileName = entity.getRegDate() + "_" + entity.getSeq() + "_"
                + dto.getUploadIndex() + "_" + dto.getUploadFileName();

        return fileName;
    }

    /**
     * ファイル フルパス取得処理
     * @param UploadDto
     * @return String
     */
    public String getFileFullPath(UploadDto uploadDto) {

        String file = BirdContext.getProperty("fileProperty", KEY_FILE);
        file += FILE_SEPARATOR;
        file += getFileName(uploadDto);

        return file;
    }

    /*
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileName(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileName(UploadDto uploadDto) {
        FormRegistFormDto dto = (FormRegistFormDto) uploadDto;

        String tempFileName = "";
        if (dto.getTempFileName() == null) {
            switch (dto.getUploadIndex()) {
            case 1:
                tempFileName = dto.getUploadTempFileNameMain();
                break;
            case 2:
                tempFileName = dto.getUploadTempFileNameTenpu1();
                break;
            case 3:
                tempFileName = dto.getUploadTempFileNameTenpu2();
                break;
            case 4:
                tempFileName = dto.getUploadTempFileNameTenpu3();
                break;
            }
        } else {
            tempFileName = dto.getTempFileName();
        }

        return tempFileName;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileFullPath(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileFullPath(UploadDto uploadDto) {

        String file = BirdContext.getProperty("fileProperty", KEY_FILE);
        file += FILE_SEPARATOR;
        file += getTempFileName(uploadDto);
        return file;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#validate()
     */
    public void validate(final UploadDto uploadDto) throws ApplicationException {
        UploadedFile upFile = uploadDto.getUploadedFile();

        if (upFile == null) {
            throw new NotSelectedException("ファイル");
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
        return null;
    }
    
    /** 
     * ファイル名取得処理
     */
    public File[] getDeleteFileFullPath(UploadDto uploadDto ,int Seq) {
        
        FormRegistFormDto dto = (FormRegistFormDto) uploadDto;
        UIFormInfo entity = dto.getEditEntity();
        String fileName = "";
        
        //削除対象のファイル名称
        fileName = entity.getRegDate() + "_" + entity.getSeq() + "_" + Seq ;
        String sherchName = fileName + ".*$";

        String file = BirdContext.getProperty("fileProperty", KEY_FILE);
        file += FILE_SEPARATOR;

        File oldFile = new File(file);
        File[] listFiles = oldFile.listFiles(getFileRegexFilter(sherchName));  
        
        return listFiles;
    }
    
    public static FilenameFilter getFileRegexFilter(String regex) {   
        final String regex_ = regex;   
        return new FilenameFilter() {   
            public boolean accept(File file, String name) {   
                boolean ret = name.matches(regex_);    
                return ret;   
            }   
        } ;  
    }   

}