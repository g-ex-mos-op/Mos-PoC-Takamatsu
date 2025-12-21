package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodKotenLinkJoho;


/**
 * ŒÂ“XƒŠƒ“ƒNî•ñ
 * @author kusama
 */
public interface CodKotenLinkJohoDao {

    public static final Class BEAN = CodKotenLinkJoho.class;

    /**
     * ŒÂ“XƒŠƒ“ƒNî•ñ‚ÌŒŸõ
     * @return List
     */
    public List selectKotenLink(String userId);
    
}