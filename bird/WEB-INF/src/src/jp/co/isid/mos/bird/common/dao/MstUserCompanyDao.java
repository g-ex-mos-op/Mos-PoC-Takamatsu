/**
 * 
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstUserCompany;

/**
 * ユーザ所属会社DAO
 * 
 * @author xyuchida
 */
public interface MstUserCompanyDao {

    public static final Class BEAN = MstUserCompany.class;

    public static final String selectUserCompanyList_ARGS = "userId, zokuseiKbnList";

    /**
     * ユーザ所属会社リスト取得
     * 
     * @param userId ユーザID
     * @param zokuseiKbnList 属性区分リスト  = null : 条件としない
     * @return ユーザ所属会社リスト
     */
    public List selectUserCompanyList(String userId, List zokuseiKbnList);

    /**
     * ユーザ所属会社リスト取得
     * 
     * @param userId ユーザID
     * @param zokuseiKbnList 属性区分リスト  = null : 条件としない
     * @return ユーザ所属会社リスト
     */
    public List selectUserCompanyListByManage(String userId, List zokuseiKbnList);
}
