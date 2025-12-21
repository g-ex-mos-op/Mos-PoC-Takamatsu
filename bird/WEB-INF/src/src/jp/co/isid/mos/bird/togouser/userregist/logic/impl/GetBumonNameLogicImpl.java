/*
 * 作成日: 2008/11/07
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import jp.co.isid.mos.bird.togouser.userregist.dao.CodBumonNameDao;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonNameLogic;

/**
 * 部門リスト検索用ロジック
 * @author K.Nihonyanagi
 */
public class GetBumonNameLogicImpl implements GetBumonNameLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L07";

    private CodBumonNameDao codBumonNameDao;
    /**
     * 部門リスト検索処理
	 */
	public String execute(String bumonCd) {
		return getCodBumonNameDao().getBumon(bumonCd);
	}
    public CodBumonNameDao getCodBumonNameDao() {
        return codBumonNameDao;
    }
    public void setCodBumonNameDao(CodBumonNameDao codBumonNameDao) {
        this.codBumonNameDao = codBumonNameDao;
    }


}
