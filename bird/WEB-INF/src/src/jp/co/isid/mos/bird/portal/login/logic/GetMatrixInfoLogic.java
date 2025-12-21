/*
 * 作成日: 2006/01/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;

/**
 * マトリクス認証情報取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetMatrixInfoLogic {
    /* マトリックス情報取得処理 */
    public UIUserMatrixInfo getMatrixInfoLogic(final LoginDto dto);
}
