package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIMiseListInfo;

/**
 * 
 * @author xnarita
 * @modifire xkinu 2007/06/11 予算登録2次対応(クローズ店の予算も表示・出力する対応)
 *
 */
public interface UIMiseListInfoDao {

    public static final Class BEAN = UIMiseListInfo.class;

    public static final String getNipoOnerInfo_ARGS = "companyCd" +
														    ",onerCd" +
														    ",dataShu" +
														    ",taishoKikan" +
														    ",kikanFrom" +
														    ",kikanTo";
    
    public static final String getGepoOnerInfo_ARGS = "companyCd" +
														    ",onerCd" +
														    ",dataShu" +
														    ",taishoKikan" +
														    ",kikanFrom" +
														    ",kikanTo";

    /** SQLメソッド引数の定数:日次 */
    public static final String selectNipo_ARGS =  
    	"userTypeCd, limitFlg, userId" +
				",companyCd, tenpoShubetu, shukeiKbn" +
					",taishoTenpo" +
						",taishoKikan, kikanFrom, kikanTo" +
							",zennenDataShubetu" +
								",taishoJoken,hyojiTaishoCd" +
									",yagoFlg, yagoList, svCd";

    /** SQLメソッド引数の定数：月次 */
    public static final String selectGepo_ARGS = 
    	"userTypeCd, limitFlg, userId" +
				",companyCd, tenpoShubetu, shukeiKbn" +
					",taishoTenpo" +
						",taishoKikan, kikanFrom, kikanTo" +
							",zennenDataShubetu" +
								",taishoJoken,hyojiTaishoCd" +
									",yagoFlg, yagoList, svCd";

    /**
     * オーナー対象店舗の取得
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @return
     */
    public List getNipoOnerInfo( String companyCd
						            , String onerCd
						            , String dataShu
						            , String taishoKikan
						            , String kikanFrom
						            , String kikanTo);
    
    /**
     * オーナー対象店舗の取得
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @return
     */
    public List getGepoOnerInfo( String companyCd
						            , String onerCd
						            , String dataShu
						            , String taishoKikan
						            , String kikanFrom
						            , String kikanTo);
    /**
     * 日次用店舗一覧検索処理
     * 
     * @author xkinu 2007/06/11
     * 
     * @param userTypeCd    ユーザータイプコード
     * @param limitFlg      支部制限判断フラグ
     * @param userId        ユーザーＩＤ
     * 
     * @param companyCd     会社コード
     * @param tenpoShubetu  店舗種別(全店・前年対象店・予算対象店・新店)
     * @param shukeiKbn     集計区分エリア大判断フラグ
     * @param taishoTenpo   対象店舗(全店・FCのみ・RCのみ)
     * 
     * @param taishoKikan   対象期間
     * @param kikanFrom     期間FROM
     * @param kikanTo       期間TO
     * @param zennenDataShubetu 前年データ種別
     * @param taishoJoken   対象条件(セグメントor事業or本部or営業エリアor支部)
     * @param hyojiTaishoCd 表示対象(対象条件の指定コード)
     * @param yagoFlg       屋号フラグ
     * @param yagoList      屋号対象業態区分リスト
     * 
     * @param svCd          SVコード 2008/12/09追加 SV対応
     * @return
     */
    public List selectNipo( String userTypeCd, boolean limitFlg, String userId
						   , String companyCd, String tenpoShubetu, String shukeiKbn
						   , String taishoTenpo
						   , String taishoKikan, String kikanFrom, String kikanTo
						   , String zennenDataShubetu
						   , String taishoJoken, String hyojiTaishoCd
						   , boolean yagoFlg, List yagoList
                           , String svCd);
    /**
     * 月次用店舗一覧検索処理
     * 
     * @param userTypeCd    ユーザータイプコード
     * @param limitFlg      支部制限判断フラグ
     * @param userId        ユーザーＩＤ
     * 
     * @param companyCd     会社コード
     * @param tenpoShubetu  店舗種別(全店・前年対象店・予算対象店・新店)
     * @param shukeiKbn     集計区分エリア大判断フラグ
     * @param taishoTenpo   対象店舗(全店・FCのみ・RCのみ)
     * 
     * @param taishoKikan   対象期間
     * @param kikanFrom     期間FROM
     * @param kikanTo       期間TO
     * @param zennenDataShubetu 前年データ種別
     * @param taishoJoken   対象条件(セグメントor事業or本部or営業エリアor支部)
     * @param hyojiTaishoCd 表示対象(対象条件の指定コード)
     * @param yagoFlg       屋号フラグ
     * @param yagoList      屋号対象業態区分リスト
     * 
     * @param svCd          SVコード 2008/12/09追加 SV対応
     * @return
     */
    public List selectGepo( String userTypeCd, boolean limitFlg, String userId
						   , String companyCd, String tenpoShubetu, String shukeiKbn
						   , String taishoTenpo
						   , String taishoKikan, String kikanFrom, String kikanTo
						   , String zennenDataShubetu
						   , String taishoJoken, String hyojiTaishoCd
						   , boolean yagoFlg, List yagoList
                           , String svCd);

}

