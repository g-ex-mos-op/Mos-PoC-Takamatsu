/*
 * HankakuEisujiPWVerifier.java
 * 
 * Created by Tamura Yuichi
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 半角文字用バリデータ。
 * 半角英数及び半角記号を許可します。
 * @version 2025/06/03
 * @author ISID MOS PROJECT
 */
public class HankakuEisujiPWVerifier extends DefaultJapaneseVerifier {

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, HANKAKU_LATIN)
            || isValidChar(source, HANKAKU_NUMBER)
            || isValidChar(source, HANKAKU_SYMBOL2);
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[半角英数]";
    }

}