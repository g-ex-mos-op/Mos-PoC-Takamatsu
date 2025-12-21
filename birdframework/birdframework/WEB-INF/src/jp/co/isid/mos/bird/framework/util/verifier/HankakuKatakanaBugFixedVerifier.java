/* Created by K.Nihonyanagi  2008/12/19 
 * HankakuKatakanaBugFixedVerifier.java
 * 2008/12/19時点で使用していたHankakuKatakanaVerifier.javaは、
 * doValidateメソッドに問題があった。
 * 問題点⇒HankakuKatakanaVerifier.doValidate()をすると、
 * 　　　　サブクラスのdoValidateを呼びたい意図で設計、実装していたが、
 * 　　　　スーパークラス(DefaultJapaneseVerifier)のdoValidateがabstractでないため、
 * 　　　　スーパークラスのdoValidateが呼び出されてしまう。
 * 　　　　本クラス(HankakuKatakanaBugFixedVerifier)では、doValidateを直接呼び出す
 * 　　　　仕様となっている。
 * 
 * ※影響範囲がわからないため、新規クラス作成で対応することとする。
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 半角カナ文字用バリデータ。
 * 半角カナを許可します。
 * @version     2008/12/19
 * @author ISID MOS PROJECT
 */
public class HankakuKatakanaBugFixedVerifier extends DefaultJapaneseVerifier {

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    public boolean doValidate(final String source) {
        boolean resultFlg = true;
        
        for (int i = 0; i < source.length(); i++) {
            if (!isValidChar(source.substring(i, i + 1), super.HANKAKU_KANA)) {
                resultFlg = false;
            }
        }
        
        return resultFlg;
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "[半角カナ]";
    }

}