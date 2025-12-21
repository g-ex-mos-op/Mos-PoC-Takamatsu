package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnSibuUriageNipoSegRelate;

public interface TrnUriageSegInfoDao {

    public static final Class BEAN  = TrnSibuUriageNipoSegRelate.class;

    public static final String getDougetuSegUriage_ARGS = "companyCd" +
                                                      ",userId" +
                                                      ",tenShu" +
                                                      ",dataShu" +
                                                      ",taishoKikan" +
                                                      ",kikanFrom" +
                                                      ",kikanTo" +
                                                      ",limitFlg" +
                                                      ",areaDaiFlg" +
                                                      ",taishoTenpo" +
                                                      ",dataShuKbn" +
                                                      ",segmentList";

    public static final String getDougetuSegUriageKikan_ARGS = "companyCd"   +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",segmentList";

    public static final String getDouyouSegUriage_ARGS = "companyCd" +
                                                        ",userId" +
                                                        ",tenShu" +
                                                        ",dataShu" +
                                                        ",taishoKikan" +
                                                        ",kikanFrom" +
                                                        ",kikanTo" +
                                                        ",limitFlg" +
                                                        ",areaDaiFlg" +
                                                        ",taishoTenpo" +
                                                        ",dataShuKbn" +
                                                        ",segmentList";

    public static final String getDouyouSegUriageKikan_ARGS = "companyCd" +
                                                            ",userId" +
                                                            ",tenShu" +
                                                            ",dataShu" +
                                                            ",taishoKikan" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",limitFlg" +
                                                            ",areaDaiFlg" +
                                                            ",taishoTenpo" +
                                                            ",dataShuKbn" +
                                                            ",segmentList";

    public static final String getDougetuHoseiSegUriage_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",segmentList";

    public static final String getDougetuHoseiSegUriageKikan_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",segmentList";

    public static final String getDougetuSegUriageGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",segmentList";

    public static final String getDougetuSegUriageGepoKikan_ARGS = "companyCd" +
                                                                    ",userId" +
                                                                    ",tenShu" +
                                                                    ",dataShu" +
                                                                    ",taishoKikan" +
                                                                    ",kikanFrom" +
                                                                    ",kikanTo" +
                                                                    ",limitFlg" +
                                                                    ",areaDaiFlg" +
                                                                    ",taishoTenpo" +
                                                                    ",dataShuKbn" +
                                                                    ",segmentList";

    public static final String getDouyouSegUriageGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn" +
                                                                ",segmentList";

    public static final String getDouyouSegUriageGepoKikan_ARGS = "companyCd" +
                                                                    ",userId" +
                                                                    ",tenShu" +
                                                                    ",dataShu" +
                                                                    ",taishoKikan" +
                                                                    ",kikanFrom" +
                                                                    ",kikanTo" +
                                                                    ",limitFlg" +
                                                                    ",areaDaiFlg" +
                                                                    ",taishoTenpo" +
                                                                    ",dataShuKbn" +
                                                                    ",segmentList";

    public static final String getDougetuHoseiSegUriageGepo_ARGS = "companyCd" +
                                                                        ",userId" +
                                                                        ",tenShu" +
                                                                        ",dataShu" +
                                                                        ",taishoKikan" +
                                                                        ",kikanFrom" +
                                                                        ",kikanTo" +
                                                                        ",limitFlg" +
                                                                        ",areaDaiFlg" +
                                                                        ",taishoTenpo" +
                                                                        ",dataShuKbn" +
                                                                        ",segmentList";

    public static final String getDougetuHoseiSegUriageGepoKikan_ARGS = "companyCd" +
                                                                        ",userId" +
                                                                        ",tenShu" +
                                                                        ",dataShu" +
                                                                        ",taishoKikan" +
                                                                        ",kikanFrom" +
                                                                        ",kikanTo" +
                                                                        ",limitFlg" +
                                                                        ",areaDaiFlg" +
                                                                        ",taishoTenpo" +
                                                                        ",dataShuKbn" +
                                                                        ",segmentList";

// add USI蔡 2018/08/10 begin --前年同日を追加
    public static final String getDoujituSegUriage_ARGS = "companyCd" +
                                                          ",userId" +
                                                          ",tenShu" +
                                                          ",dataShu" +
                                                          ",taishoKikan" +
                                                          ",kikanFrom" +
                                                          ",kikanTo" +
                                                          ",limitFlg" +
                                                          ",areaDaiFlg" +
                                                          ",taishoTenpo" +
                                                          ",dataShuKbn" +
                                                          ",segmentList";

    public static final String getDoujituSegUriageGepo_ARGS = "companyCd" +
                                                              ",userId" +
                                                              ",tenShu" +
                                                              ",dataShu" +
                                                              ",taishoKikan" +
                                                              ",kikanFrom" +
                                                              ",kikanTo" +
                                                              ",limitFlg" +
                                                              ",areaDaiFlg" +
                                                              ",taishoTenpo" +
                                                              ",dataShuKbn" +
                                                              ",segmentList";

    public static final String getDoujituSegUriageKikan_ARGS = "companyCd" +
                                                               ",userId" +
                                                               ",tenShu" +
                                                               ",dataShu" +
                                                               ",taishoKikan" +
                                                               ",kikanFrom" +
                                                               ",kikanTo" +
                                                               ",limitFlg" +
                                                               ",areaDaiFlg" +
                                                               ",taishoTenpo" +
                                                               ",dataShuKbn" +
                                                               ",segmentList";

    public static final String getDoujituSegUriageGepoKikan_ARGS = "companyCd" +
                                                                   ",userId" +
                                                                   ",tenShu" +
                                                                   ",dataShu" +
                                                                   ",taishoKikan" +
                                                                   ",kikanFrom" +
                                                                   ",kikanTo" +
                                                                   ",limitFlg" +
                                                                   ",areaDaiFlg" +
                                                                   ",taishoTenpo" +
                                                                   ",dataShuKbn" +
                                                                   ",segmentList";
 // add USI蔡 2018/08/10 end
    /**
     * セグメントごとの売上を取得する(同月)(日別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuSegUriage(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同月)(期間指定)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuSegUriageKikan(String companyCd
                                        , String userId
                                        , String tenShu
                                        , String dataShu
                                        , String taishoKikan
                                        , String kikanFrom
                                        , String kikanTo
                                        , boolean limitFlg
                                        , String areaDaiFlg
                                        , String taishoTenpo
                                        , String dataShuKbn
                                        , List segmentList);

    /**
     * セグメントごとの売上を取得する(同曜)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDouyouSegUriage(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);


    /**
     * セグメントごとの売上を取得する(同曜)(期間指定)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDouyouSegUriageKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同月補正)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuHoseiSegUriage(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同月補正)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuHoseiSegUriageKikan(String companyCd
                                            , String userId
                                            , String tenShu
                                            , String dataShu
                                            , String taishoKikan
                                            , String kikanFrom
                                            , String kikanTo
                                            , boolean limitFlg
                                            , String areaDaiFlg
                                            , String taishoTenpo
                                            , String dataShuKbn
                                            , List segmentList);

    /**
     * セグメントごとの売上を取得する(同月)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuSegUriageGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);
    /**
     * セグメントごとの売上を取得する(同月)(期別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuSegUriageGepoKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同曜)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDouyouSegUriageGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);
    /**
     * セグメントごとの売上を取得する(同曜)(期別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDouyouSegUriageGepoKikan(String companyCd
                                            , String userId
                                            , String tenShu
                                            , String dataShu
                                            , String taishoKikan
                                            , String kikanFrom
                                            , String kikanTo
                                            , boolean limitFlg
                                            , String areaDaiFlg
                                            , String taishoTenpo
                                            , String dataShuKbn
                                            , List segmentList);

    /**
     * セグメントごとの売上を取得する(同月補正)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuHoseiSegUriageGepo(String companyCd
                                            , String userId
                                            , String tenShu
                                            , String dataShu
                                            , String taishoKikan
                                            , String kikanFrom
                                            , String kikanTo
                                            , boolean limitFlg
                                            , String areaDaiFlg
                                            , String taishoTenpo
                                            , String dataShuKbn
                                            , List segmentList);
    /**
     * セグメントごとの売上を取得する(同曜)(期別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDougetuHoseiSegUriageGepoKikan(String companyCd
                                                , String userId
                                                , String tenShu
                                                , String dataShu
                                                , String taishoKikan
                                                , String kikanFrom
                                                , String kikanTo
                                                , boolean limitFlg
                                                , String areaDaiFlg
                                                , String taishoTenpo
                                                , String dataShuKbn
                                                , List segmentList);

// add USI蔡 2018/08/10 begin --前年同日を追加
    /**
     * セグメントごとの売上を取得する(同日)(日報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDoujituSegUriage(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同日)(月報)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDoujituSegUriageGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同日)(期間指定)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDoujituSegUriageKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn
                                    , List segmentList);

    /**
     * セグメントごとの売上を取得する(同日)(期別)
     * @param companyCd
     * @param userId
     * @param tenShu
     * @param dataShu
     * @param taishoKikan
     * @param kikanFrom
     * @param kikanTo
     * @param limitFlg
     * @param areaDaiFlg
     * @param taishoTenpo
     * @param dataShuKbn
     * @return
     */
    public List getDoujituSegUriageGepoKikan(String companyCd
                                            , String userId
                                            , String tenShu
                                            , String dataShu
                                            , String taishoKikan
                                            , String kikanFrom
                                            , String kikanTo
                                            , boolean limitFlg
                                            , String areaDaiFlg
                                            , String taishoTenpo
                                            , String dataShuKbn
                                            , List segmentList);
// add USI蔡 2018/08/10 end
}
