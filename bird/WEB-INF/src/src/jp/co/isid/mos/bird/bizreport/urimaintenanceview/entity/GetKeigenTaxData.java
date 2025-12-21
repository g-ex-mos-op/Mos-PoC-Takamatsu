package jp.co.isid.mos.bird.bizreport.urimaintenanceview.entity;

import java.math.BigDecimal;

public class GetKeigenTaxData {

    public static final String TABLE = "BD66ADUM";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String miseName_COLUMN = "MISE_NAME";

    public static final String closeFlg_COLUMN = "CLOSE_FLG";

    public static final String eigyoDt_COLUMN = "EIGYO_DT";

    public static final String bd65Flg_COLUMN = "BD65_FLG";

    public static final String uriage1_COLUMN = "URIAGE1";

    public static final String u01Flg_COLUMN = "U01_FLG";

    public static final String uriage2_COLUMN = "URIAGE2";

    public static final String u02Flg_COLUMN = "U02_FLG";

    public static final String uriage3_COLUMN = "URIAGE3";

    public static final String u03Flg_COLUMN = "U03_FLG";

    public static final String uriage4_COLUMN = "URIAGE4";

    public static final String u04Flg_COLUMN = "U04_FLG";

    public static final String uriage5_COLUMN = "URIAGE5";

    public static final String u05Flg_COLUMN = "U05_FLG";

    public static final String totalUriage_COLUMN = "TOTAL_URIAGE";

    public static final String tax1_COLUMN = "TAX1";

    public static final String u06Flg_COLUMN = "U06_FLG";

    public static final String tax2_COLUMN = "TAX2";

    public static final String u07Flg_COLUMN = "U07_FLG";

    public static final String tax3_COLUMN = "TAX3";

    public static final String u08Flg_COLUMN = "U08_FLG";

    public static final String tax4_COLUMN = "TAX4";

    public static final String u09Flg_COLUMN = "U09_FLG";

    public static final String tax5_COLUMN = "TAX5";

    public static final String u10Flg_COLUMN = "U10_FLG";

    public static final String totalTax_COLUMN = "TOTAL_TAX";


    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseName;

    /**
     * 営業日
     */
    private String eigyoDt;

    /**
     * 閉店フラグ(0:CLOSE店でない、1:CLOSE店)
     */
    private int closeFlg;

    /**
     * データからBD65フラグ(0:いいえ、1:はい)
     */
    private int bd65Flg;

    /**
     * 売上_通常税率対象
     */
    private BigDecimal uriage1;

    /**
     * 修正フラグ(0:修正でない、1:修正)
     */
    private int u01Flg = 0;

    /**
     * 売上_軽減税率対象
     */
    private BigDecimal uriage2;

    private int u02Flg = 0;

    /**
     * 売上_予備3
     */
    private BigDecimal uriage3;

    private int u03Flg = 0;

    /**
     * 売上_予備4
     */
    private BigDecimal uriage4;

    private int u04Flg = 0;

    /**
     * 売上_予備5
     */
    private BigDecimal uriage5;

    private int u05Flg = 0;

    /**
     * 売上_合計
     */
    private BigDecimal totalUriage;

    /**
     * 消費税_通常税率対象
     */
    private BigDecimal tax1;

    private int u06Flg = 0;

    /**
     * 消費税_軽減税率対象
     */
    private BigDecimal tax2;

    private int u07Flg = 0;

    /**
     * 消費税_予備3
     */
    private BigDecimal tax3;

    private int u08Flg = 0;

    /**
     * 消費税_予備4
     */
    private BigDecimal tax4;

    private int u09Flg = 0;

    /**
     * 消費税_予備5
     */
    private BigDecimal tax5;

    private int u10Flg = 0;

    /**
     * 消費税_合計
     */
    private BigDecimal totalTax;


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
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店名称を設定します。
     * @param miseName 店名称
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }

	/**
	 * miseNameKjを設定します。
	 * @param miseNameKj
	 */
	public void setMiseNameKj(String miseName) {
		this.miseName = miseName;
	}

	/**
	 * eigyoDtを取得します。
	 * @return eigyoDt
	 */
	public String getEigyoDt() {
		return eigyoDt;
	}

	/**
	 * eigyoDtを設定します。
	 * @param eigyoDt
	 */
	public void setEigyoDt(String eigyoDt) {
		this.eigyoDt = eigyoDt;
	}

	/**
	 * uriage1を取得します。
	 * @return uriage1
	 */
	public BigDecimal getUriage1() {
		return uriage1;
	}

	/**
	 * uriage1を設定します。
	 * @param uriage1
	 */
	public void setUriage1(BigDecimal uriage1) {
		this.uriage1 = uriage1;
	}

	/**
	 * uriage2を取得します。
	 * @return uriage2
	 */
	public BigDecimal getUriage2() {
		return uriage2;
	}

	/**
	 * uriage2を設定します。
	 * @param uriage2
	 */
	public void setUriage2(BigDecimal uriage2) {
		this.uriage2 = uriage2;
	}

	/**
	 * uriage3を取得します。
	 * @return uriage3
	 */
	public BigDecimal getUriage3() {
		return uriage3;
	}

	/**
	 * uriage3を設定します。
	 * @param uriage3
	 */
	public void setUriage3(BigDecimal uriage3) {
		this.uriage3 = uriage3;
	}

	/**
	 * uriage4を取得します。
	 * @return uriage4
	 */
	public BigDecimal getUriage4() {
		return uriage4;
	}

	/**
	 * uriage4を設定します。
	 * @param uriage4
	 */
	public void setUriage4(BigDecimal uriage4) {
		this.uriage4 = uriage4;
	}

	/**
	 * uriage5を取得します。
	 * @return uriage5
	 */
	public BigDecimal getUriage5() {
		return uriage5;
	}

	/**
	 * uriage5を設定します。
	 * @param uriage5
	 */
	public void setUriage5(BigDecimal uriage5) {
		this.uriage5 = uriage5;
	}

	/**
	 * totalUriageを取得します。
	 * @return totalUriage
	 */
	public BigDecimal getTotalUriage() {
		return totalUriage;
	}

	/**
	 * totalUriageを設定します。
	 * @param totalUriage
	 */
	public void setTotalUriage(BigDecimal totalUriage) {
		this.totalUriage = totalUriage;
	}

	/**
	 * tax1を取得します。
	 * @return tax1
	 */
	public BigDecimal getTax1() {
		return tax1;
	}

	/**
	 * tax1を設定します。
	 * @param tax1
	 */
	public void setTax1(BigDecimal tax1) {
		this.tax1 = tax1;
	}

	/**
	 * tax2を取得します。
	 * @return tax2
	 */
	public BigDecimal getTax2() {
		return tax2;
	}

	/**
	 * tax2を設定します。
	 * @param tax2
	 */
	public void setTax2(BigDecimal tax2) {
		this.tax2 = tax2;
	}

	/**
	 * tax3を取得します。
	 * @return tax3
	 */
	public BigDecimal getTax3() {
		return tax3;
	}

	/**
	 * tax3を設定します。
	 * @param tax3
	 */
	public void setTax3(BigDecimal tax3) {
		this.tax3 = tax3;
	}

	/**
	 * tax4を取得します。
	 * @return tax4
	 */
	public BigDecimal getTax4() {
		return tax4;
	}

	/**
	 * tax4を設定します。
	 * @param tax4
	 */
	public void setTax4(BigDecimal tax4) {
		this.tax4 = tax4;
	}

	/**
	 * tax5を取得します。
	 * @return tax5
	 */
	public BigDecimal getTax5() {
		return tax5;
	}

	/**
	 * tax5を設定します。
	 * @param tax5
	 */
	public void setTax5(BigDecimal tax5) {
		this.tax5 = tax5;
	}

	/**
	 * totalTaxを取得します。
	 * @return totalTax
	 */
	public BigDecimal getTotalTax() {
		return totalTax;
	}

	/**
	 * totalTaxを設定します。
	 * @param totalTax
	 */
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

    /**
     * [店コード]＋[店名称]＋[(CLOSE)]を取得します。
     * 店がCLOSE店の場合のみCLOSEを表示します。
     */
    public String getMiseCdNameClose(){
        String tmp = "";
        if(miseCd != null && miseName != null){
            tmp = miseCd + " " + miseName;
        }else if(miseCd != null){
            tmp = miseCd + " ";
        }

        //CLOSE店の時
        if(closeFlg == 1){
            tmp = tmp + "(CLOSE)";
        }
        return tmp;
    }
	/**
	 * bd65Flgを取得します。
	 * @return bd65Flg
	 */
	public int getBd65Flg() {
		return bd65Flg;
	}
	/**
	 * bd65Flgを設定します。
	 * @param bd65Flg
	 */
	public void setBd65Flg(int bd65Flg) {
		this.bd65Flg = bd65Flg;
	}
	/**
	 * u01Flgを取得します。
	 * @return u01Flg
	 */
	public int getU01Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u01Flg;
	}
	/**
	 * u01Flgを設定します。
	 * @param u01Flg
	 */
	public void setU01Flg(int u01Flg) {
		this.u01Flg = u01Flg;
	}
	/**
	 * u02Flgを取得します。
	 * @return u02Flg
	 */
	public int getU02Flg() {
		if(bd65Flg ==  1) {
			return 0;
		}
		return u02Flg;
	}
	/**
	 * u02Flgを設定します。
	 * @param u02Flg
	 */
	public void setU02Flg(int u02Flg) {
		this.u02Flg = u02Flg;
	}
	/**
	 * u03Flgを取得します。
	 * @return u03Flg
	 */
	public int getU03Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u03Flg;
	}
	/**
	 * u03Flgを設定します。
	 * @param u03Flg
	 */
	public void setU03Flg(int u03Flg) {
		this.u03Flg = u03Flg;
	}
	/**
	 * u04Flgを取得します。
	 * @return u04Flg
	 */
	public int getU04Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u04Flg;
	}
	/**
	 * u04Flgを設定します。
	 * @param u04Flg
	 */
	public void setU04Flg(int u04Flg) {
		this.u04Flg = u04Flg;
	}
	/**
	 * u05Flgを取得します。
	 * @return u05Flg
	 */
	public int getU05Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u05Flg;
	}
	/**
	 * u05Flgを設定します。
	 * @param u05Flg
	 */
	public void setU05Flg(int u05Flg) {
		this.u05Flg = u05Flg;
	}
	/**
	 * u06Flgを取得します。
	 * @return u06Flg
	 */
	public int getU06Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u06Flg;
	}
	/**
	 * u06Flgを設定します。
	 * @param u06Flg
	 */
	public void setU06Flg(int u06Flg) {
		this.u06Flg = u06Flg;
	}
	/**
	 * u07Flgを取得します。
	 * @return u07Flg
	 */
	public int getU07Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u07Flg;
	}
	/**
	 * u07Flgを設定します。
	 * @param u07Flg
	 */
	public void setU07Flg(int u07Flg) {
		this.u07Flg = u07Flg;
	}
	/**
	 * u08Flgを取得します。
	 * @return u08Flg
	 */
	public int getU08Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u08Flg;
	}
	/**
	 * u08Flgを設定します。
	 * @param u08Flg
	 */
	public void setU08Flg(int u08Flg) {
		this.u08Flg = u08Flg;
	}
	/**
	 * u09Flgを取得します。
	 * @return u09Flg
	 */
	public int getU09Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u09Flg;
	}
	/**
	 * u09Flgを設定します。
	 * @param u09Flg
	 */
	public void setU09Flg(int u09Flg) {
		this.u09Flg = u09Flg;
	}
	/**
	 * u10Flgを取得します。
	 * @return u10Flg
	 */
	public int getU10Flg() {
		if(bd65Flg == 1) {
			return 0;
		}
		return u10Flg;
	}
	/**
	 * u10Flgを設定します。
	 * @param u10Flg
	 */
	public void setU10Flg(int u10Flg) {
		this.u10Flg = u10Flg;
	}
}
