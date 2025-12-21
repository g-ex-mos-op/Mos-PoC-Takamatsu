package jp.co.isid.mos.bird.entry.mlviewlist.entity;

import java.sql.Timestamp;

public class UIExamNoCounter {

    public static final String TABLE = "BR21MLCT";
    
    public static final String cntKbn_COLUMN = "CNT_KBN";

    public static final String noCounter_COLUMN = "NO_COUNTER";

    public static final String cntShokai_COLUMN = "CNT_SHOKAI";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /**
     * 受験番号カウンタ
     */
    private String cntKbn;

    /**
     * 受験番号カウンタ
     */
    private String noCounter;
    
    /**
     * 受験番号カウンタ初期値
     */
    private String cntShokai;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プルグラム
     */
    private String lastPgm;
    
    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;

    public String getCntShokai() {
        return cntShokai;
    }

    public void setCntShokai(String cntShokai) {
        this.cntShokai = cntShokai;
    }

    public String getLastPgm() {
        return lastPgm;
    }

    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }

    public Timestamp getLastTmsp() {
        return lastTmsp;
    }

    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getNoCounter() {
        return noCounter;
    }

    public void setNoCounter(String noCounter) {
        this.noCounter = noCounter;
    }

    public String getCntKbn() {
        return cntKbn;
    }

    public void setCntKbn(String cntKbn) {
        this.cntKbn = cntKbn;
    }
    
    
}
