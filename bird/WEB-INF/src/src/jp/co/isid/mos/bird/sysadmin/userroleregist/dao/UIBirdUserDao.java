/*
 * 作成日: 200/2/16
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIBirdUser;


/**
 * BIRDユーザ情報を取得します
 * @author xylee
 */
public interface UIBirdUserDao {

    public Class BEAN = UIBirdUser.class;

    public static final String getBirdUser_ARGS = "userId";
    public static final String getBirdUser_SQL =            
        " SELECT "
     +  "         USER_ID "
     +  ",        USER_NAME_KJ " 
     +  ",        USER_NAME_KANA "
     +  ",        USERTYPE_CD  "
     +  ",        BUMON_CD  "
	 +  " FROM "
     +  "         BR01USER " 
     +  " WHERE "
	 +  " 		  USER_ID=/*userId*/ " 
     +  " AND     STOP_FLG<>'1' ";
      
    
    /**
     * BIRDユーザ情報を取得する
     * @param userId ユーザＩＤ
     * @return ユーザ情報
     */   
    public List getBirdUser(String userId);

}
