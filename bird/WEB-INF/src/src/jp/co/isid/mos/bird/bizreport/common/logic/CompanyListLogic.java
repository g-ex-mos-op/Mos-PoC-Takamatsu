package jp.co.isid.mos.bird.bizreport.common.logic;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 業務管理　グループ業績画面共通ロジック インターフェース
 * @author xkinu
 *
 */
public interface CompanyListLogic extends BizReportCommonLogic {
    /** パラメーターキー：海外会社含む判断値(Boolean) */
    public static final String PK_FOREING_IN = "FOREING_IN";
    /**
     * 処理実行
     * 
     * @param map
     * @return
     * @throws ApplicationException
     */
     public List execute(Map map) throws ApplicationException;

}
