package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MSTSibuCategoryInfo;

/**
 * 支部情報
 * @author kusama
 */
public interface MSTSibuCategoryInfoDao {

    public static final Class BEAN = MSTSibuCategoryInfo.class;

    public static final String getSibuInfo_ARGS = "companyCd, userId, limit";

    /**
     * 支部情報の取得
     * @param companyCd 企業コード
     * @param userId ユーザID
     * @param limit 制限区分
     * @return List
     */
    public List getSibuInfo(String companyCd, String userId, boolean limit);
    
}