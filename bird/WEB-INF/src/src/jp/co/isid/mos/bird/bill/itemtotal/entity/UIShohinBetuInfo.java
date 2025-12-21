package jp.co.isid.mos.bird.bill.itemtotal.entity;

import java.math.BigDecimal;

public class UIShohinBetuInfo {

    public static final String TABLE = "BS03USSR";

    public static final String seikyuBnrui_COLUMN = "SEIKYU_BNRUI";

    public static final String shoCdJitu_COLUMN = "SHO_CD_JITU";

    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";

    public static final String shoAmount_COLUMN = "SHO_AMOUNT";

    public static final String nisuName_COLUMN = "NISU_NAME";

    public static final String nohinTanka_COLUMN = "NOHIN_TANKA";

    public static final String kingaku_COLUMN = "KINGAKU";

    public static final String sogokei_COLUMN = "SOGOKEI";

    public static final String taxUri_COLUMN = "TAX_URI";

    /**
     * 請求書用分類
     */
    private String seikyuBnrui;

    /**
     * 実商品コード
     */
    private String shoCdJitu;

    /**
     * 商品名称（漢字）
     */
    private String shoNameKj;

    /**
     * 納品数量
     */
    private BigDecimal shoAmount;

    /**
     * 荷姿名称
     */
    private String nisuName;

    /**
     * 納品単価
     */
    private BigDecimal nohinTanka;

    /**
     * 金額
     */
    private BigDecimal kingaku;

    /**
     * 総合計
     */
    private BigDecimal sogokei;

    /**
     * 売上消費税区分
     */
    private String taxUri;

    /**
     * 請求書用分類を取得します。
     * @return 請求書用分類
     */
    public String getSeikyuBnrui() {
        return seikyuBnrui;
    }
    /**
     * 請求書用分類を設定します。
     * @param seikyuBnrui 請求書用分類
     */
    public void setSeikyuBnrui(String seikyuBnrui) {
        this.seikyuBnrui = seikyuBnrui;
    }

    /**
     * 実商品コードを取得します。
     * @return 実商品コード
     */
    public String getShoCdJitu() {
        return shoCdJitu;
    }
    /**
     * 実商品コードを設定します。
     * @param shoCdJitu 実商品コード
     */
    public void setShoCdJitu(String shoCdJitu) {
        this.shoCdJitu = shoCdJitu;
    }

    /**
     * 商品名称（漢字）を取得します。
     * @return 商品名称（漢字）
     */
    public String getShoNameKj() {
    	return (shoNameKj == null) ? "" : shoNameKj.replaceAll("[　*| *]*$", "");
    }
    /**
     * 商品名称（漢字）を設定します。
     * @param shoNameKj 商品名称（漢字）
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
    }

    /**
     * 納品数量を取得します。
     * @return 納品数量
     */
    public BigDecimal getShoAmount() {
        return shoAmount;
    }
    /**
     * 納品数量を設定します。
     * @param shoAmount 納品数量
     */
    public void setShoAmount(BigDecimal shoAmount) {
        this.shoAmount = shoAmount;
    }

    /**
     * 荷姿名称を取得します。
     * @return 荷姿名称
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * 荷姿名称を設定します。
     * @param nisuName 荷姿名称
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }

    /**
     * 納品単価を取得します。
     * @return 納品単価
     */
    public BigDecimal getNohinTanka() {
        return nohinTanka;
    }
    /**
     * 納品単価を設定します。
     * @param nohinTanka 納品単価
     */
    public void setNohinTanka(BigDecimal nohinTanka) {
        this.nohinTanka = nohinTanka;
    }

    /**
     * 金額を取得します。
     * @return 金額
     */
    public BigDecimal getKingaku() {
        return kingaku;
    }
    /**
     * 金額を設定します。
     * @param kingaku 金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }

    /**
     * 総合計を取得します。
     * @return 総合計
     */
    public BigDecimal getSogokei() {
        return sogokei;
    }
    /**
     * 総合計を設定します。
     * @param sogokei 総合計
     */
    public void setSogokei(BigDecimal sogokei) {
        this.sogokei = sogokei;
    }

    /**
     * 売上消費税区分を取得します。
     * @return 売上消費税区分
     */
    public String getTaxUri() {
        return taxUri;
    }
    /**
     * 売上消費税区分を設定します。
     * @param taxUri 売上消費税区分
     */
    public void setTaxUri(String taxUri) {
        this.taxUri = taxUri;
    }

}
