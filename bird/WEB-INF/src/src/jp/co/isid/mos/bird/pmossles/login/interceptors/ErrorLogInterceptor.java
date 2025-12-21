package jp.co.isid.mos.bird.pmossles.login.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.ThrowsInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.pmossles.login.action.LoginAction;
import jp.co.isid.mos.bird.pmossles.login.exception.PmosIllegalAccessException;

/**
 *  例外インターセプター
 *
 * 作成日:2010/12/08
 * @author xkinu
 *
 */
public class ErrorLogInterceptor extends ThrowsInterceptor {

    static final long serialVersionUID = 1L;

    private static Logger logger_ = Logger.getLogger(ErrorLogInterceptor.class);
    /**
     * エラー時アクセスログ出力処理
     * @param t
     * @param invocation
     * @return
     * @throws Throwable
     */
    public String handleThrowable(
    		Exception t
    	,	MethodInvocation invocation) throws Throwable
    {

    	if(t instanceof PmosIllegalAccessException) {
	    	//リクエスト情報ログ出力(仕様番号:SP010221)
            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
            StringBuffer buf = new StringBuffer(100);
        	// 新BIG-IP用に記述方法の変更: BIG-IP自身のIPではなく、クライアントのIPを取得する
        	// リモートIPのチェック
            HttpServletRequest hr = (HttpServletRequest) request;
            String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );
            /**
             * リクエスト情報ログ出力(仕様番号:SP010221)
             *
             * パラメータ妥当性チェック、店コード存在チェックでNGとなった場合は、下記内容をファイルに出力する
             * ユーザーエージェント
             * ＩＰアドレス
             * リクエストパラメータ値
             */
            buf.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            buf.append(" " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            //エラー内容
            buf.append(((PmosIllegalAccessException)t).getMemo() +"エラー");
            //ユーザーエージェント
            buf.append(" user-agent: [" + request.getHeader("user-agent") +"]");
            //ＩＰアドレス（ページを要求してきたクライアントのIPアドレス ）
            if("".equals(sUserRemoteIP) || sUserRemoteIP == null){
            	buf.append(" RemoteAddr: [" + request.getRemoteAddr() + "]");
            }else{
            	buf.append(" RemoteAddr: [" + sUserRemoteIP + "]");
            }

            if (request.getSession() != null ) {
    	        String pmosslesLoginRequestURL =
    	        	(String) request.getSession().getAttribute(LoginAction.PK_FIRSTURL);
    	        if(pmosslesLoginRequestURL != null) {
    		        //パラメータ値の設定
    	        	buf.append(" RequestURL: [" + pmosslesLoginRequestURL + "]");
    		        // ログ用：リクエストURLをセッションから削除します。
    	        	request.getSession().removeAttribute(LoginAction.PK_FIRSTURL);

    	        }
            }
            logger_.fatal(buf);
    	}
    	else if(t instanceof CannotExecuteWithReasonException) {
    		if("パスワードの有効期限が切れている".equals(
    				((CannotExecuteWithReasonException)t).getMessageArgs()[0])) {
    			String msg3 = ((CannotExecuteWithReasonException)t).getMessageArgs()[2];
    			((CannotExecuteWithReasonException)t).getMessageArgs()[2] = "PCから" + msg3;
    		}
    	}
    	throw t;
    }
}