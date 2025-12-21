package jp.co.isid.mos.bird.entry.nationalentry.entity;

import java.sql.Timestamp;

public class UINatiEntryJoinInfo {

    public static final String TABLE = "BT48NATI";

    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";

    public static final String entryKai_COLUMN = "ENTRY_KAI";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String seqNo_COLUMN = "SEQ_NO";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String lNameKj_COLUMN = "L_NAME_KJ";

    public static final String fNameKj_COLUMN = "F_NAME_KJ";

    public static final String lNameKna_COLUMN = "L_NAME_KNA";

    public static final String fNameKna_COLUMN = "F_NAME_KNA";

    public static final String sex_COLUMN = "SEX";

    public static final String birthday_COLUMN = "BIRTHDAY";

    public static final String age_COLUMN = "AGE";

    public static final String jobType_COLUMN = "JOB_TYPE";

    public static final String proposeKbn_COLUMN = "PROPOSE_KBN";

    public static final String optional1_COLUMN = "OPTIONAL1";

    public static final String optional2_COLUMN = "OPTIONAL2";

    public static final String optional3_COLUMN = "OPTIONAL3";

    public static final String optional4_COLUMN = "OPTIONAL4";

    public static final String optional5_COLUMN = "OPTIONAL5";

    public static final String optional6_COLUMN = "OPTIONAL6";

    public static final String optional7_COLUMN = "OPTIONAL7";

    public static final String optional8_COLUMN = "OPTIONAL8";

    public static final String optional9_COLUMN = "OPTIONAL9";

    public static final String optional10_COLUMN = "OPTIONAL10";

    public static final String optional11_COLUMN = "OPTIONAL11";

    public static final String optional12_COLUMN = "OPTIONAL12";

    public static final String optional13_COLUMN = "OPTIONAL13";

    public static final String optional14_COLUMN = "OPTIONAL14";

    public static final String optional15_COLUMN = "OPTIONAL15";

    public static final String optional16_COLUMN = "OPTIONAL16";

    public static final String optional17_COLUMN = "OPTIONAL17";

    public static final String optional18_COLUMN = "OPTIONAL18";

    public static final String optional19_COLUMN = "OPTIONAL19";

    public static final String optional20_COLUMN = "OPTIONAL20";

    public static final String optional21_COLUMN = "OPTIONAL21";

    public static final String optional22_COLUMN = "OPTIONAL22";

    public static final String optional23_COLUMN = "OPTIONAL23";

    public static final String optional24_COLUMN = "OPTIONAL24";

    public static final String optional25_COLUMN = "OPTIONAL25";

    public static final String optional26_COLUMN = "OPTIONAL26";

    public static final String optional27_COLUMN = "OPTIONAL27";

    public static final String optional28_COLUMN = "OPTIONAL28";

    public static final String optional29_COLUMN = "OPTIONAL29";

    public static final String optional30_COLUMN = "OPTIONAL30";

    public static final String note1_COLUMN = "NOTE1";

    public static final String note_COLUMN = "NOTE";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

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
     * 店コード
     */
    private String miseCd;

    /**
     * 漢字-氏
     */
    private String lNameKj;

    /**
     * 漢字-名
     */
    private String fNameKj;

    /**
     * カナ-氏
     */
    private String lNameKna;

    /**
     * カナ-名
     */
    private String fNameKna;

    /**
     * 性別
     */
    private String sex;

    /**
     * 年齢
     */
    private String age;

    /**
     * 生年月日
     */
    private String birthday;

//    private String birthday_Nengo;

    private String birthday_Year;

    private String birthday_Month;

    private String birthday_Day;

    /**
     * 職位コード
     */
    private String jobTypeCd;
    /**
     * 職位
     */
    private String jobType;

    /**
     * 申込区分
     */
    private String proposeKbn;

    /**
     * オプショナル1
     */
    private String optional1;

    /**
     * オプショナル2
     */
    private String optional2;

    /**
     * オプショナル3
     */
    private String optional3;

    /**
     * オプショナル4
     */
    private String optional4;

    /**
     * オプショナル5
     */
    private String optional5;

    /**
     * オプショナル6
     */
    private String optional6;

    /**
     * オプショナル7
     */
    private String optional7;

    /**
     * オプショナル8
     */
    private String optional8;

    /**
     * オプショナル9
     */
    private String optional9;

    /**
     * オプショナル10
     */
    private String optional10;

    /**
     * オプショナル11
     */
    private String optional11;

    /**
     * オプショナル12
     */
    private String optional12;

    /**
     * オプショナル13
     */
    private String optional13;

    /**
     * オプショナル14
     */
    private String optional14;

    /**
     * オプショナル15
     */
    private String optional15;

    /**
     * オプショナル16
     */
    private String optional16;

    /**
     * オプショナル17
     */
    private String optional17;

    /**
     * オプショナル18
     */
    private String optional18;

    /**
     * オプショナル19
     */
    private String optional19;

    /**
     * オプショナル20
     */
    private String optional20;

    /**
     * オプショナル21
     */
    private String optional21;

    /**
     * オプショナル22
     */
    private String optional22;

    /**
     * オプショナル23
     */
    private String optional23;

    /**
     * オプショナル24
     */
    private String optional24;

    /**
     * オプショナル25
     */
    private String optional25;

    /**
     * オプショナル26
     */
    private String optional26;

    /**
     * オプショナル27
     */
    private String optional27;

    /**
     * オプショナル28
     */
    private String optional28;

    /**
     * オプショナル29
     */
    private String optional29;

    /**
     * オプショナル30
     */
    private String optional30;

    /**
     * 備考1
     */
    private String note1;

    /**
     * 備考2
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
     * 店舗名称
     */
    private String miseNm;

    /**
     * 申込区分
     */
    private String proposeKbnNm;

    /**
     * オプショナル1
     */
    private String optional1Nm;

    /**
     * オプショナル2
     */
    private String optional2Nm;

    /**
     * オプショナル3
     */
    private String optional3Nm;

    /**
     * オプショナル4
     */
    private String optional4Nm;

    /**
     * オプショナル5
     */
    private String optional5Nm;

    /**
     * オプショナル6
     */
    private String optional6Nm;

    /**
     * オプショナル7
     */
    private String optional7Nm;

    /**
     * オプショナル8
     */
    private String optional8Nm;

    /**
     * オプショナル9
     */
    private String optional9Nm;

    /**
     * オプショナル10
     */
    private String optional10Nm;

    /**
     * オプショナル11
     */
    private String optional11Nm;

    /**
     * オプショナル12
     */
    private String optional12Nm;

    /**
     * オプショナル13
     */
    private String optional13Nm;

    /**
     * オプショナル14
     */
    private String optional14Nm;

    /**
     * オプショナル15
     */
    private String optional15Nm;

    /**
     * オプショナル16
     */
    private String optional16Nm;

    /**
     * オプショナル17
     */
    private String optional17Nm;

    /**
     * オプショナル18
     */
    private String optional18Nm;

    /**
     * オプショナル19
     */
    private String optional19Nm;

    /**
     * オプショナル20
     */
    private String optional20Nm;

    /**
     * オプショナル21
     */
    private String optional21Nm;

    /**
     * オプショナル22
     */
    private String optional22Nm;

    /**
     * オプショナル23
     */
    private String optional23Nm;

    /**
     * オプショナル24
     */
    private String optional24Nm;

    /**
     * オプショナル25
     */
    private String optional25Nm;

    /**
     * オプショナル26
     */
    private String optional26Nm;

    /**
     * オプショナル27
     */
    private String optional27Nm;

    /**
     * オプショナル28
     */
    private String optional28Nm;

    /**
     * オプショナル29
     */
    private String optional29Nm;

    /**
     * オプショナル30
     */
    private String optional30Nm;

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
     * 漢字-氏を取得します。
     * @return 漢字-氏
     */
    public String getLNameKj() {
    	return lNameKj != null ? lNameKj.trim() : lNameKj;
    }
    /**
     * 漢字-氏を設定します。
     * @param lNameKj 漢字-氏
     */
    public void setLNameKj(String lNameKj) {
        this.lNameKj = lNameKj;
    }

    /**
     * 漢字-名を取得します。
     * @return 漢字-名
     */
    public String getFNameKj() {
    	return fNameKj != null ? fNameKj.trim() : fNameKj;
    }
    /**
     * 漢字-名を設定します。
     * @param fNameKj 漢字-名
     */
    public void setFNameKj(String fNameKj) {
        this.fNameKj = fNameKj;
    }

    /**
     * カナ-氏を取得します。
     * @return カナ-氏
     */
    public String getLNameKna() {
    	return lNameKna != null ? lNameKna.trim() : lNameKna;
    }
    /**
     * カナ-氏を設定します。
     * @param lNameKna カナ-氏
     */
    public void setLNameKna(String lNameKna) {
        this.lNameKna = lNameKna;
    }

    /**
     * カナ-名を取得します。
     * @return カナ-名
     */
    public String getFNameKna() {
        return fNameKna != null ? fNameKna.trim() : fNameKna;
    }
    /**
     * カナ-名を設定します。
     * @param fNameKna カナ-名
     */
    public void setFNameKna(String fNameKna) {
        this.fNameKna = fNameKna;
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
	 * 生年月日を取得すます。
	 * @return 生年月日
	 */
	public String getBirthday() {
		return birthday != null ? birthday.trim() : birthday;
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
        return age != null ? age.trim() : age;
    }
    /**
     * 年齢を設定します。
     * @param age 年齢
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 職位を取得します。
     * @return 職位
     */
    public String getJobType() {
    	return jobType != null ? jobType.trim() : jobType;
    }
    /**
     * 職位を設定します。
     * @param jobType 職位
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * 申込区分を取得します。
     * @return 申込区分
     */
    public String getProposeKbn() {
        return proposeKbn;
    }
    /**
     * 申込区分を設定します。
     * @param proposeKbn 申込区分
     */
    public void setProposeKbn(String proposeKbn) {
        this.proposeKbn = proposeKbn;
    }

    /**
     * オプショナル1を取得します。
     * @return オプショナル1
     */
    public String getOptional1() {
        return optional1;
    }
    /**
     * オプショナル1を設定します。
     * @param optional1 オプショナル1
     */
    public void setOptional1(String optional1) {
        this.optional1 = optional1;
    }

    /**
     * オプショナル2を取得します。
     * @return オプショナル2
     */
    public String getOptional2() {
        return optional2;
    }
    /**
     * オプショナル2を設定します。
     * @param optional2 オプショナル2
     */
    public void setOptional2(String optional2) {
        this.optional2 = optional2;
    }

    /**
     * オプショナル3を取得します。
     * @return オプショナル3
     */
    public String getOptional3() {
        return optional3;
    }
    /**
     * オプショナル3を設定します。
     * @param optional3 オプショナル3
     */
    public void setOptional3(String optional3) {
        this.optional3 = optional3;
    }

    /**
     * オプショナル4を取得します。
     * @return オプショナル4
     */
    public String getOptional4() {
        return optional4;
    }
    /**
     * オプショナル4を設定します。
     * @param optional4 オプショナル4
     */
    public void setOptional4(String optional4) {
        this.optional4 = optional4;
    }

    /**
     * オプショナル5を取得します。
     * @return オプショナル5
     */
    public String getOptional5() {
        return optional5;
    }
    /**
     * オプショナル5を設定します。
     * @param optional5 オプショナル5
     */
    public void setOptional5(String optional5) {
        this.optional5 = optional5;
    }

    /**
     * オプショナル6を取得します。
     * @return オプショナル6
     */
    public String getOptional6() {
        return optional6;
    }
    /**
     * オプショナル6を設定します。
     * @param optional6 オプショナル6
     */
    public void setOptional6(String optional6) {
        this.optional6 = optional6;
    }

    /**
     * オプショナル7を取得します。
     * @return オプショナル7
     */
    public String getOptional7() {
        return optional7;
    }
    /**
     * オプショナル7を設定します。
     * @param optional7 オプショナル7
     */
    public void setOptional7(String optional7) {
        this.optional7 = optional7;
    }

    /**
     * オプショナル8を取得します。
     * @return オプショナル8
     */
    public String getOptional8() {
        return optional8;
    }
    /**
     * オプショナル8を設定します。
     * @param optional8 オプショナル8
     */
    public void setOptional8(String optional8) {
        this.optional8 = optional8;
    }

    /**
     * オプショナル9を取得します。
     * @return オプショナル9
     */
    public String getOptional9() {
        return optional9;
    }
    /**
     * オプショナル9を設定します。
     * @param optional9 オプショナル9
     */
    public void setOptional9(String optional9) {
        this.optional9 = optional9;
    }

    /**
     * オプショナル10を取得します。
     * @return オプショナル10
     */
    public String getOptional10() {
        return optional10;
    }
    /**
     * オプショナル10を設定します。
     * @param optional10 オプショナル10
     */
    public void setOptional10(String optional10) {
        this.optional10 = optional10;
    }

    /**
     * 備考1を取得します。
     * @return 備考1
     */
    public String getNote1() {
        return note1 != null ? note1.trim() : note1;
    }
    /**
     * 備考1を設定します。
     * @param note1 備考1
     */
    public void setNote1(String note1) {
        this.note1 = note1;
    }

	/**
	 * 備考2を取得します。
	 * @return 備考2
	 */
	public String getNote() {
		return note != null ? note.trim() : note;
	}
	/**
	 * 備考2を設定します。
	 * @param note 設定する
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

    //**********************************************/
    //*　店名所、申込名称、オプショナル名称を設定する
    //*********************************************/
    /**
     * 店舗を取得します。
     * @return 店舗
     */
    public String getMiseNm() {
        return miseNm;
    }
    /**
     * 店舗を設定します。
     * @param miseNm 店舗
     */
    public void setMiseNm(String miseNm) {
        this.miseNm = miseNm;
    }

    /**
     * 申込区分を取得します。
     * @return 申込区分
     */
    public String getProposeKbnNm() {
        return proposeKbnNm;
    }
    /**
     * 申込区分を設定します。
     * @param proposeKbn 申込区分
     */
    public void setProposeKbnNm(String proposeKbnNm) {
        this.proposeKbnNm = proposeKbnNm;
    }

    /**
     * オプショナル1を取得します。
     * @return オプショナル1
     */
    public String getOptional1Nm() {
        return optional1Nm;
    }
    /**
     * オプショナル1を設定します。
     * @param optional1 オプショナル1
     */
    public void setOptional1Nm(String optional1Nm) {
        this.optional1Nm = optional1Nm;
    }

    /**
     * オプショナル2を取得します。
     * @return オプショナル2
     */
    public String getOptional2Nm() {
        return optional2Nm;
    }
    /**
     * オプショナル2を設定します。
     * @param optional2 オプショナル2
     */
    public void setOptional2Nm(String optional2Nm) {
        this.optional2Nm = optional2Nm;
    }

    /**
     * オプショナル3を取得します。
     * @return オプショナル3
     */
    public String getOptional3Nm() {
        return optional3Nm;
    }
    /**
     * オプショナル3を設定します。
     * @param optional3 オプショナル3
     */
    public void setOptional3Nm(String optional3Nm) {
        this.optional3Nm = optional3Nm;
    }

    /**
     * オプショナル4を取得します。
     * @return オプショナル4
     */
    public String getOptional4Nm() {
        return optional4Nm;
    }
    /**
     * オプショナル4を設定します。
     * @param optional4 オプショナル4
     */
    public void setOptional4Nm(String optional4Nm) {
        this.optional4Nm = optional4Nm;
    }

    /**
     * オプショナル5を取得します。
     * @return オプショナル5
     */
    public String getOptional5Nm() {
        return optional5Nm;
    }
    /**
     * オプショナル5を設定します。
     * @param optional5 オプショナル5
     */
    public void setOptional5Nm(String optional5Nm) {
        this.optional5Nm = optional5Nm;
    }

    /**
     * オプショナル6を取得します。
     * @return オプショナル6
     */
    public String getOptional6Nm() {
        return optional6Nm;
    }
    /**
     * オプショナル6を設定します。
     * @param optional6 オプショナル6
     */
    public void setOptional6Nm(String optional6Nm) {
        this.optional6Nm = optional6Nm;
    }

    /**
     * オプショナル7を取得します。
     * @return オプショナル7
     */
    public String getOptional7Nm() {
        return optional7Nm;
    }
    /**
     * オプショナル7を設定します。
     * @param optional7 オプショナル7
     */
    public void setOptional7Nm(String optional7Nm) {
        this.optional7Nm = optional7Nm;
    }

    /**
     * オプショナル8を取得します。
     * @return オプショナル8
     */
    public String getOptional8Nm() {
        return optional8Nm;
    }
    /**
     * オプショナル8を設定します。
     * @param optional8 オプショナル8
     */
    public void setOptional8Nm(String optional8Nm) {
        this.optional8Nm = optional8Nm;
    }

    /**
     * オプショナル9を取得します。
     * @return オプショナル9
     */
    public String getOptional9Nm() {
        return optional9Nm;
    }
    /**
     * オプショナル9を設定します。
     * @param optional9 オプショナル9
     */
    public void setOptional9Nm(String optional9Nm) {
        this.optional9Nm = optional9Nm;
    }

    /**
     * オプショナル10を取得します。
     * @return オプショナル10
     */
    public String getOptional10Nm() {
        return optional10Nm;
    }
    /**
     * オプショナル10を設定します。
     * @param optional10 オプショナル10
     */
    public void setOptional10Nm(String optional10Nm) {
        this.optional10Nm = optional10Nm;
    }

    /**
     * オプショナル11を取得します。
     * @return オプショナル11
     */
    public String getOptional11Nm() {
		return optional11Nm;
	}
    /**
     * オプショナル11を設定します。
     * @param optional11 オプショナル11
     */
	public void setOptional11Nm(String optional11Nm) {
		this.optional11Nm = optional11Nm;
	}
	public String getOptional12Nm() {
		return optional12Nm;
	}
	public void setOptional12Nm(String optional12Nm) {
		this.optional12Nm = optional12Nm;
	}
	public String getOptional13Nm() {
		return optional13Nm;
	}
	public void setOptional13Nm(String optional13Nm) {
		this.optional13Nm = optional13Nm;
	}
	public String getOptional14Nm() {
		return optional14Nm;
	}
	public void setOptional14Nm(String optional14Nm) {
		this.optional14Nm = optional14Nm;
	}
	public String getOptional15Nm() {
		return optional15Nm;
	}
	public void setOptional15Nm(String optional15Nm) {
		this.optional15Nm = optional15Nm;
	}
	public String getOptional16Nm() {
		return optional16Nm;
	}
	public void setOptional16Nm(String optional16Nm) {
		this.optional16Nm = optional16Nm;
	}
	public String getOptional17Nm() {
		return optional17Nm;
	}
	public void setOptional17Nm(String optional17Nm) {
		this.optional17Nm = optional17Nm;
	}
	public String getOptional18Nm() {
		return optional18Nm;
	}
	public void setOptional18Nm(String optional18Nm) {
		this.optional18Nm = optional18Nm;
	}
	public String getOptional19Nm() {
		return optional19Nm;
	}
	public void setOptional19Nm(String optional19Nm) {
		this.optional19Nm = optional19Nm;
	}
	public String getOptional20Nm() {
		return optional20Nm;
	}
	public void setOptional20Nm(String optional20Nm) {
		this.optional20Nm = optional20Nm;
	}
	public String getOptional21Nm() {
		return optional21Nm;
	}
	public void setOptional21Nm(String optional21Nm) {
		this.optional21Nm = optional21Nm;
	}
	public String getOptional22Nm() {
		return optional22Nm;
	}
	public void setOptional22Nm(String optional22Nm) {
		this.optional22Nm = optional22Nm;
	}
	public String getOptional23Nm() {
		return optional23Nm;
	}
	public void setOptional23Nm(String optional23Nm) {
		this.optional23Nm = optional23Nm;
	}
	public String getOptional24Nm() {
		return optional24Nm;
	}
	public void setOptional24Nm(String optional24Nm) {
		this.optional24Nm = optional24Nm;
	}
	public String getOptional25Nm() {
		return optional25Nm;
	}
	public void setOptional25Nm(String optional25Nm) {
		this.optional25Nm = optional25Nm;
	}
	public String getOptional26Nm() {
		return optional26Nm;
	}
	public void setOptional26Nm(String optional26Nm) {
		this.optional26Nm = optional26Nm;
	}
	public String getOptional27Nm() {
		return optional27Nm;
	}
	public void setOptional27Nm(String optional27Nm) {
		this.optional27Nm = optional27Nm;
	}
	public String getOptional28Nm() {
		return optional28Nm;
	}
	public void setOptional28Nm(String optional28Nm) {
		this.optional28Nm = optional28Nm;
	}
	public String getOptional29Nm() {
		return optional29Nm;
	}
	public void setOptional29Nm(String optional29Nm) {
		this.optional29Nm = optional29Nm;
	}
	public String getOptional30Nm() {
		return optional30Nm;
	}
	public void setOptional30Nm(String optional30Nm) {
		this.optional30Nm = optional30Nm;
	}
	//**********************************************/
    //*　オプショナル1から１０までの選択可否 - boolean
    //*********************************************/
    public boolean getOptional1Check() {
        return optional1 != null && optional1.equals("1");
    }

    public void setOptional1Check(boolean optional1) {
        this.optional1 = (optional1) ? "1" : "";
    }

    public boolean getOptional2Check() {
        return optional2 != null && optional2.equals("1");
    }

    public void setOptional2Check(boolean optional2) {
        this.optional2 = (optional2) ? "1" : "";
    }

    public boolean getOptional3Check() {
        return optional3 != null && optional3.equals("1");
    }

    public void setOptional3Check(boolean optional3) {
        this.optional3 = (optional3) ? "1" : "";
    }

    public boolean getOptional4Check() {
        return optional4 != null && optional4.equals("1");
    }

    public void setOptional4Check(boolean optional4) {
        this.optional4 = (optional4) ? "1" : "";
    }

    public boolean getOptional5Check() {
        return optional5 != null && optional5.equals("1");
    }

    public void setOptional5Check(boolean optional5) {
        this.optional5 = (optional5) ? "1" : "";
    }

    public boolean getOptional6Check() {
        return optional6 != null && optional6.equals("1");
    }

    public void setOptional6Check(boolean optional6) {
        this.optional6 = (optional6) ? "1" : "";
    }

    public boolean getOptional7Check() {
        return optional7 != null && optional7.equals("1");
    }

    public void setOptional7Check(boolean optional7) {
        this.optional7 = (optional7) ? "1" : "";
    }

    public boolean getOptional8Check() {
        return optional8 != null && optional8.equals("1");
    }

    public void setOptional8Check(boolean optional8) {
        this.optional8 = (optional8) ? "1" : "";
    }

    public boolean getOptional9Check() {
        return optional9 != null && optional9.equals("1");
    }

    public void setOptional9Check(boolean optional9) {
        this.optional9 = (optional9) ? "1" : "";
    }

    public boolean getOptional10Check() {
        return optional10 != null && optional10.equals("1");
    }

    public void setOptional10Check(boolean optional10) {
        this.optional10 = (optional10) ? "1" : "";
    }

    public boolean getOptional11Check() {
        return optional11 != null && optional11.equals("1");
    }

    public void setOptional11Check(boolean optional11) {
        this.optional11 = (optional11) ? "1" : "";
    }

    public boolean getOptional12Check() {
        return optional12 != null && optional12.equals("1");
    }

    public void setOptional12Check(boolean optional12) {
        this.optional12 = (optional12) ? "1" : "";
    }

    public boolean getOptional13Check() {
        return optional13 != null && optional13.equals("1");
    }

    public void setOptional13Check(boolean optional13) {
        this.optional13 = (optional13) ? "1" : "";
    }

    public boolean getOptional14Check() {
        return optional14 != null && optional14.equals("1");
    }

    public void setOptional14Check(boolean optional14) {
        this.optional14 = (optional14) ? "1" : "";
    }

    public boolean getOptional15Check() {
        return optional15 != null && optional15.equals("1");
    }

    public void setOptional15Check(boolean optional15) {
        this.optional15 = (optional15) ? "1" : "";
    }

    public boolean getOptional16Check() {
        return optional16 != null && optional16.equals("1");
    }

    public void setOptional16Check(boolean optional16) {
        this.optional16 = (optional16) ? "1" : "";
    }

    public boolean getOptional17Check() {
        return optional17 != null && optional17.equals("1");
    }

    public void setOptional17Check(boolean optional17) {
        this.optional17 = (optional17) ? "1" : "";
    }

    public boolean getOptional18Check() {
        return optional18 != null && optional18.equals("1");
    }

    public void setOptional18Check(boolean optional18) {
        this.optional18 = (optional18) ? "1" : "";
    }

    public boolean getOptional19Check() {
        return optional19 != null && optional19.equals("1");
    }

    public void setOptional19Check(boolean optional19) {
        this.optional19 = (optional19) ? "1" : "";
    }

    public boolean getOptional20Check() {
        return optional20 != null && optional20.equals("1");
    }

    public void setOptional20Check(boolean optional20) {
        this.optional20 = (optional20) ? "1" : "";
    }

    public boolean getOptional21Check() {
        return optional21 != null && optional21.equals("1");
    }

    public void setOptional21Check(boolean optional21) {
        this.optional21 = (optional21) ? "1" : "";
    }

    public boolean getOptional22Check() {
        return optional22 != null && optional22.equals("1");
    }

    public void setOptional22Check(boolean optional22) {
        this.optional22 = (optional22) ? "1" : "";
    }

    public boolean getOptional23Check() {
        return optional23 != null && optional23.equals("1");
    }

    public void setOptional23Check(boolean optional23) {
        this.optional23 = (optional23) ? "1" : "";
    }

    public boolean getOptional24Check() {
        return optional24 != null && optional24.equals("1");
    }

    public void setOptional24Check(boolean optional24) {
        this.optional24 = (optional24) ? "1" : "";
    }

    public boolean getOptional25Check() {
        return optional25 != null && optional25.equals("1");
    }

    public void setOptional25Check(boolean optional25) {
        this.optional25 = (optional25) ? "1" : "";
    }

    public boolean getOptional26Check() {
        return optional26 != null && optional26.equals("1");
    }

    public void setOptional26Check(boolean optional26) {
        this.optional26 = (optional26) ? "1" : "";
    }

    public boolean getOptional27Check() {
        return optional27 != null && optional27.equals("1");
    }

    public void setOptional27Check(boolean optional27) {
        this.optional27 = (optional27) ? "1" : "";
    }

    public boolean getOptional28Check() {
        return optional28 != null && optional28.equals("1");
    }

    public void setOptional28Check(boolean optional28) {
        this.optional28 = (optional28) ? "1" : "";
    }

    public boolean getOptional29Check() {
        return optional29 != null && optional29.equals("1");
    }

    public void setOptional29Check(boolean optional29) {
        this.optional29 = (optional29) ? "1" : "";
    }

    public boolean getOptional30Check() {
        return optional30 != null && optional30.equals("1");
    }

    public void setOptional30Check(boolean optional30) {
        this.optional30 = (optional30) ? "1" : "";
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

//	public String getBirthday_Nengo() {
//		return birthday_Nengo;
//	}
//
//	public void setBirthday_Nengo(String birthday_Nengo) {
//		this.birthday_Nengo = birthday_Nengo;
//	}
//
	public String getBirthday_Year() {
		return birthday_Year;
	}

	public void setBirthday_Year(String birthday_Year) {
		this.birthday_Year = birthday_Year;
	}
	/**
	 * 職位コードを取得します。
	 * @return 職位コード
	 */
	public String getJobTypeCd() {
		return jobTypeCd;
	}
	/**
     * 職位コードを設定します。
     * @param jobTypeCd 職位コード
     */
	public void setJobTypeCd(String jobTypeCd) {
		this.jobTypeCd = jobTypeCd;
	}
	public String getOptional11() {
		return optional11;
	}
	public void setOptional11(String optional11) {
		this.optional11 = optional11;
	}
	public String getOptional12() {
		return optional12;
	}
	public void setOptional12(String optional12) {
		this.optional12 = optional12;
	}
	public String getOptional13() {
		return optional13;
	}
	public void setOptional13(String optional13) {
		this.optional13 = optional13;
	}
	public String getOptional14() {
		return optional14;
	}
	public void setOptional14(String optional14) {
		this.optional14 = optional14;
	}
	public String getOptional15() {
		return optional15;
	}
	public void setOptional15(String optional15) {
		this.optional15 = optional15;
	}
	public String getOptional16() {
		return optional16;
	}
	public void setOptional16(String optional16) {
		this.optional16 = optional16;
	}
	public String getOptional17() {
		return optional17;
	}
	public void setOptional17(String optional17) {
		this.optional17 = optional17;
	}
	public String getOptional18() {
		return optional18;
	}
	public void setOptional18(String optional18) {
		this.optional18 = optional18;
	}
	public String getOptional19() {
		return optional19;
	}
	public void setOptional19(String optional19) {
		this.optional19 = optional19;
	}
	public String getOptional20() {
		return optional20;
	}
	public void setOptional20(String optional20) {
		this.optional20 = optional20;
	}
	public String getOptional21() {
		return optional21;
	}
	public void setOptional21(String optional21) {
		this.optional21 = optional21;
	}
	public String getOptional22() {
		return optional22;
	}
	public void setOptional22(String optional22) {
		this.optional22 = optional22;
	}
	public String getOptional23() {
		return optional23;
	}
	public void setOptional23(String optional23) {
		this.optional23 = optional23;
	}
	public String getOptional24() {
		return optional24;
	}
	public void setOptional24(String optional24) {
		this.optional24 = optional24;
	}
	public String getOptional25() {
		return optional25;
	}
	public void setOptional25(String optional25) {
		this.optional25 = optional25;
	}
	public String getOptional26() {
		return optional26;
	}
	public void setOptional26(String optional26) {
		this.optional26 = optional26;
	}
	public String getOptional27() {
		return optional27;
	}
	public void setOptional27(String optional27) {
		this.optional27 = optional27;
	}
	public String getOptional28() {
		return optional28;
	}
	public void setOptional28(String optional28) {
		this.optional28 = optional28;
	}
	public String getOptional29() {
		return optional29;
	}
	public void setOptional29(String optional29) {
		this.optional29 = optional29;
	}
	public String getOptional30() {
		return optional30;
	}
	public void setOptional30(String optional30) {
		this.optional30 = optional30;
	}

}
