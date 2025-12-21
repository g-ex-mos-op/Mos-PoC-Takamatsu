package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;

/**
 * @author xytamura
 */
public interface MstCompanySibuTorikomiDao {

    public static final Class BEAN = MstCompanySibuTorikomi.class;
    
    public static final String getSibuTorikomi_ARGS = " gyotaiKbn";

    public static final String getSibuFromCompany_ARGS = "companyCd";

    /**
     * x•”æ‚Ìæ“¾
     * @param gyotaiKbn ‹Æ‘Ô‹æ•ª
     * @return
     */
    public List getSibuTorikomi(final String gyotaiKbn);
    
    /**
     * x•”‚Ìæ“¾
     * @param companyCd Šé‹ÆƒR[ƒh
     * @return
     */
    public List getSibuFromCompany(final String companyCd);
        
    
}
