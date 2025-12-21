package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UIEntryMst {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
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
    
    public static final String kaisaiFrom_COLUMN = "KAISAI_FROM";
    
    public static final String kaisaiTo_COLUMN = "KAISAI_TO";
    
    public static final String uketukeFrom_COLUMN = "UKETUKE_FROM";
    
    public static final String uketukeTo_COLUMN = "UKETUKE_TO";
    
    public static final String hyojiFrom_COLUMN = "HYOJI_FROM";
    
    public static final String hyojiTo_COLUMN = "HYOJI_TO";
    
    public static final String staffSu_COLUMN = "STAFFSU";
    
    public static final String onerSu_COLUMN = "ONERSU";
    
    public static final String onerStaff_COLUMN = "ONER_STAFF";
    
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
     * タイトル
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
     * 開催日From
     */
    private String kaisaiFrom;
    
    /**
     * 開催日To
     */
    private String kaisaiTo;
    
    /**
     * 本部：申込開始
     */
    private String honbuUketukeFrom;
    
    /**
     * 本部：申込終了
     */
    private String honbuUketukeTo;
    
    /**
     * オーナー：申込開始
     */
    private String onerUketukeFrom;
    
    /**
     * オーナー：申込終了
     */
    private String onerUketukeTo;
    /**
     * 表示開始
     */
    private String hyojiFrom;
    
    /**
     * 表示終了
     */
    private String hyojiTo;
    
    /**
     * オーナー表示開始
     */
    private String onerHyojiFrom;
    
    /**
     * オーナー表示終了
     */
    private String onerHyojiTo;
    
    /**
     * エントリー名 略称
     */
    private String entryNameRyak;
    
    /**
     * 総申込人数
     */
    private BigDecimal staffSu;

    /**
     * オーナー数
     */
    private BigDecimal onerSu;

    /**
     * 総申込人数 表示用
     */
    private String staffSuDisp;
    
    /**
     * オーナー 申込人数
     */
    private BigDecimal onerStaff;
    
    /**
     * 編集可能フラグ
     */
    private String editFlg;
    
    /**
     * 表示フラグ
     */
    private String displayFlg;
    
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
     * タイトルを取得します。
     * @return タイトル
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * タイトルを設定します。
     * @param entryTitle タイトル
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
    
    /**
     * 開催日Fromを取得します。
     * @return 開催日From
     */
    public String getKaisaiFrom() {
        return kaisaiFrom;
    }
    /**
     * 開催日Fromを設定します。
     * @param kaisaiFrom 開催日From
     */
    public void setKaisaiFrom(String kaisaiFrom) {
        this.kaisaiFrom = kaisaiFrom;
    }
    
    /**
     * 開催日Toを取得します。
     * @return 開催日To
     */
    public String getKaisaiTo() {
        return kaisaiTo;
    }
    /**
     * 開催日Toを設定します。
     * @param kaisaiTo 開催日To
     */
    public void setKaisaiTo(String kaisaiTo) {
        this.kaisaiTo = kaisaiTo;
    }
    
    /**
     * 表示開始を取得します。
     * @return 表示開始
     */
    public String getHyojiFrom() {
        return hyojiFrom;
    }
    /**
     * 表示開始を設定します。
     * @param hyojiFrom 表示開始
     */
    public void setHyojiFrom(String hyojiFrom) {
        this.hyojiFrom = hyojiFrom;
    }
    
    /**
     * 表示終了を取得します。
     * @return 表示終了
     */
    public String getHyojiTo() {
        return hyojiTo;
    }
    /**
     * 表示終了を設定します。
     * @param hyojiTo 表示終了
     */
    public void setHyojiTo(String hyojiTo) {
        this.hyojiTo = hyojiTo;
    }
    public String getEntryNameRyak() {
        return entryNameRyak;
    }
    public void setEntryNameRyak(String entryNameRyak) {
        this.entryNameRyak = entryNameRyak;
    }
    public String getHonbuUketukeFrom() {
        return honbuUketukeFrom;
    }
    public void setHonbuUketukeFrom(String honbuUketukeFrom) {
        this.honbuUketukeFrom = honbuUketukeFrom;
    }
    public String getHonbuUketukeTo() {
        return honbuUketukeTo;
    }
    public void setHonbuUketukeTo(String honbuUketukeTo) {
        this.honbuUketukeTo = honbuUketukeTo;
    }
    public String getOnerUketukeFrom() {
        return onerUketukeFrom;
    }
    public void setOnerUketukeFrom(String onerUketukeFrom) {
        this.onerUketukeFrom = onerUketukeFrom;
    }
    public String getOnerUketukeTo() {
        return onerUketukeTo;
    }
    public void setOnerUketukeTo(String onerUketukeTo) {
        this.onerUketukeTo = onerUketukeTo;
    }
    public BigDecimal getOnerSu() {
        return onerSu;
    }
    public void setOnerSu(BigDecimal onerCount) {
        this.onerSu = onerCount;
    }
    public BigDecimal getStaffSu() {
        return staffSu;
    }
    public void setStaffSu(BigDecimal soMosikomiNinzu) {
        this.staffSu = soMosikomiNinzu;
    }
    public String getStaffSuDisp() {
        return staffSuDisp;
    }
    public void setStaffSuDisp(String staffSuDisp) {
        this.staffSuDisp = staffSuDisp;
    }
    public BigDecimal getOnerStaff() {
        return onerStaff;
    }
    public void setOnerStaff(BigDecimal onerStaff) {
        this.onerStaff = onerStaff;
    }
    public String getOnerHyojiFrom() {
        return onerHyojiFrom;
    }
    public void setOnerHyojiFrom(String onerHyojiFrom) {
        this.onerHyojiFrom = onerHyojiFrom;
    }
    public String getOnerHyojiTo() {
        return onerHyojiTo;
    }
    public void setOnerHyojiTo(String onerHyojiTo) {
        this.onerHyojiTo = onerHyojiTo;
    }
    public String getDisplayFlg() {
        return displayFlg;
    }
    public void setDisplayFlg(String displayFlg) {
        this.displayFlg = displayFlg;
    }
    public String getEditFlg() {
        return editFlg;
    }
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
    }
    
}
