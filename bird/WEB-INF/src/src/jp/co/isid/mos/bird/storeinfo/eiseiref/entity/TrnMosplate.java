package jp.co.isid.mos.bird.storeinfo.eiseiref.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * Entity[モスプレート検査]
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class TrnMosplate {
    
    public static final String TABLE = "BT85KTFP";
    
    public static final String bnfcYear_COLUMN = "BNFC_YEAR";
    
    public static final String statusCd_COLUMN = "STATUS_CD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String makeBusyoCd_COLUMN = "MAKE_BUSYO_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String mngSeq_COLUMN = "MNG_SEQ";
    
    public static final String areaCd_COLUMN = "AREA_CD";
    
    public static final String areaNm_COLUMN = "AREA_NM";
    
    public static final String shibuCd_COLUMN = "SHIBU_CD";
    
    public static final String shibuNm_COLUMN = "SHIBU_NM";
    
    public static final String makeCmpnyNm_COLUMN = "MAKE_CMPNY_NM";
    
    public static final String makeDitlNm_COLUMN = "MAKE_DITL_NM";
    
    public static final String successCd_COLUMN = "SUCCESS_CD";
    
    public static final String successNm_COLUMN = "SUCCESS_NM";
    
    public static final String exDate_COLUMN = "EX_DATE";
    
    public static final String artcCd_COLUMN = "ARTC_CD";
    
    public static final String artcNm_COLUMN = "ARTC_NM";
    
    public static final String buyDate_COLUMN = "BUY_DATE";
    
    public static final String buyTime_COLUMN = "BUY_TIME";
    
    public static final String buyMan_COLUMN = "BUY_MAN";
    
    public static final String exNote_COLUMN = "EX_NOTE";
    
    public static final String spcJg_COLUMN = "SPC_JG";
    
    public static final String spcRslt_COLUMN = "SPC_RSLT";
    
    public static final String clnJg_COLUMN = "CLN_JG";
    
    public static final String clnRslt_COLUMN = "CLN_RSLT";
    
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
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 製造元会部署区分
     */
    private String makeBusyoCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 管理SEQ
     */
    private java.math.BigDecimal mngSeq;
    
    /**
     * エリアコード
     */
    private String areaCd;
    
    /**
     * エリア名
     */
    private String areaNm;
    
    /**
     * 支部コード
     */
    private String shibuCd;
    
    /**
     * 支部名
     */
    private String shibuNm;
    
    /**
     * 製造元会社名
     */
    private String makeCmpnyNm;
    
    /**
     * 製造元明細名
     */
    private String makeDitlNm;
    
    /**
     * 合否コード
     */
    private String successCd;
    
    /**
     * 合否名称
     */
    private String successNm;
    
    /**
     * 検査日
     */
    private String exDate;
    
    /**
     * 検体品目コード
     */
    private String artcCd;
    
    /**
     * 検体品目名
     */
    private String artcNm;
    
    /**
     * 実施年月日
     */
    private String buyDate;
    
    /**
     * 実施時間
     */
    private String buyTime;
    
    /**
     * 実施者
     */
    private String buyMan;
    
    /**
     * 備考
     */
    private String exNote;
    
    /**
     * 一般生菌数判定
     */
    private String spcJg;
    
    /**
     * 一般生菌数検査値
     */
    private String spcRslt;
    
    /**
     * 大腸菌群判定
     */
    private String clnJg;
    
    /**
     * 大腸菌群検査値
     */
    private String clnRslt;
    
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
     * 製造元会部署区分を取得します。
     * @return 製造元会部署区分
     */
    public String getMakeBusyoCd() {
        return makeBusyoCd;
    }
    /**
     * 製造元会部署区分を設定します。
     * @param makeBusyoCd 製造元会部署区分
     */
    public void setMakeBusyoCd(String makeBusyoCd) {
        this.makeBusyoCd = makeBusyoCd;
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
     * 管理SEQを取得します。
     * @return 管理SEQ
     */
    public java.math.BigDecimal getMngSeq() {
        return mngSeq;
    }
    /**
     * 管理SEQを設定します。
     * @param mngSeq 管理SEQ
     */
    public void setMngSeq(java.math.BigDecimal mngSeq) {
        this.mngSeq = mngSeq;
    }
    
    /**
     * エリアコードを取得します。
     * @return エリアコード
     */
    public String getAreaCd() {
        return areaCd;
    }
    /**
     * エリアコードを設定します。
     * @param areaCd エリアコード
     */
    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }
    
    /**
     * エリア名を取得します。
     * @return エリア名
     */
    public String getAreaNm() {
        return CommonUtil.rAllSpaceTrim(areaNm);
    }
    /**
     * エリア名を設定します。
     * @param areaNm エリア名
     */
    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getShibuCd() {
        return shibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param shibuCd 支部コード
     */
    public void setShibuCd(String shibuCd) {
        this.shibuCd = shibuCd;
    }
    
    /**
     * 支部名を取得します。
     * @return 支部名
     */
    public String getShibuNm() {
        return CommonUtil.rAllSpaceTrim(shibuNm);
    }
    /**
     * 支部名を設定します。
     * @param shibuNm 支部名
     */
    public void setShibuNm(String shibuNm) {
        this.shibuNm = shibuNm;
    }
    
    /**
     * 製造元会社名を取得します。
     * @return 製造元会社名
     */
    public String getMakeCmpnyNm() {
        return CommonUtil.rAllSpaceTrim(makeCmpnyNm);
    }
    /**
     * 製造元会社名を設定します。
     * @param makeCmpnyNm 製造元会社名
     */
    public void setMakeCmpnyNm(String makeCmpnyNm) {
        this.makeCmpnyNm = makeCmpnyNm;
    }
    
    /**
     * 製造元明細名を取得します。
     * @return 製造元明細名
     */
    public String getMakeDitlNm() {
        return CommonUtil.rAllSpaceTrim(makeDitlNm);
    }
    /**
     * 製造元明細名を設定します。
     * @param makeDitlNm 製造元明細名
     */
    public void setMakeDitlNm(String makeDitlNm) {
        this.makeDitlNm = makeDitlNm;
    }
    
    /**
     * 合否コードを取得します。
     * @return 合否コード
     */
    public String getSuccessCd() {
        return successCd;
    }
    /**
     * 合否コードを設定します。
     * @param successCd 合否コード
     */
    public void setSuccessCd(String successCd) {
        this.successCd = successCd;
    }
    
    /**
     * 合否名称を取得します。
     * @return 合否名称
     */
    public String getSuccessNm() {
        return CommonUtil.rAllSpaceTrim(successNm);
    }
    /**
     * 合否名称を設定します。
     * @param successNm 合否名称
     */
    public void setSuccessNm(String successNm) {
        this.successNm = successNm;
    }
    
    /**
     * 検査日を取得します。
     * @return 検査日
     */
    public String getExDate() {
        return exDate;
    }
    /**
     * 検査日を設定します。
     * @param exDate 検査日
     */
    public void setExDate(String exDate) {
        this.exDate = exDate;
    }
    
    /**
     * 検体品目コードを取得します。
     * @return 検体品目コード
     */
    public String getArtcCd() {
        return artcCd;
    }
    /**
     * 検体品目コードを設定します。
     * @param artcCd 検体品目コード
     */
    public void setArtcCd(String artcCd) {
        this.artcCd = artcCd;
    }
    
    /**
     * 検体品目名を取得します。
     * @return 検体品目名
     */
    public String getArtcNm() {
        return CommonUtil.rAllSpaceTrim(artcNm);
    }
    /**
     * 検体品目名を設定します。
     * @param artcNm 検体品目名
     */
    public void setArtcNm(String artcNm) {
        this.artcNm = artcNm;
    }
    
    /**
     * 実施年月日を取得します。
     * @return 実施年月日
     */
    public String getBuyDate() {
        return buyDate;
    }
    /**
     * 実施年月日を設定します。
     * @param buyDate 実施年月日
     */
    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }
    
    /**
     * 実施時間を取得します。
     * @return 実施時間
     */
    public String getBuyTime() {
        return buyTime;
    }
    /**
     * 実施時間を設定します。
     * @param buyTime 実施時間
     */
    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
    
    /**
     * 実施者を取得します。
     * @return 実施者
     */
    public String getBuyMan() {
        return buyMan;
    }
    /**
     * 実施者を設定します。
     * @param buyMan 実施者
     */
    public void setBuyMan(String buyMan) {
        this.buyMan = buyMan;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getExNote() {
        return CommonUtil.rAllSpaceTrim(exNote);
    }
    /**
     * 備考を設定します。
     * @param exNote 備考
     */
    public void setExNote(String exNote) {
        this.exNote = exNote;
    }
    
    /**
     * 一般生菌数判定を取得します。
     * @return 一般生菌数判定
     */
    public String getSpcJg() {
        return CommonUtil.rAllSpaceTrim(spcJg);
    }
    /**
     * 一般生菌数判定を設定します。
     * @param spcJg 一般生菌数判定
     */
    public void setSpcJg(String spcJg) {
        this.spcJg = spcJg;
    }
    
    /**
     * 一般生菌数検査値を取得します。
     * @return 一般生菌数検査値
     */
    public String getSpcRslt() {
        return CommonUtil.rAllSpaceTrim(spcRslt);
    }
    /**
     * 一般生菌数検査値を設定します。
     * @param spcRslt 一般生菌数検査値
     */
    public void setSpcRslt(String spcRslt) {
        this.spcRslt = spcRslt;
    }
    
    /**
     * 大腸菌群判定を取得します。
     * @return 大腸菌群判定
     */
    public String getClnJg() {
        return CommonUtil.rAllSpaceTrim(clnJg);
    }
    /**
     * 大腸菌群判定を設定します。
     * @param clnJg 大腸菌群判定
     */
    public void setClnJg(String clnJg) {
        this.clnJg = clnJg;
    }
    
    /**
     * 大腸菌群検査値を取得します。
     * @return 大腸菌群検査値
     */
    public String getClnRslt() {
        return CommonUtil.rAllSpaceTrim(clnRslt);
    }
    /**
     * 大腸菌群検査値を設定します。
     * @param clnRslt 大腸菌群検査値
     */
    public void setClnRslt(String clnRslt) {
        this.clnRslt = clnRslt;
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
