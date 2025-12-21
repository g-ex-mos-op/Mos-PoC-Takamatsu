/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;

import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;

/**
 * ファイルアップロード アクション用Interface
 * @author xnkusama
 */
public interface FileUploadAction {
    public void setUploadLogic(final UploadLogic uploadLogic);

    public void setUploadDto(final UploadDto uploadDto);

    /**
     * ファイル存在確認メソッド
     * @return
     */
    public boolean existsFileOnServer();

    /**
     * テンポラリファイル作成
     * @return String
     * @throws IOException
     */
    public String uploadTemporary() throws ApplicationException;

    /**
     * ファイル書込み処理
     * @return String
     * @throws IOException
     */
    public String upload() throws ApplicationException;
}