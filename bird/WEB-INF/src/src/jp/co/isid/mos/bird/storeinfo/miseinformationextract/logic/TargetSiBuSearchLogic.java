package jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 店舗グループ（支部）の検索ロジック インターフェイス
 * @author boukoumei
 */
public interface TargetSiBuSearchLogic {

    /**
     * 店舗グループ（支部）の検索を行う
     * @param     String shukeKubu 集計区分
     * @return    List
     * @exception ApplicationException
     */
    public List execute(String shukeKubu) throws ApplicationException;
}
