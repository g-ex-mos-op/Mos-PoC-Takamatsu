package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity;

import java.math.BigDecimal;

public class TrnUriageNipoInfo {

    public static final String TABLE = "BT60ZNIP";

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

    public static final String nebiki_COLUMN = "NEBIKI";

    public static final String nebikiZen_COLUMN = "NEBIKI_ZEN";

    public static final String uriageAfterNebiki_COLUMN = "URIAGE_AFTER_NEBIKI";

    public static final String yosan_COLUMN = "YOSAN";

    public static final String tassei_COLUMN = "TASSEI";

    public static final String tasseiAfterNebiki_COLUMN = "TASSEI_AFTER_NEBIKI";

    public static final String uriageZen_COLUMN = "URIAGE_ZEN";

    public static final String uriageZenAfterNebiki_COLUMN = "URIAGE_ZEN_AFTER_NEBIKI";

    public static final String zenHiUri_COLUMN = "ZEN_HI_URI";

    public static final String zenHiUriAfterNebiki_COLUMN = "ZEN_HI_URI_AFTER_NEBIKI";

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

    public static final String tasseiAfterNebikiUriClass_COLUMN = "TASSEI_AFTER_NEBIKI_URI_CLASS";

    public static final String zenUriClass_COLUMN = "ZEN_URI_CLASS";

    public static final String zenAfterNebikiUriClass_COLUMN = "ZEN_AFTER_NEBIKI_URI_CLASS";

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
     * 値引き
     */
    private BigDecimal nebiki;

    /**
     * 前年値引き
     */
    private BigDecimal nebikiZen;

    /**
     * 値引後売上
     */
    private BigDecimal uriageAfterNebiki;

    /**
     * 予算
     */
    private BigDecimal yosan;

    /**
     * 達成率
     */
    private BigDecimal tassei;

    /**
     * 値引後達成率
     */
    private BigDecimal tasseiAfterNebiki;

    /**
     * 前年売上
     */
    private BigDecimal uriageZen;

    /**
     * 前年売上（値引後）
     */
    private BigDecimal uriageZenAfterNebiki;

    /**
     * 前年比(売上)
     */
    private BigDecimal zenHiUri;

    /**
     * 前年比(売上)(値引後)
     */
    private BigDecimal zenHiUriAfterNebiki;

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
     * 値引後達成率（売上)クラス
     */
    private String tasseiAfterNebikiUriClass;

    /**
     * 前年比(売上)クラス
     */
    private String zenUriClass;

    /**
     * 値引後前年比(売上)クラス
     */
    private String zenAfterNebikiUriClass;

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
     * 値引を取得します。
     * @return 値引
     */
    public BigDecimal getNebiki() {
        return nebiki;
    }
    /**
     * 値引を設定します。
     * @param nebiki 値引
     */
    public void setNebiki(BigDecimal nebiki) {
        this.nebiki = nebiki;
    }

    /**
     * 値引後売上を取得します。
     * @return 値引後売上
     */
    public BigDecimal getUriageAfterNebiki() {
        return uriageAfterNebiki;
    }
    /**
     * 値引後売上を設定します。
     * @param uriageAfterNebiki 値引後売上
     */
    public void setUriageAfterNebiki(BigDecimal uriageAfterNebiki) {
        this.uriageAfterNebiki = uriageAfterNebiki;
    }

    /**
     * 前年値引を取得します。
     * @return 前年値引
     */
    public BigDecimal getNebikiZen() {
        return nebikiZen;
    }
    /**
     * 前年値引を設定します。
     * @param nebikiZen 前年値引
     */
    public void setNebikiZen(BigDecimal nebikiZen) {
        this.nebikiZen = nebikiZen;
    }

    /**
     * 前年値引後売上を取得します。
     * @return 前年値引後売上
     */
    public BigDecimal getUriageZenAfterNebiki() {
        return uriageZenAfterNebiki;
    }
    /**
     * 前年値引後売上を設定します。
     * @param uriageZenAfterNebiki 前年値引後売上
     */
    public void setUriageZenAfterNebiki(BigDecimal uriageZenAfterNebiki) {
        this.uriageZenAfterNebiki = uriageZenAfterNebiki;
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
     * 値引後達成率を取得します。
     * @return 値引後達成率
     */
    public BigDecimal getTasseiAfterNebiki() {
        return tasseiAfterNebiki;
    }
    /**
     * 値引後達成率を設定します。
     * @param tasseiAfterNebiki 値引後達成率
     */
    public void setTasseiAfterNebiki(BigDecimal tasseiAfterNebiki) {
        this.tasseiAfterNebiki = tasseiAfterNebiki;
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
     * 前年比(売上)(値引後)を取得します。
     * @return 前年比(売上)(値引後)
     */
    public BigDecimal getZenHiUriAfterNebiki() {
        return zenHiUriAfterNebiki;
    }
    /**
     * 前年比(売上)(値引後)を設定します。
     * @param zenHiUriAfterNebiki 前年比(売上)(値引後)
     */
    public void setZenHiUriAfterNebiki(BigDecimal zenHiUriAfterNebiki) {
        this.zenHiUriAfterNebiki = zenHiUriAfterNebiki;
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
     * 値引後達成率（売上)クラスを取得します。
     * @return 値引後達成率（売上)クラス
     */
    public String getTasseiAfterNebikiUriClass() {
        return tasseiAfterNebikiUriClass;
    }
    /**
     * 値引後達成率（売上)クラスを設定します。
     * @param tasseiAfterNebikiUriClass 値引後達成率（売上)クラス
     */
    public void setTasseiAfterNebikiUriClass(String tasseiAfterNebikiUriClass) {
        this.tasseiAfterNebikiUriClass = tasseiAfterNebikiUriClass;
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
     * 値引後前年比(売上)クラスを取得します。
     * @return 値引後前年比(売上)クラス
     */
    public String getZenAfterNebikiUriClass() {
        return zenAfterNebikiUriClass;
    }
    /**
     * 値引後前年比(売上)クラスを設定します。
     * @param zenAfterNebikiUriClass 値引後前年比(売上)クラス
     */
    public void setZenAfterNebikiUriClass(String zenAfterNebikiUriClass) {
        this.zenAfterNebikiUriClass = zenAfterNebikiUriClass;
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

}
