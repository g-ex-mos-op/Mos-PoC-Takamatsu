package jp.co.isid.mos.bird.communication.docform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.entity.TrnInfoAccessControl;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity;

public class UIViewDocFormInfo implements TrnInfoAccessControl, FileAccessInfoEntity{

    public static final String TABLE = "BT03DOCM";

    public static final String regDate_COLUMN = "REG_DATE";

    public static final String seq_COLUMN = "SEQ";

    public static final String cateId_COLUMN = "CATE_ID";

    public static final String cateName_COLUMN = "CATE_NAME";

    public static final String subCateId_COLUMN = "SUB_CATE_ID";

    public static final String subCateName_COLUMN = "SUB_CATE_NAME";

    public static final String title_COLUMN = "TITLE";

    public static final String discription_COLUMN = "DISCRIPTION";

    public static final String pubDateFrom_COLUMN = "PUB_DATE_FROM";

    public static final String pubDateTo_COLUMN = "PUB_DATE_TO";

    public static final String pubUser_COLUMN = "PUB_USER";

    public static final String userNameKj_COLUMN = "USER_NAME_KJ";

    public static final String pugOrg_COLUMN = "PUB_ORG";

    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";

    public static final String fileName_COLUMN = "FILE_NAME";

    public static final String attachName1_COLUMN = "ATTACH_NAME1";

    public static final String attachName2_COLUMN = "ATTACH_NAME2";

    public static final String attachName3_COLUMN = "ATTACH_NAME3";

    public static final String attachFl1_COLUMN = "ATTACH_FL1";

    public static final String attachFl2_COLUMN = "ATTACH_FL2";

    public static final String attachFl3_COLUMN = "ATTACH_FL3";

    public static final String limitKbn_COLUMN = "LIMIT_KBN";

    public static final String limitKbnName_COLUMN = "LIMIT_KBN_NAME";

    public static final String sortSeq_COLUMN = "SORT_SEQ";

    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";

    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";

    public static final String miseFlg_COLUMN = "MISE_FLG";

    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";

    public static final String cateSortKey_COLUMN = "CATE_SORT_KEY";

    public static final String subCateSortKey_COLUMN = "SUB_CATE_SORT_KEY";

    private static final int KANREN_BUNSHO_FIRST_INDEX = 5;

    public static final String kanrenFileCnt_COLUMN     = "KRNF_CNT";
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
     * 説明
     */
    private String discription;

    /**
     * 掲載期間FROM
     */
    private String pubDateFrom;

    /**
     * 掲載期間TO
     */
    private String pubDateTo;

    /**
     * 登録者
     */
    private String pubUser;

    /**
     * ユーザ名称
     */
    private String userNameKj;

    /**
     * 登録元
     */
    private String pugOrg;

    /**
     * 登録元名称
     */
    private String pubOrgName;

    /**
     * ファイル名
     */
    private String fileName;

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
     * 情報制限区分
     */
    private String limitKbn;

    /**
     * 情報制限区分名
     */
    private String limitKbnName;

    /**
     * 表示順序
     */
    private String sortSeq;

    /**
     * 削除フラグ
     */
    private String sakujoFlg;

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

    /** 関連ファイル件数 */
    private int kanrenFileCnt;
    /**
     * 関連ファイルリスト
     */
    private List listKenrenFile;

    /**
     * カテゴリー ソートキー
     */
    private String cateSortKey;

    /**
     * サブカテゴリー ソートキー
     */
    private String subCateSortKey;
    /** 要約 */
    private String description;

    /**
     * 情報種別
     */
    private String infoShu;

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
     * 説明を取得します。
     * @return 説明
     */
    public String getDiscription() {
        if (discription == null || discription.trim().equals("")) {
            discription = "タイトル名をクリックするとダウンロードされます";
        }
        return discription;
    }
    /**
     * 説明を設定します。
     * @param discription 説明
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    /**
     * 掲載期間FROMを取得します。
     * @return 掲載期間FROM
     */
    public String getPubDateFrom() {
        return pubDateFrom;
    }
    /**
     * 掲載期間FROMを設定します。
     * @param pubDateFrom 掲載期間FROM
     */
    public void setPubDateFrom(String pubDateFrom) {
        this.pubDateFrom = pubDateFrom;
    }

    /**
     * 掲載期間TOを取得します。
     * @return 掲載期間TO
     */
    public String getPubDateTo() {
        return pubDateTo;
    }
    /**
     * 掲載期間TOを設定します。
     * @param pubDateTo 掲載期間TO
     */
    public void setPubDateTo(String pubDateTo) {
        this.pubDateTo = pubDateTo;
    }

    /**
     * 登録者を取得します。
     * @return 登録者
     */
    public String getPubUser() {
        return pubUser;
    }
    /**
     * 登録者を設定します。
     * @param pubUser 登録者
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
     * 登録元を取得します。
     * @return 登録元
     */
    public String getPugOrg() {
        return pugOrg;
    }
    /**
     * 登録元を設定します。
     * @param pugOrg 登録元
     */
    public void setPugOrg(String pugOrg) {
        this.pugOrg = pugOrg;
    }

    /**
     * 登録元名称を取得します。
     * @return 登録元名称
     */
    public String getPubOrgName() {
    	return (pubOrgName == null) ? "" : pubOrgName.replaceAll("[　*| *]*$", "");
    }
    /**
     * 登録元名称を設定します。
     * @param pubOrgName 登録元名称
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
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
     * 情報制限区分を取得します。
     * @return 情報制限区分
     */
    public String getLimitKbn() {
        return limitKbn;
    }
    /**
     * 情報制限区分を設定します。
     * @param limitKbn 情報制限区分
     */
    public void setLimitKbn(String limitKbn) {
        this.limitKbn = limitKbn;
    }

    /**
     * 情報制限区分名を取得します。
     * @return 情報制限区分名
     */
    public String getLimitKbnName() {
        return limitKbnName;
    }
    /**
     * 情報制限区分名を設定します。
     * @param limitKbnName 情報制限区分名
     */
    public void setLimitKbnName(String limitKbnName) {
        this.limitKbnName = limitKbnName;
    }

    /**
     * 表示順序を取得します。
     * @return 表示順序
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * 表示順序を設定します。
     * @param sortSeq 表示順序
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
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

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * ダウンロードファイルリストを取得します。
     * @return ダウンロードファイルリスト
     */
    public List getListDownloadFile() {
        List list = new ArrayList();

        if (!isNull(getAttachName1())) {
            SelectItem item  =new SelectItem("2", getAttachName1());
            list.add(item);
        }
        if (!isNull(getAttachName2())) {
            SelectItem item  =new SelectItem("3", getAttachName2());
            list.add(item);
        }
        if (!isNull(getAttachName3())) {
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
    public String getCateSortKey() {
        return cateSortKey;
    }
    public void setCateSortKey(String cateSortKey) {
        this.cateSortKey = cateSortKey;
    }
    public String getSubCateSortKey() {
        return subCateSortKey;
    }
    public void setSubCateSortKey(String subCateSortKey) {
        this.subCateSortKey = subCateSortKey;
    }
    public String getInfoShu() {
        return infoShu;
    }
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity#getDescription()
	 */
	public String getDescription() {
		// TODO 自動生成されたメソッド・スタブ
		return description;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description= description;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity#getMatchingFileName()
	 */
	public String getMatchingFileName() {
        return getRegDate() + "_" + getSeq() + "_1_" + getFileName();
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.entity.FileAccessInfoEntity#setMatchingFileName(java.lang.String)
	 */
	public void setMatchingFileName(String fileName) {
	}
	/**
	 * @return クラス変数kanrenFileCnt を戻します。
	 */
	public int getKanrenFileCnt() {
		return kanrenFileCnt;
	}
	/**
	 * @param kanrenFileCnt を クラス変数kanrenFileCntへ設定します。
	 */
	public void setKanrenFileCnt(int kanrenFileCnt) {
		this.kanrenFileCnt = kanrenFileCnt;
	}
}