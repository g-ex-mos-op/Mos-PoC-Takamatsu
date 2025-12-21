/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic.impl;

import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetCtlPasswordPolicyLogic;
import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * パスワードポリシー取得ロジック
 * 
 * 作成日:2009/12/21
 * @author xkinu
 *
 */
public class GetCtlPasswordPolicyLogicImpl implements GetCtlPasswordPolicyLogic {
	/* ロジックNo */
    public static final String LOGIC_ID = "BBA001L04";
	/** DAO【ユーザ所属情報】*/
	private MstUserShozokuDao mstUserShozokuDao;
    /*パスワードポリシー*/
    private Map birdPasswordPolicy;
    /**
     * 事前条件処理
     * @param userId
     */
    private void validate(String userId)
    {
        if (CommonUtil.isNull(userId)) {
            throw new MissingDataException("ユーザーID");
        }
    }
	/**
	 * 実行処理
	 * @param userId
	 * @return
	 */
	public CtlPasswordPolicy execute(String userId) {
		//０．事前条件処理を行います。
		validate(userId);
		//１．DAO【ユーザ所属情報】取得を実行し、[ユーザ所属情報]を取得します。
		MstUserShozoku eShozoku = (MstUserShozoku)(getMstUserShozokuDao().getMstUserShozoku(userId)).get(0);
        //２．ユーザーの所属区分のパスワード誤回数を取得します。
        CtlPasswordPolicy ctlPswdPolicy = (CtlPasswordPolicy) getBirdPasswordPolicy().get(eShozoku.getShozokuKbn());

        return ctlPswdPolicy;
	}
	/**
	 * @return クラス変数birdPasswordPolicy を戻します。
	 */
	public Map getBirdPasswordPolicy() {
		return birdPasswordPolicy;
	}
	/**
	 * @param birdPasswordPolicy を クラス変数birdPasswordPolicyへ設定します。
	 */
	public void setBirdPasswordPolicy(Map birdPasswordPolicy) {
		this.birdPasswordPolicy = birdPasswordPolicy;
	}
	/**
	 * @return クラス変数mstUserShozokuDao を戻します。
	 */
	public MstUserShozokuDao getMstUserShozokuDao() {
		return mstUserShozokuDao;
	}
	/**
	 * @param mstUserShozokuDao を クラス変数mstUserShozokuDaoへ設定します。
	 */
	public void setMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
		this.mstUserShozokuDao = mstUserShozokuDao;
	}

}
