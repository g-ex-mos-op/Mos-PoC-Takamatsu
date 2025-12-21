package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;

/**
 * モスチキン管理対象期間Dao
 * 
 * @author xkinu
 *
 */
public interface MstKanriKikanDao {   
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstKanriKikan.class;
    /**
     * 指定管理番号の管理対象期間情報を取得する時のパラメータ
     */
    public static final String select_ARGS = "ckanriNo";
    /**
     * 当年度から過去２年前の年度から未来日までの管理対象期間情報を取得する時のパラメータ
     */
    public static final String selectList_ARGS = "startDt";
    
    /**
     * 新規用で新しい管理Noの管理対象期間情報を取得する時のパラメータ
     */
    public static final String selectNewCKanriNo_ARGS = "nendo";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "title, targetFrom, targetTo, defaultFrom, defaultTo, yobiFrom, yobiTo, lastUser, lastPgm";
    /**
     * 指定管理番号の管理対象期間情報を取得する
     * 
     * @param ckanriNo　管理番号
     * @return
     */
    public List select(String ckanriNo);
    /**
     * 当年度から過去２年前の年度から未来の管理対象期間情報を取得する
     * 
     * @param startDt 検索対象開始日
     * @return
     */
    public List selectList(String startDt);
    
    /**
     * 新規用の管理対象期間情報を取得する
     * 
     * @param nendo 年度
     * @return
     */
    public List selectNewCKanriNo(String nendo);
    /**
     * 新規登録処理
     * 
     * @param entity　管理対象期間エンティティ
     * @return
     */
    public void insert(MstKanriKikan entity);
    /**
     * 更新登録処理
     * 
     * @param entity　管理対象期間エンティティ
     * @return
     */
    public void update(MstKanriKikan entity);
}
