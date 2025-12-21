/*
 * TimestampFormatter.java
 * 
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

/** 
 * @author ISID MOS PROJECT
 * @version 2003/11/21
 */
public class TimestampFormatter extends DefaultFormatter {

    /** timestamp length*/
    private static final int LENGTHS = 10;
    private static final int LENGTHK = 16;
    private static final int LENGTHA = 19;
    private int type = 0;

    /** format to yyyy/mm/dd  */
    public static final int SYMBOL = 1;
    /** format to xx月xx日xx時xx分  */
    public static final int KANJI = 2;
    /** format to yyyy/mm/dd hh:mm:ss */
    public static final int ALL = 3;

    private char token = '/';
    private char oldChar = '-';
    private char colon = ':';

    /**
     * コンストラクタ
     */
    public TimestampFormatter() {
        this(SYMBOL);
    }

    /**
     * コンストラクタ
     * @param  type  format to type 
     */
    public TimestampFormatter(int type) {
        this.type = type;
    }

    /**
     * 
     * @param  target     対象文字列
     * @return フォーマット済文字列
     * @param  isForView  true/falseに関わらず表示用にフォーマットします
     */
    public String format(final Object target, final boolean isForView) {
        String sTime = target.toString().trim();
        String text = "";
        if (type == SYMBOL) {
            if (sTime.length() >= LENGTHS) {
                text = sTime.substring(0, LENGTHS);
                text = text.replace(oldChar, token);
            }
        }
        else if (type == KANJI) {
            if (sTime.length() >= LENGTHK) {
                text = sTime.substring(5, 7) + "月" + sTime.substring(8, 10) + "日" + sTime.substring(11, 13) + "時" + sTime.substring(14, 16) + "分";
            }
        }
        else if (type == ALL) {
            if (sTime.length() >= LENGTHA) {
                text =
                    sTime.substring(0, 4)
                        + token
                        + sTime.substring(5, 7)
                        + token
                        + sTime.substring(8, 10)
                        + ' '
                        + sTime.substring(11, 13)
                        + colon
                        + sTime.substring(14, 16)
                        + colon
                        + sTime.substring(17, 19);

            }
        }
        return text;
    }
}
