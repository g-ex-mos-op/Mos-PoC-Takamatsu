package jp.co.isid.mos.bird.entry.projectplanoffer.entity;

import java.sql.Timestamp;

public class UIOfferJoinPersonInfo {
    
    public static final String TABLE = "BT46KENS";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String tabNo_COLUMN = "TAB_NO";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String lNameKj_COLUMN = "L_NAME_KJ";
    
    public static final String fNameKj_COLUMN = "F_NAME_KJ";
    
    public static final String lNameKna_COLUMN = "L_NAME_KNA";
    
    public static final String fNameKna_COLUMN = "F_NAME_KNA";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String age_COLUMN = "AGE";
    
    public static final String jigyoFlg_COLUMN = "JIGYO_FLG";
    
    public static final String konshinFlg_COLUMN = "KONSHIN_FLG";
    
    public static final String kyoeiFlg_COLUMN = "KYOEI_FLG";
    
    public static final String shukuhakuFlg_COLUMN = "SHUKUHAKU_FLG";
    
    public static final String yobiFlg1_COLUMN = "YOBI_FLG1";
    
    public static final String yobiFlg2_COLUMN = "YOBI_FLG2";
    
    public static final String yobiFlg3_COLUMN = "YOBI_FLG3";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String absenceReason_COLUMN = "ABSENCE_REASON";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * タブ番号
     */
    private String tabNo;
    
    /**
     * 参加者の店舗コード
     */
    private String miseCd;

    /**
     * 漢字-氏
     */
    private String lNameKj;
    
    /**
     * 漢字-名
     */
    private String fNameKj;
    
    /**
     * カナ-氏
     */
    private String lNameKna;
    
    /**
     * カナ-名
     */
    private String fNameKna;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 年齢
     */
    private String age;
    
    /**
     * 事業方針説明会参加可否
     */
    private String jigyoFlg;
    
    /**
     * 懇親会参加可否
     */
    private String konshinFlg;
    
    /**
     * 共栄会定時総会参加可否
     */
    private String kyoeiFlg;
    
    /**
     * 本部手配宿泊区分
     */
    private String shukuhakuFlg;
    
    /**
     * 予備フラグ1
     */
    private String yobiFlg1;
    
    /**
     * 予備フラグ2
     */
    private String yobiFlg2;
    
    /**
     * 予備フラグ3
     */
    private String yobiFlg3;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 事業方針説明会欠席理由
     */
    private String absenceReason;
    
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
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
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
     * タブ番号を取得します。
     * @return タブ番号
     */
    public String getTabNo() {
        return tabNo;
    }
    /**
     * タブ番号を設定します。
     * @param tabNo タブ番号
     */
    public void setTabNo(String tabNo) {
        this.tabNo = tabNo;
    }
    
    /**
     * 参加者の店舗コードを取得します。
     * @return 参加者の店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 参加者の店舗コードを設定します。
     * @param miseCd 参加者の店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 漢字-氏を取得します。
     * @return 漢字-氏
     */
    public String getLNameKj() {
        return lNameKj;
    }
    /**
     * 漢字-氏を設定します。
     * @param lNameKj 漢字-氏
     */
    public void setLNameKj(String lNameKj) {
        this.lNameKj = lNameKj;
    }
    
    /**
     * 漢字-名を取得します。
     * @return 漢字-名
     */
    public String getFNameKj() {
        return fNameKj;
    }
    /**
     * 漢字-名を設定します。
     * @param fNameKj 漢字-名
     */
    public void setFNameKj(String fNameKj) {
        this.fNameKj = fNameKj;
    }
    
    /**
     * カナ-氏を取得します。
     * @return カナ-氏
     */
    public String getLNameKna() {
        return lNameKna;
    }
    /**
     * カナ-氏を設定します。
     * @param lNameKna カナ-氏
     */
    public void setLNameKna(String lNameKna) {
        this.lNameKna = lNameKna;
    }
    
    /**
     * カナ-名を取得します。
     * @return カナ-名
     */
    public String getFNameKna() {
        return fNameKna;
    }
    /**
     * カナ-名を設定します。
     * @param fNameKna カナ-名
     */
    public void setFNameKna(String fNameKna) {
        this.fNameKna = fNameKna;
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
     * 年齢を取得します。
     * @return 年齢
     */
    public String getAge() {
        return age;
    }
    /**
     * 年齢を設定します。
     * @param age 年齢
     */
    public void setAge(String age) {
        this.age = age;
    }
    
    /**
     * 事業方針説明会参加可否を取得します。
     * @return 事業方針説明会参加可否
     */
    public String getJigyoFlg() {
        return jigyoFlg;
    }
    /**
     * 事業方針説明会参加可否を設定します。
     * @param jigyoFlg 事業方針説明会参加可否
     */
    public void setJigyoFlg(String jigyoFlg) {
        this.jigyoFlg = jigyoFlg;
    }
    
    /**
     * 懇親会参加可否を取得します。
     * @return 懇親会参加可否
     */
    public String getKonshinFlg() {
        return konshinFlg;
    }
    /**
     * 懇親会参加可否を設定します。
     * @param konshinFlg 懇親会参加可否
     */
    public void setKonshinFlg(String konshinFlg) {
        this.konshinFlg = konshinFlg;
    }
    
    /**
     * 共栄会定時総会参加可否を取得します。
     * @return 共栄会定時総会参加可否
     */
    public String getKyoeiFlg() {
        return kyoeiFlg;
    }
    /**
     * 共栄会定時総会参加可否を設定します。
     * @param kyoeiFlg 共栄会定時総会参加可否
     */
    public void setKyoeiFlg(String kyoeiFlg) {
        this.kyoeiFlg = kyoeiFlg;
    }
    
    /**
     * 本部手配宿泊区分を取得します。
     * @return 本部手配宿泊区分
     */
    public String getShukuhakuFlg() {
        return shukuhakuFlg;
    }
    /**
     * 本部手配宿泊区分を設定します。
     * @param shukuhakuFlg 本部手配宿泊区分
     */
    public void setShukuhakuFlg(String shukuhakuFlg) {
        this.shukuhakuFlg = shukuhakuFlg;
    }
    
    /**
     * 予備フラグ1を取得します。
     * @return 予備フラグ1
     */
    public String getYobiFlg1() {
        return yobiFlg1;
    }
    /**
     * 予備フラグ1を設定します。
     * @param yobiFlg1 予備フラグ1
     */
    public void setYobiFlg1(String yobiFlg1) {
        this.yobiFlg1 = yobiFlg1;
    }
    
    /**
     * 予備フラグ2を取得します。
     * @return 予備フラグ2
     */
    public String getYobiFlg2() {
        return yobiFlg2;
    }
    /**
     * 予備フラグ2を設定します。
     * @param yobiFlg2 予備フラグ2
     */
    public void setYobiFlg2(String yobiFlg2) {
        this.yobiFlg2 = yobiFlg2;
    }
    
    /**
     * 予備フラグ3を取得します。
     * @return 予備フラグ3
     */
    public String getYobiFlg3() {
        return yobiFlg3;
    }
    /**
     * 予備フラグ3を設定します。
     * @param yobiFlg3 予備フラグ3
     */
    public void setYobiFlg3(String yobiFlg3) {
        this.yobiFlg3 = yobiFlg3;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 事業方針説明会欠席理由を取得します。
     * @return 事業方針説明会欠席理由
     */
    public String getAbsenceReason() {
        return absenceReason;
    }
    /**
     * 事業方針説明会欠席理由を設定します。
     * @param absenceReason 事業方針説明会欠席理由
     */
    public void setAbsenceReason(String absenceReason) {
        this.absenceReason = absenceReason;
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
    
}
