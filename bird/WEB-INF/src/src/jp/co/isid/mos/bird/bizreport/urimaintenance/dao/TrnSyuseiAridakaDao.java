/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.TrnSyuseiAridaka;

/**
 * DAO【会計区分別在高日次修正】
 * 
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public interface TrnSyuseiAridakaDao {
	/** エンティティクラス：TrnSyuseiAridaka */
	public static final Class BEAN = TrnSyuseiAridaka.class;
	/** 引数:新規登録 */
	public static final String insert_ARGS = "entity";
	/** 引数:更新登録 */
	public static final String update_ARGS = "entity";
	/** 引数:修正状況検索 */
	public static final String select_ARGS = "sysData, companyCd, miseCd, targetYM";
	/** 引数:会計区分別在高日次検索 */
	public static final String selectBD30_ARGS = "sysData, companyCd, miseCd, targetYM";
	
	/**
	 * 新規登録
	 * @param entity
	 * @return
	 */
	public int insert(TrnSyuseiAridaka entity);

	/** NO_PERSISTENT_PROPSアノテーション */
	public static final String update_NO_PERSISTENT_PROPS = "firstUser, firstPgm, firstTmsp";
	/**
	 * 更新登録
	 * @param entity
	 * @return
	 */
	public int update(TrnSyuseiAridaka entity);
	/**
	 * 修正状況検索
	 * 
	 * @param sysData(必須)
	 * @param companyCd(必須)
	 * @param miseCd(必須)
	 * @param yyyyMM(必須)
	 * @return List[[会計区分別在高日次修正]]
	 */
	public List select(String sysData, String companyCd, String miseCd, String targetYM);
	/**
	 * 会計区分別在高日次検索
	 * 
	 * 会計区分別在高日次(BD30KKAD)のデータを[会計区分別在高日次修正](エンティティクラス：TrnSyuseiAridaka)で取得します。
	 * @param sysData(必須)
	 * @param companyCd(必須)
	 * @param miseCd(必須)
	 * @param yyyyMM(必須)
	 * @return List[[会計区分別在高日次修正]]
	 */
	public List selectBD30(String sysData, String companyCd, String miseCd, String targetYM);
}
