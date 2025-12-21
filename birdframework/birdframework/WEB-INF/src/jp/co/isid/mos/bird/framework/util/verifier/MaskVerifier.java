/*
 * MaskVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

import jp.co.isid.mos.bird.framework.util.formatter.MaskConstants;

/**
 * 前ゼロを付加し桁数を揃えるフォーマッタ。
 * The following characters can be specified: 
 * # Any valid number, uses Character.isDigit.  
 * ' Escape character, used to escape any of the special formatting characters. 
 * U Any character (Character.isLetter). All lowercase letters are mapped to upper case. 
 * L Any character (Character.isLetter). All upper case letters are mapped to lower case. 
 * A Any character or number (Character.isLetter or Character.isDigit) 
 * ? Any character (Character.isLetter). 
 * * Anything. 
 * H Any hex character (0-9, a-f or A-F).
 *  
 * @author ISID MOS PROJECT
 * @since  2002.12.25
 */
public class MaskVerifier extends DefaultVerifier implements MaskConstants {

    String _formatPattern = "";
    int _fixCharNum = 0;

    /**
     * コードの桁数と、nullや空文字列の場合に返すデフォルトの文字列
     * を指定して、新しいフォーマッタを生成します。
     * @param  maskPattern       フォーマット後の全体の桁数
     */
    public MaskVerifier(String maskPattern) {
        _formatPattern = maskPattern;
        init();
    }

    private void init() {
        int count = 0;
        for (int i = 0; i < _formatPattern.length(); i++) {
            switch (_formatPattern.charAt(i)) {
                case MC_NUMBER :
                case MC_UPPER :
                case MC_LOWER :
                case MC_NUMBER_OR_LETTER :
                case MC_LETTER :
                case MC_ALL :
                    break;
                default :
                    count++;
                    break;
            }
        }
        _fixCharNum = count;
    }

    /**
     * ターゲット文字列にmaskPatternによってフォーマットします。
     * setFormatPatternメソッドでフォーマットパターンが設定されている場合は、
     * ターゲット文字列を右に寄せた上で足りない文字を補います。<BR>
     * <BR>
     * 例）<BR>
     * 元の文字列="1234567"<BR>
     * フォーマットパターン="##-###-##" であった場合、<BR>
     * フォーマット後文字列="12-345-67" となります。
     * @param  sValue 対象文字列
     * @return boolean 
     */
    public boolean validate(final String sValue) {
        boolean bSwitchError = false;
        int iValue = 0;

        if (sValue == null || sValue.trim().equals("")) {
            return _isNullable;
        }

        if (sValue.length() != getMaxValueLength()) {
            return false;
        }

        for (int i = 0; i < _formatPattern.length(); i++) {
            char formatChar = _formatPattern.charAt(i);
            char valueChar = sValue.charAt(iValue);
            switch (formatChar) {
                case MC_NUMBER :
                    if (Character.isDigit(valueChar)) {
                        iValue++;
                    }
                    else {
                        bSwitchError = true;
                    }
                    break;
                case MC_UPPER :
                    if (Character.isLetter(valueChar)) {
                        iValue++;
                    }
                    else {
                        bSwitchError = true;
                    }
                    break;
                case MC_LOWER :
                    if (Character.isLetter(valueChar)) {
                        iValue++;
                    }
                    else {
                        bSwitchError = true;
                    }
                    break;
                case MC_NUMBER_OR_LETTER :
                    if (Character.isLetterOrDigit(valueChar)) {
                        iValue++;
                    }
                    else {
                        bSwitchError = true;
                    }
                    break;
                case MC_LETTER :
                    if (Character.isLetter(valueChar)) {
                        iValue++;
                    }
                    else {
                        bSwitchError = true;
                    }
                    break;
                case MC_ALL :
                    iValue++;
                    break;
                default :
                    if (sValue.length() < _formatPattern.length()) {
                    }
                    else {
                        if (valueChar == formatChar) {
                            iValue++;
                        }
                        else {
                            bSwitchError = true;
                        }
                    }
                    break;
            }
            if (bSwitchError) {
                break;
            }
        }

        return !bSwitchError;
    }

    /**
     * get max possible characters of none formatted text
     * @return int
     */
    public int getMaxValueLength() {
        return _formatPattern.length() - _fixCharNum;
    }

}
