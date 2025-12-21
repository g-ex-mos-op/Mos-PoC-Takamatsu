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

import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 電話番号形式のみを許すバリデータ
 * @author xytamura
 */
public class PhoneValidator extends NormalValidator implements Verifier,
        Validator {

    /**
     * 入力文字の妥当性チェックを行うVerifier
     */
    private PhoneVerifier phoneVerifier;

    /**
     * コンストラクタ デフォルトのチェックのバリデータを設定
     */
    public PhoneValidator() {
        super();
        phoneVerifier = new PhoneVerifier();
//        setApplicationException(new InvalidInputException("入力された番号",
//                phoneVerifier.getValidDescription()));
        setMessageArgs(new String[]{"入力された番号", phoneVerifier.getValidDescription()});

    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param digit 桁数
     * @param isHyphen true:ハイフン許可 false:ハイフン不可
     */
    public PhoneValidator(final int digit, final boolean isHyphen) {
        super();
        phoneVerifier = new PhoneVerifier(digit, isHyphen);
//        setApplicationException(new InvalidInputException("入力された番号",
//                phoneVerifier.getValidDescription()));
        setMessageArgs(new String[]{"入力された番号", phoneVerifier.getValidDescription()});

    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = phoneVerifier.validate(convertString(value));
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
        ((Map)obj).put("phoneVerifier", phoneVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        phoneVerifier = (PhoneVerifier)((Map)state).get("phoneVerifier");
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        phoneVerifier.setNullable(isNullable);
    }


}