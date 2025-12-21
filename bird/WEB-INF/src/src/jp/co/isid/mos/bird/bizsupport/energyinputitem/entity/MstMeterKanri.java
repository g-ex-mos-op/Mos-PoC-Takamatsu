package jp.co.isid.mos.bird.bizsupport.energyinputitem.entity;

import java.sql.Timestamp;

public class MstMeterKanri {
    
    public static final String TABLE = "BM68MSET";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String meterKbn_COLUMN = "METER_KBN";
    
    public static final String electricFlg_COLUMN = "ELECTRIC_FLG";
    
    public static final String powerFlg_COLUMN = "POWER_FLG";
    
    public static final String gasFlg_COLUMN = "GAS_FLG";
    
    public static final String waterFlg_COLUMN = "WATER_FLG";
    
    public static final String electricFlgSave_COLUMN = "ELECTRIC_FLG_SAVE";
    
    public static final String powerFlgSave_COLUMN = "POWER_FLG_SAVE";
    
    public static final String gasFlgSave_COLUMN = "GAS_FLG_SAVE";
    
    public static final String waterFlgSave_COLUMN = "WATER_FLG_SAVE";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String updateFlg_COLUMN = "UPDATE_FLG";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String electricFlgDisp_COLUMN = "ELECTRIC_FLG_DISP";
    
    public static final String powerFlgDisp_COLUMN = "POWER_FLG_DISP";
    
    public static final String gasFlgDisp_COLUMN = "GAS_FLG_DISP";
    
    public static final String waterFlgDisp_COLUMN = "WATER_FLG_DISP";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * メーター区分
     */
    private String meterKbn;
    
    /**
     * 電灯使用フラグ
     */
    private String electricFlg;
    
    /**
     * 動力使用フラグ
     */
    private String powerFlg;
    
    /**
     * ガス使用フラグ
     */
    private String gasFlg;
    
    /**
     * 水道使用フラグ
     */
    private String waterFlg;
    
    /**
     * 電灯使用フラグ保存用
     */
    private String electricFlgSave;
    
    /**
     * 動力使用フラグ保存用
     */
    private String powerFlgSave;
    
    /**
     * ガス使用フラグ保存用
     */
    private String gasFlgSave;
    
    /**
     * 水道使用フラグ保存用
     */
    private String waterFlgSave;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プルグラム
     */
    private String firstPgm;
    
    /**
     * 登録時タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プルグラム
     */
    private String lastPgm;
    
    /**
     * 修正時タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 更新対象フラグ
     */
    private boolean updateFlg;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 電灯使用フラグ
     */
    private boolean electricFlgDisp;
    
    /**
     * 動力使用フラグ
     */
    private boolean powerFlgDisp;
    
    /**
     * ガス使用フラグ
     */
    private boolean gasFlgDisp;
    
    /**
     * 水道使用フラグ
     */
    private boolean waterFlgDisp;

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
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
     * メーター区分を取得します。
     * @return メーター区分
     */
    public String getMeterKbn() {
        return meterKbn;
    }
    /**
     * メーター区分を設定します。
     * @param meterKbn メーター区分
     */
    public void setMeterKbn(String meterKbn) {
        this.meterKbn = meterKbn;
    }
    
    /**
     * 電灯使用フラグを取得します。
     * @return 電灯使用フラグ
     */
    public String getElectricFlg() {
        return electricFlg;
    }
    /**
     * 電灯使用フラグを設定します。
     * @param electricFlg 電灯使用フラグ
     */
    public void setElectricFlg(String electricFlg) {
        this.electricFlg = electricFlg;
    }
    
    /**
     * 動力使用フラグを取得します。
     * @return 動力使用フラグ
     */
    public String getPowerFlg() {
        return powerFlg;
    }
    /**
     * 動力使用フラグを設定します。
     * @param powerFlg 動力使用フラグ
     */
    public void setPowerFlg(String powerFlg) {
        this.powerFlg = powerFlg;
    }
    
    /**
     * ガス使用フラグを取得します。
     * @return ガス使用フラグ
     */
    public String getGasFlg() {
        return gasFlg;
    }
    /**
     * ガス使用フラグを設定します。
     * @param gasFlg ガス使用フラグ
     */
    public void setGasFlg(String gasFlg) {
        this.gasFlg = gasFlg;
    }
    
    /**
     * 水道使用フラグを取得します。
     * @return 水道使用フラグ
     */
    public String getWaterFlg() {
        return waterFlg;
    }
    /**
     * 水道使用フラグを設定します。
     * @param waterFlg 水道使用フラグ
     */
    public void setWaterFlg(String waterFlg) {
        this.waterFlg = waterFlg;
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
     * 登録プルグラムを取得します。
     * @return 登録プルグラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プルグラムを設定します。
     * @param firstPgm 登録プルグラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時タイムスタンプを取得します。
     * @return 登録時タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時タイムスタンプを設定します。
     * @param firstTmsp 登録時タイムスタンプ
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
     * 修正プルグラムを取得します。
     * @return 修正プルグラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プルグラムを設定します。
     * @param lastPgm 修正プルグラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時タイムスタンプを取得します。
     * @return 修正時タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時タイムスタンプを設定します。
     * @param lastTmsp 修正時タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * 更新対象フラグを取得します。
     * @return 更新対象フラグ
     */
    public boolean isUpdateFlg() {
        return updateFlg;
    }
    /**
     * 更新対象フラグを設定します。
     * @param updateFlg 更新対象フラグ
     */
    public void setUpdateFlg(boolean updateFlg) {
        this.updateFlg = updateFlg;
    }
    public String getMiseNameKj() {
        return miseNameKj;
    }
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    public String getSibuName() {
        return sibuName;
    }
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    public boolean isElectricFlgDisp() {
        return electricFlgDisp;
    }
    public void setElectricFlgDisp(boolean electricFlgDisp) {
        this.electricFlgDisp = electricFlgDisp;
    }
    public boolean isGasFlgDisp() {
        return gasFlgDisp;
    }
    public void setGasFlgDisp(boolean gasFlgDisp) {
        this.gasFlgDisp = gasFlgDisp;
    }
    public boolean isPowerFlgDisp() {
        return powerFlgDisp;
    }
    public void setPowerFlgDisp(boolean powerFlgDisp) {
        this.powerFlgDisp = powerFlgDisp;
    }
    public boolean isWaterFlgDisp() {
        return waterFlgDisp;
    }
    public void setWaterFlgDisp(boolean waterFlgDisp) {
        this.waterFlgDisp = waterFlgDisp;
    }
    public String getElectricFlgSave() {
        return electricFlgSave;
    }
    public void setElectricFlgSave(String electricFlgSave) {
        this.electricFlgSave = electricFlgSave;
    }
    public String getGasFlgSave() {
        return gasFlgSave;
    }
    public void setGasFlgSave(String gasFlgSave) {
        this.gasFlgSave = gasFlgSave;
    }
    public String getPowerFlgSave() {
        return powerFlgSave;
    }
    public void setPowerFlgSave(String powerFlgSave) {
        this.powerFlgSave = powerFlgSave;
    }
    public String getWaterFlgSave() {
        return waterFlgSave;
    }
    public void setWaterFlgSave(String waterFlgSave) {
        this.waterFlgSave = waterFlgSave;
    }
    
}
