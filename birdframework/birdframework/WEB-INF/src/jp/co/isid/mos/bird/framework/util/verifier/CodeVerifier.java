/*
 * CodeVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * コードのみ許可するバリデータ。
 * コンストラクタによって数字のみ・英数字の切替えができます。
 * 2004/04/01 getValidDescription()メソッド追加
 * @author ISID MOS PROJECT
 * @since  2003.03.26
 */
public class CodeVerifier extends DefaultVerifier {

    // 英字許可フラグ
    private boolean _isAlphabet;
    // 入力可能桁数
    protected int _digitNum = 0;

    /**
     * 新しいバリデータを生成します。
     * ・nullを許可
     * ・英字不可
     * ・桁数制限なし
     */
    public CodeVerifier() {
        _isAlphabet = false;
    }

    /**
     * 桁数を指定して新しいバリデータを生成します。
     * ・nullを許可
     * ・英字不可
     * @param digit 入力可能桁数
     */
    public CodeVerifier(final int digit) {
        this();
        _digitNum = digit;
    }

    /**
     * 英字の可/不可を指定して新しいバリデータを生成します。
     * ・nullを許可
     * ・桁数制限なし
     * @param isAlphabet 英字許可フラグ true:英字可
     */
    public CodeVerifier(final boolean isAlphabet) {
        this();
        _isAlphabet = isAlphabet;
    }

    /**
     * 桁数、英字の可/不可を指定して新しいバリデータを生成します。
     * ・nullを許可
     * @param digit 入力可能桁数
     * @param isAlphabet 英字許可フラグ true:英字可
     */
    public CodeVerifier(final int digit, final boolean isAlphabet) {
        this();
        _digitNum = digit;
        _isAlphabet = isAlphabet;
    }

    /**
     *@param name string
     *@param value string
     *@return msgf 
     */
    public String[] getMsg(final String name, final String value) {
        String[] msg = new String[2];
        msg[0] = name;
        msg[1] = "(" + name + "=" + value + ")";
        return msg;
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



        /*if (_digitNum != 0 && text.trim().length() != _digitNum) {
            return false;
        }*/

        // 文字チェック
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= '\u0030' && c <= '\u0039') {
                // 数字
            }
            else if (_isAlphabet && (c >= '\u0041' && c <= '\u005A')) {
                // 英字（大文字）
            }
            else if (_isAlphabet && (c >= '\u0061' && c <= '\u007A')) {
                // 英字（小文字）
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
        
        if (_isAlphabet) {
            retDescription = "[半角英数字]";
        }
        else {
            retDescription = "[半角数字]";
        }
        
        return retDescription;
    }
}
