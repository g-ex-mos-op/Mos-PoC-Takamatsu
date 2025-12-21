package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

public class MstMise {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseNameKna_COLUMN = "MISE_NAME_KNA";
    
    public static final String miseTel_COLUMN = "MISE_TEL";
    
    public static final String telOffice_COLUMN = "TEL_OFFICE";
    
    public static final String p4TelNo_COLUMN = "P4_TEL_NO";
    
    public static final String telNote_COLUMN = "TEL_NOTES";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    
    public static final String miseKbn_COLUMN = "MISE_KBN";
    
    public static final String aiteName_COLUMN = "AITE_NAME";
    
    public static final String misePostNo_COLUMN = "MISE_POST_NO";
    
    public static final String miseAdrs1_COLUMN = "MISE_ADRS1";
    
    public static final String miseAdrs2_COLUMN = "MISE_ADRS2";
    
    public static final String miseAdrs3_COLUMN = "MISE_ADRS3";
    
    public static final String miseAdrs_COLUMN = "MISE_ADRS";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String fcKeiyaku_COLUMN = "FC_KEIYAKU";
    
    public static final String fcKaiyaku_COLUMN = "FC_KAIYAKU";
    
    public static final String closeReasonCd_COLUMN = "CLOSE_REASON_CD";
    
    public static final String closeReasonName_COLUMN = "CLOSE_REASON_NAME";
    
    public static final String closeReasonNote_COLUMN = "CLOSE_REASON_NOTE";
    
    public static final String menuType_COLUMN = "MENU_TYPE";
    
    public static final String typeName_COLUMN = "TYPE_NAME";
    
    public static final String miseKeitai_COLUMN = "MISE_KEITAI";
    
    public static final String mKeitaiName_COLUMN = "M_KEITAI_NAME";
    
//    public static final String tLocateKbn_COLUMN = "T_LOCATE_KBN";
    public static final String tLocateKbn_COLUMN = "LOCATE_KBN";
    
    public static final String locateName_COLUMN = "LOCATE_NAME";
    
    public static final String sikiMenseki_COLUMN = "SIKI_MENSEKI";
    
    public static final String miseMenseki_COLUMN = "MISE_MENSEKI";
    
    public static final String kyakMenseki_COLUMN = "KYAK_MENSEKI";
    
    public static final String openTmWkd_COLUMN = "OPEN_TM_WKD";
    
    public static final String closeTmWkd_COLUMN = "CLOSE_TM_WKD";
    
    public static final String openTmSat_COLUMN = "OPEN_TM_SAT";
    
    public static final String closeTmSat_COLUMN = "CLOSE_TM_SAT";
    
    public static final String openTmHol_COLUMN = "OPEN_TM_HOL";
    
    public static final String closeTmHol_COLUMN = "CLOSE_TM_HOL";
    
    public static final String openTmHlb_COLUMN = "OPEN_TM_HLB";
    
    public static final String closeTmHlb_COLUMN = "CLOSE_TM_HLB";
    
    public static final String parkOnlyIn_COLUMN = "PARK_ONLY_IN";
    
    public static final String parkOnlyOut_COLUMN = "PARK_ONLY_OUT";
    
    public static final String parkCommIn_COLUMN = "PARK_COMM_IN";
    
    public static final String parkCommOut_COLUMN = "PARK_COMM_OUT";
    
    public static final String parkKei_COLUMN = "PARK_KEI";
    
    public static final String sepSmokeShu_COLUMN = "SEP_SMOKE_SHU";
    
    public static final String sepSmokeShuName_COLUMN = "SEP_SMOKE_SHU_NAME";
    
    public static final String seatSmokingKei_COLUMN = "SEAT_SMOKING_KEI";
    
    public static final String seatNonSmokingKei_COLUMN = "SEAT_NON_SMOKING_KEI";
    
//    public static final String tSouSekisuu_COLUMN = "T_SOU_SEKISUU";
    public static final String tSouSekisuu_COLUMN = "SEAT_KEI";
    
    public static final String seat1F_COLUMN = "SEAT_1F";
    
    public static final String seat1FSmokingIn_COLUMN = "SEAT_1F_SMOKING_IN";
    
    public static final String seat1FNonSmokingIn_COLUMN = "SEAT_1F_NON_SMOKING_IN";
    
    public static final String seat1FSmokingOut_COLUMN = "SEAT_1F_SMOKING_OUT";
    
    public static final String seat1FNonSmokingOut_COLUMN = "SEAT_1F_NON_SMOKING_OUT";
    
    public static final String seat2F_COLUMN = "SEAT_2F";
    
    public static final String seat2FSmokingIn_COLUMN = "SEAT_2F_SMOKING_IN";
    
    public static final String seat2FNonSmokingIn_COLUMN = "SEAT_2F_NON_SMOKING_IN";
    
    public static final String seat2FSmokingOut_COLUMN = "SEAT_2F_SMOKING_OUT";
    
    public static final String seat2FNonSmokingOut_COLUMN = "SEAT_2F_NON_SMOKING_OUT";
    
    public static final String seat3F_COLUMN = "SEAT_3F";
    
    public static final String seat3FSmokingIn_COLUMN = "SEAT_3F_SMOKING_IN";
    
    public static final String seat3FNonSmokingIn_COLUMN = "SEAT_3F_NON_SMOKING_IN";
    
    public static final String seat3FSmokingOut_COLUMN = "SEAT_3F_SMOKING_OUT";
    
    public static final String seat3FNonSmokingOut_COLUMN = "SEAT_3F_NON_SMOKING_OUT";
    
    public static final String seatUnder_COLUMN = "SEAT_UNDER";
    
    public static final String seatUnderSmokingIn_COLUMN = "SEAT_UNDER_SMOKING_IN";
    
    public static final String seatUnderNonSmokingIn_COLUMN = "SEAT_UNDER_NON_SMOKING_IN";
    
    public static final String seatUnderSmokingOut_COLUMN = "SEAT_UNDER_SMOKING_OUT";
    
    public static final String seatUnderNonSmokingOut_COLUMN = "SEAT_UNDER_NON_SMOKING_OUT";
    
    public static final String seatCommon_COLUMN = "SEAT_COMMON";
    
    public static final String seatCommonSmokingIn_COLUMN = "SEAT_COMMON_SMOKING_IN";
    
    public static final String seatCommonNonSmokingIn_COLUMN = "SEAT_COMMON_NON_SMOKING_IN";
    
    public static final String seatCommonSmokingOut_COLUMN = "SEAT_COMMON_SMOKING_OUT";
    
    public static final String seatCommonNonSmokingOut_COLUMN = "SEAT_COMMON_NON_SMOKING_OUT";
    
    public static final String seatOther_COLUMN = "SEAT_OTHER";
    
    public static final String seatOtherSmokingIn_COLUMN = "SEAT_OTHER_SMOKING_IN";
    
    public static final String seatOtherNonSmokingIn_COLUMN = "SEAT_OTHER_NON_SMOKING_IN";
    
    public static final String seatOtherSmokingOut_COLUMN = "SEAT_OTHER_SMOKING_OUT";
    
    public static final String seatOtherNonSmokingOut_COLUMN = "SEAT_OTHER_NON_SMOKING_OUT";
    
    public static final String seatSmokingKei1_COLUMN = "SEAT_SMOKING_KEI_1";
    
    public static final String seatNonSmokingKei1_COLUMN = "SEAT_NON_SMOKING_KEI_1";
    
    public static final String seatSmokingKei2_COLUMN = "SEAT_SMOKING_KEI_2";
    
    public static final String seatNonSmokingKei2_COLUMN = "SEAT_NON_SMOKING_KEI_2";
    
    public static final String seatSmokingKei3_COLUMN = "SEAT_SMOKING_KEI_3";
    
    public static final String seatNonSmokingKei3_COLUMN = "SEAT_NON_SMOKING_KEI_3";
    
    public static final String seatSmokingKeiU_COLUMN = "SEAT_SMOKING_KEI_U";
    
    public static final String seatNonSmokingKeiU_COLUMN = "SEAT_NON_SMOKING_KEI_U";
    
    public static final String seatSmokingKeiC_COLUMN = "SEAT_SMOKING_KEI_C";
    
    public static final String seatNonSmokingKeiC_COLUMN = "SEAT_NON_SMOKING_KEI_C";
    
    public static final String seatSmokingKeiO_COLUMN = "SEAT_SMOKING_KEI_O";
    
    public static final String seatNonSmokingKeiO_COLUMN = "SEAT_NON_SMOKING_KEI_O";
    
    public static final String totalSmokingIn_COLUMN = "TOTAL_SMOKING_IN";
    
    public static final String totalSmokingOut_COLUMN = "TOTAL_SMOKING_OUT";
    
    public static final String totalNonSmokingIn_COLUMN = "TOTAL_NON_SMOKING_IN";
    
    public static final String totalNonSmokingOut_COLUMN = "TOTAL_NON_SMOKING_OUT";
    
    public static final String prjInvest_COLUMN = "PRJ_INVEST";
    
    public static final String prjUriage_COLUMN = "PRJ_URIAGE";
    
    public static final String toriSekkei_COLUMN = "TORI_SEKKEI";
    
    public static final String toriSekou_COLUMN = "TORI_SEKOU";
    
    public static final String keiyakuFirst_COLUMN = "KEIYAKU_FIRST";
    
    public static final String keiyakuNotes_COLUMN = "KEIYAKU_NOTES";
    
    public static final String tentai_COLUMN = "TENTAI";
    
    public static final String tentaiStartDt_COLUMN = "TENTAI_START_DT";
    
    public static final String tentaiEndDt_COLUMN = "TENTAI_END_DT";
    
    public static final String tentaiInfo_COLUMN = "TENTAI_INFO";
    
    public static final String accessLine_COLUMN = "ACCESS_LINE";
    
    public static final String accessStation_COLUMN = "ACCESS_STATION";
    
    public static final String accessDist_COLUMN = "ACCESS_DIST";
    
    public static final String accessTime_COLUMN = "ACCESS_TIME";
    
    public static final String accessWay_COLUMN = "ACCESS_WAY";
    
    public static final String accessWayName_COLUMN = "ACCESS_WAY_NAME";
    
    public static final String accessWayNote_COLUMN = "ACCESS_WAY_NOTE";
    
    public static final String miseCdMoto_COLUMN = "MISE_CD_MOTO";
    
    public static final String hikitugiDtOpen_COLUMN = "HIKITUGI_DT_OPEN";
    
    public static final String keiyakuDt_COLUMN = "KEIYAKU_DT";
    
    public static final String kaiyakuDt_COLUMN = "KAIYAKU_DT";
    
    public static final String seat1fFlg_COLUMN = "SEAT_1F_FLG";
    
    public static final String seat2fFlg_COLUMN = "SEAT_2F_FLG";
    
    public static final String seat3fFlg_COLUMN = "SEAT_3F_FLG";
    
    public static final String seatUnderFlg_COLUMN = "SEAT_UNDER_FLG";
    
    public static final String seatCommonFlg_COLUMN = "SEAT_COMMON_FLG";
    
    public static final String seatOtherFlg_COLUMN = "SEAT_OTHER_FLG";

    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    //20060515 '店休日'項目追加
    public static final String holNotes_COLUMN = "HOL_NOTES";
    //2011/04/06 店舗タイプ項目追加
    public static final String kbn2_COLUMN = "KBN2";
    //2011/04/06 店舗タイプ名称項目(MC23MTYP)追加
    public static final String mTypeKbnName_COLUMN = "M_TYPE_KBN_NAME";

    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店名称（カナ）
     */
    private String miseNameKna;
    
    /**
     * 店電話番号
     */
    private String miseTel;
    
    /**
     * 電話番号：事務所
     */
    private String telOffice;
    
    /**
     * 電話番号：POS
     */
    private String p4TelNo;
    
    /**
     * 電話番号：ノート
     */
    private String telNote;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * SV名称
     */
    private String svNameKj;
    
    /**
     * 店区分
     */
    private String miseKbn;
    
    /**
     * 相手区分名称
     */
    private String aiteName;
    
    /**
     * 店郵便番号
     */
    private String misePostNo;
    
    /**
     * 店住所1
     */
    private String miseAdrs1;
    
    /**
     * 店住所2
     */
    private String miseAdrs2;
    
    /**
     * 店住所3
     */
    private String miseAdrs3;
    
    /**
     * 店住所
     */
    private String miseAdrs;
    
    /**
     * 店オープン日
     */
    private String openDt;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * ネットワーク開始日
     */
    private String fcKeiyaku;
    
    /**
     * ネットワーク終了日
     */
    private String fcKaiyaku;
    
    /**
     * クロース理由CD
     */
    private String closeReasonCd;
    
    /**
     * クロース理由名称
     */
    private String closeReasonName;
    
    /**
     * クロース理由備考
     */
    private String closeReasonNote;
    
    /**
     * メニュータイプ
     */
    private String menuType;
    
    /**
     * メニュータイプ名称
     */
    private String typeName;
    
    /**
     * 店舗形態区分
     */
    private String miseKeitai;
    
    /**
     * 店舗形態名称
     */
    private String mKeitaiName;
    
    /**
     * ロケーション区分
     */
    private String tLocateKbn;
    
    /**
     * ロケーション名称
     */
    private String locateName;
    
    /**
     * 敷地面積（坪）
     */
    private BigDecimal sikiMenseki;
    
    /**
     * 店舗面積（坪）
     */
    private BigDecimal miseMenseki;
    
    /**
     * 客席面積（坪）
     */
    private BigDecimal kyakMenseki;
    
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
     * 営業開始時間（日祭日）
     */
    private String openTmHol;
    
    /**
     * 営業終了時間（日祭日）
     */
    private String closeTmHol;
    
    /**
     * 営業開始時間（休前日）
     */
    private String openTmHlb;
    
    /**
     * 営業終了時間（休前日）
     */
    private String closeTmHlb;
    
    /**
     * 駐車場：専用敷地内
     */
    private String parkOnlyIn;
    
    /**
     * 駐車場：専用隣接
     */
    private String parkOnlyOut;
    
    /**
     * 駐車場：共用敷地内
     */
    private String parkCommIn;
    
    /**
     * 駐車場：共用隣接
     */
    private String parkCommOut;
    
    /**
     * 駐車場：総台数
     */
    private String parkKei;
    
    /**
     * 分煙種別
     */
    private String sepSmokeShu;
    
    /**
     * 分煙種別名称
     */
    private String sepSmokeShuName;
    
    /**
     * 喫煙総席数
     */
    private String seatSmokingKei;
    
    /**
     * 禁煙総席数
     */
    private String seatNonSmokingKei;
    
    /**
     * 総席数
     */
    private String tSouSekisuu;
    
    /**
     * 客席数：１F
     */
    private String seat1F;
    
    /**
     * 客席数：１F喫煙席数(店内)
     */
    private String seat1FSmokingIn;
    
    /**
     * 客席数：１F禁煙席数(店内)
     */
    private String seat1FNonSmokingIn;
    
    /**
     * 客席数：１F喫煙席数(店外)
     */
    private String seat1FSmokingOut;
    
    /**
     * 客席数：１F禁煙席数(店外)
     */
    private String seat1FNonSmokingOut;
    
    /**
     * 客席数：２F
     */
    private String seat2F;
    
    /**
     * 客席数：２F喫煙席数(店内)
     */
    private String seat2FSmokingIn;
    
    /**
     * 客席数：２F禁煙席数(店内)
     */
    private String seat2FNonSmokingIn;
    
    /**
     * 客席数：２F喫煙席数(店外)
     */
    private String seat2FSmokingOut;
    
    /**
     * 客席数：２F禁煙席数(店外)
     */
    private String seat2FNonSmokingOut;
    
    /**
     * 客席数：３F
     */
    private String seat3F;
    
    /**
     * 客席数：３F喫煙席数(店内)
     */
    private String seat3FSmokingIn;
    
    /**
     * 客席数：３F禁煙席数(店内)
     */
    private String seat3FNonSmokingIn;
    
    /**
     * 客席数：３F喫煙席数(店外)
     */
    private String seat3FSmokingOut;
    
    /**
     * 客席数：３F禁煙席数(店外)
     */
    private String seat3FNonSmokingOut;
    
    /**
     * 客席数：地下
     */
    private String seatUnder;
    
    /**
     * 客席数：地下F喫煙席数(店内)
     */
    private String seatUnderSmokingIn;
    
    /**
     * 客席数：地下禁煙席数(店内)
     */
    private String seatUnderNonSmokingIn;
    
    /**
     * 客席数：地下F喫煙席数(店外)
     */
    private String seatUnderSmokingOut;
    
    /**
     * 客席数：地下禁煙席数(店外)
     */
    private String seatUnderNonSmokingOut;
    
    /**
     * 客席数：共用
     */
    private String seatCommon;
    
    /**
     * 客席数：共用F喫煙席数(店内)
     */
    private String seatCommonSmokingIn;
    
    /**
     * 客席数：共用F禁煙席数(店内)
     */
    private String seatCommonNonSmokingIn;
    
    /**
     * 客席数：共用F喫煙席数(店外)
     */
    private String seatCommonSmokingOut;
    
    /**
     * 客席数：共用F禁煙席数(店外)
     */
    private String seatCommonNonSmokingOut;
    
    /**
     * 客席数：その他
     */
    private String seatOther;
    
    /**
     * 客席数：その他喫煙席数(店内)
     */
    private String seatOtherSmokingIn;
    
    /**
     * 客席数：その他禁煙席数(店内)
     */
    private String seatOtherNonSmokingIn;
    
    /**
     * 客席数：その他喫煙席数(店外)
     */
    private String seatOtherSmokingOut;
    
    /**
     * 客席数：その他禁煙席数(店外)
     */
    private String seatOtherNonSmokingOut;
    
    /**
     * 1F喫煙席数計
     */
    private String seatSmokingKei1;
    
    /**
     * 1F禁煙席数計
     */
    private String seatNonSmokingKei1;
    
    /**
     * 2F喫煙席数計
     */
    private String seatSmokingKei2;
    
    /**
     * 2F禁煙席数計
     */
    private String seatNonSmokingKei2;
    
    /**
     * 3F喫煙席数計
     */
    private String seatSmokingKei3;
    
    /**
     * 3F禁煙席数計
     */
    private String seatNonSmokingKei3;
    
    /**
     * 地下喫煙席数計
     */
    private String seatSmokingKeiU;
    
    /**
     * 地下禁煙席数計
     */
    private String seatNonSmokingKeiU;
    
    /**
     * 共用喫煙席数計
     */
    private String seatSmokingKeiC;
    
    /**
     * 共用禁煙席数計
     */
    private String seatNonSmokingKeiC;
    
    /**
     * その他喫煙席数計
     */
    private String seatSmokingKeiO;
    
    /**
     * その他禁煙席数計
     */
    private String seatNonSmokingKeiO;
    
    /**
     * 総席数喫煙店内
     */
    private String totalSmokingIn;
    
    /**
     * 総席数喫煙店外
     */
    private String totalSmokingOut;
    
    /**
     * 総席数禁煙店内
     */
    private String totalNonSmokingIn;
    
    /**
     * 総席数禁煙店外
     */
    private String totalNonSmokingOut;
    
    /**
     * 事業計画：投資額
     */
    private String prjInvest;
    
    /**
     * 事業計画：売上
     */
    private String prjUriage;
    
    /**
     * 設計業者名
     */
    private String toriSekkei;
    
    /**
     * 施工業者名
     */
    private String toriSekou;
    
    /**
     * 物件契約：契約日
     */
    private String keiyakuFirst;
    
    /**
     * 物件契約：備考
     */
    private String keiyakuNotes;
    
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
     * 時間
     */
    private String accessTime;
    
    /**
     * 交通手段
     */
    private String accessWay;
    
    /**
     * 交通手段名
     */
    private String accessWayName;
    
    /**
     * 交通手段備考
     */
    private String accessWayNote;
    
    /**
     * 引継元店コード
     */
    private String miseCdMoto;
    
    /**
     * 引継日（オープン日）
     */
    private String hikitugiDtOpen;
    
    /**
     * FC契約日
     */
    private String keiyakuDt;
    
    /**
     * FC解約日
     */
    private String kaiyakuDt;
    
    /**
     * 1F席有無
     */
    private String seat1fFlg;
    
    /**
     * 2F席有無
     */
    private String seat2fFlg;
    
    /**
     * 3F席有無
     */
    private String seat3fFlg;

    /**
     * 地下席有無
     */
    private String seatUnderFlg;
    
    /**
     * 共用席有無
     */
    private String seatCommonFlg;
    
    /**
     * その他席有無
     */
    private String seatOtherFlg;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    //20060515'店休日'項目追加
    /**
     * 店休日
     */
    private String holNotes;
    
    /**
     * 店舗タイプ項目
     * 2011/04/06追加 
     */
    private String kbn2;
    /**
     * 店舗タイプ項目名称(MC23MTYP)
     * 2011/04/06追加 
     */
    private String mTypeKbnName;

    //20060515'店休日'項目getter追加
    /**
     * 店休日を取得します。
     * @return 店休日
     */
    public String getHolNotes() {
        return convString(holNotes);
    }
    
    //20060515'店休日'項目setter追加
    /**
     * 店休日を設定します。
     * @param holNotes 店休日
     */
    public void setHolNotes(String holNotes) {
        this.holNotes = holNotes;
    }
    
    
    /**
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
    }
    /**
     * 管理会社コード企業コードを設定します。
     * @param companyCd 管理会社コード企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
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
        return convString(miseNameKna);
    }
    /**
     * 店名称（カナ）を設定します。
     * @param miseNameKna 店名称（カナ）
     */
    public void setMiseNameKna(String miseNameKna) {
        this.miseNameKna = miseNameKna;
    }
    
    /**
     * 店電話番号を取得します。
     * @return 店電話番号
     */
    public String getMiseTel() {
        return convString(miseTel);
    }
    /**
     * 店電話番号を設定します。
     * @param miseTel 店電話番号
     */
    public void setMiseTel(String miseTel) {
        this.miseTel = miseTel;
    }
    
    /**
     * 電話番号：事務所を取得します。
     * @return 電話番号：事務所
     */
    public String getTelOffice() {
        return convString(telOffice);
    }
    /**
     * 電話番号：事務所を設定します。
     * @param telOffice 電話番号：事務所
     */
    public void setTelOffice(String telOffice) {
        this.telOffice = telOffice;
    }
    
    /**
     * 電話番号：POSを取得します。
     * @return 電話番号：POS
     */
    public String getP4TelNo() {
        return convString(p4TelNo);
    }
    /**
     * 電話番号：POSを設定します。
     * @param telPos 電話番号：POS
     */
    public void setP4TelNo(String p4TelNo) {
        this.p4TelNo = p4TelNo;
    }
    
    /**
     * 電話番号：ノートを取得します。
     * @return 電話番号：ノート
     */
    public String getTelNote() {
        return convString(telNote);
    }
    /**
     * 電話番号：ノートを設定します。
     * @param telNote 電話番号：ノート
     */
    public void setTelNote(String telNote) {
        this.telNote = telNote;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return convString(onerCd);
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return convString(onerNameKj);
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * SVコードを取得します。
     * @return SVコード
     */
    public String getSvCd() {
        return convString(svCd);
    }
    /**
     * SVコードを設定します。
     * @param svCd SVコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * SV名称を取得します。
     * @return SV名称
     */
    public String getSvNameKj() {
        return convString(svNameKj);
    }
    /**
     * SV名称を設定します。
     * @param svNameKj SV名称
     */
    public void setSvNameKj(String svNameKj) {
        this.svNameKj = svNameKj;
    }
    
    /**
     * 店区分を取得します。
     * @return 店区分
     */
    public String getMiseKbn() {
        return convString(miseKbn);
    }
    /**
     * 店区分を設定します。
     * @param miseKbn 店区分
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }
    
    /**
     * 相手区分名称を取得します。
     * @return 相手区分名称
     */
    public String getAiteName() {
        return convString(aiteName);
    }
    /**
     * 相手区分名称を設定します。
     * @param aiteName 相手区分名称
     */
    public void setAiteName(String aiteName) {
        this.aiteName = aiteName;
    }
    
    /**
     * 店郵便番号を取得します。
     * @return 店郵便番号
     */
    public String getMisePostNo() {
        return convString(misePostNo);
    }
    /**
     * 店郵便番号を設定します。
     * @param misePostNo 店郵便番号
     */
    public void setMisePostNo(String misePostNo) {
        this.misePostNo = misePostNo;
    }
    
    /**
     * 店住所1を取得します。
     * @return 店住所1
     */
    public String getMiseAdrs1() {
        return convString(miseAdrs1);
    }
    /**
     * 店住所1を設定します。
     * @param miseAdrs1 店住所1
     */
    public void setMiseAdrs1(String miseAdrs1) {
        this.miseAdrs1 = miseAdrs1;
    }
    
    /**
     * 店住所2を取得します。
     * @return 店住所2
     */
    public String getMiseAdrs2() {
        return convString(miseAdrs2);
    }
    /**
     * 店住所2を設定します。
     * @param miseAdrs2 店住所2
     */
    public void setMiseAdrs2(String miseAdrs2) {
        this.miseAdrs2 = miseAdrs2;
    }
    
    /**
     * 店住所3を取得します。
     * @return 店住所3
     */
    public String getMiseAdrs3() {
        return convString(miseAdrs3);
    }
    /**
     * 店住所3を設定します。
     * @param miseAdrs3 店住所3
     */
    public void setMiseAdrs3(String miseAdrs3) {
        this.miseAdrs3 = miseAdrs3;
    }
    
    /**
     * 店住所を取得します。
     * @return 店住所
     */
    public String getMiseAdrs() {
        return convString(miseAdrs);
    }
    /**
     * 店住所を設定します。
     * @param miseAdrs 店住所
     */
    public void setMiseAdrs(String miseAdrs) {
        this.miseAdrs = miseAdrs;
    }
    
    /**
     * 店オープン日を取得します。
     * @return 店オープン日
     */
    public String getOpenDt() {
        return convString(openDt);
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
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * ネットワーク開始日を取得します。
     * @return ネットワーク開始日
     */
    public String getFcKeiyaku() {
        return convString(fcKeiyaku);
    }
    /**
     * ネットワーク開始日を設定します。
     * @param fcKeiyaku ネットワーク開始日
     */
    public void setFcKeiyaku(String fcKeiyaku) {
        this.fcKeiyaku = fcKeiyaku;
    }
    
    /**
     * ネットワーク終了日を取得します。
     * @return ネットワーク終了日
     */
    public String getFcKaiyaku() {
        return convString(fcKaiyaku);
    }
    /**
     * ネットワーク終了日を設定します。
     * @param fcKaiyaku ネットワーク終了日
     */
    public void setFcKaiyaku(String fcKaiyaku) {
        this.fcKaiyaku = fcKaiyaku;
    }
    
    /**
     * クロース理由CDを取得します。
     * @return クロース理由CD
     */
    public String getCloseReasonCd() {
        return convString(closeReasonCd);
    }
    /**
     * クロース理由CDを設定します。
     * @param closeReasonCd クロース理由CD
     */
    public void setCloseReasonCd(String closeReasonCd) {
        this.closeReasonCd = closeReasonCd;
    }
    
    /**
     * クロース理由名称を取得します。
     * @return クロース理由名称
     */
    public String getCloseReasonName() {
        return convString(closeReasonName);
    }
    /**
     * クロース理由名称を設定します。
     * @param closeReasonName クロース理由名称
     */
    public void setCloseReasonName(String closeReasonName) {
        this.closeReasonName = closeReasonName;
    }
    
    /**
     * クロース理由備考を取得します。
     * @return クロース理由備考
     */
    public String getCloseReasonNote() {
        return convString(closeReasonNote);
    }
    /**
     * クロース理由備考を設定します。
     * @param closeReasonNote クロース理由備考
     */
    public void setCloseReasonNote(String closeReasonNote) {
        this.closeReasonNote = closeReasonNote;
    }
    
    /**
     * メニュータイプを取得します。
     * @return メニュータイプ
     */
    public String getMenuType() {
        return convString(menuType);
    }
    /**
     * メニュータイプを設定します。
     * @param menuType メニュータイプ
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    
    /**
     * メニュータイプ名称を取得します。
     * @return メニュータイプ名称
     */
    public String getTypeName() {
        return convString(typeName);
    }
    /**
     * メニュータイプ名称を設定します。
     * @param typeName メニュータイプ名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    /**
     * 店舗形態区分を取得します。
     * @return 店舗形態区分
     */
    public String getMiseKeitai() {
        return convString(miseKeitai);
    }
    /**
     * 店舗形態区分を設定します。
     * @param miseKeitai 店舗形態区分
     */
    public void setMiseKeitai(String miseKeitai) {
        this.miseKeitai = miseKeitai;
    }
    
    /**
     * 店舗形態名称を取得します。
     * @return 店舗形態名称
     */
    public String getMKeitaiName() {
        return convString(mKeitaiName);
    }
    /**
     * 店舗形態名称を設定します。
     * @param mKeitaiName 店舗形態名称
     */
    public void setMKeitaiName(String mKeitaiName) {
        this.mKeitaiName = mKeitaiName;
    }
    
    /**
     * ロケーション区分を取得します。
     * @return ロケーション区分
     */
    public String getTLocateKbn() {
        return convString(tLocateKbn);
    }
    /**
     * ロケーション区分を設定します。
     * @param tLocateKbn ロケーション区分
     */
    public void setTLocateKbn(String tLocateKbn) {
        this.tLocateKbn = tLocateKbn;
    }
    
    /**
     * ロケーション名称を取得します。
     * @return ロケーション名称
     */
    public String getLocateName() {
        return convString(locateName);
    }
    /**
     * ロケーション名称を設定します。
     * @param locateName ロケーション名称
     */
    public void setLocateName(String locateName) {
        this.locateName = locateName;
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
     * 営業開始時間（平日）を取得します。
     * @return 営業開始時間（平日）
     */
    public String getOpenTmWkd() {
        return convString(openTmWkd);
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
        return convString(closeTmWkd);
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
        return convString(openTmSat);
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
        return convString(closeTmSat);
    }
    /**
     * 営業終了時間（土曜）を設定します。
     * @param closeTmSat 営業終了時間（土曜）
     */
    public void setCloseTmSat(String closeTmSat) {
        this.closeTmSat = closeTmSat;
    }
    
    /**
     * 営業開始時間（日祭日）を取得します。
     * @return 営業開始時間（日祭日）
     */
    public String getOpenTmHol() {
        return convString(openTmHol);
    }
    /**
     * 営業開始時間（日祭日）を設定します。
     * @param openTmHol 営業開始時間（日祭日）
     */
    public void setOpenTmHol(String openTmHol) {
        this.openTmHol = openTmHol;
    }
    
    /**
     * 営業終了時間（日祭日）を取得します。
     * @return 営業終了時間（日祭日）
     */
    public String getCloseTmHol() {
        return convString(closeTmHol);
    }
    /**
     * 営業終了時間（日祭日）を設定します。
     * @param closeTmHol 営業終了時間（日祭日）
     */
    public void setCloseTmHol(String closeTmHol) {
        this.closeTmHol = closeTmHol;
    }
    
    /**
     * 営業開始時間（休前日）を取得します。
     * @return 営業開始時間（休前日）
     */
    public String getOpenTmHlb() {
        return convString(openTmHlb);
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
        return convString(closeTmHlb);
    }
    /**
     * 営業終了時間（休前日）を設定します。
     * @param closeTmHlb 営業終了時間（休前日）
     */
    public void setCloseTmHlb(String closeTmHlb) {
        this.closeTmHlb = closeTmHlb;
    }
    
    /**
     * 駐車場：専用敷地内を取得します。
     * @return 駐車場：専用敷地内
     */
    public String getParkOnlyIn() {
        return convStringDec(parkOnlyIn);
    }
    /**
     * 駐車場：専用敷地内を設定します。
     * @param parkOnlyIn 駐車場：専用敷地内
     */
    public void setParkOnlyIn(String parkOnlyIn) {
        this.parkOnlyIn = parkOnlyIn;
    }
    
    /**
     * 駐車場：専用隣接を取得します。
     * @return 駐車場：専用隣接
     */
    public String getParkOnlyOut() {
        return convStringDec(parkOnlyOut);
    }
    /**
     * 駐車場：専用隣接を設定します。
     * @param parkOnlyOut 駐車場：専用隣接
     */
    public void setParkOnlyOut(String parkOnlyOut) {
        this.parkOnlyOut = parkOnlyOut;
    }
    
    /**
     * 駐車場：共用敷地内を取得します。
     * @return 駐車場：共用敷地内
     */
    public String getParkCommIn() {
        return convStringDec(parkCommIn);
    }
    /**
     * 駐車場：共用敷地内を設定します。
     * @param parkCommIn 駐車場：共用敷地内
     */
    public void setParkCommIn(String parkCommIn) {
        this.parkCommIn = parkCommIn;
    }
    
    /**
     * 駐車場：共用隣接を取得します。
     * @return 駐車場：共用隣接
     */
    public String getParkCommOut() {
        return convStringDec(parkCommOut);
    }
    /**
     * 駐車場：共用隣接を設定します。
     * @param parkCommOut 駐車場：共用隣接
     */
    public void setParkCommOut(String parkCommOut) {
        this.parkCommOut = parkCommOut;
    }
    
    /**
     * 駐車場：総台数を取得します。
     * @return 駐車場：総台数
     */
    public String getParkKei() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);

        parkKei = "0";
        
        if (codeVerifierPark.validate(getParkOnlyIn()) 
            && codeVerifierPark.validate(getParkOnlyOut())
            && codeVerifierPark.validate(getParkCommIn()) 
            && codeVerifierPark.validate(getParkCommOut())){
            
            parkKei = String.valueOf(
                    new BigDecimal(getParkOnlyIn())
                    .add(new BigDecimal(getParkOnlyOut()))
                    .add(new BigDecimal(getParkCommIn()))
                    .add(new BigDecimal(getParkCommOut())));
        }
       
        return parkKei;
    }
    /**
     * 駐車場：総台数を設定します。
     * @param parkKei 駐車場：総台数
     */
    public void setParkKei(String parkKei) {
        this.parkKei = parkKei;
    }
    
    /**
     * 分煙種別を取得します。
     * @return 分煙種別
     */
    public String getSepSmokeShu() {
        return convString(sepSmokeShu);
    }
    /**
     * 分煙種別を設定します。
     * @param sepSmokeShu 分煙種別
     */
    public void setSepSmokeShu(String sepSmokeShu) {
        this.sepSmokeShu = sepSmokeShu;
    }
    
    /**
     * 分煙種別名称を取得します。
     * @return 分煙種別名称
     */
    public String getSepSmokeShuName() {
        return convString(sepSmokeShuName);
    }
    /**
     * 分煙種別名称を設定します。
     * @param sepSmokeShuName 分煙種別名称
     */
    public void setSepSmokeShuName(String sepSmokeShuName) {
        this.sepSmokeShuName = sepSmokeShuName;
    }
    
    /**
     * ★☆喫煙総席数を取得します。
     * @return 喫煙総席数
     */
    public String getSeatSmokingKei() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKei = "0";
        
        if (codeVerifierPark.validate(getSeat1FSmokingIn())
            && codeVerifierPark.validate(getSeat1FSmokingOut())
            && codeVerifierPark.validate(getSeat2FSmokingIn())
            && codeVerifierPark.validate(getSeat2FSmokingOut())
            && codeVerifierPark.validate(getSeat3FSmokingIn())
            && codeVerifierPark.validate(getSeat3FSmokingOut())
            && codeVerifierPark.validate(getSeatUnderSmokingIn())
            && codeVerifierPark.validate(getSeatUnderSmokingOut())
            && codeVerifierPark.validate(getSeatCommonSmokingIn())
            && codeVerifierPark.validate(getSeatCommonSmokingOut())
            && codeVerifierPark.validate(getSeatOtherSmokingIn())
            && codeVerifierPark.validate(getSeatOtherSmokingOut())){
                
            seatSmokingKei = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingIn())
                        .add(new BigDecimal(getSeat1FSmokingOut()))
                        .add(new BigDecimal(getSeat2FSmokingIn()))
                        .add(new BigDecimal(getSeat2FSmokingOut()))
                        .add(new BigDecimal(getSeat3FSmokingIn()))
                        .add(new BigDecimal(getSeat3FSmokingOut()))
                        .add(new BigDecimal(getSeatUnderSmokingIn()))
                        .add(new BigDecimal(getSeatUnderSmokingOut()))
                        .add(new BigDecimal(getSeatCommonSmokingIn()))
                        .add(new BigDecimal(getSeatCommonSmokingOut()))
                        .add(new BigDecimal(getSeatOtherSmokingIn()))
                        .add(new BigDecimal(getSeatOtherSmokingOut())));
            }
               
        return seatSmokingKei;
    }
    /**
     * 喫煙総席数を設定します。
     * @param seatSmokingKei 喫煙総席数
     */
    public void setSeatSmokingKei(String seatSmokingKei) {
        this.seatSmokingKei = seatSmokingKei;
    }
    
    /**
     * ★☆禁煙総席数を取得します。
     * @return 禁煙総席数
     */
    public String getSeatNonSmokingKei() {        
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKei = "0";
        
        if (codeVerifierPark.validate(getSeat1FNonSmokingIn())
            && codeVerifierPark.validate(getSeat1FNonSmokingOut())
            && codeVerifierPark.validate(getSeat2FNonSmokingIn())
            && codeVerifierPark.validate(getSeat2FNonSmokingOut())
            && codeVerifierPark.validate(getSeat3FNonSmokingIn())
            && codeVerifierPark.validate(getSeat3FNonSmokingOut())
            && codeVerifierPark.validate(getSeatUnderNonSmokingIn())
            && codeVerifierPark.validate(getSeatUnderNonSmokingOut())
            && codeVerifierPark.validate(getSeatCommonNonSmokingIn())
            && codeVerifierPark.validate(getSeatCommonNonSmokingOut())
            && codeVerifierPark.validate(getSeatOtherNonSmokingIn())
            && codeVerifierPark.validate(getSeatOtherNonSmokingOut())){
            
            seatNonSmokingKei = 
                String.valueOf(
                    new BigDecimal(getSeat1FNonSmokingIn())
                    .add(new BigDecimal(getSeat1FNonSmokingOut()))
                    .add(new BigDecimal(getSeat2FNonSmokingIn()))
                    .add(new BigDecimal(getSeat2FNonSmokingOut()))
                    .add(new BigDecimal(getSeat3FNonSmokingIn()))
                    .add(new BigDecimal(getSeat3FNonSmokingOut()))
                    .add(new BigDecimal(getSeatUnderNonSmokingIn()))
                    .add(new BigDecimal(getSeatUnderNonSmokingOut()))
                    .add(new BigDecimal(getSeatCommonNonSmokingIn()))
                    .add(new BigDecimal(getSeatCommonNonSmokingOut()))
                    .add(new BigDecimal(getSeatOtherNonSmokingIn()))
                    .add(new BigDecimal(getSeatOtherNonSmokingOut())));
        }
              
        return seatNonSmokingKei;
    }
    /**
     * 禁煙総席数を設定します。
     * @param seatNonSmokingKei 禁煙総席数
     */
    public void setSeatNonSmokingKei(String seatNonSmokingKei) {
        this.seatNonSmokingKei = seatNonSmokingKei;
    }
    
    /**
     * ★☆総席数を取得します。
     * @return 総席数
     */
    public String getTSouSekisuu() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        tSouSekisuu = "0";
        
        if (codeVerifierPark.validate(getSeat1FSmokingIn())
                && codeVerifierPark.validate(getSeat1FSmokingOut())
                && codeVerifierPark.validate(getSeat2FSmokingIn())
                && codeVerifierPark.validate(getSeat2FSmokingOut())
                && codeVerifierPark.validate(getSeat3FSmokingIn())
                && codeVerifierPark.validate(getSeat3FSmokingOut())
                && codeVerifierPark.validate(getSeatUnderSmokingIn())
                && codeVerifierPark.validate(getSeatUnderSmokingOut())
                && codeVerifierPark.validate(getSeatCommonSmokingIn())
                && codeVerifierPark.validate(getSeatCommonSmokingOut())
                && codeVerifierPark.validate(getSeatOtherSmokingIn())
                && codeVerifierPark.validate(getSeatOtherSmokingOut())
                && codeVerifierPark.validate(getSeat1FNonSmokingIn())
                && codeVerifierPark.validate(getSeat1FNonSmokingOut())
                && codeVerifierPark.validate(getSeat2FNonSmokingIn())
                && codeVerifierPark.validate(getSeat2FNonSmokingOut())
                && codeVerifierPark.validate(getSeat3FNonSmokingIn())
                && codeVerifierPark.validate(getSeat3FNonSmokingOut())
                && codeVerifierPark.validate(getSeatUnderNonSmokingIn())
                && codeVerifierPark.validate(getSeatUnderNonSmokingOut())
                && codeVerifierPark.validate(getSeatCommonNonSmokingIn())
                && codeVerifierPark.validate(getSeatCommonNonSmokingOut())
                && codeVerifierPark.validate(getSeatOtherNonSmokingIn())
                && codeVerifierPark.validate(getSeatOtherNonSmokingOut())){

            
            tSouSekisuu = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingIn())
                    .add(new BigDecimal(getSeat1FSmokingOut()))
                    .add(new BigDecimal(getSeat2FSmokingIn()))
                    .add(new BigDecimal(getSeat2FSmokingOut()))
                    .add(new BigDecimal(getSeat3FSmokingIn()))
                    .add(new BigDecimal(getSeat3FSmokingOut()))
                    .add(new BigDecimal(getSeatUnderSmokingIn()))
                    .add(new BigDecimal(getSeatUnderSmokingOut()))
                    .add(new BigDecimal(getSeatCommonSmokingIn()))
                    .add(new BigDecimal(getSeatCommonSmokingOut()))
                    .add(new BigDecimal(getSeatOtherSmokingIn()))
                    .add(new BigDecimal(getSeatOtherSmokingOut()))
                    .add(new BigDecimal(getSeat1FNonSmokingIn()))
                    .add(new BigDecimal(getSeat1FNonSmokingOut()))
                    .add(new BigDecimal(getSeat2FNonSmokingIn()))
                    .add(new BigDecimal(getSeat2FNonSmokingOut()))
                    .add(new BigDecimal(getSeat3FNonSmokingIn()))
                    .add(new BigDecimal(getSeat3FNonSmokingOut()))
                    .add(new BigDecimal(getSeatUnderNonSmokingIn()))
                    .add(new BigDecimal(getSeatUnderNonSmokingOut()))
                    .add(new BigDecimal(getSeatCommonNonSmokingIn()))
                    .add(new BigDecimal(getSeatCommonNonSmokingOut()))
                    .add(new BigDecimal(getSeatOtherNonSmokingIn()))
                    .add(new BigDecimal(getSeatOtherNonSmokingOut())));
         }
       
        return tSouSekisuu;
    }
    /**
     * 総席数を設定します。
     * @param tSouSekisuu 総席数
     */
    public void setTSouSekisuu(String tSouSekisuu) {
        this.tSouSekisuu = tSouSekisuu;
    }
    
    /**
     * ★☆客席数：１Fを取得します。
     * @return 客席数：１F
     */
    public String getSeat1F() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seat1F = "0";
        
        if(codeVerifierPark.validate(getSeat1FSmokingIn())
            && codeVerifierPark.validate(getSeat1FSmokingOut())
            && codeVerifierPark.validate(getSeat1FNonSmokingIn())
            && codeVerifierPark.validate(getSeat1FNonSmokingOut())){

            seat1F = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingIn())
                    .add(new BigDecimal(getSeat1FSmokingOut()))
                    .add(new BigDecimal(getSeat1FNonSmokingIn()))
                    .add(new BigDecimal(getSeat1FNonSmokingOut())));
         }
        
        return seat1F;
    }
    /**
     * 客席数：１Fを設定します。
     * @param seat1F 客席数：１F
     */
    public void setSeat1F(String seat1F) {
        this.seat1F = seat1F;
    }
    
    /**
     * 客席数：１F喫煙席数(店内)を取得します。
     * @return 客席数：１F喫煙席数(店内)
     */
    public String getSeat1FSmokingIn() {
        return convStringDec(seat1FSmokingIn);
    }
    /**
     * 客席数：１F喫煙席数(店内)を設定します。
     * @param seat1FSmokingIn 客席数：１F喫煙席数(店内)
     */
    public void setSeat1FSmokingIn(String seat1FSmokingIn) {
        this.seat1FSmokingIn = seat1FSmokingIn;
    }
    
    /**
     * 客席数：１F禁煙席数(店内)を取得します。
     * @return 客席数：１F禁煙席数(店内)
     */
    public String getSeat1FNonSmokingIn() {
        return convStringDec(seat1FNonSmokingIn);
    }
    /**
     * 客席数：１F禁煙席数(店内)を設定します。
     * @param seat1FNonSmokingIn 客席数：１F禁煙席数(店内)
     */
    public void setSeat1FNonSmokingIn(String seat1FNonSmokingIn) {
        this.seat1FNonSmokingIn = seat1FNonSmokingIn;
    }
    
    /**
     * 客席数：１F喫煙席数(店外)を取得します。
     * @return 客席数：１F喫煙席数(店外)
     */
    public String getSeat1FSmokingOut() {
        return convStringDec(seat1FSmokingOut);
    }
    /**
     * 客席数：１F喫煙席数(店外)を設定します。
     * @param seat1FSmokingOut 客席数：１F喫煙席数(店外)
     */
    public void setSeat1FSmokingOut(String seat1FSmokingOut) {
        this.seat1FSmokingOut = seat1FSmokingOut;
    }
    
    /**
     * 客席数：１F禁煙席数(店外)を取得します。
     * @return 客席数：１F禁煙席数(店外)
     */
    public String getSeat1FNonSmokingOut() {
        return convStringDec(seat1FNonSmokingOut);
    }
    /**
     * 客席数：１F禁煙席数(店外)を設定します。
     * @param seat1FNonSmokingOut 客席数：１F禁煙席数(店外)
     */
    public void setSeat1FNonSmokingOut(String seat1FNonSmokingOut) {
        this.seat1FNonSmokingOut = seat1FNonSmokingOut;
    }
    
    /**
     * ★☆客席数：２Fを取得します。
     * @return 客席数：２F
     */
    public String getSeat2F() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seat2F = "0";
        
        if (codeVerifierPark.validate(getSeat2FSmokingIn())
            && codeVerifierPark.validate(getSeat2FSmokingOut())
            && codeVerifierPark.validate(getSeat2FNonSmokingIn())
            && codeVerifierPark.validate(getSeat2FNonSmokingOut())){

            seat2F = 
                String.valueOf(
                    new BigDecimal(getSeat2FSmokingIn())
                    .add(new BigDecimal(getSeat2FSmokingOut()))
                    .add(new BigDecimal(getSeat2FNonSmokingIn()))
                    .add(new BigDecimal(getSeat2FNonSmokingOut())));
        }
    
        return seat2F;
    }
    /**
     * 客席数：２Fを設定します。
     * @param seat2F 客席数：２F
     */
    public void setSeat2F(String seat2F) {
        this.seat2F = seat2F;
    }
    
    /**
     * 客席数：２F喫煙席数(店内)を取得します。
     * @return 客席数：２F喫煙席数(店内)
     */
    public String getSeat2FSmokingIn() {
        return convStringDec(seat2FSmokingIn);
    }
    /**
     * 客席数：２F喫煙席数(店内)を設定します。
     * @param seat2FSmokingIn 客席数：２F喫煙席数(店内)
     */
    public void setSeat2FSmokingIn(String seat2FSmokingIn) {
        this.seat2FSmokingIn = seat2FSmokingIn;
    }
    
    /**
     * 客席数：２F禁煙席数(店内)を取得します。
     * @return 客席数：２F禁煙席数(店内)
     */
    public String getSeat2FNonSmokingIn() {
        return convStringDec(seat2FNonSmokingIn);
    }
    /**
     * 客席数：２F禁煙席数(店内)を設定します。
     * @param seat2FNonSmokingIn 客席数：２F禁煙席数(店内)
     */
    public void setSeat2FNonSmokingIn(String seat2FNonSmokingIn) {
        this.seat2FNonSmokingIn = seat2FNonSmokingIn;
    }
    
    /**
     * 客席数：２F喫煙席数(店外)を取得します。
     * @return 客席数：２F喫煙席数(店外)
     */
    public String getSeat2FSmokingOut() {
        return convStringDec(seat2FSmokingOut);
    }
    /**
     * 客席数：２F喫煙席数(店外)を設定します。
     * @param seat2FSmokingOut 客席数：２F喫煙席数(店外)
     */
    public void setSeat2FSmokingOut(String seat2FSmokingOut) {
        this.seat2FSmokingOut = seat2FSmokingOut;
    }
    
    /**
     * 客席数：２F禁煙席数(店外)を取得します。
     * @return 客席数：２F禁煙席数(店外)
     */
    public String getSeat2FNonSmokingOut() {
        return convStringDec(seat2FNonSmokingOut);
    }
    /**
     * 客席数：２F禁煙席数(店外)を設定します。
     * @param seat2FNonSmokingOut 客席数：２F禁煙席数(店外)
     */
    public void setSeat2FNonSmokingOut(String seat2FNonSmokingOut) {
        this.seat2FNonSmokingOut = seat2FNonSmokingOut;
    }
    
    /**
     * ★☆客席数：３Fを取得します。
     * @return 客席数：３F
     */
    public String getSeat3F() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seat3F = "0";
        
        if (codeVerifierPark.validate(getSeat3FSmokingIn())
            && codeVerifierPark.validate(getSeat3FSmokingOut())
            && codeVerifierPark.validate(getSeat3FNonSmokingIn())
            && codeVerifierPark.validate(getSeat3FNonSmokingOut())){

            seat3F = 
                String.valueOf(
                    new BigDecimal(getSeat3FSmokingIn())
                    .add(new BigDecimal(getSeat3FSmokingOut()))
                    .add(new BigDecimal(getSeat3FNonSmokingIn()))
                    .add(new BigDecimal(getSeat3FNonSmokingOut())));
        }
    
        return seat3F;
    }
    /**
     * 客席数：３Fを設定します。
     * @param seat3F 客席数：３F
     */
    public void setSeat3F(String seat3F) {
        this.seat3F = convStringDec(seat3F);
    }
    
    /**
     * 客席数：３F喫煙席数(店内)を取得します。
     * @return 客席数：３F喫煙席数(店内)
     */
    public String getSeat3FSmokingIn() {
        return convStringDec(seat3FSmokingIn);
    }
    /**
     * 客席数：３F喫煙席数(店内)を設定します。
     * @param seat3FSmokingIn 客席数：３F喫煙席数(店内)
     */
    public void setSeat3FSmokingIn(String seat3FSmokingIn) {
        this.seat3FSmokingIn = seat3FSmokingIn;
    }
    
    /**
     * 客席数：３F禁煙席数(店内)を取得します。
     * @return 客席数：３F禁煙席数(店内)
     */
    public String getSeat3FNonSmokingIn() {
        return convStringDec(seat3FNonSmokingIn);
    }
    /**
     * 客席数：３F禁煙席数(店内)を設定します。
     * @param seat3FNonSmokingIn 客席数：３F禁煙席数(店内)
     */
    public void setSeat3FNonSmokingIn(String seat3FNonSmokingIn) {
        this.seat3FNonSmokingIn = seat3FNonSmokingIn;
    }
    
    /**
     * 客席数：３F喫煙席数(店外)を取得します。
     * @return 客席数：３F喫煙席数(店外)
     */
    public String getSeat3FSmokingOut() {
        return convStringDec(seat3FSmokingOut);
    }
    /**
     * 客席数：３F喫煙席数(店外)を設定します。
     * @param seat3FSmokingOut 客席数：３F喫煙席数(店外)
     */
    public void setSeat3FSmokingOut(String seat3FSmokingOut) {
        this.seat3FSmokingOut = seat3FSmokingOut;
    }
    
    /**
     * 客席数：３F禁煙席数(店外)を取得します。
     * @return 客席数：３F禁煙席数(店外)
     */
    public String getSeat3FNonSmokingOut() {
        return convStringDec(seat3FNonSmokingOut);
    }
    /**
     * 客席数：３F禁煙席数(店外)を設定します。
     * @param seat3FNonSmokingOut 客席数：３F禁煙席数(店外)
     */
    public void setSeat3FNonSmokingOut(String seat3FNonSmokingOut) {
        this.seat3FNonSmokingOut = seat3FNonSmokingOut;
    }
    
    /**
     * ★☆客席数：地下を取得します。
     * @return 客席数：地下
     */
    public String getSeatUnder() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatUnder = "0";
        
        if (codeVerifierPark.validate(getSeatUnderSmokingIn())
            && codeVerifierPark.validate(getSeatUnderSmokingOut())
            && codeVerifierPark.validate(getSeatUnderNonSmokingIn())
            && codeVerifierPark.validate(getSeatUnderNonSmokingOut())){

            seatUnder = 
                String.valueOf(
                    new BigDecimal(getSeatUnderSmokingIn())
                    .add(new BigDecimal(getSeatUnderSmokingOut()))
                    .add(new BigDecimal(getSeatUnderNonSmokingIn()))
                    .add(new BigDecimal(getSeatUnderNonSmokingOut())));
         }
   
        return seatUnder;
    }
    /**
     * 客席数：地下を設定します。
     * @param seatUnder 客席数：地下
     */
    public void setSeatUnder(String seatUnder) {
        this.seatUnder = seatUnder;
    }
    
    /**
     * 客席数：地下F喫煙席数(店内)を取得します。
     * @return 客席数：地下F喫煙席数(店内)
     */
    public String getSeatUnderSmokingIn() {
        return convStringDec(seatUnderSmokingIn);
    }
    /**
     * 客席数：地下F喫煙席数(店内)を設定します。
     * @param seatUnderSmokingIn 客席数：地下F喫煙席数(店内)
     */
    public void setSeatUnderSmokingIn(String seatUnderSmokingIn) {
        this.seatUnderSmokingIn = seatUnderSmokingIn;
    }
    
    /**
     * 客席数：地下禁煙席数(店内)を取得します。
     * @return 客席数：地下禁煙席数(店内)
     */
    public String getSeatUnderNonSmokingIn() {
        return convStringDec(seatUnderNonSmokingIn);
    }
    /**
     * 客席数：地下禁煙席数(店内)を設定します。
     * @param seatUnderNonSmokingIn 客席数：地下禁煙席数(店内)
     */
    public void setSeatUnderNonSmokingIn(String seatUnderNonSmokingIn) {
        this.seatUnderNonSmokingIn = seatUnderNonSmokingIn;
    }
    
    /**
     * 客席数：地下F喫煙席数(店外)を取得します。
     * @return 客席数：地下F喫煙席数(店外)
     */
    public String getSeatUnderSmokingOut() {
        return convStringDec(seatUnderSmokingOut);
    }
    /**
     * 客席数：地下F喫煙席数(店外)を設定します。
     * @param seatUnderSmokingOut 客席数：地下F喫煙席数(店外)
     */
    public void setSeatUnderSmokingOut(String seatUnderSmokingOut) {
        this.seatUnderSmokingOut = seatUnderSmokingOut;
    }
    
    /**
     * 客席数：地下禁煙席数(店外)を取得します。
     * @return 客席数：地下禁煙席数(店外)
     */
    public String getSeatUnderNonSmokingOut() {
        return convStringDec(seatUnderNonSmokingOut);
    }
    /**
     * 客席数：地下禁煙席数(店外)を設定します。
     * @param seatUnderNonSmokingOut 客席数：地下禁煙席数(店外)
     */
    public void setSeatUnderNonSmokingOut(String seatUnderNonSmokingOut) {
        this.seatUnderNonSmokingOut = seatUnderNonSmokingOut;
    }
    
    /**
     * ★☆客席数：共用を取得します。
     * @return 客席数：共用
     */
    public String getSeatCommon() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatCommon = "0";
        
        if(codeVerifierPark.validate(getSeatCommonSmokingIn())
            && codeVerifierPark.validate(getSeatCommonSmokingOut())
            && codeVerifierPark.validate(getSeatCommonNonSmokingIn())
            && codeVerifierPark.validate(getSeatCommonNonSmokingOut())){

            seatCommon = 
                String.valueOf(
                    new BigDecimal(getSeatCommonSmokingIn())
                    .add(new BigDecimal(getSeatCommonSmokingOut()))
                    .add(new BigDecimal(getSeatCommonNonSmokingIn()))
                    .add(new BigDecimal(getSeatCommonNonSmokingOut())));
        }
    
        return seatCommon;
    }
    /**
     * 客席数：共用を設定します。
     * @param seatCommon 客席数：共用
     */
    public void setSeatCommon(String seatCommon) {
        this.seatCommon = seatCommon;
    }
    
    /**
     * 客席数：共用F喫煙席数(店内)を取得します。
     * @return 客席数：共用F喫煙席数(店内)
     */
    public String getSeatCommonSmokingIn() {
        return convStringDec(seatCommonSmokingIn);
    }
    /**
     * 客席数：共用F喫煙席数(店内)を設定します。
     * @param seatCommonSmokingIn 客席数：共用F喫煙席数(店内)
     */
    public void setSeatCommonSmokingIn(String seatCommonSmokingIn) {
        this.seatCommonSmokingIn = seatCommonSmokingIn;
    }
    
    /**
     * 客席数：共用F禁煙席数(店内)を取得します。
     * @return 客席数：共用F禁煙席数(店内)
     */
    public String getSeatCommonNonSmokingIn() {
        return convStringDec(seatCommonNonSmokingIn);
    }
    /**
     * 客席数：共用F禁煙席数(店内)を設定します。
     * @param seatCommonNonSmokingIn 客席数：共用F禁煙席数(店内)
     */
    public void setSeatCommonNonSmokingIn(String seatCommonNonSmokingIn) {
        this.seatCommonNonSmokingIn = seatCommonNonSmokingIn;
    }
    
    /**
     * 客席数：共用F喫煙席数(店外)を取得します。
     * @return 客席数：共用F喫煙席数(店外)
     */
    public String getSeatCommonSmokingOut() {
        return convStringDec(seatCommonSmokingOut);
    }
    /**
     * 客席数：共用F喫煙席数(店外)を設定します。
     * @param seatCommonSmokingOut 客席数：共用F喫煙席数(店外)
     */
    public void setSeatCommonSmokingOut(String seatCommonSmokingOut) {
        this.seatCommonSmokingOut = seatCommonSmokingOut;
    }
    
    /**
     * 客席数：共用F禁煙席数(店外)を取得します。
     * @return 客席数：共用F禁煙席数(店外)
     */
    public String getSeatCommonNonSmokingOut() {
        return convStringDec(seatCommonNonSmokingOut);
    }
    /**
     * 客席数：共用F禁煙席数(店外)を設定します。
     * @param seatCommonNonSmokingOut 客席数：共用F禁煙席数(店外)
     */
    public void setSeatCommonNonSmokingOut(String seatCommonNonSmokingOut) {
        this.seatCommonNonSmokingOut = seatCommonNonSmokingOut;
    }
    
    /**
     * ★☆客席数：その他を取得します。
     * @return 客席数：その他
     */
    public String getSeatOther() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatOther = "0";
        
        if(codeVerifierPark.validate(getSeatOtherSmokingIn())
            && codeVerifierPark.validate(getSeatOtherNonSmokingIn())
            && codeVerifierPark.validate(getSeatOtherSmokingOut())
            && codeVerifierPark.validate(getSeatOtherNonSmokingOut())){

            seatOther = 
                String.valueOf(
                    new BigDecimal(getSeatOtherSmokingIn())
                    .add(new BigDecimal(getSeatOtherNonSmokingIn()))
                    .add(new BigDecimal(getSeatOtherSmokingOut()))
                    .add(new BigDecimal(getSeatOtherNonSmokingOut())));
        }
       
        return seatOther;
    }
    /**
     * 客席数：その他を設定します。
     * @param seatOther 客席数：その他
     */
    public void setSeatOther(String seatOther) {
        this.seatOther = seatOther;
    }
    
    /**
     * 客席数：その他喫煙席数(店内)を取得します。
     * @return 客席数：その他喫煙席数(店内)
     */
    public String getSeatOtherSmokingIn() {
        return convStringDec(seatOtherSmokingIn);
    }
    /**
     * 客席数：その他喫煙席数(店内)を設定します。
     * @param seatOtherSmokingIn 客席数：その他喫煙席数(店内)
     */
    public void setSeatOtherSmokingIn(String seatOtherSmokingIn) {
        this.seatOtherSmokingIn = seatOtherSmokingIn;
    }
    
    /**
     * 客席数：その他禁煙席数(店内)を取得します。
     * @return 客席数：その他禁煙席数(店内)
     */
    public String getSeatOtherNonSmokingIn() {
        return convStringDec(seatOtherNonSmokingIn);
    }
    /**
     * 客席数：その他禁煙席数(店内)を設定します。
     * @param seatOtherNonSmokingIn 客席数：その他禁煙席数(店内)
     */
    public void setSeatOtherNonSmokingIn(String seatOtherNonSmokingIn) {
        this.seatOtherNonSmokingIn = seatOtherNonSmokingIn;
    }
    
    /**
     * 客席数：その他喫煙席数(店外)を取得します。
     * @return 客席数：その他喫煙席数(店外)
     */
    public String getSeatOtherSmokingOut() {
        return convStringDec(seatOtherSmokingOut);
    }
    /**
     * 客席数：その他喫煙席数(店外)を設定します。
     * @param seatOtherSmokingOut 客席数：その他喫煙席数(店外)
     */
    public void setSeatOtherSmokingOut(String seatOtherSmokingOut) {
        this.seatOtherSmokingOut = seatOtherSmokingOut;
    }
    
    /**
     * 客席数：その他禁煙席数(店外)を取得します。
     * @return 客席数：その他禁煙席数(店外)
     */
    public String getSeatOtherNonSmokingOut() {
        return convStringDec(seatOtherNonSmokingOut);
    }
    /**
     * 客席数：その他禁煙席数(店外)を設定します。
     * @param seatOtherNonSmokingOut 客席数：その他禁煙席数(店外)
     */
    public void setSeatOtherNonSmokingOut(String seatOtherNonSmokingOut) {
        this.seatOtherNonSmokingOut = seatOtherNonSmokingOut;
    }
    
    /**
     * 1F喫煙席数計を取得します。
     * @return 1F喫煙席数計
     */
    public String getSeatSmokingKei1() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKei1 = "0";
        
        if (codeVerifierPark.validate(getSeat1FSmokingIn()) 
            && codeVerifierPark.validate(getSeat1FSmokingOut())){
            
            seatSmokingKei1 = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingIn())
                    .add(new BigDecimal(getSeat1FSmokingOut())));
        }   

        return seatSmokingKei1;
    }
    /**
     * 1F喫煙席数計を設定します。
     * @param seatSmokingKei1 1F喫煙席数計
     */
    public void setSeatSmokingKei1(String seatSmokingKei1) {
        this.seatSmokingKei1 = seatSmokingKei1;
    }
    
    /**
     * 1F禁煙席数計を取得します。
     * @return 1F禁煙席数計
     */
    public String getSeatNonSmokingKei1() {
        
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKei1 = "0";
        
        if (codeVerifierPark.validate(getSeat1FNonSmokingIn()) 
            && codeVerifierPark.validate(getSeat1FNonSmokingIn())){
            
            seatNonSmokingKei1 = 
                String.valueOf(
                    new BigDecimal(getSeat1FNonSmokingIn())
                    .add(new BigDecimal(getSeat1FNonSmokingIn())));
        }   

        return seatNonSmokingKei1;
    }
    /**
     * 1F禁煙席数計を設定します。
     * @param seatNonSmokingKei1 1F禁煙席数計
     */
    public void setSeatNonSmokingKei1(String seatNonSmokingKei1) {
        this.seatNonSmokingKei1 = seatNonSmokingKei1;
    }
    
    /**
     * 2F喫煙席数計を取得します。
     * @return 2F喫煙席数計
     */
    public String getSeatSmokingKei2() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKei2 = "0";
        
        if (codeVerifierPark.validate(getSeat2FSmokingIn()) 
            && codeVerifierPark.validate(getSeat2FSmokingOut())){
            
            seatSmokingKei2 = 
                String.valueOf(
                    new BigDecimal(getSeat2FSmokingIn())
                    .add(new BigDecimal(getSeat2FSmokingOut())));
        }   

        return seatSmokingKei2;

    }
    /**
     * 2F喫煙席数計を設定します。
     * @param seatSmokingKei2 2F喫煙席数計
     */
    public void setSeatSmokingKei2(String seatSmokingKei2) {
        this.seatSmokingKei2 = seatSmokingKei2;
    }
    
    /**
     * 2F禁煙席数計を取得します。
     * @return 2F禁煙席数計
     */
    public String getSeatNonSmokingKei2() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKei2 = "0";
        
        if (codeVerifierPark.validate(getSeat2FNonSmokingIn()) 
            && codeVerifierPark.validate(getSeat2FNonSmokingOut())){
            
            seatNonSmokingKei2 = 
                String.valueOf(
                    new BigDecimal(getSeat2FNonSmokingIn())
                    .add(new BigDecimal(getSeat2FNonSmokingOut())));
        }   

        return seatNonSmokingKei2;
    }
    /**
     * 2F禁煙席数計を設定します。
     * @param seatNonSmokingKei2 2F禁煙席数計
     */
    public void setSeatNonSmokingKei2(String seatNonSmokingKei2) {
        this.seatNonSmokingKei2 = seatNonSmokingKei2;
    }
    
    /**
     * 3F喫煙席数計を取得します。
     * @return 3F喫煙席数計
     */
    public String getSeatSmokingKei3() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKei3 = "0";
        
        if (codeVerifierPark.validate(getSeat3FSmokingIn()) 
            && codeVerifierPark.validate(getSeat3FSmokingOut())){
            
            seatSmokingKei3 = 
                String.valueOf(
                    new BigDecimal(getSeat3FSmokingIn())
                    .add(new BigDecimal(getSeat3FSmokingOut())));
        }   

        return seatSmokingKei3;
    }
    /**
     * 3F喫煙席数計を設定します。
     * @param seatSmokingKei3 3F喫煙席数計
     */
    public void setSeatSmokingKei3(String seatSmokingKei3) {
        this.seatSmokingKei3 = seatSmokingKei3;
    }
    
    /**
     * 3F禁煙席数計を取得します。
     * @return 3F禁煙席数計
     */
    public String getSeatNonSmokingKei3() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKei3 = "0";
        
        if (codeVerifierPark.validate(getSeat3FNonSmokingIn()) 
            && codeVerifierPark.validate(getSeat3FNonSmokingOut())){
            
            seatNonSmokingKei3 = 
                String.valueOf(
                    new BigDecimal(getSeat3FNonSmokingIn())
                    .add(new BigDecimal(getSeat3FNonSmokingOut())));
        }   

        return seatNonSmokingKei3;
    }
    /**
     * 3F禁煙席数計を設定します。
     * @param seatNonSmokingKei3 3F禁煙席数計
     */
    public void setSeatNonSmokingKei3(String seatNonSmokingKei3) {
        this.seatNonSmokingKei3 = seatNonSmokingKei3;
    }
    
    /**
     * 地下喫煙席数計を取得します。
     * @return 地下喫煙席数計
     */
    public String getSeatSmokingKeiU() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKeiU = "0";
        
        if (codeVerifierPark.validate(getSeatUnderSmokingIn()) 
            && codeVerifierPark.validate(getSeatUnderSmokingOut())){
            
            seatSmokingKeiU = 
                String.valueOf(
                    new BigDecimal(getSeatUnderSmokingIn())
                    .add(new BigDecimal(getSeatUnderSmokingOut())));
        }   

        return seatSmokingKeiU;
    }
    /**
     * 地下喫煙席数計を設定します。
     * @param seatSmokingKeiU 地下喫煙席数計
     */
    public void setSeatSmokingKeiU(String seatSmokingKeiU) {
        this.seatSmokingKeiU = seatSmokingKeiU;
    }
    
    /**
     * 地下禁煙席数計を取得します。
     * @return 地下禁煙席数計
     */
    public String getSeatNonSmokingKeiU() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKeiU = "0";
        
        if (codeVerifierPark.validate(getSeatUnderNonSmokingIn()) 
            && codeVerifierPark.validate(getSeatUnderNonSmokingOut())){
            
            seatNonSmokingKeiU = 
                String.valueOf(
                    new BigDecimal(getSeatUnderNonSmokingIn())
                    .add(new BigDecimal(getSeatUnderNonSmokingOut())));
        }   

        return seatNonSmokingKeiU;
    }
    /**
     * 地下禁煙席数計を設定します。
     * @param seatNonSmokingKeiU 地下禁煙席数計
     */
    public void setSeatNonSmokingKeiU(String seatNonSmokingKeiU) {
        this.seatNonSmokingKeiU = seatNonSmokingKeiU;
    }
    
    /**
     * 共用喫煙席数計を取得します。
     * @return 共用喫煙席数計
     */
    public String getSeatSmokingKeiC() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKeiC = "0";
        
        if (codeVerifierPark.validate(getSeatCommonSmokingIn()) 
            && codeVerifierPark.validate(getSeatCommonSmokingOut())){
            
            seatSmokingKeiC = 
                String.valueOf(
                    new BigDecimal(getSeatCommonSmokingIn())
                    .add(new BigDecimal(getSeatCommonSmokingOut())));
        }   

        return seatSmokingKeiC;
    }
    /**
     * 共用喫煙席数計を設定します。
     * @param seatSmokingKeiC 共用喫煙席数計
     */
    public void setSeatSmokingKeiC(String seatSmokingKeiC) {
        this.seatSmokingKeiC = seatSmokingKeiC;
    }
    
    /**
     * 共用禁煙席数計を取得します。
     * @return 共用禁煙席数計
     */
    public String getSeatNonSmokingKeiC() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKeiC = "0";
        
        if (codeVerifierPark.validate(getSeatCommonNonSmokingIn()) 
            && codeVerifierPark.validate(getSeatCommonNonSmokingOut())){
            
            seatNonSmokingKeiC = 
                String.valueOf(
                    new BigDecimal(getSeatCommonNonSmokingIn())
                    .add(new BigDecimal(getSeatCommonNonSmokingOut())));
        }   

        return seatNonSmokingKeiC;
    }
    /**
     * 共用禁煙席数計を設定します。
     * @param seatNonSmokingKeiC 共用禁煙席数計
     */
    public void setSeatNonSmokingKeiC(String seatNonSmokingKeiC) {
        this.seatNonSmokingKeiC = seatNonSmokingKeiC;
    }
    
    /**
     * その他喫煙席数計を取得します。
     * @return その他喫煙席数計
     */
    public String getSeatSmokingKeiO() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatSmokingKeiO = "0";
        
        if (codeVerifierPark.validate(getSeatOtherSmokingIn()) 
            && codeVerifierPark.validate(getSeatOtherSmokingOut())){
            
            seatSmokingKeiO = 
                String.valueOf(
                    new BigDecimal(getSeatOtherSmokingIn())
                    .add(new BigDecimal(getSeatOtherSmokingOut())));
        }   

        return seatSmokingKeiO;
    }
    /**
     * その他喫煙席数計を設定します。
     * @param seatSmokingKeiO その他喫煙席数計
     */
    public void setSeatSmokingKeiO(String seatSmokingKeiO) {
        this.seatSmokingKeiO = seatSmokingKeiO;
    }
    
    /**
     * その他禁煙席数計を取得します。
     * @return その他禁煙席数計
     */
    public String getSeatNonSmokingKeiO() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        seatNonSmokingKeiO = "0";
        
        if (codeVerifierPark.validate(getSeatOtherNonSmokingIn()) 
            && codeVerifierPark.validate(getSeatOtherNonSmokingOut())){
            
            seatNonSmokingKeiO = 
                String.valueOf(
                    new BigDecimal(getSeatOtherNonSmokingIn())
                    .add(new BigDecimal(getSeatOtherNonSmokingOut())));
        }   

        return seatNonSmokingKeiO;
    }
    /**
     * その他禁煙席数計を設定します。
     * @param seatNonSmokingKeiO その他禁煙席数計
     */
    public void setSeatNonSmokingKeiO(String seatNonSmokingKeiO) {
        this.seatNonSmokingKeiO = seatNonSmokingKeiO;
    }
    
    /**
     * 総席数喫煙店内を取得します。
     * @return 総席数喫煙店内
     */
    public String getTotalSmokingIn() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        totalSmokingIn = "0";
        
        if(codeVerifierPark.validate(getSeat1FSmokingIn())
            && codeVerifierPark.validate(getSeat2FSmokingIn())
            && codeVerifierPark.validate(getSeat3FSmokingIn())
            && codeVerifierPark.validate(getSeatUnderSmokingIn())
            && codeVerifierPark.validate(getSeatCommonSmokingIn())
            && codeVerifierPark.validate(getSeatOtherSmokingIn())){

            totalSmokingIn = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingIn())
                    .add(new BigDecimal(getSeat2FSmokingIn()))
                    .add(new BigDecimal(getSeat3FSmokingIn()))
                    .add(new BigDecimal(getSeatUnderSmokingIn()))
                    .add(new BigDecimal(getSeatCommonSmokingIn()))
                    .add(new BigDecimal(getSeatOtherSmokingIn())));
         }
        
        return totalSmokingIn;
    }
    /**
     * 総席数喫煙店内を設定します。
     * @param totalSmokingIn 総席数喫煙店内
     */
    public void setTotalSmokingIn(String totalSmokingIn) {
        this.totalSmokingIn = totalSmokingIn;
    }
    
    /**
     * 総席数喫煙店外を取得します。
     * @return 総席数喫煙店外
     */
    public String getTotalSmokingOut() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        totalSmokingOut = "0";
        
        if(codeVerifierPark.validate(getSeat1FSmokingOut())
            && codeVerifierPark.validate(getSeat2FSmokingOut())
            && codeVerifierPark.validate(getSeat3FSmokingOut())
            && codeVerifierPark.validate(getSeatUnderSmokingOut())
            && codeVerifierPark.validate(getSeatCommonSmokingOut())
            && codeVerifierPark.validate(getSeatOtherSmokingOut())){

            totalSmokingOut = 
                String.valueOf(
                    new BigDecimal(getSeat1FSmokingOut())
                    .add(new BigDecimal(getSeat2FSmokingOut()))
                    .add(new BigDecimal(getSeat3FSmokingOut()))
                    .add(new BigDecimal(getSeatUnderSmokingOut()))
                    .add(new BigDecimal(getSeatCommonSmokingOut()))
                    .add(new BigDecimal(getSeatOtherSmokingOut())));
        }
        
        return totalSmokingOut;
    }
    /**
     * 総席数喫煙店外を設定します。
     * @param totalSmokingOut 総席数喫煙店外
     */
    public void setTotalSmokingOut(String totalSmokingOut) {
        this.totalSmokingOut = totalSmokingOut;
    }
    
    /**
     * 総席数禁煙店内を取得します。
     * @return 総席数禁煙店内
     */
    public String getTotalNonSmokingIn() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        totalNonSmokingIn = "0";
        
        if(codeVerifierPark.validate(getSeat1FNonSmokingIn())
            && codeVerifierPark.validate(getSeat2FNonSmokingIn())
            && codeVerifierPark.validate(getSeat3FNonSmokingIn())
            && codeVerifierPark.validate(getSeatUnderNonSmokingIn())
            && codeVerifierPark.validate(getSeatCommonNonSmokingIn())
            && codeVerifierPark.validate(getSeatOtherNonSmokingIn())){

            totalNonSmokingIn = 
                String.valueOf(
                    new BigDecimal(getSeat1FNonSmokingIn())
                    .add(new BigDecimal(getSeat2FNonSmokingIn()))
                    .add(new BigDecimal(getSeat3FNonSmokingIn()))
                    .add(new BigDecimal(getSeatUnderNonSmokingIn()))
                    .add(new BigDecimal(getSeatCommonNonSmokingIn()))
                    .add(new BigDecimal(getSeatOtherNonSmokingIn())));
         }
        
        return totalNonSmokingIn;
    }
    /**
     * 総席数禁煙店内を設定します。
     * @param totalNonSmokingIn 総席数禁煙店内
     */
    public void setTotalNonSmokingIn(String totalNonSmokingIn) {
        this.totalNonSmokingIn = totalNonSmokingIn;
    }
    
    /**
     * 総席数禁煙店外を取得します。
     * @return 総席数禁煙店外
     */
    public String getTotalNonSmokingOut() {
        CodeVerifier codeVerifierPark = new CodeVerifier(5, false);
        
        totalNonSmokingOut = "0";
        
        if(codeVerifierPark.validate(getSeat1FNonSmokingOut())
            && codeVerifierPark.validate(getSeat2FNonSmokingOut())
            && codeVerifierPark.validate(getSeat3FNonSmokingOut())
            && codeVerifierPark.validate(getSeatUnderNonSmokingOut())
            && codeVerifierPark.validate(getSeatCommonNonSmokingOut())
            && codeVerifierPark.validate(getSeatOtherNonSmokingOut())){

            totalNonSmokingOut = 
                String.valueOf(
                    new BigDecimal(getSeat1FNonSmokingOut())
                    .add(new BigDecimal(getSeat2FNonSmokingOut()))
                    .add(new BigDecimal(getSeat3FNonSmokingOut()))
                    .add(new BigDecimal(getSeatUnderNonSmokingOut()))
                    .add(new BigDecimal(getSeatCommonNonSmokingOut()))
                    .add(new BigDecimal(getSeatOtherNonSmokingOut())));
        }
   
        return totalNonSmokingOut;
    }
    /**
     * 総席数禁煙店外を設定します。
     * @param totalNonSmokingOut 総席数禁煙店外
     */
    public void setTotalNonSmokingOut(String totalNonSmokingOut) {
        this.totalNonSmokingOut = totalNonSmokingOut;
    }
    
    /**
     * 事業計画：投資額を取得します。
     * @return 事業計画：投資額
     */
    public String getPrjInvest() {
        return convString(prjInvest);
    }
    /**
     * 事業計画：投資額を設定します。
     * @param prjInvest 事業計画：投資額
     */
    public void setPrjInvest(String prjInvest) {
        this.prjInvest = prjInvest;
    }
    
    /**
     * 事業計画：売上を取得します。
     * @return 事業計画：売上
     */
    public String getPrjUriage() {
        return convString(prjUriage);
    }
    /**
     * 事業計画：売上を設定します。
     * @param prjUriage 事業計画：売上
     */
    public void setPrjUriage(String prjUriage) {
        this.prjUriage = prjUriage;
    }
    
    /**
     * 設計業者名を取得します。
     * @return 設計業者名
     */
    public String getToriSekkei() {
        return convString(toriSekkei);
    }
    /**
     * 設計業者名を設定します。
     * @param toriSekkei 設計業者名
     */
    public void setToriSekkei(String toriSekkei) {
        this.toriSekkei = toriSekkei;
    }
    
    /**
     * 施工業者名を取得します。
     * @return 施工業者名
     */
    public String getToriSekou() {
        return convString(toriSekou);
    }
    /**
     * 施工業者名を設定します。
     * @param toriSekou 施工業者名
     */
    public void setToriSekou(String toriSekou) {
        this.toriSekou = toriSekou;
    }
    
    /**
     * 物件契約：契約日を取得します。
     * @return 物件契約：契約日
     */
    public String getKeiyakuFirst() {
        return convString(keiyakuFirst);
    }
    /**
     * 物件契約：契約日を設定します。
     * @param keiyakuFirst 物件契約：契約日
     */
    public void setKeiyakuFirst(String keiyakuFirst) {
        this.keiyakuFirst = keiyakuFirst;
    }
    
    /**
     * 物件契約：備考を取得します。
     * @return 物件契約：備考
     */
    public String getKeiyakuNotes() {
        return convString(keiyakuNotes);
    }
    /**
     * 物件契約：備考を設定します。
     * @param keiyakuNotes 物件契約：備考
     */
    public void setKeiyakuNotes(String keiyakuNotes) {
        this.keiyakuNotes = keiyakuNotes;
    }
    
    /**
     * 転貸を取得します。
     * @return 転貸
     */
    public String getTentai() {
        return convString(tentai);
    }
    /**
     * 転貸を設定します。
     * @param tentai 転貸
     */
    public void setTentai(String tentai) {
        this.tentai = tentai;
    }
    
    /**
     * 転貸 表示用 （１：あり　０：なし）
     * @return
     */
    public String getTentaiView() {
        if ("1".equals(getTentai())) {
            return "あり";
        }
        else if ("0".equals(getTentai())) {
            return "なし";
        }
        else {
            return "";
        }
    }
    
    /**
     * 転貸開始日を取得します。
     * @return 転貸開始日
     */
    public String getTentaiStartDt() {
        return convString(tentaiStartDt);
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
        return convString(tentaiEndDt);
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
        return convString(tentaiInfo);
    }
    /**
     * 転貸情報を設定します。
     * @param tentaiInfo 転貸情報
     */
    public void setTentaiInfo(String tentaiInfo) {
        this.tentaiInfo = tentaiInfo;
    }
    
    /**
     * 最寄り駅：線を取得します。
     * @return 最寄り駅：線
     */
    public String getAccessLine() {
        return convString(accessLine);
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
        return convString(accessStation);
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
     * 時間を取得します。
     * @return 時間
     */
    public String getAccessTime() {
        return accessTime;
    }
    /**
     * 時間を設定します。
     * @param accessTime 時間
     */
    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }
    
    /**
     * 交通手段を取得します。
     * @return 交通手段
     */
    public String getAccessWay() {
        return convString(accessWay);
    }
    /**
     * 交通手段を設定します。
     * @param accessWay 交通手段
     */
    public void setAccessWay(String accessWay) {
        this.accessWay = accessWay;
    }
    
    /**
     * 交通手段名を取得します。
     * @return 交通手段名
     */
    public String getAccessWayName() {
        return convString(accessWayName);
    }
    /**
     * 交通手段名を設定します。
     * @param accessWayName 交通手段名
     */
    public void setAccessWayName(String accessWayName) {
        this.accessWayName = accessWayName;
    }
    
    /**
     * 交通手段備考を取得します。
     * @return 交通手段備考
     */
    public String getAccessWayNote() {
        return convString(accessWayNote);
    }
    /**
     * 交通手段備考を設定します。
     * @param accessWayNote 交通手段備考
     */
    public void setAccessWayNote(String accessWayNote) {
        this.accessWayNote = accessWayNote;
    }
    
    /**
     * 引継元店コードを取得します。
     * @return 引継元店コード
     */
    public String getMiseCdMoto() {
        return convString(miseCdMoto);
    }
    /**
     * 引継元店コードを設定します。
     * @param miseCdMoto 引継元店コード
     */
    public void setMiseCdMoto(String miseCdMoto) {
        this.miseCdMoto = miseCdMoto;
    }
    
    /**
     * 引継日（オープン日）を取得します。
     * @return 引継日（オープン日）
     */
    public String getHikitugiDtOpen() {
        return convString(hikitugiDtOpen);
    }
    /**
     * 引継日（オープン日）を設定します。
     * @param hikitugiDtOpen 引継日（オープン日）
     */
    public void setHikitugiDtOpen(String hikitugiDtOpen) {
        this.hikitugiDtOpen = hikitugiDtOpen;
    }
    
    /**
     * FC契約日を取得します。
     * @return FC契約日
     */
    public String getKeiyakuDt() {
        return convString(keiyakuDt);
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
        return convString(kaiyakuDt);
    }
    /**
     * FC解約日を設定します。
     * @param kaiyakuDt FC解約日
     */
    public void setKaiyakuDt(String kaiyakuDt) {
        this.kaiyakuDt = kaiyakuDt;
    }
    
    /**
     * @return seat1fFlg を戻します。
     */
    public String getSeat1fFlg() {
        return convString(seat1fFlg);
    }
    /**
     * @param seat1fFlg seat1fFlg を設定。
     */
    public void setSeat1fFlg(String seat1fFlg) {
        this.seat1fFlg = seat1fFlg;
    }
    /**
     * @return seat2fFlg を戻します。
     */
    public String getSeat2fFlg() {
        return convString(seat2fFlg);
    }
    /**
     * @param seat2fFlg seat2fFlg を設定。
     */
    public void setSeat2fFlg(String seat2fFlg) {
        this.seat2fFlg = seat2fFlg;
    }
    /**
     * @return seat3fFlg を戻します。
     */
    public String getSeat3fFlg() {
        return convString(seat3fFlg);
    }
    /**
     * @param seat3fFlg seat3fFlg を設定。
     */
    public void setSeat3fFlg(String seat3fFlg) {
        this.seat3fFlg = seat3fFlg;
    }
    /**
     * @return seatCommonFlg を戻します。
     */
    public String getSeatCommonFlg() {
        return convString(seatCommonFlg);
    }
    /**
     * @param seatCommonFlg seatCommonFlg を設定。
     */
    public void setSeatCommonFlg(String seatCommonFlg) {
        this.seatCommonFlg = seatCommonFlg;
    }
    /**
     * @return seatOtherFlg を戻します。
     */
    public String getSeatOtherFlg() {
        return convString(seatOtherFlg);
    }
    /**
     * @param seatOtherFlg seatOtherFlg を設定。
     */
    public void setSeatOtherFlg(String seatOtherFlg) {
        this.seatOtherFlg = seatOtherFlg;
    }
    /**
     * @return seatUnderFlg を戻します。
     */
    public String getSeatUnderFlg() {
        return convString(seatUnderFlg);
    }
    /**
     * @param seatUnderFlg seatUnderFlg を設定。
     */
    public void setSeatUnderFlg(String seatUnderFlg) {
        this.seatUnderFlg = seatUnderFlg;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return convString(lastUser);
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return convString(lastPgm);
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
    private String convStringDec(String str) {
        String ret = str;
        if (str == null) {
            ret = "0";
        }
        ret = ret.trim();
        return ret;
    }
    
    
    private BigDecimal convBigDecimal(BigDecimal dec) {
        BigDecimal ret = dec;
        if (dec == null) {
            ret = new BigDecimal("0");
        }
        return ret;
    }
    
    public String getSeat1fView() {
        return "1".equals(getSeat1fFlg()) ? getSeat1F().toString() + "席" : "-";
    }
    public String getSeat1fSmokingInView() {
        return "1".equals(getSeat1fFlg()) ? getSeat1FSmokingIn().toString() + "席" : "-";
    }
    public String getSeat1fNonSmokingInView() {
        return "1".equals(getSeat1fFlg()) ? getSeat1FNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeat1fSmokingOutView() {
        return "1".equals(getSeat1fFlg()) ? getSeat1FSmokingOut().toString() + "席" : "-";
    }
    public String getSeat1fNonSmokingOutView() {
        return "1".equals(getSeat1fFlg()) ? getSeat1FNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKei1View() {
        return "1".equals(getSeat1fFlg()) ? getSeatSmokingKei1().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKei1View() {
        return "1".equals(getSeat1fFlg()) ? getSeatNonSmokingKei1().toString() + "席" : "-";
    }

    public String getSeat2fView() {
        return "1".equals(getSeat2fFlg()) ? getSeat2F().toString() + "席" : "-";
    }
    public String getSeat2fSmokingInView() {
        return "1".equals(getSeat2fFlg()) ? getSeat2FSmokingIn().toString() + "席" : "-";
    }
    public String getSeat2fNonSmokingInView() {
        return "1".equals(getSeat2fFlg()) ? getSeat2FNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeat2fSmokingOutView() {
        return "1".equals(getSeat2fFlg()) ? getSeat2FSmokingOut().toString() + "席" : "-";
    }
    public String getSeat2fNonSmokingOutView() {
        return "1".equals(getSeat2fFlg()) ? getSeat2FNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKei2View() {
        return "1".equals(getSeat2fFlg()) ? getSeatSmokingKei2().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKei2View() {
        return "1".equals(getSeat2fFlg()) ? getSeatNonSmokingKei2().toString() + "席" : "-";
    }

    public String getSeat3fView() {
        return "1".equals(getSeat3fFlg()) ? getSeat3F().toString() + "席" : "-";
    }
    public String getSeat3fSmokingInView() {
        return "1".equals(getSeat3fFlg()) ? getSeat3FSmokingIn().toString() + "席" : "-";
    }
    public String getSeat3fNonSmokingInView() {
        return "1".equals(getSeat3fFlg()) ? getSeat3FNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeat3fSmokingOutView() {
        return "1".equals(getSeat3fFlg()) ? getSeat3FSmokingOut().toString() + "席" : "-";
    }
    public String getSeat3fNonSmokingOutView() {
        return "1".equals(getSeat3fFlg()) ? getSeat3FNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKei3View() {
        return "1".equals(getSeat3fFlg()) ? getSeatSmokingKei3().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKei3View() {
        return "1".equals(getSeat3fFlg()) ? getSeatNonSmokingKei3().toString() + "席" : "-";
    }

    public String getSeatUnderView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatUnder().toString() + "席" : "-";
    }
    public String getSeatUnderSmokingInView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatUnderSmokingIn().toString() + "席" : "-";
    }
    public String getSeatUnderNonSmokingInView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatUnderNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeatUnderSmokingOutView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatUnderSmokingOut().toString() + "席" : "-";
    }
    public String getSeatUnderNonSmokingOutView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatUnderNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKeiUnderView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatSmokingKeiU().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKeiUnderView() {
        return "1".equals(getSeatUnderFlg()) ? getSeatNonSmokingKeiU().toString() + "席" : "-";
    }

    public String getSeatCommonfView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatCommon().toString() + "席" : "-";
    }
    public String getSeatCommonSmokingInView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatCommonSmokingIn().toString() + "席" : "-";
    }
    public String getSeatCommonNonSmokingInView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatCommonNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeatCommonSmokingOutView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatCommonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatCommonNonSmokingOutView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatCommonNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKeiCommonView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatSmokingKeiC().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKeiCommonView() {
        return "1".equals(getSeatCommonFlg()) ? getSeatNonSmokingKeiC().toString() + "席" : "-";
    }

    public String getSeatOtherView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatOther().toString() + "席" : "-";
    }
    public String getSeatOtherSmokingInView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatOtherSmokingIn().toString() + "席" : "-";
    }
    public String getSeatOtherNonSmokingInView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatOtherNonSmokingIn().toString() + "席" : "-";
    }
    public String getSeatOtherSmokingOutView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatOtherSmokingOut().toString() + "席" : "-";
    }
    public String getSeatOtherNonSmokingOutView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatOtherNonSmokingOut().toString() + "席" : "-";
    }
    public String getSeatSmokingKeiOtherView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatSmokingKeiO().toString() + "席" : "-";
    }
    public String getSeatNonSmokingKeiOtherView() {
        return "1".equals(getSeatOtherFlg()) ? getSeatNonSmokingKeiO().toString() + "席" : "-";
    }

    /**
     * 客席情報 1F有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeat1fFlgBoolean() {
    	if ("1".equals(getSeat1fFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 1F有無フラグ チェックボックス用 設定処理
     */
    public void setSeat1fFlgBoolean(boolean flg) {
        if (flg) {
        	setSeat1fFlg("1");
        }
        else {
        	setSeat1fFlg("0");
        }
    }
    /**
     * 客席情報 2F有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeat2fFlgBoolean() {
    	if ("1".equals(getSeat2fFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 2F有無フラグ チェックボックス用 設定処理
     */
    public void setSeat2fFlgBoolean(boolean flg) {
        if (flg) {
        	setSeat2fFlg("1");
        }
        else {
        	setSeat2fFlg("0");
        }
    }
    /**
     * 客席情報 3F有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeat3fFlgBoolean() {
    	if ("1".equals(getSeat3fFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 3F有無フラグ チェックボックス用 設定処理
     */
    public void setSeat3fFlgBoolean(boolean flg) {
        if (flg) {
        	setSeat3fFlg("1");
        }
        else {
        	setSeat3fFlg("0");
        }
    }
    /**
     * 客席情報 Under有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeatUnderFlgBoolean() {
    	if ("1".equals(getSeatUnderFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 Under有無フラグ チェックボックス用 設定処理
     */
    public void setSeatUnderFlgBoolean(boolean flg) {
        if (flg) {
        	setSeatUnderFlg("1");
        }
        else {
        	setSeatUnderFlg("0");
        }
    }
    /**
     * 客席情報 Common有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeatCommonFlgBoolean() {
    	if ("1".equals(getSeatCommonFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 Common有無フラグ チェックボックス用 設定処理
     */
    public void setSeatCommonFlgBoolean(boolean flg) {
        if (flg) {
        	setSeatCommonFlg("1");
        }
        else {
        	setSeatCommonFlg("0");
        }
    }
    /**
     * 客席情報 Other有無フラグ チェックボックス用 取得処理
     */
    public boolean getSeatOtherFlgBoolean() {
    	if ("1".equals(getSeatOtherFlg())) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 客席情報 Other有無フラグ チェックボックス用 設定処理
     */
    public void setSeatOtherFlgBoolean(boolean flg) {
        if (flg) {
        	setSeatOtherFlg("1");
        }
        else {
        	setSeatOtherFlg("0");
        }
    }

	/**
	 * 店舗タイプ取得処理
	 * @return クラス変数kbn2 を戻します。
	 */
	public String getKbn2() {
		return kbn2;
	}

	/**
	 * 店舗タイプ設定処理
	 * @param kbn2 を クラス変数kbn2へ設定します。
	 */
	public void setKbn2(String kbn2) {
		this.kbn2 = kbn2;
	}

	/**
	 * 店舗タイプ名称取得処理
	 * @return クラス変数mTypeKbnName を戻します。
	 */
	public String getMTypeKbnName() {
		return mTypeKbnName;
	}

	/**
	 * 店舗タイプ名称設定処理
	 * @param typeKbnName を クラス変数mTypeKbnNameへ設定します。
	 */
	public void setMTypeKbnName(String typeKbnName) {
		mTypeKbnName = typeKbnName;
	}
}
