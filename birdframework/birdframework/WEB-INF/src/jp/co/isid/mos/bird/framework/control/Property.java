/*
 * 作成日: 2005/09/15
 *
 */
package jp.co.isid.mos.bird.framework.control;

import java.util.Set;

/**
 * プロパティの値を取得するためのインターフェイス
 * @author xytamura
 */
public interface Property {

    public String getProperty(String keyName);
    public Set getPropertyKeySet();
}
