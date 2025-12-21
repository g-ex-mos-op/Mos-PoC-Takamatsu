package jp.co.isid.mos.bird.common.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstOner {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerKbn_COLUMN = "ONER_KBN";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerNameKna_COLUMN = "ONER_NAME_KNA";
    
    public static final String onerPostNo_COLUMN = "ONER_POST_NO";
    
    public static final String onerKenName_COLUMN = "ONER_KEN_NAME";
    
    public static final String onerAdrs1_COLUMN = "ONER_ADRS1";
    
    public static final String onerAdrs2_COLUMN = "ONER_ADRS2";
    
    public static final String onerAdrs3_COLUMN = "ONER_ADRS3";
    
    public static final String onerKenNamek_COLUMN = "ONER_KEN_NAMEK";
    
    public static final String onerAdrs1K_COLUMN = "ONER_ADRS1K";
    
    public static final String onerAdrs2K_COLUMN = "ONER_ADRS2K";
    
    public static final String onerAdrs3K_COLUMN = "ONER_ADRS3K";
    
    public static final String seikyuPostNo_COLUMN = "SEIKYU_POST_NO";
    
    public static final String seikyuKenName_COLUMN = "SEIKYU_KEN_NAME";
    
    public static final String seikyuAdrs1_COLUMN = "SEIKYU_ADRS1";
    
    public static final String seikyuAdrs2_COLUMN = "SEIKYU_ADRS2";
    
    public static final String seikyuAdrs3_COLUMN = "SEIKYU_ADRS3";
    
    public static final String seikyuName_COLUMN = "SEIKYU_NAME";
    
    public static final String seikyuKenNamek_COLUMN = "SEIKYU_KEN_NAMEK";
    
    public static final String seikyuAdrs1K_COLUMN = "SEIKYU_ADRS1K";
    
    public static final String seikyuAdrs2K_COLUMN = "SEIKYU_ADRS2K";
    
    public static final String seikyuAdrs3K_COLUMN = "SEIKYU_ADRS3K";
    
    public static final String seikyuNamek_COLUMN = "SEIKYU_NAMEK";
    
    public static final String onerTel_COLUMN = "ONER_TEL";
    
    public static final String onerFax_COLUMN = "ONER_FAX";
    
    public static final String mosBank_COLUMN = "MOS_BANK";
    
    public static final String kessanM_COLUMN = "KESSAN_M";
    
    public static final String birthOner_COLUMN = "BIRTH_ONER";
    
    public static final String birthFaml_COLUMN = "BIRTH_FAML";
    
    public static final String keiyakuSta_COLUMN = "KEIYAKU_STA";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    public static final String seikyuSycl_COLUMN = "SEIKYU_SYCL";
    
    public static final String codKbn_COLUMN = "COD_KBN";
    
    public static final String nyukinKbn_COLUMN = "NYUKIN_KBN";
    
    public static final String nyuTukisu_COLUMN = "NYU_TUKISU";
    
    public static final String nyuHiduke_COLUMN = "NYU_HIDUKE";
    
    public static final String cardStartDate_COLUMN = "CARD_START_DATE";
    
    public static final String onerDate3_COLUMN = "ONER_DATE_3";
    
    public static final String onerDate4_COLUMN = "ONER_DATE_4";
    
    public static final String onerDate5_COLUMN = "ONER_DATE_5";
    
    public static final String onerDate6_COLUMN = "ONER_DATE_6";
    
    public static final String onerDate7_COLUMN = "ONER_DATE_7";
    
    public static final String onerDate8_COLUMN = "ONER_DATE_8";
    
    public static final String onerDate9_COLUMN = "ONER_DATE_9";
    
    public static final String uneiCd_COLUMN = "UNEI_CD";
    
    public static final String keiyakuName_COLUMN = "KEIYAKU_NAME";
    
    public static final String miseFirst_COLUMN = "MISE_FIRST";
    
    public static final String onerTel2_COLUMN = "ONER_TEL2";
    
    public static final String otherBusiness_COLUMN = "OTHER_BUSINESS";
    
    public static final String bloodType_COLUMN = "BLOOD_TYPE";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String married_COLUMN = "MARRIED";
    
    public static final String tantoName_COLUMN = "TANTO_NAME";
    
    public static final String tantoPostNo_COLUMN = "TANTO_POST_NO";
    
    public static final String tantoAdrs1_COLUMN = "TANTO_ADRS1";
    
    public static final String tantoAdrs2_COLUMN = "TANTO_ADRS2";
    
    public static final String tantoAdrs3_COLUMN = "TANTO_ADRS3";
    
    public static final String tantoTel_COLUMN = "TANTO_TEL";
    
    public static final String tantoFax_COLUMN = "TANTO_FAX";
    
    public static final String capital_COLUMN = "CAPITAL";
    
    public static final String mosRate_COLUMN = "MOS_RATE";
    
    public static final String gyomu_COLUMN = "GYOMU";
    
    public static final String shrhldStat_COLUMN = "SHRHLD_STAT";
    
    public static final String shrhldAmount_COLUMN = "SHRHLD_AMOUNT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String mstVer_COLUMN = "MST_VER";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー区分
     */
    private String onerKbn;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * オーナー名称（カナ）
     */
    private String onerNameKna;
    
    /**
     * オーナー郵便番号
     */
    private String onerPostNo;
    
    /**
     * オーナー都道府県名称
     */
    private String onerKenName;
    
    /**
     * オーナー住所１
     */
    private String onerAdrs1;
    
    /**
     * オーナー住所２
     */
    private String onerAdrs2;
    
    /**
     * オーナー住所３
     */
    private String onerAdrs3;
    
    /**
     * オーナー都道府県名称（カナ）
     */
    private String onerKenNamek;
    
    /**
     * オーナー住所１（カナ）
     */
    private String onerAdrs1K;
    
    /**
     * オーナー住所２（カナ）
     */
    private String onerAdrs2K;
    
    /**
     * オーナー住所３（カナ）
     */
    private String onerAdrs3K;
    
    /**
     * 請求郵便番号
     */
    private String seikyuPostNo;
    
    /**
     * 請求都道府県名称
     */
    private String seikyuKenName;
    
    /**
     * 請求住所１
     */
    private String seikyuAdrs1;
    
    /**
     * 請求住所２
     */
    private String seikyuAdrs2;
    
    /**
     * 請求住所３
     */
    private String seikyuAdrs3;
    
    /**
     * 送付先名称
     */
    private String seikyuName;
    
    /**
     * 請求都道府県名称（カナ）
     */
    private String seikyuKenNamek;
    
    /**
     * 請求住所１（カナ）
     */
    private String seikyuAdrs1K;
    
    /**
     * 請求住所２（カナ）
     */
    private String seikyuAdrs2K;
    
    /**
     * 請求住所３（カナ）
     */
    private String seikyuAdrs3K;
    
    /**
     * 送付先名称（カナ）
     */
    private String seikyuNamek;
    
    /**
     * オーナー電話番号
     */
    private String onerTel;
    
    /**
     * オーナーＦＡＸ番号
     */
    private String onerFax;
    
    /**
     * モス銀行コード
     */
    private String mosBank;
    
    /**
     * 決算月
     */
    private String kessanM;
    
    /**
     * オーナー誕生日
     */
    private String birthOner;
    
    /**
     * 家族誕生日
     */
    private String birthFaml;
    
    /**
     * 契約開始日
     */
    private String keiyakuSta;
    
    /**
     * 契約終了日
     */
    private String keiyakuEnd;
    
    /**
     * 請求書ｻｲｸﾙ区分
     */
    private String seikyuSycl;
    
    /**
     * 前払い区分
     */
    private String codKbn;
    
    /**
     * 入金予定区分
     */
    private String nyukinKbn;
    
    /**
     * 入金予定月数
     */
    private String nyuTukisu;
    
    /**
     * 入金予定日付
     */
    private String nyuHiduke;
    
    /**
     * 圧着ハガキ開始日
     */
    private String cardStartDate;
    
    /**
     * オーナー日付予備３
     */
    private String onerDate3;
    
    /**
     * オーナー日付予備４
     */
    private String onerDate4;
    
    /**
     * オーナー日付予備５
     */
    private String onerDate5;
    
    /**
     * オーナー日付予備６
     */
    private String onerDate6;
    
    /**
     * オーナー日付予備７
     */
    private String onerDate7;
    
    /**
     * オーナー日付予備８
     */
    private String onerDate8;
    
    /**
     * オーナー日付予備９
     */
    private String onerDate9;
    
    /**
     * 運営コード
     */
    private String uneiCd;
    
    /**
     * 契約者名
     */
    private String keiyakuName;
    
    /**
     * 1号店コード
     */
    private String miseFirst;
    
    /**
     * 電話番号２
     */
    private String onerTel2;
    
    /**
     * 他事業
     */
    private String otherBusiness;
    
    /**
     * 血液型
     */
    private String bloodType;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 配偶者有無
     */
    private String married;
    
    /**
     * モス事業担当者名
     */
    private String tantoName;
    
    /**
     * 担当者郵便番号
     */
    private String tantoPostNo;
    
    /**
     * 担当者住所１
     */
    private String tantoAdrs1;
    
    /**
     * 担当者住所２
     */
    private String tantoAdrs2;
    
    /**
     * 担当者住所３
     */
    private String tantoAdrs3;
    
    /**
     * 担当者電話番号
     */
    private String tantoTel;
    
    /**
     * 担当者FAX番号
     */
    private String tantoFax;
    
    /**
     * 資本金
     */
    private BigDecimal capital;
    
    /**
     * モス事業売上比率
     */
    private BigDecimal mosRate;
    
    /**
     * 業務内容
     */
    private String gyomu;
    
    /**
     * 持株会加入状況
     */
    private String shrhldStat;
    
    /**
     * 持株会口数
     */
    private BigDecimal shrhldAmount;
    
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
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    private int mstVer;
    
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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー区分を取得します。
     * @return オーナー区分
     */
    public String getOnerKbn() {
        return onerKbn;
    }
    /**
     * オーナー区分を設定します。
     * @param onerKbn オーナー区分
     */
    public void setOnerKbn(String onerKbn) {
        this.onerKbn = onerKbn;
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
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * オーナー名称（カナ）を取得します。
     * @return オーナー名称（カナ）
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }
    /**
     * オーナー名称（カナ）を設定します。
     * @param onerNameKna オーナー名称（カナ）
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }
    
    /**
     * オーナー郵便番号を取得します。
     * @return オーナー郵便番号
     */
    public String getOnerPostNo() {
        return onerPostNo;
    }
    /**
     * オーナー郵便番号を設定します。
     * @param onerPostNo オーナー郵便番号
     */
    public void setOnerPostNo(String onerPostNo) {
        this.onerPostNo = onerPostNo;
    }
    
    /**
     * オーナー都道府県名称を取得します。
     * @return オーナー都道府県名称
     */
    public String getOnerKenName() {
        return onerKenName;
    }
    /**
     * オーナー都道府県名称を設定します。
     * @param onerKenName オーナー都道府県名称
     */
    public void setOnerKenName(String onerKenName) {
        this.onerKenName = onerKenName;
    }
    
    /**
     * オーナー住所１を取得します。
     * @return オーナー住所１
     */
    public String getOnerAdrs1() {
        return onerAdrs1;
    }
    /**
     * オーナー住所１を設定します。
     * @param onerAdrs1 オーナー住所１
     */
    public void setOnerAdrs1(String onerAdrs1) {
        this.onerAdrs1 = onerAdrs1;
    }
    
    /**
     * オーナー住所２を取得します。
     * @return オーナー住所２
     */
    public String getOnerAdrs2() {
        return onerAdrs2;
    }
    /**
     * オーナー住所２を設定します。
     * @param onerAdrs2 オーナー住所２
     */
    public void setOnerAdrs2(String onerAdrs2) {
        this.onerAdrs2 = onerAdrs2;
    }
    
    /**
     * オーナー住所３を取得します。
     * @return オーナー住所３
     */
    public String getOnerAdrs3() {
        return onerAdrs3;
    }
    /**
     * オーナー住所３を設定します。
     * @param onerAdrs3 オーナー住所３
     */
    public void setOnerAdrs3(String onerAdrs3) {
        this.onerAdrs3 = onerAdrs3;
    }
    
    /**
     * オーナー都道府県名称（カナ）を取得します。
     * @return オーナー都道府県名称（カナ）
     */
    public String getOnerKenNamek() {
        return onerKenNamek;
    }
    /**
     * オーナー都道府県名称（カナ）を設定します。
     * @param onerKenNamek オーナー都道府県名称（カナ）
     */
    public void setOnerKenNamek(String onerKenNamek) {
        this.onerKenNamek = onerKenNamek;
    }
    
    /**
     * オーナー住所１（カナ）を取得します。
     * @return オーナー住所１（カナ）
     */
    public String getOnerAdrs1K() {
        return onerAdrs1K;
    }
    /**
     * オーナー住所１（カナ）を設定します。
     * @param onerAdrs1K オーナー住所１（カナ）
     */
    public void setOnerAdrs1K(String onerAdrs1K) {
        this.onerAdrs1K = onerAdrs1K;
    }
    
    /**
     * オーナー住所２（カナ）を取得します。
     * @return オーナー住所２（カナ）
     */
    public String getOnerAdrs2K() {
        return onerAdrs2K;
    }
    /**
     * オーナー住所２（カナ）を設定します。
     * @param onerAdrs2K オーナー住所２（カナ）
     */
    public void setOnerAdrs2K(String onerAdrs2K) {
        this.onerAdrs2K = onerAdrs2K;
    }
    
    /**
     * オーナー住所３（カナ）を取得します。
     * @return オーナー住所３（カナ）
     */
    public String getOnerAdrs3K() {
        return onerAdrs3K;
    }
    /**
     * オーナー住所３（カナ）を設定します。
     * @param onerAdrs3K オーナー住所３（カナ）
     */
    public void setOnerAdrs3K(String onerAdrs3K) {
        this.onerAdrs3K = onerAdrs3K;
    }
    
    /**
     * 請求郵便番号を取得します。
     * @return 請求郵便番号
     */
    public String getSeikyuPostNo() {
        return seikyuPostNo;
    }
    /**
     * 請求郵便番号を設定します。
     * @param seikyuPostNo 請求郵便番号
     */
    public void setSeikyuPostNo(String seikyuPostNo) {
        this.seikyuPostNo = seikyuPostNo;
    }
    
    /**
     * 請求都道府県名称を取得します。
     * @return 請求都道府県名称
     */
    public String getSeikyuKenName() {
        return seikyuKenName;
    }
    /**
     * 請求都道府県名称を設定します。
     * @param seikyuKenName 請求都道府県名称
     */
    public void setSeikyuKenName(String seikyuKenName) {
        this.seikyuKenName = seikyuKenName;
    }
    
    /**
     * 請求住所１を取得します。
     * @return 請求住所１
     */
    public String getSeikyuAdrs1() {
        return seikyuAdrs1;
    }
    /**
     * 請求住所１を設定します。
     * @param seikyuAdrs1 請求住所１
     */
    public void setSeikyuAdrs1(String seikyuAdrs1) {
        this.seikyuAdrs1 = seikyuAdrs1;
    }
    
    /**
     * 請求住所２を取得します。
     * @return 請求住所２
     */
    public String getSeikyuAdrs2() {
        return seikyuAdrs2;
    }
    /**
     * 請求住所２を設定します。
     * @param seikyuAdrs2 請求住所２
     */
    public void setSeikyuAdrs2(String seikyuAdrs2) {
        this.seikyuAdrs2 = seikyuAdrs2;
    }
    
    /**
     * 請求住所３を取得します。
     * @return 請求住所３
     */
    public String getSeikyuAdrs3() {
        return seikyuAdrs3;
    }
    /**
     * 請求住所３を設定します。
     * @param seikyuAdrs3 請求住所３
     */
    public void setSeikyuAdrs3(String seikyuAdrs3) {
        this.seikyuAdrs3 = seikyuAdrs3;
    }
    
    /**
     * 送付先名称を取得します。
     * @return 送付先名称
     */
    public String getSeikyuName() {
        return seikyuName;
    }
    /**
     * 送付先名称を設定します。
     * @param seikyuName 送付先名称
     */
    public void setSeikyuName(String seikyuName) {
        this.seikyuName = seikyuName;
    }
    
    /**
     * 請求都道府県名称（カナ）を取得します。
     * @return 請求都道府県名称（カナ）
     */
    public String getSeikyuKenNamek() {
        return seikyuKenNamek;
    }
    /**
     * 請求都道府県名称（カナ）を設定します。
     * @param seikyuKenNamek 請求都道府県名称（カナ）
     */
    public void setSeikyuKenNamek(String seikyuKenNamek) {
        this.seikyuKenNamek = seikyuKenNamek;
    }
    
    /**
     * 請求住所１（カナ）を取得します。
     * @return 請求住所１（カナ）
     */
    public String getSeikyuAdrs1K() {
        return seikyuAdrs1K;
    }
    /**
     * 請求住所１（カナ）を設定します。
     * @param seikyuAdrs1K 請求住所１（カナ）
     */
    public void setSeikyuAdrs1K(String seikyuAdrs1K) {
        this.seikyuAdrs1K = seikyuAdrs1K;
    }
    
    /**
     * 請求住所２（カナ）を取得します。
     * @return 請求住所２（カナ）
     */
    public String getSeikyuAdrs2K() {
        return seikyuAdrs2K;
    }
    /**
     * 請求住所２（カナ）を設定します。
     * @param seikyuAdrs2K 請求住所２（カナ）
     */
    public void setSeikyuAdrs2K(String seikyuAdrs2K) {
        this.seikyuAdrs2K = seikyuAdrs2K;
    }
    
    /**
     * 請求住所３（カナ）を取得します。
     * @return 請求住所３（カナ）
     */
    public String getSeikyuAdrs3K() {
        return seikyuAdrs3K;
    }
    /**
     * 請求住所３（カナ）を設定します。
     * @param seikyuAdrs3K 請求住所３（カナ）
     */
    public void setSeikyuAdrs3K(String seikyuAdrs3K) {
        this.seikyuAdrs3K = seikyuAdrs3K;
    }
    
    /**
     * 送付先名称（カナ）を取得します。
     * @return 送付先名称（カナ）
     */
    public String getSeikyuNamek() {
        return seikyuNamek;
    }
    /**
     * 送付先名称（カナ）を設定します。
     * @param seikyuNamek 送付先名称（カナ）
     */
    public void setSeikyuNamek(String seikyuNamek) {
        this.seikyuNamek = seikyuNamek;
    }
    
    /**
     * オーナー電話番号を取得します。
     * @return オーナー電話番号
     */
    public String getOnerTel() {
        return onerTel;
    }
    /**
     * オーナー電話番号を設定します。
     * @param onerTel オーナー電話番号
     */
    public void setOnerTel(String onerTel) {
        this.onerTel = onerTel;
    }
    
    /**
     * オーナーＦＡＸ番号を取得します。
     * @return オーナーＦＡＸ番号
     */
    public String getOnerFax() {
        return onerFax;
    }
    /**
     * オーナーＦＡＸ番号を設定します。
     * @param onerFax オーナーＦＡＸ番号
     */
    public void setOnerFax(String onerFax) {
        this.onerFax = onerFax;
    }
    
    /**
     * モス銀行コードを取得します。
     * @return モス銀行コード
     */
    public String getMosBank() {
        return mosBank;
    }
    /**
     * モス銀行コードを設定します。
     * @param mosBank モス銀行コード
     */
    public void setMosBank(String mosBank) {
        this.mosBank = mosBank;
    }
    
    /**
     * 決算月を取得します。
     * @return 決算月
     */
    public String getKessanM() {
        return kessanM;
    }
    /**
     * 決算月を設定します。
     * @param kessanM 決算月
     */
    public void setKessanM(String kessanM) {
        this.kessanM = kessanM;
    }
    
    /**
     * オーナー誕生日を取得します。
     * @return オーナー誕生日
     */
    public String getBirthOner() {
        return birthOner;
    }
    /**
     * オーナー誕生日を設定します。
     * @param birthOner オーナー誕生日
     */
    public void setBirthOner(String birthOner) {
        this.birthOner = birthOner;
    }
    
    /**
     * 家族誕生日を取得します。
     * @return 家族誕生日
     */
    public String getBirthFaml() {
        return birthFaml;
    }
    /**
     * 家族誕生日を設定します。
     * @param birthFaml 家族誕生日
     */
    public void setBirthFaml(String birthFaml) {
        this.birthFaml = birthFaml;
    }
    
    /**
     * 契約開始日を取得します。
     * @return 契約開始日
     */
    public String getKeiyakuSta() {
        return keiyakuSta;
    }
    /**
     * 契約開始日を設定します。
     * @param keiyakuSta 契約開始日
     */
    public void setKeiyakuSta(String keiyakuSta) {
        this.keiyakuSta = keiyakuSta;
    }
    
    /**
     * 契約終了日を取得します。
     * @return 契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * 契約終了日を設定します。
     * @param keiyakuEnd 契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    
    /**
     * 請求書ｻｲｸﾙ区分を取得します。
     * @return 請求書ｻｲｸﾙ区分
     */
    public String getSeikyuSycl() {
        return seikyuSycl;
    }
    /**
     * 請求書ｻｲｸﾙ区分を設定します。
     * @param seikyuSycl 請求書ｻｲｸﾙ区分
     */
    public void setSeikyuSycl(String seikyuSycl) {
        this.seikyuSycl = seikyuSycl;
    }
    
    /**
     * 前払い区分を取得します。
     * @return 前払い区分
     */
    public String getCodKbn() {
        return codKbn;
    }
    /**
     * 前払い区分を設定します。
     * @param codKbn 前払い区分
     */
    public void setCodKbn(String codKbn) {
        this.codKbn = codKbn;
    }
    
    /**
     * 入金予定区分を取得します。
     * @return 入金予定区分
     */
    public String getNyukinKbn() {
        return nyukinKbn;
    }
    /**
     * 入金予定区分を設定します。
     * @param nyukinKbn 入金予定区分
     */
    public void setNyukinKbn(String nyukinKbn) {
        this.nyukinKbn = nyukinKbn;
    }
    
    /**
     * 入金予定月数を取得します。
     * @return 入金予定月数
     */
    public String getNyuTukisu() {
        return nyuTukisu;
    }
    /**
     * 入金予定月数を設定します。
     * @param nyuTukisu 入金予定月数
     */
    public void setNyuTukisu(String nyuTukisu) {
        this.nyuTukisu = nyuTukisu;
    }
    
    /**
     * 入金予定日付を取得します。
     * @return 入金予定日付
     */
    public String getNyuHiduke() {
        return nyuHiduke;
    }
    /**
     * 入金予定日付を設定します。
     * @param nyuHiduke 入金予定日付
     */
    public void setNyuHiduke(String nyuHiduke) {
        this.nyuHiduke = nyuHiduke;
    }
    
    /**
     * 圧着ハガキ開始日を取得します。
     * @return 圧着ハガキ開始日
     */
    public String getCardStartDate() {
        return cardStartDate;
    }
    /**
     * 圧着ハガキ開始日を設定します。
     * @param cardStartDate 圧着ハガキ開始日
     */
    public void setCardStartDate(String cardStartDate) {
        this.cardStartDate = cardStartDate;
    }
    
    /**
     * オーナー日付予備３を取得します。
     * @return オーナー日付予備３
     */
    public String getOnerDate3() {
        return onerDate3;
    }
    /**
     * オーナー日付予備３を設定します。
     * @param onerDate3 オーナー日付予備３
     */
    public void setOnerDate3(String onerDate3) {
        this.onerDate3 = onerDate3;
    }
    
    /**
     * オーナー日付予備４を取得します。
     * @return オーナー日付予備４
     */
    public String getOnerDate4() {
        return onerDate4;
    }
    /**
     * オーナー日付予備４を設定します。
     * @param onerDate4 オーナー日付予備４
     */
    public void setOnerDate4(String onerDate4) {
        this.onerDate4 = onerDate4;
    }
    
    /**
     * オーナー日付予備５を取得します。
     * @return オーナー日付予備５
     */
    public String getOnerDate5() {
        return onerDate5;
    }
    /**
     * オーナー日付予備５を設定します。
     * @param onerDate5 オーナー日付予備５
     */
    public void setOnerDate5(String onerDate5) {
        this.onerDate5 = onerDate5;
    }
    
    /**
     * オーナー日付予備６を取得します。
     * @return オーナー日付予備６
     */
    public String getOnerDate6() {
        return onerDate6;
    }
    /**
     * オーナー日付予備６を設定します。
     * @param onerDate6 オーナー日付予備６
     */
    public void setOnerDate6(String onerDate6) {
        this.onerDate6 = onerDate6;
    }
    
    /**
     * オーナー日付予備７を取得します。
     * @return オーナー日付予備７
     */
    public String getOnerDate7() {
        return onerDate7;
    }
    /**
     * オーナー日付予備７を設定します。
     * @param onerDate7 オーナー日付予備７
     */
    public void setOnerDate7(String onerDate7) {
        this.onerDate7 = onerDate7;
    }
    
    /**
     * オーナー日付予備８を取得します。
     * @return オーナー日付予備８
     */
    public String getOnerDate8() {
        return onerDate8;
    }
    /**
     * オーナー日付予備８を設定します。
     * @param onerDate8 オーナー日付予備８
     */
    public void setOnerDate8(String onerDate8) {
        this.onerDate8 = onerDate8;
    }
    
    /**
     * オーナー日付予備９を取得します。
     * @return オーナー日付予備９
     */
    public String getOnerDate9() {
        return onerDate9;
    }
    /**
     * オーナー日付予備９を設定します。
     * @param onerDate9 オーナー日付予備９
     */
    public void setOnerDate9(String onerDate9) {
        this.onerDate9 = onerDate9;
    }
    
    /**
     * 運営コードを取得します。
     * @return 運営コード
     */
    public String getUneiCd() {
        return uneiCd;
    }
    /**
     * 運営コードを設定します。
     * @param uneiCd 運営コード
     */
    public void setUneiCd(String uneiCd) {
        this.uneiCd = uneiCd;
    }
    
    /**
     * 契約者名を取得します。
     * @return 契約者名
     */
    public String getKeiyakuName() {
        return keiyakuName;
    }
    /**
     * 契約者名を設定します。
     * @param keiyakuName 契約者名
     */
    public void setKeiyakuName(String keiyakuName) {
        this.keiyakuName = keiyakuName;
    }
    
    /**
     * 1号店コードを取得します。
     * @return 1号店コード
     */
    public String getMiseFirst() {
        return miseFirst;
    }
    /**
     * 1号店コードを設定します。
     * @param miseFirst 1号店コード
     */
    public void setMiseFirst(String miseFirst) {
        this.miseFirst = miseFirst;
    }
    
    /**
     * 電話番号２を取得します。
     * @return 電話番号２
     */
    public String getOnerTel2() {
        return onerTel2;
    }
    /**
     * 電話番号２を設定します。
     * @param onerTel2 電話番号２
     */
    public void setOnerTel2(String onerTel2) {
        this.onerTel2 = onerTel2;
    }
    
    /**
     * 他事業を取得します。
     * @return 他事業
     */
    public String getOtherBusiness() {
        return otherBusiness;
    }
    /**
     * 他事業を設定します。
     * @param otherBusiness 他事業
     */
    public void setOtherBusiness(String otherBusiness) {
        this.otherBusiness = otherBusiness;
    }
    
    /**
     * 血液型を取得します。
     * @return 血液型
     */
    public String getBloodType() {
        return bloodType;
    }
    /**
     * 血液型を設定します。
     * @param bloodType 血液型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return sex;
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * 配偶者有無を取得します。
     * @return 配偶者有無
     */
    public String getMarried() {
        return married;
    }
    /**
     * 配偶者有無を設定します。
     * @param married 配偶者有無
     */
    public void setMarried(String married) {
        this.married = married;
    }
    
    /**
     * モス事業担当者名を取得します。
     * @return モス事業担当者名
     */
    public String getTantoName() {
        return tantoName;
    }
    /**
     * モス事業担当者名を設定します。
     * @param tantoName モス事業担当者名
     */
    public void setTantoName(String tantoName) {
        this.tantoName = tantoName;
    }
    
    /**
     * 担当者郵便番号を取得します。
     * @return 担当者郵便番号
     */
    public String getTantoPostNo() {
        return tantoPostNo;
    }
    /**
     * 担当者郵便番号を設定します。
     * @param tantoPostNo 担当者郵便番号
     */
    public void setTantoPostNo(String tantoPostNo) {
        this.tantoPostNo = tantoPostNo;
    }
    
    /**
     * 担当者住所１を取得します。
     * @return 担当者住所１
     */
    public String getTantoAdrs1() {
        return tantoAdrs1;
    }
    /**
     * 担当者住所１を設定します。
     * @param tantoAdrs1 担当者住所１
     */
    public void setTantoAdrs1(String tantoAdrs1) {
        this.tantoAdrs1 = tantoAdrs1;
    }
    
    /**
     * 担当者住所２を取得します。
     * @return 担当者住所２
     */
    public String getTantoAdrs2() {
        return tantoAdrs2;
    }
    /**
     * 担当者住所２を設定します。
     * @param tantoAdrs2 担当者住所２
     */
    public void setTantoAdrs2(String tantoAdrs2) {
        this.tantoAdrs2 = tantoAdrs2;
    }
    
    /**
     * 担当者住所３を取得します。
     * @return 担当者住所３
     */
    public String getTantoAdrs3() {
        return tantoAdrs3;
    }
    /**
     * 担当者住所３を設定します。
     * @param tantoAdrs3 担当者住所３
     */
    public void setTantoAdrs3(String tantoAdrs3) {
        this.tantoAdrs3 = tantoAdrs3;
    }
    
    /**
     * 担当者電話番号を取得します。
     * @return 担当者電話番号
     */
    public String getTantoTel() {
        return tantoTel;
    }
    /**
     * 担当者電話番号を設定します。
     * @param tantoTel 担当者電話番号
     */
    public void setTantoTel(String tantoTel) {
        this.tantoTel = tantoTel;
    }
    
    /**
     * 担当者FAX番号を取得します。
     * @return 担当者FAX番号
     */
    public String getTantoFax() {
        return tantoFax;
    }
    /**
     * 担当者FAX番号を設定します。
     * @param tantoFax 担当者FAX番号
     */
    public void setTantoFax(String tantoFax) {
        this.tantoFax = tantoFax;
    }
    
    /**
     * 資本金を取得します。
     * @return 資本金
     */
    public BigDecimal getCapital() {
        return capital;
    }
    /**
     * 資本金を設定します。
     * @param capital 資本金
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }
    
    /**
     * モス事業売上比率を取得します。
     * @return モス事業売上比率
     */
    public BigDecimal getMosRate() {
        return mosRate;
    }
    /**
     * モス事業売上比率を設定します。
     * @param mosRate モス事業売上比率
     */
    public void setMosRate(BigDecimal mosRate) {
        this.mosRate = mosRate;
    }
    
    /**
     * 業務内容を取得します。
     * @return 業務内容
     */
    public String getGyomu() {
        return gyomu;
    }
    /**
     * 業務内容を設定します。
     * @param gyomu 業務内容
     */
    public void setGyomu(String gyomu) {
        this.gyomu = gyomu;
    }
    
    /**
     * 持株会加入状況を取得します。
     * @return 持株会加入状況
     */
    public String getShrhldStat() {
        return shrhldStat;
    }
    /**
     * 持株会加入状況を設定します。
     * @param shrhldStat 持株会加入状況
     */
    public void setShrhldStat(String shrhldStat) {
        this.shrhldStat = shrhldStat;
    }
    
    /**
     * 持株会口数を取得します。
     * @return 持株会口数
     */
    public BigDecimal getShrhldAmount() {
        return shrhldAmount;
    }
    /**
     * 持株会口数を設定します。
     * @param shrhldAmount 持株会口数
     */
    public void setShrhldAmount(BigDecimal shrhldAmount) {
        this.shrhldAmount = shrhldAmount;
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
    
    /**
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝを取得します。
     * @return ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    public int getMstVer() {
        return mstVer;
    }
    /**
     * ﾏｽﾀｰﾊﾞｰｼﾞｮﾝを設定します。
     * @param mstVer ﾏｽﾀｰﾊﾞｰｼﾞｮﾝ
     */
    public void setMstVer(int mstVer) {
        this.mstVer = mstVer;
    }
    
}
