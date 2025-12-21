package jp.co.isid.mos.bird.bizreport.common.suiiref.entity;

import java.math.BigDecimal;

/**
 * 推移表共通Entity[推移表]
 * 
 * 作成日:2013/05/01
 * @author xkinu
 *
 */
public abstract class SuiiRefUIEntity {
        
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String syozokuSibu_COLUMN = "SYOZOKU_SIBU"; //add xou.zoubun 2019/11/19

    public static final String uriage_COLUMN = "URIAGE";

//add 2019/07/18 #35 USI張 begin
    public static final String nebiki_COLUMN = "NEBIKI";

    public static final String uriageAfterNebiki_COLUMN = "URIAGE_AFTER_NEBIKI";
//add 2019/07/18 #35 USI張 end

    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String yosanTasseiRitu_COLUMN = "YOSAN_TASSEI_RITU";

//add 2019/07/18 #35 USI張 begin
    public static final String tasseiAfterNebikiRitu_COLUMN = "TASSEI_AFTERNEBIKI_RITU";
//add 2019/07/18 #35 USI張 end

    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";

//add 2019/07/18 #35 USI張 begin
    public static final String uriageZenAfterNebiki_COLUMN = "URIAGE_ZEN_AFTER_NEBIKI";
//add 2019/07/18 #35 USI張 end

    public static final String uriageZennenhi_COLUMN = "URIAGE_ZENNENHI";

//add 2019/07/18 #35 USI張 begin
    public static final String uriageAfterNebikiZennenhi_COLUMN = "URIAGE_AFTERNEBIKI_ZENNENHI";
//add 2019/07/18 #35 USI張 end

    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String kyakusuZennenhi_COLUMN = "KYAKUSU_ZENNENHI";
    
    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZen_COLUMN = "KYAKUTANKA_ZEN";
    
    public static final String kyakutankaZennenhi_COLUMN = "KYAKUTANKA_ZENNENHI";
    
    public static final String netUriage_COLUMN = "NET_URIAGE";
    
    public static final String netUriageZen_COLUMN = "NET_URIAGE_ZEN";
    
    public static final String netUriageZennenhi_COLUMN = "NET_URIAGE_ZENNENHI";

//begin add 2020/02/19 xou.zoubun
    public static final String netNebiki_COLUMN = "NET_NEBIKI";  //NET値引き
    public static final String netUriageAfterNebiki_COLUMN = "NET_URIAGE_AFTER_NEBIKI";  //値引後NET売上
    public static final String netUriageAfterNebikiZennenhi_COLUMN = "NET_URIAGE_AFTERNEBIKI_ZENNENHI";  //値引後NET売上前年比
//end add 2002/02/19 xou.zoubun

    public static final String netKyakusu_COLUMN = "NET_KYAKUSU";
    
    public static final String netKyakusuZen_COLUMN = "NET_KYAKUSU_ZEN";
    
    public static final String netKyakusuZennenhi_COLUMN = "NET_KYAKUSU_ZENNENHI";
    
    public static final String netKyakutanka_COLUMN = "NET_KYAKUTANKA";
    
    public static final String netKyakutankaZen_COLUMN = "NET_KYAKUTANKA_ZEN";
    
    public static final String netKyakutankaZennenhi_COLUMN = "NET_KYAKUTANKA_ZENNENHI";
    
    public static final String eigyoYm_COLUMN = "EIGYO_YM";
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    public static final String tenkoKbnKj_COLUMN = "TENKO_KBN_KJ";  
    public static final String tenkoKbnZenKj_COLUMN = "TENKO_KBN_ZEN_KJ";

    /**
     * 営業日ラベル
     */
    private String eigyoDtLabel;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 企業コード
     */
    private String companyCd;

    /**
     * 所属支部（個店検索時）
     */
    private String syozokuSibu;
    /**
     * 前年対象日付
     */
    private String zennenDt;
    
    /**
     * 行ＣＳＳクラス名称
     */
    private String cssClassName = "defaultTR";
    /**
     * 売上高
     */
    private BigDecimal uriage = new BigDecimal("0");

//add 2019/07/18 USI張 #35 begin
    /**
     * 値引
     */
    private BigDecimal nebiki = new BigDecimal("0");

    /**
     * 値引後売上
     */
    private BigDecimal uriageAfterNebiki = new BigDecimal("0");
//add 2019/07/18 USI張 #35 end

    /**
     * 売上予算
     */
    private BigDecimal yosan = new BigDecimal("0");
    
    /**
     * 予算達成率
     */
    private BigDecimal yosanTasseiRitu = new BigDecimal("0.00");

//add 2019/07/18 #34 USI張 begin
    /**
     * 値引後達成率
     */
    private BigDecimal tasseiAfterNebiki = new BigDecimal("0.00");
//add 2019/07/18 #34 USI張 end

    /**
     * 当年天候
     */
    private BigDecimal tenkoKbn = new BigDecimal("0");
    
    /**
     * 前年売上高
     */
    private BigDecimal uriageZen = new BigDecimal("0");

//add 2019/07/18 #34 USI張 begin
    /**
     * 値引後前年売上高
     */
    private BigDecimal uriageZenAfterNebiki = new BigDecimal("0");
  //add 2019/07/18 #34 USI張 end

    /**
     * 売上前年比
     */
    private BigDecimal uriageZennenhi = new BigDecimal("0.00");

//add 2019/07/18 #35 USI張 begin
    /**
     * 値引後売上前年比
     */
    private BigDecimal uriageZennenhiAfterNebiki = new BigDecimal("0.00");
//add 2019/07/18 #35 USI張 end

//begin add 2020/02/19 xou.zoubun
    private BigDecimal netNebiki = new BigDecimal("0"); //NET値引
    private BigDecimal netUriageAfterNebiki = new BigDecimal("0"); //値引後NET売上
    private BigDecimal netUriageZennenhiAfterNebiki = new BigDecimal("0.00"); //値引後NET売上前年比
//end add 2020/02/19 xou.zoubun

    /**
     * 前年天候
     */
    private BigDecimal tenkoKbnZen = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * 客数前年比
     */
    private BigDecimal kyakusuZennenhi = new BigDecimal("0.00");
    
    /**
     * 客単価
     */
    private BigDecimal kyakutanka = new BigDecimal("0");
    
    /**
     * 前年客単価
     */
    private BigDecimal kyakutankaZen = new BigDecimal("0");
    
    /**
     * 客単価前年比
     */
    private BigDecimal kyakutankaZennenhi = new BigDecimal("0.00");
    
    /**
     * 営業日数
     */
    private BigDecimal eigyoDays = new BigDecimal("0");
    
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen = new BigDecimal("0");
    
    /**
     * 当年天候漢字名称
     */
    private String tenkoKbnKj="";
    
    /**
     * 前年天候漢字名称
     */
    private String tenkoKbnZenKj="";
    
    /**
     * NET売上高
     */
    private BigDecimal netUriage = new BigDecimal("0");
    
    /**
     * NET前年売上高
     */
    private BigDecimal netUriageZen = new BigDecimal("0");
    
    /**
     * NET売上前年比
     */
    private BigDecimal netUriageZennenhi = new BigDecimal("0.00");
    
    /**
     * NET客数
     */
    private BigDecimal netKyakusu = new BigDecimal("0");
    
    /**
     * NET前年客数
     */
    private BigDecimal netKyakusuZen = new BigDecimal("0");
    
    /**
     * NET客数前年比
     */
    private BigDecimal netKyakusuZennenhi = new BigDecimal("0.00");
    
    /**
     * NET客単価
     */
    private BigDecimal netKyakutanka = new BigDecimal("0");
    
    /**
     * NET前年客単価
     */
    private BigDecimal netKyakutankaZen = new BigDecimal("0");
    
    /**
     * NET客単価前年比
     */
    private BigDecimal netKyakutankaZennenhi = new BigDecimal("0.00");
    
    /**
     * 対象データ有無判断値
     */
    private boolean isExistData = false;
    /** 未来判断値 */
    private boolean isLink = false;

    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDtLabel() {
        return eigyoDtLabel;
    }
    /**
     * 営業日を設定します。
     * @param eigyoYm 営業日
     */
    public void setEigyoDtLabel(String eigyoYm) {
        this.eigyoDtLabel = eigyoYm;
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
     * 所属支部コードを取得します。
     * @return 所属支部コード
     */
    public String getSyozokuSibu() {
        return syozokuSibu;
    }
    /**
     * 所属支部コードを設定します。
     * @param syozokuSibu 所属支部コード
     */
    public void setSyozokuSibu(String syozokuSibu) {
        this.syozokuSibu = syozokuSibu;
    }

    /**
     * 前年対象日付を取得します。
     * @return 前年対象日付
     */
    public String getZennenDt() {
        return zennenDt;
    }
    /**
     * 前年対象日付を設定します。
     * @param zennenDt 前年対象日付
     */
    public void setZennenDt(String zennenDt) {
        this.zennenDt = zennenDt;
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

//add 2019/07/18 #35 USI張 begin
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
//add 2019/07/18 #35 USI張 end
    /**
     * 売上予算を取得します。
     * @return 売上予算
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * 売上予算を設定します。
     * @param yosan 売上予算
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 予算達成率を取得します。
     * @return 予算達成率
     */
    public BigDecimal getYosanTasseiRitu() {
        return yosanTasseiRitu;
    }
    /**
     * 予算達成率を設定します。
     * @param yosanTasseiRitu 予算達成率
     */
    public void setYosanTasseiRitu(BigDecimal yosanTasseiRitu) {
        this.yosanTasseiRitu = yosanTasseiRitu;
    }

//add 2019/08/18 #35 USI張 begin
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
//add 2019/08/18 #35 USI張 end
    /**
     * 当年天候を取得します。
     * @return 当年天候
     */
    public BigDecimal getTenkoKbn() {
        return tenkoKbn;
    }
    /**
     * 当年天候を設定します。
     * @param tenkoKbn 当年天候
     */
    public void setTenkoKbn(BigDecimal tenkoKbn) {
        this.tenkoKbn = tenkoKbn;
    }
    
    /**
     * 前年売上高を取得します。
     * @return 前年売上高
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上高を設定します。
     * @param uriageZen 前年売上高
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }

//add 2019/07/18 #35 USI張 begin
    /**
     * 値引後前年売上高を取得します。
     * @return 値引後前年売上高
     */
	public BigDecimal getUriageZenAfterNebiki() {
		return uriageZenAfterNebiki;
	}

    /**
     * 値引後前年売上高を設定します。
     * @param uriageZenAfterNebiki 値引後前年売上高
     */
	public void setUriageZenAfterNebiki(BigDecimal uriageZenAfterNebiki) {
		this.uriageZenAfterNebiki = uriageZenAfterNebiki;
	}
//add 2019/07/18 #35 USI張 end

    /**
     * 売上前年比を取得します。
     * @return 売上前年比
     */
    public BigDecimal getUriageZennenhi() {
        return uriageZennenhi;
    }
    /**
     * 売上前年比を設定します。
     * @param uriageZennenhi 売上前年比
     */
    public void setUriageZennenhi(BigDecimal uriageZennenhi) {
        this.uriageZennenhi = uriageZennenhi;
    }

//add 2019/07/18 #35 USI張 begin
    /**
     * 値引後売上前年比を取得します。
     * @return 値引後売上前年比
     */
	public BigDecimal getUriageZennenhiAfterNebiki() {
		return uriageZennenhiAfterNebiki;
	}
    /**
     * 値引後売上前年比を設定します。
     * @param uriageZennenhiAfterNebiki 値引後売上前年比
     */
	public void setUriageZennenhiAfterNebiki(BigDecimal uriageZennenhiAfterNebiki) {
		this.uriageZennenhiAfterNebiki = uriageZennenhiAfterNebiki;
	}
//add 2019/07/18 #35 USI張 end
    /**
     * 前年天候を取得します。
     * @return 前年天候
     */
    public BigDecimal getTenkoKbnZen() {
        return tenkoKbnZen;
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
     * 客数前年比を取得します。
     * @return 客数前年比
     */
    public BigDecimal getKyakusuZennenhi() {
        return kyakusuZennenhi;
    }
    /**
     * 客数前年比を設定します。
     * @param kyakusuZennenhi 客数前年比
     */
    public void setKyakusuZennenhi(BigDecimal kyakusuZennenhi) {
        this.kyakusuZennenhi = kyakusuZennenhi;
    }
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka() {
        return kyakutanka;
    }
    /**
     * 客単価を設定します。
     * @param kyakutanka 客単価
     */
    public void setKyakutanka(BigDecimal kyakutanka) {
        this.kyakutanka = kyakutanka;
    }
    
    /**
     * 前年客単価を取得します。
     * @return 前年客単価
     */
    public BigDecimal getKyakutankaZen() {
        return kyakutankaZen;
    }
    /**
     * 前年客単価を設定します。
     * @param kyakutankaZen 前年客単価
     */
    public void setKyakutankaZen(BigDecimal kyakutankaZen) {
        this.kyakutankaZen = kyakutankaZen;
    }
    
    /**
     * 客単価前年比を取得します。
     * @return 客単価前年比
     */
    public BigDecimal getKyakutankaZennenhi() {
        return kyakutankaZennenhi;
    }
    /**
     * 客単価前年比を設定します。
     * @param kyakutankaZennenhi 客単価前年比
     */
    public void setKyakutankaZennenhi(BigDecimal kyakutankaZennenhi) {
        this.kyakutankaZennenhi = kyakutankaZennenhi;
    }
    
    /**
     * 営業日数を取得します。
     * @return 営業日数
     */
    public BigDecimal getEigyoDays() {
        return eigyoDays;
    }
    /**
     * 営業日数を設定します。
     * @param eigyoDays 営業日数
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
     * 当年天候漢字名称を取得します。
     * @return 当年天候漢字名称
     */
    public String getTenkoKbnKj() {
        return tenkoKbnKj;
    }
    /**
     * 当年天候漢字名称を設定します。
     * @param tenkoKbnKj 当年天候漢字名称
     */
    public void setTenkoKbnKj(String tenkoKbnKj) {
        this.tenkoKbnKj = tenkoKbnKj;
    }
    
    /**
     * 前年天候漢字名称を取得します。
     * @return 前年天候漢字名称
     */
    public String getTenkoKbnZenKj() {
        return tenkoKbnZenKj;
    }
    /**
     * 前年天候漢字名称を設定します。
     * @param tenkoKbnZenKj 前年天候漢字名称
     */
    public void setTenkoKbnZenKj(String tenkoKbnZenKj) {
        this.tenkoKbnZenKj = tenkoKbnZenKj;
    }
    
    /**
     * NET売上高を取得します。
     * @return NET売上高
     */
    public BigDecimal getNetUriage() {
        return netUriage;
    }
    /**
     * NET売上高を設定します。
     * @param netUriage NET売上高
     */
    public void setNetUriage(BigDecimal netUriage) {
        this.netUriage = netUriage;
    }
    
    /**
     * NET前年売上高を取得します。
     * @return NET前年売上高
     */
    public BigDecimal getNetUriageZen() {
        return netUriageZen;
    }
    /**
     * NET前年売上高を設定します。
     * @param netUriageZen NET前年売上高
     */
    public void setNetUriageZen(BigDecimal netUriageZen) {
        this.netUriageZen = netUriageZen;
    }

//begin add 2020/02/19 xou.zoubun
    /**
     * NET値引を取得します。
     * @return NET値引
     */
    public BigDecimal getNetNebiki() {
        return this.netNebiki;
    }

    /**
     * 値引き後NET売上高を取得します。
     * @return 値引き後NET売上高
     */
    public BigDecimal getNetUriageAfterNebiki() {
        return this.netUriageAfterNebiki;
    }
    /**
     * 値引NETを設定します。
     * @para netNebiki 値引NET
     */
    public void setNetNebiki(BigDecimal netNebiki) {
        this.netNebiki = netNebiki;
    }
    /**
     * 値引き後NET売上を設定します。
     * @para netUriageAfterNebiki 値引き後NET売上
     */
    public void setNetUriageAfterNebiki(BigDecimal netUriageAfterNebiki) {
        this.netUriageAfterNebiki = netUriageAfterNebiki;
    }

    /**
     * 値引き後NET売上前年比を取得します。
     * @return 値引き後NET売上前年比
     */
    public BigDecimal getNetUriageZennenhiAfterNebiki() {
        return netUriageZennenhiAfterNebiki;
    }

    /**
     * 値引き後NET売上前年比を設定します。
     * @param netUriageZennenhiAfterNebiki 値引き後NET売上前年比
     */
    public void setNetUriageZennenhiAfterNebiki(BigDecimal netUriageZennenhiAfterNebiki) {
        this.netUriageZennenhiAfterNebiki = netUriageZennenhiAfterNebiki;
    }
//end add 2020/02/19

    /**
     * NET売上前年比を取得します。
     * @return NET売上前年比
     */
    public BigDecimal getNetUriageZennenhi() {
        return netUriageZennenhi;
    }
    /**
     * NET売上前年比を設定します。
     * @param netUriageZennenhi NET売上前年比
     */
    public void setNetUriageZennenhi(BigDecimal netUriageZennenhi) {
        this.netUriageZennenhi = netUriageZennenhi;
    }
    
    /**
     * NET客数を取得します。
     * @return NET客数
     */
    public BigDecimal getNetKyakusu() {
        return netKyakusu;
    }
    /**
     * NET客数を設定します。
     * @param netKyakusu NET客数
     */
    public void setNetKyakusu(BigDecimal netKyakusu) {
        this.netKyakusu = netKyakusu;
    }
    
    /**
     * NET前年客数を取得します。
     * @return NET前年客数
     */
    public BigDecimal getNetKyakusuZen() {
        return netKyakusuZen;
    }
    /**
     * NET前年客数を設定します。
     * @param netKyakusuZen NET前年客数
     */
    public void setNetKyakusuZen(BigDecimal netKyakusuZen) {
        this.netKyakusuZen = netKyakusuZen;
    }
    
    /**
     * NET客数前年比を取得します。
     * @return NET客数前年比
     */
    public BigDecimal getNetKyakusuZennenhi() {
        return netKyakusuZennenhi;
    }
    /**
     * NET客数前年比を設定します。
     * @param netKyakusuZennenhi NET客数前年比
     */
    public void setNetKyakusuZennenhi(BigDecimal netKyakusuZennenhi) {
        this.netKyakusuZennenhi = netKyakusuZennenhi;
    }
    
    /**
     * NET客単価を取得します。
     * @return NET客単価
     */
    public BigDecimal getNetKyakutanka() {
        return netKyakutanka;
    }
    /**
     * NET客単価を設定します。
     * @param netKyakutanka NET客単価
     */
    public void setNetKyakutanka(BigDecimal netKyakutanka) {
        this.netKyakutanka = netKyakutanka;
    }
    
    /**
     * NET前年客単価を取得します。
     * @return NET前年客単価
     */
    public BigDecimal getNetKyakutankaZen() {
        return netKyakutankaZen;
    }
    /**
     * NET前年客単価を設定します。
     * @param netKyakutankaZen NET前年客単価
     */
    public void setNetKyakutankaZen(BigDecimal netKyakutankaZen) {
        this.netKyakutankaZen = netKyakutankaZen;
    }
    
    /**
     * NET客単価前年比を取得します。
     * @return NET客単価前年比
     */
    public BigDecimal getNetKyakutankaZennenhi() {
        return netKyakutankaZennenhi;
    }
    /**
     * NET客単価前年比を設定します。
     * @param netKyakutankaZennenhi NET客単価前年比
     */
    public void setNetKyakutankaZennenhi(BigDecimal netKyakutankaZennenhi) {
        this.netKyakutankaZennenhi = netKyakutankaZennenhi;
    }
	/**
	 * ＣＳＳクラス名称取得処理
	 * 
	 * @return クラス変数cssClassName を戻します。
	 */
	public String getCssClassName() {
		return cssClassName;
	}
	/**
	 * ＣＳＳクラス名称設定処理
	 * 
	 * @param cssClassName を クラス変数cssClassNameへ設定します。
	 */
	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}
	/**
	 * @return クラス変数isDataExist を戻します。
	 */
	public boolean isExistData() {
		return isExistData;
	}
	/**
	 * @param isDataExist を クラス変数isDataExistへ設定します。
	 */
	public void setExistData(boolean isDataExist) {
		this.isExistData = isDataExist;
	}
	/**
	 * @return クラス変数isFuture を戻します。
	 */
	public boolean isLink() {
		return isLink;
	}
	/**
	 * @param isFuture を クラス変数isFutureへ設定します。
	 */
	public void setLink(boolean isFuture) {
		this.isLink = isFuture;
	}
    
}
