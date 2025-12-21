/*
 * 
 * 作成日: 2009/12/09
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIViewKanrenBunsho;

/**
 * 関連文書情報取得Dao
 * 
 * @author xkinu
 */
public interface UIViewKanrenBunshoDao {

    public Class BEAN = UIViewKanrenBunsho.class;

    public static final String select_ARGS = "sysDate, userId, infoShu, regDate, seq" +
    		", pubDate, pubDateTutatu, pubDtFrom, pubDtTo" +
    		", title, gyotaiKbn, cateId, subCateId, searchEnginFileList";
    
    /**
     * 照会用関連文書の取得
     * 
     * 対象文書全体の関連文書を取得できます。
     * 
     * @param userId ログインユーザーID
     * @param infoShu[必須] 情報種別
     * @param regDate       パラメータ情報種別の登録日
     * @param seq 　        パラメータ情報種別のシーケンス番号
     * @param pubDate 　　　文書フォーム用有効公開日
     * @param pubDateTutatu 通達用有効公開日
     * @param pubDtFrom     パラメータ情報種別の開始公開日
     * @param pubDtTo       パラメータ情報種別の終了公開日
     * @param title         パラメータ情報種別のタイトル
     * @param gyotaiKbn     パラメータ情報種別の業態区分
     * @param cateId        パラメータ情報種別のカテゴリー
     * @param subCateId     パラメータ情報種別のサブカテゴリー
     * @param searchEnginFileList パラメータ情報種別の検索エンジン結果(登録日+シーケンス番号の文字列)
     * 
     * @return
     */
    public List select(String sysDate, String userId, String infoShu, String regDate, String seq
    		, String pubDate, String pubDateTutatu
    		, String pubDtFrom, String pubDtTo
    		, String title, String gyotaiKbn
    		, String cateId, String subCateId
    		, List searchEnginFileList);

}
