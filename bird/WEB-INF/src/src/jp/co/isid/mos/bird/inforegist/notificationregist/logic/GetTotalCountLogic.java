/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.logic;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * 文書の件数取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetTotalCountLogic {
    /**
     * 既存の通達情報一覧の件数取得
     * 
     * @param nengetu 対象年月
     * @param cateId  カテゴリID
     * @param kanriNo 管理番号
     * @param mstUser ログインユーザー情報エンティティ
     * @return 通達の件数
     */
    public int countTutatu(String nengetu,
                            String cateId, 
                            String kanriNo, 
                            MstUser mstUser);
}