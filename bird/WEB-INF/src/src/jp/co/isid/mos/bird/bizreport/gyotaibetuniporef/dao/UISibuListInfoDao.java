package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UISibuListInfo;

public interface UISibuListInfoDao {

    public static final Class BEAN = UISibuListInfo.class;
    
    public static final String getNipoTenpoCount_ARGS = "companyCd" +
													    ",userId" +
													    ",tenShu" +
													    ",shukeiKbn" +
													    ",dataShu" +
													    ",taishoKikan" +
													    ",kikanFrom" +
													    ",kikanTo" +
													    ",limitFlg" + 
													    ",areaDaiFlg" +
													    ",taishoTenpo" +
													    ",dataShuKbn";
    
    public static final String getGepoTenpoCount_ARGS = "companyCd" +
													    ",userId" +
													    ",tenShu" +
													    ",shukeiKbn" +
													    ",dataShu" +
													    ",taishoKikan" +
													    ",kikanFrom" +
													    ",kikanTo" +
													    ",limitFlg" + 
													    ",areaDaiFlg" +
													    ",taishoTenpo" +
													    ",dataShuKbn";
    
    /**
     * 対象店舗数を取得
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
    public int getNipoTenpoCount( String companyCd
						            , String userId
						            , String tenShu
						            , String shukeiKbn
						            , String dataShu
						            , String taishoKikan
						            , String kikanFrom
						            , String kikanTo
						            , boolean limitFlg
						            , String areaDaiFlg 
						            , String taishoTenpo
						            , String dataShuKbn);
    
    /**
     * 対象店舗数を取得
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
    public int getGepoTenpoCount( String companyCd
						            , String userId
						            , String tenShu
						            , String shukeiKbn
						            , String dataShu
						            , String taishoKikan
						            , String kikanFrom
						            , String kikanTo
						            , boolean limitFlg
						            , String areaDaiFlg 
						            , String taishoTenpo
						            , String dataShuKbn);
    
    /** SQLメソッド引数の定数:日次 */
    public static final String selectNipo_ARGS =  
    	"userTypeCd, limitFlg, userId" +
		",companyCd, tenpoShubetu, shukeiKbn" +
			",taishoTenpo" +
				",taishoKikan, kikanFrom, kikanTo" +
					",zennenDataShubetu" +
						",taishoJoken,hyojiTaishoCd";

    /** SQLメソッド引数の定数：月次 */
    public static final String selectGepo_ARGS = 
    	"userTypeCd, limitFlg, userId" +
			",companyCd, tenpoShubetu, shukeiKbn" +
				",taishoTenpo" +
					",taishoKikan, kikanFrom, kikanTo" +
						",zennenDataShubetu" +
							",taishoJoken,hyojiTaishoCd";

    /**
     * 日次用支部一覧検索処理
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
     * @return
     */
    public List selectNipo( String userTypeCd, boolean limitFlg, String userId
						   , String companyCd, String tenpoShubetu, String shukeiKbn
						   , String taishoTenpo
						   , String taishoKikan, String kikanFrom, String kikanTo
						   , String zennenDataShubetu);
    /**
     * 月次用支部一覧検索処理
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
     * @return
     */
    public List selectGepo( String userTypeCd, boolean limitFlg, String userId
						   , String companyCd, String tenpoShubetu, String shukeiKbn
						   , String taishoTenpo
						   , String taishoKikan, String kikanFrom, String kikanTo
						   , String zennenDataShubetu);
}