package jp.co.isid.mos.bird.inforegist.documentregist.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class UIBunshoInfo {

    public static final String TABLE = "BT03DOCM";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

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

    public static final String pubOrg_COLUMN = "PUB_ORG";

    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";

    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";

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

    public static final String bumonCd_COLUMN = "BUMON_NAME";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String subCateCount_COLUMN = "SUB_CATE_COUNT";

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
    private String pubOrg;

    /**
     * 登録元名
     */
    private String pubOrgName;

    /**
     * 企業コード
     */
    private String rCompanyCd;

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
     * サブカテゴリの件数
     */
    private int subCateCount;

    /**
	 * 添付ファイル選択インデックス
     */
    private int downloadAttachIndex;

    /**
     * 部門コード
     */
    private String bumonName;

    /**
     * 登録日を取得します。
     * @return 登録日
     */
    public String getRegDate() {
        return convString(regDate);
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
        return convString(seq);
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
        return convString(cateId);
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
        return convString(cateName);
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
        return convString(subCateId);
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
        return convString(subCateName);
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
        return convString(title);
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
        String ret = discription;
        if (ret == null) {
            ret = "";
        }
        return ret;
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
        return convString(pubDateFrom);
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
        return convString(pubDateTo);
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
        return convString(pubUser);
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
        return convString(userNameKj);
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
    public String getPubOrg() {
        return convString(pubOrg);
    }
    /**
     * 登録元を設定します。
     * @param pubOrg 登録元
     */
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }

    /**
     * 登録元名を取得します。
     * @return 登録元名
     */
    public String getPubOrgName() {
        return convString(pubOrgName);
    }
    /**
     * 登録元名を設定します。
     * @param pubOrgName 登録元名
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }

    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return convString(rCompanyCd);
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }

    /**
     * ファイル名を取得します。
     * @return ファイル名
     */
    public String getFileName() {
        return convString(fileName);
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
        return convString(attachName1);
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
        return convString(attachName2);
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
        return convString(attachName3);
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
        return convString(attachFl1);
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
        return convString(attachFl2);
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
        return convString(attachFl3);
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
        return convString(limitKbn);
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
        return convString(limitKbnName);
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
        return convString(sortSeq);
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
        return convString(sakujoFlg);
    }
    /**
     * 削除フラグを設定します。
     * @param sakujoFlg 削除フラグ
     */
    public void setSakujoFlg(String sakujoFlg) {
        this.sakujoFlg = sakujoFlg;
    }

    /**
     * 部門コードﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public String getBumonName() {
        return convString(bumonName);
    }
    /**
     * 部門コードを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }


    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return convString(firstUser);
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
        return convString(firstPgm);
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
        return convString(lastUser);
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
        return convString(lastPgm);
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
     * サブカテゴリごとの件数取得処理
     * @return 件数
     */
    public int getSubCateCount() {
        return subCateCount;
    }
    /**
     * サブカテゴリごとの件数設定処理
     * @param 件数
     */
    public void setSubCateCount(int subCateCount) {
        this.subCateCount = subCateCount;
    }

    /**
     * 添付ファイル有効個数
     * @return
     */
    public int getListAttachFileSize() {
        int size = 0;

        if (!isNull(getAttachName1())) {
            size++;
        }
        if (!isNull(getAttachName2())) {
            size++;
        }
        if (!isNull(getAttachName3())) {
            size++;
        }
        return size;
    }

    public List getListAttachFile() {
        List list = new ArrayList();

        if (!isNull(getAttachName1())) {
            SelectItem item  =new SelectItem("1", getAttachName1());
            list.add(item);
        }
        if (!isNull(getAttachName2())) {
            SelectItem item  =new SelectItem("2", getAttachName2());
            list.add(item);
        }
        if (!isNull(getAttachName3())) {
            SelectItem item  =new SelectItem("3", getAttachName3());
            list.add(item);
        }
        return list;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret.replaceAll("[　*]*$", "");
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

	public int getDownloadAttachIndex() {
		return downloadAttachIndex;
	}
	public void setDownloadAttachIndex(int downloadAttachIndex) {
		this.downloadAttachIndex = downloadAttachIndex;
	}
}