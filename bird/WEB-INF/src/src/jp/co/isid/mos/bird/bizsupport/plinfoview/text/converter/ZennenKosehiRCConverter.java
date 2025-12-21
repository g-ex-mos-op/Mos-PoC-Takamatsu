/*
 * 作成日: 2006/04/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.text.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * RC用前年構成比
 * 
 * @author xytamura
 */
public class ZennenKosehiRCConverter extends NormalConverter implements Converter, Formatter {

	private PlInfoViewDto plInfoViewDto;
    /**
     * コンストラクタ デフォルトのパターン
     */
    public ZennenKosehiRCConverter() {
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
        NumericFormatter formatter = new NumericFormatter(true, 2);
        
        BigDecimal kosehi = new BigDecimal("0");
        if (val != null && !val.trim().equals("")) {
            BigDecimal kingaku = new BigDecimal(val);
            BigDecimal kingakuKei = getPlInfoViewDto().getBigKouseihiMotoZennen();
            kosehi = Calculator.percentage(kingaku, kingakuKei, 2);
        }
        
        String formatValue = formatter.format(kosehi.toString());
        
        if (kosehi.compareTo(new BigDecimal("0")) < 0) {
            formatValue = "<font color=\"red\">" + formatValue + "</font>";
        }
        return formatValue;
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

	public PlInfoViewDto getPlInfoViewDto() {
		return plInfoViewDto;
	}
	public void setPlInfoViewDto(PlInfoViewDto plInfoViewDto) {
		this.plInfoViewDto = plInfoViewDto;
	}
}