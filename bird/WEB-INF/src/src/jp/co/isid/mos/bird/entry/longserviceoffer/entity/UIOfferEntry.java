package jp.co.isid.mos.bird.entry.longserviceoffer.entity;

import java.sql.Timestamp;

/**
 *
 * @update 2010/04/23 xkinu 店舗経験プルダウン値追加対応 25年・30年を追加
 */
public class UIOfferEntry {

public static final String TABLE = "BT49LONG";

    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";

    public static final String entryKai_COLUMN = "ENTRY_KAI";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String seqNo_COLUMN = "SEQ_NO";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String lNameKj_COLUMN = "L_NAME_KJ";

    public static final String fNameKj_COLUMN = "F_NAME_KJ";

    public static final String lNameRm_COLUMN = "L_NAME_RM";

    public static final String fNameRm_COLUMN = "F_NAME_RM";

    public static final String sex_COLUMN = "SEX";

    public static final String birthday_COLUMN = "BIRTHDAY";

    public static final String age_COLUMN = "AGE";

    public static final String entryDate_COLUMN = "ENTRY_DATE";

    public static final String expKbn_COLUMN = "EXP_KBN";

    public static final String note_COLUMN = "NOTE";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    /**
     * 職種職種(店舗経験の社員とパートのラジオボタンの値)
     * @author xkinu
     * @since 2010/04/22
     */
    public static final String staffType_COLUMN = "STAFF_TYPE";

    /**
     * データのクリア判断フラグ
     */
    private boolean cleaFlg;

    /**
     * エントリーコード
     */
    private String entryCd;

    /**
     * エントリー年
     */
    private String entryYear;

    /**
     * エントリー回
     */
    private String entryKai;

    /**
     * 企業コード
     */
    private String companyCd;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * ソート番号
     */
    private String seqNo;

    /**
     * 確認画面表示用ソート番号
     */
    private int seqNoView;

    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseName;

    /**
     * 氏（漢字）
     */
    private String lNameKj;

    /**
     * 名（漢字）
     */
    private String fNameKj;

    /**
     * 氏（ローマ字）
     */
    private String lNameRm;

    /**
     * 名（ローマ字）
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

    private String birthday_Year;

    private String birthday_Month;

    private String birthday_Day;

    /**
     * 年齢
     */
    private String age;

    /**
     * 入社年月日
     */
    private String entryDate;

    private String entryDate_Year;

    private String entryDate_Month;

    private String entryDate_Day;

    /**
     * 店舗経験
     */
    private String expKbn;

    /**
     * 備考
     */
    private String note;

    /**
     * 登録ユーザー
     */
    private String firstUser;

    /**
     * 登録プログラム
     */
    private String firstPgm;

    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;

    /**
     * 修正ユーザー
     */
    private String lastUser;

    /**
     * 修正プログラム
     */
    private String lastPgm;

    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    /**
     * 職種職種(店舗経験の社員とパートのラジオボタンの値)
     * @author xkinu
     * @since 2010/04/22
     */
    private String staffType = "";

    private String employeeYears = "";
    private String parttimerYears = "";

    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }

    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }

    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * ソート番号を取得します。
     * @return ソート番号
     */
    public String getSeqNo() {
        return seqNo;
    }
    /**
     * ソート番号を設定します。
     * @param seqNo ソート番号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
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
     * 氏（漢字）を取得します。
     * @return 氏（漢字）
     */
    public String getLNameKj() {
        return lNameKj;
    }
    /**
     * 氏（漢字）を設定します。
     * @param lNameKj 氏（漢字）
     */
    public void setLNameKj(String lNameKj) {
        this.lNameKj = lNameKj;
    }

    /**
     * 名（漢字）を取得します。
     * @return 名（漢字）
     */
    public String getFNameKj() {
        return fNameKj;
    }
    /**
     * 名（漢字）を設定します。
     * @param fNameKj 名（漢字）
     */
    public void setFNameKj(String fNameKj) {
        this.fNameKj = fNameKj;
    }

    /**
     * 氏（ローマ字）を取得します。
     * @return 氏（ローマ字）
     */
    public String getLNameRm() {
        return lNameRm;
    }
    /**
     * 氏（ローマ字）を設定します。
     * @param lNameRm 氏（ローマ字）
     */
    public void setLNameRm(String lNameRm) {
        this.lNameRm = lNameRm;
    }

    /**
     * 名（ローマ字）を取得します。
     * @return 名（ローマ字）
     */
    public String getFNameRm() {
        return fNameRm;
    }
    /**
     * 名（ローマ字）を設定します。
     * @param fNameRm 名（ローマ字）
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
     * 生年月日を戻します。
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 生年月日を設定する。
	 * @param birthday
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
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
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
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }

    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }

    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
	public String getBirthday_Day() {
		return birthday_Day;
	}
	public void setBirthday_Day(String birthday_Day) {
		this.birthday_Day = birthday_Day;
	}
	public String getBirthday_Month() {
		return birthday_Month;
	}
	public void setBirthday_Month(String birthday_Month) {
		this.birthday_Month = birthday_Month;
	}
	public String getBirthday_Year() {
		return birthday_Year;
	}
	public void setBirthday_Year(String birthday_Year) {
		this.birthday_Year = birthday_Year;
	}
	public String getEntryDate_Day() {
		return entryDate_Day;
	}
	public void setEntryDate_Day(String entryDate_Day) {
		this.entryDate_Day = entryDate_Day;
	}
	public String getEntryDate_Month() {
		return entryDate_Month;
	}
	public void setEntryDate_Month(String entryDate_Month) {
		this.entryDate_Month = entryDate_Month;
	}
	public String getEntryDate_Year() {
		return entryDate_Year;
	}
	public void setEntryDate_Year(String entryDate_Year) {
		this.entryDate_Year = entryDate_Year;
	}
	public boolean isCleaFlg() {
		return cleaFlg;
	}
	public void setCleaFlg(boolean cleaFlg) {
		this.cleaFlg = cleaFlg;
	}
	public String getMiseName() {
		return miseName;
	}
	public void setMiseName(String miseName) {
		this.miseName = miseName;
	}
	public int getSeqNoView() {
		this.seqNoView = Integer.parseInt(this.seqNo.trim());
		return seqNoView;
	}
	public void setSeqNoView(int seqNoView) {
		this.seqNoView = seqNoView;
	}
	/**
	 * 職種取得処理
	 *
	 * 店舗経験の社員とパートのラジオボタンの値。
	 * 店舗経験の先頭1バイトの値を取得します。
	 * 店舗経験の値が空(or null)の場合は”E”(社員)を返します。
	 * @return
	 */
	public String getStaffType() {
		return staffType;
	}
	/**
	 * 職種設定処理
	 * @param type "E" or "P"の値が設定されます。
	 */
	public void setStaffType(String type) {
		staffType = type;
	}
	/**
	 * @return クラス変数employeeYears を戻します。
	 */
	public String getEmployeeYears() {
		return employeeYears;
	}
	/**
	 * @param employeeYears を クラス変数employeeYearsへ設定します。
	 */
	public void setEmployeeYears(String employeeYears) {
		this.employeeYears = employeeYears;
	}
	/**
	 * @return クラス変数parttimerYears を戻します。
	 */
	public String getParttimerYears() {
		return parttimerYears;
	}
	/**
	 * @param parttimerYears を クラス変数parttimerYearsへ設定します。
	 */
	public void setParttimerYears(String parttimerYears) {
		this.parttimerYears = parttimerYears;
	}

}
