package jp.co.isid.mos.bird.bizsupport.common.entity;

public class UISeidoMst {

    public static final String TABLE = "BM76SEID";

    public static final String tourokuNo_COLUMN = "TOUROKU_NO";

    public static final String dataVer_COLUMN =  "DATA_VER";

    public static final String planName_COLUMN = "PLAN_NAME";

    public static final String g_rendoKeisu_COLUMN = "G_RENDO_KEISU";

    public static final String g_rendoKesuYakuin_COLUMN = "G_RENDO_KEISU_YAKUIN";

    public static final String g_pointDate_COLUMN = "G_POINT_DATE";

    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN =  "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN =  "LAST_USER";

    public static final String lastPgm_COLUMN =   "LAST_PGM";

    public static final String lastTmsp_COLUMN =  "LAST_TMSP";

    /**
     * 登録番号
     */
    private String tourokuNo;

    /**
     * データバージョン
     */
    private String dataVer;

    /**
     * 計画名称
     */
    private String planName;

    /**
     * 業績連動係数（社員）
     */
    private String gRendoKeisu;

    /**
     * 業績連動係数（役員）
     */
    private String gRendoKeisuYakuin;

    /**
     * 業績ポイント付与年月日
     */
    private String gPointDate;

    /**
     * 備考
     */
    private String bikou;

    /**
     * 削除フラグ
     */
    private String sakujoFlg;

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
     * 開始年度
     */
    private String startNendo;

    /**
     * 終了年度
     */
    private String endNendo;

    /**
     * 株式報酬制度計画番号
     */
    private String seidoCd;

	/**
	 * 株式報酬制度計画番号を取得します。
	 * @return seidoCd 株式報酬制度計画番号
	 */
	public String getSeidoCd() {
		this.seidoCd = getTourokuNo() + "-" + getDataVer();
		return seidoCd;
	}

	/**
	 * 開始年度を取得します。
	 * @return startNendo 開始年度
	 */
	public String getStartNendo() {
		return startNendo;
	}

	/**
	 * 開始年度を設定します。
	 * @param startNendo 開始年度
	 */
	public void setStartNendo(String startNendo) {
		this.startNendo = startNendo;
	}

	/**
	 * 終了年度を取得します。
	 * @return endNendo
	 */
	public String getEndNendo() {
		return endNendo;
	}

	/**
	 * を設定します。
	 * @param endNendo 終了年度
	 */
	public void setEndNendo(String endNendo) {
		this.endNendo = endNendo;
	}

	/**
     * 登録番号を取得します。
     * @return 登録番号
     */
	public String getTourokuNo() {
		return tourokuNo;
	}

	/**
	 * 登録番号を設定します。
	 * @param tourokuNo 登録番号
	 */
	public void setTourokuNo(String tourokuNo) {
		this.tourokuNo = tourokuNo;
	}

	/**
	 * データバージョンを取得します。
	 * @return データバージョン
	 */
	public String getDataVer() {
		return dataVer;
	}

	/**
	 * データバージョンを設定します。
	 * @param dataVer データバージョン
	 */
	public void setDataVer(String dataVer) {
		this.dataVer = dataVer;
	}

	/**
	 * 計画名称を取得します。
	 * @return 計画名称
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * 計画名称を設定します。
	 * @param planName 計画名称
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * 業績連動係数を取得します。
	 * @return 業績連動係数
	 */
	public String getgRendoKeisu() {
		return gRendoKeisu;
	}

	/**
	 * 業績連動係数を設定します。
	 * @param gRendoKeisu 業績連動係数
	 */
	public void setgRendoKeisu(String gRendoKeisu) {
		this.gRendoKeisu = gRendoKeisu;
	}

	/**
	 * 業績ポイント付与年月日を取得します。
	 * @return 業績ポイント付与年月日
	 */
	public String getgPointDate() {
		return gPointDate;
	}

	/**
	 * 業績ポイント付与年月日を設定します。
	 * @param gPointDate 業績ポイント付与年月日
	 */
	public void setgPointDate(String gPointDate) {
		this.gPointDate = gPointDate;
	}

	/**
	 * 削除フラグを取得します。
	 * @return 削除フラグ
	 */
	public String getSakujoFlg() {
		return sakujoFlg;
	}

	/**
	 * 削除フラグを設定します。
	 * @param sakujoFlg 削除フラグ
	 */
	public void setSakujoFlg(String sakujoFlg) {
		this.sakujoFlg = sakujoFlg;
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

    /**
     * 業績連動係数（役員）を取得します。
     * @return gRendoKeisuYakuin 業績連動係数（役員）
     */
	public String getgRendoKeisuYakuin() {
		return gRendoKeisuYakuin;
	}

    /**
     * 業績連動係数（役員）を設定します。
     * @param gRendoKeisuYakuin 業績連動係数（役員）
     */
	public void setgRendoKeisuYakuin(String gRendoKeisuYakuin) {
		this.gRendoKeisuYakuin = gRendoKeisuYakuin;
	}

	/**
	 * 備考を取得します。
	 * @return 備考
	 */
	public String getBikou() {
		return bikou;
	}

	/**
	 * 備考を設定します。
	 * @param bikou 備考
	 */
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}
}
