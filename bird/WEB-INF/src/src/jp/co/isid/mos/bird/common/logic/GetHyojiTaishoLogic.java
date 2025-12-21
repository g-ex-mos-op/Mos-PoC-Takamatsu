package jp.co.isid.mos.bird.common.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * 業務管理　表示対象項目ロジック インターフェース
 * @author xnkusama
 *
 */
public interface GetHyojiTaishoLogic {
    /**
     * 処理実行
     * @param map
     * @return
     * @throws ApplicationException
     */
     public Map execute(Map map) throws ApplicationException;
}
