/*
 * çÏê¨ì˙: 2006/07/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * @author xyuchida
 */
public class CheckBoxConverter implements Converter {

    private static final String DEFAULT_TRUE_VALUE = "1";
    private static final String DEFAULT_FALSE_VALUE = "0";

    private String trueValue;
    private String falseValue;

    public CheckBoxConverter() {
        setTrueValue(DEFAULT_TRUE_VALUE);
        setFalseValue(DEFAULT_FALSE_VALUE);
    }

    public CheckBoxConverter(String trueValue, String falseValue) {
        setTrueValue(trueValue);
        setFalseValue(falseValue);
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        String result = null;
        if (value.equals(Boolean.toString(true))) {
            result = getTrueValue();
        } else if (value.equals(Boolean.toString(false))) {
            result = getFalseValue();
        }
        return result;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object)
            throws ConverterException {
        String value = (String) object;
        boolean result = false;
        if (value.equals(getFalseValue())) {
            result = false;
        } else if (value.equals(getTrueValue())) {
            result = true;
        }
        return Boolean.toString(result);
    }

    public String getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }

    public String getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }
}
