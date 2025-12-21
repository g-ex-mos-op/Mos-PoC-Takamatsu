package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity;
import java.math.BigDecimal;

public class UISibuListInfo {
    
    public static final String TABLE = "BT60ZNIP";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String honbuCd_COLUMN = "HONBU_CD";
    
    public static final String honbuName_COLUMN = "HONBU_NAME";
    
    public static final String jigyouCd_COLUMN = "JIGYOU_CD";
    
    public static final String jigyouName_COLUMN = "JIGYOU_NAME";
    
    public static final String slareaCd_COLUMN = "SLAREA_CD";
    
    public static final String slareaName_COLUMN = "SLAREA_NAME";
    
    public static final String mosgKbn_COLUMN = "MOSG_KBN";
    
    public static final String count_COLUMN = "COUNT";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String yosan_COLUMN = "URI_YOSAN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String zennenUriage_COLUMN = "URIAGE_ZEN";
    
    public static final String zennenKyakusu_COLUMN = "KYAKUSU_ZEN";
    
    public static final String zennenEigyoDays_COLUMN = "EIGYO_DAYS_ZEN";
    /** カラム名称：NET値がある店舗数 */
    public static final String tenpoCountNet_COLUMN = "TENPO_COUNT_NET";
    /** カラム名称：売上のNET値 */
    public static final String uriageNet_COLUMN = "URIAGE_NET";
    /** カラム名称：客数のNET値 */
    public static final String kyakusuNet_COLUMN = "KYAKUSU_NET";
    /** カラム名称：前年売上のNET値 */
    public static final String uriageZenNet_COLUMN = "URIAGE_ZEN_NET";
    /** カラム名称：前年件数のNET値 */
    public static final String kyakusuZenNet_COLUMN = "KYAKUSU_ZEN_NET";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名
     */
    private String sibuName;
    
    /**
     * 本部コード
     */
    private String honbuCd;
    
    /**
     * 本部名称
     */
    private String honbuName;
    
    /**
     * 事業本部コード
     */
    private String jigyouCd;
    
    /**
     * 事業本部名称
     */
    private String jigyouName;
    
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    
    /**
     * 営業エリア名称
     */
    private String slareaName;
    
    /**
     * 屋号区分
     */
    private String mosgKbn;
    
    /**
     * 屋号区分(モスファクトリー)
     */
    private String mosfKbn;
    
    /**
     * 店舗数
     */
    private int count;
    
    /**
     * 売上高
     */
    private BigDecimal uriage;
    
    /**
     * 事業計画予算
     */
    private BigDecimal yosan;
    
    /**
     * 前年売上高
     */
    private BigDecimal zennenUriage;

    /**
     * 客数
     */
    private int kyakusu;
    
    /**
     * 前年客数
     */
    private int zennenKyakusu;
    
    /**
     * 前年客単価
     */
    private BigDecimal zennenKyakuTanka;
        
    /**
     * 前年営業日数
     */
    private String zennenEigyoDays;
    /**
     * NET値がある店舗数
     */
    private int tenpoCountNet;
    /**
     * 売上
     */
    private BigDecimal uriageNet;
    
    /**
     * 客数
     */
    private BigDecimal kyakusuNet;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZenNet;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZenNet;
    
	/**
	 * @return kyakusuNet を戻します。
	 */
	public BigDecimal getKyakusuNet() {
		return kyakusuNet;
	}

	/**
	 * @param kyakusuNet 設定する kyakusuNet。
	 */
	public void setKyakusuNet(BigDecimal kyakusuNet) {
		this.kyakusuNet = kyakusuNet;
	}

	/**
	 * @return kyakusuZenNet を戻します。
	 */
	public BigDecimal getKyakusuZenNet() {
		return kyakusuZenNet;
	}

	/**
	 * @param kyakusuZenNet 設定する kyakusuZenNet。
	 */
	public void setKyakusuZenNet(BigDecimal kyakusuZenNet) {
		this.kyakusuZenNet = kyakusuZenNet;
	}

	/**
	 * @return tenpoCountNet を戻します。
	 */
	public int getTenpoCountNet() {
		return tenpoCountNet;
	}

	/**
	 * @param tenpoCountNet 設定する tenpoCountNet。
	 */
	public void setTenpoCountNet(int tenpoCountNet) {
		this.tenpoCountNet = tenpoCountNet;
	}

	/**
	 * @return uriageNet を戻します。
	 */
	public BigDecimal getUriageNet() {
		return uriageNet;
	}

	/**
	 * @param uriageNet 設定する uriageNet。
	 */
	public void setUriageNet(BigDecimal uriageNet) {
		this.uriageNet = uriageNet;
	}

	/**
	 * @return uriageZenNet を戻します。
	 */
	public BigDecimal getUriageZenNet() {
		return uriageZenNet;
	}

	/**
	 * @param uriageZenNet 設定する uriageZenNet。
	 */
	public void setUriageZenNet(BigDecimal uriageZenNet) {
		this.uriageZenNet = uriageZenNet;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getHonbuCd() {
		return honbuCd;
	}

	public void setHonbuCd(String honbuCd) {
		this.honbuCd = honbuCd;
	}

	public String getHonbuName() {
		return honbuName;
	}

	public void setHonbuName(String honbuName) {
		this.honbuName = honbuName;
	}

	public String getJigyouCd() {
		return jigyouCd;
	}

	public void setJigyouCd(String jigyouCd) {
		this.jigyouCd = jigyouCd;
	}

	public String getJigyouName() {
		return jigyouName;
	}

	public void setJigyouName(String jigyouName) {
		this.jigyouName = jigyouName;
	}
	public String getMiseCd() {
		return miseCd;
	}

	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	public String getMiseNameKj() {
		return miseNameKj;
	}

	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}

	public String getMosfKbn() {
		return mosfKbn;
	}

	public void setMosfKbn(String mosfKbn) {
		this.mosfKbn = mosfKbn;
	}

	public String getMosgKbn() {
		return mosgKbn;
	}

	public void setMosgKbn(String mosgKbn) {
		this.mosgKbn = mosgKbn;
	}

	public String getSibuCd() {
		return sibuCd;
	}

	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

	public String getSibuName() {
		return sibuName;
	}

	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}

	public String getSlareaCd() {
		return slareaCd;
	}

	public void setSlareaCd(String slareaCd) {
		this.slareaCd = slareaCd;
	}

	public String getSlareaName() {
		return slareaName;
	}

	public void setSlareaName(String slareaName) {
		this.slareaName = slareaName;
	}

	public String getZennenEigyoDays() {
		return zennenEigyoDays;
	}

	public void setZennenEigyoDays(String zennenEigyoDays) {
		this.zennenEigyoDays = zennenEigyoDays;
	}
	public int getZennenKyakusu() {
		return zennenKyakusu;
	}

	public void setZennenKyakusu(int zennenKyakusu) {
		this.zennenKyakusu = zennenKyakusu;
	}

	public BigDecimal getZennenKyakuTanka() {
		return zennenKyakuTanka;
	}

	public void setZennenKyakuTanka(BigDecimal zennenKyakuTanka) {
		this.zennenKyakuTanka = zennenKyakuTanka;
	}

	public int getCount() {
		return count;
	}

	public int getKyakusu() {
		return kyakusu;
	}

	public void setKyakusu(int kyakusu) {
		this.kyakusu = kyakusu;
	}

	public BigDecimal getUriage() {
		return uriage;
	}

	public void setUriage(BigDecimal uriage) {
		this.uriage = uriage;
	}

	public BigDecimal getYosan() {
		return yosan;
	}

	public void setYosan(BigDecimal yosan) {
		this.yosan = yosan;
	}

	public BigDecimal getZennenUriage() {
		return zennenUriage;
	}

	public void setZennenUriage(BigDecimal zennenUriage) {
		this.zennenUriage = zennenUriage;
	}
        
}
