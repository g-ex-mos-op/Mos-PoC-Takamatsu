/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity;

/**
 * @author Noh
 */
public class UIMLResultNum {
	
	public static final String TABLE = "BT32MLKR";
	
	public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
	
	public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
	
	public static final String goukakuNum_COLUMN = "GOKAKU";
	
	public static final String fugoukakuNum_COLUMN = "HUGOKAKU";
    
    public static final String horyuNum_COLUMN = "HORYU";
    
    public static final String mukouNum_COLUMN = "MUKOU";
	
	/**
	 * 合格者人数
	 */
	private String goukakuNum;
	
	/**
	 * 不合格者人数
	 */
	private String fugoukakuNum;
		
	/**
	 * エントリー年
	 */
	private String totalLastYear;
	
	/**
	 * エントリー回
	 */
	private String totalLastKai;
    
    /**
     * 保留者人数
     */
    private String horyuNum;
    
    /**
     * 無効者人数
     */
    private String mukouNum;

	
	
	/**
	 * @return totalLastKai を戻します。
	 */
	public String getTotalLastKai() {
		return totalLastKai;
	}
	/**
	 * @param totalLastKai totalLastKai を設定。
	 */
	public void setTotalLastKai(String totalLastKai) {
		this.totalLastKai = totalLastKai;
	}
	/**
	 * @return totalLastYear を戻します。
	 */
	public String getTotalLastYear() {
		return totalLastYear;
	}
	/**
	 * @param totalLastYear totalLastYear を設定。
	 */
	public void setTotalLastYear(String totalLastYear) {
		this.totalLastYear = totalLastYear;
	}
	/**
	 * @return fugoukakuNum を戻します。
	 */
	public String getFugoukakuNum() {
		return fugoukakuNum;
	}
	/**
	 * @param fugoukakuNum fugoukakuNum を設定。
	 */
	public void setFugoukakuNum(String fugoukakuNum) {
		this.fugoukakuNum = fugoukakuNum;
	}
	/**
	 * @return goukakuNum を戻します。
	 */
	public String getGoukakuNum() {
		return goukakuNum;
	}
	/**
	 * @param goukakuNum goukakuNum を設定。
	 */
	public void setGoukakuNum(String goukakuNum) {
		this.goukakuNum = goukakuNum;
	}
    
    /**
     * @return horyuNum を戻します。
     */
    public String getHoryuNum() {
        return horyuNum;
    }
    /**
     * @param horyuNum horyuNum を設定。
     */
    public void setHoryuNum(String horyuNum) {
        this.horyuNum = horyuNum;
    }
    
    /**
     * @return mukouNum を戻します。
     */
    public String getMukouNum() {
        return mukouNum;
    }
    /**
     * @param mukouNum mukouNum を設定。
     */
    public void setMukouNum(String mukouNum) {
        this.mukouNum = mukouNum;
    }
}
