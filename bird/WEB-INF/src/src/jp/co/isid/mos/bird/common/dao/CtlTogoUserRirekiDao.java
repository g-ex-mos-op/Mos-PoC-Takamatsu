package jp.co.isid.mos.bird.common.dao;

import jp.co.isid.mos.bird.common.entity.CtlTogoUserRireki;


public interface CtlTogoUserRirekiDao {

    public Class BEAN = CtlTogoUserRireki.class;
    
    public static final String getCtlTogoUserRireki_ARGS = "userId";
    
    /**
     * ユーザー情報の取得
     * @param userId ユーザーID
     * @return 最新の統合ユーザー履歴情報Entity
     */
    public CtlTogoUserRireki getCtlTogoUserRireki(String userId);
    
    /**
     * ユーザー情報の更新
     * @param entity
     */
    public void updateCtlTogoUserRireki(CtlTogoUserRireki entity);

}
