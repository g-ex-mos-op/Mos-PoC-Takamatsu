package jp.co.isid.mos.bird.bizreport.netorderniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.netorderniporef.entity.TrnUriageInfo;

public interface TrnUriageInfoDao {

    public static final Class BEAN = TrnUriageInfo.class;

    public static final String getUriageDougetu_ARGS = "companyCd" +
                                                          ",userId" +
                                                          ",tenShu" +
                                                          ",dataShu" +
                                                          ",taishoKikan" +
                                                          ",kikanFrom" +
                                                          ",kikanTo" +
                                                          ",limitFlg" +
                                                          ",areaDaiFlg" +
                                                          ",taishoTenpo" +
                                                          ",dataShuKbn";

    public static final String getUriageDougetuKikan_ARGS = "companyCd" +
                                                        ",userId" +
                                                        ",tenShu" +
                                                        ",dataShu" +
                                                        ",taishoKikan" +
                                                        ",kikanFrom" +
                                                        ",kikanTo" +
                                                        ",limitFlg" +
                                                        ",areaDaiFlg" +
                                                        ",taishoTenpo" +
                                                        ",dataShuKbn";

    public static final String getUriageDouyou_ARGS = "companyCd" +
                                                        ",userId" +
                                                        ",tenShu" +
                                                        ",dataShu" +
                                                        ",taishoKikan" +
                                                        ",kikanFrom" +
                                                        ",kikanTo" +
                                                        ",limitFlg" +
                                                        ",areaDaiFlg" +
                                                        ",taishoTenpo" +
                                                        ",dataShuKbn";

    public static final String getUriageDouyouKikan_ARGS = "companyCd" +
                                                            ",userId" +
                                                            ",tenShu" +
                                                            ",dataShu" +
                                                            ",taishoKikan" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",limitFlg" +
                                                            ",areaDaiFlg" +
                                                            ",taishoTenpo" +
                                                            ",dataShuKbn";

    public static final String getUriageDougetuHosei_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDougetuHoseiKikan_ARGS = "companyCd" +
                                                                    ",userId" +
                                                                    ",tenShu" +
                                                                    ",dataShu" +
                                                                    ",taishoKikan" +
                                                                    ",kikanFrom" +
                                                                    ",kikanTo" +
                                                                    ",limitFlg" +
                                                                    ",areaDaiFlg" +
                                                                    ",taishoTenpo" +
                                                                    ",dataShuKbn";

    public static final String getUriageDougetuGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDougetuGepoKikan_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDouyouGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDouyouGepoKikan_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDougetuHoseiGepo_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";

    public static final String getUriageDougetuHoseiGepoKikan_ARGS = "companyCd" +
                                                                        ",userId" +
                                                                        ",tenShu" +
                                                                        ",dataShu" +
                                                                        ",taishoKikan" +
                                                                        ",kikanFrom" +
                                                                        ",kikanTo" +
                                                                        ",limitFlg" +
                                                                        ",areaDaiFlg" +
                                                                        ",taishoTenpo" +
                                                                        ",dataShuKbn";

// add USIäï 2018/08/13 begin --‘O”N“¯“úˆ—‚ğ’Ç‰Á
    public static final String getUriageDoujitu_ARGS = "companyCd" +
                                                       ",userId" +
                                                       ",tenShu" +
                                                       ",dataShu" +
                                                       ",taishoKikan" +
                                                       ",kikanFrom" +
                                                       ",kikanTo" +
                                                       ",limitFlg" +
                                                       ",areaDaiFlg" +
                                                       ",taishoTenpo" +
                                                       ",dataShuKbn";

    public static final String getUriageDoujituKikan_ARGS = "companyCd" +
                                                            ",userId" +
                                                            ",tenShu" +
                                                            ",dataShu" +
                                                            ",taishoKikan" +
                                                            ",kikanFrom" +
                                                            ",kikanTo" +
                                                            ",limitFlg" +
                                                            ",areaDaiFlg" +
                                                            ",taishoTenpo" +
                                                            ",dataShuKbn";

    public static final String getUriageDoujituGepo_ARGS = "companyCd" +
                                                           ",userId" +
                                                           ",tenShu" +
                                                           ",dataShu" +
                                                           ",taishoKikan" +
                                                           ",kikanFrom" +
                                                           ",kikanTo" +
                                                           ",limitFlg" +
                                                           ",areaDaiFlg" +
                                                           ",taishoTenpo" +
                                                           ",dataShuKbn";

    public static final String getUriageDoujituGepoKikan_ARGS = "companyCd" +
                                                                ",userId" +
                                                                ",tenShu" +
                                                                ",dataShu" +
                                                                ",taishoKikan" +
                                                                ",kikanFrom" +
                                                                ",kikanTo" +
                                                                ",limitFlg" +
                                                                ",areaDaiFlg" +
                                                                ",taishoTenpo" +
                                                                ",dataShuKbn";
// add USIäï 2018/08/13 end
    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ)(“ú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetu(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ)(“ú•ñ)(ŠúŠÔw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuKikan(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;
    /**
     * Œ”„ãæ“¾(x•”—p)(“¯—j)(“ú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDouyou(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;

    /**
     * Œ”„ãæ“¾(x•”—p)(“¯—j)(ŠúŠÔw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDouyouKikan(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;


    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ•â³)(“ú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuHosei(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ•â³)(ŠúŠÔw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuHoseiKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;
    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ)(Œ•ñ)(Œw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;


    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ)(Œ•ñ)(Šú•ÊŠú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuGepoKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯—j)(Œ•ñ)(Œw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDouyouGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;


    /**
     * ”„ãæ“¾(x•”—p)(“¯—j)(Œ•ñ)(Šú•ÊŠú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDouyouGepoKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ•â³)(Œ•ñ)(Œw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuHoseiGepo(String companyCd
                                        , String userId
                                        , String tenShu
                                        , String dataShu
                                        , String taishoKikan
                                        , String kikanFrom
                                        , String kikanTo
                                        , boolean limitFlg
                                        , String areaDaiFlg
                                        , String taishoTenpo
                                        , String dataShuKbn) ;


    /**
     * ”„ãæ“¾(x•”—p)(“¯Œ•â³)(Œ•ñ)(Šú•ÊŠú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDougetuHoseiGepoKikan(String companyCd
                                            , String userId
                                            , String tenShu
                                            , String dataShu
                                            , String taishoKikan
                                            , String kikanFrom
                                            , String kikanTo
                                            , boolean limitFlg
                                            , String areaDaiFlg
                                            , String taishoTenpo
                                            , String dataShuKbn) ;
// add USIäï 2018/08/13 begin --‘O”N“¯“úˆ—‚ğ’Ç‰Á
    /**
     * Œ”„ãæ“¾(x•”—p)(“¯“ú)(“ú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDoujitu(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;

    /**
     * Œ”„ãæ“¾(x•”—p)(“¯“ú)(ŠúŠÔw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDoujituKikan(String companyCd
                                , String userId
                                , String tenShu
                                , String dataShu
                                , String taishoKikan
                                , String kikanFrom
                                , String kikanTo
                                , boolean limitFlg
                                , String areaDaiFlg
                                , String taishoTenpo
                                , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯“ú)(Œ•ñ)(Œw’è)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDoujituGepo(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;

    /**
     * ”„ãæ“¾(x•”—p)(“¯“ú)(Œ•ñ)(Šú•ÊŠú•ñ)
     * @param companyCd
     * @param taishoTenpo
     * @param taishoKikan
     * @param tenShu
     * @param kikanFrom
     * @param kikanTo
     * @param areaDaiFlg
     * @param limitFlg
     * @return
     */
    public List getUriageDoujituGepoKikan(String companyCd
                                    , String userId
                                    , String tenShu
                                    , String dataShu
                                    , String taishoKikan
                                    , String kikanFrom
                                    , String kikanTo
                                    , boolean limitFlg
                                    , String areaDaiFlg
                                    , String taishoTenpo
                                    , String dataShuKbn) ;
// add USIäï 2018/08/13 end
}