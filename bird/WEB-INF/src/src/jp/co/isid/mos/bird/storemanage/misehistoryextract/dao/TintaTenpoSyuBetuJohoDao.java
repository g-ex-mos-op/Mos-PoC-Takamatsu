/*
 * ì¬“ú: 2016/02/22
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.TintaTenpoSyuBetuJoho;

/**
 * ’À‘İ“X•Üí•Êî•ñæ“¾
 */
public interface TintaTenpoSyuBetuJohoDao {
	public static final Class BEAN = TintaTenpoSyuBetuJoho.class;
	public static final String select_ARGS = "miseCd";

	/**
	 * ’À‘İ“X•Üí•Êî•ñ‚ÌŒŸõ
	 */
	public List select(String miseCd);

}
