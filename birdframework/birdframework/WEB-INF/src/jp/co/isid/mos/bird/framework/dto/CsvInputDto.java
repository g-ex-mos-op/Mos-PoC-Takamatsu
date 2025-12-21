package jp.co.isid.mos.bird.framework.dto;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * CSVì«Ç›çûÇ›ópÇÃDTO
 * @author xnkusama
 */
public interface CsvInputDto {

    public UploadedFile getUploadedFile();
    public void setUploadedFile(UploadedFile uploadFile);
    
}
