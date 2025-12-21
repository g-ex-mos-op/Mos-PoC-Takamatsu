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

import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.framework.text.converter.NormalConverter;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * RC前年比較 金額
 * 
 * @author xytamura
 */
public class RcZennenHikakuKoseihiConverter extends NormalConverter implements Converter, Formatter {

	private PlInfoViewDto plInfoViewDto;
    /**
     * コンストラクタ デフォルトのパターン
     */
    public RcZennenHikakuKoseihiConverter() {
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
        
        // 文字列「_」で分割し当年と前年の金額を算出
        if (val.indexOf("_") < 0) {
            //「_」が見つからない場合は、「0.00」
            return "0";
        }
        String tounen = val.substring(0, val.indexOf("_"));
        String zennen = val.substring(val.indexOf("_") + 1);
        
        if (tounen == null || "".equals(tounen)) {
        	tounen = "0";
        }
        if (zennen == null || "".equals(zennen)) {
        	zennen = "0";
        }
        
//        BigDecimal kinTou = new BigDecimal(tounen);
//        BigDecimal kinTouMoto = getPlInfoViewDto().getBigKouseihiMoto();
//        BigDecimal kinZen = new BigDecimal(zennen);
//        BigDecimal kinZenMoto = getPlInfoViewDto().getBigKouseihiMotoZennen();
//        BigDecimal kosehi = Calculator.percentage(kinTou, kinTouMoto, 2);
//        BigDecimal kosehiZen = Calculator.percentage(kinZen, kinZenMoto, 2);
        
//        return formatter.format(kosehi.subtract(kosehiZen).toString());
        BigDecimal big = Calculator.percentage(new BigDecimal(tounen), new BigDecimal(zennen), 2);
        return big.toString();
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