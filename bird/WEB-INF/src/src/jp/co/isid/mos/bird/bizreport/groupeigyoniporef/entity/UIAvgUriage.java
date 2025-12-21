package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity;

import java.math.BigDecimal;

public class UIAvgUriage {
    
    public static final String TABLE = "BT60ZNIP";
    
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
    
}
