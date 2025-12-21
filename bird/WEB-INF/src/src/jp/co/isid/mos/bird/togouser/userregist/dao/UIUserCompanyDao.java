/*
 * 作成日: 2008/11/11
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserCompany;

/**
 * @author K.Nihonyanagi
 *
 */
public interface UIUserCompanyDao {
	
	public static final Class BEAN = UIUserCompany.class;
	
	public static final String getCltUserCompany_ARGS = "user_id";
//    public static final String updateUserCompany_PERSISTENT_PROPS
//                                  = "userId, rCompanyCd, zokuseiKbn, firstUser,"
//                                  + "firstPgm, firstTmsp, lastUser, lastPgm";
    public static final String deleteUserCompanyByUserId_SQL = "delete from BM03USCP where USER_ID = ?";

    public List getCltUserCompany(String user_id);
	
	public void deleteUserCompany(UIUserCompany uIUserCompany);

    public void insertUserCompany(UIUserCompany uIUserCompany);

    public void updateUserCompany(UIUserCompany uIUserCompany);

    public void deleteUserCompanyByUserId(String userId);
}
