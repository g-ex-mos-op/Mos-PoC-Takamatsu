/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * CSV取込ロジック
 * 
 * @author xyuchida
 */
public interface TakeCsvLogic {

    /**
     * CSV取込
     * 
     * @param uploadedFile アップロードファイル
     * @return CSVレコードList
     */
    public List execute(UploadedFile uploadedFile);
}
