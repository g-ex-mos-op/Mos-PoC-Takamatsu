package jp.co.isid.mos.bird.bizsupport.pointmstregist.entity;

import java.io.Serializable;

public class UIPointMst  implements Serializable{

    public static final String TABLE = "BM77HUYP";

	public static final String tourokuNo_COLUMN ="TOUROKU_NO";

	public static final String tourokuSeq_COLUMN ="TOUROKU_SEQ";

	public static final String dataVer_COLUMN ="DATA_VER";

	public static final String nendo_COLUMN ="NENDO";

	public static final String kbCompanyCd_COLUMN ="KB_COMPANY_CD";

	public static final String kbCompanyName_COLUMN ="KB_COMPANY_NAME";

	public static final String rankCd_COLUMN ="RANK_CD";

	public static final String rankName_COLUMN ="RANK_NAME";

	public static final String kihonPoint_COLUMN ="KIHON_POINT";

	public static final String sakujoFlg_COLUMN ="SAKUJO_FLG";

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
     * 登録番号連番
     */
    private String tourokuSeq;

    /**
     * データバージョン
     */
    private String dataVer;

    /**
     * 対象年度
     */
    private String nendo;

    /**
     * 株式報酬会社コード
     */
    private String kbCompanyCd;

    /**
     * 株式報酬会社名称
     */
    private String kbCompanyName;

    /**
     * 等級コード
     */
    private String rankCd;

    /**
     * 等級名称
     */
    private String rankName;

    /**
     * 基本ポイント
     */
    private String kihonPoint;

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
     * 分割フラグ
     */
    private boolean splitFlag;

	/**
	 * 分割フラグを取得します。
	 * @return splitFlag 分割フラグ
	 */
	public boolean isSplitFlag() {
		return splitFlag;
	}

	/**
	 * 分割フラグを設定します。
	 * @param splitFlag 分割フラグ
	 */
	public void setSplitFlag(boolean splitFlag) {
		this.splitFlag = splitFlag;
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
	 * 登録番号連番を取得します。
	 * @return 登録番号連番
	 */
	public String getTourokuSeq() {
		return tourokuSeq;
	}

	/**
	 * 登録番号連番を設定します。
	 * @param tourokuSeq 登録番号連番
	 */
	public void setTourokuSeq(String tourokuSeq) {
		this.tourokuSeq = tourokuSeq;
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
	 * 対象年度を取得します。
	 * @return 対象年度
	 */
	public String getNendo() {
		return nendo;
	}

	/**
	 * 対象年度を設定します。
	 * @param nendo 対象年度
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

	/**
	 * 株式報酬会社コードを取得します。
	 * @return 株式報酬会社コード
	 */
	public String getKbCompanyCd() {
		return kbCompanyCd;
	}

	/**
	 * 株式報酬会社コードを設定します。
	 * @param kb_companyCd 株式報酬会社コード
	 */
	public void setKbCompanyCd(String kbCompanyCd) {
		this.kbCompanyCd = kbCompanyCd;
	}

	/**
	 * 株式報酬会社名称を取得します。
	 * @return 株式報酬会社名称
	 */
	public String getKbCompanyName() {
		return kbCompanyName;
	}

	/**
	 * 株式報酬会社名称を設定します。
	 * @param kb_companyName 株式報酬会社名称
	 */
	public void setKbCompanyName(String kbCompanyName) {
		this.kbCompanyName = kbCompanyName;
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
	 * 基本ポイントを取得します。
	 * @return 基本ポイント
	 */
	public String getKihonPoint() {
		return kihonPoint;
	}

	/**
	 * 基本ポイントを設定します。
	 * @param kihonPoint 基本ポイント
	 */
	public void setKihonPoint(String kihonPoint) {
		this.kihonPoint = kihonPoint;
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
}
