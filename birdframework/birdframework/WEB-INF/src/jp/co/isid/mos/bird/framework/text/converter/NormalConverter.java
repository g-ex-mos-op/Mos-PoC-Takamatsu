/*
 * 作成日: 2005/10/27
 *
 */
package jp.co.isid.mos.bird.framework.text.converter;

import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;

/**
 * コンバータの親クラス
 * 文字列をそのまま返すデフォルトのコンバータ。
 * @author xytamura
 */
public abstract class NormalConverter implements Converter, Formatter, StateHolder {

    protected String formatPattern = "";

    protected String defaultText = "";

    protected boolean isTransient;

    /**
     * コンストラクタ
     */
    public NormalConverter() {
        super();
    }

    /**
     * DTOと関連付けた場合、DTOのプロパティにセットされるタイミングで実行されます。
     * @param facesContext コンテキスト情報
     * @param uiComponent 入力されたコンポーネント
     * @param value 入力された値
     * @return コンバートされた値
     */
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
            throws ConverterException {
        if(value == null){
            return "";
        }
        return value;
    }

    /**
     * HTMLに表示するタイミングで実行されます。
     * @param facesContext コンテキスト情報
     * @param uiComponent 入力されたコンポーネント
     * @param value 入力された値
     * @return コンバートされた値
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
            throws ConverterException {
        if(value == null){
            return "";
        }
        return value.toString();
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#format(java.lang.Object, boolean)
     */
    public String format(Object target, boolean isForView) {
        if (target == null) {
            return "";
        }
        return target.toString();
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#parseValue(java.lang.String)
     */
    public Object parseValue(String formattedText) {
        return (Object) formattedText;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#setFormatPattern(java.lang.String)
     */
    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#getFormatPattern()
     */
    public String getFormatPattern() {
        return formatPattern;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#setDefaultText(java.lang.String)
     */
    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.formatter.Formatter#getDefaultText()
     */
    public String getDefaultText() {
        return defaultText;
    }
    
    /**
     * @see javax.faces.component.StateHolder#isTransient()
     */
    public boolean isTransient() {
        return isTransient;
    }

    /**
     * @see javax.faces.component.StateHolder#setTransient(boolean)
     */
    public void setTransient(boolean isTransient) {
        this.isTransient = isTransient;

    }


}
