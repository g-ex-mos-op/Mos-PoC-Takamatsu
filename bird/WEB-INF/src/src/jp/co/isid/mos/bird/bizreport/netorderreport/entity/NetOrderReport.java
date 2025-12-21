package jp.co.isid.mos.bird.bizreport.netorderreport.entity;

public class NetOrderReport {

	private static final String TABLE ="BT63SNIP";
	private static final String jigyouCd_COLUMN     = "JIGYOU_CD";
	private static final String jigyouName_COLUMN   = "JIGYOU_NAME";
	private static final String slareaCd_COLUMN     = "SLAREA_CD";
	private static final String slareaName_COLUMN   = "SLAREA_NAME";
	private static final String sibuCd_COLUMN       = "SIBU_CD";
	private static final String sibuName_COLUMN     = "SIBU_NAME";
	private static final String areaDai_COLUMN      = "AREA_DAI";
	private static final String areaName_COLUMN     = "AREA_NAME";
	private static final String miseCd_COLUMN       = "MISE_CD";
	private static final String miseNameKj_COLUMN   = "MISE_NAME_KJ";
	private static final String onerCd_COLUMN       = "ONER_CD";
	private static final String onerNameKj_COLUMN   = "ONER_NAME_KJ";

	private static final String otherKin7_COLUMN    = "OTHER_KIN_7";
	private static final String otherKin8_COLUMN    = "OTHER_KIN_8";
	private static final String eat_kin_COLUMN      = "EAT_KIN";
	private static final String takeKin_COLUMN      = "TAKE_KIN";
	private static final String telKin_COLUMN       = "TEL_KIN";
	private static final String driveKin_COLUMN     = "DRIVE_KIN";
	private static final String otherKin1_COLUMN    = "OTHER_KIN_1";
	private static final String otherKin2_COLUMN    = "OTHER_KIN_2";

	private static final String otherKen7_COLUMN    = "OTHER_KEN_7";
	private static final String otherKen8_COLUMN    = "OTHER_KEN_8";
	private static final String eatKen_COLUMN       = "EAT_KEN";
	private static final String takeKen_COLUMN      = "TAKE_KEN";
	private static final String telKen_COLUMN       = "TEL_KEN";
	private static final String driveKen_COLUMN     = "DRIVE_KEN";
	private static final String otherKen1_COLUMN    = "OTHER_KEN_1";
	private static final String otherKen2_COLUMN    = "OTHER_KEN_2";

	private static final String zenOtherKin7_COLUMN = "ZEN_OTHER_KIN_7";
	private static final String zenOtherKin8_COLUMN = "ZEN_OTHER_KIN_8";
	private static final String zenEat_kin_COLUMN   = "ZEN_EAT_KIN";
	private static final String zenTakeKin_COLUMN   = "ZEN_TAKE_KIN";
	private static final String zenTelKin_COLUMN    = "ZEN_TEL_KIN";
	private static final String zenDriveKin_COLUMN  = "ZEN_DRIVE_KIN";
	private static final String zenOtherKin1_COLUMN = "ZEN_OTHER_KIN_1";
	private static final String zenOtherKin2_COLUMN = "ZEN_OTHER_KIN_2";

	private static final String zenOtherKen7_COLUMN = "ZEN_OTHER_KEN_7";
	private static final String zenOtherKen8_COLUMN = "ZEN_OTHER_KEN_8";
	private static final String zenEatKen_COLUMN    = "ZEN_EAT_KEN";
	private static final String zenTakeKen_COLUMN   = "ZEN_TAKE_KEN";
	private static final String zenTelKen_COLUMN    = "ZEN_TEL_KEN";
	private static final String zenDriveKen_COLUMN  = "ZEN_DRIVE_KEN";
	private static final String zenOtherKen1_COLUMN = "ZEN_OTHER_KEN_1";
	private static final String zenOtherKen2_COLUMN = "ZEN_OTHER_KEN_2";


	/** 事業本部コード */
	private String jigyouCd;
	/** 事業本部名称 */
	private String jigyouName;
	/** 営業エリアコード */
	private String slareaCd;
	/** 営業エリア名称 */
	private String slareaName;
	/** 支部コード */
	private String sibuCd;
	/** 支部名称 */
	private String sibuName;
	/** 支部取込コード */
	private String areaDai;
	/** 支部取込名 */
	private String areaName;
	/** 店コード */
	private String miseCd;
	/** 店名称 */
	private String miseNameKj;
	/** オーナーコード */
	private String onerCd;
	/** オーナー名称 */
	private String onerNameKj;
	/** ネットテイク金額 */
	private String otherKin7;
	/** ネット宅配金額 */
	private String otherKin8;
	/** EAT-IN金額 */
	private String eatKin;
	/** TAKE-OUT金額 */
	private String takeKin;
	/** TEL-ORDER金額 */
	private String telKin;
	/** DRV-TH金額 */
	private String driveKin;
	/** 宅配金額 */
	private String otherKin1;
	/** 外販金額 */
	private String otherKin2;
	/** ネットテイク件数 */
	private String otherKen7;
	/** ネット宅配件数 */
	private String otherKen8;
	/** EAT-IN件数 */
	private String eatKen;
	/** TAKE-OUT件数 */
	private String takeKen;
	/** TEL-ORDER件数 */
	private String telKen;
	/** DRV-TH件数 */
	private String driveKen;
	/** 宅配件数 */
	private String otherKen1;
	/** 外販件数 */
	private String otherKen2;
	/** 前年ネットテイク金額 */
	private String zenOtherKin7;
	/** 前年ネット宅配金額 */
	private String zenOtherKin8;
	/** 前年EAT-IN金額 */
	private String zenEatKin;
	/** 前年TAKE-OUT金額 */
	private String zenTakeKin;
	/** 前年TEL-ORDER金額 */
	private String zenTelKin;
	/** 前年DRV-TH金額 */
	private String zenDriveKin;
	/** 前年宅配金額 */
	private String zenOtherKin1;
	/** 前年外販金額 */
	private String zenOtherKin2;
	/** 前年ネットテイク件数 */
	private String zenOtherKen7;
	/** 前年ネット宅配件数 */
	private String zenOtherKen8;
	/** 前年EAT-IN件数 */
	private String zenEatKen;
	/** 前年TAKE-OUT件数 */
	private String zenTakeKen;
	/** 前年TEL-ORDER件数 */
	private String zenTelKen;
	/** 前年DRV-TH件数 */
	private String zenDriveKen;
	/** 前年宅配件数 */
	private String zenOtherKen1;
	/** 前年外販件数 */
	private String zenOtherKen2;

	/**
	 * 事業本部コードを取得します
	 * @return 事業本部コード
	 */
	public String getJigyouCd() {
		return convString(jigyouCd);
	}

	/**
	 * 事業本部コードを設定します
	 * @param jigyouCd 事業本部コード
	 */
	public void setJigyouCd(String jigyouCd) {
		this.jigyouCd = jigyouCd;
	}

	/**
	 * 事業本部名称を取得します
	 * @return 事業本部名称
	 */
	public String getJigyouName() {
		return convString(jigyouName);
	}

	/**
	 * 事業本部名称を設定します
	 * @param jigyouName 事業本部名称
	 */
	public void setJigyouName(String jigyouName) {
		this.jigyouName = jigyouName;
	}

	/**
	 * 営業エリアコードを取得します
	 * @return 営業エリアコード
	 */
	public String getSlareaCd() {
		return convString(slareaCd);
	}

	/**
	 * 営業エリアコードを設定します
	 * @param slareaCd 営業エリアコード
	 */
	public void setSlareaCd(String slareaCd) {
		this.slareaCd = slareaCd;
	}

	/**
	 * 営業エリア名称を取得します
	 * @return 営業エリア名称
	 */
	public String getSlareaName() {
		return convString(slareaName);
	}

	/**
	 * 営業エリア名称を設定します
	 * @param slareaName 営業エリア名称
	 */
	public void setSlareaName(String slareaName) {
		this.slareaName = slareaName;
	}

	/**
	 * 支部コードを取得します
	 * @return 支部コード
	 */
	public String getSibuCd() {
		return convString(sibuCd);
	}

	/**
	 * 支部コードを設定します
	 * @param sibuCd 支部コード
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

	/**
	 * 支部名称を取得します
	 * @return 支部名称
	 */
	public String getSibuName() {
		return convString(sibuName);
	}

	/**
	 * 支部名称を設定します
	 * @param sibuName 支部名称
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}

	/**
	 * 支部取込コードを取得します
	 * @return 支部取込コード
	 */
	public String getAreaDai() {
		return convString(areaDai);
	}

	/**
	 * 支部取込コードを設定します
	 * @param areaDai 支部取込コード
	 */
	public void setAreaDai(String areaDai) {
		this.areaDai = areaDai;
	}

	/**
	 * 支部取込名を取得します
	 * @return 支部取込名
	 */
	public String getAreaName() {
		return convString(areaName);
	}

	/**
	 * 支部取込名を設定します
	 * @param areaName 支部取込名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 店コードを取得します
	 * @return 店コード
	 */
	public String getMiseCd() {
		return convString(miseCd);
	}

	/**
	 * 店コードを設定します
	 * @param miseCd 店コード
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	/**
	 * 店名称を取得します
	 * @return 店名称
	 */
	public String getMiseNameKj() {
		return convString(miseNameKj);
	}

	/**
	 * 店名称を設定します
	 * @param miseNameKj店名称
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}

	/**
	 * オーナーコードを取得します
	 * @return オーナーコード
	 */
	public String getOnerCd() {
		return convString(onerCd);
	}

	/**
	 * オーナーコードを設定します
	 * @param onerCd オーナーコード
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}

	/**
	 * オーナー名称を取得します
	 * @return オーナー名称
	 */
	public String getOnerNameKj() {
		return convString(onerNameKj);
	}

	/**
	 * オーナー名称を設定します
	 * @param onerNameKj オーナー名称
	 */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}

	/**
	 * ネットテイク金額を取得します
	 * @return ネットテイク金額
	 */
	public String getOtherKin7() {
		return convString(otherKin7);
	}

	/**
	 * ネットテイク金額を設定します
	 * @param otherKin7 ネットテイク金額
	 */
	public void setOtherKin7(String otherKin7) {
		this.otherKin7 = otherKin7;
	}

	/**
	 * ネット宅配金額を取得します
	 * @return ネット宅配金額
	 */
	public String getOtherKin8() {
		return convString(otherKin8);
	}

	/**
	 * ネット宅配金額を設定します
	 * @param otherKin8 ネット宅配金額
	 */
	public void setOtherKin8(String otherKin8) {
		this.otherKin8 = otherKin8;
	}

	/**
	 * EAT-IN金額を取得します
	 * @return EAT-IN金額
	 */
	public String getEatKin() {
		return convString(eatKin);
	}

	/**
	 * EAT-IN金額を設定します
	 * @param eatKin EAT-IN金額
	 */
	public void setEatKin(String eatKin) {
		this.eatKin = eatKin;
	}

	/**
	 * TAKE-OUT金額を取得します
	 * @return TAKE-OUT金額
	 */
	public String getTakeKin() {
		return convString(takeKin);
	}

	/**
	 * TAKE-OUT金額を設定します
	 * @param takeKin TAKE-OUT金額
	 */
	public void setTakeKin(String takeKin) {
		this.takeKin = takeKin;
	}

	/**
	 * TEL-ORDER金額を取得します
	 * @return TEL-ORDER金額
	 */
	public String getTelKin() {
		return convString(telKin);
	}

	/**
	 * TEL-ORDER金額を設定します
	 * @param telKin TEL-ORDER金額
	 */
	public void setTelKin(String telKin) {
		this.telKin = telKin;
	}

	/**
	 * DRV-TH金額を取得します
	 * @return DRV-TH金額
	 */
	public String getDriveKin() {
		return convString(driveKin);
	}

	/**
	 * DRV-TH金額を設定します
	 * @param driveKin DRV-TH金額
	 */
	public void setDriveKin(String driveKin) {
		this.driveKin = driveKin;
	}

	/**
	 * 宅配金額を取得します
	 * @return 宅配金額
	 */
	public String getOtherKin1() {
		return convString(otherKin1);
	}

	/**
	 * 宅配金額を設定します
	 * @param otherKin1 宅配金額
	 */
	public void setOtherKin1(String otherKin1) {
		this.otherKin1 = otherKin1;
	}

	/**
	 * 外販金額を取得します
	 * @return 外販金額
	 */
	public String getOtherKin2() {
		return convString(otherKin2);
	}

	/**
	 * 外販金額を設定します
	 * @param otherKin2 外販金額
	 */
	public void setOtherKin2(String otherKin2) {
		this.otherKin2 = otherKin2;
	}

	/**
	 * ネットテイク件数を取得します
	 * @return ネットテイク件数
	 */
	public String getOtherKen7() {
		return convString(otherKen7);
	}

	/**
	 * ネットテイク件数を設定します
	 * @param otherKen7 ネットテイク件数
	 */
	public void setOtherKen7(String otherKen7) {
		this.otherKen7 = otherKen7;
	}

	/**
	 * ネット宅配件数を取得します
	 * @return ネット宅配件数
	 */
	public String getOtherKen8() {
		return convString(otherKen8);
	}

	/**
	 * ネット宅配件数を設定します
	 * @param otherKen8 ネット宅配件数
	 */
	public void setOtherKen8(String otherKen8) {
		this.otherKen8 = otherKen8;
	}

	/**
	 * EAT-IN件数を取得します
	 * @return EAT-IN件数
	 */
	public String getEatKen() {
		return convString(eatKen);
	}

	/**
	 * EAT-IN件数を設定します
	 * @param eatKen EAT-IN件数
	 */
	public void setEatKen(String eatKen) {
		this.eatKen = eatKen;
	}

	/**
	 * TAKE-OUT件数を取得します
	 * @return TAKE-OUT件数
	 */
	public String getTakeKen() {
		return convString(takeKen);
	}

	/**
	 * TAKE-OUT件数を設定します
	 * @param takeKen TAKE-OUT件数
	 */
	public void setTakeKen(String takeKen) {
		this.takeKen = takeKen;
	}

	/**
	 * TEL-ORDER件数を取得します
	 * @return TEL-ORDER件数
	 */
	public String getTelKen() {
		return convString(telKen);
	}

	/**
	 * TEL-ORDER件数を設定します
	 * @param telKen TEL-ORDER件数
	 */
	public void setTelKen(String telKen) {
		this.telKen = telKen;
	}

	/**
	 * DRV-TH件数を取得します
	 * @return DRV-TH件数
	 */
	public String getDriveKen() {
		return convString(driveKen);
	}

	/**
	 * DRV-TH件数を設定します
	 * @param driveKen DRV-TH件数
	 */
	public void setDriveKen(String driveKen) {
		this.driveKen = driveKen;
	}

	/**
	 * 宅配件数を取得します
	 * @return 宅配件数
	 */
	public String getOtherKen1() {
		return convString(otherKen1);
	}

	/**
	 * 宅配件数を設定します
	 * @param otherKen1 宅配件数
	 */
	public void setOtherKen1(String otherKen1) {
		this.otherKen1 = otherKen1;
	}

	/**
	 * 外販件数を取得します
	 * @return 外販件数
	 */
	public String getOtherKen2() {
		return convString(otherKen2);
	}

	/**
	 * 外販件数を設定します
	 * @param otherKen2
	 */
	public void setOtherKen2(String otherKen2) {
		this.otherKen2 = otherKen2;
	}

	/**
	 * 前年ネットテイク金額を取得します
	 * @return 前年ネットテイク金額
	 */
	public String getZenOtherKin7() {
		return convString(zenOtherKin7);
	}

	/**
	 * 前年ネットテイク金額を設定します
	 * @param zenOtherKin7 前年ネットテイク金額
	 */
	public void setZenOtherKin7(String zenOtherKin7) {
		this.zenOtherKin7 = zenOtherKin7;
	}

	/**
	 * 前年ネット宅配金額を取得します
	 * @return 前年ネット宅配金額
	 */
	public String getZenOtherKin8() {
		return convString(zenOtherKin8);
	}

	/**
	 * 前年ネット宅配金額を設定します
	 * @param zenOtherKin8 前年ネット宅配金額
	 */
	public void setZenOtherKin8(String zenOtherKin8) {
		this.zenOtherKin8 = zenOtherKin8;
	}

	/**
	 * 前年EAT-IN金額を取得します
	 * @return 前年EAT-IN金額
	 */
	public String getZenEatKin() {
		return convString(zenEatKin);
	}

	/**
	 * 前年EAT-IN金額を設定します
	 * @param zenEatKin 前年EAT-IN金額
	 */
	public void setZenEatKin(String zenEatKin) {
		this.zenEatKin = zenEatKin;
	}

	/**
	 * 前年TAKE-OUT金額を取得します
	 * @return 前年TAKE-OUT金額
	 */
	public String getZenTakeKin() {
		return convString(zenTakeKin);
	}

	/**
	 * 前年TAKE-OUT金額を設定します
	 * @param 前年zenTakeKin
	 */
	public void setZenTakeKin(String zenTakeKin) {
		this.zenTakeKin = zenTakeKin;
	}

	/**
	 * 前年TEL-ORDER金額を取得します
	 * @return 前年TEL-ORDER金額
	 */
	public String getZenTelKin() {
		return convString(zenTelKin);
	}

	/**
	 * 前年TEL-ORDER金額を設定します
	 * @param zenTelKin 前年TEL-ORDER金額
	 */
	public void setZenTelKin(String zenTelKin) {
		this.zenTelKin = zenTelKin;
	}

	/**
	 * 前年DRV-TH金額を取得します
	 * @return 前年DRV-TH金額
	 */
	public String getZenDriveKin() {
		return convString(zenDriveKin);
	}

	/**
	 * 前年DRV-TH金額を設定します
	 * @param zenDriveKin 前年DRV-TH金額
	 */
	public void setZenDriveKin(String zenDriveKin) {
		this.zenDriveKin = zenDriveKin;
	}

	/**
	 * 前年宅配金額を取得します
	 * @return 前年宅配金額
	 */
	public String getZenOtherKin1() {
		return convString(zenOtherKin1);
	}

	/**
	 * 前年宅配金額を設定します
	 * @param zenOtherKin1 前年宅配金額
	 */
	public void setZenOtherKin1(String zenOtherKin1) {
		this.zenOtherKin1 = zenOtherKin1;
	}

	/**
	 * 前年外販金額を取得します
	 * @return 前年外販金額
	 */
	public String getZenOtherKin2() {
		return convString(zenOtherKin2);
	}

	/**
	 * 前年外販金額を設定します
	 * @param zenOtherKin2 前年外販金額
	 */
	public void setZenOtherKin2(String zenOtherKin2) {
		this.zenOtherKin2 = zenOtherKin2;
	}

	/**
	 * 前年ネットテイク件数を取得します
	 * @return 前年ネットテイク件数
	 */
	public String getZenOtherKen7() {
		return convString(zenOtherKen7);
	}

	/**
	 * 前年ネットテイク件数を設定します
	 * @param zenOtherKen7 前年ネットテイク件数
	 */
	public void setZenOtherKen7(String zenOtherKen7) {
		this.zenOtherKen7 = zenOtherKen7;
	}

	/**
	 * 前年ネット宅配件数を取得します
	 * @return 前年ネット宅配件数
	 */
	public String getZenOtherKen8() {
		return convString(zenOtherKen8);
	}

	/**
	 * 前年ネット宅配件数を設定します
	 * @param zenOtherKen8 前年ネット宅配件数
	 */
	public void setZenOtherKen8(String zenOtherKen8) {
		this.zenOtherKen8 = zenOtherKen8;
	}

	/**
	 * 前年EAT-IN件数を取得します
	 * @return 前年EAT-IN件数
	 */
	public String getZenEatKen() {
		return convString(zenEatKen);
	}

	/**
	 * 前年EAT-IN件数を設定します
	 * @param zenEatKen 前年EAT-IN件数
	 */
	public void setZenEatKen(String zenEatKen) {
		this.zenEatKen = zenEatKen;
	}

	/**
	 * 前年TAKE-OUT件数を取得します
	 * @return 前年TAKE-OUT件数
	 */
	public String getZenTakeKen() {
		return convString(zenTakeKen);
	}

	/**
	 * 前年TAKE-OUT件数を設定します
	 * @param zenTakeKen 前年TAKE-OUT件数
	 */
	public void setZenTakeKen(String zenTakeKen) {
		this.zenTakeKen = zenTakeKen;
	}

	/**
	 * 前年TEL-ORDER件数を取得します
	 * @return 前年TEL-ORDER件数
	 */
	public String getZenTelKen() {
		return convString(zenTelKen);
	}

	/**
	 * 前年TEL-ORDER件数を設定します
	 * @param zenTelKen 前年TEL-ORDER件数
	 */
	public void setZenTelKen(String zenTelKen) {
		this.zenTelKen = zenTelKen;
	}

	/**
	 * 前年DRV-TH件数を取得します
	 * @return 前年DRV-TH件数
	 */
	public String getZenDriveKen() {
		return convString(zenDriveKen);
	}

	/**
	 * 前年DRV-TH件数を設定します
	 * @param zenDriveKen 前年DRV-TH件数
	 */
	public void setZenDriveKen(String zenDriveKen) {
		this.zenDriveKen = zenDriveKen;
	}

	/**
	 * 前年宅配件数を取得します
	 * @return 前年宅配件数
	 */
	public String getZenOtherKen1() {
		return convString(zenOtherKen1);
	}

	/**
	 * 前年宅配件数を設定します
	 * @param zenOtherKen1 前年宅配件数
	 */
	public void setZenOtherKen1(String zenOtherKen1) {
		this.zenOtherKen1 = zenOtherKen1;
	}

	/**
	 * 前年外販件数を取得します
	 * @return 前年外販件数
	 */
	public String getZenOtherKen2() {
		return convString(zenOtherKen2);
	}

	/**
	 * 前年外販件数を設定します
	 * @param zenOtherKen2 前年外販件数
	 */
	public void setZenOtherKen2(String zenOtherKen2) {
		this.zenOtherKen2 = zenOtherKen2;
	}

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }

}
