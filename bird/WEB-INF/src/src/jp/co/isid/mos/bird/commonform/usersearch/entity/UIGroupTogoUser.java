/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.entity;
/**
 * 
 * @author m.onodera
 */
public class UIGroupTogoUser {

    public static final String TABLE = "BR01USER";

    public static final String userId_COLUMN       = "USER_ID";
    public static final String userName_COLUMN     = "USER_NAME_KJ";
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    public static final String usertypeCd_COLUMN   = "USERTYPE_CD";
    public static final String usertypeName_COLUMN = "USERTYPE_NAME";
    public static final String companyCd_COLUMN    = "R_COMPANY_CD";
    public static final String companyName_COLUMN  = "COMPANY_NAME";
    public static final String bumonCd_COLUMN      = "BUMON_CD";
    public static final String bumonName_COLUMN    = "BUMON_NAME";
    public static final String onerCd_COLUMN       = "ONER_CD";
    public static final String onerNameKj_COLUMN   = "ONER_NAME_KJ";
    public static final String miseCd_COLUMN       = "MISE_CD";
    public static final String miseNameKj_COLUMN   = "MISE_NAME_KJ";
    public static final String sosiki_COLUMN       = "SOSIKI";


    /** ユーザーID */
    private String userId;
    /** ユーザー名称 */
    private String userName;
    /** ユーザー名称(カナ) */
    private String userNameKana;
    /** ユーザータイプコード */
    private String usertypeCd;
    /** ユーザータイプ名称 */
    private String usertypeName;
    /** 会社コード */
    private String companyCd;
    /** 会社名 */
    private String companyName;
    /** 部門コード */
    private String bumonCd;
    /** 部門名 */
    private String bumonName;
    /** オーナーコード */
    private String onerCd;
    /** オーナー名称 */
    private String onerNameKj;
    /** 店コード */
    private String miseCd;
    /** 店名称 */
    private String miseNameKj;
    /** 組織（表示用） */
	public String sosiki;


    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
		return userId;
	}
    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     * ユーザー名称を取得します。
     * @return ユーザー名称
     */
	public String getUserName() {
		return userName;
	}
    /**
     * ユーザー名称を設定します。
     * @param userName ユーザー名称
     */
	public void setUserName(String userName) {
		this.userName = userName;
	}
    /**
     * ユーザー名称(カナ)を取得します。
     * @return ユーザー名称(カナ)
     */
	public String getUserNameKana() {
		return userNameKana;
	}
    /**
     * ユーザー名称(カナ)を設定します。
     * @param userNameKana ユーザー名称(カナ)
     */
	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}
    /**
     * ユーザータイプコードを取得します。
     * @return ユーザータイプコード
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザータイプコードを設定します。
     * @param usertypeCd ユーザータイプコード
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }
    /**
     * ユーザータイプ名称を取得します。
     * @return ユーザータイプ名称
     */
    public String getUsertypeName() {
        return usertypeName;
    }
    /**
     * ユーザータイプ名称を設定します。
     * @param usertypeName ユーザータイプ名称
     */
    public void setUsertypeName(String usertypeName) {
        this.usertypeName = usertypeName;
    }
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
		return companyCd;
	}
    /**
     * 会社コードを設定します。
     * @param kaisyaCd 会社コード
     */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
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
     * 部門コードを取得します。
     * @return 部門コード
     */
    public String getBumonCd() {
		return bumonCd;
	}
    /**
     * 部門コードを設定します。
     * @param bumonCd 部門コード
     */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}
    /**
     * 部門名を取得します。
     * @return 部門名
     */
    public String getBumonName() {
		return bumonName;
	}
    /**
     * 部門名を設定します。
     * @param bumonName 部門名
     */
	public void setBumonName(String bumonName) {
		this.bumonName = bumonName;
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
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
		return onerNameKj;
	}
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
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
     * 組織（表示用）を取得します。
     * 
     * ユーザータイプコードにしたがって、組織名を返します。
     *-------------------------- 
     * [所属区分]→[組織名]
     * 　本部(01) → 部門名
     * 　ｵｰﾅｰ(02) → オーナー名
     * 　店舗(03) → 店名称
     *-------------------------- 
     * @return 組織
     */
    public String getSosiki() {
        return sosiki;
    }
    /**
     * 組織（表示用）を設定します。
     * 
     * ユーザータイプコードにしたがって、組織名を返します。
     *-------------------------- 
     * [所属区分]→[組織名]
     * 　本部(01) → 部門名
     * 　ｵｰﾅｰ(02) → オーナー名
     * 　店舗(03) → 店名称
     *-------------------------- 
     * @param sosiki 組織
     */
    public void setSosiki(String sosiki) {
        this.sosiki = sosiki;
    }
}
