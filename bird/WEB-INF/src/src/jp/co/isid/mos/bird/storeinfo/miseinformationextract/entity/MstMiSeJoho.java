package jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity;

public class MstMiSeJoho {
    public static final String TABLE = "BM73MMLT";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String miseNameKna_COLUMN = "MISE_NAME_KNA";
    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    public static final String sibuToriCd_COLUMN = "SIBU_TORI_CD";
    public static final String sibuToriName_COLUMN = "SIBU_TORI_NAME";
    public static final String miseKbn_COLUMN = "MISE_KBN";
    public static final String aiteName_COLUMN = "AITE_NAME";
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    public static final String gyotaiKbnName_COLUMN = "GYOTAI_KBN_NAME";
    public static final String svCd_COLUMN = "SV_CD";
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    public static final String openDt_COLUMN = "OPEN_DT";
    public static final String closeDt_COLUMN = "CLOSE_DT";
    public static final String miseCdSaishin_COLUMN = "MISE_CD_SAISHIN";
    public static final String miseCdShinten_COLUMN = "MISE_CD_SHINTEN";
    public static final String kenCd_COLUMN = "KEN_CD";
    public static final String kenName_COLUMN = "KEN_NAME";
    public static final String misePostNo_COLUMN = "MISE_POST_NO";
    public static final String miseAdrs1_COLUMN = "MISE_ADRS1";
    public static final String miseAdrs2_COLUMN = "MISE_ADRS2";
    public static final String miseAdrs3_COLUMN = "MISE_ADRS3";
    public static final String miseTel_COLUMN = "MISE_TEL";
    public static final String mTypeKbn_COLUMN = "M_TYPE_KBN";
    public static final String mTypeKbnName_COLUMN = "M_TYPE_KBN_NAME";
    public static final String tMiseKeitai_COLUMN = "T_MISE_KEITAI";
    public static final String mKeitaiName_COLUMN = "M_KEITAI_NAME";
    public static final String tLocateKbn_COLUMN = "T_LOCATE_KBN";
    public static final String locateName_COLUMN = "LOCATE_NAME";
    public static final String tentai_COLUMN = "TENTAI";
    public static final String tentaiStartDt_COLUMN = "TENTAI_START_DT";
    public static final String tentaiEndDt_COLUMN = "TENTAI_END_DT";
    public static final String tentaiInfo_COLUMN = "TENTAI_INFO";
    public static final String miseLeaseKCd_COLUMN = "MISE_LEASE_K_CD";

	/** 店コード */
	private String miseCd;
	/** 店名称（漢字） */
	private String miseNameKj;
	/** 店名称（カナ） */
	private String miseNameKna;
	/** オーナーコード */
	private String onerCd;
	/** オーナー名称 */
	private String onerNameKj;
	/** 支部コード */
	private String sibuCd;
	/** 支部名称 */
	private String sibuName;
	/** 支部取込コード */
	private String sibuToriCd;
	/** 支部取込コード名称 */
	private String sibuToriName;
	/** 店区分 */
	private String miseKbn;
	/** 店区分名称 */
	private String aiteName;
	/** 業態区分 */
	private String gyotaiKbn;
	/** 業態区分名称 */
	private String gyotaiKbnName;
	/** 担当SVコード */
	private String svCd;
	/** 担当SV名称 */
	private String userNameKj;
	/** 店オープン日 */
	private String openDt;
	/** 店クローズ日 */
	private String closeDt;
	/** 新店時店コード */
	private String miseCdSaishin;
	/** 最新店コード */
	private String miseCdShinten;
	/** 県コード */
	private String kenCd;
	/** 県名称 */
	private String kenName;
	/** 郵便番号 */
	private String misePostNo;
    /** 店住所1 */
	private String miseAdrs1;
	/** 店住所2 */
	private String miseAdrs2;
	/** 店住所3 */
	private String miseAdrs3;
	/** 電話番号 */
	private String miseTel;
	/** 店舗タイプ区分 */
	private String mTypeKbn;
	/** 店舗タイプ区分名称 */
	private String mTypeKbnName;
	/** 店舗形態区分 */
	private String tMiseKeitai;
	/** 店舗形態区分名称 */
	private String mKeitaiName;
	/** ロケーション区分 */
	private String tLocateKbn;
    /** ロケーション区分名称 */
	private String locateName;
	/** 転貸 */
	private String tentai;
	/** 転貸開始日 */
	private String tentaiStartDt;
	/** 転貸終了日 */
	private String tentaiEndDt;
	/** 転貸情報 */
	private String tentaiInfo;
	/** 賃貸店舗経理コード */
	private String miseLeaseKCd;

    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }

    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
    }

    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }

    /**
     * 店名称（カナ）を取得します。
     * @return 店名称（カナ）
     */
    public String getMiseNameKna() {
        return convString(miseNameKna);
    }

    /**
     * 店名称（カナ）を設定します。
     * @param miseNameKna 店名称（カナ）
     */
    public void setMiseNameKna(String miseNameKna) {
        this.miseNameKna = miseNameKna;
    }

    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return convString(onerCd);
    }

    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return convString(onerNameKj);
    }

    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }

    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
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
        return convString(sibuName);
    }

    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }

    /**
     * 支部取込コードを取得します。
     * @return 支部取込コード
     */
    public String getSibuToriCd() {
        return convString(sibuToriCd);
    }

    /**
     * 支部取込コードを設定します。
     * @param sibuToriCd 支部取込コード
     */
    public void setSibuToriCd(String sibuToriCd) {
        this.sibuToriCd = sibuToriCd;
    }

    /**
     * 支部取込コード名称を取得します。
     * @return 支部取込コード名称
     */
    public String getSibuToriName() {
        return convString(sibuToriName);
    }

    /**
     * 支部取込コード名称を設定します。
     * @param sibuToriName 支部取込コード名称
     */
    public void setSibuToriName(String sibuToriName) {
        this.sibuToriName = sibuToriName;
    }

    /**
     * 店区分を取得します。
     * @return 店区分
     */
    public String getMiseKbn() {
        return convString(miseKbn);
    }

    /**
     * 店区分を設定します。
     * @param miseKbn 店区分
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }

    /**
     * 店区分名称を取得します。
     * @return 店区分名称
     */
    public String getAiteName() {
        return convString(aiteName);
    }

    /**
     * 店区分名称を設定します。
     * @param aiteName 店区分名称
     */
    public void setAiteName(String aiteName) {
        this.aiteName = aiteName;
    }

    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return convString(gyotaiKbn);
    }

    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }

    /**
     * 業態区分名称を取得します。
     * @return 業態区分名称
     */
    public String getGyotaiKbnName() {
        return convString(gyotaiKbnName);
    }

    /**
     * 業態区分名称を設定します。
     * @param gyotaiKbnName 業態区分名称
     */
    public void setGyotaiKbnName(String gyotaiKbnName) {
        this.gyotaiKbnName = gyotaiKbnName;
    }

    /**
     * 担当SVコードを取得します。
     * @return 担当SVコード
     */
    public String getSvCd() {
        return convString(svCd);
    }

    /**
     * 担当SVコードを設定します。
     * @param svCd 担当SVコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }

    /**
     * 担当SV名称を取得します。
     * @return 担当SV名称
     */
    public String getUserNameKj() {
        return convString(userNameKj);
    }

    /**
     * 担当SV名称を設定します。
     * @param userNameKj 担当SV名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

    /**
     * 店オープン日を取得します。
     * @return 店オープン日
     */
    public String getOpenDt() {
        return convString(openDt);
    }

    /**
     * 店オープン日を設定します。
     * @param openDt 店オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }

    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }

    /**
     * 新店時店コードを取得します。
     * @return 新店時店コード
     */
    public String getMiseCdSaishin() {
        return convString(miseCdSaishin);
    }

    /**
     * 新店時店コードを設定します。
     * @param miseCdSaishin 新店時店コード
     */
    public void setMiseCdSaishin(String miseCdSaishin) {
        this.miseCdSaishin = miseCdSaishin;
    }

    /**
     * 最新店コードを取得します。
     * @return 最新店コード
     */
    public String getMiseCdShinten() {
        return convString(miseCdShinten);
    }

    /**
     * 最新店コードを設定します。
     * @param miseCdShinten 最新店コード
     */
    public void setMiseCdShinten(String miseCdShinten) {
        this.miseCdShinten = miseCdShinten;
    }

    /**
     * 県コードを取得します。
     * @return 県コード
     */
    public String getKenCd() {
        return convString(kenCd);
    }

    /**
     * 県コードを設定します。
     * @param kenCd 県コード
     */
    public void setKenCd(String kenCd) {
        this.kenCd = kenCd;
    }

    /**
     * 県名称を取得します。
     * @return 県名称
     */
    public String getKenName() {
        return convString(kenName);
    }

    /**
     * 県名称を設定します。
     * @param kenName 県名称
     */
    public void setKenName(String kenName) {
        this.kenName = kenName;
    }

    /**
     * 郵便番号を取得します。
     * @return 郵便番号
     */
    public String getMisePostNo() {
        return convString(misePostNo);
    }

    /**
     * 郵便番号を設定します。
     * @param misePostNo 郵便番号
     */
    public void setMisePostNo(String misePostNo) {
        this.misePostNo = misePostNo;
    }

    /**
     * 店住所1を取得します。
     * @return 店住所1
     */
    public String getMiseAdrs1() {
        return convString(miseAdrs1);
    }

    /**
     * 店住所1を設定します。
     * @param miseAdrs1 店住所1
     */
    public void setMiseAdrs1(String miseAdrs1) {
        this.miseAdrs1 = miseAdrs1;
    }

    /**
     * 店住所2を取得します。
     * @return 店住所2
     */
    public String getMiseAdrs2() {
        return convString(miseAdrs2);
    }

    /**
     * 店住所2を設定します。
     * @param miseAdrs2 店住所2
     */
    public void setMiseAdrs2(String miseAdrs2) {
        this.miseAdrs2 = miseAdrs2;
    }

    /**
     * 店住所3を取得します。
     * @return 店住所3
     */
    public String getMiseAdrs3() {
        return convString(miseAdrs3);
    }

    /**
     * 店住所3を設定します。
     * @param miseAdrs3 店住所3
     */
    public void setMiseAdrs3(String miseAdrs3) {
        this.miseAdrs3 = miseAdrs3;
    }

    /**
     * 電話番号を取得します。
     * @return 電話番号
     */
    public String getMiseTel() {
        return convString(miseTel);
    }

    /**
     * 電話番号を設定します。
     * @param miseTel 電話番号
     */
    public void setMiseTel(String miseTel) {
        this.miseTel = miseTel;
    }

    /**
     * 店舗タイプ区分を取得します。
     * @return 店舗タイプ区分
     */
    public String getMTypeKbn() {
        return convString(mTypeKbn);
    }

    /**
     * 店舗タイプ区分を設定します。
     * @param mTypeKbn 店舗タイプ区分
     */
    public void setMTypeKbn(String mTypeKbn) {
        this.mTypeKbn = mTypeKbn;
    }

    /**
     * 店舗タイプ区分名称を取得します。
     * @return 店舗タイプ区分名称
     */
    public String getMTypeKbnName() {
        return convString(mTypeKbnName);
    }

    /**
     * 店舗タイプ区分名称を設定します。
     * @param mTypeKbnName 店舗タイプ区分名称
     */
    public void setMTypeKbnName(String mTypeKbnName) {
        this.mTypeKbnName = mTypeKbnName;
    }

    /**
     * 店舗形態区分を取得します。
     * @return 店舗形態区分
     */
    public String getTMiseKeitai() {
        return convString(tMiseKeitai);
    }

    /**
     * 店舗形態区分を設定します。
     * @param tMiseKeitai 店舗形態区分
     */
    public void setTMiseKeitai(String tMiseKeitai) {
        this.tMiseKeitai = tMiseKeitai;
    }

    /**
     * 店舗形態区分名称を取得します。
     * @return 店舗形態区分名称
     */
    public String getMKeitaiName() {
        return convString(mKeitaiName);
    }

    /**
     * 店舗形態区分名称を設定します。
     * @param mKeitaiName 店舗形態区分名称
     */
    public void setMKeitaiName(String mKeitaiName) {
        this.mKeitaiName = mKeitaiName;
    }

    /**
     * ロケーション区分を取得します。
     * @return ロケーション区分
     */
    public String getTLocateKbn() {
        return convString(tLocateKbn);
    }

    /**
     * ロケーション区分を設定します。
     * @param tLocateKbn ロケーション区分
     */
    public void setTLocateKbn(String tLocateKbn) {
        this.tLocateKbn = tLocateKbn;
    }

    /**
     * ロケーション区分名称を取得します。
     * @return ロケーション区分名称
     */
    public String getLocateName() {
        return convString(locateName);
    }

    /**
     * ロケーション区分名称を設定します。
     * @param locateName ロケーション区分名称
     */
    public void setLocateName(String locateName) {
        this.locateName = locateName;
    }

    /**
     * 転貸を取得します。
     * @return 転貸
     */
    public String getTentai() {
        return convString(tentai);
    }

    /**
     * 転貸を設定します。
     * @param tentai 転貸
     */
    public void setTentai(String tentai) {
        this.tentai = tentai;
    }

    /**
     * 転貸開始日を取得します。
     * @return 転貸開始日
     */
    public String getTentaiStartDt() {
        return convString(tentaiStartDt);
    }

    /**
     * 転貸開始日を設定します。
     * @param tentaiStartDt 転貸開始日
     */
    public void setTentaiStartDt(String tentaiStartDt) {
        this.tentaiStartDt = tentaiStartDt;
    }

    /**
     * 転貸終了日を取得します。
     * @return 転貸終了日
     */
    public String getTentaiEndDt() {
        return convString(tentaiEndDt);
    }

    /**
     * 転貸終了日を設定します。
     * @param tentaiEndDt 転貸終了日
     */
    public void setTentaiEndDt(String tentaiEndDt) {
        this.tentaiEndDt = tentaiEndDt;
    }

    /**
     * 転貸情報を取得します。
     * @return 転貸情報
     */
    public String getTentaiInfo() {
        return convString(tentaiInfo);
    }

    /**
     * 転貸情報を設定します。
     * @param tentaiInfo 転貸情報
     */
    public void setTentaiInfo(String tentaiInfo) {
        this.tentaiInfo = tentaiInfo;
    }

    /**
     * 賃貸店舗経理コードを取得します。
     * @return 賃貸店舗経理コード
     */
    public String getMiseLeaseKCd() {
        return convString(miseLeaseKCd);
    }

    /**
     * 賃貸店舗経理コードを設定します。
     * @param miseLeaseKCd 賃貸店舗経理コード
     */
    public void setMiseLeaseKCd(String miseLeaseKCd) {
        this.miseLeaseKCd = miseLeaseKCd;
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
