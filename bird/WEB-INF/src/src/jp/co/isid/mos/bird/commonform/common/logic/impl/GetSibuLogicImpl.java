/*
 * 作成日: 2006/1/12
 */
package jp.co.isid.mos.bird.commonform.common.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.common.dao.CodSibuDao;
import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;

/**
 * 支部コード検索用ロジック
 * @author itamoto
 */
public class GetSibuLogicImpl implements GetSibuLogic {

    public static final String LOGIC_ID = "BCC102L01";

    private CodSibuDao codSibuDao;

    public CodSibuDao getCodSibuDao() {
        return codSibuDao;
    }

    public void setCodSibuDao(CodSibuDao codSibuDao) {
        this.codSibuDao = codSibuDao;
    }

    /**
     * 支部コード検索処理
	 */
	public List execute(String companyCd) {
		if (companyCd == null || companyCd.length() <= 0) {
			return new ArrayList();
		}
		return getCodSibuDao().select(companyCd);
	}
}
