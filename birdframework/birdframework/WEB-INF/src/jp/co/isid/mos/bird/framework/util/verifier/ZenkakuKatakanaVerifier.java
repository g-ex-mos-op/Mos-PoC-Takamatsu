/*
 * ZenkakuKatakanaVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 全角カナ文字用バリデータ。
 * 全角カナ、全角英数及び全角記号を許可します。
 * @version     2003/09/06
 * @author ISID MOS PROJECT
 */
public class ZenkakuKatakanaVerifier extends DefaultJapaneseVerifier {

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, ZENKAKU_KANA)
            || isValidChar(source, ZENKAKU_LATIN)
            || isValidChar(source, ZENKAKU_NUMBER)
            || isValidChar(source, ZENKAKU_SYMBOL);
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[全角カナ]";
    }

}