package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

/**
 * 
 * @author   xkhata
 * @modifier xkinu  2007/06/05 クローズ店予算表示対応
 *
 */
public class TrnSibuYosanNipoSegRelate {
    
    public static final String TABLE = "BT45DSJY";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String segmentType_COLUMN = "SEGMENT_TYPE";
    
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
     * セグメントタイプ
     */
    private String segmentType;
    
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
     * セグメントタイプを取得します。
     * @return セグメントタイプ
     */
    public String getSegmentType() {
        return segmentType;
    }
    /**
     * セグメントタイプを設定します。
     * @param segmentType セグメントタイプ
     */
    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
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
	 * @return miseCnt を戻します。
	 */
	public BigDecimal getMiseCnt() {
		return miseCnt;
	}
	/**
	 * 対象店舗数設定処理
	 * @param miseCnt 設定する miseCnt。
	 */
	public void setMiseCnt(BigDecimal miseCnt) {
		this.miseCnt = miseCnt;
	}
    
}
