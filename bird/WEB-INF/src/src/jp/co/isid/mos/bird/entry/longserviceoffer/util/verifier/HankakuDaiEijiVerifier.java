/*
 * HankakuVerifier.java
 * 
 * Created by narita
 * Copy from 2ndGenesis on 2007/01/17
 *
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.util.verifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;

/**
 * 半角文字用バリデータ。
 * 半角英字を許可します。
 * @version 2007/01/12
 * @author ISID MOS PROJECT
 */
public class HankakuDaiEijiVerifier extends DefaultJapaneseVerifier {

    /**
     * 半角大文字アルファベットを示す定数です。
     */
    public static final String HANKAKU_DAI_LATIN =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    
    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, HANKAKU_DAI_LATIN);
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[半角大文字英字]";
    }

}