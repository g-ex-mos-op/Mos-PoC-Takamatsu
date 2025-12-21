/*
 * ì¬“ú: 200/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouMise;

/**
 *@SV’S“–“X•Üî•ñDao
 * 
 * @author xnkusama
 */
public interface MstTantouMiseDao {

    public static final Class BEAN = MstTantouMise.class;

    public static final String getTantouMise_ARGS = "companyCd, sibuCd, sysDate";
    public static final String checkExist_ARGS = "companyCd, miseCd";
    public static final String insertMstTantouMise_ARGS = "mstTantouMise";
    public static final String updateMstTantouMise_ARGS = "mstTantouMise";
    
    /**
     * “o˜^î•ñ‚Ìæ“¾
     * @param String ‰ïĞƒR[ƒh
     * @param String x•”ƒR[ƒh
     * @param String ƒVƒXƒeƒ€“ú•t
     * @return List ’S“–“X•Üî•ñ
     */
    public List getTantouMise(String companyCd, String sibuCd, String sysDate);

    /**
     * “o˜^î•ñ‚Ìæ“¾
     * @param String ‰ïĞƒR[ƒh
     * @param String “XƒR[ƒh
     * @return MstTantouMise
     */
    public MstTantouMise checkExist(String companyCd, String miseCd);
    
    /**
     * ’S“–“X•Ü“o˜^
     * @param mstTantouMise
     */
    public void insertMstTantouMise(MstTantouMise mstTantouMise);

    /**
     * ’S“–“X•ÜXV
     * @param mstTantouMise
     */
    public void updateMstTantouMise(MstTantouMise mstTantouMise);
}
