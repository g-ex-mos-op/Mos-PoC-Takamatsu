/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.entity;

/**
 * @author xyuchida
 *
 */
public class UIUserRole {
    
    public static final String TABLE = "BR04USRL";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String usertypeName_COLUMN = "USERTYPE_NAME";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_NAME";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ユーザ名称
     */
    private String userNameKj;
    
    /**
     * ユーザタイプコード
     */
    private String usertypeCd;
    
    /**
     * ユーザタイプ名
     */
    private String usertypeName;
    
    /**
     * 会社コード
     */
    private String rCompanyCd;
    /**
     * 会社名
     */
    private String companyName;
    
    /**
     * 部門コード
     */
    private String bumonCd;
    
    /**
     * 部門名称
     */
    private String bumonName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * ロールコードを取得します。
     * @return ロールコード
     */
    public String getRoleCd() {
        return roleCd;
    }
    /**
     * ロールコードを設定します。
     * @param roleCd ロールコード
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
    /**
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNameKj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * ユーザタイプコードを取得します。
     * @return ユーザタイプコード
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザタイプコードを設定します。
     * @param usertypeCd ユーザタイプコード
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }
    
    /**
     * ユーザタイプ名を取得します。
     * @return ユーザタイプ名
     */
    public String getUsertypeName() {
        return usertypeName;
    }
    /**
     * ユーザタイプ名を設定します。
     * @param usertypeName ユーザタイプ名
     */
    public void setUsertypeName(String usertypeName) {
        this.usertypeName = usertypeName;
    }
    
    /**
     * 会社名を取得します。
     * @return 会社名
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名を設定します。
     * @param companyName 会社名
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 部門名称を取得します。
     * @return 部門名称
     */
    public String getBumonName() {
        return bumonName;
    }
    /**
     * 部門名称を設定します。
     * @param bumonName 部門名称
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
	/**
	 * @return bumonCd を戻します。
	 */
	public String getBumonCd() {
		return bumonCd;
	}
	/**
	 * @param bumonCd を クラス変数bumonCdへ設定します。
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd を クラス変数onerCdへ設定します。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return rCompanyCd を戻します。
	 */
	public String getRCompanyCd() {
		return rCompanyCd;
	}
	/**
	 * @param companyCd を クラス変数rCompanyCdへ設定します。
	 */
	public void setRCompanyCd(String companyCd) {
		rCompanyCd = companyCd;
	}
}
