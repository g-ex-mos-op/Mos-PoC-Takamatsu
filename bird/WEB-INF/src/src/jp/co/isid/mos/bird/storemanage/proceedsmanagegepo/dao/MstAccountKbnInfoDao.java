package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstAccountKbnInfo;

/**
 * 会計区分情報Daoクラス
 * 
 * @author xjung
 */
public interface MstAccountKbnInfoDao {

    /**
     * 会計区分情報エンティティクラス
     */
    public static final Class BEAN = MstAccountKbnInfo.class;
 
    /**
     * 会計区分情報リストを取得する
     * @return List 会計区分情報リスト
     */
    public List select();
}