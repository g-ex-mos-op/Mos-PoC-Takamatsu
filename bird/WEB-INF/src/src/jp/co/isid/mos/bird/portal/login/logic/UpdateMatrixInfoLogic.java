/*
 * 作成日: 2006/01/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import jp.co.isid.mos.bird.portal.login.dto.MatrixRegistDto;

/**
 * マトリクス認証情報取得ロジック インターフェイス
 * @author xnkusama
 */
public interface UpdateMatrixInfoLogic {
    /* マトリックス情報登録処理 */
    public void updateMatrixInfo(final MatrixRegistDto dto);
}
