package jp.co.isid.mos.bird.storeinfo.miseref.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstSB {
    
    public static final String TABLE = "BM20SCBD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String sbKaisu_COLUMN = "SB_KAISU";
    
    public static final String sbDate_COLUMN = "SB_DATE";
    
    public static final String oldMiseCd_COLUMN = "OLD_MISE_CD";
    
    public static final String oldMiseName_COLUMN = "OLD_MISE_NAME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * スクラップビルド回数
     */
    private BigDecimal sbKaisu;
    
    /**
     * スクラップビルド日
     */
    private String sbDate;
    
    /**
     * 旧店舗コード
     */
    private String oldMiseCd;
    
    /**
     * 旧店舗名称
     */
    private String oldMiseName;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 行削除判別フラグ
     */
    private boolean delFlg;

    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * スクラップビルド回数を取得します。
     * @return スクラップビルド回数
     */
    public BigDecimal getSbKaisu() {
        return sbKaisu;
    }
    /**
     * スクラップビルド回数を設定します。
     * @param sbKaisu スクラップビルド回数
     */
    public void setSbKaisu(BigDecimal sbKaisu) {
        this.sbKaisu = sbKaisu;
    }
    
    /**
     * スクラップビルド日を取得します。
     * @return スクラップビルド日
     */
    public String getSbDate() {
        return sbDate;
    }
    /**
     * スクラップビルド日を設定します。
     * @param sbDate スクラップビルド日
     */
    public void setSbDate(String sbDate) {
        this.sbDate = sbDate;
    }
    
    /**
     * 旧店舗コードを取得します。
     * @return 旧店舗コード
     */
    public String getOldMiseCd() {
        return oldMiseCd;
    }
    /**
     * 旧店舗コードを設定します。
     * @param oldMiseCd 旧店舗コード
     */
    public void setOldMiseCd(String oldMiseCd) {
        this.oldMiseCd = oldMiseCd;
    }
    
    /**
     * 旧店舗名称を取得します。
     * @return 旧店舗名称
     */
    public String getOldMiseName() {
        return oldMiseName;
    }
    /**
     * 旧店舗名称を設定します。
     * @param oldMiseName 旧店舗名称
     */
    public void setOldMiseName(String oldMiseName) {
        this.oldMiseName = oldMiseName;
    }
    
    /**
     * @return delFlg を戻します。
     */
    public boolean isDelFlg() {
        return delFlg;
    }
    /**
     * @param delFlg delFlg を設定。
     */
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
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
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
}