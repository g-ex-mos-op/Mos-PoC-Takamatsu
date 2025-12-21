/*
 * HankakuKatakanaVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 半角カナ文字用バリデータ。
 * 半角カナを許可します。
 * @version     2003/09/06
 * @author ISID MOS PROJECT
 */
public class HankakuKatakanaVerifier extends DefaultJapaneseVerifier {

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, HANKAKU_KANA);
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[半角カナ]";
    }

}