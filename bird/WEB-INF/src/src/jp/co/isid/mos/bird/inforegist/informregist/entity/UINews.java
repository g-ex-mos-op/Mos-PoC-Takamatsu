package jp.co.isid.mos.bird.inforegist.informregist.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class UINews {

    public static final String TABLE = "BT05WNEW";

    public static final String regDate_COLUMN = "REG_DATE";

    public static final String seq_COLUMN = "SEQ";

    public static final String title_COLUMN = "TITLE";

    public static final String pubDateFrom_COLUMN = "PUB_DATE_FROM";

    public static final String pubDateTo_COLUMN = "PUB_DATE_TO";

    public static final String pubUser_COLUMN = "PUB_USER";

    public static final String userNamekj_COLUMN = "USER_NAME_KJ";

    public static final String pubOrg_COLUMN = "PUB_ORG";

    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";

    public static final String simpleMsg_COLUMN = "SIMPLE_MSG";

    public static final String message_COLUMN = "MESSAGE";

    public static final String attachName1_COLUMN = "ATTACH_NAME1";

    public static final String attachName2_COLUMN = "ATTACH_NAME2";

    public static final String attachName3_COLUMN = "ATTACH_NAME3";

    public static final String attachFl1_COLUMN = "ATTACH_FL1";

    public static final String attachFl2_COLUMN = "ATTACH_FL2";

    public static final String attachFl3_COLUMN = "ATTACH_FL3";

    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

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
     * タイトル
     */
    private String title;

    /**
     * 公開期間FROM
     */
    private String pubDateFrom;
    /**
     * 公開期間TO
     */
    private String pubDateTo;

    /**
     * 発信者
     */
    private String pubUser;

    /**
     * ユーザ名称
     */
    private String userNameKj;

    /**
     * 発信元
     */
    private String pubOrg;

    /**
     * 発信元名称
     */
    private String pubOrgName;

    /**
     * 簡易メッセージ
     */
    private String simpleMsg;

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
     * 添付ファイルプルダウンのインデックス
     */
    private int downloadAttachIndex;

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
     * 発信日を取得します。
     * @return 発信日
     */
    public String getPubDateFrom() {
        return convertString(pubDateFrom);
    }
    /**
     * 発信日を設定します。
     * @param pubDateFrom 発信日
     */
    public void setPubDateFrom(String pubDate) {
        this.pubDateFrom = pubDate;
    }

    /**
     * 発信者を取得します。
     * @return 発信者
     */
    public String getPubUser() {
        return convertString(pubUser);
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
        return convertString(userNameKj);
    }
    /**
     * ユーザ名称を設定します。
     * @param userNamekj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

    /**
     * 発信元を取得します。
     * @return 発信元
     */
    public String getPubOrg() {
        return convertString(pubOrg);
    }
    /**
     * 発信元を設定します。
     * @param pubOrg 発信元
     */
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }

    /**
     * 簡易メッセージを取得します。
     * @return 簡易メッセージ
     */
    public String getSimpleMsg() {
        return convertString(simpleMsg);
    }
    /**
     * 簡易メッセージを設定します。
     * @param simpleMsg 簡易メッセージ
     */
    public void setSimpleMsg(String simpleMsg) {
        this.simpleMsg = simpleMsg;
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
	 * 発信元名称の設定
	 * @return pubOrgName を戻します。
	 */
	public String getPubOrgName() {
		return convertString(pubOrgName);
	}
	/**
	 * 発信元名称の設定
	 * @param pubOrgName pubOrgName を設定。
	 */
	public void setPubOrgName(String pubOrgName) {
		this.pubOrgName = pubOrgName;
	}
    /**
     * 添付ファイル存在チェック
     * @return
     */
    public boolean isAddAttachFile() {
        if (isNull(getAttachFl1())
                || isNull(getAttachFl2())
                || isNull(getAttachFl3())){
            return true;
        }
        return false;
    }

    /**
     * Null、空文字チェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }

    /**
     * Nullはブランクに、それ以外はトリムします。
     * @param str 対象文字列
     * @return 変換後文字列
     */
    private String convertString(String str){
        if(isNull(str)){
            return "";
        }
        return str.trim().replaceAll("[　*]*$", "");
    }

    /**
     * 添付ファイル有効個数を取得します。
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

    /**
     * 添付ファイルのプルダウンを取得します。
     * @return 添付ファイルのプルダウン
     */
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

    /**
     * 添付ファイルプルダウンのインデックスを取得します。
     * @return 添付ファイルプルダウンのインデックス
     */
    public int getDownloadAttachIndex() {
        return downloadAttachIndex;
    }

    /**
     * 添付ファイルプルダウンのインデックスを設定します。
     * @param downloadAttachIndex 添付ファイルプルダウンのインデックス
     */
    public void setDownloadAttachIndex(int downloadAttachIndex) {
        this.downloadAttachIndex = downloadAttachIndex;
    }
	/**
	 * @return pubDateTo を戻します。
	 */
	public String getPubDateTo() {
		return convertString(pubDateTo);
	}
	/**
	 * @param pubDateTo を クラス変数pubDateToへ設定します。
	 */
	public void setPubDateTo(String pubDateTo) {
		this.pubDateTo = pubDateTo;
	}

}
