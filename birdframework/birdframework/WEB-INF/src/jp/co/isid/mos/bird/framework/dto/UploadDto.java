package jp.co.isid.mos.bird.framework.dto;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * アップロード処理用DTO
 * @author xnkusama
 */
public interface UploadDto {

    public UploadedFile getUploadedFile();
    public void setUploadedFile(UploadedFile uploadFile);
    
    public String getTempFileName();
    public void setTempFileName(final String tempFileName);
    
    public String getUploadFileName();
    public void setUploadFileName(final String uploadFileName);
}
