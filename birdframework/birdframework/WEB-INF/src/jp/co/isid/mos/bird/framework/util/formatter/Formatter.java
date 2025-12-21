/*
 * Formatter.java
 * 
 * Created by xytamura on 2005/10/26
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * フォーマット処理を行うインタフェース。
 * @author ISID MOS PROJECT
 * @since  2002.12.25
 */
public interface Formatter {

    /**
     * フォーマット処理を行います。
     * @param  target     対象文字列
     * @param  isForView  true：表示用 / false：値処理用
     * @return フォーマット済文字列DateFormatter.java
     */
    public String format(final Object target, final boolean isForView);

    /**
     * @param formattedText 対象文字列
     * @return Object
     */
    public Object parseValue(final String formattedText);

    /**
     * フォーマットパターンを設定します。
     * @param  formatPattern   フォーマットパターン文字列
     */
    public void setFormatPattern(final String formatPattern);

    /**
     * フォーマットパターンを取得します。
     * @return フォーマットパターン文字列
     */
    public String getFormatPattern();

    /**
     * nullが渡された場合のデフォルトのテキストを設定します。
     * @param defaultText デフォルト文字列
     */
    public void setDefaultText(final String defaultText);

    /**
     * nullが渡された場合のデフォルトのテキストを取得します。
     * @return デフォルト文字列
     */
    public String getDefaultText();

}
