/*
 * NormalValidator.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 常にtrueを返すデフォルトのValidator
 */
public class DefaultVerifier implements Verifier {

    // null許可フラグ
    protected boolean _isNullable = true;

    /**
     * @see Verifier#validate(String)
     */
    public boolean validate(final String text) {
        return true;
    }

    /**
     * nullの許可/不可を設定します。
     * @param  isNullable  true：許可する / false：許可しない デフォルト：true
     */
    public void setNullable(final boolean isNullable) {
        _isNullable = isNullable;
    }
    
    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "";
    }
}
