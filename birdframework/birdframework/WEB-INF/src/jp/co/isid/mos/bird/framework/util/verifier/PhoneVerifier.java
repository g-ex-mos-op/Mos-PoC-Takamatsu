/*
 * PhoneVerifier.java
 * 
 * Created by xytamura
 * Created by yhli on 2003/11/03
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/** 
 * @author ISID MOS PROJECT
 * @version 2003/11/03
 */
public class PhoneVerifier extends DefaultVerifier {

    /** hyphen 許可フラグ*/
    private boolean _isHyphen;

    /** 入力可能桁数*/
    private int _digitNum = 0;

    /**
     * 新しいバリデータを生成します。
     * ・Hyphen不可
     * ・桁数制限なし
     */
    public PhoneVerifier() {
        _isHyphen = false;
    }

    /**
     * 新しいバリデータを生成します。
     * ・Hyphen不可
     * @param digit 入力可能桁数
     */
    public PhoneVerifier(final int digit) {
        this();
        _digitNum = digit;
    }

    /**
     * 新しいバリデータを生成します。
     * ・桁数制限なし
     * @param isHyphen Hyphen許可フラグ true:Hyphen可
     */
    public PhoneVerifier(final boolean isHyphen) {
        this();
        _isHyphen = isHyphen;
    }

    /**
     * 新しいバリデータを生成します。
     * @param digit 入力可能桁数
     * @param isHyphen Hyphen許可フラグ true:Hyphen可
     */
    public PhoneVerifier(final int digit, final boolean isHyphen) {
        this();
        _digitNum = digit;
        _isHyphen = isHyphen;
    }

    /**
     * 整数値としての妥当性チェックを行います。 
     * @param  text       対象となる文字列 
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String text) {
        if (text == null || text.trim().equals("")) {
            return _isNullable;
        }

        if (text.charAt(0) == '-') {
            return false;
        }
        if (text.charAt(text.length() - 1) == '-') {
            return false;
        }
        // 文字チェック
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= '\u0030' && c <= '\u0039') {
                // 数字
            }
            //else if ((c == '(') || (c == ')')) {
                //"( )"
            //}
            else if (_isHyphen && (c == '-')) {
                // Hyphen
            }
            else {
                return false;
            }
        }

        // レングスチェック
        if (_digitNum != 0 && text.getBytes().length > _digitNum) {
            return false;
        }
        return true;
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        String retDescription = "";
        
        retDescription = "[電話番号";
        
        if (!_isHyphen) {
            retDescription += "（ハイフン不可）";
        }
        
        return retDescription + "]";
    }

}
