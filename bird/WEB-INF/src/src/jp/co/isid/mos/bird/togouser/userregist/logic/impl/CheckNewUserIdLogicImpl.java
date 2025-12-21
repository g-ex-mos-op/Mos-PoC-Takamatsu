/*
 * 作成日: 2008/11/07
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UITogoUserRirekiDao;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.entity.UITogoUserRireki;
import jp.co.isid.mos.bird.togouser.userregist.logic.CheckNewUserIdLogic;

/**
 * ユーザーIDが、BIRDユーザー、統合ユーザーに登録済みでないかチェックする。
 * @author K.Nihonyanagi
 */
public class CheckNewUserIdLogicImpl implements CheckNewUserIdLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L08";

    private UITogoUserRirekiDao uiTogoUserRirekiDao;
    private UIBirdUserDao uiBirdUserDao;
    
    /**
     * ユーザーIDが、BIRDユーザー、統合ユーザーに登録済みでないかチェックする。
	 */
	public void execute(String userId) {
        
        //１、Dao【統合ユーザー履歴情報．ユーザー情報の取得】を実行する。       
        //　　　　　　　パラメータ：   パラメータ．ユーザーID    
        UITogoUserRireki uiTogoUserRireki = getUiTogoUserRirekiDao().getUITogoUserRireki(userId);
        
        //２、Dao【BIRDユーザー情報．BIRDユーザー情報の取得】を実行する。       
        //　　　　　　　パラメータ：   パラメータ．ユーザーID    
        UIBirdUser uiBirduser = getUiBirdUserDao().getBirdUser(userId);

        //３、１の[統合ユーザー履歴情報]の取得件数≠0 または、２の[BIRDユーザー情報]の取得件数≠0の場合        
        //　　　　　　　E0607("そのユーザーは登録済み")     
        if (uiTogoUserRireki!=null || uiBirduser!=null) {
            throw new GenericCommentException("そのユーザーは登録済み");
        }

	}

    public UIBirdUserDao getUiBirdUserDao() {
        return uiBirdUserDao;
    }

    public UITogoUserRirekiDao getUiTogoUserRirekiDao() {
        return uiTogoUserRirekiDao;
    }

    public void setUiBirdUserDao(UIBirdUserDao uiBirdUserDao) {
        this.uiBirdUserDao = uiBirdUserDao;
    }

    public void setUiTogoUserRirekiDao(UITogoUserRirekiDao uiTogoUserRirekiDao) {
        this.uiTogoUserRirekiDao = uiTogoUserRirekiDao;
    }

    
    


}
