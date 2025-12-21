/*
 * 作成日: 2006/07/24
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * 能力、面接の○×コンバーター
 * @author kusama
 */
public class GohiInterviewHanteiConverter extends NormalConverter implements Converter, Formatter {

    /**
     * コンストラクタ デフォルトのパターン
     */
    public GohiInterviewHanteiConverter() {
        super();
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
        String retVal = "";
        if ("2".equals(val)) {
            retVal = "免除";
        } else if ("1".equals(val)) {
            retVal = "合格";
        } else if ("0".equals(val)) {
            retVal = "不合格";
        } 
//20060830 nakajima start
        else if ("3".equals(val)) {
            retVal = "受験不可";
        }
        else if ("9".equals(val)) {
            retVal = "未受験";
        }
//20060830 nakajima end
        return  retVal;
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