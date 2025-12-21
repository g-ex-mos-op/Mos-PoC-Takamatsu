/*
 * çÏê¨ì˙: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import jp.co.isid.mos.bird.togouser.userregist.dao.RankCodeDao;
import jp.co.isid.mos.bird.togouser.userregist.entity.RankCode;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetRankCodeLogic;

/**
 * @author K.Nihonyanagi
 *
 */
public class GetRankCodeLogicImpl implements GetRankCodeLogic{

    /* ÉçÉWÉbÉNÇhÇc*/ 
    public static final String LOGIC_ID = "BUR001L09";
    
	private RankCodeDao rankCodeDao;

	
	public RankCode execute(String bumonCd){
        return getRankCodeDao().getRankCode(bumonCd);
	}


    public RankCodeDao getRankCodeDao() {
        return rankCodeDao;
    }


    public void setRankCodeDao(RankCodeDao rankCodeDao) {
        this.rankCodeDao = rankCodeDao;
    }


}
