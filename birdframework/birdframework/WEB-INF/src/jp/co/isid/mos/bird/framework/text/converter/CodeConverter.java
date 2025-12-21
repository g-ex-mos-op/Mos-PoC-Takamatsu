/*
 * 作成日: 2005/10/26
 *
 */
package jp.co.isid.mos.bird.framework.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * コードコンバータ 指定のパターンで桁数を揃えるコンバータ
 * 
 * @author xytamura
 */
public class CodeConverter extends NormalConverter implements Converter,
        Formatter {

    /**
     * コードフォーマッタ
     */
    private CodeFormatter codeFormatter;

    /**
     * コンストラクタ デフォルトのパターン（５桁前０付き）でフォーマッタを設定
     */
    public CodeConverter() {
        super();
        codeFormatter = new CodeFormatter(5, defaultText);
        setFormatPattern("00000");
    }

    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * 
     * @param digit 桁数
     * @param formatPattern フォーマットパターン
     */
    public CodeConverter(final int digit, final String formatPattern) {
        super();
        codeFormatter = new CodeFormatter(digit, defaultText);
        setFormatPattern(formatPattern);
    }

    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(final FacesContext facesContext,
            final UIComponent uiComponent, final String value)
            throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);

        String strFormat = codeFormatter.format(obj.toString(), false);
        return strFormat;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(final FacesContext facesContext,
            final UIComponent uiComponent, final Object value)
            throws ConverterException {

        String strFormat = codeFormatter.format(super.getAsString(facesContext,
                uiComponent, value), true);

        return strFormat;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(final String formatPattern) {
        super.setFormatPattern(formatPattern);
        codeFormatter.setFormatPattern(formatPattern);

    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(final String defaultText) {
        super.setDefaultText(defaultText);
        codeFormatter.setDefaultText(defaultText);

    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[4];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
        values[3] = codeFormatter;
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
        codeFormatter = (CodeFormatter) values[3];

    }

}