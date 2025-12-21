package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.UIMCStatusInfo;

/**
 * モスチキン管理対象期間Dao
 * 
 * @author xkinu
 *
 */
public interface UIMCStatusInfoDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = UIMCStatusInfo.class;
    
    /**
     * 指定管理番号の管理対象期間情報を取得する時のパラメータ
     */
//modify 2019/08/12 USI張 begin
//    public static final String select_ARGS = "companyCd, ckanriNo, miseCd, lastDate, thisDate, nextDate";
    public static final String select_ARGS = "companyCd, ckanriNo, miseCd, shokuCd, lastDate, thisDate, nextDate";
//modify 2019/08/12 USI張 end
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS  = "amtCase, amtBara, amtBl, lastUser, lastPgm";
    /**
     * 指定管理番号の管理対象期間情報を取得する
     * 
     * @param companyCd
     * @param ckanriNo
     * @param miseCd
     * @param lastDate
     * @param thisDate
     * @param nextDate
     * @return
     */
//modify 2019/08/12 USI張 begin
//    public List select(String companyCd, String ckanriNo, String miseCd, String lastDate, String thisDate, String nextDate);
    public List select(String companyCd, String ckanriNo, String miseCd, String shokuCd, String lastDate, String thisDate, String nextDate);
//modify 2019/08/12 USI張 end
    /**
     * 新規登録処理
     * 
     * @param entity　管理対象期間エンティティ
     * @return
     */
    public void insert(UIMCStatusInfo entity);
    /**
     * 更新登録処理
     * 
     * @param entity　管理対象期間エンティティ
     * @return
     */
    public void update(UIMCStatusInfo entity);
}
