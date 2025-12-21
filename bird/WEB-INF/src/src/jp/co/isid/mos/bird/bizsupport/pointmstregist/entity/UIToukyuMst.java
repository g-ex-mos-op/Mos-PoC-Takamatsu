package jp.co.isid.mos.bird.bizsupport.pointmstregist.entity;

public class UIToukyuMst {

    public static final String TABLE = "BM78COMR";

	public static final String kb_companyCd_COLUMN ="KB_COMPANY_CD";

	public static final String kb_companyName_COLUMN ="KB_COMPANY_NAME";

	public static final String rankCd_COLUMN ="RANK_CD";

	public static final String rankName_COLUMN ="RANK_NAME";

	public static final String sortNo_COLUMN ="SORT_NO";

	public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN =  "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN =  "LAST_USER";

    public static final String lastPgm_COLUMN =   "LAST_PGM";

    public static final String lastTmsp_COLUMN =  "LAST_TMSP";

    /**
     * 株式報酬会社コード
     */
    private String kb_companyCd;

    /**
     * 株式報酬会社名称
     */
    private String kb_companyName;

    /**
     * 等級コード
     */
    private String rankCd;

    /**
     * 等級名称
     */
    private String rankName;

    /**
     * ソート順
     */
    private String sortNo;

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
	 * 株式報酬会社コードを取得します。
	 * @return 株式報酬会社コード
	 */
	public String getKb_companyCd() {
		return kb_companyCd;
	}

	/**
	 * 株式報酬会社コードを設定します。
	 * @param kb_companyCd 株式報酬会社コード
	 */
	public void setKb_companyCd(String kb_companyCd) {
		this.kb_companyCd = kb_companyCd;
	}

	/**
	 * 株式報酬会社名称を取得します。
	 * @return 株式報酬会社名称
	 */
	public String getKb_companyName() {
		return kb_companyName;
	}

	/**
	 * 株式報酬会社名称を設定します。
	 * @param kb_companyName 株式報酬会社名称
	 */
	public void setKb_companyName(String kb_companyName) {
		this.kb_companyName = kb_companyName;
	}

	/**
	 * 等級コードを取得します。
	 * @return 等級コード
	 */
	public String getRankCd() {
		return rankCd;
	}

	/**
	 * 等級コードを設定します。
	 * @param rankCd 等級コード
	 */
	public void setRankCd(String rankCd) {
		this.rankCd = rankCd;
	}

	/**
	 * 等級名称を取得します。
	 * @return 等級名称
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * 等級名称を設定します。
	 * @param rankName 等級名称
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * ソート順を取得します。
	 * @return ソート順
	 */
	public String getSortNo() {
		return sortNo;
	}

	/**
	 * ソート順を設定します。
	 * @param sortNo ソート順
	 */
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
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
