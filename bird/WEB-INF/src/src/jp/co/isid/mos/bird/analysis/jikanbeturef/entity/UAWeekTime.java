package jp.co.isid.mos.bird.analysis.jikanbeturef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.analysis.jikanbeturef.code.TimeElement;
import jp.co.isid.mos.bird.common.util.CommonUtil;


/**
 * 曜日別用Abstractエンティティクラス
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public abstract class UAWeekTime extends UAWeek {
        
    public static final String weekType_COLUMN = "WEEK_TYPE";

    public static final String tmElem_COLUMN = "TM_ELEM";
    
    public static final String tmKbn_COLUMN = "TM_KBN";
    
    public static final String kingaku_COLUMN = "KINGAKU";
    
    public static final String kingakuKouseiHi_COLUMN = "KINGAKU_KOUSEI_HI";
    
    public static final String kyauksu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuKouseiHi_COLUMN = "KYAKUSU_KOUSEI_HI";
    
    /**
     * 曜日種別
     * 1：三曜日、2：七曜日
     */
    private String weekType;
    /**
     * 時間帯別
     */
    private String tmElem;
    
    /**
     * 時間
     */
    private String tmKbn;
    
    /**
     * 売上金額
     */
    private BigDecimal kingaku = new BigDecimal("0");
    
    /**
     * 金額構成比
     */
    private BigDecimal kingakuKouseiHi = new BigDecimal("0.00");
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 客数構成比
     */
    private BigDecimal kyakusuKouseiHi = new BigDecimal("0.00");
    
    /**
     * 時間帯別を取得します。
     * @return 時間帯別
     */
    public String getTmElem() {
        return tmElem;
    }
    /**
     * 時間帯別を設定します。
     * @param tmElem 時間帯別
     */
    public void setTmElem(String tmElem) {
        this.tmElem = tmElem;
    }
    
    /**
     * 時間帯名称を取得します。
     * @return 時間帯名称
     */
    public String getTmElemName() {
    	if("00".equals(getTmElem())) {
    		return "合計";
    	}
        return TimeElement.getName(getTmElem());
    }
    
    /**
     * 時間を取得します。
     * @return 時間
     */
    public String getTmKbn() {
        return tmKbn;
    }
    /**
     * 時間を設定します。
     * @param tmKbn 時間
     */
    public void setTmKbn(String tmKbn) {
        this.tmKbn = tmKbn;
    }
    /**
     * 時間を設定します。
     * @param tmKbn 時間
     */
    public String getTmName() {
    	if(!CommonUtil.isNull(getTmKbn())) {
    		if("99".equals(getTmKbn())) {
    			return "";
    		}
    		return getTmKbn()+"時～";
    	}
    	return "";
    }
    
    /**
     * 売上金額を取得します。
     * @return 売上金額
     */
    public BigDecimal getKingaku() {
        return kingaku;
    }
    /**
     * 売上金額を設定します。
     * @param kingaku 売上金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }
    
    /**
     * 金額構成比を取得します。
     * @return 金額構成比
     */
    public BigDecimal getKingakuKouseiHi() {
        return kingakuKouseiHi;
    }
    /**
     * 金額構成比を設定します。
     * @param kingakuKouseiHi 金額構成比
     */
    public void setKingakuKouseiHi(BigDecimal kingakuKouseiHi) {
        this.kingakuKouseiHi = kingakuKouseiHi;
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
	 * @return kyakusuKouseiHi を戻します。
	 */
	public BigDecimal getKyakusuKouseiHi() {
		return kyakusuKouseiHi;
	}
	/**
	 * @param kyakusuKouseiHi を クラス変数kyakusuKouseiHiへ設定します。
	 */
	public void setKyakusuKouseiHi(BigDecimal kyakusuKouseiHi) {
		this.kyakusuKouseiHi = kyakusuKouseiHi;
	}
	/**
	 * @return weekType を戻します。
	 */
	public String getWeekType() {
		return weekType;
	}
	/**
	 * @param weekType を クラス変数weekTypeへ設定します。
	 */
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}
    
}
