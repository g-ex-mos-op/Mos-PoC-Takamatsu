/*
 * 作成日: 2006/02/07
 *
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * @author xytamura
 *
 */
public interface GetKanrenBunshoInfoLogic {
	/**
     * 関連文書の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @return 関連文書情報
     */
    public List execute(String infoShu, String regDate, String seq);
    /**
     * 関連文書の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @return 関連文書情報
     */
    public List execute(String infoShu
    		, String regDate, String seq
    		, BirdUserInfo birdUserInfo, String sysDate);
    /**
     * 関連文書取得処理
     * 
     * @param userId ログインユーザーID
     * @param infoShu[必須] 情報種別
     * @param regDate       パラメータ情報種別の登録日
     * @param seq 　        パラメータ情報種別のシーケンス番号
     * @param pubDate 　　　文書フォーム用有効公開日
     * @param pubDateTutatu 通達用有効公開日
     * @return
     */ 
    public List execute(String userId
    		, String infoShu, String regDate, String seq
    		, String pubDate, String tutatuPubDate);
    /**
     * 関連文書の取得
     * 
     * @param userId[必須]        ログインユーザーID
     * @param infoShu[必須]       情報種別
     * @param pubDate[必須]　　　 文書フォーム用有効公開日
     * @param pubDateTutatu[必須] 通達用有効公開日
     * @param pubDtFrom     パラメータ情報種別の開始公開日
     * @param pubDtTo       パラメータ情報種別の終了公開日
     * @param title               パラメータ情報種別のタイトル
     * @param gyotaiKbn     パラメータ情報種別の業態区分
     * @param cateId        パラメータ情報種別のカテゴリー
     * @param subCateId     パラメータ情報種別のサブカテゴリー
     * @param searchEnginFileList パラメータ情報種別の検索エンジン結果(登録日+シーケンス番号の文字列)
     * @return
     */
    public List execute(String userId
   		, String infoShu
		, String pubDate, String pubDateTutatu
		, String pubDtFrom, String pubDtTo
		, String title, String gyotaiKbn
		, String cateId, String subCateId
		, List searchEnginFileList);
	/**
	 *文書の公開対象チェック
	 */    
	public boolean checkBunshoAccess(UIDocSearch entity, BirdUserInfo birdUserInfo,String sysDate);
}