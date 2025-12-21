package jp.co.isid.mos.bird.framework.logic;

import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * アップロードロジック Interface
 * @author xnkusama
 */
public interface UploadLogic {

    /* ファイル名取得 */
    public String getFileName(final UploadDto uploadDto);
    
    /* ファイルフルパス取得 */
    public String getFileFullPath(final UploadDto uploadDto);
    
    /* ファイル名取得 */
    public String getTempFileName(final UploadDto uploadDto);
    
    /* ファイルフルパス取得 */
    public String getTempFileFullPath(final UploadDto uploadDto);

    /* 事前条件チェック処理 */
    public void validate(final UploadDto uploadDto) throws ApplicationException;
    
    /* アクション戻り値 テンポラリ作成 */
    public String getPageKeyTemporary(final UploadDto uploadDto);

    /* アクション戻り値 メイン処理 */
    public String getPageKey(final UploadDto uploadDto);
}
