package jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity;

import java.math.BigDecimal;

public class UIRetirePointInfo {

	public static final String TABLE = "BD61TSES";

	public static final String nendo_COLUMN ="NENDO";

	public static final String userId_COLUMN ="USER_ID";

	public static final String pointRuikei_COLUMN ="POINT_RUIKEI";

	public static final String kbCompanyCd_COLUMN ="KB_COMPANY_CD";

	public static final String nyusyaDt_COLUMN ="NYUSYA_DT";

	public static final String taishokuDt_COLUMN ="TAISHOKU_DT";

	public static final String kinzokuYear_COLUMN ="KINZOKU_YEAR";

	public static final String retireCd_COLUMN ="RETIRE_CD";

	public static final String sikyuRate_COLUMN ="SIKYU_RATE";

	public static final String tSeisanPoint_COLUMN ="T_SEISAN_POINT";

	public static final String bikou_COLUMN ="BIKOU";

	public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN =  "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN =  "LAST_USER";

    public static final String lastPgm_COLUMN =   "LAST_PGM";

    public static final String lastTmsp_COLUMN =  "LAST_TMSP";

    /**
     * 年度
     */
    private String nendo;

    /**
     * 社員番号
     */
    private String userId;

    /**
     * 累計ポイント
     */
    private BigDecimal pointRuikei;

    /**
     * 株式報酬会社コード
     */
    private String kbCompanyCd;

    /**
     * 入社日
     */
    private String nyusyaDt;

    /**
     * 退職日
     */
    private String taishokuDt;

    /**
     * 勤続年数
     */
    private BigDecimal kinzokuYear;

    /**
     * 退職事由コード
     */
    private String retireCd;

    /**
     * 支給率
     */
    private BigDecimal sikyuRate;

    /**
     * 退職精算ポイント
     */
    private BigDecimal tSeisanPoint;

	/**
     * 備考
     */
    private String bikou;

    /**
     * 登録ユーザＩＤ
     */
    private String firstUser;

    /**
     * 登録プログラムＩＤ
     */
    private String firstPgm;

    /**
     * 登録タイムスタンプ
     */
    private java.sql.Timestamp firstTmsp;

    /**
     * 修正ユーザＩＤ
     */
    private String lastUser;

    /**
     * 修正プログラムＩＤ
     */
    private String lastPgm;

    /**
     * 修正タイムスタンプ
     */
    private java.sql.Timestamp lastTmsp;


	/**
	 * @return nendo
	 */
	public String getNendo() {
		return nendo;
	}

	/**
	 * @param nendo セットする nendo
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return pointRuikei
	 */
	public BigDecimal getPointRuikei() {
		return pointRuikei;
	}

	/**
	 * @param pointRuikei セットする pointRuikei
	 */
	public void setPointRuikei(BigDecimal pointRuikei) {
		this.pointRuikei = pointRuikei;
	}

	/**
	 * @return kbCompanyCd
	 */
	public String getKbCompanyCd() {
		return kbCompanyCd;
	}

	/**
	 * @param kbCompanyCd セットする kbCompanyCd
	 */
	public void setKbCompanyCd(String kbCompanyCd) {
		this.kbCompanyCd = kbCompanyCd;
	}

	/**
	 * @return nyusyaDt
	 */
	public String getNyusyaDt() {
		return nyusyaDt;
	}

	/**
	 * @param nyusyaDt セットする nyusyaDt
	 */
	public void setNyusyaDt(String nyusyaDt) {
		this.nyusyaDt = nyusyaDt;
	}

	/**
	 * @return taishokuDt
	 */
	public String getTaishokuDt() {
		return taishokuDt;
	}

	/**
	 * @param taishokuDt セットする taishokuDt
	 */
	public void setTaishokuDt(String taishokuDt) {
		this.taishokuDt = taishokuDt;
	}

	/**
	 * @return kinzokuYear
	 */
	public BigDecimal getKinzokuYear() {
		return kinzokuYear;
	}

	/**
	 * @param kinzokuYear セットする kinzokuYear
	 */
	public void setKinzokuYear(BigDecimal kinzokuYear) {
		this.kinzokuYear = kinzokuYear;
	}

	/**
	 * @return retireCd
	 */
	public String getRetireCd() {
		return retireCd;
	}

	/**
	 * @param retireCd セットする retireCd
	 */
	public void setRetireCd(String retireCd) {
		this.retireCd = retireCd;
	}

	/**
	 * @return sikyuRate
	 */
	public BigDecimal getSikyuRate() {
		return sikyuRate;
	}

	/**
	 * @param sikyuRate セットする sikyuRate
	 */
	public void setSikyuRate(BigDecimal sikyuRate) {
		this.sikyuRate = sikyuRate;
	}

    /**
	 * @return tSeisanPoint
	 */
	public BigDecimal gettSeisanPoint() {
		return tSeisanPoint;
	}

	/**
	 * @param tSeisanPoint セットする tSeisanPoint
	 */
	public void settSeisanPoint(BigDecimal tSeisanPoint) {
		this.tSeisanPoint = tSeisanPoint;
	}

	/**
	 * @return bikou
	 */
	public String getBikou() {
		return bikou;
	}

	/**
	 * @param bikou セットする bikou
	 */
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}
	/**
     * 登録ユーザＩＤを取得します。
     * @return 登録ユーザＩＤ
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザＩＤを設定します。
     * @param firstUser 登録ユーザＩＤ
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    /**
     * 登録プログラムＩＤを取得します。
     * @return 登録プログラムＩＤ
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムＩＤを設定します。
     * @param firstPgm 登録プログラムＩＤ
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }

    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public java.sql.Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(java.sql.Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }

    /**
     * 修正ユーザＩＤを取得します。
     * @return 修正ユーザＩＤ
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザＩＤを設定します。
     * @param lastUser 修正ユーザＩＤ
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * 修正プログラムＩＤを取得します。
     * @return 修正プログラムＩＤ
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムＩＤを設定します。
     * @param lastPgm 修正プログラムＩＤ
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }

    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public java.sql.Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(java.sql.Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
}
