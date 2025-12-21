/*
 * 作成日: 2006/04/05
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;

/**
 * スタッフ情報DAO
 * @author xylee
 */
public interface MstStaffDao {

    public static final Class BEAN = MstStaff.class;
    public static final String selectStaffList_ARGS = "companyCd, onerCd";
    public static final String selectDuplicateName_ARGS = "entity";
    public static final String updateStaffLimited_ARGS = "entity";
    public static final String updateStaffLimited_PERSISTENT_PROPS 
                                    = "miseCd1, moveDt, situationKbn, retireDt, note, "
                                    + "leaveDt, returnDt, lastUser, lastPgm, lastTmsp";

    /**
     * スタッフ情報の検索
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return List
     */
    public List selectStaffList(String companyCd, String onerCd);

    /**
     * スタッフ情報の検索
     * @param staffId スタッフID
     * @return MstStaff
     */
    public MstStaff selectStaff(String staffId);

    /**
     * 同姓同名スタッフ情報の検索
     * 
     * @param MstStaff
     * @return
     */
    public List selectDuplicateName(MstStaff mstStaff);

    /**
     * スタッフ情報の更新
     * @param entity
     */
    public int updateStaff(MstStaff entity);
    
    /**
     * スタッフ情報の更新（項目限定版）
     * @param entity
     */
    public int updateStaffLimited(MstStaff entity);
    
    /**
     * スタッフ情報の追加
     * @param entity
     */
    public int insertStaff(MstStaff entity);
}