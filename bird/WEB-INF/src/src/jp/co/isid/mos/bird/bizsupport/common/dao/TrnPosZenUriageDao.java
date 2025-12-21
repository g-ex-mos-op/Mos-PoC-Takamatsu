/*
 * ì¬“ú: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPosZenUriage;

/**
 * POS‘OŒ”„ã(TrnPosZenUriageDao)
 * @author itamoto
 */
public interface TrnPosZenUriageDao {

    public static final Class BEAN = TrnPosZenUriage.class;
    public static final String getPosZenUriage_ARGS  = "EIGYO_DT, MISE_CD";

    /**
     * POS‘OŒ”„ã‚Ìæ“¾(getPosZenUriage)
     * @param targetYM
     * @param miseCd
     * @return TrnPosZenUriage ŒŸõŒ‹‰Ê
     */
    public TrnPosZenUriage getPosZenUriage (String targetYM, String miseCd);
}
