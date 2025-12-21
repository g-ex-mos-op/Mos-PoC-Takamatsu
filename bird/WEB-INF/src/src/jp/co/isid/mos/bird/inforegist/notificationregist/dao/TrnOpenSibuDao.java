/**
 * 
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.notificationregist.entity.TrnOpenSibu;

/**
 * ŒöŠJ‘ÎÛx•”î•ñ
 * 
 * ì¬“ú:2010/02/23
 * @author xkinu
 *
 */
public interface TrnOpenSibuDao {
    public Class BEAN = TrnOpenSibu.class;

    public static final String select_ARGS = "lastTmspDt";
    
    /**
     * ŒöŠJ‘ÎÛx•”î•ñ‚Ìæ“¾
     * 
     * @param lastTmspDt
     * @return
     */
    public List select(String lastTmspDt);

}
