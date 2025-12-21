package jp.co.isid.mos.bird.bizreport.posreportregist.dao;

import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIRealtimeStInfo;

/**
 * リアルタイムステータスDAO
 * @author Aspac
 *
 */
public interface UIRealtimeStInfoDao {

    public static final Class BEAN = UIRealtimeStInfo.class;
    public static final String delete_ARGS = "entity";
    
    
    /**
     * 更新処理
     * @param
     * @return
     */
    public void insert(UIRealtimeStInfo uIRealtimeStInfo);
    
    
    /**
     * 削除処理
     * @param
     * @return
     */
    public void delete(UIRealtimeStInfo entity);

}

