package jp.co.isid.mos.bird.storemanage.anshinreport.entity;

import java.util.List;

public class ResultInfo {
    
    public static final String TABLE = "BM99DMMY";
    
    public static final String fileList_COLUMN = "FILE_LIST";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseName_COLUMN = "MISE_NAME";
    
    /**
     * ファイル情報リスト
     */
    private List fileList;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseName;

    /**
     * 実施年度
     */
   private String jissiNendo;
    
    /**
     * ファイル情報リストを取得します。
     * @return ファイル情報リスト
     */
    public List getFileList() {
        return fileList;
    }
    /**
     * ファイル情報リストを設定します。
     * @param fileList ファイル情報リスト
     */
    public void setFileList(List fileList) {
        this.fileList = fileList;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店名称を設定します。
     * @param miseName 店名称
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }
    /**
     * 実施年度
     * @return jissiNendo を戻します。
     */
    public String getJissiNendo() {
        return jissiNendo;
    }
    /**
     * 実施年度
     * @param jissiNendo を設定します。
     */
    public void setJissiNendo(String jissiNendo) {
        this.jissiNendo = jissiNendo;
    }
    
}
