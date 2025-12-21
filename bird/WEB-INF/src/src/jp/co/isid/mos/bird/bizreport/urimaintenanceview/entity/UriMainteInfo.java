package jp.co.isid.mos.bird.bizreport.urimaintenanceview.entity;

import java.math.BigDecimal;

public class UriMainteInfo {
    
    public static final String TABLE = "BT97ADUP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseName_COLUMN = "MISE_NAME";

    public static final String closeFlg_COLUMN = "CLOSE_FLG";

    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String upDt_COLUMN = "UP_DT";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String tax_COLUMN = "TAX";
    
    public static final String genkinKin_COLUMN = "GENKIN_KIN";
    
    public static final String aridakaJitu_COLUMN = "ARIDAKA_JITU";
    
    public static final String kajou_COLUMN = "KAJOU";
    
    public static final String fusoku_COLUMN = "FUSOKU";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String canKen_COLUMN = "CAN_KEN";
    
    public static final String canKin_COLUMN = "CAN_KIN";
    
    public static final String userName_COLUMN = "USER_NAME";
    
    public static final String kaikeiKen2_COLUMN = "KAIKEI_KEN_2";
    
    public static final String kaikeiKin2_COLUMN = "KAIKEI_KIN_2";
    
    public static final String kaikeiKen3_COLUMN = "KAIKEI_KEN_3";
    
    public static final String kaikeiKin3_COLUMN = "KAIKEI_KIN_3";
    
    public static final String kaikeiKen4_COLUMN = "KAIKEI_KEN_4";
    
    public static final String kaikeiKin4_COLUMN = "KAIKEI_KIN_4";
    
    public static final String kaikeiKen5_COLUMN = "KAIKEI_KEN_5";
    
    public static final String kaikeiKin5_COLUMN = "KAIKEI_KIN_5";
    
    public static final String kaikeiKen6_COLUMN = "KAIKEI_KEN_6";
    
    public static final String kaikeiKin6_COLUMN = "KAIKEI_KIN_6";
    
    public static final String kaikeiKen7_COLUMN = "KAIKEI_KEN_7";
    
    public static final String kaikeiKin7_COLUMN = "KAIKEI_KIN_7";
    
    public static final String kaikeiKen8_COLUMN = "KAIKEI_KEN_8";
    
    public static final String kaikeiKin8_COLUMN = "KAIKEI_KIN_8";
    
    public static final String kaikeiKen9_COLUMN = "KAIKEI_KEN_9";
    
    public static final String kaikeiKin9_COLUMN = "KAIKEI_KIN_9";
    
    public static final String kaikeiKen10_COLUMN = "KAIKEI_KEN_10";
    
    public static final String kaikeiKin10_COLUMN = "KAIKEI_KIN_10";
    
    public static final String kaikeiKen11_COLUMN = "KAIKEI_KEN_11";
    
    public static final String kaikeiKin11_COLUMN = "KAIKEI_KIN_11";
    
    public static final String ticketKen1_COLUMN = "TIEKET_KEN_1";
    
    public static final String ticketKin1_COLUMN = "TIEKET_KIN_1";
    
    public static final String ticketKen2_COLUMN = "TIEKET_KEN_2";
    
    public static final String ticketKin2_COLUMN = "TIEKET_KIN_2";
    
    public static final String ticketKen3_COLUMN = "TIEKET_KEN_3";
    
    public static final String ticketKin3_COLUMN = "TIEKET_KIN_3";
    
    public static final String ticketKen4_COLUMN = "TIEKET_KEN_4";
    
    public static final String ticketKin4_COLUMN = "TIEKET_KIN_4";
    
    public static final String ticketKen5_COLUMN = "TIEKET_KEN_5";
    
    public static final String ticketKin5_COLUMN = "TIEKET_KIN_5";
    
    public static final String ticketKen6_COLUMN = "TIEKET_KEN_6";
    
    public static final String ticketKin6_COLUMN = "TIEKET_KIN_6";
    
    public static final String ticketKen7_COLUMN = "TIEKET_KEN_7";
    
    public static final String ticketKin7_COLUMN = "TIEKET_KIN_7";
    
    public static final String ticketKen8_COLUMN = "TIEKET_KEN_8";
    
    public static final String ticketKin8_COLUMN = "TIEKET_KIN_8";
    
    public static final String ticketKen9_COLUMN = "TIEKET_KEN_9";
    
    public static final String ticketKin9_COLUMN = "TIEKET_KIN_9";
    
    public static final String ticketKen10_COLUMN = "TIEKET_KEN_10";
    
    public static final String ticketKin10_COLUMN = "TIEKET_KIN_10";
    
    public static final String ticketKen11_COLUMN = "TIEKET_KEN_11";
    
    public static final String ticketKin11_COLUMN = "TIEKET_KIN_11";
    
    public static final String ticketKen12_COLUMN = "TIEKET_KEN_12";
    
    public static final String ticketKin12_COLUMN = "TIEKET_KIN_12";
    
    public static final String ticketKen13_COLUMN = "TIEKET_KEN_13";
    
    public static final String ticketKin13_COLUMN = "TIEKET_KIN_13";
    
    public static final String ticketKen14_COLUMN = "TIEKET_KEN_14";
    
    public static final String ticketKin14_COLUMN = "TIEKET_KIN_14";
    
    public static final String ticketKen15_COLUMN = "TIEKET_KEN_15";
    
    public static final String ticketKin15_COLUMN = "TIEKET_KIN_15";
    
    public static final String nebikiKen1_COLUMN = "NEBIKI_KEN_1";
    
    public static final String nebikiKin1_COLUMN = "NEBIKI_KIN_1";
    
    public static final String nebikiTax1_COLUMN = "NEBIKI_TAX_1";
    
    public static final String nebikiKen2_COLUMN = "NEBIKI_KEN_2";
    
    public static final String nebikiKin2_COLUMN = "NEBIKI_KIN_2";
    
    public static final String nebikiTax2_COLUMN = "NEBIKI_TAX_2";
    
    public static final String nebikiKen3_COLUMN = "NEBIKI_KEN_3";
    
    public static final String nebikiKin3_COLUMN = "NEBIKI_KIN_3";
    
    public static final String nebikiTax3_COLUMN = "NEBIKI_TAX_3";
    
    public static final String nebikiKen4_COLUMN = "NEBIKI_KEN_4";
    
    public static final String nebikiKin4_COLUMN = "NEBIKI_KIN_4";
    
    public static final String nebikiTax4_COLUMN = "NEBIKI_TAX_4";
    
    public static final String nebikiKen5_COLUMN = "NEBIKI_KEN_5";
    
    public static final String nebikiKin5_COLUMN = "NEBIKI_KIN_5";
    
    public static final String nebikiTax5_COLUMN = "NEBIKI_TAX_5";
    
    public static final String nebikiKen6_COLUMN = "NEBIKI_KEN_6";
    
    public static final String nebikiKin6_COLUMN = "NEBIKI_KIN_6";
    
    public static final String nebikiTax6_COLUMN = "NEBIKI_TAX_6";
    
    public static final String nebikiKen7_COLUMN = "NEBIKI_KEN_7";
    
    public static final String nebikiKin7_COLUMN = "NEBIKI_KIN_7";
    
    public static final String nebikiTax7_COLUMN = "NEBIKI_TAX_7";
    
    public static final String nebikiKen8_COLUMN = "NEBIKI_KEN_8";
    
    public static final String nebikiKin8_COLUMN = "NEBIKI_KIN_8";
    
    public static final String nebikiTax8_COLUMN = "NEBIKI_TAX_8";
    
    public static final String nebikiKen9_COLUMN = "NEBIKI_KEN_9";
    
    public static final String nebikiKin9_COLUMN = "NEBIKI_KIN_9";
    
    public static final String nebikiTax9_COLUMN = "NEBIKI_TAX_9";

    public static final String bt97UpNo_COLUMN = "BT97_UP_NO";

    public static final String bt96UpNo_COLUMN = "BT96_UP_NO";

    /**
     * (現金在高日次修正)更新項目NO
     */
    private String bt97UpNo;

    /**
     * (値引修正)更新項目NO
     */
    private String bt96UpNo;

    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseName;

    /**
     * 閉店フラグ(0:CLOSE店でない、1:CLOSE店)
     */
    private int closeFlg;

    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 更新日付
     */
    private String upDt;
    
    /**
     * 売上高
     */
    private BigDecimal uriage;

    /**
     * 消費税
     */
    private BigDecimal tax;
    
    /**
     * 現金金額
     */
    private BigDecimal genkinKin;
    
    /**
     * 実現金在高
     */
    private BigDecimal aridakaJitu;
    
    /**
     * 過剰金額
     */
    private BigDecimal kajou;
    
    /**
     * 不足金額
     */
    private BigDecimal fusoku;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 取消件数
     */
    private BigDecimal canKen;
    
    /**
     * 取消金額
     */
    private BigDecimal canKin;
    
    /**
     * 更新者名
     */
    private String userName;
    
    /**
     * 会計区分２件数
     */
    private BigDecimal kaikeiKen2;
    
    /**
     * 会計区分２金額
     */
    private BigDecimal kaikeiKin2;
    
    /**
     * 会計区分３件数
     */
    private BigDecimal kaikeiKen3;
    
    /**
     * 会計区分３金額
     */
    private BigDecimal kaikeiKin3;
    
    /**
     * 会計区分４件数
     */
    private BigDecimal kaikeiKen4;
    
    /**
     * 会計区分４金額
     */
    private BigDecimal kaikeiKin4;
    
    /**
     * 会計区分５件数
     */
    private BigDecimal kaikeiKen5;
    
    /**
     * 会計区分５金額
     */
    private BigDecimal kaikeiKin5;
    
    /**
     * 会計区分６件数
     */
    private BigDecimal kaikeiKen6;
    
    /**
     * 会計区分６金額
     */
    private BigDecimal kaikeiKin6;
    
    /**
     * 会計区分７件数
     */
    private BigDecimal kaikeiKen7;
    
    /**
     * 会計区分７金額
     */
    private BigDecimal kaikeiKin7;
    
    /**
     * 会計区分８件数
     */
    private BigDecimal kaikeiKen8;
    
    /**
     * 会計区分８金額
     */
    private BigDecimal kaikeiKin8;
    
    /**
     * 会計区分９件数
     */
    private BigDecimal kaikeiKen9;
    
    /**
     * 会計区分９金額
     */
    private BigDecimal kaikeiKin9;
    
    /**
     * 会計区分１０件数
     */
    private BigDecimal kaikeiKen10;
    
    /**
     * 会計区分１０金額
     */
    private BigDecimal kaikeiKin10;
    
    /**
     * 会計区分１１件数
     */
    private BigDecimal kaikeiKen11;
    
    /**
     * 会計区分１１金額
     */
    private BigDecimal kaikeiKin11;
    
    /**
     * チケット１販売件数
     */
    private BigDecimal ticketKen1;
    
    /**
     * チケット１販売金額
     */
    private BigDecimal ticketKin1;
    
    /**
     * チケット２販売件数
     */
    private BigDecimal ticketKen2;
    
    /**
     * チケット２販売金額
     */
    private BigDecimal ticketKin2;
    
    /**
     * チケット３販売件数
     */
    private BigDecimal ticketKen3;
    
    /**
     * チケット３販売金額
     */
    private BigDecimal ticketKin3;
    
    /**
     * チケット４販売件数
     */
    private BigDecimal ticketKen4;
    
    /**
     * チケット４販売金額
     */
    private BigDecimal ticketKin4;
    
    /**
     * チケット５販売件数
     */
    private BigDecimal ticketKen5;
    
    /**
     * チケット５販売金額
     */
    private BigDecimal ticketKin5;
    
    /**
     * チケット６販売件数
     */
    private BigDecimal ticketKen6;
    
    /**
     * チケット６販売金額
     */
    private BigDecimal ticketKin6;
    
    /**
     * チケット７販売件数
     */
    private BigDecimal ticketKen7;
    
    /**
     * チケット７販売金額
     */
    private BigDecimal ticketKin7;
    
    /**
     * チケット８販売件数
     */
    private BigDecimal ticketKen8;
    
    /**
     * チケット８販売金額
     */
    private BigDecimal ticketKin8;
    
    /**
     * チケット９販売件数
     */
    private BigDecimal ticketKen9;
    
    /**
     * チケット９販売金額
     */
    private BigDecimal ticketKin9;
    
    /**
     * チケット１０販売件数
     */
    private BigDecimal ticketKen10;
    
    /**
     * チケット１０販売金額
     */
    private BigDecimal ticketKin10;
    
    /**
     * チケット１１販売件数
     */
    private BigDecimal ticketKen11;
    
    /**
     * チケット１１販売金額
     */
    private BigDecimal ticketKin11;
    
    /**
     * チケット１２販売件数
     */
    private BigDecimal ticketKen12;
    
    /**
     * チケット１２販売金額
     */
    private BigDecimal ticketKin12;
    
    /**
     * チケット１３販売件数
     */
    private BigDecimal ticketKen13;
    
    /**
     * チケット１３販売金額
     */
    private BigDecimal ticketKin13;
    
    /**
     * チケット１４販売件数
     */
    private BigDecimal ticketKen14;
    
    /**
     * チケット１４販売金額
     */
    private BigDecimal ticketKin14;
    
    /**
     * チケット１５販売件数
     */
    private BigDecimal ticketKen15;
    
    /**
     * チケット１５販売金額
     */
    private BigDecimal ticketKin15;
    
    /**
     * 値引１件数
     */
    private BigDecimal nebikiKen1;
    
    /**
     * 値引１金額
     */
    private BigDecimal nebikiKin1;
    
    /**
     * 値引１税額
     */
    private BigDecimal nebikiTax1;
    
    /**
     * 値引２件数
     */
    private BigDecimal nebikiKen2;
    
    /**
     * 値引２金額
     */
    private BigDecimal nebikiKin2;
    
    /**
     * 値引２税額
     */
    private BigDecimal nebikiTax2;
    
    /**
     * 値引３件数
     */
    private String nebikiKen3;
    
    /**
     * 値引３金額
     */
    private String nebikiKin3;
    
    /**
     * 値引３税額
     */
    private String nebikiTax3;
    
    /**
     * 値引４件数
     */
    private String nebikiKen4;
    
    /**
     * 値引４金額
     */
    private BigDecimal nebikiKin4;
    
    /**
     * 値引４税額
     */
    private BigDecimal nebikiTax4;
    
    /**
     * 値引５件数
     */
    private BigDecimal nebikiKen5;
    
    /**
     * 値引５金額
     */
    private BigDecimal nebikiKin5;
    
    /**
     * 値引５税額
     */
    private BigDecimal nebikiTax5;
    
    /**
     * 値引６件数
     */
    private BigDecimal nebikiKen6;
    
    /**
     * 値引６金額
     */
    private BigDecimal nebikiKin6;
    
    /**
     * 値引６税額
     */
    private BigDecimal nebikiTax6;
    
    /**
     * 値引７件数
     */
    private BigDecimal nebikiKen7;
    
    /**
     * 値引７金額
     */
    private BigDecimal nebikiKin7;
    
    /**
     * 値引７税額
     */
    private BigDecimal nebikiTax7;
    
    /**
     * 値引８件数
     */
    private BigDecimal nebikiKen8;
    
    /**
     * 値引８金額
     */
    private BigDecimal nebikiKin8;
    
    /**
     * 値引８税額
     */
    private BigDecimal nebikiTax8;
    
    /**
     * 値引９件数
     */
    private BigDecimal nebikiKen9;
    
    /**
     * 値引９金額
     */
    private BigDecimal nebikiKin9;
    
    /**
     * 値引９税額
     */
    private BigDecimal nebikiTax9;
    
    ////////////////////////////////////////////////////
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店名称を設定します。
     * @param miseName 店名称
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }

    /**
     * 閉店フラグを取得します。
     * (0:CLOSEしてない、1:CLOSEしている)
     */
    public int getCloseFlg() {
        return closeFlg;
    }
    /**
     * 閉店フラグを設定します。
     * (0:CLOSEしてない、1:CLOSEしている)
     */
    public void setCloseFlg(int closeFlg) {
        this.closeFlg = closeFlg;
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
     * 売上高を取得します。
     * @return 売上高
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上高を設定します。
     * @param uriage 売上高
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 消費税を取得します。
     * @return 消費税
     */
    public BigDecimal getTax() {
        return tax;
    }
    /**
     * 消費税を設定します。
     * @param tax 消費税
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    /**
     * 現金金額を取得します。
     * @return 現金金額
     */
    public BigDecimal getGenkinKin() {
        return genkinKin;
    }
    /**
     * 現金金額を設定します。
     * @param genkinKin 現金金額
     */
    public void setGenkinKin(BigDecimal genkinKin) {
        this.genkinKin = genkinKin;
    }
    
    /**
     * 実現金在高を取得します。
     * @return 実現金在高
     */
    public BigDecimal getAridakaJitu() {
        return aridakaJitu;
    }
    /**
     * 実現金在高を設定します。
     * @param aridakaJitu 実現金在高
     */
    public void setAridakaJitu(BigDecimal aridakaJitu) {
        this.aridakaJitu = aridakaJitu;
    }
    
    /**
     * 過剰金額を取得します。
     * @return 過剰金額
     */
    public BigDecimal getKajou() {
        return kajou;
    }
    /**
     * 過剰金額を設定します。
     * @param kajou 過剰金額
     */
    public void setKajou(BigDecimal kajou) {
        this.kajou = kajou;
    }
    
    /**
     * 不足金額を取得します。
     * @return 不足金額
     */
    public BigDecimal getFusoku() {
        return fusoku;
    }
    /**
     * 不足金額を設定します。
     * @param fusoku 不足金額
     */
    public void setFusoku(BigDecimal fusoku) {
        this.fusoku = fusoku;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 取消件数を取得します。
     * @return 取消件数
     */
    public BigDecimal getCanKen() {
        return canKen;
    }
    /**
     * 取消件数を設定します。
     * @param canKen 取消件数
     */
    public void setCanKen(BigDecimal canKen) {
        this.canKen = canKen;
    }
    
    /**
     * 取消金額を取得します。
     * @return 取消金額
     */
    public BigDecimal getCanKin() {
        return canKin;
    }
    /**
     * 取消金額を設定します。
     * @param canKin 取消金額
     */
    public void setCanKin(BigDecimal canKin) {
        this.canKin = canKin;
    }
    
    /**
     * 更新者名を取得します。
     * @return 更新者名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 更新者名を設定します。
     * @param userName 更新者名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * 会計区分２件数を取得します。
     * @return 会計区分２件数
     */
    public BigDecimal getKaikeiKen2() {
        return kaikeiKen2;
    }
    /**
     * 会計区分２件数を設定します。
     * @param kaikeiKen2 会計区分２件数
     */
    public void setKaikeiKen2(BigDecimal kaikeiKen2) {
        this.kaikeiKen2 = kaikeiKen2;
    }
    
    /**
     * 会計区分２金額を取得します。
     * @return 会計区分２金額
     */
    public BigDecimal getKaikeiKin2() {
        return kaikeiKin2;
    }
    /**
     * 会計区分２金額を設定します。
     * @param kaikeiKin2 会計区分２金額
     */
    public void setKaikeiKin2(BigDecimal kaikeiKin2) {
        this.kaikeiKin2 = kaikeiKin2;
    }
    
    /**
     * 会計区分３件数を取得します。
     * @return 会計区分３件数
     */
    public BigDecimal getKaikeiKen3() {
        return kaikeiKen3;
    }
    /**
     * 会計区分３件数を設定します。
     * @param kaikeiKen3 会計区分３件数
     */
    public void setKaikeiKen3(BigDecimal kaikeiKen3) {
        this.kaikeiKen3 = kaikeiKen3;
    }
    
    /**
     * 会計区分３金額を取得します。
     * @return 会計区分３金額
     */
    public BigDecimal getKaikeiKin3() {
        return kaikeiKin3;
    }
    /**
     * 会計区分３金額を設定します。
     * @param kaikeiKin3 会計区分３金額
     */
    public void setKaikeiKin3(BigDecimal kaikeiKin3) {
        this.kaikeiKin3 = kaikeiKin3;
    }
    
    /**
     * 会計区分４件数を取得します。
     * @return 会計区分４件数
     */
    public BigDecimal getKaikeiKen4() {
        return kaikeiKen4;
    }
    /**
     * 会計区分４件数を設定します。
     * @param kaikeiKen4 会計区分４件数
     */
    public void setKaikeiKen4(BigDecimal kaikeiKen4) {
        this.kaikeiKen4 = kaikeiKen4;
    }
    
    /**
     * 会計区分４金額を取得します。
     * @return 会計区分４金額
     */
    public BigDecimal getKaikeiKin4() {
        return kaikeiKin4;
    }
    /**
     * 会計区分４金額を設定します。
     * @param kaikeiKin4 会計区分４金額
     */
    public void setKaikeiKin4(BigDecimal kaikeiKin4) {
        this.kaikeiKin4 = kaikeiKin4;
    }
    
    /**
     * 会計区分５件数を取得します。
     * @return 会計区分５件数
     */
    public BigDecimal getKaikeiKen5() {
        return kaikeiKen5;
    }
    /**
     * 会計区分５件数を設定します。
     * @param kaikeiKen5 会計区分５件数
     */
    public void setKaikeiKen5(BigDecimal kaikeiKen5) {
        this.kaikeiKen5 = kaikeiKen5;
    }
    
    /**
     * 会計区分５金額を取得します。
     * @return 会計区分５金額
     */
    public BigDecimal getKaikeiKin5() {
        return kaikeiKin5;
    }
    /**
     * 会計区分５金額を設定します。
     * @param kaikeiKin5 会計区分５金額
     */
    public void setKaikeiKin5(BigDecimal kaikeiKin5) {
        this.kaikeiKin5 = kaikeiKin5;
    }
    
    /**
     * 会計区分６件数を取得します。
     * @return 会計区分６件数
     */
    public BigDecimal getKaikeiKen6() {
        return kaikeiKen6;
    }
    /**
     * 会計区分６件数を設定します。
     * @param kaikeiKen6 会計区分６件数
     */
    public void setKaikeiKen6(BigDecimal kaikeiKen6) {
        this.kaikeiKen6 = kaikeiKen6;
    }
    
    /**
     * 会計区分６金額を取得します。
     * @return 会計区分６金額
     */
    public BigDecimal getKaikeiKin6() {
        return kaikeiKin6;
    }
    /**
     * 会計区分６金額を設定します。
     * @param kaikeiKin6 会計区分６金額
     */
    public void setKaikeiKin6(BigDecimal kaikeiKin6) {
        this.kaikeiKin6 = kaikeiKin6;
    }
    
    /**
     * 会計区分７件数を取得します。
     * @return 会計区分７件数
     */
    public BigDecimal getKaikeiKen7() {
        return kaikeiKen7;
    }
    /**
     * 会計区分７件数を設定します。
     * @param kaikeiKen7 会計区分７件数
     */
    public void setKaikeiKen7(BigDecimal kaikeiKen7) {
        this.kaikeiKen7 = kaikeiKen7;
    }
    
    /**
     * 会計区分７金額を取得します。
     * @return 会計区分７金額
     */
    public BigDecimal getKaikeiKin7() {
        return kaikeiKin7;
    }
    /**
     * 会計区分７金額を設定します。
     * @param kaikeiKin7 会計区分７金額
     */
    public void setKaikeiKin7(BigDecimal kaikeiKin7) {
        this.kaikeiKin7 = kaikeiKin7;
    }
    
    /**
     * 会計区分８件数を取得します。
     * @return 会計区分８件数
     */
    public BigDecimal getKaikeiKen8() {
        return kaikeiKen8;
    }
    /**
     * 会計区分８件数を設定します。
     * @param kaikeiKen8 会計区分８件数
     */
    public void setKaikeiKen8(BigDecimal kaikeiKen8) {
        this.kaikeiKen8 = kaikeiKen8;
    }
    
    /**
     * 会計区分８金額を取得します。
     * @return 会計区分８金額
     */
    public BigDecimal getKaikeiKin8() {
        return kaikeiKin8;
    }
    /**
     * 会計区分８金額を設定します。
     * @param kaikeiKin8 会計区分８金額
     */
    public void setKaikeiKin8(BigDecimal kaikeiKin8) {
        this.kaikeiKin8 = kaikeiKin8;
    }
    
    /**
     * 会計区分９件数を取得します。
     * @return 会計区分９件数
     */
    public BigDecimal getKaikeiKen9() {
        return kaikeiKen9;
    }
    /**
     * 会計区分９件数を設定します。
     * @param kaikeiKen9 会計区分９件数
     */
    public void setKaikeiKen9(BigDecimal kaikeiKen9) {
        this.kaikeiKen9 = kaikeiKen9;
    }
    
    /**
     * 会計区分９金額を取得します。
     * @return 会計区分９金額
     */
    public BigDecimal getKaikeiKin9() {
        return kaikeiKin9;
    }
    /**
     * 会計区分９金額を設定します。
     * @param kaikeiKin9 会計区分９金額
     */
    public void setKaikeiKin9(BigDecimal kaikeiKin9) {
        this.kaikeiKin9 = kaikeiKin9;
    }
    
    /**
     * 会計区分１０件数を取得します。
     * @return 会計区分１０件数
     */
    public BigDecimal getKaikeiKen10() {
        return kaikeiKen10;
    }
    /**
     * 会計区分１０件数を設定します。
     * @param kaikeiKen10 会計区分１０件数
     */
    public void setKaikeiKen10(BigDecimal kaikeiKen10) {
        this.kaikeiKen10 = kaikeiKen10;
    }
    
    /**
     * 会計区分１０金額を取得します。
     * @return 会計区分１０金額
     */
    public BigDecimal getKaikeiKin10() {
        return kaikeiKin10;
    }
    /**
     * 会計区分１０金額を設定します。
     * @param kaikeiKin10 会計区分１０金額
     */
    public void setKaikeiKin10(BigDecimal kaikeiKin10) {
        this.kaikeiKin10 = kaikeiKin10;
    }
    
    /**
     * 会計区分１１件数を取得します。
     * @return 会計区分１１件数
     */
    public BigDecimal getKaikeiKen11() {
        return kaikeiKen11;
    }
    /**
     * 会計区分１１件数を設定します。
     * @param kaikeiKen11 会計区分１１件数
     */
    public void setKaikeiKen11(BigDecimal kaikeiKen11) {
        this.kaikeiKen11 = kaikeiKen11;
    }
    
    /**
     * 会計区分１１金額を取得します。
     * @return 会計区分１１金額
     */
    public BigDecimal getKaikeiKin11() {
        return kaikeiKin11;
    }
    /**
     * 会計区分１１金額を設定します。
     * @param kaikeiKin11 会計区分１１金額
     */
    public void setKaikeiKin11(BigDecimal kaikeiKin11) {
        this.kaikeiKin11 = kaikeiKin11;
    }
    
    /**
     * チケット１販売件数を取得します。
     * @return チケット１販売件数
     */
    public BigDecimal getTicketKen1() {
        return ticketKen1;
    }
    /**
     * チケット１販売件数を設定します。
     * @param ticketKen1 チケット１販売件数
     */
    public void setTicketKen1(BigDecimal ticketKen1) {
        this.ticketKen1 = ticketKen1;
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public BigDecimal getTicketKin1() {
        return ticketKin1;
    }
    /**
     * チケット１販売金額を設定します。
     * @param ticketKin1 チケット１販売金額
     */
    public void setTicketKin1(BigDecimal ticketKin1) {
        this.ticketKin1 = ticketKin1;
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public BigDecimal getTicketKen2() {
        return ticketKen2;
    }
    /**
     * チケット２販売件数を設定します。
     * @param ticketKen2 チケット２販売件数
     */
    public void setTicketKen2(BigDecimal ticketKen2) {
        this.ticketKen2 = ticketKen2;
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public BigDecimal getTicketKin2() {
        return ticketKin2;
    }
    /**
     * チケット２販売金額を設定します。
     * @param ticketKin2 チケット２販売金額
     */
    public void setTicketKin2(BigDecimal ticketKin2) {
        this.ticketKin2 = ticketKin2;
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public BigDecimal getTicketKen3() {
        return ticketKen3;
    }
    /**
     * チケット３販売件数を設定します。
     * @param ticketKen3 チケット３販売件数
     */
    public void setTicketKen3(BigDecimal ticketKen3) {
        this.ticketKen3 = ticketKen3;
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public BigDecimal getTicketKin3() {
        return ticketKin3;
    }
    /**
     * チケット３販売金額を設定します。
     * @param ticketKin3 チケット３販売金額
     */
    public void setTicketKin3(BigDecimal ticketKin3) {
        this.ticketKin3 = ticketKin3;
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public BigDecimal getTicketKen4() {
        return ticketKen4;
    }
    /**
     * チケット４販売件数を設定します。
     * @param ticketKen4 チケット４販売件数
     */
    public void setTicketKen4(BigDecimal ticketKen4) {
        this.ticketKen4 = ticketKen4;
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public BigDecimal getTicketKin4() {
        return ticketKin4;
    }
    /**
     * チケット４販売金額を設定します。
     * @param ticketKin4 チケット４販売金額
     */
    public void setTicketKin4(BigDecimal ticketKin4) {
        this.ticketKin4 = ticketKin4;
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public BigDecimal getTicketKen5() {
        return ticketKen5;
    }
    /**
     * チケット５販売件数を設定します。
     * @param ticketKen5 チケット５販売件数
     */
    public void setTicketKen5(BigDecimal ticketKen5) {
        this.ticketKen5 = ticketKen5;
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public BigDecimal getTicketKin5() {
        return ticketKin5;
    }
    /**
     * チケット５販売金額を設定します。
     * @param ticketKin5 チケット５販売金額
     */
    public void setTicketKin5(BigDecimal ticketKin5) {
        this.ticketKin5 = ticketKin5;
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public BigDecimal getTicketKen6() {
        return ticketKen6;
    }
    /**
     * チケット６販売件数を設定します。
     * @param ticketKen6 チケット６販売件数
     */
    public void setTicketKen6(BigDecimal ticketKen6) {
        this.ticketKen6 = ticketKen6;
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public BigDecimal getTicketKin6() {
        return ticketKin6;
    }
    /**
     * チケット６販売金額を設定します。
     * @param ticketKin6 チケット６販売金額
     */
    public void setTicketKin6(BigDecimal ticketKin6) {
        this.ticketKin6 = ticketKin6;
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public BigDecimal getTicketKen7() {
        return ticketKen7;
    }
    /**
     * チケット７販売件数を設定します。
     * @param ticketKen7 チケット７販売件数
     */
    public void setTicketKen7(BigDecimal ticketKen7) {
        this.ticketKen7 = ticketKen7;
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public BigDecimal getTicketKin7() {
        return ticketKin7;
    }
    /**
     * チケット７販売金額を設定します。
     * @param ticketKin7 チケット７販売金額
     */
    public void setTicketKin7(BigDecimal ticketKin7) {
        this.ticketKin7 = ticketKin7;
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public BigDecimal getTicketKen8() {
        return ticketKen8;
    }
    /**
     * チケット８販売件数を設定します。
     * @param ticketKen8 チケット８販売件数
     */
    public void setTicketKen8(BigDecimal ticketKen8) {
        this.ticketKen8 = ticketKen8;
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public BigDecimal getTicketKin8() {
        return ticketKin8;
    }
    /**
     * チケット８販売金額を設定します。
     * @param ticketKin8 チケット８販売金額
     */
    public void setTicketKin8(BigDecimal ticketKin8) {
        this.ticketKin8 = ticketKin8;
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public BigDecimal getTicketKen9() {
        return ticketKen9;
    }
    /**
     * チケット９販売件数を設定します。
     * @param ticketKen9 チケット９販売件数
     */
    public void setTicketKen9(BigDecimal ticketKen9) {
        this.ticketKen9 = ticketKen9;
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public BigDecimal getTicketKin9() {
        return ticketKin9;
    }
    /**
     * チケット９販売金額を設定します。
     * @param ticketKin9 チケット９販売金額
     */
    public void setTicketKin9(BigDecimal ticketKin9) {
        this.ticketKin9 = ticketKin9;
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public BigDecimal getTicketKen10() {
        return ticketKen10;
    }
    /**
     * チケット１０販売件数を設定します。
     * @param ticketKen10 チケット１０販売件数
     */
    public void setTicketKen10(BigDecimal ticketKen10) {
        this.ticketKen10 = ticketKen10;
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public BigDecimal getTicketKin10() {
        return ticketKin10;
    }
    /**
     * チケット１０販売金額を設定します。
     * @param ticketKin10 チケット１０販売金額
     */
    public void setTicketKin10(BigDecimal ticketKin10) {
        this.ticketKin10 = ticketKin10;
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public BigDecimal getTicketKen11() {
        return ticketKen11;
    }
    /**
     * チケット１１販売件数を設定します。
     * @param ticketKen11 チケット１１販売件数
     */
    public void setTicketKen11(BigDecimal ticketKen11) {
        this.ticketKen11 = ticketKen11;
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public BigDecimal getTicketKin11() {
        return ticketKin11;
    }
    /**
     * チケット１１販売金額を設定します。
     * @param ticketKin11 チケット１１販売金額
     */
    public void setTicketKin11(BigDecimal ticketKin11) {
        this.ticketKin11 = ticketKin11;
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public BigDecimal getTicketKen12() {
        return ticketKen12;
    }
    /**
     * チケット１２販売件数を設定します。
     * @param ticketKen12 チケット１２販売件数
     */
    public void setTicketKen12(BigDecimal ticketKen12) {
        this.ticketKen12 = ticketKen12;
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public BigDecimal getTicketKin12() {
        return ticketKin12;
    }
    /**
     * チケット１２販売金額を設定します。
     * @param ticketKin12 チケット１２販売金額
     */
    public void setTicketKin12(BigDecimal ticketKin12) {
        this.ticketKin12 = ticketKin12;
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public BigDecimal getTicketKen13() {
        return ticketKen13;
    }
    /**
     * チケット１３販売件数を設定します。
     * @param ticketKen13 チケット１３販売件数
     */
    public void setTicketKen13(BigDecimal ticketKen13) {
        this.ticketKen13 = ticketKen13;
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public BigDecimal getTicketKin13() {
        return ticketKin13;
    }
    /**
     * チケット１３販売金額を設定します。
     * @param ticketKin13 チケット１３販売金額
     */
    public void setTicketKin13(BigDecimal ticketKin13) {
        this.ticketKin13 = ticketKin13;
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public BigDecimal getTicketKen14() {
        return ticketKen14;
    }
    /**
     * チケット１４販売件数を設定します。
     * @param ticketKen14 チケット１４販売件数
     */
    public void setTicketKen14(BigDecimal ticketKen14) {
        this.ticketKen14 = ticketKen14;
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public BigDecimal getTicketKin14() {
        return ticketKin14;
    }
    /**
     * チケット１４販売金額を設定します。
     * @param ticketKin14 チケット１４販売金額
     */
    public void setTicketKin14(BigDecimal ticketKin14) {
        this.ticketKin14 = ticketKin14;
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public BigDecimal getTicketKen15() {
        return ticketKen15;
    }
    /**
     * チケット１５販売件数を設定します。
     * @param ticketKen15 チケット１５販売件数
     */
    public void setTicketKen15(BigDecimal ticketKen15) {
        this.ticketKen15 = ticketKen15;
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public BigDecimal getTicketKin15() {
        return ticketKin15;
    }
    /**
     * チケット１５販売金額を設定します。
     * @param ticketKin15 チケット１５販売金額
     */
    public void setTicketKin15(BigDecimal ticketKin15) {
        this.ticketKin15 = ticketKin15;
    }
    
    /**
     * 値引１件数を取得します。
     * @return 値引１件数
     */
    public BigDecimal getNebikiKen1() {
        return nebikiKen1;
    }
    /**
     * 値引１件数を設定します。
     * @param nebikiKen1 値引１件数
     */
    public void setNebikiKen1(BigDecimal nebikiKen1) {
        this.nebikiKen1 = nebikiKen1;
    }
    
    /**
     * 値引１金額を取得します。
     * @return 値引１金額
     */
    public BigDecimal getNebikiKin1() {
        return nebikiKin1;
    }
    /**
     * 値引１金額を設定します。
     * @param nebikiKin1 値引１金額
     */
    public void setNebikiKin1(BigDecimal nebikiKin1) {
        this.nebikiKin1 = nebikiKin1;
    }
    
    /**
     * 値引１税額を取得します。
     * @return 値引１税額
     */
    public BigDecimal getNebikiTax1() {
        return nebikiTax1;
    }
    /**
     * 値引１税額を設定します。
     * @param nebikiTax1 値引１税額
     */
    public void setNebikiTax1(BigDecimal nebikiTax1) {
        this.nebikiTax1 = nebikiTax1;
    }
    
    /**
     * 値引２件数を取得します。
     * @return 値引２件数
     */
    public BigDecimal getNebikiKen2() {
        return nebikiKen2;
    }
    /**
     * 値引２件数を設定します。
     * @param nebikiKen2 値引２件数
     */
    public void setNebikiKen2(BigDecimal nebikiKen2) {
        this.nebikiKen2 = nebikiKen2;
    }
    
    /**
     * 値引２金額を取得します。
     * @return 値引２金額
     */
    public BigDecimal getNebikiKin2() {
        return nebikiKin2;
    }
    /**
     * 値引２金額を設定します。
     * @param nebikiKin2 値引２金額
     */
    public void setNebikiKin2(BigDecimal nebikiKin2) {
        this.nebikiKin2 = nebikiKin2;
    }
    
    /**
     * 値引２税額を取得します。
     * @return 値引２税額
     */
    public BigDecimal getNebikiTax2() {
        return nebikiTax2;
    }
    /**
     * 値引２税額を設定します。
     * @param nebikiTax2 値引２税額
     */
    public void setNebikiTax2(BigDecimal nebikiTax2) {
        this.nebikiTax2 = nebikiTax2;
    }
    
    /**
     * 値引３件数を取得します。
     * @return 値引３件数
     */
    public String getNebikiKen3() {
        return nebikiKen3;
    }
    /**
     * 値引３件数を設定します。
     * @param nebikiKen3 値引３件数
     */
    public void setNebikiKen3(String nebikiKen3) {
        this.nebikiKen3 = nebikiKen3;
    }
    
    /**
     * 値引３金額を取得します。
     * @return 値引３金額
     */
    public String getNebikiKin3() {
        return nebikiKin3;
    }
    /**
     * 値引３金額を設定します。
     * @param nebikiKin3 値引３金額
     */
    public void setNebikiKin3(String nebikiKin3) {
        this.nebikiKin3 = nebikiKin3;
    }
    
    /**
     * 値引３税額を取得します。
     * @return 値引３税額
     */
    public String getNebikiTax3() {
        return nebikiTax3;
    }
    /**
     * 値引３税額を設定します。
     * @param nebikiTax3 値引３税額
     */
    public void setNebikiTax3(String nebikiTax3) {
        this.nebikiTax3 = nebikiTax3;
    }
    
    /**
     * 値引４件数を取得します。
     * @return 値引４件数
     */
    public String getNebikiKen4() {
        return nebikiKen4;
    }
    /**
     * 値引４件数を設定します。
     * @param nebikiKen4 値引４件数
     */
    public void setNebikiKen4(String nebikiKen4) {
        this.nebikiKen4 = nebikiKen4;
    }
    
    /**
     * 値引４金額を取得します。
     * @return 値引４金額
     */
    public BigDecimal getNebikiKin4() {
        return nebikiKin4;
    }
    /**
     * 値引４金額を設定します。
     * @param nebikiKin4 値引４金額
     */
    public void setNebikiKin4(BigDecimal nebikiKin4) {
        this.nebikiKin4 = nebikiKin4;
    }
    
    /**
     * 値引４税額を取得します。
     * @return 値引４税額
     */
    public BigDecimal getNebikiTax4() {
        return nebikiTax4;
    }
    /**
     * 値引４税額を設定します。
     * @param nebikiTax4 値引４税額
     */
    public void setNebikiTax4(BigDecimal nebikiTax4) {
        this.nebikiTax4 = nebikiTax4;
    }
    
    /**
     * 値引５件数を取得します。
     * @return 値引５件数
     */
    public BigDecimal getNebikiKen5() {
        return nebikiKen5;
    }
    /**
     * 値引５件数を設定します。
     * @param nebikiKen5 値引５件数
     */
    public void setNebikiKen5(BigDecimal nebikiKen5) {
        this.nebikiKen5 = nebikiKen5;
    }
    
    /**
     * 値引５金額を取得します。
     * @return 値引５金額
     */
    public BigDecimal getNebikiKin5() {
        return nebikiKin5;
    }
    /**
     * 値引５金額を設定します。
     * @param nebikiKin5 値引５金額
     */
    public void setNebikiKin5(BigDecimal nebikiKin5) {
        this.nebikiKin5 = nebikiKin5;
    }
    
    /**
     * 値引５税額を取得します。
     * @return 値引５税額
     */
    public BigDecimal getNebikiTax5() {
        return nebikiTax5;
    }
    /**
     * 値引５税額を設定します。
     * @param nebikiTax5 値引５税額
     */
    public void setNebikiTax5(BigDecimal nebikiTax5) {
        this.nebikiTax5 = nebikiTax5;
    }
    
    /**
     * 値引６件数を取得します。
     * @return 値引６件数
     */
    public BigDecimal getNebikiKen6() {
        return nebikiKen6;
    }
    /**
     * 値引６件数を設定します。
     * @param nebikiKen6 値引６件数
     */
    public void setNebikiKen6(BigDecimal nebikiKen6) {
        this.nebikiKen6 = nebikiKen6;
    }
    
    /**
     * 値引６金額を取得します。
     * @return 値引６金額
     */
    public BigDecimal getNebikiKin6() {
        return nebikiKin6;
    }
    /**
     * 値引６金額を設定します。
     * @param nebikiKin6 値引６金額
     */
    public void setNebikiKin6(BigDecimal nebikiKin6) {
        this.nebikiKin6 = nebikiKin6;
    }
    
    /**
     * 値引６税額を取得します。
     * @return 値引６税額
     */
    public BigDecimal getNebikiTax6() {
        return nebikiTax6;
    }
    /**
     * 値引６税額を設定します。
     * @param nebikiTax6 値引６税額
     */
    public void setNebikiTax6(BigDecimal nebikiTax6) {
        this.nebikiTax6 = nebikiTax6;
    }
    
    /**
     * 値引７件数を取得します。
     * @return 値引７件数
     */
    public BigDecimal getNebikiKen7() {
        return nebikiKen7;
    }
    /**
     * 値引７件数を設定します。
     * @param nebikiKen7 値引７件数
     */
    public void setNebikiKen7(BigDecimal nebikiKen7) {
        this.nebikiKen7 = nebikiKen7;
    }
    
    /**
     * 値引７金額を取得します。
     * @return 値引７金額
     */
    public BigDecimal getNebikiKin7() {
        return nebikiKin7;
    }
    /**
     * 値引７金額を設定します。
     * @param nebikiKin7 値引７金額
     */
    public void setNebikiKin7(BigDecimal nebikiKin7) {
        this.nebikiKin7 = nebikiKin7;
    }
    
    /**
     * 値引７税額を取得します。
     * @return 値引７税額
     */
    public BigDecimal getNebikiTax7() {
        return nebikiTax7;
    }
    /**
     * 値引７税額を設定します。
     * @param nebikiTax7 値引７税額
     */
    public void setNebikiTax7(BigDecimal nebikiTax7) {
        this.nebikiTax7 = nebikiTax7;
    }
    
    /**
     * 値引８件数を取得します。
     * @return 値引８件数
     */
    public BigDecimal getNebikiKen8() {
        return nebikiKen8;
    }
    /**
     * 値引８件数を設定します。
     * @param nebikiKen8 値引８件数
     */
    public void setNebikiKen8(BigDecimal nebikiKen8) {
        this.nebikiKen8 = nebikiKen8;
    }
    
    /**
     * 値引８金額を取得します。
     * @return 値引８金額
     */
    public BigDecimal getNebikiKin8() {
        return nebikiKin8;
    }
    /**
     * 値引８金額を設定します。
     * @param nebikiKin8 値引８金額
     */
    public void setNebikiKin8(BigDecimal nebikiKin8) {
        this.nebikiKin8 = nebikiKin8;
    }
    
    /**
     * 値引８税額を取得します。
     * @return 値引８税額
     */
    public BigDecimal getNebikiTax8() {
        return nebikiTax8;
    }
    /**
     * 値引８税額を設定します。
     * @param nebikiTax8 値引８税額
     */
    public void setNebikiTax8(BigDecimal nebikiTax8) {
        this.nebikiTax8 = nebikiTax8;
    }
    
    /**
     * 値引９件数を取得します。
     * @return 値引９件数
     */
    public BigDecimal getNebikiKen9() {
        return nebikiKen9;
    }
    /**
     * 値引９件数を設定します。
     * @param nebikiKen9 値引９件数
     */
    public void setNebikiKen9(BigDecimal nebikiKen9) {
        this.nebikiKen9 = nebikiKen9;
    }
    
    /**
     * 値引９金額を取得します。
     * @return 値引９金額
     */
    public BigDecimal getNebikiKin9() {
        return nebikiKin9;
    }
    /**
     * 値引９金額を設定します。
     * @param nebikiKin9 値引９金額
     */
    public void setNebikiKin9(BigDecimal nebikiKin9) {
        this.nebikiKin9 = nebikiKin9;
    }
    
    /**
     * 値引９税額を取得します。
     * @return 値引９税額
     */
    public BigDecimal getNebikiTax9() {
        return nebikiTax9;
    }
    /**
     * 値引９税額を設定します。
     * @param nebikiTax9 値引９税額
     */
    public void setNebikiTax9(BigDecimal nebikiTax9) {
        this.nebikiTax9 = nebikiTax9;
    }

    ///////////////////////////////////////////////////////////////////////

    /**
     * [店コード]＋[店名称]を取得します。
     */
    public String getMiseCdName(){
        String tmp = "";
        if(miseCd != null && miseName != null){
            tmp = miseCd + " " + miseName;
        }else if(miseCd != null){
            tmp = miseCd + " ";
        }
        return tmp;
    }

    /**
     * [店コード]＋[店名称]＋[(CLOSE)]を取得します。
     * 店がCLOSE店の場合のみCLOSEを表示します。
     */
    public String getMiseCdNameClose(){
        String tmp = "";
        if(miseCd != null && miseName != null){
            tmp = miseCd + " " + miseName;
        }else if(miseCd != null){
            tmp = miseCd + " ";
        }

        //CLOSE店の時
        if(closeFlg == 1){
            tmp = tmp + "(CLOSE)";
        }
        return tmp;
    }

    public BigDecimal getGenkinKabusoku(){
        return kajou.subtract(fusoku);
    }
    /**
     * @return upNo を戻します。
     */
    public String getBt97UpNo() {
        return bt97UpNo;
    }
    /**
     * @param upNo 設定する upNo。
     */
    public void setBt97UpNo(String upNo) {
        this.bt97UpNo = upNo;
    }

    /**
     * @return upNo を戻します。
     */
    public String getBt96UpNo() {
        return bt96UpNo;
    }
    /**
     * @param upNo 設定する upNo。
     */
    public void setBt96UpNo(String upNo) {
        this.bt96UpNo = upNo;
    }

    ///////////////////////////////////////////////////////////////////////
    
    /**
     * 更新項目NO文字列より、指定したインデックスの更新NO値を取得する。
     * @param index
     * @return
     */
    private String getBt97UpNoVal(int index){

        String ret = "";

        if(bt97UpNo != null && bt97UpNo.length() > 0){
            
            try{
                char[] array = bt97UpNo.toCharArray();
                char chr = array[index -1];
                ret = String.valueOf(chr);
                
            }catch(ArrayIndexOutOfBoundsException ex){
            }
        }
        return ret;
    }

    /**
     * 更新項目NO文字列より、指定したインデックスの更新NO値を取得する。
     * @param index
     * @return
     */
    private String getBt96UpNoVal(int index){

        String ret = "";

        if(bt96UpNo != null && bt96UpNo.length() > 0){
            
            try{
                char[] array = bt96UpNo.toCharArray();
                char chr = array[index -1];
                ret = String.valueOf(chr);
                
            }catch(ArrayIndexOutOfBoundsException ex){
            }
        }
        return ret;
    }

    /**
     * 更新フラグ(売上高)を取得します。
     */
    public String getUriageUpFlg() {
        return getBt97UpNoVal(1);
    }

    /**
     * 更新フラグ(消費税)を取得します。
     */
    public String getTaxUpFlg(){
        return getBt97UpNoVal(6);
    }

    /**
     * 更新フラグ(現金)を取得します。
     */
    public String getGenkinKinUpFlg(){
        return getBt97UpNoVal(14);
    }

    /**
     * 更新フラグ(実現金在高)を取得します。
     */
    public String getAridakaJituUpFlg(){
        return getBt97UpNoVal(68);
    }

    /**
     * 更新フラグ(実現過不足)を取得します。
     */
    public String getGenkinKabusokuUpFlg(){

        String kajou  = getBt97UpNoVal(69);
        String fusoku = getBt97UpNoVal(70);

        if(kajou.equals("1") || fusoku.equals("1")){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 更新フラグ(客数)を取得します。
     */
    public String getKyakusuUpFlg(){
        return getBt97UpNoVal(74);
    }

    /**
     * 更新フラグ(取消件数)を取得します。
     */
    public String getCanKenUpFlg(){
        return getBt97UpNoVal(71);
    }

    /**
     * 更新フラグ(取消金額)を取得します。
     */
    public String getCanKinUpFlg(){
        return getBt97UpNoVal(72);
    }

    /**
     * 更新フラグ(会計区分2件数)を取得します。
     */
    public String getKaikeiKen2UpFlg(){
        return getBt97UpNoVal(15);
    }
    /**
     * 更新フラグ(会計区分2金額)を取得します。
     */
    public String getKaikeiKin2UpFlg(){
        return getBt97UpNoVal(16);
    }

    /**
     * 更新フラグ(会計区分3件数)を取得します。
     */
    public String getKaikeiKen3UpFlg(){
        return getBt97UpNoVal(17);
    }
    /**
     * 更新フラグ(会計区分3金額)を取得します。
     */
    public String getKaikeiKin3UpFlg(){
        return getBt97UpNoVal(18);
    }

    /**
     * 更新フラグ(会計区分4件数)を取得します。
     */
    public String getKaikeiKen4UpFlg(){
        return getBt97UpNoVal(19);
    }
    /**
     * 更新フラグ(会計区分4金額)を取得します。
     */
    public String getKaikeiKin4UpFlg(){
        return getBt97UpNoVal(20);
    }

    /**
     * 更新フラグ(会計区分5件数)を取得します。
     */
    public String getKaikeiKen5UpFlg(){
        return getBt97UpNoVal(21);
    }
    /**
     * 更新フラグ(会計区分5金額)を取得します。
     */
    public String getKaikeiKin5UpFlg(){
        return getBt97UpNoVal(22);
    }

    /**
     * 更新フラグ(会計区分6件数)を取得します。
     */
    public String getKaikeiKen6UpFlg(){
        return getBt97UpNoVal(23);
    }
    /**
     * 更新フラグ(会計区分6金額)を取得します。
     */
    public String getKaikeiKin6UpFlg(){
        return getBt97UpNoVal(24);
    }

    /**
     * 更新フラグ(会計区分7件数)を取得します。
     */
    public String getKaikeiKen7UpFlg(){
        return getBt97UpNoVal(25);
    }
    /**
     * 更新フラグ(会計区分7金額)を取得します。
     */
    public String getKaikeiKin7UpFlg(){
        return getBt97UpNoVal(26);
    }

    /**
     * 更新フラグ(会計区分8件数)を取得します。
     */
    public String getKaikeiKen8UpFlg(){
        return getBt97UpNoVal(27);
    }
    /**
     * 更新フラグ(会計区分8金額)を取得します。
     */
    public String getKaikeiKin8UpFlg(){
        return getBt97UpNoVal(28);
    }

    /**
     * 更新フラグ(会計区分9件数)を取得します。
     */
    public String getKaikeiKen9UpFlg(){
        return getBt97UpNoVal(29);
    }
    /**
     * 更新フラグ(会計区分9金額)を取得します。
     */
    public String getKaikeiKin9UpFlg(){
        return getBt97UpNoVal(30);
    }

    /**
     * 更新フラグ(会計区分10件数)を取得します。
     */
    public String getKaikeiKen10UpFlg(){
        return getBt97UpNoVal(31);
    }
    /**
     * 更新フラグ(会計区分10金額)を取得します。
     */
    public String getKaikeiKin10UpFlg(){
        return getBt97UpNoVal(32);
    }

    /**
     * 更新フラグ(会計区分11件数)を取得します。
     */
    public String getKaikeiKen11UpFlg(){
        return getBt97UpNoVal(33);
    }
    /**
     * 更新フラグ(会計区分11金額)を取得します。
     */
    public String getKaikeiKin11UpFlg(){
        return getBt97UpNoVal(34);
    }

    /**
     * 更新フラグ(チケット1販売件数)を取得します。
     */
    public String getTicketKen1UpFlg(){
        return getBt97UpNoVal(35);
    }
    /**
     * 更新フラグ(チケット1販売金額)を取得します。
     */
    public String getTicketKin1UpFlg(){
        return getBt97UpNoVal(36);
    }
    /**
     * 更新フラグ(チケット2販売件数)を取得します。
     */
    public String getTicketKen2UpFlg(){
        return getBt97UpNoVal(37);
    }
    /**
     * 更新フラグ(チケット2販売金額)を取得します。
     */
    public String getTicketKin2UpFlg(){
        return getBt97UpNoVal(38);
    }
    /**
     * 更新フラグ(チケット3販売件数)を取得します。
     */
    public String getTicketKen3UpFlg(){
        return getBt97UpNoVal(39);
    }
    /**
     * 更新フラグ(チケット3販売金額)を取得します。
     */
    public String getTicketKin3UpFlg(){
        return getBt97UpNoVal(40);
    }
    /**
     * 更新フラグ(チケット4販売件数)を取得します。
     */
    public String getTicketKen4UpFlg(){
        return getBt97UpNoVal(41);
    }
    /**
     * 更新フラグ(チケット4販売金額)を取得します。
     */
    public String getTicketKin4UpFlg(){
        return getBt97UpNoVal(42);
    }
    /**
     * 更新フラグ(チケット5販売件数)を取得します。
     */
    public String getTicketKen5UpFlg(){
        return getBt97UpNoVal(43);
    }
    /**
     * 更新フラグ(チケット5販売金額)を取得します。
     */
    public String getTicketKin5UpFlg(){
        return getBt97UpNoVal(44);
    }
    /**
     * 更新フラグ(チケット6販売件数)を取得します。
     */
    public String getTicketKen6UpFlg(){
        return getBt97UpNoVal(45);
    }
    /**
     * 更新フラグ(チケット6販売金額)を取得します。
     */
    public String getTicketKin6UpFlg(){
        return getBt97UpNoVal(46);
    }
    /**
     * 更新フラグ(チケット7販売件数)を取得します。
     */
    public String getTicketKen7UpFlg(){
        return getBt97UpNoVal(47);
    }
    /**
     * 更新フラグ(チケット7販売金額)を取得します。
     */
    public String getTicketKin7UpFlg(){
        return getBt97UpNoVal(48);
    }
    /**
     * 更新フラグ(チケット8販売件数)を取得します。
     */
    public String getTicketKen8UpFlg(){
        return getBt97UpNoVal(49);
    }
    /**
     * 更新フラグ(チケット8販売金額)を取得します。
     */
    public String getTicketKin8UpFlg(){
        return getBt97UpNoVal(50);
    }
    /**
     * 更新フラグ(チケット9販売件数)を取得します。
     */
    public String getTicketKen9UpFlg(){
        return getBt97UpNoVal(51);
    }
    /**
     * 更新フラグ(チケット9販売金額)を取得します。
     */
    public String getTicketKin9UpFlg(){
        return getBt97UpNoVal(52);
    }
    /**
     * 更新フラグ(チケット10販売件数)を取得します。
     */
    public String getTicketKen10UpFlg(){
        return getBt97UpNoVal(53);
    }
    /**
     * 更新フラグ(チケット10販売金額)を取得します。
     */
    public String getTicketKin10UpFlg(){
        return getBt97UpNoVal(54);
    }
    /**
     * 更新フラグ(チケット11販売件数)を取得します。
     */
    public String getTicketKen11UpFlg(){
        return getBt97UpNoVal(55);
    }
    /**
     * 更新フラグ(チケット11販売金額)を取得します。
     */
    public String getTicketKin11UpFlg(){
        return getBt97UpNoVal(56);
    }
    /**
     * 更新フラグ(チケット12販売件数)を取得します。
     */
    public String getTicketKen12UpFlg(){
        return getBt97UpNoVal(57);
    }
    /**
     * 更新フラグ(チケット12販売金額)を取得します。
     */
    public String getTicketKin12UpFlg(){
        return getBt97UpNoVal(58);
    }
    /**
     * 更新フラグ(チケット13販売件数)を取得します。
     */
    public String getTicketKen13UpFlg(){
        return getBt97UpNoVal(59);
    }
    /**
     * 更新フラグ(チケット13販売金額)を取得します。
     */
    public String getTicketKin13UpFlg(){
        return getBt97UpNoVal(60);
    }
    /**
     * 更新フラグ(チケット14販売件数)を取得します。
     */
    public String getTicketKen14UpFlg(){
        return getBt97UpNoVal(61);
    }
    /**
     * 更新フラグ(チケット14販売金額)を取得します。
     */
    public String getTicketKin14UpFlg(){
        return getBt97UpNoVal(62);
    }
    /**
     * 更新フラグ(チケット15販売件数)を取得します。
     */
    public String getTicketKen15UpFlg(){
        return getBt97UpNoVal(63);
    }
    /**
     * 更新フラグ(チケット15販売金額)を取得します。
     */
    public String getTicketKin15UpFlg(){
        return getBt97UpNoVal(64);
    }

    /**
     * 更新フラグ(値引1件数)を取得します。
     */
    public String getNebikiKen1UpFlg(){
        return getBt96UpNoVal(1);
    }
    /**
     * 更新フラグ(値引1金額)を取得します。
     */
    public String getNebikiKin1UpFlg(){
        return getBt96UpNoVal(2);
    }
    /**
     * 更新フラグ(値引1税額)を取得します。
     */
    public String getNebikiTax1UpFlg(){
        return getBt96UpNoVal(3);
    }
    /**
     * 更新フラグ(値引2件数)を取得します。
     */
    public String getNebikiKen2UpFlg(){
        return getBt96UpNoVal(4);
    }
    /**
     * 更新フラグ(値引2金額)を取得します。
     */
    public String getNebikiKin2UpFlg(){
        return getBt96UpNoVal(5);
    }
    /**
     * 更新フラグ(値引2税額)を取得します。
     */
    public String getNebikiTax2UpFlg(){
        return getBt96UpNoVal(6);
    }
    /**
     * 更新フラグ(値引3件数)を取得します。
     */
    public String getNebikiKen3UpFlg(){
        return getBt96UpNoVal(7);
    }
    /**
     * 更新フラグ(値引3金額)を取得します。
     */
    public String getNebikiKin3UpFlg(){
        return getBt96UpNoVal(8);
    }
    /**
     * 更新フラグ(値引3税額)を取得します。
     */
    public String getNebikiTax3UpFlg(){
        return getBt96UpNoVal(9);
    }
    /**
     * 更新フラグ(値引4件数)を取得します。
     */
    public String getNebikiKen4UpFlg(){
        return getBt96UpNoVal(10);
    }
    /**
     * 更新フラグ(値引4金額)を取得します。
     */
    public String getNebikiKin4UpFlg(){
        return getBt96UpNoVal(11);
    }
    /**
     * 更新フラグ(値引4税額)を取得します。
     */
    public String getNebikiTax4UpFlg(){
        return getBt96UpNoVal(12);
    }
    /**
     * 更新フラグ(値引5件数)を取得します。
     */
    public String getNebikiKen5UpFlg(){
        return getBt96UpNoVal(13);
    }
    /**
     * 更新フラグ(値引5金額)を取得します。
     */
    public String getNebikiKin5UpFlg(){
        return getBt96UpNoVal(14);
    }
    /**
     * 更新フラグ(値引5税額)を取得します。
     */
    public String getNebikiTax5UpFlg(){
        return getBt96UpNoVal(15);
    }
    /**
     * 更新フラグ(値引6件数)を取得します。
     */
    public String getNebikiKen6UpFlg(){
        return getBt96UpNoVal(16);
    }
    /**
     * 更新フラグ(値引6金額)を取得します。
     */
    public String getNebikiKin6UpFlg(){
        return getBt96UpNoVal(17);
    }
    /**
     * 更新フラグ(値引6税額)を取得します。
     */
    public String getNebikiTax6UpFlg(){
        return getBt96UpNoVal(18);
    }
    /**
     * 更新フラグ(値引7件数)を取得します。
     */
    public String getNebikiKen7UpFlg(){
        return getBt96UpNoVal(19);
    }
    /**
     * 更新フラグ(値引7金額)を取得します。
     */
    public String getNebikiKin7UpFlg(){
        return getBt96UpNoVal(20);
    }
    /**
     * 更新フラグ(値引7税額)を取得します。
     */
    public String getNebikiTax7UpFlg(){
        return getBt96UpNoVal(21);
    }
    /**
     * 更新フラグ(値引8件数)を取得します。
     */
    public String getNebikiKen8UpFlg(){
        return getBt96UpNoVal(22);
    }
    /**
     * 更新フラグ(値引8金額)を取得します。
     */
    public String getNebikiKin8UpFlg(){
        return getBt96UpNoVal(23);
    }
    /**
     * 更新フラグ(値引8税額)を取得します。
     */
    public String getNebikiTax8UpFlg(){
        return getBt96UpNoVal(24);
    }
    /**
     * 更新フラグ(値引9件数)を取得します。
     */
    public String getNebikiKen9UpFlg(){
        return getBt96UpNoVal(25);
    }
    /**
     * 更新フラグ(値引9金額)を取得します。
     */
    public String getNebikiKin9UpFlg(){
        return getBt96UpNoVal(26);
    }
    /**
     * 更新フラグ(値引9税額)を取得します。
     */
    public String getNebikiTax9UpFlg(){
        return getBt96UpNoVal(27);
    }
}
