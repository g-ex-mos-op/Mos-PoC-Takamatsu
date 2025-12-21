package jp.co.isid.mos.bird.communication.documentdownload.entity;

import jp.co.isid.mos.bird.common.entity.TrnInfoAccessControl;

/**
 * フォームダウンロード情報
 * @author xytamura
 */
public class UICateList implements TrnInfoAccessControl {
    
    public static final String TABLE = "BT03DOCM";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String cateId_COLUMN = "CATE_ID";
    
    public static final String cateName_COLUMN = "CATE_NAME";
    
    public static final String subCateId_COLUMN = "SUB_CATE_ID";
    
    public static final String subCateName_COLUMN = "SUB_CATE_NAME";
    
    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";
    
    public static final String miseFlg_COLUMN = "MISE_FLG";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String cateSortKey_COLUMN = "CATE_SORT_KEY";
    
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
     * 個別設定フラグ
     */
    private String kobetsuFlg;
    
    /**
     * 個店別設定フラグ
     */
    private String miseFlg;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    private String cateSortKey;
    
    /**
     * 登録日を取得します。
     * @return 登録日
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * 登録日を設定します。
     * @param regDate 登録日
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
     * 個別設定フラグを取得します。
     * @return 個別設定フラグ
     */
    public String getKobetsuFlg() {
        return kobetsuFlg;
    }
    /**
     * 個別設定フラグを設定します。
     * @param kobetsuFlg 個別設定フラグ
     */
    public void setKobetsuFlg(String kobetsuFlg) {
        this.kobetsuFlg = kobetsuFlg;
    }
    
    /**
     * 個店別設定フラグを取得します。
     * @return 個店別設定フラグ
     */
    public String getMiseFlg() {
        return miseFlg;
    }
    /**
     * 個店別設定フラグを設定します。
     * @param miseFlg 個店別設定フラグ
     */
    public void setMiseFlg(String miseFlg) {
        this.miseFlg = miseFlg;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    public String getCateSortKey() {
        return cateSortKey;
    }
    public void setCateSortKey(String cateSortKey) {
        this.cateSortKey = cateSortKey;
    }
    public String getSubCateId() {
        return subCateId;
    }
    public void setSubCateId(String subCateId) {
        this.subCateId = subCateId;
    }
    public String getSubCateName() {
        return subCateName;
    }
    public void setSubCateName(String subCateName) {
        this.subCateName = subCateName;
    }
}