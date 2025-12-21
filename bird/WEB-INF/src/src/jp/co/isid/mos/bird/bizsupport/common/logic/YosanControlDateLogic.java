package jp.co.isid.mos.bird.bizsupport.common.logic;

import jp.co.isid.mos.bird.bizsupport.common.entity.CtlYosanControlDate;

/**
 * —\Z“o˜^§Œä“ú•tæ“¾ƒƒWƒbƒN
 * 
 * @author Aspac
 */
public interface YosanControlDateLogic {

    /**
     * —\Z“o˜^§Œä“ú•tæ“¾
     * @param String companyCd
     * @param String nendo
     * @param String shoriKbn
     * @return —\Z“o˜^§Œä“ú•tŠÇ—
     */
    public CtlYosanControlDate execute(String companyCd, String nendo, String shoriKbn);
}
