/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.entity.MstCategoryInfo;

/**
 * @author xyuchida
 *
 */
public interface MstCategoryInfoDao {

    public static final Class BEAN = MstCategoryInfo.class;

    public static final String getMaxNumber_SQL
            = "select decimal(max(BM02.CATE_ID)) from BM02IFCT as BM02 where BM02.INFO_SHU = /*infoShu*/'01'";

    public static final String updateCategory_PERSISTENT_PROPS = "newCateName, sortKey, lastUser, lastPgm";

    /**
     * カテゴリ一覧取得
     * 
     * @param infoShu 情報種別
     * @return カテゴリ一覧
     */
    public List getCategory(String infoShu);

    /**
     * カテゴリID最大値取得
     * 
     * @param infoShu 情報種別
     * @return カテゴリID最大値
     */
    public int getMaxNumber(String infoShu);

    /**
     * カテゴリ登録
     * 
     * @param mstCategoryInfo 情報カテゴリエンティティ
     * @return 登録件数
     */
    public int insertCategory(MstCategoryInfo mstCategoryInfo);

    /**
     * カテゴリ更新
     * 
     * @param mstCategoryInfo 情報カテゴリエンティティ
     * @return 更新件数
     */
    public int updateCategory(MstCategoryInfo mstCategoryInfo);

    /**
     * カテゴリ削除
     * 
     * @param mstCategoryInfo 情報カテゴリエンティティ
     * @return 削除件数
     */
    public int deleteCategory(MstCategoryInfo mstCategoryInfo);
}
