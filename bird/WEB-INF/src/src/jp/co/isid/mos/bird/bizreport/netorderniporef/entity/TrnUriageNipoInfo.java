package jp.co.isid.mos.bird.bizreport.netorderniporef.entity;

import java.math.BigDecimal;

public class TrnUriageNipoInfo {
    
    public static final String TABLE = "BD54NNIP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String honbuCd_COLUMN = "HONBU_CD";
    
    public static final String honbuName_COLUMN = "HONBU_NAME";
    
    public static final String jigyoCd_COLUMN = "JIGYO_CD";
    
    public static final String jigyoName_COLUMN = "JIGYO_NAME";
    
    public static final String slareaCd_COLUMN = "SLAREA_CD";
    
    public static final String slareaName_COLUMN = "SLAREA_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String kbn1_COLUMN = "KBN1";
    
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String tassei_COLUMN = "TASSEI";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String zenHiUri_COLUMN = "ZEN_HI_URI";
    
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String zenHiKyaku_COLUMN = "ZEN_HI_KYAKU";
    
    public static final String openKbn_COLUMN = "OPEN_KBN";
    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    
    public static final String tanka_COLUMN = "TANKA";
    
    public static final String tankaZen_COLUMN = "TANKA_ZEN";
    
    public static final String zenHiTanka_COLUMN = "ZEN_HI_TANKA";
    
    public static final String misecntKbnNsum_COLUMN = "MISECNT_KBN_NSUM";
    
    public static final String eigyoDaysNsum_COLUMN = "EIGYO_DAYS_NSUM";
    
    public static final String uriageNsum_COLUMN = "URIAGE_NSUM";
    
    public static final String uriageNsumZen_COLUMN = "URIAGE_NSUM_ZEN";
    
    public static final String kyakusuNsum_COLUMN = "KYAKUSU_NSUM";
    
    public static final String kyakusuNsumZen_COLUMN = "KYAKUSU_NSUM_ZEN";
    
    public static final String misecntKbnNtake_COLUMN = "MISECNT_KBN_NTAKE";
    
    public static final String eigyoDaysNtake_COLUMN = "EIGYO_DAYS_NTAKE";
    
    public static final String uriageNtake_COLUMN = "URIAGE_NTAKE";
    
    public static final String uriageNtakeZen_COLUMN = "URIAGE_NTAKE_ZEN";
    
    public static final String kyakusuNtake_COLUMN = "KYAKUSU_NTAKE";
    
    public static final String kyakusuNtakeZen_COLUMN = "KYAKUSU_NTAKE_ZEN";
    
    public static final String misecntKbnNtaku_COLUMN = "MISECNT_KBN_NTAKU";
    
    public static final String eigyoDaysNtaku_COLUMN = "EIGYO_DAYS_NTAKU";
    
    public static final String uriageNtaku_COLUMN = "URIAGE_NTAKU";
    
    public static final String uriageNtakuZen_COLUMN = "URIAGE_NTAKU_ZEN";
    
    public static final String kyakusuNtaku_COLUMN = "KYAKUSU_NTAKU";
    
    public static final String kyakusuNtakuZen_COLUMN = "KYAKUSU_NTAKU_ZEN";
     
    public static final String rClass_COLUMN = "R_CLASS";
    
    public static final String tasseiUriClass_COLUMN = "TASSEI_URI_CLASS";
    
    public static final String zenUriClass_COLUMN = "ZEN_URI_CLASS";
    
    public static final String zenKyaClass_COLUMN = "ZEN_KYA_CLASS";
    
    public static final String zenTanClass_COLUMN = "ZEN_TAN_CLASS";
    
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * 本部コード
     */
    private String honbuCd;
    
    /**
     * 本部名称
     */
    private String honbuName;
    
    /**
     * 事業部コード
     */
    private String jigyoCd;
    
    /**
     * 事業部名称
     */
    private String jigyoName;
    
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    
    /**
     * 営業エリア名称
     */
    private String slareaName;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 前年対象区分
     */
    private String kbn1;
    
    /**
     * 当年天候区分
     */
    private BigDecimal tenkoKbn;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 予算
     */
    private BigDecimal yosan;
    
    /**
     * 達成率
     */
    private BigDecimal tassei;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen;
    
    /**
     * 前年比(売上)
     */
    private BigDecimal zenHiUri;
    
    /**
     * 前年天候
     */
    private BigDecimal tenkoKbnZen;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen;
    
    /**
     * 前年比(客数)
     */
    private BigDecimal zenHiKyaku;
    
    /**
     * オープン区分
     */
    private String openKbn;
    
    /**
     * 前年オープン区分
     */
    private String openKbnZen;
    
    /**
     * 客単価
     */
    private BigDecimal tanka;
    
    /**
     * 前年単価
     */
    private BigDecimal tankaZen;
    
    /**
     * 前年比(客単価)
     */
    private BigDecimal zenHiTanka;
    
    /**
     * RCLASS
     */
    private String rClass;
    
    /**
     * 達成率（売上)クラス
     */
    private String tasseiUriClass;
    
    /**
     * 前年比(売上)クラス
     */
    private String zenUriClass;
    
    /**
     * 前年比'(客数)クラス
     */
    private String zenKyaClass;
    
    /**
     * 前年比(客単価)クラス
     */
    private String zenTanClass;
    
    /**
     * 当年営業日数
     */
    private BigDecimal eigyoDays;
    
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen;
    
    /**
     * ネット注文実績店舗数
     */
    private BigDecimal misecntKbnNsum;
    
    /**
     * ネット注文日数
     */
    private BigDecimal eigyoDaysNsum;
    
    /**
     * ネット注文売上
     */
    private BigDecimal uriageNsum;
    
    /**
     * 前年ネット注文実績
     */
    private BigDecimal uriageNsumZen;
    
    /**
     * 前年比(ネット注文売上)
     */
    private BigDecimal zenHiUriageNsum;
    
    /**
     * 構成比(ネット注文売上)
     */
    private BigDecimal kouseiHiUriageNsum;
    
    /**
     * ネット注文売上平均
     */
    private BigDecimal avgUriageNsum;
    
    /**
     * ネット注文件数
     */
    private BigDecimal kyakusuNsum;
    
    /**
     * 前年ネット注文件数
     */
    private BigDecimal kyakusuNsumZen;
    
    /**
     * 前年比(ネット注文件数)
     */
    private BigDecimal zenHiKyakusuNsum;
    
    /**
     * 構成比(ネット注文件数)
     */
    private BigDecimal kouseiHiKyakusuNsum;
    
    /**
     * ネット注文件数平均
     */
    private BigDecimal avgKyakusuNsum;
    
    /** 
     * 客単価(ネット注文) 
    */
    private BigDecimal kyakuTankaNsum;
    
    /** 
     * 前年客単価(ネット注文) 
     */
    private BigDecimal zenKyakuTankaNsum;
    
    /** 
     * 前年比(客単価ネット注文) 
     */
    private BigDecimal zenHikyakuTankaNsum;
    
    /**
     * ネットテイク実績店舗数
     */
    private BigDecimal misecntKbnNtake;
    
    /**
     * ネットテイク日数
     */
    private BigDecimal eigyoDaysNtake;
    
    /**
     * ネットテイク売上
     */
    private BigDecimal uriageNtake;
    
    /**
     * 前年ネットテイク実績
     */
    private BigDecimal uriageNtakeZen;
    
    /**
     * 前年比(ネットテイク売上)
     */
    private BigDecimal zenHiUriageNtake;
    
    /**
     * 構成比(ネットテイク売上)
     */
    private BigDecimal kouseiHiUriageNtake;
    
    /**
     * ネットテイク売上平均
     */
    private BigDecimal avgUriageNtake;
    
    /**
     * ネットテイク文件数
     */
    private BigDecimal kyakusuNtake;
    
    /**
     * 前年ネットテイク件数
     */
    private BigDecimal kyakusuNtakeZen;
    
    /**
     * 前年比(ネットテイク件数)
     */
    private BigDecimal zenHiKyakusuNtake;
    
    /**
     * 構成比(ネットテイク件数)
     */
    private BigDecimal kouseiHiKyakusuNtake;
    
    /**
     * ネットテイク件数平均
     */
    private BigDecimal avgKyakusuNtake;
    
    /** 
     * 客単価(ネットテイク) 
    */
    private BigDecimal kyakuTankaNtake;
    
    /** 
     * 前年客単価(ネットテイク) 
     */
    private BigDecimal zenKyakuTankaNtake;
    
    /** 
     * 前年比(客単価ネットテイク) 
     */
    private BigDecimal zenHikyakuTankaNtake;
    
    /**
     * ネット宅配実績店舗数
     */
    private BigDecimal misecntKbnNtaku;
    
    /**
     * ネット宅配日数
     */
    private BigDecimal eigyoDaysNtaku;
    
    /**
     * ネット宅配売上
     */
    private BigDecimal uriageNtaku;
    
    /**
     * 前年ネット宅配実績
     */
    private BigDecimal uriageNtakuZen;
    
    /**
     * 前年比(ネット宅配売上)
     */
    private BigDecimal zenHiUriageNtaku;
    
    /**
     * 構成比(ネット宅配売上)
     */
    private BigDecimal kouseiHiUriageNtaku;
    
    /**
     * ネット宅配売上平均
     */
    private BigDecimal avgUriageNtaku;
    
    /**
     * ネット宅配文件数
     */
    private BigDecimal kyakusuNtaku;
    
    /**
     * 前年ネット宅配件数
     */
    private BigDecimal kyakusuNtakuZen;
    
    /**
     * 前年比(ネット宅配件数)
     */
    private BigDecimal zenHiKyakusuNtaku;
    
    /**
     * 構成比(ネット宅配件数)
     */
    private BigDecimal kouseiHiKyakusuNtaku;
    
    /**
     * ネット宅配件数平均
     */
    private BigDecimal avgKyakusuNtaku;
    
    /** 
     * 客単価(ネット宅配) 
    */
    private BigDecimal kyakuTankaNtaku;
    
    /** 
     * 前年客単価(ネット宅配) 
     */
    private BigDecimal zenKyakuTankaNtaku;
    
    /** 
     * 前年比(客単価ネット宅配) 
     */
    private BigDecimal zenHikyakuTankaNtaku;
    
    /** 
     * LAST INDEX判断 
     */
    private boolean lastIndex = false;
    
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
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
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return blockName;
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    /**
     * 本部コードを取得します。
     * @return 本部コード
     */
    public String getHonbuCd() {
        return honbuCd;
    }
    /**
     * 本部コードを設定します。
     * @param honbuCd 本部コード
     */
    public void setHonbuCd(String honbuCd) {
        this.honbuCd = honbuCd;
    }
    
    /**
     * 本部名称を取得します。
     * @return 本部名称
     */
    public String getHonbuName() {
        return honbuName;
    }
    /**
     * 本部名称を設定します。
     * @param honbuName 本部名称
     */
    public void setHonbuName(String honbuName) {
        this.honbuName = honbuName;
    }
    
    /**
     * 事業部コードを取得します。
     * @return 事業部コード
     */
    public String getJigyoCd() {
        return jigyoCd;
    }
    /**
     * 事業部コードを設定します。
     * @param jigyoCd 事業部コード
     */
    public void setJigyoCd(String jigyoCd) {
        this.jigyoCd = jigyoCd;
    }
    
    /**
     * 事業部名称を取得します。
     * @return 事業部名称
     */
    public String getJigyoName() {
        return jigyoName;
    }
    /**
     * 事業部名称を設定します。
     * @param jigyoName 事業部名称
     */
    public void setJigyoName(String jigyoName) {
        this.jigyoName = jigyoName;
    }
    
    /**
     * 営業エリアコードを取得します。
     * @return 営業エリアコード
     */
    public String getSlareaCd() {
        return slareaCd;
    }
    /**
     * 営業エリアコードを設定します。
     * @param slareaCd 営業エリアコード
     */
    public void setSlareaCd(String slareaCd) {
        this.slareaCd = slareaCd;
    }
    
    /**
     * 営業エリア名称を取得します。
     * @return 営業エリア名称
     */
    public String getSlareaName() {
        return slareaName;
    }
    /**
     * 営業エリア名称を設定します。
     * @param slareaName 営業エリア名称
     */
    public void setSlareaName(String slareaName) {
        this.slareaName = slareaName;
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
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 前年対象区分を取得します。
     * @return 前年対象区分
     */
    public String getKbn1() {
        return kbn1;
    }
    
    /**
     * 前年対象区分(日本語1文字)を取得します。
     * @return
     */
    public String getZenKbn() {
        
        String str = new String();
        if ( kbn1 == null ) {
            str = "";
        } else if (kbn1.equals("1") ) {
            str = "前";
        } else if ( kbn1.equals("2") ){
            str = "予";
        } else if ( kbn1.equals("3") ) {
            str = "新";
        }
        
        return str;
    }
    /**
     * 前年対象区分を設定します。
     * @param kbn1 前年対象区分
     */
    public void setKbn1(String kbn1) {
        this.kbn1 = kbn1;
    }
    
    /**
     * 当年天候区分を取得します。
     * @return 当年天候区分
     */
    public BigDecimal getTenkoKbn() {
        return tenkoKbn;
    }
    
    /**
     * 当年天候区分を返します。
     * @return
     */
    public String getTenkoKbnJpn() {
        String str = new String();
        
        String tenkoKbnStr = new String();
        if ( tenkoKbn != null ) {
            tenkoKbnStr = tenkoKbn.toString();
        }
        if ( tenkoKbn == null ) {
            str = "";
        } else if ( tenkoKbnStr.equals( "1" ) ) {
            str = "晴";
        } else if ( tenkoKbnStr.equals("2" ) ) {
            str = "曇";
        } else if ( tenkoKbnStr.equals("3" ) ) {
            str = "雨";
        } else if ( tenkoKbnStr.equals("4" ) ) {
            str = "雪";
        } else if (tenkoKbnStr.equals("5" ) ) {
            str = "嵐";
        } else {
            str = "";
        }
        
        return str;
    }
    /**
     * 当年天候区分を設定します。
     * @param tenkoKbn 当年天候区分
     */
    public void setTenkoKbn(BigDecimal tenkoKbn) {
        this.tenkoKbn = tenkoKbn;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 予算を取得します。
     * @return 予算
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * 予算を設定します。
     * @param yosan 予算
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 達成率を取得します。
     * @return 達成率
     */
    public BigDecimal getTassei() {
        return tassei;
    }
    /**
     * 達成率を設定します。
     * @param tassei 達成率
     */
    public void setTassei(BigDecimal tassei) {
        this.tassei = tassei;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上を設定します。
     * @param uriageZen 前年売上
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }
    
    /**
     * 前年比(売上)を取得します。
     * @return 前年比(売上)
     */
    public BigDecimal getZenHiUri() {        
        return zenHiUri;
    }
    /**
     * 前年比(売上)を設定します。
     * @param zenHiUri 前年比(売上)
     */
    public void setZenHiUri(BigDecimal zenHiUri) {
        this.zenHiUri = zenHiUri;
    }
    
    /**
     * 前年天候を取得します。
     * @return 前年天候
     */
    public BigDecimal getTenkoKbnZen() {
        return tenkoKbnZen;
    }
    
    /**
     * 前年天候区分を返します。
     * @return
     */
    public String getTenkoKbnZenJpn() {
        String str = new String();
        
        String tenkoKbnZenStr = new String();
        
        if ( tenkoKbnZen != null ) {
            tenkoKbnZenStr = tenkoKbnZen.toString();
        }
        if ( tenkoKbnZen == null ) {
            str = "";
        } else if ( tenkoKbnZenStr.equals( "1" ) ) {
            str = "晴";
        } else if ( tenkoKbnZenStr.equals("2" ) ) {
            str = "曇";
        } else if ( tenkoKbnZenStr.equals("3" ) ) {
            str = "雨";
        } else if ( tenkoKbnZenStr.equals("4" ) ) {
            str = "雪";
        } else if (tenkoKbnZenStr.equals("5" ) ) {
            str = "嵐";
        } else {
            str = "";
        }
        
        return str;
    }

    /**
     * 前年天候を設定します。
     * @param tenkoKbnZen 前年天候
     */
    public void setTenkoKbnZen(BigDecimal tenkoKbnZen) {
        this.tenkoKbnZen = tenkoKbnZen;
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
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
    
    /**
     * 前年比(客数)を取得します。
     * @return 前年比(客数)
     */
    public BigDecimal getZenHiKyaku() {
        return zenHiKyaku;
    }
    /**
     * 前年比(客数)を設定します。
     * @param zenHiKyaku 前年比(客数)
     */
    public void setZenHiKyaku(BigDecimal zenHiKyaku) {
        this.zenHiKyaku = zenHiKyaku;
    }
    
    /**
     * オープン区分を取得します。
     * @return オープン区分
     */
    public String getOpenKbn() {
        return openKbn;
    }
    
    /**
     * オープン区分
     * @return
     */
    public String getOpenKbnJpn() {
        String str = new String();
        if ( openKbn == null ) {
            str ="";
        }else if ( openKbn.equals("1") ) {
            str = "";
        } else {
//            str = "休業";
            str = "クローズ";
        }
        
        return str;
    }
    /**
     * オープン区分を設定します。
     * @param openKbn オープン区分
     */
    public void setOpenKbn(String openKbn) {
        this.openKbn = openKbn;
    }
    
    /**
     * 前年オープン区分を取得します。
     * @return 前年オープン区分
     */
    public String getOpenKbnZen() {
        return openKbnZen;
    }
    /**
     * 前年オープン区分を設定します。
     * @param openKbnZen 前年オープン区分
     */
    public void setOpenKbnZen(String openKbnZen) {
        this.openKbnZen = openKbnZen;
    }
    
    public  String getOpenKbnZenJpn() {
        String str = new String();
        
        if ( openKbnZen == null ) {
            str = "";
        } else if ( openKbnZen.equals( "1") ) {
            str = "";
        } else {
//            str = "休業";
            str = "クローズ";
        }
        
        return str;
    }
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getTanka() {
        return tanka;
    }
    /**
     * 客単価を設定します。
     * @param tanka 客単価
     */
    public void setTanka(BigDecimal tanka) {
        this.tanka = tanka;
    }
    
    /**
     * 前年単価を取得します。
     * @return 前年単価
     */
    public BigDecimal getTankaZen() {
        return tankaZen;
    }
    /**
     * 前年単価を設定します。
     * @param tankaZen 前年単価
     */
    public void setTankaZen(BigDecimal tankaZen) {
        this.tankaZen = tankaZen;
    }
    
    /**
     * 前年比(客単価)を取得します。
     * @return 前年比(客単価)
     */
    public BigDecimal getZenHiTanka() {
        return zenHiTanka;
    }
    /**
     * 前年比(客単価)を設定します。
     * @param zenHiTanka 前年比(客単価)
     */
    public void setZenHiTanka(BigDecimal zenHiTanka) {
        this.zenHiTanka = zenHiTanka;
    }
    
    /**
     * RCLASSを取得します。
     * @return RCLASS
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * RCLASSを設定します。
     * @param rClass RCLASS
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }
    
    /**
     * 達成率（売上)クラスを取得します。
     * @return 達成率（売上)クラス
     */
    public String getTasseiUriClass() {
        return tasseiUriClass;
    }
    /**
     * 達成率（売上)クラスを設定します。
     * @param tasseiUriClass 達成率（売上)クラス
     */
    public void setTasseiUriClass(String tasseiUriClass) {
        this.tasseiUriClass = tasseiUriClass;
    }
    
    /**
     * 前年比(売上)クラスを取得します。
     * @return 前年比(売上)クラス
     */
    public String getZenUriClass() {
        return zenUriClass;
    }
    /**
     * 前年比(売上)クラスを設定します。
     * @param zenUriClass 前年比(売上)クラス
     */
    public void setZenUriClass(String zenUriClass) {
        this.zenUriClass = zenUriClass;
    }
    
    /**
     * 前年比'(客数)クラスを取得します。
     * @return 前年比'(客数)クラス
     */
    public String getZenKyaClass() {
        return zenKyaClass;
    }
    /**
     * 前年比'(客数)クラスを設定します。
     * @param zenKyaClass 前年比'(客数)クラス
     */
    public void setZenKyaClass(String zenKyaClass) {
        this.zenKyaClass = zenKyaClass;
    }
    
    /**
     * 前年比(客単価)クラスを取得します。
     * @return 前年比(客単価)クラス
     */
    public String getZenTanClass() {
        return zenTanClass;
    }
    /**
     * 前年比(客単価)クラスを設定します。
     * @param zenTanClass 前年比(客単価)クラス
     */
    public void setZenTanClass(String zenTanClass) {
        this.zenTanClass = zenTanClass;
    }
    
    /**
     * 当年営業日数を取得します。
     * @return 当年営業日数
     */
    public BigDecimal getEigyoDays() {
        return eigyoDays;
    }
    /**
     * 当年営業日数を設定します。
     * @param eigyoDays 当年営業日数
     */
    public void setEigyoDays(BigDecimal eigyoDays) {
        this.eigyoDays = eigyoDays;
    }
    
    /**
     * 前年営業日数を取得します。
     * @return 前年営業日数
     */
    public BigDecimal getEigyoDaysZen() {
        return eigyoDaysZen;
    }
    /**
     * 前年営業日数を設定します。
     * @param eigyoDaysZen 前年営業日数
     */
    public void setEigyoDaysZen(BigDecimal eigyoDaysZen) {
        this.eigyoDaysZen = eigyoDaysZen;
    }
	/**
	 * @return avgKyakusuNsum を戻します。
	 */
	public BigDecimal getAvgKyakusuNsum() {
		return avgKyakusuNsum;
	}
	/**
	 * @param avgKyakusuNsum 設定する avgKyakusuNsum。
	 */
	public void setAvgKyakusuNsum(BigDecimal avgKyakusuNsum) {
		this.avgKyakusuNsum = avgKyakusuNsum;
	}
	/**
	 * @return avgKyakusuNtake を戻します。
	 */
	public BigDecimal getAvgKyakusuNtake() {
		return avgKyakusuNtake;
	}
	/**
	 * @param avgKyakusuNtake 設定する avgKyakusuNtake。
	 */
	public void setAvgKyakusuNtake(BigDecimal avgKyakusuNtake) {
		this.avgKyakusuNtake = avgKyakusuNtake;
	}
	/**
	 * @return avgKyakusuNtaku を戻します。
	 */
	public BigDecimal getAvgKyakusuNtaku() {
		return avgKyakusuNtaku;
	}
	/**
	 * @param avgKyakusuNtaku 設定する avgKyakusuNtaku。
	 */
	public void setAvgKyakusuNtaku(BigDecimal avgKyakusuNtaku) {
		this.avgKyakusuNtaku = avgKyakusuNtaku;
	}
	/**
	 * @return avgUriageNsum を戻します。
	 */
	public BigDecimal getAvgUriageNsum() {
		return avgUriageNsum;
	}
	/**
	 * @param avgUriageNsum 設定する avgUriageNsum。
	 */
	public void setAvgUriageNsum(BigDecimal avgUriageNsum) {
		this.avgUriageNsum = avgUriageNsum;
	}
	/**
	 * @return avgUriageNtake を戻します。
	 */
	public BigDecimal getAvgUriageNtake() {
		return avgUriageNtake;
	}
	/**
	 * @param avgUriageNtake 設定する avgUriageNtake。
	 */
	public void setAvgUriageNtake(BigDecimal avgUriageNtake) {
		this.avgUriageNtake = avgUriageNtake;
	}
	/**
	 * @return avgUriageNtaku を戻します。
	 */
	public BigDecimal getAvgUriageNtaku() {
		return avgUriageNtaku;
	}
	/**
	 * @param avgUriageNtaku 設定する avgUriageNtaku。
	 */
	public void setAvgUriageNtaku(BigDecimal avgUriageNtaku) {
		this.avgUriageNtaku = avgUriageNtaku;
	}
	/**
	 * @return eigyoDaysNsum を戻します。
	 */
	public BigDecimal getEigyoDaysNsum() {
		return eigyoDaysNsum;
	}
	/**
	 * @param eigyoDaysNsum 設定する eigyoDaysNsum。
	 */
	public void setEigyoDaysNsum(BigDecimal eigyoDaysNsum) {
		this.eigyoDaysNsum = eigyoDaysNsum;
	}
	/**
	 * @return eigyoDaysNtake を戻します。
	 */
	public BigDecimal getEigyoDaysNtake() {
		return eigyoDaysNtake;
	}
	/**
	 * @param eigyoDaysNtake 設定する eigyoDaysNtake。
	 */
	public void setEigyoDaysNtake(BigDecimal eigyoDaysNtake) {
		this.eigyoDaysNtake = eigyoDaysNtake;
	}
	/**
	 * @return eigyoDaysNtaku を戻します。
	 */
	public BigDecimal getEigyoDaysNtaku() {
		return eigyoDaysNtaku;
	}
	/**
	 * @param eigyoDaysNtaku 設定する eigyoDaysNtaku。
	 */
	public void setEigyoDaysNtaku(BigDecimal eigyoDaysNtaku) {
		this.eigyoDaysNtaku = eigyoDaysNtaku;
	}
	/**
	 * @return kouseiHiKyakusuNsum を戻します。
	 */
	public BigDecimal getKouseiHiKyakusuNsum() {
		return kouseiHiKyakusuNsum;
	}
	/**
	 * @param kouseiHiKyakusuNsum 設定する kouseiHiKyakusuNsum。
	 */
	public void setKouseiHiKyakusuNsum(BigDecimal kouseiHiKyakusuNsum) {
		this.kouseiHiKyakusuNsum = kouseiHiKyakusuNsum;
	}
	/**
	 * @return kouseiHiKyakusuNtake を戻します。
	 */
	public BigDecimal getKouseiHiKyakusuNtake() {
		return kouseiHiKyakusuNtake;
	}
	/**
	 * @param kouseiHiKyakusuNtake 設定する kouseiHiKyakusuNtake。
	 */
	public void setKouseiHiKyakusuNtake(BigDecimal kouseiHiKyakusuNtake) {
		this.kouseiHiKyakusuNtake = kouseiHiKyakusuNtake;
	}
	/**
	 * @return kouseiHiKyakusuNtaku を戻します。
	 */
	public BigDecimal getKouseiHiKyakusuNtaku() {
		return kouseiHiKyakusuNtaku;
	}
	/**
	 * @param kouseiHiKyakusuNtaku 設定する kouseiHiKyakusuNtaku。
	 */
	public void setKouseiHiKyakusuNtaku(BigDecimal kouseiHiKyakusuNtaku) {
		this.kouseiHiKyakusuNtaku = kouseiHiKyakusuNtaku;
	}
	/**
	 * @return kouseiHiUriageNsum を戻します。
	 */
	public BigDecimal getKouseiHiUriageNsum() {
		return kouseiHiUriageNsum;
	}
	/**
	 * @param kouseiHiUriageNsum 設定する kouseiHiUriageNsum。
	 */
	public void setKouseiHiUriageNsum(BigDecimal kouseiHiUriageNsum) {
		this.kouseiHiUriageNsum = kouseiHiUriageNsum;
	}
	/**
	 * @return kouseiHiUriageNtake を戻します。
	 */
	public BigDecimal getKouseiHiUriageNtake() {
		return kouseiHiUriageNtake;
	}
	/**
	 * @param kouseiHiUriageNtake 設定する kouseiHiUriageNtake。
	 */
	public void setKouseiHiUriageNtake(BigDecimal kouseiHiUriageNtake) {
		this.kouseiHiUriageNtake = kouseiHiUriageNtake;
	}
	/**
	 * @return kouseiHiUriageNtaku を戻します。
	 */
	public BigDecimal getKouseiHiUriageNtaku() {
		return kouseiHiUriageNtaku;
	}
	/**
	 * @param kouseiHiUriageNtaku 設定する kouseiHiUriageNtaku。
	 */
	public void setKouseiHiUriageNtaku(BigDecimal kouseiHiUriageNtaku) {
		this.kouseiHiUriageNtaku = kouseiHiUriageNtaku;
	}
	/**
	 * @return kyakusuNsum を戻します。
	 */
	public BigDecimal getKyakusuNsum() {
		return kyakusuNsum;
	}
	/**
	 * @param kyakusuNsum 設定する kyakusuNsum。
	 */
	public void setKyakusuNsum(BigDecimal kyakusuNsum) {
		this.kyakusuNsum = kyakusuNsum;
	}
	/**
	 * @return kyakusuNsumZen を戻します。
	 */
	public BigDecimal getKyakusuNsumZen() {
		return kyakusuNsumZen;
	}
	/**
	 * @param kyakusuNsumZen 設定する kyakusuNsumZen。
	 */
	public void setKyakusuNsumZen(BigDecimal kyakusuNsumZen) {
		this.kyakusuNsumZen = kyakusuNsumZen;
	}
	/**
	 * @return kyakusuNtake を戻します。
	 */
	public BigDecimal getKyakusuNtake() {
		return kyakusuNtake;
	}
	/**
	 * @param kyakusuNtake 設定する kyakusuNtake。
	 */
	public void setKyakusuNtake(BigDecimal kyakusuNtake) {
		this.kyakusuNtake = kyakusuNtake;
	}
	/**
	 * @return kyakusuNtakeZen を戻します。
	 */
	public BigDecimal getKyakusuNtakeZen() {
		return kyakusuNtakeZen;
	}
	/**
	 * @param kyakusuNtakeZen 設定する kyakusuNtakeZen。
	 */
	public void setKyakusuNtakeZen(BigDecimal kyakusuNtakeZen) {
		this.kyakusuNtakeZen = kyakusuNtakeZen;
	}
	/**
	 * @return kyakusuNtaku を戻します。
	 */
	public BigDecimal getKyakusuNtaku() {
		return kyakusuNtaku;
	}
	/**
	 * @param kyakusuNtaku 設定する kyakusuNtaku。
	 */
	public void setKyakusuNtaku(BigDecimal kyakusuNtaku) {
		this.kyakusuNtaku = kyakusuNtaku;
	}
	/**
	 * @return kyakusuNtakuZen を戻します。
	 */
	public BigDecimal getKyakusuNtakuZen() {
		return kyakusuNtakuZen;
	}
	/**
	 * @param kyakusuNtakuZen 設定する kyakusuNtakuZen。
	 */
	public void setKyakusuNtakuZen(BigDecimal kyakusuNtakuZen) {
		this.kyakusuNtakuZen = kyakusuNtakuZen;
	}
	/**
	 * @return kyakuTankaNsum を戻します。
	 */
	public BigDecimal getKyakuTankaNsum() {
		return kyakuTankaNsum;
	}
	/**
	 * @param kyakuTankaNsum 設定する kyakuTankaNsum。
	 */
	public void setKyakuTankaNsum(BigDecimal kyakuTankaNsum) {
		this.kyakuTankaNsum = kyakuTankaNsum;
	}
	/**
	 * @return kyakuTankaNtake を戻します。
	 */
	public BigDecimal getKyakuTankaNtake() {
		return kyakuTankaNtake;
	}
	/**
	 * @param kyakuTankaNtake 設定する kyakuTankaNtake。
	 */
	public void setKyakuTankaNtake(BigDecimal kyakuTankaNtake) {
		this.kyakuTankaNtake = kyakuTankaNtake;
	}
	/**
	 * @return kyakuTankaNtaku を戻します。
	 */
	public BigDecimal getKyakuTankaNtaku() {
		return kyakuTankaNtaku;
	}
	/**
	 * @param kyakuTankaNtaku 設定する kyakuTankaNtaku。
	 */
	public void setKyakuTankaNtaku(BigDecimal kyakuTankaNtaku) {
		this.kyakuTankaNtaku = kyakuTankaNtaku;
	}
	/**
	 * @return misecntKbnNsum を戻します。
	 */
	public BigDecimal getMisecntKbnNsum() {
		return misecntKbnNsum;
	}
	/**
	 * @param misecntKbnNsum 設定する misecntKbnNsum。
	 */
	public void setMisecntKbnNsum(BigDecimal misecntKbnNsum) {
		this.misecntKbnNsum = misecntKbnNsum;
	}
	/**
	 * @return misecntKbnNtake を戻します。
	 */
	public BigDecimal getMisecntKbnNtake() {
		return misecntKbnNtake;
	}
	/**
	 * @param misecntKbnNtake 設定する misecntKbnNtake。
	 */
	public void setMisecntKbnNtake(BigDecimal misecntKbnNtake) {
		this.misecntKbnNtake = misecntKbnNtake;
	}
	/**
	 * @return misecntKbnNtaku を戻します。
	 */
	public BigDecimal getMisecntKbnNtaku() {
		return misecntKbnNtaku;
	}
	/**
	 * @param misecntKbnNtaku 設定する misecntKbnNtaku。
	 */
	public void setMisecntKbnNtaku(BigDecimal misecntKbnNtaku) {
		this.misecntKbnNtaku = misecntKbnNtaku;
	}
	/**
	 * @return uriageNsum を戻します。
	 */
	public BigDecimal getUriageNsum() {
		return uriageNsum;
	}
	/**
	 * @param uriageNsum 設定する uriageNsum。
	 */
	public void setUriageNsum(BigDecimal uriageNsum) {
		this.uriageNsum = uriageNsum;
	}
	/**
	 * @return uriageNsumZen を戻します。
	 */
	public BigDecimal getUriageNsumZen() {
		return uriageNsumZen;
	}
	/**
	 * @param uriageNsumZen 設定する uriageNsumZen。
	 */
	public void setUriageNsumZen(BigDecimal uriageNsumZen) {
		this.uriageNsumZen = uriageNsumZen;
	}
	/**
	 * @return uriageNtake を戻します。
	 */
	public BigDecimal getUriageNtake() {
		return uriageNtake;
	}
	/**
	 * @param uriageNtake 設定する uriageNtake。
	 */
	public void setUriageNtake(BigDecimal uriageNtake) {
		this.uriageNtake = uriageNtake;
	}
	/**
	 * @return uriageNtakeZen を戻します。
	 */
	public BigDecimal getUriageNtakeZen() {
		return uriageNtakeZen;
	}
	/**
	 * @param uriageNtakeZen 設定する uriageNtakeZen。
	 */
	public void setUriageNtakeZen(BigDecimal uriageNtakeZen) {
		this.uriageNtakeZen = uriageNtakeZen;
	}
	/**
	 * @return uriageNtaku を戻します。
	 */
	public BigDecimal getUriageNtaku() {
		return uriageNtaku;
	}
	/**
	 * @param uriageNtaku 設定する uriageNtaku。
	 */
	public void setUriageNtaku(BigDecimal uriageNtaku) {
		this.uriageNtaku = uriageNtaku;
	}
	/**
	 * @return uriageNtakuZen を戻します。
	 */
	public BigDecimal getUriageNtakuZen() {
		return uriageNtakuZen;
	}
	/**
	 * @param uriageNtakuZen 設定する uriageNtakuZen。
	 */
	public void setUriageNtakuZen(BigDecimal uriageNtakuZen) {
		this.uriageNtakuZen = uriageNtakuZen;
	}
	/**
	 * @return zenHiKyakusuNsum を戻します。
	 */
	public BigDecimal getZenHiKyakusuNsum() {
		return zenHiKyakusuNsum;
	}
	/**
	 * @param zenHiKyakusuNsum 設定する zenHiKyakusuNsum。
	 */
	public void setZenHiKyakusuNsum(BigDecimal zenHiKyakusuNsum) {
		this.zenHiKyakusuNsum = zenHiKyakusuNsum;
	}
	/**
	 * @return zenHiKyakusuNtake を戻します。
	 */
	public BigDecimal getZenHiKyakusuNtake() {
		return zenHiKyakusuNtake;
	}
	/**
	 * @param zenHiKyakusuNtake 設定する zenHiKyakusuNtake。
	 */
	public void setZenHiKyakusuNtake(BigDecimal zenHiKyakusuNtake) {
		this.zenHiKyakusuNtake = zenHiKyakusuNtake;
	}
	/**
	 * @return zenHiKyakusuNtaku を戻します。
	 */
	public BigDecimal getZenHiKyakusuNtaku() {
		return zenHiKyakusuNtaku;
	}
	/**
	 * @param zenHiKyakusuNtaku 設定する zenHiKyakusuNtaku。
	 */
	public void setZenHiKyakusuNtaku(BigDecimal zenHiKyakusuNtaku) {
		this.zenHiKyakusuNtaku = zenHiKyakusuNtaku;
	}
	/**
	 * @return zenHikyakuTankaNsum を戻します。
	 */
	public BigDecimal getZenHikyakuTankaNsum() {
		return zenHikyakuTankaNsum;
	}
	/**
	 * @param zenHikyakuTankaNsum 設定する zenHikyakuTankaNsum。
	 */
	public void setZenHikyakuTankaNsum(BigDecimal zenHikyakuTankaNsum) {
		this.zenHikyakuTankaNsum = zenHikyakuTankaNsum;
	}
	/**
	 * @return zenHikyakuTankaNtake を戻します。
	 */
	public BigDecimal getZenHikyakuTankaNtake() {
		return zenHikyakuTankaNtake;
	}
	/**
	 * @param zenHikyakuTankaNtake 設定する zenHikyakuTankaNtake。
	 */
	public void setZenHikyakuTankaNtake(BigDecimal zenHikyakuTankaNtake) {
		this.zenHikyakuTankaNtake = zenHikyakuTankaNtake;
	}
	/**
	 * @return zenHikyakuTankaNtaku を戻します。
	 */
	public BigDecimal getZenHikyakuTankaNtaku() {
		return zenHikyakuTankaNtaku;
	}
	/**
	 * @param zenHikyakuTankaNtaku 設定する zenHikyakuTankaNtaku。
	 */
	public void setZenHikyakuTankaNtaku(BigDecimal zenHikyakuTankaNtaku) {
		this.zenHikyakuTankaNtaku = zenHikyakuTankaNtaku;
	}
	/**
	 * @return zenHiUriageNsum を戻します。
	 */
	public BigDecimal getZenHiUriageNsum() {
		return zenHiUriageNsum;
	}
	/**
	 * @param zenHiUriageNsum 設定する zenHiUriageNsum。
	 */
	public void setZenHiUriageNsum(BigDecimal zenHiUriageNsum) {
		this.zenHiUriageNsum = zenHiUriageNsum;
	}
	/**
	 * @return zenHiUriageNtake を戻します。
	 */
	public BigDecimal getZenHiUriageNtake() {
		return zenHiUriageNtake;
	}
	/**
	 * @param zenHiUriageNtake 設定する zenHiUriageNtake。
	 */
	public void setZenHiUriageNtake(BigDecimal zenHiUriageNtake) {
		this.zenHiUriageNtake = zenHiUriageNtake;
	}
	/**
	 * @return zenHiUriageNtaku を戻します。
	 */
	public BigDecimal getZenHiUriageNtaku() {
		return zenHiUriageNtaku;
	}
	/**
	 * @param zenHiUriageNtaku 設定する zenHiUriageNtaku。
	 */
	public void setZenHiUriageNtaku(BigDecimal zenHiUriageNtaku) {
		this.zenHiUriageNtaku = zenHiUriageNtaku;
	}
	/**
	 * @return zenKyakuTankaNsum を戻します。
	 */
	public BigDecimal getZenKyakuTankaNsum() {
		return zenKyakuTankaNsum;
	}
	/**
	 * @param zenKyakuTankaNsum 設定する zenKyakuTankaNsum。
	 */
	public void setZenKyakuTankaNsum(BigDecimal zenKyakuTankaNsum) {
		this.zenKyakuTankaNsum = zenKyakuTankaNsum;
	}
	/**
	 * @return zenKyakuTankaNtake を戻します。
	 */
	public BigDecimal getZenKyakuTankaNtake() {
		return zenKyakuTankaNtake;
	}
	/**
	 * @param zenKyakuTankaNtake 設定する zenKyakuTankaNtake。
	 */
	public void setZenKyakuTankaNtake(BigDecimal zenKyakuTankaNtake) {
		this.zenKyakuTankaNtake = zenKyakuTankaNtake;
	}
	/**
	 * @return zenKyakuTankaNtaku を戻します。
	 */
	public BigDecimal getZenKyakuTankaNtaku() {
		return zenKyakuTankaNtaku;
	}
	/**
	 * @param zenKyakuTankaNtaku 設定する zenKyakuTankaNtaku。
	 */
	public void setZenKyakuTankaNtaku(BigDecimal zenKyakuTankaNtaku) {
		this.zenKyakuTankaNtaku = zenKyakuTankaNtaku;
	}
	/**
	 * @return lastIndex を戻します。
	 */
	public boolean isLastIndex() {
		return lastIndex;
	}
	/**
	 * @param lastIndex 設定する lastIndex。
	 */
	public void setLastIndex(boolean lastIndex) {
		this.lastIndex = lastIndex;
	}  
}
