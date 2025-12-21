package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstInsentive;


/**
 * インセンティブ履歴
 * @author xayumi
 */
public interface MstInsentiveDao {

    public static final Class BEAN = MstInsentive.class;
    public static final String selectInsentiveRireki_ARGS = "companyCd, sibuCd, closeFlg";

    /**
     * インセンティブ履歴の検索
     * @param String companyCd 会社コード
     * @param String sibuCd    支部コード
     * @param String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectInsentiveRireki(String companyCd, String sibuCd, String closeFlg);
    
}            
