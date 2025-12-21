/*
 * ì¬“ú: 2007/05/31
 */
package jp.co.isid.mos.bird.common.dao;

import jp.co.isid.mos.bird.common.entity.PublicTargetKobetuMise;

/**
 * ŒöŠJ‘ÎÛ ŒÂ•Ê“X•Üİ’èDAO
 * @author kusama
 */
public interface PublicTargetKobetuMiseDao {

    public static final Class BEAN = PublicTargetKobetuMise.class;
    public static final String getMiseInfo_ARGS = "miseCd, gyotaiKbn";

    /**
     * ‹Æ‘Ô–¼Ìæ“¾
     * @param gyotaiKbn
     * @return PublicTargetKobetuMise
     */
    public PublicTargetKobetuMise getMiseInfo(String miseCd, String gyotaiKbn);

}