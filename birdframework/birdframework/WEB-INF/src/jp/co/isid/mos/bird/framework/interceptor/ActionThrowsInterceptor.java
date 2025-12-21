/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.BirdMessageFormatter;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.aop.interceptors.ThrowsInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.jsf.util.MessageUtil;

/**
 * @author xyuchida
 */
public class ActionThrowsInterceptor extends ThrowsInterceptor {
    
    static final long serialVersionUID = 1L;
    /**
     * エラーコード
     */
    private static final String ERR_CANNOT_EXEC_REASON = "E0404";

    public String handleThrowable(ApplicationException ex,
            MethodInvocation invocation) throws Throwable {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, MessageUtil.getErrorMessage(ex.getMessageId(),
                ex.getMessageArgs()));
        /* 渡された例外クラスにHTML要素情報が設定されている場合 */
        //例外情報用の共通DTOにセット
        setupCommonDto(ex);
        return null;
    }

    /**
     * 排他制御にひっっかた場合のExceptionをＢＩＲＤ用エラーメッセージに変換
     * @param ex 排他制御にひっっかた場合のException
     * @param invocation 
     * @return
     * @throws Throwable
     */
    public String handleThrowable(NotSingleRowUpdatedRuntimeException ex,
            MethodInvocation invocation) throws Throwable {

        FacesContext context = FacesContext.getCurrentInstance();
        context
                .addMessage(null, new FacesMessage(BirdMessageFormatter
                        .getMessage(ERR_CANNOT_EXEC_REASON,
                                new String[] { "このデータは既に更新されている", "更新"})));
        /* 渡された例外クラスにHTML要素情報が設定されている場合 */
        //例外情報用の共通DTOにセット
        return null;

    }

    /**
     * 例外情報用DTO関連処理
     * @param request リクエスト
     * @param ex 渡された例外クラス
     */
    private void setupCommonDto(ApplicationException ex) {

        S2Container container = SingletonS2ContainerFactory.getContainer();

        ErrHtmlElementDto elementDto = (ErrHtmlElementDto) container
                .getComponent(ErrHtmlElementDto.class);
        if (elementDto != null) {
        	elementDto.setExceptionFlg(true);
            if (ex.getHtmlElementName() != null) {
                //エラー対象HTML要素情報設定
                elementDto.setHtmlElementName(ex.getHtmlElementName());
                elementDto.setHtmlElementIndex(ex.getHtmlElementIndex());
                //Exception発生フラグ設定
                elementDto.setExceptionFlg(true);
            }
            if (ex.getHtmlTabName() != null) {
                //エラー対象HTMLタブ情報設定
                elementDto.setHtmlTabName(ex.getHtmlTabName());
                //Exception発生フラグ設定
                elementDto.setExceptionFlg(true);
            }
        }
    }
}