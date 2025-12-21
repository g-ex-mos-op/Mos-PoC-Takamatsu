package jp.co.isid.mos.bird.storeinfo.miseref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstStaff;


/**
 * 加盟店スタッフマスタ
 * @author xayumi
 */
public interface MstStaffDao {

    public static final Class BEAN = MstStaff.class;
    public static final String selectStaff_ARGS = "companyCd, miseCd";

    /**
     * 加盟店スタッフマスタ
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectStaff(String companyCd, String miseCd);
    
}