/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * フォーム情報の取得ロジック インターフェイス
 * @author xytamura
 */
public interface GetFormInfoLogic {
    
//    /**
//     * 既存の文書情報一覧の取得
//     * @param String nengetu 対象年月
//     * @param String cateId カテゴリID
//     * @param String subCateId サブカテゴリID
//     * @param int page ページ
//     * @param String rCompanyCd 企業コード
//     * @param String bumonCd 部門コード
//     * @return List 文書一覧情報
//     * @exception ApplicationException
//     */
//    public List execute(String nengetu,
//                           String cateId, 
    //                           String subCateId,
    //                           int page,
//                           String rCompanyCd,
//                           String bumonCd) 
//                    throws ApplicationException;

    /**
     * フォーム情報の取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String userId ユーザーID
     * @param int page ページ
     * @param String sysDate システム日付
     * @return List フォーム情報
     * @exception ApplicationException
     */
    public List execute(String nengetu,
                           String cateId, 
                           String subCateId, 
                           String userId,
                           int page,
                           String sysDate) 
                    throws ApplicationException;

    
}