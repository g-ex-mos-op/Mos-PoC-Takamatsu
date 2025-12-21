package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;

import java.math.BigDecimal;

/**
 * 売上金管理月報情報エンティティクラス
 * 
 * @author xjung
 */
public class ProceedsManageGepoInfo {
    /** テーブル名称 */   
    public static final String TABLE = "BT65SADY";
    /** カラム名称：営業日 */     
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    /** カラム名称：売上 */      
    public static final String uriage_COLUMN = "URIAGE";
    //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** カラム名称：通常税率対象売上 */
    public static final String uriage1_COLUMN = "URIAGE1";
    /** カラム名称：軽減税率対象売上 */
    public static final String uriage2_COLUMN = "URIAGE2";
    //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** カラム名称：値引合計 */
    public static final String nebiki_COLUMN = "NEBIKI";
    /** カラム名称：消費税 */     
    public static final String tax_COLUMN = "TAX";
    //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** カラム名称：通常税率対象消費税 */
    public static final String tax1_COLUMN = "TAX1";
    /** カラム名称：軽減税率対象消費税 */
    public static final String tax2_COLUMN = "TAX2";
    //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
    /** カラム名称：金券販売等 */
    public static final String gcHanKin_COLUMN = "GC_HAN_KIN";
    /** カラム名称：前受・売掛件数 */     
    public static final String urikakeKen_COLUMN = "URIKAKE_KEN";
    /** カラム名称：前受・売掛金額 */     
    public static final String urikakeKin_COLUMN = "URIKAKE_KIN";
    /** カラム名称：Ｇｶｰﾄﾞ回収 */     
    public static final String gcUriKin_COLUMN = "GC_URI_KIN";
    /** カラム名称：招待券回収 */      
    public static final String invKin_COLUMN = "INV_KIN";
    /** カラム名称：現金合計 */     
    public static final String genkin_COLUMN = "GENKIN";
    /** カラム名称：現金在高 */     
    public static final String aridakaJitu_COLUMN = "ARIDAKA_JITU";
    /** カラム名称：過剰 */     
    public static final String kajou_COLUMN = "KAJOU";
    /** カラム名称：不足 */    
    public static final String fusoku_COLUMN = "FUSOKU";
    /** カラム名称：客数 */    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    /** カラム名称：売上取消件数 */     
    public static final String canKen_COLUMN = "CAN_KEN";
    /** カラム名称：売上取消金額 */     
    public static final String canKin_COLUMN = "CAN_KIN";
    /** カラム名称：ｵｰﾀﾞｰ票ｷｬﾝｾﾙ件数 */      
    public static final String allcanKen_COLUMN = "ALLCAN_KEN";
    /** カラム名称：ｵｰﾀﾞｰ票ｷｬﾝｾﾙ金額 */     
    public static final String allcanKin_COLUMN = "ALLCAN_KIN";
    /** カラム名称：会計合計４件数 */      
    public static final String kaikeiKen4_COLUMN = "KAIKEI_KEN_4";
    /** カラム名称：会計合計４金額 */      
    public static final String kaikeiKin4_COLUMN = "KAIKEI_KIN_4";
    /** カラム名称：会計合計５件数 */      
    public static final String kaikeiKen5_COLUMN = "KAIKEI_KEN_5";
    /** カラム名称：会計合計５金額 */     
    public static final String kaikeiKin5_COLUMN = "KAIKEI_KIN_5";
    /** カラム名称：会計合計６件数 */     
    public static final String kaikeiKen6_COLUMN = "KAIKEI_KEN_6";
    /** カラム名称：会計合計６金額 */     
    public static final String kaikeiKin6_COLUMN = "KAIKEI_KIN_6";
    /** カラム名称：会計合計７件数 */     
    public static final String kaikeiKen7_COLUMN = "KAIKEI_KEN_7";
    /** カラム名称：会計合計７金額 */     
    public static final String kaikeiKin7_COLUMN = "KAIKEI_KIN_7";
    /** カラム名称：会計合計８件数 */      
    public static final String kaikeiKen8_COLUMN = "KAIKEI_KEN_8";
    /** カラム名称：会計合計８金額 */     
    public static final String kaikeiKin8_COLUMN = "KAIKEI_KIN_8";
    /** カラム名称：会計合計９件数 */      
    public static final String kaikeiKen9_COLUMN = "KAIKEI_KEN_9";
    /** カラム名称：会計合計９金額 */     
    public static final String kaikeiKin9_COLUMN = "KAIKEI_KIN_9";
    /** カラム名称：会計合計１０件数 */     
    public static final String kaikeiKen10_COLUMN = "KAIKEI_KEN_10";
    /** カラム名称：会計合計１０金額 */    
    public static final String kaikeiKin10_COLUMN = "KAIKEI_KIN_10";
    /** カラム名称：会計合計１１件数 */    
    public static final String kaikeiKen11_COLUMN = "KAIKEI_KEN_11";
    /** カラム名称：会計合計１１金額 */    
    public static final String kaikeiKin11_COLUMN = "KAIKEI_KIN_11";
    /** カラム名称：チケット販売１件数 */    
    public static final String tieketKen1_COLUMN = "TIEKET_KEN_1";
    /** カラム名称：チケット販売１金額 */     
    public static final String tieketKin1_COLUMN = "TIEKET_KIN_1";
    /** カラム名称：チケット販売２件数 */     
    public static final String tieketKen2_COLUMN = "TIEKET_KEN_2";
    /** カラム名称：チケット販売２金額 */     
    public static final String tieketKin2_COLUMN = "TIEKET_KIN_2";
    /** カラム名称：チケット販売３件数 */     
    public static final String tieketKen3_COLUMN = "TIEKET_KEN_3";
    /** カラム名称：チケット販売３金額 */      
    public static final String tieketKin3_COLUMN = "TIEKET_KIN_3";
    /** カラム名称：チケット販売４件数 */      
    public static final String tieketKen4_COLUMN = "TIEKET_KEN_4";
    /** カラム名称：チケット販売４金額 */     
    public static final String tieketKin4_COLUMN = "TIEKET_KIN_4";
    /** カラム名称：チケット販売５件数 */     
    public static final String tieketKen5_COLUMN = "TIEKET_KEN_5";
    /** カラム名称：チケット販売５金額 */    
    public static final String tieketKin5_COLUMN = "TIEKET_KIN_5";
    /** カラム名称：チケット販売６件数 */    
    public static final String tieketKen6_COLUMN = "TIEKET_KEN_6";
    /** カラム名称：チケット販売６金額 */     
    public static final String tieketKin6_COLUMN = "TIEKET_KIN_6";
    /** カラム名称：チケット販売７件数 */     
    public static final String tieketKen7_COLUMN = "TIEKET_KEN_7";
    /** カラム名称：チケット販売７金額 */     
    public static final String tieketKin7_COLUMN = "TIEKET_KIN_7";
    /** カラム名称：チケット販売８件数 */     
    public static final String tieketKen8_COLUMN = "TIEKET_KEN_8";
    /** カラム名称：チケット販売８金額 */      
    public static final String tieketKin8_COLUMN = "TIEKET_KIN_8";
    /** カラム名称：チケット販売９件数 */     
    public static final String tieketKen9_COLUMN = "TIEKET_KEN_9";
    /** カラム名称：チケット販売９金額 */      
    public static final String tieketKin9_COLUMN = "TIEKET_KIN_9";
    /** カラム名称：チケット販売１０件数 */     
    public static final String tieketKen10_COLUMN = "TIEKET_KEN_10";
    /** カラム名称：チケット販売１０金額 */     
    public static final String tieketKin10_COLUMN = "TIEKET_KIN_10";
    /** カラム名称：チケット販売１１件数 */      
    public static final String tieketKen11_COLUMN = "TIEKET_KEN_11";
    /** カラム名称：チケット販売１１金額 */      
    public static final String tieketKin11_COLUMN = "TIEKET_KIN_11";
    /** カラム名称：チケット販売１２件数 */      
    public static final String tieketKen12_COLUMN = "TIEKET_KEN_12";
    /** カラム名称：チケット販売１２金額 */     
    public static final String tieketKin12_COLUMN = "TIEKET_KIN_12";
    /** カラム名称：チケット販売１３件数 */     
    public static final String tieketKen13_COLUMN = "TIEKET_KEN_13";
    /** カラム名称：チケット販売１３金額 */      
    public static final String tieketKin13_COLUMN = "TIEKET_KIN_13";
    /** カラム名称：チケット販売１４件数 */     
    public static final String tieketKen14_COLUMN = "TIEKET_KEN_14";
    /** カラム名称：チケット販売１４金額 */      
    public static final String tieketKin14_COLUMN = "TIEKET_KIN_14";
    /** カラム名称：チケット販売１５件数 */     
    public static final String tieketKen15_COLUMN = "TIEKET_KEN_15";
    /** カラム名称：チケット販売１５金額 */      
    public static final String tieketKin15_COLUMN = "TIEKET_KIN_15";
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 売上高計
     */
    private BigDecimal uriage;

    /**
     * 通常税率対象売上高計
     */
    private BigDecimal uriage1;

    /**
     * 軽減税率対象売上高計
     */
    private BigDecimal uriage2;

    /**
     * 値引計
     */
    private BigDecimal nebiki;
    
    /**
     * 消費税
     */
    private BigDecimal tax;

    /**
     * 通常税率対象消費税
     */
    private BigDecimal tax1;

    /**
     * 軽減税率対象消費税
     */
    private BigDecimal tax2;

    /**
     * Gカード販売金額
     */
    private BigDecimal gcHanKin;
    
    /**
     * 売掛件数
     */
    private BigDecimal urikakeKen;
    
    /**
     * 売掛金額
     */
    private BigDecimal urikakeKin;
    
    /**
     * Gカード回収金額
     */
    private BigDecimal gcUriKin;
    
    /**
     * 招待券売上金額
     */
    private BigDecimal invKin;
    
    /**
     * 現金
     */
    private BigDecimal genkin;
    
    /**
     * 実現金在高
     */
    private BigDecimal aridakaJitu;
    
    /**
     * 過剰額
     */
    private BigDecimal kajou;
    
    /**
     * 不足額
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
     * オールキャンセル回数
     */
    private BigDecimal allcanKen;
    
    /**
     * オールキャンセル金額
     */
    private BigDecimal allcanKin;
    
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
    private BigDecimal tieketKen1;
    
    /**
     * チケット１販売金額
     */
    private BigDecimal tieketKin1;
    
    /**
     * チケット２販売件数
     */
    private BigDecimal tieketKen2;
    
    /**
     * チケット２販売金額
     */
    private BigDecimal tieketKin2;
    
    /**
     * チケット３販売件数
     */
    private BigDecimal tieketKen3;
    
    /**
     * チケット３販売金額
     */
    private BigDecimal tieketKin3;
    
    /**
     * チケット４販売件数
     */
    private BigDecimal tieketKen4;
    
    /**
     * チケット４販売金額
     */
    private BigDecimal tieketKin4;
    
    /**
     * チケット５販売件数
     */
    private BigDecimal tieketKen5;
    
    /**
     * チケット５販売金額
     */
    private BigDecimal tieketKin5;
    
    /**
     * チケット６販売件数
     */
    private BigDecimal tieketKen6;
    
    /**
     * チケット６販売金額
     */
    private BigDecimal tieketKin6;
    
    /**
     * チケット７販売件数
     */
    private BigDecimal tieketKen7;
    
    /**
     * チケット７販売金額
     */
    private BigDecimal tieketKin7;
    
    /**
     * チケット８販売件数
     */
    private BigDecimal tieketKen8;
    
    /**
     * チケット８販売金額
     */
    private BigDecimal tieketKin8;
    
    /**
     * チケット９販売件数
     */
    private BigDecimal tieketKen9;
    
    /**
     * チケット９販売金額
     */
    private BigDecimal tieketKin9;
    
    /**
     * チケット１０販売件数
     */
    private BigDecimal tieketKen10;
    
    /**
     * チケット１０販売金額
     */
    private BigDecimal tieketKin10;
    
    /**
     * チケット１１販売件数
     */
    private BigDecimal tieketKen11;
    
    /**
     * チケット１１販売金額
     */
    private BigDecimal tieketKin11;
    
    /**
     * チケット１２販売件数
     */
    private BigDecimal tieketKen12;
    
    /**
     * チケット１２販売金額
     */
    private BigDecimal tieketKin12;
    
    /**
     * チケット１３販売件数
     */
    private BigDecimal tieketKen13;
    
    /**
     * チケット１３販売金額
     */
    private BigDecimal tieketKin13;
    
    /**
     * チケット１４販売件数
     */
    private BigDecimal tieketKen14;
    
    /**
     * チケット１４販売金額
     */
    private BigDecimal tieketKin14;
    
    /**
     * チケット１５販売件数
     */
    private BigDecimal tieketKen15;
    
    /**
     * チケット１５販売金額
     */
    private BigDecimal tieketKin15;
    
    /**
     * 行CSSクラス名
     */
    private String rClass;
    
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
     * 売上高計を取得します。
     * @return 売上高計
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上高計を設定します。
     * @param uriage 売上高計
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }

    /**
     * 通常税率対象売上高計を取得します。
     * @return 通常税率対象売上高計
     */
    public BigDecimal getUriage1() { return uriage1; }

    /**
     * 通常税率対象売上高計を設定します。
     * @param uriage1 通常税率対象売上高計
     */
    public void setUriage1(BigDecimal uriage1) {
        this.uriage1 = uriage1;
    }

    /**
     * 軽減税率対象売上高計を取得します。
     * @return 軽減税率対象売上高計
     */
    public BigDecimal getUriage2() {
        return uriage2;
    }

    /**
     * 軽減税率対象売上高計を設定します。
     * @param uriage2 軽減税率対象売上高計
     */
    public void setUriage2(BigDecimal uriage2) {
        this.uriage2 = uriage2;
    }

    /**
     * 値引計を取得します。
     * @return 値引計
     */
    public BigDecimal getNebiki() {
        return nebiki;
    }

    /**
     * 値引計を設定します。
     * @param nebiki 値引計
     */
    public void setNebiki(BigDecimal nebiki) {
        this.nebiki = nebiki;
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
     * 通常税率対象消費税を取得します。
     * @return 通常税率対象消費税
     */
    public BigDecimal getTax1() {
        return tax1;
    }
    /**
     * 通常税率対象消費税を設定します。
     * @param tax1 通常税率対象消費税
     */
    public void setTax1(BigDecimal tax1) {
        this.tax1 = tax1;
    }

    /**
     * 軽減税率対象消費税を取得します。
     * @return 軽減税率対象消費税
     */
    public BigDecimal getTax2() {
        return tax2;
    }
    /**
     * 軽減税率対象消費税を設定します。
     * @param tax2 軽減税率対象消費税
     */
    public void setTax2(BigDecimal tax2) {
        this.tax2 = tax2;
    }

    /**
     * Gカード販売金額を取得します。
     * @return Gカード販売金額
     */
    public BigDecimal getGcHanKin() {
        return gcHanKin;
    }
    /**
     * Gカード販売金額を設定します。
     * @param gcHanKin Gカード販売金額
     */
    public void setGcHanKin(BigDecimal gcHanKin) {
        this.gcHanKin = gcHanKin;
    }
    
    /**
     * 売掛件数を取得します。
     * @return 売掛件数
     */
    public BigDecimal getUrikakeKen() {
        return urikakeKen;
    }
    /**
     * 売掛件数を設定します。
     * @param urikakeKen 売掛件数
     */
    public void setUrikakeKen(BigDecimal urikakeKen) {
        this.urikakeKen = urikakeKen;
    }
    
    /**
     * 売掛金額を取得します。
     * @return 売掛金額
     */
    public BigDecimal getUrikakeKin() {
        return urikakeKin;
    }
    /**
     * 売掛金額を設定します。
     * @param urikakeKin 売掛金額
     */
    public void setUrikakeKin(BigDecimal urikakeKin) {
        this.urikakeKin = urikakeKin;
    }
    
    /**
     * Gカード回収金額を取得します。
     * @return Gカード回収金額
     */
    public BigDecimal getGcUriKin() {
        return gcUriKin;
    }
    /**
     * Gカード回収金額を設定します。
     * @param gcUriKin Gカード回収金額
     */
    public void setGcUriKin(BigDecimal gcUriKin) {
        this.gcUriKin = gcUriKin;
    }
    
    /**
     * 招待券売上金額を取得します。
     * @return 招待券売上金額
     */
    public BigDecimal getInvKin() {
        return invKin;
    }
    /**
     * 招待券売上金額を設定します。
     * @param invKin 招待券売上金額
     */
    public void setInvKin(BigDecimal invKin) {
        this.invKin = invKin;
    }
    
    /**
     * 現金を取得します。
     * @return 現金
     */
    public BigDecimal getGenkin() {
        return genkin;
    }
    /**
     * 現金を設定します。
     * @param genkin 現金
     */
    public void setGenkin(BigDecimal genkin) {
        this.genkin = genkin;
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
     * 過剰額を取得します。
     * @return 過剰額
     */
    public BigDecimal getKajou() {
        return kajou;
    }
    /**
     * 過剰額を設定します。
     * @param kajou 過剰額
     */
    public void setKajou(BigDecimal kajou) {
        this.kajou = kajou;
    }
    
    /**
     * 不足額を取得します。
     * @return 不足額
     */
    public BigDecimal getFusoku() {
        return fusoku;
    }
    /**
     * 不足額を設定します。
     * @param fusoku 不足額
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
     * オールキャンセル回数を取得します。
     * @return オールキャンセル回数
     */
    public BigDecimal getAllcanKen() {
        return allcanKen;
    }
    /**
     * オールキャンセル回数を設定します。
     * @param allcanKen オールキャンセル回数
     */
    public void setAllcanKen(BigDecimal allcanKen) {
        this.allcanKen = allcanKen;
    }
    
    /**
     * オールキャンセル金額を取得します。
     * @return オールキャンセル金額
     */
    public BigDecimal getAllcanKin() {
        return allcanKin;
    }
    /**
     * オールキャンセル金額を設定します。
     * @param allcanKin オールキャンセル金額
     */
    public void setAllcanKin(BigDecimal allcanKin) {
        this.allcanKin = allcanKin;
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
    public BigDecimal getTieketKen1() {
        return tieketKen1;
    }
    /**
     * チケット１販売件数を設定します。
     * @param tieketKen1 チケット１販売件数
     */
    public void setTieketKen1(BigDecimal tieketKen1) {
        this.tieketKen1 = tieketKen1;
    }
    
    /**
     * チケット１販売金額を取得します。
     * @return チケット１販売金額
     */
    public BigDecimal getTieketKin1() {
        return tieketKin1;
    }
    /**
     * チケット１販売金額を設定します。
     * @param tieketKin1 チケット１販売金額
     */
    public void setTieketKin1(BigDecimal tieketKin1) {
        this.tieketKin1 = tieketKin1;
    }
    
    /**
     * チケット２販売件数を取得します。
     * @return チケット２販売件数
     */
    public BigDecimal getTieketKen2() {
        return tieketKen2;
    }
    /**
     * チケット２販売件数を設定します。
     * @param tieketKen2 チケット２販売件数
     */
    public void setTieketKen2(BigDecimal tieketKen2) {
        this.tieketKen2 = tieketKen2;
    }
    
    /**
     * チケット２販売金額を取得します。
     * @return チケット２販売金額
     */
    public BigDecimal getTieketKin2() {
        return tieketKin2;
    }
    /**
     * チケット２販売金額を設定します。
     * @param tieketKin2 チケット２販売金額
     */
    public void setTieketKin2(BigDecimal tieketKin2) {
        this.tieketKin2 = tieketKin2;
    }
    
    /**
     * チケット３販売件数を取得します。
     * @return チケット３販売件数
     */
    public BigDecimal getTieketKen3() {
        return tieketKen3;
    }
    /**
     * チケット３販売件数を設定します。
     * @param tieketKen3 チケット３販売件数
     */
    public void setTieketKen3(BigDecimal tieketKen3) {
        this.tieketKen3 = tieketKen3;
    }
    
    /**
     * チケット３販売金額を取得します。
     * @return チケット３販売金額
     */
    public BigDecimal getTieketKin3() {
        return tieketKin3;
    }
    /**
     * チケット３販売金額を設定します。
     * @param tieketKin3 チケット３販売金額
     */
    public void setTieketKin3(BigDecimal tieketKin3) {
        this.tieketKin3 = tieketKin3;
    }
    
    /**
     * チケット４販売件数を取得します。
     * @return チケット４販売件数
     */
    public BigDecimal getTieketKen4() {
        return tieketKen4;
    }
    /**
     * チケット４販売件数を設定します。
     * @param tieketKen4 チケット４販売件数
     */
    public void setTieketKen4(BigDecimal tieketKen4) {
        this.tieketKen4 = tieketKen4;
    }
    
    /**
     * チケット４販売金額を取得します。
     * @return チケット４販売金額
     */
    public BigDecimal getTieketKin4() {
        return tieketKin4;
    }
    /**
     * チケット４販売金額を設定します。
     * @param tieketKin4 チケット４販売金額
     */
    public void setTieketKin4(BigDecimal tieketKin4) {
        this.tieketKin4 = tieketKin4;
    }
    
    /**
     * チケット５販売件数を取得します。
     * @return チケット５販売件数
     */
    public BigDecimal getTieketKen5() {
        return tieketKen5;
    }
    /**
     * チケット５販売件数を設定します。
     * @param tieketKen5 チケット５販売件数
     */
    public void setTieketKen5(BigDecimal tieketKen5) {
        this.tieketKen5 = tieketKen5;
    }
    
    /**
     * チケット５販売金額を取得します。
     * @return チケット５販売金額
     */
    public BigDecimal getTieketKin5() {
        return tieketKin5;
    }
    /**
     * チケット５販売金額を設定します。
     * @param tieketKin5 チケット５販売金額
     */
    public void setTieketKin5(BigDecimal tieketKin5) {
        this.tieketKin5 = tieketKin5;
    }
    
    /**
     * チケット６販売件数を取得します。
     * @return チケット６販売件数
     */
    public BigDecimal getTieketKen6() {
        return tieketKen6;
    }
    /**
     * チケット６販売件数を設定します。
     * @param tieketKen6 チケット６販売件数
     */
    public void setTieketKen6(BigDecimal tieketKen6) {
        this.tieketKen6 = tieketKen6;
    }
    
    /**
     * チケット６販売金額を取得します。
     * @return チケット６販売金額
     */
    public BigDecimal getTieketKin6() {
        return tieketKin6;
    }
    /**
     * チケット６販売金額を設定します。
     * @param tieketKin6 チケット６販売金額
     */
    public void setTieketKin6(BigDecimal tieketKin6) {
        this.tieketKin6 = tieketKin6;
    }
    
    /**
     * チケット７販売件数を取得します。
     * @return チケット７販売件数
     */
    public BigDecimal getTieketKen7() {
        return tieketKen7;
    }
    /**
     * チケット７販売件数を設定します。
     * @param tieketKen7 チケット７販売件数
     */
    public void setTieketKen7(BigDecimal tieketKen7) {
        this.tieketKen7 = tieketKen7;
    }
    
    /**
     * チケット７販売金額を取得します。
     * @return チケット７販売金額
     */
    public BigDecimal getTieketKin7() {
        return tieketKin7;
    }
    /**
     * チケット７販売金額を設定します。
     * @param tieketKin7 チケット７販売金額
     */
    public void setTieketKin7(BigDecimal tieketKin7) {
        this.tieketKin7 = tieketKin7;
    }
    
    /**
     * チケット８販売件数を取得します。
     * @return チケット８販売件数
     */
    public BigDecimal getTieketKen8() {
        return tieketKen8;
    }
    /**
     * チケット８販売件数を設定します。
     * @param tieketKen8 チケット８販売件数
     */
    public void setTieketKen8(BigDecimal tieketKen8) {
        this.tieketKen8 = tieketKen8;
    }
    
    /**
     * チケット８販売金額を取得します。
     * @return チケット８販売金額
     */
    public BigDecimal getTieketKin8() {
        return tieketKin8;
    }
    /**
     * チケット８販売金額を設定します。
     * @param tieketKin8 チケット８販売金額
     */
    public void setTieketKin8(BigDecimal tieketKin8) {
        this.tieketKin8 = tieketKin8;
    }
    
    /**
     * チケット９販売件数を取得します。
     * @return チケット９販売件数
     */
    public BigDecimal getTieketKen9() {
        return tieketKen9;
    }
    /**
     * チケット９販売件数を設定します。
     * @param tieketKen9 チケット９販売件数
     */
    public void setTieketKen9(BigDecimal tieketKen9) {
        this.tieketKen9 = tieketKen9;
    }
    
    /**
     * チケット９販売金額を取得します。
     * @return チケット９販売金額
     */
    public BigDecimal getTieketKin9() {
        return tieketKin9;
    }
    /**
     * チケット９販売金額を設定します。
     * @param tieketKin9 チケット９販売金額
     */
    public void setTieketKin9(BigDecimal tieketKin9) {
        this.tieketKin9 = tieketKin9;
    }
    
    /**
     * チケット１０販売件数を取得します。
     * @return チケット１０販売件数
     */
    public BigDecimal getTieketKen10() {
        return tieketKen10;
    }
    /**
     * チケット１０販売件数を設定します。
     * @param tieketKen10 チケット１０販売件数
     */
    public void setTieketKen10(BigDecimal tieketKen10) {
        this.tieketKen10 = tieketKen10;
    }
    
    /**
     * チケット１０販売金額を取得します。
     * @return チケット１０販売金額
     */
    public BigDecimal getTieketKin10() {
        return tieketKin10;
    }
    /**
     * チケット１０販売金額を設定します。
     * @param tieketKin10 チケット１０販売金額
     */
    public void setTieketKin10(BigDecimal tieketKin10) {
        this.tieketKin10 = tieketKin10;
    }
    
    /**
     * チケット１１販売件数を取得します。
     * @return チケット１１販売件数
     */
    public BigDecimal getTieketKen11() {
        return tieketKen11;
    }
    /**
     * チケット１１販売件数を設定します。
     * @param tieketKen11 チケット１１販売件数
     */
    public void setTieketKen11(BigDecimal tieketKen11) {
        this.tieketKen11 = tieketKen11;
    }
    
    /**
     * チケット１１販売金額を取得します。
     * @return チケット１１販売金額
     */
    public BigDecimal getTieketKin11() {
        return tieketKin11;
    }
    /**
     * チケット１１販売金額を設定します。
     * @param tieketKin11 チケット１１販売金額
     */
    public void setTieketKin11(BigDecimal tieketKin11) {
        this.tieketKin11 = tieketKin11;
    }
    
    /**
     * チケット１２販売件数を取得します。
     * @return チケット１２販売件数
     */
    public BigDecimal getTieketKen12() {
        return tieketKen12;
    }
    /**
     * チケット１２販売件数を設定します。
     * @param tieketKen12 チケット１２販売件数
     */
    public void setTieketKen12(BigDecimal tieketKen12) {
        this.tieketKen12 = tieketKen12;
    }
    
    /**
     * チケット１２販売金額を取得します。
     * @return チケット１２販売金額
     */
    public BigDecimal getTieketKin12() {
        return tieketKin12;
    }
    /**
     * チケット１２販売金額を設定します。
     * @param tieketKin12 チケット１２販売金額
     */
    public void setTieketKin12(BigDecimal tieketKin12) {
        this.tieketKin12 = tieketKin12;
    }
    
    /**
     * チケット１３販売件数を取得します。
     * @return チケット１３販売件数
     */
    public BigDecimal getTieketKen13() {
        return tieketKen13;
    }
    /**
     * チケット１３販売件数を設定します。
     * @param tieketKen13 チケット１３販売件数
     */
    public void setTieketKen13(BigDecimal tieketKen13) {
        this.tieketKen13 = tieketKen13;
    }
    
    /**
     * チケット１３販売金額を取得します。
     * @return チケット１３販売金額
     */
    public BigDecimal getTieketKin13() {
        return tieketKin13;
    }
    /**
     * チケット１３販売金額を設定します。
     * @param tieketKin13 チケット１３販売金額
     */
    public void setTieketKin13(BigDecimal tieketKin13) {
        this.tieketKin13 = tieketKin13;
    }
    
    /**
     * チケット１４販売件数を取得します。
     * @return チケット１４販売件数
     */
    public BigDecimal getTieketKen14() {
        return tieketKen14;
    }
    /**
     * チケット１４販売件数を設定します。
     * @param tieketKen14 チケット１４販売件数
     */
    public void setTieketKen14(BigDecimal tieketKen14) {
        this.tieketKen14 = tieketKen14;
    }
    
    /**
     * チケット１４販売金額を取得します。
     * @return チケット１４販売金額
     */
    public BigDecimal getTieketKin14() {
        return tieketKin14;
    }
    /**
     * チケット１４販売金額を設定します。
     * @param tieketKin14 チケット１４販売金額
     */
    public void setTieketKin14(BigDecimal tieketKin14) {
        this.tieketKin14 = tieketKin14;
    }
    
    /**
     * チケット１５販売件数を取得します。
     * @return チケット１５販売件数
     */
    public BigDecimal getTieketKen15() {
        return tieketKen15;
    }
    /**
     * チケット１５販売件数を設定します。
     * @param tieketKen15 チケット１５販売件数
     */
    public void setTieketKen15(BigDecimal tieketKen15) {
        this.tieketKen15 = tieketKen15;
    }
    
    /**
     * チケット１５販売金額を取得します。
     * @return チケット１５販売金額
     */
    public BigDecimal getTieketKin15() {
        return tieketKin15;
    }
    /**
     * チケット１５販売金額を設定します。
     * @param tieketKin15 チケット１５販売金額
     */
    public void setTieketKin15(BigDecimal tieketKin15) {
        this.tieketKin15 = tieketKin15;
    }
    
    /**
     * 行CSSクラス名を取得します。
     * @return 行CSSクラス名
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * 行CSSクラス名を設定します。
     * @param rClass 行CSSクラス名
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }    
}