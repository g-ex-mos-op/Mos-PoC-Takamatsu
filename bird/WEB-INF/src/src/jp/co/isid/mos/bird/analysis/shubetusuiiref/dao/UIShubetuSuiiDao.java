package jp.co.isid.mos.bird.analysis.shubetusuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.entity.UIShubetuSuii;

/**
 * 種別売上推移日次DAOクラス
 * 
 * @author xwatanabe
 */
public interface UIShubetuSuiiDao {

    /** 店舗情報エンティティクラス */
    public static final Class BEAN = UIShubetuSuii.class;

    /** 日次種別売上推移データ取得のパラメータ */
    public static final String selectNitiData_ARGS = "companyCd, sibuCd, miseCd, targetDtFrom, targetDtTo, tenpoShu, taishoJoken, zenDataShu, limitFlg, userTypeCd, userId";
    /** 月次種別売上推移データ取得のパラメータ */
    public static final String selectGepoData_ARGS = "companyCd, sibuCd, miseCd, targetDtFrom, targetDtTo, tenpoShu, taishoJoken, zenDataShu, limitFlg, userTypeCd, userId";
    /** 月次種別売上推移データ店舗数取得のパラメータ */
    public static final String selectGepoMiseCount_ARGS = "companyCd, sibuCd, miseCd, targetDtFrom, targetDtTo, tenpoShu, taishoJoken, zenDataShu, limitFlg, userTypeCd, userId";

    /**
     * 日次データ取得
     * @param companyCd
     * @param sibuCd
     * @param miseCd
     * @param targetDtFrom
     * @param targetDtTo
     * @param tenpoShu
     * @param taishoJoken
     * @param zenDataShu
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectNitiData(
                    String companyCd,
                    String sibuCd,
                    String miseCd,
                    String targetDtFrom,
                    String targetDtTo,
                    String tenpoShu,
                    String taishoJoken,
                    String zenDataShu,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);

    /**
     * 月次データ取得
     * @param companyCd
     * @param sibuCd
     * @param miseCd
     * @param targetDtFrom
     * @param targetDtTo
     * @param tenpoShu
     * @param taishoJoken
     * @param zenDataShu
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectGepoData(
                    String companyCd,
                    String sibuCd,
                    String miseCd,
                    String targetDtFrom,
                    String targetDtTo,
                    String tenpoShu,
                    String taishoJoken,
                    String zenDataShu,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);

    /**
     * 月次データ店舗数取得
     * @param companyCd
     * @param sibuCd
     * @param miseCd
     * @param targetDtFrom
     * @param targetDtTo
     * @param tenpoShu
     * @param taishoJoken
     * @param zenDataShu
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public UIShubetuSuii selectGepoMiseCount(
                    String companyCd,
                    String sibuCd,
                    String miseCd,
                    String targetDtFrom,
                    String targetDtTo,
                    String tenpoShu,
                    String taishoJoken,
                    String zenDataShu,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);

}