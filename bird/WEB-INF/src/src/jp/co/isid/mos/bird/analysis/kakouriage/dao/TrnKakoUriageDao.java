package jp.co.isid.mos.bird.analysis.kakouriage.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.kakouriage.entity.TrnKakoUriage;

/**
 * 過去売上情報DAOクラス
 * 
 * @author xnkusama
 */
public interface TrnKakoUriageDao {

    /** 過去売上情報エンティティクラス */
    public static final Class BEAN = TrnKakoUriage.class;

    /** 過去売上情報取得のパラメータ */
    public static final String selectKakoUriage_ARGS = "companyCd, taishoJoken, miseCd, nendoFrom, nendoTo, limitFlg, userTypeCd, userId";
    /** 過去売上最新年月取得のパラメータ */
    public static final String selectMaxYM_ARGS = "companyCd, nendo";

    /**
     * 過去売上情報取得
     * @param companyCd
     * @param taishoJoken
     * @param miseCd
     * @param nendoFrom
     * @param nendoDtTo
     * @param limitFlg
     * @param userTypeCd
     * @param userId
     * @return
     */
    public List selectKakoUriage(
                    String companyCd,
                    String taishoJoken,
                    String miseCd,
                    String nendoFrom,
                    String nendoTo,
                    boolean limitFlg,
                    String userTypeCd,
                    String userId);

    /**
     * 過去売上情報取得
     * @param companyCd
     * @param nendo
     */ 
    public TrnKakoUriage selectMaxYM(String companyCd, String nendo);
}