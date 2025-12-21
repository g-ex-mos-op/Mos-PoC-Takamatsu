/*
 * 作成日: 2006/09/12
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic;

import java.util.Map;

/**
 * 業績管理　宅配売上推移日次情報取得
 * @author xwatanabe
 *
 */
public interface TakuhaiSuiiNipoInfoLogic {


    /**
     * 宅配売上推移日次情報を取得する。
     * @param  Map
     * @param  Map
     * @return List
     */
    public Map execute(Map argsMap, Map tenpoCntMap);
   
}
