package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;

import java.math.BigDecimal;

public class PointInfo {

	/** テーブル名称 */
    public static final String TABLE = "BD67DPNT";
    /** カラム名称：営業日 */
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    /** カラム名称：ポイント数計 */
    public static final String pointKei_COLUMN = "POINT_KEI";
// add 2021/09/02 USI戚 begin
    /** カラム名称：ポイント（ネット）数計 */
    public static final String pointNetKei_COLUMN = "POINT_NET_KEI";
// add 2021/09/02 USI戚 end
// add 2020/06/10 USI張 begin
    /** カラム名称：優待券チャージ金額 */
    public static final String yChargeKin_COLUMN = "Y_CHARGE_KIN";
// add 2020/06/10 USI張 end

    /**
     * 営業日
     */
    private String eigyoDt;

    /**
     * ポイント数計
     */
    private BigDecimal pointKei;

// add 2021/09/02 USI戚 begin
    /**
     * ポイント（ネット）数計
     */
    private BigDecimal pointNetKei;
// add 2021/09/02 USI戚 end

// add 2020/06/10 USI張 begin
    /**
     * 優待券チャージ金額
     */
    private BigDecimal yChargeKin;
// add 2020/06/10 USI張 end

    /**
     * 行CSSクラス名
     */
    private String rClass;

    /**
     * 営業日を取得します。
     * @return eigyoDt 営業日
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
     * ポイント数計を取得します。
     * @return pointKei ポイント数計
     */
	public BigDecimal getPointKei() {
		return pointKei;
	}

    /**
     * ポイント数計を設定します。
     * @param pointKei ポイント数計
     */
	public void setPointKei(BigDecimal pointKei) {
		this.pointKei = pointKei;
	}

// add 2021/09/02 USI戚 begin
    /**
     * ポイント（ネット）数計を取得します。
     * @return pointNetKei ポイント（ネット）数計
     */
	public BigDecimal getPointNetKei() {
		return pointNetKei;
	}

    /**
     * ポイント（ネット）数計を設定します。
     * @param pointNetKei ポイント（ネット）数計
     */
	public void setPointNetKei(BigDecimal pointNetKei) {
		this.pointNetKei = pointNetKei;
	}
// add 2021/09/02 USI戚 end
// add 2020/06/09 USI張 begin
    /**
     * 優待券チャージ金額を取得します。
     * @return yChargeKin 優待券チャージ金額
     */
	public BigDecimal getYChargeKin() {
		return yChargeKin;
	}

	/**
     * 優待券チャージ金額を設定します。
     * @param yChargeKin 優待券チャージ金額
     */
	public void setYChargeKin(BigDecimal yChargeKin) {
		this.yChargeKin = yChargeKin;
	}
// add 2020/06/09 USI張 end

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
