/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * フォームの件数取得ロジック インターフェイス
 * @author xytamura
 */
public interface CountFormLogic {

    //    /**
    //     * 既存の文書情報一覧の取得
    //     * @param String nengetu 対象年月
    //     * @param String cateId カテゴリID
    //     * @param String subCateId サブカテゴリID
    //     * @param String rCompanyCd 企業コード
    //     * @param String bumonCd 部門コード
    //     * @return int 文書の件数
    //     * @exception ApplicationException
    //     */
    //    public int execute(String nengetu,
    //                            String cateId,
    //                            String subCateId,
    //                            String rCompanyCd,
    //                            String bumonCd)
    //                    throws ApplicationException;

    /**
     * 既存のフォーム情報一覧の取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String userId ユーザーID
     * @param String sysDate システム日付
     * @return int フォームの件数
     * @exception ApplicationException
     */
    public int execute(String nengetu, String cateId, String subCateId, String userId, String sysDate)
            throws ApplicationException;
}