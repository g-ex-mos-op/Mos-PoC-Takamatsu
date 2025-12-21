package jp.co.isid.mos.bird.bizsupport.campcheckamount.entity;

import java.math.BigDecimal;

public class UISetQuantityInfo {

    public static final String TABLE = "CM07KAMT";

    public static final String cnt_COLUMN = "CNT";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String koteiCdName_COLUMN = "KOTEI_CD_NAME";

    public static final String koteiAmt_COLUMN = "KOTEI_AMT";

    public static final String sibuCd_COLUMN = "SIBU_CD";

    public static final String sibuName_COLUMN = "SIBU_NAME";

    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseNameKj;

    /**
     * 固定数量名称
     */
    private String koteiCdName;

    /**
     * 固定数量
     */
    private BigDecimal koteiAmt;

    /**
     * アイテム数
     */
    private BigDecimal cnt;
    /**
     * 支部コード
     */
    private String sibuCd;
    /**
     * 支部名称
     */
    private String sibuName;

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
     * 固定数量名称を取得します。
     * @return 固定数量名称
     */
    public String getKoteiCdName() {
        return koteiCdName;
    }
    /**
     * 固定数量名称を設定します。
     * @param koteiCdName 固定数量名称
     */
    public void setKoteiCdName(String koteiCdName) {
        this.koteiCdName = koteiCdName;
    }

    /**
     * 固定数量を取得します。
     * @return 固定数量
     */
    public BigDecimal getKoteiAmt() {
        return koteiAmt;
    }
    /**
     * 固定数量を設定します。
     * @param koteiAmt 固定数量
     */
    public void setKoteiAmt(BigDecimal koteiAmt) {
        this.koteiAmt = koteiAmt;
    }

    /**
     * アイテム数を取得します。
     * @return アイテム数
     */
    public BigDecimal getCnt() {
        return cnt;
    }
    /**
     * アイテム数を設定します。
     * @param cnt アイテム数
     */
    public void setCnt(BigDecimal cnt) {
        this.cnt = cnt;
    }

	/**
	 * 支部コードを取得します。
	 *
	 * @return 支部コード
	 */

	public String getSibuCd() {
		return sibuCd;
	}

	/**
	 * 支部コードを設定します。
	 *
	 * @param sibuCd 支部コード
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

	/**
	 * 支部名称を取得します。
	 *
	 * @return 支部名称
	 */
	public String getSibuName() {
		return sibuName;
	}

	/**
	 * 支部名称を設定します。
	 *
	 * @param sibuName
	 *            支部名称
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}
}
