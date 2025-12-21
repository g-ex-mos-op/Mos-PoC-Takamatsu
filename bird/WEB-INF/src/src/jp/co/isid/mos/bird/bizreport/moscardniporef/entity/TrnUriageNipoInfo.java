package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

public class TrnUriageNipoInfo {
    
    public static final String TABLE = "BD35ZNMC";
    
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
    
    public static final String rClass_COLUMN = "R_CLASS";
    
    public static final String tasseiUriClass_COLUMN = "TASSEI_URI_CLASS";
    
    public static final String zenUriClass_COLUMN = "ZEN_URI_CLASS";
    
    public static final String zenKyaClass_COLUMN = "ZEN_KYA_CLASS";
    
    public static final String zenTanClass_COLUMN = "ZEN_TAN_CLASS";
    
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    
    public static final String zenKyakusu_COLUMN = "KYAKUSU_ZEN";
    
    public static final String onerYosan_COLUMN = "ONER_YOSAN";
    
    public static final String uriYosan_COLUMN = "URI_YOSAN";

    public static final String issueCnt_COLUMN = "ISSUE_CNT";
    
    public static final String chargeKin_COLUMN = "CHARGE_KIN";
    
    public static final String chargeKen_COLUMN = "CHARGE_KEN";
    
    public static final String kessaiKin_COLUMN = "KESSAI_KIN";
    
    public static final String kessaiKen_COLUMN = "KESSAI_KEN";
    
    public static final String zenIssueCnt_COLUMN = "ZEN_ISSUE_CNT";
    
    public static final String zenChargeKin_COLUMN = "ZEN_CHARGE_KIN";
    
    public static final String zenChargeKen_COLUMN = "ZEN_CHARGE_KEN";
    
    public static final String zenKessaiKin_COLUMN = "ZEN_KESSAI_KIN";
    
    public static final String zenkessaiKen_COLUMN = "ZEN_KESSAI_KEN";
    
    public static final String chargeKinCancel_COLUMN = "CHARGE_KIN_CANCEL";
    
    public static final String chargeKenCancel__COLUMN = "CHARGE_KEN_CANCEL";
    
    public static final String useKinCancel_COLUMN = "USE_KIN_CANCEL";
    
    public static final String useKenCancel_COLUMN = "USE_KEN_CANCEL";
    
    public static final String bonusVIssue_COLUMN = "BONUS_V_ISSUE";
    
    public static final String bonusVUse_COLUMN = "BONUS_V_USE";
    
    public static final String couponVIssue_COLUMN = "COUPON_V_ISSUE";
    
    public static final String couponVUse_COLUMN = "COUPON_V_USE";
    
    public static final String zandaka_COLUMN = "ZANDAKA";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String usernamekj_COLUMN = "USERNAMEKJ";
    
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
     * 当年営業日数
     */
    private BigDecimal eigyoDays;
    
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen;
    
    /**
     * 発行枚数
     */
    private BigDecimal issueCnt = new BigDecimal("0");
    
    /**
     * 入金額
     */
    private BigDecimal chargeKin = new BigDecimal("0");
    
    /**
     * 入金件数
     */
    private BigDecimal chargeKen = new BigDecimal("0");
    
    /**
     * 決済金額
     */
    private BigDecimal kessaiKin = new BigDecimal("0");
    
    /**
     * 決済件数
     */
    private BigDecimal kessaiKen = new BigDecimal("0");
    
    /**
     * 前年発行枚数
     */
    private BigDecimal zenIssueCnt = new BigDecimal("0");
    
    /**
     * 発行枚数（前年比）
     * */
    private BigDecimal zenIssueCntHiritu = new BigDecimal("0");
    
    /**
     * 発行枚数（前年比）クラス
     */
    private String zenIssueCntHirituClass;
    
    /**
     * 前年同月チャージ金額
     */
    private BigDecimal zenChargeKin = new BigDecimal("0");    
    
    /**
     * チャージ金額（売上比）
     * */
    private BigDecimal chargeKinUriHi = new BigDecimal("0");
    
    /**
     * チャージ金額（売上比）クラス
     */
    private String chargeKinUriHiClass;
    
    /**
     * チャージ金額（前年比）
     * */
    private BigDecimal chargeKinZenHi = new BigDecimal("0");
    
    /**
     * チャージ金額（前年比）クラス
     */
    private String chargeKinZenHiClass;
    
    /**
     * 前年同月チャージ件数
     */
    private BigDecimal zenChargeKen = new BigDecimal("0");
    
    /**
     * チャージ件数（客数比）
     * */
    private BigDecimal chargeKenKyakuHi = new BigDecimal("0");
    
    /**
     * チャージ件数（客数比）クラス
     */
    private String chargeKenKyakuHiClass;
    
    /**
     * チャージ件数（前年比）
     * */
    private BigDecimal chargeKenZenHi = new BigDecimal("0");
    
    /**
     * チャージ件数（前年比）クラス
     */
    private String chargeKenZenHiClass;
    
    /**
     * チャージ単価
     * */
    private BigDecimal chargeTanka = new BigDecimal("0");
    
    /**
     * チャージ単価クラス
     */
    private String chargeTankaClass;
    
    /**
     * 前年チャージ単価
     * */
    private BigDecimal zenChargeTanka = new BigDecimal("0");
    /**
     * チャージ単価（単価比）
     * */
    private BigDecimal chargeTankaHi = new BigDecimal("0");
    
    /**
     * チャージ単価（単価比）クラス
     */
    private String chargeTankaHiClass;
    
    /**
     * チャージ単価（前年比）
     * */
    private BigDecimal chargeTankaZenHi = new BigDecimal("0");
    
    /**
     * チャージ単価（前年比）クラス
     */
    private String chargeTankaZenHiClass;
    
    /**
     * 前年同月決済金額
     */
    private BigDecimal zenKessaiKin = new BigDecimal("0");    
    
    /**
     * 前年同月決済件数
     */
    private BigDecimal zenkessaiKen = new BigDecimal("0");    
    
    /**
     * 決済金額（売上比）
     * */
    private BigDecimal kessaiKinUrihi = new BigDecimal("0");
    
    /**
     * 決済金額（売上比）クラス
     */
    private String kessaiKinUrihiClass;
    
    /**
     * 決済金額（前年比）
     * */
    private BigDecimal kessaiKinZenhi = new BigDecimal("0");
    
    /**
     * 決済金額（前年比）クラス
     */
    private String kessaiKinZenhiClass;
    /**
     * 決済件数（客数比）
     * */
    private BigDecimal kessaiKenKyakuhi = new BigDecimal("0");
    
    /**
     * 決済件数（客数比）クラス
     */
    private String kessaiKenKyakuhiClass;
    /**
     * 決済件数（前年比）
     * */
    private BigDecimal kessaiKenZenhi = new BigDecimal("0");
    
    /**
     * 決済件数（前年比）クラス
     */
    private String kessaiKenZenhiClass;
    /**
     * 決済単価
     * */
    private BigDecimal kessaiTanka = new BigDecimal("0");
    
    /**
     * 前年決済単価
     * */
    private BigDecimal zenKessaiTanka = new BigDecimal("0");
    
    /**
     * 決済単価クラス
     */
    private String kessaiTankaClass;
    /**
     * 決済単価（単価比）
     * */
    private BigDecimal kessaiTankaHi = new BigDecimal("0");
    
    /**
     * 決済単価（単価比）クラス
     */
    private String kessaiTankaHiClass;
    /**
     * 決済単価（前年比）
     * */
    private BigDecimal kessaiTankaZenhi = new BigDecimal("0");
    
    /**
     * 決済単価（前年比）クラス
     */
    private String kessaiTankaZenhiClass;
    
    /**
     * 入金取消金額
     * */
    private BigDecimal chargeKinCancel = new BigDecimal("0");
    /**
     * 入金取消件数
     * */
    private BigDecimal chargeKenCancel = new BigDecimal("0");
    /**
     * 利用取消金額
     * */
    private BigDecimal useKinCancel = new BigDecimal("0");
    /**
     * 利用取消件数
     * */
    private BigDecimal useKenCancel = new BigDecimal("0");
    /**
     * 発行ボーナスバリュー
     * */
    private BigDecimal bonusVIssue = new BigDecimal("0");
    /**
     * 利用ボーナスバリュー
     * */
    private BigDecimal bonusVUse = new BigDecimal("0");
    /**
     * 発行クーポンバリュー
     * */
    private BigDecimal couponVIssue = new BigDecimal("0");
    
    /**
     * 利用クーポンバリュー
     * */
    private BigDecimal couponVUse = new BigDecimal("0");
    
    /**
     * 前受残高
     * */
    private BigDecimal zandaka = new BigDecimal("0");

    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * SV名称
     */
    private String usernamekj;
    
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
     * 発行枚数を取得します。
     * @return issueCnt 発行枚数
     */
    public BigDecimal getIssueCnt() {
        return issueCnt;
    }
    /**
     * 発行枚数を設定します。
     * @param issueCnt 発行枚数
     */
    public void setIssueCnt(BigDecimal issueCnt) {
        this.issueCnt = issueCnt;
    }
    
    /**
     * チャージ金額を取得します。
     * @return チャージ金額
     */
    public BigDecimal getChargeKin() {
        return chargeKin;
    }
    /**
     * チャージ金額を設定します。
     * @param chargeKin チャージ金額
     */
    public void setChargeKin(BigDecimal chargeKin) {
        this.chargeKin = chargeKin;
    }
    
    /**
     * チャージ件数を取得します。
     * @return チャージ件数
     */
    public BigDecimal getChargeKen() {
        return chargeKen;
    }
    /**
     * チャージ件数を設定します。
     * @param chargeKen チャージ件数
     */
    public void setChargeKen(BigDecimal chargeKen) {
        this.chargeKen = chargeKen;
    }
    
    /**
     * 決済金額を取得します。
     * @return 決済金額
     */
    public BigDecimal getKessaiKin() {
        return kessaiKin;
    }
    /**
     * 決済金額を設定します。
     * @param chargeKen 決済金額
     */
    public void setKessaiKin(BigDecimal kessaiKin) {
        this.kessaiKin = kessaiKin;
    }
    
    /**
     * 決済件数を取得します。
     * @return 決済件数
     */
    public BigDecimal getKessaiKen() {
        return kessaiKen;
    }
    /**
     * 決済件数を設定します。
     * @param chargeKen 決済件数
     */
    public void setKessaiKen(BigDecimal kessaiKen) {
        this.kessaiKen = kessaiKen;
    }
    
    /**
     * 前年発行枚数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenIssueCnt() {
        return zenIssueCnt;
    }
    /**
     * 前年発行枚数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenIssueCnt(BigDecimal zenIssueCnt) {
        this.zenIssueCnt = zenIssueCnt;
    }
    /**
     * 発行枚数（前年比）を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenIssueCntHiritu() {
        return zenIssueCntHiritu;
    }
    /**
     * 発行枚数（前年比）を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenIssueCntHiritu(BigDecimal zenIssueCntHiritu) {
        this.zenIssueCntHiritu = zenIssueCntHiritu;
    }
    
    /**
     * 発行枚数（前年比）クラスを取得します。
     * @return 達成率（売上)クラス
     */
    public String getZenIssueCntHirituClass() {
        return zenIssueCntHirituClass;
    }
    /**
     * 発行枚数（前年比）クラスを設定します。
     * @param tasseiUriClass 達成率（売上)クラス
     */
    public void setZenIssueCntHirituClass(String zenIssueCntHirituClass) {
        this.zenIssueCntHirituClass = zenIssueCntHirituClass;
    }
    
    
    /**
     * 前年チャージ金額を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenChargeKin() {
        return zenChargeKin;
    }
    /**
     * 前年チャージ金額を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenChargeKin(BigDecimal zenChargeKin) {
        this.zenChargeKin = zenChargeKin;
    }
    /**
     * チャージ金額（売上比）を取得します。
     * @return チャージ金額（売上比）
     */
    public BigDecimal getChargeKinUriHi() {
        return chargeKinUriHi;
    }
    /**
     * チャージ金額（売上比）を設定します。
     * @param chargeKinUriHi チャージ金額（売上比）
     */
    public void setChargeKinUriHi(BigDecimal chargeKinUriHi) {
        this.chargeKinUriHi = chargeKinUriHi;
    }
    
    /**
     * チャージ金額（売上比）クラスを取得します。
     * @return チャージ金額（売上比）クラス
     */
    public String getChargeKinUriHiClass() {
        return chargeKinUriHiClass;
    }
    /**
     * チャージ金額（売上比）クラスを設定します。
     * @param chargeKinUriHiClass チャージ金額（売上比）クラス
     */
    public void setChargeKinUriHiClass(String chargeKinUriHiClass) {
        this.chargeKinUriHiClass = chargeKinUriHiClass;
    }
    
    /**
     * チャージ金額（前年比）を取得します。
     * @return チャージ金額（売上比）
     */
    public BigDecimal getChargeKinZenHi() {
        return chargeKinZenHi;
    }
    /**
     * チャージ金額（前年比）を設定します。
     * @param chargeKinUriHi チャージ金額（前年比）
     */
    public void setChargeKinZenHi(BigDecimal chargeKinZenHi) {
        this.chargeKinZenHi = chargeKinZenHi;
    }
    
    /**
     * チャージ金額（前年比）クラスを取得します。
     * @return チャージ金額（前年比）クラス
     */
    public String getChargeKinZenHiClass() {
        return chargeKinZenHiClass;
    }
    /**
     * チャージ金額（前年比）クラスを設定します。
     * @param chargeKinUriHiClass チャージ金額（前年比）クラス
     */
    public void setChargeKinZenHiClass(String chargeKinZenHiClass) {
        this.chargeKinZenHiClass = chargeKinZenHiClass;
    }
    
    
    /**
     * 前年同月チャージ件数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenChargeKen() {
        return zenChargeKen;
    }
    /**
     * 前年同月チャージ件数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenChargeKen(BigDecimal zenChargeKen) {
        this.zenChargeKen = zenChargeKen;
    }
    
    /**
     * チャージ件数（客数比）を取得します。
     * @return チャージ件数（客数比）
     */
    public BigDecimal getChargeKenKyakuHi() {
        return chargeKenKyakuHi;
    }
    /**
     * チャージ件数（客数比）を設定します。
     * @param chargeKinUriHi チャージ件数（客数比）
     */
    public void setChargeKenKyakuHi(BigDecimal chargeKenKyakuHi) {
        this.chargeKenKyakuHi = chargeKenKyakuHi;
    }
    
    /**
     * チャージ件数（客数比）クラスを取得します。
     * @return チャージ件数（客数比）クラス
     */
    public String getChargeKenKyakuHiClass() {
        return chargeKenKyakuHiClass;
    }
    /**
     * チャージ件数（客数比）クラスを設定します。
     * @param chargeKinUriHiClass チャージ件数（客数比）
     */
    public void setChargeKenKyakuHiClass(String chargeKenKyakuHiClass) {
        this.chargeKenKyakuHiClass = chargeKenKyakuHiClass;
    }
    
    /**
     * チャージ件数（前年比）を取得します。
     * @return チャージ件数（前年比）
     */
    public BigDecimal getChargeKenZenHi() {
        return chargeKenZenHi;
    }
    /**
     * チャージ件数（前年比）を設定します。
     * @param chargeKenZenHi チャージ件数（前年比）
     */
    public void setChargeKenZenHi(BigDecimal chargeKenZenHi) {
        this.chargeKenZenHi = chargeKenZenHi;
    }
    
    /**
     * チャージ件数（前年比）クラスを取得します。
     * @return チャージ件数（前年比）クラス
     */
    public String getChargeKenZenHiClass() {
        return chargeKenZenHiClass;
    }
    /**
     * チャージ件数（前年比）クラスを設定します。
     * @param chargeKenZenHiiClass チャージ件数（前年比）
     */
    public void setChargeKenZenHiClass(String chargeKenZenHiClass) {
        this.chargeKenZenHiClass = chargeKenZenHiClass;
    }
    
    /**
     * チャージ単価を取得します。
     * @return チャージ単価
     */
    public BigDecimal getChargeTanka() {
        return chargeTanka;
    }
    /**
     * チャージ単価を設定します。
     * @param chargeTanka チャージ単価
     */
    public void setChargeTanka(BigDecimal chargeTanka) {
        this.chargeTanka = chargeTanka;
    }
    
    /**
     * チャージ単価クラスを取得します。
     * @return チャージ単価クラス
     */
    public String getChargeTankaClass() {
        return chargeTankaClass;
    }
    /**
     * チャージ単価クラスを設定します。
     * @param chargeKenZenHiiClass チャージ単価
     */
    public void setChargeTankaClass(String chargeTankaClass) {
        this.chargeTankaClass = chargeTankaClass;
    }
    /**
     * 前年チャージ単価を取得します。
     * @return 前年チャージ単価
     */
    public BigDecimal getZenChargeTanka() {
        return zenChargeTanka;
    }
    /**
     * 前年チャージ単価を設定します。
     * @param zenChargeTanka 前年チャージ単価
     */
    public void setZenChargeTanka(BigDecimal zenChargeTanka) {
        this.zenChargeTanka = zenChargeTanka;
    }
    /**
     * チャージ単価（単価比）を取得します。
     * @return チャージ単価
     */
    public BigDecimal getChargeTankaHi() {
        return chargeTankaHi;
    }
    /**
     * チャージ単価（単価比）を設定します。
     * @param chargeTanka チャージ単価
     */
    public void setChargeTankaHi(BigDecimal chargeTankaHi) {
        this.chargeTankaHi = chargeTankaHi;
    }
    
    /**
     * チャージ単価（単価比）クラスを取得します。
     * @return チャージ単価クラス
     */
    public String getChargeTankaHiClass() {
        return chargeTankaHiClass;
    }
    /**
     * チャージ単価（単価比）クラスを設定します。
     * @param chargeKenZenHiiClass チャージ単価
     */
    public void setChargeTankaHiClass(String chargeTankaHiClass) {
        this.chargeTankaHiClass = chargeTankaHiClass;
    }
    
    /**
     * チャージ単価（前年比）を取得します。
     * @return チャージ単価（前年比）
     */
    public BigDecimal getChargeTankaZenHi() {
        return chargeTankaZenHi;
    }
    /**
     * チャージ単価（前年比）を設定します。
     * @param chargeTankaZenHi チャージ単価（前年比）
     */
    public void setChargeTankaZenHi(BigDecimal chargeTankaZenHi) {
        this.chargeTankaZenHi = chargeTankaZenHi;
    }
    
    /**
     * チャージ単価（前年比）クラスを取得します。
     * @return  チャージ単価（前年比）クラス
     */
    public String getChargeTankaZenHiClass() {
        return chargeTankaZenHiClass;
    }
    /**
     * チャージ単価（前年比）クラスを設定します。
     * @param chargeKenZenHiiClass  チャージ単価（前年比）
     */
    public void setChargeTankaZenHiClass(String chargeTankaZenHiClass) {
        this.chargeTankaZenHiClass = chargeTankaZenHiClass;
    }
    
    /**
     * 前年決済金額を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenKessaiKin() {
        return zenKessaiKin;
    }
    /**
     * 前年決済金額を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenKessaiKin(BigDecimal zenKessaiKin) {
        this.zenKessaiKin = zenKessaiKin;
    }
    
    /**
     * 前年同月決済件数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenKessaiKen() {
        return zenkessaiKen;
    }
    /**
     * 前年同月決済件数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenKessaiKen(BigDecimal zenkessaiKen) {
        this.zenkessaiKen = zenkessaiKen;
    }
    /**
     * 決済金額（売上比）を取得します。
     * @return 決済金額（売上比）
     */
    public BigDecimal getKessaiKinUrihi() {
        return kessaiKinUrihi;
    }
    /**
     * 決済金額（売上比）を設定します。
     * @param kessaiKinUrihi 決済金額（売上比）
     */
    public void setKessaiKinUrihi(BigDecimal kessaiKinUrihi) {
        this.kessaiKinUrihi = kessaiKinUrihi;
    }
    
    /**
     * 決済金額（売上比）クラスを取得します。
     * @return  決済金額（売上比）クラス
     */
    public String getKessaiKinUrihiClass() {
        return kessaiKinUrihiClass;
    }
    /**
     * 決済金額（売上比）クラスを設定します。
     * @param kessaiKinUrihiClass  決済金額（売上比）
     */
    public void setKessaiKinUrihiClass(String kessaiKinUrihiClass) {
        this.kessaiKinUrihiClass = kessaiKinUrihiClass;
    }
    
    /**
     * 決済金額（前年比）を取得します。
     * @return 決済金額（前年比）
     */
    public BigDecimal getKessaiKinZenhi() {
        return kessaiKinZenhi;
    }
    /**
     * 決済金額（前年比）を設定します。
     * @param kessaiKinUrihi 決済金額（前年比）
     */
    public void setKessaiKinZenhi(BigDecimal kessaiKinZenhi) {
        this.kessaiKinZenhi = kessaiKinZenhi;
    }
    
    /**
     * 決済金額（売上比）クラスを取得します。
     * @return  決済金額（売上比）クラス
     */
    public String getKessaiKinZenhiClass() {
        return kessaiKinZenhiClass;
    }
    /**
     * 決済金額（売上比）クラスを設定します。
     * @param kessaiKinUrihiClass  決済金額（売上比）
     */
    public void setKessaiKinZenhiClass(String kessaiKinZenhiClass) {
        this.kessaiKinZenhiClass = kessaiKinZenhiClass;
    }
    /**
     * 決済件数（客数比）を取得します。
     * @return 決済件数（客数比）
     */
    public BigDecimal getKessaiKenKyakuhi() {
        return kessaiKenKyakuhi;
    }
    /**
     * 決済件数（客数比）を設定します。
     * @param kessaiKenKyakuhi 決済件数（客数比）
     */
    public void setKessaiKenKyakuhi(BigDecimal kessaiKenKyakuhi) {
        this.kessaiKenKyakuhi = kessaiKenKyakuhi;
    }
    
    /**
     * 決済件数（客数比）クラスを取得します。
     * @return  決済件数（客数比）クラス
     */
    public String getKessaiKenKyakuhiClass() {
        return kessaiKenKyakuhiClass;
    }
    /**
     * 決済件数（客数比）クラスを設定します。
     * @param kessaiKinZenhiClass  決済件数（客数比）
     */
    public void setKessaiKenKyakuhiClass(String kessaiKenKyakuhiiClass) {
        this.kessaiKenKyakuhiClass = kessaiKenKyakuhiiClass;
    }
    
    /**
     * 決済件数（前年比）を取得します。
     * @return 決済件数（前年比）
     */
    public BigDecimal getKessaiKenZenhi() {
        return kessaiKenZenhi;
    }
    /**
     * 決済件数（前年比）を設定します。
     * @param kessaiKenKyakuhi 決済件数（前年比）
     */
    public void setKessaiKenZenhi(BigDecimal kessaiKenZenhi) {
        this.kessaiKenZenhi = kessaiKenZenhi;
    }
    
    /**
     * 決済件数（前年比）クラスを取得します。
     * @return  決済件数（前年比）クラス
     */
    public String getKessaiKenZenhiClass() {
        return kessaiKenZenhiClass;
    }
    /**
     * 決済件数（前年比）クラスを設定します。
     * @param kessaiKinZenhiClass  決済件数（前年比）
     */
    public void setKessaiKenZenhiClass(String kessaiKenZenhiClass) {
        this.kessaiKenZenhiClass = kessaiKenZenhiClass;
    }
    /**
     * 決済単価を取得します。
     * @return 決済単価
     */
    public BigDecimal getKessaiTanka() {
        return kessaiTanka;
    }
    /**
     * 決済単価を設定します。
     * @param kessaiTanka 決済単価
     */
    public void setKessaiTanka(BigDecimal kessaiTanka) {
        this.kessaiTanka = kessaiTanka;
    }
    
    /**
     * 決済単価クラスを取得します。
     * @return  決済単価クラス
     */
    public String getKessaiTankaClass() {
        return kessaiTankaClass;
    }
    /**
     * 決済単価クラスを設定します。
     * @param kessaiTankaClass  決済単価
     */
    public void setｋessaiTankaClass(String kessaiTankaClass) {
        this.kessaiTankaClass = kessaiTankaClass;
    }
    /**
     * 前年決済単価を取得します。
     * @return 決済単価
     */
    public BigDecimal getZenKessaiTanka() {
        return zenKessaiTanka;
    }
    /**
     * 前年決済単価を設定します。
     * @param kessaiTanka 決済単価
     */
    public void setZenKessaiTanka(BigDecimal zenKessaiTanka) {
        this.zenKessaiTanka = zenKessaiTanka;
    }
    
    /**
     * 決済単価（単価比）を取得します。
     * @return 決済単価（単価比）
     */
    public BigDecimal getKessaiTankaHi() {
        return kessaiTankaHi;
    }
    /**
     * 決済単価（単価比）を設定します。
     * @param kessaiTankaHi 決済単価（単価比）
     */
    public void setKessaiTankaHi(BigDecimal kessaiTankaHi) {
        this.kessaiTankaHi = kessaiTankaHi;
    }
    
    /**
     * 決済単価（単価比）クラスを取得します。
     * @return  決済単価（単価比）クラス
     */
    public String getKessaiTankaHiClass() {
        return kessaiTankaHiClass;
    }
    /**
     * 決済単価（単価比）クラスを設定します。
     * @param kessaiTankaHiClass  決済単価（単価比）
     */
    public void setKessaiTankaHiClass(String kessaiTankaHiClass) {
        this.kessaiTankaHiClass = kessaiTankaHiClass;
    }
    /**
     * 決済単価（前年比）を取得します。
     * @return 決済単価（単価比）
     */
    public BigDecimal getKessaiTankaZenhi() {
        return kessaiTankaZenhi;
    }
    /**
     * 決済単価（前年比）を設定します。
     * @param kessaiTankaZenhi 決済単価（前年比）
     */
    public void setKessaiTankaZenhi(BigDecimal kessaiTankaZenhi) {
        this.kessaiTankaZenhi = kessaiTankaZenhi;
    }
    
    /**
     * 決済単価（前年比）クラスを取得します。
     * @return  決済単価（前年比）クラス
     */
    public String getKessaiTankaZenhiClass() {
        return kessaiTankaZenhiClass;
    }
    /**
     * 決済単価（前年比）クラスを設定します。
     * @param kessaiTankaHiClass  決済単価（前年比）
     */
    public void setKessaiTankaZenhiClass(String kessaiTankaZenhiClass) {
        this.kessaiTankaZenhiClass = kessaiTankaZenhiClass;
    }
    
    /**
     * 入金取消金額を取得します。
     * @return 入金取消金額
     */
    public BigDecimal getChargeKinCancel() {
        return chargeKinCancel;
    }
    /**
     * 入金取消金額を設定します。
     * @param chargeKinCancel 入金取消金額
     */
    public void setChargeKinCancel(BigDecimal chargeKinCancel) {
        this.chargeKinCancel = chargeKinCancel;
    }
    
    /**
     * 入金取消件数を取得します。
     * @return 入金取消件数
     */
    public BigDecimal getChargeKenCancel() {
        return chargeKenCancel;
    }
    /**
     * 入金取消件数を設定します。
     * @param chargeKenCancel 入金取消件数
     */
    public void setChargeKenCancel(BigDecimal chargeKenCancel) {
        this.chargeKenCancel = chargeKenCancel;
    }
    
    /**
     * 利用取消金額を取得します。
     * @return 利用取消金額
     */
    public BigDecimal getUseKinCancel() {
        return useKinCancel;
    }
    /**
     * 利用取消金額を設定します。
     * @param useKinCancel 利用取消金額
     */
    public void setUseKinCancel(BigDecimal useKinCancel) {
        this.useKinCancel = useKinCancel;
    }

    /**
     * 利用取消件数を取得します。
     * @return 利用取消件数
     */
    public BigDecimal getUseKenCancel() {
        return useKenCancel;
    }
    /**
     * 利用取消件数を設定します。
     * @param useKinCancel 利用取消件数
     */
    public void setUseKenCancel(BigDecimal useKenCancel) {
        this.useKenCancel = useKenCancel;
    }
    
    /**
     * 発行ボーナスバリューを取得します。
     * @return 発行ボーナスバリュー
     */
    public BigDecimal getBonusVIssue() {
        return bonusVIssue;
    }
    /**
     * 発行ボーナスバリューを設定します。
     * @param bonusVIssue 発行ボーナスバリュー
     */
    public void setBonusVIssue(BigDecimal bonusVIssue) {
        this.bonusVIssue = bonusVIssue;
    }
    
    /**
     * 利用ボーナスバリューを取得します。
     * @return 利用ボーナスバリュー
     */
    public BigDecimal getBonusVUse() {
        return bonusVUse;
    }
    
    /**
     * 利用ボーナスバリューを設定します。
     * @param bonusVUse 利用ボーナスバリュー
     */
    public void setBonusVUse(BigDecimal bonusVUse) {
        this.bonusVUse = bonusVUse;
    }
    
    /**
     * 発行クーポンバリューを取得します。
     * @return 発行クーポンバリュー
     */
    public BigDecimal getCouponVIssue() {
        return couponVIssue;
    }
    /**
     * 発行クーポンバリューを設定します。
     * @param couponVIssue 発行クーポンバリュー
     */
    public void setCouponVIssue(BigDecimal couponVIssue) {
        this.couponVIssue = couponVIssue;
    }
    
    /**
     * 利用クーポンバリューを取得します。
     * @return 利用クーポンバリュー
     */
    public BigDecimal getCouponVUse() {
        return couponVUse;
    }
    /**
     * 利用クーポンバリューを設定します。
     * @param couponVUse 利用クーポンバリュー
     */
    public void setCouponVUse(BigDecimal couponVUse) {
        this.couponVUse = couponVUse;
    }
    /**
     * 前受残高を取得します。
     * @return 前受残高
     */
    public BigDecimal getZandaka() {
        return zandaka;
    }
    /**
     * 前受残高を設定します。
     * @param zandaka 前受残高
     */
    public void setZandaka(BigDecimal zandaka) {
        this.zandaka = zandaka;
    }
    
    /**
     * SVコードを取得します。
     * @return SVコード
     */
    public String getSvCd() {
        if(svCd == null) {
            return "";
        }
        return svCd;
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
    public String getUsernamekj() {
        if(usernamekj == null) {
            return "";
        }
        return usernamekj;
    }
    /**
     * SV名称を設定します。
     * @param usernamekj_ SV名称
     */
    public void setUsernamekj(String usernamekj) {
        this.usernamekj = usernamekj;
    }
}
