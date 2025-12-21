/*
 * 作成日: 2006/05/29
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.dao;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiGepo;


/**
 * 推移表日次データ情報検索Dao
 * 
 * @author xkinu
 */
public interface UISuiiGepoDao {

    public static final Class BEAN = UISuiiGepo.class;

    public static final String select_ARGS = "sysDate, userId, userTypeCd" +
	", companyCd, tenpoShubetu, zennenDatashubetu" +
	", taishoJoken, hyojiTaisho, blockCd, kikanFrom, kikanTo, limitFlg";
    public static final String selectFuture_ARGS = "sysDate, userId, userTypeCd" +
    	", companyCd, tenpoShubetu, zennenDatashubetu" +
    	", taishoJoken, hyojiTaisho, blockCd, kikanFrom, futureFrom, futureTo, zennenFrom, zennenTo, limitFlg";
    public static final String selectTogetuYosan_ARGS = "sysDate, userId, userTypeCd" +
	", companyCd, tenpoShubetu" +
	", taishoJoken, hyojiTaisho, blockCd, togetuYm, limitFlg";
    /**
     * 未来日推移表データ情報の取得
     * 
     * @param sysDate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param companyCd 会社コード
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param kikanFrom 期間開始年月 yyyyMM
     * @param furureFrom 未来開始日 yyyyMMdd
     * @param furureTo 未来終了日 yyyyMMdd
     * @param zenKikanFrom 前年期間開始日 yyyyMMdd
     * @param zenKikanTo 前年期間終了日 yyyyMMdd
     * @param limitFlg 制限フラグ
     * @return List
     */
    public List selectFuture(
    		  String sysDate
            , String userId
            , String userTypeCd
            , String companyCd
            , String tenpoShubetu
            , String zennenDatashubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
            , String kikanFrom
            , String furureFrom
            , String furureTo    
            , String zennenFrom
            , String zennenTo
            , boolean limitFlg
            );    
 
    /**
     * 月次推移表データ情報の取得
     * 
     * @param sysDate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param companyCd 会社コード
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param zennenDatashubetu 前年データ種別 [前年同日・前年同月・前年同曜]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param kikanFrom 期間開始年月 yyyyMM
     * @param kikanTo 期間終了年月 yyyyMM
     * @param limitFlg 制限フラグ
     * @return List
     */
    public List select(
    		  String sysDate
            , String userId
            , String userTypeCd
            , String companyCd
            , String tenpoShubetu
            , String zennenDatashubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
            , String kikanFrom
            , String kikanTo
            , boolean limitFlg
            );    
    /**
     * 月次推移表当月データ情報の取得
     * 
     * @param sysDate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param companyCd 会社コード
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param togetuYm 当月年月
     * @param limitFlg 制限フラグ
     * @return List
     */
    public BigDecimal selectTogetuYosan(
    		  String sysDate
            , String userId
            , String userTypeCd
            , String companyCd
            , String tenpoShubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
            , String togetuYm
            , boolean limitFlg);    


}