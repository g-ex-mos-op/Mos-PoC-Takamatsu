/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic;


import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * オーナー統合マスタの検索ロジック インターフェイス
 * @author xayumi
 */
public interface MstOnerNameLogic {
    /**
     * オーナー名称を取得する。
     * @param String companyCd 会社コード
     * @param String onerCd    オーナーコード
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd) throws ApplicationException;
}