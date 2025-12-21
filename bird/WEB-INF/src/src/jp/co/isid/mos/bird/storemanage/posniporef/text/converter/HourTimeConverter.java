/*
 * 作成日: 2007/02/19
 *
 */
package jp.co.isid.mos.bird.storemanage.posniporef.text.converter;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
//import jp.co.isid.mos.bird.framework.util.formatter.HourTimeFormatter;
import jp.co.isid.mos.bird.storemanage.posniporef.util.HourTimeFormatter;

/**
 * 時間コンバータ 時間文字列用のコンバータ
 * 
 * @author xwtanabe
 */
public class HourTimeConverter implements Converter{

    /** 時間フォーマッタ */
    private HourTimeFormatter hourTimeFormatter;

    /**
     * コンストラクタ デフォルトのパターン（HH:MM:SS）でフォーマッタを設定
     */
    public HourTimeConverter() {
        super();
        hourTimeFormatter = new HourTimeFormatter();
    }


    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext,
            UIComponent uiComponent, String strValue) throws ConverterException {

        BigDecimal dec = hourTimeFormatter.parseTimeValue(strValue);

        return dec;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext,
            UIComponent uiComponent, Object value) throws ConverterException {

        BigDecimal bd = (BigDecimal)value;
        
        String strFormat = hourTimeFormatter.format(bd);

        return strFormat;
    }
}