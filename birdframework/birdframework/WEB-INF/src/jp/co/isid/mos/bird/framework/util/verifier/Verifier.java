/*
 * Verifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 入力内容の妥当性チェックを行うインタフェース。
 * @author ISID MOS PROJECT
 * @since  2002.12.25
 */
public interface Verifier {

    /**
     * 妥当性チェックを行います。 
     * @param  target       対象となる文字列 
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String target);

    /**
     * nullの許可/不可を設定します。
     * @param  isNullable  true：nullを許可する / false：nullを許可しない
     */
    public void setNullable(final boolean isNullable);
    
    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription();

}
