/*
 * 作成日: 2016/02/19
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstMiSeRiRekiJoho;

/**
 * 店マスタ履歴情報取得
 */
public interface MstMiSeRiRekiJohoDao {

	public static final Class BEAN = MstMiSeRiRekiJoho.class;
	public static final String select_ARGS = "miseCd";

	/**
	 * 店マスタ履歴情報の検索
	 */
	public List select(String miseCd);

}
