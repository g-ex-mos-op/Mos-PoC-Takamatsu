/*
 * 作成日: 2006/07/27
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

/**
 * マスターライセンス能力チェックカテゴリー情報取得ロジック 
 * 
 * @author xyuchida
 */
public interface GetAbilityCheckCategoryInfoLogic {

    /**
     * カテゴリー情報取得
     * 
     * @return カテゴリー情報リスト
     */
    public List execute();
}
