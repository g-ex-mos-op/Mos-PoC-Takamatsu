/*
 * 作成日: 2006/02/24
 * 更新日: 2009/02/19 UIBirdUserDaoを同一パッケージに用意し、そちらを使用するよう変更
 *
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.GetUserInfoLogic;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class GetUserInfoLogicImpl implements GetUserInfoLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA012L01";
    
	private UIBirdUserDao useractregistBirdUserDao;
		
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
	public List execute(String userId) {
		//０．事前条件処理を行います。
		validate(userId);
		List resultList = getUseractregistBirdUserDao().getUserInfo(userId);
		
		if( resultList == null || resultList.size() == 0 ) {
			throw new NotExistException("ユーザー情報");
		}
		
		return resultList;
	}

    /* UIBirdUserDao DI */
    public UIBirdUserDao getUseractregistBirdUserDao() {
        return useractregistBirdUserDao;
    }

    public void setUseractregistBirdUserDao(UIBirdUserDao useractregistBirdUserDao) {
        this.useractregistBirdUserDao = useractregistBirdUserDao;
    }

}