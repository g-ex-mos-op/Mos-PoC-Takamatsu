/*
 * çÏê¨ì˙: 2006/02/02
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;

/**
 * @author xyuchida
 *
 */
public class ContactRegistUploadLogicImpl implements UploadLogic {

    public static final String LOGIC_ID = "BIF001L04";

    public String getFileName(UploadDto uploadDto) {
        return uploadDto.getUploadFileName();
    }

    public String getFileFullPath(UploadDto uploadDto) {
        return BirdContext.getProperty("fileProperty", "filePathRenraku")
                + File.separator + getFileName(uploadDto);
    }

    public String getTempFileName(UploadDto uploadDto) {
        return uploadDto.getTempFileName();
    }

    public String getTempFileFullPath(UploadDto uploadDto) {
        return BirdContext.getProperty("fileProperty", "filePathRenraku")
                + File.separator + getTempFileName(uploadDto);
    }

    public void validate(UploadDto uploadDto) {
    }

    public String getPageKeyTemporary(UploadDto uploadDto) {
        return null;
    }

    public String getPageKey(UploadDto uploadDto) {
        return null;
    }
}
