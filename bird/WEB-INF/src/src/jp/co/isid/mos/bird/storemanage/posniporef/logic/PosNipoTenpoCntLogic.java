/*
 * ì¬“ú: 2007/02/12
 */
package jp.co.isid.mos.bird.storemanage.posniporef.logic;


/**
 * POS“ú•ñ@æ“¾“X•Ü”‚ğæ“¾
 * @author xwatanabe
 *
 */
public interface PosNipoTenpoCntLogic {

    /**
     * POS“ú•ñæ“¾“X•Ü”‚ğæ“¾‚·‚éB
     * @param companyCd
     * @param yyyymm
     * @param miseCd
     * @param onerCd
     * @param kbn
     * @param userId
     * @param limitFlg
     * @return
     */
    public int execute(String companyCd, String yyyymm, String miseCd, String onerCd, String kbn, String userId, boolean limitFlg);
}
