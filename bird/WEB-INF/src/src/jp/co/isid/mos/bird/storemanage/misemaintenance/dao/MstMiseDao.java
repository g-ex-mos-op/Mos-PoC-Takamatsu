/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMise;

/**
 * 店統合マスタ
 * @author xnkusama
 */
public interface MstMiseDao {

    public static final Class BEAN = MstMise.class;
    public static final String selectMiseMst_ARGS = "companyCd, miseCd";

    public static final String updateBunsho_PERSISTENT_PROPS 
                                        = "openTmWkd, closeTmWkd, openTmSat, closeTmSat, openTmHol, closeTmHol, "
                                        + "openTmHlb, closeTmHlb, parkKei, parkOnlyIn, parkOnlyOut, parkCommIn, parkCommOu, "
                                        + "prjInvest, prjUriage, accessLine, accessStation, accessDist, accessTime, "
                                        + "accessWayName, accessWayNote, "
                                        + "seat1F, seat1FSmokingIn, seat1FNonSmokingIn, seat1FSmokingOut, seat1FNonSmokingOut, "
                                        + "seat2F, seat2FSmokingIn, seat2FNonSmokingIn, seat2FSmokingOut, seat2FNonSmokingOut, "
                                        + "seat3F, seat3FSmokingIn, seat3FNonSmokingIn, seat3FSmokingOut, seat3FNonSmokingOut, "
                                        + "seatUnder, seatUnderSmokingIn, seatUnderNonSmokingIn, seatUnderSmokingOut, seatUnderNonSmokingOut, "
                                        + "seatCommon, seatCommonSmokingIn, seatCommonNonSmokingIn, seatCommonSmokingOut, seatCommonNonSmokingOut, "
                                        + "seatOther, seatOtherSmokingIn, seatOtherNonSmokingIn, seatOtherSmokingOut, seatOtherNonSmokingOut, "
                                        + "keiyakuNotes, tentai, tentaiStartDt, tentaiEndDt, tentaiInfo, "
                                        + "lastUser, lastPgm, lastTmsp";

    /**
     * 店統合マスタの検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectMiseMst(String companyCd, String miseCd);
    
    /**
     * 店総合マスタの更新
     * @param entity
     * @return
     */
    public int updateMiseMst(MstMise entity);
}            
