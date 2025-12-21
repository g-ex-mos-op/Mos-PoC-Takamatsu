/*
 * 作成日: 2006/01/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.portal.login.dto.MatrixDto;

/**
 * マトリクス認証入力情報チェックロジック インターフェイス
 * @author xnkusama
 */
public interface CheckInputMatrixInfoLogic {
    /* マトリクス認証入力情報チェック */
    public void check(final MatrixDto dto, final LoginDto loginDto) throws ApplicationException;
}
