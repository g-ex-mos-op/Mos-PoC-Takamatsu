/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.TotalMiseGepoCnt;

/**
 * (月次)店舗数取得
 * 
 * @author xwatanabe
 */
public interface TotalMiseGepoCntDao {

    public static final Class BEAN = TotalMiseGepoCnt.class;
    
    public static final String selectHonbuTotalMiseGepoCnt_ARGS = "companyCd, userId, tenpoShubetu,kikanStart,kikanEnd,taishoJoken,hyojiTaisho,blockCd,zenDataShubetu,limitFlg";
    public static final String selectOnerTotalMiseGepoCnt_ARGS  = "companyCd, userId, kikanStart, kikanEnd, taishoJoken, hyojiTaisho, limitFlg, onerCd";
    public static final String selectTenpoTotalMiseGepoCnt_ARGS = "companyCd, userId, kikanStart, kikanEnd, limitFlg";

    /**
     * 月別宅配売上推移情報(本部ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String tenpoShubetu   店舗種別
     * @param String kikanStart     表示開始年月日
     * @param String kikanEnd       表示終了年月日
     * @param String taishoJoken    対象条件
     * @param String hyojiTaisho    表示対象
     * @param String blockCd        ブロックコード
     * @param String zenDataShubetu 前年データ種別
     * @param boolean limitFlg      制限区分
     * @return List
     */
    public List selectHonbuTotalMiseGepoCnt(String companyCd, String userId, String tenpoShubetu, String kikanStart, 
            String kikanEnd, String taishoJoken, String hyojiTaisho, String blockCd, String zenDataShubetu, boolean limitFlg);

    /**
     * 月別宅配売上推移情報(オーナの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String kikanStart     表示開始年月日
     * @param String kikanEnd       表示終了年月日
     * @param String taishoJoken    対象条件
     * @param String hyojiTaisho    表示対象
     * @param boolean limitFlg      制限区分
     * @param String onerCd         オーナーコード
     * @return List
     */
    public List selectOnerTotalMiseGepoCnt(String companyCd, String userId, String kikanStart, String kikanEnd,
             String taishoJoken, String hyojiTaisho, boolean limitFlg, String onerCd);

    /**
     * 月別宅配売上推移情報(店舗ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String kikanStart     表示開始年月日
     * @param String kikanEnd       表示終了年月日
     * @param boolean limitFlg      制限区分
     * @return List
     */
    public List selectTenpoTotalMiseGepoCnt(String companyCd, String userId, String kikanStart, String kikanEnd, boolean limitFlg);

}
