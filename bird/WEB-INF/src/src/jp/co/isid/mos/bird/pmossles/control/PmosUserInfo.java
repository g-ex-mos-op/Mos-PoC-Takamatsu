/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.control;

import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * P-mosslesユーザー情報
 * 
 * 作成日:2010/07/09
 * @author xkinu
 *
 */
public class PmosUserInfo {
	private MstUser mstUser;
	private MstMise userMise;

	/**
	 * コンストラクター
	 */
	public PmosUserInfo() {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
	}

	/**
	 * @param mstUser
	 * @param mstMiseコンストラクター
	 */
	public PmosUserInfo(MstUser mstUser, MstMise userMise) {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
		this.mstUser = mstUser;
		this.userMise = userMise;
	}

	/**
	 * @return クラス変数mstUser を戻します。
	 */
	public MstUser getMstUser() {
		return mstUser;
	}

	/**
	 * @param mstUser を クラス変数mstUserへ設定します。
	 */
	public void setMstUser(MstUser mstUser) {
		this.mstUser = mstUser;
	}

	/**
	 * @return クラス変数userMise を戻します。
	 */
	public MstMise getUserMise() {
		return userMise;
	}

	/**
	 * @param userMise を クラス変数userMiseへ設定します。
	 */
	public void setUserMise(MstMise userMise) {
		this.userMise = userMise;
	}
}
