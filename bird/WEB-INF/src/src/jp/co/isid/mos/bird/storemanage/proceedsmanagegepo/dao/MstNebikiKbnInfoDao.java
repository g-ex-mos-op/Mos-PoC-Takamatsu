package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstNebikiKbnInfo;

/**
 * 値引区分情報Dao
 * @author xsong
 *
 */
public interface MstNebikiKbnInfoDao {

   /**
     * 値引区分情報エンティティクラス
     */
    public static final Class BEAN = MstNebikiKbnInfo.class;
 
    /**
     * 値引区分情報リストを取得する
     * @return List 値引区分情報リスト
     */
    public List select();
	
}
