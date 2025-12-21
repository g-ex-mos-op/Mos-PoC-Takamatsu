/**
 * 作成日：2006/9/13
 * 参照：jp.co.isid.mos.bird.framework.logic.dao.UIBirdUserDao
 *
 */

package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UIBirdUser;


/**
 * 同一部門コードが設定されているBIRDユーザ情報（自分含む）を取得します
 * @author miyagi
 */
public interface UIBirdUserSelfDao {

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

