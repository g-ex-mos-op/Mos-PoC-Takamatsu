/*
 * 作成日: 2006/01/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * マトリクス認証情報取得ロジック インターフェイス
 * @author xnkusama
 */
public interface MakeMatrixLogic {
    /* セッションキー作成処理 */
    public List makeMatrixKey() throws ApplicationException;
}
