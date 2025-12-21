/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetLoginFailInfoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * ロック情報取得ロジック
 * 
 * 作成日:2009/12/14
 * @author xkinu
 *
 */
public class GetLoginFailInfoLogicImpl implements GetLoginFailInfoLogic {
	/* ロジックNo */
    public static final String LOGIC_ID = "BBA001L03";
	/** DAO【パスワード誤回数DAO】*/
	private CtlLoginFailKaisuDao accounteditCtlLoginFailKaisuDao;
	
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
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetLoginFailInfoLogic#execute(java.lang.String)
	 */
	public CtlLoginFailKaisu execute(String userId) {
		//０．事前条件処理を行います。
		validate(userId);
        //１．DAO【パスワード誤回数】取得を実行し、[パスワード誤回数]を取得します。
    	CtlLoginFailKaisu eFailKaisu = getAccounteditCtlLoginFailKaisuDao().getCtlLoginFailKaisu(userId);
    	//２．処理２の戻り値[パスワード誤回数]がnullの場合は下記の処理を行います。
        if(eFailKaisu == null) {
        	//２－１．インスタンス化した[パスワード誤回数]を戻り値[パスワード誤回数]へ代入しますします。
        	eFailKaisu = new CtlLoginFailKaisu();
        	//２－２．戻り値[パスワード誤回数].ユーザーIDへ引数.ユーザーIDを設定します。
        	eFailKaisu.setUserId(userId);
        	eFailKaisu.setLoginFail(new BigDecimal("0"));
		}
        //３．戻り値[パスワード誤回数]をリターンします。
		return eFailKaisu;
	}
	/**
	 * @return accounteditCtlLoginFailKaisuDao を戻します。
	 */
	public CtlLoginFailKaisuDao getAccounteditCtlLoginFailKaisuDao() {
		return accounteditCtlLoginFailKaisuDao;
	}
	/**
	 * @param accounteditCtlLoginFailKaisuDao を クラス変数ctlLoginFailKaisuDaoへ設定します。
	 */
	public void setAccounteditCtlLoginFailKaisuDao(CtlLoginFailKaisuDao ctlLoginFailKaisuDao) {
		this.accounteditCtlLoginFailKaisuDao = ctlLoginFailKaisuDao;
	}
}
