package jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity;

public class PointShuInfo {

	public static final String TABLE = "BC39PSHU";

	public static final String pointShu_COLUMN ="POINT_SHU";

	public static final String pointShuName_COLUMN ="POINT_SHU_NAME";

	public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN =  "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN =  "LAST_USER";

    public static final String lastPgm_COLUMN =   "LAST_PGM";

    public static final String lastTmsp_COLUMN =  "LAST_TMSP";

    /**
     * ƒ|ƒCƒ“ƒgí•Ê
     */
    private String pointShu;

    /**
     * ƒ|ƒCƒ“ƒgí•Ê–¼Ì
     */
    private String pointShuName;

    /**
     * “o˜^ƒ†[ƒU‚h‚c
     */
    private String firstUser;

    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    private String firstPgm;

    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    private java.sql.Timestamp firstTmsp;

    /**
     * C³ƒ†[ƒU‚h‚c
     */
    private String lastUser;

    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    private String lastPgm;

    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    private java.sql.Timestamp lastTmsp;

	/**
     * “o˜^ƒ†[ƒU‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒ†[ƒU‚h‚c
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * “o˜^ƒ†[ƒU‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param firstUser “o˜^ƒ†[ƒU‚h‚c
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param firstPgm “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }

    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public java.sql.Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğİ’è‚µ‚Ü‚·B
     * @param firstTmsp “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public void setFirstTmsp(java.sql.Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }

    /**
     * C³ƒ†[ƒU‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒ†[ƒU‚h‚c
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * C³ƒ†[ƒU‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param lastUser C³ƒ†[ƒU‚h‚c
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param lastPgm C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }

    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public java.sql.Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğİ’è‚µ‚Ü‚·B
     * @param lastTmsp C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public void setLastTmsp(java.sql.Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
	public String getPointShu() {
		return pointShu;
	}
	public void setPointShu(String pointShu) {
		this.pointShu = pointShu;
	}
	public String getPointShuName() {
		return pointShuName;
	}
	public void setPointShuName(String pointShuName) {
		this.pointShuName = pointShuName;
	}
}
