/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.GepoInfo;

/**
 * 宅配推移表月次取得
 * 
 * @author xwatanabe
 */
public interface GepoInfoDao {

    public static final Class BEAN = GepoInfo.class;
    
    public static final String getGepoInfoHonbuList_ARGS = "companyCd, userId, tenpoShubetu,kikanStart,kikanEnd,taishoJoken,hyojiTaisho,blockCd,zenDataShubetu,limitFlg";
    public static final String getGepoInfoOnerList_ARGS  = "companyCd, userId, onerCd, kikanStart, kikanEnd, taishoJoken, zenDataShubetu, hyojiTaisho, limitFlg";
    public static final String getGepoInfoTenpoList_ARGS = "companyCd, userId, kikanStart, kikanEnd, zenDataShubetu, limitFlg";

    /**
     * 月別宅配売上推移情報(本部ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String tenpoShubetu   店舗種別
     * @param String kikanStart     表示開始年月
     * @param String kikanEnd       表示終了年月
     * @param String taishoJoken    対象条件
     * @param String hyojiTaisho    表示対象
     * @param String blockCd        ブロックコード
     * @param String zenDataShubetu 前年データ種別
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getGepoInfoHonbuList(
            String companyCd, String userId, String tenpoShubetu, String kikanStart, String kikanEnd,
            String taishoJoken, String hyojiTaisho, String blockCd, String zenDataShubetu, boolean limitFlg);

    /**
     * 月別宅配売上推移情報(オーナーの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String onerCd         オーナーコード
     * @param String kikanStart     表示開始年月
     * @param String kikanEnd       表示終了年月
     * @param String taishoJoken    対象条件
     * @param String zenDataShubetu 前年データ種別
     * @param String hyojiTaisho    表示対象
     * @param boolean limitFlg       制限区分
     * @return List
     */
    public List getGepoInfoOnerList(
            String companyCd, String userId,      String onerCd,         String kikanStart,
            String kikanEnd,  String taishoJoken, String zenDataShubetu, String hyojiTaisho, boolean limitFlg );

    /**
     * 月別宅配売上推移情報(店舗ユーザの時)の取得
     * @param String companyCd      会社コード
     * @param String userId         ユーザID
     * @param String kikanStart     表示開始年月
     * @param String kikanEnd       表示終了年月
     * @param String zenDataShubetu 前年データ種別
     * @param boolean limitFlg      制限区分
     * @return List
     */
    public List getGepoInfoTenpoList(
            String companyCd, String userId, String kikanStart, String kikanEnd, String zenDataShubetu, boolean limitFlg );

}
