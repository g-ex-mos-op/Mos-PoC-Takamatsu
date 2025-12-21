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

import jp.co.isid.mos.bird.framework.util.verifier.HankakuEisujiPWVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 半角英数字のみを許すバリデータ
 * @author xytamura
 */
public class HankakuEisujiValidator extends NormalValidator implements
        Verifier, Validator {

    /**
     * 入力文字の妥当性チェックを行うVerifier
     */
    private HankakuEisujiPWVerifier hankakuEisujiPWVerifier;

    /**
     * コンストラクタ デフォルトのチェックのバリデータを設定
     */
    public HankakuEisujiValidator() {
        super();
        hankakuEisujiPWVerifier = new HankakuEisujiPWVerifier();
//        setApplicationException(new InvalidInputException("入力された文字",
//                hankakuEisujiVerifier.getValidDescription()));
        setMessageArgs(new String[]{"入力された文字", hankakuEisujiPWVerifier.getValidDescription()});

    }


    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = hankakuEisujiPWVerifier.validate(convertString(value));
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
        ((Map)obj).put("hankakuEisujiPWVerifier", hankakuEisujiPWVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        hankakuEisujiPWVerifier = (HankakuEisujiPWVerifier)((Map)state).get("hankakuEisujiPWVerifier");
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        hankakuEisujiPWVerifier.setNullable(isNullable);
    }


}