package jp.co.isid.mos.bird.bizreport.campsuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campsuiiref.entity.UISuii;


/**
 * キャンペーン売上推移表Dao
 * @author xnkusama
 *
 */
public interface UISuiiDao {

    public static final Class BEAN = UISuii.class;
    
    public static final String select_ARGS = "userId, userTypeCd, campId, totaledKbn, menuCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, zenDataShubetu, kikanFrom, kikanTo, sysDate, companyCd, limitFlg";
    public static final String selectOner_ARGS = "userId, campId, totaledKbn, menuCd, hyojiTaisho, zenDataShubetu, kikanFrom, kikanTo, sysDate, companyCd, onerCd";
    
    /**
     * メニュー検索
     * @param userId
     * @param userTypeCd
     * @param campId
     * @param totaledKbn
     * @param menuCd
     * @param tenpoShu
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param zenDataShubetu
     * @param kikanFrom
     * @param kikanTo
     * @param companyCd
     * @param limitFlg
     * @return
     */
    public List select (String userId, 
                         String userTypeCd, 
                         String campId, 
                         String totaledKbn, 
                         String menuCd, 
                         String tenpoShubetu,
                         String taishoJoken, 
                         String hyojiTaisho,
                         String blockCd, 
                         String zenDataShubetu,
                         String kikanFrom, 
                         String kikanTo, 
                         String sysDate, 
                         String companyCd,
                         boolean limitFlg);
    

    /**
     * メニュー検索 オーナー用
     * @param userId
     * @param campId
     * @param totaledKbn
     * @param menuCd
     * @param hyojiTaisho
     * @param zenDataShubetu
     * @param kikanFrom
     * @param kikanTo
     * @param sysDate
     * @param companyCd
     * @return
     */
    public List selectOner (String userId, 
                             String campId, 
                             String totaledKbn, 
                             String menuCd, 
                             String hyojiTaisho, 
                             String zenDataShubetu, 
                             String kikanFrom, 
                             String kikanTo, 
                             String sysDate, 
                             String companyCd,
                             String onerCd);
    
}