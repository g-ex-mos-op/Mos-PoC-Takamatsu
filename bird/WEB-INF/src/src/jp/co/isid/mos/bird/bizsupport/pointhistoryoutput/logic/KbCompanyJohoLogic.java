/**
 * 作成日: 2017/04/01
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 株式報酬制度の会社の検索ロジック インターフェイス
 * @author Yuichi Tamura(ISID-AO)
 */
public interface KbCompanyJohoLogic {
    /**
     * 株式報酬制度の会社の検索を行う
     * @return    会社リスト
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException;
}