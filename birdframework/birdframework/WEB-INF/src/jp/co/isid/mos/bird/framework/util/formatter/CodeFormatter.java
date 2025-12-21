/*
 * CodeFormatter.java
 * 
 * Created by xytamura on 2005/10/31
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * 前ゼロを付加し桁数を揃えるフォーマッタ。
 * @author ISID MOS PROJECT
 * @since  2002.12.25
 */
public class CodeFormatter extends DefaultFormatter {

    private int digit;

    /**
     * コードの桁数と、nullや空文字列の場合に返すデフォルトの文字列
     * を指定して、新しいフォーマッタを生成します。
     * @param  digit       フォーマット後の全体の桁数
     * @param  defaultText デフォルトの文字列
     */
    public CodeFormatter(final int digit, final String defaultText) {
        this(digit);
        setDefaultText(defaultText);
    }

    /**
     * コードの桁数を指定して、新しいフォーマッタを生成します。
     * @param  digit    フォーマット後の全体の桁数
     */
    public CodeFormatter(final int digit) {
        /*StringBuffer prefix = new StringBuffer(digit);
        for (int i = 0; i < digit; i++) {
            prefix.append(0);
        }
        setFormatPattern(prefix.toString());*/
        setFormatPattern("");
        setDefaultText("");
        this.digit = digit;
    }

    /**
     * ターゲット文字列に前ゼロを付加します。
     * setFormatPatternメソッドでフォーマットパターンが設定されている場合は、
     * ターゲット文字列を右に寄せた上で足りない文字を補います。<BR>
     * <BR>
     * 例）<BR>
     * 元の文字列="123"<BR>
     * フォーマットパターン="ABCDE" であった場合、<BR>
     * フォーマット後文字列="AB123" となります。
     * @param  target     対象文字列
     * @param  isForView  true/falseに関わらず表示用にフォーマットします
     * @return フォーマット済文字列
     */
    public String format(final Object target, final boolean isForView) {
        String sCode = (String) target;
        if (sCode == null || sCode.trim().equals("")) {
            return defaultText;
        }

        if (getFormatPattern().equals("")) {
            return sCode;
        }

        StringBuffer formattedText = new StringBuffer(sCode.trim());
        for (int i = formatPattern.length() - formattedText.length() - 1;
            i >= 0;
            i--) {
            formattedText.insert(0, formatPattern.charAt(i));
        }
        return formattedText.substring(0, digit);
    }
}
