package jp.co.isid.mos.bird.common.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstMise {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseKbn_COLUMN = "MISE_KBN";
    
    public static final String kenCd_COLUMN = "KEN_CD";
    
    public static final String areaDai_COLUMN = "AREA_DAI";
    
    public static final String areaSho_COLUMN = "AREA_SHO";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseNameKna_COLUMN = "MISE_NAME_KNA";
    
    public static final String miseRyakKj_COLUMN = "MISE_RYAK_KJ";
    
    public static final String miseRyakKna_COLUMN = "MISE_RYAK_KNA";
    
    public static final String misePostNo_COLUMN = "MISE_POST_NO";
    
    public static final String miseKenName_COLUMN = "MISE_KEN_NAME";
    
    public static final String miseAdrs1_COLUMN = "MISE_ADRS1";
    
    public static final String miseAdrs2_COLUMN = "MISE_ADRS2";
    
    public static final String miseAdrs3_COLUMN = "MISE_ADRS3";
    
    public static final String miseKenNamek_COLUMN = "MISE_KEN_NAMEK";
    
    public static final String miseAdrs1K_COLUMN = "MISE_ADRS1K";
    
    public static final String miseAdrs2K_COLUMN = "MISE_ADRS2K";
    
    public static final String miseAdrs3K_COLUMN = "MISE_ADRS3K";
    
    public static final String miseTel_COLUMN = "MISE_TEL";
    
    public static final String miseFax_COLUMN = "MISE_FAX";
    
    public static final String ncuNo_COLUMN = "NCU_NO";
    
    public static final String menuPtn_COLUMN = "MENU_PTN";
    
    public static final String posKbn_COLUMN = "POS_KBN";
    
    public static final String posKbnN_COLUMN = "POS_KBN_N";
    
    public static final String nohinCd_COLUMN = "NOHIN_CD";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String nohinshoKbn_COLUMN = "NOHINSHO_KBN";
    
    public static final String nohinshoCd_COLUMN = "NOHINSHO_CD";
    
    public static final String kanriKa_COLUMN = "KANRI_KA";
    
    public static final String kaihatsuKa_COLUMN = "KAIHATSU_KA";
    
    public static final String miseGrp1_COLUMN = "MISE_GRP1";
    
    public static final String miseGrp2_COLUMN = "MISE_GRP2";
    
    public static final String miseGrp3_COLUMN = "MISE_GRP3";
    
    public static final String seikyuSycl_COLUMN = "SEIKYU_SYCL";
    
    public static final String seikyuZui_COLUMN = "SEIKYU_ZUI";
    
    public static final String codKbn_COLUMN = "COD_KBN";
    
    public static final String royKbn_COLUMN = "ROY_KBN";
    
    public static final String kosenKbn_COLUMN = "KOSEN_KBN";
    
    public static final String hhKbn_COLUMN = "HH_KBN";
    
    public static final String kebunKbn_COLUMN = "KEBUN_KBN";
    
    public static final String nebikiRank_COLUMN = "NEBIKI_RANK";
    
    public static final String nyukinKbn_COLUMN = "NYUKIN_KBN";
    
    public static final String nyuTukisu_COLUMN = "NYU_TUKISU";
    
    public static final String nyuHiduke_COLUMN = "NYU_HIDUKE";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String nohinDenFlg_COLUMN = "NOHIN_DEN_FLG";
    
    public static final String nohinOldFlg_COLUMN = "NOHIN_OLD_FLG";
    
    public static final String code1_COLUMN = "CODE1";
    
    public static final String code2_COLUMN = "CODE2";
    
    public static final String code3_COLUMN = "CODE3";
    
    public static final String flg1_COLUMN = "FLG1";
    
    public static final String flg2_COLUMN = "FLG2";
    
    public static final String date1_COLUMN = "DATE1";
    
    public static final String date2_COLUMN = "DATE2";
    
    public static final String kbn1_COLUMN = "KBN1";
    
    public static final String kbn2_COLUMN = "KBN2";
    
    public static final String fcKeiyaku_COLUMN = "FC_KEIYAKU";
    
    public static final String fcKaiyaku_COLUMN = "FC_KAIYAKU";
    
    public static final String preOpenDt_COLUMN = "PRE_OPEN_DT";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String takeOverDt_COLUMN = "TAKE_OVER_DT";
    
    public static final String miseIdoF_COLUMN = "MISE_IDO_F";
    
    public static final String miseKaisoF_COLUMN = "MISE_KAISO_F";
    
    public static final String miseOldCd_COLUMN = "MISE_OLD_CD";
    
    public static final String holMon_COLUMN = "HOL_MON";
    
    public static final String holTue_COLUMN = "HOL_TUE";
    
    public static final String holWed_COLUMN = "HOL_WED";
    
    public static final String holThu_COLUMN = "HOL_THU";
    
    public static final String holFri_COLUMN = "HOL_FRI";
    
    public static final String holSat_COLUMN = "HOL_SAT";
    
    public static final String holSun_COLUMN = "HOL_SUN";
    
    public static final String holAnv_COLUMN = "HOL_ANV";
    
    public static final String tMiseKeiei_COLUMN = "T_MISE_KEIEI";
    
    public static final String tDataSinKbn_COLUMN = "T_DATA_SIN_KBN";
    
    public static final String tMiseMenseki_COLUMN = "T_MISE_MENSEKI";
    
    public static final String tKyakMenseki_COLUMN = "T_KYAK_MENSEKI";
    
    public static final String tSouSekisuu_COLUMN = "T_SOU_SEKISUU";
    
    public static final String tKyakSekisuu_COLUMN = "T_KYAK_SEKISUU";
    
    public static final String tMiseMaguchi_COLUMN = "T_MISE_MAGUCHI";
    
    public static final String tMiseChusha_COLUMN = "T_MISE_CHUSHA";
    
    public static final String tTsukoMae_COLUMN = "T_TSUKO_MAE";
    
    public static final String tTsukoUra_COLUMN = "T_TSUKO_URA";
    
    public static final String tTsukoYok_COLUMN = "T_TSUKO_YOK";
    
    public static final String tSetai500_COLUMN = "T_SETAI_500";
    
    public static final String tJinko500_COLUMN = "T_JINKO_500";
    
    public static final String tSetaiKiro_COLUMN = "T_SETAI_KIRO";
    
    public static final String tJinkoKiro_COLUMN = "T_JINKO_KIRO";
    
    public static final String tJigyouJoho_COLUMN = "T_JIGYOU_JOHO";
    
    public static final String tShoukenKbn_COLUMN = "T_SHOUKEN_KBN";
    
    public static final String tLocateKbn_COLUMN = "T_LOCATE_KBN";
    
    public static final String tMiseKeitai_COLUMN = "T_MISE_KEITAI";
    
    public static final String tToriSekkei_COLUMN = "T_TORI_SEKKEI";
    
    public static final String tToriNaiso_COLUMN = "T_TORI_NAISO";
    
    public static final String tToriKucho_COLUMN = "T_TORI_KUCHO";
    
    public static final String tToriSuido_COLUMN = "T_TORI_SUIDO";
    
    public static final String tToriChubo_COLUMN = "T_TORI_CHUBO";
    
    public static final String tOpenTmMon_COLUMN = "T_OPEN_TM_MON";
    
    public static final String tCloseTmMon_COLUMN = "T_CLOSE_TM_MON";
    
    public static final String tOpenTmTue_COLUMN = "T_OPEN_TM_TUE";
    
    public static final String tCloseTmTue_COLUMN = "T_CLOSE_TM_TUE";
    
    public static final String tOpenTmWed_COLUMN = "T_OPEN_TM_WED";
    
    public static final String tCloseTmWed_COLUMN = "T_CLOSE_TM_WED";
    
    public static final String tOpenTmThu_COLUMN = "T_OPEN_TM_THU";
    
    public static final String tCloseTmThu_COLUMN = "T_CLOSE_TM_THU";
    
    public static final String tOpenTmFri_COLUMN = "T_OPEN_TM_FRI";
    
    public static final String tCloseTmFri_COLUMN = "T_CLOSE_TM_FRI";
    
    public static final String tOpenTmSat_COLUMN = "T_OPEN_TM_SAT";
    
    public static final String tCloseTmSat_COLUMN = "T_CLOSE_TM_SAT";
    
    public static final String tOpenTmSun_COLUMN = "T_OPEN_TM_SUN";
    
    public static final String tCloseTmSun_COLUMN = "T_CLOSE_TM_SUN";
    
    public static final String tOpenTmHol_COLUMN = "T_OPEN_TM_HOL";
    
    public static final String tCloseTmHol_COLUMN = "T_CLOSE_TM_HOL";
    
    public static final String tOpenTmHlb_COLUMN = "T_OPEN_TM_HLB";
    
    public static final String tCloseTmHlb_COLUMN = "T_CLOSE_TM_HLB";
    
    public static final String telOffice_COLUMN = "TEL_OFFICE";
    
    public static final String locateKbn_COLUMN = "LOCATE_KBN";
    
    public static final String telPink_COLUMN = "TEL_PINK";
    
    public static final String miseKeitai_COLUMN = "MISE_KEITAI";
    
    public static final String telPos_COLUMN = "TEL_POS";
    
    public static final String telOther_COLUMN = "TEL_OTHER";
    
    public static final String telNotes_COLUMN = "TEL_NOTES";
    
    public static final String keiyakuFirst_COLUMN = "KEIYAKU_FIRST";
    
    public static final String keiyakuKeitai_COLUMN = "KEIYAKU_KEITAI";
    
    public static final String openTmWkd_COLUMN = "OPEN_TM_WKD";
    
    public static final String closeTmWkd_COLUMN = "CLOSE_TM_WKD";
    
    public static final String openTmSat_COLUMN = "OPEN_TM_SAT";
    
    public static final String closeTmSat_COLUMN = "CLOSE_TM_SAT";
    
    public static final String keiyakuNotes_COLUMN = "KEIYAKU_NOTES";
    
    public static final String openTmHol_COLUMN = "OPEN_TM_HOL";
    
    public static final String parkOnlyIn_COLUMN = "PARK_ONLY_IN";
    
    public static final String closeTmHol_COLUMN = "CLOSE_TM_HOL";
    
    public static final String parkOnlyOut_COLUMN = "PARK_ONLY_OUT";
    
    public static final String parkCommIn_COLUMN = "PARK_COMM_IN";
    
    public static final String openTmHlb_COLUMN = "OPEN_TM_HLB";
    
    public static final String closeTmHlb_COLUMN = "CLOSE_TM_HLB";
    
    public static final String parkCommOut_COLUMN = "PARK_COMM_OUT";
    
    public static final String seat1F_COLUMN = "SEAT_1F";
    
    public static final String toriSekkei_COLUMN = "TORI_SEKKEI";
    
    public static final String seat2F_COLUMN = "SEAT_2F";
    
    public static final String toriSekou_COLUMN = "TORI_SEKOU";
    
    public static final String seat3F_COLUMN = "SEAT_3F";
    
    public static final String seatUnder_COLUMN = "SEAT_UNDER";
    
    public static final String seatCommon_COLUMN = "SEAT_COMMON";
    
    public static final String seatOther_COLUMN = "SEAT_OTHER";
    
    public static final String prjInvest_COLUMN = "PRJ_INVEST";
    
    public static final String prjUriage_COLUMN = "PRJ_URIAGE";
    
    public static final String tax_COLUMN = "TAX";
    
    public static final String miseMenseki_COLUMN = "MISE_MENSEKI";
    
    public static final String rateTakuhai_COLUMN = "RATE_TAKUHAI";
    
    public static final String rateGaihan_COLUMN = "RATE_GAIHAN";
    
    public static final String sikiMenseki_COLUMN = "SIKI_MENSEKI";
    
    public static final String eventTime_COLUMN = "EVENT_TIME";
    
    public static final String kyakMenseki_COLUMN = "KYAK_MENSEKI";
    
    public static final String parkKei_COLUMN = "PARK_KEI";
    
    public static final String eventSpot_COLUMN = "EVENT_SPOT";
    
    public static final String eventContents_COLUMN = "EVENT_CONTENTS";
    
    public static final String accessLine_COLUMN = "ACCESS_LINE";
    
    public static final String accessStation_COLUMN = "ACCESS_STATION";
    
    public static final String accessDist_COLUMN = "ACCESS_DIST";
    
    public static final String seatKei_COLUMN = "SEAT_KEI";
    
    public static final String accessTime_COLUMN = "ACCESS_TIME";
    
    public static final String accessWay_COLUMN = "ACCESS_WAY";
    
    public static final String map_COLUMN = "MAP";
    
    public static final String photo_COLUMN = "PHOTO";
    
    public static final String holNotes_COLUMN = "HOL_NOTES";
    
    public static final String chuboMenseki_COLUMN = "CHUBO_MENSEKI";
    
    public static final String otherMenseki_COLUMN = "OTHER_MENSEKI";
    
    public static final String miseCdMoto_COLUMN = "MISE_CD_MOTO";
    
    public static final String hikitugiDt_COLUMN = "HIKITUGI_DT";
    
    public static final String hikitugiDtOpen_COLUMN = "HIKITUGI_DT_OPEN";
    
    public static final String menuType_COLUMN = "MENU_TYPE";
    
    public static final String rSakujoFlg_COLUMN = "R_SAKUJO_FLG";
    
    public static final String gyotaiChgDt_COLUMN = "GYOTAI_CHG_DT";
    
    public static final String closeReasonCd_COLUMN = "CLOSE_REASON_CD";
    
    public static final String closeReasonNote_COLUMN = "CLOSE_REASON_NOTE";
    
    public static final String sepSmokeShu_COLUMN = "SEP_SMOKE_SHU";
    
    public static final String tentai_COLUMN = "TENTAI";
    
    public static final String tentaiStartDt_COLUMN = "TENTAI_START_DT";
    
    public static final String tentaiEndDt_COLUMN = "TENTAI_END_DT";
    
    public static final String tentaiInfo_COLUMN = "TENTAI_INFO";
    
    public static final String seatSmokingKei_COLUMN = "SEAT_SMOKING_KEI";
    
    public static final String seatNonSmokingKei_COLUMN = "SEAT_NON_SMOKING_KEI";
    
    public static final String seat1FFlg_COLUMN = "SEAT_1F_FLG";
    
    public static final String seat1FSmokingIn_COLUMN = "SEAT_1F_SMOKING_IN";
    
    public static final String seat1FNonSmokingIn_COLUMN = "SEAT_1F_NON_SMOKING_IN";
    
    public static final String seat1FSmokingOut_COLUMN = "SEAT_1F_SMOKING_OUT";
    
    public static final String seat1FNonSmokingOut_COLUMN = "SEAT_1F_NON_SMOKING_OUT";
    
    public static final String seat2FFlg_COLUMN = "SEAT_2F_FLG";
    
    public static final String seat2FSmokingIn_COLUMN = "SEAT_2F_SMOKING_IN";
    
    public static final String seat2FNonSmokingIn_COLUMN = "SEAT_2F_NON_SMOKING_IN";
    
    public static final String seat2FSmokingOut_COLUMN = "SEAT_2F_SMOKING_OUT";
    
    public static final String seat2FNonSmokingOut_COLUMN = "SEAT_2F_NON_SMOKING_OUT";
    
    public static final String seat3FFlg_COLUMN = "SEAT_3F_FLG";
    
    public static final String seat3FSmokingIn_COLUMN = "SEAT_3F_SMOKING_IN";
    
    public static final String seat3FNonSmokingIn_COLUMN = "SEAT_3F_NON_SMOKING_IN";
    
    public static final String seat3FSmokingOut_COLUMN = "SEAT_3F_SMOKING_OUT";
    
    public static final String seat3FNonSmokingOut_COLUMN = "SEAT_3F_NON_SMOKING_OUT";
    
    public static final String seatUnderFlg_COLUMN = "SEAT_UNDER_FLG";
    
    public static final String seatUnderSmokingIn_COLUMN = "SEAT_UNDER_SMOKING_IN";
    
    public static final String seatUnderNonSmokingIn_COLUMN = "SEAT_UNDER_NON_SMOKING_IN";
    
    public static final String seatUnderSmokingOut_COLUMN = "SEAT_UNDER_SMOKING_OUT";
    
    public static final String seatUnderNonSmokingOut_COLUMN = "SEAT_UNDER_NON_SMOKING_OUT";
    
    public static final String seatCommonFlg_COLUMN = "SEAT_COMMON_FLG";
    
    public static final String seatCommonSmokingIn_COLUMN = "SEAT_COMMON_SMOKING_IN";
    
    public static final String seatCommonNonSmokingIn_COLUMN = "SEAT_COMMON_NON_SMOKING_IN";
    
    public static final String seatCommonSmokingOut_COLUMN = "SEAT_COMMON_SMOKING_OUT";
    
    public static final String seatCommonNonSmokingOut_COLUMN = "SEAT_COMMON_NON_SMOKING_OUT";
    
    public static final String seatOtherFlg_COLUMN = "SEAT_OTHER_FLG";
    
    public static final String seatOtherSmokingIn_COLUMN = "SEAT_OTHER_SMOKING_IN";
    
    public static final String seatOtherNonSmokingIn_COLUMN = "SEAT_OTHER_NON_SMOKING_IN";
    
    public static final String seatOtherSmokingOut_COLUMN = "SEAT_OTHER_SMOKING_OUT";
    
    public static final String seatOtherNonSmokingOut_COLUMN = "SEAT_OTHER_NON_SMOKING_OUT";
    
    public static final String accessWayNote_COLUMN = "ACCESS_WAY_NOTE";
    
    public static final String miseLeaseKCd_COLUMN = "MISE_LEASE_K_CD";
    
    public static final String keiyakuDt_COLUMN = "KEIYAKU_DT";
    
    public static final String kaiyakuDt_COLUMN = "KAIYAKU_DT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String mstVer_COLUMN = "MST_VER";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店区分
     */
    private String miseKbn;
    
    /**
     * 店都道府県ｺｰﾄﾞ
     */
    private String kenCd;
    
    /**
     * エリア大コード(支部取込コード)
     */
    private String areaDai;
    
    /**
     * エリア小コード
     */
    private String areaSho;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店名称（カナ）
     */
    private String miseNameKna;
    
    /**
     * 店略称（漢字）
     */
    private String miseRyakKj;
    
    /**
     * 店略称（カナ）
     */
    private String miseRyakKna;
    
    /**
     * 店郵便番号
     */
    private String misePostNo;
    
    /**
     * 店都道府県名称
     */
    private String miseKenName;
    
    /**
     * 店住所１
     */
    private String miseAdrs1;
    
    /**
     * 店住所２
     */
    private String miseAdrs2;
    
    /**
     * 店住所３
     */
    private String miseAdrs3;
    
    /**
     * 店都道府県名称（カナ）
     */
    private String miseKenNamek;
    
    /**
     * 店住所１（カナ）
     */
    private String miseAdrs1K;
    
    /**
     * 店住所２（カナ）
     */
    private String miseAdrs2K;
    
    /**
     * 店住所３（カナ）
     */
    private String miseAdrs3K;
    
    /**
     * 店電話番号
     */
    private String miseTel;
    
    /**
     * 店ＦＡＸ番号
     */
    private String miseFax;
    
    /**
     * ＮＣＵ番号
     */
    private String ncuNo;
    
    /**
     * メニューパターン
     */
    private String menuPtn;
    
    /**
     * ＰＯＳ区分
     */
    private String posKbn;
    
    /**
     * 新ＰＯＳ区分
     */
    private String posKbnN;
    
    /**
     * 納品先コード
     */
    private String nohinCd;
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 納品書送付先区分
     */
    private String nohinshoKbn;
    
    /**
     * 納品書送付先ｺｰﾄﾞ
     */
    private String nohinshoCd;
    
    /**
     * 管理部課ｺｰﾄﾞ
     */
    private String kanriKa;
    
    /**
     * 開発部課ｺｰﾄﾞ
     */
    private String kaihatsuKa;
    
    /**
     * 集計用コード１
     */
    private String miseGrp1;
    
    /**
     * 集計用コード２
     */
    private String miseGrp2;
    
    /**
     * 集計用コード３
     */
    private String miseGrp3;
    
    /**
     * 請求書ｻｲｸﾙ区分
     */
    private String seikyuSycl;
    
    /**
     * 災害時自動発注区分
     */
    private String seikyuZui;
    
    /**
     * 前払い区分
     */
    private String codKbn;
    
    /**
     * ロイヤルティ区分
     */
    private String royKbn;
    
    /**
     * 広告宣伝費区分
     */
    private String kosenKbn;
    
    /**
     * ＨＨ協会費区分
     */
    private String hhKbn;
    
    /**
     * 経営分析料区分
     */
    private String kebunKbn;
    
    /**
     * 値引きランク
     */
    private String nebikiRank;
    
    /**
     * 入金予定区分
     */
    private String nyukinKbn;
    
    /**
     * 入金予定月数
     */
    private String nyuTukisu;
    
    /**
     * 入金予定日付
     */
    private String nyuHiduke;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * ？
     */
    private String nohinDenFlg;
    
    /**
     * ？
     */
    private String nohinOldFlg;
    
    /**
     * コード１
     */
    private String code1;
    
    /**
     * コード２
     */
    private String code2;
    
    /**
     * コード３
     */
    private String code3;
    
    /**
     * フラグ１
     */
    private String flg1;
    
    /**
     * フラグ２
     */
    private String flg2;
    
    /**
     * 日付１
     */
    private String date1;
    
    /**
     * 日付２
     */
    private String date2;
    
    /**
     * 区分１
     */
    private String kbn1;
    
    /**
     * 区分２
     */
    private String kbn2;
    
    /**
     * ﾈｯﾄﾜｰｸ開始日
     */
    private String fcKeiyaku;
    
    /**
     * ﾈｯﾄﾜｰｸ終了日
     */
    private String fcKaiyaku;
    
    /**
     * プレオープン日
     */
    private String preOpenDt;
    
    /**
     * 店オープン日
     */
    private String openDt;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * ﾃｲｸｵｰﾊﾞｰ日
     */
    private String takeOverDt;
    
    /**
     * 店移動フラグ
     */
    private String miseIdoF;
    
    /**
     * 店改装フラグ
     */
    private String miseKaisoF;
    
    /**
     * 旧店コード
     */
    private String miseOldCd;
    
    /**
     * 月曜日休日区分
     */
    private String holMon;
    
    /**
     * 火曜日休日区分
     */
    private String holTue;
    
    /**
     * 水曜日休日区分
     */
    private String holWed;
    
    /**
     * 木曜日休日区分
     */
    private String holThu;
    
    /**
     * 金曜日休日区分
     */
    private String holFri;
    
    /**
     * 土曜日休日区分
     */
    private String holSat;
    
    /**
     * 日曜日休日区分
     */
    private String holSun;
    
    /**
     * 祝日休日区分
     */
    private String holAnv;
    
    /**
     * 経営形態
     */
    private String tMiseKeiei;
    
    /**
     * ﾃﾞｰﾀ信用度区分
     */
    private String tDataSinKbn;
    
    /**
     * 店舗面積（㎡）
     */
    private short tMiseMenseki;
    
    /**
     * 客席面積（㎡）
     */
    private short tKyakMenseki;
    
    /**
     * 総席数
     */
    private short tSouSekisuu;
    
    /**
     * 客席数
     */
    private short tKyakSekisuu;
    
    /**
     * 間口
     */
    private short tMiseMaguchi;
    
    /**
     * 駐車台数
     */
    private short tMiseChusha;
    
    /**
     * 店前通行量　
     */
    private short tTsukoMae;
    
    /**
     * 店裏通行量
     */
    private short tTsukoUra;
    
    /**
     * 店横通行量
     */
    private short tTsukoYok;
    
    /**
     * 敷地面積（㎡）
     */
    private int tSetai500;
    
    /**
     * 厨房面積（㎡）
     */
    private int tJinko500;
    
    /**
     * その他面積（㎡）
     */
    private int tSetaiKiro;
    
    /**
     * 1ｷﾛﾒｰﾄﾙ内人口
     */
    private int tJinkoKiro;
    
    /**
     * 事業計画情報
     */
    private BigDecimal tJigyouJoho;
    
    /**
     * 開発商圏構造区分
     */
    private String tShoukenKbn;
    
    /**
     * ﾛｹｰｼｮﾝ区分
     */
    private String tLocateKbn;
    
    /**
     * 店舗形態区分
     */
    private String tMiseKeitai;
    
    /**
     * 設計業者コード
     */
    private String tToriSekkei;
    
    /**
     * 内装業者コード
     */
    private String tToriNaiso;
    
    /**
     * 空調業者コード
     */
    private String tToriKucho;
    
    /**
     * 水道業者コード
     */
    private String tToriSuido;
    
    /**
     * 厨房業者コード
     */
    private String tToriChubo;
    
    /**
     * 営業開始時間（月曜）
     */
    private String tOpenTmMon;
    
    /**
     * 営業終了時間（月曜）
     */
    private String tCloseTmMon;
    
    /**
     * 営業開始時間（火曜）
     */
    private String tOpenTmTue;
    
    /**
     * 営業終了時間（火曜）
     */
    private String tCloseTmTue;
    
    /**
     * 営業開始時間（水曜）
     */
    private String tOpenTmWed;
    
    /**
     * 営業終了時間（水曜）
     */
    private String tCloseTmWed;
    
    /**
     * 営業開始時間（木曜）
     */
    private String tOpenTmThu;
    
    /**
     * 営業終了時間（木曜）
     */
    private String tCloseTmThu;
    
    /**
     * 営業開始時間（金曜）
     */
    private String tOpenTmFri;
    
    /**
     * 営業終了時間（金曜）
     */
    private String tCloseTmFri;
    
    /**
     * 営業開始時間（土曜）
     */
    private String tOpenTmSat;
    
    /**
     * 営業終了時間（土曜）
     */
    private String tCloseTmSat;
    
    /**
     * 営業開始時間（日曜）
     */
    private String tOpenTmSun;
    
    /**
     * 営業終了時間（日曜）
     */
    private String tCloseTmSun;
    
    /**
     * 営業開始時間（祝日）
     */
    private String tOpenTmHol;
    
    /**
     * 営業終了時間（祝日）
     */
    private String tCloseTmHol;
    
    /**
     * 営業開始時間（祝前）
     */
    private String tOpenTmHlb;
    
    /**
     * 営業終了時間（祝前）
     */
    private String tCloseTmHlb;
    
    /**
     * 電話番号：事務所
     */
    private String telOffice;
    
    /**
     * ロケーション区分
     */
    private String locateKbn;
    
    /**
     * 電話番号：ピンク
     */
    private String telPink;
    
    /**
     * 店舗形態区分
     */
    private String miseKeitai;
    
    /**
     * 電話番号：POS
     */
    private String telPos;
    
    /**
     * 電話番号：その他
     */
    private String telOther;
    
    /**
     * 電話番号：ノート
     */
    private String telNotes;
    
    /**
     * 物件契約：契約日
     */
    private String keiyakuFirst;
    
    /**
     * 物件契約：所有形態
     */
    private String keiyakuKeitai;
    
    /**
     * 営業開始時間（平日）
     */
    private String openTmWkd;
    
    /**
     * 営業終了時間（平日）
     */
    private String closeTmWkd;
    
    /**
     * 営業開始時間（土曜）
     */
    private String openTmSat;
    
    /**
     * 営業終了時間（土曜）
     */
    private String closeTmSat;
    
    /**
     * 物件契約：備考
     */
    private String keiyakuNotes;
    
    /**
     * 営業開始時間（日祭日）
     */
    private String openTmHol;
    
    /**
     * 駐車場：専用敷地内
     */
    private BigDecimal parkOnlyIn;
    
    /**
     * 営業終了時間（日祭日）
     */
    private String closeTmHol;
    
    /**
     * 駐車場：専用隣接
     */
    private BigDecimal parkOnlyOut;
    
    /**
     * 駐車場：共用敷地内
     */
    private BigDecimal parkCommIn;
    
    /**
     * 営業開始時間（休前日）
     */
    private String openTmHlb;
    
    /**
     * 営業終了時間（休前日）
     */
    private String closeTmHlb;
    
    /**
     * 駐車場：共用隣接
     */
    private BigDecimal parkCommOut;
    
    /**
     * 客席数：１F
     */
    private BigDecimal seat1F;
    
    /**
     * 設計業者名
     */
    private String toriSekkei;
    
    /**
     * 客席数：２F
     */
    private BigDecimal seat2F;
    
    /**
     * 施工業者名
     */
    private String toriSekou;
    
    /**
     * 客席数：３F
     */
    private BigDecimal seat3F;
    
    /**
     * 客席数：地下
     */
    private BigDecimal seatUnder;
    
    /**
     * 客席数：共用
     */
    private BigDecimal seatCommon;
    
    /**
     * 客席数：その他
     */
    private BigDecimal seatOther;
    
    /**
     * 事業計画：投資額
     */
    private BigDecimal prjInvest;
    
    /**
     * 事業計画：売上
     */
    private BigDecimal prjUriage;
    
    /**
     * 消費税
     */
    private String tax;
    
    /**
     * 店舗面積（坪）
     */
    private BigDecimal miseMenseki;
    
    /**
     * 宅配比率
     */
    private BigDecimal rateTakuhai;
    
    /**
     * 外販比率
     */
    private BigDecimal rateGaihan;
    
    /**
     * 敷地面積（坪）
     */
    private BigDecimal sikiMenseki;
    
    /**
     * イベント販売時期
     */
    private String eventTime;
    
    /**
     * 客席面積（坪）
     */
    private BigDecimal kyakMenseki;
    
    /**
     * 駐車場：総台数
     */
    private BigDecimal parkKei;
    
    /**
     * イベント販売場所
     */
    private String eventSpot;
    
    /**
     * イベント内容
     */
    private String eventContents;
    
    /**
     * 最寄り駅：線
     */
    private String accessLine;
    
    /**
     * 最寄り駅：駅
     */
    private String accessStation;
    
    /**
     * 距離
     */
    private BigDecimal accessDist;
    
    /**
     * 客席数：総客席数
     */
    private BigDecimal seatKei;
    
    /**
     * 時間
     */
    private BigDecimal accessTime;
    
    /**
     * 交通手段
     */
    private String accessWay;
    
    /**
     * 地図
     */
    private String map;
    
    /**
     * 写真
     */
    private String photo;
    
    /**
     * 店休日
     */
    private String holNotes;
    
    /**
     * 厨房面積（坪）
     */
    private BigDecimal chuboMenseki;
    
    /**
     * その他面積（坪）
     */
    private BigDecimal otherMenseki;
    
    /**
     * 引継元店コード
     */
    private String miseCdMoto;
    
    /**
     * 引継日
     */
    private String hikitugiDt;
    
    /**
     * 引継日（オープン日）
     */
    private String hikitugiDtOpen;
    
    /**
     * メニュータイプ
     */
    private String menuType;
    
    /**
     * 論理削除フラグ
     */
    private String rSakujoFlg;
    
    /**
     * 業態変更日
     */
    private String gyotaiChgDt;
    
    /**
     * クローズ理由CD
     */
    private String closeReasonCd;
    
    /**
     * クローズ理由備考
     */
    private String closeReasonNote;
    
    /**
     * 分煙種別
     */
    private String sepSmokeShu;
    
    /**
     * 転貸
     */
    private String tentai;
    
    /**
     * 転貸開始日
     */
    private String tentaiStartDt;
    
    /**
     * 転貸終了日
     */
    private String tentaiEndDt;
    
    /**
     * 転貸情報
     */
    private String tentaiInfo;
    
    /**
     * 客席数：喫煙総席数
     */
    private BigDecimal seatSmokingKei;
    
    /**
     * 客席数：禁煙総席数
     */
    private BigDecimal seatNonSmokingKei;
    
    /**
     * 1F席有無
     */
    private String seat1FFlg;
    
    /**
     * 客席数：１F喫煙席数(店内)
     */
    private BigDecimal seat1FSmokingIn;
    
    /**
     * 客席数：１F禁煙席数(店内)
     */
    private BigDecimal seat1FNonSmokingIn;
    
    /**
     * 客席数：１F喫煙席数(店外)
     */
    private BigDecimal seat1FSmokingOut;
    
    /**
     * 客席数：１F禁煙席数(店外)
     */
    private BigDecimal seat1FNonSmokingOut;
    
    /**
     * 2F席有無
     */
    private String seat2FFlg;
    
    /**
     * 客席数：２F喫煙席数(店内)
     */
    private BigDecimal seat2FSmokingIn;
    
    /**
     * 客席数：２F禁煙席数(店内)
     */
    private BigDecimal seat2FNonSmokingIn;
    
    /**
     * 客席数：２F喫煙席数(店外)
     */
    private BigDecimal seat2FSmokingOut;
    
    /**
     * 客席数：２F禁煙席数(店外)
     */
    private BigDecimal seat2FNonSmokingOut;
    
    /**
     * 3F席有無
     */
    private String seat3FFlg;
    
    /**
     * 客席数：３F喫煙席数(店内)
     */
    private BigDecimal seat3FSmokingIn;
    
    /**
     * 客席数：３F禁煙席数(店内)
     */
    private BigDecimal seat3FNonSmokingIn;
    
    /**
     * 客席数：３F喫煙席数(店外)
     */
    private BigDecimal seat3FSmokingOut;
    
    /**
     * 客席数：３F禁煙席数(店外)
     */
    private BigDecimal seat3FNonSmokingOut;
    
    /**
     * 地下席有無
     */
    private String seatUnderFlg;
    
    /**
     * 客席数：地下F喫煙席数(店内)
     */
    private BigDecimal seatUnderSmokingIn;
    
    /**
     * 客席数：地下禁煙席数(店内)
     */
    private BigDecimal seatUnderNonSmokingIn;
    
    /**
     * 客席数：地下F喫煙席数(店外)
     */
    private BigDecimal seatUnderSmokingOut;
    
    /**
     * 客席数：地下禁煙席数(店外)
     */
    private BigDecimal seatUnderNonSmokingOut;
    
    /**
     * 共用席有無
     */
    private String seatCommonFlg;
    
    /**
     * 客席数：共用F喫煙席数(店内)
     */
    private BigDecimal seatCommonSmokingIn;
    
    /**
     * 客席数：共用F禁煙席数(店内)
     */
    private BigDecimal seatCommonNonSmokingIn;
    
    /**
     * 客席数：共用F喫煙席数(店外)
     */
    private BigDecimal seatCommonSmokingOut;
    
    /**
     * 客席数：共用F禁煙席数(店外)
     */
    private BigDecimal seatCommonNonSmokingOut;
    
    /**
     * その他席有無
     */
    private String seatOtherFlg;
    
    /**
     * 客席数：その他喫煙席数(店内)
     */
    private BigDecimal seatOtherSmokingIn;
    
    /**
     * 客席数：その他禁煙席数(店内)
     */
    private BigDecimal seatOtherNonSmokingIn;
    
    /**
     * 客席数：その他喫煙席数(店外)
     */
    private BigDecimal seatOtherSmokingOut;
    
    /**
     * 客席数：その他禁煙席数(店外)
     */
    private BigDecimal seatOtherNonSmokingOut;
    
    /**
     * 交通手段備考
     */
    private String accessWayNote;
    
    /**
     * 賃貸店舗経理コード
     */
    private String miseLeaseKCd;
    
    /**
     * FC契約日
     */
    private String keiyakuDt;
    
    /**
     * FC解約日
     */
    private String kaiyakuDt;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    private short mstVer;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店区分を取得します。
     * @return 店区分
     */
    public String getMiseKbn() {
        return miseKbn;
    }
    /**
     * 店区分を設定します。
     * @param miseKbn 店区分
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }
    
    /**
     * 店都道府県ｺｰﾄﾞを取得します。
     * @return 店都道府県ｺｰﾄﾞ
     */
    public String getKenCd() {
        return kenCd;
    }
    /**
     * 店都道府県ｺｰﾄﾞを設定します。
     * @param kenCd 店都道府県ｺｰﾄﾞ
     */
    public void setKenCd(String kenCd) {
        this.kenCd = kenCd;
    }
    
    /**
     * エリア大コード(支部取込コード)を取得します。
     * @return エリア大コード(支部取込コード)
     */
    public String getAreaDai() {
        return areaDai;
    }
    /**
     * エリア大コード(支部取込コード)を設定します。
     * @param areaDai エリア大コード(支部取込コード)
     */
    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
    }
    
    /**
     * エリア小コードを取得します。
     * @return エリア小コード
     */
    public String getAreaSho() {
        return areaSho;
    }
    /**
     * エリア小コードを設定します。
     * @param areaSho エリア小コード
     */
    public void setAreaSho(String areaSho) {
        this.areaSho = areaSho;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return blockCd;
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店名称（カナ）を取得します。
     * @return 店名称（カナ）
     */
    public String getMiseNameKna() {
        return miseNameKna;
    }
    /**
     * 店名称（カナ）を設定します。
     * @param miseNameKna 店名称（カナ）
     */
    public void setMiseNameKna(String miseNameKna) {
        this.miseNameKna = miseNameKna;
    }
    
    /**
     * 店略称（漢字）を取得します。
     * @return 店略称（漢字）
     */
    public String getMiseRyakKj() {
        return miseRyakKj;
    }
    /**
     * 店略称（漢字）を設定します。
     * @param miseRyakKj 店略称（漢字）
     */
    public void setMiseRyakKj(String miseRyakKj) {
        this.miseRyakKj = miseRyakKj;
    }
    
    /**
     * 店略称（カナ）を取得します。
     * @return 店略称（カナ）
     */
    public String getMiseRyakKna() {
        return miseRyakKna;
    }
    /**
     * 店略称（カナ）を設定します。
     * @param miseRyakKna 店略称（カナ）
     */
    public void setMiseRyakKna(String miseRyakKna) {
        this.miseRyakKna = miseRyakKna;
    }
    
    /**
     * 店郵便番号を取得します。
     * @return 店郵便番号
     */
    public String getMisePostNo() {
        return misePostNo;
    }
    /**
     * 店郵便番号を設定します。
     * @param misePostNo 店郵便番号
     */
    public void setMisePostNo(String misePostNo) {
        this.misePostNo = misePostNo;
    }
    
    /**
     * 店都道府県名称を取得します。
     * @return 店都道府県名称
     */
    public String getMiseKenName() {
        return miseKenName;
    }
    /**
     * 店都道府県名称を設定します。
     * @param miseKenName 店都道府県名称
     */
    public void setMiseKenName(String miseKenName) {
        this.miseKenName = miseKenName;
    }
    
    /**
     * 店住所１を取得します。
     * @return 店住所１
     */
    public String getMiseAdrs1() {
        return miseAdrs1;
    }
    /**
     * 店住所１を設定します。
     * @param miseAdrs1 店住所１
     */
    public void setMiseAdrs1(String miseAdrs1) {
        this.miseAdrs1 = miseAdrs1;
    }
    
    /**
     * 店住所２を取得します。
     * @return 店住所２
     */
    public String getMiseAdrs2() {
        return miseAdrs2;
    }
    /**
     * 店住所２を設定します。
     * @param miseAdrs2 店住所２
     */
    public void setMiseAdrs2(String miseAdrs2) {
        this.miseAdrs2 = miseAdrs2;
    }
    
    /**
     * 店住所３を取得します。
     * @return 店住所３
     */
    public String getMiseAdrs3() {
        return miseAdrs3;
    }
    /**
     * 店住所３を設定します。
     * @param miseAdrs3 店住所３
     */
    public void setMiseAdrs3(String miseAdrs3) {
        this.miseAdrs3 = miseAdrs3;
    }
    
    /**
     * 店都道府県名称（カナ）を取得します。
     * @return 店都道府県名称（カナ）
     */
    public String getMiseKenNamek() {
        return miseKenNamek;
    }
    /**
     * 店都道府県名称（カナ）を設定します。
     * @param miseKenNamek 店都道府県名称（カナ）
     */
    public void setMiseKenNamek(String miseKenNamek) {
        this.miseKenNamek = miseKenNamek;
    }
    
    /**
     * 店住所１（カナ）を取得します。
     * @return 店住所１（カナ）
     */
    public String getMiseAdrs1K() {
        return miseAdrs1K;
    }
    /**
     * 店住所１（カナ）を設定します。
     * @param miseAdrs1K 店住所１（カナ）
     */
    public void setMiseAdrs1K(String miseAdrs1K) {
        this.miseAdrs1K = miseAdrs1K;
    }
    
    /**
     * 店住所２（カナ）を取得します。
     * @return 店住所２（カナ）
     */
    public String getMiseAdrs2K() {
        return miseAdrs2K;
    }
    /**
     * 店住所２（カナ）を設定します。
     * @param miseAdrs2K 店住所２（カナ）
     */
    public void setMiseAdrs2K(String miseAdrs2K) {
        this.miseAdrs2K = miseAdrs2K;
    }
    
    /**
     * 店住所３（カナ）を取得します。
     * @return 店住所３（カナ）
     */
    public String getMiseAdrs3K() {
        return miseAdrs3K;
    }
    /**
     * 店住所３（カナ）を設定します。
     * @param miseAdrs3K 店住所３（カナ）
     */
    public void setMiseAdrs3K(String miseAdrs3K) {
        this.miseAdrs3K = miseAdrs3K;
    }
    
    /**
     * 店電話番号を取得します。
     * @return 店電話番号
     */
    public String getMiseTel() {
        return miseTel;
    }
    /**
     * 店電話番号を設定します。
     * @param miseTel 店電話番号
     */
    public void setMiseTel(String miseTel) {
        this.miseTel = miseTel;
    }
    
    /**
     * 店ＦＡＸ番号を取得します。
     * @return 店ＦＡＸ番号
     */
    public String getMiseFax() {
        return miseFax;
    }
    /**
     * 店ＦＡＸ番号を設定します。
     * @param miseFax 店ＦＡＸ番号
     */
    public void setMiseFax(String miseFax) {
        this.miseFax = miseFax;
    }
    
    /**
     * ＮＣＵ番号を取得します。
     * @return ＮＣＵ番号
     */
    public String getNcuNo() {
        return ncuNo;
    }
    /**
     * ＮＣＵ番号を設定します。
     * @param ncuNo ＮＣＵ番号
     */
    public void setNcuNo(String ncuNo) {
        this.ncuNo = ncuNo;
    }
    
    /**
     * メニューパターンを取得します。
     * @return メニューパターン
     */
    public String getMenuPtn() {
        return menuPtn;
    }
    /**
     * メニューパターンを設定します。
     * @param menuPtn メニューパターン
     */
    public void setMenuPtn(String menuPtn) {
        this.menuPtn = menuPtn;
    }
    
    /**
     * ＰＯＳ区分を取得します。
     * @return ＰＯＳ区分
     */
    public String getPosKbn() {
        return posKbn;
    }
    /**
     * ＰＯＳ区分を設定します。
     * @param posKbn ＰＯＳ区分
     */
    public void setPosKbn(String posKbn) {
        this.posKbn = posKbn;
    }
    
    /**
     * 新ＰＯＳ区分を取得します。
     * @return 新ＰＯＳ区分
     */
    public String getPosKbnN() {
        return posKbnN;
    }
    /**
     * 新ＰＯＳ区分を設定します。
     * @param posKbnN 新ＰＯＳ区分
     */
    public void setPosKbnN(String posKbnN) {
        this.posKbnN = posKbnN;
    }
    
    /**
     * 納品先コードを取得します。
     * @return 納品先コード
     */
    public String getNohinCd() {
        return nohinCd;
    }
    /**
     * 納品先コードを設定します。
     * @param nohinCd 納品先コード
     */
    public void setNohinCd(String nohinCd) {
        this.nohinCd = nohinCd;
    }
    
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * 納品書送付先区分を取得します。
     * @return 納品書送付先区分
     */
    public String getNohinshoKbn() {
        return nohinshoKbn;
    }
    /**
     * 納品書送付先区分を設定します。
     * @param nohinshoKbn 納品書送付先区分
     */
    public void setNohinshoKbn(String nohinshoKbn) {
        this.nohinshoKbn = nohinshoKbn;
    }
    
    /**
     * 納品書送付先ｺｰﾄﾞを取得します。
     * @return 納品書送付先ｺｰﾄﾞ
     */
    public String getNohinshoCd() {
        return nohinshoCd;
    }
    /**
     * 納品書送付先ｺｰﾄﾞを設定します。
     * @param nohinshoCd 納品書送付先ｺｰﾄﾞ
     */
    public void setNohinshoCd(String nohinshoCd) {
        this.nohinshoCd = nohinshoCd;
    }
    
    /**
     * 管理部課ｺｰﾄﾞを取得します。
     * @return 管理部課ｺｰﾄﾞ
     */
    public String getKanriKa() {
        return kanriKa;
    }
    /**
     * 管理部課ｺｰﾄﾞを設定します。
     * @param kanriKa 管理部課ｺｰﾄﾞ
     */
    public void setKanriKa(String kanriKa) {
        this.kanriKa = kanriKa;
    }
    
    /**
     * 開発部課ｺｰﾄﾞを取得します。
     * @return 開発部課ｺｰﾄﾞ
     */
    public String getKaihatsuKa() {
        return kaihatsuKa;
    }
    /**
     * 開発部課ｺｰﾄﾞを設定します。
     * @param kaihatsuKa 開発部課ｺｰﾄﾞ
     */
    public void setKaihatsuKa(String kaihatsuKa) {
        this.kaihatsuKa = kaihatsuKa;
    }
    
    /**
     * 集計用コード１を取得します。
     * @return 集計用コード１
     */
    public String getMiseGrp1() {
        return miseGrp1;
    }
    /**
     * 集計用コード１を設定します。
     * @param miseGrp1 集計用コード１
     */
    public void setMiseGrp1(String miseGrp1) {
        this.miseGrp1 = miseGrp1;
    }
    
    /**
     * 集計用コード２を取得します。
     * @return 集計用コード２
     */
    public String getMiseGrp2() {
        return miseGrp2;
    }
    /**
     * 集計用コード２を設定します。
     * @param miseGrp2 集計用コード２
     */
    public void setMiseGrp2(String miseGrp2) {
        this.miseGrp2 = miseGrp2;
    }
    
    /**
     * 集計用コード３を取得します。
     * @return 集計用コード３
     */
    public String getMiseGrp3() {
        return miseGrp3;
    }
    /**
     * 集計用コード３を設定します。
     * @param miseGrp3 集計用コード３
     */
    public void setMiseGrp3(String miseGrp3) {
        this.miseGrp3 = miseGrp3;
    }
    
    /**
     * 請求書ｻｲｸﾙ区分を取得します。
     * @return 請求書ｻｲｸﾙ区分
     */
    public String getSeikyuSycl() {
        return seikyuSycl;
    }
    /**
     * 請求書ｻｲｸﾙ区分を設定します。
     * @param seikyuSycl 請求書ｻｲｸﾙ区分
     */
    public void setSeikyuSycl(String seikyuSycl) {
        this.seikyuSycl = seikyuSycl;
    }
    
    /**
     * 災害時自動発注区分を取得します。
     * @return 災害時自動発注区分
     */
    public String getSeikyuZui() {
        return seikyuZui;
    }
    /**
     * 災害時自動発注区分を設定します。
     * @param seikyuZui 災害時自動発注区分
     */
    public void setSeikyuZui(String seikyuZui) {
        this.seikyuZui = seikyuZui;
    }
    
    /**
     * 前払い区分を取得します。
     * @return 前払い区分
     */
    public String getCodKbn() {
        return codKbn;
    }
    /**
     * 前払い区分を設定します。
     * @param codKbn 前払い区分
     */
    public void setCodKbn(String codKbn) {
        this.codKbn = codKbn;
    }
    
    /**
     * ロイヤルティ区分を取得します。
     * @return ロイヤルティ区分
     */
    public String getRoyKbn() {
        return royKbn;
    }
    /**
     * ロイヤルティ区分を設定します。
     * @param royKbn ロイヤルティ区分
     */
    public void setRoyKbn(String royKbn) {
        this.royKbn = royKbn;
    }
    
    /**
     * 広告宣伝費区分を取得します。
     * @return 広告宣伝費区分
     */
    public String getKosenKbn() {
        return kosenKbn;
    }
    /**
     * 広告宣伝費区分を設定します。
     * @param kosenKbn 広告宣伝費区分
     */
    public void setKosenKbn(String kosenKbn) {
        this.kosenKbn = kosenKbn;
    }
    
    /**
     * ＨＨ協会費区分を取得します。
     * @return ＨＨ協会費区分
     */
    public String getHhKbn() {
        return hhKbn;
    }
    /**
     * ＨＨ協会費区分を設定します。
     * @param hhKbn ＨＨ協会費区分
     */
    public void setHhKbn(String hhKbn) {
        this.hhKbn = hhKbn;
    }
    
    /**
     * 経営分析料区分を取得します。
     * @return 経営分析料区分
     */
    public String getKebunKbn() {
        return kebunKbn;
    }
    /**
     * 経営分析料区分を設定します。
     * @param kebunKbn 経営分析料区分
     */
    public void setKebunKbn(String kebunKbn) {
        this.kebunKbn = kebunKbn;
    }
    
    /**
     * 値引きランクを取得します。
     * @return 値引きランク
     */
    public String getNebikiRank() {
        return nebikiRank;
    }
    /**
     * 値引きランクを設定します。
     * @param nebikiRank 値引きランク
     */
    public void setNebikiRank(String nebikiRank) {
        this.nebikiRank = nebikiRank;
    }
    
    /**
     * 入金予定区分を取得します。
     * @return 入金予定区分
     */
    public String getNyukinKbn() {
        return nyukinKbn;
    }
    /**
     * 入金予定区分を設定します。
     * @param nyukinKbn 入金予定区分
     */
    public void setNyukinKbn(String nyukinKbn) {
        this.nyukinKbn = nyukinKbn;
    }
    
    /**
     * 入金予定月数を取得します。
     * @return 入金予定月数
     */
    public String getNyuTukisu() {
        return nyuTukisu;
    }
    /**
     * 入金予定月数を設定します。
     * @param nyuTukisu 入金予定月数
     */
    public void setNyuTukisu(String nyuTukisu) {
        this.nyuTukisu = nyuTukisu;
    }
    
    /**
     * 入金予定日付を取得します。
     * @return 入金予定日付
     */
    public String getNyuHiduke() {
        return nyuHiduke;
    }
    /**
     * 入金予定日付を設定します。
     * @param nyuHiduke 入金予定日付
     */
    public void setNyuHiduke(String nyuHiduke) {
        this.nyuHiduke = nyuHiduke;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * ？を取得します。
     * @return ？
     */
    public String getNohinDenFlg() {
        return nohinDenFlg;
    }
    /**
     * ？を設定します。
     * @param nohinDenFlg ？
     */
    public void setNohinDenFlg(String nohinDenFlg) {
        this.nohinDenFlg = nohinDenFlg;
    }
    
    /**
     * ？を取得します。
     * @return ？
     */
    public String getNohinOldFlg() {
        return nohinOldFlg;
    }
    /**
     * ？を設定します。
     * @param nohinOldFlg ？
     */
    public void setNohinOldFlg(String nohinOldFlg) {
        this.nohinOldFlg = nohinOldFlg;
    }
    
    /**
     * コード１を取得します。
     * @return コード１
     */
    public String getCode1() {
        return code1;
    }
    /**
     * コード１を設定します。
     * @param code1 コード１
     */
    public void setCode1(String code1) {
        this.code1 = code1;
    }
    
    /**
     * コード２を取得します。
     * @return コード２
     */
    public String getCode2() {
        return code2;
    }
    /**
     * コード２を設定します。
     * @param code2 コード２
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }
    
    /**
     * コード３を取得します。
     * @return コード３
     */
    public String getCode3() {
        return code3;
    }
    /**
     * コード３を設定します。
     * @param code3 コード３
     */
    public void setCode3(String code3) {
        this.code3 = code3;
    }
    
    /**
     * フラグ１を取得します。
     * @return フラグ１
     */
    public String getFlg1() {
        return flg1;
    }
    /**
     * フラグ１を設定します。
     * @param flg1 フラグ１
     */
    public void setFlg1(String flg1) {
        this.flg1 = flg1;
    }
    
    /**
     * フラグ２を取得します。
     * @return フラグ２
     */
    public String getFlg2() {
        return flg2;
    }
    /**
     * フラグ２を設定します。
     * @param flg2 フラグ２
     */
    public void setFlg2(String flg2) {
        this.flg2 = flg2;
    }
    
    /**
     * 日付１を取得します。
     * @return 日付１
     */
    public String getDate1() {
        return date1;
    }
    /**
     * 日付１を設定します。
     * @param date1 日付１
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }
    
    /**
     * 日付２を取得します。
     * @return 日付２
     */
    public String getDate2() {
        return date2;
    }
    /**
     * 日付２を設定します。
     * @param date2 日付２
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }
    
    /**
     * 区分１を取得します。
     * @return 区分１
     */
    public String getKbn1() {
        return kbn1;
    }
    /**
     * 区分１を設定します。
     * @param kbn1 区分１
     */
    public void setKbn1(String kbn1) {
        this.kbn1 = kbn1;
    }
    
    /**
     * 区分２を取得します。
     * @return 区分２
     */
    public String getKbn2() {
        return kbn2;
    }
    /**
     * 区分２を設定します。
     * @param kbn2 区分２
     */
    public void setKbn2(String kbn2) {
        this.kbn2 = kbn2;
    }
    
    /**
     * ﾈｯﾄﾜｰｸ開始日を取得します。
     * @return ﾈｯﾄﾜｰｸ開始日
     */
    public String getFcKeiyaku() {
        return fcKeiyaku;
    }
    /**
     * ﾈｯﾄﾜｰｸ開始日を設定します。
     * @param fcKeiyaku ﾈｯﾄﾜｰｸ開始日
     */
    public void setFcKeiyaku(String fcKeiyaku) {
        this.fcKeiyaku = fcKeiyaku;
    }
    
    /**
     * ﾈｯﾄﾜｰｸ終了日を取得します。
     * @return ﾈｯﾄﾜｰｸ終了日
     */
    public String getFcKaiyaku() {
        return fcKaiyaku;
    }
    /**
     * ﾈｯﾄﾜｰｸ終了日を設定します。
     * @param fcKaiyaku ﾈｯﾄﾜｰｸ終了日
     */
    public void setFcKaiyaku(String fcKaiyaku) {
        this.fcKaiyaku = fcKaiyaku;
    }
    
    /**
     * プレオープン日を取得します。
     * @return プレオープン日
     */
    public String getPreOpenDt() {
        return preOpenDt;
    }
    /**
     * プレオープン日を設定します。
     * @param preOpenDt プレオープン日
     */
    public void setPreOpenDt(String preOpenDt) {
        this.preOpenDt = preOpenDt;
    }
    
    /**
     * 店オープン日を取得します。
     * @return 店オープン日
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * 店オープン日を設定します。
     * @param openDt 店オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * ﾃｲｸｵｰﾊﾞｰ日を取得します。
     * @return ﾃｲｸｵｰﾊﾞｰ日
     */
    public String getTakeOverDt() {
        return takeOverDt;
    }
    /**
     * ﾃｲｸｵｰﾊﾞｰ日を設定します。
     * @param takeOverDt ﾃｲｸｵｰﾊﾞｰ日
     */
    public void setTakeOverDt(String takeOverDt) {
        this.takeOverDt = takeOverDt;
    }
    
    /**
     * 店移動フラグを取得します。
     * @return 店移動フラグ
     */
    public String getMiseIdoF() {
        return miseIdoF;
    }
    /**
     * 店移動フラグを設定します。
     * @param miseIdoF 店移動フラグ
     */
    public void setMiseIdoF(String miseIdoF) {
        this.miseIdoF = miseIdoF;
    }
    
    /**
     * 店改装フラグを取得します。
     * @return 店改装フラグ
     */
    public String getMiseKaisoF() {
        return miseKaisoF;
    }
    /**
     * 店改装フラグを設定します。
     * @param miseKaisoF 店改装フラグ
     */
    public void setMiseKaisoF(String miseKaisoF) {
        this.miseKaisoF = miseKaisoF;
    }
    
    /**
     * 旧店コードを取得します。
     * @return 旧店コード
     */
    public String getMiseOldCd() {
        return miseOldCd;
    }
    /**
     * 旧店コードを設定します。
     * @param miseOldCd 旧店コード
     */
    public void setMiseOldCd(String miseOldCd) {
        this.miseOldCd = miseOldCd;
    }
    
    /**
     * 月曜日休日区分を取得します。
     * @return 月曜日休日区分
     */
    public String getHolMon() {
        return holMon;
    }
    /**
     * 月曜日休日区分を設定します。
     * @param holMon 月曜日休日区分
     */
    public void setHolMon(String holMon) {
        this.holMon = holMon;
    }
    
    /**
     * 火曜日休日区分を取得します。
     * @return 火曜日休日区分
     */
    public String getHolTue() {
        return holTue;
    }
    /**
     * 火曜日休日区分を設定します。
     * @param holTue 火曜日休日区分
     */
    public void setHolTue(String holTue) {
        this.holTue = holTue;
    }
    
    /**
     * 水曜日休日区分を取得します。
     * @return 水曜日休日区分
     */
    public String getHolWed() {
        return holWed;
    }
    /**
     * 水曜日休日区分を設定します。
     * @param holWed 水曜日休日区分
     */
    public void setHolWed(String holWed) {
        this.holWed = holWed;
    }
    
    /**
     * 木曜日休日区分を取得します。
     * @return 木曜日休日区分
     */
    public String getHolThu() {
        return holThu;
    }
    /**
     * 木曜日休日区分を設定します。
     * @param holThu 木曜日休日区分
     */
    public void setHolThu(String holThu) {
        this.holThu = holThu;
    }
    
    /**
     * 金曜日休日区分を取得します。
     * @return 金曜日休日区分
     */
    public String getHolFri() {
        return holFri;
    }
    /**
     * 金曜日休日区分を設定します。
     * @param holFri 金曜日休日区分
     */
    public void setHolFri(String holFri) {
        this.holFri = holFri;
    }
    
    /**
     * 土曜日休日区分を取得します。
     * @return 土曜日休日区分
     */
    public String getHolSat() {
        return holSat;
    }
    /**
     * 土曜日休日区分を設定します。
     * @param holSat 土曜日休日区分
     */
    public void setHolSat(String holSat) {
        this.holSat = holSat;
    }
    
    /**
     * 日曜日休日区分を取得します。
     * @return 日曜日休日区分
     */
    public String getHolSun() {
        return holSun;
    }
    /**
     * 日曜日休日区分を設定します。
     * @param holSun 日曜日休日区分
     */
    public void setHolSun(String holSun) {
        this.holSun = holSun;
    }
    
    /**
     * 祝日休日区分を取得します。
     * @return 祝日休日区分
     */
    public String getHolAnv() {
        return holAnv;
    }
    /**
     * 祝日休日区分を設定します。
     * @param holAnv 祝日休日区分
     */
    public void setHolAnv(String holAnv) {
        this.holAnv = holAnv;
    }
    
    /**
     * 経営形態を取得します。
     * @return 経営形態
     */
    public String getTMiseKeiei() {
        return tMiseKeiei;
    }
    /**
     * 経営形態を設定します。
     * @param tMiseKeiei 経営形態
     */
    public void setTMiseKeiei(String tMiseKeiei) {
        this.tMiseKeiei = tMiseKeiei;
    }
    
    /**
     * ﾃﾞｰﾀ信用度区分を取得します。
     * @return ﾃﾞｰﾀ信用度区分
     */
    public String getTDataSinKbn() {
        return tDataSinKbn;
    }
    /**
     * ﾃﾞｰﾀ信用度区分を設定します。
     * @param tDataSinKbn ﾃﾞｰﾀ信用度区分
     */
    public void setTDataSinKbn(String tDataSinKbn) {
        this.tDataSinKbn = tDataSinKbn;
    }
    
    /**
     * 店舗面積（㎡）を取得します。
     * @return 店舗面積（㎡）
     */
    public short getTMiseMenseki() {
        return tMiseMenseki;
    }
    /**
     * 店舗面積（㎡）を設定します。
     * @param tMiseMenseki 店舗面積（㎡）
     */
    public void setTMiseMenseki(short tMiseMenseki) {
        this.tMiseMenseki = tMiseMenseki;
    }
    
    /**
     * 客席面積（㎡）を取得します。
     * @return 客席面積（㎡）
     */
    public short getTKyakMenseki() {
        return tKyakMenseki;
    }
    /**
     * 客席面積（㎡）を設定します。
     * @param tKyakMenseki 客席面積（㎡）
     */
    public void setTKyakMenseki(short tKyakMenseki) {
        this.tKyakMenseki = tKyakMenseki;
    }
    
    /**
     * 総席数を取得します。
     * @return 総席数
     */
    public short getTSouSekisuu() {
        return tSouSekisuu;
    }
    /**
     * 総席数を設定します。
     * @param tSouSekisuu 総席数
     */
    public void setTSouSekisuu(short tSouSekisuu) {
        this.tSouSekisuu = tSouSekisuu;
    }
    
    /**
     * 客席数を取得します。
     * @return 客席数
     */
    public short getTKyakSekisuu() {
        return tKyakSekisuu;
    }
    /**
     * 客席数を設定します。
     * @param tKyakSekisuu 客席数
     */
    public void setTKyakSekisuu(short tKyakSekisuu) {
        this.tKyakSekisuu = tKyakSekisuu;
    }
    
    /**
     * 間口を取得します。
     * @return 間口
     */
    public short getTMiseMaguchi() {
        return tMiseMaguchi;
    }
    /**
     * 間口を設定します。
     * @param tMiseMaguchi 間口
     */
    public void setTMiseMaguchi(short tMiseMaguchi) {
        this.tMiseMaguchi = tMiseMaguchi;
    }
    
    /**
     * 駐車台数を取得します。
     * @return 駐車台数
     */
    public short getTMiseChusha() {
        return tMiseChusha;
    }
    /**
     * 駐車台数を設定します。
     * @param tMiseChusha 駐車台数
     */
    public void setTMiseChusha(short tMiseChusha) {
        this.tMiseChusha = tMiseChusha;
    }
    
    /**
     * 店前通行量　を取得します。
     * @return 店前通行量　
     */
    public short getTTsukoMae() {
        return tTsukoMae;
    }
    /**
     * 店前通行量　を設定します。
     * @param tTsukoMae 店前通行量　
     */
    public void setTTsukoMae(short tTsukoMae) {
        this.tTsukoMae = tTsukoMae;
    }
    
    /**
     * 店裏通行量を取得します。
     * @return 店裏通行量
     */
    public short getTTsukoUra() {
        return tTsukoUra;
    }
    /**
     * 店裏通行量を設定します。
     * @param tTsukoUra 店裏通行量
     */
    public void setTTsukoUra(short tTsukoUra) {
        this.tTsukoUra = tTsukoUra;
    }
    
    /**
     * 店横通行量を取得します。
     * @return 店横通行量
     */
    public short getTTsukoYok() {
        return tTsukoYok;
    }
    /**
     * 店横通行量を設定します。
     * @param tTsukoYok 店横通行量
     */
    public void setTTsukoYok(short tTsukoYok) {
        this.tTsukoYok = tTsukoYok;
    }
    
    /**
     * 敷地面積（㎡）を取得します。
     * @return 敷地面積（㎡）
     */
    public int getTSetai500() {
        return tSetai500;
    }
    /**
     * 敷地面積（㎡）を設定します。
     * @param tSetai500 敷地面積（㎡）
     */
    public void setTSetai500(int tSetai500) {
        this.tSetai500 = tSetai500;
    }
    
    /**
     * 厨房面積（㎡）を取得します。
     * @return 厨房面積（㎡）
     */
    public int getTJinko500() {
        return tJinko500;
    }
    /**
     * 厨房面積（㎡）を設定します。
     * @param tJinko500 厨房面積（㎡）
     */
    public void setTJinko500(int tJinko500) {
        this.tJinko500 = tJinko500;
    }
    
    /**
     * その他面積（㎡）を取得します。
     * @return その他面積（㎡）
     */
    public int getTSetaiKiro() {
        return tSetaiKiro;
    }
    /**
     * その他面積（㎡）を設定します。
     * @param tSetaiKiro その他面積（㎡）
     */
    public void setTSetaiKiro(int tSetaiKiro) {
        this.tSetaiKiro = tSetaiKiro;
    }
    
    /**
     * 1ｷﾛﾒｰﾄﾙ内人口を取得します。
     * @return 1ｷﾛﾒｰﾄﾙ内人口
     */
    public int getTJinkoKiro() {
        return tJinkoKiro;
    }
    /**
     * 1ｷﾛﾒｰﾄﾙ内人口を設定します。
     * @param tJinkoKiro 1ｷﾛﾒｰﾄﾙ内人口
     */
    public void setTJinkoKiro(int tJinkoKiro) {
        this.tJinkoKiro = tJinkoKiro;
    }
    
    /**
     * 事業計画情報を取得します。
     * @return 事業計画情報
     */
    public BigDecimal getTJigyouJoho() {
        return tJigyouJoho;
    }
    /**
     * 事業計画情報を設定します。
     * @param tJigyouJoho 事業計画情報
     */
    public void setTJigyouJoho(BigDecimal tJigyouJoho) {
        this.tJigyouJoho = tJigyouJoho;
    }
    
    /**
     * 開発商圏構造区分を取得します。
     * @return 開発商圏構造区分
     */
    public String getTShoukenKbn() {
        return tShoukenKbn;
    }
    /**
     * 開発商圏構造区分を設定します。
     * @param tShoukenKbn 開発商圏構造区分
     */
    public void setTShoukenKbn(String tShoukenKbn) {
        this.tShoukenKbn = tShoukenKbn;
    }
    
    /**
     * ﾛｹｰｼｮﾝ区分を取得します。
     * @return ﾛｹｰｼｮﾝ区分
     */
    public String getTLocateKbn() {
        return tLocateKbn;
    }
    /**
     * ﾛｹｰｼｮﾝ区分を設定します。
     * @param tLocateKbn ﾛｹｰｼｮﾝ区分
     */
    public void setTLocateKbn(String tLocateKbn) {
        this.tLocateKbn = tLocateKbn;
    }
    
    /**
     * 店舗形態区分を取得します。
     * @return 店舗形態区分
     */
    public String getTMiseKeitai() {
        return tMiseKeitai;
    }
    /**
     * 店舗形態区分を設定します。
     * @param tMiseKeitai 店舗形態区分
     */
    public void setTMiseKeitai(String tMiseKeitai) {
        this.tMiseKeitai = tMiseKeitai;
    }
    
    /**
     * 設計業者コードを取得します。
     * @return 設計業者コード
     */
    public String getTToriSekkei() {
        return tToriSekkei;
    }
    /**
     * 設計業者コードを設定します。
     * @param tToriSekkei 設計業者コード
     */
    public void setTToriSekkei(String tToriSekkei) {
        this.tToriSekkei = tToriSekkei;
    }
    
    /**
     * 内装業者コードを取得します。
     * @return 内装業者コード
     */
    public String getTToriNaiso() {
        return tToriNaiso;
    }
    /**
     * 内装業者コードを設定します。
     * @param tToriNaiso 内装業者コード
     */
    public void setTToriNaiso(String tToriNaiso) {
        this.tToriNaiso = tToriNaiso;
    }
    
    /**
     * 空調業者コードを取得します。
     * @return 空調業者コード
     */
    public String getTToriKucho() {
        return tToriKucho;
    }
    /**
     * 空調業者コードを設定します。
     * @param tToriKucho 空調業者コード
     */
    public void setTToriKucho(String tToriKucho) {
        this.tToriKucho = tToriKucho;
    }
    
    /**
     * 水道業者コードを取得します。
     * @return 水道業者コード
     */
    public String getTToriSuido() {
        return tToriSuido;
    }
    /**
     * 水道業者コードを設定します。
     * @param tToriSuido 水道業者コード
     */
    public void setTToriSuido(String tToriSuido) {
        this.tToriSuido = tToriSuido;
    }
    
    /**
     * 厨房業者コードを取得します。
     * @return 厨房業者コード
     */
    public String getTToriChubo() {
        return tToriChubo;
    }
    /**
     * 厨房業者コードを設定します。
     * @param tToriChubo 厨房業者コード
     */
    public void setTToriChubo(String tToriChubo) {
        this.tToriChubo = tToriChubo;
    }
    
    /**
     * 営業開始時間（月曜）を取得します。
     * @return 営業開始時間（月曜）
     */
    public String getTOpenTmMon() {
        return tOpenTmMon;
    }
    /**
     * 営業開始時間（月曜）を設定します。
     * @param tOpenTmMon 営業開始時間（月曜）
     */
    public void setTOpenTmMon(String tOpenTmMon) {
        this.tOpenTmMon = tOpenTmMon;
    }
    
    /**
     * 営業終了時間（月曜）を取得します。
     * @return 営業終了時間（月曜）
     */
    public String getTCloseTmMon() {
        return tCloseTmMon;
    }
    /**
     * 営業終了時間（月曜）を設定します。
     * @param tCloseTmMon 営業終了時間（月曜）
     */
    public void setTCloseTmMon(String tCloseTmMon) {
        this.tCloseTmMon = tCloseTmMon;
    }
    
    /**
     * 営業開始時間（火曜）を取得します。
     * @return 営業開始時間（火曜）
     */
    public String getTOpenTmTue() {
        return tOpenTmTue;
    }
    /**
     * 営業開始時間（火曜）を設定します。
     * @param tOpenTmTue 営業開始時間（火曜）
     */
    public void setTOpenTmTue(String tOpenTmTue) {
        this.tOpenTmTue = tOpenTmTue;
    }
    
    /**
     * 営業終了時間（火曜）を取得します。
     * @return 営業終了時間（火曜）
     */
    public String getTCloseTmTue() {
        return tCloseTmTue;
    }
    /**
     * 営業終了時間（火曜）を設定します。
     * @param tCloseTmTue 営業終了時間（火曜）
     */
    public void setTCloseTmTue(String tCloseTmTue) {
        this.tCloseTmTue = tCloseTmTue;
    }
    
    /**
     * 営業開始時間（水曜）を取得します。
     * @return 営業開始時間（水曜）
     */
    public String getTOpenTmWed() {
        return tOpenTmWed;
    }
    /**
     * 営業開始時間（水曜）を設定します。
     * @param tOpenTmWed 営業開始時間（水曜）
     */
    public void setTOpenTmWed(String tOpenTmWed) {
        this.tOpenTmWed = tOpenTmWed;
    }
    
    /**
     * 営業終了時間（水曜）を取得します。
     * @return 営業終了時間（水曜）
     */
    public String getTCloseTmWed() {
        return tCloseTmWed;
    }
    /**
     * 営業終了時間（水曜）を設定します。
     * @param tCloseTmWed 営業終了時間（水曜）
     */
    public void setTCloseTmWed(String tCloseTmWed) {
        this.tCloseTmWed = tCloseTmWed;
    }
    
    /**
     * 営業開始時間（木曜）を取得します。
     * @return 営業開始時間（木曜）
     */
    public String getTOpenTmThu() {
        return tOpenTmThu;
    }
    /**
     * 営業開始時間（木曜）を設定します。
     * @param tOpenTmThu 営業開始時間（木曜）
     */
    public void setTOpenTmThu(String tOpenTmThu) {
        this.tOpenTmThu = tOpenTmThu;
    }
    
    /**
     * 営業終了時間（木曜）を取得します。
     * @return 営業終了時間（木曜）
     */
    public String getTCloseTmThu() {
        return tCloseTmThu;
    }
    /**
     * 営業終了時間（木曜）を設定します。
     * @param tCloseTmThu 営業終了時間（木曜）
     */
    public void setTCloseTmThu(String tCloseTmThu) {
        this.tCloseTmThu = tCloseTmThu;
    }
    
    /**
     * 営業開始時間（金曜）を取得します。
     * @return 営業開始時間（金曜）
     */
    public String getTOpenTmFri() {
        return tOpenTmFri;
    }
    /**
     * 営業開始時間（金曜）を設定します。
     * @param tOpenTmFri 営業開始時間（金曜）
     */
    public void setTOpenTmFri(String tOpenTmFri) {
        this.tOpenTmFri = tOpenTmFri;
    }
    
    /**
     * 営業終了時間（金曜）を取得します。
     * @return 営業終了時間（金曜）
     */
    public String getTCloseTmFri() {
        return tCloseTmFri;
    }
    /**
     * 営業終了時間（金曜）を設定します。
     * @param tCloseTmFri 営業終了時間（金曜）
     */
    public void setTCloseTmFri(String tCloseTmFri) {
        this.tCloseTmFri = tCloseTmFri;
    }
    
    /**
     * 営業開始時間（土曜）を取得します。
     * @return 営業開始時間（土曜）
     */
    public String getTOpenTmSat() {
        return tOpenTmSat;
    }
    /**
     * 営業開始時間（土曜）を設定します。
     * @param tOpenTmSat 営業開始時間（土曜）
     */
    public void setTOpenTmSat(String tOpenTmSat) {
        this.tOpenTmSat = tOpenTmSat;
    }
    
    /**
     * 営業終了時間（土曜）を取得します。
     * @return 営業終了時間（土曜）
     */
    public String getTCloseTmSat() {
        return tCloseTmSat;
    }
    /**
     * 営業終了時間（土曜）を設定します。
     * @param tCloseTmSat 営業終了時間（土曜）
     */
    public void setTCloseTmSat(String tCloseTmSat) {
        this.tCloseTmSat = tCloseTmSat;
    }
    
    /**
     * 営業開始時間（日曜）を取得します。
     * @return 営業開始時間（日曜）
     */
    public String getTOpenTmSun() {
        return tOpenTmSun;
    }
    /**
     * 営業開始時間（日曜）を設定します。
     * @param tOpenTmSun 営業開始時間（日曜）
     */
    public void setTOpenTmSun(String tOpenTmSun) {
        this.tOpenTmSun = tOpenTmSun;
    }
    
    /**
     * 営業終了時間（日曜）を取得します。
     * @return 営業終了時間（日曜）
     */
    public String getTCloseTmSun() {
        return tCloseTmSun;
    }
    /**
     * 営業終了時間（日曜）を設定します。
     * @param tCloseTmSun 営業終了時間（日曜）
     */
    public void setTCloseTmSun(String tCloseTmSun) {
        this.tCloseTmSun = tCloseTmSun;
    }
    
    /**
     * 営業開始時間（祝日）を取得します。
     * @return 営業開始時間（祝日）
     */
    public String getTOpenTmHol() {
        return tOpenTmHol;
    }
    /**
     * 営業開始時間（祝日）を設定します。
     * @param tOpenTmHol 営業開始時間（祝日）
     */
    public void setTOpenTmHol(String tOpenTmHol) {
        this.tOpenTmHol = tOpenTmHol;
    }
    
    /**
     * 営業終了時間（祝日）を取得します。
     * @return 営業終了時間（祝日）
     */
    public String getTCloseTmHol() {
        return tCloseTmHol;
    }
    /**
     * 営業終了時間（祝日）を設定します。
     * @param tCloseTmHol 営業終了時間（祝日）
     */
    public void setTCloseTmHol(String tCloseTmHol) {
        this.tCloseTmHol = tCloseTmHol;
    }
    
    /**
     * 営業開始時間（祝前）を取得します。
     * @return 営業開始時間（祝前）
     */
    public String getTOpenTmHlb() {
        return tOpenTmHlb;
    }
    /**
     * 営業開始時間（祝前）を設定します。
     * @param tOpenTmHlb 営業開始時間（祝前）
     */
    public void setTOpenTmHlb(String tOpenTmHlb) {
        this.tOpenTmHlb = tOpenTmHlb;
    }
    
    /**
     * 営業終了時間（祝前）を取得します。
     * @return 営業終了時間（祝前）
     */
    public String getTCloseTmHlb() {
        return tCloseTmHlb;
    }
    /**
     * 営業終了時間（祝前）を設定します。
     * @param tCloseTmHlb 営業終了時間（祝前）
     */
    public void setTCloseTmHlb(String tCloseTmHlb) {
        this.tCloseTmHlb = tCloseTmHlb;
    }
    
    /**
     * 電話番号：事務所を取得します。
     * @return 電話番号：事務所
     */
    public String getTelOffice() {
        return telOffice;
    }
    /**
     * 電話番号：事務所を設定します。
     * @param telOffice 電話番号：事務所
     */
    public void setTelOffice(String telOffice) {
        this.telOffice = telOffice;
    }
    
    /**
     * ロケーション区分を取得します。
     * @return ロケーション区分
     */
    public String getLocateKbn() {
        return locateKbn;
    }
    /**
     * ロケーション区分を設定します。
     * @param locateKbn ロケーション区分
     */
    public void setLocateKbn(String locateKbn) {
        this.locateKbn = locateKbn;
    }
    
    /**
     * 電話番号：ピンクを取得します。
     * @return 電話番号：ピンク
     */
    public String getTelPink() {
        return telPink;
    }
    /**
     * 電話番号：ピンクを設定します。
     * @param telPink 電話番号：ピンク
     */
    public void setTelPink(String telPink) {
        this.telPink = telPink;
    }
    
    /**
     * 店舗形態区分を取得します。
     * @return 店舗形態区分
     */
    public String getMiseKeitai() {
        return miseKeitai;
    }
    /**
     * 店舗形態区分を設定します。
     * @param miseKeitai 店舗形態区分
     */
    public void setMiseKeitai(String miseKeitai) {
        this.miseKeitai = miseKeitai;
    }
    
    /**
     * 電話番号：POSを取得します。
     * @return 電話番号：POS
     */
    public String getTelPos() {
        return telPos;
    }
    /**
     * 電話番号：POSを設定します。
     * @param telPos 電話番号：POS
     */
    public void setTelPos(String telPos) {
        this.telPos = telPos;
    }
    
    /**
     * 電話番号：その他を取得します。
     * @return 電話番号：その他
     */
    public String getTelOther() {
        return telOther;
    }
    /**
     * 電話番号：その他を設定します。
     * @param telOther 電話番号：その他
     */
    public void setTelOther(String telOther) {
        this.telOther = telOther;
    }
    
    /**
     * 電話番号：ノートを取得します。
     * @return 電話番号：ノート
     */
    public String getTelNotes() {
        return telNotes;
    }
    /**
     * 電話番号：ノートを設定します。
     * @param telNotes 電話番号：ノート
     */
    public void setTelNotes(String telNotes) {
        this.telNotes = telNotes;
    }
    
    /**
     * 物件契約：契約日を取得します。
     * @return 物件契約：契約日
     */
    public String getKeiyakuFirst() {
        return keiyakuFirst;
    }
    /**
     * 物件契約：契約日を設定します。
     * @param keiyakuFirst 物件契約：契約日
     */
    public void setKeiyakuFirst(String keiyakuFirst) {
        this.keiyakuFirst = keiyakuFirst;
    }
    
    /**
     * 物件契約：所有形態を取得します。
     * @return 物件契約：所有形態
     */
    public String getKeiyakuKeitai() {
        return keiyakuKeitai;
    }
    /**
     * 物件契約：所有形態を設定します。
     * @param keiyakuKeitai 物件契約：所有形態
     */
    public void setKeiyakuKeitai(String keiyakuKeitai) {
        this.keiyakuKeitai = keiyakuKeitai;
    }
    
    /**
     * 営業開始時間（平日）を取得します。
     * @return 営業開始時間（平日）
     */
    public String getOpenTmWkd() {
        return openTmWkd;
    }
    /**
     * 営業開始時間（平日）を設定します。
     * @param openTmWkd 営業開始時間（平日）
     */
    public void setOpenTmWkd(String openTmWkd) {
        this.openTmWkd = openTmWkd;
    }
    
    /**
     * 営業終了時間（平日）を取得します。
     * @return 営業終了時間（平日）
     */
    public String getCloseTmWkd() {
        return closeTmWkd;
    }
    /**
     * 営業終了時間（平日）を設定します。
     * @param closeTmWkd 営業終了時間（平日）
     */
    public void setCloseTmWkd(String closeTmWkd) {
        this.closeTmWkd = closeTmWkd;
    }
    
    /**
     * 営業開始時間（土曜）を取得します。
     * @return 営業開始時間（土曜）
     */
    public String getOpenTmSat() {
        return openTmSat;
    }
    /**
     * 営業開始時間（土曜）を設定します。
     * @param openTmSat 営業開始時間（土曜）
     */
    public void setOpenTmSat(String openTmSat) {
        this.openTmSat = openTmSat;
    }
    
    /**
     * 営業終了時間（土曜）を取得します。
     * @return 営業終了時間（土曜）
     */
    public String getCloseTmSat() {
        return closeTmSat;
    }
    /**
     * 営業終了時間（土曜）を設定します。
     * @param closeTmSat 営業終了時間（土曜）
     */
    public void setCloseTmSat(String closeTmSat) {
        this.closeTmSat = closeTmSat;
    }
    
    /**
     * 物件契約：備考を取得します。
     * @return 物件契約：備考
     */
    public String getKeiyakuNotes() {
        return keiyakuNotes;
    }
    /**
     * 物件契約：備考を設定します。
     * @param keiyakuNotes 物件契約：備考
     */
    public void setKeiyakuNotes(String keiyakuNotes) {
        this.keiyakuNotes = keiyakuNotes;
    }
    
    /**
     * 営業開始時間（日祭日）を取得します。
     * @return 営業開始時間（日祭日）
     */
    public String getOpenTmHol() {
        return openTmHol;
    }
    /**
     * 営業開始時間（日祭日）を設定します。
     * @param openTmHol 営業開始時間（日祭日）
     */
    public void setOpenTmHol(String openTmHol) {
        this.openTmHol = openTmHol;
    }
    
    /**
     * 駐車場：専用敷地内を取得します。
     * @return 駐車場：専用敷地内
     */
    public BigDecimal getParkOnlyIn() {
        return parkOnlyIn;
    }
    /**
     * 駐車場：専用敷地内を設定します。
     * @param parkOnlyIn 駐車場：専用敷地内
     */
    public void setParkOnlyIn(BigDecimal parkOnlyIn) {
        this.parkOnlyIn = parkOnlyIn;
    }
    
    /**
     * 営業終了時間（日祭日）を取得します。
     * @return 営業終了時間（日祭日）
     */
    public String getCloseTmHol() {
        return closeTmHol;
    }
    /**
     * 営業終了時間（日祭日）を設定します。
     * @param closeTmHol 営業終了時間（日祭日）
     */
    public void setCloseTmHol(String closeTmHol) {
        this.closeTmHol = closeTmHol;
    }
    
    /**
     * 駐車場：専用隣接を取得します。
     * @return 駐車場：専用隣接
     */
    public BigDecimal getParkOnlyOut() {
        return parkOnlyOut;
    }
    /**
     * 駐車場：専用隣接を設定します。
     * @param parkOnlyOut 駐車場：専用隣接
     */
    public void setParkOnlyOut(BigDecimal parkOnlyOut) {
        this.parkOnlyOut = parkOnlyOut;
    }
    
    /**
     * 駐車場：共用敷地内を取得します。
     * @return 駐車場：共用敷地内
     */
    public BigDecimal getParkCommIn() {
        return parkCommIn;
    }
    /**
     * 駐車場：共用敷地内を設定します。
     * @param parkCommIn 駐車場：共用敷地内
     */
    public void setParkCommIn(BigDecimal parkCommIn) {
        this.parkCommIn = parkCommIn;
    }
    
    /**
     * 営業開始時間（休前日）を取得します。
     * @return 営業開始時間（休前日）
     */
    public String getOpenTmHlb() {
        return openTmHlb;
    }
    /**
     * 営業開始時間（休前日）を設定します。
     * @param openTmHlb 営業開始時間（休前日）
     */
    public void setOpenTmHlb(String openTmHlb) {
        this.openTmHlb = openTmHlb;
    }
    
    /**
     * 営業終了時間（休前日）を取得します。
     * @return 営業終了時間（休前日）
     */
    public String getCloseTmHlb() {
        return closeTmHlb;
    }
    /**
     * 営業終了時間（休前日）を設定します。
     * @param closeTmHlb 営業終了時間（休前日）
     */
    public void setCloseTmHlb(String closeTmHlb) {
        this.closeTmHlb = closeTmHlb;
    }
    
    /**
     * 駐車場：共用隣接を取得します。
     * @return 駐車場：共用隣接
     */
    public BigDecimal getParkCommOut() {
        return parkCommOut;
    }
    /**
     * 駐車場：共用隣接を設定します。
     * @param parkCommOut 駐車場：共用隣接
     */
    public void setParkCommOut(BigDecimal parkCommOut) {
        this.parkCommOut = parkCommOut;
    }
    
    /**
     * 客席数：１Fを取得します。
     * @return 客席数：１F
     */
    public BigDecimal getSeat1F() {
        return seat1F;
    }
    /**
     * 客席数：１Fを設定します。
     * @param seat1F 客席数：１F
     */
    public void setSeat1F(BigDecimal seat1F) {
        this.seat1F = seat1F;
    }
    
    /**
     * 設計業者名を取得します。
     * @return 設計業者名
     */
    public String getToriSekkei() {
        return toriSekkei;
    }
    /**
     * 設計業者名を設定します。
     * @param toriSekkei 設計業者名
     */
    public void setToriSekkei(String toriSekkei) {
        this.toriSekkei = toriSekkei;
    }
    
    /**
     * 客席数：２Fを取得します。
     * @return 客席数：２F
     */
    public BigDecimal getSeat2F() {
        return seat2F;
    }
    /**
     * 客席数：２Fを設定します。
     * @param seat2F 客席数：２F
     */
    public void setSeat2F(BigDecimal seat2F) {
        this.seat2F = seat2F;
    }
    
    /**
     * 施工業者名を取得します。
     * @return 施工業者名
     */
    public String getToriSekou() {
        return toriSekou;
    }
    /**
     * 施工業者名を設定します。
     * @param toriSekou 施工業者名
     */
    public void setToriSekou(String toriSekou) {
        this.toriSekou = toriSekou;
    }
    
    /**
     * 客席数：３Fを取得します。
     * @return 客席数：３F
     */
    public BigDecimal getSeat3F() {
        return seat3F;
    }
    /**
     * 客席数：３Fを設定します。
     * @param seat3F 客席数：３F
     */
    public void setSeat3F(BigDecimal seat3F) {
        this.seat3F = seat3F;
    }
    
    /**
     * 客席数：地下を取得します。
     * @return 客席数：地下
     */
    public BigDecimal getSeatUnder() {
        return seatUnder;
    }
    /**
     * 客席数：地下を設定します。
     * @param seatUnder 客席数：地下
     */
    public void setSeatUnder(BigDecimal seatUnder) {
        this.seatUnder = seatUnder;
    }
    
    /**
     * 客席数：共用を取得します。
     * @return 客席数：共用
     */
    public BigDecimal getSeatCommon() {
        return seatCommon;
    }
    /**
     * 客席数：共用を設定します。
     * @param seatCommon 客席数：共用
     */
    public void setSeatCommon(BigDecimal seatCommon) {
        this.seatCommon = seatCommon;
    }
    
    /**
     * 客席数：その他を取得します。
     * @return 客席数：その他
     */
    public BigDecimal getSeatOther() {
        return seatOther;
    }
    /**
     * 客席数：その他を設定します。
     * @param seatOther 客席数：その他
     */
    public void setSeatOther(BigDecimal seatOther) {
        this.seatOther = seatOther;
    }
    
    /**
     * 事業計画：投資額を取得します。
     * @return 事業計画：投資額
     */
    public BigDecimal getPrjInvest() {
        return prjInvest;
    }
    /**
     * 事業計画：投資額を設定します。
     * @param prjInvest 事業計画：投資額
     */
    public void setPrjInvest(BigDecimal prjInvest) {
        this.prjInvest = prjInvest;
    }
    
    /**
     * 事業計画：売上を取得します。
     * @return 事業計画：売上
     */
    public BigDecimal getPrjUriage() {
        return prjUriage;
    }
    /**
     * 事業計画：売上を設定します。
     * @param prjUriage 事業計画：売上
     */
    public void setPrjUriage(BigDecimal prjUriage) {
        this.prjUriage = prjUriage;
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public String getTax() {
        return tax;
    }
    /**
     * 消費税を設定します。
     * @param tax 消費税
     */
    public void setTax(String tax) {
        this.tax = tax;
    }
    
    /**
     * 店舗面積（坪）を取得します。
     * @return 店舗面積（坪）
     */
    public BigDecimal getMiseMenseki() {
        return miseMenseki;
    }
    /**
     * 店舗面積（坪）を設定します。
     * @param miseMenseki 店舗面積（坪）
     */
    public void setMiseMenseki(BigDecimal miseMenseki) {
        this.miseMenseki = miseMenseki;
    }
    
    /**
     * 宅配比率を取得します。
     * @return 宅配比率
     */
    public BigDecimal getRateTakuhai() {
        return rateTakuhai;
    }
    /**
     * 宅配比率を設定します。
     * @param rateTakuhai 宅配比率
     */
    public void setRateTakuhai(BigDecimal rateTakuhai) {
        this.rateTakuhai = rateTakuhai;
    }
    
    /**
     * 外販比率を取得します。
     * @return 外販比率
     */
    public BigDecimal getRateGaihan() {
        return rateGaihan;
    }
    /**
     * 外販比率を設定します。
     * @param rateGaihan 外販比率
     */
    public void setRateGaihan(BigDecimal rateGaihan) {
        this.rateGaihan = rateGaihan;
    }
    
    /**
     * 敷地面積（坪）を取得します。
     * @return 敷地面積（坪）
     */
    public BigDecimal getSikiMenseki() {
        return sikiMenseki;
    }
    /**
     * 敷地面積（坪）を設定します。
     * @param sikiMenseki 敷地面積（坪）
     */
    public void setSikiMenseki(BigDecimal sikiMenseki) {
        this.sikiMenseki = sikiMenseki;
    }
    
    /**
     * イベント販売時期を取得します。
     * @return イベント販売時期
     */
    public String getEventTime() {
        return eventTime;
    }
    /**
     * イベント販売時期を設定します。
     * @param eventTime イベント販売時期
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    
    /**
     * 客席面積（坪）を取得します。
     * @return 客席面積（坪）
     */
    public BigDecimal getKyakMenseki() {
        return kyakMenseki;
    }
    /**
     * 客席面積（坪）を設定します。
     * @param kyakMenseki 客席面積（坪）
     */
    public void setKyakMenseki(BigDecimal kyakMenseki) {
        this.kyakMenseki = kyakMenseki;
    }
    
    /**
     * 駐車場：総台数を取得します。
     * @return 駐車場：総台数
     */
    public BigDecimal getParkKei() {
        return parkKei;
    }
    /**
     * 駐車場：総台数を設定します。
     * @param parkKei 駐車場：総台数
     */
    public void setParkKei(BigDecimal parkKei) {
        this.parkKei = parkKei;
    }
    
    /**
     * イベント販売場所を取得します。
     * @return イベント販売場所
     */
    public String getEventSpot() {
        return eventSpot;
    }
    /**
     * イベント販売場所を設定します。
     * @param eventSpot イベント販売場所
     */
    public void setEventSpot(String eventSpot) {
        this.eventSpot = eventSpot;
    }
    
    /**
     * イベント内容を取得します。
     * @return イベント内容
     */
    public String getEventContents() {
        return eventContents;
    }
    /**
     * イベント内容を設定します。
     * @param eventContents イベント内容
     */
    public void setEventContents(String eventContents) {
        this.eventContents = eventContents;
    }
    
    /**
     * 最寄り駅：線を取得します。
     * @return 最寄り駅：線
     */
    public String getAccessLine() {
        return accessLine;
    }
    /**
     * 最寄り駅：線を設定します。
     * @param accessLine 最寄り駅：線
     */
    public void setAccessLine(String accessLine) {
        this.accessLine = accessLine;
    }
    
    /**
     * 最寄り駅：駅を取得します。
     * @return 最寄り駅：駅
     */
    public String getAccessStation() {
        return accessStation;
    }
    /**
     * 最寄り駅：駅を設定します。
     * @param accessStation 最寄り駅：駅
     */
    public void setAccessStation(String accessStation) {
        this.accessStation = accessStation;
    }
    
    /**
     * 距離を取得します。
     * @return 距離
     */
    public BigDecimal getAccessDist() {
        return accessDist;
    }
    /**
     * 距離を設定します。
     * @param accessDist 距離
     */
    public void setAccessDist(BigDecimal accessDist) {
        this.accessDist = accessDist;
    }
    
    /**
     * 客席数：総客席数を取得します。
     * @return 客席数：総客席数
     */
    public BigDecimal getSeatKei() {
        return seatKei;
    }
    /**
     * 客席数：総客席数を設定します。
     * @param seatKei 客席数：総客席数
     */
    public void setSeatKei(BigDecimal seatKei) {
        this.seatKei = seatKei;
    }
    
    /**
     * 時間を取得します。
     * @return 時間
     */
    public BigDecimal getAccessTime() {
        return accessTime;
    }
    /**
     * 時間を設定します。
     * @param accessTime 時間
     */
    public void setAccessTime(BigDecimal accessTime) {
        this.accessTime = accessTime;
    }
    
    /**
     * 交通手段を取得します。
     * @return 交通手段
     */
    public String getAccessWay() {
        return accessWay;
    }
    /**
     * 交通手段を設定します。
     * @param accessWay 交通手段
     */
    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }
    
    /**
     * 地図を取得します。
     * @return 地図
     */
    public String getMap() {
        return map;
    }
    /**
     * 地図を設定します。
     * @param map 地図
     */
    public void setMap(String map) {
        this.map = map;
    }
    
    /**
     * 写真を取得します。
     * @return 写真
     */
    public String getPhoto() {
        return photo;
    }
    /**
     * 写真を設定します。
     * @param photo 写真
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    /**
     * 店休日を取得します。
     * @return 店休日
     */
    public String getHolNotes() {
        return holNotes;
    }
    /**
     * 店休日を設定します。
     * @param holNotes 店休日
     */
    public void setHolNotes(String holNotes) {
        this.holNotes = holNotes;
    }
    
    /**
     * 厨房面積（坪）を取得します。
     * @return 厨房面積（坪）
     */
    public BigDecimal getChuboMenseki() {
        return chuboMenseki;
    }
    /**
     * 厨房面積（坪）を設定します。
     * @param chuboMenseki 厨房面積（坪）
     */
    public void setChuboMenseki(BigDecimal chuboMenseki) {
        this.chuboMenseki = chuboMenseki;
    }
    
    /**
     * その他面積（坪）を取得します。
     * @return その他面積（坪）
     */
    public BigDecimal getOtherMenseki() {
        return otherMenseki;
    }
    /**
     * その他面積（坪）を設定します。
     * @param otherMenseki その他面積（坪）
     */
    public void setOtherMenseki(BigDecimal otherMenseki) {
        this.otherMenseki = otherMenseki;
    }
    
    /**
     * 引継元店コードを取得します。
     * @return 引継元店コード
     */
    public String getMiseCdMoto() {
        return miseCdMoto;
    }
    /**
     * 引継元店コードを設定します。
     * @param miseCdMoto 引継元店コード
     */
    public void setMiseCdMoto(String miseCdMoto) {
        this.miseCdMoto = miseCdMoto;
    }
    
    /**
     * 引継日を取得します。
     * @return 引継日
     */
    public String getHikitugiDt() {
        return hikitugiDt;
    }
    /**
     * 引継日を設定します。
     * @param hikitugiDt 引継日
     */
    public void setHikitugiDt(String hikitugiDt) {
        this.hikitugiDt = hikitugiDt;
    }
    
    /**
     * 引継日（オープン日）を取得します。
     * @return 引継日（オープン日）
     */
    public String getHikitugiDtOpen() {
        return hikitugiDtOpen;
    }
    /**
     * 引継日（オープン日）を設定します。
     * @param hikitugiDtOpen 引継日（オープン日）
     */
    public void setHikitugiDtOpen(String hikitugiDtOpen) {
        this.hikitugiDtOpen = hikitugiDtOpen;
    }
    
    /**
     * メニュータイプを取得します。
     * @return メニュータイプ
     */
    public String getMenuType() {
        return menuType;
    }
    /**
     * メニュータイプを設定します。
     * @param menuType メニュータイプ
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    
    /**
     * 論理削除フラグを取得します。
     * @return 論理削除フラグ
     */
    public String getRSakujoFlg() {
        return rSakujoFlg;
    }
    /**
     * 論理削除フラグを設定します。
     * @param rSakujoFlg 論理削除フラグ
     */
    public void setRSakujoFlg(String rSakujoFlg) {
        this.rSakujoFlg = rSakujoFlg;
    }
    
    /**
     * 業態変更日を取得します。
     * @return 業態変更日
     */
    public String getGyotaiChgDt() {
        return gyotaiChgDt;
    }
    /**
     * 業態変更日を設定します。
     * @param gyotaiChgDt 業態変更日
     */
    public void setGyotaiChgDt(String gyotaiChgDt) {
        this.gyotaiChgDt = gyotaiChgDt;
    }
    
    /**
     * クローズ理由CDを取得します。
     * @return クローズ理由CD
     */
    public String getCloseReasonCd() {
        return closeReasonCd;
    }
    /**
     * クローズ理由CDを設定します。
     * @param closeReasonCd クローズ理由CD
     */
    public void setCloseReasonCd(String closeReasonCd) {
        this.closeReasonCd = closeReasonCd;
    }
    
    /**
     * クローズ理由備考を取得します。
     * @return クローズ理由備考
     */
    public String getCloseReasonNote() {
        return closeReasonNote;
    }
    /**
     * クローズ理由備考を設定します。
     * @param closeReasonNote クローズ理由備考
     */
    public void setCloseReasonNote(String closeReasonNote) {
        this.closeReasonNote = closeReasonNote;
    }
    
    /**
     * 分煙種別を取得します。
     * @return 分煙種別
     */
    public String getSepSmokeShu() {
        return sepSmokeShu;
    }
    /**
     * 分煙種別を設定します。
     * @param sepSmokeShu 分煙種別
     */
    public void setSepSmokeShu(String sepSmokeShu) {
        this.sepSmokeShu = sepSmokeShu;
    }
    
    /**
     * 転貸を取得します。
     * @return 転貸
     */
    public String getTentai() {
        return tentai;
    }
    /**
     * 転貸を設定します。
     * @param tentai 転貸
     */
    public void setTentai(String tentai) {
        this.tentai = tentai;
    }
    
    /**
     * 転貸開始日を取得します。
     * @return 転貸開始日
     */
    public String getTentaiStartDt() {
        return tentaiStartDt;
    }
    /**
     * 転貸開始日を設定します。
     * @param tentaiStartDt 転貸開始日
     */
    public void setTentaiStartDt(String tentaiStartDt) {
        this.tentaiStartDt = tentaiStartDt;
    }
    
    /**
     * 転貸終了日を取得します。
     * @return 転貸終了日
     */
    public String getTentaiEndDt() {
        return tentaiEndDt;
    }
    /**
     * 転貸終了日を設定します。
     * @param tentaiEndDt 転貸終了日
     */
    public void setTentaiEndDt(String tentaiEndDt) {
        this.tentaiEndDt = tentaiEndDt;
    }
    
    /**
     * 転貸情報を取得します。
     * @return 転貸情報
     */
    public String getTentaiInfo() {
        return tentaiInfo;
    }
    /**
     * 転貸情報を設定します。
     * @param tentaiInfo 転貸情報
     */
    public void setTentaiInfo(String tentaiInfo) {
        this.tentaiInfo = tentaiInfo;
    }
    
    /**
     * 客席数：喫煙総席数を取得します。
     * @return 客席数：喫煙総席数
     */
    public BigDecimal getSeatSmokingKei() {
        return seatSmokingKei;
    }
    /**
     * 客席数：喫煙総席数を設定します。
     * @param seatSmokingKei 客席数：喫煙総席数
     */
    public void setSeatSmokingKei(BigDecimal seatSmokingKei) {
        this.seatSmokingKei = seatSmokingKei;
    }
    
    /**
     * 客席数：禁煙総席数を取得します。
     * @return 客席数：禁煙総席数
     */
    public BigDecimal getSeatNonSmokingKei() {
        return seatNonSmokingKei;
    }
    /**
     * 客席数：禁煙総席数を設定します。
     * @param seatNonSmokingKei 客席数：禁煙総席数
     */
    public void setSeatNonSmokingKei(BigDecimal seatNonSmokingKei) {
        this.seatNonSmokingKei = seatNonSmokingKei;
    }
    
    /**
     * 1F席有無を取得します。
     * @return 1F席有無
     */
    public String getSeat1FFlg() {
        return seat1FFlg;
    }
    /**
     * 1F席有無を設定します。
     * @param seat1FFlg 1F席有無
     */
    public void setSeat1FFlg(String seat1FFlg) {
        this.seat1FFlg = seat1FFlg;
    }
    
    /**
     * 客席数：１F喫煙席数(店内)を取得します。
     * @return 客席数：１F喫煙席数(店内)
     */
    public BigDecimal getSeat1FSmokingIn() {
        return seat1FSmokingIn;
    }
    /**
     * 客席数：１F喫煙席数(店内)を設定します。
     * @param seat1FSmokingIn 客席数：１F喫煙席数(店内)
     */
    public void setSeat1FSmokingIn(BigDecimal seat1FSmokingIn) {
        this.seat1FSmokingIn = seat1FSmokingIn;
    }
    
    /**
     * 客席数：１F禁煙席数(店内)を取得します。
     * @return 客席数：１F禁煙席数(店内)
     */
    public BigDecimal getSeat1FNonSmokingIn() {
        return seat1FNonSmokingIn;
    }
    /**
     * 客席数：１F禁煙席数(店内)を設定します。
     * @param seat1FNonSmokingIn 客席数：１F禁煙席数(店内)
     */
    public void setSeat1FNonSmokingIn(BigDecimal seat1FNonSmokingIn) {
        this.seat1FNonSmokingIn = seat1FNonSmokingIn;
    }
    
    /**
     * 客席数：１F喫煙席数(店外)を取得します。
     * @return 客席数：１F喫煙席数(店外)
     */
    public BigDecimal getSeat1FSmokingOut() {
        return seat1FSmokingOut;
    }
    /**
     * 客席数：１F喫煙席数(店外)を設定します。
     * @param seat1FSmokingOut 客席数：１F喫煙席数(店外)
     */
    public void setSeat1FSmokingOut(BigDecimal seat1FSmokingOut) {
        this.seat1FSmokingOut = seat1FSmokingOut;
    }
    
    /**
     * 客席数：１F禁煙席数(店外)を取得します。
     * @return 客席数：１F禁煙席数(店外)
     */
    public BigDecimal getSeat1FNonSmokingOut() {
        return seat1FNonSmokingOut;
    }
    /**
     * 客席数：１F禁煙席数(店外)を設定します。
     * @param seat1FNonSmokingOut 客席数：１F禁煙席数(店外)
     */
    public void setSeat1FNonSmokingOut(BigDecimal seat1FNonSmokingOut) {
        this.seat1FNonSmokingOut = seat1FNonSmokingOut;
    }
    
    /**
     * 2F席有無を取得します。
     * @return 2F席有無
     */
    public String getSeat2FFlg() {
        return seat2FFlg;
    }
    /**
     * 2F席有無を設定します。
     * @param seat2FFlg 2F席有無
     */
    public void setSeat2FFlg(String seat2FFlg) {
        this.seat2FFlg = seat2FFlg;
    }
    
    /**
     * 客席数：２F喫煙席数(店内)を取得します。
     * @return 客席数：２F喫煙席数(店内)
     */
    public BigDecimal getSeat2FSmokingIn() {
        return seat2FSmokingIn;
    }
    /**
     * 客席数：２F喫煙席数(店内)を設定します。
     * @param seat2FSmokingIn 客席数：２F喫煙席数(店内)
     */
    public void setSeat2FSmokingIn(BigDecimal seat2FSmokingIn) {
        this.seat2FSmokingIn = seat2FSmokingIn;
    }
    
    /**
     * 客席数：２F禁煙席数(店内)を取得します。
     * @return 客席数：２F禁煙席数(店内)
     */
    public BigDecimal getSeat2FNonSmokingIn() {
        return seat2FNonSmokingIn;
    }
    /**
     * 客席数：２F禁煙席数(店内)を設定します。
     * @param seat2FNonSmokingIn 客席数：２F禁煙席数(店内)
     */
    public void setSeat2FNonSmokingIn(BigDecimal seat2FNonSmokingIn) {
        this.seat2FNonSmokingIn = seat2FNonSmokingIn;
    }
    
    /**
     * 客席数：２F喫煙席数(店外)を取得します。
     * @return 客席数：２F喫煙席数(店外)
     */
    public BigDecimal getSeat2FSmokingOut() {
        return seat2FSmokingOut;
    }
    /**
     * 客席数：２F喫煙席数(店外)を設定します。
     * @param seat2FSmokingOut 客席数：２F喫煙席数(店外)
     */
    public void setSeat2FSmokingOut(BigDecimal seat2FSmokingOut) {
        this.seat2FSmokingOut = seat2FSmokingOut;
    }
    
    /**
     * 客席数：２F禁煙席数(店外)を取得します。
     * @return 客席数：２F禁煙席数(店外)
     */
    public BigDecimal getSeat2FNonSmokingOut() {
        return seat2FNonSmokingOut;
    }
    /**
     * 客席数：２F禁煙席数(店外)を設定します。
     * @param seat2FNonSmokingOut 客席数：２F禁煙席数(店外)
     */
    public void setSeat2FNonSmokingOut(BigDecimal seat2FNonSmokingOut) {
        this.seat2FNonSmokingOut = seat2FNonSmokingOut;
    }
    
    /**
     * 3F席有無を取得します。
     * @return 3F席有無
     */
    public String getSeat3FFlg() {
        return seat3FFlg;
    }
    /**
     * 3F席有無を設定します。
     * @param seat3FFlg 3F席有無
     */
    public void setSeat3FFlg(String seat3FFlg) {
        this.seat3FFlg = seat3FFlg;
    }
    
    /**
     * 客席数：３F喫煙席数(店内)を取得します。
     * @return 客席数：３F喫煙席数(店内)
     */
    public BigDecimal getSeat3FSmokingIn() {
        return seat3FSmokingIn;
    }
    /**
     * 客席数：３F喫煙席数(店内)を設定します。
     * @param seat3FSmokingIn 客席数：３F喫煙席数(店内)
     */
    public void setSeat3FSmokingIn(BigDecimal seat3FSmokingIn) {
        this.seat3FSmokingIn = seat3FSmokingIn;
    }
    
    /**
     * 客席数：３F禁煙席数(店内)を取得します。
     * @return 客席数：３F禁煙席数(店内)
     */
    public BigDecimal getSeat3FNonSmokingIn() {
        return seat3FNonSmokingIn;
    }
    /**
     * 客席数：３F禁煙席数(店内)を設定します。
     * @param seat3FNonSmokingIn 客席数：３F禁煙席数(店内)
     */
    public void setSeat3FNonSmokingIn(BigDecimal seat3FNonSmokingIn) {
        this.seat3FNonSmokingIn = seat3FNonSmokingIn;
    }
    
    /**
     * 客席数：３F喫煙席数(店外)を取得します。
     * @return 客席数：３F喫煙席数(店外)
     */
    public BigDecimal getSeat3FSmokingOut() {
        return seat3FSmokingOut;
    }
    /**
     * 客席数：３F喫煙席数(店外)を設定します。
     * @param seat3FSmokingOut 客席数：３F喫煙席数(店外)
     */
    public void setSeat3FSmokingOut(BigDecimal seat3FSmokingOut) {
        this.seat3FSmokingOut = seat3FSmokingOut;
    }
    
    /**
     * 客席数：３F禁煙席数(店外)を取得します。
     * @return 客席数：３F禁煙席数(店外)
     */
    public BigDecimal getSeat3FNonSmokingOut() {
        return seat3FNonSmokingOut;
    }
    /**
     * 客席数：３F禁煙席数(店外)を設定します。
     * @param seat3FNonSmokingOut 客席数：３F禁煙席数(店外)
     */
    public void setSeat3FNonSmokingOut(BigDecimal seat3FNonSmokingOut) {
        this.seat3FNonSmokingOut = seat3FNonSmokingOut;
    }
    
    /**
     * 地下席有無を取得します。
     * @return 地下席有無
     */
    public String getSeatUnderFlg() {
        return seatUnderFlg;
    }
    /**
     * 地下席有無を設定します。
     * @param seatUnderFlg 地下席有無
     */
    public void setSeatUnderFlg(String seatUnderFlg) {
        this.seatUnderFlg = seatUnderFlg;
    }
    
    /**
     * 客席数：地下F喫煙席数(店内)を取得します。
     * @return 客席数：地下F喫煙席数(店内)
     */
    public BigDecimal getSeatUnderSmokingIn() {
        return seatUnderSmokingIn;
    }
    /**
     * 客席数：地下F喫煙席数(店内)を設定します。
     * @param seatUnderSmokingIn 客席数：地下F喫煙席数(店内)
     */
    public void setSeatUnderSmokingIn(BigDecimal seatUnderSmokingIn) {
        this.seatUnderSmokingIn = seatUnderSmokingIn;
    }
    
    /**
     * 客席数：地下禁煙席数(店内)を取得します。
     * @return 客席数：地下禁煙席数(店内)
     */
    public BigDecimal getSeatUnderNonSmokingIn() {
        return seatUnderNonSmokingIn;
    }
    /**
     * 客席数：地下禁煙席数(店内)を設定します。
     * @param seatUnderNonSmokingIn 客席数：地下禁煙席数(店内)
     */
    public void setSeatUnderNonSmokingIn(BigDecimal seatUnderNonSmokingIn) {
        this.seatUnderNonSmokingIn = seatUnderNonSmokingIn;
    }
    
    /**
     * 客席数：地下F喫煙席数(店外)を取得します。
     * @return 客席数：地下F喫煙席数(店外)
     */
    public BigDecimal getSeatUnderSmokingOut() {
        return seatUnderSmokingOut;
    }
    /**
     * 客席数：地下F喫煙席数(店外)を設定します。
     * @param seatUnderSmokingOut 客席数：地下F喫煙席数(店外)
     */
    public void setSeatUnderSmokingOut(BigDecimal seatUnderSmokingOut) {
        this.seatUnderSmokingOut = seatUnderSmokingOut;
    }
    
    /**
     * 客席数：地下禁煙席数(店外)を取得します。
     * @return 客席数：地下禁煙席数(店外)
     */
    public BigDecimal getSeatUnderNonSmokingOut() {
        return seatUnderNonSmokingOut;
    }
    /**
     * 客席数：地下禁煙席数(店外)を設定します。
     * @param seatUnderNonSmokingOut 客席数：地下禁煙席数(店外)
     */
    public void setSeatUnderNonSmokingOut(BigDecimal seatUnderNonSmokingOut) {
        this.seatUnderNonSmokingOut = seatUnderNonSmokingOut;
    }
    
    /**
     * 共用席有無を取得します。
     * @return 共用席有無
     */
    public String getSeatCommonFlg() {
        return seatCommonFlg;
    }
    /**
     * 共用席有無を設定します。
     * @param seatCommonFlg 共用席有無
     */
    public void setSeatCommonFlg(String seatCommonFlg) {
        this.seatCommonFlg = seatCommonFlg;
    }
    
    /**
     * 客席数：共用F喫煙席数(店内)を取得します。
     * @return 客席数：共用F喫煙席数(店内)
     */
    public BigDecimal getSeatCommonSmokingIn() {
        return seatCommonSmokingIn;
    }
    /**
     * 客席数：共用F喫煙席数(店内)を設定します。
     * @param seatCommonSmokingIn 客席数：共用F喫煙席数(店内)
     */
    public void setSeatCommonSmokingIn(BigDecimal seatCommonSmokingIn) {
        this.seatCommonSmokingIn = seatCommonSmokingIn;
    }
    
    /**
     * 客席数：共用F禁煙席数(店内)を取得します。
     * @return 客席数：共用F禁煙席数(店内)
     */
    public BigDecimal getSeatCommonNonSmokingIn() {
        return seatCommonNonSmokingIn;
    }
    /**
     * 客席数：共用F禁煙席数(店内)を設定します。
     * @param seatCommonNonSmokingIn 客席数：共用F禁煙席数(店内)
     */
    public void setSeatCommonNonSmokingIn(BigDecimal seatCommonNonSmokingIn) {
        this.seatCommonNonSmokingIn = seatCommonNonSmokingIn;
    }
    
    /**
     * 客席数：共用F喫煙席数(店外)を取得します。
     * @return 客席数：共用F喫煙席数(店外)
     */
    public BigDecimal getSeatCommonSmokingOut() {
        return seatCommonSmokingOut;
    }
    /**
     * 客席数：共用F喫煙席数(店外)を設定します。
     * @param seatCommonSmokingOut 客席数：共用F喫煙席数(店外)
     */
    public void setSeatCommonSmokingOut(BigDecimal seatCommonSmokingOut) {
        this.seatCommonSmokingOut = seatCommonSmokingOut;
    }
    
    /**
     * 客席数：共用F禁煙席数(店外)を取得します。
     * @return 客席数：共用F禁煙席数(店外)
     */
    public BigDecimal getSeatCommonNonSmokingOut() {
        return seatCommonNonSmokingOut;
    }
    /**
     * 客席数：共用F禁煙席数(店外)を設定します。
     * @param seatCommonNonSmokingOut 客席数：共用F禁煙席数(店外)
     */
    public void setSeatCommonNonSmokingOut(BigDecimal seatCommonNonSmokingOut) {
        this.seatCommonNonSmokingOut = seatCommonNonSmokingOut;
    }
    
    /**
     * その他席有無を取得します。
     * @return その他席有無
     */
    public String getSeatOtherFlg() {
        return seatOtherFlg;
    }
    /**
     * その他席有無を設定します。
     * @param seatOtherFlg その他席有無
     */
    public void setSeatOtherFlg(String seatOtherFlg) {
        this.seatOtherFlg = seatOtherFlg;
    }
    
    /**
     * 客席数：その他喫煙席数(店内)を取得します。
     * @return 客席数：その他喫煙席数(店内)
     */
    public BigDecimal getSeatOtherSmokingIn() {
        return seatOtherSmokingIn;
    }
    /**
     * 客席数：その他喫煙席数(店内)を設定します。
     * @param seatOtherSmokingIn 客席数：その他喫煙席数(店内)
     */
    public void setSeatOtherSmokingIn(BigDecimal seatOtherSmokingIn) {
        this.seatOtherSmokingIn = seatOtherSmokingIn;
    }
    
    /**
     * 客席数：その他禁煙席数(店内)を取得します。
     * @return 客席数：その他禁煙席数(店内)
     */
    public BigDecimal getSeatOtherNonSmokingIn() {
        return seatOtherNonSmokingIn;
    }
    /**
     * 客席数：その他禁煙席数(店内)を設定します。
     * @param seatOtherNonSmokingIn 客席数：その他禁煙席数(店内)
     */
    public void setSeatOtherNonSmokingIn(BigDecimal seatOtherNonSmokingIn) {
        this.seatOtherNonSmokingIn = seatOtherNonSmokingIn;
    }
    
    /**
     * 客席数：その他喫煙席数(店外)を取得します。
     * @return 客席数：その他喫煙席数(店外)
     */
    public BigDecimal getSeatOtherSmokingOut() {
        return seatOtherSmokingOut;
    }
    /**
     * 客席数：その他喫煙席数(店外)を設定します。
     * @param seatOtherSmokingOut 客席数：その他喫煙席数(店外)
     */
    public void setSeatOtherSmokingOut(BigDecimal seatOtherSmokingOut) {
        this.seatOtherSmokingOut = seatOtherSmokingOut;
    }
    
    /**
     * 客席数：その他禁煙席数(店外)を取得します。
     * @return 客席数：その他禁煙席数(店外)
     */
    public BigDecimal getSeatOtherNonSmokingOut() {
        return seatOtherNonSmokingOut;
    }
    /**
     * 客席数：その他禁煙席数(店外)を設定します。
     * @param seatOtherNonSmokingOut 客席数：その他禁煙席数(店外)
     */
    public void setSeatOtherNonSmokingOut(BigDecimal seatOtherNonSmokingOut) {
        this.seatOtherNonSmokingOut = seatOtherNonSmokingOut;
    }
    
    /**
     * 交通手段備考を取得します。
     * @return 交通手段備考
     */
    public String getAccessWayNote() {
        return accessWayNote;
    }
    /**
     * 交通手段備考を設定します。
     * @param accessWayNote 交通手段備考
     */
    public void setAccessWayNote(String accessWayNote) {
        this.accessWayNote = accessWayNote;
    }
    
    /**
     * 賃貸店舗経理コードを取得します。
     * @return 賃貸店舗経理コード
     */
    public String getMiseLeaseKCd() {
        return miseLeaseKCd;
    }
    /**
     * 賃貸店舗経理コードを設定します。
     * @param miseLeaseKCd 賃貸店舗経理コード
     */
    public void setMiseLeaseKCd(String miseLeaseKCd) {
        this.miseLeaseKCd = miseLeaseKCd;
    }
    
    /**
     * FC契約日を取得します。
     * @return FC契約日
     */
    public String getKeiyakuDt() {
        return keiyakuDt;
    }
    /**
     * FC契約日を設定します。
     * @param keiyakuDt FC契約日
     */
    public void setKeiyakuDt(String keiyakuDt) {
        this.keiyakuDt = keiyakuDt;
    }
    
    /**
     * FC解約日を取得します。
     * @return FC解約日
     */
    public String getKaiyakuDt() {
        return kaiyakuDt;
    }
    /**
     * FC解約日を設定します。
     * @param kaiyakuDt FC解約日
     */
    public void setKaiyakuDt(String kaiyakuDt) {
        this.kaiyakuDt = kaiyakuDt;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝを取得します。
     * @return ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    public short getMstVer() {
        return mstVer;
    }
    /**
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝを設定します。
     * @param mstVer ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    public void setMstVer(short mstVer) {
        this.mstVer = mstVer;
    }
    
}
