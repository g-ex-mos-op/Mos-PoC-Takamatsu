package jp.co.isid.mos.bird.analysis.shubetusuiiref.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;

public class UIShubetuSuii {
    
    public static final String TABLE = "BT63SNIP";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseCnt_COLUMN = "MISE_CNT";
    
    public static final String eatKen_COLUMN = "EAT_KEN";
    
    public static final String eatKin_COLUMN = "EAT_KIN";
    
    public static final String takeKen_COLUMN = "TAKE_KEN";
    
    public static final String takeKin_COLUMN = "TAKE_KIN";
    
    public static final String telKen_COLUMN = "TEL_KEN";
    
    public static final String telKin_COLUMN = "TEL_KIN";
    
    public static final String driveKen_COLUMN = "DRIVE_KEN";
    
    public static final String driveKin_COLUMN = "DRIVE_KIN";
    
    public static final String takuhaiKen_COLUMN = "TAKUHAI_KEN";
    
    public static final String takuhaiKin_COLUMN = "TAKUHAI_KIN";
    
    public static final String gaihanKen_COLUMN = "GAIHAN_KEN";
    
    public static final String gaihanKin_COLUMN = "GAIHAN_KIN";
    
    public static final String syubetsu07Ken_COLUMN = "SYUBETSU07_KEN";
    
    public static final String syubetsu07Kin_COLUMN = "SYUBETSU07_KIN";
    
    public static final String syubetsu08Ken_COLUMN = "SYUBETSU08_KEN";
    
    public static final String syubetsu08Kin_COLUMN = "SYUBETSU08_KIN";
    
    public static final String syubetsu09Ken_COLUMN = "SYUBETSU09_KEN";
    
    public static final String syubetsu09Kin_COLUMN = "SYUBETSU09_KIN";
    
    public static final String syubetsu10Ken_COLUMN = "SYUBETSU10_KEN";
    
    public static final String syubetsu10Kin_COLUMN = "SYUBETSU10_KIN";
    
    public static final String nettakeKen_COLUMN = "NETTAKE_KEN";
    
    public static final String nettakeKin_COLUMN = "NETTAKE_KIN";
    
    public static final String nettakuhaiKen_COLUMN = "NETTAKUHAI_KEN";
    
    public static final String nettakuhaiKin_COLUMN = "NETTAKUHAI_KIN";
    
    public static final String syubetsu13Ken_COLUMN = "SYUBETSU13_KEN";
    
    public static final String syubetsu13Kin_COLUMN = "SYUBETSU13_KIN";
    
    public static final String syubetsu14Ken_COLUMN = "SYUBETSU14_KEN";
    
    public static final String syubetsu14Kin_COLUMN = "SYUBETSU14_KIN";
    
    public static final String syubetsu15Ken_COLUMN = "SYUBETSU15_KEN";
    
    public static final String syubetsu15Kin_COLUMN = "SYUBETSU15_KIN";
    
    public static final String otherKen_COLUMN = "OTHER_KEN";
    
    public static final String otherKin_COLUMN = "OTHER_KIN";
    
    public static final String eatKenZen_COLUMN = "EAT_KEN_ZEN";
    
    public static final String eatKinZen_COLUMN = "EAT_KIN_ZEN";
    
    public static final String takeKenZen_COLUMN = "TAKE_KEN_ZEN";
    
    public static final String takeKinZen_COLUMN = "TAKE_KIN_ZEN";
    
    public static final String telKenZen_COLUMN = "TEL_KEN_ZEN";
    
    public static final String telKinZen_COLUMN = "TEL_KIN_ZEN";
    
    public static final String driveKenZen_COLUMN = "DRIVE_KEN_ZEN";
    
    public static final String driveKinZen_COLUMN = "DRIVE_KIN_ZEN";
    
    public static final String takuhaiKenZen_COLUMN = "TAKUHAI_KEN_ZEN";
    
    public static final String takuhaiKinZen_COLUMN = "TAKUHAI_KIN_ZEN";
    
    public static final String gaihanKenZen_COLUMN = "GAIHAN_KEN_ZEN";
    
    public static final String gaihanKinZen_COLUMN = "GAIHAN_KIN_ZEN";
    
	public static final String syubetsu07KenZen_COLUMN = "SYUBETSU07_KEN_ZEN";
    
    public static final String syubetsu07KinZen_COLUMN = "SYUBETSU07_KIN_ZEN";
    
    public static final String syubetsu08KenZen_COLUMN = "SYUBETSU08_KEN_ZEN";
    
    public static final String syubetsu08KinZen_COLUMN = "SYUBETSU08_KIN_ZEN";
    
    public static final String syubetsu09KenZen_COLUMN = "SYUBETSU09_KEN_ZEN";
    
    public static final String syubetsu09KinZen_COLUMN = "SYUBETSU09_KIN_ZEN";
    
    public static final String syubetsu10KenZen_COLUMN = "SYUBETSU10_KEN_ZEN";
    
    public static final String syubetsu10KinZen_COLUMN = "SYUBETSU10_KIN_ZEN";
    
    public static final String nettakeKenZen_COLUMN = "NETTAKE_KEN_ZEN";
    
    public static final String nettakeKinZen_COLUMN = "NETTAKE_KIN_ZEN";
    
    public static final String nettakuhaiKenZen_COLUMN = "NETTAKUHAI_KEN_ZEN";
    
    public static final String nettakuhaiKinZen_COLUMN = "NETTAKUHAI_KIN_ZEN";
    
    public static final String syubetsu13KenZen_COLUMN = "SYUBETSU13_KEN_ZEN";
    
    public static final String syubetsu13KinZen_COLUMN = "SYUBETSU13_KIN_ZEN";
    
    public static final String syubetsu14KenZen_COLUMN = "SYUBETSU14_KEN_ZEN";
    
    public static final String syubetsu14KinZen_COLUMN = "SYUBETSU14_KIN_ZEN";
    
    public static final String syubetsu15KenZen_COLUMN = "SYUBETSU15_KEN_ZEN";
    
    public static final String syubetsu15KinZen_COLUMN = "SYUBETSU15_KIN_ZEN";
    
    public static final String otherKenZen_COLUMN = "OTHER_KEN_ZEN";
    
    public static final String otherKinZen_COLUMN = "OTHER_KIN_ZEN";
/**    
    public static final String eatKenZennenhi_COLUMN = "EAT_KEN_ZENNENHI";
    
    public static final String eatKinZennenhi_COLUMN = "EAT_KIN_ZENNENHI";
    
    public static final String takeKenZennenhi_COLUMN = "TAKE_KEN_ZENNENHI";
    
    public static final String takeKinZennenhi_COLUMN = "TAKE_KIN_ZENNENHI";
    
    public static final String telKenZennenhi_COLUMN = "TEL_KEN_ZENNENHI";
    
    public static final String telKinZennenhi_COLUMN = "TEL_KIN_ZENNENHI";
    
    public static final String driveKenZennenhi_COLUMN = "DRIVE_KEN_ZENNENHI";
    
    public static final String driveKinZennenhi_COLUMN = "DRIVE_KIN_ZENNENHI";
    
    public static final String takuhaiKenZennenhi_COLUMN = "TAKUHAI_KEN_ZENNENHI";
    
    public static final String takuhaiKinZennenhi_COLUMN = "TAKUHAI_KIN_ZENNENHI";
    
    public static final String otherKenZennenhi_COLUMN = "OTHER_KEN_ZENNENHI";
    
    public static final String otherKinZennenhi_COLUMN = "OTHER_KIN_ZENNENHI";
    
    public static final String shubetuKeiKen_COLUMN = "SHUBETU_KEI_KEN";
    
    public static final String shubetuKeiKenZen_COLUMN = "SHUBETU_KEI_KEN_ZEN";
    
    public static final String shubetuKeiKenZennenhi_COLUMN = "SHUBETU_KEI_KEN_ZENNENHI";
    
    public static final String shubetuKeiKin_COLUMN = "SHUBETU_KEI_KIN";
    
    public static final String shubetuKeiKinZen_COLUMN = "SHUBETU_KEI_KIN_ZEN";
    
    public static final String shubetuKeiKinZennenhi_COLUMN = "SHUBETU_KEI_KIN_ZENNENHI";
*/    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 店舗カウント
     */
    private BigDecimal miseCnt;
    
    /**
     * EAT-IN件数
     */
    private BigDecimal eatKen;
    
    /**
     * EAT-IN金額
     */
    private BigDecimal eatKin;
    
    /**
     * TAKE-OUT件数
     */
    private BigDecimal takeKen;
    
    /**
     * TAKE-OUT金額
     */
    private BigDecimal takeKin;
    
    /**
     * TEL-ORDER件数
     */
    private BigDecimal telKen;
    
    /**
     * TEL-ORDER金額
     */
    private BigDecimal telKin;
    
    /**
     * DRIVE-TH件数
     */
    private BigDecimal driveKen;
    
    /**
     * DRIVE-TH金額
     */
    private BigDecimal driveKin;
    
    /**
     * 宅配件数
     */
    private BigDecimal takuhaiKen;
    
    /**
     * 宅配金額
     */
    private BigDecimal takuhaiKin;
    
    /**
     * 外販件数
     */
    private BigDecimal gaihanKen;
    
    /**
     * 外販金額
     */
    private BigDecimal gaihanKin;
    
    /**
     * 種別７件数
     */
    private BigDecimal syubetsu07Ken;
    
    /**
     * 種別７金額
     */
    private BigDecimal syubetsu07Kin;
    
    /**
     * 種別８件数
     */
    private BigDecimal syubetsu08Ken;
    
    /**
     * 種別８金額
     */
    private BigDecimal syubetsu08Kin;
    
    /**
     * 種別９件数
     */
    private BigDecimal syubetsu09Ken;
    
    /**
     * 種別９金額
     */
    private BigDecimal syubetsu09Kin;
    
    /**
     * 種別１０件数
     */
    private BigDecimal syubetsu10Ken;
    
    /**
     * 種別１０金額
     */
    private BigDecimal syubetsu10Kin;
    
    /**
     * ネットテイク件数
     */
    private BigDecimal nettakeKen;
    
    /**
     * ネットテイク金額
     */
    private BigDecimal nettakeKin;
    
    /**
     * ネット宅配件数
     */
    private BigDecimal nettakuhaiKen;
    
    /**
     * ネット宅配金額
     */
    private BigDecimal nettakuhaiKin;
    
    /**
     * 種別１３件数
     */
    private BigDecimal syubetsu13Ken;
    
    /**
     * 種別１３金額
     */
    private BigDecimal syubetsu13Kin;
    
    /**
     * 種別１４件数
     */
    private BigDecimal syubetsu14Ken;
    
    /**
     * 種別１４金額
     */
    private BigDecimal syubetsu14Kin;
    
    /**
     * 種別１５件数
     */
    private BigDecimal syubetsu15Ken;
    
    /**
     * 種別１５金額
     */
    private BigDecimal syubetsu15Kin;
    
    /**
     * 外販・その他件数
     */
    private BigDecimal otherKen;
    
    /**
     * 外販・その他金額
     */
    private BigDecimal otherKin;
    
    /**
     * EAT-IN件数（前年）
     */
    private BigDecimal eatKenZen;
    
    /**
     * EAT-IN金額（前年）
     */
    private BigDecimal eatKinZen;
    
    /**
     * TAKE-OUT件数（前年）
     */
    private BigDecimal takeKenZen;
    
    /**
     * TAKE-OUT金額（前年）
     */
    private BigDecimal takeKinZen;
    
    /**
     * TEL-ORDER件数（前年）
     */
    private BigDecimal telKenZen;
    
    /**
     * TEL-ORDER金額（前年）
     */
    private BigDecimal telKinZen;
    
    /**
     * DRIVE-TH件数（前年）
     */
    private BigDecimal driveKenZen;
    
    /**
     * DRIVE-TH金額（前年）
     */
    private BigDecimal driveKinZen;
    
    /**
     * 宅配件数（前年）
     */
    private BigDecimal takuhaiKenZen;
    
    /**
     * 宅配金額（前年）
     */
    private BigDecimal takuhaiKinZen;
    
    /**
     * 外販件数（前年）
     */
    private BigDecimal gaihanKenZen;
    
    /**
     * 外販金額（前年）
     */
    private BigDecimal gaihanKinZen;
    
    /**
     * 種別７件数（前年）
     */
    private BigDecimal syubetsu07KenZen;
    
    /**
     * 種別７金額（前年）
     */
    private BigDecimal syubetsu07KinZen;
    
    /**
     * 種別８件数（前年）
     */
    private BigDecimal syubetsu08KenZen;
    
    /**
     * 種別８金額（前年）
     */
    private BigDecimal syubetsu08KinZen;
    
    /**
     * 種別９件数（前年）
     */
    private BigDecimal syubetsu09KenZen;
    
    /**
     * 種別９金額（前年）
     */
    private BigDecimal syubetsu09KinZen;
    
    /**
     * 種別１０件数（前年）
     */
    private BigDecimal syubetsu10KenZen;
    
    /**
     * 種別１０金額（前年）
     */
    private BigDecimal syubetsu10KinZen;
    
    /**
     * ネットテイク件数（前年）
     */
    private BigDecimal nettakeKenZen;
    
    /**
     * ネットテイク金額（前年）
     */
    private BigDecimal nettakeKinZen;
    
    /**
     * ネット宅配件数（前年）
     */
    private BigDecimal nettakuhaiKenZen;
    
    /**
     * ネット宅配金額（前年）
     */
    private BigDecimal nettakuhaiKinZen;
    
    /**
     * 種別１３件数（前年）
     */
    private BigDecimal syubetsu13KenZen;
    
    /**
     * 種別１３金額（前年）
     */
    private BigDecimal syubetsu13KinZen;
    
    /**
     * 種別１４件数（前年）
     */
    private BigDecimal syubetsu14KenZen;
    
    /**
     * 種別１４金額（前年）
     */
    private BigDecimal syubetsu14KinZen;
    
    /**
     * 種別１５件数（前年）
     */
    private BigDecimal syubetsu15KenZen;
    
    /**
     * 種別１５金額（前年）
     */
    private BigDecimal syubetsu15KinZen;
    
    /**
     * 外販・その他件数（前年）
     */
    private BigDecimal otherKenZen;
    
    /**
     * 外販・その他金額（前年）
     */
    private BigDecimal otherKinZen;
    
//    /**
//     * 種別計件数
//     */
//    private BigDecimal shubetuKeiKen;
//    
//    /**
//     * 種別計件数（前年）
//     */
//    private BigDecimal shubetuKeiKenZen;
//    
//    /**
//     * 種別計金額
//     */
//    private BigDecimal shubetuKeiKin;
//    
//    /**
//     * 種別計金額（前年）
//     */
//    private BigDecimal shubetuKeiKinZen;
    
    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * EAT-IN件数を取得します。
     * @return EAT-IN件数
     */
    public BigDecimal getEatKen() {
        return eatKen;
    }
    /**
     * EAT-IN件数を設定します。
     * @param eatKen EAT-IN件数
     */
    public void setEatKen(BigDecimal eatKen) {
        this.eatKen = eatKen;
    }
    
    /**
     * EAT-IN金額を取得します。
     * @return EAT-IN金額
     */
    public BigDecimal getEatKin() {
        return eatKin;
    }
    /**
     * EAT-IN金額を設定します。
     * @param eatKin EAT-IN金額
     */
    public void setEatKin(BigDecimal eatKin) {
        this.eatKin = eatKin;
    }
    
    /**
     * TAKE-OUT件数を取得します。
     * @return TAKE-OUT件数
     */
    public BigDecimal getTakeKen() {
        return takeKen;
    }
    /**
     * TAKE-OUT件数を設定します。
     * @param takeKen TAKE-OUT件数
     */
    public void setTakeKen(BigDecimal takeKen) {
        this.takeKen = takeKen;
    }
    
    /**
     * TAKE-OUT金額を取得します。
     * @return TAKE-OUT金額
     */
    public BigDecimal getTakeKin() {
        return takeKin;
    }
    /**
     * TAKE-OUT金額を設定します。
     * @param takeKin TAKE-OUT金額
     */
    public void setTakeKin(BigDecimal takeKin) {
        this.takeKin = takeKin;
    }
    
    /**
     * TEL-ORDER件数を取得します。
     * @return TEL-ORDER件数
     */
    public BigDecimal getTelKen() {
        return telKen;
    }
    /**
     * TEL-ORDER件数を設定します。
     * @param telKen TEL-ORDER件数
     */
    public void setTelKen(BigDecimal telKen) {
        this.telKen = telKen;
    }
    
    /**
     * TEL-ORDER金額を取得します。
     * @return TEL-ORDER金額
     */
    public BigDecimal getTelKin() {
        return telKin;
    }
    /**
     * TEL-ORDER金額を設定します。
     * @param telKin TEL-ORDER金額
     */
    public void setTelKin(BigDecimal telKin) {
        this.telKin = telKin;
    }
    
    /**
     * DRIVE-TH件数を取得します。
     * @return DRIVE-TH件数
     */
    public BigDecimal getDriveKen() {
        return driveKen;
    }
    /**
     * DRIVE-TH件数を設定します。
     * @param driveKen DRIVE-TH件数
     */
    public void setDriveKen(BigDecimal driveKen) {
        this.driveKen = driveKen;
    }
    
    /**
     * DRIVE-TH金額を取得します。
     * @return DRIVE-TH金額
     */
    public BigDecimal getDriveKin() {
        return driveKin;
    }
    /**
     * DRIVE-TH金額を設定します。
     * @param driveKin DRIVE-TH金額
     */
    public void setDriveKin(BigDecimal driveKin) {
        this.driveKin = driveKin;
    }
    
    /**
     * 宅配件数を取得します。
     * @return 宅配件数
     */
    public BigDecimal getTakuhaiKen() {
        return takuhaiKen;
    }
    /**
     * 宅配件数を設定します。
     * @param takuhaiKen 宅配件数
     */
    public void setTakuhaiKen(BigDecimal takuhaiKen) {
        this.takuhaiKen = takuhaiKen;
    }
    
    /**
     * 宅配金額を取得します。
     * @return 宅配金額
     */
    public BigDecimal getTakuhaiKin() {
        return takuhaiKin;
    }
    /**
     * 宅配金額を設定します。
     * @param takuhaiKin 宅配金額
     */
    public void setTakuhaiKin(BigDecimal takuhaiKin) {
        this.takuhaiKin = takuhaiKin;
    }
    
    /**
     * 外販件数を取得します。
     * @return 外販件数
     */
    public BigDecimal getGaihanKen() {
        return gaihanKen;
    }
    /**
     * 外販件数を設定します。
     * @param gaihanKen 外販件数
     */
    public void setGaihanKen(BigDecimal gaihanKen) {
        this.gaihanKen = gaihanKen;
    }
    
    /**
     * 外販金額を取得します。
     * @return 外販金額
     */
    public BigDecimal getGaihanKin() {
        return gaihanKin;
    }
    /**
     * 外販金額を設定します。
     * @param gaihanKin 外販金額
     */
    public void setGaihanKin(BigDecimal gaihanKin) {
        this.gaihanKin = gaihanKin;
    }
    
    /**
     * 種別７件数を取得します。
     * @return 種別７件数
     */
    public BigDecimal getSyubetsu07Ken() {
        return syubetsu07Ken;
    }
    /**
     * 種別７件数を設定します。
     * @param syubetsu07Ken 種別７件数
     */
    public void setSyubetsu07Ken(BigDecimal syubetsu07Ken) {
        this.syubetsu07Ken = syubetsu07Ken;
    }
    
    /**
     * 種別７金額を取得します。
     * @return 種別７金額
     */
    public BigDecimal getSyubetsu07Kin() {
        return syubetsu07Kin;
    }
    /**
     * 種別７金額を設定します。
     * @param syubetsu07Kin 種別７金額
     */
    public void setSyubetsu07Kin(BigDecimal syubetsu07Kin) {
        this.syubetsu07Kin = syubetsu07Kin;
    }
    
    /**
     * 種別８件数を取得します。
     * @return 種別８件数
     */
    public BigDecimal getSyubetsu08Ken() {
        return syubetsu08Ken;
    }
    /**
     * 種別８件数を設定します。
     * @param syubetsu08Ken 種別８件数
     */
    public void setSyubetsu08Ken(BigDecimal syubetsu08Ken) {
        this.syubetsu08Ken = syubetsu08Ken;
    }
    
    /**
     * 種別８金額を取得します。
     * @return 種別８金額
     */
    public BigDecimal getSyubetsu08Kin() {
        return syubetsu08Kin;
    }
    /**
     * 種別８金額を設定します。
     * @param syubetsu08Kin 種別８金額
     */
    public void setSyubetsu08Kin(BigDecimal syubetsu08Kin) {
        this.syubetsu08Kin = syubetsu08Kin;
    }
    
    /**
     * 種別９件数を取得します。
     * @return 種別９件数
     */
    public BigDecimal getSyubetsu09Ken() {
        return syubetsu09Ken;
    }
    /**
     * 種別９件数を設定します。
     * @param syubetsu08Ken 種別９件数
     */
    public void setSyubetsu09Ken(BigDecimal syubetsu09Ken) {
        this.syubetsu09Ken = syubetsu09Ken;
    }
    
    /**
     * 種別９金額を取得します。
     * @return 種別９金額
     */
    public BigDecimal getSyubetsu09Kin() {
        return syubetsu09Kin;
    }
    /**
     * 種別９金額を設定します。
     * @param syubetsu09Kin 種別９金額
     */
    public void setSyubetsu09Kin(BigDecimal syubetsu09Kin) {
        this.syubetsu09Kin = syubetsu09Kin;
    }
    
    /**
     * 種別１０件数を取得します。
     * @return 種別１０件数
     */
    public BigDecimal getSyubetsu10Ken() {
        return syubetsu10Ken;
    }
    /**
     * 種別１０件数を設定します。
     * @param syubetsu10Ken 種別１０件数
     */
    public void setSyubetsu10Ken(BigDecimal syubetsu10Ken) {
        this.syubetsu10Ken = syubetsu10Ken;
    }
    
    /**
     * 種別１０金額を取得します。
     * @return 種別１０金額
     */
    public BigDecimal getSyubetsu10Kin() {
        return syubetsu10Kin;
    }
    /**
     * 種別１０金額を設定します。
     * @param syubetsu10Kin 種別１０金額
     */
    public void setSyubetsu10Kin(BigDecimal syubetsu10Kin) {
        this.syubetsu10Kin = syubetsu10Kin;
    }
    
    /**
     * ネットテイク件数を取得します。
     * @return ネットテイク件数
     */
    public BigDecimal getNettakeKen() {
        return nettakeKen;
    }
    /**
     * ネットテイク件数を設定します。
     * @param nettakeKen ネットテイク件数
     */
    public void setNettakeKen(BigDecimal nettakeKen) {
        this.nettakeKen = nettakeKen;
    }
    
    /**
     * ネットテイク金額を取得します。
     * @return ネットテイク金額
     */
    public BigDecimal getNettakeKin() {
        return nettakeKin;
    }
    /**
     * ネットテイク金額を設定します。
     * @param nettakeKin ネットテイク金額
     */
    public void setNettakeKin(BigDecimal nettakeKin) {
        this.nettakeKin = nettakeKin;
    }
    
    /**
     * ネット宅配件数を取得します。
     * @return ネット宅配件数
     */
    public BigDecimal getNettakuhaiKen() {
        return nettakuhaiKen;
    }
    /**
     * ネット宅配件数を設定します。
     * @param nettakuhaiKen ネット宅配件数
     */
    public void setNettakuhaiKen(BigDecimal nettakuhaiKen) {
        this.nettakuhaiKen = nettakuhaiKen;
    }
    
    /**
     * ネット宅配金額を取得します。
     * @return ネット宅配金額
     */
    public BigDecimal getNettakuhaiKin() {
        return nettakuhaiKin;
    }
    /**
     * ネット宅配金額を設定します。
     * @param nettakuhaiKin ネット宅配金額
     */
    public void setNettakuhaiKin(BigDecimal nettakuhaiKin) {
        this.nettakuhaiKin = nettakuhaiKin;
    }
    
    /**
     * 種別１３件数を取得します。
     * @return 種別１３件数
     */
    public BigDecimal getSyubetsu13Ken() {
        return syubetsu13Ken;
    }
    /**
     * 種別１３件数を設定します。
     * @param syubetsu13Ken 種別１３件数
     */
    public void setSyubetsu13Ken(BigDecimal syubetsu13Ken) {
        this.syubetsu13Ken = syubetsu13Ken;
    }
    
    /**
     * 種別１３金額を取得します。
     * @return 種別１３金額
     */
    public BigDecimal getSyubetsu13Kin() {
        return syubetsu13Kin;
    }
    /**
     * 種別１３金額を設定します。
     * @param syubetsu13Kin 種別１３金額
     */
    public void setSyubetsu13Kin(BigDecimal syubetsu13Kin) {
        this.syubetsu13Kin = syubetsu13Kin;
    }
    
    /**
     * 種別１４件数を取得します。
     * @return 種別１４件数
     */
    public BigDecimal getSyubetsu14Ken() {
        return syubetsu14Ken;
    }
    /**
     * 種別１４件数を設定します。
     * @param syubetsu14Ken 種別１４件数
     */
    public void setSyubetsu14Ken(BigDecimal syubetsu14Ken) {
        this.syubetsu14Ken = syubetsu14Ken;
    }
    
    /**
     * 種別１４金額を取得します。
     * @return 種別１４金額
     */
    public BigDecimal getSyubetsu14Kin() {
        return syubetsu14Kin;
    }
    /**
     * 種別１４金額を設定します。
     * @param syubetsu14Kin 種別１４金額
     */
    public void setSyubetsu14Kin(BigDecimal syubetsu14Kin) {
        this.syubetsu14Kin = syubetsu14Kin;
    }
    
    /**
     * 種別１５件数を取得します。
     * @return 種種別１５件数
     */
    public BigDecimal getSyubetsu15Ken() {
        return syubetsu15Ken;
    }
    /**
     * 種別１５件数を設定します。
     * @param syubetsu15Ken 種別１５件数
     */
    public void setSyubetsu15Ken(BigDecimal syubetsu15Ken) {
        this.syubetsu15Ken = syubetsu15Ken;
    }
    
    /**
     * 種別１５金額を取得します。
     * @return 種別１５金額
     */
    public BigDecimal getSyubetsu15Kin() {
        return syubetsu15Kin;
    }
    /**
     * 種別１５金額を設定します。
     * @param syubetsu15Kin 種別１５金額
     */
    public void setSyubetsu15Kin(BigDecimal syubetsu15Kin) {
        this.syubetsu15Kin = syubetsu15Kin;
    }
    
    /**
     * 外販・その他件数を取得します。
     * @return 外販・その他件数
     */
    public BigDecimal getOtherKen() {
        return otherKen;
    }
    /**
     * 外販・その他件数を設定します。
     * @param otherKen 外販・その他件数
     */
    public void setOtherKen(BigDecimal otherKen) {
        this.otherKen = otherKen;
    }
    
    /**
     * 外販・その他金額を取得します。
     * @return 外販・その他金額
     */
    public BigDecimal getOtherKin() {
        return otherKin;
    }
    /**
     * 外販・その他金額を設定します。
     * @param otherKin 外販・その他金額
     */
    public void setOtherKin(BigDecimal otherKin) {
        this.otherKin = otherKin;
    }
    
    /**
     * EAT-IN件数（前年）を取得します。
     * @return EAT-IN件数（前年）
     */
    public BigDecimal getEatKenZen() {
        return eatKenZen;
    }
    /**
     * EAT-IN件数（前年）を設定します。
     * @param eatKenZen EAT-IN件数（前年）
     */
    public void setEatKenZen(BigDecimal eatKenZen) {
        this.eatKenZen = eatKenZen;
    }
    
    /**
     * EAT-IN金額（前年）を取得します。
     * @return EAT-IN金額（前年）
     */
    public BigDecimal getEatKinZen() {
        return eatKinZen;
    }
    /**
     * EAT-IN金額（前年）を設定します。
     * @param eatKinZen EAT-IN金額（前年）
     */
    public void setEatKinZen(BigDecimal eatKinZen) {
        this.eatKinZen = eatKinZen;
    }
    
    /**
     * TAKE-OUT件数（前年）を取得します。
     * @return TAKE-OUT件数（前年）
     */
    public BigDecimal getTakeKenZen() {
        return takeKenZen;
    }
    /**
     * TAKE-OUT件数（前年）を設定します。
     * @param takeKenZen TAKE-OUT件数（前年）
     */
    public void setTakeKenZen(BigDecimal takeKenZen) {
        this.takeKenZen = takeKenZen;
    }
    
    /**
     * TAKE-OUT金額（前年）を取得します。
     * @return TAKE-OUT金額（前年）
     */
    public BigDecimal getTakeKinZen() {
        return takeKinZen;
    }
    /**
     * TAKE-OUT金額（前年）を設定します。
     * @param takeKinZen TAKE-OUT金額（前年）
     */
    public void setTakeKinZen(BigDecimal takeKinZen) {
        this.takeKinZen = takeKinZen;
    }
    
    /**
     * TEL-ORDER件数（前年）を取得します。
     * @return TEL-ORDER件数（前年）
     */
    public BigDecimal getTelKenZen() {
        return telKenZen;
    }
    /**
     * TEL-ORDER件数（前年）を設定します。
     * @param telKenZen TEL-ORDER件数（前年）
     */
    public void setTelKenZen(BigDecimal telKenZen) {
        this.telKenZen = telKenZen;
    }
    
    /**
     * TEL-ORDER金額（前年）を取得します。
     * @return TEL-ORDER金額（前年）
     */
    public BigDecimal getTelKinZen() {
        return telKinZen;
    }
    /**
     * TEL-ORDER金額（前年）を設定します。
     * @param telKinZen TEL-ORDER金額（前年）
     */
    public void setTelKinZen(BigDecimal telKinZen) {
        this.telKinZen = telKinZen;
    }
    
    /**
     * DRIVE-TH件数（前年）を取得します。
     * @return DRIVE-TH件数（前年）
     */
    public BigDecimal getDriveKenZen() {
        return driveKenZen;
    }
    /**
     * DRIVE-TH件数（前年）を設定します。
     * @param driveKenZen DRIVE-TH件数（前年）
     */
    public void setDriveKenZen(BigDecimal driveKenZen) {
        this.driveKenZen = driveKenZen;
    }
    
    /**
     * DRIVE-TH金額（前年）を取得します。
     * @return DRIVE-TH金額（前年）
     */
    public BigDecimal getDriveKinZen() {
        return driveKinZen;
    }
    /**
     * DRIVE-TH金額（前年）を設定します。
     * @param driveKinZen DRIVE-TH金額（前年）
     */
    public void setDriveKinZen(BigDecimal driveKinZen) {
        this.driveKinZen = driveKinZen;
    }
    
    /**
     * 宅配件数（前年）を取得します。
     * @return 宅配件数（前年）
     */
    public BigDecimal getTakuhaiKenZen() {
        return takuhaiKenZen;
    }
    /**
     * 宅配件数（前年）を設定します。
     * @param takuhaiKenZen 宅配件数（前年）
     */
    public void setTakuhaiKenZen(BigDecimal takuhaiKenZen) {
        this.takuhaiKenZen = takuhaiKenZen;
    }
    
    /**
     * 宅配金額（前年）を取得します。
     * @return 宅配金額（前年）
     */
    public BigDecimal getTakuhaiKinZen() {
        return takuhaiKinZen;
    }
    /**
     * 宅配金額（前年）を設定します。
     * @param takuhaiKinZen 宅配金額（前年）
     */
    public void setTakuhaiKinZen(BigDecimal takuhaiKinZen) {
        this.takuhaiKinZen = takuhaiKinZen;
    }
    
    /**
     * 外販件数（前年）を取得します。
     * @return 外販件数（前年）
     */
    public BigDecimal getGaihanKenZen() {
        return gaihanKenZen;
    }
    /**
     * 外販件数（前年）を設定します。
     * @param gaihanKenZen 外販件数（前年）
     */
    public void setGaihanKenZen(BigDecimal gaihanKenZen) {
        this.gaihanKenZen = gaihanKenZen;
    }
    
    /**
     * 外販金額（前年）を取得します。
     * @return 外販金額（前年）
     */
    public BigDecimal getGaihanKinZen() {
        return gaihanKinZen;
    }
    /**
     * 外販金額（前年）を設定します。
     * @param gaihanKinZen 外販金額（前年）
     */
    public void setGaihanKinZen(BigDecimal gaihanKinZen) {
        this.gaihanKinZen = gaihanKinZen;
    }
    
    /**
     * 種別７件数（前年）を取得します。
     * @return 種別７件数（前年）
     */
    public BigDecimal getSyubetsu07KenZen() {
        return syubetsu07KenZen;
    }
    /**
     * 種別７件数（前年）を設定します。
     * @param syubetsu07KenZen 種別７件数（前年）
     */
    public void setSyubetsu07KenZen(BigDecimal syubetsu07KenZen) {
        this.syubetsu07KenZen = syubetsu07KenZen;
    }
    
    /**
     * 種別７金額（前年）を取得します。
     * @return 種別７金額（前年）
     */
    public BigDecimal getSyubetsu07KinZen() {
        return syubetsu07KinZen;
    }
    /**
     * 種別７金額（前年）を設定します。
     * @param syubetsu07KinZen 種別７金額（前年）
     */
    public void setSyubetsu07KinZen(BigDecimal syubetsu07KinZen) {
        this.syubetsu07KinZen = syubetsu07KinZen;
    }
    
    /**
     * 種別８件数（前年）を取得します。
     * @return 種別８件数（前年）
     */
    public BigDecimal getSyubetsu08KenZen() {
        return syubetsu08KenZen;
    }
    /**
     * 種別８件数（前年）を設定します。
     * @param syubetsu08KenZen 種別８件数（前年）
     */
    public void setSyubetsu08KenZen(BigDecimal syubetsu08KenZen) {
        this.syubetsu08KenZen = syubetsu08KenZen;
    }
    
    /**
     * 種別８金額（前年）を取得します。
     * @return 種別８金額（前年）
     */
    public BigDecimal getSyubetsu08KinZen() {
        return syubetsu08KinZen;
    }
    /**
     * 種別８金額（前年）を設定します。
     * @param syubetsu08KinZen 種別８金額（前年）
     */
    public void setSyubetsu08KinZen(BigDecimal syubetsu08KinZen) {
        this.syubetsu08KinZen = syubetsu08KinZen;
    }
    
    /**
     * 種別９件数（前年）を取得します。
     * @return 種別９件数（前年）
     */
    public BigDecimal getSyubetsu09KenZen() {
        return syubetsu09KenZen;
    }
    /**
     * 種別９件数（前年）を設定します。
     * @param syubetsu08KenZen 種別９件数（前年）
     */
    public void setSyubetsu09KenZen(BigDecimal syubetsu09KenZen) {
        this.syubetsu09KenZen = syubetsu09KenZen;
    }
    
    /**
     * 種別９金額（前年）を取得します。
     * @return 種別９金額（前年）
     */
    public BigDecimal getSyubetsu09KinZen() {
        return syubetsu09KinZen;
    }
    /**
     * 種別９金額（前年）を設定します。
     * @param syubetsu09KinZen 種別９金額（前年）
     */
    public void setSyubetsu09KinZen(BigDecimal syubetsu09KinZen) {
        this.syubetsu09KinZen = syubetsu09KinZen;
    }
    
    /**
     * 種別１０件数（前年）を取得します。
     * @return 種別１０件数（前年）
     */
    public BigDecimal getSyubetsu10KenZen() {
        return syubetsu10KenZen;
    }
    /**
     * 種別１０件数（前年）を設定します。
     * @param syubetsu10Ken 種別１０件数（前年）
     */
    public void setSyubetsu10KenZen(BigDecimal syubetsu10KenZen) {
        this.syubetsu10KenZen = syubetsu10KenZen;
    }
    
    /**
     * 種別１０金額（前年）を取得します。
     * @return 種別１０金額（前年）
     */
    public BigDecimal getSyubetsu10KinZen() {
        return syubetsu10KinZen;
    }
    /**
     * 種別１０金額（前年）を設定します。
     * @param syubetsu10KinZen 種別１０金額（前年）
     */
    public void setSyubetsu10KinZen(BigDecimal syubetsu10KinZen) {
        this.syubetsu10KinZen = syubetsu10KinZen;
    }
    
    /**
     * ネットテイク件数（前年）を取得します。
     * @return ネットテイク件数（前年）
     */
    public BigDecimal getNettakeKenZen() {
        return nettakeKenZen;
    }
    /**
     * ネットテイク件数（前年）を設定します。
     * @param nettakeKenZen ネットテイク件数（前年）
     */
    public void setNettakeKenZen(BigDecimal nettakeKenZen) {
        this.nettakeKenZen = nettakeKenZen;
    }
    
    /**
     * ネットテイク金額（前年）を取得します。
     * @return ネットテイク金額（前年）
     */
    public BigDecimal getNettakeKinZen() {
        return nettakeKinZen;
    }
    /**
     * ネットテイク金額（前年）を設定します。
     * @param nettakeKinZen ネットテイク金額（前年）
     */
    public void setNettakeKinZen(BigDecimal nettakeKinZen) {
        this.nettakeKinZen = nettakeKinZen;
    }
    
    /**
     * ネット宅配件数（前年）を取得します。
     * @return ネット宅配件数（前年）
     */
    public BigDecimal getNettakuhaiKenZen() {
        return nettakuhaiKenZen;
    }
    /**
     * ネット宅配件数（前年）を設定します。
     * @param nettakuhaiKenZen ネット宅配件数（前年）
     */
    public void setNettakuhaiKenZen(BigDecimal nettakuhaiKenZen) {
        this.nettakuhaiKenZen = nettakuhaiKenZen;
    }
    
    /**
     * ネット宅配金額（前年）を取得します。
     * @return ネット宅配金額（前年）
     */
    public BigDecimal getNettakuhaiKinZen() {
        return nettakuhaiKinZen;
    }
    /**
     * ネット宅配金額（前年）を設定します。
     * @param nettakuhaiKinZen ネット宅配金額（前年）
     */
    public void setNettakuhaiKinZen(BigDecimal nettakuhaiKinZen) {
        this.nettakuhaiKinZen = nettakuhaiKinZen;
    }
    
    /**
     * 種別１３件数（前年）を取得します。
     * @return 種別１３件数（前年）
     */
    public BigDecimal getSyubetsu13KenZen() {
        return syubetsu13KenZen;
    }
    /**
     * 種別１３件数（前年）を設定します。
     * @param syubetsu13KenZen 種別１３件数（前年）
     */
    public void setSyubetsu13KenZen(BigDecimal syubetsu13KenZen) {
        this.syubetsu13KenZen = syubetsu13KenZen;
    }
    
    /**
     * 種別１３金額（前年）を取得します。
     * @return 種別１３金額（前年）
     */
    public BigDecimal getSyubetsu13KinZen() {
        return syubetsu13KinZen;
    }
    /**
     * 種別１３金額（前年）を設定します。
     * @param syubetsu13KinZen 種別１３金額（前年）
     */
    public void setSyubetsu13KinZen(BigDecimal syubetsu13KinZen) {
        this.syubetsu13KinZen = syubetsu13KinZen;
    }
    
    /**
     * 種別１４件数（前年）を取得します。
     * @return 種別１４件数（前年）
     */
    public BigDecimal getSyubetsu14KenZen() {
        return syubetsu14KenZen;
    }
    /**
     * 種別１４件数（前年）を設定します。
     * @param syubetsu14KenZen 種別１４件数（前年）
     */
    public void setSyubetsu14KenZen(BigDecimal syubetsu14KenZen) {
        this.syubetsu14KenZen = syubetsu14KenZen;
    }
    
    /**
     * 種別１４金額（前年）を取得します。
     * @return 種別１４金額（前年）
     */
    public BigDecimal getSyubetsu14KinZen() {
        return syubetsu14KinZen;
    }
    /**
     * 種別１４金額（前年）を設定します。
     * @param syubetsu14KinZen 種別１４金額（前年）
     */
    public void setSyubetsu14KinZen(BigDecimal syubetsu14KinZen) {
        this.syubetsu14KinZen = syubetsu14KinZen;
    }
    
    /**
     * 種別１５件数（前年）を取得します。
     * @return 種種別１５件数（前年）
     */
    public BigDecimal getSyubetsu15KenZen() {
        return syubetsu15KenZen;
    }
    /**
     * 種別１５件数（前年）を設定します。
     * @param syubetsu15KenZen 種別１５件数（前年）
     */
    public void setSyubetsu15KenZen(BigDecimal syubetsu15KenZen) {
        this.syubetsu15KenZen = syubetsu15KenZen;
    }
    
    /**
     * 種別１５金額（前年）を取得します。
     * @return 種別１５金額（前年）
     */
    public BigDecimal getSyubetsu15KinZen() {
        return syubetsu15KinZen;
    }
    /**
     * 種別１５金額（前年）を設定します。
     * @param syubetsu15Kin 種別１５金額（前年）
     */
    public void setSyubetsu15KinZen(BigDecimal syubetsu15KinZen) {
        this.syubetsu15KinZen = syubetsu15KinZen;
    }
    
    /**
     * 外販・その他件数（前年）を取得します。
     * @return 外販・その他件数（前年）
     */
    public BigDecimal getOtherKenZen() {
        return otherKenZen;
    }
    /**
     * 外販・その他件数（前年）を設定します。
     * @param otherKenZen 外販・その他件数（前年）
     */
    public void setOtherKenZen(BigDecimal otherKenZen) {
        this.otherKenZen = otherKenZen;
    }
    
    /**
     * 外販・その他金額（前年）を取得します。
     * @return 外販・その他金額（前年）
     */
    public BigDecimal getOtherKinZen() {
        return otherKinZen;
    }
    /**
     * 外販・その他金額（前年）を設定します。
     * @param otherKinZen 外販・その他金額（前年）
     */
    public void setOtherKinZen(BigDecimal otherKinZen) {
        this.otherKinZen = otherKinZen;
    }
    
    /**
     * EAT-IN件数（前年比）を取得します。
     * @return EAT-IN件数（前年比）
     */
    public BigDecimal getEatKenZennenhi() {
        return Calculator.percentage(getEatKen(), getEatKenZen(), 2);
    }
    /**
     * EAT-IN件数（前年比）を設定します。
     * @param eatKenZennenhi EAT-IN件数（前年比）
     */
    public String getEatKenZennenhiCls() {
        return getHirituClass(getEatKenZennenhi());
    }
    
    /**
     * EAT-IN金額（前年比）を取得します。
     * @return EAT-IN金額（前年比）
     */
    public BigDecimal getEatKinZennenhi() {
        return Calculator.percentage(getEatKin(), getEatKinZen(), 2);
    }
    /**
     * EAT-IN金額（前年比）を設定します。
     * @param eatKinZennenhi EAT-IN金額（前年比）
     */
    public String getEatKinZennenhiCls() {
        return getHirituClass(getEatKinZennenhi());
    }
    
    /**
     * TAKE-OUT件数（前年比）を取得します。
     * @return TAKE-OUT件数（前年比）
     */
    public BigDecimal getTakeKenZennenhi() {
        return Calculator.percentage(getTakeKen(), getTakeKenZen(), 2);
    }
    /**
     * TAKE-OUT件数（前年比）を設定します。
     * @param takeKenZennenhi TAKE-OUT件数（前年比）
     */
    public String getTakeKenZennenhiCls() {
        return getHirituClass(getTakeKenZennenhi());
    }
    
    /**
     * TAKE-OUT金額（前年比）を取得します。
     * @return TAKE-OUT金額（前年比）
     */
    public BigDecimal getTakeKinZennenhi() {
        return Calculator.percentage(getTakeKin(), getTakeKinZen(), 2);
    }
    /**
     * TAKE-OUT金額（前年比）を設定します。
     * @param takeKinZennenhi TAKE-OUT金額（前年比）
     */
    public String getTakeKinZennenhiCls() {
        return getHirituClass(getTakeKinZennenhi());
    }
    
    /**
     * TEL-ORDER件数（前年比）を取得します。
     * @return TEL-ORDER件数（前年比）
     */
    public BigDecimal getTelKenZennenhi() {
        return Calculator.percentage(getTelKen(), getTelKenZen(), 2);
    }
    /**
     * TEL-ORDER件数（前年比）を設定します。
     * @param telKenZennenhi TEL-ORDER件数（前年比）
     */
    public String getTelKenZennenhiCls() {
        return getHirituClass(getTelKenZennenhi());
    }
    
    /**
     * TEL-ORDER金額（前年比）を取得します。
     * @return TEL-ORDER金額（前年比）
     */
    public BigDecimal getTelKinZennenhi() {
        return Calculator.percentage(getTelKin(), getTelKinZen(), 2);
    }
    /**
     * TEL-ORDER金額（前年比）を設定します。
     * @param telKinZennenhi TEL-ORDER金額（前年比）
     */
    public String getTelKinZennenhiCls() {
        return getHirituClass(getTelKinZennenhi());
    }
    
    /**
     * DRIVE-TH件数（前年比）を取得します。
     * @return DRIVE-TH件数（前年比）
     */
    public BigDecimal getDriveKenZennenhi() {
        return Calculator.percentage(getDriveKen(), getDriveKenZen(), 2);
    }
    /**
     * DRIVE-TH件数（前年比）を設定します。
     * @param driveKenZennenhi DRIVE-TH件数（前年比）
     */
    public String getDriveKenZennenhiCls() {
        return getHirituClass(getDriveKenZennenhi());
    }
    
    /**
     * DRIVE-TH金額（前年比）を取得します。
     * @return DRIVE-TH金額（前年比）
     */
    public BigDecimal getDriveKinZennenhi() {
        return Calculator.percentage(getDriveKin(), getDriveKinZen(), 2);
    }
    /**
     * DRIVE-TH金額（前年比）を設定します。
     * @param driveKinZennenhi DRIVE-TH金額（前年比）
     */
    public String getDriveKinZennenhiCls() {
        return getHirituClass(getDriveKinZennenhi());
    }
    
    /**
     * 宅配件数（前年比）を取得します。
     * @return 宅配件数（前年比）
     */
    public BigDecimal getTakuhaiKenZennenhi() {
        return Calculator.percentage(getTakuhaiKen(), getTakuhaiKenZen(), 2);
    }
    /**
     * 宅配件数（前年比）を設定します。
     * @param takuhaiKenZennenhi 宅配件数（前年比）
     */
    public String getTakuhaiKenZennenhiCls() {
        return getHirituClass(getTakuhaiKenZennenhi());
    }
    
    /**
     * 宅配金額（前年比）を取得します。
     * @return 宅配金額（前年比）
     */
    public BigDecimal getTakuhaiKinZennenhi() {
        return Calculator.percentage(getTakuhaiKin(), getTakuhaiKinZen(), 2);
    }
    /**
     * 宅配金額（前年比）を設定します。
     * @param takuhaiKinZennenhi 宅配金額（前年比）
     */
    public String getTakuhaiKinZennenhiCls() {
        return getHirituClass(getTakuhaiKinZennenhi());
    }
    
    /**
     * 外販件数（前年比）を取得します。
     * @return 外販件数（前年比）
     */
    public BigDecimal getGaihanKenZennenhi() {
        return Calculator.percentage(getGaihanKen(), getGaihanKenZen(), 2);
    }
    /**
     * 外販件数（前年比）を設定します。
     * @param gaihanKenZennenhi 外販件数（前年比）
     */
    public String getGaihanKenZennenhiCls() {
        return getHirituClass(getGaihanKenZennenhi());
    }
    
    /**
     * 外販金額（前年比）を取得します。
     * @return 外販金額（前年比）
     */
    public BigDecimal getGaihanKinZennenhi() {
        return Calculator.percentage(getGaihanKin(), getGaihanKinZen(), 2);
    }
    /**
     * 外販金額（前年比）を設定します。
     * @param gaihanKinZennenhi 外販金額（前年比）
     */
    public String getGaihanKinZennenhiCls() {
        return getHirituClass(getGaihanKinZennenhi());
    }
    
    /**
     * 種別７件数（前年比）を取得します。
     * @return 種別７件数（前年比）
     */
    public BigDecimal getSyubetsu07KenZennenhi() {
        return Calculator.percentage(getSyubetsu07Ken(), getSyubetsu07KenZen(), 2);
    }
    /**
     * 種別７件数（前年比）を設定します。
     * @param syubetsu07KenZennenhi 種別７件数（前年比）
     */
    public String getSyubetsu07KenZennenhiCls() {
        return getHirituClass(getSyubetsu07KenZennenhi());
    }
    
    /**
     * 種別７金額（前年比）を取得します。
     * @return 種別７金額（前年比）
     */
    public BigDecimal getSyubetsu07KinZennenhi() {
        return Calculator.percentage(getSyubetsu07Kin(), getSyubetsu07KinZen(), 2);
    }
    /**
     * 種別７金額（前年比）を設定します。
     * @param syubetsu07KinZennenhi 種別７金額（前年比）
     */
    public String getSyubetsu07KinZennenhiCls() {
        return getHirituClass(getSyubetsu07KinZennenhi());
    }
    
    /**
     * 種別８件数（前年比）を取得します。
     * @return 種別８件数（前年比）
     */
    public BigDecimal getSyubetsu08KenZennenhi() {
        return Calculator.percentage(getSyubetsu08Ken(), getSyubetsu08KenZen(), 2);
    }
    /**
     * 種別８件数（前年比）を設定します。
     * @param syubetsu08KenZennenhi 種別８件数（前年比）
     */
    public String getSyubetsu08KenZennenhiCls() {
        return getHirituClass(getSyubetsu08KenZennenhi());
    }
    
    /**
     * 種別８金額（前年比）を取得します。
     * @return 種別８金額（前年比）
     */
    public BigDecimal getSyubetsu08KinZennenhi() {
        return Calculator.percentage(getSyubetsu08Kin(), getSyubetsu08KinZen(), 2);
    }
    /**
     * 種別８金額（前年比）を設定します。
     * @param syubetsu08KinZennenhi 種別８金額（前年比）
     */
    public String getSyubetsu08KinZennenhiCls() {
        return getHirituClass(getSyubetsu08KinZennenhi());
    }
    
    /**
     * 種別９件数（前年比）を取得します。
     * @return 種別９件数（前年比）
     */
    public BigDecimal getSyubetsu09KenZennenhi() {
        return Calculator.percentage(getSyubetsu09Ken(), getSyubetsu09KenZen(), 2);
    }
    /**
     * 種別９件数（前年比）を設定します。
     * @param syubetsu09KenZennenhi 種別９件数（前年比）
     */
    public String getSyubetsu09KenZennenhiCls() {
        return getHirituClass(getSyubetsu09KenZennenhi());
    }
    
    /**
     * 種別９金額（前年比）を取得します。
     * @return 種別９金額（前年比）
     */
    public BigDecimal getSyubetsu09KinZennenhi() {
        return Calculator.percentage(getSyubetsu09Kin(), getSyubetsu09KinZen(), 2);
    }
    /**
     * 種別９金額（前年比）を設定します。
     * @param syubetsu09KinZennenhi 種別９金額（前年比）
     */
    public String getSyubetsu09KinZennenhiCls() {
        return getHirituClass(getSyubetsu09KinZennenhi());
    }
    
    /**
     * 種別１０件数（前年比）を取得します。
     * @return 種別１０件数（前年比）
     */
    public BigDecimal getSyubetsu10KenZennenhi() {
        return Calculator.percentage(getSyubetsu10Ken(), getSyubetsu10KenZen(), 2);
    }
    /**
     * 種別１０件数（前年比）を設定します。
     * @param syubetsu10KenZennenhi 種別１０件数（前年比）
     */
    public String getSyubetsu10KenZennenhiCls() {
        return getHirituClass(getSyubetsu10KenZennenhi());
    }
    
    /**
     * 種別１０金額（前年比）を取得します。
     * @return 種別１０金額（前年比）
     */
    public BigDecimal getSyubetsu10KinZennenhi() {
        return Calculator.percentage(getSyubetsu10Kin(), getSyubetsu10KinZen(), 2);
    }
    /**
     * 種別１０金額（前年比）を設定します。
     * @param syubetsu10KinZennenhi 種別１０金額（前年比）
     */
    public String getSyubetsu10KinZennenhiCls() {
        return getHirituClass(getSyubetsu10KinZennenhi());
    }
    
    /**
     * ネットテイク件数（前年比）を取得します。
     * @return ネットテイク件数（前年比）
     */
    public BigDecimal getNettakeKenZennenhi() {
        return Calculator.percentage(getNettakeKen(), getNettakeKenZen(), 2);
    }
    /**
     * ネットテイク件数（前年比）を設定します。
     * @param nettakeKenZennenhi ネットテイク件数（前年比）
     */
    public String getNettakeKenZennenhiCls() {
        return getHirituClass(getNettakeKenZennenhi());
    }
    
    /**
     * ネットテイク金額（前年比）を取得します。
     * @return ネットテイク金額（前年比）
     */
    public BigDecimal getNettakeKinZennenhi() {
        return Calculator.percentage(getNettakeKin(), getNettakeKinZen(), 2);
    }
    /**
     * ネットテイク金額（前年比）を設定します。
     * @param nettakeKinZennenhi ネットテイク金額（前年比）
     */
    public String getNettakeKinZennenhiCls() {
        return getHirituClass(getNettakeKinZennenhi());
    }
    
    /**
     * ネット宅配件数（前年比）を取得します。
     * @return ネット宅配件数（前年比）
     */
    public BigDecimal getNettakuhaiKenZennenhi() {
        return Calculator.percentage(getNettakuhaiKen(), getNettakuhaiKenZen(), 2);
    }
    /**
     * ネット宅配件数（前年比）を設定します。
     * @param nettakuhaiKenZennenhi ネット宅配件数（前年比）
     */
    public String getNettakuhaiKenZennenhiCls() {
        return getHirituClass(getNettakuhaiKenZennenhi());
    }
    
    /**
     * ネット宅配金額（前年比）を取得します。
     * @return ネット宅配金額（前年比）
     */
    public BigDecimal getNettakuhaiKinZennenhi() {
        return Calculator.percentage(getNettakuhaiKin(), getNettakuhaiKinZen(), 2);
    }
    /**
     * ネット宅配金額（前年比）を設定します。
     * @param nettakuhaiKinZennenhi ネット宅配金額（前年比）
     */
    public String getNettakuhaiKinZennenhiCls() {
        return getHirituClass(getNettakuhaiKinZennenhi());
    }
    
    /**
     * 種別１３件数（前年比）を取得します。
     * @return 種別１３件数（前年比）
     */
    public BigDecimal getSyubetsu13KenZennenhi() {
        return Calculator.percentage(getSyubetsu13Ken(), getSyubetsu13KenZen(), 2);
    }
    /**
     * 種別１３件数（前年比）を設定します。
     * @param syubetsu13KenZennenhi 種別１３件数（前年比）
     */
    public String getSyubetsu13KenZennenhiCls() {
        return getHirituClass(getSyubetsu13KenZennenhi());
    }
    
    /**
     * 種別１３金額（前年比）を取得します。
     * @return 種別１３金額（前年比）
     */
    public BigDecimal getSyubetsu13KinZennenhi() {
        return Calculator.percentage(getSyubetsu13Kin(), getSyubetsu13KinZen(), 2);
    }
    /**
     * 種別１３金額（前年比）を設定します。
     * @param syubetsu13KinZennenhi 種別１３金額（前年比）
     */
    public String getSyubetsu13KinZennenhiCls() {
        return getHirituClass(getSyubetsu13KinZennenhi());
    }
    
    /**
     * 種別１４件数（前年比）を取得します。
     * @return 種別１４件数（前年比）
     */
    public BigDecimal getSyubetsu14KenZennenhi() {
        return Calculator.percentage(getSyubetsu14Ken(), getSyubetsu14KenZen(), 2);
    }
    /**
     * 種別１４件数（前年比）を設定します。
     * @param syubetsu14KenZennenhi 種別１４件数（前年比）
     */
    public String getSyubetsu14KenZennenhiCls() {
        return getHirituClass(getSyubetsu14KenZennenhi());
    }
    
    /**
     * 種別１４金額（前年比）を取得します。
     * @return 種別１４金額（前年比）
     */
    public BigDecimal getSyubetsu14KinZennenhi() {
        return Calculator.percentage(getSyubetsu14Kin(), getSyubetsu14KinZen(), 2);
    }
    /**
     * 種別１４金額（前年比）を設定します。
     * @param syubetsu14KinZennenhi 種別１４金額（前年比）
     */
    public String getSyubetsu14KinZennenhiCls() {
        return getHirituClass(getSyubetsu14KinZennenhi());
    }
    
    /**
     * 種別１５件数（前年比）を取得します。
     * @return 種別１５件数（前年比）
     */
    public BigDecimal getSyubetsu15KenZennenhi() {
        return Calculator.percentage(getSyubetsu15Ken(), getSyubetsu15KenZen(), 2);
    }
    /**
     * 種別１５件数（前年比）を設定します。
     * @param syubetsu15KenZennenhi 種別１５件数（前年比）
     */
    public String getSyubetsu15KenZennenhiCls() {
        return getHirituClass(getSyubetsu15KenZennenhi());
    }
    
    /**
     * 種別１５金額（前年比）を取得します。
     * @return 種別１５金額（前年比）
     */
    public BigDecimal getSyubetsu15KinZennenhi() {
        return Calculator.percentage(getSyubetsu15Kin(), getSyubetsu15KinZen(), 2);
    }
    /**
     * 種別１５金額（前年比）を設定します。
     * @param syubetsu15KinZennenhi 種別１５金額（前年比）
     */
    public String getSyubetsu15KinZennenhiCls() {
        return getHirituClass(getSyubetsu15KinZennenhi());
    }
    
    /**
     * 外販・その他件数（前年比）を取得します。
     * @return 外販・その他件数（前年比）
     */
    public BigDecimal getOtherKenZennenhi() {
        return Calculator.percentage(getOtherKen(), getOtherKenZen(), 2);
    }
    /**
     * 外販・その他件数（前年比）を設定します。
     * @param otherKenZennenhi 外販・その他件数（前年比）
     */
    public String getOtherKenZennenhiCls() {
        return getHirituClass(getOtherKenZennenhi());
    }
    
    /**
     * 外販・その他金額（前年比）を取得します。
     * @return 外販・その他金額（前年比）
     */
    public BigDecimal getOtherKinZennenhi() {
        return Calculator.percentage(getOtherKin(), getOtherKinZen(), 2);
    }
    /**
     * 外販・その他金額（前年比）を設定します。
     * @param otherKinZennenhi 外販・その他金額（前年比）
     */
    public String getOtherKinZennenhiCls() {
        return getHirituClass(getOtherKinZennenhi());
    }
    
    /**
     * 種別計件数を取得します。
     * @return 種別計件数
     */
    public BigDecimal getShubetuKeiKen() {
        return getEatKen().add(getTakeKen()).add(getTelKen()).add(getDriveKen()).add(getTakuhaiKen()).add(getOtherKen());
    }
    
    /**
     * 種別計件数（前年）を取得します。
     * @return 種別計件数（前年）
     */
    public BigDecimal getShubetuKeiKenZen() {
        return getEatKenZen().add(getTakeKenZen()).add(getTelKenZen()).add(getDriveKenZen()).add(getTakuhaiKenZen()).add(getOtherKenZen());
    }
    
    /**
     * 種別計件数（前年比）を取得します。
     * @return 種別計件数（前年比）
     */
    public BigDecimal getShubetuKeiKenZennenhi() {
        return Calculator.percentage(getShubetuKeiKen(), getShubetuKeiKenZen(), 2);
    }
    /**
     * 種別計件数（前年比）を設定します。
     * @param shubetuKeiKenZennenhi 種別計件数（前年比）
     */
    public String getShubetuKeiKenZennenhiCls() {
        return getHirituClass(getShubetuKeiKenZennenhi());
    }
    
    /**
     * 種別計金額を取得します。
     * @return 種別計金額
     */
    public BigDecimal getShubetuKeiKin() {
        return getEatKin().add(getTakeKin()).add(getTelKin()).add(getDriveKin()).add(getTakuhaiKin()).add(getOtherKin());
    }
    
    /**
     * 種別計金額（前年）を取得します。
     * @return 種別計金額（前年）
     */
    public BigDecimal getShubetuKeiKinZen() {
        return getEatKinZen().add(getTakeKinZen()).add(getTelKinZen()).add(getDriveKinZen()).add(getTakuhaiKinZen()).add(getOtherKinZen());
    }
    
    /**
     * 種別計金額（前年比）を取得します。
     * @return 種別計金額（前年比）
     */
    public BigDecimal getShubetuKeiKinZennenhi() {
        return Calculator.percentage(getShubetuKeiKin(), getShubetuKeiKinZen(), 2);
    }
    /**
     * 種別計金額（前年比）を設定します。
     * @param shubetuKeiKinZennenhi 種別計金額（前年比）
     */
    public String getShubetuKeiKinZennenhiCls() {
        return getHirituClass(getShubetuKeiKinZennenhi());
    }
    public String getMiseNameKj() {
        return miseNameKj;
    }
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    public BigDecimal getMiseCnt() {
        return miseCnt;
    }
    public void setMiseCnt(BigDecimal miseCnt) {
        this.miseCnt = miseCnt;
    }
    
    private String getHirituClass(BigDecimal val) {
        if (getEigyoDt().equals("月合計")) {
            return val.compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu" : "body_num";
        }
        return val.compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
    }
    
    
}
