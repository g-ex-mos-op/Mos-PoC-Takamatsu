package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GetKeigenTaxData {

    public static final String TABLE = "BD66ADUM";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String eigyoDt_COLUMN = "EIGYO_DT";

    public static final String upFlg_COLUMN = "UP_FLG";

    public static final String upDt_COLUMN = "UP_DT";

    public static final String batupDt_COLUMN = "BATUP_DT";

    public static final String upNo97_COLUMN = "UP_NO";

    public static final String uriage1_COLUMN = "U01_URIAGE1";

    public static final String uriage2_COLUMN = "U02_URIAGE2";

    public static final String uriage3_COLUMN = "U03_URIAGE3";

    public static final String uriage4_COLUMN = "U04_URIAGE4";

    public static final String uriage5_COLUMN = "U05_URIAGE5";

    public static final String tax1_COLUMN = "U06_TAX1";

    public static final String tax2_COLUMN = "U07_TAX2";

    public static final String tax3_COLUMN = "U08_TAX3";

    public static final String tax4_COLUMN = "U09_TAX4";

    public static final String tax5_COLUMN = "U10_TAX5";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /**
     * 企業コード
     */
    private String companyCd;
    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 営業日
     */
    private String eigyoDt;

    /**
     * バッチ更新フラグ
     */
    private String upFlg;

    /**
     * 更新日付
     */
    private String upDt;

    /**
     * バッチ更新日付
     */
    private String batupDt;

    /**
     * 更新項目NO(BT97ADUP)
     */
    private String upNo;

    /**
     * 売上_通常税率対象
     */
    private BigDecimal uriage1;

    /**
     * 売上_軽減税率対象
     */
    private BigDecimal uriage2;

    /**
     * 売上_予備3
     */
    private BigDecimal uriage3;

    /**
     * 売上_予備4
     */
    private BigDecimal uriage4;

    /**
     * 売上_予備5
     */
    private BigDecimal uriage5;

    /**
     * 消費税_通常税率対象
     */
    private BigDecimal tax1;

    /**
     * 消費税_軽減税率対象
     */
    private BigDecimal tax2;

    /**
     * 消費税_予備3
     */
    private BigDecimal tax3;

    /**
     * 消費税_予備4
     */
    private BigDecimal tax4;

    /**
     * 消費税_予備5
     */
    private BigDecimal tax5;

    /**
     * 登録ユーザＩＤ
     */
    private String  firstUser;

    /**
     * 登録プログラムＩＤ
     */
    private String  firstPgm;

    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;

    /**
     * 修正ユーザＩＤ
     */
    private String  lastUser;

    /**
     * 修正プログラムＩＤ
     */
    private String  lastPgm;

    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;

    /**
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
		return companyCd;
	}

    /**
     * @param companyCd を設定します。
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
     * @param miseCd を設定します。
     */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

    /**
     * @return eigyoDt を戻します。
     */
	public String getEigyoDt() {
		return eigyoDt;
	}

    /**
     * @param eigyoDt を設定します。
     */
	public void setEigyoDt(String eigyoDt) {
		this.eigyoDt = eigyoDt;
	}

    /**
     * バッチ更新フラグを取得します。
     * @return バッチ更新フラグ
     */
    public String getUpFlg() {
        return upFlg;
    }
    /**
     * バッチ更新フラグを設定します。
     * @param upFlg バッチ更新フラグ
     */
    public void setUpFlg(String upFlg) {
        this.upFlg = upFlg;
    }

    /**
     * 更新日付を取得します。
     * @return 更新日付
     */
    public String getUpDt() {
        return upDt;
    }
    /**
     * 更新日付を設定します。
     * @param upDt 更新日付
     */
    public void setUpDt(String upDt) {
        this.upDt = upDt;
    }

    /**
     * バッチ更新日付を取得します。
     * @return バッチ更新日付
     */
    public String getBatupDt() {
        return batupDt;
    }
    /**
     * バッチ更新日付を設定します。
     * @param batupDt バッチ更新日付
     */
    public void setBatupDt(String batupDt) {
        this.batupDt = batupDt;
    }

    /**
     * 更新項目NOを取得します。
     * @return 更新項目NO
     */
    public String getUpNo() {
        return upNo;
    }
    /**
     * 更新項目NO()を設定します。
     * @param upNo 更新項目NO
     */
    public void setUpNo(String upNo) {
        this.upNo = upNo;
    }

    /**
     * @return uriage1 を戻します。
     */
	public BigDecimal getUriage1() {
		return uriage1;
	}

    /**
     * @param uriage1 を設定します。
     */
	public void setUriage1(BigDecimal uriage1) {
		this.uriage1 = uriage1;
	}

    /**
     * @return uriage2 を戻します。
     */
	public BigDecimal getUriage2() {
		return uriage2;
	}

    /**
     * @param uriage2 を設定します。
     */
	public void setUriage2(BigDecimal uriage2) {
		this.uriage2 = uriage2;
	}

    /**
     * @return uriage3 を戻します。
     */
	public BigDecimal getUriage3() {
		return uriage3;
	}

    /**
     * @param uriage3 を設定します。
     */
	public void setUriage3(BigDecimal uriage3) {
		this.uriage3 = uriage3;
	}

    /**
     * @return uriage4 を戻します。
     */
	public BigDecimal getUriage4() {
		return uriage4;
	}

    /**
     * @param uriage4 を設定します。
     */
	public void setUriage4(BigDecimal uriage4) {
		this.uriage4 = uriage4;
	}

    /**
     * @return uriage5 を戻します。
     */
	public BigDecimal getUriage5() {
		return uriage5;
	}

    /**
     * @param uriage5 を設定します。
     */
	public void setUriage5(BigDecimal uriage5) {
		this.uriage5 = uriage5;
	}

    /**
     * @return tax1 を戻します。
     */
	public BigDecimal getTax1() {
		return tax1;
	}

    /**
     * @param tax1 を設定します。
     */
	public void setTax1(BigDecimal tax1) {
		this.tax1 = tax1;
	}

    /**
     * @return tax2 を戻します。
     */
	public BigDecimal getTax2() {
		return tax2;
	}

    /**
     * @param tax2 を設定します。
     */
	public void setTax2(BigDecimal tax2) {
		this.tax2 = tax2;
	}

    /**
     * @return tax3 を戻します。
     */
	public BigDecimal getTax3() {
		return tax3;
	}

    /**
     * @param tax3 を設定します。
     */
	public void setTax3(BigDecimal tax3) {
		this.tax3 = tax3;
	}

    /**
     * @return tax4 を戻します。
     */
	public BigDecimal getTax4() {
		return tax4;
	}

    /**
     * @param tax4 を設定します。
     */
	public void setTax4(BigDecimal tax4) {
		this.tax4 = tax4;
	}

    /**
     * @return tax5 を戻します。
     */
	public BigDecimal getTax5() {
		return tax5;
	}

    /**
     * @param tax5 を設定します。
     */
	public void setTax5(BigDecimal tax5) {
		this.tax5 = tax5;
	}
    /**
     * 登録ユーザＩＤを取得します。
     * @return 登録ユーザＩＤ
     */
    public String  getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザＩＤを設定します。
     * @param firstUser 登録ユーザＩＤ
     */
    public void setFirstUser(String  firstUser) {
        this.firstUser = firstUser;
    }

    /**
     * 登録プログラムＩＤを取得します。
     * @return 登録プログラムＩＤ
     */
    public String  getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムＩＤを設定します。
     * @param firstPgm 登録プログラムＩＤ
     */
    public void setFirstPgm(String  firstPgm) {
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
     * 修正ユーザＩＤを取得します。
     * @return 修正ユーザＩＤ
     */
    public String  getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザＩＤを設定します。
     * @param lastUser 修正ユーザＩＤ
     */
    public void setLastUser(String  lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * 修正プログラムＩＤを取得します。
     * @return 修正プログラムＩＤ
     */
    public String  getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムＩＤを設定します。
     * @param lastPgm 修正プログラムＩＤ
     */
    public void setLastPgm(String  lastPgm) {
        this.lastPgm = lastPgm;
    }

    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

}
