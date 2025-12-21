package jp.co.isid.mos.bird.analysis.sibuaverage.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.entity.UISuiiData;

/**
 * 支部平均比較情報DAOクラス
 * 
 * @author xnkusama
 * 更新日:2012/11/01 検索条件変更対応 集計区分の条件を追加しました。
 */
public interface UISuiiDataDao {

    /** 売上情報エンティティクラス */
    public static final Class BEAN = UISuiiData.class;

    /** パラメータ：検索 */
    public static final String selectNipo_ARGS = "companyCd, taishoJoken, hyojiTaisho, shukeiKbn, sibuCd, taishoKikanFrom, taishoKikanTo, zenDataShu, limitFlg, userTypeCd, userId";
    /** パラメータ：検索 */
    public static final String selectGepo_ARGS = "companyCd, taishoJoken, hyojiTaisho, shukeiKbn, sibuCd, taishoYm, zenDataShu, limitFlg, userTypeCd, userId";

    /**
     * 検索
     * 
     * 2012/11/02
     * 
     * @param companyCd
     * @param taishoJoken
     * @param hyojiTaisho
     * @param shukeiKbn
     * @param sibuCd
     * @param taishoKikanFrom
     * @param taishoKikanTo
     * @param zenDataShu
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectNipo(
                    String companyCd,
                    String taishoJoken,
                    String hyojiTaisho,
                    String shukeiKbn,
                    String sibuCd,
                    String taishoKikanFrom,
                    String taishoKikanTo,
                    String zenDataShu,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);
    /**
     * 月合計検索
     * 営業日報の月次検索時と同じ月平均値を取得します。
     * 
     * @param companyCd
     * @param taishoJoken
     * @param hyojiTaisho
     * @param shukeiKbn
     * @param sibuCd
     * @param taishoYm
     * @param zenDataShu
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectGepo(
                    String companyCd,
                    String taishoJoken,
                    String hyojiTaisho,
                    String shukeiKbn,
                    String sibuCd,
                    String taishoYm,
                    String zenDataShu,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);
}