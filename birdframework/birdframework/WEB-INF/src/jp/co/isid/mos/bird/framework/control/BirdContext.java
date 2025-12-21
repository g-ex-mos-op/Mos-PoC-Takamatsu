/*
 * 作成日: 2005/09/15
 *
 */
package jp.co.isid.mos.bird.framework.control;

import java.util.Set;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.SRuntimeException;

/**
 * コンテキスト（プロパティ）を取得するためのクラス
 * 
 * @author xytamura
 */
public class BirdContext {

    /**
     * コンストラクタ
     */
    private BirdContext() {
        super();
    }

    /**
     * コンテキスト（プロパティ）を取得します。
     * @param componentName プロパティを保持するコンポーネント名
     * @param keyName プロパティのキー名
     * @return プロパティ
     * @throws SRuntimeException 例外発生
     */
    public static String getProperty(final String componentName,
            final String keyName) throws SRuntimeException {
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        Property prop = (Property) s2Con.getComponent(componentName);
        String name = prop.getProperty(keyName);
        return name;
    }

    public static Set getPropertyKeySet(final String componentName) throws SRuntimeException {
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        Property prop = (Property) s2Con.getComponent(componentName);
        return prop.getPropertyKeySet();
    }
}