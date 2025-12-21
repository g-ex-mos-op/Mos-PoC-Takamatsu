package jp.co.isid.mos.bird.sysadmin.useractregist.logic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.co.isid.mos.bird.framework.dto.UserListDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIBirdUser;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.GetBirdUserLogic;

/**
 * 対象ユーザに紐付くユーザ一覧をユーザタイプ別に取得します
 *  （2009/02/19 一覧は請求フラグ＝ONのもののみ表示するようになったため共通から個別Logicに変更）
 * @see jp.co.isid.mos.bird.framework.logic.impl.GetBirdUserLogicImpl
 * @author xnkusama
 */
public class GetBirdUserLogicImpl implements GetBirdUserLogic {
    //ユーザタイプ
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_OWNER = "02";
    private static final String USER_TYPE_TEMPO = "03"; 

    private UIBirdUserDao useractregistBirdUserDao;
    private UIUserOnerDao useractregistUserOnerDao;
    private UIUserMiseDao useractregistUserMiseDao;
        
    /**
     * ユーザ一覧をユーザタイプ別に取得します
     * @param mstUser  BIRDユーザ情報
     * @param userOner ユーザ対応オーナー
     * @param userMise ユーザ対応店舗
     * @return ユーザ一覧
     */
    public List execute(final MstUser mstUser, final List userOner,final List userMise) {
        
    	
    	List userInfo = new ArrayList();
    	
    	if (mstUser.getUserTypeCd().equals(USER_TYPE_HONBU)) {
			List userList = getUseractregistBirdUserDao().getBumonUserInfo(mstUser.getUser_id(), mstUser.getBumonCd());

			 for (int i = 0; i < userList.size(); i++) {
				UIBirdUser entity = (UIBirdUser) userList.get(i);
				UserListDto userListDto = new UserListDto();	 	
				
				userListDto.setUserId(entity.getUserId());
				userListDto.setUserNameKj(entity.getUserNameKj());
				userListDto.setUserNameKana(entity.getUserNameKana());
				userInfo.add(userListDto);
			 }
    	
    	} else if (mstUser.getUserTypeCd().equals(USER_TYPE_OWNER)) {
    		
    		Set userIdSet = new HashSet();
    		for (int i = 0; i < userOner.size(); i++) {
    			UIUserOner uiUserOner = (UIUserOner) userOner.get(i);
                List userList = getUseractregistUserOnerDao().getUserOner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
                
                //2006/03/07 追加
                //オーナーの保有する店舗のユーザを取得
                List userListMise =  getUseractregistUserMiseDao().getUserMiseFromOwner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
                
    			for (int j = 0; j < userList.size(); j++) {
    				UIUserOner entity = (UIUserOner) userList.get(j);
    				UserListDto userListDto = new UserListDto();
    				
    				userListDto.setUserId(entity.getUserId());
    				userListDto.setUserNameKj(entity.getUserNameKj());
    				userListDto.setUserNameKana(entity.getUserNameKana());
      		
    				if (!userIdSet.contains(userListDto.getUserId())) {
    					userInfo.add(userListDto);
    					userIdSet.add(userListDto.getUserId());
    				}
    			}
                
                //2006/03/07 追加
                //オーナーの保有する店舗のユーザを取得 start
                for (int j = 0; j < userListMise.size(); j++) {
                    UIUserMise entity = (UIUserMise) userListMise.get(j);
                    UserListDto userListDto = new UserListDto();
                    
                    userListDto.setUserId(entity.getUserId());
                    userListDto.setUserNameKj(entity.getUserNameKj());
                    userListDto.setUserNameKana(entity.getUserNameKana());
            
                    if (!userIdSet.contains(userListDto.getUserId())) {
                        userInfo.add(userListDto);
                        userIdSet.add(userListDto.getUserId());
                    }
                }
                //2006/03/07 追加
                //オーナーの保有する店舗のユーザを取得 end
                
    		}	
    			
    	} else if (mstUser.getUserTypeCd().equals(USER_TYPE_TEMPO)) {
		
    		Set userIdSet = new HashSet();
    		for (int i = 0; i < userMise.size(); i++) {
    			UIUserMise uiUserMise = (UIUserMise) userMise.get(i);
    			List userList = getUseractregistUserMiseDao().getUserMiseList(uiUserMise.getUserId(), uiUserMise.getCompanyCd(), uiUserMise.getMiseCd());
    			for (int j = 0; j < userList.size(); j++) {
    				UIUserMise entity = (UIUserMise) userList.get(j);
    				
    				UserListDto userListDto = new UserListDto();
    				userListDto.setUserId(entity.getUserId());
    				userListDto.setUserNameKj(entity.getUserNameKj());
    				userListDto.setUserNameKana(entity.getUserNameKana());

    				if (!userIdSet.contains(userListDto.getUserId())) {
    					userInfo.add(userListDto);
    					userIdSet.add(userListDto.getUserId());
    				}
    			}
    		}

    	} else {
    		userInfo = null;
    		throw new FtlSystemException("セッション情報チェック");
    	}
    	
        return userInfo;
    
    }

    public UIBirdUserDao getUseractregistBirdUserDao() {
        return useractregistBirdUserDao;
    }

    public void setUseractregistBirdUserDao(UIBirdUserDao useractregistBirdUserDao) {
        this.useractregistBirdUserDao = useractregistBirdUserDao;
    }

    public UIUserMiseDao getUseractregistUserMiseDao() {
        return useractregistUserMiseDao;
    }

    public void setUseractregistUserMiseDao(UIUserMiseDao useractregistUserMiseDao) {
        this.useractregistUserMiseDao = useractregistUserMiseDao;
    }

    public UIUserOnerDao getUseractregistUserOnerDao() {
        return useractregistUserOnerDao;
    }

    public void setUseractregistUserOnerDao(UIUserOnerDao useractregistUserOnerDao) {
        this.useractregistUserOnerDao = useractregistUserOnerDao;
    }

   
}