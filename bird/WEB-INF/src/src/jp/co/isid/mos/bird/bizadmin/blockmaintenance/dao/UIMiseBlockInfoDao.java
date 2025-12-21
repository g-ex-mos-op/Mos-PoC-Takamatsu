/*
 * 作成日: 2007/04/18
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;


/**
 * 店舗ブロック設定情報Daoクラス
 * (UIMiseBlockInfoDao)
 * 
 * @author xkinu
 */
public interface UIMiseBlockInfoDao {

    public static final Class BEAN = UIMiseBlockInfo.class;
    /**
     * 検索時のパラメータ
     */
    public static final String select_ARGS = "companyCd, sibuCd, sysDate";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "sibuCd, blockCd, lastUser, lastPgm";
    
    /**
     * 店舗ブロック設定情報の取得
     * 
     * @param companyCd
     * @param sibuCd
     * @param sysDate
     * @return
     */
    public List select(String companyCd, String sibuCd, String sysDate);
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public void insert(UIMiseBlockInfo entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public void update(UIMiseBlockInfo entity);
    
}