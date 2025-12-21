/*
 * ì¬“ú: 200/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouSibu;

/**
 *@ƒŠƒ‚[ƒg‰{——x•”DAO
 * 
 * @author xnkusama
 */
public interface MstTantouSibuDao {

    public static final Class BEAN = MstTantouSibu.class;

    public static final String getTantouSibu_ARGS = "companyCd, userId";
    public static final String deleteTantouSibu_ARGS = "mstTantouSibu";
    public static final String insertTantouSibu_ARGS = "mstTantouSibu";
    public static final String updateTantouSibu_ARGS = "mstTantouSibu";
    
    /**
     * ‰{——‰Â”\x•”‚Ìæ“¾
     * @param String ‰ïĞƒR[ƒh
     * @param String ƒ†[ƒU[ID
     * @return List ‰{——‰Â”\x•”î•ñ
     */
    public List getTantouSibu(String companyCd, String userId);
    
    /**
     * ‰{——‰Â”\x•” íœ
     * @param MstTantouSibu
     */
    public void deleteTantouSibu(MstTantouSibu mstTantouSibu);

    /**
     * ‰{——‰Â”\x•” “o˜^
     * @param MstTantouSibu
     */
    public void insertTantouSibu(MstTantouSibu mstTantouSibu);

    /**
     * ‰{——‰Â”\x•” XV
     * @param MstTantouSibu
     */
    public void updateTantouSibu(MstTantouSibu mstTantouSibu);
}
