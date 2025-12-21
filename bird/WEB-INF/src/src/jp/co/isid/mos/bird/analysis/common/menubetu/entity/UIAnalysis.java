package jp.co.isid.mos.bird.analysis.common.menubetu.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;

/**
 * データ分析共通Abstractエンティティ
 * 
 * 作成日:2008/10/02
 * @author xkinu
 *
 */
public abstract class UIAnalysis {
    
    public static final String targetNameKj_COLUMN = "TARGET_NAME_KJ";
    
    public static final String rowType_COLUMN = "ROW_TYPE";
    /** SELECT対象カラム：対象店舗数 */
    public static final String tenpoCnt_COLUMN = "TENPO_CNT";

     
    /**
     * 対象名称
     */
    private String targetNameKj;
    /**
     * 行種類
     */
    private String rowType;
    
    /**
     * 対象店舗数
     */
    private BigDecimal tenpoCnt = new BigDecimal("0");
   /**
     * TRタグスタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getTrCssClass() {
    	return MenubetuUtil.getTrCssClass(getRowType());
    }
    /**
     * 行種類を取得します。
     * @return 行種類
     */
    public String getRowType() {
        return rowType;
    }
    /**
     * 行種類を設定します。
     * @param rowType 行種類
     */
    public void setRowType(String rowType) {
        this.rowType = rowType;
    }
    
    /**
     * 指定期日販売店舗数を取得します。
     * @return 指定期日販売店舗数
     */
    public BigDecimal getTenpoCnt() {
        return tenpoCnt;
    }
    /**
     * 指定期日販売店舗数を設定します。
     * @param tenpoCnt 指定期日販売店舗数
     */
    public void setTenpoCnt(BigDecimal tenpoCnt) {
        this.tenpoCnt = tenpoCnt;
    }
	/**
	 * @return targetNameKj を戻します。
	 */
	public String getTargetNameKj() {
		return targetNameKj;
	}
	/**
	 * @param targetNameKj を クラス変数targetNameKjへ設定します。
	 */
	public void setTargetNameKj(String targetNameKj) {
		this.targetNameKj = targetNameKj;
	}
}
