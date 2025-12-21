package jp.co.isid.mos.bird.storeinfo.eiseiref.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * Entity[PDF店舗衛生情報]
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class TrnBd33shtb {
    
    public static final String TABLE = "BD33SHTB";
    
    public static final String bnfcYear_COLUMN = "BNFC_YEAR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String machine1Name_COLUMN = "MACHINE1_NAME";
    
    public static final String machine2Name_COLUMN = "MACHINE2_NAME";
    
    public static final String gridole1Name_COLUMN = "GRIDOLE1_NAME";
    
    public static final String gridole2Name_COLUMN = "GRIDOLE2_NAME";
    
    public static final String fryer1Name_COLUMN = "FRYER1_NAME";
    
    public static final String fryer2Name_COLUMN = "FRYER2_NAME";
    
    public static final String fryer3Name_COLUMN = "FRYER3_NAME";
    
    public static final String waterKind_COLUMN = "WATER_KIND";
    
    public static final String typeStartDate_COLUMN = "TYPE_START_DATE";
    
    public static final String typeNm_COLUMN = "TYPE_NM";
    
    public static final String pdfFilenm_COLUMN = "PDF_FILENM";
    
    public static final String kakuDate_COLUMN = "KAKU_DATE";
    
    public static final String tensoDate_COLUMN = "TENSO_DATE";
    
    /**
     * 実施年度
     */
    private String bnfcYear;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店コード
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
     * シェイクマシン１
     */
    private String machine1Name;
    
    /**
     * シェイクマシン２
     */
    private String machine2Name;
    
    /**
     * グりドル１
     */
    private String gridole1Name;
    
    /**
     * グりドル２
     */
    private String gridole2Name;
    
    /**
     * フライヤー１
     */
    private String fryer1Name;
    
    /**
     * フライヤー２
     */
    private String fryer2Name;
    
    /**
     * フライヤー３
     */
    private String fryer3Name;
    
    /**
     * 水の種類
     */
    private String waterKind;
    
    /**
     * 現契約開始日
     */
    private String typeStartDate;
    
    /**
     * 契約タイプ名
     */
    private String typeNm;
    
    /**
     * PDFファイル名
     */
    private String pdfFilenm;
    
    /**
     * 確定日
     */
    private String kakuDate;
    
    /**
     * 転送日
     */
    private String tensoDate;
    
    /**
     * 実施年度を取得します。
     * @return 実施年度
     */
    public String getBnfcYear() {
        return bnfcYear;
    }
    /**
     * 実施年度を設定します。
     * @param bnfcYear 実施年度
     */
    public void setBnfcYear(String bnfcYear) {
        this.bnfcYear = bnfcYear;
    }
    
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
     * シェイクマシン１を取得します。
     * @return シェイクマシン１
     */
    public String getMachine1Name() {
        return CommonUtil.rAllSpaceTrim(machine1Name);
    }
    /**
     * シェイクマシン１を設定します。
     * @param machine1Name シェイクマシン１
     */
    public void setMachine1Name(String machine1Name) {
        this.machine1Name = machine1Name;
    }
    
    /**
     * シェイクマシン２を取得します。
     * @return シェイクマシン２
     */
    public String getMachine2Name() {
        return CommonUtil.rAllSpaceTrim(machine2Name);
    }
    /**
     * シェイクマシン２を設定します。
     * @param machine2Name シェイクマシン２
     */
    public void setMachine2Name(String machine2Name) {
        this.machine2Name = machine2Name;
    }
    
    /**
     * グりドル１を取得します。
     * @return グりドル１
     */
    public String getGridole1Name() {
        return CommonUtil.rAllSpaceTrim(gridole1Name);
    }
    /**
     * グりドル１を設定します。
     * @param gridole1Name グりドル１
     */
    public void setGridole1Name(String gridole1Name) {
        this.gridole1Name = gridole1Name;
    }
    
    /**
     * グりドル２を取得します。
     * @return グりドル２
     */
    public String getGridole2Name() {
        return CommonUtil.rAllSpaceTrim(gridole2Name);
    }
    /**
     * グりドル２を設定します。
     * @param gridole2Name グりドル２
     */
    public void setGridole2Name(String gridole2Name) {
        this.gridole2Name = gridole2Name;
    }
    
    /**
     * フライヤー１を取得します。
     * @return フライヤー１
     */
    public String getFryer1Name() {
        return CommonUtil.rAllSpaceTrim(fryer1Name);
    }
    /**
     * フライヤー１を設定します。
     * @param fryer1Name フライヤー１
     */
    public void setFryer1Name(String fryer1Name) {
        this.fryer1Name = fryer1Name;
    }
    
    /**
     * フライヤー２を取得します。
     * @return フライヤー２
     */
    public String getFryer2Name() {
        return CommonUtil.rAllSpaceTrim(fryer2Name);
    }
    /**
     * フライヤー２を設定します。
     * @param fryer2Name フライヤー２
     */
    public void setFryer2Name(String fryer2Name) {
        this.fryer2Name = fryer2Name;
    }
    
    /**
     * フライヤー３を取得します。
     * @return フライヤー３
     */
    public String getFryer3Name() {
        return CommonUtil.rAllSpaceTrim(fryer3Name);
    }
    /**
     * フライヤー３を設定します。
     * @param fryer3Name フライヤー３
     */
    public void setFryer3Name(String fryer3Name) {
        this.fryer3Name = fryer3Name;
    }
    
    /**
     * 水の種類を取得します。
     * @return 水の種類
     */
    public String getWaterKind() {
        return waterKind;
    }
    /**
     * 水の種類を設定します。
     * @param waterKind 水の種類
     */
    public void setWaterKind(String waterKind) {
        this.waterKind = waterKind;
    }
    
    /**
     * 現契約開始日を取得します。
     * @return 現契約開始日
     */
    public String getTypeStartDate() {
        return typeStartDate;
    }
    /**
     * 現契約開始日を設定します。
     * @param typeStartDate 現契約開始日
     */
    public void setTypeStartDate(String typeStartDate) {
        this.typeStartDate = typeStartDate;
    }
    
    /**
     * 契約タイプ名を取得します。
     * @return 契約タイプ名
     */
    public String getTypeNm() {
        return CommonUtil.rAllSpaceTrim(typeNm);
    }
    /**
     * 契約タイプ名を設定します。
     * @param typeNm 契約タイプ名
     */
    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }
    
    /**
     * PDFファイル名を取得します。
     * @return PDFファイル名
     */
    public String getPdfFilenm() {
        return CommonUtil.rAllSpaceTrim(pdfFilenm);
    }
    /**
     * PDFファイル名を設定します。
     * @param pdfFilenm PDFファイル名
     */
    public void setPdfFilenm(String pdfFilenm) {
        this.pdfFilenm = pdfFilenm;
    }
    
    /**
     * 確定日を取得します。
     * @return 確定日
     */
    public String getKakuDate() {
        return kakuDate;
    }
    /**
     * 確定日を設定します。
     * @param kakuDate 確定日
     */
    public void setKakuDate(String kakuDate) {
        this.kakuDate = kakuDate;
    }
    
    /**
     * 転送日を取得します。
     * @return 転送日
     */
    public String getTensoDate() {
        return tensoDate;
    }
    /**
     * 転送日を設定します。
     * @param tensoDate 転送日
     */
    public void setTensoDate(String tensoDate) {
        this.tensoDate = tensoDate;
    }
	/**
	 * @return クラス変数miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return CommonUtil.rAllSpaceTrim(miseNameKj);
	}
	/**
	 * @param miseNameKj を クラス変数miseNameKjへ設定します。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
	/**
	 * @return クラス変数sibuCd を戻します。
	 */
	public String getSibuCd() {
		return CommonUtil.rAllSpaceTrim(sibuCd);
	}
	/**
	 * @param sibuCd を クラス変数sibuCdへ設定します。
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}
	/**
	 * @return クラス変数sibuName を戻します。
	 */
	public String getSibuName() {
		return CommonUtil.rAllSpaceTrim(sibuName);
	}
	/**
	 * @param sibuName を クラス変数sibuNameへ設定します。
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}
    
}
