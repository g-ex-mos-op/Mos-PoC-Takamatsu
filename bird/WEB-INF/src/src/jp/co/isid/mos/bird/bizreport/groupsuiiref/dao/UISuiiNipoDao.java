/*
 * 作成日: 2006/05/29
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiNipo;


/**
 * 推移表日次データ情報検索Dao
 *
 * @author xkinu
 */
public interface UISuiiNipoDao {

    public static final Class BEAN = UISuiiNipo.class;

    public static final String select_ARGS = "sysDate, userId, userTypeCd, limitFlg" +
    		", companyCd, zennenDatashubetu, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd" +
    		", taishoYm, kikanFromAddDay, kikanToAddDay";
    /** うるう年２月用日次データ取得SQLパラメーター */
    public static final String selectLeap0229_ARGS = "sysDate, userId, userTypeCd, limitFlg" +
    		", companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd" +
    		", taishoYm";
    /**
     * 未来日用日次データ取得SQLパラメーター
     */
    public static final String selectFuture_ARGS = "sysdate, userId, userTypeCd, limitFlg" +
	", companyCd, zennenDatashubetu, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd, taishoYm" +
	", futureFrom, futureTo, zennenYm, zennenFrom, zennenTo, sysdateGetumatu, togetuGessho, zennenGessho";

    /**
     * 未来日推移表データ情報の取得
     *
     * @param sysdate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg 制限フラグ
     * @param companyCd 会社コード
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param kikanYm 対象年月 yyyyMM
     * @param furureFrom 期間開始年月 yyyyMMdd
     * @param furureTo 期間終了年月 yyyyMMdd
     * @param zennenFrom 未来日の前年月日開始日 yyyyMMdd
     * @param zennenTo 未来日の前年月日終了日 yyyyMMdd
     * @param sysdateGetumatu システム日付の月末 yyyyMMdd
     * @param togetuGessho 当月月初 yyyyMMdd
     * @param zennenGessho 前年月初 yyyyMMdd
     * @return List
     */
    public List selectFuture(
    		String sysdate
            , String userId
            , String userTypeCd
            , boolean limitFlg
            , String companyCd
            , String zennenDatashubetu
            , String tenpoShubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
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
     * 日次データ情報の取得
     *
     * @param sysdate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg 制限フラグ
     * @param companyCd 会社コード
     * @param zennenDatashubetu 前年データ種別
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param taishoYm 対象年月 yyyyMM
     * @param kikanFromAddDay 期間開始年月 yyyyMM + '01'
     * @param kikanToAddDay 期間終了年月 yyyyMM + '31'
     * @return List
     */
    public List select(
            String sysDate
            , String userId
            , String userTypeCd
            , boolean limitFlg
            , String companyCd
            , String zennenDatashubetu
            , String tenpoShubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
            , String taishoYm
            , String kikanFromAddDay
            , String kikanToAddDay
            );
    /**
     * うるう年２月用日次データ取得
     *
     * 前年データ種別が「前年同月」時のみ実行される処理です。
     *
     * @param sysdate システム日付
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg 制限フラグ
     * @param companyCd 会社コード
     * @param zennenDatashubetu 前年データ種別
     * @param tenpoShubetu 店舗種別 [全社・前年対象店・予算対象店・新店]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param blockCd ブロックコード
     * @param taishoYm 対象年月 yyyyMM
     * @return List
     */
    public List selectLeap0229(
            String sysDate
            , String userId
            , String userTypeCd
            , boolean limitFlg
            , String companyCd
            , String tenpoShubetu
            , String taishoJoken
            , String hyojiTaiso
            , String blockCd
            , String taishoYm);
}