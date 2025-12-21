package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;

public class UIEnergyAmount {
    
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
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String zenUriage_COLUMN = "ZEN_URIAGE";
    
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String zenEigyoDays_COLUMN = "ZEN_EIGYO_DAYS";
    
    public static final String electricFlg_COLUMN = "ELECTRIC_FLG";
    
    public static final String electricMeterYokugetu_COLUMN = "ELECTRIC_METER_YOKUGETU";
    
    public static final String electricMeter_COLUMN = "ELECTRIC_METER";
    
    public static final String electricAmt_COLUMN = "ELECTRIC_AMT";
    
    public static final String electricAmtZennen_COLUMN = "ELECTRIC_AMT_ZENNEN";
    
    public static final String electricAmtUriagehi_COLUMN = "ELECTRIC_AMT_URIAGEHI";
    
    public static final String electricAmtUriagehiZennen_COLUMN = "ELECTRIC_AMT_URIAGEHI_ZENNEN";
    
    public static final String powerFlg_COLUMN = "POWER_FLG";
    
    public static final String powerMeterYokugetu_COLUMN = "POWER_METER_YOKUGETU";
    
    public static final String powerMeter_COLUMN = "POWER_METER";
    
    public static final String powerAmt_COLUMN = "POWER_AMT";
    
    public static final String powerAmtZennen_COLUMN = "POWER_AMT_ZENNEN";
    
    public static final String powerAmtUriagehi_COLUMN = "POWER_AMT_URIAGEHI";
    
    public static final String powerAmtUriagehiZennen_COLUMN = "POWER_AMT_URIAGEHI_ZENNEN";
    
    public static final String gasFlg_COLUMN = "GAS_FLG";
    
    public static final String gasMeterYokugetu_COLUMN = "GAS_METER_YOKUGETU";
    
    public static final String gasMeter_COLUMN = "GAS_METER";
    
    public static final String gasAmt_COLUMN = "GAS_AMT";
    
    public static final String gasAmtZennen_COLUMN = "GAS_AMT_ZENNEN";
    
    public static final String gasAmtUriagehi_COLUMN = "GAS_AMT_URIAGEHI";
    
    public static final String gasAmtUriagehiZennen_COLUMN = "GAS_AMT_URIAGEHI_ZENNEN";
    
    public static final String waterFlg_COLUMN = "WATER_FLG";
    
    public static final String waterMeterYokugetu_COLUMN = "WATER_METER_YOKUGETU";
    
    public static final String waterMeter_COLUMN = "WATER_METER";
    
    public static final String waterAmt_COLUMN = "WATER_AMT";
    
    public static final String waterAmtZennen_COLUMN = "WATER_AMT_ZENNEN";
    
    public static final String waterAmtUriagehi_COLUMN = "WATER_AMT_URIAGEHI";
    
    public static final String waterAmtUriagehiZennen_COLUMN = "WATER_AMT_URIAGEHI_ZENNEN";
    
    public static final String dataMtDt_COLUMN = "DATA_MT_DT";
    
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
     * 売上
     */
    private BigDecimal uriage = new BigDecimal(0);
    
    /**
     * 前年売上
     */
    private BigDecimal zenUriage = new BigDecimal(0);
    
    /**
     * 営業日数
     */
    private BigDecimal eigyoDays = new BigDecimal(0);
    
    /**
     * 前年営業日数
     */
    private BigDecimal zenEigyoDays = new BigDecimal(0);
    
    /**
     * 電灯対象外
     */
    private String electricFlg;
    
    /**
     * 電灯当月採用値
     */
    private BigDecimal electricMeterYokugetu = new BigDecimal(0);
    
    /**
     * 電灯前月採用値
     */
    private BigDecimal electricMeter = new BigDecimal(0);
    
    /**
     * 電灯当年使用量
     */
    private BigDecimal electricAmt = new BigDecimal(0);
    
    /**
     * 電灯前年使用量
     */
    private BigDecimal electricAmtZennen = new BigDecimal(0);
    
    /**
     * 電灯当年使用量/売上百万
     */
    private BigDecimal electricAmtUriagehi = new BigDecimal(0);
    
    /**
     * 電灯前年使用量/売上百万
     */
    private BigDecimal electricAmtUriagehiZennen = new BigDecimal(0);
    
    /**
     * 動力対象外
     */
    private String powerFlg;
    
    /**
     * 動力当月採用値
     */
    private BigDecimal powerMeterYokugetu = new BigDecimal(0);
    
    /**
     * 動力前月採用値
     */
    private BigDecimal powerMeter = new BigDecimal(0);
    
    /**
     * 動力当年使用量
     */
    private BigDecimal powerAmt = new BigDecimal(0);
    
    /**
     * 動力前年使用量
     */
    private BigDecimal powerAmtZennen = new BigDecimal(0);
    
    /**
     * 動力当年使用量/売上百万
     */
    private BigDecimal powerAmtUriagehi = new BigDecimal(0);
    
    /**
     * 動力前年使用量/売上百万
     */
    private BigDecimal powerAmtUriagehiZennen = new BigDecimal(0);
    
    /**
     * ガス対象外
     */
    private String gasFlg;
    
    /**
     * ガス当月採用値
     */
    private BigDecimal gasMeterYokugetu = new BigDecimal(0);
    
    /**
     * ガス前月採用値
     */
    private BigDecimal gasMeter = new BigDecimal(0);
    
    /**
     * ガス当年使用量
     */
    private BigDecimal gasAmt = new BigDecimal(0);
    
    /**
     * ガス前年使用量
     */
    private BigDecimal gasAmtZennen = new BigDecimal(0);
    
    /**
     * ガス当年使用量/売上百万
     */
    private BigDecimal gasAmtUriagehi = new BigDecimal(0);
    
    /**
     * ガス前年使用量/売上百万
     */
    private BigDecimal gasAmtUriagehiZennen = new BigDecimal(0);
    
    /**
     * 水道対象外
     */
    private String waterFlg;
    
    /**
     * 水道当月採用値
     */
    private BigDecimal waterMeterYokugetu = new BigDecimal(0);
    
    /**
     * 水道前月採用値
     */
    private BigDecimal waterMeter = new BigDecimal(0);
    
    /**
     * 水道当年使用量
     */
    private BigDecimal waterAmt = new BigDecimal(0);
    
    /**
     * 水道前年使用量
     */
    private BigDecimal waterAmtZennen = new BigDecimal(0);
    
    /**
     * 水道当年使用量/売上百万
     */
    private BigDecimal waterAmtUriagehi = new BigDecimal(0);
    
    /**
     * 水道前年使用量/売上百万
     */
    private BigDecimal waterAmtUriagehiZennen = new BigDecimal(0);
    
    /**
     * データメンテ日
     */
    private String dataMtDt;
    
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
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getZenUriage() {
        return zenUriage;
    }
    /**
     * 前年売上を設定します。
     * @param zenUriage 前年売上
     */
    public void setZenUriage(BigDecimal zenUriage) {
        this.zenUriage = zenUriage;
    }
    
    /**
     * 営業日数を取得します。
     * @return 営業日数
     */
    public BigDecimal getEigyoDays() {
        return eigyoDays;
    }
    /**
     * 営業日数を設定します。
     * @param eigyoDays 営業日数
     */
    public void setEigyoDays(BigDecimal eigyoDays) {
        this.eigyoDays = eigyoDays;
    }
    
    /**
     * 前年営業日数を取得します。
     * @return 前年営業日数
     */
    public BigDecimal getZenEigyoDays() {
        return zenEigyoDays;
    }
    /**
     * 前年営業日数を設定します。
     * @param zenEigyoDays 前年営業日数
     */
    public void setZenEigyoDays(BigDecimal zenEigyoDays) {
        this.zenEigyoDays = zenEigyoDays;
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
    public BigDecimal getElectricMeterYokugetu() {
        return electricMeterYokugetu;
    }
    /**
     * 電灯当月採用値を設定します。
     * @param electricMeterYokugetu 電灯当月採用値
     */
    public void setElectricMeterYokugetu(BigDecimal electricMeterYokugetu) {
        this.electricMeterYokugetu = electricMeterYokugetu;
    }
    
    /**
     * 電灯前月採用値を取得します。
     * @return 電灯前月採用値
     */
    public BigDecimal getElectricMeter() {
        return electricMeter;
    }
    /**
     * 電灯前月採用値を設定します。
     * @param electricMeter 電灯前月採用値
     */
    public void setElectricMeter(BigDecimal electricMeter) {
        this.electricMeter = electricMeter;
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
     * 電灯前年使用量を取得します。
     * @return 電灯前年使用量
     */
    public BigDecimal getElectricAmtZennen() {
        return electricAmtZennen;
    }
    /**
     * 電灯前年使用量を設定します。
     * @param electricAmtZennen 電灯前年使用量
     */
    public void setElectricAmtZennen(BigDecimal electricAmtZennen) {
        this.electricAmtZennen = electricAmtZennen;
    }
    
    /**
     * 電灯当年使用量/売上百万を取得します。
     * @return 電灯当年使用量/売上百万
     */
    public BigDecimal getElectricAmtUriagehi() {
        return electricAmtUriagehi;
    }
    /**
     * 電灯当年使用量/売上百万を設定します。
     * @param electricAmtUriagehi 電灯当年使用量/売上百万
     */
    public void setElectricAmtUriagehi(BigDecimal electricAmtUriagehi) {
        this.electricAmtUriagehi = electricAmtUriagehi;
    }
    
    /**
     * 電灯前年使用量/売上百万を取得します。
     * @return 電灯前年使用量/売上百万
     */
    public BigDecimal getElectricAmtUriagehiZennen() {
        return electricAmtUriagehiZennen;
    }
    /**
     * 電灯前年使用量/売上百万を設定します。
     * @param electricAmtUriagehiZennen 電灯前年使用量/売上百万
     */
    public void setElectricAmtUriagehiZennen(BigDecimal electricAmtUriagehiZennen) {
        this.electricAmtUriagehiZennen = electricAmtUriagehiZennen;
    }
    
    /**
     * 電灯使用量前年比/売上百万を取得します。
     * @return 電灯使用量前年比/売上百万
     */
    public BigDecimal getElectricAmtUriagehiZennenhi() {
        return Calculator.percentage(getElectricAmtUriagehi(), getElectricAmtUriagehiZennen(), 2);
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
    public BigDecimal getPowerMeterYokugetu() {
        return powerMeterYokugetu;
    }
    /**
     * 動力当月採用値を設定します。
     * @param powerMeterYokugetu 動力当月採用値
     */
    public void setPowerMeterYokugetu(BigDecimal powerMeterYokugetu) {
        this.powerMeterYokugetu = powerMeterYokugetu;
    }
    
    /**
     * 動力前月採用値を取得します。
     * @return 動力前月採用値
     */
    public BigDecimal getPowerMeter() {
        return powerMeter;
    }
    /**
     * 動力前月採用値を設定します。
     * @param powerMeter 動力前月採用値
     */
    public void setPowerMeter(BigDecimal powerMeter) {
        this.powerMeter = powerMeter;
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
     * 動力前年使用量を取得します。
     * @return 動力前年使用量
     */
    public BigDecimal getPowerAmtZennen() {
        return powerAmtZennen;
    }
    /**
     * 動力前年使用量を設定します。
     * @param powerAmtZennen 動力前年使用量
     */
    public void setPowerAmtZennen(BigDecimal powerAmtZennen) {
        this.powerAmtZennen = powerAmtZennen;
    }
    
    /**
     * 動力当年使用量/売上百万を取得します。
     * @return 動力当年使用量/売上百万
     */
    public BigDecimal getPowerAmtUriagehi() {
        return powerAmtUriagehi;
    }
    /**
     * 動力当年使用量/売上百万を設定します。
     * @param powerAmtUriagehi 動力当年使用量/売上百万
     */
    public void setPowerAmtUriagehi(BigDecimal powerAmtUriagehi) {
        this.powerAmtUriagehi = powerAmtUriagehi;
    }
    
    /**
     * 動力前年使用量/売上百万を取得します。
     * @return 動力前年使用量/売上百万
     */
    public BigDecimal getPowerAmtUriagehiZennen() {
        return powerAmtUriagehiZennen;
    }
    /**
     * 動力前年使用量/売上百万を設定します。
     * @param powerAmtUriagehiZennen 動力前年使用量/売上百万
     */
    public void setPowerAmtUriagehiZennen(BigDecimal powerAmtUriagehiZennen) {
        this.powerAmtUriagehiZennen = powerAmtUriagehiZennen;
    }
    
    /**
     * 動力使用量前年比/売上百万を取得します。
     * @return 動力使用量前年比/売上百万
     */
    public BigDecimal getPowerAmtUriagehiZennenhi() {
        return Calculator.percentage(getPowerAmtUriagehi(), getPowerAmtUriagehiZennen(), 2);
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
    public BigDecimal getGasMeterYokugetu() {
        return gasMeterYokugetu;
    }
    /**
     * ガス当月採用値を設定します。
     * @param gasMeterYokugetu ガス当月採用値
     */
    public void setGasMeterYokugetu(BigDecimal gasMeterYokugetu) {
        this.gasMeterYokugetu = gasMeterYokugetu;
    }
    
    /**
     * ガス前月採用値を取得します。
     * @return ガス前月採用値
     */
    public BigDecimal getGasMeter() {
        return gasMeter;
    }
    /**
     * ガス前月採用値を設定します。
     * @param gasMeter ガス前月採用値
     */
    public void setGasMeter(BigDecimal gasMeter) {
        this.gasMeter = gasMeter;
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
     * ガス前年使用量を取得します。
     * @return ガス前年使用量
     */
    public BigDecimal getGasAmtZennen() {
        return gasAmtZennen;
    }
    /**
     * ガス前年使用量を設定します。
     * @param gasAmtZennen ガス前年使用量
     */
    public void setGasAmtZennen(BigDecimal gasAmtZennen) {
        this.gasAmtZennen = gasAmtZennen;
    }
    
    /**
     * ガス当年使用量/売上百万を取得します。
     * @return ガス当年使用量/売上百万
     */
    public BigDecimal getGasAmtUriagehi() {
        return gasAmtUriagehi;
    }
    /**
     * ガス当年使用量/売上百万を設定します。
     * @param gasAmtUriagehi ガス当年使用量/売上百万
     */
    public void setGasAmtUriagehi(BigDecimal gasAmtUriagehi) {
        this.gasAmtUriagehi = gasAmtUriagehi;
    }
    
    /**
     * ガス前年使用量/売上百万を取得します。
     * @return ガス前年使用量/売上百万
     */
    public BigDecimal getGasAmtUriagehiZennen() {
        return gasAmtUriagehiZennen;
    }
    /**
     * ガス前年使用量/売上百万を設定します。
     * @param gasAmtUriagehiZennen ガス前年使用量/売上百万
     */
    public void setGasAmtUriagehiZennen(BigDecimal gasAmtUriagehiZennen) {
        this.gasAmtUriagehiZennen = gasAmtUriagehiZennen;
    }
    
    /**
     * ガス使用量前年比/売上百万を取得します。
     * @return ガス使用量前年比/売上百万
     */
    public BigDecimal getGasAmtUriagehiZennenhi() {
        return Calculator.percentage(getGasAmtUriagehi(), getGasAmtUriagehiZennen(), 2);
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
    public BigDecimal getWaterMeterYokugetu() {
        return waterMeterYokugetu;
    }
    /**
     * 水道当月採用値を設定します。
     * @param waterMeterYokugetu 水道当月採用値
     */
    public void setWaterMeterYokugetu(BigDecimal waterMeterYokugetu) {
        this.waterMeterYokugetu = waterMeterYokugetu;
    }
    
    /**
     * 水道前月採用値を取得します。
     * @return 水道前月採用値
     */
    public BigDecimal getWaterMeter() {
        return waterMeter;
    }
    /**
     * 水道前月採用値を設定します。
     * @param waterMeter 水道前月採用値
     */
    public void setWaterMeter(BigDecimal waterMeter) {
        this.waterMeter = waterMeter;
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
    
    /**
     * 水道前年使用量を取得します。
     * @return 水道前年使用量
     */
    public BigDecimal getWaterAmtZennen() {
        return waterAmtZennen;
    }
    /**
     * 水道前年使用量を設定します。
     * @param waterAmtZennen 水道前年使用量
     */
    public void setWaterAmtZennen(BigDecimal waterAmtZennen) {
        this.waterAmtZennen = waterAmtZennen;
    }
    
    /**
     * 水道当年使用量/売上百万を取得します。
     * @return 水道当年使用量/売上百万
     */
    public BigDecimal getWaterAmtUriagehi() {
        return waterAmtUriagehi;
    }
    /**
     * 水道当年使用量/売上百万を設定します。
     * @param waterAmtUriagehi 水道当年使用量/売上百万
     */
    public void setWaterAmtUriagehi(BigDecimal waterAmtUriagehi) {
        this.waterAmtUriagehi = waterAmtUriagehi;
    }
    
    /**
     * 水道前年使用量/売上百万を取得します。
     * @return 水道前年使用量/売上百万
     */
    public BigDecimal getWaterAmtUriagehiZennen() {
        return waterAmtUriagehiZennen;
    }
    /**
     * 水道前年使用量/売上百万を設定します。
     * @param waterAmtUriagehiZennen 水道前年使用量/売上百万
     */
    public void setWaterAmtUriagehiZennen(BigDecimal waterAmtUriagehiZennen) {
        this.waterAmtUriagehiZennen = waterAmtUriagehiZennen;
    }
    
    /**
     * 水道使用量前年比/売上百万を取得します。
     * @return 水道使用量前年比/売上百万
     */
    public BigDecimal getWaterAmtUriagehiZennenhi() {
        return Calculator.percentage(getWaterAmtUriagehi(), getWaterAmtUriagehiZennen(), 2);
    }
    
    /**
     * データメンテ日を取得します。
     * @return データメンテ日
     */
    public String getDataMtDt() {
        return dataMtDt;
    }
    /**
     * データメンテ日を設定します。
     * @param dataMtDt データメンテ日
     */
    public void setDataMtDt(String dataMtDt) {
        this.dataMtDt = dataMtDt;
    }
    public String getNewOpen() {
        return newOpen;
    }
    public void setNewOpen(String newOpen) {
        this.newOpen = newOpen;
    }
    
}
