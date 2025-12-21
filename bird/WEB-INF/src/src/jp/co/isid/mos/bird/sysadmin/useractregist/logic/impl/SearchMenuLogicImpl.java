/*
 * 作成日: 2006/02/22
 * 修正日: 2006/11/30 修正者：mwatanabe
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.UIBirdMenuDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.entity.UIBirdMenu;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.SearchMenuLogic;

/**
 * @author xkhata
 * メニュー検索を行う
 */
public class SearchMenuLogicImpl implements SearchMenuLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA012L01";

    /* DAO */
	private UIBirdMenuDao uIBirdMenuDao;
    /* 定数 */
    /** 使用可能フラグ：使用可 */
    private static final String ENABLE_KA      = "1";
    /** 個別設定フラグ：使用可 */
    private static final String CUSTOMIZE_KA   = "1";
    /** 個別設定フラグ：使用不可 */
    private static final String CUSTOMIZE_FUKA = "9";
	
    /**
     * 事前条件処理
     * @param userId
     */
    private void validate(String userId)
    {
        if (CommonUtil.isNull(userId)) {
            throw new NotNullException("ユーザーID", "selectUserId", 0);
        }
        LengthVerifier lengthVerifier = new LengthVerifier(8);
        if ( !lengthVerifier.validate( userId ) ) {
        	throw new InvalidInputException("ユーザーID", "selectUserId", 0);
        }
    }
    /**
     * 対象ユーザのアクセス可能なメニュー情報の検索を行います。
     * UIBirdMenuDaoを設定します。
     * @param  userId 対象ユーザのユーザID
     * @return List(CtlUserActのリスト)
     */
	public List execute(String userId) throws ApplicationException {
		//０．事前条件処理を行います。
		validate(userId);
		
        //----------------------------------------------------------
        // <1> アクセス権限を付与できるメニュー(上限)のリストを取得
        //----------------------------------------------------------
        List listAct = getUIBirdMenuDao().getJogenRoleMenu(userId);

        if ( listAct == null || listAct.size() == 0) {
            throw new NotExistException("機能");
        }
        for(int i=0; i<listAct.size(); i++) {
        	UIBirdMenu entity = (UIBirdMenu)listAct.get(i);
     		if(CUSTOMIZE_FUKA.equals(entity.getCustomizeFlg())) {
        			continue;
        	}
	        if(CUSTOMIZE_KA.equals(entity.getCustomizeFlg())
	        		|| ENABLE_KA.equals(entity.getEnableFlg()))
	        {
        		entity.setCheckFlgBefor(true);
        	}
        }

		//アクセス権限を付与できるメニュー(上限)のリストをリターンします。
		return listAct;
	}
	
    /**
     * UIBirdMenuDaoを設定します。
     */
    public void setUIBirdMenuDao(UIBirdMenuDao uIBirdMenuDao) {
        this.uIBirdMenuDao = uIBirdMenuDao;
    }
    
    /**
     * UIBirdMenuDaoを取得します。
     */
    public UIBirdMenuDao getUIBirdMenuDao() {
        return this.uIBirdMenuDao;
    }
}
