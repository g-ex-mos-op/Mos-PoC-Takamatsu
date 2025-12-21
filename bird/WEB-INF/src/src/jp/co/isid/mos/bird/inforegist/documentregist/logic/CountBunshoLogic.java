/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 文書の件数取得ロジック インターフェイス
 * @author xnkusama
 */
public interface CountBunshoLogic {
    /**
     * 既存の文書情報一覧の取得
     * @param String nengetu 対象年月
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String rCompanyCd 企業コード
     * @param String bumonCd 部門コード
     * @param String sysDate システム日付
     * @return int 文書の件数
     * @exception ApplicationException
     */
    public int countBunsho(String nengetu,
                            String cateId, 
                            String subCateId, 
                            String rCompanyCd,
                            String bumonCd,
                            String sysDate) 
                    throws ApplicationException;
}