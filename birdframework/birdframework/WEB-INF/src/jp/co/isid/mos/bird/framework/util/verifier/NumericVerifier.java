/*
 * NumericVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

import java.math.BigDecimal;

/**
 * 数字のみ許可するバリデータ。
 * 2004/04/01 getValidDescription()メソッド追加
 * @author ISID MOS PROJECT
 * @since  2002.12.25
 */
public class NumericVerifier extends DefaultVerifier {

    // マイナス許可フラグ
    private boolean _isMinusAllowed;

    // 入力可能桁数
    private int _ketasu = 0;
    // 小数点以下桁数
    private int _digitNum = 0;

    private static final String NUMERIC_CHRS = "0123456789.,+- ";

    /**
     * マイナスの許可/不可を指定して新しいバリデータを生成します。
     * @param isMinusAllowed true：マイナスを許可する / false：マイナスを許可しない
     */
    public NumericVerifier(final boolean isMinusAllowed) {
        this(isMinusAllowed, 0);
    }

    /**
     * マイナスの許可/不可を指定して新しいバリデータを生成します。
     * @param isMinusAllowed true：マイナスを許可する / false：マイナスを許可しない
     * @param ketasu 入力可能桁数（カンマを抜かした桁数）
     */
    public NumericVerifier(final boolean isMinusAllowed, final int ketasu) {
        this(isMinusAllowed, ketasu, 0);
    }

    /**
     * マイナスの許可/不可を指定して新しいバリデータを生成します。
     * @param isMinusAllowed マイナスを許可する / false：マイナスを許可しない
     * @param ketasu 入力可能桁数（カンマを抜かした桁数）
     * @param digitNum 小数点以下桁数（「9,999.99」がMaxの場合は、2を指定）
     */
    public NumericVerifier(
        final boolean isMinusAllowed,
        final int ketasu,
        final int digitNum) {
        this();
        _isMinusAllowed = isMinusAllowed;
        _ketasu = ketasu;
        _digitNum = digitNum;
    }

    /**
     * 新しいバリデータを生成します。マイナスも許可されます。
     */
    public NumericVerifier() {
        _isMinusAllowed = true;
    }

    /**
     * 整数値としての妥当性チェックを行います。 
     * @param text 対象となる文字列
     * @return true：成功 / false：失敗
     */
    public boolean validate(final String text) {
        if (text == null || text.trim().equals("")) {

            return _isNullable;
        }

        try {
            if (_digitNum == 0) {
                new java.lang.Long(text.trim());
            }
            else {

                new java.math.BigDecimal(text.trim());
            }
        }
        catch (NumberFormatException nfe) {
            return false;
        }

//--- 2004/04/01 update 桁数チェック時の＋－記号対応        
        // 全桁数のチェック
//        if (_ketasu != 0 && text.trim().length() > _ketasu) {
//            return false;
//        }
        int maxlen = _ketasu;
        if (text.startsWith("+") || text.startsWith("-")) {
            maxlen++;
        }
        //小数点なし設定の場合
        if(_digitNum <= 0){
            if (_ketasu != 0 && text.trim().length() > maxlen) {
                return false;
            }
        //小数点あり設定の場合
        }else{
            //小数点がありで入力
            if(text.indexOf(".") != -1){
                if ( _ketasu != 0 && text.trim().length() > maxlen + 1) {
                    return false;
                }
                if(text.indexOf(".")  > maxlen - _digitNum) {
                    return false;
                }
            //小数点がなしで入力
            }else{
                if ( _ketasu != 0 && text.trim().length() > maxlen - _digitNum) {
                    return false;
                }
            }
        }

        // 少数以下の桁数チェック
        if (_ketasu != 0
            && text.indexOf(".") != -1
            && (text.length() - text.indexOf(".") - 1) > _digitNum) 
        {
            return false;
        }

        // マイナスチェック
        if (!_isMinusAllowed) {

            if (text.indexOf("-") >= 0) {

                return false;
            }
        }

        // 小数チェック
        if (_digitNum == 0) {

            if (text.indexOf(".") >= 0) {

                return false;
            }
        }

        //ADD: by kondo on 2004/03/04 全角チェックを追加
        for (int i = 0; i < text.length(); i++) {
            if (!isValidChar(text.substring(i, i + 1))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 整数値としての妥当性チェックを行います。 
     * @param text 対象となる文字列
     * @return true：成功 / false：失敗
     */
    public boolean validate(final BigDecimal text) {
        if(text == null){
            return true;
        }
        return validate(text.toString());
    }
    
    
    private boolean isValidChar(final String source) {
        return NUMERIC_CHRS.indexOf(source) != -1;
    }

    /**
     * 許可するデータタイプを文字列で返します。
     * @return String
     */
    public String getValidDescription() {
        String retDescription = "";
        
        if (_digitNum > 0) {
            retDescription = "[数値(整数" + (_ketasu - _digitNum - 1) + "桁";
            retDescription += " 小数" + _digitNum + "桁";
        }
        else {
            retDescription = "[数値(整数" + _ketasu + "桁";
        }
        
        if (!_isMinusAllowed) {
            retDescription += " マイナス不可";
        }
        
        return retDescription + ")]";
    }

}
