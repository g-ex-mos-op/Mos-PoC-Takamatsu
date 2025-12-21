/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 関連文書の取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetBunshoInfoLogic {
    /**
     * 既存の文書情報一覧の取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param int page ページ
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @param String sysDate システム日付
     * @return List 文書一覧情報
     * @exception ApplicationException
     */
    public List getBunsho(String nengetu,
                           String cateId, 
                           String subCateId, 
                           int page,
                           String rCompanyCd,
                           String bumonCd,
                           String sysDate) 
                    throws ApplicationException;
}