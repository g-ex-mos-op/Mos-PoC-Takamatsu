/*
 * 作成日: 2006/04/13
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaffMise;

/**
 * スタッフ店舗情報
 * @author xylee
 */
public interface MstStaffMiseDao {

    public static final Class BEAN = MstStaffMise.class;
    public static final String selectMiseCode_ARGS = "companyCd, onerCd, sysDate";
    public static final String getExistOwnerCount_ARGS = "companyCd, onerCd";

    /**
     * 店舗情報の検索
     * @param String companyCd 会社コード
     * @param String onerCd    オーナーコード
     * @param String sysDate   システム日付
     * @return List
     */
    public List selectMiseCode(String companyCd, String onerCd, String sysDate);

    /**
     * オーナーコード存在判定
     * @param companyCd 企業コード
     * @param onerCd オーナーコード
     * @return オーナーコード存在有無  > 0 : 存在  = 0 : 存在しない
     */
    public int getExistOwnerCount(String companyCd, String onerCd);
}