package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;


public class UIUriMainteWorkInfo {
    
    public static final String TABLE = "BT97ADUP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String upFlg_COLUMN = "UP_FLG";
    
    public static final String upDt_COLUMN = "UP_DT";
    
    public static final String batupDt_COLUMN = "BATUP_DT";
    
    public static final String upNo97_COLUMN = "UP_NO97";
    
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
    
    public static final String upNo96_COLUMN = "UP_NO96";
    
    public static final String u01NebikiKen1_COLUMN = "U01_NEBIKI_KEN_1";
    
    public static final String u02NebikiKin1_COLUMN = "U02_NEBIKI_KIN_1";
    
    public static final String u03NebikiTax1_COLUMN = "U03_NEBIKI_TAX_1";
    
    public static final String u04NebikiKen2_COLUMN = "U04_NEBIKI_KEN_2";
    
    public static final String u05NebikiKin2_COLUMN = "U05_NEBIKI_KIN_2";
    
    public static final String u06NebikiTax2_COLUMN = "U06_NEBIKI_TAX_2";
    
    public static final String u07NebikiKen3_COLUMN = "U07_NEBIKI_KEN_3";
    
    public static final String u08NebikiKin3_COLUMN = "U08_NEBIKI_KIN_3";
    
    public static final String u09NebikiTax3_COLUMN = "U09_NEBIKI_TAX_3";
    
    public static final String u10NebikiKen4_COLUMN = "U10_NEBIKI_KEN_4";
    
    public static final String u11NebikiKin4_COLUMN = "U11_NEBIKI_KIN_4";
    
    public static final String u12NebikiTax4_COLUMN = "U12_NEBIKI_TAX_4";
    
    public static final String u13NebikiKen5_COLUMN = "U13_NEBIKI_KEN_5";
    
    public static final String u14NebikiKin5_COLUMN = "U14_NEBIKI_KIN_5";
    
    public static final String u15NebikiTax5_COLUMN = "U15_NEBIKI_TAX_5";
    
    public static final String u16NebikiKen6_COLUMN = "U16_NEBIKI_KEN_6";
    
    public static final String u17NebikiKin6_COLUMN = "U17_NEBIKI_KIN_6";
    
    public static final String u18NebikiTax6_COLUMN = "U18_NEBIKI_TAX_6";
    
    public static final String u19NebikiKen7_COLUMN = "U19_NEBIKI_KEN_7";
    
    public static final String u20NebikiKin7_COLUMN = "U20_NEBIKI_KIN_7";
    
    public static final String u21NebikiTax7_COLUMN = "U21_NEBIKI_TAX_7";
    
    public static final String u22NebikiKen8_COLUMN = "U22_NEBIKI_KEN_8";
    
    public static final String u23NebikiKin8_COLUMN = "U23_NEBIKI_KIN_8";
    
    public static final String u24NebikiTax8_COLUMN = "U24_NEBIKI_TAX_8";
    
    public static final String u25NebikiKen9_COLUMN = "U25_NEBIKI_KEN_9";
    
    public static final String u26NebikiKin9_COLUMN = "U26_NEBIKI_KIN_9";
    
    public static final String u27NebikiTax9_COLUMN = "U27_NEBIKI_TAX_9";
    
    public static final String flg1_COLUMN = "FLG1";
    
    public static final String flg2_COLUMN = "FLG2";
    
    public static final String flg3_COLUMN = "FLG3";
    
    public static final String LAST_TMSP96_COLUMN = "LAST_TMSP96";
    
    public static final String LAST_TMSP97_COLUMN = "LAST_TMSP97";
    
    
    
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
     * 更新項目NO(BT97ADUP)
     */
    private String upNo97;
    
    /**
     * 更新項目NO(BT96NBUP)
     */
    private String upNo96;
    
    /**
     * 天候区分
     */
    private String tenkoKbn;
    
    /**
     * 売上計
     */
    private String u01Uriage;
    
    /**
     * メニュー売上計
     */
    private String u02MenuUri;
    
    /**
     * 物販計
     */
    private String u03BuppanUri;
    
    /**
     * 値引計
     */
    private String u04Nebiki;
    
    /**
     * 値引後売上
     */
    private String u05Nebikigo;
    
    /**
     * 消費税
     */
    private String u06Tax;
    
    /**
     * マイナス商品件数
     */
    private String u07MinusKen;
    
    /**
     * マイナス商品金額
     */
    private String u08MinusKin;
    
    /**
     * 値引件数
     */
    private String u09NebikiKen;
    
    /**
     * 値引金額
     */
    private String u10NebikiKin;
    
    /**
     * ％値引件数
     */
    private String u11PNebikiKen;
    
    /**
     * ％値引金額
     */
    private String u12PNebikiKin;
    
    /**
     * 現金件数
     */
    private String u13GenkinKen;
    
    /**
     * 現金
     */
    private String u14GenkinKin;
    
    /**
     * 会計区分２件数
     */
    private String u15KaikeiKen2;
    
    /**
     * 会計区分２金額
     */
    private String u16KaikeiKin2;
    
    /**
     * 会計区分３件数
     */
    private String u17KaikeiKen3;
    
    /**
     * 会計区分３金額
     */
    private String u18KaikeiKin3;
    
    /**
     * 会計区分４件数
     */
    private String u19KaikeiKen4;
    
    /**
     * 会計区分４金額
     */
    private String u20KaikeiKin4;
    
    /**
     * 会計区分５件数
     */
    private String u21KaikeiKen5;
    
    /**
     * 会計区分５金額
     */
    private String u22KaikeiKin5;
    
    /**
     * 会計区分６件数
     */
    private String u23KaikeiKen6;
    
    /**
     * 会計区分６金額
     */
    private String u24KaikeiKin6;
    
    /**
     * 会計区分７件数
     */
    private String u25KaikeiKen7;
    
    /**
     * 会計区分７金額
     */
    private String u26KaikeiKin7;
    
    /**
     * 会計区分８件数
     */
    private String u27KaikeiKen8;
    
    /**
     * 会計区分８金額
     */
    private String u28KaikeiKin8;
    
    /**
     * 会計区分９件数
     */
    private String u29KaikeiKen9;
    
    /**
     * 会計区分９金額
     */
    private String u30KaikeiKin9;
    
    /**
     * 会計区分１０件数
     */
    private String u31KaikeiKen10;
    
    /**
     * 会計区分１０金額
     */
    private String u32KaikeiKin10;
    
    /**
     * 会計区分１１件数
     */
    private String u33KaikeiKen11;
    
    /**
     * 会計区分１１金額
     */
    private String u34KaikeiKin11;
    
    /**
     * チケット１販売件数
     */
    private String u35TieketKen1;
    
    /**
     * チケット１販売金額
     */
    private String u36TieketKin1;
    
    /**
     * チケット２販売件数
     */
    private String u37TieketKen2;
    
    /**
     * チケット２販売金額
     */
    private String u38TieketKin2;
    
    /**
     * チケット３販売件数
     */
    private String u39TieketKen3;
    
    /**
     * チケット３販売金額
     */
    private String u40TieketKin3;
    
    /**
     * チケット４販売件数
     */
    private String u41TieketKen4;
    
    /**
     * チケット４販売金額
     */
    private String u42TieketKin4;
    
    /**
     * チケット５販売件数
     */
    private String u43TieketKen5;
    
    /**
     * チケット５販売金額
     */
    private String u44TieketKin5;
    
    /**
     * チケット６販売件数
     */
    private String u45TieketKen6;
    
    /**
     * チケット６販売金額
     */
    private String u46TieketKin6;
    
    /**
     * チケット７販売件数
     */
    private String u47TieketKen7;
    
    /**
     * チケット７販売金額
     */
    private String u48TieketKin7;
    
    /**
     * チケット８販売件数
     */
    private String u49TieketKen8;
    
    /**
     * チケット８販売金額
     */
    private String u50TieketKin8;
    
    /**
     * チケット９販売件数
     */
    private String u51TieketKen9;
    
    /**
     * チケット９販売金額
     */
    private String u52TieketKin9;
    
    /**
     * チケット１０販売件数
     */
    private String u53TieketKen10;
    
    /**
     * チケット１０販売金額
     */
    private String u54TieketKin10;
    
    /**
     * チケット１１販売件数
     */
    private String u55TieketKen11;
    
    /**
     * チケット１１販売金額
     */
    private String u56TieketKin11;
    
    /**
     * チケット１２販売件数
     */
    private String u57TieketKen12;
    
    /**
     * チケット１２販売金額
     */
    private String u58TieketKin12;
    
    /**
     * チケット１３販売件数
     */
    private String u59TieketKen13;
    
    /**
     * チケット１３販売金額
     */
    private String u60TieketKin13;
    
    /**
     * チケット１４販売件数
     */
    private String u61TieketKen14;
    
    /**
     * チケット１４販売金額
     */
    private String u62TieketKin14;
    
    /**
     * チケット１５販売件数
     */
    private String u63TieketKen15;
    
    /**
     * チケット１５販売金額
     */
    private String u64TieketKin15;
    
    /**
     * 入金金額
     */
    private String u65Nyukin;
    
    /**
     * 出金金額
     */
    private String u66Shukin;
    
    /**
     * 計算現金在高
     */
    private String u67AridakaCal;
    
    /**
     * 実現金在高
     */
    private String u68AridakaJitu;
    
    /**
     * 過剰金額
     */
    private String u69Kajou;
    
    /**
     * 不足金額
     */
    private String u70Fusoku;
    
    /**
     * 取消件数
     */
    private String u71CanKen;
    
    /**
     * 取消金額
     */
    private String u72CanKin;
    
    /**
     * 両替回数
     */
    private String u73ChengeCnt;
    
    /**
     * 客数
     */
    private String u74Kyakusu;
    
    /**
     * オールキャンセル回数
     */
    private String u75AllcanKen;
    
    /**
     * オールキャンセル金額
     */
    private String u76AllcanKin;
    
    /**
     * データ区分
     */
    private String  dataKbn;
    
    /**
     * 値引１件数
     */
    private String  u01NebikiKen1;
    
    /**
     * 値引１金額
     */
    private String  u02NebikiKin1;
    
    /**
     * 値引１税額
     */
    private String  u03NebikiTax1;
    
    /**
     * 値引２件数
     */
    private String  u04NebikiKen2;
    
    /**
     * 値引２金額
     */
    private String  u05NebikiKin2;
    
    /**
     * 値引２税額
     */
    private String  u06NebikiTax2;
    
    /**
     * 値引３件数
     */
    private String  u07NebikiKen3;
    
    /**
     * 値引３金額
     */
    private String  u08NebikiKin3;
    
    /**
     * 値引３税額
     */
    private String  u09NebikiTax3;
    
    /**
     * 値引４件数
     */
    private String  u10NebikiKen4;
    
    /**
     * 値引４金額
     */
    private String  u11NebikiKin4;
    
    /**
     * 値引４税額
     */
    private String  u12NebikiTax4;
    
    /**
     * 値引５件数
     */
    private String  u13NebikiKen5;
    
    /**
     * 値引５金額
     */
    private String  u14NebikiKin5;
    
    /**
     * 値引５税額
     */
    private String  u15NebikiTax5;
    
    /**
     * 値引６件数
     */
    private String  u16NebikiKen6;
    
    /**
     * 値引６金額
     */
    private String  u17NebikiKin6;
    
    /**
     * 値引６税額
     */
    private String  u18NebikiTax6;
    
    /**
     * 値引７件数
     */
    private String  u19NebikiKen7;
    
    /**
     * 値引７金額
     */
    private String  u20NebikiKin7;
    
    /**
     * 値引７税額
     */
    private String  u21NebikiTax7;
    
    /**
     * 値引８件数
     */
    private String  u22NebikiKen8;
    
    /**
     * 値引８金額
     */
    private String  u23NebikiKin8;
    
    /**
     * 値引８税額
     */
    private String  u24NebikiTax8;
    
    /**
     * 値引９件数
     */
    private String  u25NebikiKen9;
    
    /**
     * 値引９金額
     */
    private String  u26NebikiKin9;
    
    /**
     * 値引９税額
     */
    private String  u27NebikiTax9;
    
    /**
     * フラグ１
     */
    private String flg1;

    /**
     * フラグ２
     */
    private String flg2;

    /**
     * フラグ３
     */
    private String flg3;
    
    /**
     * 取引修正TBL lastTmsp
     */
    private Timestamp lastTmsp96;
    
    /**
     * 現金在高日次修正TBL lastTmsp
     */
    private Timestamp lastTmsp97;
    
    
    /**
     * 現金過不足
     */
    private String genkinKafusoku = "0";
    
    /**
     * 合計レコードフラグ
     */
    private boolean totalFlg;
    
    /**
     * 新規追加フラグ
     */
    private boolean insertFlg;
    
    
    
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
     * 更新項目NO(BT97ADUP)を取得します。
     * @return 更新項目NO
     */
    public String getUpNo97() {
        return upNo97;
    }
    /**
     * 更新項目NO()を設定します。
     * @param upNo97 更新項目NO
     */
    public void setUpNo97(String upNo97) {
        this.upNo97 = upNo97;
    }
    
    /**
     * 更新項目NO(BT97ADUP)を取得します。
     * @return 更新項目NO
     */
    public String getUpNo96() {
        return upNo96;
    }
    /**
     * 更新項目NO()を設定します。
     * @param upNo96 更新項目NO
     */
    public void setUpNo96(String upNo96) {
        this.upNo96 = upNo96;
    }
    
    /**
     * 天候区分を取得します。
     * @return 天候区分
     */
    public String getTenkoKbn() {
        return tenkoKbn;
    }
    /**
     * 天候区分を設定します。
     * @param tenkoKbn 天候区分
     */
    public void setTenkoKbn(String tenkoKbn) {
        this.tenkoKbn = tenkoKbn;
    }
    
    /**
     * 売上計を取得します。
     * @return 売上計
     */
    public String getU01Uriage() {
        return u01Uriage;
    }
    /**
     * 売上計を設定します。
     * @param u01Uriage 売上計
     */
    public void setU01Uriage(String u01Uriage) {
        this.u01Uriage = u01Uriage;
    }
    
    /**
     * メニュー売上計を取得します。
     * @return メニュー売上計
     */
    public String getU02MenuUri() {
        return u02MenuUri;
    }
    /**
     * メニュー売上計を設定します。
     * @param u02MenuUri メニュー売上計
     */
    public void setU02MenuUri(String u02MenuUri) {
        this.u02MenuUri = u02MenuUri;
    }
    
    /**
     * 物販計を取得します。
     * @return 物販計
     */
    public String getU03BuppanUri() {
        return u03BuppanUri;
    }
    /**
     * 物販計を設定します。
     * @param u03BuppanUri 物販計
     */
    public void setU03BuppanUri(String u03BuppanUri) {
        this.u03BuppanUri = u03BuppanUri;
    }
    
    /**
     * 値引計を取得します。
     * @return 値引計
     */
    public String getU04Nebiki() {
        return u04Nebiki;
    }
    /**
     * 値引計を設定します。
     * @param u04Nebiki 値引計
     */
    public void setU04Nebiki(String u04Nebiki) {
        this.u04Nebiki = u04Nebiki;
    }
    
    /**
     * 値引後売上を取得します。
     * @return 値引後売上
     */
    public String getU05Nebikigo() {
        return u05Nebikigo;
    }
    /**
     * 値引後売上を設定します。
     * @param u05Nebikigo 値引後売上
     */
    public void setU05Nebikigo(String u05Nebikigo) {
        this.u05Nebikigo = u05Nebikigo;
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public String getU06Tax() {
        return u06Tax;
    }
    /**
     * 消費税を設定します。
     * @param u06Tax 消費税
     */
    public void setU06Tax(String u06Tax) {
        this.u06Tax = u06Tax;
    }
    
    /**
     * マイナス商品件数を取得します。
     * @return マイナス商品件数
     */
    public String getU07MinusKen() {
        return u07MinusKen;
    }
    /**
     * マイナス商品件数を設定します。
     * @param u07MinusKen マイナス商品件数
     */
    public void setU07MinusKen(String u07MinusKen) {
        this.u07MinusKen = u07MinusKen;
    }
    
    /**
     * マイナス商品金額を取得します。
     * @return マイナス商品金額
     */
    public String getU08MinusKin() {
        return u08MinusKin;
    }
    /**
     * マイナス商品金額を設定します。
     * @param u08MinusKin マイナス商品金額
     */
    public void setU08MinusKin(String u08MinusKin) {
        this.u08MinusKin = u08MinusKin;
    }
    
    /**
     * 値引件数を取得します。
     * @return 値引件数
     */
    public String getU09NebikiKen() {
        return u09NebikiKen;
    }
    /**
     * 値引件数を設定します。
     * @param u09NebikiKen 値引件数
     */
    public void setU09NebikiKen(String u09NebikiKen) {
        this.u09NebikiKen = u09NebikiKen;
    }
    
    /**
     * 値引金額を取得します。
     * @return 値引金額
     */
    public String getU10NebikiKin() {
        return u10NebikiKin;
    }
    /**
     * 値引金額を設定します。
     * @param u10NebikiKin 値引金額
     */
    public void setU10NebikiKin(String u10NebikiKin) {
        this.u10NebikiKin = u10NebikiKin;
    }
    
    /**
     * ％値引件数を取得します。
     * @return ％値引件数
     */
    public String getU11PNebikiKen() {
        return u11PNebikiKen;
    }
    /**
     * ％値引件数を設定します。
     * @param u11PNebikiKen ％値引件数
     */
    public void setU11PNebikiKen(String u11PNebikiKen) {
        this.u11PNebikiKen = u11PNebikiKen;
    }
    
    /**
     * ％値引金額を取得します。
     * @return ％値引金額
     */
    public String getU12PNebikiKin() {
        return u12PNebikiKin;
    }
    /**
     * ％値引金額を設定します。
     * @param u12PNebikiKin ％値引金額
     */
    public void setU12PNebikiKin(String u12PNebikiKin) {
        this.u12PNebikiKin = u12PNebikiKin;
    }
    
    /**
     * 現金件数を取得します。
     * @return 現金件数
     */
    public String getU13GenkinKen() {
        return u13GenkinKen;
    }
    /**
     * 現金件数を設定します。
     * @param u13GenkinKen 現金件数
     */
    public void setU13GenkinKen(String u13GenkinKen) {
        this.u13GenkinKen = u13GenkinKen;
    }
    
    /**
     * 現金を取得します。
     * @return 現金
     */
    public String getU14GenkinKin() {
        return u14GenkinKin;
    }
    /**
     * 現金を設定します。
     * @param u14GenkinKin 現金
     */
    public void setU14GenkinKin(String u14GenkinKin) {
        this.u14GenkinKin = u14GenkinKin;
    }
    
    /**
     * 会計区分２件数を取得します。
     * @return 会計区分２件数
     */
    public String getU15KaikeiKen2() {
        return u15KaikeiKen2;
    }
    /**
     * 会計区分２件数を設定します。
     * @param u15KaikeiKen2 会計区分２件数
     */
    public void setU15KaikeiKen2(String u15KaikeiKen2) {
        this.u15KaikeiKen2 = u15KaikeiKen2;
    }
    
    /**
     * 会計区分２金額を取得します。
     * @return 会計区分２金額
     */
    public String getU16KaikeiKin2() {
        return u16KaikeiKin2;
    }
    /**
     * 会計区分２金額を設定します。
     * @param u16KaikeiKin2 会計区分２金額
     */
    public void setU16KaikeiKin2(String u16KaikeiKin2) {
        this.u16KaikeiKin2 = u16KaikeiKin2;
    }
    
    /**
     * 会計区分３件数を取得します。
     * @return 会計区分３件数
     */
    public String getU17KaikeiKen3() {
        return u17KaikeiKen3;
    }
    /**
     * 会計区分３件数を設定します。
     * @param u17KaikeiKen3 会計区分３件数
     */
    public void setU17KaikeiKen3(String u17KaikeiKen3) {
        this.u17KaikeiKen3 = u17KaikeiKen3;
    }
    
    /**
     * 会計区分３金額を取得します。
     * @return 会計区分３金額
     */
    public String getU18KaikeiKin3() {
        return u18KaikeiKin3;
    }
    /**
     * 会計区分３金額を設定します。
     * @param u18KaikeiKin3 会計区分３金額
     */
    public void setU18KaikeiKin3(String u18KaikeiKin3) {
        this.u18KaikeiKin3 = u18KaikeiKin3;
    }
    
    /**
     * 会計区分４件数を取得します。
     * @return 会計区分４件数
     */
    public String getU19KaikeiKen4() {
        return u19KaikeiKen4;
    }
    /**
     * 会計区分４件数を設定します。
     * @param u19KaikeiKen4 会計区分４件数
     */
    public void setU19KaikeiKen4(String u19KaikeiKen4) {
        this.u19KaikeiKen4 = u19KaikeiKen4;
    }
    
    /**
     * 会計区分４金額を取得します。
     * @return 会計区分４金額
     */
    public String getU20KaikeiKin4() {
        return u20KaikeiKin4;
    }
    /**
     * 会計区分４金額を設定します。
     * @param u20KaikeiKin4 会計区分４金額
     */
    public void setU20KaikeiKin4(String u20KaikeiKin4) {
        this.u20KaikeiKin4 = u20KaikeiKin4;
    }
    
    /**
     * 会計区分５件数を取得します。
     * @return 会計区分５件数
     */
    public String getU21KaikeiKen5() {
        return u21KaikeiKen5;
    }
    /**
     * 会計区分５件数を設定します。
     * @param u21KaikeiKen5 会計区分５件数
     */
    public void setU21KaikeiKen5(String u21KaikeiKen5) {
        this.u21KaikeiKen5 = u21KaikeiKen5;
    }
    
    /**
     * 会計区分５金額を取得します。
     * @return 会計区分５金額
     */
    public String getU22KaikeiKin5() {
        return u22KaikeiKin5;
    }
    /**
     * 会計区分５金額を設定します。
     * @param u22KaikeiKin5 会計区分５金額
     */
    public void setU22KaikeiKin5(String u22KaikeiKin5) {
        this.u22KaikeiKin5 = u22KaikeiKin5;
    }
    
    /**
     * 会計区分６件数を取得します。
     * @return 会計区分６件数
     */
    public String getU23KaikeiKen6() {
        return u23KaikeiKen6;
    }
    /**
     * 会計区分６件数を設定します。
     * @param u23KaikeiKen6 会計区分６件数
     */
    public void setU23KaikeiKen6(String u23KaikeiKen6) {
        this.u23KaikeiKen6 = u23KaikeiKen6;
    }
    
    /**
     * 会計区分６金額を取得します。
     * @return 会計区分６金額
     */
    public String getU24KaikeiKin6() {
        return u24KaikeiKin6;
    }
    /**
     * 会計区分６金額を設定します。
     * @param u24KaikeiKin6 会計区分６金額
     */
    public void setU24KaikeiKin6(String u24KaikeiKin6) {
        this.u24KaikeiKin6 = u24KaikeiKin6;
    }
    
    /**
     * 会計区分７件数を取得します。
     * @return 会計区分７件数
     */
    public String getU25KaikeiKen7() {
        return u25KaikeiKen7;
    }
    /**
     * 会計区分７件数を設定します。
     * @param u25KaikeiKen7 会計区分７件数
     */
    public void setU25KaikeiKen7(String u25KaikeiKen7) {
        this.u25KaikeiKen7 = u25KaikeiKen7;
    }
    
    /**
     * 会計区分７金額を取得します。
     * @return 会計区分７金額
     */
    public String getU26KaikeiKin7() {
        return u26KaikeiKin7;
    }
    /**
     * 会計区分７金額を設定します。
     * @param u26KaikeiKin7 会計区分７金額
     */
    public void setU26KaikeiKin7(String u26KaikeiKin7) {
        this.u26KaikeiKin7 = u26KaikeiKin7;
    }
    
    /**
     * 会計区分８件数を取得します。
     * @return 会計区分８件数
     */
    public String getU27KaikeiKen8() {
        return u27KaikeiKen8;
    }
    /**
     * 会計区分８件数を設定します。
     * @param u27KaikeiKen8 会計区分８件数
     */
    public void setU27KaikeiKen8(String u27KaikeiKen8) {
        this.u27KaikeiKen8 = u27KaikeiKen8;
    }
    
    /**
     * 会計区分８金額を取得します。
     * @return 会計区分８金額
     */
    public String getU28KaikeiKin8() {
        return u28KaikeiKin8;
    }
    /**
     * 会計区分８金額を設定します。
     * @param u28KaikeiKin8 会計区分８金額
     */
    public void setU28KaikeiKin8(String u28KaikeiKin8) {
        this.u28KaikeiKin8 = u28KaikeiKin8;
    }
    
    /**
     * 会計区分９件数を取得します。
     * @return 会計区分９件数
     */
    public String getU29KaikeiKen9() {
        return u29KaikeiKen9;
    }
    /**
     * 会計区分９件数を設定します。
     * @param u29KaikeiKen9 会計区分９件数
     */
    public void setU29KaikeiKen9(String u29KaikeiKen9) {
        this.u29KaikeiKen9 = u29KaikeiKen9;
    }
    
    /**
     * 会計区分９金額を取得します。
     * @return 会計区分９金額
     */
    public String getU30KaikeiKin9() {
        return u30KaikeiKin9;
    }
    /**
     * 会計区分９金額を設定します。
     * @param u30KaikeiKin9 会計区分９金額
     */
    public void setU30KaikeiKin9(String u30KaikeiKin9) {
        this.u30KaikeiKin9 = u30KaikeiKin9;
    }
    
    /**
     * 会計区分１０件数を取得します。
     * @return 会計区分１０件数
     */
    public String getU31KaikeiKen10() {
        return u31KaikeiKen10;
    }
    /**
     * 会計区分１０件数を設定します。
     * @param u31KaikeiKen10 会計区分１０件数
     */
    public void setU31KaikeiKen10(String u31KaikeiKen10) {
        this.u31KaikeiKen10 = u31KaikeiKen10;
    }
    
    /**
     * 会計区分１０金額を取得します。
     * @return 会計区分１０金額
     */
    public String getU32KaikeiKin10() {
        return u32KaikeiKin10;
    }
    /**
     * 会計区分１０金額を設定します。
     * @param u32KaikeiKin10 会計区分１０金額
     */
    public void setU32KaikeiKin10(String u32KaikeiKin10) {
        this.u32KaikeiKin10 = u32KaikeiKin10;
    }
    
    /**
     * 会計区分１１件数を取得します。
     * @return 会計区分１１件数
     */
    public String getU33KaikeiKen11() {
        return u33KaikeiKen11;
    }
    /**
     * 会計区分１１件数を設定します。
     * @param u33KaikeiKen11 会計区分１１件数
     */
    public void setU33KaikeiKen11(String u33KaikeiKen11) {
        this.u33KaikeiKen11 = u33KaikeiKen11;
    }
    
    /**
     * 会計区分１１金額を取得します。
     * @return 会計区分１１金額
     */
    public String getU34KaikeiKin11() {
        return u34KaikeiKin11;
    }
    /**
     * 会計区分１１金額を設定します。
     * @param u34KaikeiKin11 会計区分１１金額
     */
    public void setU34KaikeiKin11(String u34KaikeiKin11) {
        this.u34KaikeiKin11 = u34KaikeiKin11;
    }
    
    /**
     * チケット１販売件数を取得します。
     * @return チケット１販売件数
     */
    public String getU35TieketKen1() {
        return u35TieketKen1;
    }
    /**
     * チケット１販売件数を設定します。
     * @param u35TieketKen1 チケット１販売件数
     */
    public void setU35TieketKen1(String u35TieketKen1) {
        this.u35TieketKen1 = u35TieketKen1;
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public String getU36TieketKin1() {
        return u36TieketKin1;
    }
    /**
     * チケット１販売金額を設定します。
     * @param u36TieketKin1 チケット１販売金額
     */
    public void setU36TieketKin1(String u36TieketKin1) {
        this.u36TieketKin1 = u36TieketKin1;
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public String getU37TieketKen2() {
        return u37TieketKen2;
    }
    /**
     * チケット２販売件数を設定します。
     * @param u37TieketKen2 チケット２販売件数
     */
    public void setU37TieketKen2(String u37TieketKen2) {
        this.u37TieketKen2 = u37TieketKen2;
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public String getU38TieketKin2() {
        return u38TieketKin2;
    }
    /**
     * チケット２販売金額を設定します。
     * @param u38TieketKin2 チケット２販売金額
     */
    public void setU38TieketKin2(String u38TieketKin2) {
        this.u38TieketKin2 = u38TieketKin2;
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public String getU39TieketKen3() {
        return u39TieketKen3;
    }
    /**
     * チケット３販売件数を設定します。
     * @param u39TieketKen3 チケット３販売件数
     */
    public void setU39TieketKen3(String u39TieketKen3) {
        this.u39TieketKen3 = u39TieketKen3;
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public String getU40TieketKin3() {
        return u40TieketKin3;
    }
    /**
     * チケット３販売金額を設定します。
     * @param u40TieketKin3 チケット３販売金額
     */
    public void setU40TieketKin3(String u40TieketKin3) {
        this.u40TieketKin3 = u40TieketKin3;
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public String getU41TieketKen4() {
        return u41TieketKen4;
    }
    /**
     * チケット４販売件数を設定します。
     * @param u41TieketKen4 チケット４販売件数
     */
    public void setU41TieketKen4(String u41TieketKen4) {
        this.u41TieketKen4 = u41TieketKen4;
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public String getU42TieketKin4() {
        return u42TieketKin4;
    }
    /**
     * チケット４販売金額を設定します。
     * @param u42TieketKin4 チケット４販売金額
     */
    public void setU42TieketKin4(String u42TieketKin4) {
        this.u42TieketKin4 = u42TieketKin4;
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public String getU43TieketKen5() {
        return u43TieketKen5;
    }
    /**
     * チケット５販売件数を設定します。
     * @param u43TieketKen5 チケット５販売件数
     */
    public void setU43TieketKen5(String u43TieketKen5) {
        this.u43TieketKen5 = u43TieketKen5;
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public String getU44TieketKin5() {
        return u44TieketKin5;
    }
    /**
     * チケット５販売金額を設定します。
     * @param u44TieketKin5 チケット５販売金額
     */
    public void setU44TieketKin5(String u44TieketKin5) {
        this.u44TieketKin5 = u44TieketKin5;
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public String getU45TieketKen6() {
        return u45TieketKen6;
    }
    /**
     * チケット６販売件数を設定します。
     * @param u45TieketKen6 チケット６販売件数
     */
    public void setU45TieketKen6(String u45TieketKen6) {
        this.u45TieketKen6 = u45TieketKen6;
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public String getU46TieketKin6() {
        return u46TieketKin6;
    }
    /**
     * チケット６販売金額を設定します。
     * @param u46TieketKin6 チケット６販売金額
     */
    public void setU46TieketKin6(String u46TieketKin6) {
        this.u46TieketKin6 = u46TieketKin6;
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public String getU47TieketKen7() {
        return u47TieketKen7;
    }
    /**
     * チケット７販売件数を設定します。
     * @param u47TieketKen7 チケット７販売件数
     */
    public void setU47TieketKen7(String u47TieketKen7) {
        this.u47TieketKen7 = u47TieketKen7;
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public String getU48TieketKin7() {
        return u48TieketKin7;
    }
    /**
     * チケット７販売金額を設定します。
     * @param u48TieketKin7 チケット７販売金額
     */
    public void setU48TieketKin7(String u48TieketKin7) {
        this.u48TieketKin7 = u48TieketKin7;
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public String getU49TieketKen8() {
        return u49TieketKen8;
    }
    /**
     * チケット８販売件数を設定します。
     * @param u49TieketKen8 チケット８販売件数
     */
    public void setU49TieketKen8(String u49TieketKen8) {
        this.u49TieketKen8 = u49TieketKen8;
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public String getU50TieketKin8() {
        return u50TieketKin8;
    }
    /**
     * チケット８販売金額を設定します。
     * @param u50TieketKin8 チケット８販売金額
     */
    public void setU50TieketKin8(String u50TieketKin8) {
        this.u50TieketKin8 = u50TieketKin8;
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public String getU51TieketKen9() {
        return u51TieketKen9;
    }
    /**
     * チケット９販売件数を設定します。
     * @param u51TieketKen9 チケット９販売件数
     */
    public void setU51TieketKen9(String u51TieketKen9) {
        this.u51TieketKen9 = u51TieketKen9;
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public String getU52TieketKin9() {
        return u52TieketKin9;
    }
    /**
     * チケット９販売金額を設定します。
     * @param u52TieketKin9 チケット９販売金額
     */
    public void setU52TieketKin9(String u52TieketKin9) {
        this.u52TieketKin9 = u52TieketKin9;
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public String getU53TieketKen10() {
        return u53TieketKen10;
    }
    /**
     * チケット１０販売件数を設定します。
     * @param u53TieketKen10 チケット１０販売件数
     */
    public void setU53TieketKen10(String u53TieketKen10) {
        this.u53TieketKen10 = u53TieketKen10;
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public String getU54TieketKin10() {
        return u54TieketKin10;
    }
    /**
     * チケット１０販売金額を設定します。
     * @param u54TieketKin10 チケット１０販売金額
     */
    public void setU54TieketKin10(String u54TieketKin10) {
        this.u54TieketKin10 = u54TieketKin10;
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public String getU55TieketKen11() {
        return u55TieketKen11;
    }
    /**
     * チケット１１販売件数を設定します。
     * @param u55TieketKen11 チケット１１販売件数
     */
    public void setU55TieketKen11(String u55TieketKen11) {
        this.u55TieketKen11 = u55TieketKen11;
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public String getU56TieketKin11() {
        return u56TieketKin11;
    }
    /**
     * チケット１１販売金額を設定します。
     * @param u56TieketKin11 チケット１１販売金額
     */
    public void setU56TieketKin11(String u56TieketKin11) {
        this.u56TieketKin11 = u56TieketKin11;
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public String getU57TieketKen12() {
        return u57TieketKen12;
    }
    /**
     * チケット１２販売件数を設定します。
     * @param u57TieketKen12 チケット１２販売件数
     */
    public void setU57TieketKen12(String u57TieketKen12) {
        this.u57TieketKen12 = u57TieketKen12;
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public String getU58TieketKin12() {
        return u58TieketKin12;
    }
    /**
     * チケット１２販売金額を設定します。
     * @param u58TieketKin12 チケット１２販売金額
     */
    public void setU58TieketKin12(String u58TieketKin12) {
        this.u58TieketKin12 = u58TieketKin12;
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public String getU59TieketKen13() {
        return u59TieketKen13;
    }
    /**
     * チケット１３販売件数を設定します。
     * @param u59TieketKen13 チケット１３販売件数
     */
    public void setU59TieketKen13(String u59TieketKen13) {
        this.u59TieketKen13 = u59TieketKen13;
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public String getU60TieketKin13() {
        return u60TieketKin13;
    }
    /**
     * チケット１３販売金額を設定します。
     * @param u60TieketKin13 チケット１３販売金額
     */
    public void setU60TieketKin13(String u60TieketKin13) {
        this.u60TieketKin13 = u60TieketKin13;
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public String getU61TieketKen14() {
        return u61TieketKen14;
    }
    /**
     * チケット１４販売件数を設定します。
     * @param u61TieketKen14 チケット１４販売件数
     */
    public void setU61TieketKen14(String u61TieketKen14) {
        this.u61TieketKen14 = u61TieketKen14;
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public String getU62TieketKin14() {
        return u62TieketKin14;
    }
    /**
     * チケット１４販売金額を設定します。
     * @param u62TieketKin14 チケット１４販売金額
     */
    public void setU62TieketKin14(String u62TieketKin14) {
        this.u62TieketKin14 = u62TieketKin14;
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public String getU63TieketKen15() {
        return u63TieketKen15;
    }
    /**
     * チケット１５販売件数を設定します。
     * @param u63TieketKen15 チケット１５販売件数
     */
    public void setU63TieketKen15(String u63TieketKen15) {
        this.u63TieketKen15 = u63TieketKen15;
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public String getU64TieketKin15() {
        return u64TieketKin15;
    }
    /**
     * チケット１５販売金額を設定します。
     * @param u64TieketKin15 チケット１５販売金額
     */
    public void setU64TieketKin15(String u64TieketKin15) {
        this.u64TieketKin15 = u64TieketKin15;
    }
    
    /**
     * 入金金額を取得します。
     * @return 入金金額
     */
    public String getU65Nyukin() {
        return u65Nyukin;
    }
    /**
     * 入金金額を設定します。
     * @param u65Nyukin 入金金額
     */
    public void setU65Nyukin(String u65Nyukin) {
        this.u65Nyukin = u65Nyukin;
    }
    
    /**
     * 出金金額を取得します。
     * @return 出金金額
     */
    public String getU66Shukin() {
        return u66Shukin;
    }
    /**
     * 出金金額を設定します。
     * @param u66Shukin 出金金額
     */
    public void setU66Shukin(String u66Shukin) {
        this.u66Shukin = u66Shukin;
    }
    
    /**
     * 計算現金在高を取得します。
     * @return 計算現金在高
     */
    public String getU67AridakaCal() {
        return u67AridakaCal;
    }
    /**
     * 計算現金在高を設定します。
     * @param u67AridakaCal 計算現金在高
     */
    public void setU67AridakaCal(String u67AridakaCal) {
        this.u67AridakaCal = u67AridakaCal;
    }
    
    /**
     * 実現金在高を取得します。
     * @return 実現金在高
     */
    public String getU68AridakaJitu() {
        return u68AridakaJitu;
    }
    /**
     * 実現金在高を設定します。
     * @param u68AridakaJitu 実現金在高
     */
    public void setU68AridakaJitu(String u68AridakaJitu) {
        this.u68AridakaJitu = u68AridakaJitu;
    }
    
    /**
     * 過剰金額を取得します。
     * @return 過剰金額
     */
    public String getU69Kajou() {
        return u69Kajou;
    }
    /**
     * 過剰金額を設定します。
     * @param u69Kajou 過剰金額
     */
    public void setU69Kajou(String u69Kajou) {
        this.u69Kajou = u69Kajou;
    }
    
    /**
     * 不足金額を取得します。
     * @return 不足金額
     */
    public String getU70Fusoku() {
        return u70Fusoku;
    }
    /**
     * 不足金額を設定します。
     * @param u70Fusoku 不足金額
     */
    public void setU70Fusoku(String u70Fusoku) {
        this.u70Fusoku = u70Fusoku;
    }
    
    /**
     * 取消件数を取得します。
     * @return 取消件数
     */
    public String getU71CanKen() {
        return u71CanKen;
    }
    /**
     * 取消件数を設定します。
     * @param u71CanKen 取消件数
     */
    public void setU71CanKen(String u71CanKen) {
        this.u71CanKen = u71CanKen;
    }
    
    /**
     * 取消金額を取得します。
     * @return 取消金額
     */
    public String getU72CanKin() {
        return u72CanKin;
    }
    /**
     * 取消金額を設定します。
     * @param u72CanKin 取消金額
     */
    public void setU72CanKin(String u72CanKin) {
        this.u72CanKin = u72CanKin;
    }
    
    /**
     * 両替回数を取得します。
     * @return 両替回数
     */
    public String getU73ChengeCnt() {
        return u73ChengeCnt;
    }
    /**
     * 両替回数を設定します。
     * @param u73ChengeCnt 両替回数
     */
    public void setU73ChengeCnt(String u73ChengeCnt) {
        this.u73ChengeCnt = u73ChengeCnt;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public String getU74Kyakusu() {
        return u74Kyakusu;
    }
    /**
     * 客数を設定します。
     * @param u74Kyakusu 客数
     */
    public void setU74Kyakusu(String u74Kyakusu) {
        this.u74Kyakusu = u74Kyakusu;
    }
    
    /**
     * オールキャンセル回数を取得します。
     * @return オールキャンセル回数
     */
    public String getU75AllcanKen() {
        return u75AllcanKen;
    }
    /**
     * オールキャンセル回数を設定します。
     * @param u75AllcanKen オールキャンセル回数
     */
    public void setU75AllcanKen(String u75AllcanKen) {
        this.u75AllcanKen = u75AllcanKen;
    }
    
    /**
     * オールキャンセル金額を取得します。
     * @return オールキャンセル金額
     */
    public String getU76AllcanKin() {
        return u76AllcanKin;
    }
    /**
     * オールキャンセル金額を設定します。
     * @param u76AllcanKin オールキャンセル金額
     */
    public void setU76AllcanKin(String u76AllcanKin) {
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
     * 値引１件数を取得します。
     * @return 値引１件数
     */
    public String  getU01NebikiKen1() {
        return u01NebikiKen1;
    }
    /**
     * 値引１件数を設定します。
     * @param u01NebikiKen1 値引１件数
     */
    public void setU01NebikiKen1(String  u01NebikiKen1) {
        this.u01NebikiKen1 = u01NebikiKen1;
    }
    
    /**
     * 値引１金額を取得します。
     * @return 値引１金額
     */
    public String  getU02NebikiKin1() {
        return u02NebikiKin1;
    }
    /**
     * 値引１金額を設定します。
     * @param u02NebikiKin1 値引１金額
     */
    public void setU02NebikiKin1(String  u02NebikiKin1) {
        this.u02NebikiKin1 = u02NebikiKin1;
    }
    
    /**
     * 値引１税額を取得します。
     * @return 値引１税額
     */
    public String  getU03NebikiTax1() {
        return u03NebikiTax1;
    }
    /**
     * 値引１税額を設定します。
     * @param u03NebikiTax1 値引１税額
     */
    public void setU03NebikiTax1(String  u03NebikiTax1) {
        this.u03NebikiTax1 = u03NebikiTax1;
    }
    
    /**
     * 値引２件数を取得します。
     * @return 値引２件数
     */
    public String  getU04NebikiKen2() {
        return u04NebikiKen2;
    }
    /**
     * 値引２件数を設定します。
     * @param u04NebikiKen2 値引２件数
     */
    public void setU04NebikiKen2(String  u04NebikiKen2) {
        this.u04NebikiKen2 = u04NebikiKen2;
    }
    
    /**
     * 値引２金額を取得します。
     * @return 値引２金額
     */
    public String  getU05NebikiKin2() {
        return u05NebikiKin2;
    }
    /**
     * 値引２金額を設定します。
     * @param u05NebikiKin2 値引２金額
     */
    public void setU05NebikiKin2(String  u05NebikiKin2) {
        this.u05NebikiKin2 = u05NebikiKin2;
    }
    
    /**
     * 値引２税額を取得します。
     * @return 値引２税額
     */
    public String  getU06NebikiTax2() {
        return u06NebikiTax2;
    }
    /**
     * 値引２税額を設定します。
     * @param u06NebikiTax2 値引２税額
     */
    public void setU06NebikiTax2(String  u06NebikiTax2) {
        this.u06NebikiTax2 = u06NebikiTax2;
    }
    
    /**
     * 値引３件数を取得します。
     * @return 値引３件数
     */
    public String  getU07NebikiKen3() {
        return u07NebikiKen3;
    }
    /**
     * 値引３件数を設定します。
     * @param u07NebikiKen3 値引３件数
     */
    public void setU07NebikiKen3(String  u07NebikiKen3) {
        this.u07NebikiKen3 = u07NebikiKen3;
    }
    
    /**
     * 値引３金額を取得します。
     * @return 値引３金額
     */
    public String  getU08NebikiKin3() {
        return u08NebikiKin3;
    }
    /**
     * 値引３金額を設定します。
     * @param u08NebikiKin3 値引３金額
     */
    public void setU08NebikiKin3(String  u08NebikiKin3) {
        this.u08NebikiKin3 = u08NebikiKin3;
    }
    
    /**
     * 値引３税額を取得します。
     * @return 値引３税額
     */
    public String  getU09NebikiTax3() {
        return u09NebikiTax3;
    }
    /**
     * 値引３税額を設定します。
     * @param u09NebikiTax3 値引３税額
     */
    public void setU09NebikiTax3(String  u09NebikiTax3) {
        this.u09NebikiTax3 = u09NebikiTax3;
    }
    
    /**
     * 値引４件数を取得します。
     * @return 値引４件数
     */
    public String  getU10NebikiKen4() {
        return u10NebikiKen4;
    }
    /**
     * 値引４件数を設定します。
     * @param u10NebikiKen4 値引４件数
     */
    public void setU10NebikiKen4(String  u10NebikiKen4) {
        this.u10NebikiKen4 = u10NebikiKen4;
    }
    
    /**
     * 値引４金額を取得します。
     * @return 値引４金額
     */
    public String  getU11NebikiKin4() {
        return u11NebikiKin4;
    }
    /**
     * 値引４金額を設定します。
     * @param u11NebikiKin4 値引４金額
     */
    public void setU11NebikiKin4(String  u11NebikiKin4) {
        this.u11NebikiKin4 = u11NebikiKin4;
    }
    
    /**
     * 値引４税額を取得します。
     * @return 値引４税額
     */
    public String  getU12NebikiTax4() {
        return u12NebikiTax4;
    }
    /**
     * 値引４税額を設定します。
     * @param u12NebikiTax4 値引４税額
     */
    public void setU12NebikiTax4(String  u12NebikiTax4) {
        this.u12NebikiTax4 = u12NebikiTax4;
    }
    
    /**
     * 値引５件数を取得します。
     * @return 値引５件数
     */
    public String  getU13NebikiKen5() {
        return u13NebikiKen5;
    }
    /**
     * 値引５件数を設定します。
     * @param u13NebikiKen5 値引５件数
     */
    public void setU13NebikiKen5(String  u13NebikiKen5) {
        this.u13NebikiKen5 = u13NebikiKen5;
    }
    
    /**
     * 値引５金額を取得します。
     * @return 値引５金額
     */
    public String  getU14NebikiKin5() {
        return u14NebikiKin5;
    }
    /**
     * 値引５金額を設定します。
     * @param u14NebikiKin5 値引５金額
     */
    public void setU14NebikiKin5(String  u14NebikiKin5) {
        this.u14NebikiKin5 = u14NebikiKin5;
    }
    
    /**
     * 値引５税額を取得します。
     * @return 値引５税額
     */
    public String  getU15NebikiTax5() {
        return u15NebikiTax5;
    }
    /**
     * 値引５税額を設定します。
     * @param u15NebikiTax5 値引５税額
     */
    public void setU15NebikiTax5(String  u15NebikiTax5) {
        this.u15NebikiTax5 = u15NebikiTax5;
    }
    
    /**
     * 値引６件数を取得します。
     * @return 値引６件数
     */
    public String  getU16NebikiKen6() {
        return u16NebikiKen6;
    }
    /**
     * 値引６件数を設定します。
     * @param u16NebikiKen6 値引６件数
     */
    public void setU16NebikiKen6(String  u16NebikiKen6) {
        this.u16NebikiKen6 = u16NebikiKen6;
    }
    
    /**
     * 値引６金額を取得します。
     * @return 値引６金額
     */
    public String  getU17NebikiKin6() {
        return u17NebikiKin6;
    }
    /**
     * 値引６金額を設定します。
     * @param u17NebikiKin6 値引６金額
     */
    public void setU17NebikiKin6(String  u17NebikiKin6) {
        this.u17NebikiKin6 = u17NebikiKin6;
    }
    
    /**
     * 値引６税額を取得します。
     * @return 値引６税額
     */
    public String  getU18NebikiTax6() {
        return u18NebikiTax6;
    }
    /**
     * 値引６税額を設定します。
     * @param u18NebikiTax6 値引６税額
     */
    public void setU18NebikiTax6(String  u18NebikiTax6) {
        this.u18NebikiTax6 = u18NebikiTax6;
    }
    
    /**
     * 値引７件数を取得します。
     * @return 値引７件数
     */
    public String  getU19NebikiKen7() {
        return u19NebikiKen7;
    }
    /**
     * 値引７件数を設定します。
     * @param u19NebikiKen7 値引７件数
     */
    public void setU19NebikiKen7(String  u19NebikiKen7) {
        this.u19NebikiKen7 = u19NebikiKen7;
    }
    
    /**
     * 値引７金額を取得します。
     * @return 値引７金額
     */
    public String  getU20NebikiKin7() {
        return u20NebikiKin7;
    }
    /**
     * 値引７金額を設定します。
     * @param u20NebikiKin7 値引７金額
     */
    public void setU20NebikiKin7(String  u20NebikiKin7) {
        this.u20NebikiKin7 = u20NebikiKin7;
    }
    
    /**
     * 値引７税額を取得します。
     * @return 値引７税額
     */
    public String  getU21NebikiTax7() {
        return u21NebikiTax7;
    }
    /**
     * 値引７税額を設定します。
     * @param u21NebikiTax7 値引７税額
     */
    public void setU21NebikiTax7(String  u21NebikiTax7) {
        this.u21NebikiTax7 = u21NebikiTax7;
    }
    
    /**
     * 値引８件数を取得します。
     * @return 値引８件数
     */
    public String  getU22NebikiKen8() {
        return u22NebikiKen8;
    }
    /**
     * 値引８件数を設定します。
     * @param u22NebikiKen8 値引８件数
     */
    public void setU22NebikiKen8(String  u22NebikiKen8) {
        this.u22NebikiKen8 = u22NebikiKen8;
    }
    
    /**
     * 値引８金額を取得します。
     * @return 値引８金額
     */
    public String  getU23NebikiKin8() {
        return u23NebikiKin8;
    }
    /**
     * 値引８金額を設定します。
     * @param u23NebikiKin8 値引８金額
     */
    public void setU23NebikiKin8(String  u23NebikiKin8) {
        this.u23NebikiKin8 = u23NebikiKin8;
    }
    
    /**
     * 値引８税額を取得します。
     * @return 値引８税額
     */
    public String  getU24NebikiTax8() {
        return u24NebikiTax8;
    }
    /**
     * 値引８税額を設定します。
     * @param u24NebikiTax8 値引８税額
     */
    public void setU24NebikiTax8(String  u24NebikiTax8) {
        this.u24NebikiTax8 = u24NebikiTax8;
    }
    
    /**
     * 値引９件数を取得します。
     * @return 値引９件数
     */
    public String  getU25NebikiKen9() {
        return u25NebikiKen9;
    }
    /**
     * 値引９件数を設定します。
     * @param u25NebikiKen9 値引９件数
     */
    public void setU25NebikiKen9(String  u25NebikiKen9) {
        this.u25NebikiKen9 = u25NebikiKen9;
    }
    
    /**
     * 値引９金額を取得します。
     * @return 値引９金額
     */
    public String  getU26NebikiKin9() {
        return u26NebikiKin9;
    }
    /**
     * 値引９金額を設定します。
     * @param u26NebikiKin9 値引９金額
     */
    public void setU26NebikiKin9(String  u26NebikiKin9) {
        this.u26NebikiKin9 = u26NebikiKin9;
    }
    
    /**
     * 値引９税額を取得します。
     * @return 値引９税額
     */
    public String  getU27NebikiTax9() {
        return u27NebikiTax9;
    }
    /**
     * 値引９税額を設定します。
     * @param u27NebikiTax9 値引９税額
     */
    public void setU27NebikiTax9(String  u27NebikiTax9) {
        this.u27NebikiTax9 = u27NebikiTax9;
    }
    
    /**
     * 現金金額を取得します。
     * @return
     */
    public String getGenkinKafusoku() {
        return genkinKafusoku;
    }
    /**
     * 現金金額を設定します。
     * @param genkinKafusoku
     */
    public void setGenkinKafusoku(String genkinKafusoku) {
        this.genkinKafusoku = genkinKafusoku;
    }
    
    /**
     * 合計レコードフラグを取得します。
     * @return
     */
    public boolean isTotalFlg() {
        return totalFlg;
    }
    /**
     * 合計レコードフラグを設定します。
     * @param totalFlg
     */
    public void setTotalFlg(boolean totalFlg) {
        this.totalFlg = totalFlg;
    }
    
    /**
     * 新規追加フラグを取得します。
     * @return
     */
    public boolean isInsertFlg() {
        return insertFlg;
    }
    /**
     * 新規追加フラグを設定します。
     * @param insertFlg
     */
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }
    

    //////////////////////////////////////////////// BigDecimal //////////////////////////////////////////////////////////////
    
    
    /**
     * 売上計を取得します。
     * @return 売上計
     */
    public BigDecimal getDecU01Uriage() {
        if(UriMaintenanceCommon.isNull(u01Uriage)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u01Uriage);
    }
    /**
     * 売上計を設定します。
     * @param u01Uriage 売上計
     */
    public void setDecU01Uriage(BigDecimal u01Uriage) {
        this.u01Uriage = String.valueOf(u01Uriage);
    }
    
    /**
     * メニュー売上計を取得します。
     * @return メニュー売上計
     */
    public BigDecimal getDecU02MenuUri() {
        if(UriMaintenanceCommon.isNull(u02MenuUri)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u02MenuUri);
    }
    /**
     * メニュー売上計を設定します。
     * @param u02MenuUri メニュー売上計
     */
    public void setDecU02MenuUri(BigDecimal u02MenuUri) {
        this.u02MenuUri = String.valueOf(u02MenuUri);
    }
    
    /**
     * 物販計を取得します。
     * @return 物販計
     */
    public BigDecimal getDecU03BuppanUri() {
        if(UriMaintenanceCommon.isNull(u03BuppanUri)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u03BuppanUri);
    }
    /**
     * 物販計を設定します。
     * @param u03BuppanUri 物販計
     */
    public void setDecU03BuppanUri(BigDecimal u03BuppanUri) {
        this.u03BuppanUri = String.valueOf(u03BuppanUri);
    }
    
    /**
     * 値引計を取得します。
     * @return 値引計
     */
    public BigDecimal getDecU04Nebiki() {
        if(UriMaintenanceCommon.isNull(u04Nebiki)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u04Nebiki);
    }
    /**
     * 値引計を設定します。
     * @param u04Nebiki 値引計
     */
    public void setDecU04Nebiki(BigDecimal u04Nebiki) {
        this.u04Nebiki = String.valueOf(u04Nebiki);
    }
    
    /**
     * 値引後売上を取得します。
     * @return 値引後売上
     */
    public BigDecimal getDecU05Nebikigo() {
        if(UriMaintenanceCommon.isNull(u05Nebikigo)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u05Nebikigo);
    }
    /**
     * 値引後売上を設定します。
     * @param u05Nebikigo 値引後売上
     */
    public void setDecU05Nebikigo(BigDecimal u05Nebikigo) {
        this.u05Nebikigo = String.valueOf(u05Nebikigo);
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public BigDecimal getDecU06Tax() {
        if(UriMaintenanceCommon.isNull(u06Tax)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u06Tax);
    }
    /**
     * 消費税を設定します。
     * @param u06Tax 消費税
     */
    public void setDecU06Tax(BigDecimal u06Tax) {
        this.u06Tax = String.valueOf(u06Tax);
    }
    
    /**
     * マイナス商品件数を取得します。
     * @return マイナス商品件数
     */
    public BigDecimal getDecU07MinusKen() {
        if(UriMaintenanceCommon.isNull(u07MinusKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u07MinusKen);
    }
    /**
     * マイナス商品件数を設定します。
     * @param u07MinusKen マイナス商品件数
     */
    public void setDecU07MinusKen(BigDecimal u07MinusKen) {
        this.u07MinusKen = String.valueOf(u07MinusKen);
    }
    
    /**
     * マイナス商品金額を取得します。
     * @return マイナス商品金額
     */
    public BigDecimal getDecU08MinusKin() {
        if(UriMaintenanceCommon.isNull(u08MinusKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u08MinusKin);
    }
    /**
     * マイナス商品金額を設定します。
     * @param u08MinusKin マイナス商品金額
     */
    public void setDecU08MinusKin(BigDecimal u08MinusKin) {
        this.u08MinusKin = String.valueOf(u08MinusKin);
    }
    
    /**
     * 値引件数を取得します。
     * @return 値引件数
     */
    public BigDecimal getDecU09NebikiKen() {
        if(UriMaintenanceCommon.isNull(u09NebikiKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u09NebikiKen);
    }
    /**
     * 値引件数を設定します。
     * @param u09NebikiKen 値引件数
     */
    public void setDecU09NebikiKen(BigDecimal u09NebikiKen) {
        this.u09NebikiKen = String.valueOf(u09NebikiKen);
    }
    
    /**
     * 値引金額を取得します。
     * @return 値引金額
     */
    public BigDecimal getDecU10NebikiKin() {
        if(UriMaintenanceCommon.isNull(u10NebikiKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u10NebikiKin);
    }
    /**
     * 値引金額を設定します。
     * @param u10NebikiKin 値引金額
     */
    public void setDecU10NebikiKin(BigDecimal u10NebikiKin) {
        this.u10NebikiKin = String.valueOf(u10NebikiKin);
    }
    
    /**
     * ％値引件数を取得します。
     * @return ％値引件数
     */
    public BigDecimal getDecU11PNebikiKen() {
        if(UriMaintenanceCommon.isNull(u11PNebikiKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u11PNebikiKen);
    }
    /**
     * ％値引件数を設定します。
     * @param u11PNebikiKen ％値引件数
     */
    public void setDecU11PNebikiKen(BigDecimal u11PNebikiKen) {
        this.u11PNebikiKen = String.valueOf(u11PNebikiKen);
    }
    
    /**
     * ％値引金額を取得します。
     * @return ％値引金額
     */
    public BigDecimal getDecU12PNebikiKin() {
        if(UriMaintenanceCommon.isNull(u12PNebikiKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u12PNebikiKin);
    }
    /**
     * ％値引金額を設定します。
     * @param u12PNebikiKin ％値引金額
     */
    public void setDecU12PNebikiKin(BigDecimal u12PNebikiKin) {
        this.u12PNebikiKin = String.valueOf(u12PNebikiKin);
    }
    
    /**
     * 現金件数を取得します。
     * @return 現金件数
     */
    public BigDecimal getDecU13GenkinKen() {
        if(UriMaintenanceCommon.isNull(u13GenkinKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u13GenkinKen);
    }
    /**
     * 現金件数を設定します。
     * @param u13GenkinKen 現金件数
     */
    public void setDecU13GenkinKen(BigDecimal u13GenkinKen) {
        this.u13GenkinKen = String.valueOf(u13GenkinKen);
    }
    
    /**
     * 現金を取得します。
     * @return 現金
     */
    public BigDecimal getDecU14GenkinKin() {
        if(UriMaintenanceCommon.isNull(u14GenkinKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u14GenkinKin);
    }
    /**
     * 現金を設定します。
     * @param u14GenkinKin 現金
     */
    public void setDecU14GenkinKin(BigDecimal u14GenkinKin) {
        this.u14GenkinKin = String.valueOf(u14GenkinKin);
    }
    
    /**
     * 会計区分２件数を取得します。
     * @return 会計区分２件数
     */
    public BigDecimal getDecU15KaikeiKen2() {
        if(UriMaintenanceCommon.isNull(u15KaikeiKen2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u15KaikeiKen2);
    }
    /**
     * 会計区分２件数を設定します。
     * @param u15KaikeiKen2 会計区分２件数
     */
    public void setDecU15KaikeiKen2(BigDecimal u15KaikeiKen2) {
        this.u15KaikeiKen2 = String.valueOf(u15KaikeiKen2);
    }
    
    /**
     * 会計区分２金額を取得します。
     * @return 会計区分２金額
     */
    public BigDecimal getDecU16KaikeiKin2() {
        if(UriMaintenanceCommon.isNull(u16KaikeiKin2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u16KaikeiKin2);
    }
    /**
     * 会計区分２金額を設定します。
     * @param u16KaikeiKin2 会計区分２金額
     */
    public void setDecU16KaikeiKin2(BigDecimal u16KaikeiKin2) {
        this.u16KaikeiKin2 = String.valueOf(u16KaikeiKin2);
    }
    
    /**
     * 会計区分３件数を取得します。
     * @return 会計区分３件数
     */
    public BigDecimal getDecU17KaikeiKen3() {
        if(UriMaintenanceCommon.isNull(u17KaikeiKen3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u17KaikeiKen3);
    }
    /**
     * 会計区分３件数を設定します。
     * @param u17KaikeiKen3 会計区分３件数
     */
    public void setDecU17KaikeiKen3(BigDecimal u17KaikeiKen3) {
        this.u17KaikeiKen3 = String.valueOf(u17KaikeiKen3);
    }
    
    /**
     * 会計区分３金額を取得します。
     * @return 会計区分３金額
     */
    public BigDecimal getDecU18KaikeiKin3() {
        if(UriMaintenanceCommon.isNull(u18KaikeiKin3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u18KaikeiKin3);
    }
    /**
     * 会計区分３金額を設定します。
     * @param u18KaikeiKin3 会計区分３金額
     */
    public void setDecU18KaikeiKin3(BigDecimal u18KaikeiKin3) {
        this.u18KaikeiKin3 = String.valueOf(u18KaikeiKin3);
    }
    
    /**
     * 会計区分４件数を取得します。
     * @return 会計区分４件数
     */
    public BigDecimal getDecU19KaikeiKen4() {
        if(UriMaintenanceCommon.isNull(u19KaikeiKen4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u19KaikeiKen4);
    }
    /**
     * 会計区分４件数を設定します。
     * @param u19KaikeiKen4 会計区分４件数
     */
    public void setDecU19KaikeiKen4(BigDecimal u19KaikeiKen4) {
        this.u19KaikeiKen4 = String.valueOf(u19KaikeiKen4);
    }
    
    /**
     * 会計区分４金額を取得します。
     * @return 会計区分４金額
     */
    public BigDecimal getDecU20KaikeiKin4() {
        if(UriMaintenanceCommon.isNull(u20KaikeiKin4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u20KaikeiKin4);
    }
    /**
     * 会計区分４金額を設定します。
     * @param u20KaikeiKin4 会計区分４金額
     */
    public void setDecU20KaikeiKin4(BigDecimal u20KaikeiKin4) {
        this.u20KaikeiKin4 = String.valueOf(u20KaikeiKin4);
    }
    
    /**
     * 会計区分５件数を取得します。
     * @return 会計区分５件数
     */
    public BigDecimal getDecU21KaikeiKen5() {
        if(UriMaintenanceCommon.isNull(u21KaikeiKen5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u21KaikeiKen5);
    }
    /**
     * 会計区分５件数を設定します。
     * @param u21KaikeiKen5 会計区分５件数
     */
    public void setDecU21KaikeiKen5(BigDecimal u21KaikeiKen5) {
        this.u21KaikeiKen5 = String.valueOf(u21KaikeiKen5);
    }
    
    /**
     * 会計区分５金額を取得します。
     * @return 会計区分５金額
     */
    public BigDecimal getDecU22KaikeiKin5() {
        if(UriMaintenanceCommon.isNull(u22KaikeiKin5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u22KaikeiKin5);
    }
    /**
     * 会計区分５金額を設定します。
     * @param u22KaikeiKin5 会計区分５金額
     */
    public void setDecU22KaikeiKin5(BigDecimal u22KaikeiKin5) {
        this.u22KaikeiKin5 = String.valueOf(u22KaikeiKin5);
    }
    
    /**
     * 会計区分６件数を取得します。
     * @return 会計区分６件数
     */
    public BigDecimal getDecU23KaikeiKen6() {
        if(UriMaintenanceCommon.isNull(u23KaikeiKen6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u23KaikeiKen6);
    }
    /**
     * 会計区分６件数を設定します。
     * @param u23KaikeiKen6 会計区分６件数
     */
    public void setDecU23KaikeiKen6(BigDecimal u23KaikeiKen6) {
        this.u23KaikeiKen6 = String.valueOf(u23KaikeiKen6);
    }
    
    /**
     * 会計区分６金額を取得します。
     * @return 会計区分６金額
     */
    public BigDecimal getDecU24KaikeiKin6() {
        if(UriMaintenanceCommon.isNull(u24KaikeiKin6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u24KaikeiKin6);
    }
    /**
     * 会計区分６金額を設定します。
     * @param u24KaikeiKin6 会計区分６金額
     */
    public void setDecU24KaikeiKin6(BigDecimal u24KaikeiKin6) {
        this.u24KaikeiKin6 = String.valueOf(u24KaikeiKin6);
    }
    
    /**
     * 会計区分７件数を取得します。
     * @return 会計区分７件数
     */
    public BigDecimal getDecU25KaikeiKen7() {
        if(UriMaintenanceCommon.isNull(u25KaikeiKen7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u25KaikeiKen7);
    }
    /**
     * 会計区分７件数を設定します。
     * @param u25KaikeiKen7 会計区分７件数
     */
    public void setDecU25KaikeiKen7(BigDecimal u25KaikeiKen7) {
        this.u25KaikeiKen7 = String.valueOf(u25KaikeiKen7);
    }
    
    /**
     * 会計区分７金額を取得します。
     * @return 会計区分７金額
     */
    public BigDecimal getDecU26KaikeiKin7() {
        if(UriMaintenanceCommon.isNull(u26KaikeiKin7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u26KaikeiKin7);
    }
    /**
     * 会計区分７金額を設定します。
     * @param u26KaikeiKin7 会計区分７金額
     */
    public void setDecU26KaikeiKin7(BigDecimal u26KaikeiKin7) {
        this.u26KaikeiKin7 = String.valueOf(u26KaikeiKin7);
    }
    
    /**
     * 会計区分８件数を取得します。
     * @return 会計区分８件数
     */
    public BigDecimal getDecU27KaikeiKen8() {
        if(UriMaintenanceCommon.isNull(u27KaikeiKen8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u27KaikeiKen8);
    }
    /**
     * 会計区分８件数を設定します。
     * @param u27KaikeiKen8 会計区分８件数
     */
    public void setDecU27KaikeiKen8(BigDecimal u27KaikeiKen8) {
        this.u27KaikeiKen8 = String.valueOf(u27KaikeiKen8);
    }
    
    /**
     * 会計区分８金額を取得します。
     * @return 会計区分８金額
     */
    public BigDecimal getDecU28KaikeiKin8() {
        if(UriMaintenanceCommon.isNull(u28KaikeiKin8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u28KaikeiKin8);
    }
    /**
     * 会計区分８金額を設定します。
     * @param u28KaikeiKin8 会計区分８金額
     */
    public void setDecU28KaikeiKin8(BigDecimal u28KaikeiKin8) {
        this.u28KaikeiKin8 = String.valueOf(u28KaikeiKin8);
    }
    
    /**
     * 会計区分９件数を取得します。
     * @return 会計区分９件数
     */
    public BigDecimal getDecU29KaikeiKen9() {
        if(UriMaintenanceCommon.isNull(u29KaikeiKen9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u29KaikeiKen9);
    }
    /**
     * 会計区分９件数を設定します。
     * @param u29KaikeiKen9 会計区分９件数
     */
    public void setDecU29KaikeiKen9(BigDecimal u29KaikeiKen9) {
        this.u29KaikeiKen9 = String.valueOf(u29KaikeiKen9);
    }
    
    /**
     * 会計区分９金額を取得します。
     * @return 会計区分９金額
     */
    public BigDecimal getDecU30KaikeiKin9() {
        if(UriMaintenanceCommon.isNull(u30KaikeiKin9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u30KaikeiKin9);
    }
    /**
     * 会計区分９金額を設定します。
     * @param u30KaikeiKin9 会計区分９金額
     */
    public void setDecU30KaikeiKin9(BigDecimal u30KaikeiKin9) {
        this.u30KaikeiKin9 = String.valueOf(u30KaikeiKin9);
    }
    
    /**
     * 会計区分１０件数を取得します。
     * @return 会計区分１０件数
     */
    public BigDecimal getDecU31KaikeiKen10() {
        if(UriMaintenanceCommon.isNull(u31KaikeiKen10)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u31KaikeiKen10);
    }
    /**
     * 会計区分１０件数を設定します。
     * @param u31KaikeiKen10 会計区分１０件数
     */
    public void setDecU31KaikeiKen10(BigDecimal u31KaikeiKen10) {
        this.u31KaikeiKen10 = String.valueOf(u31KaikeiKen10);
    }
    
    /**
     * 会計区分１０金額を取得します。
     * @return 会計区分１０金額
     */
    public BigDecimal getDecU32KaikeiKin10() {
        if(UriMaintenanceCommon.isNull(u32KaikeiKin10)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u32KaikeiKin10);
    }
    /**
     * 会計区分１０金額を設定します。
     * @param u32KaikeiKin10 会計区分１０金額
     */
    public void setDecU32KaikeiKin10(BigDecimal u32KaikeiKin10) {
        this.u32KaikeiKin10 = String.valueOf(u32KaikeiKin10);
    }
    
    /**
     * 会計区分１１件数を取得します。
     * @return 会計区分１１件数
     */
    public BigDecimal getDecU33KaikeiKen11() {
        if(UriMaintenanceCommon.isNull(u33KaikeiKen11)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u33KaikeiKen11);
    }
    /**
     * 会計区分１１件数を設定します。
     * @param u33KaikeiKen11 会計区分１１件数
     */
    public void setDecU33KaikeiKen11(BigDecimal u33KaikeiKen11) {
        this.u33KaikeiKen11 = String.valueOf(u33KaikeiKen11);
    }
    
    /**
     * 会計区分１１金額を取得します。
     * @return 会計区分１１金額
     */
    public BigDecimal getDecU34KaikeiKin11() {
        if(UriMaintenanceCommon.isNull(u34KaikeiKin11)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u34KaikeiKin11);
    }
    /**
     * 会計区分１１金額を設定します。
     * @param u34KaikeiKin11 会計区分１１金額
     */
    public void setDecU34KaikeiKin11(BigDecimal u34KaikeiKin11) {
        this.u34KaikeiKin11 = String.valueOf(u34KaikeiKin11);
    }
    
    /**
     * チケット１販売件数を取得します。
     * @return チケット１販売件数
     */
    public BigDecimal getDecU35TieketKen1() {
        if(UriMaintenanceCommon.isNull(u35TieketKen1)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u35TieketKen1);
    }
    /**
     * チケット１販売件数を設定します。
     * @param u35TieketKen1 チケット１販売件数
     */
    public void setDecU35TieketKen1(BigDecimal u35TieketKen1) {
        this.u35TieketKen1 = String.valueOf(u35TieketKen1);
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public BigDecimal getDecU36TieketKin1() {
        if(UriMaintenanceCommon.isNull(u36TieketKin1)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u36TieketKin1);
    }
    /**
     * チケット１販売金額を設定します。
     * @param u36TieketKin1 チケット１販売金額
     */
    public void setDecU36TieketKin1(BigDecimal u36TieketKin1) {
        this.u36TieketKin1 = String.valueOf(u36TieketKin1);
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public BigDecimal getDecU37TieketKen2() {
        if(UriMaintenanceCommon.isNull(u37TieketKen2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u37TieketKen2);
    }
    /**
     * チケット２販売件数を設定します。
     * @param u37TieketKen2 チケット２販売件数
     */
    public void setDecU37TieketKen2(BigDecimal u37TieketKen2) {
        this.u37TieketKen2 = String.valueOf(u37TieketKen2);
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public BigDecimal getDecU38TieketKin2() {
        if(UriMaintenanceCommon.isNull(u38TieketKin2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u38TieketKin2);
    }
    /**
     * チケット２販売金額を設定します。
     * @param u38TieketKin2 チケット２販売金額
     */
    public void setDecU38TieketKin2(BigDecimal u38TieketKin2) {
        this.u38TieketKin2 = String.valueOf(u38TieketKin2);
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public BigDecimal getDecU39TieketKen3() {
        if(UriMaintenanceCommon.isNull(u39TieketKen3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u39TieketKen3);
    }
    /**
     * チケット３販売件数を設定します。
     * @param u39TieketKen3 チケット３販売件数
     */
    public void setDecU39TieketKen3(BigDecimal u39TieketKen3) {
        this.u39TieketKen3 = String.valueOf(u39TieketKen3);
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public BigDecimal getDecU40TieketKin3() {
        if(UriMaintenanceCommon.isNull(u40TieketKin3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u40TieketKin3);
    }
    /**
     * チケット３販売金額を設定します。
     * @param u40TieketKin3 チケット３販売金額
     */
    public void setDecU40TieketKin3(BigDecimal u40TieketKin3) {
        this.u40TieketKin3 = String.valueOf(u40TieketKin3);
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public BigDecimal getDecU41TieketKen4() {
        if(UriMaintenanceCommon.isNull(u41TieketKen4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u41TieketKen4);
    }
    /**
     * チケット４販売件数を設定します。
     * @param u41TieketKen4 チケット４販売件数
     */
    public void setDecU41TieketKen4(BigDecimal u41TieketKen4) {
        this.u41TieketKen4 = String.valueOf(u41TieketKen4);
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public BigDecimal getDecU42TieketKin4() {
        if(UriMaintenanceCommon.isNull(u42TieketKin4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u42TieketKin4);
    }
    /**
     * チケット４販売金額を設定します。
     * @param u42TieketKin4 チケット４販売金額
     */
    public void setDecU42TieketKin4(BigDecimal u42TieketKin4) {
        this.u42TieketKin4 = String.valueOf(u42TieketKin4);
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public BigDecimal getDecU43TieketKen5() {
        if(UriMaintenanceCommon.isNull(u43TieketKen5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u43TieketKen5);
    }
    /**
     * チケット５販売件数を設定します。
     * @param u43TieketKen5 チケット５販売件数
     */
    public void setDecU43TieketKen5(BigDecimal u43TieketKen5) {
        this.u43TieketKen5 = String.valueOf(u43TieketKen5);
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public BigDecimal getDecU44TieketKin5() {
        if(UriMaintenanceCommon.isNull(u44TieketKin5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u44TieketKin5);
    }
    /**
     * チケット５販売金額を設定します。
     * @param u44TieketKin5 チケット５販売金額
     */
    public void setDecU44TieketKin5(BigDecimal u44TieketKin5) {
        this.u44TieketKin5 = String.valueOf(u44TieketKin5);
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public BigDecimal getDecU45TieketKen6() {
        if(UriMaintenanceCommon.isNull(u45TieketKen6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u45TieketKen6);
    }
    /**
     * チケット６販売件数を設定します。
     * @param u45TieketKen6 チケット６販売件数
     */
    public void setDecU45TieketKen6(BigDecimal u45TieketKen6) {
        this.u45TieketKen6 = String.valueOf(u45TieketKen6);
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public BigDecimal getDecU46TieketKin6() {
        if(UriMaintenanceCommon.isNull(u46TieketKin6)) {
            return new BigDecimal(0);
        }        
        return new BigDecimal(u46TieketKin6);
    }
    /**
     * チケット６販売金額を設定します。
     * @param u46TieketKin6 チケット６販売金額
     */
    public void setDecU46TieketKin6(BigDecimal u46TieketKin6) {
        this.u46TieketKin6 = String.valueOf(u46TieketKin6);
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public BigDecimal getDecU47TieketKen7() {
        if(UriMaintenanceCommon.isNull(u47TieketKen7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u47TieketKen7);
    }
    /**
     * チケット７販売件数を設定します。
     * @param u47TieketKen7 チケット７販売件数
     */
    public void setDecU47TieketKen7(BigDecimal u47TieketKen7) {
        this.u47TieketKen7 = String.valueOf(u47TieketKen7);
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public BigDecimal getDecU48TieketKin7() {
        if(UriMaintenanceCommon.isNull(u48TieketKin7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u48TieketKin7);
    }
    /**
     * チケット７販売金額を設定します。
     * @param u48TieketKin7 チケット７販売金額
     */
    public void setDecU48TieketKin7(BigDecimal u48TieketKin7) {
        this.u48TieketKin7 = String.valueOf(u48TieketKin7);
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public BigDecimal getDecU49TieketKen8() {
        if(UriMaintenanceCommon.isNull(u49TieketKen8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u49TieketKen8);
    }
    /**
     * チケット８販売件数を設定します。
     * @param u49TieketKen8 チケット８販売件数
     */
    public void setDecU49TieketKen8(BigDecimal u49TieketKen8) {
        this.u49TieketKen8 = String.valueOf(u49TieketKen8);
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public BigDecimal getDecU50TieketKin8() {
        if(UriMaintenanceCommon.isNull(u50TieketKin8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u50TieketKin8);
    }
    /**
     * チケット８販売金額を設定します。
     * @param u50TieketKin8 チケット８販売金額
     */
    public void setDecU50TieketKin8(BigDecimal u50TieketKin8) {
        this.u50TieketKin8 = String.valueOf(u50TieketKin8);
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public BigDecimal getDecU51TieketKen9() {
        if(UriMaintenanceCommon.isNull(u51TieketKen9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u51TieketKen9);
    }
    /**
     * チケット９販売件数を設定します。
     * @param u51TieketKen9 チケット９販売件数
     */
    public void setDecU51TieketKen9(BigDecimal u51TieketKen9) {
        this.u51TieketKen9 = String.valueOf(u51TieketKen9);
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public BigDecimal getDecU52TieketKin9() {
        if(UriMaintenanceCommon.isNull(u52TieketKin9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u52TieketKin9);
    }
    /**
     * チケット９販売金額を設定します。
     * @param u52TieketKin9 チケット９販売金額
     */
    public void setDecU52TieketKin9(BigDecimal u52TieketKin9) {
        this.u52TieketKin9 = String.valueOf(u52TieketKin9);
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public BigDecimal getDecU53TieketKen10() {
        if(UriMaintenanceCommon.isNull(u53TieketKen10)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u53TieketKen10);
    }
    /**
     * チケット１０販売件数を設定します。
     * @param u53TieketKen10 チケット１０販売件数
     */
    public void setDecU53TieketKen10(BigDecimal u53TieketKen10) {
        this.u53TieketKen10 = String.valueOf(u53TieketKen10);
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public BigDecimal getDecU54TieketKin10() {
        if(UriMaintenanceCommon.isNull(u54TieketKin10)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u54TieketKin10);
    }
    /**
     * チケット１０販売金額を設定します。
     * @param u54TieketKin10 チケット１０販売金額
     */
    public void setDecU54TieketKin10(BigDecimal u54TieketKin10) {
        this.u54TieketKin10 = String.valueOf(u54TieketKin10);
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public BigDecimal getDecU55TieketKen11() {
        if(UriMaintenanceCommon.isNull(u55TieketKen11)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u55TieketKen11);
    }
    /**
     * チケット１１販売件数を設定します。
     * @param u55TieketKen11 チケット１１販売件数
     */
    public void setDecU55TieketKen11(BigDecimal u55TieketKen11) {
        this.u55TieketKen11 = String.valueOf(u55TieketKen11);
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public BigDecimal getDecU56TieketKin11() {
        if(UriMaintenanceCommon.isNull(u56TieketKin11)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u56TieketKin11);
    }
    /**
     * チケット１１販売金額を設定します。
     * @param u56TieketKin11 チケット１１販売金額
     */
    public void setDecU56TieketKin11(BigDecimal u56TieketKin11) {
        this.u56TieketKin11 = String.valueOf(u56TieketKin11);
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public BigDecimal getDecU57TieketKen12() {
        if(UriMaintenanceCommon.isNull(u57TieketKen12)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u57TieketKen12);
    }
    /**
     * チケット１２販売件数を設定します。
     * @param u57TieketKen12 チケット１２販売件数
     */
    public void setDecU57TieketKen12(BigDecimal u57TieketKen12) {
        this.u57TieketKen12 = String.valueOf(u57TieketKen12);
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public BigDecimal getDecU58TieketKin12() {
        if(UriMaintenanceCommon.isNull(u58TieketKin12)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u58TieketKin12);
    }
    /**
     * チケット１２販売金額を設定します。
     * @param u58TieketKin12 チケット１２販売金額
     */
    public void setDecU58TieketKin12(BigDecimal u58TieketKin12) {
        this.u58TieketKin12 = String.valueOf(u58TieketKin12);
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public BigDecimal getDecU59TieketKen13() {
        if(UriMaintenanceCommon.isNull(u59TieketKen13)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u59TieketKen13);
    }
    /**
     * チケット１３販売件数を設定します。
     * @param u59TieketKen13 チケット１３販売件数
     */
    public void setDecU59TieketKen13(BigDecimal u59TieketKen13) {
        this.u59TieketKen13 = String.valueOf(u59TieketKen13);
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public BigDecimal getDecU60TieketKin13() {
        if(UriMaintenanceCommon.isNull(u60TieketKin13)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u60TieketKin13);
    }
    /**
     * チケット１３販売金額を設定します。
     * @param u60TieketKin13 チケット１３販売金額
     */
    public void setDecU60TieketKin13(BigDecimal u60TieketKin13) {
        this.u60TieketKin13 = String.valueOf(u60TieketKin13);
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public BigDecimal getDecU61TieketKen14() {
        if(UriMaintenanceCommon.isNull(u61TieketKen14)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u61TieketKen14);
    }
    /**
     * チケット１４販売件数を設定します。
     * @param u61TieketKen14 チケット１４販売件数
     */
    public void setDecU61TieketKen14(BigDecimal u61TieketKen14) {
        this.u61TieketKen14 = String.valueOf(u61TieketKen14);
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public BigDecimal getDecU62TieketKin14() {
        if(UriMaintenanceCommon.isNull(u62TieketKin14)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u62TieketKin14);
    }
    /**
     * チケット１４販売金額を設定します。
     * @param u62TieketKin14 チケット１４販売金額
     */
    public void setDecU62TieketKin14(BigDecimal u62TieketKin14) {
        this.u62TieketKin14 = String.valueOf(u62TieketKin14);
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public BigDecimal getDecU63TieketKen15() {
        if(UriMaintenanceCommon.isNull(u63TieketKen15)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u63TieketKen15);
    }
    /**
     * チケット１５販売件数を設定します。
     * @param u63TieketKen15 チケット１５販売件数
     */
    public void setDecU63TieketKen15(BigDecimal u63TieketKen15) {
        this.u63TieketKen15 = String.valueOf(u63TieketKen15);
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public BigDecimal getDecU64TieketKin15() {
        if(UriMaintenanceCommon.isNull(u64TieketKin15)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u64TieketKin15);
    }
    /**
     * チケット１５販売金額を設定します。
     * @param u64TieketKin15 チケット１５販売金額
     */
    public void setDecU64TieketKin15(BigDecimal u64TieketKin15) {
        this.u64TieketKin15 = String.valueOf(u64TieketKin15);
    }
    
    /**
     * 入金金額を取得します。
     * @return 入金金額
     */
    public BigDecimal getDecU65Nyukin() {
        if(UriMaintenanceCommon.isNull(u65Nyukin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u65Nyukin);
    }
    /**
     * 入金金額を設定します。
     * @param u65Nyukin 入金金額
     */
    public void setDecU65Nyukin(BigDecimal u65Nyukin) {
        this.u65Nyukin = String.valueOf(u65Nyukin);
    }
    
    /**
     * 出金金額を取得します。
     * @return 出金金額
     */
    public BigDecimal getDecU66Shukin() {
        if(UriMaintenanceCommon.isNull(u66Shukin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u66Shukin);
    }
    /**
     * 出金金額を設定します。
     * @param u66Shukin 出金金額
     */
    public void setDecU66Shukin(BigDecimal u66Shukin) {
        this.u66Shukin = String.valueOf(u66Shukin);
    }
    
    /**
     * 計算現金在高を取得します。
     * @return 計算現金在高
     */
    public BigDecimal getDecU67AridakaCal() {
        if(UriMaintenanceCommon.isNull(u67AridakaCal)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u67AridakaCal);
    }
    /**
     * 計算現金在高を設定します。
     * @param u67AridakaCal 計算現金在高
     */
    public void setDecU67AridakaCal(BigDecimal u67AridakaCal) {
        this.u67AridakaCal = String.valueOf(u67AridakaCal);
    }
    
    /**
     * 実現金在高を取得します。
     * @return 実現金在高
     */
    public BigDecimal getDecU68AridakaJitu() {
        if(UriMaintenanceCommon.isNull(u68AridakaJitu)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u68AridakaJitu);
    }
    /**
     * 実現金在高を設定します。
     * @param u68AridakaJitu 実現金在高
     */
    public void setDecU68AridakaJitu(BigDecimal u68AridakaJitu) {
        this.u68AridakaJitu = String.valueOf(u68AridakaJitu);
    }
    
    /**
     * 過剰金額を取得します。
     * @return 過剰金額
     */
    public BigDecimal getDecU69Kajou() {
        if(UriMaintenanceCommon.isNull(u69Kajou)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u69Kajou);
    }
    /**
     * 過剰金額を設定します。
     * @param u69Kajou 過剰金額
     */
    public void setDecU69Kajou(BigDecimal u69Kajou) {
        this.u69Kajou = String.valueOf(u69Kajou);
    }
    
    /**
     * 不足金額を取得します。
     * @return 不足金額
     */
    public BigDecimal getDecU70Fusoku() {
        if(UriMaintenanceCommon.isNull(u70Fusoku)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u70Fusoku);
    }
    /**
     * 不足金額を設定します。
     * @param u70Fusoku 不足金額
     */
    public void setDecU70Fusoku(BigDecimal u70Fusoku) {
        this.u70Fusoku = String.valueOf(u70Fusoku);
    }
    
    /**
     * 取消件数を取得します。
     * @return 取消件数
     */
    public BigDecimal getDecU71CanKen() {
        if(UriMaintenanceCommon.isNull(u71CanKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u71CanKen);
    }
    /**
     * 取消件数を設定します。
     * @param u71CanKen 取消件数
     */
    public void setDecU71CanKen(BigDecimal u71CanKen) {
        this.u71CanKen = String.valueOf(u71CanKen);
    }
    
    /**
     * 取消金額を取得します。
     * @return 取消金額
     */
    public BigDecimal getDecU72CanKin() {
        if(UriMaintenanceCommon.isNull(u72CanKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u72CanKin);
    }
    /**
     * 取消金額を設定します。
     * @param u72CanKin 取消金額
     */
    public void setDecU72CanKin(BigDecimal u72CanKin) {
        this.u72CanKin = String.valueOf(u72CanKin);
    }
    
    /**
     * 両替回数を取得します。
     * @return 両替回数
     */
    public BigDecimal getDecU73ChengeCnt() {
        if(UriMaintenanceCommon.isNull(u73ChengeCnt)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u73ChengeCnt);
    }
    /**
     * 両替回数を設定します。
     * @param u73ChengeCnt 両替回数
     */
    public void setDecU73ChengeCnt(BigDecimal u73ChengeCnt) {
        this.u73ChengeCnt = String.valueOf(u73ChengeCnt);
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getDecU74Kyakusu() {
        if(UriMaintenanceCommon.isNull(u74Kyakusu)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u74Kyakusu);
    }
    /**
     * 客数を設定します。
     * @param u74Kyakusu 客数
     */
    public void setDecU74Kyakusu(BigDecimal u74Kyakusu) {
        this.u74Kyakusu = String.valueOf(u74Kyakusu);
    }
    
    /**
     * オールキャンセル回数を取得します。
     * @return オールキャンセル回数
     */
    public BigDecimal getDecU75AllcanKen() {
        if(UriMaintenanceCommon.isNull(u75AllcanKen)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u75AllcanKen);
    }
    /**
     * オールキャンセル回数を設定します。
     * @param u75AllcanKen オールキャンセル回数
     */
    public void setDecU75AllcanKen(BigDecimal u75AllcanKen) {
        this.u75AllcanKen = String.valueOf(u75AllcanKen);
    }
    
    /**
     * オールキャンセル金額を取得します。
     * @return オールキャンセル金額
     */
    public BigDecimal getDecU76AllcanKin() {
        if(UriMaintenanceCommon.isNull(u76AllcanKin)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u76AllcanKin);
    }
    /**
     * オールキャンセル金額を設定します。
     * @param u76AllcanKin オールキャンセル金額
     */
    public void setDecU76AllcanKin(BigDecimal u76AllcanKin) {
        this.u76AllcanKin = String.valueOf(u76AllcanKin);
    }
    
    /**
     * 値引１件数を取得します。
     * @return 値引１件数
     */
    public BigDecimal getDecU01NebikiKen1() {
        if(UriMaintenanceCommon.isNull(u01NebikiKen1)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u01NebikiKen1);
    }
    /**
     * 値引１件数を設定します。
     * @param u01NebikiKen1 値引１件数
     */
    public void setDecU01NebikiKen1(BigDecimal u01NebikiKen1) {
        this.u01NebikiKen1 = String.valueOf(u01NebikiKen1);
    }
    
    /**
     * 値引１金額を取得します。
     * @return 値引１金額
     */
    public BigDecimal getDecU02NebikiKin1() {
        if(UriMaintenanceCommon.isNull(u02NebikiKin1)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u02NebikiKin1);
    }
    /**
     * 値引１金額を設定します。
     * @param u02NebikiKin1 値引１金額
     */
    public void setDecU02NebikiKin1(BigDecimal u02NebikiKin1) {
        this.u02NebikiKin1 = String.valueOf(u02NebikiKin1);
    }
    
    /**
     * 値引１税額を取得します。
     * @return 値引１税額
     */
    public BigDecimal getDecU03NebikiTax1() {
        if(UriMaintenanceCommon.isNull(u03NebikiTax1)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u03NebikiTax1);
    }
    /**
     * 値引１税額を設定します。
     * @param u03NebikiTax1 値引１税額
     */
    public void setDecU03NebikiTax1(BigDecimal u03NebikiTax1) {
        this.u03NebikiTax1 = String.valueOf(u03NebikiTax1);
    }
    
    /**
     * 値引２件数を取得します。
     * @return 値引２件数
     */
    public BigDecimal getDecU04NebikiKen2() {
        if(UriMaintenanceCommon.isNull(u04NebikiKen2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u04NebikiKen2);
    }
    /**
     * 値引２件数を設定します。
     * @param u04NebikiKen2 値引２件数
     */
    public void setDecU04NebikiKen2(BigDecimal u04NebikiKen2) {
        this.u04NebikiKen2 = String.valueOf(u04NebikiKen2);
    }
    
    /**
     * 値引２金額を取得します。
     * @return 値引２金額
     */
    public BigDecimal getDecU05NebikiKin2() {
        if(UriMaintenanceCommon.isNull(u05NebikiKin2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u05NebikiKin2);
    }
    /**
     * 値引２金額を設定します。
     * @param u05NebikiKin2 値引２金額
     */
    public void setDecU05NebikiKin2(BigDecimal u05NebikiKin2) {
        this.u05NebikiKin2 = String.valueOf(u05NebikiKin2);
    }
    
    /**
     * 値引２税額を取得します。
     * @return 値引２税額
     */
    public BigDecimal getDecU06NebikiTax2() {
        if(UriMaintenanceCommon.isNull(u06NebikiTax2)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u06NebikiTax2);
    }
    /**
     * 値引２税額を設定します。
     * @param u06NebikiTax2 値引２税額
     */
    public void setDecU06NebikiTax2(BigDecimal u06NebikiTax2) {
        this.u06NebikiTax2 = String.valueOf(u06NebikiTax2);
    }
    
    /**
     * 値引３件数を取得します。
     * @return 値引３件数
     */
    public BigDecimal getDecU07NebikiKen3() {
        if(UriMaintenanceCommon.isNull(u07NebikiKen3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u07NebikiKen3);
    }
    /**
     * 値引３件数を設定します。
     * @param u07NebikiKen3 値引３件数
     */
    public void setDecU07NebikiKen3(BigDecimal u07NebikiKen3) {
        this.u07NebikiKen3 = String.valueOf(u07NebikiKen3);
    }
    
    /**
     * 値引３金額を取得します。
     * @return 値引３金額
     */
    public BigDecimal getDecU08NebikiKin3() {
        if(UriMaintenanceCommon.isNull(u08NebikiKin3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u08NebikiKin3);
    }
    /**
     * 値引３金額を設定します。
     * @param u08NebikiKin3 値引３金額
     */
    public void setDecU08NebikiKin3(BigDecimal u08NebikiKin3) {
        this.u08NebikiKin3 = String.valueOf(u08NebikiKin3);
    }
    
    /**
     * 値引３税額を取得します。
     * @return 値引３税額
     */
    public BigDecimal getDecU09NebikiTax3() {
        if(UriMaintenanceCommon.isNull(u09NebikiTax3)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u09NebikiTax3);
    }
    /**
     * 値引３税額を設定します。
     * @param u09NebikiTax3 値引３税額
     */
    public void setDecU09NebikiTax3(BigDecimal u09NebikiTax3) {
        this.u09NebikiTax3 = String.valueOf(u09NebikiTax3);
    }
    
    /**
     * 値引４件数を取得します。
     * @return 値引４件数
     */
    public BigDecimal getDecU10NebikiKen4() {
        if(UriMaintenanceCommon.isNull(u10NebikiKen4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u10NebikiKen4);
    }
    /**
     * 値引４件数を設定します。
     * @param u10NebikiKen4 値引４件数
     */
    public void setDecU10NebikiKen4(BigDecimal u10NebikiKen4) {
        this.u10NebikiKen4 = String.valueOf(u10NebikiKen4);
    }
    
    /**
     * 値引４金額を取得します。
     * @return 値引４金額
     */
    public BigDecimal getDecU11NebikiKin4() {
        if(UriMaintenanceCommon.isNull(u11NebikiKin4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u11NebikiKin4);
    }
    /**
     * 値引４金額を設定します。
     * @param u11NebikiKin4 値引４金額
     */
    public void setDecU11NebikiKin4(BigDecimal u11NebikiKin4) {
        this.u11NebikiKin4 = String.valueOf(u11NebikiKin4);
    }
    
    /**
     * 値引４税額を取得します。
     * @return 値引４税額
     */
    public BigDecimal getDecU12NebikiTax4() {
        if(UriMaintenanceCommon.isNull(u12NebikiTax4)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u12NebikiTax4);
    }
    /**
     * 値引４税額を設定します。
     * @param u12NebikiTax4 値引４税額
     */
    public void setDecU12NebikiTax4(BigDecimal u12NebikiTax4) {
        this.u12NebikiTax4 = String.valueOf(u12NebikiTax4);
    }
    
    /**
     * 値引５件数を取得します。
     * @return 値引５件数
     */
    public BigDecimal getDecU13NebikiKen5() {
        if(UriMaintenanceCommon.isNull(u13NebikiKen5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u13NebikiKen5);
    }
    /**
     * 値引５件数を設定します。
     * @param u13NebikiKen5 値引５件数
     */
    public void setDecU13NebikiKen5(BigDecimal u13NebikiKen5) {
        this.u13NebikiKen5 = String.valueOf(u13NebikiKen5);
    }
    
    /**
     * 値引５金額を取得します。
     * @return 値引５金額
     */
    public BigDecimal getDecU14NebikiKin5() {
        if(UriMaintenanceCommon.isNull(u14NebikiKin5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u14NebikiKin5);
    }
    /**
     * 値引５金額を設定します。
     * @param u14NebikiKin5 値引５金額
     */
    public void setDecU14NebikiKin5(BigDecimal u14NebikiKin5) {
        this.u14NebikiKin5 = String.valueOf(u14NebikiKin5);
    }
    
    /**
     * 値引５税額を取得します。
     * @return 値引５税額
     */
    public BigDecimal getDecU15NebikiTax5() {
        if(UriMaintenanceCommon.isNull(u15NebikiTax5)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u15NebikiTax5);
    }
    /**
     * 値引５税額を設定します。
     * @param u15NebikiTax5 値引５税額
     */
    public void setDecU15NebikiTax5(BigDecimal u15NebikiTax5) {
        this.u15NebikiTax5 = String.valueOf(u15NebikiTax5);
    }
    
    /**
     * 値引６件数を取得します。
     * @return 値引６件数
     */
    public BigDecimal getDecU16NebikiKen6() {
        if(UriMaintenanceCommon.isNull(u16NebikiKen6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u16NebikiKen6);
    }
    /**
     * 値引６件数を設定します。
     * @param u16NebikiKen6 値引６件数
     */
    public void setDecU16NebikiKen6(BigDecimal u16NebikiKen6) {
        this.u16NebikiKen6 = String.valueOf(u16NebikiKen6);
    }
    
    /**
     * 値引６金額を取得します。
     * @return 値引６金額
     */
    public BigDecimal getDecU17NebikiKin6() {
        if(UriMaintenanceCommon.isNull(u17NebikiKin6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u17NebikiKin6);
    }
    /**
     * 値引６金額を設定します。
     * @param u17NebikiKin6 値引６金額
     */
    public void setDecU17NebikiKin6(BigDecimal u17NebikiKin6) {
        this.u17NebikiKin6 = String.valueOf(u17NebikiKin6);
    }
    
    /**
     * 値引６税額を取得します。
     * @return 値引６税額
     */
    public BigDecimal getDecU18NebikiTax6() {
        if(UriMaintenanceCommon.isNull(u18NebikiTax6)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u18NebikiTax6);
    }
    /**
     * 値引６税額を設定します。
     * @param u18NebikiTax6 値引６税額
     */
    public void setDecU18NebikiTax6(BigDecimal u18NebikiTax6) {
        this.u18NebikiTax6 = String.valueOf(u18NebikiTax6);
    }
    
    /**
     * 値引７件数を取得します。
     * @return 値引７件数
     */
    public BigDecimal getDecU19NebikiKen7() {
        if(UriMaintenanceCommon.isNull(u19NebikiKen7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u19NebikiKen7);
    }
    /**
     * 値引７件数を設定します。
     * @param u19NebikiKen7 値引７件数
     */
    public void setDecU19NebikiKen7(BigDecimal u19NebikiKen7) {
        this.u19NebikiKen7 = String.valueOf(u19NebikiKen7);
    }
    
    /**
     * 値引７金額を取得します。
     * @return 値引７金額
     */
    public BigDecimal getDecU20NebikiKin7() {
        if(UriMaintenanceCommon.isNull(u20NebikiKin7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u20NebikiKin7);
    }
    /**
     * 値引７金額を設定します。
     * @param u20NebikiKin7 値引７金額
     */
    public void setDecU20NebikiKin7(BigDecimal u20NebikiKin7) {
        this.u20NebikiKin7 = String.valueOf(u20NebikiKin7);
    }
    
    /**
     * 値引７税額を取得します。
     * @return 値引７税額
     */
    public BigDecimal getDecU21NebikiTax7() {
        if(UriMaintenanceCommon.isNull(u21NebikiTax7)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u21NebikiTax7);
    }
    /**
     * 値引７税額を設定します。
     * @param u21NebikiTax7 値引７税額
     */
    public void setDecU21NebikiTax7(BigDecimal u21NebikiTax7) {
        this.u21NebikiTax7 = String.valueOf(u21NebikiTax7);
    }
    
    /**
     * 値引８件数を取得します。
     * @return 値引８件数
     */
    public BigDecimal getDecU22NebikiKen8() {
        if(UriMaintenanceCommon.isNull(u22NebikiKen8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u22NebikiKen8);
    }
    /**
     * 値引８件数を設定します。
     * @param u22NebikiKen8 値引８件数
     */
    public void setDecU22NebikiKen8(BigDecimal u22NebikiKen8) {
        this.u22NebikiKen8 = String.valueOf(u22NebikiKen8);
    }
    
    /**
     * 値引８金額を取得します。
     * @return 値引８金額
     */
    public BigDecimal getDecU23NebikiKin8() {
        if(UriMaintenanceCommon.isNull(u23NebikiKin8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u23NebikiKin8);
    }
    /**
     * 値引８金額を設定します。
     * @param u23NebikiKin8 値引８金額
     */
    public void setDecU23NebikiKin8(BigDecimal u23NebikiKin8) {
        this.u23NebikiKin8 = String.valueOf(u23NebikiKin8);
    }
    
    /**
     * 値引８税額を取得します。
     * @return 値引８税額
     */
    public BigDecimal getDecU24NebikiTax8() {
        if(UriMaintenanceCommon.isNull(u24NebikiTax8)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u24NebikiTax8);
    }
    /**
     * 値引８税額を設定します。
     * @param u24NebikiTax8 値引８税額
     */
    public void setDecU24NebikiTax8(BigDecimal u24NebikiTax8) {
        this.u24NebikiTax8 = String.valueOf(u24NebikiTax8);
    }
    
    /**
     * 値引９件数を取得します。
     * @return 値引９件数
     */
    public BigDecimal getDecU25NebikiKen9() {
        if(UriMaintenanceCommon.isNull(u25NebikiKen9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u25NebikiKen9);
    }
    /**
     * 値引９件数を設定します。
     * @param u25NebikiKen9 値引９件数
     */
    public void setDecU25NebikiKen9(BigDecimal u25NebikiKen9) {
        this.u25NebikiKen9 = String.valueOf(u25NebikiKen9);
    }
    
    /**
     * 値引９金額を取得します。
     * @return 値引９金額
     */
    public BigDecimal getDecU26NebikiKin9() {
        if(UriMaintenanceCommon.isNull(u26NebikiKin9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u26NebikiKin9);
    }
    /**
     * 値引９金額を設定します。
     * @param u26NebikiKin9 値引９金額
     */
    public void setDecU26NebikiKin9(BigDecimal u26NebikiKin9) {
        this.u26NebikiKin9 = String.valueOf(u26NebikiKin9);
    }
    
    /**
     * 値引９税額を取得します。
     * @return 値引９税額
     */
    public BigDecimal getDecU27NebikiTax9() {
        if(UriMaintenanceCommon.isNull(u27NebikiTax9)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(u27NebikiTax9);
    }
    /**
     * 値引９税額を設定します。
     * @param u27NebikiTax9 値引９税額
     */
    public void setDecU27NebikiTax9(BigDecimal u27NebikiTax9) {
        this.u27NebikiTax9 = String.valueOf(u27NebikiTax9);
    }
    
    /**
     * フラグ１を取得する
     * @return
     */
    public String getFlg1() {
        return flg1;
    }
    /**
     * フラグ１を設定する
     * @param flg1
     */
    public void setFlg1(String flg1) {
        this.flg1 = flg1;
    }
    /**
     * フラグ２を取得する
     * @return
     */
    public String getFlg2() {
        return flg2;
    }
    /**
     * フラグ２を設定する
     * @param flg2
     */
    public void setFlg2(String flg2) {
        this.flg2 = flg2;
    }
    /**
     * フラグ３を取得する
     * @return
     */
    public String getFlg3() {
        return flg3;
    }
    /**
     * フラグ３を設定する
     * @param flg3
     */
    public void setFlg3(String flg3) {
        this.flg3 = flg3;
    }
    
    /**
     * 取引修正TBLのLastTmspを取得する
     * @return
     */
    public Timestamp getLastTmsp96() {
        return lastTmsp96;
    }
    /**
     * 取引修正TBLのLastTmspを設定する
     * @param lastTmsp96
     */
    public void setLastTmsp96(Timestamp lastTmsp96) {
        this.lastTmsp96 = lastTmsp96;
    }
    /**
     * 現金在高日次修正TBLのLastTmspを取得する
     * @return
     */
    public Timestamp getLastTmsp97() {
        return lastTmsp97;
    }
    /**
     * 現金在高日次修正TBL LastTmspを設定する
     * @param lastTmsp97
     */
    public void setLastTmsp97(Timestamp lastTmsp97) {
        this.lastTmsp97 = lastTmsp97;
    }
    
    /**
     * 現金過不足を取得します。
     * @return 現金過不足
     */
    public BigDecimal getDecGenkinKafusoku() {
        if(UriMaintenanceCommon.isNull(genkinKafusoku)) {
            return new BigDecimal(0);
        }
        return new BigDecimal(genkinKafusoku);
    }
    /**
     * 現金過不足を設定します。
     * @param genkinKafusoku
     */
    public void setDecGenkinKafusoku(BigDecimal genkinKafusoku) {
        this.genkinKafusoku = String.valueOf(genkinKafusoku);
    }


    
    
    /*************************************
     *  エレメントインデックスを保持する
     *************************************/
    
    
    /**
     * 売上計
     */
    private String u01UriageElm;
    
    /**
     * メニュー売上計
     */
    private String u02MenuUriElm;
    
    /**
     * 物販計
     */
    private String u03BuppanUriElm;
    
    /**
     * 値引計
     */
    private String u04NebikiElm;
    
    /**
     * 値引後売上
     */
    private String u05NebikigoElm;
    
    /**
     * 消費税
     */
    private String u06TaxElm;
    
    /**
     * マイナス商品件数
     */
    private String u07MinusKenElm;
    
    /**
     * マイナス商品金額
     */
    private String u08MinusKinElm;
    
    /**
     * 値引件数
     */
    private String u09NebikiKenElm;
    
    /**
     * 値引金額
     */
    private String u10NebikiKinElm;
    
    /**
     * ％値引件数
     */
    private String u11PNebikiKenElm;
    
    /**
     * ％値引金額
     */
    private String u12PNebikiKinElm;
    
    /**
     * 現金件数
     */
    private String u13GenkinKenElm;
    
    /**
     * 現金
     */
    private String u14GenkinKinElm;
    
    /**
     * 会計区分２件数
     */
    private String u15KaikeiKen2Elm;
    
    /**
     * 会計区分２金額
     */
    private String u16KaikeiKin2Elm;
    
    /**
     * 会計区分３件数
     */
    private String u17KaikeiKen3Elm;
    
    /**
     * 会計区分３金額
     */
    private String u18KaikeiKin3Elm;
    
    /**
     * 会計区分４件数
     */
    private String u19KaikeiKen4Elm;
    
    /**
     * 会計区分４金額
     */
    private String u20KaikeiKin4Elm;
    
    /**
     * 会計区分５件数
     */
    private String u21KaikeiKen5Elm;
    
    /**
     * 会計区分５金額
     */
    private String u22KaikeiKin5Elm;
    
    /**
     * 会計区分６件数
     */
    private String u23KaikeiKen6Elm;
    
    /**
     * 会計区分６金額
     */
    private String u24KaikeiKin6Elm;
    
    /**
     * 会計区分７件数
     */
    private String u25KaikeiKen7Elm;
    
    /**
     * 会計区分７金額
     */
    private String u26KaikeiKin7Elm;
    
    /**
     * 会計区分８件数
     */
    private String u27KaikeiKen8Elm;
    
    /**
     * 会計区分８金額
     */
    private String u28KaikeiKin8Elm;
    
    /**
     * 会計区分９件数
     */
    private String u29KaikeiKen9Elm;
    
    /**
     * 会計区分９金額
     */
    private String u30KaikeiKin9Elm;
    
    /**
     * 会計区分１０件数
     */
    private String u31KaikeiKen10Elm;
    
    /**
     * 会計区分１０金額
     */
    private String u32KaikeiKin10Elm;
    
    /**
     * 会計区分１１件数
     */
    private String u33KaikeiKen11Elm;
    
    /**
     * 会計区分１１金額
     */
    private String u34KaikeiKin11Elm;
    
    /**
     * チケット１販売件数
     */
    private String u35TieketKen1Elm;
    
    /**
     * チケット１販売金額
     */
    private String u36TieketKin1Elm;
    
    /**
     * チケット２販売件数
     */
    private String u37TieketKen2Elm;
    
    /**
     * チケット２販売金額
     */
    private String u38TieketKin2Elm;
    
    /**
     * チケット３販売件数
     */
    private String u39TieketKen3Elm;
    
    /**
     * チケット３販売金額
     */
    private String u40TieketKin3Elm;
    
    /**
     * チケット４販売件数
     */
    private String u41TieketKen4Elm;
    
    /**
     * チケット４販売金額
     */
    private String u42TieketKin4Elm;
    
    /**
     * チケット５販売件数
     */
    private String u43TieketKen5Elm;
    
    /**
     * チケット５販売金額
     */
    private String u44TieketKin5Elm;
    
    /**
     * チケット６販売件数
     */
    private String u45TieketKen6Elm;
    
    /**
     * チケット６販売金額
     */
    private String u46TieketKin6Elm;
    
    /**
     * チケット７販売件数
     */
    private String u47TieketKen7Elm;
    
    /**
     * チケット７販売金額
     */
    private String u48TieketKin7Elm;
    
    /**
     * チケット８販売件数
     */
    private String u49TieketKen8Elm;
    
    /**
     * チケット８販売金額
     */
    private String u50TieketKin8Elm;
    
    /**
     * チケット９販売件数
     */
    private String u51TieketKen9Elm;
    
    /**
     * チケット９販売金額
     */
    private String u52TieketKin9Elm;
    
    /**
     * チケット１０販売件数
     */
    private String u53TieketKen10Elm;
    
    /**
     * チケット１０販売金額
     */
    private String u54TieketKin10Elm;
    
    /**
     * チケット１１販売件数
     */
    private String u55TieketKen11Elm;
    
    /**
     * チケット１１販売金額
     */
    private String u56TieketKin11Elm;
    
    /**
     * チケット１２販売件数
     */
    private String u57TieketKen12Elm;
    
    /**
     * チケット１２販売金額
     */
    private String u58TieketKin12Elm;
    
    /**
     * チケット１３販売件数
     */
    private String u59TieketKen13Elm;
    
    /**
     * チケット１３販売金額
     */
    private String u60TieketKin13Elm;
    
    /**
     * チケット１４販売件数
     */
    private String u61TieketKen14Elm;
    
    /**
     * チケット１４販売金額
     */
    private String u62TieketKin14Elm;
    
    /**
     * チケット１５販売件数
     */
    private String u63TieketKen15Elm;
    
    /**
     * チケット１５販売金額
     */
    private String u64TieketKin15Elm;
    
    /**
     * 入金金額
     */
    private String u65NyukinElm;
    
    /**
     * 出金金額
     */
    private String u66ShukinElm;
    
    /**
     * 計算現金在高
     */
    private String u67AridakaCalElm;
    
    /**
     * 実現金在高
     */
    private String u68AridakaJituElm;
    
    /**
     * 過剰金額
     */
    private String u69KajouElm;
    
    /**
     * 不足金額
     */
    private String u70FusokuElm;
    
    /**
     * 取消件数
     */
    private String u71CanKenElm;
    
    /**
     * 取消金額
     */
    private String u72CanKinElm;
    
    /**
     * 両替回数
     */
    private String u73ChengeCntElm;
    
    /**
     * 客数
     */
    private String u74KyakusuElm;
    
    /**
     * オールキャンセル回数
     */
    private String u75AllcanKenElm;
    
    /**
     * オールキャンセル金額
     */
    private String u76AllcanKinElm;
    
    /**
     * 値引１件数
     */
    private String  u01NebikiKen1Elm;
    
    /**
     * 値引１金額
     */
    private String  u02NebikiKin1Elm;
    
    /**
     * 値引１税額
     */
    private String  u03NebikiTax1Elm;
    
    /**
     * 値引２件数
     */
    private String  u04NebikiKen2Elm;
    
    /**
     * 値引２金額
     */
    private String  u05NebikiKin2Elm;
    
    /**
     * 値引２税額
     */
    private String  u06NebikiTax2Elm;
    
    /**
     * 値引３件数
     */
    private String  u07NebikiKen3Elm;
    
    /**
     * 値引３金額
     */
    private String  u08NebikiKin3Elm;
    
    /**
     * 値引３税額
     */
    private String  u09NebikiTax3Elm;
    
    /**
     * 値引４件数
     */
    private String  u10NebikiKen4Elm;
    
    /**
     * 値引４金額
     */
    private String  u11NebikiKin4Elm;
    
    /**
     * 値引４税額
     */
    private String  u12NebikiTax4Elm;
    
    /**
     * 値引５件数
     */
    private String  u13NebikiKen5Elm;
    
    /**
     * 値引５金額
     */
    private String  u14NebikiKin5Elm;
    
    /**
     * 値引５税額
     */
    private String  u15NebikiTax5Elm;
    
    /**
     * 値引６件数
     */
    private String  u16NebikiKen6Elm;
    
    /**
     * 値引６金額
     */
    private String  u17NebikiKin6Elm;
    
    /**
     * 値引６税額
     */
    private String  u18NebikiTax6Elm;
    
    /**
     * 値引７件数
     */
    private String  u19NebikiKen7Elm;
    
    /**
     * 値引７金額
     */
    private String  u20NebikiKin7Elm;
    
    /**
     * 値引７税額
     */
    private String  u21NebikiTax7Elm;
    
    /**
     * 値引８件数
     */
    private String  u22NebikiKen8Elm;
    
    /**
     * 値引８金額
     */
    private String  u23NebikiKin8Elm;
    
    /**
     * 値引８税額
     */
    private String  u24NebikiTax8Elm;
    
    /**
     * 値引９件数
     */
    private String  u25NebikiKen9Elm;
    
    /**
     * 値引９金額
     */
    private String  u26NebikiKin9Elm;
    
    /**
     * 値引９税額
     */
    private String  u27NebikiTax9Elm;
    
    /**
     * 売上計を取得します。
     * @return 売上計
     */
    public String getU01UriageElm() {
        return u01UriageElm;
    }
    /**
     * 売上計を設定します。
     * @param u01UriageElm 売上計
     */
    public void setU01UriageElm(String u01UriageElm) {
        this.u01UriageElm = u01UriageElm;
    }
    
    /**
     * メニュー売上計を取得します。
     * @return メニュー売上計
     */
    public String getU02MenuUriElm() {
        return u02MenuUriElm;
    }
    /**
     * メニュー売上計を設定します。
     * @param u02MenuUriElm メニュー売上計
     */
    public void setU02MenuUriElm(String u02MenuUriElm) {
        this.u02MenuUriElm = u02MenuUriElm;
    }
    
    /**
     * 物販計を取得します。
     * @return 物販計
     */
    public String getU03BuppanUriElm() {
        return u03BuppanUriElm;
    }
    /**
     * 物販計を設定します。
     * @param u03BuppanUriElm 物販計
     */
    public void setU03BuppanUriElm(String u03BuppanUriElm) {
        this.u03BuppanUriElm = u03BuppanUriElm;
    }
    
    /**
     * 値引計を取得します。
     * @return 値引計
     */
    public String getU04NebikiElm() {
        return u04NebikiElm;
    }
    /**
     * 値引計を設定します。
     * @param u04NebikiElm 値引計
     */
    public void setU04NebikiElm(String u04NebikiElm) {
        this.u04NebikiElm = u04NebikiElm;
    }
    
    /**
     * 値引後売上を取得します。
     * @return 値引後売上
     */
    public String getU05NebikigoElm() {
        return u05NebikigoElm;
    }
    /**
     * 値引後売上を設定します。
     * @param u05NebikigoElm 値引後売上
     */
    public void setU05NebikigoElm(String u05NebikigoElm) {
        this.u05NebikigoElm = u05NebikigoElm;
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public String getU06TaxElm() {
        return u06TaxElm;
    }
    /**
     * 消費税を設定します。
     * @param u06TaxElm 消費税
     */
    public void setU06TaxElm(String u06TaxElm) {
        this.u06TaxElm = u06TaxElm;
    }
    
    /**
     * マイナス商品件数を取得します。
     * @return マイナス商品件数
     */
    public String getU07MinusKenElm() {
        return u07MinusKenElm;
    }
    /**
     * マイナス商品件数を設定します。
     * @param u07MinusKenElm マイナス商品件数
     */
    public void setU07MinusKenElm(String u07MinusKenElm) {
        this.u07MinusKenElm = u07MinusKenElm;
    }
    
    /**
     * マイナス商品金額を取得します。
     * @return マイナス商品金額
     */
    public String getU08MinusKinElm() {
        return u08MinusKinElm;
    }
    /**
     * マイナス商品金額を設定します。
     * @param u08MinusKinElm マイナス商品金額
     */
    public void setU08MinusKinElm(String u08MinusKinElm) {
        this.u08MinusKinElm = u08MinusKinElm;
    }
    
    /**
     * 値引件数を取得します。
     * @return 値引件数
     */
    public String getU09NebikiKenElm() {
        return u09NebikiKenElm;
    }
    /**
     * 値引件数を設定します。
     * @param u09NebikiKenElm 値引件数
     */
    public void setU09NebikiKenElm(String u09NebikiKenElm) {
        this.u09NebikiKenElm = u09NebikiKenElm;
    }
    
    /**
     * 値引金額を取得します。
     * @return 値引金額
     */
    public String getU10NebikiKinElm() {
        return u10NebikiKinElm;
    }
    /**
     * 値引金額を設定します。
     * @param u10NebikiKinElm 値引金額
     */
    public void setU10NebikiKinElm(String u10NebikiKinElm) {
        this.u10NebikiKinElm = u10NebikiKinElm;
    }
    
    /**
     * ％値引件数を取得します。
     * @return ％値引件数
     */
    public String getU11PNebikiKenElm() {
        return u11PNebikiKenElm;
    }
    /**
     * ％値引件数を設定します。
     * @param u11PNebikiKenElm ％値引件数
     */
    public void setU11PNebikiKenElm(String u11PNebikiKenElm) {
        this.u11PNebikiKenElm = u11PNebikiKenElm;
    }
    
    /**
     * ％値引金額を取得します。
     * @return ％値引金額
     */
    public String getU12PNebikiKinElm() {
        return u12PNebikiKinElm;
    }
    /**
     * ％値引金額を設定します。
     * @param u12PNebikiKinElm ％値引金額
     */
    public void setU12PNebikiKinElm(String u12PNebikiKinElm) {
        this.u12PNebikiKinElm = u12PNebikiKinElm;
    }
    
    /**
     * 現金件数を取得します。
     * @return 現金件数
     */
    public String getU13GenkinKenElm() {
        return u13GenkinKenElm;
    }
    /**
     * 現金件数を設定します。
     * @param u13GenkinKenElm 現金件数
     */
    public void setU13GenkinKenElm(String u13GenkinKenElm) {
        this.u13GenkinKenElm = u13GenkinKenElm;
    }
    
    /**
     * 現金を取得します。
     * @return 現金
     */
    public String getU14GenkinKinElm() {
        return u14GenkinKinElm;
    }
    /**
     * 現金を設定します。
     * @param u14GenkinKinElm 現金
     */
    public void setU14GenkinKinElm(String u14GenkinKinElm) {
        this.u14GenkinKinElm = u14GenkinKinElm;
    }
    
    /**
     * 会計区分２件数を取得します。
     * @return 会計区分２件数
     */
    public String getU15KaikeiKen2Elm() {
        return u15KaikeiKen2Elm;
    }
    /**
     * 会計区分２件数を設定します。
     * @param u15KaikeiKen2Elm 会計区分２件数
     */
    public void setU15KaikeiKen2Elm(String u15KaikeiKen2Elm) {
        this.u15KaikeiKen2Elm = u15KaikeiKen2Elm;
    }
    
    /**
     * 会計区分２金額を取得します。
     * @return 会計区分２金額
     */
    public String getU16KaikeiKin2Elm() {
        return u16KaikeiKin2Elm;
    }
    /**
     * 会計区分２金額を設定します。
     * @param u16KaikeiKin2Elm 会計区分２金額
     */
    public void setU16KaikeiKin2Elm(String u16KaikeiKin2Elm) {
        this.u16KaikeiKin2Elm = u16KaikeiKin2Elm;
    }
    
    /**
     * 会計区分３件数を取得します。
     * @return 会計区分３件数
     */
    public String getU17KaikeiKen3Elm() {
        return u17KaikeiKen3Elm;
    }
    /**
     * 会計区分３件数を設定します。
     * @param u17KaikeiKen3Elm 会計区分３件数
     */
    public void setU17KaikeiKen3Elm(String u17KaikeiKen3Elm) {
        this.u17KaikeiKen3Elm = u17KaikeiKen3Elm;
    }
    
    /**
     * 会計区分３金額を取得します。
     * @return 会計区分３金額
     */
    public String getU18KaikeiKin3Elm() {
        return u18KaikeiKin3Elm;
    }
    /**
     * 会計区分３金額を設定します。
     * @param u18KaikeiKin3Elm 会計区分３金額
     */
    public void setU18KaikeiKin3Elm(String u18KaikeiKin3Elm) {
        this.u18KaikeiKin3Elm = u18KaikeiKin3Elm;
    }
    
    /**
     * 会計区分４件数を取得します。
     * @return 会計区分４件数
     */
    public String getU19KaikeiKen4Elm() {
        return u19KaikeiKen4Elm;
    }
    /**
     * 会計区分４件数を設定します。
     * @param u19KaikeiKen4Elm 会計区分４件数
     */
    public void setU19KaikeiKen4Elm(String u19KaikeiKen4Elm) {
        this.u19KaikeiKen4Elm = u19KaikeiKen4Elm;
    }
    
    /**
     * 会計区分４金額を取得します。
     * @return 会計区分４金額
     */
    public String getU20KaikeiKin4Elm() {
        return u20KaikeiKin4Elm;
    }
    /**
     * 会計区分４金額を設定します。
     * @param u20KaikeiKin4Elm 会計区分４金額
     */
    public void setU20KaikeiKin4Elm(String u20KaikeiKin4Elm) {
        this.u20KaikeiKin4Elm = u20KaikeiKin4Elm;
    }
    
    /**
     * 会計区分５件数を取得します。
     * @return 会計区分５件数
     */
    public String getU21KaikeiKen5Elm() {
        return u21KaikeiKen5Elm;
    }
    /**
     * 会計区分５件数を設定します。
     * @param u21KaikeiKen5Elm 会計区分５件数
     */
    public void setU21KaikeiKen5Elm(String u21KaikeiKen5Elm) {
        this.u21KaikeiKen5Elm = u21KaikeiKen5Elm;
    }
    
    /**
     * 会計区分５金額を取得します。
     * @return 会計区分５金額
     */
    public String getU22KaikeiKin5Elm() {
        return u22KaikeiKin5Elm;
    }
    /**
     * 会計区分５金額を設定します。
     * @param u22KaikeiKin5Elm 会計区分５金額
     */
    public void setU22KaikeiKin5Elm(String u22KaikeiKin5Elm) {
        this.u22KaikeiKin5Elm = u22KaikeiKin5Elm;
    }
    
    /**
     * 会計区分６件数を取得します。
     * @return 会計区分６件数
     */
    public String getU23KaikeiKen6Elm() {
        return u23KaikeiKen6Elm;
    }
    /**
     * 会計区分６件数を設定します。
     * @param u23KaikeiKen6Elm 会計区分６件数
     */
    public void setU23KaikeiKen6Elm(String u23KaikeiKen6Elm) {
        this.u23KaikeiKen6Elm = u23KaikeiKen6Elm;
    }
    
    /**
     * 会計区分６金額を取得します。
     * @return 会計区分６金額
     */
    public String getU24KaikeiKin6Elm() {
        return u24KaikeiKin6Elm;
    }
    /**
     * 会計区分６金額を設定します。
     * @param u24KaikeiKin6Elm 会計区分６金額
     */
    public void setU24KaikeiKin6Elm(String u24KaikeiKin6Elm) {
        this.u24KaikeiKin6Elm = u24KaikeiKin6Elm;
    }
    
    /**
     * 会計区分７件数を取得します。
     * @return 会計区分７件数
     */
    public String getU25KaikeiKen7Elm() {
        return u25KaikeiKen7Elm;
    }
    /**
     * 会計区分７件数を設定します。
     * @param u25KaikeiKen7Elm 会計区分７件数
     */
    public void setU25KaikeiKen7Elm(String u25KaikeiKen7Elm) {
        this.u25KaikeiKen7Elm = u25KaikeiKen7Elm;
    }
    
    /**
     * 会計区分７金額を取得します。
     * @return 会計区分７金額
     */
    public String getU26KaikeiKin7Elm() {
        return u26KaikeiKin7Elm;
    }
    /**
     * 会計区分７金額を設定します。
     * @param u26KaikeiKin7Elm 会計区分７金額
     */
    public void setU26KaikeiKin7Elm(String u26KaikeiKin7Elm) {
        this.u26KaikeiKin7Elm = u26KaikeiKin7Elm;
    }
    
    /**
     * 会計区分８件数を取得します。
     * @return 会計区分８件数
     */
    public String getU27KaikeiKen8Elm() {
        return u27KaikeiKen8Elm;
    }
    /**
     * 会計区分８件数を設定します。
     * @param u27KaikeiKen8Elm 会計区分８件数
     */
    public void setU27KaikeiKen8Elm(String u27KaikeiKen8Elm) {
        this.u27KaikeiKen8Elm = u27KaikeiKen8Elm;
    }
    
    /**
     * 会計区分８金額を取得します。
     * @return 会計区分８金額
     */
    public String getU28KaikeiKin8Elm() {
        return u28KaikeiKin8Elm;
    }
    /**
     * 会計区分８金額を設定します。
     * @param u28KaikeiKin8Elm 会計区分８金額
     */
    public void setU28KaikeiKin8Elm(String u28KaikeiKin8Elm) {
        this.u28KaikeiKin8Elm = u28KaikeiKin8Elm;
    }
    
    /**
     * 会計区分９件数を取得します。
     * @return 会計区分９件数
     */
    public String getU29KaikeiKen9Elm() {
        return u29KaikeiKen9Elm;
    }
    /**
     * 会計区分９件数を設定します。
     * @param u29KaikeiKen9Elm 会計区分９件数
     */
    public void setU29KaikeiKen9Elm(String u29KaikeiKen9Elm) {
        this.u29KaikeiKen9Elm = u29KaikeiKen9Elm;
    }
    
    /**
     * 会計区分９金額を取得します。
     * @return 会計区分９金額
     */
    public String getU30KaikeiKin9Elm() {
        return u30KaikeiKin9Elm;
    }
    /**
     * 会計区分９金額を設定します。
     * @param u30KaikeiKin9Elm 会計区分９金額
     */
    public void setU30KaikeiKin9Elm(String u30KaikeiKin9Elm) {
        this.u30KaikeiKin9Elm = u30KaikeiKin9Elm;
    }
    
    /**
     * 会計区分１０件数を取得します。
     * @return 会計区分１０件数
     */
    public String getU31KaikeiKen10Elm() {
        return u31KaikeiKen10Elm;
    }
    /**
     * 会計区分１０件数を設定します。
     * @param u31KaikeiKen10Elm 会計区分１０件数
     */
    public void setU31KaikeiKen10Elm(String u31KaikeiKen10Elm) {
        this.u31KaikeiKen10Elm = u31KaikeiKen10Elm;
    }
    
    /**
     * 会計区分１０金額を取得します。
     * @return 会計区分１０金額
     */
    public String getU32KaikeiKin10Elm() {
        return u32KaikeiKin10Elm;
    }
    /**
     * 会計区分１０金額を設定します。
     * @param u32KaikeiKin10Elm 会計区分１０金額
     */
    public void setU32KaikeiKin10Elm(String u32KaikeiKin10Elm) {
        this.u32KaikeiKin10Elm = u32KaikeiKin10Elm;
    }
    
    /**
     * 会計区分１１件数を取得します。
     * @return 会計区分１１件数
     */
    public String getU33KaikeiKen11Elm() {
        return u33KaikeiKen11Elm;
    }
    /**
     * 会計区分１１件数を設定します。
     * @param u33KaikeiKen11Elm 会計区分１１件数
     */
    public void setU33KaikeiKen11Elm(String u33KaikeiKen11Elm) {
        this.u33KaikeiKen11Elm = u33KaikeiKen11Elm;
    }
    
    /**
     * 会計区分１１金額を取得します。
     * @return 会計区分１１金額
     */
    public String getU34KaikeiKin11Elm() {
        return u34KaikeiKin11Elm;
    }
    /**
     * 会計区分１１金額を設定します。
     * @param u34KaikeiKin11Elm 会計区分１１金額
     */
    public void setU34KaikeiKin11Elm(String u34KaikeiKin11Elm) {
        this.u34KaikeiKin11Elm = u34KaikeiKin11Elm;
    }
    
    /**
     * チケット１販売件数を取得します。
     * @return チケット１販売件数
     */
    public String getU35TieketKen1Elm() {
        return u35TieketKen1Elm;
    }
    /**
     * チケット１販売件数を設定します。
     * @param u35TieketKen1Elm チケット１販売件数
     */
    public void setU35TieketKen1Elm(String u35TieketKen1Elm) {
        this.u35TieketKen1Elm = u35TieketKen1Elm;
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public String getU36TieketKin1Elm() {
        return u36TieketKin1Elm;
    }
    /**
     * チケット１販売金額を設定します。
     * @param u36TieketKin1Elm チケット１販売金額
     */
    public void setU36TieketKin1Elm(String u36TieketKin1Elm) {
        this.u36TieketKin1Elm = u36TieketKin1Elm;
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public String getU37TieketKen2Elm() {
        return u37TieketKen2Elm;
    }
    /**
     * チケット２販売件数を設定します。
     * @param u37TieketKen2Elm チケット２販売件数
     */
    public void setU37TieketKen2Elm(String u37TieketKen2Elm) {
        this.u37TieketKen2Elm = u37TieketKen2Elm;
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public String getU38TieketKin2Elm() {
        return u38TieketKin2Elm;
    }
    /**
     * チケット２販売金額を設定します。
     * @param u38TieketKin2Elm チケット２販売金額
     */
    public void setU38TieketKin2Elm(String u38TieketKin2Elm) {
        this.u38TieketKin2Elm = u38TieketKin2Elm;
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public String getU39TieketKen3Elm() {
        return u39TieketKen3Elm;
    }
    /**
     * チケット３販売件数を設定します。
     * @param u39TieketKen3Elm チケット３販売件数
     */
    public void setU39TieketKen3Elm(String u39TieketKen3Elm) {
        this.u39TieketKen3Elm = u39TieketKen3Elm;
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public String getU40TieketKin3Elm() {
        return u40TieketKin3Elm;
    }
    /**
     * チケット３販売金額を設定します。
     * @param u40TieketKin3Elm チケット３販売金額
     */
    public void setU40TieketKin3Elm(String u40TieketKin3Elm) {
        this.u40TieketKin3Elm = u40TieketKin3Elm;
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public String getU41TieketKen4Elm() {
        return u41TieketKen4Elm;
    }
    /**
     * チケット４販売件数を設定します。
     * @param u41TieketKen4Elm チケット４販売件数
     */
    public void setU41TieketKen4Elm(String u41TieketKen4Elm) {
        this.u41TieketKen4Elm = u41TieketKen4Elm;
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public String getU42TieketKin4Elm() {
        return u42TieketKin4Elm;
    }
    /**
     * チケット４販売金額を設定します。
     * @param u42TieketKin4Elm チケット４販売金額
     */
    public void setU42TieketKin4Elm(String u42TieketKin4Elm) {
        this.u42TieketKin4Elm = u42TieketKin4Elm;
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public String getU43TieketKen5Elm() {
        return u43TieketKen5Elm;
    }
    /**
     * チケット５販売件数を設定します。
     * @param u43TieketKen5Elm チケット５販売件数
     */
    public void setU43TieketKen5Elm(String u43TieketKen5Elm) {
        this.u43TieketKen5Elm = u43TieketKen5Elm;
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public String getU44TieketKin5Elm() {
        return u44TieketKin5Elm;
    }
    /**
     * チケット５販売金額を設定します。
     * @param u44TieketKin5Elm チケット５販売金額
     */
    public void setU44TieketKin5Elm(String u44TieketKin5Elm) {
        this.u44TieketKin5Elm = u44TieketKin5Elm;
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public String getU45TieketKen6Elm() {
        return u45TieketKen6Elm;
    }
    /**
     * チケット６販売件数を設定します。
     * @param u45TieketKen6Elm チケット６販売件数
     */
    public void setU45TieketKen6Elm(String u45TieketKen6Elm) {
        this.u45TieketKen6Elm = u45TieketKen6Elm;
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public String getU46TieketKin6Elm() {
        return u46TieketKin6Elm;
    }
    /**
     * チケット６販売金額を設定します。
     * @param u46TieketKin6Elm チケット６販売金額
     */
    public void setU46TieketKin6Elm(String u46TieketKin6Elm) {
        this.u46TieketKin6Elm = u46TieketKin6Elm;
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public String getU47TieketKen7Elm() {
        return u47TieketKen7Elm;
    }
    /**
     * チケット７販売件数を設定します。
     * @param u47TieketKen7Elm チケット７販売件数
     */
    public void setU47TieketKen7Elm(String u47TieketKen7Elm) {
        this.u47TieketKen7Elm = u47TieketKen7Elm;
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public String getU48TieketKin7Elm() {
        return u48TieketKin7Elm;
    }
    /**
     * チケット７販売金額を設定します。
     * @param u48TieketKin7Elm チケット７販売金額
     */
    public void setU48TieketKin7Elm(String u48TieketKin7Elm) {
        this.u48TieketKin7Elm = u48TieketKin7Elm;
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public String getU49TieketKen8Elm() {
        return u49TieketKen8Elm;
    }
    /**
     * チケット８販売件数を設定します。
     * @param u49TieketKen8Elm チケット８販売件数
     */
    public void setU49TieketKen8Elm(String u49TieketKen8Elm) {
        this.u49TieketKen8Elm = u49TieketKen8Elm;
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public String getU50TieketKin8Elm() {
        return u50TieketKin8Elm;
    }
    /**
     * チケット８販売金額を設定します。
     * @param u50TieketKin8Elm チケット８販売金額
     */
    public void setU50TieketKin8Elm(String u50TieketKin8Elm) {
        this.u50TieketKin8Elm = u50TieketKin8Elm;
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public String getU51TieketKen9Elm() {
        return u51TieketKen9Elm;
    }
    /**
     * チケット９販売件数を設定します。
     * @param u51TieketKen9Elm チケット９販売件数
     */
    public void setU51TieketKen9Elm(String u51TieketKen9Elm) {
        this.u51TieketKen9Elm = u51TieketKen9Elm;
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public String getU52TieketKin9Elm() {
        return u52TieketKin9Elm;
    }
    /**
     * チケット９販売金額を設定します。
     * @param u52TieketKin9Elm チケット９販売金額
     */
    public void setU52TieketKin9Elm(String u52TieketKin9Elm) {
        this.u52TieketKin9Elm = u52TieketKin9Elm;
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public String getU53TieketKen10Elm() {
        return u53TieketKen10Elm;
    }
    /**
     * チケット１０販売件数を設定します。
     * @param u53TieketKen10Elm チケット１０販売件数
     */
    public void setU53TieketKen10Elm(String u53TieketKen10Elm) {
        this.u53TieketKen10Elm = u53TieketKen10Elm;
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public String getU54TieketKin10Elm() {
        return u54TieketKin10Elm;
    }
    /**
     * チケット１０販売金額を設定します。
     * @param u54TieketKin10Elm チケット１０販売金額
     */
    public void setU54TieketKin10Elm(String u54TieketKin10Elm) {
        this.u54TieketKin10Elm = u54TieketKin10Elm;
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public String getU55TieketKen11Elm() {
        return u55TieketKen11Elm;
    }
    /**
     * チケット１１販売件数を設定します。
     * @param u55TieketKen11Elm チケット１１販売件数
     */
    public void setU55TieketKen11Elm(String u55TieketKen11Elm) {
        this.u55TieketKen11Elm = u55TieketKen11Elm;
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public String getU56TieketKin11Elm() {
        return u56TieketKin11Elm;
    }
    /**
     * チケット１１販売金額を設定します。
     * @param u56TieketKin11Elm チケット１１販売金額
     */
    public void setU56TieketKin11Elm(String u56TieketKin11Elm) {
        this.u56TieketKin11Elm = u56TieketKin11Elm;
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public String getU57TieketKen12Elm() {
        return u57TieketKen12Elm;
    }
    /**
     * チケット１２販売件数を設定します。
     * @param u57TieketKen12Elm チケット１２販売件数
     */
    public void setU57TieketKen12Elm(String u57TieketKen12Elm) {
        this.u57TieketKen12Elm = u57TieketKen12Elm;
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public String getU58TieketKin12Elm() {
        return u58TieketKin12Elm;
    }
    /**
     * チケット１２販売金額を設定します。
     * @param u58TieketKin12Elm チケット１２販売金額
     */
    public void setU58TieketKin12Elm(String u58TieketKin12Elm) {
        this.u58TieketKin12Elm = u58TieketKin12Elm;
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public String getU59TieketKen13Elm() {
        return u59TieketKen13Elm;
    }
    /**
     * チケット１３販売件数を設定します。
     * @param u59TieketKen13Elm チケット１３販売件数
     */
    public void setU59TieketKen13Elm(String u59TieketKen13Elm) {
        this.u59TieketKen13Elm = u59TieketKen13Elm;
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public String getU60TieketKin13Elm() {
        return u60TieketKin13Elm;
    }
    /**
     * チケット１３販売金額を設定します。
     * @param u60TieketKin13Elm チケット１３販売金額
     */
    public void setU60TieketKin13Elm(String u60TieketKin13Elm) {
        this.u60TieketKin13Elm = u60TieketKin13Elm;
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public String getU61TieketKen14Elm() {
        return u61TieketKen14Elm;
    }
    /**
     * チケット１４販売件数を設定します。
     * @param u61TieketKen14Elm チケット１４販売件数
     */
    public void setU61TieketKen14Elm(String u61TieketKen14Elm) {
        this.u61TieketKen14Elm = u61TieketKen14Elm;
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public String getU62TieketKin14Elm() {
        return u62TieketKin14Elm;
    }
    /**
     * チケット１４販売金額を設定します。
     * @param u62TieketKin14Elm チケット１４販売金額
     */
    public void setU62TieketKin14Elm(String u62TieketKin14Elm) {
        this.u62TieketKin14Elm = u62TieketKin14Elm;
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public String getU63TieketKen15Elm() {
        return u63TieketKen15Elm;
    }
    /**
     * チケット１５販売件数を設定します。
     * @param u63TieketKen15Elm チケット１５販売件数
     */
    public void setU63TieketKen15Elm(String u63TieketKen15Elm) {
        this.u63TieketKen15Elm = u63TieketKen15Elm;
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public String getU64TieketKin15Elm() {
        return u64TieketKin15Elm;
    }
    /**
     * チケット１５販売金額を設定します。
     * @param u64TieketKin15Elm チケット１５販売金額
     */
    public void setU64TieketKin15Elm(String u64TieketKin15Elm) {
        this.u64TieketKin15Elm = u64TieketKin15Elm;
    }
    
    /**
     * 入金金額を取得します。
     * @return 入金金額
     */
    public String getU65NyukinElm() {
        return u65NyukinElm;
    }
    /**
     * 入金金額を設定します。
     * @param u65NyukinElm 入金金額
     */
    public void setU65NyukinElm(String u65NyukinElm) {
        this.u65NyukinElm = u65NyukinElm;
    }
    
    /**
     * 出金金額を取得します。
     * @return 出金金額
     */
    public String getU66ShukinElm() {
        return u66ShukinElm;
    }
    /**
     * 出金金額を設定します。
     * @param u66ShukinElm 出金金額
     */
    public void setU66ShukinElm(String u66ShukinElm) {
        this.u66ShukinElm = u66ShukinElm;
    }
    
    /**
     * 計算現金在高を取得します。
     * @return 計算現金在高
     */
    public String getU67AridakaCalElm() {
        return u67AridakaCalElm;
    }
    /**
     * 計算現金在高を設定します。
     * @param u67AridakaCalElm 計算現金在高
     */
    public void setU67AridakaCalElm(String u67AridakaCalElm) {
        this.u67AridakaCalElm = u67AridakaCalElm;
    }
    
    /**
     * 実現金在高を取得します。
     * @return 実現金在高
     */
    public String getU68AridakaJituElm() {
        return u68AridakaJituElm;
    }
    /**
     * 実現金在高を設定します。
     * @param u68AridakaJituElm 実現金在高
     */
    public void setU68AridakaJituElm(String u68AridakaJituElm) {
        this.u68AridakaJituElm = u68AridakaJituElm;
    }
    
    /**
     * 過剰金額を取得します。
     * @return 過剰金額
     */
    public String getU69KajouElm() {
        return u69KajouElm;
    }
    /**
     * 過剰金額を設定します。
     * @param u69KajouElm 過剰金額
     */
    public void setU69KajouElm(String u69KajouElm) {
        this.u69KajouElm = u69KajouElm;
    }
    
    /**
     * 不足金額を取得します。
     * @return 不足金額
     */
    public String getU70FusokuElm() {
        return u70FusokuElm;
    }
    /**
     * 不足金額を設定します。
     * @param u70FusokuElm 不足金額
     */
    public void setU70FusokuElm(String u70FusokuElm) {
        this.u70FusokuElm = u70FusokuElm;
    }
    
    /**
     * 取消件数を取得します。
     * @return 取消件数
     */
    public String getU71CanKenElm() {
        return u71CanKenElm;
    }
    /**
     * 取消件数を設定します。
     * @param u71CanKenElm 取消件数
     */
    public void setU71CanKenElm(String u71CanKenElm) {
        this.u71CanKenElm = u71CanKenElm;
    }
    
    /**
     * 取消金額を取得します。
     * @return 取消金額
     */
    public String getU72CanKinElm() {
        return u72CanKinElm;
    }
    /**
     * 取消金額を設定します。
     * @param u72CanKinElm 取消金額
     */
    public void setU72CanKinElm(String u72CanKinElm) {
        this.u72CanKinElm = u72CanKinElm;
    }
    
    /**
     * 両替回数を取得します。
     * @return 両替回数
     */
    public String getU73ChengeCntElm() {
        return u73ChengeCntElm;
    }
    /**
     * 両替回数を設定します。
     * @param u73ChengeCntElm 両替回数
     */
    public void setU73ChengeCntElm(String u73ChengeCntElm) {
        this.u73ChengeCntElm = u73ChengeCntElm;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public String getU74KyakusuElm() {
        return u74KyakusuElm;
    }
    /**
     * 客数を設定します。
     * @param u74KyakusuElm 客数
     */
    public void setU74KyakusuElm(String u74KyakusuElm) {
        this.u74KyakusuElm = u74KyakusuElm;
    }
    
    /**
     * オールキャンセル回数を取得します。
     * @return オールキャンセル回数
     */
    public String getU75AllcanKenElm() {
        return u75AllcanKenElm;
    }
    /**
     * オールキャンセル回数を設定します。
     * @param u75AllcanKenElm オールキャンセル回数
     */
    public void setU75AllcanKenElm(String u75AllcanKenElm) {
        this.u75AllcanKenElm = u75AllcanKenElm;
    }
    
    /**
     * オールキャンセル金額を取得します。
     * @return オールキャンセル金額
     */
    public String getU76AllcanKinElm() {
        return u76AllcanKinElm;
    }
    /**
     * オールキャンセル金額を設定します。
     * @param u76AllcanKinElm オールキャンセル金額
     */
    public void setU76AllcanKinElm(String u76AllcanKinElm) {
        this.u76AllcanKinElm = u76AllcanKinElm;
    }
    
    /**
     * 値引１件数を取得します。
     * @return 値引１件数
     */
    public String  getU01NebikiKen1Elm() {
        return u01NebikiKen1Elm;
    }
    /**
     * 値引１件数を設定します。
     * @param u01NebikiKen1Elm 値引１件数
     */
    public void setU01NebikiKen1Elm(String  u01NebikiKen1Elm) {
        this.u01NebikiKen1Elm = u01NebikiKen1Elm;
    }
    
    /**
     * 値引１金額を取得します。
     * @return 値引１金額
     */
    public String  getU02NebikiKin1Elm() {
        return u02NebikiKin1Elm;
    }
    /**
     * 値引１金額を設定します。
     * @param u02NebikiKin1Elm 値引１金額
     */
    public void setU02NebikiKin1Elm(String  u02NebikiKin1Elm) {
        this.u02NebikiKin1Elm = u02NebikiKin1Elm;
    }
    
    /**
     * 値引１税額を取得します。
     * @return 値引１税額
     */
    public String  getU03NebikiTax1Elm() {
        return u03NebikiTax1Elm;
    }
    /**
     * 値引１税額を設定します。
     * @param u03NebikiTax1Elm 値引１税額
     */
    public void setU03NebikiTax1Elm(String  u03NebikiTax1Elm) {
        this.u03NebikiTax1Elm = u03NebikiTax1Elm;
    }
    
    /**
     * 値引２件数を取得します。
     * @return 値引２件数
     */
    public String  getU04NebikiKen2Elm() {
        return u04NebikiKen2Elm;
    }
    /**
     * 値引２件数を設定します。
     * @param u04NebikiKen2Elm 値引２件数
     */
    public void setU04NebikiKen2Elm(String  u04NebikiKen2Elm) {
        this.u04NebikiKen2Elm = u04NebikiKen2Elm;
    }
    
    /**
     * 値引２金額を取得します。
     * @return 値引２金額
     */
    public String  getU05NebikiKin2Elm() {
        return u05NebikiKin2Elm;
    }
    /**
     * 値引２金額を設定します。
     * @param u05NebikiKin2Elm 値引２金額
     */
    public void setU05NebikiKin2Elm(String  u05NebikiKin2Elm) {
        this.u05NebikiKin2Elm = u05NebikiKin2Elm;
    }
    
    /**
     * 値引２税額を取得します。
     * @return 値引２税額
     */
    public String  getU06NebikiTax2Elm() {
        return u06NebikiTax2Elm;
    }
    /**
     * 値引２税額を設定します。
     * @param u06NebikiTax2Elm 値引２税額
     */
    public void setU06NebikiTax2Elm(String  u06NebikiTax2Elm) {
        this.u06NebikiTax2Elm = u06NebikiTax2Elm;
    }
    
    /**
     * 値引３件数を取得します。
     * @return 値引３件数
     */
    public String  getU07NebikiKen3Elm() {
        return u07NebikiKen3Elm;
    }
    /**
     * 値引３件数を設定します。
     * @param u07NebikiKen3Elm 値引３件数
     */
    public void setU07NebikiKen3Elm(String  u07NebikiKen3Elm) {
        this.u07NebikiKen3Elm = u07NebikiKen3Elm;
    }
    
    /**
     * 値引３金額を取得します。
     * @return 値引３金額
     */
    public String  getU08NebikiKin3Elm() {
        return u08NebikiKin3Elm;
    }
    /**
     * 値引３金額を設定します。
     * @param u08NebikiKin3Elm 値引３金額
     */
    public void setU08NebikiKin3Elm(String  u08NebikiKin3Elm) {
        this.u08NebikiKin3Elm = u08NebikiKin3Elm;
    }
    
    /**
     * 値引３税額を取得します。
     * @return 値引３税額
     */
    public String  getU09NebikiTax3Elm() {
        return u09NebikiTax3Elm;
    }
    /**
     * 値引３税額を設定します。
     * @param u09NebikiTax3Elm 値引３税額
     */
    public void setU09NebikiTax3Elm(String  u09NebikiTax3Elm) {
        this.u09NebikiTax3Elm = u09NebikiTax3Elm;
    }
    
    /**
     * 値引４件数を取得します。
     * @return 値引４件数
     */
    public String  getU10NebikiKen4Elm() {
        return u10NebikiKen4Elm;
    }
    /**
     * 値引４件数を設定します。
     * @param u10NebikiKen4Elm 値引４件数
     */
    public void setU10NebikiKen4Elm(String  u10NebikiKen4Elm) {
        this.u10NebikiKen4Elm = u10NebikiKen4Elm;
    }
    
    /**
     * 値引４金額を取得します。
     * @return 値引４金額
     */
    public String  getU11NebikiKin4Elm() {
        return u11NebikiKin4Elm;
    }
    /**
     * 値引４金額を設定します。
     * @param u11NebikiKin4Elm 値引４金額
     */
    public void setU11NebikiKin4Elm(String  u11NebikiKin4Elm) {
        this.u11NebikiKin4Elm = u11NebikiKin4Elm;
    }
    
    /**
     * 値引４税額を取得します。
     * @return 値引４税額
     */
    public String  getU12NebikiTax4Elm() {
        return u12NebikiTax4Elm;
    }
    /**
     * 値引４税額を設定します。
     * @param u12NebikiTax4Elm 値引４税額
     */
    public void setU12NebikiTax4Elm(String  u12NebikiTax4Elm) {
        this.u12NebikiTax4Elm = u12NebikiTax4Elm;
    }
    
    /**
     * 値引５件数を取得します。
     * @return 値引５件数
     */
    public String  getU13NebikiKen5Elm() {
        return u13NebikiKen5Elm;
    }
    /**
     * 値引５件数を設定します。
     * @param u13NebikiKen5Elm 値引５件数
     */
    public void setU13NebikiKen5Elm(String  u13NebikiKen5Elm) {
        this.u13NebikiKen5Elm = u13NebikiKen5Elm;
    }
    
    /**
     * 値引５金額を取得します。
     * @return 値引５金額
     */
    public String  getU14NebikiKin5Elm() {
        return u14NebikiKin5Elm;
    }
    /**
     * 値引５金額を設定します。
     * @param u14NebikiKin5Elm 値引５金額
     */
    public void setU14NebikiKin5Elm(String  u14NebikiKin5Elm) {
        this.u14NebikiKin5Elm = u14NebikiKin5Elm;
    }
    
    /**
     * 値引５税額を取得します。
     * @return 値引５税額
     */
    public String  getU15NebikiTax5Elm() {
        return u15NebikiTax5Elm;
    }
    /**
     * 値引５税額を設定します。
     * @param u15NebikiTax5Elm 値引５税額
     */
    public void setU15NebikiTax5Elm(String  u15NebikiTax5Elm) {
        this.u15NebikiTax5Elm = u15NebikiTax5Elm;
    }
    
    /**
     * 値引６件数を取得します。
     * @return 値引６件数
     */
    public String  getU16NebikiKen6Elm() {
        return u16NebikiKen6Elm;
    }
    /**
     * 値引６件数を設定します。
     * @param u16NebikiKen6Elm 値引６件数
     */
    public void setU16NebikiKen6Elm(String  u16NebikiKen6Elm) {
        this.u16NebikiKen6Elm = u16NebikiKen6Elm;
    }
    
    /**
     * 値引６金額を取得します。
     * @return 値引６金額
     */
    public String  getU17NebikiKin6Elm() {
        return u17NebikiKin6Elm;
    }
    /**
     * 値引６金額を設定します。
     * @param u17NebikiKin6Elm 値引６金額
     */
    public void setU17NebikiKin6Elm(String  u17NebikiKin6Elm) {
        this.u17NebikiKin6Elm = u17NebikiKin6Elm;
    }
    
    /**
     * 値引６税額を取得します。
     * @return 値引６税額
     */
    public String  getU18NebikiTax6Elm() {
        return u18NebikiTax6Elm;
    }
    /**
     * 値引６税額を設定します。
     * @param u18NebikiTax6Elm 値引６税額
     */
    public void setU18NebikiTax6Elm(String  u18NebikiTax6Elm) {
        this.u18NebikiTax6Elm = u18NebikiTax6Elm;
    }
    
    /**
     * 値引７件数を取得します。
     * @return 値引７件数
     */
    public String  getU19NebikiKen7Elm() {
        return u19NebikiKen7Elm;
    }
    /**
     * 値引７件数を設定します。
     * @param u19NebikiKen7Elm 値引７件数
     */
    public void setU19NebikiKen7Elm(String  u19NebikiKen7Elm) {
        this.u19NebikiKen7Elm = u19NebikiKen7Elm;
    }
    
    /**
     * 値引７金額を取得します。
     * @return 値引７金額
     */
    public String  getU20NebikiKin7Elm() {
        return u20NebikiKin7Elm;
    }
    /**
     * 値引７金額を設定します。
     * @param u20NebikiKin7Elm 値引７金額
     */
    public void setU20NebikiKin7Elm(String  u20NebikiKin7Elm) {
        this.u20NebikiKin7Elm = u20NebikiKin7Elm;
    }
    
    /**
     * 値引７税額を取得します。
     * @return 値引７税額
     */
    public String  getU21NebikiTax7Elm() {
        return u21NebikiTax7Elm;
    }
    /**
     * 値引７税額を設定します。
     * @param u21NebikiTax7Elm 値引７税額
     */
    public void setU21NebikiTax7Elm(String  u21NebikiTax7Elm) {
        this.u21NebikiTax7Elm = u21NebikiTax7Elm;
    }
    
    /**
     * 値引８件数を取得します。
     * @return 値引８件数
     */
    public String  getU22NebikiKen8Elm() {
        return u22NebikiKen8Elm;
    }
    /**
     * 値引８件数を設定します。
     * @param u22NebikiKen8Elm 値引８件数
     */
    public void setU22NebikiKen8Elm(String  u22NebikiKen8Elm) {
        this.u22NebikiKen8Elm = u22NebikiKen8Elm;
    }
    
    /**
     * 値引８金額を取得します。
     * @return 値引８金額
     */
    public String  getU23NebikiKin8Elm() {
        return u23NebikiKin8Elm;
    }
    /**
     * 値引８金額を設定します。
     * @param u23NebikiKin8Elm 値引８金額
     */
    public void setU23NebikiKin8Elm(String  u23NebikiKin8Elm) {
        this.u23NebikiKin8Elm = u23NebikiKin8Elm;
    }
    
    /**
     * 値引８税額を取得します。
     * @return 値引８税額
     */
    public String  getU24NebikiTax8Elm() {
        return u24NebikiTax8Elm;
    }
    /**
     * 値引８税額を設定します。
     * @param u24NebikiTax8Elm 値引８税額
     */
    public void setU24NebikiTax8Elm(String  u24NebikiTax8Elm) {
        this.u24NebikiTax8Elm = u24NebikiTax8Elm;
    }
    
    /**
     * 値引９件数を取得します。
     * @return 値引９件数
     */
    public String  getU25NebikiKen9Elm() {
        return u25NebikiKen9Elm;
    }
    /**
     * 値引９件数を設定します。
     * @param u25NebikiKen9Elm 値引９件数
     */
    public void setU25NebikiKen9Elm(String  u25NebikiKen9Elm) {
        this.u25NebikiKen9Elm = u25NebikiKen9Elm;
    }
    
    /**
     * 値引９金額を取得します。
     * @return 値引９金額
     */
    public String  getU26NebikiKin9Elm() {
        return u26NebikiKin9Elm;
    }
    /**
     * 値引９金額を設定します。
     * @param u26NebikiKin9Elm 値引９金額
     */
    public void setU26NebikiKin9Elm(String  u26NebikiKin9Elm) {
        this.u26NebikiKin9Elm = u26NebikiKin9Elm;
    }
    
    /**
     * 値引９税額を取得します。
     * @return 値引９税額
     */
    public String  getU27NebikiTax9Elm() {
        return u27NebikiTax9Elm;
    }
    /**
     * 値引９税額を設定します。
     * @param u27NebikiTax9Elm 値引９税額
     */
    public void setU27NebikiTax9Elm(String  u27NebikiTax9Elm) {
        this.u27NebikiTax9Elm = u27NebikiTax9Elm;
    }



}
