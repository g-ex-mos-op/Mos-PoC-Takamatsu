package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.CtlYosanControlDate;

/**
 * —\Z“o˜^§Œä“ú•tŠÇ—DAO
 * 
 * @author Aspac
 */
public interface CtlYosanControlDateDao {

    public static final Class BEAN = CtlYosanControlDate.class;

    public static final String getControlDate_ARGS = "companyCd, nendo, shoriKbn";
    public static final String insert_ARGS  = "entity";
    public static final String update_ARGS  = "entity";
    public static final String update_PERSISTENT_PROPS = "shoriDt, lastUser, lastPgm";

    /**
     * —\Z“o˜^§Œä“ú•tŠÇ—î•ñæ“¾
     * @return 
     */
    public List getControlDate(String companyCd, String nendo, String shoriKbn);
    
    /**
     * —\Z“o˜^§Œä“ú•tŠÇ—î•ñ “o˜^ˆ—
     * @param CtlYosanControlDate
     */
    public void insert(CtlYosanControlDate entity);

    /**
     * —\Z“o˜^§Œä“ú•tŠÇ—î•ñ XVˆ—
     * @param CtlYosanControlDate
     */
    public void update(CtlYosanControlDate entity);
}
