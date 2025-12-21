/*
 * NumericFormatter.java
 * 
 * Created by xytamura on 2005/10/27
 * Copy from 2ndGenesis
 */
package jp.co.isid.mos.bird.framework.util.formatter;

import java.text.DecimalFormat;
import java.math.BigDecimal;

/**
 * 数値タイプの文字列フォーマッタ。
 * <PRE>
 * ・変換後のフォーマットパターンについて
 * 　・format(String,String)
 * 　　　第2パラメータで指定されたフォーマットで変換されます
 * 　・format(String) 、 format(String,boolean)
 * 　　　setDefaultText(String)でフォーマット
 * 　　　　指定されてる
 * 　　　　　　→指定されたフォーマットで変換されます
 * 　　　　指定されていない
 * 　　　　　　→表示用：「#,##0」　　内部処理用：「0」
 * <PRE>
 * @since : 2003.03.03
 * @author: ISID MOS PROJECT
 */
public class NumericFormatter extends DefaultFormatter {
    private DecimalFormat decf = new DecimalFormat();

    private boolean isForView = true;
    private int digitsNum = -1; // 小数点以下桁数
    //値よりもパターン(形式)が優先か否か
    private boolean mustPattern = false;

    /**
     * コンストラクタ
     * 当コンストラクタを使用した場合は
     * 　・デフォルト戻り値：空文字
     * 　・表示用編集（#,##0)
     * が設定されます。
     */
    public NumericFormatter() {
    }
    
    /**
     * コンストラクタ
     * 表示用・内部処理用の指定を行えます
     * @param isForView 表示用フラグ
     */
    public NumericFormatter(final boolean isForView) {
        this();
        this.isForView = isForView;
    }

    /**
     * コンストラクタ
     * ・表示用・内部処理用の指定を行えます
     * ・小数点以下の桁数を指定できます
     * @param isForView 表示用フラグ
     * @param digitsNum 小数点以下桁数
     */
    public NumericFormatter(final boolean isForView, final int digitsNum) {
        this();
        this.isForView = isForView;
        this.digitsNum = digitsNum;
    }
    /**
     * コンストラクタ
     * ・表示用・内部処理用の指定を行えます
     * ・小数点以下の桁数を指定できます
     * @param isForView 表示用フラグ
     * @param digitsNum 小数点以下桁数
     */
    public NumericFormatter(final boolean isForView, final int digitsNum, final boolean mustPattern) {
        this(isForView, digitsNum);
        setMustPattern(mustPattern);
    }
    /**
     * コンストラクタ
     * ・表示用・内部処理用の指定を行えます
     * ・小数点以下の桁数を指定できます
     * @param isForView 表示用フラグ
     * @param digitsNum 小数点以下桁数
     */
    public NumericFormatter(final boolean isForView, final String formatPattern, final boolean mustPattern) {
        this.isForView = isForView;
        setFormatPattern(formatPattern);
        setMustPattern(mustPattern);
    }
    /**
     * カンマ（,）を取り除く
     * @return java.lang.String カンマを取り除いた文字列
     * @param source java.lang.String 対象文字列
     */
    private static String removeComma(final String source) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != ',') {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * フォーマット処理
     * @param  target     対象文字列
     * @return フォーマット済文字列
     */
    public String format(final Object target) {
        return format(target, isForView);
    }

    /**
     * フォーマット処理
     * 作成日 : (2003/01/17 19:35:13)
     * @return java.lang.String フォーマット済文字列
     * @param target java.lang.String　対象文字列
     * @param pattern java.lang.String フォーマットパターン
     */
    public String format(final String target, final String pattern) {

        String ret = "";

        if (target == null || target.trim().equals("")) {
            return getDefaultText();
        }

        String dec = removeComma(target);

        try {
            if(!isMustPattern()) {
	            // 小数点の桁数チェック
	            if (digitsNum > 0) {
	                if (target.indexOf(".") != -1) {
	                    if ((target.length() - target.indexOf(".") - 1)
	                        > digitsNum) {
	                        throw new NumberFormatException();
	                    }
	                }
	            }
	            else {
	                if (target.indexOf(".") >=0 ) {
	                    throw new NumberFormatException();
	                }
	            }
            }
            synchronized (decf) {
                decf.applyPattern(pattern);
                decf.format(Double.valueOf(dec).doubleValue());
                return decf.format(Double.valueOf(dec).doubleValue());
            }
        }
        catch (NumberFormatException ex) {
            ret = target;
        }

        return ret;
    }

    /**
     * フォーマット処理を行います。
     * 第2パラメータisForViewは、当メソッドの処理のみに影響します。
     * コンストラクタで指定されたフラグには影響しません。
     * @param  target     対象文字列
     * @param  isForView  true：表示用 / false：値処理用
     * @return フォーマット済文字列
     */
    public String format(final Object target, final boolean isForView) {

        String ret;
        String pattern;
        boolean bkViewFlg = isForView;
        this.isForView = isForView;

        if (target == null) {
            return getDefaultText();
        }
        
        String sNumeric = target.toString();
        
        if (getFormatPattern().equals("")) {
            if(digitsNum == -1 && isMustPattern()) {
            	//小数桁未指定の場合
            	digitsNum = 0;
            	if(sNumeric.indexOf(".")>=0) {
            		digitsNum = (sNumeric.length() - sNumeric.indexOf(".") - 1);
            	}
            }
            if (isForView) {
                pattern = "#,##0";
                if (digitsNum > 0 ) {
                    pattern += ".";
                    for (int i = 0; i < digitsNum; i++) {
                        pattern += "0";
                    }
                }
            }
            else {
                pattern = "0";
                if (digitsNum > 0) {
                    pattern += ".";
                    for (int i = 0; i < digitsNum; i++) {
                        pattern += "0";
                    }
                }
            }
        }
        else {
            pattern = getFormatPattern();
            if(isMustPattern()) {
            	//[表示形式]指定の場合、[表示形式]から小数桁を確定します。
            	digitsNum = 0;
            	if(pattern.indexOf(".")>=0) {
            		digitsNum = (pattern.length() - pattern.indexOf(".") - 1);
            	}
            }
        }

        ret = format(sNumeric, pattern);
        this.isForView = bkViewFlg;
        return ret;
    }
    
    /**
     * @param target 対象文字列
     * @return BigDecimal
     */
    public Object parseValue(final String target) {
        if (target == null || target.trim().equals("")) {
            return BigDecimal.valueOf(0);
        }
        return new BigDecimal(target);
    }

	/**
     * trueに設定することで強制的に指定された桁数や表示形式にフォーマットします。
     * 小数の桁数は下記の順で優先順位が低くなります。
     * [表示形式]->[小数桁]->[値の形式]
	 * @return クラス変数mustPattern を戻します。
	 */
	public boolean isMustPattern() {
		return mustPattern;
	}

	/**
	 * @param mustPattern を クラス変数mustPatternへ設定します。
	 */
	public void setMustPattern(boolean mustPattern) {
		this.mustPattern = mustPattern;
	}
}
