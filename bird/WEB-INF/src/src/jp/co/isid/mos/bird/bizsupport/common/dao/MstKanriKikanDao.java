package jp.co.isid.mos.bird.bizsupport.common.dao;

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
     * 指定管理番号の管理対象期間情報を取得する時のパラメータ
     */
    public static final String selectKikanList_ARGS = "sysDate";
    /**
     * 全管理対象期間情報を取得する
     * 
     * @param startDt 検索対象開始日
     * @return
     */
    public List selectAllList();
    
    /**
     * 指定管理番号の管理対象期間情報を取得する
     * 
     * @param ckanriNo　管理番号
     * @return
     */
    public List select(String ckanriNo);
    /**
     * 指定システム日付象期間情報を取得する
     * 
     * @param sysDate
     * @return
     */
    public List selectKikanList(String sysDate);
}
