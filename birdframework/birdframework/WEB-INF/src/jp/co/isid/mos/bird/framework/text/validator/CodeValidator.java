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

import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * コードの妥当性（桁数、入力値）をチェックするバリデータ
 * @author xytamura
 */
public class CodeValidator extends NormalValidator implements Verifier,
        Validator {
    
    /**
     * コードの妥当性チェックを行うVerifier
     */
    private CodeVerifier codeVerifier;

    /**
     * コンストラクタ デフォルトのチェック（５桁前０付き）のバリデータを設定
     */
    public CodeValidator() {
        super();
        codeVerifier = new CodeVerifier();
        setMessageArgs(new String[]{"入力されたコード"});
    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param digit 桁数
     * @param isAlphabet true:アルファベット可 false:アルファベット不可
     */
    public CodeValidator(final int digit, final boolean isAlphabet) {
        super();
        codeVerifier = new CodeVerifier(digit, isAlphabet);
        setMessageArgs(new String[]{"入力されたコード"});
    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = codeVerifier.validate(convertString(value));
        //バリデーションに失敗した場合
        if(!isValidate){
            throw getException();        

        }
    }

    /**
     * @see javax.faces.component.StateHolder#saveState(javax.faces.context.FacesContext)
     */
    public Object saveState(FacesContext facesContext) {
        Object obj = super.saveState(facesContext);
        ((Map)obj).put("codeVerifier", codeVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        codeVerifier = (CodeVerifier)((Map)state).get("codeVerifier");
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        codeVerifier.setNullable(isNullable);
    }

    
}