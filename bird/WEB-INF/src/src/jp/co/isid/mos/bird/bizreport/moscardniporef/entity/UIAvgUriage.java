package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

public class UIAvgUriage {
    
    public static final String TABLE = "BD35ZNMC";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String threeKbn_COLUMN = "THREE_KBN";
    
    public static final String threeName_COLUMN = "THREE_NAME";
    
    public static final String uriageAvg_COLUMN = "URIAGE_AVG";
    
    public static final String kyakusuAvg_COLUMN = "KYAKUSU_AVG";
      
    public static final String eigyoDaysSum_COLUMN = "EIGYO_DAYS_SUM";
    
    public static final String uriageZenDogetuAvg_COLUMN = "URIAGE_ZEN_DOGETU_AVG";
    
    public static final String kyakusuZenDogetuAvg_COLUMN = "KYAKUSU_ZEN_DOGETU_AVG";
    
    public static final String eigyoDaysZenDogetuSum_COLUMN = "EIGYO_DAYS_ZEN_DOGETU_SUM";
    
    public static final String uriageZennenhi_COLUMN = "URIAGE_ZENNENHI";
    
    public static final String kyakusuZennenhi_COLUMN = "KYAKUSU_ZENNENHI";
    
    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZenDogetu_COLUMN = "KYAKUTANKA_ZEN_DOGETU";
    
    public static final String kyakutankaZenDogetuhi_COLUMN = "KYAKUTANKA_ZEN_DOGETUHI";
    
    public static final String issueCntAvg_COLUMN = "ISSUE_CNT_AVG";
    
    public static final String chargeKinAvg_COLUMN = "CHARGE_KIN_AVG";
    
    public static final String chargeKenAvg_COLUMN = "CHARGE_KEN_AVG";
    
    public static final String kessaiKinAvg_COLUMN = "KESSAI_KIN_AVG";
    
    public static final String kessaiKenAvg_COLUMN = "KESSAI_KEN_AVG";
    
    public static final String issueCntZenDogetuAvg_COLUMN = "ISSUE_CNT_ZEN_DOGETU_AVG";
    
    public static final String chargeKinZenDogetuAvg_COLUMN = "CHARGE_KIN_ZEN_DOGETU_AVG";
    
    public static final String chargeKenZenDogetuAvg_COLUMN = "CHARGE_KEN_ZEN_DOGETU_AVG";
    
    public static final String kessaiKinZenDogetuAvg_COLUMN = "KESSAI_KIN_ZEN_DOGETU_AVG";
    
    public static final String kessaiKenZenDogetuAvg_COLUMN = "KESSAI_KEN_ZEN_DOGETU_AVG";
    
    public static final String issueCntZennenhi_COLUMN = "ISSUE_CNT_ZENNENHI";
    
    public static final String chargeKinZennenhi_COLUMN = "CHARGE_KIN_ZENNENHI";
    
    public static final String chargeKenZennenhi_COLUMN = "CHARGE_KEN_ZENNENHI";
    
    public static final String chargeTanka_COLUMN = "CHARGE_TANKA";
    
    public static final String chargeTankaZenDogetu_COLUMN = "CHARGE_TANKA_ZEN_DOGETU";
    
    public static final String chargeTankaZenDogetuhi_COLUMNHI = "CHARGE_TANKA_ZEN_DOGETUHI";
    
    public static final String kessaiKinZennenhi_COLUMN = "KESSAI_KIN_ZENNENHI";
    
    public static final String kessaiKenZennenhi_COLUMN = "KESSAI_KEN_ZENNENHI";
    
    public static final String kessaiTanka_COLUMN = "KESSAI_TANKA";
    
    public static final String kessaiTankaZenDogetu_COLUMN = "KESSAI_TANKA_ZEN_DOGETU";
    
    public static final String kessaiTankaZenDogetuhi_COLUMN = "KESSAI_TANKA_ZEN_DOGETUHI";
    
     /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 三曜日区分
     */
    private String threeKbn;
    
    /**
     * 三曜日名
     */
    private String threeName;
    
    /**
     * 売上平均
     */
    private BigDecimal uriageAvg;
    
    /**
     * 客数平均
     */
    private BigDecimal kyakusuAvg;
    
    /**
     * 営業日数
     */
    private BigDecimal eigyoDaysSum;
    
    /**
     * 前年同月売上平均
     */
    private BigDecimal uriageZenDogetuAvg;
    
    /**
     * 前年同月客数平均
     */
    private BigDecimal kyakusuZenDogetuAvg;
    
    /**
     * 前年同月営業日数
     */
    private BigDecimal eigyoDaysZenDogetuSum;
    
    /**
     * 売上前年比
     */
    private BigDecimal uriageZennenhi;
    
    /**
     * 客数前年比
     */
    private BigDecimal kyakusuZennenhi;
    
    /**
     * 客単価
     */
    private BigDecimal kyakutanka;
    
    /**
     * 前年同月客単価
     */
    private BigDecimal kyakutankaZenDogetu;
    
    /**
     * 客単価前年比
     */
    private BigDecimal kyakutankaZenDogetuhi;
    
    /**
     * 発行枚数平均
     */
    private BigDecimal issueCntAvg;
    
    /**
     * チャージ金額平均
     */
    private BigDecimal chargeKinAvg;
    
    /**
     * チャージ件数平均
     */
    private BigDecimal chargeKenAvg;
    
    /**
     * 決済金額平均
     */
    private BigDecimal kessaiKinAvg;
    
    /**
     * 決済件数平均
     */
    private BigDecimal kessaiKenAvg;
    
    /**
     * 前年同月発行枚数平均
     */
    private BigDecimal issueCntZenDogetuAvg;
    
    /**
     * 前年同月チャージ金額平均
     */
    private BigDecimal chargeKinZenDogetuAvg;
    
    /**
     * 前年同月チャージ件数平均
     */
    private BigDecimal chargeKenZenDogetuAvg;
    
    /**
     * 前年同月決済金額平均
     */
    private BigDecimal kessaiKinZenDogetuAvg;
    
    /**
     * 前年同月決済件数平均
     */
    private BigDecimal kessaiKenZenDogetuAvg;
    
    /**
     * 発行枚数前年比
     */
    private BigDecimal issueCntZennenhi;
    
    /**
     * チャージ金額前年比
     */
    private BigDecimal chargeKinZennenhi;
    
    /**
     * チャージ件数前年比
     */
    private BigDecimal chargeKenZennenhi;
    
    /**
     * チャージ単価
     */
    private BigDecimal chargeTanka;
    
    /**
     * 前年同月チャージ単価
     */
    private BigDecimal chargeTankaZenDogetu;
    
    /**
     * チャージ単価前年比
     */
    private BigDecimal chargeTankaZenDogetuhi;
    
    /**
     * 決済金額前年比
     */
    private BigDecimal kessaiKinZennenhi;

    /**
     * 決済金額前年比
     */
    private BigDecimal kessaiKenZennenhi;

    /**
     * チャージ単価
     */
    private BigDecimal kessaiTanka;
    
    /**
     * 前年同月チャージ単価
     */
    private BigDecimal kessaiTankaZenDogetu;
    
    /**
     * チャージ単価前年比
     */
    private BigDecimal kessaiTankaZenDogetuhi;
    
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
     * 三曜日区分を取得します。
     * @return 三曜日区分
     */
    public String getThreeKbn() {
        return threeKbn;
    }
    /**
     * 三曜日区分を設定します。
     * @param threeKbn 三曜日区分
     */
    public void setThreeKbn(String threeKbn) {
        this.threeKbn = threeKbn;
    }
    
    /**
     * 三曜日名を取得します。
     * @return 三曜日名
     */
    public String getThreeName() {
        return threeName;
    }
    /**
     * 三曜日名を設定します。
     * @param threeName 三曜日名
     */
    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }
    
    /**
     * 売上平均を取得します。
     * @return 売上平均
     */
    public BigDecimal getUriageAvg() {
        return uriageAvg;
    }
    /**
     * 売上平均を設定します。
     * @param uriageAvg 売上平均
     */
    public void setUriageAvg(BigDecimal uriageAvg) {
        this.uriageAvg = uriageAvg;
    }
    
    /**
     * 客数平均を取得します。
     * @return 客数平均
     */
    public BigDecimal getKyakusuAvg() {
        return kyakusuAvg;
    }
    /**
     * 客数平均を設定します。
     * @param kyakusuAvg 客数平均
     */
    public void setKyakusuAvg(BigDecimal kyakusuAvg) {
        this.kyakusuAvg = kyakusuAvg;
    }
    
    /**
     * 営業日数を取得します。
     * @return 営業日数
     */
    public BigDecimal getEigyoDaysSum() {
        return eigyoDaysSum;
    }
    /**
     * 営業日数を設定します。
     * @param eigyoDaysSum 営業日数
     */
    public void setEigyoDaysSum(BigDecimal eigyoDaysSum) {
        this.eigyoDaysSum = eigyoDaysSum;
    }
    
    /**
     * 前年同月売上平均を取得します。
     * @return 前年同月売上平均
     */
    public BigDecimal getUriageZenDogetuAvg() {
        return uriageZenDogetuAvg;
    }
    /**
     * 前年同月売上平均を設定します。
     * @param uriageZenDogetuAvg 前年同月売上平均
     */
    public void setUriageZenDogetuAvg(BigDecimal uriageZenDogetuAvg) {
        this.uriageZenDogetuAvg = uriageZenDogetuAvg;
    }
    
    /**
     * 前年同月客数平均を取得します。
     * @return 前年同月客数平均
     */
    public BigDecimal getKyakusuZenDogetuAvg() {
        return kyakusuZenDogetuAvg;
    }
    /**
     * 前年同月客数平均を設定します。
     * @param kyakusuZenDogetuAvg 前年同月客数平均
     */
    public void setKyakusuZenDogetuAvg(BigDecimal kyakusuZenDogetuAvg) {
        this.kyakusuZenDogetuAvg = kyakusuZenDogetuAvg;
    }
    
    /**
     * 前年同月営業日数を取得します。
     * @return 前年同月営業日数
     */
    public BigDecimal getEigyoDaysZenDogetuSum() {
        return eigyoDaysZenDogetuSum;
    }
    /**
     * 前年同月営業日数を設定します。
     * @param eigyoDaysZenDogetuSum 前年同月営業日数
     */
    public void setEigyoDaysZenDogetuSum(BigDecimal eigyoDaysZenDogetuSum) {
        this.eigyoDaysZenDogetuSum = eigyoDaysZenDogetuSum;
    }
    
    /**
     * 売上前年比を取得します。
     * @return 売上前年比
     */
    public BigDecimal getUriageZennenhi() {
        return uriageZennenhi;
    }
    /**
     * 売上前年比を設定します。
     * @param uriageZennenhi 売上前年比
     */
    public void setUriageZennenhi(BigDecimal uriageZennenhi) {
        this.uriageZennenhi = uriageZennenhi;
    }
    
    /**
     * 客数前年比を取得します。
     * @return 客数前年比
     */
    public BigDecimal getKyakusuZennenhi() {
        return kyakusuZennenhi;
    }
    /**
     * 客数前年比を設定します。
     * @param kyakusuZennenhi 客数前年比
     */
    public void setKyakusuZennenhi(BigDecimal kyakusuZennenhi) {
        this.kyakusuZennenhi = kyakusuZennenhi;
    }
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka() {
        return kyakutanka;
    }
    /**
     * 客単価を設定します。
     * @param kyakutanka 客単価
     */
    public void setKyakutanka(BigDecimal kyakutanka) {
        this.kyakutanka = kyakutanka;
    }
    
    /**
     * 前年同月客単価を取得します。
     * @return 前年同月客単価
     */
    public BigDecimal getKyakutankaZenDogetu() {
        return kyakutankaZenDogetu;
    }
    /**
     * 前年同月客単価を設定します。
     * @param kyakutankaZenDogetu 前年同月客単価
     */
    public void setKyakutankaZenDogetu(BigDecimal kyakutankaZenDogetu) {
        this.kyakutankaZenDogetu = kyakutankaZenDogetu;
    }
    
    /**
     * 客単価前年比を取得します。
     * @return 客単価前年比
     */
    public BigDecimal getKyakutankaZenDogetuhi() {
        return kyakutankaZenDogetuhi;
    }
    /**
     * 客単価前年比を設定します。
     * @param kyakutankaZenDogetuhi 客単価前年比
     */
    public void setKyakutankaZenDogetuhi(BigDecimal kyakutankaZenDogetuhi) {
        this.kyakutankaZenDogetuhi = kyakutankaZenDogetuhi;
    }
    
    /**
     * 発行枚数平均を取得します。
     * @return 発行枚数平均
     */
    public BigDecimal getIssueCntAvg() {
        return issueCntAvg;
    }
    /**
     * 発行枚数平均を設定します。
     * @param issueCntAvg 発行枚数平均
     */
    public void setIssueCntAvg(BigDecimal issueCntAvg) {
        this.issueCntAvg = issueCntAvg;
    }
    
    /**
     * チャージ金額平均を取得します。
     * @return チャージ金額平均
     */
    public BigDecimal getChargeKinAvg() {
        return chargeKinAvg;
    }
    /**
     * チャージ金額平均を設定します。
     * @param chargeKinAvg チャージ金額平均
     */
    public void setChargeKinAvg(BigDecimal chargeKinAvg) {
        this.chargeKinAvg = chargeKinAvg;
    }
    
    /**
     * チャージ件数平均を取得します。
     * @return チャージ件数平均
     */
    public BigDecimal getChargeKenAvg() {
        return chargeKenAvg;
    }
    /**
     * チャージ件数平均を設定します。
     * @param chargeKenAvg チャージ件数平均
     */
    public void setChargeKenAvg(BigDecimal chargeKenAvg) {
        this.chargeKenAvg = chargeKenAvg;
    }
    
    /**
     * 決済金額平均を取得します。
     * @return 決済金額平均
     */
    public BigDecimal getKessaiKinAvg() {
        return kessaiKinAvg;
    }
    /**
     * 決済金額平均を設定します。
     * @param kessaiKinAvg 決済金額平均
     */
    public void setKessaiKinAvg(BigDecimal kessaiKinAvg) {
        this.kessaiKinAvg = kessaiKinAvg;
    }
    
    /**
     * 決済件数平均を取得します。
     * @return 決済件数平均
     */
    public BigDecimal getKessaiKenAvg() {
        return kessaiKenAvg;
    }
    /**
     * 決済件数平均を設定します。
     * @param kessaiKenAvg 決済件数平均
     */
    public void setkessaiKenAvg(BigDecimal kessaiKenAvg) {
        this.kessaiKenAvg = kessaiKenAvg;
    }
    
    /**
     * 前年同月発行枚数平均を取得します。
     * @return 前年同月発行枚数平均
     */
    public BigDecimal getIssueCntZenDogetuAvg() {
        return issueCntZenDogetuAvg;
    }
    /**
     * 前年同月発行枚数平均を設定します。
     * @param issueCntZenDogetuAvg 前年同月発行枚数平均
     */
    public void setIssueCntZenDogetuAvg(BigDecimal issueCntZenDogetuAvg) {
        this.issueCntZenDogetuAvg = issueCntZenDogetuAvg;
    }
    
    /**
     * 前年同月チャージ金額平均を取得します。
     * @return チャージ金額平均
     */
    public BigDecimal getChargeKinZenDogetuAvg() {
        return chargeKinZenDogetuAvg;
    }
    /**
     * 前年同月チャージ金額平均を設定します。
     * @param chargeKinZenDogetuAvg チャージ金額平均
     */
    public void setChargeKinZenDogetuAvg(BigDecimal chargeKinZenDogetuAvg) {
        this.chargeKinZenDogetuAvg = chargeKinZenDogetuAvg;
    }
    
    /**
     * 前年同月チャージ件数平均を取得します。
     * @return チャージ件数平均
     */
    public BigDecimal getChargeKenZenDogetuAvg() {
        return chargeKenZenDogetuAvg;
    }
    /**
     * 前年同月チャージ件数平均を設定します。
     * @param chargeKenZenDogetuAvg チャージ件数平均
     */
    public void setChargeKenZenDogetuAvg(BigDecimal chargeKenZenDogetuAvg) {
        this.chargeKenZenDogetuAvg = chargeKenZenDogetuAvg;
    }
    
    /**
     * 前年同月決済金額平均を取得します。
     * @return 決済金額平均
     */
    public BigDecimal getKessaiKinZenDogetuAvg() {
        return kessaiKinZenDogetuAvg;
    }
    /**
     * 前年同月決済金額平均を設定します。
     * @param kessaiKinZenDogetuAvg 決済金額平均
     */
    public void setKessaiKinZenDogetuAvg(BigDecimal kessaiKinZenDogetuAvg) {
        this.kessaiKinZenDogetuAvg = kessaiKinZenDogetuAvg;
    }
    
    /**
     * 前年同月決済件数平均を取得します。
     * @return 決済件数平均
     */
    public BigDecimal getKessaiKenZenDogetuAvg() {
        return kessaiKenZenDogetuAvg;
    }
    /**
     * 前年同月決済件数平均を設定します。
     * @param kessaiKenZenDogetuAvg 決済件数平均
     */
    public void setKessaiKenZenDogetuAvg(BigDecimal kessaiKenZenDogetuAvg) {
        this.kessaiKenZenDogetuAvg = kessaiKenZenDogetuAvg;
    }
    
    /**
     * 発行枚数前年比を取得します。
     * @return 発行枚数前年比
     */
    public BigDecimal getIssueCntZennenhi() {
        return issueCntZennenhi;
    }
    /**
     * 発行枚数前年比を設定します。
     * @param issueCntZennenhi 発行枚数前年比
     */
    public void setIssueCntZennenhi(BigDecimal issueCntZennenhi) {
        this.issueCntZennenhi = issueCntZennenhi;
    }
    
    /**
     * チャージ金額前年比を取得します。
     * @return チャージ金額前年比
     */
    public BigDecimal getChargeKinZennenhi() {
        return chargeKinZennenhi;
    }
    /**
     * チャージ金額前年比を設定します。
     * @param chargeKinZennenhi チャージ金額前年比
     */
    public void setChargeKinZennenhi(BigDecimal chargeKinZennenhi) {
        this.chargeKinZennenhi = chargeKinZennenhi;
    }
    
    /**
     * チャージ件数前年比を取得します。
     * @return チャージ件数前年比
     */
    public BigDecimal getChargeKenZennenhi() {
        return chargeKenZennenhi;
    }
    /**
     * チャージ件数前年比を設定します。
     * @param chargeKinZennenhi チャージ件数前年比
     */
    public void setChargeKenZennenhi(BigDecimal chargeKenZennenhi) {
        this.chargeKenZennenhi = chargeKenZennenhi;
    }
    
    /**
     * チャージ単価を取得します。
     * @return チャージ単価
     */
    public BigDecimal getChargeTanka() {
        return chargeTanka;
    }
    /**
     * チャージ単価を設定します。
     * @param chargeTanka チャージ単価
     */
    public void setChargeTanka(BigDecimal chargeTanka) {
        this.chargeTanka = chargeTanka;
    }
    
    /**
     * 前年同月チャージ単価を取得します。
     * @return チャージ単価
     */
    public BigDecimal getChargeTankaZenDogetu() {
        return chargeTankaZenDogetu;
    }
    /**
     * 前年同月チャージ単価を設定します。
     * @param chargeTankaZenDogetu チャージ単価
     */
    public void setChargeTankaZenDogetu(BigDecimal chargeTankaZenDogetu) {
        this.chargeTankaZenDogetu = chargeTankaZenDogetu;
    }
    
    /**
     * チャージ単価前年比を取得します。
     * @return チャージ単価前年比
     */
    public BigDecimal getChargeTankaZenDogetuhi() {
        return chargeTankaZenDogetuhi;
    }
    /**
     * チャージ単価前年比を設定します。
     * @param chargeTankaZenDogetuhi チャージ単価前年比
     */
    public void setChargeTankaZenDogetuhi(BigDecimal chargeTankaZenDogetuhi) {
        this.chargeTankaZenDogetuhi = chargeTankaZenDogetuhi;
    }
    
    /**
     * 決済金額前年比を取得します。
     * @return 決済金額前年比
     */
    public BigDecimal getKessaiKinZennenhi() {
        return kessaiKinZennenhi;
    }
    /**
     * 決済金額前年比を設定します。
     * @param kessaiKinZennenhi 決済金額前年比
     */
    public void setKessaiKinZennenhi(BigDecimal kessaiKinZennenhi) {
        this.kessaiKinZennenhi = kessaiKinZennenhi;
    }
    
    /**
     * 決済件数前年比を取得します。
     * @return 決済件数前年比
     */
    public BigDecimal getKessaiKenZennenhi() {
        return kessaiKenZennenhi;
    }
    /**
     * 決済件数前年比を設定します。
     * @param kessaiKenZennenhi 決済件数前年比
     */
    public void setKessaiKenZennenhi(BigDecimal kessaiKenZennenhi) {
        this.kessaiKenZennenhi = kessaiKenZennenhi;
    }
    
    /**
     * 決済単価を取得します。
     * @return 決済単価
     */
    public BigDecimal getKessaiTanka() {
        return kessaiTanka;
    }
    /**
     * 決済単価を設定します。
     * @param kessaiTanka 決済単価
     */
    public void setKessaiTanka(BigDecimal kessaiTanka) {
        this.kessaiTanka = kessaiTanka;
    }
    
    /**
     * 前年同月決済単価を取得します。
     * @return 前年同月決済単価
     */
    public BigDecimal getKessaiTankaZenDogetu() {
        return kessaiTankaZenDogetu;
    }
    /**
     * 前年同月決済単価を設定します。
     * @param kessaiTankaZenDogetu 前年同月決済単価
     */
    public void setKessaiTankaZenDogetu(BigDecimal kessaiTankaZenDogetu) {
        this.kessaiTankaZenDogetu = kessaiTankaZenDogetu;
    }
    
    /**
     * 決済単価前年比を取得します。
     * @return 決済単価前年比
     */
    public BigDecimal getKessaiTankaZenDogetuhi() {
        return kessaiTankaZenDogetuhi;
    }
    /**
     * 決済単価前年比を設定します。
     * @param kessaiTankaZenDogetu 決済単価前年比
     */
    public void setKessaiTankaZenDogetuhi(BigDecimal kessaiTankaZenDogetuhi) {
        this.kessaiTankaZenDogetuhi = kessaiTankaZenDogetuhi;
    }
}
