/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.storeinfo.miseref.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 店統合マスタの検索ロジック インターフェイス
 * @author xayumi
 */
public interface SchMiseListLogic {
    /**
     * 条件画面プルダウン用、店リストを取得する。
     * @param String companyCd 会社コード
     * @param String onerCd    オーナーコード
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd) throws ApplicationException;
}