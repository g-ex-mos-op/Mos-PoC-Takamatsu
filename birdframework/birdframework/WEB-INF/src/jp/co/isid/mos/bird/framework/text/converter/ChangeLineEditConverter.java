/*
 * 作成日: 2006/02/24
 *
 */
package jp.co.isid.mos.bird.framework.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.util.formatter.ChangeLineFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 改行コンバータ(編集画面用) \r\n ⇔ ` 変換します。
 * @author xytamura
 */
public class ChangeLineEditConverter extends NormalConverter implements
        Converter {

    /**
     * 改行フォーマッター
     */
    private ChangeLineFormatter changeLineFormatter;

    /**
     * コンストラクタ
     */
    public ChangeLineEditConverter() {
        super();
        changeLineFormatter = new ChangeLineFormatter(true);
    }

    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext,
            UIComponent uiComponent, String value) throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);

        String strFormat = changeLineFormatter.format(obj, false);

        return strFormat;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext,
            UIComponent uiComponent, Object value) throws ConverterException {

        Object obj = super.getAsString(facesContext, uiComponent, value);
        String strFormat = changeLineFormatter.format(obj, true);

        return strFormat;
    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[4];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
        values[3] = changeLineFormatter;

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
        changeLineFormatter = (ChangeLineFormatter) values[3];

    }

}