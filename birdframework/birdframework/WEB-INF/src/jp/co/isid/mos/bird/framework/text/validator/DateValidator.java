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

import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * 日付の妥当性（桁数、入力値）をチェックするバリデータ
 * @author xytamura
 */
public class DateValidator extends NormalValidator implements Verifier,
        Validator {
 
    /**
     * 日付の妥当性チェックを行うVerifier
     */
    private DateVerifier dateVerifier;

    /**
     * コンストラクタ デフォルトのチェック（YYYY/MM/DD）のバリデータを設定
     */
    public DateValidator() {
        super();
        dateVerifier = new DateVerifier();
//        setApplicationException(new InvalidInputException("入力された日付"));
        setMessageArgs(new String[]{"入力された日付"});

    }

    /**
     * コンストラクタ 指定のチェックのバリデータを設定
     * @param dateType 日付のパターン
     */
    public DateValidator(final int dateType) {
        super();
        dateVerifier = new DateVerifier(dateType);
//        setApplicationException(new InvalidInputException("入力された日付"));
        setMessageArgs(new String[]{"入力された日付"});
    }

    /**
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        super.validate(facesContext, uiComponent, value);
        boolean isValidate = dateVerifier.validate(convertString(value));
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
        ((Map)obj).put("dateVerifier", dateVerifier);
        return obj;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        super.restoreState(facesContext, state);
        dateVerifier = (DateVerifier)((Map)state).get("dateVerifier");
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        super.setNullable(isNullable);
        dateVerifier.setNullable(isNullable);
    }

    
}