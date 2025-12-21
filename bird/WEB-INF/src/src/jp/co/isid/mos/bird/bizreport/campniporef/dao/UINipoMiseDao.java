/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campniporef.entity.UINipoMise;

/**
 * 店舗一覧用キャンペーン日報Dao
 * 
 * @author xkinu
 *
 */
public interface UINipoMiseDao {
    public static final Class BEAN = UINipoMise.class;
    
    public static final String select_ARGS = "targetTable, sysDate, userId, userTypeCd, limitFlg, companyCd, campId, menuTotaledKbn, tenpoShubetu, shukeiKbn, taishoKikan, taishoDtFrom, taishoDt, taishoJoken, hyojiTaisho, blockCd, zennenDataShubetu, rankKind, taishoDtFromLastDay, targetZenneClm";
    public static final String selectDays_ARGS = "targetTable, sysDate, userId, userTypeCd, limitFlg, companyCd, campId, menuTotaledKbn, tenpoShubetu, shukeiKbn, taishoKikan, taishoDtFrom, taishoDt, taishoJoken, hyojiTaisho, blockCd, zennenDataShubetu, rankKind, taishoDtFromLastDay, targetZenneClm";
    /**
     * 検索実行処理
     * 
     * @param targetTable
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param campId
     * @param menuTotaledKbn
     * @param tenpoShubetu
     * @param shukeiKbn
     * @param taishoKikan
     * @param taishoDtFrom
     * @param taishoDt
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param zennenDataShubetu
     * @param rankKind
     * @param taishoDtFromLastDay キャンペーン開始日前日の日付(yyyyMMdd)
     * @param targetZenneClm
     * @return
     */
    public List select(String targetTable, String sysDate
    		,  String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId, String menuTotaledKbn
    		, String tenpoShubetu, String shukeiKbn, String taishoKikan, String taishoDtFrom, String taishoDt
    		, String taishoJoken, String hyojiTaisho, String blockCd
    		, String zennenDataShubetu, String rankKind
    		, String taishoDtFromLastDay
    		, String targetZenneClm);
    /**
     * 検索実行処理
     * 
     * @param targetTable
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param campId
     * @param menuTotaledKbn
     * @param tenpoShubetu
     * @param shukeiKbn
     * @param taishoKikan
     * @param taishoDtFrom
     * @param taishoDt
     * @param taishoJoken
     * @param hyojiTaisho
     * @param blockCd
     * @param zennenDataShubetu
     * @param rankKind
     * @param taishoDtFromLastDay キャンペーン開始日前日の日付(yyyyMMdd)
     * @param targetZenneClm
     * @return
     */
    public List selectDays(String targetTable, String sysDate
    		,  String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId, String menuTotaledKbn
    		, String tenpoShubetu, String shukeiKbn, String taishoKikan, String taishoDtFrom, String taishoDt
    		, String taishoJoken, String hyojiTaisho, String blockCd
    		, String zennenDataShubetu, String rankKind
    		, String taishoDtFromLastDay
    		, String targetZenneClm);
}
