package jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity;

/**
 * コースリスト
 * 
 * @author xkinu
 *
 */
public class CodCourse {
    
    public static final String TABLE = "BC05KCOM";
    
    public static final String courseCd_COLUMN = "COURSE_CD";
    
    public static final String courseName_COLUMN = "COURSE_NAME";
    
    /**
     * 管理会社企業コード
     */
    private String courseCd;
    
    /**
     * 管理会社企業名称
     */
    private String courseName;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCourseCd() {
        return courseCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param courseCd 管理会社企業コード
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }
    
    /**
     * 管理会社企業名称を取得します。
     * @return 管理会社企業名称
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * 管理会社企業名称を設定します。
     * @param courseName 管理会社企業名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
}
