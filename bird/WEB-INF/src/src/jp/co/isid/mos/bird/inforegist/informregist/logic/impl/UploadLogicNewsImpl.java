/*
 * 作成日: 2006/01/31
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic.impl;

import java.io.File;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.inforegist.informregist.dto.InformRegistDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

/**
 * ファイル登録ロジック
 * @author xytamura
 */
public class UploadLogicNewsImpl implements UploadLogic {

    

    /** 
     * ファイル名取得処理
     */
    public String getFileName(UploadDto uploadDto) {
        
        InformRegistDto dto = (InformRegistDto) uploadDto;
        UINews entity = dto.getUploadEntity();
        String fileName = "";
        if (dto.getUploadedFile() != null) {
            String file = dto.getUploadedFile().getName();
            fileName = file.substring(file.lastIndexOf(File.separator) + 1);
        }
        else {
            fileName = dto.getUploadFileName();
        }
        fileName = entity.getRegDate() + "_" + entity.getSeq() + "_"
                 + dto.getUploadIndex() + "_"
                 + dto.getUploadFileName();

        return fileName;
    }

    /**
     * ファイル フルパス取得処理
     * @param UploadDto
     * @return String
     */
    public String getFileFullPath(UploadDto uploadDto) {
        
        String file = BirdContext.getProperty("fileProperty", "filePathOshirase");
        file += File.separator;
        file += getFileName(uploadDto);
        
        return file;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileName(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileName(UploadDto uploadDto) {
        InformRegistDto dto = (InformRegistDto) uploadDto;
        
        String tempFileName = "";
        if (dto.getTempFileName() == null) {
            switch (dto.getUploadIndex()) {
                case 1:
//                    tempFileName = dto.getUploadTempFileNameMain();
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
        }
        else {
            tempFileName = dto.getTempFileName();
        }
        
        return tempFileName;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.logic.UploadLogic#getTempFileFullPath(jp.co.isid.mos.bird.framework.dto.UploadDto)
     */
    public String getTempFileFullPath(UploadDto uploadDto) {
        
        String file = BirdContext.getProperty("fileProperty", "filePathOshirase");
        file += File.separator;
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

}
