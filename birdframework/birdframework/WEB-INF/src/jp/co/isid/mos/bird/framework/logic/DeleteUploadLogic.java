package jp.co.isid.mos.bird.framework.logic;

import java.io.File;

import jp.co.isid.mos.bird.framework.dto.UploadDto;

/**
 * デリートロジック Interface
 * @author xnkusama
 */
public interface DeleteUploadLogic extends UploadLogic {

    /* ファイル名取得 */
    public File[] getDeleteFileFullPath(UploadDto uploadDto , int Seq);

}
