/*
 * 作成日: 200/2/13
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UIBirdUser;


/**
 * 同一部門コードが設定されているBIRDユーザ情報を取得します
 * @author xylee
 */
public interface UIBirdUserDao {

    public Class BEAN = UIBirdUser.class;

    public static final String getBirdUserInfo_ARGS = "userId,bumonCd";
 
    
    /**
     * BIRDユーザ情報を取得する
     * @param userId ユーザＩＤ
     * @param bumonCd 部門C D
     * @return ユーザ情報
     */   
    public List getBirdUserInfo(String userId,String bumonCd);

}
