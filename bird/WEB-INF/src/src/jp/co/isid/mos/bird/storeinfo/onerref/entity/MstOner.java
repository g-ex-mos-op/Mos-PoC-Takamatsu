package jp.co.isid.mos.bird.storeinfo.onerref.entity;

import java.math.BigDecimal;

public class MstOner {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerKbn_COLUMN = "ONER_KBN";
    
    public static final String aiteName_COLUMN = "AITE_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerNameKna_COLUMN = "ONER_NAME_KNA";
    
    public static final String onerPostNo_COLUMN = "ONER_POST_NO";
    
    public static final String onerKenName_COLUMN = "ONER_KEN_NAME";
    
    public static final String onerAdrs1_COLUMN = "ONER_ADRS1";
    
    public static final String onerAdrs2_COLUMN = "ONER_ADRS2";
    
    public static final String onerAdrs3_COLUMN = "ONER_ADRS3";
    
    public static final String onerTel_COLUMN = "ONER_TEL";
    
    public static final String onerFax_COLUMN = "ONER_FAX";
    
    public static final String kessanM_COLUMN = "KESSAN_DT";
    
    public static final String birthOner_COLUMN = "BIRTH_ONER";
    
    public static final String keiyakuSta_COLUMN = "KEIYAKU_STA";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    public static final String uneiCd_COLUMN = "UNEI_CD";
    
    public static final String unei_COLUMN = "UNEI";
    
    public static final String keiyakuName_COLUMN = "KEIYAKU_NAME";
    
    public static final String miseFirst_COLUMN = "MISE_FIRST";
    
    public static final String miseFirstName_COLUMN = "MISE_FIRST_NAME";
    
    public static final String onerTel2_COLUMN = "ONER_TEL2";
    
    public static final String otherBusiness_COLUMN = "OTHER_BUSINESS";
    
    public static final String tantoName_COLUMN = "TANTO_NAME";
    
    public static final String tantoTel_COLUMN = "TANTO_TEL";
    
    public static final String capital_COLUMN = "CAPITAL";
    
    public static final String mosRate_COLUMN = "MOS_RATE";
    
    public static final String gyomu_COLUMN = "GYOMU";

    public static final String sex_COLUMN = "SEX";

    //20060621追加
    public static final String onerNameShow_COLUMN = "ONER_NAME_SHOW";
    
    
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
     * 相手区分名称
     */
    private String aiteName;
    
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
     * オーナー電話番号
     */
    private String onerTel;
    
    /**
     * オーナーＦＡＸ番号
     */
    private String onerFax;
    
    /**
     * 決算月
     */
    private String kessanM;
    
    /**
     * オーナー誕生日
     */
    private String birthOner;
    
    /**
     * 契約開始日
     */
    private String keiyakuSta;
    
    /**
     * 契約終了日
     */
    private String keiyakuEnd;
    
    /**
     * 運営コード
     */
    private String uneiCd;
    
    /**
     * 運営
     */
    private String unei;
    
    /**
     * 契約者名
     */
    private String keiyakuName;
    
    /**
     * 1号店コード
     */
    private String miseFirst;
    
    /**
     * 1号店名称
     */
    private String miseFirstName;
    
    /**
     * 電話番号２
     */
    private String onerTel2;
    
    /**
     * 他事業
     */
    private String otherBusiness;
    
    /**
     * モス事業担当者名
     */
    private String tantoName;
    
    /**
     * 担当者電話番号
     */
    private String tantoTel;
    
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
     * 性別
     */
    private String sex;
    
    //20060621追加
    /**
     * オーナー名称 プルダウン表示用
     */
    private String onerNameShow;

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
     * 相手区分名称を取得します。
     * @return 相手区分名称
     */
    public String getAiteName() {
        return aiteName;
    }
    /**
     * 相手区分名称を設定します。
     * @param aiteName 相手区分名称
     */
    public void setAiteName(String aiteName) {
        this.aiteName = aiteName;
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
        return onerPostNo.trim();
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
     * 運営を取得します。
     * @return 運営
     */
    public String getUnei() {
        return unei;
    }
    /**
     * 運営を設定します。
     * @param unei 運営
     */
    public void setUnei(String unei) {
        this.unei = unei;
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
     * 1号店名称を取得します。
     * @return 1号店名称
     */
    public String getMiseFirstName() {
        return miseFirstName;
    }
    /**
     * 1号店名称を設定します。
     * @param miseFirstName 1号店名称
     */
    public void setMiseFirstName(String miseFirstName) {
        this.miseFirstName = miseFirstName;
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
	 * 性別の設定
	 * @return sex を戻します。
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 性別の設定
	 * @param sex sex を設定。
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
    
    //20060621 追加 start ---------------------------------------
    /**
     * オーナー名称 プルダウン表示用を取得します。
     * @return オーナー名称 プルダウン表示用
     */
    public String getOnerNameShow() {
        onerNameShow = getOnerCd() + "　" + getOnerNameKj();
        return onerNameShow;
    }
    /**
     * オーナー名称 プルダウン表示用を設定します。
     * @param onerNameShow オーナー名称 プルダウン表示用
     */
    public void setOnerNameShow(String onerNameShow) {
        this.onerNameShow = onerNameShow;
    }
    
    //20060621 追加 end ---------------------------------------
}
