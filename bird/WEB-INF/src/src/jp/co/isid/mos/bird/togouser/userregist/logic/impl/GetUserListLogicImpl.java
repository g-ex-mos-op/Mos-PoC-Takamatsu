/*
 * 作成日: 2008/11/07
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetUserListLogic;

/**
 * 部門リスト検索用ロジック
 * @author K.Nihonyanagi
 */
public class GetUserListLogicImpl implements GetUserListLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L09";

    private UIBirdUserDao uiBirdUserDao;
    /**
     * 部門リスト検索処理
	 */
	public List execute(String userId) {
		return getUiBirdUserDao().getUserList(userId+'%');
	}
    public UIBirdUserDao getUiBirdUserDao() {
        return uiBirdUserDao;
    }
    public void setUiBirdUserDao(UIBirdUserDao uiBirdUserDao) {
        this.uiBirdUserDao = uiBirdUserDao;
    }


}
