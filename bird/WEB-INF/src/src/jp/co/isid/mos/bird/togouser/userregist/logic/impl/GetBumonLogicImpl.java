/*
 * 作成日: 2008/11/07
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dao.CodBumonDao;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonLogic;

/**
 * 部門リスト検索用ロジック
 * @author K.Nihonyanagi
 */
public class GetBumonLogicImpl implements GetBumonLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L04";

    private CodBumonDao codBumonDao;
    /**
     * 部門リスト検索処理
	 */
	public List execute() {
		return getCodBumonDao().getBumonList();
	}
    public CodBumonDao getCodBumonDao() {
        return codBumonDao;
    }
    public void setCodBumonDao(CodBumonDao codBumonDao) {
        this.codBumonDao = codBumonDao;
    }



}
