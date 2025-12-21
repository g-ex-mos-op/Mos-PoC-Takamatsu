/*
 * ì¬“ú: 2007/05/29
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstNextNendoSibu;

/**
 * x•”
 * @author xnkusama
 */
public interface MstNextNendoSibuDao {

    public static final Class BEAN = MstNextNendoSibu.class;
    public static final String getAllSibu_ARGS = "companyCd";

    /**
     * x•”‚ğ‘SŒæ“¾‚·‚é
     * @param companyCd Šé‹ÆƒR[ƒh
     * @return x•”
     */
    public List getAllSibu(String companyCd);

}