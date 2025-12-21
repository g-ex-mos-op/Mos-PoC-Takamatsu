package jp.co.isid.mos.bird.entry.basicregist.entity;

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

    public static final String basicFromDt_COLUMN = "BASIC_FROM_DT";

    public static final String basicToDt_COLUMN = "BASIC_TO_DT";

    public static final String visitFromDt_COLUMN = "VISIT_FROM_DT";

    public static final String visitToDt_COLUMN = "VISIT_TO_DT";

    public static final String displayHeadFromDt_COLUMN = "DISPLAYHEAD_FROM_DT";

    public static final String displayHeadToDt_COLUMN = "DISPLAYHEAD_TO_DT";

    public static final String displayOnerFromDt_COLUMN = "DISPLAYONER_FROM_DT";

    public static final String displayOnerToDt_COLUMN = "DISPLAYONER_TO_DT";

    public static final String applyHeadFromDt_COLUMN = "APPLYHEAD_FROM_DT";

    public static final String applyHeadToDt_COLUMN = "APPLYHEAD_TO_DT";

    public static final String applyOnerFromDt_COLUMN = "APPLYONER_FROM_DT";

    public static final String applyOnerToDt_COLUMN = "APPLYONER_TO_DT";

    public static final String resultFromDt_COLUMN = "RESULT_FROM_DT";

    public static final String resultToDt_COLUMN = "RESULT_TO_DT";
    
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
    private String numberLimit;
    
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
     * ベーシック研修From日付
     */
    private String basicFromDt;
    
    /**
     * ベーシック研修To日付
     */
    private String basicToDt;
    
    /**
     * 臨店研修コースFrom日付
     */
    private String visitFromDt;
    
    /**
     * 臨店研修コースTo日付
     */
    private String visitToDt;
    
    /**
     * 表示From日付(本部)
     */
    private String displayHeadFromDt;
    
    /**
     * 表示To日付(本部)
     */
    private String displayHeadToDt;
    
    /**
     * 表示From日付(オーナー)
     */
    private String displayOnerFromDt;
    
    /**
     * 表示To日付(オーナー)
     */
    private String displayOnerToDt;
    
    /**
     * 申込From日付(本部)
     */
    private String applyHeadFromDt;
    
    /**
     * 申込To日付(本部)
     */
    private String applyHeadToDtt;
    
    /**
     * 申込From日付(オーナー)
     */
    private String applyOnerFromDt;
    
    /**
     * 申込To日付(オーナー)
     */
    private String applyOnerToDt;
    
    /**
     * 結果登録From日付
     */
    private String resultFromDt;
    
    /**
     * 結果登録To日付
     */
    private String resultToDt;
    
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
    public String getNumberLimit() {
        return numberLimit;
    }
    /**
     * 申込定員を設定します。
     * @param numberLimit 申込定員
     */
    public void setNumberLimit(String numberLimit) {
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
     * @return applyHeadFromDt を戻します。
     */
    public String getApplyHeadFromDt() {
        return applyHeadFromDt;
    }
    /**
     * @param applyHeadFromDt applyHeadFromDt を設定。
     */
    public void setApplyHeadFromDt(String applyHeadFromDt) {
        this.applyHeadFromDt = applyHeadFromDt;
    }
    /**
     * @return applyHeadToDtt を戻します。
     */
    public String getApplyHeadToDtt() {
        return applyHeadToDtt;
    }
    /**
     * @param applyHeadToDtt applyHeadToDtt を設定。
     */
    public void setApplyHeadToDtt(String applyHeadToDtt) {
        this.applyHeadToDtt = applyHeadToDtt;
    }
    /**
     * @return applyOnerFromDt を戻します。
     */
    public String getApplyOnerFromDt() {
        return applyOnerFromDt;
    }
    /**
     * @param applyOnerFromDt applyOnerFromDt を設定。
     */
    public void setApplyOnerFromDt(String applyOnerFromDt) {
        this.applyOnerFromDt = applyOnerFromDt;
    }
    /**
     * @return applyOnerToDt を戻します。
     */
    public String getApplyOnerToDt() {
        return applyOnerToDt;
    }
    /**
     * @param applyOnerToDt applyOnerToDt を設定。
     */
    public void setApplyOnerToDt(String applyOnerToDt) {
        this.applyOnerToDt = applyOnerToDt;
    }
    /**
     * @return basicFromDt を戻します。
     */
    public String getBasicFromDt() {
        return basicFromDt;
    }
    /**
     * @param basicFromDt basicFromDt を設定。
     */
    public void setBasicFromDt(String basicFromDt) {
        this.basicFromDt = basicFromDt;
    }
    /**
     * @return basicToDt を戻します。
     */
    public String getBasicToDt() {
        return basicToDt;
    }
    /**
     * @param basicToDt basicToDt を設定。
     */
    public void setBasicToDt(String basicToDt) {
        this.basicToDt = basicToDt;
    }
    /**
     * @return displayHeadFromDt を戻します。
     */
    public String getDisplayHeadFromDt() {
        return displayHeadFromDt;
    }
    /**
     * @param displayHeadFromDt displayHeadFromDt を設定。
     */
    public void setDisplayHeadFromDt(String displayHeadFromDt) {
        this.displayHeadFromDt = displayHeadFromDt;
    }
    /**
     * @return displayHeadToDt を戻します。
     */
    public String getDisplayHeadToDt() {
        return displayHeadToDt;
    }
    /**
     * @param displayHeadToDt displayHeadToDt を設定。
     */
    public void setDisplayHeadToDt(String displayHeadToDt) {
        this.displayHeadToDt = displayHeadToDt;
    }
    /**
     * @return displayOnerFromDt を戻します。
     */
    public String getDisplayOnerFromDt() {
        return displayOnerFromDt;
    }
    /**
     * @param displayOnerFromDt displayOnerFromDt を設定。
     */
    public void setDisplayOnerFromDt(String displayOnerFromDt) {
        this.displayOnerFromDt = displayOnerFromDt;
    }
    /**
     * @return displayOnerToDt を戻します。
     */
    public String getDisplayOnerToDt() {
        return displayOnerToDt;
    }
    /**
     * @param displayOnerToDt displayOnerToDt を設定。
     */
    public void setDisplayOnerToDt(String displayOnerToDt) {
        this.displayOnerToDt = displayOnerToDt;
    }
    /**
     * @return resultFromDt を戻します。
     */
    public String getResultFromDt() {
        return resultFromDt;
    }
    /**
     * @param resultFromDt resultFromDt を設定。
     */
    public void setResultFromDt(String resultFromDt) {
        this.resultFromDt = resultFromDt;
    }
    /**
     * @return resultToDt を戻します。
     */
    public String getResultToDt() {
        return resultToDt;
    }
    /**
     * @param resultToDt resultToDt を設定。
     */
    public void setResultToDt(String resultToDt) {
        this.resultToDt = resultToDt;
    }
    /**
     * @return visitFromDt を戻します。
     */
    public String getVisitFromDt() {
        return visitFromDt;
    }
    /**
     * @param visitFromDt visitFromDt を設定。
     */
    public void setVisitFromDt(String visitFromDt) {
        this.visitFromDt = visitFromDt;
    }
    /**
     * @return visitToDt を戻します。
     */
    public String getVisitToDt() {
        return visitToDt;
    }
    /**
     * @param visitToDt visitToDt を設定。
     */
    public void setVisitToDt(String visitToDt) {
        this.visitToDt = visitToDt;
    }
}
