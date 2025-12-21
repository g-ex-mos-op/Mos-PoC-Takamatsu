package jp.co.isid.mos.bird.portal.login.action;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * マトリクス認証画面 アクションインターフェイス
 * @author xnkusama
 */
public interface MatrixAction {
    public String init();
    public String matrix() throws ApplicationException;
    public String back();
}
