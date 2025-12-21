package jp.co.isid.mos.bird.config.categoryregist.entity;

import java.sql.Timestamp;

public class MstSubCategoryInfo implements CategoryRegistEntity {
    
    public static final String TABLE = "BM17SBCT";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String infoShu_COLUMN = "INFO_SHU";
    
    public static final String cateId_COLUMN = "CATE_ID";
    
    public static final String subCateId_COLUMN = "SUB_CATE_ID";
    
    public static final String newCateName_COLUMN = "SUB_CATE_NAME";
    
    public static final String oldCateName_COLUMN = "OLD_SUB_CATE_NAME";
    
    public static final String sortKey_COLUMN = "SORT_KEY";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 情報種別
     */
    private String infoShu;
    
    /**
     * カテゴリID
     */
    private String cateId;
    
    /**
     * サブカテゴリID
     */
    private String subCateId;
    
    /**
     * サブカテゴリ名
     */
    private String newCateName;
    
    /**
     * 変更前サブカテゴリ名
     */
    private String oldCateName;
    
    /**
     * ソートキー
     */
    private String sortKey;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * 選択フラグ
     */
    private boolean chkFlg;
    
    /**
     * 新規フラグ
     */
    private boolean insertFlg;
    
    /**
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * 情報種別を設定します。
     * @param infoShu 情報種別
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
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
     * サブカテゴリ名を取得します。
     * @return サブカテゴリ名
     */
    public String getNewCateName() {
        return newCateName;
    }
    /**
     * サブカテゴリ名を設定します。
     * @param newCateName サブカテゴリ名
     */
    public void setNewCateName(String newCateName) {
        this.newCateName = newCateName;
    }
    
    /**
     * 変更前サブカテゴリ名を取得します。
     * @return 変更前サブカテゴリ名
     */
    public String getOldCateName() {
        return oldCateName;
    }
    /**
     * 変更前サブカテゴリ名を設定します。
     * @param oldCateName 変更前サブカテゴリ名
     */
    public void setOldCateName(String oldCateName) {
        this.oldCateName = oldCateName;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean isChkFlg() {
        return chkFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param chkFlg 選択フラグ
     */
    public void setChkFlg(boolean chkFlg) {
        this.chkFlg = chkFlg;
    }
    
    /**
     * 新規フラグを取得します。
     * @return 新規フラグ
     */
    public boolean isInsertFlg() {
        return insertFlg;
    }
    /**
     * 新規フラグを設定します。
     * @param insertFlg 新規フラグ
     */
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }
    public String getSortKey() {
        return sortKey == null ? "" : sortKey;
    }
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
}