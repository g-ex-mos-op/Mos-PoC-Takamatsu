/*
 * ì¬“ú: 2006/04/20
 *
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * x•”‚Ìæ“¾
 * @author xytamura
 */
public interface GetSibuLogic {
    
    /**
     * x•”‚Ìæ“¾
     * @param companyCd Šé‹ÆƒR[ƒh
     * @return x•”
     */
    public abstract List execute(String companyCd, String sibuCd);
}