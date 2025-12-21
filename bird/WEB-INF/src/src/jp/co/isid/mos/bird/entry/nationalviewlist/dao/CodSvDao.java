package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.CodSv;


/**
 * SVƒR[ƒhî•ñ
 * 
 * @author xkinu
 */
public interface CodSvDao {

    public static final Class BEAN = CodSv.class;

    public static final String select_ARGS = "companyCd, svCd";
    /**
     * ŒŸõˆ—
     * 
     * @param companyCd
     * @param svCd
     * @return
     */
    public List select(String companyCd, String svCd);
    
}