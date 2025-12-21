package jp.co.isid.mos.bird.storeinfo.eiseiref.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * Entity[PDF総合衛生チェック]
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class TrnBd32setb {
    
    public static final String TABLE = "BD32SETB";
    
    public static final String bnfcYear_COLUMN = "BNFC_YEAR";
    
    public static final String statusCd_COLUMN = "STATUS_CD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String statusNm_COLUMN = "STATUS_NM";
    
    public static final String exDate_COLUMN = "EX_DATE";
    
    public static final String exJisshiKbn_COLUMN = "EX_JISSHI_KBN";
    
    public static final String exJisshiKbnNm_COLUMN = "EX_JISSHI_KBN_NM";
    
    public static final String pdfFilenm_COLUMN = "PDF_FILENM";
    
    public static final String kakuDate_COLUMN = "KAKU_DATE";
    
    public static final String tensoDate_COLUMN = "TENSO_DATE";
    
    /**
     * 実施年度
     */
    private String bnfcYear;
    
    /**
     * 実施状況(回数)
     */
    private String statusCd;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 実施状況名称
     */
    private String statusNm;
    
    /**
     * 実施日
     */
    private String exDate;
    
    /**
     * 検査実施区分
     */
    private String exJisshiKbn;
    
    /**
     * 検査実施区分名称
     */
    private String exJisshiKbnNm;
    
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
     * 実施状況(回数)を取得します。
     * @return 実施状況(回数)
     */
    public String getStatusCd() {
        return statusCd;
    }
    /**
     * 実施状況(回数)を設定します。
     * @param statusCd 実施状況(回数)
     */
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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
     * 実施状況名称を取得します。
     * @return 実施状況名称
     */
    public String getStatusNm() {
        return CommonUtil.rAllSpaceTrim(statusNm);
    }
    /**
     * 実施状況名称を設定します。
     * @param statusNm 実施状況名称
     */
    public void setStatusNm(String statusNm) {
        this.statusNm = statusNm;
    }
    
    /**
     * 実施日を取得します。
     * @return 実施日
     */
    public String getExDate() {
        return exDate;
    }
    /**
     * 実施日を設定します。
     * @param exDate 実施日
     */
    public void setExDate(String exDate) {
        this.exDate = exDate;
    }
    
    /**
     * 検査実施区分を取得します。
     * @return 検査実施区分
     */
    public String getExJisshiKbn() {
        return exJisshiKbn;
    }
    /**
     * 検査実施区分を設定します。
     * @param exJisshiKbn 検査実施区分
     */
    public void setExJisshiKbn(String exJisshiKbn) {
        this.exJisshiKbn = exJisshiKbn;
    }
    
    /**
     * 検査実施区分名称を取得します。
     * @return 検査実施区分名称
     */
    public String getExJisshiKbnNm() {
        return CommonUtil.rAllSpaceTrim(exJisshiKbnNm);
    }
    /**
     * 検査実施区分名称を設定します。
     * @param exJisshiKbnNm 検査実施区分名称
     */
    public void setExJisshiKbnNm(String exJisshiKbnNm) {
        this.exJisshiKbnNm = exJisshiKbnNm;
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
    
}
