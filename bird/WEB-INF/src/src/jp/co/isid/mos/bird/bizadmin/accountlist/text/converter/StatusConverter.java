/*
 * 作成日: 2008/12/04
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountlist.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.bizadmin.accountlist.code.AccountListConst;
import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * 状態 コンバータ
 * @author xnkusama
 */
public class StatusConverter extends NormalConverter implements Converter, Formatter {
    
    /**
     * コンストラクタ 
     */
    public StatusConverter() {
        super();
    }
    
    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        return super.getAsObject(facesContext, uiComponent, value);
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
            throws ConverterException {
        String val = super.getAsString(facesContext, uiComponent, value);
        if (AccountListConst.STOP_FLG_ON.equals(val)) {
            val = AccountListConst.STOP_FLG_ON_DISP;
        }
        else {
            val = AccountListConst.STOP_FLG_OFF_DISP;
        }
        return val;
    }
    
    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object[] values = new Object[4];
        values[0] = formatPattern;
        values[1] = defaultText;
        values[2] = Boolean.valueOf(isTransient);
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
