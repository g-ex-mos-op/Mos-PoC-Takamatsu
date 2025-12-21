/*
 * 作成日: 2007/02/12
 */
package jp.co.isid.mos.bird.storemanage.posniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.posniporef.entity.TenpoInfo;

/**
 * オーナー対応店舗の取得
 * 
 * @author xwatanabe
 */
public interface TenpoListByOnerDao {

    public static final Class BEAN = TenpoInfo.class;
    
    public static final String getTenpoListByOner_ARGS    = "companyCd, onerCd, date, userId, limitFlg";

    /**
     * POS日報情報の取得(店舗指定)
     * @param String companyCd      会社コード
     * @param String onerCd         オーナーコード
     * @param String date           年月日
     * @return List
     */
    public List getTenpoListByOner(String companyCd, String onerCd, String date, String userId, boolean limitFlg);

}
