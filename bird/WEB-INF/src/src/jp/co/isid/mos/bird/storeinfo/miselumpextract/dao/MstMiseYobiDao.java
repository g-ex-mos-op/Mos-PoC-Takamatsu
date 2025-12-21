package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstMiseYobi;

/**
 * 店舗拡張マスタ
 * @author xayumi
 */
public interface MstMiseYobiDao {

    public static final Class BEAN = MstMiseYobi.class;
    public static final String selectMiseYobi_ARGS = "companyCd, sibuCd, closeFlg";

    /**
     * 店舗拡張マスタの検索
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectMiseYobi(String companyCd, String sibuCd, String closeFlg);
    
}