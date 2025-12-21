/*
 * 作成日: 2006/09/12
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic;

import java.util.Map;

/**
 * 業績管理　宅配売上推移月次情報取得
 * @author xwatanabe
 *
 */
public interface TakuhaiSuiiGepoInfoLogic {

    /**
     * 宅配売上推移月次情報を取得する。
     * @param  Map
     * @return Map
     */
    public Map execute(Map argsMap);
   
}
