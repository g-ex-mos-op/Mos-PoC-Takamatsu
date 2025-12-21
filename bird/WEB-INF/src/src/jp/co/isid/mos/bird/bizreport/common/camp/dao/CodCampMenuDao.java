package jp.co.isid.mos.bird.bizreport.common.camp.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.CodCampMenu;


/**
 * メニューDao
 * @author xkinu
 *
 */
public interface CodCampMenuDao {

    public static final Class BEAN = CodCampMenu.class;
    
    public static final String selectMenu_ARGS = "campId";
    public static final String selectSumMenu_ARGS = "campId";
    public static final String selectSumMenuKako_ARGS = "campId";
    
    /**
     * メニュー検索
     * @param campId
     * @return
     */
    public List selectMenu( String campId);
    /**
     * 集約メニュー検索
     * @param campId
     * @return
     */
    public List selectSumMenu( String campId);
    /**
     * 集約メニュー検索(過去売上用）
     * @param campId
     * @return
     */
    public List selectSumMenuKako(String campId);
}