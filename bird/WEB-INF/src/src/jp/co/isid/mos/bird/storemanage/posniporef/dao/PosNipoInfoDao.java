/*
 * 作成日: 2007/02/12
 */
package jp.co.isid.mos.bird.storemanage.posniporef.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.PosNipoInfo;

/**
 * POS日報情報取得
 * 
 * @author xwatanabe
 */
public interface PosNipoInfoDao {

    public static final Class BEAN = PosNipoInfo.class;
    
    public static final String getPosNipoInfoByMiseCd_ARGS    = "companyCd, startYmd, endYmd, miseCd, userId, limitFlg";
    public static final String getPosNipoInfoByOnerCd_ARGS    = "companyCd, startYmd, endYmd, onerCd, userId, limitFlg";

    /**
     * POS日報情報の取得(店舗指定)
     * @param String companyCd      会社コード
     * @param String startYmd       開始年月日
     * @param String endYmd         終了年月日
     * @param String miseCd         店コード
     * @return List
     */
    public List getPosNipoInfoByMiseCd(String companyCd, String startYmd, String endYmd, String miseCd, String userId, boolean limitFlg);

    /**
     * POS日報情報の取得(オーナー指定)
     * @param String companyCd      会社コード
     * @param String startYmd       開始年月日
     * @param String endYmd         終了年月日
     * @param String onerCd         オーナーード
     * @return List
     */
    public List getPosNipoInfoByOnerCd(String companyCd, String startYmd, String endYmd, String onerCd, String userId, boolean limitFlg);

}
