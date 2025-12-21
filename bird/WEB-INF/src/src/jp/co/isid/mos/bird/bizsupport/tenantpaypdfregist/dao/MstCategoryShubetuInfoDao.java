/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.MstCategoryShubetuInfo;

/**
 * カテゴリー種別情報Dao
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface MstCategoryShubetuInfoDao {
    /**
     * 情報格納エンティティクラス
     */
    public static final Class BEAN = MstCategoryShubetuInfo.class;
    
    /**
     * カテゴリー種別情報取得時のパラメータ
     */
    public static final String select_ARGS = "category, shu";
    /**
     * カテゴリー種別情報取得
     * 
     * @param category
     * @param shu
     * @return
     */
    public List select(String category, String shu);
}
