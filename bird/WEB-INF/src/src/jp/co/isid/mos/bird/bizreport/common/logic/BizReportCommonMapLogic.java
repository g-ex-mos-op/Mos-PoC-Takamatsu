package jp.co.isid.mos.bird.bizreport.common.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 業務管理　グループ業績画面共通ロジック インターフェース
 * @author xkinu
 *
 */
public interface BizReportCommonMapLogic extends BizReportCommonLogic {
   /**
    * 処理実行
    * @param map
    * @return
    * @throws ApplicationException
    */
    public Map execute(Map map) throws ApplicationException;

}
