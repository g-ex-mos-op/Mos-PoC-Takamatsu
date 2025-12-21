package jp.co.isid.mos.bird.bizreport.campkako.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.entity.UISuii;

/**
 * キャンペーン売上推移表Dao
 * @author xnkusama
 *
 */
public interface UISuiiDao {

    public static final Class BEAN = UISuii.class;
    
    public static final String select_ARGS = "userId, campId, totaledKbn, menuCd, taishoJoken, hyojiTaisho, blockCd, kikanFrom, kikanTo, companyCd, limitFlg";
    public static final String selectOner_ARGS = "userId, campId, totaledKbn, menuCd, hyojiTaisho, kikanFrom, kikanTo, companyCd, onerCd";
    
    /**
     * 推移データ検索
     * @param userId
     * @param campId
     * @param totaledKbn
     * @param menuCd
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param kikanFrom
     * @param kikanTo
     * @param companyCd
     * @param limitFlg
     * @return
     */
    public List select (String userId, 
                         String campId, 
                         String totaledKbn, 
                         String menuCd, 
                         String taishoJoken, 
                         String hyojiTaisho,
                         String blockCd, 
                         String kikanFrom, 
                         String kikanTo, 
                         String companyCd,
                         boolean limitFlg);
    

    /**
     * 推移データ検索（本部以外）
     * @param userId
     * @param campId
     * @param totaledKbn
     * @param menuCd
     * @param hyojiTaisho
     * @param kikanFrom
     * @param kikanTo
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List selectOner (String userId, 
                             String campId, 
                             String totaledKbn, 
                             String menuCd, 
                             String hyojiTaisho, 
                             String kikanFrom, 
                             String kikanTo, 
                             String companyCd,
                             String onerCd);
    
}