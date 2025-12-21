/*
 * DefaultJapaneseVerifier.java
 *
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

import jp.co.isid.mos.bird.framework.util.MojiConverter;

/**
 * 日本語文字列用バリデータ。
 * JIS非漢字、第一水準、第二水準、及び半角文字列を許可します。
 */
public class DefaultJapaneseVerifier extends DefaultVerifier {

    /**
     * 半角カナを示す定数です。
     */
    public static final String HANKAKU_KANA =
        "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ｡､｢｣ ";

    /**
     * 半角数字を示す定数です。
     */
    public static final String HANKAKU_NUMBER = "0123456789 ";

    /**
     * 半角アルファベットを示す定数です。
     */
    public static final String HANKAKU_LATIN =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    /**
     * 半角記号を示す定数です。
     */
    public static final String HANKAKU_SYMBOL = "!#$%&'()*+-./_:=?@[\\]^`{|}~･ ";

    /**
     * パスワード用半角記号を示す定数です。
     */
    public static final String HANKAKU_SYMBOL2 = "!#$%&()*+-./_:=?@[]^{|}･ ";

    /**
     * 全角カナを示す定数です。
     */
    public static final String ZENKAKU_KANA =
        "ァアィイゥウェエォオカガキギクグケゲコゴサザシジ"
            + "スズセゼソゾタダチヂッツヅテデトドナニヌネノハバパ"
            + "ヒビピフブプヘベペホボポマミムメモャヤュユョヨラリル"
            + "レロヮワヰヱヲンヴヵヶー　";

    /**
     * 全角ひらがなを示す定数です。
     */
    public static final String ZENKAKU_HIRA =
        "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞ"
            + "ただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽ"
            + "まみむめもゃやゅゆょよらりるれろゎわゐゑをんー　";

    /**
     * 全角数字を示す定数です。
     */
    public static final String ZENKAKU_NUMBER = "０１２３４５６７８９";

    /**
     * 全角アルファベットを示す定数です。
     */
    public static final String ZENKAKU_LATIN =
        "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ　";

    /**
     * 全角記号を示す定数です。
     */
    public static final String ZENKAKU_SYMBOL =
        "、。，．・：；？！゛゜´｀¨＾"
            + "￣ヽヾゝゞ〃仝々〆〇ー―‐／＼＿　"
            + "～∥｜…‥‘’“”（）〔〕［］｛"
            + "｝〈〉《》「」『』【】＋－±×"
            + "÷＝≠＜＞≦≧∞∴♂♀°′″℃￥"
            + "＄￠￡％＃＆＊＠§☆★○●◎◇◆"
            + "□■△▲▽▼※〒→←↑↓〓　"
            + "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ"
            + "αβγδεζηθικλμνξοπρστυφχψω"
            + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    /**
     * JIS 第一水準、及び JIS 第二水準の漢字を示す定数です。
     */
    public static final String ZENKAKU_KANJI = "KANJI";

    /**
     * 文字列が妥当かどうかを検査します。
     * @param value 検査対象の文字列
     * @return true:　妥当　false:　妥当ない
     */
    public boolean validate(final String value) {
        String newValue = MojiConverter.convertUnicodetoMS932(value);
        if (newValue == null || "".equals(newValue.trim())) {
            return _isNullable;
        }

        for (int i = 0; i < newValue.length(); i++) {
            if (!doValidate(newValue.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * １文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @return 検査結果
     */
    protected boolean doValidate(final String source) {
        return isValidChar(source, HANKAKU_KANA)
            || isValidChar(source, HANKAKU_LATIN)
            || isValidChar(source, HANKAKU_NUMBER)
            || isValidChar(source, HANKAKU_SYMBOL)
            || isValidChar(source, ZENKAKU_KANA)
            || isValidChar(source, ZENKAKU_HIRA)
            || isValidChar(source, ZENKAKU_LATIN)
            || isValidChar(source, ZENKAKU_NUMBER)
            || isValidChar(source, ZENKAKU_SYMBOL)
            || isValidChar(source, ZENKAKU_KANJI);
    }

    /**
     * タイプに応じて１文字分の妥当性を検索します。
     * @param source 検索対象の文字
     * @param type 文字のタイプ（このクラスの定数で指定）
     * @return 検査結果
     */
    protected boolean isValidChar(final String source, final String type) {
        if (ZENKAKU_KANJI.equals(type)) {
            return isKanji(source);
        }
        return type.indexOf(source) != -1;
    }

    protected boolean isKanji(final String source) {
        if (source.equals("\n")) {
            return true;
        }

        if (source.equals("\r")) {
            return true;
        }

        /* from http://home.att.ne.jp/apple/wizard/java/ShiftJIS.html
        JISX0208   非漢字           0x8140～0x84BE
                   第一水準         0x889F～0x9872
                   第二水準         0x989F～0x9FFC, 0xE040～0xEAA4

        Windows start-program-アクセサリ-システムツール-文字コード表も参照 */

        //String special = "～∥－￠￡￢";
        //String special = "～∥－";
        //if (special.indexOf(source.charAt(0)) >= 0) {
        //return true;
        //}
        byte[] bytes;
        try {
            bytes = source.getBytes("Shift_JIS");
        }
        catch (java.io.UnsupportedEncodingException e) {
            return false;
        }
        if (bytes.length != 2) {
            return false;
        }

        int high = bytes[0];
        int low = bytes[1];
        if (high < 0) {
            high = 256 + high;
        }
        if (low < 0) {
            low = 256 + low;
        }

        long value = low + (long) high * 256;

        /*
        if (0x8140 <= value && value <= 0x84BE
            || 0x889F <= value && value <= 0x9872
            || 0x989F <= value && value <= 0x9FFC
            || 0xE040 <= value && value <= 0xEAA4) {
            return true;
        }
        */

        //JISX0208 第一水準漢字及び第二水準漢字のみ許可
        if ((0x889F <= value && value <= 0x9872)
            || (0x989F <= value && value <= 0x9FFC)
            || (0xE040 <= value && value <= 0xEAA4)) {
            return true;
        }

        return false;
    }
}