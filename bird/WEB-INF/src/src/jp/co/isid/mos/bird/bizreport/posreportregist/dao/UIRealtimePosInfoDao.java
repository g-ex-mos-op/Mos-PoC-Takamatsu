package jp.co.isid.mos.bird.bizreport.posreportregist.dao;


import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIRealtimePosInfo;


/**
 * リアルタイム集信マスタDAO
 * @author Aspac
 *
 */
public interface UIRealtimePosInfoDao {

    public static final Class BEAN = UIRealtimePosInfo.class;
   
    public static final String getRealtimeBaseSeqNo_ARGS = "haisSijiDt";
    public static final String delete_ARGS = "entity";
    
    /**
     * シーケンスNo取得
     * @param haisSijiDt 配信指示日
     * @return
     */
    public String getRealtimeBaseSeqNo(String haisSijiDt);

    
    /**
     * 更新処理
     * @param
     * @return
     */
    public void insert(UIRealtimePosInfo uIRealtimePosInfo);
    
    
    /**
     * 削除処理
     * @param
     * @return
     */
    public void delete(UIRealtimePosInfo entity);
    
}

