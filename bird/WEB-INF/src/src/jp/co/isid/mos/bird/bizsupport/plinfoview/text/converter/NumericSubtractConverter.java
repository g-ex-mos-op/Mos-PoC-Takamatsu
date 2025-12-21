/*
 * 作成日: 2006/04/14
 *
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.text.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 「_」で区切られた２つ値の差を求め、「#,##0」フォーマット編集します
 * 
 * @author xytamura
 */
public class NumericSubtractConverter extends NormalConverter implements Converter, Formatter {

    /**
     * 数値フォーマッタ
     */
    private NumericFormatter numericFormatter;
    /**
     * 小数点以下桁数
     */
    private int digitsNum = 0;
    
    /**
     * コンストラクタ デフォルトのパターン
     */
    public NumericSubtractConverter() {
        super();
    }

    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * @param digitsNum 小数点以下の桁数
     */
    public NumericSubtractConverter(final int digitsNum) {
        super();
        this.digitsNum  =digitsNum;
        numericFormatter = new NumericFormatter(true, digitsNum);
        
    }

    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext,
            UIComponent uiComponent, String value) throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);

        return obj;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext,
            UIComponent uiComponent, Object value) throws ConverterException {

        String val = super.getAsString(facesContext, uiComponent, value);
        //NumericFormatter formatter = new NumericFormatter(true, 0);
        
        // 文字列「_」で分割し当年と前年の金額を算出
        if (val.indexOf("_") < 0) {
            //「_」が見つからない場合は、「0」
            return numericFormatter.format("0");
        }
        String strVal1 = val.substring(0, val.indexOf("_"));
        String strVal2 = val.substring(val.indexOf("_") + 1);
        
        if (strVal1 == null || "".equals(strVal1)) {
            strVal1 = "0";
        }
        if (strVal2 == null || "".equals(strVal2)) {
            strVal2 = "0";
        }
        BigDecimal subtractVal = new BigDecimal("0");
        
        strVal1 = jp.co.isid.mos.bird.framework.util.Converter.decToString(new BigDecimal(strVal1), this.digitsNum);
        strVal2 = jp.co.isid.mos.bird.framework.util.Converter.decToString(new BigDecimal(strVal2), this.digitsNum);

        try {
            BigDecimal bigVal1 = jp.co.isid.mos.bird.framework.util.Converter.stringToDec(strVal1);
            BigDecimal bigVal2 = jp.co.isid.mos.bird.framework.util.Converter.stringToDec(strVal2);
            
            subtractVal = bigVal1.subtract(bigVal2);
        }
        catch (Exception ex) {
            throw new ConverterException(ex);
        }
        
        String retVal = numericFormatter.format(subtractVal.toString());
        if (subtractVal.compareTo(new BigDecimal("0")) < 0) {
            retVal = "<font color=\"red\">" + retVal + "</font>";
        }
        return retVal;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(final String formatPattern) {
        super.setFormatPattern(formatPattern);
    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(final String defaultText) {
        super.setDefaultText(defaultText);
    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[4];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
        values[3] = new DefaultFormatter();
        return values;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        Object[] values = (Object[]) state;
        formatPattern = (String) values[0];
        defaultText = (String) values[1];
        isTransient = ((Boolean) values[2]).booleanValue();
    }
}