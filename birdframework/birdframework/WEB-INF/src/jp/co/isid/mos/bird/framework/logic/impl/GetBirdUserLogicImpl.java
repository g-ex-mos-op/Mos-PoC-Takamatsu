package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.co.isid.mos.bird.framework.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.framework.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.framework.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.framework.dto.UserListDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIBirdUser;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.GetBirdUserLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 対象ユーザに紐付くユーザ一覧をユーザタイプ別に取得します
 * @author xylee
 */
public class GetBirdUserLogicImpl implements GetBirdUserLogic {
    //ユーザタイプ
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_OWNER = "02";
    private static final String USER_TYPE_TEMPO = "03"; 

        
    /**
     * ユーザ一覧をユーザタイプ別に取得します
     * @param mstUser  BIRDユーザ情報
     * @param userOner ユーザ対応オーナー
     * @param userMise ユーザ対応店舗
     * @return ユーザ一覧
     */
    public List execute(final MstUser mstUser, final List userOner,final List userMise) {
        
    	S2Container s2Con = SingletonS2ContainerFactory.getContainer();
    	UIBirdUserDao uiBirdUserDao = (UIBirdUserDao) s2Con.getComponent(UIBirdUserDao.class);
    	
    	UIUserOnerDao uiUserOnerDao = (UIUserOnerDao) s2Con.getComponent(UIUserOnerDao.class);
    	
    	UIUserMiseDao uiUserMiseDao = (UIUserMiseDao) s2Con.getComponent(UIUserMiseDao.class);
    	
    	List userInfo = new ArrayList();
    	
    	if (mstUser.getUserTypeCd().equals(USER_TYPE_HONBU)) {
			List userList = uiBirdUserDao.getBirdUserInfo(mstUser.getUser_id(), mstUser.getBumonCd());

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
                List userList = uiUserOnerDao.getUserOner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
                
                //2006/03/07 追加
                //オーナーの保有する店舗のユーザを取得
                List userListMise =  uiUserMiseDao.getUserMiseFromOwner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
                
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
    			List userList = uiUserMiseDao.getUserMiseList(uiUserMise.getUserId(), uiUserMise.getCompanyCd(), uiUserMise.getMiseCd());
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

   
}