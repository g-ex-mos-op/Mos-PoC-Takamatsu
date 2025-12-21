/*
 * 作成日: 2006/02/10
 * 更新日: 2007/08/24 検索条件：公開対象月をFrom～Toで検索できるように変更
 * 更新日: 2008/02/18 WAS高負荷対応（検索専用のentityを使用するように変更）
 */
package jp.co.isid.mos.bird.communication.notificationreference.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.notificationreference.entity.ViewTutatu;

/**
 * @author m.onodera
 * 更新日：2008/02/19 ASPAC T.Kinugawa WAS高負荷対応
 */
public interface UIViewTutatuDao {

    public static final Class BEAN = ViewTutatu.class;
    /**
     * タブ『全て』の公開対象検索結果取得SQL実行引数
     * 作成日：2008/02/19
     */
    public static final String select_ARGS = "infoShu, pubDate, pubDateTo, gyotaiKbn, title, userId, userTypeCd, sysDate, fullTextSearchFileList, kobetsuFlg, misebetsuFlg";
    /**
     * タブ『全て』の公開対象検索結果取得SQL
     *
     * 作成日：2008/02/19
     *
     * @param infoShu
     * @param pubDate
     * @param pubDateTo
     * @param gyotaiKbn
     * @param title
     * @param userId
     * @param userTypeCd
     * @param sysDate
     * @param fullTextSearchFileList
     * @param kobetsuFlg
     * @param misebetsuFlg
     * @return
     */
    public List select(String infoShu
    		, String pubDate
            , String pubDateTo
    		, String gyotaiKbn
    		, String title
    		, String userId
    		, String userTypeCd
    		, String sysDate
            , List fullTextSearchFileList
            , boolean kobetsuFlg
            , boolean misebetsuFlg);
}
