/*
 * ì¬“ú: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;

public interface CountKeijibanLogic {

    /**
     * Œf¦”ÂŒ”‚Ìæ“¾
     * @param dto ƒpƒ‰ƒ[ƒ^
     * @return Œf¦”ÂŒ”
     * @throws ApplicationException
     */
    public int execute(KeijibanConditionDto dto)
            throws ApplicationException;

}