package jp.co.isid.mos.bird.inforegist.informregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

public interface CheckNewsKanrenBunshoLogic {

    /**
     * @see jp.co.isid.mos.bird.inforegist.contactregist.logic.CheckRenrakuKanrenBunshoLogic#execute(java.util.List, java.util.List)
     */
    public abstract List execute(List listKanren, List listAddKanren)
            throws ApplicationException;

}