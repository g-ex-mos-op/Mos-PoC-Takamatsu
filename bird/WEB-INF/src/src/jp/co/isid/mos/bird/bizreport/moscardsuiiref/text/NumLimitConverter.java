/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.text;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import jp.co.isid.mos.bird.framework.text.converter.NumericConverter;

/**
 * 数値制限付コンバータ
 * 
 * 作成日:2013/05/07
 * @author xkinu
 *
 */
public class NumLimitConverter extends NumericConverter {
	//制限値:100
	private static BigDecimal LIMIT_NUM = new BigDecimal("1000");
	
    /**
	 * @param formatPattern
	 * @param mustPatternコンストラクター
	 */
	public NumLimitConverter(String formatPattern, boolean mustPattern) {
		super(formatPattern, mustPattern);
	}

	/**
     * HTMLに表示するタイミングで実行されます。
     * @param facesContext コンテキスト情報
     * @param uiComponent 入力されたコンポーネント
     * @param value 入力された値
     * @return コンバートされた値
     */
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
            throws ConverterException
    {
        if(value != null && LIMIT_NUM.compareTo(new BigDecimal(value.toString())) <= 0) {
        	return "***.**";
        }
        return super.getAsString(facesContext, uiComponent, value);
    }
}
