package jp.co.isid.mos.bird.bizadmin.svtantousibu.entity;

import java.sql.Timestamp;

public class MstTantouMise {
    
    public static final String TABLE = "BM50TANM";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
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
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * ユーザー名称
     */
    private String userNameKj;
    
    /**
     * 登録ユーザ
     */
    private String firstUser;
    
    /**
     * 登録PGM
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 最終変更ユーザ
     */
    private String lastUser;
    
    /**
     * 最終変更PGM
     */
    private String lastPgm;
    
    /**
     * 最終変更タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return convertNullToBlank(companyCd);
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
        return convertNullToBlank(miseCd);
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
    public String getMiseNameKj() {
        return convertNullToBlank(miseNameKj);
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convertNullToBlank(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convertNullToBlank(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return convertNullToBlank(blockCd);
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return convertNullToBlank(blockName);
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    /**
     * SVコードを取得します。
     * @return SVコード
     */
    public String getSvCd() {
        return convertNullToBlank(svCd);
    }
    /**
     * SVコードを設定します。
     * @param svCd SVコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * ユーザー名称を取得します。
     * @return ユーザー名称
     */
    public String getUserNameKj() {
        return convertNullToBlank(userNameKj);
    }
    /**
     * ユーザー名称を設定します。
     * @param userNameKj ユーザー名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * 登録ユーザを取得します。
     * @return 登録ユーザ
     */
    public String getFirstUser() {
        return convertNullToBlank(firstUser);
    }
    /**
     * 登録ユーザを設定します。
     * @param firstUser 登録ユーザ
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録PGMを取得します。
     * @return 登録PGM
     */
    public String getFirstPgm() {
        return convertNullToBlank(firstPgm);
    }
    /**
     * 登録PGMを設定します。
     * @param firstPgm 登録PGM
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
     * 最終変更ユーザを取得します。
     * @return 最終変更ユーザ
     */
    public String getLastUser() {
        return convertNullToBlank(lastUser);
    }
    /**
     * 最終変更ユーザを設定します。
     * @param lastUser 最終変更ユーザ
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 最終変更PGMを取得します。
     * @return 最終変更PGM
     */
    public String getLastPgm() {
        return convertNullToBlank(lastPgm);
    }
    /**
     * 最終変更PGMを設定します。
     * @param lastPgm 最終変更PGM
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 最終変更タイムスタンプを取得します。
     * @return 最終変更タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 最終変更タイムスタンプを設定します。
     * @param lastTmsp 最終変更タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * outter joinで取得した項目のNull対処
     */
    private String convertNullToBlank(String value) {
        return value == null ? "" : value.trim();
    }
    
    /**
     * クローズ日を取得します。
     * @return クローズ日
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * クローズ日を設定します。
     * @param closeDt クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
}