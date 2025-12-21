/*
 * 作成日: 2005/10/26
 *
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.text.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 数値コンバータ
 * カンマ編集を行うコンバータ
 * @author xnkusama
 */
public class NumericConverter extends NormalConverter implements Converter, Formatter {
    /**
     * 数値フォーマッタ
     */
    private NumericFormatter numericFormatter;
    /**
     * 小数点以下桁数
     */
    private int digitsNum = 0; 
    /**
     * マイナス値を赤文字にするか？ （true：赤文字にする)
     */
    private boolean isCharColor = false;
    
    /**
     * コンストラクタ デフォルトのパターン（小数点なし、カンマ付き）でフォーマッタを設定
     */
    public NumericConverter() {
        super();
        numericFormatter = new NumericFormatter();
    }
    
    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * @param digitsNum 小数点以下の桁数
     */
    public NumericConverter(final int digitsNum) {
        super();
        this.digitsNum  =digitsNum;
        this.isCharColor = false;
        numericFormatter = new NumericFormatter(true, digitsNum);
        
    }

    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * @param digitsNum 小数点以下の桁数
     */
    public NumericConverter(final int digitsNum, final boolean charColor) {
        super();
        this.digitsNum  =digitsNum;
        this.isCharColor = charColor;
        numericFormatter = new NumericFormatter(true, digitsNum);
        
    }
    
    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);
        
        String strFormat = numericFormatter.format(obj, false);
        return strFormat;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
            throws ConverterException {
        
        String strValue = "0";
        String strFormat = "";
        try {
            if (value != null && !value.toString().equals("")) {
                strValue = jp.co.isid.mos.bird.framework.util.Converter.decToString(new BigDecimal(value.toString()), this.digitsNum);
            }
            strFormat = numericFormatter.format(strValue, true);

            if (value != null && !value.toString().equals("")) {
                if ((new BigDecimal(value.toString())).compareTo(new BigDecimal("0")) < 0 && isCharColor) {
                    strFormat = "<font color=\"red\">" + strFormat + "</font>";
                }
            }
        }
        catch (Exception ex) {
            throw new ConverterException(ex);
        }
        
        return strFormat;
    }
    

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(final String formatPattern){
       super.setFormatPattern(formatPattern);
       numericFormatter.setFormatPattern(formatPattern);
        
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(final String defaultText){
        super.setDefaultText(defaultText);
        numericFormatter.setDefaultText(defaultText);
         
     }
    
    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[4];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
        values[3] = numericFormatter;
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
        numericFormatter = (NumericFormatter) values[3];

    }


    
}
