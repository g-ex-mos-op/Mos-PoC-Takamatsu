/*
 * 作成日: 2006/2/20
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserCompany;

/**
 * ユーザ所属会社の新規登録
 * @author itamoto
 */
public interface MstUserCompanyDao {

    public static final Class BEAN = MstUserCompany.class;
    public static final String update_PERSISTENT_PROPS 
    = "userId, rCompanyCd, zokuseiKbn, firstUser,"
        + "firstPgm, firstTmsp, lastUser, lastPgm";
    public static final String deleteByUserId_SQL = "delete from BM03USCP where USER_ID = ?";

    /**
     * ユーザ所属会社の新規登録(insertUserCompany)
     * @param mstUserCompany  エンティティ
     * @return int なし
     */
    public int insert(MstUserCompany mstUserCompany);
    
	public int delete(MstUserCompany mstUserCompany);

    public int update(MstUserCompany mstUserCompany);

    public void deleteByUserId(String userId);
}
