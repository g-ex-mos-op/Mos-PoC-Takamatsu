/*
 * 作成日: 2005/09/15
 *
 */
package jp.co.isid.mos.bird.framework.control;

import java.util.Map;
import java.util.Set;

/**
 * プロパティの値を取得する実装クラス
 * @author xytamura
 */
public class PropertyInjection implements Property {

    // プロパティを保持する
    private Map propertyMap;

    /**
     * コンストラクタ
     */
    public PropertyInjection(Map propertyMap) {

        this.propertyMap = propertyMap;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.control.Property#getProperty(java.lang.String)
     */
    public String getProperty(String keyName) {
        return (String) propertyMap.get(keyName);
    }

    public Set getPropertyKeySet() {
        return propertyMap.keySet();
    }
}