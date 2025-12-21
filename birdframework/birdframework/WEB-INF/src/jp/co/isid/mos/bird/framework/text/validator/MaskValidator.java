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

import jp.co.isid.mos.bird.framework.util.verifier.MaskVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 指定のパターンに一致した文字列のみを許すバリデータ
 * @author xytamura
 */
public class MaskValidator extends NormalValidator implements Verifier,
        Validator {

    /**
     * 入力文字の妥当性チェックを行うVerifier
     */
    private MaskVerifier maskVerifier;

    /**
     * コンストラクタ デフォルトのチェック(何もしない)のバリデータを設定
     */
    public MaskValidator() {
        super();
        maskVerifier = new MaskVerifier("");
//        setApplicationException(new InvalidInputException("入力された文字列"));
        setMessageArgs(new String[]{"入力された文字列"});
    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param maskPattern 文字列パターン
     */
    public MaskValidator(final String maskPattern) {
        super();
        maskVerifier = new MaskVerifier(maskPattern);
//        setApplicationException(new InvalidInputException("入力された文字列"));
        setMessageArgs(new String[]{"入力された文字列"});

    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = maskVerifier.validate(convertString(value));
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
        ((Map)obj).put("maskVerifier", maskVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        maskVerifier = (MaskVerifier)((Map)state).get("maskVerifier");
    }
    
    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        maskVerifier.setNullable(isNullable);
    }


}