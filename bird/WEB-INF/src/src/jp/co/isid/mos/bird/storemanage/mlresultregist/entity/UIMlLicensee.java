package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class UIMlLicensee {
    
    public static final String TABLE = "BT26UPJK";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String licenseKbn_COLUMN = "LICENSE_KBN";
    
    public static final String licenseNo_COLUMN = "LICENSE_NO";
    
    public static final String licenseDt_COLUMN = "LICENSE_DT";
    
    public static final String licenseUpDt_COLUMN = "LICENSE_UP_DT";
    
    public static final String licenseValidDt_COLUMN = "LICENSE_VALID_DT";
    
    public static final String renew1EntYear_COLUMN = "RENEW1_ENT_YEAR";
    
    public static final String renew1EntKai_COLUMN = "RENEW1_ENT_KAI";
    
    public static final String renew1Course_COLUMN = "RENEW1_COURSE";
    
    public static final String renew1CourseName_COLUMN = "RENEW1_COURSE_NAME";
    
    public static final String renew1Date_COLUMN = "RENEW1_DATE";
    
    public static final String renew1Status_COLUMN = "RENEW1_STATUS";
    
    public static final String renew2EntYear_COLUMN = "RENEW2_ENT_YEAR";
    
    public static final String renew2EntKai_COLUMN = "RENEW2_ENT_KAI";
    
    public static final String renew2Course_COLUMN = "RENEW2_COURSE";
    
    public static final String renew2CourseName_COLUMN = "RENEW2_COURSE_NAME";
    
    public static final String renew2Date_COLUMN = "RENEW2_DATE";
    
    public static final String renew2Status_COLUMN = "RENEW2_STATUS";
    
    public static final String renew3EntYear_COLUMN = "RENEW3_ENT_YEAR";
    
    public static final String renew3EntKai_COLUMN = "RENEW3_ENT_KAI";
    
    public static final String renew3Course_COLUMN = "RENEW3_COURSE";
    
    public static final String renew3CourseName_COLUMN = "RENEW3_COURSE_NAME";
    
    public static final String renew3Date_COLUMN = "RENEW3_DATE";
    
    public static final String renew3Status_COLUMN = "RENEW3_STATUS";
    
    public static final String renew4EntYear_COLUMN = "RENEW4_ENT_YEAR";
    
    public static final String renew4EntKai_COLUMN = "RENEW4_ENT_KAI";
    
    public static final String renew4Course_COLUMN = "RENEW4_COURSE";
    
    public static final String renew4CourseName_COLUMN = "RENEW4_COURSE_NAME";
    
    public static final String renew4Date_COLUMN = "RENEW4_DATE";
    
    public static final String renew4Status_COLUMN = "RENEW4_STATUS";
    
    public static final String renew5EntYear_COLUMN = "RENEW5_ENT_YEAR";
    
    public static final String renew5EntKai_COLUMN = "RENEW5_ENT_KAI";
    
    public static final String renew5Course_COLUMN = "RENEW5_COURSE";
    
    public static final String renew5CourseName_COLUMN = "RENEW5_COURSE_NAME";
    
    public static final String renew5Date_COLUMN = "RENEW5_DATE";
    
    public static final String renew5Status_COLUMN = "RENEW5_STATUS";
    
    public static final String expireFlg_COLUMN = "EXPIRE_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * ライセンス種別
     */
    private String licenseKbn;
    
    /**
     * ライセンス認定番号
     */
    private String licenseNo;
    
    /**
     * ライセンス取得年月
     */
    private String licenseDt;
    
    /**
     * ライセンス更新年月
     */
    private String licenseUpDt;
    
    /**
     * ライセンス有効期限日
     */
    private String licenseValidDt;
    
    /**
     * 更新研修1受講年度
     */
    private String renew1EntYear;
    
    /**
     * 更新研修1受講回
     */
    private String renew1EntKai;
    
    /**
     * 更新研修1コースコード
     */
    private String renew1Course;
    
    /**
     * 更新研修1コース名
     */
    private String renew1CourseName;
    
    /**
     * 更新研修修了日1
     */
    private String renew1Date;
    
    /**
     * 更新研修1状況
     */
    private String renew1Status;
    
    /**
     * 更新研修2受講年度
     */
    private String renew2EntYear;
    
    /**
     * 更新研修2受講回
     */
    private String renew2EntKai;
    
    /**
     * 更新研修2コースコード
     */
    private String renew2Course;
    
    /**
     * 更新研修2コース名
     */
    private String renew2CourseName;
    
    /**
     * 更新研修修了日2
     */
    private String renew2Date;
    
    /**
     * 更新研修2状況
     */
    private String renew2Status;
    
    /**
     * 更新研修3受講年度
     */
    private String renew3EntYear;
    
    /**
     * 更新研修3受講回
     */
    private String renew3EntKai;
    
    /**
     * 更新研修3コースコード
     */
    private String renew3Course;
    
    /**
     * 更新研修3コース名
     */
    private String renew3CourseName;
    
    /**
     * 更新研修修了日3
     */
    private String renew3Date;
    
    /**
     * 更新研修3状況
     */
    private String renew3Status;
    
    /**
     * 更新研修4受講年度
     */
    private String renew4EntYear;
    
    /**
     * 更新研修4受講回
     */
    private String renew4EntKai;
    
    /**
     * 更新研修4コースコード
     */
    private String renew4Course;
    
    /**
     * 更新研修4コース名
     */
    private String renew4CourseName;
    
    /**
     * 更新研修修了日4
     */
    private String renew4Date;
    
    /**
     * 更新研修4状況
     */
    private String renew4Status;
    
    /**
     * 更新研修5受講年度
     */
    private String renew5EntYear;
    
    /**
     * 更新研修5受講回
     */
    private String renew5EntKai;
    
    /**
     * 更新研修5コースコード
     */
    private String renew5Course;
    
    /**
     * 更新研修5コース名
     */
    private String renew5CourseName;
    
    /**
     * 更新研修修了日5
     */
    private String renew5Date;
    
    /**
     * 更新研修5状況
     */
    private String renew5Status;
    
    /**
     * 失効フラグ
     */
    private String expireFlg;
    
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
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * スタッフIDを設定します。
     * @param staffId スタッフID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * ライセンス種別を取得します。
     * @return ライセンス種別
     */
    public String getLicenseKbn() {
        return licenseKbn;
    }
    /**
     * ライセンス種別を設定します。
     * @param licenseKbn ライセンス種別
     */
    public void setLicenseKbn(String licenseKbn) {
        this.licenseKbn = licenseKbn;
    }
    
    /**
     * ライセンス認定番号を取得します。
     * @return ライセンス認定番号
     */
    public String getLicenseNo() {
        return licenseNo;
    }
    /**
     * ライセンス認定番号を設定します。
     * @param licenseNo ライセンス認定番号
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    
    /**
     * ライセンス取得年月を取得します。
     * @return ライセンス取得年月
     */
    public String getLicenseDt() {
        return licenseDt;
    }
    /**
     * ライセンス取得年月を設定します。
     * @param licenseDt ライセンス取得年月
     */
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    
    /**
     * ライセンス更新年月を取得します。
     * @return ライセンス更新年月
     */
    public String getLicenseUpDt() {
        return licenseUpDt;
    }
    /**
     * ライセンス更新年月を設定します。
     * @param licenseUpDt ライセンス更新年月
     */
    public void setLicenseUpDt(String licenseUpDt) {
        this.licenseUpDt = licenseUpDt;
    }
    
    /**
     * ライセンス有効期限日を取得します。
     * @return ライセンス有効期限日
     */
    public String getLicenseValidDt() {
        return licenseValidDt;
    }
    /**
     * ライセンス有効期限日を設定します。
     * @param licenseValidDt ライセンス有効期限日
     */
    public void setLicenseValidDt(String licenseValidDt) {
        this.licenseValidDt = licenseValidDt;
    }
    
    /**
     * 更新研修1受講年度を取得します。
     * @return 更新研修1受講年度
     */
    public String getRenew1EntYear() {
        return renew1EntYear;
    }
    /**
     * 更新研修1受講年度を設定します。
     * @param renew1EntYear 更新研修1受講年度
     */
    public void setRenew1EntYear(String renew1EntYear) {
        this.renew1EntYear = renew1EntYear;
    }
    
    /**
     * 更新研修1受講回を取得します。
     * @return 更新研修1受講回
     */
    public String getRenew1EntKai() {
        return renew1EntKai;
    }
    /**
     * 更新研修1受講回を設定します。
     * @param renew1EntKai 更新研修1受講回
     */
    public void setRenew1EntKai(String renew1EntKai) {
        this.renew1EntKai = renew1EntKai;
    }
    
    /**
     * 更新研修1コースコードを取得します。
     * @return 更新研修1コースコード
     */
    public String getRenew1Course() {
        return renew1Course;
    }
    /**
     * 更新研修1コースコードを設定します。
     * @param renew1Course 更新研修1コースコード
     */
    public void setRenew1Course(String renew1Course) {
        this.renew1Course = renew1Course;
    }
    
    /**
     * 更新研修1コース名を取得します。
     * @return 更新研修1コース名
     */
    public String getRenew1CourseName() {
        return renew1CourseName;
    }
    /**
     * 更新研修1コース名を設定します。
     * @param renew1CourseName 更新研修1コース名
     */
    public void setRenew1CourseName(String renew1CourseName) {
        this.renew1CourseName = renew1CourseName;
    }
    
    /**
     * 更新研修修了日1を取得します。
     * @return 更新研修修了日1
     */
    public String getRenew1Date() {
        return renew1Date;
    }
    /**
     * 更新研修修了日1を設定します。
     * @param renew1Date 更新研修修了日1
     */
    public void setRenew1Date(String renew1Date) {
        this.renew1Date = renew1Date;
    }
    
    /**
     * 更新研修1状況を取得します。
     * @return 更新研修1状況
     */
    public String getRenew1Status() {
        return renew1Status;
    }
    /**
     * 更新研修1状況を設定します。
     * @param renew1Status 更新研修1状況
     */
    public void setRenew1Status(String renew1Status) {
        this.renew1Status = renew1Status;
    }
    
    /**
     * 更新研修2受講年度を取得します。
     * @return 更新研修2受講年度
     */
    public String getRenew2EntYear() {
        return renew2EntYear;
    }
    /**
     * 更新研修2受講年度を設定します。
     * @param renew2EntYear 更新研修2受講年度
     */
    public void setRenew2EntYear(String renew2EntYear) {
        this.renew2EntYear = renew2EntYear;
    }
    
    /**
     * 更新研修2受講回を取得します。
     * @return 更新研修2受講回
     */
    public String getRenew2EntKai() {
        return renew2EntKai;
    }
    /**
     * 更新研修2受講回を設定します。
     * @param renew2EntKai 更新研修2受講回
     */
    public void setRenew2EntKai(String renew2EntKai) {
        this.renew2EntKai = renew2EntKai;
    }
    
    /**
     * 更新研修2コースコードを取得します。
     * @return 更新研修2コースコード
     */
    public String getRenew2Course() {
        return renew2Course;
    }
    /**
     * 更新研修2コースコードを設定します。
     * @param renew2Course 更新研修2コースコード
     */
    public void setRenew2Course(String renew2Course) {
        this.renew2Course = renew2Course;
    }
    
    /**
     * 更新研修2コース名を取得します。
     * @return 更新研修2コース名
     */
    public String getRenew2CourseName() {
        return renew2CourseName;
    }
    /**
     * 更新研修2コース名を設定します。
     * @param renew2CourseName 更新研修2コース名
     */
    public void setRenew2CourseName(String renew2CourseName) {
        this.renew2CourseName = renew2CourseName;
    }
    
    /**
     * 更新研修修了日2を取得します。
     * @return 更新研修修了日2
     */
    public String getRenew2Date() {
        return renew2Date;
    }
    /**
     * 更新研修修了日2を設定します。
     * @param renew2Date 更新研修修了日2
     */
    public void setRenew2Date(String renew2Date) {
        this.renew2Date = renew2Date;
    }
    
    /**
     * 更新研修2状況を取得します。
     * @return 更新研修2状況
     */
    public String getRenew2Status() {
        return renew2Status;
    }
    /**
     * 更新研修2状況を設定します。
     * @param renew2Status 更新研修2状況
     */
    public void setRenew2Status(String renew2Status) {
        this.renew2Status = renew2Status;
    }
    
    /**
     * 更新研修3受講年度を取得します。
     * @return 更新研修3受講年度
     */
    public String getRenew3EntYear() {
        return renew3EntYear;
    }
    /**
     * 更新研修3受講年度を設定します。
     * @param renew3EntYear 更新研修3受講年度
     */
    public void setRenew3EntYear(String renew3EntYear) {
        this.renew3EntYear = renew3EntYear;
    }
    
    /**
     * 更新研修3受講回を取得します。
     * @return 更新研修3受講回
     */
    public String getRenew3EntKai() {
        return renew3EntKai;
    }
    /**
     * 更新研修3受講回を設定します。
     * @param renew3EntKai 更新研修3受講回
     */
    public void setRenew3EntKai(String renew3EntKai) {
        this.renew3EntKai = renew3EntKai;
    }
    
    /**
     * 更新研修3コースコードを取得します。
     * @return 更新研修3コースコード
     */
    public String getRenew3Course() {
        return renew3Course;
    }
    /**
     * 更新研修3コースコードを設定します。
     * @param renew3Course 更新研修3コースコード
     */
    public void setRenew3Course(String renew3Course) {
        this.renew3Course = renew3Course;
    }
    
    /**
     * 更新研修3コース名を取得します。
     * @return 更新研修3コース名
     */
    public String getRenew3CourseName() {
        return renew3CourseName;
    }
    /**
     * 更新研修3コース名を設定します。
     * @param renew3CourseName 更新研修3コース名
     */
    public void setRenew3CourseName(String renew3CourseName) {
        this.renew3CourseName = renew3CourseName;
    }
    
    /**
     * 更新研修修了日3を取得します。
     * @return 更新研修修了日3
     */
    public String getRenew3Date() {
        return renew3Date;
    }
    /**
     * 更新研修修了日3を設定します。
     * @param renew3Date 更新研修修了日3
     */
    public void setRenew3Date(String renew3Date) {
        this.renew3Date = renew3Date;
    }
    
    /**
     * 更新研修3状況を取得します。
     * @return 更新研修3状況
     */
    public String getRenew3Status() {
        return renew3Status;
    }
    /**
     * 更新研修3状況を設定します。
     * @param renew3Status 更新研修3状況
     */
    public void setRenew3Status(String renew3Status) {
        this.renew3Status = renew3Status;
    }
    
    /**
     * 更新研修4受講年度を取得します。
     * @return 更新研修4受講年度
     */
    public String getRenew4EntYear() {
        return renew4EntYear;
    }
    /**
     * 更新研修4受講年度を設定します。
     * @param renew4EntYear 更新研修4受講年度
     */
    public void setRenew4EntYear(String renew4EntYear) {
        this.renew4EntYear = renew4EntYear;
    }
    
    /**
     * 更新研修4受講回を取得します。
     * @return 更新研修4受講回
     */
    public String getRenew4EntKai() {
        return renew4EntKai;
    }
    /**
     * 更新研修4受講回を設定します。
     * @param renew4EntKai 更新研修4受講回
     */
    public void setRenew4EntKai(String renew4EntKai) {
        this.renew4EntKai = renew4EntKai;
    }
    
    /**
     * 更新研修4コースコードを取得します。
     * @return 更新研修4コースコード
     */
    public String getRenew4Course() {
        return renew4Course;
    }
    /**
     * 更新研修4コースコードを設定します。
     * @param renew4Course 更新研修4コースコード
     */
    public void setRenew4Course(String renew4Course) {
        this.renew4Course = renew4Course;
    }
    
    /**
     * 更新研修4コース名を取得します。
     * @return 更新研修4コース名
     */
    public String getRenew4CourseName() {
        return renew4CourseName;
    }
    /**
     * 更新研修4コース名を設定します。
     * @param renew4CourseName 更新研修4コース名
     */
    public void setRenew4CourseName(String renew4CourseName) {
        this.renew4CourseName = renew4CourseName;
    }
    
    /**
     * 更新研修修了日4を取得します。
     * @return 更新研修修了日4
     */
    public String getRenew4Date() {
        return renew4Date;
    }
    /**
     * 更新研修修了日4を設定します。
     * @param renew4Date 更新研修修了日4
     */
    public void setRenew4Date(String renew4Date) {
        this.renew4Date = renew4Date;
    }
    
    /**
     * 更新研修4状況を取得します。
     * @return 更新研修4状況
     */
    public String getRenew4Status() {
        return renew4Status;
    }
    /**
     * 更新研修4状況を設定します。
     * @param renew4Status 更新研修4状況
     */
    public void setRenew4Status(String renew4Status) {
        this.renew4Status = renew4Status;
    }
    
    /**
     * 更新研修5受講年度を取得します。
     * @return 更新研修5受講年度
     */
    public String getRenew5EntYear() {
        return renew5EntYear;
    }
    /**
     * 更新研修5受講年度を設定します。
     * @param renew5EntYear 更新研修5受講年度
     */
    public void setRenew5EntYear(String renew5EntYear) {
        this.renew5EntYear = renew5EntYear;
    }
    
    /**
     * 更新研修5受講回を取得します。
     * @return 更新研修5受講回
     */
    public String getRenew5EntKai() {
        return renew5EntKai;
    }
    /**
     * 更新研修5受講回を設定します。
     * @param renew5EntKai 更新研修5受講回
     */
    public void setRenew5EntKai(String renew5EntKai) {
        this.renew5EntKai = renew5EntKai;
    }
    
    /**
     * 更新研修5コースコードを取得します。
     * @return 更新研修5コースコード
     */
    public String getRenew5Course() {
        return renew5Course;
    }
    /**
     * 更新研修5コースコードを設定します。
     * @param renew5Course 更新研修5コースコード
     */
    public void setRenew5Course(String renew5Course) {
        this.renew5Course = renew5Course;
    }
    
    /**
     * 更新研修5コース名を取得します。
     * @return 更新研修5コース名
     */
    public String getRenew5CourseName() {
        return renew5CourseName;
    }
    /**
     * 更新研修5コース名を設定します。
     * @param renew5CourseName 更新研修5コース名
     */
    public void setRenew5CourseName(String renew5CourseName) {
        this.renew5CourseName = renew5CourseName;
    }
    
    /**
     * 更新研修修了日5を取得します。
     * @return 更新研修修了日5
     */
    public String getRenew5Date() {
        return renew5Date;
    }
    /**
     * 更新研修修了日5を設定します。
     * @param renew5Date 更新研修修了日5
     */
    public void setRenew5Date(String renew5Date) {
        this.renew5Date = renew5Date;
    }
    
    /**
     * 更新研修5状況を取得します。
     * @return 更新研修5状況
     */
    public String getRenew5Status() {
        return renew5Status;
    }
    /**
     * 更新研修5状況を設定します。
     * @param renew5Status 更新研修5状況
     */
    public void setRenew5Status(String renew5Status) {
        this.renew5Status = renew5Status;
    }
    
    /**
     * 失効フラグを取得します。
     * @return 失効フラグ
     */
    public String getExpireFlg() {
        return expireFlg;
    }
    /**
     * 失効フラグを設定します。
     * @param expireFlg 失効フラグ
     */
    public void setExpireFlg(String expireFlg) {
        this.expireFlg = expireFlg;
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
