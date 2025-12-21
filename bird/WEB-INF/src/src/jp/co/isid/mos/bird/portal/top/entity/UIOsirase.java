package jp.co.isid.mos.bird.portal.top.entity;

import jp.co.isid.mos.bird.common.entity.TrnInfoAccessControl;

/**
 * WhatsNew(お知らせ)エンティティ
 * 
 * 作成日:2008/11/25
 * 更新日:2008/11/25 1.発信日の削除
 *                   2.企業コードの削除
 *                   3.公開期間FROM＆TOを追加
 * @author xkinu
 *
 */
public class UIOsirase  implements TrnInfoAccessControl{
    
    public static final String TABLE = "BT05WNEW";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String pubDateFrom_COLUMN = "PUB_DATE_FROM";
    
    public static final String pubDateTo_COLUMN = "PUB_DATE_TO";
    
    public static final String pubUser_COLUMN = "PUB_USER";
    
    public static final String pubOrg_COLUMN = "PUB_ORG";

    public static final String pubOrgName_COLUMN = "PUB_ORG_NAME";

    public static final String simpleMsg_COLUMN = "SIMPLE_MSG";
    
    public static final String message_COLUMN = "MESSAGE";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";
    public static final String miseFlg_COLUMN = "MISE_FLG";
    
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
     * 発信元名称
     */
    private String bumonName;
    
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
    public String getPubDateFrom() {
        return pubDateFrom;
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
     * 発信元名称を取得します。
     * @return pubOrgName を戻します。
     */
    public String getPubOrgName() {
        return pubOrgName;
    }
    /**
     * 発信元名称を設定します。
     * @param pubOrgName pubOrgName を設定。
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }
    /**
     * 簡易メッセージを取得します。
     * @return 簡易メッセージ
     */
    public String getSimpleMsg() {
        return simpleMsg;
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
     * 発信元名称を取得します。
     * @return 発信元名称
     */
    public String getBumonName() {
        return bumonName;
    }
    /**
     * 発信元名称を設定します。
     * @param bumonName 発信元名称
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
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
	 * @return pubDateTo を戻します。
	 */
	public String getPubDateTo() {
		return pubDateTo;
	}
	/**
	 * @param pubDateTo を クラス変数pubDateToへ設定します。
	 */
	public void setPubDateTo(String pubDateTo) {
		this.pubDateTo = pubDateTo;
	}
}
