/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

/**
 * @author 慮
 *
 */
public class UIGroupTogoMise {
	
	public static final String TABLE = "BM01TENM";
	
	public static final String companyCd_COLUMN = "COMPANY_CD";
	
	public static final String miseCd_COLUMN = "MISE_CD";
	
	public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
	
    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String miseKbn_COLUMN = "MISE_KBN";

    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
	private String companyCd;
	
	private String miseCd;
	
	private String gyotaiKbn;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 店区分
     */
    private String miseKbn;

    /**
     * 店名称
     */
    private String miseNameKj;
    
	
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return gyotaiKbn を戻します。
	 */
	public String getGyotaiKbn() {
		return gyotaiKbn;
	}
	/**
	 * @param gyotaiKbn gyotaiKbn を設定。
	 */
	public void setGyotaiKbn(String gyotaiKbn) {
		this.gyotaiKbn = gyotaiKbn;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd miseCd を設定。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
    
    /**
     * 店区分を取得します。
     * @return 店区分
     */
    public String getMiseKbn() {
        return miseKbn;
    }
    /**
     * 店区分を設定します。
     * @param 店区分
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    /**
     * 店名称を取得します。
     * @return miseNameKj 店名称
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
}
