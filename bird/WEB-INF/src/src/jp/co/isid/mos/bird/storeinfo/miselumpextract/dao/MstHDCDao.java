package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstHDC;


/**
 * HDC受賞履歴
 * @author xayumi
 */
public interface MstHDCDao {

    public static final Class BEAN = MstHDC.class;
    public static final String selectHDC_ARGS = "companyCd, sibuCd, closeFlg";

    /**
     * HDC受賞履歴の検索
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectHDC(String companyCd, String sibuCd, String closeFlg);
    
}            
