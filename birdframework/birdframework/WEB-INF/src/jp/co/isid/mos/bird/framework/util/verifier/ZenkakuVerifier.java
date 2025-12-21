/*
 * ZenkakuVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 日本語全角文字のみ許可するバリデータ。
 * 2004/04/01 getValidDescription()メソッド追加
 * @version   2002/02/06
 * @author ISID MOS PROJECT
 */
public class ZenkakuVerifier extends DefaultJapaneseVerifier {

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, ZENKAKU_KANA)
            || isValidChar(source, ZENKAKU_HIRA)
            || isValidChar(source, ZENKAKU_LATIN)
            || isValidChar(source, ZENKAKU_NUMBER)
            || isValidChar(source, ZENKAKU_SYMBOL)
            || isValidChar(source, ZENKAKU_KANJI);

    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[全角]";
    }

}