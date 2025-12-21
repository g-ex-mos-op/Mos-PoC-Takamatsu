/*
 * 作成日: 2006/01/24
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 文書照会順序を取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetBunshoSortLogic {
    /**
     * 文書照会順序を取得する
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @return List 文書情報
     * @exception ApplicationException
     */
    public List getBunshoSort(String nengetu,
                               String cateId, 
                               String subCateId, 
                               String rCompanyCd,
                               String bumonCd) 
                    throws ApplicationException;
}