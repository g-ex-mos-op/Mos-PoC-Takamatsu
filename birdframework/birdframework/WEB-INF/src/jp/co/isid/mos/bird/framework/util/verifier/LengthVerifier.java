/*
 * LengthVerifier.java
 *
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

import java.io.UnsupportedEncodingException;

/**
 * 文字数(バイト数で)チェックを行う
 */
public class LengthVerifier extends DefaultVerifier {


	private int _maxLength;

    /**
     * 新しいバリデータを生成します。
     */
    public LengthVerifier() {
    }

    /**
     * 新しいバリデータを生成します。
     * @param maxLength 最大文字数(バイト数)
     */
    public LengthVerifier(int maxLength) {
    	this();
    	_maxLength = maxLength;
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

    	if ( text == null ) {
    		return true;
    	}

    	byte[] textByte = null;
    	try {
    		textByte = text.getBytes("Windows-31j");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	int textLength = textByte.length;

    	if ( this._maxLength < textLength ) {
    		return false;
    	}

        return true;
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        return "";
    }
}
