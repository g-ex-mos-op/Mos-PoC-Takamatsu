/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.UISeidoPointDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetSeidoPointInfoLogic;

/**
 * 株式報酬制度ポイント検索ロジック
 *
 * @author yushuncheng
 *
 */
public class GetSeidoPointInfoLogicImpl implements GetSeidoPointInfoLogic {

	/**
	 * 株式報酬制度ポイントDao
	 */
	private UISeidoPointDao uISeidoPointDao;

	/**
	 * 株式報酬制度ポイントDaoを取得します。
	 *
	 * @return uISeidoPointDao 株式報酬制度ポイントDao
	 */
	public UISeidoPointDao getuISeidoPointDao() {
		return uISeidoPointDao;
	}

	/**
	 * 株式報酬制度ポイントDaoを設定します。
	 *
	 * @param uISeidoPointDao
	 *            株式報酬制度ポイントDao
	 */
	public void setuISeidoPointDao(UISeidoPointDao uISeidoPointDao) {
		this.uISeidoPointDao = uISeidoPointDao;
	}

	/**
	 * 株式報酬制度ポイントリストを取得します。
	 */
	@Override
	public List<UISeidoMst> execute() {

		List<UISeidoMst> seidoPointList = getuISeidoPointDao().getSeidoPointInfo();

		return seidoPointList;
	}

}
