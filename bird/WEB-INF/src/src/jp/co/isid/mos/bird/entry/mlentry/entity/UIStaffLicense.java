package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIStaffLicense {
    
    public static final String TABLE = "BT26UPJK";
    
    
    public static final String staff_id_COLUMN = "STAFF_ID";
    public static final String license_kbn_COLUMN = "LICENSE_KBN";
    public static final String license_no_COLUMN = "LICENSE_NO";
    public static final String license_dt_COLUMN = "LICENSE_DT";
    public static final String license_up_dt_COLUMN = "LICENSE_UP_DT";
    public static final String license_valid_dt_COLUMN = "LICENSE_VALID_DT";
    public static final String renew1_ent_year_COLUMN = "RENEW1_ENT_YEAR";
    public static final String renew1_ent_kai_COLUMN = "RENEW1_ENT_KAI";
    public static final String renew1_course_COLUMN = "RENEW1_COURSE";
    public static final String renew1_course_name_COLUMN = "RENEW1_COURSE_NAME";
    public static final String renew1_date_COLUMN = "RENEW1_DATE";
    public static final String renew1_status_COLUMN = "RENEW1_STATUS";
    public static final String renew2_ent_year_COLUMN = "RENEW2_ENT_YEAR";
    public static final String renew2_ent_kai_COLUMN = "RENEW2_ENT_KAI";
    public static final String renew2_course_COLUMN = "RENEW2_COURSE";
    public static final String renew2_course_name_COLUMN = "RENEW2_COURSE_NAME";
    public static final String renew2_date_COLUMN = "RENEW2_DATE";
    public static final String renew2_status_COLUMN = "RENEW2_STATUS";
    public static final String renew3_ent_year_COLUMN = "RENEW3_ENT_YEAR";
    public static final String renew3_ent_kai_COLUMN = "RENEW3_ENT_KAI";
    public static final String renew3_course_COLUMN = "RENEW3_COURSE";
    public static final String renew3_course_name_COLUMN = "RENEW3_COURSE_NAME";
    public static final String renew3_date_COLUMN = "RENEW3_DATE";
    public static final String renew3_status_COLUMN = "RENEW3_STATUS";
    public static final String renew4_ent_year_COLUMN = "RENEW4_ENT_YEAR";
    public static final String renew4_ent_kai_COLUMN = "RENEW4_ENT_KAI";
    public static final String renew4_course_COLUMN = "RENEW4_COURSE";
    public static final String renew4_course_name_COLUMN = "RENEW4_COURSE_NAME";
    public static final String renew4_date_COLUMN = "RENEW4_DATE";
    public static final String renew4_status_COLUMN = "RENEW4_STATUS";
    public static final String renew5_ent_year_COLUMN = "RENEW5_ENT_YEAR";
    public static final String renew5_ent_kai_COLUMN = "RENEW5_ENT_KAI";
    public static final String renew5_course_COLUMN = "RENEW5_COURSE";
    public static final String renew5_course_name_COLUMN = "RENEW5_COURSE_NAME";
    public static final String renew5_date_COLUMN = "RENEW5_DATE";
    public static final String renew5_status_COLUMN = "RENEW5_STATUS";
    public static final String expire_flg_COLUMN = "EXPIRE_FLG";
    public static final String first_user_COLUMN = "FIRST_USER";
    public static final String first_pgm_COLUMN = "FIRST_PGM";
    public static final String first_tmsp_COLUMN = "FIRST_TMSP";
    public static final String last_user_COLUMN = "LAST_USER";
    public static final String last_pgm_COLUMN = "LAST_PGM";
    public static final String last_tmsp_COLUMN = "LAST_TMSP";

    
    
    private String staffId;
    private String licenseKbn;
    private String licenseNo;
    private String licenseDt;
    private String licenseUpDt;
    private String licenseValidDt;
    private String renew1EntYear;
    private String renew1EntKai;
    private String renew1Course;
    private String renew1CourseName;
    private String renew1Date;
    private String renew1Status;
    private String renew2EntYear;
    private String renew2EntKai;
    private String renew2Course;
    private String renew2CourseName;
    private String renew2Date;
    private String renew2Status;
    private String renew3EntYear;
    private String renew3EntKai;
    private String renew3Course;
    private String renew3CourseName;
    private String renew3Date;
    private String renew3Status;
    private String renew4EntYear;
    private String renew4EntKai;
    private String renew4Course;
    private String renew4CourseName;
    private String renew4Date;
    private String renew4Status;
    private String renew5EntYear;
    private String renew5EntKai;
    private String renew5Course;
    private String renew5CourseName;
    private String renew5Date;
    private String renew5Status;
    private String expireFlg;
    private String firstUser;
    private String firstPgm;
    private Timestamp firstTmsp;
    private String lastUser;
    private String lastPgm;
    private Timestamp lastTmsp;
    
    
    
    
    public String getExpireFlg() {
        return expireFlg;
    }
    public void setExpireFlg(String expireFlg) {
        this.expireFlg = expireFlg;
    }
    public String getFirstPgm() {
        return firstPgm;
    }
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    public String getFirstUser() {
        return firstUser;
    }
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    public String getLastPgm() {
        return lastPgm;
    }
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    public String getLastUser() {
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    public String getLicenseDt() {
        return licenseDt;
    }
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    public String getLicenseKbn() {
        return licenseKbn;
    }
    public void setLicenseKbn(String licenseKbn) {
        this.licenseKbn = licenseKbn;
    }
    public String getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    public String getLicenseUpDt() {
        return licenseUpDt;
    }
    public void setLicenseUpDt(String licenseUpDt) {
        this.licenseUpDt = licenseUpDt;
    }
    public String getLicenseValidDt() {
        return licenseValidDt;
    }
    public void setLicenseValidDt(String licenseValidDt) {
        this.licenseValidDt = licenseValidDt;
    }
    public String getRenew1Course() {
        return renew1Course;
    }
    public void setRenew1Course(String renew1Course) {
        this.renew1Course = renew1Course;
    }
    public String getRenew1CourseName() {
        return renew1CourseName;
    }
    public void setRenew1CourseName(String renew1CourseName) {
        this.renew1CourseName = renew1CourseName;
    }
    public String getRenew1Date() {
        return renew1Date;
    }
    public void setRenew1Date(String renew1Date) {
        this.renew1Date = renew1Date;
    }
    public String getRenew1EntKai() {
        return renew1EntKai;
    }
    public void setRenew1EntKai(String renew1EntKai) {
        this.renew1EntKai = renew1EntKai;
    }
    public String getRenew1EntYear() {
        return renew1EntYear;
    }
    public void setRenew1EntYear(String renew1EntYear) {
        this.renew1EntYear = renew1EntYear;
    }
    public String getRenew1Status() {
        return renew1Status;
    }
    public void setRenew1Status(String renew1Status) {
        this.renew1Status = renew1Status;
    }
    public String getRenew2Course() {
        return renew2Course;
    }
    public void setRenew2Course(String renew2Course) {
        this.renew2Course = renew2Course;
    }
    public String getRenew2CourseName() {
        return renew2CourseName;
    }
    public void setRenew2CourseName(String renew2CourseName) {
        this.renew2CourseName = renew2CourseName;
    }
    public String getRenew2Date() {
        return renew2Date;
    }
    public void setRenew2Date(String renew2Date) {
        this.renew2Date = renew2Date;
    }
    public String getRenew2EntKai() {
        return renew2EntKai;
    }
    public void setRenew2EntKai(String renew2EntKai) {
        this.renew2EntKai = renew2EntKai;
    }
    public String getRenew2EntYear() {
        return renew2EntYear;
    }
    public void setRenew2EntYear(String renew2EntYear) {
        this.renew2EntYear = renew2EntYear;
    }
    public String getRenew2Status() {
        return renew2Status;
    }
    public void setRenew2Status(String renew2Status) {
        this.renew2Status = renew2Status;
    }
    public String getRenew3Course() {
        return renew3Course;
    }
    public void setRenew3Course(String renew3Course) {
        this.renew3Course = renew3Course;
    }
    public String getRenew3CourseName() {
        return renew3CourseName;
    }
    public void setRenew3CourseName(String renew3CourseName) {
        this.renew3CourseName = renew3CourseName;
    }
    public String getRenew3Date() {
        return renew3Date;
    }
    public void setRenew3Date(String renew3Date) {
        this.renew3Date = renew3Date;
    }
    public String getRenew3EntKai() {
        return renew3EntKai;
    }
    public void setRenew3EntKai(String renew3EntKai) {
        this.renew3EntKai = renew3EntKai;
    }
    public String getRenew3EntYear() {
        return renew3EntYear;
    }
    public void setRenew3EntYear(String renew3EntYear) {
        this.renew3EntYear = renew3EntYear;
    }
    public String getRenew3Status() {
        return renew3Status;
    }
    public void setRenew3Status(String renew3Status) {
        this.renew3Status = renew3Status;
    }
    public String getRenew4Course() {
        return renew4Course;
    }
    public void setRenew4Course(String renew4Course) {
        this.renew4Course = renew4Course;
    }
    public String getRenew4CourseName() {
        return renew4CourseName;
    }
    public void setRenew4CourseName(String renew4CourseName) {
        this.renew4CourseName = renew4CourseName;
    }
    public String getRenew4Date() {
        return renew4Date;
    }
    public void setRenew4Date(String renew4Date) {
        this.renew4Date = renew4Date;
    }
    public String getRenew4EntKai() {
        return renew4EntKai;
    }
    public void setRenew4EntKai(String renew4EntKai) {
        this.renew4EntKai = renew4EntKai;
    }
    public String getRenew4EntYear() {
        return renew4EntYear;
    }
    public void setRenew4EntYear(String renew4EntYear) {
        this.renew4EntYear = renew4EntYear;
    }
    public String getRenew4Status() {
        return renew4Status;
    }
    public void setRenew4Status(String renew4Status) {
        this.renew4Status = renew4Status;
    }
    public String getRenew5Course() {
        return renew5Course;
    }
    public void setRenew5Course(String renew5Course) {
        this.renew5Course = renew5Course;
    }
    public String getRenew5CourseName() {
        return renew5CourseName;
    }
    public void setRenew5CourseName(String renew5CourseName) {
        this.renew5CourseName = renew5CourseName;
    }
    public String getRenew5Date() {
        return renew5Date;
    }
    public void setRenew5Date(String renew5Date) {
        this.renew5Date = renew5Date;
    }
    public String getRenew5EntKai() {
        return renew5EntKai;
    }
    public void setRenew5EntKai(String renew5EntKai) {
        this.renew5EntKai = renew5EntKai;
    }
    public String getRenew5EntYear() {
        return renew5EntYear;
    }
    public void setRenew5EntYear(String renew5EntYear) {
        this.renew5EntYear = renew5EntYear;
    }
    public String getRenew5Status() {
        return renew5Status;
    }
    public void setRenew5Status(String renew5Status) {
        this.renew5Status = renew5Status;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }


}
