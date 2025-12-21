package jp.co.isid.mos.bird.entry.mlviewlist.entity;

/**
 * CSVópEntity
 * èCê≥ÅF2006/09/12
 * Å@Å@Å@ëóïtêÊÅAíSìñé“ÇÃèZèäÇÇPÅ`ÇRÇ…ï™äÑÇµÇƒï€éùÇ∑ÇÈÇÊÇ§ïœçX
 */
public class UIMlEntryCsvInfo {

    public static final String TABLE = "BT23MLEJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String entryName_COLUMN = "ENTRY_NAME";
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
    public static final String entryCount_COLUMN = "ENTRY_COUNT";
    public static final String examNo_COLUMN = "EXAM_NO";
    public static final String job_COLUMN = "JOB";
    public static final String nameTanto_COLUMN = "NAME_TANTO";
    public static final String telTanto_COLUMN = "TEL_TANTO";
    public static final String zipTanto_COLUMN = "ZIP_TANTO";
    public static final String addressTanto1_COLUMN = "ADDRESS_TANTO1";
    public static final String addressTanto2_COLUMN = "ADDRESS_TANTO2";
    public static final String addressTanto3_COLUMN = "ADDRESS_TANTO3";
    public static final String soufuNameTanto_COLUMN = "SOUFU_NAME_TANTO";
    public static final String nameHokoku_COLUMN = "NAME_HOKOKU";
    public static final String telHokoku_COLUMN = "TEL_HOKOKU";
    public static final String zipHokoku_COLUMN = "ZIP_HOKOKU";
    public static final String addressHokoku1_COLUMN = "ADDRESS_HOKOKU1";
    public static final String addressHokoku2_COLUMN = "ADDRESS_HOKOKU2";
    public static final String addressHokoku3_COLUMN = "ADDRESS_HOKOKU3";
    public static final String soufuNameHokaku_COLUMN = "SOUFU_NAME_HOKOKU";
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    public static final String staffLNameKna_COLUMN = "STAFF_L_NAME_KNA";
    public static final String staffFNameKna_COLUMN = "STAFF_F_NAME_KNA";
    public static final String sex_COLUMN = "SEX";
    public static final String birthday_COLUMN = "BIRTHDAY";
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    public static final String entry_placeCd_COLUMN = "ENTRY_PLACE_CD";
    public static final String note_COLUMN = "NOTE";
    public static final String abilityChk_COLUMN = "ABILITY_CHK";
    public static final String examChk_COLUMN = "EXAM_CHK";
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
 
    private static final String COUSE_STATUS_NOTCOMPLETION = "0";
    private static final String COUSE_STATUS_COMPLETION = "1";
    private static final String COUSE_STATUS_SCHEDULE = "2";
    private static final String COUSE_STATUS_NAME_NOTCOMPLETION = "ñ¢èCóπ";
    private static final String COUSE_STATUS_NAME_COMPLETION = "èCóπ";
    private static final String COUSE_STATUS_NAME_SCHEDULE = "ó\íË";

    private String onerCd;
    private String onerNameKj;
    private String sibuCd;
    private String sibuName;
    private String miseCd;
    private String miseNameKj;
    private String entryName;
    private String totalLastYear;
    private String totalLastKai;
    private String entryCount;
    private String examNo;
    private String job;
    private String nameTanto;
    private String telTanto;
    private String zipTanto;
    private String addressTanto1;
    private String addressTanto2;
    private String addressTanto3;
    private String soufuNameTanto;
    private String nameHokoku;
    private String telHokoku;
    private String zipHokoku;
    private String addressHokoku1;
    private String addressHokoku2;
    private String addressHokoku3;
    private String soufuNameHokoku;
    private String staffLNameKj;
    private String staffFNameKj;
    private String staffLNameKna;
    private String staffFNameKna;
    private String sex;
    private String birthday;
    private String empExpYear;
    private String empExpMonth;
    private String paExpYear;
    private String paExpMonth;
    private String courseStatus;
    private String compleCourseDt;
    private String compleCourseCd;
    private String compleCourseName;
    private String entry_placeCd;
    private String note;
    private String abilityChk;
    private String examChk;
    private String interviewChk;
    private String lastUser;
    private String lastTmsp;
    private String keiyakuEnd;
    
    
    public String getAbilityChk() {
        return abilityChk;
    }
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCompleCourseCd() {
        return compleCourseCd;
    }
    public void setCompleCourseCd(String compleCourseCd) {
        this.compleCourseCd = compleCourseCd;
    }
    public String getCompleCourseDt() {
        return compleCourseDt;
    }
    public void setCompleCourseDt(String compleCourseDt) {
        this.compleCourseDt = compleCourseDt;
    }
    public String getCompleCourseName() {
        return compleCourseName;
    }
    public void setCompleCourseName(String compleCourseName) {
        this.compleCourseName = compleCourseName;
    }
    public String getCourseStatus() {
        return courseStatus;
    }
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    public String getEmpExpMonth() {
        return empExpMonth;
    }
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    public String getEmpExpYear() {
        return empExpYear;
    }
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    public String getEntry_placeCd() {
        return entry_placeCd;
    }
    public void setEntry_placeCd(String entry_placeCd) {
        this.entry_placeCd = entry_placeCd;
    }
    public String getEntryName() {
        return entryName;
    }
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
    public String getExamChk() {
        return examChk;
    }
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }
    public String getEntryCount() {
        return entryCount;
    }
    public void setEntryCount(String entryCount) {
        this.entryCount = entryCount;
    }
    public String getExamNo() {
        return examNo;
    }
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    public String getInterviewChk() {
        return interviewChk;
    }
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    public String getLastTmsp() {
        return lastTmsp;
    }
    public void setLastTmsp(String lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    public String getLastUser() {
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    public String getMiseCd() {
        return miseCd;
    }
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    public String getMiseNameKj() {
        return miseNameKj;
    }
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    public String getNameHokoku() {
        return nameHokoku;
    }
    public void setNameHokoku(String nameHokoku) {
        this.nameHokoku = nameHokoku;
    }
    public String getNameTanto() {
        return nameTanto;
    }
    public void setNameTanto(String nameTanto) {
        this.nameTanto = nameTanto;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public String getOnerNameKj() {
        return onerNameKj;
    }
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    public String getPaExpMonth() {
        return paExpMonth;
    }
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    public String getPaExpYear() {
        return paExpYear;
    }
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSibuCd() {
        return sibuCd;
    }
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    public String getSibuName() {
        return sibuName;
    }
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    public String getSoufuNameTanto() {
        return soufuNameTanto;
    }
    public void setSoufuNameTanto(String soufuNameTanto) {
        this.soufuNameTanto = soufuNameTanto;
    }
    public String getStaffFNameKj() {
        return staffFNameKj;
    }
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }
    public String getStaffFNameKna() {
        return staffFNameKna;
    }
    public void setStaffFNameKna(String staffFNameKna) {
        this.staffFNameKna = staffFNameKna;
    }
    public String getStaffLNameKj() {
        return staffLNameKj;
    }
    public void setStaffLNameKj(String staffLNameKj) {
        this.staffLNameKj = staffLNameKj;
    }
    public String getStaffLNameKna() {
        return staffLNameKna;
    }
    public void setStaffLNameKna(String staffLNameKna) {
        this.staffLNameKna = staffLNameKna;
    }
    public String getTelHokoku() {
        return telHokoku;
    }
    public void setTelHokoku(String telHokoku) {
        this.telHokoku = telHokoku;
    }
    public String getTelTanto() {
        return telTanto;
    }
    public void setTelTanto(String telTanto) {
        this.telTanto = telTanto;
    }
    public String getTotalLastKai() {
        return totalLastKai;
    }
    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }
    public String getTotalLastYear() {
        return totalLastYear;
    }
    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    public String getZipHokoku() {
        return zipHokoku;
    }
    public void setZipHokoku(String zipHokoku) {
        this.zipHokoku = zipHokoku;
    }
    public String getZipTanto() {
        return zipTanto;
    }
    public void setZipTanto(String zipTanto) {
        this.zipTanto = zipTanto;
    }
    public String getSoufuNameHokoku() {
        return soufuNameHokoku;
    }
    public void setSoufuNameHokoku(String soufuNameHokoku) {
        this.soufuNameHokoku = soufuNameHokoku;
    }

    public String getCourseStatusName() {
        String courseStatusName = "";
        if (getCourseStatus() != null) {
            if (getCourseStatus().equals(COUSE_STATUS_NOTCOMPLETION)) {
                courseStatusName = COUSE_STATUS_NAME_NOTCOMPLETION;
            } else if (getCourseStatus().equals(COUSE_STATUS_COMPLETION)) {
                courseStatusName = COUSE_STATUS_NAME_COMPLETION;
            } else if (getCourseStatus().equals(COUSE_STATUS_SCHEDULE)) {
                courseStatusName = COUSE_STATUS_NAME_SCHEDULE;
            }
        }
        return courseStatusName;
    }
    public String getAddressHokoku1() {
        return addressHokoku1;
    }
    public void setAddressHokoku1(String addressHokoku1) {
        this.addressHokoku1 = addressHokoku1;
    }
    public String getAddressHokoku2() {
        return addressHokoku2;
    }
    public void setAddressHokoku2(String addressHokoku2) {
        this.addressHokoku2 = addressHokoku2;
    }
    public String getAddressHokoku3() {
        return addressHokoku3;
    }
    public void setAddressHokoku3(String addressHokoku3) {
        this.addressHokoku3 = addressHokoku3;
    }
    public String getAddressTanto1() {
        return addressTanto1;
    }
    public void setAddressTanto1(String addressTanto1) {
        this.addressTanto1 = addressTanto1;
    }
    public String getAddressTanto2() {
        return addressTanto2;
    }
    public void setAddressTanto2(String addressTanto2) {
        this.addressTanto2 = addressTanto2;
    }
    public String getAddressTanto3() {
        return addressTanto3;
    }
    public void setAddressTanto3(String addressTanto3) {
        this.addressTanto3 = addressTanto3;
    }
}
