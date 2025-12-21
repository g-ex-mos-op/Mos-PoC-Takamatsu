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

import jp.co.isid.mos.bird.framework.util.verifier.Verifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;

/**
 * 全角カタカナのみを許すバリデータ
 * @author xytamura
 */
public class ZenkakuKatakanaValidator extends NormalValidator implements
        Verifier, Validator {

    /**
     * 入力文字の妥当性チェックを行うVerifier
     */
    private ZenkakuKatakanaVerifier zenkakuKatakanaVerifier;

    /**
     * コンストラクタ デフォルトのチェックのバリデータを設定
     */
    public ZenkakuKatakanaValidator() {
        super();
        zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();
//        setApplicationException(new InvalidInputException("入力された文字",
//                zenkakuKatakanaVerifier.getValidDescription()));
        setMessageArgs(new String[]{"入力された文字", zenkakuKatakanaVerifier.getValidDescription()});

    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = zenkakuKatakanaVerifier
                .validate(convertString(value));
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
        ((Map)obj).put("zenkakuKatakanaVerifier", zenkakuKatakanaVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        zenkakuKatakanaVerifier = (ZenkakuKatakanaVerifier)((Map)state).get("zenkakuKatakanaVerifier");
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        zenkakuKatakanaVerifier.setNullable(isNullable);
    }


}