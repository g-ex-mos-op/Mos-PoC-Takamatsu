package jp.co.isid.mos.bird.storemanage.staffregist.dao;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaffMise;


/**
 * 編集対象スタッフ情報確認Dao
 * @author Aspac
 */
public interface TrnStaffStateDao {

    public static final Class BEAN = MstStaffMise.class;
    
    /**
     * 研修結果状況
     * ※過去に研修履歴があるかを確認する
     * @param String staffId スタッフID
     * @return int 0：受講履歴がない 1以上：受講履歴がある
     */
    public int selectKenshuState(String staffId);
    
    
    /**
     * エントリー履歴状況
     * ※過去にML受験申込履歴があるかを確認する
     * @param String staffId スタッフID
     * @return int 0：受験履歴がない 1以上：受験履歴がある
     */    
    public int selectEntryHistoryState(String staffId);
    
}