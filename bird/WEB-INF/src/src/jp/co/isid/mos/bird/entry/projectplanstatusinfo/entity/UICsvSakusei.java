package jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity;

public class UICsvSakusei {
    
    public static final String TABLE = "BT46KENS";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String tabNo_COLUMN = "TAB_NO";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String name_COLUMN = "NAME";
    
    public static final String tel_COLUMN = "TEL";
    
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
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String absenceReason_COLUMN = "ABSENCE_REASON";
    
    public static final String ininMiseCd_COLUMN = "ININ_MISE_CD";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * タブ番号
     */
    private String tabNo;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 申込責任者
     */
    private String name;
    
    /**
     * 申込者電話番号
     */
    private String tel;
    
    /**
     * 参加者氏（漢字）
     */
    private String lNameKj;
    
    /**
     * 参加者名（漢字）
     */
    private String fNameKj;
    
    /**
     * 参加者氏（フリガナ）
     */
    private String lNameKna;
    
    /**
     * 参加者名（フリガナ）
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
     * 事業方針説明会
     */
    private String jigyoFlg;
    
    /**
     * 懇親会出席
     */
    private String konshinFlg;
    
    /**
     * 共栄会定時総会出席
     */
    private String kyoeiFlg;
    
    /**
     * 本部手配宿泊
     */
    private String shukuhakuFlg;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 事業方針説明会欠席理由
     */
    private String absenceReason;
    
    /**
     * 委任状
     */
    private String ininMiseCd;
    
    /**
     * 更新者ID
     */
    private String lastUser;
    
    /**
     * 更新者名称
     */
    private String userNameKj;
    
    /**
     * オーナー契約終了日
     */
    private String keiyakuEnd;
    
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
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
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
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
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 申込責任者を取得します。
     * @return 申込責任者
     */
    public String getName() {
        return name;
    }
    /**
     * 申込責任者を設定します。
     * @param name 申込責任者
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 申込者電話番号を取得します。
     * @return 申込者電話番号
     */
    public String getTel() {
        return tel;
    }
    /**
     * 申込者電話番号を設定します。
     * @param tel 申込者電話番号
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * 参加者氏（漢字）を取得します。
     * @return 参加者氏（漢字）
     */
    public String getLNameKj() {
        return lNameKj;
    }
    /**
     * 参加者氏（漢字）を設定します。
     * @param lNameKj 参加者氏（漢字）
     */
    public void setLNameKj(String lNameKj) {
        this.lNameKj = lNameKj;
    }
    
    /**
     * 参加者名（漢字）を取得します。
     * @return 参加者名（漢字）
     */
    public String getFNameKj() {
        return fNameKj;
    }
    /**
     * 参加者名（漢字）を設定します。
     * @param fNameKj 参加者名（漢字）
     */
    public void setFNameKj(String fNameKj) {
        this.fNameKj = fNameKj;
    }
    
    /**
     * 参加者氏（フリガナ）を取得します。
     * @return 参加者氏（フリガナ）
     */
    public String getLNameKna() {
        return lNameKna;
    }
    /**
     * 参加者氏（フリガナ）を設定します。
     * @param lNameKna 参加者氏（フリガナ）
     */
    public void setLNameKna(String lNameKna) {
        this.lNameKna = lNameKna;
    }
    
    /**
     * 参加者名（フリガナ）を取得します。
     * @return 参加者名（フリガナ）
     */
    public String getFNameKna() {
        return fNameKna;
    }
    /**
     * 参加者名（フリガナ）を設定します。
     * @param fNameKna 参加者名（フリガナ）
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
     * 事業方針説明会を取得します。
     * @return 事業方針説明会
     */
    public String getJigyoFlg() {
        return jigyoFlg;
    }
    /**
     * 事業方針説明会を設定します。
     * @param jigyoFlg 事業方針説明会
     */
    public void setJigyoFlg(String jigyoFlg) {
        this.jigyoFlg = jigyoFlg;
    }
    
    /**
     * 懇親会出席を取得します。
     * @return 懇親会出席
     */
    public String getKonshinFlg() {
        return konshinFlg;
    }
    /**
     * 懇親会出席を設定します。
     * @param konshinFlg 懇親会出席
     */
    public void setKonshinFlg(String konshinFlg) {
        this.konshinFlg = konshinFlg;
    }
    
    /**
     * 共栄会定時総会出席を取得します。
     * @return 共栄会定時総会出席
     */
    public String getKyoeiFlg() {
        return kyoeiFlg;
    }
    /**
     * 共栄会定時総会出席を設定します。
     * @param kyoeiFlg 共栄会定時総会出席
     */
    public void setKyoeiFlg(String kyoeiFlg) {
        this.kyoeiFlg = kyoeiFlg;
    }
    
    /**
     * 本部手配宿泊を取得します。
     * @return 本部手配宿泊
     */
    public String getShukuhakuFlg() {
        return shukuhakuFlg;
    }
    /**
     * 本部手配宿泊を設定します。
     * @param shukuhakuFlg 本部手配宿泊
     */
    public void setShukuhakuFlg(String shukuhakuFlg) {
        this.shukuhakuFlg = shukuhakuFlg;
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
     * 委任状を取得します。
     * @return 委任状
     */
    public String getIninMiseCd() {
        return ininMiseCd;
    }
    /**
     * 委任状を設定します。
     * @param ininMiseCd 委任状
     */
    public void setIninMiseCd(String ininMiseCd) {
        this.ininMiseCd = ininMiseCd;
    }
    
    /**
     * 更新者IDを取得します。
     * @return 更新者ID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新者IDを設定します。
     * @param lastUser 更新者ID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新者名称を取得します。
     * @return 更新者名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 更新者名称を設定します。
     * @param userNameKj 更新者名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * オーナー契約終了日を取得します。
     * @return オーナー契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * オーナー契約終了日を設定します。
     * @param keiyakuEnd オーナー契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    
}
