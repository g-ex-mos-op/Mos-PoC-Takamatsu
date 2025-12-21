package jp.co.isid.mos.bird.togouser.userregist.entity;

import java.sql.Timestamp;

public class UITogoUserRireki {
    
    public static final String TABLE = "IR51USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String hatsureiDt_COLUMN = "HATSUREI_DT";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKna_COLUMN = "USER_NAME_KNA";
    
    public static final String userNameAlph_COLUMN = "USER_NAME_ALPH";
    
    public static final String taishokuDt_COLUMN = "TAISHOKU_DT";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String salaryOfficeCd_COLUMN = "SALARY_OFFICE_CD";
    
    public static final String salaryOfficeName_COLUMN = "SALARY_OFFICE_NAME";
    
    public static final String salaryOfficeDt_COLUMN = "SALARY_OFFICE_DT";
    
    public static final String salarySectionRank_COLUMN = "SALARY_SECTION_RANK";
    
    public static final String salarySectionCd_COLUMN = "SALARY_SECTION_CD";
    
    public static final String salaryCd_COLUMN = "SALARY_CD";
    
    public static final String sararyName_COLUMN = "SARARY_NAME";
    
    public static final String sararyDt_COLUMN = "SARARY_DT";
    
    public static final String jinjiOfficeCd_COLUMN = "JINJI_OFFICE_CD";
    
    public static final String jinjiOfficeName_COLUMN = "JINJI_OFFICE_NAME";
    
    public static final String jinjiOfficeDt_COLUMN = "JINJI_OFFICE_DT";
    
    public static final String jinjiSectionRank_COLUMN = "JINJI_SECTION_RANK";
    
    public static final String jinjiSectionCd_COLUMN = "JINJI_SECTION_CD";
    
    public static final String jinjiCd_COLUMN = "JINJI_CD";
    
    public static final String jinjiName_COLUMN = "JINJI_NAME";
    
    public static final String kenmuKbn_COLUMN = "KENMU_KBN";
    
    public static final String jinjiDt_COLUMN = "JINJI_DT";
    
    public static final String employeeKbn_COLUMN = "EMPLOYEE_KBN";
    
    public static final String employeeName_COLUMN = "EMPLOYEE_NAME";
    
    public static final String employeeDt_COLUMN = "EMPLOYEE_DT";
    
    public static final String positionCd_COLUMN = "POSITION_CD";
    
    public static final String positionName_COLUMN = "POSITION_NAME";
    
    public static final String positionDt_COLUMN = "POSITION_DT";
    
    public static final String gradeCd_COLUMN = "GRADE_CD";
    
    public static final String gradeName_COLUMN = "GRADE_NAME";
    
    public static final String gradeDt_COLUMN = "GRADE_DT";
    
    public static final String kyushokuFrom_COLUMN = "KYUSHOKU_FROM";
    
    public static final String kyushokuTo_COLUMN = "KYUSHOKU_TO";
    
    public static final String cellularPhone1_COLUMN = "CELLULAR_PHONE1";
    
    public static final String cellularPhone2_COLUMN = "CELLULAR_PHONE2";
    
    public static final String extensionNo_COLUMN = "EXTENSION_NO";
    
    public static final String otherPhone_COLUMN = "OTHER_PHONE";
    
    public static final String mailAdd1_COLUMN = "MAIL_ADD1";
    
    public static final String mailAdd2_COLUMN = "MAIL_ADD2";
    
    public static final String mailAdd3_COLUMN = "MAIL_ADD3";
    
    public static final String oldUserId_COLUMN = "OLD_USER_ID";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";
    
    public static final String kbnSpare1_COLUMN = "KBN_SPARE1";
    
    public static final String kbnSpare2_COLUMN = "KBN_SPARE2";
    
    public static final String kbnSpare3_COLUMN = "KBN_SPARE3";
    
    public static final String kbnSpare4_COLUMN = "KBN_SPARE4";
    
    public static final String kbnSpare5_COLUMN = "KBN_SPARE5";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    
    /**
     * 社員番号
     */
    private String userId="";
    
    /**
     * 発令日
     */
    private String hatsureiDt="";
    
    /**
     * 氏名
     */
    private String userNameKj="";
    
    /**
     * カナ氏名
     */
    private String userNameKna="";
    
    /**
     * アルファベット氏名
     */
    private String userNameAlph="";
    
    /**
     * 退職日
     */
    private String taishokuDt="";
    
    /**
     * 性別
     */
    private String sex="";
    
    /**
     * パスワード
     */
    private String userPswd="";
    
    /**
     * 給与事業所コード
     */
    private String salaryOfficeCd="";
    
    /**
     * 給与事業所名称
     */
    private String salaryOfficeName="";
    
    /**
     * 給与事業所歴発令日
     */
    private String salaryOfficeDt="";
    
    /**
     * 給与所属部門ランク
     */
    private String salarySectionRank="";
    
    /**
     * 給与所属部門コード
     */
    private String salarySectionCd="";
    
    /**
     * 給与所属コード
     */
    private String salaryCd="";
    
    /**
     * 給与所属名称
     */
    private String sararyName="";
    
    /**
     * 給与所属歴発令日
     */
    private String sararyDt="";
    
    /**
     * 人事事業所コード
     */
    private String jinjiOfficeCd="";
    
    /**
     * 人事事業所名称
     */
    private String jinjiOfficeName="";
    
    /**
     * 人事事業所歴発令日
     */
    private String jinjiOfficeDt="";
    
    /**
     * 人事所属部門ランク
     */
    private String jinjiSectionRank="";
    
    /**
     * 人事所属部門コード
     */
    private String jinjiSectionCd="";
    
    /**
     * 人事所属コード
     */
    private String jinjiCd="";
    
    /**
     * 人事所属名称
     */
    private String jinjiName="";
    
    /**
     * 兼務区分
     */
    private String kenmuKbn="";
    
    /**
     * 人事所属歴発令日
     */
    private String jinjiDt="";
    
    /**
     * 社員区分
     */
    private String employeeKbn="";
    
    /**
     * 社員区分名称
     */
    private String employeeName="";
    
    /**
     * 社員区分歴発令日
     */
    private String employeeDt="";
    
    /**
     * 役職
     */
    private String positionCd="";
    
    /**
     * 役職名称
     */
    private String positionName="";
    
    /**
     * 役職歴発令日
     */
    private String positionDt="";
    
    /**
     * 職務等級コード
     */
    private String gradeCd="";
    
    /**
     * 職務等級名称
     */
    private String gradeName="";
    
    /**
     * 職級歴発令日
     */
    private String gradeDt="";
    
    /**
     * 休職開始日
     */
    private String kyushokuFrom="";
    
    /**
     * 休職期日
     */
    private String kyushokuTo="";
    
    /**
     * 携帯電話番号１
     */
    private String cellularPhone1="";
    
    /**
     * 携帯電話番号２
     */
    private String cellularPhone2="";
    
    /**
     * 内線番号
     */
    private String extensionNo="";
    
    /**
     * その他電話番号
     */
    private String otherPhone="";
    
    /**
     * メールアドレス１
     */
    private String mailAdd1="";
    
    /**
     * メールアドレス２
     */
    private String mailAdd2="";
    
    /**
     * メールアドレス３
     */
    private String mailAdd3="";
    
    /**
     * ユーザーID（5桁）
     */
    private String oldUserId="";
    
    /**
     * 使用停止フラグ
     */
    private String stopFlg="";
    
    /**
     * 画面登録フラグ
     */
    private String kbnSpare1="";
    
    /**
     * 予備フラグ２
     */
    private String kbnSpare2="";
    
    /**
     * 予備フラグ３
     */
    private String kbnSpare3="";
    
    /**
     * 予備フラグ４
     */
    private String kbnSpare4="";
    
    /**
     * 予備フラグ５
     */
    private String kbnSpare5="";
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録ＰＧＭ
     */
    private String firstPgm;
    
    /**
     * 登録ＴＭＳＰ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新ＰＧＭ
     */
    private String lastPgm;
    
    /**
     * 更新ＴＭＳＰ
     */
    private Timestamp lastTmsp;
    
    /**
     * 社員番号を取得します。
     * @return 社員番号
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 社員番号を設定します。
     * @param userId 社員番号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 発令日を取得します。
     * @return 発令日
     */
    public String getHatsureiDt() {
        return hatsureiDt;
    }
    /**
     * 発令日を設定します。
     * @param hatsureiDt 発令日
     */
    public void setHatsureiDt(String hatsureiDt) {
        this.hatsureiDt = hatsureiDt;
    }
    
    /**
     * 氏名を取得します。
     * @return 氏名
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 氏名を設定します。
     * @param userNameKj 氏名
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * カナ氏名を取得します。
     * @return カナ氏名
     */
    public String getUserNameKna() {
        return userNameKna;
    }
    /**
     * カナ氏名を設定します。
     * @param userNameKna カナ氏名
     */
    public void setUserNameKna(String userNameKna) {
        this.userNameKna = userNameKna;
    }
    
    /**
     * アルファベット氏名を取得します。
     * @return アルファベット氏名
     */
    public String getUserNameAlph() {
        return userNameAlph;
    }
    /**
     * アルファベット氏名を設定します。
     * @param userNameAlph アルファベット氏名
     */
    public void setUserNameAlph(String userNameAlph) {
        this.userNameAlph = userNameAlph;
    }
    
    /**
     * 退職日を取得します。
     * @return 退職日
     */
    public String getTaishokuDt() {
        return taishokuDt;
    }
    /**
     * 退職日を設定します。
     * @param taishokuDt 退職日
     */
    public void setTaishokuDt(String taishokuDt) {
        this.taishokuDt = taishokuDt;
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
     * パスワードを取得します。
     * @return パスワード
     */
    public String getUserPswd() {
        return userPswd;
    }
    /**
     * パスワードを設定します。
     * @param userPswd パスワード
     */
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }
    
    /**
     * 給与事業所コードを取得します。
     * @return 給与事業所コード
     */
    public String getSalaryOfficeCd() {
        return salaryOfficeCd;
    }
    /**
     * 給与事業所コードを設定します。
     * @param salaryOfficeCd 給与事業所コード
     */
    public void setSalaryOfficeCd(String salaryOfficeCd) {
        this.salaryOfficeCd = salaryOfficeCd;
    }
    
    /**
     * 給与事業所名称を取得します。
     * @return 給与事業所名称
     */
    public String getSalaryOfficeName() {
        return salaryOfficeName;
    }
    /**
     * 給与事業所名称を設定します。
     * @param salaryOfficeName 給与事業所名称
     */
    public void setSalaryOfficeName(String salaryOfficeName) {
        this.salaryOfficeName = salaryOfficeName;
    }
    
    /**
     * 給与事業所歴発令日を取得します。
     * @return 給与事業所歴発令日
     */
    public String getSalaryOfficeDt() {
        return salaryOfficeDt;
    }
    /**
     * 給与事業所歴発令日を設定します。
     * @param salaryOfficeDt 給与事業所歴発令日
     */
    public void setSalaryOfficeDt(String salaryOfficeDt) {
        this.salaryOfficeDt = salaryOfficeDt;
    }
    
    /**
     * 給与所属部門ランクを取得します。
     * @return 給与所属部門ランク
     */
    public String getSalarySectionRank() {
        return salarySectionRank;
    }
    /**
     * 給与所属部門ランクを設定します。
     * @param salarySectionRank 給与所属部門ランク
     */
    public void setSalarySectionRank(String salarySectionRank) {
        this.salarySectionRank = salarySectionRank;
    }
    
    /**
     * 給与所属部門コードを取得します。
     * @return 給与所属部門コード
     */
    public String getSalarySectionCd() {
        return salarySectionCd;
    }
    /**
     * 給与所属部門コードを設定します。
     * @param salarySectionCd 給与所属部門コード
     */
    public void setSalarySectionCd(String salarySectionCd) {
        this.salarySectionCd = salarySectionCd;
    }
    
    /**
     * 給与所属コードを取得します。
     * @return 給与所属コード
     */
    public String getSalaryCd() {
        return salaryCd;
    }
    /**
     * 給与所属コードを設定します。
     * @param salaryCd 給与所属コード
     */
    public void setSalaryCd(String salaryCd) {
        this.salaryCd = salaryCd;
    }
    
    /**
     * 給与所属名称を取得します。
     * @return 給与所属名称
     */
    public String getSararyName() {
        return sararyName;
    }
    /**
     * 給与所属名称を設定します。
     * @param sararyName 給与所属名称
     */
    public void setSararyName(String sararyName) {
        this.sararyName = sararyName;
    }
    
    /**
     * 給与所属歴発令日を取得します。
     * @return 給与所属歴発令日
     */
    public String getSararyDt() {
        return sararyDt;
    }
    /**
     * 給与所属歴発令日を設定します。
     * @param sararyDt 給与所属歴発令日
     */
    public void setSararyDt(String sararyDt) {
        this.sararyDt = sararyDt;
    }
    
    /**
     * 人事事業所コードを取得します。
     * @return 人事事業所コード
     */
    public String getJinjiOfficeCd() {
        return jinjiOfficeCd;
    }
    /**
     * 人事事業所コードを設定します。
     * @param jinjiOfficeCd 人事事業所コード
     */
    public void setJinjiOfficeCd(String jinjiOfficeCd) {
        this.jinjiOfficeCd = jinjiOfficeCd;
    }
    
    /**
     * 人事事業所名称を取得します。
     * @return 人事事業所名称
     */
    public String getJinjiOfficeName() {
        return jinjiOfficeName;
    }
    /**
     * 人事事業所名称を設定します。
     * @param jinjiOfficeName 人事事業所名称
     */
    public void setJinjiOfficeName(String jinjiOfficeName) {
        this.jinjiOfficeName = jinjiOfficeName;
    }
    
    /**
     * 人事事業所歴発令日を取得します。
     * @return 人事事業所歴発令日
     */
    public String getJinjiOfficeDt() {
        return jinjiOfficeDt;
    }
    /**
     * 人事事業所歴発令日を設定します。
     * @param jinjiOfficeDt 人事事業所歴発令日
     */
    public void setJinjiOfficeDt(String jinjiOfficeDt) {
        this.jinjiOfficeDt = jinjiOfficeDt;
    }
    
    /**
     * 人事所属部門ランクを取得します。
     * @return 人事所属部門ランク
     */
    public String getJinjiSectionRank() {
        return jinjiSectionRank;
    }
    /**
     * 人事所属部門ランクを設定します。
     * @param jinjiSectionRank 人事所属部門ランク
     */
    public void setJinjiSectionRank(String jinjiSectionRank) {
        this.jinjiSectionRank = jinjiSectionRank;
    }
    
    /**
     * 人事所属部門コードを取得します。
     * @return 人事所属部門コード
     */
    public String getJinjiSectionCd() {
        return jinjiSectionCd;
    }
    /**
     * 人事所属部門コードを設定します。
     * @param jinjiSectionCd 人事所属部門コード
     */
    public void setJinjiSectionCd(String jinjiSectionCd) {
        this.jinjiSectionCd = jinjiSectionCd;
    }
    
    /**
     * 人事所属コードを取得します。
     * @return 人事所属コード
     */
    public String getJinjiCd() {
        return jinjiCd;
    }
    /**
     * 人事所属コードを設定します。
     * @param jinjiCd 人事所属コード
     */
    public void setJinjiCd(String jinjiCd) {
        this.jinjiCd = jinjiCd;
    }
    
    /**
     * 人事所属名称を取得します。
     * @return 人事所属名称
     */
    public String getJinjiName() {
        return jinjiName;
    }
    /**
     * 人事所属名称を設定します。
     * @param jinjiName 人事所属名称
     */
    public void setJinjiName(String jinjiName) {
        this.jinjiName = jinjiName;
    }
    
    /**
     * 兼務区分を取得します。
     * @return 兼務区分
     */
    public String getKenmuKbn() {
        return kenmuKbn;
    }
    /**
     * 兼務区分を設定します。
     * @param kenmuKbn 兼務区分
     */
    public void setKenmuKbn(String kenmuKbn) {
        this.kenmuKbn = kenmuKbn;
    }
    
    /**
     * 人事所属歴発令日を取得します。
     * @return 人事所属歴発令日
     */
    public String getJinjiDt() {
        return jinjiDt;
    }
    /**
     * 人事所属歴発令日を設定します。
     * @param jinjiDt 人事所属歴発令日
     */
    public void setJinjiDt(String jinjiDt) {
        this.jinjiDt = jinjiDt;
    }
    
    /**
     * 社員区分を取得します。
     * @return 社員区分
     */
    public String getEmployeeKbn() {
        return employeeKbn;
    }
    /**
     * 社員区分を設定します。
     * @param employeeKbn 社員区分
     */
    public void setEmployeeKbn(String employeeKbn) {
        this.employeeKbn = employeeKbn;
    }
    
    /**
     * 社員区分名称を取得します。
     * @return 社員区分名称
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /**
     * 社員区分名称を設定します。
     * @param employeeName 社員区分名称
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    /**
     * 社員区分歴発令日を取得します。
     * @return 社員区分歴発令日
     */
    public String getEmployeeDt() {
        return employeeDt;
    }
    /**
     * 社員区分歴発令日を設定します。
     * @param employeeDt 社員区分歴発令日
     */
    public void setEmployeeDt(String employeeDt) {
        this.employeeDt = employeeDt;
    }
    
    /**
     * 役職を取得します。
     * @return 役職
     */
    public String getPositionCd() {
        return positionCd;
    }
    /**
     * 役職を設定します。
     * @param positionCd 役職
     */
    public void setPositionCd(String positionCd) {
        this.positionCd = positionCd;
    }
    
    /**
     * 役職名称を取得します。
     * @return 役職名称
     */
    public String getPositionName() {
        return positionName;
    }
    /**
     * 役職名称を設定します。
     * @param positionName 役職名称
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
    /**
     * 役職歴発令日を取得します。
     * @return 役職歴発令日
     */
    public String getPositionDt() {
        return positionDt;
    }
    /**
     * 役職歴発令日を設定します。
     * @param positionDt 役職歴発令日
     */
    public void setPositionDt(String positionDt) {
        this.positionDt = positionDt;
    }
    
    /**
     * 職務等級コードを取得します。
     * @return 職務等級コード
     */
    public String getGradeCd() {
        return gradeCd;
    }
    /**
     * 職務等級コードを設定します。
     * @param gradeCd 職務等級コード
     */
    public void setGradeCd(String gradeCd) {
        this.gradeCd = gradeCd;
    }
    
    /**
     * 職務等級名称を取得します。
     * @return 職務等級名称
     */
    public String getGradeName() {
        return gradeName;
    }
    /**
     * 職務等級名称を設定します。
     * @param gradeName 職務等級名称
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    
    /**
     * 職級歴発令日を取得します。
     * @return 職級歴発令日
     */
    public String getGradeDt() {
        return gradeDt;
    }
    /**
     * 職級歴発令日を設定します。
     * @param gradeDt 職級歴発令日
     */
    public void setGradeDt(String gradeDt) {
        this.gradeDt = gradeDt;
    }
    
    /**
     * 休職開始日を取得します。
     * @return 休職開始日
     */
    public String getKyushokuFrom() {
        return kyushokuFrom;
    }
    /**
     * 休職開始日を設定します。
     * @param kyushokuFrom 休職開始日
     */
    public void setKyushokuFrom(String kyushokuFrom) {
        this.kyushokuFrom = kyushokuFrom;
    }
    
    /**
     * 休職期日を取得します。
     * @return 休職期日
     */
    public String getKyushokuTo() {
        return kyushokuTo;
    }
    /**
     * 休職期日を設定します。
     * @param kyushokuTo 休職期日
     */
    public void setKyushokuTo(String kyushokuTo) {
        this.kyushokuTo = kyushokuTo;
    }
    
    /**
     * 携帯電話番号１を取得します。
     * @return 携帯電話番号１
     */
    public String getCellularPhone1() {
        return cellularPhone1;
    }
    /**
     * 携帯電話番号１を設定します。
     * @param cellularPhone1 携帯電話番号１
     */
    public void setCellularPhone1(String cellularPhone1) {
        this.cellularPhone1 = cellularPhone1;
    }
    
    /**
     * 携帯電話番号２を取得します。
     * @return 携帯電話番号２
     */
    public String getCellularPhone2() {
        return cellularPhone2;
    }
    /**
     * 携帯電話番号２を設定します。
     * @param cellularPhone2 携帯電話番号２
     */
    public void setCellularPhone2(String cellularPhone2) {
        this.cellularPhone2 = cellularPhone2;
    }
    
    /**
     * 内線番号を取得します。
     * @return 内線番号
     */
    public String getExtensionNo() {
        return extensionNo;
    }
    /**
     * 内線番号を設定します。
     * @param extensionNo 内線番号
     */
    public void setExtensionNo(String extensionNo) {
        this.extensionNo = extensionNo;
    }
    
    /**
     * その他電話番号を取得します。
     * @return その他電話番号
     */
    public String getOtherPhone() {
        return otherPhone;
    }
    /**
     * その他電話番号を設定します。
     * @param otherPhone その他電話番号
     */
    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }
    
    /**
     * メールアドレス１を取得します。
     * @return メールアドレス１
     */
    public String getMailAdd1() {
        return mailAdd1;
    }
    /**
     * メールアドレス１を設定します。
     * @param mailAdd1 メールアドレス１
     */
    public void setMailAdd1(String mailAdd1) {
        this.mailAdd1 = mailAdd1;
    }
    
    /**
     * メールアドレス２を取得します。
     * @return メールアドレス２
     */
    public String getMailAdd2() {
        return mailAdd2;
    }
    /**
     * メールアドレス２を設定します。
     * @param mailAdd2 メールアドレス２
     */
    public void setMailAdd2(String mailAdd2) {
        this.mailAdd2 = mailAdd2;
    }
    
    /**
     * メールアドレス３を取得します。
     * @return メールアドレス３
     */
    public String getMailAdd3() {
        return mailAdd3;
    }
    /**
     * メールアドレス３を設定します。
     * @param mailAdd3 メールアドレス３
     */
    public void setMailAdd3(String mailAdd3) {
        this.mailAdd3 = mailAdd3;
    }
    
    /**
     * ユーザーID（5桁）を取得します。
     * @return ユーザーID（5桁）
     */
    public String getOldUserId() {
        return oldUserId;
    }
    /**
     * ユーザーID（5桁）を設定します。
     * @param oldUserId ユーザーID（5桁）
     */
    public void setOldUserId(String oldUserId) {
        this.oldUserId = oldUserId;
    }
    
    /**
     * 使用停止フラグを取得します。
     * @return 使用停止フラグ
     */
    public String getStopFlg() {
        return stopFlg;
    }
    /**
     * 使用停止フラグを設定します。
     * @param stopFlg 使用停止フラグ
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }
    
    /**
     * 画面登録フラグを取得します。
     * @return 画面登録フラグ
     */
    public String getKbnSpare1() {
        return kbnSpare1;
    }
    /**
     * 画面登録フラグを設定します。
     * @param kbnSpare1 画面登録フラグ
     */
    public void setKbnSpare1(String kbnSpare1) {
        this.kbnSpare1 = kbnSpare1;
    }
    
    /**
     * 予備フラグ２を取得します。
     * @return 予備フラグ２
     */
    public String getKbnSpare2() {
        return kbnSpare2;
    }
    /**
     * 予備フラグ２を設定します。
     * @param kbnSpare2 予備フラグ２
     */
    public void setKbnSpare2(String kbnSpare2) {
        this.kbnSpare2 = kbnSpare2;
    }
    
    /**
     * 予備フラグ３を取得します。
     * @return 予備フラグ３
     */
    public String getKbnSpare3() {
        return kbnSpare3;
    }
    /**
     * 予備フラグ３を設定します。
     * @param kbnSpare3 予備フラグ３
     */
    public void setKbnSpare3(String kbnSpare3) {
        this.kbnSpare3 = kbnSpare3;
    }
    
    /**
     * 予備フラグ４を取得します。
     * @return 予備フラグ４
     */
    public String getKbnSpare4() {
        return kbnSpare4;
    }
    /**
     * 予備フラグ４を設定します。
     * @param kbnSpare4 予備フラグ４
     */
    public void setKbnSpare4(String kbnSpare4) {
        this.kbnSpare4 = kbnSpare4;
    }
    
    /**
     * 予備フラグ５を取得します。
     * @return 予備フラグ５
     */
    public String getKbnSpare5() {
        return kbnSpare5;
    }
    /**
     * 予備フラグ５を設定します。
     * @param kbnSpare5 予備フラグ５
     */
    public void setKbnSpare5(String kbnSpare5) {
        this.kbnSpare5 = kbnSpare5;
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
     * 登録ＰＧＭを取得します。
     * @return 登録ＰＧＭ
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録ＰＧＭを設定します。
     * @param firstPgm 登録ＰＧＭ
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録ＴＭＳＰを取得します。
     * @return 登録ＴＭＳＰ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録ＴＭＳＰを設定します。
     * @param firstTmsp 登録ＴＭＳＰ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新ＰＧＭを取得します。
     * @return 更新ＰＧＭ
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新ＰＧＭを設定します。
     * @param lastPgm 更新ＰＧＭ
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新ＴＭＳＰを取得します。
     * @return 更新ＴＭＳＰ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新ＴＭＳＰを設定します。
     * @param lastTmsp 更新ＴＭＳＰ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
