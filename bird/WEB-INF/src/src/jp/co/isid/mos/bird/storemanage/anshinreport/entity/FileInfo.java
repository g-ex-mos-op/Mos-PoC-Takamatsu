package jp.co.isid.mos.bird.storemanage.anshinreport.entity;

public class FileInfo {
    
    public static final String TABLE = "BM99DMMY";
    
    public static final String fileName_COLUMN = "FILE_NAME";
    
    public static final String fullFileName_COLUMN = "FULL_FILE_NAME";
    
    public static final String sortNo_COLUMN = "SORT_NO";
    
    /**
     * ファイル名称(表示用)
     */
    private String fileName;

    /**
     * ファイル名称(実ファイル名)
     */
    private String fullFileName;

    /**
     * ソート順
     */
    private String sortNo;
    
    /**
     * ファイル名称を取得します。
     * @return ファイル名称
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * ファイル名称を設定します。
     * @param fileName ファイル名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 実ファイル名称を取得します。
     * @return 実ファイル名称
     */
    public String getFullFileName() {
        return fullFileName;
    }

    /**
     * 実ファイル名称を設定します。
     * @param 実ファイル名称
     */
    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }
    
    /**
     * ソート順
     * @return sortNo を戻します。
     */
    public String getSortNo() {
        return sortNo;
    }
    /**
     * ソート順
     * @param sortNo を設定します。
     */
    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }
    
}
