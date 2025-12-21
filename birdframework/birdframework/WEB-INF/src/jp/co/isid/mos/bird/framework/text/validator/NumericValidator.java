/*
 * 作成日: 2005/11/01
 *
 */
package jp.co.isid.mos.bird.framework.text.validator;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 半角数値のみを許すバリデータ
 * @author xytamura
 */
public class NumericValidator extends NormalValidator implements Verifier,
        Validator {

    /**
     * 数値の妥当性チェックを行うVerifier
     */
    private NumericVerifier numericVerifier;

    /**
     * コンストラクタ デフォルトのチェック（桁数なし、マイナス許可 ）のバリデータを設定
     */
    public NumericValidator() {
        super();
        numericVerifier = new NumericVerifier();
//        setApplicationException(new InvalidInputException("入力された数値"));
        setMessageArgs(new String[]{"入力された数値"});

    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param isMinusAllowed true:マイナス許可 false:マイナス不可
     * @param ketasu 桁数
     */
    public NumericValidator(final boolean isMinusAllowed, final int ketasu) {
        super();
        numericVerifier = new NumericVerifier(isMinusAllowed, ketasu);
//        setApplicationException(new InvalidInputException("入力された数値"));
        setMessageArgs(new String[]{"入力された数値"});

    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param isMinusAllowed true:マイナス許可 false:マイナス不可
     * @param ketasu 桁数
     * @param digitNum 小数点以下桁数
     */
    public NumericValidator(final boolean isMinusAllowed, final int ketasu,
            final int digitNum) {

        super();
        numericVerifier = new NumericVerifier(isMinusAllowed, ketasu, digitNum);
//        setApplicationException(new InvalidInputException("入力された数値"));
        setMessageArgs(new String[]{"入力された数値"});

    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = numericVerifier.validate(convertString(value));
        //バリデーションに失敗した場合
        if (!isValidate) {
            throw getException();

        }
    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object obj = super.saveState(facesContext);
        ((Map)obj).put("numericVerifier", numericVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        numericVerifier = (NumericVerifier)((Map)state).get("numericVerifier");
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        numericVerifier.setNullable(isNullable);
    }



}