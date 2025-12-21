/*
 * ì¬“ú: 2007/02/12
 */
package jp.co.isid.mos.bird.storemanage.posniporef.logic;

import java.util.List;

/**
 * POS“ú•ñ@POS“ú•ñî•ñæ“¾
 * @author xwatanabe
 *
 */
public interface PosNipoInfoLogic {

    /**
     * POS“ú•ñî•ñ‚ğæ“¾‚·‚éB
     * @param companyCd
     * @param yyyymm
     * @param miseCd
     * @param onerCd
     * @param kbn
     * @return Map
     */
    public List execute(String companyCd, String yyyymm, String miseCd, String onerCd, String kbn, String userId, boolean limitFlg);
   
}
