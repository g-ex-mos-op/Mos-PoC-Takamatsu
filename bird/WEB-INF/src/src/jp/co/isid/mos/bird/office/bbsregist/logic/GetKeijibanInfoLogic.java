/*
 * ì¬“ú: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;

public interface GetKeijibanInfoLogic {

    /**
     * Œf¦”Âî•ñ‚Ìæ“¾
     * @param dto ƒpƒ‰ƒ[ƒ^
     * @return Œf¦”Âî•ñ
     * @throws ApplicationException
     */
    public List execute(KeijibanConditionDto dto)
            throws ApplicationException;

}