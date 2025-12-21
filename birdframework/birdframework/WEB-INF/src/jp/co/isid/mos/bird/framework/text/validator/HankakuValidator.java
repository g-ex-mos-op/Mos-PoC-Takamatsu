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

import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 半角文字を許すバリデータ
 * @author xytamura
 */
public class HankakuValidator extends NormalValidator implements Verifier,
        Validator {

    /**
     *入力文字の妥当性チェックを行うVerifier
     */
    private HankakuVerifier hankakuVerifier;

    /**
     * コンストラクタ デフォルトのチェックのバリデータを設定
     */
    public HankakuValidator() {
        super();
        hankakuVerifier = new HankakuVerifier();
//        setApplicationException(new InvalidInputException("入力された文字",
//                hankakuVerifier.getValidDescription()));
        setMessageArgs(new String[]{"入力された文字", hankakuVerifier.getValidDescription()});

    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = hankakuVerifier.validate(convertString(value));
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
        ((Map)obj).put("hankakuVerifier", hankakuVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        hankakuVerifier = (HankakuVerifier)((Map)state).get("hankakuVerifier");
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        hankakuVerifier.setNullable(isNullable);
    }


}