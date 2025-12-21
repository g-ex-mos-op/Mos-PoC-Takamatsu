package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

public class MstEventStatus {
    
    public static final String TABLE = "BM22EVNT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String eventCd_COLUMN = "EVENT_CD";
    
    public static final String eventName_COLUMN = "EVENT_NAME";
    
    public static final String eventStatus_COLUMN = "EVENT_STATUS";
    
    public static final String eventStartDt_COLUMN = "EVENT_START_DT";
    
    public static final String eventEndDt_COLUMN = "EVENT_END_DT";
    
    public static final String eventBnrui_COLUMN = "EVENT_BNRUI";
    
    public static final String eventBnruiName_COLUMN = "EVENT_BNRUI_NAME";
    
    public static final String eventSortCd_COLUMN = "EVENT_SORT_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String eventPatternCd_COLUMN = "EVENT_PATTERN_CD";
    
    public static final String eventPatternName_COLUMN = "EVENT_PATTERN_NAME";
    
    public static final String henkoDt_COLUMN = "HENKO_DT";
    
    public static final String notes_COLUMN = "NOTES";
    
    public static final String eventStatusName_COLUMN = "EVENT_STATUS_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String eventMaxCnt_COLUMN = "EVENT_MAX_CNT";
    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * イベントコード
     */
    private String eventCd;
    
    /**
     * イベント名称
     */
    private String eventName;
    
    /**
     * イベントステータス
     */
    private String eventStatus;
    
    /**
     * イベント開始日
     */
    private String eventStartDt;
    
    /**
     * イベント終了日
     */
    private String eventEndDt;
    
    /**
     * イベント分類
     */
    private String eventBnrui;
    
    /**
     * イベント分類名称
     */
    private String eventBnruiName;
    
    /**
     * イベントソートキー
     */
    private String eventSortCd;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * イベントパターンコード
     */
    private String eventPatternCd;
    
    /**
     * イベントパターン名称
     */
    private String eventPatternName;
    
    /**
     * 変更日
     */
    private String henkoDt;
    
    /**
     * 備考
     */
    private String notes;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * イベント分類毎の、イベント種別の数のMAX
     */
    private String eventMaxCnt;
    
    /**
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
    }
    /**
     * 管理会社コード企業コードを設定します。
     * @param companyCd 管理会社コード企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * イベントコードを取得します。
     * @return イベントコード
     */
    public String getEventCd() {
        return convString(eventCd);
    }
    /**
     * イベントコードを設定します。
     * @param eventCd イベントコード
     */
    public void setEventCd(String eventCd) {
        this.eventCd = eventCd;
    }
    
    /**
     * イベント名称を取得します。
     * @return イベント名称
     */
    public String getEventName() {
        return convString(eventName);
    }
    /**
     * イベント名称を設定します。
     * @param eventName イベント名称
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    /**
     * イベントステータスを取得します。
     * @return イベントステータス
     */
    public String getEventStatus() {
        return convString(eventStatus);
    }
    /**
     * イベントステータスを設定します。
     * @param eventStatus イベントステータス
     */
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
    
    /**
     * イベント開始日を取得します。
     * @return イベント開始日
     */
    public String getEventStartDt() {
        return convString(eventStartDt);
    }
    /**
     * イベント開始日を設定します。
     * @param eventStartDt イベント開始日
     */
    public void setEventStartDt(String eventStartDt) {
        this.eventStartDt = eventStartDt;
    }
    
    /**
     * イベント終了日を取得します。
     * @return イベント終了日
     */
    public String getEventEndDt() {
        return convString(eventEndDt);
    }
    /**
     * イベント終了日を設定します。
     * @param eventEndDt イベント終了日
     */
    public void setEventEndDt(String eventEndDt) {
        this.eventEndDt = eventEndDt;
    }
    
    /**
     * イベント分類を取得します。
     * @return イベント分類
     */
    public String getEventBnrui() {
        return convString(eventBnrui);
    }
    /**
     * イベント分類を設定します。
     * @param eventBnrui イベント分類
     */
    public void setEventBnrui(String eventBnrui) {
        this.eventBnrui = eventBnrui;
    }
    
    /**
     * イベント分類名称を取得します。
     * @return イベント分類名称
     */
    public String getEventBnruiName() {
        return convString(eventBnruiName);
    }
    /**
     * イベント分類名称を設定します。
     * @param eventBnruiName イベント分類名称
     */
    public void setEventBnruiName(String eventBnruiName) {
        this.eventBnruiName = eventBnruiName;
    }
    
    /**
     * イベントソートキーを取得します。
     * @return イベントソートキー
     */
    public String getEventSortCd() {
        return convString(eventSortCd);
    }
    /**
     * イベントソートキーを設定します。
     * @param eventSortCd イベントソートキー
     */
    public void setEventSortCd(String eventSortCd) {
        this.eventSortCd = eventSortCd;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return convString(gyotaiKbn);
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * イベントパターンコードを取得します。
     * @return イベントパターンコード
     */
    public String getEventPatternCd() {
        return convString(eventPatternCd);
    }
    /**
     * イベントパターンコードを設定します。
     * @param eventPatternCd イベントパターンコード
     */
    public void setEventPatternCd(String eventPatternCd) {
        this.eventPatternCd = eventPatternCd;
    }
    
    /**
     * イベントパターン名称を取得します。
     * @return イベントパターン名称
     */
    public String getEventPatternName() {
        return convString(eventPatternName);
    }
    /**
     * イベントパターン名称を設定します。
     * @param eventPatternName イベントパターン名称
     */
    public void setEventPatternName(String eventPatternName) {
        this.eventPatternName = eventPatternName;
    }
    
    /**
     * 変更日を取得します。
     * @return 変更日
     */
    public String getHenkoDt() {
        return convString(henkoDt);
    }
    /**
     * 変更日を設定します。
     * @param henkoDt 変更日
     */
    public void setHenkoDt(String henkoDt) {
        this.henkoDt = henkoDt;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNotes() {
        return convString(notes);
    }
    /**
     * 備考を設定します。
     * @param notes 備考
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }    
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return convString(blockCd);
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return convString(blockName);
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    /**
     * イベント分類毎の、イベント種別の数のMAXを取得します。
     * @return イベント分類毎の、イベント種別の数のMAX
     */
    public String getEventMaxCnt() {
        return eventMaxCnt;
    }
    /**
     * イベント分類毎の、イベント種別の数のMAXを設定します。
     * @param eventMaxCnt イベント分類毎の、イベント種別の数のMAX
     */
    public void setEventMaxCnt(String eventMaxCnt) {
        this.eventMaxCnt = eventMaxCnt;
    }
    
    
    /**
     * イベントステータス名称取得。(CSV表示用)
     * @return
     */
    public String getEventStatusName() {
        String eventStatusName = "";

        if ("0".equals(getEventStatus())) {
            eventStatusName = "未実施";
        }
        else if ("1".equals(getEventStatus())) {
            eventStatusName = "実施";
        }
        else if ("2".equals(getEventStatus())) {
            eventStatusName = "一部実施";
        }
        else if ("3".equals(getEventStatus())) {
            eventStatusName = "未設定";
        }
        return eventStatusName;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
}
