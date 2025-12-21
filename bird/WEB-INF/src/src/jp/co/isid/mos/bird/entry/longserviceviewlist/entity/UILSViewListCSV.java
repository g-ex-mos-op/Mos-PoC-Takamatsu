/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.entity;

public class UILSViewListCSV {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String seqNo_COLUMN = "SEQ_NO";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String name_COLUMN = "NAME";
    
    public static final String tel_COLUMN = "TEL";
    
    public static final String lNameKj_COLUMN = "L_NAME_KJ";
    
    public static final String fNameKj_COLUMN = "F_NAME_KJ";
    
    public static final String lNameRm_COLUMN = "L_NAME_RM";
    
    public static final String fNameRm_COLUMN = "F_NAME_RM";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String birthday_COLUMN = "BIRTHDAY";
    
    public static final String age_COLUMN = "AGE";
    
    public static final String entryDate_COLUMN = "ENTRY_DATE";
    
    public static final String expKbn_COLUMN = "EXP_KBN";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * タブ番号
     */
    private String seqNo;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 申込責任者
     */
    private String name;
    
    /**
     * 申込者電話番号
     */
    private String tel;
    
    /**
     * 参加者氏（漢字）
     */
    private String lNameKj;
    
    /**
     * 参加者名（漢字）
     */
    private String fNameKj;
    
    /**
     * 参加者氏（ローマ字）
     */
    private String lNameRm;
    
    /**
     * 参加者名（ローマ字）
     */
    private String fNameRm;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 生年月日
     */
    private String birthday;
    
    /**
     * 年齢
     */
    private String age;
    
    /**
     * 入社年月日
     */
    private String entryDate;
    
    /**
     * 店舗経験
     */
    private String expKbn;
    
    /**
     * 更新者ID
     */
    private String lastUser;
    
    /**
     * 更新者名称
     */
    private String userNameKj;
    
    /**
     * オーナー契約終了日
     */
    private String keiyakuEnd;
    
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
     * タブ番号を取得します。
     * @return タブ番号
     */
    public String getSeqNo() {
        return seqNo;
    }
    /**
     * タブ番号を設定します。
     * @param seqNo タブ番号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 申込責任者を取得します。
     * @return 申込責任者
     */
    public String getName() {
        return name;
    }
    /**
     * 申込責任者を設定します。
     * @param name 申込責任者
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 申込者電話番号を取得します。
     * @return 申込者電話番号
     */
    public String getTel() {
        return tel;
    }
    /**
     * 申込者電話番号を設定します。
     * @param tel 申込者電話番号
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * 参加者氏（漢字）を取得します。
     * @return 参加者氏（漢字）
     */
    public String getLNameKj() {
        return lNameKj;
    }
    /**
     * 参加者氏（漢字）を設定します。
     * @param lNameKj 参加者氏（漢字）
     */
    public void setLNameKj(String lNameKj) {
        this.lNameKj = lNameKj;
    }
    
    /**
     * 参加者名（漢字）を取得します。
     * @return 参加者名（漢字）
     */
    public String getFNameKj() {
        return fNameKj;
    }
    /**
     * 参加者名（漢字）を設定します。
     * @param fNameKj 参加者名（漢字）
     */
    public void setFNameKj(String fNameKj) {
        this.fNameKj = fNameKj;
    }
    
    /**
     * 参加者氏（ローマ字）を取得します。
     * @return 参加者氏（ローマ字）
     */
    public String getLNameRm() {
        return lNameRm;
    }
    /**
     * 参加者氏（ローマ字）を設定します。
     * @param lNameRm 参加者氏（ローマ字）
     */
    public void setLNameRm(String lNameRm) {
        this.lNameRm = lNameRm;
    }
    
    /**
     * 参加者名（ローマ字）を取得します。
     * @return 参加者名（ローマ字）
     */
    public String getFNameRm() {
        return fNameRm;
    }
    /**
     * 参加者名（ローマ字）を設定します。
     * @param fNameRm 参加者名（ローマ字）
     */
    public void setFNameRm(String fNameRm) {
        this.fNameRm = fNameRm;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return sex;
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
	/**
     * 生年月日を取得します。
     * @return 生年月日
     */
	public String getBirthday() {
		return birthday;
	}
	/**
     * 生年月日を設定します。
     * @param birthday 生年月日
     */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
     * 年齢を取得します。
     * @return 年齢
     */
    public String getAge() {
        return age;
    }
    /**
     * 年齢を設定します。
     * @param age 年齢
     */
    public void setAge(String age) {
        this.age = age;
    }
    
    /**
     * 入社年月日を取得します。
     * @return 入社年月日
     */
    public String getEntryDate() {
        return entryDate;
    }
    /**
     * 入社年月日を設定します。
     * @param entryDate 入社年月日
     */
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
    
    /**
     * 店舗経験を取得します。
     * @return 店舗経験
     */
    public String getExpKbn() {
        return expKbn;
    }
    /**
     * 店舗経験を設定します。
     * @param expKbn 店舗経験
     */
    public void setExpKbn(String expKbn) {
        this.expKbn = expKbn;
    }
    
    /**
     * 更新者IDを取得します。
     * @return 更新者ID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新者IDを設定します。
     * @param lastUser 更新者ID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新者名称を取得します。
     * @return 更新者名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 更新者名称を設定します。
     * @param userNameKj 更新者名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * オーナー契約終了日を取得します。
     * @return オーナー契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * オーナー契約終了日を設定します。
     * @param keiyakuEnd オーナー契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    
}
