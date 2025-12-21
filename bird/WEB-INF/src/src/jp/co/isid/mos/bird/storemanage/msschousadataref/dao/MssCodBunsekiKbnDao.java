/*
 * 作成日: 2006/08/06
 */
package jp.co.isid.mos.bird.storemanage.msschousadataref.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.CodBunsekiName;
/**
 * 分析ネーム取得
 */
public interface MssCodBunsekiKbnDao {
    
    public static final Class BEAN = CodBunsekiName.class;
    
    public static final String selectMise_ARGS = "";

    /**
     * オーナーユーザー保有店舗の検索
     * @param user_id
     * @return List
     */
    public List selectBunsekiName();

    

}