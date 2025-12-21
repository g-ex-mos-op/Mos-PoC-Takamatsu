/*
 * 作成日: 2006/01/24
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * フォームダウンロード順序を取得ロジック インターフェイス
 * @author xytamura
 */
public interface GetFormSortLogic {

    //    /**
    //     * フォームダウンロード照会順序を取得する
    //     * @param String nengetu 対象年月
    //     * @param String cateId カテゴリID
    //     * @param String subCateId サブカテゴリID
    //     * @param String rCompanyCd 企業コード
    //     * @param String bumonCd 部門コード
    //     * @return Listフォーム情報
    //     * @exception ApplicationException
    //     */
    //    public List execute(String nengetu,
    //                               String cateId,
    //                               String subCateId,
    //                               String rCompanyCd,
    //                               String bumonCd)
    //                    throws ApplicationException;

    /**
     * フォームダウンロード順序を取得する
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @return List フォーム情報
     * @exception ApplicationException
     */
    public List execute(String nengetu, String cateId, String subCateId)
            throws ApplicationException;
}