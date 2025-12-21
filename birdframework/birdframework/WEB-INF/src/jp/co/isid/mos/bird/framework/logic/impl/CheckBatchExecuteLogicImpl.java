/*
 * 作成日: 2005/12/28
 *
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import jp.co.isid.mos.bird.framework.dao.CtlOnlineUsedTblDao;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.logic.CheckBatchExecuteLogic;

/**
 * バッチ排他チェック
 * @author xytamura
 */
public class CheckBatchExecuteLogicImpl implements CheckBatchExecuteLogic {

    /**
     * テーブルのステータスを取得する。
     */
    private static final Class CLASS_DAO = CtlOnlineUsedTblDao.class;

    /**
     * バッチ排他チェックを実行する
     * @param id アクションＩＤまたは、ロジックＩＤ
     */
    public void execute(final String id) {
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        CtlOnlineUsedTblDao dao = (CtlOnlineUsedTblDao) s2Con
                .getComponent(CLASS_DAO);
        List result = dao.getState(id);
        if(result.size() != 0){
            throw new BatchProcessingException();
        }
    }
    
}
