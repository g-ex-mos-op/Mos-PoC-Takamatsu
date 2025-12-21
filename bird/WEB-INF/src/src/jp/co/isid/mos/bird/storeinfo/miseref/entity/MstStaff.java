package jp.co.isid.mos.bird.storeinfo.miseref.entity;

import java.math.BigDecimal;

public class MstStaff {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String staffLNameKna_COLUMN = "STAFF_L_NAME_KNA";
    
    public static final String staffFNameKna_COLUMN = "STAFF_F_NAME_KNA";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String situationKbn_COLUMN = "SITUATION_KBN";
    
    public static final String situationKbnName_COLUMN = "SITUATION_KBN_NAME";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    
    public static final String complePoint_COLUMN = "COMPLE_POINT";
    
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 店コード
     */
    private String miseCd1;
    
    /**
     * スタッフ氏　（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名　（漢字）
     */
    private String staffFNameKj;
    
    /**
     * スタッフ氏　（カナ）
     */
    private String staffLNameKna;
    
    /**
     * スタッフ名　（カナ）
     */
    private String staffFNameKna;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    /**
     * 活動状況区分名称
     */
    private String situationKbnName;
    
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
     * 修了（予定）コース状況
     */
    private String courseStatus;
    
    /**
     * 修了（予定）コース修了年月
     */
    private String compleCourseDt;
    
    /**
     * 点数
     */
    private BigDecimal complePoint;
    
    /**
     * 修了（予定）コースコード
     */
    private String compleCourseCd;
    
    /**
     * 修了（予定）コース名称
     */
    private String compleCourseName;
    
    /**
     * タイトル
     */
    private String entryTitle;
    
    /**
     * スタッフ名　(苗字 + 名前）
     */
    private String staffName;
    
    
    /**
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return convString(staffId);
    }
    /**
     * スタッフIDを設定します。
     * @param staffId スタッフID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
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
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd1() {
        return convString(miseCd1);
    }
    /**
     * 店コードを設定します。
     * @param miseCd1 店コード
     */
    public void setMiseCd1(String miseCd1) {
        this.miseCd1 = miseCd1;
    }
    
    /**
     * スタッフ氏　（漢字）を取得します。
     * @return スタッフ氏　（漢字）
     */
    public String getStaffLNameKj() {
        return convString(staffLNameKj);
    }
    /**
     * スタッフ氏　（漢字）を設定します。
     * @param staffLNameKj スタッフ氏　（漢字）
     */
    public void setStaffLNameKj(String staffLNameKj) {
        this.staffLNameKj = staffLNameKj;
    }
    
    /**
     * スタッフ名　（漢字）を取得します。
     * @return スタッフ名　（漢字）
     */
    public String getStaffFNameKj() {
        return convString(staffFNameKj);
    }
    /**
     * スタッフ名　（漢字）を設定します。
     * @param staffFNameKj スタッフ名　（漢字）
     */
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }    
    /**
     * スタッフ氏　（カナ）を取得します。
     * @return スタッフ氏　（カナ）
     */
    public String getStaffLNameKna() {
        return convString(staffLNameKna);
    }
    /**
     * スタッフ氏　（カナ）を設定します。
     * @param staffLNameKna スタッフ氏　（カナ）
     */
    public void setStaffLNameKna(String staffLNameKna) {
        this.staffLNameKna = staffLNameKna;
    }
    
    /**
     * スタッフ名　（カナ）を取得します。
     * @return スタッフ名　（カナ）
     */
    public String getStaffFNameKna() {
        return convString(staffFNameKna);
    }
    /**
     * スタッフ名　（カナ）を設定します。
     * @param staffFNameKna スタッフ名　（カナ）
     */
    public void setStaffFNameKna(String staffFNameKna) {
        this.staffFNameKna = staffFNameKna;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return convString(sex);
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * 活動状況区分を取得します。
     * @return 活動状況区分
     */
    public String getSituationKbn() {
        return convString(situationKbn);
    }
    /**
     * 
     * 0:活動中 1:休職中 2:退職
     * 活動状況区分を設定します。
     * @param situationKbn 活動状況区分
     */
    public void setSituationKbn(String situationKbn) {        
        this.situationKbn = situationKbn;
    }
    
    /**
     * [表示用]
     * 活動状況区分名称を取得します。
     * @return 活動状況区分名称
     */
    public String getSituationKbnName() {
        situationKbnName = "";
        
        if ("0".equals(getSituationKbn())) {
            situationKbnName = "活動中";
        }
        else if ("1".equals(getSituationKbn())) {
            situationKbnName = "休職中";
            
        }else if ("2".equals(getSituationKbn())) {
            situationKbnName = "退職";
        }
        return situationKbnName;
    }
    /**
     * [表示用]
     * 活動状況区分名称を設定します。
     * @param situationKbnName 活動状況区分名称
     */
    public void setSituationKbnName(String situationKbnName) {
        this.situationKbnName = situationKbnName;
    }
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return convString(entryCd);
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
        return convString(entryYear);
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
        entryKai = convString(entryKai);
        
        BigDecimal decEntryKai = new BigDecimal(entryKai);
        entryKai = String.valueOf(decEntryKai);
        
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
     * 修了（予定）コース状況を取得します。
     * @return 修了（予定）コース状況
     */
    public String getCourseStatus() {
        return convString(courseStatus);
    }
    /**
     * 修了（予定）コース状況を設定します。
     * @param courseStatus 修了（予定）コース状況
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    
    /**
     * 修了（予定）コース修了年月を取得します。
     * @return 修了（予定）コース修了年月
     */
    public String getCompleCourseDt() {
        return convString(compleCourseDt);
    }
    /**
     * 修了（予定）コース修了年月を設定します。
     * @param compleCourseDt 修了（予定）コース修了年月
     */
    public void setCompleCourseDt(String compleCourseDt) {
        this.compleCourseDt = compleCourseDt;
    }
    
    /**
     * 点数を取得します。
     * @return 点数
     */
    public BigDecimal getComplePoint() {
        return complePoint;
    }
    /**
     * 点数を設定します。
     * @param complePoint 点数
     */
    public void setComplePoint(BigDecimal complePoint) {
        this.complePoint = complePoint;
    }
    
    /**
     * 修了（予定）コースコードを取得します。
     * @return 修了（予定）コースコード
     */
    public String getCompleCourseCd() {
        return convString(compleCourseCd);
    }
    /**
     * 修了（予定）コースコードを設定します。
     * @param compleCourseCd 修了（予定）コースコード
     */
    public void setCompleCourseCd(String compleCourseCd) {
        this.compleCourseCd = compleCourseCd;
    }
    
    /**
     * 修了（予定）コース名称を取得します。
     * @return 修了（予定）コース名称
     */
    public String getCompleCourseName() {
        return convString(compleCourseName);
    }
    /**
     * 修了（予定）コース名称を設定します。
     * @param compleCourseName 修了（予定）コース名称
     */
    public void setCompleCourseName(String compleCourseName) {
        this.compleCourseName = compleCourseName;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getEntryTitle() {
        return convString(entryTitle);
    }
    /**
     * タイトルを設定します。
     * @param entryTitle タイトル
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
        
    /**
     * [表示用]
     * スタッフ名　(苗字 + 名前）を取得します。
     * @return スタッフ名　(苗字 + 名前）
     */
    public String getStaffName() {
        staffName = getStaffLNameKj() + getStaffFNameKj();
        return staffName;
    }
    /**
     * [表示用]
     * スタッフ名　(苗字 + 名前）を設定します。
     * @param staffName スタッフ名　(苗字 + 名前）
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
