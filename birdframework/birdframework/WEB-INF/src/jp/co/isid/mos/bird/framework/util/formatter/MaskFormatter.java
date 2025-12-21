/*
 * MaskFormatter.java
 * 
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * 文字列を指定のパターンに編集するフォーマッタ。
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
public class MaskFormatter extends DefaultFormatter implements MaskConstants {
    int fixCharNum = 0;

    /**
     * コードの桁数と、nullや空文字列の場合に返すデフォルトの文字列
     * を指定して、新しいフォーマッタを生成します。
     * @param  maskPattern       フォーマット後の全体の桁数
     */
    public MaskFormatter(String maskPattern) {
        formatPattern = maskPattern;
        init();
    }

    private void init() {
        int count = 0;
        for (int i = 0; i < formatPattern.length(); i++) {
            switch (formatPattern.charAt(i)) {
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
        fixCharNum = count;
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
     * @param  target     対象文字列
     * @param  isForView  true/falseに関わらず表示用にフォーマットします
     * @return フォーマット済文字列
     */
    public String format(final Object target, final boolean isForView) {
        if (target == null ) {
            return getDefaultText();
        }
        
        String sValue = (String) target;
        StringBuffer sForView = new StringBuffer("");
        StringBuffer sForValue = new StringBuffer("");

        if (sValue.length() != getMaxValueLength()
            && sValue.length() != formatPattern.length()) {
            return sValue;
        }

        int iValue = 0;

        for (int i = 0; i < formatPattern.length(); i++) {
            char formatChar = formatPattern.charAt(i);
            char valueChar = sValue.charAt(iValue);
            switch (formatChar) {
                case MC_NUMBER :
                case MC_NUMBER_OR_LETTER :
                case MC_LETTER :
                case MC_ALL :
                    sForView.append(valueChar);
                    sForValue.append(valueChar);
                    iValue++;
                    break;
                case MC_UPPER :
                    sForView.append(Character.toUpperCase(valueChar));
                    sForValue.append(Character.toUpperCase(valueChar));
                    iValue++;
                    break;
                case MC_LOWER :
                    sForView.append(Character.toLowerCase(valueChar));
                    sForValue.append(Character.toLowerCase(valueChar));
                    iValue++;
                    break;
                default :
                    sForView.append(formatChar);
                    if (sValue.length() == formatPattern.length()) {
                        iValue++;
                    }
                    break;
            }
        }

        if (isForView) {
            return sForView.toString();
        }
        else {
            return sForValue.toString();
        }
    }

    /**
     * get max possible characters of none formatted text
     * @return int
     */
    public int getMaxValueLength() {
        return formatPattern.length() - fixCharNum;
    }

    /**
     * フォーマットパターンを設定します。
     * @param  formatPattern   フォーマットパターン文字列
     */
    public void setFormatPattern(final String formatPattern) {
        super.setFormatPattern(formatPattern);
        init();
    }

}
