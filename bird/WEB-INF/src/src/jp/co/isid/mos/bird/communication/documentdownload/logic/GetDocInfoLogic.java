/*
 * 作成日: 2006/02/10
 */
package jp.co.isid.mos.bird.communication.documentdownload.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 照会文書の取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetDocInfoLogic {
    /**
     * 照会対象の文書を取得する
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @return List  
     * @exception ApplicationException
     */
    public Map execute(String infoShu, 
                         String cateId,
                         String subCateId,
                         String title,
                         String sysDate,
                         BirdUserInfo birdUserInfo,
                         int pageNo)
                    throws ApplicationException;
}