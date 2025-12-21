/**
 * 作成日：2006/9/13
 * 参照：jp.co.isid.mos.bird.framework.logic.impl.GetBirdUserLogicImpl
 *
 */

package jp.co.isid.mos.bird.bizadmin.common.logic.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizadmin.common.logic.GetBirdUserSelfLogic;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIBirdUserSelfDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserMiseSelfDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserOnerSelfDao;
import jp.co.isid.mos.bird.framework.dto.UserListDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIBirdUser;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * 自分自身を含めたユーザー一覧を取得します
 * @author miyagi
 *
 */
public class GetBirdUserSelfLogicImpl implements GetBirdUserSelfLogic {

    //ユーザタイプ
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_OWNER = "02";
    private static final String USER_TYPE_TEMPO = "03"; 

        
    /**
     * ユーザ一覧（自分含む）をユーザタイプ別に取得します
     * @param mstUser  BIRDユーザ情報
     * @param userOner ユーザ対応オーナー
     * @param userMise ユーザ対応店舗
     * @return ユーザ一覧
     */
    public List execute(final MstUser mstUser, final List userOner,final List userMise) {
        
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        UIBirdUserSelfDao uiBirdUserDao = (UIBirdUserSelfDao) s2Con.getComponent(UIBirdUserSelfDao.class);
       
        UIUserOnerSelfDao uiUserOnerDao = (UIUserOnerSelfDao) s2Con.getComponent(UIUserOnerSelfDao.class);
        
        UIUserMiseSelfDao uiUserMiseDao = (UIUserMiseSelfDao) s2Con.getComponent(UIUserMiseSelfDao.class);

        
        List userInfo = new ArrayList();
        
        
        if (mstUser.getUserTypeCd().equals(USER_TYPE_HONBU)) {//ログインしたひとが本部ユーザのとき
            //アカウントを変更・照会できるユーザ（部門コードが一致するユーザ）一覧を取得
            List userList = uiBirdUserDao.getBirdUserInfo(mstUser.getUser_id(), mstUser.getBumonCd());

            //取得したユーザ一覧をDTOにセット
             for (int i = 0; i < userList.size(); i++) {
                UIBirdUser entity = (UIBirdUser) userList.get(i);
                UserListDto userListDto = new UserListDto();        
                
                userListDto.setUserId(entity.getUserId());
                userListDto.setUserNameKj(entity.getUserNameKj());
                userListDto.setUserNameKana(entity.getUserNameKana());
                userInfo.add(userListDto);
             }
        
        } else if (mstUser.getUserTypeCd().equals(USER_TYPE_OWNER)) {//ログインしたひとがオーナーユーザのとき
            
            Set userIdSet = new HashSet();
            for (int i = 0; i < userOner.size(); i++) {
                UIUserOner uiUserOner = (UIUserOner) userOner.get(i);
                
                //同じオーナーコードを持つユーザを取得              
                List userList = uiUserOnerDao.getUserOner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
                
                //オーナーの保有する店舗のユーザを取得
                List userListMise =  uiUserMiseDao.getUserMiseFromOwner(uiUserOner.getUserId(), uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());
     
                
                //同じオーナーコードを持つユーザをDTOにセット
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
                
                //オーナーの保有する店舗のユーザををDTOにセット
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
                
            }   
                
        } else if (mstUser.getUserTypeCd().equals(USER_TYPE_TEMPO)) {//ログインしたひとが店舗スタッフのとき
        
            Set userIdSet = new HashSet();
            for (int i = 0; i < userMise.size(); i++) {
                UIUserMise uiUserMise = (UIUserMise) userMise.get(i);
                
                //同じ店舗のユーザ一覧を取得
                List userList = uiUserMiseDao.getUserMiseList(uiUserMise.getUserId(), uiUserMise.getCompanyCd(), uiUserMise.getMiseCd());

                //取得したユーザ一覧をDTOにセット
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

        } else {//それ以外＜エラー＞
            userInfo = null;
            throw new FtlSystemException("セッション情報チェック");
        }
        
        return userInfo;
    
    }  

}
