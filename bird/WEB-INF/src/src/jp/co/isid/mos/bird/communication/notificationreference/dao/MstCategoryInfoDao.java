/*
 * 更新日: 2007/08/24 検索条件：公開対象月をFrom～Toで検索できるように変更
 */
package jp.co.isid.mos.bird.communication.notificationreference.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.notificationreference.entity.MstCategoryInfo;

/**
 *カテゴリ情報DAO
 * @author m.onodera
 */
public interface MstCategoryInfoDao {

    public Class BEAN = MstCategoryInfo.class;
    public static final String getCategory_ARGS = "infoShu, title, pubDate, pubDateTo, gyotaiKbn, cateId, userId, fullTextSearchFileList";


    /**
     * カテゴリ一覧の取得
     * @param String infoShu
     * @return
     */
    public List getCategory(String infoShu
                           , String title
                           , String pubDate
                           , String pubDateTo
                           , String gyotaiKbn
                           , String cateId
                           , String userId
                           , List fullTextSearchFileList);

}
