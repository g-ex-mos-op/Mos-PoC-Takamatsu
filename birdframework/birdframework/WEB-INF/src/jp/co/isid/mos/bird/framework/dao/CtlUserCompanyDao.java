/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlUserCompany;


/**
 * ユーザ所属会社情報
 * @author xnkusama
 */
public interface CtlUserCompanyDao {

    public Class BEAN = CtlUserCompany.class;

    public static final String getUserCompanyList_ARGS = "userId";
    
    /**
     * 指定ユーザーの所属会社一覧取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserCompanyList(String userId);

}
