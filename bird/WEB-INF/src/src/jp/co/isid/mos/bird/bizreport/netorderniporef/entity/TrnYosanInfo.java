package jp.co.isid.mos.bird.bizreport.netorderniporef.entity;

import java.math.BigDecimal;

/**
 * 
 * @author   xkhata
 * @modifier xkinu  2007/06/05 クローズ店予算表示対応
 *
 */
public class TrnYosanInfo {
    
    public static final String TABLE = "BT45DSJY";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String yosan_COLUMN = "YOSAN";
    /** 
     * カラム名称定数：対象店舗数
     * 作成日：2007/06/05 クローズ店予算表示対応
     */
    public static final String miseCnt_COLUMN = "MISE_CNT";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 予算
     */
    private BigDecimal yosan;
    /**
     * 対象店舗数
     * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
     */
    private BigDecimal miseCnt = new BigDecimal("0");
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
	 * 対象店舗数取得処理
	 * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
	 * @return miseCnt を戻します。
	 */
	public BigDecimal getMiseCnt() {
		return miseCnt;
	}
	/**
	 * 対象店舗数設定処理
	 * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
	 * @param miseCnt 設定する miseCnt。
	 */
	public void setMiseCnt(BigDecimal miseCnt) {
		this.miseCnt = miseCnt;
	}
    
       
}
