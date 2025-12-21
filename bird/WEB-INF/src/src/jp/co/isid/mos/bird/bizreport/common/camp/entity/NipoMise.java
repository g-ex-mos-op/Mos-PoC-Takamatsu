/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;

/**
 * 店舗一覧用日報エンティティー
 * 
 * @author xkinu
 *
 */
public abstract class NipoMise extends Nipo {
    public static final String blockCd_COLUMN = "BLOCK_CD";
    public static final String blockNameKj_COLUMN = "BLOCK_NAME";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String tenpoShubetu_COLUMN = "TENPO_SHUBETU";
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";    
    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    public static final String openKbn_COLUMN = "OPEN_KBN";    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    public static final String sibuTenpoCnt_COLUMN = "SIBU_TENPO_CNT";
    public static final String allTenpoCnt_COLUMN = "ALL_TENPO_CNT";
    public static final String rankInSibu_COLUMN = "RANK_IN_SIBU";
    public static final String rankInAll_COLUMN = "RANK_IN_ALL";
    
    /**
     * ブロックコード
     */
    private String blockCd;
    /**
     * ブロックコード
     */
    private String blockName;
    /**
     * 店舗コード
     */
    private String miseCd;
    /**
     * 店舗コード
     */
    private String miseNameKj;
    /**
     * 店舗種別
     */
    private String tenpoShubetu;
    /**
     * 天候区分
     */
    private String tenkoKbn;
    /**
     * 前年天候区分
     */
    private String tenkoKbnZen;
    /**
     * 営業日数
     */
    private BigDecimal eigyoDays = new BigDecimal("0");
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen = new BigDecimal("0");
    
    /**
     * OPEN区分
     */
    private BigDecimal openKbn = new BigDecimal("0");
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen = new BigDecimal("0");
    /**
     * 支部内対象店舗数
     */
    private BigDecimal sibuTenpoCnt = new BigDecimal("0");
    /**
     * 全国対象店舗数
     */
    private BigDecimal allTenpoCnt = new BigDecimal("0");
    /**
     * 支部順位
     */
    private BigDecimal rankInSibu = new BigDecimal("0");
    /**
     * 全国順位
     */
    private BigDecimal rankInAll = new BigDecimal("0");

	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}

	/**
	 * @param miseCd 設定する eigyoDt。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	/**
	 * クローズ文字列取得処理
	 * 
	 * クローズしている場合は、「クローズ」の文字列を返します。
	 * クローズ店舗は「クローズ」表記
	 * 
	 * @return クローズ文字列を戻します。
	 */
	public String getClose() {
		return CommonUtil.getCloseKj(getOpenKbn());
	}

	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}

	/**
	 * @param miseNameKj 設定する miseNameKj。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}

	/**
	 * @return openKbnZen を戻します。
	 */
	public BigDecimal getOpenKbnZen() {
		return openKbnZen;
	}

	/**
	 * @param openKbnZen 設定する openKbnZen。
	 */
	public void setOpenKbnZen(BigDecimal openKbnZen) {
		this.openKbnZen = openKbnZen;
	}
	/**
	 * クローズ文字列取得処理
	 * 
	 * クローズしている場合は、「クローズ」の文字列を返します。
	 * クローズ店舗は「クローズ」表記
	 * 
	 * @return クローズ文字列を戻します。
	 */
	public String getCloseZen() {
		return CommonUtil.getCloseKj(getOpenKbnZen());
	}

	/**
	 * 天候区分取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbn() {
		return tenkoKbn;
	}

	/**
	 * 天候区分設定処理
	 * @param tenkoKbn 設定する tenkoKbn。
	 */
	public void setTenkoKbn(String tenkoKbn) {
		this.tenkoKbn = tenkoKbn;
	}
	/**
	 * 天候区分漢字取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbnKigou() {
		return CommonUtil.getTenkoKj(getTenkoKbn());
	}

	/**
	 * 前年天候区分取得処理
	 * @return tenkoKbnZen を戻します。
	 */
	public String getTenkoKbnZen() {
		return tenkoKbnZen;
	}

	/**
	 * 前年天候区分設定処理
	 * @param tenkoKbnZen 設定する tenkoKbnZen。
	 */
	public void setTenkoKbnZen(String tenkoKbnZen) {
		this.tenkoKbnZen = tenkoKbnZen;
	}
	/**
	 * 前年天候区分漢字取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbnZenKigou() {
		return CommonUtil.getTenkoKj(getTenkoKbnZen());
	}

	/**
	 * @return blockCd を戻します。
	 */
	public String getBlockCd() {
		return blockCd;
	}

	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}

	/**
	 * @return blockName を戻します。
	 */
	public String getBlockName() {
		return blockName;
	}

	/**
	 * @param blockName 設定する blockName。
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return eigyoDays を戻します。
	 */
	public BigDecimal getEigyoDays() {
		return eigyoDays;
	}

	/**
	 * @param eigyoDays 設定する eigyoDays。
	 */
	public void setEigyoDays(BigDecimal eigyoDays) {
		this.eigyoDays = eigyoDays;
	}

	/**
	 * @return eigyoDaysZen を戻します。
	 */
	public BigDecimal getEigyoDaysZen() {
		return eigyoDaysZen;
	}

	/**
	 * @param eigyoDaysZen 設定する eigyoDaysZen。
	 */
	public void setEigyoDaysZen(BigDecimal eigyoDaysZen) {
		this.eigyoDaysZen = eigyoDaysZen;
	}

	/**
	 * @return openKbn を戻します。
	 */
	public BigDecimal getOpenKbn() {
		return openKbn;
	}

	/**
	 * @param openKbn 設定する openKbn。
	 */
	public void setOpenKbn(BigDecimal openKbn) {
		this.openKbn = openKbn;
	}

	/**
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetu() {
		return tenpoShubetu;
	}

	/**
	 * @param tenpoShubetu 設定する tenpoShubetu。
	 */
	public void setTenpoShubetu(String tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
	}
	/**
	 * 店舗種別略語名称取得
	 * 
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetuRyakuKj() {
		return CommonUtil.getTenpoShubetuKj(getTenpoShubetu());
	}
    /**
     * 金額構成比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getKingakuKoseiHiCssClass();
    /**
     * 売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getUriageZennenHiCssClass();
    /**
     * 客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getKyakusuZennenHiCssClass();
    /**
     * 客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getKyakutankaZennenHiCssClass();
    /**
     * 売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getNetUriageZennenHiCssClass();
    /**
     * 客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getNetKyakusuZennenHiCssClass();
    /**
     * 客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public abstract String getNetKyakutankaZennenHiCssClass();

	/**
	 * @return rankInAll を戻します。
	 */
	public BigDecimal getRankInAll() {
		return rankInAll;
	}

	/**
	 * @param rankInAll 設定する rankInAll。
	 */
	public void setRankInAll(BigDecimal rankInAll) {
		this.rankInAll = rankInAll;
	}

	/**
	 * @return rankInSibu を戻します。
	 */
	public BigDecimal getRankInSibu() {
		return rankInSibu;
	}

	/**
	 * @param rankInSibu 設定する rankInSibu。
	 */
	public void setRankInSibu(BigDecimal rankInSibu) {
		this.rankInSibu = rankInSibu;
	}

	/**
	 * @return allTenpoCnt を戻します。
	 */
	public BigDecimal getAllTenpoCnt() {
		return allTenpoCnt;
	}

	/**
	 * @param allTenpoCnt 設定する allTenpoCnt。
	 */
	public void setAllTenpoCnt(BigDecimal allTenpoCnt) {
		this.allTenpoCnt = allTenpoCnt;
	}

	/**
	 * @return sibuTenpoCnt を戻します。
	 */
	public BigDecimal getSibuTenpoCnt() {
		return sibuTenpoCnt;
	}

	/**
	 * @param sibuTenpoCnt 設定する sibuTenpoCnt。
	 */
	public void setSibuTenpoCnt(BigDecimal sibuTenpoCnt) {
		this.sibuTenpoCnt = sibuTenpoCnt;
	}
}
