/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public interface SearchSubCategoryLogic {

    /**
     * 情報サブカテゴリ検索
     * 
     * @param infoShu 情報種別
     * @param cateId カテゴリID
     * @return 検索結果
     */
    public List execute(String infoShu, String cateId);
}
