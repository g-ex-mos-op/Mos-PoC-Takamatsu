package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

import java.math.BigDecimal;

public class UINoInputMeter {
    
    public static final String TABLE = "BD08ENGY";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String meterYm_COLUMN = "METER_YM";
    
    public static final String jigyouCd_COLUMN = "JIGYOU_CD";
    
    public static final String jigyouName_COLUMN = "JIGYOU_NAME";
    
    public static final String slareaCd_COLUMN = "SLAREA_CD";
    
    public static final String slareaName_COLUMN = "SLAREA_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String electricFlg_COLUMN = "ELECTRIC_FLG";
    
    public static final String electricMeter_COLUMN = "ELECTRIC_METER";
    
    public static final String electricMeterZengetu_COLUMN = "ELECTRIC_METER_ZENGETU";
    
    public static final String electricAmt_COLUMN = "ELECTRIC_AMT";
    
    public static final String powerFlg_COLUMN = "POWER_FLG";
    
    public static final String powerMeter_COLUMN = "POWER_METER";
    
    public static final String powerMeterZengetu_COLUMN = "POWER_METER_ZENGETU";
    
    public static final String powerAmt_COLUMN = "POWER_AMT";
    
    public static final String gasFlg_COLUMN = "GAS_FLG";
    
    public static final String gasMeter_COLUMN = "GAS_METER";
    
    public static final String gasMeterZengetu_COLUMN = "GAS_METER_ZENGETU";
    
    public static final String gasAmt_COLUMN = "GAS_AMT";
    
    public static final String waterFlg_COLUMN = "WATER_FLG";
    
    public static final String waterMeter_COLUMN = "WATER_METER";
    
    public static final String waterMeterZengetu_COLUMN = "WATER_METER_ZENGETU";
    
    public static final String waterAmt_COLUMN = "WATER_AMT";
    
    public static final String newOpen_COLUMN = "NEW_OPEN";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 年月
     */
    private String meterYm;
    
    /**
     * 事業本部コード
     */
    private String jigyouCd;
    
    /**
     * 事業本部名称
     */
    private String jigyouName;
    
    /**
     * エリアコード
     */
    private String slareaCd;
    
    /**
     * エリア名称
     */
    private String slareaName;
    
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
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * オープン日
     */
    private String openDt;
    
    /**
     * 電灯対象外
     */
    private String electricFlg;
    
    /**
     * 電灯当月採用値
     */
    private BigDecimal electricMeter;
    
    /**
     * 電灯前月採用値
     */
    private BigDecimal electricMeterZengetu;
    
    /**
     * 電灯当年使用量
     */
    private BigDecimal electricAmt;
    
    /**
     * 動力対象外
     */
    private String powerFlg;
    
    /**
     * 動力当月採用値
     */
    private BigDecimal powerMeter;
    
    /**
     * 動力前月採用値
     */
    private BigDecimal powerMeterZengetu;
    
    /**
     * 動力当年使用量
     */
    private BigDecimal powerAmt;
    
    /**
     * ガス対象外
     */
    private String gasFlg;
    
    /**
     * ガス当月採用値
     */
    private BigDecimal gasMeter;
    
    /**
     * ガス前月採用値
     */
    private BigDecimal gasMeterZengetu;
    
    /**
     * ガス当年使用量
     */
    private BigDecimal gasAmt;
    
    /**
     * 水道対象外
     */
    private String waterFlg;
    
    /**
     * 水道当月採用値
     */
    private BigDecimal waterMeter;
    
    /**
     * 水道前月採用値
     */
    private BigDecimal waterMeterZengetu;
    
    /**
     * 水道当年使用量
     */
    private BigDecimal waterAmt;
    
    /**
     * 新オープン
     */
    private String newOpen;
    
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
     * 年月を取得します。
     * @return 年月
     */
    public String getMeterYm() {
        return meterYm;
    }
    /**
     * 年月を設定します。
     * @param meterYm 年月
     */
    public void setMeterYm(String meterYm) {
        this.meterYm = meterYm;
    }
    
    /**
     * 事業本部コードを取得します。
     * @return 事業本部コード
     */
    public String getJigyouCd() {
        return jigyouCd;
    }
    /**
     * 事業本部コードを設定します。
     * @param jigyouCd 事業本部コード
     */
    public void setJigyouCd(String jigyouCd) {
        this.jigyouCd = jigyouCd;
    }
    
    /**
     * 事業本部名称を取得します。
     * @return 事業本部名称
     */
    public String getJigyouName() {
        return jigyouName;
    }
    /**
     * 事業本部名称を設定します。
     * @param jigyouName 事業本部名称
     */
    public void setJigyouName(String jigyouName) {
        this.jigyouName = jigyouName;
    }
    
    /**
     * エリアコードを取得します。
     * @return エリアコード
     */
    public String getSlareaCd() {
        return slareaCd;
    }
    /**
     * エリアコードを設定します。
     * @param slareaCd エリアコード
     */
    public void setSlareaCd(String slareaCd) {
        this.slareaCd = slareaCd;
    }
    
    /**
     * エリア名称を取得します。
     * @return エリア名称
     */
    public String getSlareaName() {
        return slareaName;
    }
    /**
     * エリア名称を設定します。
     * @param slareaName エリア名称
     */
    public void setSlareaName(String slareaName) {
        this.slareaName = slareaName;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
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
        return sibuName;
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
        return blockCd;
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
        return blockName;
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * オープン日を取得します。
     * @return オープン日
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * オープン日を設定します。
     * @param openDt オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
    /**
     * 電灯対象外を取得します。
     * @return 電灯対象外
     */
    public String getElectricFlg() {
        return electricFlg;
    }
    /**
     * 電灯対象外を設定します。
     * @param electricFlg 電灯対象外
     */
    public void setElectricFlg(String electricFlg) {
        this.electricFlg = electricFlg;
    }
    
    /**
     * 電灯当月採用値を取得します。
     * @return 電灯当月採用値
     */
    public BigDecimal getElectricMeter() {
        return electricMeter;
    }
    /**
     * 電灯当月採用値を設定します。
     * @param electricMeter 電灯当月採用値
     */
    public void setElectricMeter(BigDecimal electricMeter) {
        this.electricMeter = electricMeter;
    }
    
    /**
     * 電灯前月採用値を取得します。
     * @return 電灯前月採用値
     */
    public BigDecimal getElectricMeterZengetu() {
        return electricMeterZengetu;
    }
    /**
     * 電灯前月採用値を設定します。
     * @param electricMeterZengetu 電灯前月採用値
     */
    public void setElectricMeterZengetu(BigDecimal electricMeterZengetu) {
        this.electricMeterZengetu = electricMeterZengetu;
    }
    
    /**
     * 電灯当年使用量を取得します。
     * @return 電灯当年使用量
     */
    public BigDecimal getElectricAmt() {
        return electricAmt;
    }
    /**
     * 電灯当年使用量を設定します。
     * @param electricAmt 電灯当年使用量
     */
    public void setElectricAmt(BigDecimal electricAmt) {
        this.electricAmt = electricAmt;
    }
    
    /**
     * 動力対象外を取得します。
     * @return 動力対象外
     */
    public String getPowerFlg() {
        return powerFlg;
    }
    /**
     * 動力対象外を設定します。
     * @param powerFlg 動力対象外
     */
    public void setPowerFlg(String powerFlg) {
        this.powerFlg = powerFlg;
    }
    
    /**
     * 動力当月採用値を取得します。
     * @return 動力当月採用値
     */
    public BigDecimal getPowerMeter() {
        return powerMeter;
    }
    /**
     * 動力当月採用値を設定します。
     * @param powerMeter 動力当月採用値
     */
    public void setPowerMeter(BigDecimal powerMeter) {
        this.powerMeter = powerMeter;
    }
    
    /**
     * 動力前月採用値を取得します。
     * @return 動力前月採用値
     */
    public BigDecimal getPowerMeterZengetu() {
        return powerMeterZengetu;
    }
    /**
     * 動力前月採用値を設定します。
     * @param powerMeterZengetu 動力前月採用値
     */
    public void setPowerMeterZengetu(BigDecimal powerMeterZengetu) {
        this.powerMeterZengetu = powerMeterZengetu;
    }
    
    /**
     * 動力当年使用量を取得します。
     * @return 動力当年使用量
     */
    public BigDecimal getPowerAmt() {
        return powerAmt;
    }
    /**
     * 動力当年使用量を設定します。
     * @param powerAmt 動力当年使用量
     */
    public void setPowerAmt(BigDecimal powerAmt) {
        this.powerAmt = powerAmt;
    }
    
    /**
     * ガス対象外を取得します。
     * @return ガス対象外
     */
    public String getGasFlg() {
        return gasFlg;
    }
    /**
     * ガス対象外を設定します。
     * @param gasFlg ガス対象外
     */
    public void setGasFlg(String gasFlg) {
        this.gasFlg = gasFlg;
    }
    
    /**
     * ガス当月採用値を取得します。
     * @return ガス当月採用値
     */
    public BigDecimal getGasMeter() {
        return gasMeter;
    }
    /**
     * ガス当月採用値を設定します。
     * @param gasMeter ガス当月採用値
     */
    public void setGasMeter(BigDecimal gasMeter) {
        this.gasMeter = gasMeter;
    }
    
    /**
     * ガス前月採用値を取得します。
     * @return ガス前月採用値
     */
    public BigDecimal getGasMeterZengetu() {
        return gasMeterZengetu;
    }
    /**
     * ガス前月採用値を設定します。
     * @param gasMeterZengetu ガス前月採用値
     */
    public void setGasMeterZengetu(BigDecimal gasMeterZengetu) {
        this.gasMeterZengetu = gasMeterZengetu;
    }
    
    /**
     * ガス当年使用量を取得します。
     * @return ガス当年使用量
     */
    public BigDecimal getGasAmt() {
        return gasAmt;
    }
    /**
     * ガス当年使用量を設定します。
     * @param gasAmt ガス当年使用量
     */
    public void setGasAmt(BigDecimal gasAmt) {
        this.gasAmt = gasAmt;
    }
    
    /**
     * 水道対象外を取得します。
     * @return 水道対象外
     */
    public String getWaterFlg() {
        return waterFlg;
    }
    /**
     * 水道対象外を設定します。
     * @param waterFlg 水道対象外
     */
    public void setWaterFlg(String waterFlg) {
        this.waterFlg = waterFlg;
    }
    
    /**
     * 水道当月採用値を取得します。
     * @return 水道当月採用値
     */
    public BigDecimal getWaterMeter() {
        return waterMeter;
    }
    /**
     * 水道当月採用値を設定します。
     * @param waterMeter 水道当月採用値
     */
    public void setWaterMeter(BigDecimal waterMeter) {
        this.waterMeter = waterMeter;
    }
    
    /**
     * 水道前月採用値を取得します。
     * @return 水道前月採用値
     */
    public BigDecimal getWaterMeterZengetu() {
        return waterMeterZengetu;
    }
    /**
     * 水道前月採用値を設定します。
     * @param waterMeterZengetu 水道前月採用値
     */
    public void setWaterMeterZengetu(BigDecimal waterMeterZengetu) {
        this.waterMeterZengetu = waterMeterZengetu;
    }
    
    /**
     * 水道当年使用量を取得します。
     * @return 水道当年使用量
     */
    public BigDecimal getWaterAmt() {
        return waterAmt;
    }
    /**
     * 水道当年使用量を設定します。
     * @param waterAmt 水道当年使用量
     */
    public void setWaterAmt(BigDecimal waterAmt) {
        this.waterAmt = waterAmt;
    }
    public String getNewOpen() {
        return newOpen;
    }
    public void setNewOpen(String newOpen) {
        this.newOpen = newOpen;
    }
    
}
