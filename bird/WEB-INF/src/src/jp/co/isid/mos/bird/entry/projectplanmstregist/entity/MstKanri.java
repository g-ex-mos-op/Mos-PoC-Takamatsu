package jp.co.isid.mos.bird.entry.projectplanmstregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 事業方針説明会マスタ エンティティクラス
 * 
 * @author xkinu
 *
 */
public class MstKanri {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String entryPlace_COLUMN = "ENTRY_PLACE";
    
    public static final String numberLimit_COLUMN = "NUMBER_LIMIT";
    
    public static final String placeLimit_COLUMN = "PLACE_LIMIT";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String spareFlg1_COLUMN = "SPARE_FLG1";
    
    public static final String spareFlg2_COLUMN = "SPARE_FLG2";
    
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    /**
     * エントリーコード
     */
    private String entryCd = "";
    
    /**
     * エントリー年
     */
    private String entryYear = "";
    
    /**
     * エントリー回
     */
    private String entryKai = "";
    
    /**
     * エントリータイトル
     */
    private String entryTitle = "";
    
    /**
     * 開催場所
     */
    private String entryPlace = "";
    
    /**
     * 申込定員
     */
    private BigDecimal numberLimit = new BigDecimal("0");
    
    /**
     * 会場定員
     */
    private BigDecimal placeLimit = new BigDecimal("0");
    
    /**
     * 備考
     */
    private String note = "";
    
    /**
     * 予備フラグ1
     */
    private String spareFlg1 = "";
    
    /**
     * 予備フラグ2
     */
    private String spareFlg2 = "";
    
    /**
     * 削除フラグ
     */
    private String sakujoFlg = "";
    
    /**
     * 登録ユーザー
     */
    private String firstUser = "";
    
    /**
     * 登録プログラム
     */
    private String firstPgm = "";
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser = "";
    
    /**
     * 修正プログラム
     */
    private String lastPgm = "";
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
    /**
     * エントリータイトルを取得します。
     * @return エントリータイトル
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトルを設定します。
     * @param entryTitle エントリータイトル
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * 開催場所を取得します。
     * @return 開催場所
     */
    public String getEntryPlace() {
        return entryPlace;
    }
    /**
     * 開催場所を設定します。
     * @param entryPlace 開催場所
     */
    public void setEntryPlace(String entryPlace) {
        this.entryPlace = entryPlace;
    }
    
    /**
     * 申込定員を取得します。
     * @return 申込定員
     */
    public java.math.BigDecimal getNumberLimit() {
        return numberLimit;
    }
    /**
     * 申込定員を設定します。
     * @param numberLimit 申込定員
     */
    public void setNumberLimit(java.math.BigDecimal numberLimit) {
        this.numberLimit = numberLimit;
    }
    
    /**
     * 会場定員を取得します。
     * @return 会場定員
     */
    public java.math.BigDecimal getPlaceLimit() {
        return placeLimit;
    }
    /**
     * 会場定員を設定します。
     * @param placeLimit 会場定員
     */
    public void setPlaceLimit(java.math.BigDecimal placeLimit) {
        this.placeLimit = placeLimit;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 予備フラグ1を取得します。
     * @return 予備フラグ1
     */
    public String getSpareFlg1() {
        return spareFlg1;
    }
    /**
     * 予備フラグ1を設定します。
     * @param spareFlg1 予備フラグ1
     */
    public void setSpareFlg1(String spareFlg1) {
        this.spareFlg1 = spareFlg1;
    }
    
    /**
     * 予備フラグ2を取得します。
     * @return 予備フラグ2
     */
    public String getSpareFlg2() {
        return spareFlg2;
    }
    /**
     * 予備フラグ2を設定します。
     * @param spareFlg2 予備フラグ2
     */
    public void setSpareFlg2(String spareFlg2) {
        this.spareFlg2 = spareFlg2;
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
    
}
