/*
 * DefaultFormatter.java
 *
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/**
 * 引数の文字列をそのまま返すデフォルトのFormatter。
 */
public class DefaultFormatter implements Formatter {

    protected String formatPattern = "";

    protected String defaultText = "";

    /**
     * @see Formatter#format(String, boolean)
     */
    public String format(final Object target, final boolean isForView) {
        if (target == null) {
            return "";
        }
        return target.toString();
    }

    /**
     * @param target 対象文字列
     * @return Object
     */
    public Object parseValue(final String target) {
        return (Object) target;
    }

    /**
     * nullが渡された場合のデフォルトのテキストを取得します。
     * @return デフォルト文字列
     */
    public String getDefaultText() {
        return defaultText;
    }

    /**
     * フォーマットパターンを取得します。
     * @return フォーマットパターン文字列
     */
    public String getFormatPattern() {
        return formatPattern;
    }

    /**
     * nullが渡された場合のデフォルトのテキストを設定します。
     * @param defaultText デフォルト文字列
     */
    public void setDefaultText(final String defaultText) {
        this.defaultText = defaultText;
    }

    /**
     * フォーマットパターンを設定します。
     * @param  formatPattern   フォーマットパターン文字列
     */
    public void setFormatPattern(final String formatPattern) {
        this.formatPattern = formatPattern;
    }

    /**
     * 文字列(str)内に指定文字列(appointChar)が何個あるかを取得します
     * @since 2003.01.10
     * @return int
     * @param str java.lang.String
     * @param appointChar java.lang.String
     */
    protected int countAppointChar(
        final String str,
        final String appointChar) {
        if (str == null) {
            return 0;
        }
        int count = 0;
        int startIndex = 0;

        while (str.indexOf(appointChar, startIndex) != -1) {
            startIndex =
                str.indexOf(appointChar, startIndex)
                    + appointChar.getBytes().length;
            count++;
        }

        return count;
    }

	/**
	 * 文字列前後のスペース[全角／半角]を削除します。
	 *
	 * @param inTarget
	 *            処理対象文字列
	 * @return 処理された文字列
	 */
	public String trimWideHalfSpace(String inTarget) {
		// nullの場合は何もしない
		if (inTarget == null) {
			return "";
		}
		inTarget = inTarget.trim();
		// 文字列の前のスペース[全角／半角]を削除します
		while (inTarget.startsWith("　")) {
			inTarget = inTarget.substring(1, inTarget.length()).trim();
		}
		// 文字列の後ろのスペース[全角／半角]を削除します
		while (inTarget.endsWith("　")) {
			inTarget = inTarget.substring(0, inTarget.length() - 1).trim();
		}

		return inTarget;
	}
}
