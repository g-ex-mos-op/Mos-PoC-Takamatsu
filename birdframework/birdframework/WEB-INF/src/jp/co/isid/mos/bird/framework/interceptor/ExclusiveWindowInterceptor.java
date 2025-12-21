/**
 * 
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.dto.ExclusiveWindowDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 画面排他制御インターセプター
 * 
 * @author xyuchida
 */
public class ExclusiveWindowInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -1708747300280490983L;
    private static final String VIEW_ID_OPERATION_ERROR = "operation.Err";

    /**
     * 画面排他制御DTO
     */
    private ExclusiveWindowDto exclusiveWindowDto;

    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;

    /**
     * request
     */
    private HttpServletRequest request;

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        MakeSessionKey makeSessionKey = new MakeSessionKey();

        // 事後チェック対象ビューID取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        List checkViewIdList = (List)container.getComponent("exclusiveViewId");

        // 今回ビューID取得
        String viewId = selectViewId();

        // 初期処理判定
        boolean initFlag = false;
        if (getPullDownMenuDto().isClearFlg()) {
            // 初期処理フラグON
            initFlag = true;
        }

        // 事前処理チェック要否判定
        boolean isBeforehand = isProceedCheck(viewId, checkViewIdList);
        // 事前チェック 初期処理以外実施
        if(isBeforehand && !getPullDownMenuDto().isClearFlg()){
            if(checkSessionKey(makeSessionKey)){
                return VIEW_ID_OPERATION_ERROR;
            }
        }

        Object result = null;
        try {
            // 実行
            result = methodInvocation.proceed();

        } catch (ApplicationException e) {
            // 初期処理
            init(makeSessionKey,initFlag);
            throw e;
        }

        // 初期処理
        init(makeSessionKey,initFlag);
        
        // 事後チェック
        if(!isBeforehand){
            if(checkSessionKey(makeSessionKey)){
                return VIEW_ID_OPERATION_ERROR;
            }
        }

        return result;
    }
    /**
     * 事前処理チェック要否判定
     * @param viewId
     * @param checkViewIdList
     * @return
     */
    private boolean isProceedCheck(String viewId, List checkViewIdList) {
        boolean result = true;
        for (int i = 0; checkViewIdList.size() > i; i++) {
            String checkViewId = (String)checkViewIdList.get(i);
            if (viewId != null && viewId.startsWith(checkViewId)) {
                result = false;
                break;
            }
        }
        return result;
    }

    
    //初期処理
    private void init(MakeSessionKey makeSessionKey, boolean initFlag) {
        if (initFlag) {
            // SessionKey生成
            String sessionKey = makeSessionKey._makeSessionKey();
            getExclusiveWindowDto().setSessionKey(sessionKey);
            getExclusiveWindowDto().setLastSessionKey(sessionKey);
        }
    }
    /**
     * セッションキーチェック
     *
     */
    private boolean checkSessionKey(MakeSessionKey makeSessionKey) {
        // SessionKeyマッチング
        if (makeSessionKey.isValidSessionKey(
                getExclusiveWindowDto().getLastSessionKey(),
                getExclusiveWindowDto().getSessionKey())) {
            // SessionKey再生成
            String sessionKey = makeSessionKey._makeSessionKey();
            getExclusiveWindowDto().setSessionKey(sessionKey);
            getExclusiveWindowDto().setLastSessionKey(sessionKey);

        } else {
            // 権限エラー
            return true;
        }
        return false;
        
    }

    /**
     * 今回ビューID取得
     * @return 今回ビューID
     */
    private String selectViewId() {
        String[] viewIdKey = new String[] {"BIRD_PAGE_KEY", "selectedViewId", ":viewId"};
        String viewId = null;
        outer : for (Enumeration e = getRequest().getParameterNames(); e.hasMoreElements();) {
            String paramName = (String) e.nextElement();
            for (int i = 0; i < viewIdKey.length; i++) {
                if (paramName.indexOf(viewIdKey[i]) != -1) {
                    viewId = (String) getRequest().getParameter(paramName);
                    break outer;
                }
            }
        }
        return viewId;
    }

    /**
     * @return exclusiveWindowDto を戻します。
     */
    public ExclusiveWindowDto getExclusiveWindowDto() {
        return exclusiveWindowDto;
    }

    /**
     * @param exclusiveWindowDto 設定する exclusiveWindowDto。
     */
    public void setExclusiveWindowDto(ExclusiveWindowDto exclusiveWindowDto) {
        this.exclusiveWindowDto = exclusiveWindowDto;
    }

    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * @param pullDownMenuDto 設定する pullDownMenuDto。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * @return request を戻します。
     */
    private HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @param request 設定する request。
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
