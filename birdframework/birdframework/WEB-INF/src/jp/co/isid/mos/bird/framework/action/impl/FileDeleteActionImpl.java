package jp.co.isid.mos.bird.framework.action.impl;

import java.io.File;

import jp.co.isid.mos.bird.framework.action.FileDeleteAction;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.DeleteUploadLogic;

/**
 * アップロード アクションクラス
 * @author xnkusama
 */
public class FileDeleteActionImpl implements FileDeleteAction {
    public static final String delete_ACTION_ID = "BFW008A01";

    // エラーメッセージ2
    private static final String ERR_MSG_DELETE_ERR = "デリート";
    
    private UploadDto _uploadDto;
    private DeleteUploadLogic _deleteUploadLogic;
    

    
    public void setDeleteUploadLogic(final  DeleteUploadLogic deleteUploadLogic) {
        this._deleteUploadLogic = deleteUploadLogic;
    }
    private  DeleteUploadLogic getDeleteUploadLogic()  {
        return _deleteUploadLogic;
    }
    
    public void setUploadDto(final UploadDto uploadDto) {
        this._uploadDto = uploadDto;
    }
    private UploadDto getUploadDto() {
        return _uploadDto;
    }
    
    
    public void delete(final int seq)  throws ApplicationException {
        try {
            File[] oldFile = getDeleteUploadLogic().getDeleteFileFullPath(getUploadDto(),seq);
            
            if (oldFile.length > 0) {
                for (int i = 0 ; i < oldFile.length ; i ++) {
                    File deleteFile = oldFile[i]; 
                    if (deleteFile.exists()) {
                        deleteFile.delete();    
                    }
                }
            }
        }
        catch (FileNotFoundException ioe) {
            ioe.printStackTrace();
            throw new FtlSystemException(ERR_MSG_DELETE_ERR);
        }
    
    }
    
}