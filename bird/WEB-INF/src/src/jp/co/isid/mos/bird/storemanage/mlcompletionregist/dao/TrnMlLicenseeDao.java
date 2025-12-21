/*
 * 作成日: 2006/08/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.TrnMlLicensee;

/**
 * ライセンス保持者管理DAO
 * 
 * @author xyuchida
 */
public interface TrnMlLicenseeDao {

    public static final Class BEAN = TrnMlLicensee.class;

    public static final String selectLicensee_QUERY = "STAFF_ID = ?";

    public static final String updateAllClear_ARGS = "entity";

    public TrnMlLicensee selectLicensee(String staffId);

    public void insertLicensee(TrnMlLicensee entity);

    public void updateLicensee(TrnMlLicensee entity);
    /**
     * ライセンス保持者管理(BT26UPJK)情報の更新研修情報全クリア更新
     * 
     * 更新研修のみの使用
     * 更新研修全カラム対象更新処理
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int updateAllClear(TrnMlLicensee entity);
}
