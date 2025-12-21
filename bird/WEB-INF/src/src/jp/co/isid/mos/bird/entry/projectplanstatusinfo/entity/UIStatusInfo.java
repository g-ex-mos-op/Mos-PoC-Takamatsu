package jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity;

public class UIStatusInfo {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String kaisaiDt_COLUMN = "KAISAI_DT";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String honbuFromDt_COLUMN = "HONBU_FROM_DT";
    
    public static final String honbuToDt_COLUMN = "HONBU_TO_DT";
    
    public static final String onerFromDt_COLUMN = "ONER_FROM_DT";
    
    public static final String onerToDt_COLUMN = "ONER_TO_DT";
    
    public static final String jAttendCnt_COLUMN = "J_ATTEND_CNT";
    
    public static final String jAbsentCnt_COLUMN = "J_ABSENT_CNT";
    
    public static final String konAttendCnt_COLUMN = "KON_ATTEND_CNT";
    
    public static final String konAbsentCnt_COLUMN = "KON_ABSENT_CNT";
    
    public static final String kyoAttendCnt_COLUMN = "KYO_ATTEND_CNT";
    
    public static final String kyoAbsentCnt_COLUMN = "KYO_ABSENT_CNT";
       
    
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
     * 開催日
     */
    private String kaisaiDt;
    
    /**
     * エントリータイトル（説明会名称）
     */
    private String entryTitle;
    
    /**
     * 開始日（本部申込開始日）
     */
    private String honbuFromDt;
    
    /**
     * 終了日(本部申込終了日)
     */
    private String honbuToDt;
    
    /**
     * 開始日（オーナー申込開始日）
     */
    private String onerFromDt;
    
    /**
     * 終了日(オーナー申込終了日)
     */
    private String onerToDt;
    
    /**
     * 事業方針説明会出席フラグ
     */
    private String jAttendCnt;
    
    /**
     * 事業方針説明会欠席フラグ
     */
    private String jAbsentCnt;
    
    /**
     * 懇親会出席フラグ
     */
    private String konAttendCnt;
    
    /**
     * 懇親会欠席フラグ
     */
    private String konAbsentCnt;
    
    /**
     * 共栄会定時総会出席フラグ
     */
    private String kyoAttendCnt;
    
    /**
     * 共栄会定時総会出席フラグ
     */
    private String kyoAbsentCnt;
    
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
     * 開催日を取得します。
     * @return 開催日
     */
    public String getKaisaiDt() {
        return kaisaiDt;
    }
    /**
     * 開催日を設定します。
     * @param kaisaiDt 開催日
     */
    public void setKaisaiDt(String kaisaiDt) {
        this.kaisaiDt = kaisaiDt;
    }
    
    /**
     * エントリータイトル（説明会名称）を取得します。
     * @return エントリータイトル（説明会名称）
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトル（説明会名称）を設定します。
     * @param entryTitle エントリータイトル（説明会名称）
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * 開始日（本部申込開始日）を取得します。
     * @return 開始日（本部申込開始日）
     */
    public String getHonbuFromDt() {
        return honbuFromDt;
    }
    /**
     * 開始日（本部申込開始日）を設定します。
     * @param honbuFromDt 開始日（本部申込開始日）
     */
    public void setHonbuFromDt(String honbuFromDt) {
        this.honbuFromDt = honbuFromDt;
    }
    
    /**
     * 終了日(本部申込終了日)を取得します。
     * @return 終了日(本部申込終了日)
     */
    public String getHonbuToDt() {
        return honbuToDt;
    }
    /**
     * 終了日(本部申込終了日)を設定します。
     * @param honbuToDt 終了日(本部申込終了日)
     */
    public void setHonbuToDt(String honbuToDt) {
        this.honbuToDt = honbuToDt;
    }
    
    /**
     * 開始日（オーナー申込開始日）を取得します。
     * @return 開始日（オーナー申込開始日）
     */
    public String getOnerFromDt() {
        return onerFromDt;
    }
    /**
     * 開始日（オーナー申込開始日）を設定します。
     * @param onerFromDt 開始日（オーナー申込開始日）
     */
    public void setOnerFromDt(String onerFromDt) {
        this.onerFromDt = onerFromDt;
    }
    
    /**
     * 終了日(オーナー申込終了日)を取得します。
     * @return 終了日(オーナー申込終了日)
     */
    public String getOnerToDt() {
        return onerToDt;
    }
    /**
     * 終了日(オーナー申込終了日)を設定します。
     * @param onerToDt 終了日(オーナー申込終了日)
     */
    public void setOnerToDt(String onerToDt) {
        this.onerToDt = onerToDt;
    }
    
    /**
     * 事業方針説明会出席フラグを取得します。
     * @return 事業方針説明会出席フラグ
     */
    public String getJAttendCnt() {
        return jAttendCnt;
    }
    /**
     * 事業方針説明会出席フラグを設定します。
     * @param jAttendCnt 事業方針説明会出席フラグ
     */
    public void setJAttendCnt(String jAttendCnt) {
        this.jAttendCnt = jAttendCnt;
    }
    
    /**
     * 事業方針説明会欠席フラグを取得します。
     * @return 事業方針説明会欠席フラグ
     */
    public String getJAbsentCnt() {
        return jAbsentCnt;
    }
    /**
     * 事業方針説明会欠席フラグを設定します。
     * @param jAbsentCnt 事業方針説明会欠席フラグ
     */
    public void setJAbsentCnt(String jAbsentCnt) {
        this.jAbsentCnt = jAbsentCnt;
    }
    
    /**
     * 懇親会出席フラグを取得します。
     * @return 懇親会出席フラグ
     */
    public String getKonAttendCnt() {
        return konAttendCnt;
    }
    /**
     * 懇親会出席フラグを設定します。
     * @param konAttendCnt 懇親会出席フラグ
     */
    public void setKonAttendCnt(String konAttendCnt) {
        this.konAttendCnt = konAttendCnt;
    }
    
    /**
     * 懇親会欠席フラグを取得します。
     * @return 懇親会欠席フラグ
     */
    public String getKonAbsentCnt() {
        return konAbsentCnt;
    }
    /**
     * 懇親会欠席フラグを設定します。
     * @param konAbsentCnt 懇親会欠席フラグ
     */
    public void setKonAbsentCnt(String konAbsentCnt) {
        this.konAbsentCnt = konAbsentCnt;
    }
    
    /**
     * 共栄会定時総会出席フラグを取得します。
     * @return 共栄会定時総会出席フラグ
     */
    public String getKyoAttendCnt() {
        return kyoAttendCnt;
    }
    /**
     * 共栄会定時総会出席フラグを設定します。
     * @param kyoAttendCnt 共栄会定時総会出席フラグ
     */
    public void setKyoAttendCnt(String kyoAttendCnt) {
        this.kyoAttendCnt = kyoAttendCnt;
    }
    
    /**
     * 共栄会定時総会出席フラグを取得します。
     * @return 共栄会定時総会出席フラグ
     */
    public String getKyoAbsentCnt() {
        return kyoAbsentCnt;
    }
    /**
     * 共栄会定時総会出席フラグを設定します。
     * @param kyoAbsentCnt 共栄会定時総会出席フラグ
     */
    public void setKyoAbsentCnt(String kyoAbsentCnt) {
        this.kyoAbsentCnt = kyoAbsentCnt;
    }
    
}
