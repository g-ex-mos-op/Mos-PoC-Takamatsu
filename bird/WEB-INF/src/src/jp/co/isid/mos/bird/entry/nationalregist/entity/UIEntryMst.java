package jp.co.isid.mos.bird.entry.nationalregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * エントリーマスタ管理エンティティ
 * 
 * @author xjung
 */
public class UIEntryMst {
    /** テーブル名称 */
    public static final String TABLE = "BR17ENTL";
    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    /** カラム名称：エントリーコード */
    public static final String entryCd_COLUMN = "ENTRY_CD";
    /** カラム名称：エントリー年 */    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    /** カラム名称：エントリー回 */
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    /** カラム名称：エントリータイトル */
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    /** カラム名称：開催場所 */
    public static final String entryPlace_COLUMN = "ENTRY_PLACE";
    /** カラム名称：申込定員 */
    public static final String numberLimit_COLUMN = "NUMBER_LIMIT";
    /** カラム名称：会場定員 */
    public static final String placeLimit_COLUMN = "PLACE_LIMIT";
    /** カラム名称：備考 */
    public static final String note_COLUMN = "NOTE";
    /** カラム名称：予備フラグ１ */
    public static final String spareFlg1_COLUMN = "SPARE_FLG1";
    /** カラム名称：予備フラグ２ */
    public static final String spareFlg2_COLUMN = "SPARE_FLG2";
    /** カラム名称：削除フラグ */
    public static final String sakujoFlg_COLUMN = "SAKUJO_FLG";
    /** カラム名称：登録ユーザー */
    public static final String firstUser_COLUMN = "FIRST_USER";
    /** カラム名称：登録プログラム */
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    /** カラム名称：登録時タイムスタンプ */
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    /** カラム名称：修正ユーザー */
    public static final String lastUser_COLUMN = "LAST_USER";
    /** カラム名称：修正プログラム */
    public static final String lastPgm_COLUMN = "LAST_PGM";
    /** カラム名称：修正時タイムスタンプ */
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    /** カラム名称：開催開始日 */
    public static final String openFrom_COLUMN = "OPEN_FROM";
    /** カラム名称：開催終了日 */
    public static final String openTo_COLUMN = "OPEN_TO";
    /** カラム名称：オーナー：申込開始日 */
    public static final String onerEntryFrom_COLUMN = "ONER_ENTRY_FROM";
    /** カラム名称：オーナー：申込終了日 */
    public static final String onerEntryTo_COLUMN = "ONER_ENTRY_TO";
    /** カラム名称：オーナー：表示開始日 */
    public static final String onerDispFrom_COLUMN = "ONER_DISP_FROM";
    /** カラム名称：オーナー：表示終了日 */
    public static final String onerDispTo_COLUMN = "ONER_DISP_TO";
    /** カラム名称：本部：申込開始日 */
    public static final String honbuEntryFrom_COLUMN = "HONBU_ENTRY_FROM";
    /** カラム名称：本部：申込終了日 */
    public static final String honbuEntryTo_COLUMN = "HONBU_ENTRY_TO";
    /** カラム名称：本部：表示開始日 */
    public static final String honbuDispFrom_COLUMN = "HONBU_DISP_FROM";
    /** カラム名称：本部：表示終了日 */
    public static final String honbuDispTo_COLUMN = "HONBU_DISP_TO";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * エントリータイトル
     */
    private String entryTitle;
    
    /**
     * 開催場所
     */
    private String entryPlace;
    
    /**
     * 申込定員
     */
    private BigDecimal numberLimit;
    
    /**
     * 会場定員
     */
    private BigDecimal placeLimit;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 予備フラグ1
     */
    private String spareFlg1;
    
    /**
     * 予備フラグ2
     */
    private String spareFlg2;
    
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
     * 開催開始日
     */
    private String openFrom;
    
    /**
     * 開催終了日
     */
    private String openTo;
    
    /**
     * オーナー：申込開始日
     */
    private String onerEntryFrom;
    
    /**
     * オーナー：申込終了日
     */
    private String onerEntryTo;
    
    /**
     * オーナー：表示開始日
     */
    private String onerDispFrom;
    
    /**
     * オーナー：表示終了日
     */
    private String onerDispTo;
    
    /**
     * 本部：申込開始日
     */
    private String honbuEntryFrom;
    
    /**
     * 本部：申込終了日
     */
    private String honbuEntryTo;

    /**
     * 本部：表示開始日
     */
    private String honbuDispFrom;
    
    /**
     * 本部：表示終了日
     */
    private String honbuDispTo;
    
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
        return entryTitle != null ? entryTitle.trim() : entryTitle;
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
        return entryPlace != null ? entryPlace.trim() : entryPlace;
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
    public BigDecimal getNumberLimit() {
        return numberLimit;
    }
    /**
     * 申込定員を設定します。
     * @param numberLimit 申込定員
     */
    public void setNumberLimit(BigDecimal numberLimit) {
        this.numberLimit = numberLimit;
    }
    
    /**
     * 会場定員を取得します。
     * @return 会場定員
     */
    public BigDecimal getPlaceLimit() {
        return placeLimit;
    }
    /**
     * 会場定員を設定します。
     * @param placeLimit 会場定員
     */
    public void setPlaceLimit(BigDecimal placeLimit) {
        this.placeLimit = placeLimit;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note != null ? note.trim() : note;
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
    
    /**
     * 開催開始日を取得します。
     * @return 開催開始日
     */
    public String getOpenFrom() {
        return openFrom;
    }
    /**
     * 開催開始日を設定します。
     * @param openFrom 開催開始日
     */
    public void setOpenFrom(String openFrom) {
        this.openFrom = openFrom;
    }
    
    /**
     * 開催終了日を取得します。
     * @return 開催終了日
     */
    public String getOpenTo() {
        return openTo;
    }
    /**
     * 開催終了日を設定します。
     * @param openTo 開催終了日
     */
    public void setOpenTo(String openTo) {
        this.openTo = openTo;
    }
    
    /**
     * 本部：申込開始日を取得します。
     * @return 本部：申込開始日
     */
    public String getHonbuEntryFrom() {
        return honbuEntryFrom;
    }
    /**
     * 本部：申込開始日を設定します。
     * @param honbuEntryFrom 本部：申込開始日
     */
    public void setHonbuEntryFrom(String honbuEntryFrom) {
        this.honbuEntryFrom = honbuEntryFrom;
    }
    
    /**
     * オーナー：申込開始日を取得します。
     * @return オーナー：申込開始日
     */
    public String getOnerEntryFrom() {
        return onerEntryFrom;
    }
    /**
     * オーナー：申込開始日を設定します。
     * @param onerEntryFrom オーナー：申込開始日
     */
    public void setOnerEntryFrom(String onerEntryFrom) {
        this.onerEntryFrom = onerEntryFrom;
    }
    
    /**
     * オーナー：申込終了日を取得します。
     * @return オーナー：申込終了日
     */
    public String getOnerEntryTo() {
        return onerEntryTo;
    }
    /**
     * オーナー：申込終了日を設定します。
     * @param onerEntryTo オーナー：申込終了日
     */
    public void setOnerEntryTo(String onerEntryTo) {
        this.onerEntryTo = onerEntryTo;
    }
    
    /**
     * オーナー：表示開始日を取得します。
     * @return オーナー：表示開始日
     */
    public String getOnerDispFrom() {
        return onerDispFrom;
    }
    /**
     * オーナー：表示開始日を設定します。
     * @param onerDispFrom オーナー：表示開始日
     */
    public void setOnerDispFrom(String onerDispFrom) {
        this.onerDispFrom = onerDispFrom;
    }
    
    /**
     * オーナー：表示終了日を取得します。
     * @return オーナー：表示終了日
     */
    public String getOnerDispTo() {
        return onerDispTo;
    }
    /**
     * オーナー：表示終了日を設定します。
     * @param onerDispTo オーナー：表示終了日
     */
    public void setOnerDispTo(String onerDispTo) {
        this.onerDispTo = onerDispTo;
    }
    
    /**
     * 本部：申込終了日を取得します。
     * @return 本部：申込終了日
     */
    public String getHonbuEntryTo() {
        return honbuEntryTo;
    }
    /**
     * 本部：申込終了日を設定します。
     * @param honbuEntryTo 本部：申込終了日
     */
    public void setHonbuEntryTo(String honbuEntryTo) {
        this.honbuEntryTo = honbuEntryTo;
    }

    /**
     * 本部：表示開始日を取得します。
     * @return 本部：表示開始日
     */
    public String getHonbuDispFrom() {
        return honbuDispFrom;
    }
    /**
     * 本部：表示開始日を設定します。
     * @param honbuDispFrom 本部：表示開始日
     */
    public void setHonbuDispFrom(String honbuDispFrom) {
        this.honbuDispFrom = honbuDispFrom;
    }

    /**
     * 本部：表示終了日を取得します。
     * @return 本部：表示終了日
     */
    public String getHonbuDispTo() {
        return honbuDispTo;
    }
    /**
     * 本部：表示終了日を設定します。
     * @param honbuDispTo 本部：表示終了日
     */
    public void setHonbuDispTo(String honbuDispTo) {
        this.honbuDispTo = honbuDispTo;
    }
}
