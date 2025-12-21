/*
 * 作成日: 2006/3/2
 *
 */
package jp.co.isid.mos.bird.framework.text.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.util.MojiConverter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * 文字コード変換コンバータ
 * 文字コード変換を行うコンバータ
 * @author itamoto
 */
public class CharacterEncodingConverter extends NormalConverter implements Converter, Formatter {
    
    /**
     * 各種変換処理クラス
     */
    private MojiConverter mojiConverter;
    
    /**
     * コンストラクタ デフォルトのパターン
     */
    public CharacterEncodingConverter() {
        super();
    }
    
    /**
     * コンストラクタ 指定のパターンでフォーマッタを設定
     * @param digitsNum
     */
    public CharacterEncodingConverter(final int digitsNum) {
        super();
    }
    
    /**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        Object obj = super.getAsObject(facesContext, uiComponent, value);
        String strFormat = MojiConverter.convertMS932toUnicode(obj.toString());
        return strFormat;
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
            throws ConverterException {
        String strFormat = MojiConverter.convertUnicodetoMS932(super
				.getAsString(facesContext, uiComponent, value).toString());
        return strFormat;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(final String formatPattern){
       super.setFormatPattern(formatPattern);
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.text.converter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(final String defaultText){
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
        values[3] = mojiConverter;
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
        mojiConverter = (MojiConverter) values[3];
    }
}
