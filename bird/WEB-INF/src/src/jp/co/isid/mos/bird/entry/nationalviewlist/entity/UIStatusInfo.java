package jp.co.isid.mos.bird.entry.nationalviewlist.entity;

/**
 * 対象エントリー情報エンティティー
 * 
 * @author xkinu
 *
 */
public class UIStatusInfo {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String kaisaiFromDt_COLUMN = "KAISAI_FROM_DT";
    
    public static final String kaisaiToDt_COLUMN = "KAISAI_TO_DT";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String honbuFromDt_COLUMN = "HONBU_FROM_DT";
    
    public static final String honbuToDt_COLUMN = "HONBU_TO_DT";
    
    public static final String onerFromDt_COLUMN = "ONER_FROM_DT";
    
    public static final String onerToDt_COLUMN = "ONER_TO_DT";
    
    public static final String onerAttendCnt_COLUMN = "ONER_ATTEND_CNT";
    
    public static final String onerAbsentCnt_COLUMN = "ONER_ABSENT_CNT";
    
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
     * 開催日From
     */
    private String kaisaiFromDt;
    
    /**
     * 開催日To
     */
    private String kaisaiToDt;
    
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
     * オーナー参加or出席件数
     */
    private String onerAttendCnt;
    
    /**
     * オーナー不参加or欠席件数
     */
    private String onerAbsentCnt;
    
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
    public String getKaisaiFromDt() {
        return kaisaiFromDt;
    }
    /**
     * 開催日を設定します。
     * @param kaisaiDt 開催日
     */
    public void setKaisaiFromDt(String kaisaiDt) {
        this.kaisaiFromDt = kaisaiDt;
    }
    
    /**
     * 開催日を取得します。
     * @return 開催日
     */
    public String getKaisaiToDt() {
        return kaisaiToDt;
    }
    /**
     * 開催日を設定します。
     * @param kaisaiDt 開催日
     */
    public void setKaisaiToDt(String kaisaiDt) {
        this.kaisaiToDt = kaisaiDt;
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
     * オーナー参加or出席件数を取得します。
     * @return オーナー参加or出席件数
     */
    public String getOnerAttendCnt() {
        return onerAttendCnt;
    }
    /**
     * オーナー参加or出席件数を設定します。
     * @param onerAttendCnt オーナー参加or出席件数
     */
    public void setOnerAttendCnt(String onerAttendCnt) {
        this.onerAttendCnt = onerAttendCnt;
    }
    
    /**
     * オーナー不参加or欠席件数を取得します。
     * @return オーナー不参加or欠席件数
     */
    public String getOnerAbsentCnt() {
        return onerAbsentCnt;
    }
    /**
     * オーナー不参加or欠席件数を設定します。
     * @param onerAbsentCnt オーナー不参加or欠席件数
     */
    public void setOnerAbsentCnt(String onerAbsentCnt) {
        this.onerAbsentCnt = onerAbsentCnt;
    }
    
}
