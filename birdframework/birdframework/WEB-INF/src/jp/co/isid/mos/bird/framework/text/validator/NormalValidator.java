/*
 * 作成日: 2005/11/01
 *
 */
package jp.co.isid.mos.bird.framework.text.validator;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.seasar.jsf.util.MessageUtil;

import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * バリデータの親クラス
 * @author xytamura
 */
public abstract class NormalValidator implements Validator, Verifier,
        StateHolder {

    /**
     * null許可フラグ
     */
    protected boolean isNullable = true;

    /**
     * バリデーションが失敗した時に発生されるException
     */
    //    protected ApplicationException applicationException;
    protected boolean isTransient;

    protected String messageId = "E0505";

    protected String[] messageArgs;

    /**
     * コンストラクタ
     */
    public NormalValidator() {
        super();
    }

    /**
     * チェック処理。DTOと関連付けた場合、DTOのプロパティにセットされるタイミングで実行されます。 コンバータよりも先に実行されます。
     * @see javax.faces.validator.Verifier#validate(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#validate(java.lang.String)
     */
    public boolean validate(String target) {
        return true;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#setNullable(boolean)
     */
    public void setNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    /**
     * @see jp.co.isid.mos.bird.framework.util.verifier.Verifier#getValidDescription()
     */
    public String getValidDescription() {
        return "";
    }

    //    /**
    //     * バリデータ失敗時にスローするExceptionを設定します。
    //     * @param applicationException バリデータ失敗時にスローするException
    //     */
    //    public void setApplicationException(
    //            final ApplicationException applicationException) {
    //          this.applicationException = applicationException;
    //    }

    /**
     * メッセージ引数を取得します。
     * @return メッセージ引数
     */
    public String[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * メッセージ引数を設定します。
     * @param messageArgs メッセージ引数
     */
    public void setMessageArgs(String[] messageArgs) {
        this.messageArgs = messageArgs;
    }

    /**
     * メッセージＩＤを取得します。
     * @return メッセージＩＤ
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * メッセージＩＤを設定します。
     * @param messageId メッセージＩＤ
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * バリデータ失敗時にスローするExceptionを取得します。
     * @return バリデータ失敗時にスローするException
     */
    public ValidatorException getException() {

        return new ValidatorException(MessageUtil.getErrorMessage(
                getMessageId(), getMessageArgs()));
    }

    /**
     * 値をストリングに変換します。
     * @param target 変換対象
     * @return ストリングに変換された値
     */
    protected String convertString(final Object target) {
        if (target == null) {
            return "";
        }
        return target.toString();
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
    
    public Object saveState(FacesContext facesContext) {
        Map map = new HashMap();
        map.put("isNullable", Boolean.valueOf(isNullable));
        map.put("isTransient", Boolean.valueOf(isTransient));
        map.put("messageId", messageId);
        map.put("messageArgs", messageArgs);
        return map;
    }

    /**
     * @see javax.faces.component.StateHolder#restoreState(javax.faces.context.FacesContext,
     *      java.lang.Object)
     */
    public void restoreState(FacesContext facesContext, Object state) {
        Map map = (Map)state;
        isNullable = ((Boolean)map.get("isNullable")).booleanValue();
        isTransient = ((Boolean)map.get("isTransient")).booleanValue();
        messageId = (String)map.get("messageId");
        messageArgs = (String[])map.get("messageArgs");

    }

    

}