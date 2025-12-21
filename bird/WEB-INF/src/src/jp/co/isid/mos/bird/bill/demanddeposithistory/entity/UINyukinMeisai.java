package jp.co.isid.mos.bird.bill.demanddeposithistory.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UINyukinMeisai {
    
    public static final String TABLE = "BT36NYUT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String urikakeYm_COLUMN = "URIKAKE_YM";
    
    public static final String nyukinDt_COLUMN = "NYUKIN_DT";
    
    public static final String nkanriNo_COLUMN = "NKANRI_NO";
    
    public static final String dataVer_COLUMN = "DATA_VER";
    
    public static final String denpyoNo_COLUMN = "DENPYO_NO";
    
    public static final String nyukinGaku_COLUMN = "NYUKIN_GAKU";
    
    public static final String nyukinKbn_COLUMN = "NYUKIN_KBN";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String denpyoKbn_COLUMN = "DENPYO_KBN";
    
    public static final String bankCd_COLUMN = "BANK_CD";
    
    public static final String sitenCd_COLUMN = "SITEN_CD";
    
    public static final String yokinShu_COLUMN = "YOKIN_SHU";
    
    public static final String kouzaNo_COLUMN = "KOUZA_NO";
    
    public static final String denpyoDt_COLUMN = "DENPYO_DT";
    
    public static final String kenshuDt_COLUMN = "KENSHU_DT";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛先名称
     */
    private String onerNameKj;
    
    /**
     * 売掛残高年月
     */
    private String urikakeYm;
    
    /**
     * 入金年月日
     */
    private String nyukinDt;
    
    /**
     * 入金管理ＮＯ
     */
    private String nkanriNo;
    
    /**
     * ﾃﾞｰﾀﾊﾞｰｼﾞｮﾝ
     */
    private String dataVer;
    
    /**
     * 伝票ＮＯ
     */
    private String denpyoNo;
    
    /**
     * 今回入金金額
     */
    private BigDecimal nyukinGaku;
    
    /**
     * 入金区分
     */
    private String nyukinKbn;
    
    /**
     * データ削除ﾌﾗｸﾞ
     */
    private String sakujoFlg;
    
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
     * 伝票区分
     */
    private String denpyoKbn;
    
    /**
     * 銀行コード
     */
    private String bankCd;
    
    /**
     * 支店コード
     */
    private String sitenCd;
    
    /**
     * 預金種別
     */
    private String yokinShu;
    
    /**
     * 口座番号
     */
    private String kouzaNo;
    
    /**
     * 検収確定日
     */
    private String denpyoDt;
    
    /**
     * 入金伝票日付
     */
    private String kenshuDt;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * 売掛先名称を取得します。
     * @return 売掛先名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * 売掛先名称を設定します。
     * @param onerNameKj 売掛先名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 売掛残高年月を取得します。
     * @return 売掛残高年月
     */
    public String getUrikakeYm() {
        return urikakeYm;
    }
    /**
     * 売掛残高年月を設定します。
     * @param urikakeYm 売掛残高年月
     */
    public void setUrikakeYm(String urikakeYm) {
        this.urikakeYm = urikakeYm;
    }
    
    /**
     * 入金年月日を取得します。
     * @return 入金年月日
     */
    public String getNyukinDt() {
        return nyukinDt;
    }
    /**
     * 入金年月日を設定します。
     * @param nyukinDt 入金年月日
     */
    public void setNyukinDt(String nyukinDt) {
        this.nyukinDt = nyukinDt;
    }
    
    /**
     * 入金管理ＮＯを取得します。
     * @return 入金管理ＮＯ
     */
    public String getNkanriNo() {
        return nkanriNo;
    }
    /**
     * 入金管理ＮＯを設定します。
     * @param nkanriNo 入金管理ＮＯ
     */
    public void setNkanriNo(String nkanriNo) {
        this.nkanriNo = nkanriNo;
    }
    
    /**
     * ﾃﾞｰﾀﾊﾞｰｼﾞｮﾝを取得します。
     * @return ﾃﾞｰﾀﾊﾞｰｼﾞｮﾝ
     */
    public String getDataVer() {
        return dataVer;
    }
    /**
     * ﾃﾞｰﾀﾊﾞｰｼﾞｮﾝを設定します。
     * @param dataVer ﾃﾞｰﾀﾊﾞｰｼﾞｮﾝ
     */
    public void setDataVer(String dataVer) {
        this.dataVer = dataVer;
    }
    
    /**
     * 伝票ＮＯを取得します。
     * @return 伝票ＮＯ
     */
    public String getDenpyoNo() {
        return denpyoNo;
    }
    /**
     * 伝票ＮＯを設定します。
     * @param denpyoNo 伝票ＮＯ
     */
    public void setDenpyoNo(String denpyoNo) {
        this.denpyoNo = denpyoNo;
    }
    
    /**
     * 今回入金金額を取得します。
     * @return 今回入金金額
     */
    public BigDecimal getNyukinGaku() {
        return nyukinGaku;
    }
    /**
     * 今回入金金額を設定します。
     * @param nyukinGaku 今回入金金額
     */
    public void setNyukinGaku(BigDecimal nyukinGaku) {
        this.nyukinGaku = nyukinGaku;
    }
    
    /**
     * 入金区分を取得します。
     * @return 入金区分
     */
    public String getNyukinKbn() {
        return nyukinKbn;
    }
    /**
     * 入金区分を設定します。
     * @param nyukinKbn 入金区分
     */
    public void setNyukinKbn(String nyukinKbn) {
        this.nyukinKbn = nyukinKbn;
    }
    
    /**
     * データ削除ﾌﾗｸﾞを取得します。
     * @return データ削除ﾌﾗｸﾞ
     */
    public String getSakujoFlg() {
        return sakujoFlg;
    }
    /**
     * データ削除ﾌﾗｸﾞを設定します。
     * @param sakujoFlg データ削除ﾌﾗｸﾞ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
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
     * 伝票区分を取得します。
     * @return 伝票区分
     */
    public String getDenpyoKbn() {
        return denpyoKbn;
    }
    /**
     * 伝票区分を設定します。
     * @param denpyoKbn 伝票区分
     */
    public void setDenpyoKbn(String denpyoKbn) {
        this.denpyoKbn = denpyoKbn;
    }
    
    /**
     * 銀行コードを取得します。
     * @return 銀行コード
     */
    public String getBankCd() {
        return bankCd;
    }
    /**
     * 銀行コードを設定します。
     * @param bankCd 銀行コード
     */
    public void setBankCd(String bankCd) {
        this.bankCd = bankCd;
    }
    
    /**
     * 支店コードを取得します。
     * @return 支店コード
     */
    public String getSitenCd() {
        return sitenCd;
    }
    /**
     * 支店コードを設定します。
     * @param sitenCd 支店コード
     */
    public void setSitenCd(String sitenCd) {
        this.sitenCd = sitenCd;
    }
    
    /**
     * 預金種別を取得します。
     * @return 預金種別
     */
    public String getYokinShu() {
        return yokinShu;
    }
    /**
     * 預金種別を設定します。
     * @param yokinShu 預金種別
     */
    public void setYokinShu(String yokinShu) {
        this.yokinShu = yokinShu;
    }
    
    /**
     * 口座番号を取得します。
     * @return 口座番号
     */
    public String getKouzaNo() {
        return kouzaNo;
    }
    /**
     * 口座番号を設定します。
     * @param kouzaNo 口座番号
     */
    public void setKouzaNo(String kouzaNo) {
        this.kouzaNo = kouzaNo;
    }
    
    /**
     * 検収確定日を取得します。
     * @return 検収確定日
     */
    public String getDenpyoDt() {
        return denpyoDt;
    }
    /**
     * 検収確定日を設定します。
     * @param denpyoDt 検収確定日
     */
    public void setDenpyoDt(String denpyoDt) {
        this.denpyoDt = denpyoDt;
    }
    
    /**
     * 入金伝票日付を取得します。
     * @return 入金伝票日付
     */
    public String getKenshuDt() {
        return kenshuDt;
    }
    /**
     * 入金伝票日付を設定します。
     * @param kenshuDt 入金伝票日付
     */
    public void setKenshuDt(String kenshuDt) {
        this.kenshuDt = kenshuDt;
    }
    
}
