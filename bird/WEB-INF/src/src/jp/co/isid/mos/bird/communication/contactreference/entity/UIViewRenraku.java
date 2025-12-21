package jp.co.isid.mos.bird.communication.contactreference.entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.entity.TrnInfoAccessControl;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

public class UIViewRenraku implements DownloadDto, TrnInfoAccessControl {

    public static final String TABLE = "BT01INFM";

    public static final String regDate_COLUMN = "REG_DATE";

    public static final String seq_COLUMN = "SEQ";

    public static final String cateId_COLUMN = "CATE_ID";

    public static final String cateName_COLUMN = "CATE_NAME";

    public static final String title_COLUMN = "TITLE";

    public static final String pubDate_COLUMN = "PUB_DATE";

    public static final String pubUser_COLUMN = "PUB_USER";

    public static final String userNameKj_COLUMN = "USER_NAME_KJ";

    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";

    public static final String pubOrg_COLUMN = "PUB_ORG";

    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";

    public static final String message_COLUMN = "MESSAGE";

    public static final String attachName1_COLUMN = "ATTACH_NAME1";

    public static final String attachName2_COLUMN = "ATTACH_NAME2";

    public static final String attachName3_COLUMN = "ATTACH_NAME3";

    public static final String attachFl1_COLUMN = "ATTACH_FL1";

    public static final String attachFl2_COLUMN = "ATTACH_FL2";

    public static final String attachFl3_COLUMN = "ATTACH_FL3";

    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";

    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";

    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";

    public static final String miseFlg_COLUMN = "MISE_FLG";

    private static final int KANREN_BUNSHO_FIRST_INDEX = 5;

    //ダウンロードファイルプルダウンのインデックス
    private int downloadFileIndex;

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
     * カテゴリ名
     */
    private String cateName;

    /**
     * タイトル
     */
    private String title;

    /**
     * 発信日
     */
    private String pubDate;

    /**
     * 発信者
     */
    private String pubUser;

    /**
     * ユーザ名称
     */
    private String userNameKj;

    /**
     * 企業コード
     */
    private String rCompanyCd;

    /**
     * 発信元
     */
    private String pubOrg;

    /**
     * 部門名称
     */
    private String pubOrgName;

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
     * 業態区分
     */
    private String gyotaiKbn;

    /**
     * 個別設定フラグ
     */
    private String kobetsuFlg;

    /**
     * 個店別設定フラグ
     */
    private String miseFlg;

    /**
     * 有効フラグ
     */
    private boolean enabled;

    /**
     * 添付ファイルインデックス
     */
    private int fileIndex;

    /**
     *  関連ファイルリストを取得します。
     */
    private List listKenrenFile;

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
     * カテゴリ名を取得します。
     * @return カテゴリ名
     */
    public String getCateName() {
        return cateName;
    }
    /**
     * カテゴリ名を設定します。
     * @param cateName カテゴリ名
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
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
     * 発信日を取得します。
     * @return 発信日
     */
    public String getPubDate() {
        return pubDate;
    }
    /**
     * 発信日を設定します。
     * @param pubDate 発信日
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 発信者を取得します。
     * @return 発信者
     */
    public String getPubUser() {
        return pubUser;
    }
    /**
     * 発信者を設定します。
     * @param pubUser 発信者
     */
    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }

    /**
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNameKj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }

    /**
     * 発信元を取得します。
     * @return 発信元
     */
    public String getPubOrg() {
        return pubOrg;
    }
    /**
     * 発信元を設定します。
     * @param pubOrg 発信元
     */
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }

    /**
     * 部門名称を取得します。
     * @return 部門名称
     */
    public String getPubOrgName() {
        return (pubOrgName == null) ? "" : pubOrgName.replaceAll("[　*| *]*$", "");
    }
    /**
     * 部門名称を設定します。
     * @param pubOrgName 部門名称
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }

    /**
     * メッセージを取得します。
     * @return メッセージ
     */
    public String getMessage() {
        return message;
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
        return attachName1;
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
        return attachName2;
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
        return attachName3;
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
        return attachFl1;
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
        return attachFl2;
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
        return attachFl3;
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
     * 有効フラグを取得します。
     * @return 有効フラグ
     */
    public boolean isEnabled() {
        return enabled;
    }
    /**
     * 有効フラグを設定します。
     * @param enabled 有効フラグ
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 添付ファイルインデックスを取得します。
     * @return 添付ファイルインデックス
     */
    public int getFileIndex() {
        return fileIndex;
    }
    /**
     * 添付ファイルインデックスを設定します。
     * @param fileIndex 添付ファイルインデックス
     */
    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public boolean isExistAttachFile1() {
        return getAttachFl1() != null && getAttachFl1().trim().length() > 0;
    }
    public boolean isExistAttachFile2() {
        return getAttachFl2() != null && getAttachFl2().trim().length() > 0;
    }
    public boolean isExistAttachFile3() {
        return getAttachFl3() != null && getAttachFl3().trim().length() > 0;
    }

    public boolean isExistAttachFile() {
        return isExistAttachFile1() || isExistAttachFile2() || isExistAttachFile3() || getListDownloadFileSize() != 0;
    }


    /**
     * ダウンロードファイルリストを取得します。
     * @return ダウンロードファイルリスト
     */
    public List getListDownloadFile() {
        List list = new ArrayList();

        if (isExistAttachFile1()) {
            SelectItem item  =new SelectItem("2", getAttachName1());
            list.add(item);
        }
        if (isExistAttachFile2()) {
            SelectItem item  =new SelectItem("3", getAttachName2());
            list.add(item);
        }
        if (isExistAttachFile3()) {
            SelectItem item  =new SelectItem("4", getAttachName3());
            list.add(item);
        }
        for(int i = 0; listKenrenFile != null && i < listKenrenFile.size(); i++){
            UIDocSearch entityKanren = (UIDocSearch) listKenrenFile.get(i);
            SelectItem item  =new SelectItem(String.valueOf(KANREN_BUNSHO_FIRST_INDEX + i), entityKanren.getTitle());
            list.add(item);
        }

        return list;
    }

    /**
     * 関連ファイルリストのサイズを取得します。
     * @return 関連ファイルリストのサイズ
     */
    public int getListDownloadFileSize() {
        if(getListDownloadFile() == null){
            return 0;
        }
        return getListDownloadFile().size();
    }


    /**
     * 関連ファイルリストを取得します。
     * @return listKenrenFile 関連ファイルリスト
     */
    public List getListKenrenFile() {
        return listKenrenFile;
    }

    /**
     * 関連ファイルリストを設定します。
     * @param 関連ファイルリスト
     */
    public void setListKenrenFile(List listKenrenFile) {
        this.listKenrenFile = listKenrenFile;
    }


    /**
     * ダウンロードファイルのプルダウンのインデックスを取得します。
     * @return ダウンロードファイルのプルダウンのインデックス
     */
    public int getDownloadFileIndex() {
        return downloadFileIndex;
    }

    /**
     * ダウンロードファイルのプルダウンのインデックスを設定します。
     * @param downloadFileIndex ダウンロードファイルのプルダウンのインデックス
     */
    public void setDownloadFileIndex(int downloadFileIndex) {
        this.downloadFileIndex = downloadFileIndex;
    }

}
