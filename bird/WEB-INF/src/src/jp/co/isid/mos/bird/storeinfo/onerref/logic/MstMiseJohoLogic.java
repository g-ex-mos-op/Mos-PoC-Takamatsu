/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic;


import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstMise;

/**
 * 店統合マスタの検索ロジック インターフェイス
 * @author xayumi
 */
public interface MstMiseJohoLogic {
    /**
     * 店コードからオーナーを取得する。
     * @param String companyCd 会社コード
     * @param String miseCd    店コード
     * @return List  
     * @exception ApplicationException
     */
    public MstMise execute(String companyCd, String miseCd) throws ApplicationException;
}