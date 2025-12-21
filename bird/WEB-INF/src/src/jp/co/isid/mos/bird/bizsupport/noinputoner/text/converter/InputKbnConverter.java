/*
 * 作成日: 2005/10/26
 *
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * 日付コンバータ 日付文字列用のコンバータ
 * 
 * @author xytamura
 */
public class InputKbnConverter extends NormalConverter implements Converter, Formatter {
    /**
     * 日付フォーマッタ
     */
//    private DateFormatter dateFormatter;

    /**
     * コンストラクタ デフォルトのパターン（変換前：YYYYMMDD、変換後：YYYY/MM/DD）でフォーマッタを設定
     */
    public InputKbnConverter() {
        super();
//        dateFormatter = new DateFormatter();
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

        String strFormat;
        String val = super.getAsString(facesContext, uiComponent, value);
        if (val != null && "0".equals(val.trim())) {
            strFormat = "○";
        }
        else if (val != null && "1".equals(val.trim())) {
            strFormat = "Ｅ";
        }
        else if (val != null && "9".equals(val.trim())) {
            //---2006/05/17追加  ERR_FLG='9'は「×（未入力）」にする
            strFormat = "×";
        }
        else {
            strFormat = "×";
        }
        return strFormat;
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

    public String convert(String val) {
        String strFormat;
        if (val != null && "0".equals(val.trim())) {
            strFormat = "○";
        }
        else if (val != null && "1".equals(val.trim())) {
            strFormat = "Ｅ";
        }
        else {
            strFormat = "×";
        }
        return strFormat;
    }
}