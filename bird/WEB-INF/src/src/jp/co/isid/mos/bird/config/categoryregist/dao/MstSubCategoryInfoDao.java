/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.entity.MstSubCategoryInfo;

/**
 * @author xyuchida
 *
 */
public interface MstSubCategoryInfoDao {

    public static final Class BEAN = MstSubCategoryInfo.class;

    public static final String getSubCategory_ARGS = "infoShu, cateId";

    public static final String getMaxNumber_ARGS = "infoShu, cateId";
    public static final String getMaxNumber_SQL
            = "select decimal(max(BM17.SUB_CATE_ID)) from BM17SBCT as BM17 where BM17.INFO_SHU = /*infoShu*/'03' and BM17.CATE_ID = /*cateId*/'01'";

    public static final String updateSubCategory_PERSISTENT_PROPS = "newCateName, sortKey, lastUser, lastPgm";

    /**
     * サブカテゴリ一覧取得
     * 
     * @param infoShu 情報種別
     * @param cateId カテゴリID
     * @return サブカテゴリ一覧
     */
    public List getSubCategory(String infoShu, String cateId);

    /**
     * サブカテゴリID最大値取得
     * 
     * @param infoShu 情報種別
     * @param cateId カテゴリID
     * @return サブカテゴリID最大値
     */
    public int getMaxNumber(String infoShu, String cateId);

    /**
     * サブカテゴリ登録
     * 
     * @param mstSubCategoryInfo 情報サブカテゴリエンティティ
     * @return 登録件数
     */
    public int insertSubCategory(MstSubCategoryInfo mstSubCategoryInfo);

    /**
     * サブカテゴリ更新
     * 
     * @param mstSubCategoryInfo 情報サブカテゴリエンティティ
     * @return 更新件数
     */
    public int updateSubCategory(MstSubCategoryInfo mstSubCategoryInfo);

    /**
     * サブカテゴリ削除
     * 
     * @param mstSubCategoryInfo 情報サブカテゴリエンティティ
     * @return 削除件数
     */
    public int deleteSubCategory(MstSubCategoryInfo mstSubCategoryInfo);
}
