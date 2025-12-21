/*
 * 作成日: 2006/05/29
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiNipo;


/**
 * サイト区分やま用推移表日次データ情報検索Dao
 *
 * 予算はオーナー予算を取得します。
 *
 * 作成日:2011/01/20
 * @author xkinu
 *
 */
public interface UISuiiNipoOnerDao {

    public static final Class BEAN = UISuiiNipo.class;

    public static final String select_ARGS = "sysDate, userId, userTypeCd, onerCd" +
    		", companyCd, taishoJoken, hyojiTaisho, zennenDatashubetu" +
    		", kikanYm";
    public static final String selectFuture_ARGS = "sysDate, userId, userTypeCd, onerCd" +
    		", companyCd, taishoJoken, hyojiTaisho, zennenDatashubetu" +
    		", taishoYm, futureFrom, futureTo, zennenYm, zennenFrom, zennenTo " +
    		", sysdateGetumatu, togetuGessho, zennenGessho";
    /** うるう年２月用日次データ取得SQLパラメーター */
    public static final String selectLeap0229_ARGS = "sysDate, userId, userTypeCd, onerCd" +
    		", companyCd, taishoJoken, hyojiTaisho" +
    		", taishoYm";

    /**
     * 未来日推移表データ情報の取得
     *
     * @param sysdate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param onerCd オーナーコード
     * @param companyCd 会社コード
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param kikanYm 対象年月 yyyyMM
     * @param furureFrom 期間開始年月日 yyyyMMdd
     * @param furureTo 期間終了年月日 yyyyMMdd
     * @param zennenFrom 未来日の前年月日開始日 yyyyMMdd
     * @param zennenTo 未来日の前年月日終了日 yyyyMMdd
     * @param sysdateGetumatu システム日付の月末 yyyyMMdd
     * @param togetuGessho 当月月初 yyyyMMdd
     * @param zennenGessho 前年月初 yyyyMMdd
     * @return List
     */
    public List selectFuture(
    		String sysDate
            , String userId
            , String userTypeCd
            , String onerCd
            , String companyCd
            , String taishoJoken
            , String hyojiTaiso
            , String zennenDatashubetu
            , String taishoYm
            , String furureFrom
            , String furureTo
            , String zennenYm
            , String zennenFrom
            , String zennenTo
            , String sysdateGetumatu
            , String togetuGessho
            , String zennenGessho
            );
    /**
     * 推移表データ情報の取得
     *
     * サイト区分が”やま”のログインユーザー時に実行される検索SQL実行処理です。
     *
     * @param sysDate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param onerCd オーナーコード
     * @param companyCd 会社コード
     * @param zennenDatashubetu 前年データ種別 [前年同日・前年同月・前年同曜]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param kikanYm 対象年月 yyyyMM
     * @return List
     */
    public List select(
            String sysDate
            , String userId
            , String userTypeCd
            , String onerCd
            , String companyCd
            , String taishoJoken
            , String hyojiTaiso
            , String zennenDatashubetu
            , String kikanYm);
    /**
     * うるう年２月用日次データ取得
     * 店舗種別全店時うるう年2月用推移表データ情報の取得
     *
     * @param sysdate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param onerCd 制限フラグ
     * @param companyCd 会社コード
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param taishoYm 対象年月 yyyyMM
     * @return List
     */
    public List selectLeap0229(
            String sysDate
            , String userId
            , String userTypeCd
            , String onerCd
            , String companyCd
            , String taishoJoken
            , String hyojiTaiso
            , String taishoYm);


}