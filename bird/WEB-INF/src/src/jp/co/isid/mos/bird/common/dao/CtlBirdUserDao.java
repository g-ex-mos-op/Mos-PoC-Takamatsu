package jp.co.isid.mos.bird.common.dao;

import jp.co.isid.mos.bird.common.entity.CtlBirdUser;


public interface CtlBirdUserDao {

    public Class BEAN = CtlBirdUser.class;
    
    public static final String getBirdCtlBirdUser_ARGS = "userId";
    
    /**
     * ユーザー情報の取得
     * @param userId ユーザーID
     * @return BIRDユーザー情報
     */
    public CtlBirdUser getBirdCtlBirdUser(String userId);
    
    /**
     * ユーザー情報の更新
     * @param entity
     */
    public void updateBirdCtlBirdUser(CtlBirdUser entity);

}
