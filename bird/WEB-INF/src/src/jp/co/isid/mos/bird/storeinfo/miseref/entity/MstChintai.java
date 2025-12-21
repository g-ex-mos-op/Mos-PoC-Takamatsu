package jp.co.isid.mos.bird.storeinfo.miseref.entity;

import java.sql.Timestamp;

public class MstChintai {
    
    public static final String TABLE = "BM21CHIT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseLeaseShu_COLUMN = "MISE_LEASE_SHU";
    
    public static final String miseLeaseName_COLUMN = "MISE_LEASE_NAME";
    
    public static final String miseLeaseStart_COLUMN = "MISE_LEASE_START";
    
    public static final String miseLeaseEnd_COLUMN = "MISE_LEASE_END";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 賃貸店舗種別
     */
    private String miseLeaseShu;
    
    /**
     * 賃貸店舗名称
     */
    private String miseLeaseName;
    
    /**
     * 賃貸店舗開始日
     */
    private String miseLeaseStart;
    
    /**
     * 賃貸店舗終了日
     */
    private String miseLeaseEnd;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 行削除判別フラグ
     */
    private boolean delFlg;

    /**
     * 賃貸店舗種別を取得します。
     * @return 賃貸店舗種別
     */
    public String getMiseLeaseShu() {
        return miseLeaseShu;
    }
    /**
     * 賃貸店舗種別を設定します。
     * @param miseLeaseShu 賃貸店舗種別
     */
    public void setMiseLeaseShu(String miseLeaseShu) {
        this.miseLeaseShu = miseLeaseShu;
    }
    
    /**
     * 賃貸店舗名称を取得します。
     * @return 賃貸店舗名称
     */
    public String getMiseLeaseName() {
        return miseLeaseName;
    }
    /**
     * 賃貸店舗名称を設定します。
     * @param miseLeaseName 賃貸店舗名称
     */
    public void setMiseLeaseName(String miseLeaseName) {
        this.miseLeaseName = miseLeaseName;
    }
    
    /**
     * 賃貸店舗開始日を取得します。
     * @return 賃貸店舗開始日
     */
    public String getMiseLeaseStart() {
        return miseLeaseStart;
    }
    /**
     * 賃貸店舗開始日を設定します。
     * @param miseLeaseStart 賃貸店舗開始日
     */
    public void setMiseLeaseStart(String miseLeaseStart) {
        this.miseLeaseStart = miseLeaseStart;
    }
    
    /**
     * 賃貸店舗終了日を取得します。
     * @return 賃貸店舗終了日
     */
    public String getMiseLeaseEnd() {
        return miseLeaseEnd;
    }
    /**
     * 賃貸店舗終了日を設定します。
     * @param miseLeaseEnd 賃貸店舗終了日
     */
    public void setMiseLeaseEnd(String miseLeaseEnd) {
        this.miseLeaseEnd = miseLeaseEnd;
    }
    
    /**
     * @return delFlg を戻します。
     */
    public boolean isDelFlg() {
        return delFlg;
    }
    /**
     * @param delFlg delFlg を設定。
     */
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
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
}
