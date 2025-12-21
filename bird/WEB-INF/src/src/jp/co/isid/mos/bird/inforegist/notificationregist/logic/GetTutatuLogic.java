/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * 関連文書の取得ロジック インターフェイス
 * @author m.onodera
 */
public interface GetTutatuLogic {
    /**
     * 既存の文書情報一覧の取得
     * 
     * @param nengetu 対象年月
     * @param cateId  カテゴリID
     * @param kanriNo 管理番号
     * @param page    ページ
     * @param mstUser ログインユーザー情報エンティティ
     * @return
     */
    public List getTutatu(String nengetu,
                           String cateId, 
                           String kanriNo, 
                           int page,
                           MstUser mstUser);
}