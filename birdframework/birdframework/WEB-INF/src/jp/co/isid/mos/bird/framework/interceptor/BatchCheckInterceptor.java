/*
 * 作成日: 2005/12/28
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.lang.reflect.Field;

import jp.co.isid.mos.bird.framework.logic.CheckBatchExecuteLogic;
import jp.co.isid.mos.bird.framework.logic.impl.CheckBatchExecuteLogicImpl;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ロジックＩＤやアクションＩＤをキーにバッチ排他チェックを行うインターセプター
 * ロジック、アクションに設定します。
 * ■使い方
 * ①各画面のdiconファイルで"jp/co/isid/mos/bird/framework/dicon/CheckBatchExecute.dicon"をインクルード
 * ②バッチ排他チェックを行いたいロジックやアクションに「batchCheckInterceptor」をアスペクト
 * ③ロジックＩＤやアクションＩＤを必ず設定する
 * @author xytamura
 */
public class BatchCheckInterceptor extends AbstractInterceptor {

    /**
     * バッチ排他
     */
    private static final Class CLASS_LOGIC = CheckBatchExecuteLogicImpl.class;

    
    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
       
        String logicId = getLogicId(invocation);
        String actionId = getActionId(invocation);
        String param = "";
        //アクションＩＤまたは、ロジックＩＤをパラメータに設定
        if(logicId.trim().length() != 0){
            param = logicId;
        }else{
            param = actionId;
        }
        if(param != null && param.trim().length() != 0){
            S2Container s2Con = SingletonS2ContainerFactory.getContainer();
            CheckBatchExecuteLogic logic = (CheckBatchExecuteLogic) s2Con.getComponent(CLASS_LOGIC);
            logic.execute(param);
        }        
        Object ret = invocation.proceed();
        
        return ret;
    }
    
    /**
     * アクションＩＤを取得します。
     * @param invocation 処理対象
     * @return アクションＩＤ
     * @throws Exception エラー発生
     */
    private String getActionId(MethodInvocation invocation) throws Exception{
        Field field = null;
        Field[] fields = getTargetClass(invocation).getFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(invocation.getMethod().getName() + "_ACTION_ID")) {
                field = getTargetClass(invocation).getField(
                        invocation.getMethod().getName() + "_ACTION_ID");
                break;
            }
        }
        if(field == null){
            return "";
        }
        return (String)field.get(null);
    }

    /**
     * ロジックＩＤを取得します。
     * @param invocation 処理対象
     * @return ロジックＩＤ
     * @throws Exception エラー発生
     */
    private String getLogicId(MethodInvocation invocation) throws Exception{

        Field field = null;
        Field[] fields = getTargetClass(invocation).getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("LOGIC_ID")) {
                field = fields[i];
                field.setAccessible(true);
                break;
            }
        }
        if(field == null){

            return "";
        }
        return (String)field.get(null);
    }

    
}


