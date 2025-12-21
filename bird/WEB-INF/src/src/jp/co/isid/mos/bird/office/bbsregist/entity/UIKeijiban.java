package jp.co.isid.mos.bird.office.bbsregist.entity;

import java.sql.Timestamp;

public class UIKeijiban {
    
    public static final String TABLE = "BT06KBAN";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String cateId_COLUMN = "CATE_ID";
    
    public static final String cateName_COLUMN = "CATE_NAME";
    
    public static final String subCateId_COLUMN = "SUB_CATE_ID";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String message_COLUMN = "MESSAGE";
    
    public static final String attachName1_COLUMN = "ATTACH_NAME1";
    
    public static final String attachName2_COLUMN = "ATTACH_NAME2";
    
    public static final String attachName3_COLUMN = "ATTACH_NAME3";
    
    public static final String attachFl1_COLUMN = "ATTACH_FL1";
    
    public static final String attachFl2_COLUMN = "ATTACH_FL2";
    
    public static final String attachFl3_COLUMN = "ATTACH_FL3";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstUserName_COLUMN = "FIRST_USER_NAME_KJ";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastUserName_COLUMN = "LAST_USER_NAME_KJ";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
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
     * カテゴリー名
     */
    private String cateName;
    
    /**
     * サブカテゴリID
     */
    private String subCateId;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * メッセージ
     */
    private String message;
    
    /**
     * 添付タイトル１
     */
    private String attachName1;
    
    /**
     * 添付タイトル２
     */
    private String attachName2;
    
    /**
     * 添付タイトル３
     */
    private String attachName3;
    
    /**
     * 添付ファイル名１
     */
    private String attachFl1;
    
    /**
     * 添付ファイル名２
     */
    private String attachFl2;
    
    /**
     * 添付ファイル名３
     */
    private String attachFl3;
    
    /**
     * 削除フラグ
     */
    private String sakujoFlg;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録ユーザー名
     */
    private String firstUserName;
    
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
     * 修正ユーザー名
     */
    private String lastUserName;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
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
        return convertString(cateId);
    }
    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    
    /**
     * カテゴリー名を取得します。
     * @return カテゴリー名
     */
    public String getCateName() {
        return convertString(cateName);
    }
    /**
     * カテゴリー名を設定します。
     * @param cateName カテゴリー名
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    /**
     * サブカテゴリIDを取得します。
     * @return サブカテゴリID
     */
    public String getSubCateId() {
        return convertString(subCateId);
    }
    /**
     * サブカテゴリIDを設定します。
     * @param subCateId サブカテゴリID
     */
    public void setSubCateId(String subCateId) {
        this.subCateId = subCateId;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return convertString(title);
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * メッセージを取得します。
     * @return メッセージ
     */
    public String getMessage() {
        return convertString(message);
    }
    /**
     * メッセージを設定します。
     * @param message メッセージ
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 添付タイトル１を取得します。
     * @return 添付タイトル１
     */
    public String getAttachName1() {
        return convertString(attachName1);
    }
    /**
     * 添付タイトル１を設定します。
     * @param attachName1 添付タイトル１
     */
    public void setAttachName1(String attachName1) {
        this.attachName1 = attachName1;
    }
    
    /**
     * 添付タイトル２を取得します。
     * @return 添付タイトル２
     */
    public String getAttachName2() {
        return convertString(attachName2);
    }
    /**
     * 添付タイトル２を設定します。
     * @param attachName2 添付タイトル２
     */
    public void setAttachName2(String attachName2) {
        this.attachName2 = attachName2;
    }
    
    /**
     * 添付タイトル３を取得します。
     * @return 添付タイトル３
     */
    public String getAttachName3() {
        return convertString(attachName3);
    }
    /**
     * 添付タイトル３を設定します。
     * @param attachName3 添付タイトル３
     */
    public void setAttachName3(String attachName3) {
        this.attachName3 = attachName3;
    }
    
    /**
     * 添付ファイル名１を取得します。
     * @return 添付ファイル名１
     */
    public String getAttachFl1() {
        return convertString(attachFl1);
    }
    /**
     * 添付ファイル名１を設定します。
     * @param attachFl1 添付ファイル名１
     */
    public void setAttachFl1(String attachFl1) {
        this.attachFl1 = attachFl1;
    }
    
    /**
     * 添付ファイル名２を取得します。
     * @return 添付ファイル名２
     */
    public String getAttachFl2() {
        return convertString(attachFl2);
    }
    /**
     * 添付ファイル名２を設定します。
     * @param attachFl2 添付ファイル名２
     */
    public void setAttachFl2(String attachFl2) {
        this.attachFl2 = attachFl2;
    }
    
    /**
     * 添付ファイル名３を取得します。
     * @return 添付ファイル名３
     */
    public String getAttachFl3() {
        return convertString(attachFl3);
    }
    /**
     * 添付ファイル名３を設定します。
     * @param attachFl3 添付ファイル名３
     */
    public void setAttachFl3(String attachFl3) {
        this.attachFl3 = attachFl3;
    }
    
    /**
     * 削除フラグを取得します。
     * @return 削除フラグ
     */
    public String getSakujoFlg() {
        return sakujoFlg;
    }
    /**
     * 削除フラグを設定します。
     * @param sakujoFlg 削除フラグ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
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
     * 登録ユーザー名を取得します。
     * @return 登録ユーザー名
     */
    public String getFirstUserName() {
        return firstUserName;
    }
    /**
     * 登録ユーザー名を設定します。
     * @param firstUserName 登録ユーザー名
     */
    public void setFirstUserName(String firstUserName) {
        this.firstUserName = firstUserName;
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
     * 修正ユーザー名を取得します。
     * @return 修正ユーザー名
     */
    public String getLastUserName() {
        return lastUserName;
    }
    /**
     * 修正ユーザー名を設定します。
     * @param lastUserName 修正ユーザー名
     */
    public void setLastUserName(String lastUserName) {
        this.lastUserName = lastUserName;
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
     * 添付ファイル１の存在チェック
     * @return true:あり、false:なし
     */
    public boolean isExistFile1(){
        if(getAttachFl1() != null && getAttachFl1().trim().length() != 0){
            return true;
        }
        return false;
    }
    
    /**
     * 添付ファイル２の存在チェック
     * @return true:あり、false:なし
     */
    public boolean isExistFile2(){
        if(getAttachFl2() != null && getAttachFl2().trim().length() != 0){
            return true;
        }
        return false;
    }

    /**
     * 添付ファイル３の存在チェック
     * @return true:あり、false:なし
     */
    public boolean isExistFile3(){
        if(getAttachFl3() != null && getAttachFl3().trim().length() != 0){
            return true;
        }
        return false;
    }

    /**
     * null文字、スペースをブランクに変換
     * @param str 対象
     * @return 変換後
     */
    private String convertString(String str){
        if(str == null){
            return "";
        }
        return str.trim();
    }
}
