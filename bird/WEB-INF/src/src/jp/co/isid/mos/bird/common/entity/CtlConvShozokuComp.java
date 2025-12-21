package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

/**
 * 所属コード会社変換
 * @author xnkusama
 *
 */
public class CtlConvShozokuComp {
    
    public static final String TABLE = "BR29BMCP";
    
    public static final String bumonCdStfF_COLUMN = "BUMON_CD_STF_F";
    
    public static final String bumonCdStfT_COLUMN = "BUMON_CD_STF_T";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";
    
    public static final String zokuseiKbn_COLUMN = "ZOKUSEI_KBN";
    
    public static final String description_COLUMN = "DESCRIPTION";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 所属コードFROM
     */
    private String bumonCdStfF;
    
    /**
     * 所属コードTO
     */
    private String bumonCdStfT;
    
    /**
     * 企業コード
     */
    private String rCompanyCd;
    
    /**
     * 属性区分
     */
    private String zokuseiKbn;
    
    /**
     * 説明
     */
    private String description;
    
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
     * 所属コードFROMを取得します。
     * @return 所属コードFROM
     */
    public String getBumonCdStfF() {
        return bumonCdStfF;
    }
    /**
     * 所属コードFROMを設定します。
     * @param bumonCdStfF 所属コードFROM
     */
    public void setBumonCdStfF(String bumonCdStfF) {
        this.bumonCdStfF = bumonCdStfF;
    }
    
    /**
     * 所属コードTOを取得します。
     * @return 所属コードTO
     */
    public String getBumonCdStfT() {
        return bumonCdStfT;
    }
    /**
     * 所属コードTOを設定します。
     * @param bumonCdStfT 所属コードTO
     */
    public void setBumonCdStfT(String bumonCdStfT) {
        this.bumonCdStfT = bumonCdStfT;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
    /**
     * 属性区分を取得します。
     * @return 属性区分
     */
    public String getZokuseiKbn() {
        return zokuseiKbn;
    }
    /**
     * 属性区分を設定します。
     * @param zokuseiKbn 属性区分
     */
    public void setZokuseiKbn(String zokuseiKbn) {
        this.zokuseiKbn = zokuseiKbn;
    }
    
    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getDescription() {
        return description;
    }
    /**
     * 説明を設定します。
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
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
    
}
