package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIEntryState {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String stateFlg_COLUMN = "STATE_FLG";
    public static final String staffId_COLUMN = "STAFF_ID";
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    public static final String miseNameKJ_COLUMN = "MISE_NAME_KJ";
    public static final String staffNameKj_COLUMN = "STAFF_NAME_KJ";
    public static final String staffNameKna_COLUMN = "STAFF_NAME_KNA";
    public static final String sex_COLUMN = "SEX";
    public static final String job_COLUMN = "JOB";
    public static final String examNo_COLUMN = "EXAM_NO";
    public static final String entryPlaceCd_COLUMN = "ENTRY_PLACE_CD";
    public static final String note_COLUMN = "NOTE";
    public static final String abilityChk_COLUMN = "ABILITY_CHK";
    public static final String examChk_COLUMN = "EXAM_CHK";
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    public static final String reentryFlgStr_COLUMN = "REENTRY_FLG";
    public static final String entryCount_COLUMN = "ENTRY_COUNT";
    public static final String reentryBaseYear_COLUMN = "REENTRY_BASE_YEAR";
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
    public static final String totalResult_COLUMN = "TOTAL_RESULT";
    public static final String sub1Result_COLUMN = "SUB1_RESULT";
    public static final String sub1LastYear_COLUMN = "SUB1_LAST_YEAR";
    public static final String sub1LastKai_COLUMN = "SUB1_LAST_KAI";
    public static final String sub2Result_COLUMN = "SUB2_RESULT";
    public static final String sub2LastYear_COLUMN = "SUB2_LAST_YEAR";
    public static final String sub2LastKai_COLUMN = "SUB2_LAST_KAI";
    public static final String sub3Result_COLUMN = "SUB3_RESULT";
    public static final String sub3LastYear_COLUMN = "SUB3_LAST_YEAR";
    public static final String sub3LastKai_COLUMN = "SUB3_LAST_KAI";
    
    public static final String stateFUsr_COLUMN = "STATE_F_USR";
    public static final String stateFPgm_COLUMN = "STATE_F_PGM";
    public static final String stateFTmp_COLUMN = "STATE_F_TMP";
    
    public static final String stateLUsr_COLUMN = "STATE_L_USR";
    public static final String stateLPgm_COLUMN = "STATE_L_PGM";
    public static final String stateLTmp_COLUMN = "STATE_L_TMP";
    
    public static final String resultFUsr_COLUMN = "RESULT_F_USR";
    public static final String resultFPgm_COLUMN = "RESULT_F_PGM";
    public static final String resultFTmp_COLUMN = "RESULT_F_TMP";
    
    public static final String resultLUsr_COLUMN = "RESULT_L_USR";
    public static final String resultLPgm_COLUMN = "RESULT_L_PGM";
    public static final String resultLTmp_COLUMN = "RESULT_L_TMP";
   
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String beforeFlg_COLUMN = "BEFORE_FLG";
    
    
    private String stateFlg;
    private String staffId;
    private String companyCd;
    private String onerCd;
    private String onerNameKj;
    private String miseCd1;
    private String miseNameKj;
    private String staffNameKj;
    private String staffNameKna;
    private String sex;
    private String job;
    private String examNo;
    private String entryPlaceCd;
    private String note;
    private String empExpYear;
    private String empExpMonth;
    private String paExpYear;
    private String paExpMonth;
    private String reentryFlgStr;
    private String entryCount;
    private String reentryBaseYear;
    private String totalLastYear;
    private String totalLastKai;
    private String totalResult;
    private String sub1Result;
    private String sub1LastYear;
    private String sub1LastKai;
    private String sub2Result;
    private String sub2LastYear;
    private String sub2LastKai;
    private String sub3Result;
    private String sub3LastYear;
    private String sub3LastKai;
    
    /**
     * 能力・筆記・面接チェック
     */
    private String abilityChk;
    private String examChk;
    private String interviewChk;
    
    
    /**
     * 能力・筆記・面接チェック文言
     */
    private String abilityChkMsg;
    private String examChkMsg;
    private String interviewChkMsg;
    
    
    private String stateFUsr;
    private String stateFPgm;
    private Timestamp stateFTmp;
    private String stateLUsr;
    private String stateLPgm;
    private Timestamp stateLTmp;
    private String resultFUsr;
    private String resultFPgm;
    private Timestamp resultFTmp;
    private String resultLUsr;
    private String resultLPgm;
    private Timestamp resultLTmp;
    
    private String entryCd;
    private String entryYear;
    private String entryKai;
    
    private String entryMsg;
    
    /**
     * 画面表示用エントリー者No.
     */
    private String entryNo;
    
    /**
     * 面接前回受験確認フラグ
     * １:前回受験
     * ※面接は連続で受験できないため
     */
    private String beforeFlg;
    
    
    private String entryPlaceName;
    
    
    /**
     * 新規行フラグ true:新規
     */
    private boolean insertFlg = false;
    
    /**
     * 再エントリーフラグ
     */
    private boolean reEntryFlg = false;
    
    
    
    /**
     * 能力フラグ
     * true: 受験or免除 false:受験不可
     */
    private boolean abilityFlg = false;
    
    /**
     * 筆記フラグ
     * true: 受験or免除 false:受験不可
     */
    private boolean examFlg = false;
    
    /**
     * 面接フラグ
     * true: 受験or免除 false:受験不可
     */
    private boolean interviewFlg = false;
    
    
    /**
     * 能力フラグ(前回結果)
     */
    private boolean abilityPastFlg = false;
    
    /**
     * 筆記フラグ(前回結果)
     */
    private boolean examPastFlg = false;
    
    /**
     * 面接フラグ(前回結果)
     */
    private boolean interviewPastFlg = false;
    
    
  
    /**
     * Nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    
    /**
     * 能力チェックステータス取得
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @return
     */
    public String getAbilityChk() {
        return abilityChk;
    }
    /**
     * 能力チェックステータス設定
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @param abilityChk
     */
    public void setAbilityChk(String str) {
        this.abilityChk = str;
    }
    
    
    /**
     * 筆記チェックステータス取得
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @return
     */
    public String getExamChk() {
        return examChk;
    }
    /**
     * 筆記チェックステータス設定 
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @param examChk
     */
    public void setExamChk(String str) {
        this.examChk = str;
    }
    
    
    /**
     * 面接チェックステータス取得
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @return
     */
    public String getInterviewChk() {
        return this.interviewChk;
    }
    /**
     * 面接チェックステータス設定
     * 0:受験 1:免除 2:受験不可 3:未受験
     * @param interviewChk
     */
    public void setInterviewChk(String str) {
        this.interviewChk = str;
    }
    
    
    public String getCompanyCd() {
        if(isNull(this.companyCd)) return "";
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getEmpExpMonth() {
        if(isNull(this.empExpMonth)) return "";
        return empExpMonth;
    }
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    public String getEmpExpYear() {
        if(isNull(this.empExpYear)) return "";
        return empExpYear;
    }
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    public String getEntryPlaceCd() {
        if(isNull(this.entryPlaceCd)) return "";
        return entryPlaceCd;
    }
    public void setEntryPlaceCd(String entryPlaceCd) {
        this.entryPlaceCd = entryPlaceCd;
    }
    public String getExamNo() {
        if(isNull(this.examNo)) return "";
        return examNo;
    }
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    public String getJob() {
        if(isNull(this.job)) return "";
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getMiseCd1() {
        if(isNull(this.miseCd1)) return "";
        return miseCd1;
    }
    public void setMiseCd1(String miseCd1) {
        this.miseCd1 = miseCd1;
    }
    public String getMiseNameKj() {
        if(isNull(this.miseNameKj)) return "";
        return miseNameKj;
    }
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    public String getNote() {
        if(isNull(this.note)) return "";
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getOnerCd() {
        if(isNull(this.onerCd)) return "";
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public String getOnerNameKj() {
        if(isNull(this.onerNameKj)) return "";
        return onerNameKj;
    }
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    public String getPaExpMonth() {
        if(isNull(this.paExpMonth)) return "";
        return paExpMonth;
    }
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    public String getPaExpYear() {
        if(isNull(this.paExpYear)) return "";
        return paExpYear;
    }
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
    public String getSex() {
        if (isNull(sex)) sex = "0";
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getStaffId() {
        if(isNull(this.staffId)) return "";
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getStaffNameKna() {
        if(isNull(this.staffNameKna)) return "";
        return staffNameKna;
    }
    public void setStaffNameKna(String staffNameKna) {
        this.staffNameKna = staffNameKna;
    }
    public String getStateFlg() {
        return stateFlg;
    }
    public void setStateFlg(String stateFlg) {
        this.stateFlg = stateFlg;
    }
    public String getSub1Result() {
        if(isNull(this.sub1Result)) return "";
        return sub1Result;
    }
    public void setSub1Result(String sub1Result) {
        this.sub1Result = sub1Result;
    }
    public String getSub2Result() {
        if(isNull(this.sub2Result)) return "";
        return sub2Result;
    }
    public void setSub2Result(String sub2Result) {
        this.sub2Result = sub2Result;
    }
    public String getSub3Result() {
        if(isNull(this.sub3Result)) return "";
        return sub3Result;
    }
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }
    public String getStaffNameKj() {
        if(isNull(this.staffNameKj)) return "";
        return staffNameKj;
    }
    public void setStaffNameKj(String staffNameKj) {
        this.staffNameKj = staffNameKj;
    }
    public String getTotalLastKai() {
        if(isNull(this.totalLastKai)) return "";
        return totalLastKai;
    }
    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }
    public String getTotalLastYear() {
        if(isNull(this.totalLastYear)) return "";
        return totalLastYear;
    }
    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    public String getTotalResult() {
        if(isNull(this.totalResult)) return "";
        return totalResult;
    }
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    
    public String getEntryCd() {
        if(isNull(this.entryCd)) return "";
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryKai() {
        if(isNull(this.entryKai)) return "";
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public String getEntryYear() {
        if(isNull(this.entryYear)) return "";
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    
    
    /**
     * 能力チェックフラグを設定する
     * @return
     */
    public boolean isAbilityFlg() {
        return abilityFlg;
    }
    /**
     * 能力チェックフラグを設定する
     * @param abilityFlg
     */
    public void setAbilityFlg(boolean abilityFlg) {
        this.abilityFlg = abilityFlg;
    }
    /**
     * 面接チェックフラグを設定する
     * @return
     */
    public boolean isInterviewFlg() {
        return interviewFlg;
    }
    /**
     * 面接チェックフラグを設定する
     * @param interviewFlg
     */
    public void setInterviewFlg(boolean interviewFlg) {
        this.interviewFlg = interviewFlg;
    }
    /**
     * 筆記チェックフラグを設定する
     * @return
     */
    public boolean isExamFlg() {
        return examFlg;
    }
    /**
     * 筆記チェックフラグを設定する
     * @param examFlg
     */
    public void setExamFlg(boolean examFlg) {
        this.examFlg = examFlg;
    }
    
    
    public boolean isAbilityPastFlg() {
        return abilityPastFlg;
    }
    public void setAbilityPastFlg(boolean abilityPastFlg) {
        this.abilityPastFlg = abilityPastFlg;
    }
    public boolean isExamPastFlg() {
        return examPastFlg;
    }
    public void setExamPastFlg(boolean examPastFlg) {
        this.examPastFlg = examPastFlg;
    }
    public boolean isInterviewPastFlg() {
        return interviewPastFlg;
    }
    public void setInterviewPastFlg(boolean interviewPastFlg) {
        this.interviewPastFlg = interviewPastFlg;
    }
    

    public boolean isReEntryFlg() {
        return reEntryFlg;
    }
    public void setReEntryFlg(boolean reEntryFlg) {
        this.reEntryFlg = reEntryFlg;
    }
    public boolean isInsertFlg() {
        return insertFlg;
    }
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }

    public String getEntryCount() {
        if(isNull(this.entryCount)) return "";
        return entryCount;
    }
    public void setEntryCount(String entryCount) {
        this.entryCount = entryCount;
    }
    public String getReentryBaseYear() {
        if(isNull(this.reentryBaseYear)) return "";
        return reentryBaseYear;
    }
    public void setReentryBaseYear(String reentryBaseYear) {
        this.reentryBaseYear = reentryBaseYear;
    }
    public String getReentryFlgStr() {
        if(isNull(this.reentryFlgStr)) return "";
        return reentryFlgStr;
    }
    public void setReentryFlgStr(String reentryFlgStr) {
        this.reentryFlgStr = reentryFlgStr;
    }
    public String getSub3LastKai() {
        if(isNull(this.sub3LastKai)) return "";
        return sub3LastKai;
    }
    public void setSub3LastKai(String sub3LastKai) {
        this.sub3LastKai = sub3LastKai;
    }
    public String getSub3LastYear() {
        if(isNull(this.sub3LastYear)) return "";
        return sub3LastYear;
    }
    public void setSub3LastYear(String sub3LastYear) {
        this.sub3LastYear = sub3LastYear;
    }
    
    
    public String getResultFPgm() {
        if(isNull(this.resultFPgm)) return "";
        return resultFPgm;
    }
    public void setResultFPgm(String resultFPgm) {
        this.resultFPgm = resultFPgm;
    }
    public Timestamp getResultFTmp() {
        return resultFTmp;
    }
    public void setResultFTmp(Timestamp resultFTmp) {
        this.resultFTmp = resultFTmp;
    }
    public String getResultFUsr() {
        if(isNull(this.resultFUsr)) return "";
        return resultFUsr;
    }
    public void setResultFUsr(String resultFUsr) {
        this.resultFUsr = resultFUsr;
    }
    public String getResultLPgm() {
        if(isNull(this.resultLPgm)) return "";
        return resultLPgm;
    }
    public void setResultLPgm(String resultLPgm) {
        this.resultLPgm = resultLPgm;
    }
    public Timestamp getResultLTmp() {
        return resultLTmp;
    }
    public void setResultLTmp(Timestamp resultLTmp) {
        this.resultLTmp = resultLTmp;
    }
    public String getResultLUsr() {
        if(isNull(this.resultLUsr)) return "";
        return resultLUsr;
    }
    public void setResultLUsr(String resultLUsr) {
        this.resultLUsr = resultLUsr;
    }
    public String getStateFPgm() {
        if(isNull(this.stateFPgm)) return "";
        return stateFPgm;
    }
    public void setStateFPgm(String stateFPgm) {
        this.stateFPgm = stateFPgm;
    }
    public Timestamp getStateFTmp() {
        return stateFTmp;
    }
    public void setStateFTmp(Timestamp stateFTmp) {
        this.stateFTmp = stateFTmp;
    }
    public String getStateFUsr() {
        if(isNull(this.stateFUsr)) return "";
        return stateFUsr;
    }
    public void setStateFUsr(String stateFUsr) {
        this.stateFUsr = stateFUsr;
    }
    public String getStateLPgm() {
        if(isNull(this.stateLPgm)) return "";
        return stateLPgm;
    }
    public void setStateLPgm(String stateLPgm) {
        this.stateLPgm = stateLPgm;
    }
    public Timestamp getStateLTmp() {
        return stateLTmp;
    }
    public void setStateLTmp(Timestamp stateLTmp) {
        this.stateLTmp = stateLTmp;
    }
    public String getStateLUsr() {
        if(isNull(this.stateLUsr)) return "";
        return stateLUsr;
    }
    public void setStateLUsr(String stateLUsr) {
        this.stateLUsr = stateLUsr;
    }
    public String getSub1LastKai() {
        if(isNull(this.sub1LastKai)) return "";
        return sub1LastKai;
    }
    public void setSub1LastKai(String sub1LastKai) {
        this.sub1LastKai = sub1LastKai;
    }
    public String getSub1LastYear() {
        if(isNull(this.sub1LastYear)) return "";
        return sub1LastYear;
    }
    public void setSub1LastYear(String sub1LastYear) {
        this.sub1LastYear = sub1LastYear;
    }
    public String getSub2LastKai() {
        if(isNull(this.sub2LastKai)) return "";
        return sub2LastKai;
    }
    public void setSub2LastKai(String sub2LastKai) {
        this.sub2LastKai = sub2LastKai;
    }
    public String getSub2LastYear() {
        if(isNull(this.sub2LastYear)) return "";
        return sub2LastYear;
    }
    public void setSub2LastYear(String sub2LastYear) {
        this.sub2LastYear = sub2LastYear;
    }
    public String getEntryPlaceName() {
        if(isNull(this.entryPlaceName)) return "";
        return entryPlaceName;
    }
    public void setEntryPlaceName(String entryPlaceName) {
        this.entryPlaceName = entryPlaceName;
    }
    public String getBeforeFlg() {
        if(isNull(this.beforeFlg)) return "";
        return beforeFlg;
    }
    public void setBeforeFlg(String beforeFlg) {
        this.beforeFlg = beforeFlg;
    }
    public String getEntryNo() {
        return entryNo;
    }
    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }
    public String getEntryMsg() {
        return entryMsg;
    }
    public void setEntryMsg(String entryMsg) {
        this.entryMsg = entryMsg;
    }
    public String getAbilityChkMsg() {
        return abilityChkMsg;
    }
    public void setAbilityChkMsg(String abilityChkMsg) {
        this.abilityChkMsg = abilityChkMsg;
    }
    public String getExamChkMsg() {
        return examChkMsg;
    }
    public void setExamChkMsg(String examChkMsg) {
        this.examChkMsg = examChkMsg;
    }
    public String getInterviewChkMsg() {
        return interviewChkMsg;
    }
    public void setInterviewChkMsg(String interviewChkMsg) {
        this.interviewChkMsg = interviewChkMsg;
    }
    

}
