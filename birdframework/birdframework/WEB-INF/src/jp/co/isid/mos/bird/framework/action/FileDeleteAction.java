/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;

import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DeleteUploadLogic;

/**
 * ファイルアップロード アクション用Interface
 * @author xnkusama
 */
public interface FileDeleteAction {
    public void setDeleteUploadLogic(final DeleteUploadLogic deleteUploadLogic);

    public void setUploadDto(final UploadDto uploadDto);
    
    /**
     * ファイル削除処理
     * @return String
     * @throws IOException
     */
    public void delete(final int seq) throws ApplicationException;
}