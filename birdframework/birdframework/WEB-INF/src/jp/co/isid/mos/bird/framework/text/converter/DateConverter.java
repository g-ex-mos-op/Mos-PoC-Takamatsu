/*
 * 作成日: 2005/10/26
 *
 */
package jp.co.isid.mos.bird.framework.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * 日付コンバータ 日付文字列用のコンバータ
 * 
 * @author xytamura
 */
public class DateConverter extends NormalConverter implements Converter,
        Formatter {
    /**
     * 日付フォーマッタ
     */
    private DateFormatter dateFormatter;
    
    /**
     * 9999/12/31を表示しない
     */
    private boolean maxDateNoDisp = true;
    
    private static final String MAX_DATE="9999/12/31";
    
    /**
     * コンストラクタ デフォルトのパターン（変換前：YYYYMMDD、変換後：YYYY/MM/DD）でフォーマッタを設定
     */
    public DateConverter() {
        super();
        dateFormatter = new DateFormatter();
    }

    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * 
     * @param parsePattern 変換前のパターン
     * @param formatPattern 変換後のパターン
     */
    public DateConverter(final int parsePattern, final String formatPattern) {
        super();
        dateFormatter = new DateFormatter(parsePattern, formatPattern);
        setFormatPattern(formatPattern);
        //        setParsePattern(parsePattern);
    }

    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext,
            UIComponent uiComponent, String value) throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);

        String strFormat = dateFormatter.format(obj, false);

        return strFormat;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext,
            UIComponent uiComponent, Object value) throws ConverterException {
        
        String strFormat = dateFormatter.format(super.getAsString(facesContext,
                uiComponent, value), true);

        if(isMaxDateNoDisp() && MAX_DATE.equals(strFormat)){
            return "";
        }
        return strFormat;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(final String formatPattern) {
        super.setFormatPattern(formatPattern);
        dateFormatter.setFormatPattern(formatPattern);

    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(final String defaultText) {
        super.setDefaultText(defaultText);
        dateFormatter.setDefaultText(defaultText);

    }

    /**
     * 9999/12/31を表示しない
     * @return true:表示しない。false:表示する。
     */
    public boolean isMaxDateNoDisp() {
        return maxDateNoDisp;
    }
    
    /**
     * 9999/12/31を表示しないかの識別フラグをセットします。
     * @param true:表示しない。false:表示する。
     */
    public void setMaxDateNoDisp(boolean maxDateNoDisp) {
        this.maxDateNoDisp = maxDateNoDisp;
    }
    
    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[5];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
        values[3] = dateFormatter;
        values[4] = Boolean.valueOf(maxDateNoDisp);

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
        dateFormatter = (DateFormatter) values[3];
        maxDateNoDisp =((Boolean) values[4]).booleanValue(); 

    }

}