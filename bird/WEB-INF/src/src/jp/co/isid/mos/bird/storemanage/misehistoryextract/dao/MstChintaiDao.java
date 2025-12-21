/*
 * ì¬“ú: 2016/02/22
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstChintai;

/**
 * ’À‘İ“X•Ü—š—ğæ“¾
 */
public interface MstChintaiDao {

	public static final Class BEAN = MstChintai.class;
	public static final String select_ARGS = "miseCd";

	/**
	 * ’À‘İ“X•Ü—š—ğ‚ÌŒŸõ
	 */
	public List select(String miseCd);

}
