/*
 * 作成日: 2006/02/01
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
/**
 * 
 * 権限未設定BIRDメニュー・ロールの検索のロジックインターフェース
 * @author xkhata
 */
public interface SearchDataLogic {
    /**
     * 権限未設定BIRDメニュー・ロールの検索,取得
     * @return List
     * @exception ApplicationException
     */
	public Map execute(Map listMap) throws ApplicationException; 
}
