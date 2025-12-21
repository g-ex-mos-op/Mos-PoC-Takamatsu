/*
 * 作成日: 2008/06/20
 */
package jp.co.isid.mos.bird.storemanage.claimtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.claimtotal.entity.UIClaimTotalData;

/**
 * お客様の声データDao
 * 
 * @author xnkusama
 */
public interface ClaimTotalDataDao {

    public static final Class BEAN = UIClaimTotalData.class;
    public static final String select_ARGS = "companyCd, typeCd, kikanFrom, kikanTo, taishoJoken, hyojiTaisho, userTypeCd, userId, limitKbn";
    public static final String selectZennen_ARGS = "companyCd, typeCd, kikanFrom, kikanTo, taishoJoken, hyojiTaisho, userTypeCd, userId, limitKbn";
    public static final String getHyojitaishoName_ARGS = "companyCd, taishoJoken, hyojiTaisho";
    
    /**
     * お客様の声データの取得
     * @param companyCd
     * @param typeCd
     * @param kikanFrom
     * @param kikanTo
     * @return List
     */
    public List select(String companyCd, 
                        String typeCd, 
                        String kikanFrom, 
                        String kikanTo, 
                        String taishoJoken, 
                        String hyojiTaisho,
                        String userTypeCd,
                        String userId,
                        boolean limitKbn);
    
    /**
     * お客様の声データ前年の取得
     */
    public List selectZennen(String companyCd, 
                              String typeCd, 
                              String kikanFrom, 
                              String kikanTo, 
                              String taishoJoken, 
                              String hyojiTaisho,
                              String userTypeCd,
                              String userId,
                              boolean limitKbn);

    /**
     * 表示対象名称取得
     */
    public UIClaimTotalData getHyojitaishoName(String companyCd, 
                                                String taishoJoken, 
                                                String hyojiTaisho);

}