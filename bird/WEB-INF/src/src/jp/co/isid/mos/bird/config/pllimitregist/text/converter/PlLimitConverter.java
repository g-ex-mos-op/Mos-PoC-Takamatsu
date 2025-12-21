/*
 * 作成日: 2006/09/01
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.text.converter;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * P/L上下限コンバータ
 * 
 * @author xyuchida
 */
public class PlLimitConverter implements Converter {

    /**
     * 金額バリデータ
     */
    private Verifier limitSumVerifier;
    /**
     * 構成比バリデータ
     */
    private Verifier limitRatioVerifier;
    /**
     * 金額フォーマッタ
     */
    private DecimalFormat limitSumFormatter;
    /**
     * 構成比フォーマッタ
     */
    private DecimalFormat limitRatioFormatter;

    public Verifier getLimitSumVerifier() {
        return limitSumVerifier;
    }
    public void setLimitSumVerifier(Verifier limitSumVerifier) {
        this.limitSumVerifier = limitSumVerifier;
    }
    public Verifier getLimitRatioVerifier() {
        return limitRatioVerifier;
    }
    public void setLimitRatioVerifier(Verifier limitRatioVerifier) {
        this.limitRatioVerifier = limitRatioVerifier;
    }
    public DecimalFormat getLimitSumFormatter() {
        return limitSumFormatter;
    }
    public void setLimitSumFormatter(DecimalFormat limitSumFormatter) {
        this.limitSumFormatter = limitSumFormatter;
    }
    public DecimalFormat getLimitRatioFormatter() {
        return limitRatioFormatter;
    }
    public void setLimitRatioFormatter(DecimalFormat limitRatioFormatter) {
        this.limitRatioFormatter = limitRatioFormatter;
    }

    /**
     * オブジェクト取得
     * 表示用文字列からオブジェクトへの変換を行う。
     * 
     * @param context
     * @param component
     * @param value
     * @return
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value)
            throws ConverterException {

        // カンマ削除
        String result = "";
        if (value != null) {
            result = value.trim().replaceAll(",", "");
        }

        return result;
    }

    /**
     * 表示用文字列取得
     * オブジェクトから表示用文字列への変換を行う。
     * 
     * @param context
     * @param component
     * @param value
     * @return
     */
    public String getAsString(FacesContext context, UIComponent component,
            Object value) throws ConverterException {

        String target = (String) ((value == null) ? "" : value);
        String result = target;
        int separatorIndex = target.indexOf(PlLimitRegistConstants.LIMIT_SEPARATOR);
        // セパレータあり？
        if (separatorIndex >= 0) {
            // 設定タイプ取得
            String limitType = target.substring(0, separatorIndex);
            // セパレータ以降に文字列あり？
            if (separatorIndex + 1 < target.length()) {
                // 上下限値取得
                String limitValue = target.substring(separatorIndex + 1, target.length());
                // 設定タイプが金額、かつ整数値
                if (PlLimitRegistConstants.LIMIT_TYPE_SUM.equals(limitType)
                        && getLimitSumVerifier().validate(limitValue)) {
                    // カンマ編集フォーマット
                    result = getLimitSumFormatter().format(Long.parseLong(limitValue.trim()));

                // 設定タイプが構成比、かつ小数値
                } else if (PlLimitRegistConstants.LIMIT_TYPE_RATIO.equals(limitType)
                        && getLimitRatioVerifier().validate(limitValue)) {
                    // カンマ編集フォーマット
                    result = getLimitRatioFormatter().format(Double.parseDouble(limitValue.trim()));

                // 異常値
                } else {
                    // 入力値をそのまま出力
                    result = limitValue;
                }
            } else {
                // 上下限値が空なのでブランク出力
                result = "";
            }
        }
        return result;
    }
}
