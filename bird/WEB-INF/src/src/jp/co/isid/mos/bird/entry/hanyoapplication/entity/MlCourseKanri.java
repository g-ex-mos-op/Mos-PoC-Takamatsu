package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

public class MlCourseKanri {
    
    public static final String TABLE = "BR19MLCR";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String courseCd_COLUMN = "COURSE_CD";
    
    public static final String courseName_COLUMN = "COURSE_NAME";
    
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
     * コースコード
     */
    private String courseCd;
    
    /**
     * コース名称
     */
    private String courseName;
    
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
     * コースコードを取得します。
     * @return コースコード
     */
    public String getCourseCd() {
        return courseCd;
    }
    /**
     * コースコードを設定します。
     * @param courseCd コースコード
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }
    
    /**
     * コース名称を取得します。
     * @return コース名称
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * コース名称を設定します。
     * @param courseName コース名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
}
