package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.CodSibu;


/**
 * x•”ƒR[ƒhî•ñ
 * 
 * @author xkinu
 */
public interface CodSibuDao {

    public static final Class BEAN = CodSibu.class;

    public static final String select_ARGS = "limit, userId, companyCd";
    /**
     * ‘ÎÛx•”ŒŸõ
     * 
     * @param limit
     * @param userId
     * @param companyCd
     * @return
     */
    public List select(boolean limit, String userId, String companyCd);
    
}