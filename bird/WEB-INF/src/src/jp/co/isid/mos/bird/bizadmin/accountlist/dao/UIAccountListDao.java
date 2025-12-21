/*
 * 作成日: 2008/12/03
 */
package jp.co.isid.mos.bird.bizadmin.accountlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountlist.entity.UIAccountList;

/**
 * お申込内容一覧DAO
 * 
 * @author xnkusama
 */
public interface UIAccountListDao {

    public static final Class BEAN = UIAccountList.class;
    /**
     * お申込内容一覧取得(
     */
    public static final String selectAccountList_ARGS = "userId, sysDt, closeKizyunDt";

    /**
     * お申込内容一覧取得
     * @param userId
     * @param sysDt
     * @param closeKizyunDt システム日付の1年前（システム日付2009/02/26の場合、2008/02/26）
     * @return
     */
    public List selectAccountList(String userId, String sysDt, String closeKizyunDt);
    
}