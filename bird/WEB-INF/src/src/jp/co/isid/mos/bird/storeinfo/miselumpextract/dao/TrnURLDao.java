package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.TrnURL;

/**
 * 地図URL情報
 * @author xayumi
 */
public interface TrnURLDao {

    public static final Class BEAN = TrnURL.class;
    public static final String selectMapURL_ARGS = "companyCd, sibuCd, closeFlg";

    /**
     * 地図URL情報の検索
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectMapURL(String companyCd, String sibuCd, String closeFlg);
    
}