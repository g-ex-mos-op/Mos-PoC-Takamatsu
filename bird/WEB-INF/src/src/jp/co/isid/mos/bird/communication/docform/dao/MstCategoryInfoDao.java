/*
 * 作成日: 2011/02/21
 */
package jp.co.isid.mos.bird.communication.docform.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;


/**
 * カテゴリ情報
 * 
 * 作成日:2011/02/21
 * @author xkinu
 *
 */
public interface MstCategoryInfoDao {

    public static final Class BEAN = MstCategoryInfo.class;

    public static final String select_ARGS = "infoShu, sysDate, userId, userTypeCd, kobetsuFlg";

    /**
     * カテゴリの取得
     * 
     * アクセス権限有りのカテゴリのみ取得します。
     * 
     * @param String infoShu 情報種別
     * @param String sysDate 日付
     * @param String userId ユーザーID
     * @return List
     */
    public List select(String infoShu, String sysDate, String userId, String userTypeCd, boolean kobetsuFlg);
}