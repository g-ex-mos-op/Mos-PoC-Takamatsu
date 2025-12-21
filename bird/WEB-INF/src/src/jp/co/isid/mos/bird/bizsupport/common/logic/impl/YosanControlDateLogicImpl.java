package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.CtlYosanControlDateDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.CtlYosanControlDate;
import jp.co.isid.mos.bird.bizsupport.common.logic.YosanControlDateLogic;


/**
 * —\Z“o˜^§Œä“ú•tæ“¾ƒƒWƒbƒN
 * 
 * @author Aspac
 */
public class YosanControlDateLogicImpl implements YosanControlDateLogic {

    
    /** ƒƒWƒbƒNID ’è‹` */
    public static final String LOGIC_ID = "BBS000L07";

    
    /**
     * —\Z“o˜^§Œä“ú•tŠÇ—Dao
     */
    private CtlYosanControlDateDao ctlYosanControlDateDao;
    
    
    /**
     * —\Z“o˜^§Œä“ú•tæ“¾
     * 
     * @param String companyCd
     * @param String nendo
     * @param String shoriKbn
     * @return —\Z“o˜^§Œä“ú•tŠÇ—
     */
    public CtlYosanControlDate execute(String companyCd, String nendo, String shoriKbn) {
        List listData = getCtlYosanControlDateDao().getControlDate(companyCd, nendo, shoriKbn);
        if (listData == null || listData.isEmpty()) {
            return null;
        }
        return (CtlYosanControlDate) listData.get(0);
    }

    public CtlYosanControlDateDao getCtlYosanControlDateDao() {
        return ctlYosanControlDateDao;
    }

    public void setCtlYosanControlDateDao(
            CtlYosanControlDateDao ctlYosanControlDateDao) {
        this.ctlYosanControlDateDao = ctlYosanControlDateDao;
    }
}
