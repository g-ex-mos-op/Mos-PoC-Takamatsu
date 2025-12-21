package jp.co.isid.mos.bird.bizreport.moscardniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.TrnUriageNipoInfo;

public interface TrnUriageNipoInfoDao {

    public static final Class BEAN = TrnUriageNipoInfo.class;
    
    public static final String getOnerUriageNipo_ARGS = "companyCd" +
                                                            ",kikanInfo" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",onerCd" +
                                                            ",dataOnerInfo";
    
    /**
     * 売上取得(オーナー用日報)
     * @param companyCd
     * @param kikanInfo
     * @param kikanFrom
     * @param kikanTo
     * @param onerCd
     * @param dataOnerInfo
     * @return
     */
    public List getOnerUriageNipo(String companyCd
                                  ,String kikanInfo
                                  ,String kikanFrom
                                  ,String kikanTo
                                  ,String onerCd
                                  ,String dataOnerInfo);
    /**
     * 日次売上店舗一覧用 検索処理パラメーター
     */
    public static final String selectMiseList_ARGS = 
    	"userType,userId,limitFlg" +
    	",companyCd" +
    	",tenpoShu,dataShu," +
        ",taishoKikan,kikanFrom,kikanTo" +
        ",areaDaiFlg" +
        ",taishoTenpo" +
        ",taishoJoken,hyojiTaisho,svCd";
    
    /**
     * 日次売上店舗一覧用 検索処理
     * 
     * @param userType      ユーザータイプコード
     * @param userId        ユーザーID
     * @param limitFlg      支部制限フラグ
     * @param companyCd     会社コード
     * @param tenpoShu      店舗種別
     * @param dataShu 前年データ種別
     * @param taishoKikan   対象期間
     * @param kikanFrom     期間指定FROM(対象期間がDAY1の場合は期間指定日を設定)
     * @param kikanTo       期間指定TO
     * @param areaDaiFlg    エリア大フラグ
     * @param taishoTenpo   対象店舗（全店・FC・RC）
     * @param taishoJoken   対象条件
     * @param hyojiTaisho　 表示対象コード(本部コード・エリアコード・支部コードetc...)
     * 
     * @param svCd          SVコード 2008/12/09追加 SV対応
     * 
     * @return
     */
    public List selectMiseList(String userType, String userId, boolean limitFlg
    		, String companyCd
    		, String tenpoShu, String dataShu
    		, String taishoKikan, String kikanFrom, String kikanTo
    		, String areaDaiFlg,  String taishoTenpo
    		, String taishoJoken,  String hyojiTaisho
            , String svCd);

}
