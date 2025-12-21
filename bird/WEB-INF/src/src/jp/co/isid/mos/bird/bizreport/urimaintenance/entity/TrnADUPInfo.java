package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TrnADUPInfo {
    
    public static final String TABLE = "BT97ADUP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String upFlg_COLUMN = "UP_FLG";
    
    public static final String upDt_COLUMN = "UP_DT";
    
    public static final String batupDt_COLUMN = "BATUP_DT";
    
    public static final String upNo_COLUMN = "UP_NO";
    
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    
    public static final String u01Uriage_COLUMN = "U01_URIAGE";
    
    public static final String u02MenuUri_COLUMN = "U02_MENU_URI";
    
    public static final String u03BuppanUri_COLUMN = "U03_BUPPAN_URI";
    
    public static final String u04Nebiki_COLUMN = "U04_NEBIKI";
    
    public static final String u05Nebikigo_COLUMN = "U05_NEBIKIGO";
    
    public static final String u06Tax_COLUMN = "U06_TAX";
    
    public static final String u07MinusKen_COLUMN = "U07_MINUS_KEN";
    
    public static final String u08MinusKin_COLUMN = "U08_MINUS_KIN";
    
    public static final String u09NebikiKen_COLUMN = "U09_NEBIKI_KEN";
    
    public static final String u10NebikiKin_COLUMN = "U10_NEBIKI_KIN";
    
    public static final String u11PNebikiKen_COLUMN = "U11_P_NEBIKI_KEN";
    
    public static final String u12PNebikiKin_COLUMN = "U12_P_NEBIKI_KIN";
    
    public static final String u13GenkinKen_COLUMN = "U13_GENKIN_KEN";
    
    public static final String u14GenkinKin_COLUMN = "U14_GENKIN_KIN";
    
    public static final String u15KaikeiKen2_COLUMN = "U15_KAIKEI_KEN_2";
    
    public static final String u16KaikeiKin2_COLUMN = "U16_KAIKEI_KIN_2";
    
    public static final String u17KaikeiKen3_COLUMN = "U17_KAIKEI_KEN_3";
    
    public static final String u18KaikeiKin3_COLUMN = "U18_KAIKEI_KIN_3";
    
    public static final String u19KaikeiKen4_COLUMN = "U19_KAIKEI_KEN_4";
    
    public static final String u20KaikeiKin4_COLUMN = "U20_KAIKEI_KIN_4";
    
    public static final String u21KaikeiKen5_COLUMN = "U21_KAIKEI_KEN_5";
    
    public static final String u22KaikeiKin5_COLUMN = "U22_KAIKEI_KIN_5";
    
    public static final String u23KaikeiKen6_COLUMN = "U23_KAIKEI_KEN_6";
    
    public static final String u24KaikeiKin6_COLUMN = "U24_KAIKEI_KIN_6";
    
    public static final String u25KaikeiKen7_COLUMN = "U25_KAIKEI_KEN_7";
    
    public static final String u26KaikeiKin7_COLUMN = "U26_KAIKEI_KIN_7";
    
    public static final String u27KaikeiKen8_COLUMN = "U27_KAIKEI_KEN_8";
    
    public static final String u28KaikeiKin8_COLUMN = "U28_KAIKEI_KIN_8";
    
    public static final String u29KaikeiKen9_COLUMN = "U29_KAIKEI_KEN_9";
    
    public static final String u30KaikeiKin9_COLUMN = "U30_KAIKEI_KIN_9";
    
    public static final String u31KaikeiKen10_COLUMN = "U31_KAIKEI_KEN_10";
    
    public static final String u32KaikeiKin10_COLUMN = "U32_KAIKEI_KIN_10";
    
    public static final String u33KaikeiKen11_COLUMN = "U33_KAIKEI_KEN_11";
    
    public static final String u34KaikeiKin11_COLUMN = "U34_KAIKEI_KIN_11";
    
    public static final String u35TieketKen1_COLUMN = "U35_TIEKET_KEN_1";
    
    public static final String u36TieketKin1_COLUMN = "U36_TIEKET_KIN_1";
    
    public static final String u37TieketKen2_COLUMN = "U37_TIEKET_KEN_2";
    
    public static final String u38TieketKin2_COLUMN = "U38_TIEKET_KIN_2";
    
    public static final String u39TieketKen3_COLUMN = "U39_TIEKET_KEN_3";
    
    public static final String u40TieketKin3_COLUMN = "U40_TIEKET_KIN_3";
    
    public static final String u41TieketKen4_COLUMN = "U41_TIEKET_KEN_4";
    
    public static final String u42TieketKin4_COLUMN = "U42_TIEKET_KIN_4";
    
    public static final String u43TieketKen5_COLUMN = "U43_TIEKET_KEN_5";
    
    public static final String u44TieketKin5_COLUMN = "U44_TIEKET_KIN_5";
    
    public static final String u45TieketKen6_COLUMN = "U45_TIEKET_KEN_6";
    
    public static final String u46TieketKin6_COLUMN = "U46_TIEKET_KIN_6";
    
    public static final String u47TieketKen7_COLUMN = "U47_TIEKET_KEN_7";
    
    public static final String u48TieketKin7_COLUMN = "U48_TIEKET_KIN_7";
    
    public static final String u49TieketKen8_COLUMN = "U49_TIEKET_KEN_8";
    
    public static final String u50TieketKin8_COLUMN = "U50_TIEKET_KIN_8";
    
    public static final String u51TieketKen9_COLUMN = "U51_TIEKET_KEN_9";
    
    public static final String u52TieketKin9_COLUMN = "U52_TIEKET_KIN_9";
    
    public static final String u53TieketKen10_COLUMN = "U53_TIEKET_KEN_10";
    
    public static final String u54TieketKin10_COLUMN = "U54_TIEKET_KIN_10";
    
    public static final String u55TieketKen11_COLUMN = "U55_TIEKET_KEN_11";
    
    public static final String u56TieketKin11_COLUMN = "U56_TIEKET_KIN_11";
    
    public static final String u57TieketKen12_COLUMN = "U57_TIEKET_KEN_12";
    
    public static final String u58TieketKin12_COLUMN = "U58_TIEKET_KIN_12";
    
    public static final String u59TieketKen13_COLUMN = "U59_TIEKET_KEN_13";
    
    public static final String u60TieketKin13_COLUMN = "U60_TIEKET_KIN_13";
    
    public static final String u61TieketKen14_COLUMN = "U61_TIEKET_KEN_14";
    
    public static final String u62TieketKin14_COLUMN = "U62_TIEKET_KIN_14";
    
    public static final String u63TieketKen15_COLUMN = "U63_TIEKET_KEN_15";
    
    public static final String u64TieketKin15_COLUMN = "U64_TIEKET_KIN_15";
    
    public static final String u65Nyukin_COLUMN = "U65_NYUKIN";
    
    public static final String u66Shukin_COLUMN = "U66_SHUKIN";
    
    public static final String u67AridakaCal_COLUMN = "U67_ARIDAKA_CAL";
    
    public static final String u68AridakaJitu_COLUMN = "U68_ARIDAKA_JITU";
    
    public static final String u69Kajou_COLUMN = "U69_KAJOU";
    
    public static final String u70Fusoku_COLUMN = "U70_FUSOKU";
    
    public static final String u71CanKen_COLUMN = "U71_CAN_KEN";
    
    public static final String u72CanKin_COLUMN = "U72_CAN_KIN";
    
    public static final String u73ChengeCnt_COLUMN = "U73_CHENGE_CNT";
    
    public static final String u74Kyakusu_COLUMN = "U74_KYAKUSU";
    
    public static final String u75AllcanKen_COLUMN = "U75_ALLCAN_KEN";
    
    public static final String u76AllcanKin_COLUMN = "U76_ALLCAN_KIN";
    
    public static final String dataKbn_COLUMN = "DATA_KBN";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * バッチ更新フラグ
     */
    private String upFlg;
    
    /**
     * 更新日付
     */
    private String upDt;
    
    /**
     * バッチ更新日付
     */
    private String batupDt;
    
    /**
     * 更新項目NO
     */
    private String upNo;
    
    /**
     * 天候区分
     */
    private BigDecimal tenkoKbn;
    
    /**
     * 売上計
     */
    private BigDecimal u01Uriage;
    
    /**
     * メニュー売上計
     */
    private BigDecimal u02MenuUri;
    
    /**
     * 物販計
     */
    private BigDecimal u03BuppanUri;
    
    /**
     * 値引計
     */
    private BigDecimal u04Nebiki;
    
    /**
     * 値引後売上
     */
    private BigDecimal u05Nebikigo;
    
    /**
     * 消費税
     */
    private BigDecimal u06Tax;
    
    /**
     * マイナス商品件数
     */
    private BigDecimal u07MinusKen;
    
    /**
     * マイナス商品金額
     */
    private BigDecimal u08MinusKin;
    
    /**
     * 値引件数
     */
    private BigDecimal u09NebikiKen;
    
    /**
     * 値引金額
     */
    private BigDecimal u10NebikiKin;
    
    /**
     * ％値引件数
     */
    private BigDecimal u11PNebikiKen;
    
    /**
     * ％値引金額
     */
    private BigDecimal u12PNebikiKin;
    
    /**
     * 現金件数
     */
    private BigDecimal u13GenkinKen;
    
    /**
     * 現金
     */
    private BigDecimal u14GenkinKin;
    
    /**
     * 会計区分２件数
     */
    private BigDecimal u15KaikeiKen2;
    
    /**
     * 会計区分２金額
     */
    private BigDecimal u16KaikeiKin2;
    
    /**
     * 会計区分３件数
     */
    private BigDecimal u17KaikeiKen3;
    
    /**
     * 会計区分３金額
     */
    private BigDecimal u18KaikeiKin3;
    
    /**
     * 会計区分４件数
     */
    private BigDecimal u19KaikeiKen4;
    
    /**
     * 会計区分４金額
     */
    private BigDecimal u20KaikeiKin4;
    
    /**
     * 会計区分５件数
     */
    private BigDecimal u21KaikeiKen5;
    
    /**
     * 会計区分５金額
     */
    private BigDecimal u22KaikeiKin5;
    
    /**
     * 会計区分６件数
     */
    private BigDecimal u23KaikeiKen6;
    
    /**
     * 会計区分６金額
     */
    private BigDecimal u24KaikeiKin6;
    
    /**
     * 会計区分７件数
     */
    private BigDecimal u25KaikeiKen7;
    
    /**
     * 会計区分７金額
     */
    private BigDecimal u26KaikeiKin7;
    
    /**
     * 会計区分８件数
     */
    private BigDecimal u27KaikeiKen8;
    
    /**
     * 会計区分８金額
     */
    private BigDecimal u28KaikeiKin8;
    
    /**
     * 会計区分９件数
     */
    private BigDecimal u29KaikeiKen9;
    
    /**
     * 会計区分９金額
     */
    private BigDecimal u30KaikeiKin9;
    
    /**
     * 会計区分１０件数
     */
    private BigDecimal u31KaikeiKen10;
    
    /**
     * 会計区分１０金額
     */
    private BigDecimal u32KaikeiKin10;
    
    /**
     * 会計区分１１件数
     */
    private BigDecimal u33KaikeiKen11;
    
    /**
     * 会計区分１１金額
     */
    private BigDecimal u34KaikeiKin11;
    
    /**
     * チケット１販売件数
     */
    private BigDecimal u35TieketKen1;
    
    /**
     * チケット１販売金額
     */
    private BigDecimal u36TieketKin1;
    
    /**
     * チケット２販売件数
     */
    private BigDecimal u37TieketKen2;
    
    /**
     * チケット２販売金額
     */
    private BigDecimal u38TieketKin2;
    
    /**
     * チケット３販売件数
     */
    private BigDecimal u39TieketKen3;
    
    /**
     * チケット３販売金額
     */
    private BigDecimal u40TieketKin3;
    
    /**
     * チケット４販売件数
     */
    private BigDecimal u41TieketKen4;
    
    /**
     * チケット４販売金額
     */
    private BigDecimal u42TieketKin4;
    
    /**
     * チケット５販売件数
     */
    private BigDecimal u43TieketKen5;
    
    /**
     * チケット５販売金額
     */
    private BigDecimal u44TieketKin5;
    
    /**
     * チケット６販売件数
     */
    private BigDecimal u45TieketKen6;
    
    /**
     * チケット６販売金額
     */
    private BigDecimal u46TieketKin6;
    
    /**
     * チケット７販売件数
     */
    private BigDecimal u47TieketKen7;
    
    /**
     * チケット７販売金額
     */
    private BigDecimal u48TieketKin7;
    
    /**
     * チケット８販売件数
     */
    private BigDecimal u49TieketKen8;
    
    /**
     * チケット８販売金額
     */
    private BigDecimal u50TieketKin8;
    
    /**
     * チケット９販売件数
     */
    private BigDecimal u51TieketKen9;
    
    /**
     * チケット９販売金額
     */
    private BigDecimal u52TieketKin9;
    
    /**
     * チケット１０販売件数
     */
    private BigDecimal u53TieketKen10;
    
    /**
     * チケット１０販売金額
     */
    private BigDecimal u54TieketKin10;
    
    /**
     * チケット１１販売件数
     */
    private BigDecimal u55TieketKen11;
    
    /**
     * チケット１１販売金額
     */
    private BigDecimal u56TieketKin11;
    
    /**
     * チケット１２販売件数
     */
    private BigDecimal u57TieketKen12;
    
    /**
     * チケット１２販売金額
     */
    private BigDecimal u58TieketKin12;
    
    /**
     * チケット１３販売件数
     */
    private BigDecimal u59TieketKen13;
    
    /**
     * チケット１３販売金額
     */
    private BigDecimal u60TieketKin13;
    
    /**
     * チケット１４販売件数
     */
    private BigDecimal u61TieketKen14;
    
    /**
     * チケット１４販売金額
     */
    private BigDecimal u62TieketKin14;
    
    /**
     * チケット１５販売件数
     */
    private BigDecimal u63TieketKen15;
    
    /**
     * チケット１５販売金額
     */
    private BigDecimal u64TieketKin15;
    
    /**
     * 入金金額
     */
    private BigDecimal u65Nyukin;
    
    /**
     * 出金金額
     */
    private BigDecimal u66Shukin;
    
    /**
     * 計算現金在高
     */
    private BigDecimal u67AridakaCal;
    
    /**
     * 実現金在高
     */
    private BigDecimal u68AridakaJitu;
    
    /**
     * 過剰金額
     */
    private BigDecimal u69Kajou;
    
    /**
     * 不足金額
     */
    private BigDecimal u70Fusoku;
    
    /**
     * 取消件数
     */
    private BigDecimal u71CanKen;
    
    /**
     * 取消金額
     */
    private BigDecimal u72CanKin;
    
    /**
     * 両替回数
     */
    private BigDecimal u73ChengeCnt;
    
    /**
     * 客数
     */
    private BigDecimal u74Kyakusu;
    
    /**
     * オールキャンセル回数
     */
    private BigDecimal u75AllcanKen;
    
    /**
     * オールキャンセル金額
     */
    private BigDecimal u76AllcanKin;
    
    /**
     * データ区分
     */
    private String  dataKbn;
    
    /**
     * 登録ユーザＩＤ
     */
    private String  firstUser;
    
    /**
     * 登録プログラムＩＤ
     */
    private String  firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザＩＤ
     */
    private String  lastUser;
    
    /**
     * 修正プログラムＩＤ
     */
    private String  lastPgm;
    
    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
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
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * バッチ更新フラグを取得します。
     * @return バッチ更新フラグ
     */
    public String getUpFlg() {
        return upFlg;
    }
    /**
     * バッチ更新フラグを設定します。
     * @param upFlg バッチ更新フラグ
     */
    public void setUpFlg(String upFlg) {
        this.upFlg = upFlg;
    }
    
    /**
     * 更新日付を取得します。
     * @return 更新日付
     */
    public String getUpDt() {
        return upDt;
    }
    /**
     * 更新日付を設定します。
     * @param upDt 更新日付
     */
    public void setUpDt(String upDt) {
        this.upDt = upDt;
    }
    
    /**
     * バッチ更新日付を取得します。
     * @return バッチ更新日付
     */
    public String getBatupDt() {
        return batupDt;
    }
    /**
     * バッチ更新日付を設定します。
     * @param batupDt バッチ更新日付
     */
    public void setBatupDt(String batupDt) {
        this.batupDt = batupDt;
    }
    
    /**
     * 更新項目NOを取得します。
     * @return 更新項目NO
     */
    public String getUpNo() {
        return upNo;
    }
    /**
     * 更新項目NOを設定します。
     * @param upNo 更新項目NO
     */
    public void setUpNo(String upNo) {
        this.upNo = upNo;
    }
    
    /**
     * 天候区分を取得します。
     * @return 天候区分
     */
    public BigDecimal getTenkoKbn() {
        return tenkoKbn;
    }
    /**
     * 天候区分を設定します。
     * @param tenkoKbn 天候区分
     */
    public void setTenkoKbn(BigDecimal tenkoKbn) {
        this.tenkoKbn = tenkoKbn;
    }
    
    /**
     * 売上計を取得します。
     * @return 売上計
     */
    public BigDecimal getU01Uriage() {
        return u01Uriage;
    }
    /**
     * 売上計を設定します。
     * @param u01Uriage 売上計
     */
    public void setU01Uriage(BigDecimal u01Uriage) {
        this.u01Uriage = u01Uriage;
    }
    
    /**
     * メニュー売上計を取得します。
     * @return メニュー売上計
     */
    public BigDecimal getU02MenuUri() {
        return u02MenuUri;
    }
    /**
     * メニュー売上計を設定します。
     * @param u02MenuUri メニュー売上計
     */
    public void setU02MenuUri(BigDecimal u02MenuUri) {
        this.u02MenuUri = u02MenuUri;
    }
    
    /**
     * 物販計を取得します。
     * @return 物販計
     */
    public BigDecimal getU03BuppanUri() {
        return u03BuppanUri;
    }
    /**
     * 物販計を設定します。
     * @param u03BuppanUri 物販計
     */
    public void setU03BuppanUri(BigDecimal u03BuppanUri) {
        this.u03BuppanUri = u03BuppanUri;
    }
    
    /**
     * 値引計を取得します。
     * @return 値引計
     */
    public BigDecimal getU04Nebiki() {
        return u04Nebiki;
    }
    /**
     * 値引計を設定します。
     * @param u04Nebiki 値引計
     */
    public void setU04Nebiki(BigDecimal u04Nebiki) {
        this.u04Nebiki = u04Nebiki;
    }
    
    /**
     * 値引後売上を取得します。
     * @return 値引後売上
     */
    public BigDecimal getU05Nebikigo() {
        return u05Nebikigo;
    }
    /**
     * 値引後売上を設定します。
     * @param u05Nebikigo 値引後売上
     */
    public void setU05Nebikigo(BigDecimal u05Nebikigo) {
        this.u05Nebikigo = u05Nebikigo;
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public BigDecimal getU06Tax() {
        return u06Tax;
    }
    /**
     * 消費税を設定します。
     * @param u06Tax 消費税
     */
    public void setU06Tax(BigDecimal u06Tax) {
        this.u06Tax = u06Tax;
    }
    
    /**
     * マイナス商品件数を取得します。
     * @return マイナス商品件数
     */
    public BigDecimal getU07MinusKen() {
        return u07MinusKen;
    }
    /**
     * マイナス商品件数を設定します。
     * @param u07MinusKen マイナス商品件数
     */
    public void setU07MinusKen(BigDecimal u07MinusKen) {
        this.u07MinusKen = u07MinusKen;
    }
    
    /**
     * マイナス商品金額を取得します。
     * @return マイナス商品金額
     */
    public BigDecimal getU08MinusKin() {
        return u08MinusKin;
    }
    /**
     * マイナス商品金額を設定します。
     * @param u08MinusKin マイナス商品金額
     */
    public void setU08MinusKin(BigDecimal u08MinusKin) {
        this.u08MinusKin = u08MinusKin;
    }
    
    /**
     * 値引件数を取得します。
     * @return 値引件数
     */
    public BigDecimal getU09NebikiKen() {
        return u09NebikiKen;
    }
    /**
     * 値引件数を設定します。
     * @param u09NebikiKen 値引件数
     */
    public void setU09NebikiKen(BigDecimal u09NebikiKen) {
        this.u09NebikiKen = u09NebikiKen;
    }
    
    /**
     * 値引金額を取得します。
     * @return 値引金額
     */
    public BigDecimal getU10NebikiKin() {
        return u10NebikiKin;
    }
    /**
     * 値引金額を設定します。
     * @param u10NebikiKin 値引金額
     */
    public void setU10NebikiKin(BigDecimal u10NebikiKin) {
        this.u10NebikiKin = u10NebikiKin;
    }
    
    /**
     * ％値引件数を取得します。
     * @return ％値引件数
     */
    public BigDecimal getU11PNebikiKen() {
        return u11PNebikiKen;
    }
    /**
     * ％値引件数を設定します。
     * @param u11PNebikiKen ％値引件数
     */
    public void setU11PNebikiKen(BigDecimal u11PNebikiKen) {
        this.u11PNebikiKen = u11PNebikiKen;
    }
    
    /**
     * ％値引金額を取得します。
     * @return ％値引金額
     */
    public BigDecimal getU12PNebikiKin() {
        return u12PNebikiKin;
    }
    /**
     * ％値引金額を設定します。
     * @param u12PNebikiKin ％値引金額
     */
    public void setU12PNebikiKin(BigDecimal u12PNebikiKin) {
        this.u12PNebikiKin = u12PNebikiKin;
    }
    
    /**
     * 現金件数を取得します。
     * @return 現金件数
     */
    public BigDecimal getU13GenkinKen() {
        return u13GenkinKen;
    }
    /**
     * 現金件数を設定します。
     * @param u13GenkinKen 現金件数
     */
    public void setU13GenkinKen(BigDecimal u13GenkinKen) {
        this.u13GenkinKen = u13GenkinKen;
    }
    
    /**
     * 現金を取得します。
     * @return 現金
     */
    public BigDecimal getU14GenkinKin() {
        return u14GenkinKin;
    }
    /**
     * 現金を設定します。
     * @param u14GenkinKin 現金
     */
    public void setU14GenkinKin(BigDecimal u14GenkinKin) {
        this.u14GenkinKin = u14GenkinKin;
    }
    
    /**
     * 会計区分２件数を取得します。
     * @return 会計区分２件数
     */
    public BigDecimal getU15KaikeiKen2() {
        return u15KaikeiKen2;
    }
    /**
     * 会計区分２件数を設定します。
     * @param u15KaikeiKen2 会計区分２件数
     */
    public void setU15KaikeiKen2(BigDecimal u15KaikeiKen2) {
        this.u15KaikeiKen2 = u15KaikeiKen2;
    }
    
    /**
     * 会計区分２金額を取得します。
     * @return 会計区分２金額
     */
    public BigDecimal getU16KaikeiKin2() {
        return u16KaikeiKin2;
    }
    /**
     * 会計区分２金額を設定します。
     * @param u16KaikeiKin2 会計区分２金額
     */
    public void setU16KaikeiKin2(BigDecimal u16KaikeiKin2) {
        this.u16KaikeiKin2 = u16KaikeiKin2;
    }
    
    /**
     * 会計区分３件数を取得します。
     * @return 会計区分３件数
     */
    public BigDecimal getU17KaikeiKen3() {
        return u17KaikeiKen3;
    }
    /**
     * 会計区分３件数を設定します。
     * @param u17KaikeiKen3 会計区分３件数
     */
    public void setU17KaikeiKen3(BigDecimal u17KaikeiKen3) {
        this.u17KaikeiKen3 = u17KaikeiKen3;
    }
    
    /**
     * 会計区分３金額を取得します。
     * @return 会計区分３金額
     */
    public BigDecimal getU18KaikeiKin3() {
        return u18KaikeiKin3;
    }
    /**
     * 会計区分３金額を設定します。
     * @param u18KaikeiKin3 会計区分３金額
     */
    public void setU18KaikeiKin3(BigDecimal u18KaikeiKin3) {
        this.u18KaikeiKin3 = u18KaikeiKin3;
    }
    
    /**
     * 会計区分４件数を取得します。
     * @return 会計区分４件数
     */
    public BigDecimal getU19KaikeiKen4() {
        return u19KaikeiKen4;
    }
    /**
     * 会計区分４件数を設定します。
     * @param u19KaikeiKen4 会計区分４件数
     */
    public void setU19KaikeiKen4(BigDecimal u19KaikeiKen4) {
        this.u19KaikeiKen4 = u19KaikeiKen4;
    }
    
    /**
     * 会計区分４金額を取得します。
     * @return 会計区分４金額
     */
    public BigDecimal getU20KaikeiKin4() {
        return u20KaikeiKin4;
    }
    /**
     * 会計区分４金額を設定します。
     * @param u20KaikeiKin4 会計区分４金額
     */
    public void setU20KaikeiKin4(BigDecimal u20KaikeiKin4) {
        this.u20KaikeiKin4 = u20KaikeiKin4;
    }
    
    /**
     * 会計区分５件数を取得します。
     * @return 会計区分５件数
     */
    public BigDecimal getU21KaikeiKen5() {
        return u21KaikeiKen5;
    }
    /**
     * 会計区分５件数を設定します。
     * @param u21KaikeiKen5 会計区分５件数
     */
    public void setU21KaikeiKen5(BigDecimal u21KaikeiKen5) {
        this.u21KaikeiKen5 = u21KaikeiKen5;
    }
    
    /**
     * 会計区分５金額を取得します。
     * @return 会計区分５金額
     */
    public BigDecimal getU22KaikeiKin5() {
        return u22KaikeiKin5;
    }
    /**
     * 会計区分５金額を設定します。
     * @param u22KaikeiKin5 会計区分５金額
     */
    public void setU22KaikeiKin5(BigDecimal u22KaikeiKin5) {
        this.u22KaikeiKin5 = u22KaikeiKin5;
    }
    
    /**
     * 会計区分６件数を取得します。
     * @return 会計区分６件数
     */
    public BigDecimal getU23KaikeiKen6() {
        return u23KaikeiKen6;
    }
    /**
     * 会計区分６件数を設定します。
     * @param u23KaikeiKen6 会計区分６件数
     */
    public void setU23KaikeiKen6(BigDecimal u23KaikeiKen6) {
        this.u23KaikeiKen6 = u23KaikeiKen6;
    }
    
    /**
     * 会計区分６金額を取得します。
     * @return 会計区分６金額
     */
    public BigDecimal getU24KaikeiKin6() {
        return u24KaikeiKin6;
    }
    /**
     * 会計区分６金額を設定します。
     * @param u24KaikeiKin6 会計区分６金額
     */
    public void setU24KaikeiKin6(BigDecimal u24KaikeiKin6) {
        this.u24KaikeiKin6 = u24KaikeiKin6;
    }
    
    /**
     * 会計区分７件数を取得します。
     * @return 会計区分７件数
     */
    public BigDecimal getU25KaikeiKen7() {
        return u25KaikeiKen7;
    }
    /**
     * 会計区分７件数を設定します。
     * @param u25KaikeiKen7 会計区分７件数
     */
    public void setU25KaikeiKen7(BigDecimal u25KaikeiKen7) {
        this.u25KaikeiKen7 = u25KaikeiKen7;
    }
    
    /**
     * 会計区分７金額を取得します。
     * @return 会計区分７金額
     */
    public BigDecimal getU26KaikeiKin7() {
        return u26KaikeiKin7;
    }
    /**
     * 会計区分７金額を設定します。
     * @param u26KaikeiKin7 会計区分７金額
     */
    public void setU26KaikeiKin7(BigDecimal u26KaikeiKin7) {
        this.u26KaikeiKin7 = u26KaikeiKin7;
    }
    
    /**
     * 会計区分８件数を取得します。
     * @return 会計区分８件数
     */
    public BigDecimal getU27KaikeiKen8() {
        return u27KaikeiKen8;
    }
    /**
     * 会計区分８件数を設定します。
     * @param u27KaikeiKen8 会計区分８件数
     */
    public void setU27KaikeiKen8(BigDecimal u27KaikeiKen8) {
        this.u27KaikeiKen8 = u27KaikeiKen8;
    }
    
    /**
     * 会計区分８金額を取得します。
     * @return 会計区分８金額
     */
    public BigDecimal getU28KaikeiKin8() {
        return u28KaikeiKin8;
    }
    /**
     * 会計区分８金額を設定します。
     * @param u28KaikeiKin8 会計区分８金額
     */
    public void setU28KaikeiKin8(BigDecimal u28KaikeiKin8) {
        this.u28KaikeiKin8 = u28KaikeiKin8;
    }
    
    /**
     * 会計区分９件数を取得します。
     * @return 会計区分９件数
     */
    public BigDecimal getU29KaikeiKen9() {
        return u29KaikeiKen9;
    }
    /**
     * 会計区分９件数を設定します。
     * @param u29KaikeiKen9 会計区分９件数
     */
    public void setU29KaikeiKen9(BigDecimal u29KaikeiKen9) {
        this.u29KaikeiKen9 = u29KaikeiKen9;
    }
    
    /**
     * 会計区分９金額を取得します。
     * @return 会計区分９金額
     */
    public BigDecimal getU30KaikeiKin9() {
        return u30KaikeiKin9;
    }
    /**
     * 会計区分９金額を設定します。
     * @param u30KaikeiKin9 会計区分９金額
     */
    public void setU30KaikeiKin9(BigDecimal u30KaikeiKin9) {
        this.u30KaikeiKin9 = u30KaikeiKin9;
    }
    
    /**
     * 会計区分１０件数を取得します。
     * @return 会計区分１０件数
     */
    public BigDecimal getU31KaikeiKen10() {
        return u31KaikeiKen10;
    }
    /**
     * 会計区分１０件数を設定します。
     * @param u31KaikeiKen10 会計区分１０件数
     */
    public void setU31KaikeiKen10(BigDecimal u31KaikeiKen10) {
        this.u31KaikeiKen10 = u31KaikeiKen10;
    }
    
    /**
     * 会計区分１０金額を取得します。
     * @return 会計区分１０金額
     */
    public BigDecimal getU32KaikeiKin10() {
        return u32KaikeiKin10;
    }
    /**
     * 会計区分１０金額を設定します。
     * @param u32KaikeiKin10 会計区分１０金額
     */
    public void setU32KaikeiKin10(BigDecimal u32KaikeiKin10) {
        this.u32KaikeiKin10 = u32KaikeiKin10;
    }
    
    /**
     * 会計区分１１件数を取得します。
     * @return 会計区分１１件数
     */
    public BigDecimal getU33KaikeiKen11() {
        return u33KaikeiKen11;
    }
    /**
     * 会計区分１１件数を設定します。
     * @param u33KaikeiKen11 会計区分１１件数
     */
    public void setU33KaikeiKen11(BigDecimal u33KaikeiKen11) {
        this.u33KaikeiKen11 = u33KaikeiKen11;
    }
    
    /**
     * 会計区分１１金額を取得します。
     * @return 会計区分１１金額
     */
    public BigDecimal getU34KaikeiKin11() {
        return u34KaikeiKin11;
    }
    /**
     * 会計区分１１金額を設定します。
     * @param u34KaikeiKin11 会計区分１１金額
     */
    public void setU34KaikeiKin11(BigDecimal u34KaikeiKin11) {
        this.u34KaikeiKin11 = u34KaikeiKin11;
    }
    
    /**
     * チケット１販売件数を取得します。
     * @return チケット１販売件数
     */
    public BigDecimal getU35TieketKen1() {
        return u35TieketKen1;
    }
    /**
     * チケット１販売件数を設定します。
     * @param u35TieketKen1 チケット１販売件数
     */
    public void setU35TieketKen1(BigDecimal u35TieketKen1) {
        this.u35TieketKen1 = u35TieketKen1;
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public BigDecimal getU36TieketKin1() {
        return u36TieketKin1;
    }
    /**
     * チケット１販売金額を設定します。
     * @param u36TieketKin1 チケット１販売金額
     */
    public void setU36TieketKin1(BigDecimal u36TieketKin1) {
        this.u36TieketKin1 = u36TieketKin1;
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public BigDecimal getU37TieketKen2() {
        return u37TieketKen2;
    }
    /**
     * チケット２販売件数を設定します。
     * @param u37TieketKen2 チケット２販売件数
     */
    public void setU37TieketKen2(BigDecimal u37TieketKen2) {
        this.u37TieketKen2 = u37TieketKen2;
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public BigDecimal getU38TieketKin2() {
        return u38TieketKin2;
    }
    /**
     * チケット２販売金額を設定します。
     * @param u38TieketKin2 チケット２販売金額
     */
    public void setU38TieketKin2(BigDecimal u38TieketKin2) {
        this.u38TieketKin2 = u38TieketKin2;
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public BigDecimal getU39TieketKen3() {
        return u39TieketKen3;
    }
    /**
     * チケット３販売件数を設定します。
     * @param u39TieketKen3 チケット３販売件数
     */
    public void setU39TieketKen3(BigDecimal u39TieketKen3) {
        this.u39TieketKen3 = u39TieketKen3;
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public BigDecimal getU40TieketKin3() {
        return u40TieketKin3;
    }
    /**
     * チケット３販売金額を設定します。
     * @param u40TieketKin3 チケット３販売金額
     */
    public void setU40TieketKin3(BigDecimal u40TieketKin3) {
        this.u40TieketKin3 = u40TieketKin3;
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public BigDecimal getU41TieketKen4() {
        return u41TieketKen4;
    }
    /**
     * チケット４販売件数を設定します。
     * @param u41TieketKen4 チケット４販売件数
     */
    public void setU41TieketKen4(BigDecimal u41TieketKen4) {
        this.u41TieketKen4 = u41TieketKen4;
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public BigDecimal getU42TieketKin4() {
        return u42TieketKin4;
    }
    /**
     * チケット４販売金額を設定します。
     * @param u42TieketKin4 チケット４販売金額
     */
    public void setU42TieketKin4(BigDecimal u42TieketKin4) {
        this.u42TieketKin4 = u42TieketKin4;
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public BigDecimal getU43TieketKen5() {
        return u43TieketKen5;
    }
    /**
     * チケット５販売件数を設定します。
     * @param u43TieketKen5 チケット５販売件数
     */
    public void setU43TieketKen5(BigDecimal u43TieketKen5) {
        this.u43TieketKen5 = u43TieketKen5;
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public BigDecimal getU44TieketKin5() {
        return u44TieketKin5;
    }
    /**
     * チケット５販売金額を設定します。
     * @param u44TieketKin5 チケット５販売金額
     */
    public void setU44TieketKin5(BigDecimal u44TieketKin5) {
        this.u44TieketKin5 = u44TieketKin5;
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public BigDecimal getU45TieketKen6() {
        return u45TieketKen6;
    }
    /**
     * チケット６販売件数を設定します。
     * @param u45TieketKen6 チケット６販売件数
     */
    public void setU45TieketKen6(BigDecimal u45TieketKen6) {
        this.u45TieketKen6 = u45TieketKen6;
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public BigDecimal getU46TieketKin6() {
        return u46TieketKin6;
    }
    /**
     * チケット６販売金額を設定します。
     * @param u46TieketKin6 チケット６販売金額
     */
    public void setU46TieketKin6(BigDecimal u46TieketKin6) {
        this.u46TieketKin6 = u46TieketKin6;
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public BigDecimal getU47TieketKen7() {
        return u47TieketKen7;
    }
    /**
     * チケット７販売件数を設定します。
     * @param u47TieketKen7 チケット７販売件数
     */
    public void setU47TieketKen7(BigDecimal u47TieketKen7) {
        this.u47TieketKen7 = u47TieketKen7;
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public BigDecimal getU48TieketKin7() {
        return u48TieketKin7;
    }
    /**
     * チケット７販売金額を設定します。
     * @param u48TieketKin7 チケット７販売金額
     */
    public void setU48TieketKin7(BigDecimal u48TieketKin7) {
        this.u48TieketKin7 = u48TieketKin7;
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public BigDecimal getU49TieketKen8() {
        return u49TieketKen8;
    }
    /**
     * チケット８販売件数を設定します。
     * @param u49TieketKen8 チケット８販売件数
     */
    public void setU49TieketKen8(BigDecimal u49TieketKen8) {
        this.u49TieketKen8 = u49TieketKen8;
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public BigDecimal getU50TieketKin8() {
        return u50TieketKin8;
    }
    /**
     * チケット８販売金額を設定します。
     * @param u50TieketKin8 チケット８販売金額
     */
    public void setU50TieketKin8(BigDecimal u50TieketKin8) {
        this.u50TieketKin8 = u50TieketKin8;
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public BigDecimal getU51TieketKen9() {
        return u51TieketKen9;
    }
    /**
     * チケット９販売件数を設定します。
     * @param u51TieketKen9 チケット９販売件数
     */
    public void setU51TieketKen9(BigDecimal u51TieketKen9) {
        this.u51TieketKen9 = u51TieketKen9;
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public BigDecimal getU52TieketKin9() {
        return u52TieketKin9;
    }
    /**
     * チケット９販売金額を設定します。
     * @param u52TieketKin9 チケット９販売金額
     */
    public void setU52TieketKin9(BigDecimal u52TieketKin9) {
        this.u52TieketKin9 = u52TieketKin9;
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public BigDecimal getU53TieketKen10() {
        return u53TieketKen10;
    }
    /**
     * チケット１０販売件数を設定します。
     * @param u53TieketKen10 チケット１０販売件数
     */
    public void setU53TieketKen10(BigDecimal u53TieketKen10) {
        this.u53TieketKen10 = u53TieketKen10;
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public BigDecimal getU54TieketKin10() {
        return u54TieketKin10;
    }
    /**
     * チケット１０販売金額を設定します。
     * @param u54TieketKin10 チケット１０販売金額
     */
    public void setU54TieketKin10(BigDecimal u54TieketKin10) {
        this.u54TieketKin10 = u54TieketKin10;
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public BigDecimal getU55TieketKen11() {
        return u55TieketKen11;
    }
    /**
     * チケット１１販売件数を設定します。
     * @param u55TieketKen11 チケット１１販売件数
     */
    public void setU55TieketKen11(BigDecimal u55TieketKen11) {
        this.u55TieketKen11 = u55TieketKen11;
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public BigDecimal getU56TieketKin11() {
        return u56TieketKin11;
    }
    /**
     * チケット１１販売金額を設定します。
     * @param u56TieketKin11 チケット１１販売金額
     */
    public void setU56TieketKin11(BigDecimal u56TieketKin11) {
        this.u56TieketKin11 = u56TieketKin11;
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public BigDecimal getU57TieketKen12() {
        return u57TieketKen12;
    }
    /**
     * チケット１２販売件数を設定します。
     * @param u57TieketKen12 チケット１２販売件数
     */
    public void setU57TieketKen12(BigDecimal u57TieketKen12) {
        this.u57TieketKen12 = u57TieketKen12;
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public BigDecimal getU58TieketKin12() {
        return u58TieketKin12;
    }
    /**
     * チケット１２販売金額を設定します。
     * @param u58TieketKin12 チケット１２販売金額
     */
    public void setU58TieketKin12(BigDecimal u58TieketKin12) {
        this.u58TieketKin12 = u58TieketKin12;
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public BigDecimal getU59TieketKen13() {
        return u59TieketKen13;
    }
    /**
     * チケット１３販売件数を設定します。
     * @param u59TieketKen13 チケット１３販売件数
     */
    public void setU59TieketKen13(BigDecimal u59TieketKen13) {
        this.u59TieketKen13 = u59TieketKen13;
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public BigDecimal getU60TieketKin13() {
        return u60TieketKin13;
    }
    /**
     * チケット１３販売金額を設定します。
     * @param u60TieketKin13 チケット１３販売金額
     */
    public void setU60TieketKin13(BigDecimal u60TieketKin13) {
        this.u60TieketKin13 = u60TieketKin13;
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public BigDecimal getU61TieketKen14() {
        return u61TieketKen14;
    }
    /**
     * チケット１４販売件数を設定します。
     * @param u61TieketKen14 チケット１４販売件数
     */
    public void setU61TieketKen14(BigDecimal u61TieketKen14) {
        this.u61TieketKen14 = u61TieketKen14;
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public BigDecimal getU62TieketKin14() {
        return u62TieketKin14;
    }
    /**
     * チケット１４販売金額を設定します。
     * @param u62TieketKin14 チケット１４販売金額
     */
    public void setU62TieketKin14(BigDecimal u62TieketKin14) {
        this.u62TieketKin14 = u62TieketKin14;
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public BigDecimal getU63TieketKen15() {
        return u63TieketKen15;
    }
    /**
     * チケット１５販売件数を設定します。
     * @param u63TieketKen15 チケット１５販売件数
     */
    public void setU63TieketKen15(BigDecimal u63TieketKen15) {
        this.u63TieketKen15 = u63TieketKen15;
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public BigDecimal getU64TieketKin15() {
        return u64TieketKin15;
    }
    /**
     * チケット１５販売金額を設定します。
     * @param u64TieketKin15 チケット１５販売金額
     */
    public void setU64TieketKin15(BigDecimal u64TieketKin15) {
        this.u64TieketKin15 = u64TieketKin15;
    }
    
    /**
     * 入金金額を取得します。
     * @return 入金金額
     */
    public BigDecimal getU65Nyukin() {
        return u65Nyukin;
    }
    /**
     * 入金金額を設定します。
     * @param u65Nyukin 入金金額
     */
    public void setU65Nyukin(BigDecimal u65Nyukin) {
        this.u65Nyukin = u65Nyukin;
    }
    
    /**
     * 出金金額を取得します。
     * @return 出金金額
     */
    public BigDecimal getU66Shukin() {
        return u66Shukin;
    }
    /**
     * 出金金額を設定します。
     * @param u66Shukin 出金金額
     */
    public void setU66Shukin(BigDecimal u66Shukin) {
        this.u66Shukin = u66Shukin;
    }
    
    /**
     * 計算現金在高を取得します。
     * @return 計算現金在高
     */
    public BigDecimal getU67AridakaCal() {
        return u67AridakaCal;
    }
    /**
     * 計算現金在高を設定します。
     * @param u67AridakaCal 計算現金在高
     */
    public void setU67AridakaCal(BigDecimal u67AridakaCal) {
        this.u67AridakaCal = u67AridakaCal;
    }
    
    /**
     * 実現金在高を取得します。
     * @return 実現金在高
     */
    public BigDecimal getU68AridakaJitu() {
        return u68AridakaJitu;
    }
    /**
     * 実現金在高を設定します。
     * @param u68AridakaJitu 実現金在高
     */
    public void setU68AridakaJitu(BigDecimal u68AridakaJitu) {
        this.u68AridakaJitu = u68AridakaJitu;
    }
    
    /**
     * 過剰金額を取得します。
     * @return 過剰金額
     */
    public BigDecimal getU69Kajou() {
        return u69Kajou;
    }
    /**
     * 過剰金額を設定します。
     * @param u69Kajou 過剰金額
     */
    public void setU69Kajou(BigDecimal u69Kajou) {
        this.u69Kajou = u69Kajou;
    }
    
    /**
     * 不足金額を取得します。
     * @return 不足金額
     */
    public BigDecimal getU70Fusoku() {
        return u70Fusoku;
    }
    /**
     * 不足金額を設定します。
     * @param u70Fusoku 不足金額
     */
    public void setU70Fusoku(BigDecimal u70Fusoku) {
        this.u70Fusoku = u70Fusoku;
    }
    
    /**
     * 取消件数を取得します。
     * @return 取消件数
     */
    public BigDecimal getU71CanKen() {
        return u71CanKen;
    }
    /**
     * 取消件数を設定します。
     * @param u71CanKen 取消件数
     */
    public void setU71CanKen(BigDecimal u71CanKen) {
        this.u71CanKen = u71CanKen;
    }
    
    /**
     * 取消金額を取得します。
     * @return 取消金額
     */
    public BigDecimal getU72CanKin() {
        return u72CanKin;
    }
    /**
     * 取消金額を設定します。
     * @param u72CanKin 取消金額
     */
    public void setU72CanKin(BigDecimal u72CanKin) {
        this.u72CanKin = u72CanKin;
    }
    
    /**
     * 両替回数を取得します。
     * @return 両替回数
     */
    public BigDecimal getU73ChengeCnt() {
        return u73ChengeCnt;
    }
    /**
     * 両替回数を設定します。
     * @param u73ChengeCnt 両替回数
     */
    public void setU73ChengeCnt(BigDecimal u73ChengeCnt) {
        this.u73ChengeCnt = u73ChengeCnt;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getU74Kyakusu() {
        return u74Kyakusu;
    }
    /**
     * 客数を設定します。
     * @param u74Kyakusu 客数
     */
    public void setU74Kyakusu(BigDecimal u74Kyakusu) {
        this.u74Kyakusu = u74Kyakusu;
    }
    
    /**
     * オールキャンセル回数を取得します。
     * @return オールキャンセル回数
     */
    public BigDecimal getU75AllcanKen() {
        return u75AllcanKen;
    }
    /**
     * オールキャンセル回数を設定します。
     * @param u75AllcanKen オールキャンセル回数
     */
    public void setU75AllcanKen(BigDecimal u75AllcanKen) {
        this.u75AllcanKen = u75AllcanKen;
    }
    
    /**
     * オールキャンセル金額を取得します。
     * @return オールキャンセル金額
     */
    public BigDecimal getU76AllcanKin() {
        return u76AllcanKin;
    }
    /**
     * オールキャンセル金額を設定します。
     * @param u76AllcanKin オールキャンセル金額
     */
    public void setU76AllcanKin(BigDecimal u76AllcanKin) {
        this.u76AllcanKin = u76AllcanKin;
    }
    
    /**
     * データ区分を取得します。
     * @return データ区分
     */
    public String  getDataKbn() {
        return dataKbn;
    }
    /**
     * データ区分を設定します。
     * @param dataKbn データ区分
     */
    public void setDataKbn(String  dataKbn) {
        this.dataKbn = dataKbn;
    }
    
    /**
     * 登録ユーザＩＤを取得します。
     * @return 登録ユーザＩＤ
     */
    public String  getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザＩＤを設定します。
     * @param firstUser 登録ユーザＩＤ
     */
    public void setFirstUser(String  firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムＩＤを取得します。
     * @return 登録プログラムＩＤ
     */
    public String  getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムＩＤを設定します。
     * @param firstPgm 登録プログラムＩＤ
     */
    public void setFirstPgm(String  firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザＩＤを取得します。
     * @return 修正ユーザＩＤ
     */
    public String  getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザＩＤを設定します。
     * @param lastUser 修正ユーザＩＤ
     */
    public void setLastUser(String  lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムＩＤを取得します。
     * @return 修正プログラムＩＤ
     */
    public String  getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムＩＤを設定します。
     * @param lastPgm 修正プログラムＩＤ
     */
    public void setLastPgm(String  lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
