package jp.co.isid.mos.bird.common.entity;

public class UIDocSearch {
    
//    public static final String TABLE = "BT02NTCM";
    
    public static final String rowNum_COLUMN       = "ROW_NUM";
    public static final String infoShu_COLUMN     = "INFO_SHU";
    public static final String regdate_COLUMN     = "REG_DATE";
    public static final String seq_COLUMN         = "SEQ";
    public static final String cateId_COLUMN      = "CATE_ID";
    public static final String cateName_COLUMN    = "CATE_NAME";
    public static final String subCateId_COLUMN   = "SUB_CATE_ID";
    public static final String subCateName_COLUMN = "SUB_CATE_NAME";
    public static final String title_COLUMN       = "TITLE";
    public static final String fileName_COLUMN    = "FILE_NAME";
    
    /**
     * 行番号
     */
    private String rowNum;
    /**
     * 登録日
     */
    private String regDate;
    
    /**
     * シーケンス番号
     */
    private String seq;
    
    /**
     * カテゴリID
     */
    private String cateId;
    
    /**
     * カテゴリ名称
     */
    private String cateName;
    
    /**
     * サブカテゴリID
     */
    private String subCateId;
    
    /**
     * サブカテゴリ名称
     */
    private String subCateName;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * ファイル名
     */
    private String fileName;
    /**
     * 選択状態
     */
    private boolean checkedDoc;
    /**
     * タイプ（INFO_SHU）
     */
    private String infoShu;

    /**
     * 行番号を取得します。
     * @return 行番号
     */
    public String getRowNum() {
        return rowNum;
    }
    /**
     * 行番号を設定します。
     * @param rowNum 行番号
     */
    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }
    /**
     * 登録日を取得します。
     * @return 登録日
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * 登録日を設定します。
     * @param regdate 登録日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * シーケンス番号を取得します。
     * @return シーケンス番号
     */
    public String getSeq() {
        return seq;
    }
    /**
     * シーケンス番号を設定します。
     * @param seq シーケンス番号
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId() {
        return cateId;
    }
    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    
    /**
     * カテゴリ名称を取得します。
     * @return カテゴリ名称
     */
    public String getCateName() {
        return cateName;
    }
    /**
     * カテゴリ名称を設定します。
     * @param cateName カテゴリ名称
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    /**
     * サブカテゴリIDを取得します。
     * @return サブカテゴリID
     */
    public String getSubCateId() {
        return subCateId;
    }
    /**
     * サブカテゴリIDを設定します。
     * @param subCateId サブカテゴリID
     */
    public void setSubCateId(String subCateId) {
        this.subCateId = subCateId;
    }
    
    /**
     * サブカテゴリ名称を取得します。
     * @return サブカテゴリ名称
     */
    public String getSubCateName() {
        return subCateName;
    }
    /**
     * サブカテゴリ名称を設定します。
     * @param subCateName サブカテゴリ名称
     */
    public void setSubCateName(String subCateName) {
        this.subCateName = subCateName;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * ファイル名を取得します。
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * ファイル名を設定します。
     * @param fileName ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * 選択状態を取得します。
     * @return 選択状態
     */
    public boolean isCheckedDoc() {
        return checkedDoc;
    }
    /**
     * 選択状態を設定します。
     * @param checkedGyotai 選択状態
     */
    public void setCheckedDoc(boolean checkedDoc) {
        this.checkedDoc = checkedDoc;
    }
    /**
     * タイプ（INFO_SHU）を取得します。
     * @return タイプ（INFO_SHU）
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * タイプ（INFO_SHU）を設定します。
     * @param infoShu タイプ（INFO_SHU）
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }

}
