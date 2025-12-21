package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstChintai;


/**
 * 賃貸店舗履歴
 * @author xayumi
 */
public interface MstChintaiDao {

    public static final Class BEAN = MstChintai.class;
    public static final String selectChintai_ARGS = "companyCd, sibuCd, closeFlg";

    /**
     * 賃貸店舗履歴の検索
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectChintai(String companyCd, String sibuCd, String closeFlg);
    
}