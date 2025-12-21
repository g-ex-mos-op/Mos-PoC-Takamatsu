package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstStaff;


/**
 * マスターライセンス結果状況
 * @author xayumi
 */
public interface MstStaffDao {

    public static final Class BEAN = MstStaff.class;
    public static final String selectStaff_ARGS = "companyCd, sibuCd, closeFlg";
    public static final String selectMise_ARGS  = "companyCd, sibuCd, closeFlg";

    /**
     * マスターライセンス結果状況一覧の検索
     * @param  String companyCd 会社コード
     * @param  String closeFlg 店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectStaff(String companyCd, String sibuCd, String closeFlg);
    
    
    /**
     * 全店リストの検索
     * @param  String companyCd 会社コード
     * @param  String closeFlg 店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectMise(String companyCd, String sibuCd, String closeFlg);
}