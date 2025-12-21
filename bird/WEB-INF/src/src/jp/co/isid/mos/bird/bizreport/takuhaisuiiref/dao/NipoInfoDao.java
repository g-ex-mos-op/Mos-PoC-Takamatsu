/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.NipoInfo;

/**
 * 宅配推移表日次取得
 * 
 * @author xwatanabe
 */
public interface NipoInfoDao {

    public static final Class BEAN = NipoInfo.class;
    
    public static final String getNipoInfoListHonbu_ARGS = "companyCd, userId, tenpoShubetu,kikanStart,kikanEnd,taishoJoken,hyojiTaisho,blockCd,zenDataShubetu,limitFlg";
    public static final String getNipoInfoListHonbuLeapYear_ARGS = "companyCd, userId, tenpoShubetu,leapYearDt,tounenKizyunDt,taishoJoken,hyojiTaisho,blockCd,zenDataShubetu,limitFlg";
    public static final String getNipoInfoListOner_ARGS  = "companyCd, userId, onerCd, startYmd, endYmd, taishoJoken, zenDataShubetu, hyojiTaisho, limitFlg";
    public static final String getNipoInfoListOnerLeapYear_ARGS  = "companyCd, userId, onerCd, leapYearDt, tounenKizyunDt, taishoJoken, zenDataShubetu, hyojiTaisho, limitFlg";
    public static final String getNipoInfoListMise_ARGS  = "companyCd, userId, startYmd, endYmd, zenDataShubetu, miseCd, limitFlg";
    public static final String getNipoInfoListMiseLeapYear_ARGS  = "companyCd, userId, leapYearDt, tounenKizyunDt, zenDataShubetu, miseCd, limitFlg";

    /**
     * 日別宅配売上推移情報(本部ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String tenpoShubetu   店舗種別
     * @param String kikanStart     表示開始年月(日)
     * @param String kikanEnd       表示終了年月(日)
     * @param String taishoJoken    対象条件
     * @param String hyojiTaisho    表示対象
     * @param String blockCd        ブロックコード
     * @param String zenDataShubetu 前年データ種別
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getNipoInfoListHonbu(
            String companyCd, String userId, String tenpoShubetu, String kikanStart, String kikanEnd,
            String taishoJoken, String hyojiTaisho, String blockCd, String zenDataShubetu, boolean limitFlg);

    /**
     * 日別宅配売上推移情報(本部ユーザの時)の取得（閏年翌年用）
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String tenpoShubetu   店舗種別
     * @param String leapYearDt     閏年のデータを保持している年月日（200X0299）
     * @param String tounenKizyunDt 2/29データを取得する店舗の基準日（200X0228）
     * @param String taishoJoken    対象条件
     * @param String hyojiTaisho    表示対象
     * @param String blockCd        ブロックコード
     * @param String zenDataShubetu 前年データ種別
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getNipoInfoListHonbuLeapYear(
            String companyCd, String userId, String tenpoShubetu, String leapYearDt, String tounenKizyunDt,
            String taishoJoken, String hyojiTaisho, String blockCd, String zenDataShubetu, boolean limitFlg);

    /**
     * 日別宅配売上推移情報(オーナーの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String onerCd         オーナーコード
     * @param String startYmd       表示開始年月
     * @param String endYmd         表示終了年月
     * @param String taishoJoken    対象条件
     * @param String zenDataShubetu 前年データ種別
     * @param String hyojiTaisho    表示対象
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getNipoInfoListOner(
            String companyCd, String userId,      String onerCd,         String startYmd,
            String endYmd,    String taishoJoken, String zenDataShubetu, String hyojiTaisho, boolean limitFlg );

    /**
     * 日別宅配売上推移情報(オーナーの時)の取得（閏年翌年用）
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String onerCd         オーナーコード
     * @param String leapYearDt     閏年のデータを保持している年月日（200X0299）
     * @param String tounenKizyunDt 2/29データを取得する店舗の基準日（200X0228）
     * @param String taishoJoken    対象条件
     * @param String zenDataShubetu 前年データ種別
     * @param String hyojiTaisho    表示対象
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getNipoInfoListOnerLeapYear(
            String companyCd, String userId,      String onerCd,         String leapYearDt,
            String tounenKizyunDt,  String taishoJoken, String zenDataShubetu, String hyojiTaisho, boolean limitFlg );

    /**
     * 日別宅配売上推移情報(店舗ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String startYm        表示開始年月
     * @param String endYm          表示終了年月
     * @param String zenDataShubetu 前年データ種別
     * @param String miseCd         表示対象
     * @param boolean limitFlg      制限区分
     * @return List
     */
    public List getNipoInfoListMise(
            String companyCd,      String userId, String startYmd, String endYmd,
            String zenDataShubetu, String miseCd, boolean limitFlg );

    /**
     * 日別宅配売上推移情報(店舗ユーザの時)の取得（閏年翌年用）
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String leapYearDt     閏年のデータを保持している年月日（200X0299）
     * @param String tounenKizyunDt 2/29データを取得する店舗の基準日（200X0228）
     * @param String zenDataShubetu 前年データ種別
     * @param String miseCd         表示対象
     * @param boolean limitFlg      制限区分
     * @return List
     */
    public List getNipoInfoListMiseLeapYear(
            String companyCd,      String userId, String leapYearDt, String tounenKizyunDt,
            String zenDataShubetu, String miseCd, boolean limitFlg );

}
