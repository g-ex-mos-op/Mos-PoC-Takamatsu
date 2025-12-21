package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.sql.Timestamp;
import java.util.List;

public class MstEventStatus {
    
    public static final String TABLE = "BM22EVNT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eventCd_COLUMN = "EVENT_CD";
    
    public static final String eventName_COLUMN = "EVENT_NAME";
    
    public static final String eventStatus_COLUMN = "EVENT_STATUS";
    
    public static final String eventStartDt_COLUMN = "EVENT_START_DT";
    
    public static final String eventEndDt_COLUMN = "EVENT_END_DT";
    
    public static final String eventBnrui_COLUMN = "EVENT_BNRUI";
    
    public static final String eventBnruiName_COLUMN = "EVENT_BNRUI_NAME";
    
    public static final String eventSortCd_COLUMN = "EVENT_SORT_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    // 20060517 追加
    public static final String eventPatternCd_COLUMN = "EVENT_PATTERN_CD";
    // 20060517 追加
    public static final String eventPatternName_COLUMN = "EVENT_PATTERN_NAME";
    // 20060607 追加
    public static final String henkoDt_COLUMN = "HENKO_DT";
    // 20060607 追加
    public static final String notes_COLUMN = "NOTES";
    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
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
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    // 20060517追加    
    /**                                
     * イベントパターンコード          
     */                                
    private String eventPatternCd;
    
    // 20060517追加                        
    /**                                
     * イベントパターン名称            
     */                                
    private String eventPatternName;
    
    // 20060517 追加
    /**
     * イベントパターンリスト
     */
    private List listEventPatternCd;
    // 20060607 追加
    /**
     * 変更日
     */
    private String henkoDt;
    
    // 20060607 追加
    /**
     * 備考
     */
    private String notes;
    
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
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return convString(lastUser);
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return convString(lastPgm);
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
//  20060517 追加 start ----------------------------------------    
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
     * イベントパターンリストを取得します。
     * @return listEventPatternCd イベントパターンリスト
     */
    public List getListEventPatternCd() {
        return listEventPatternCd;
    }
    
    /**
     * イベントパターンリストを設定します。
     * @param listEventPatternCd　イベントパターンリスト
     */
    public void setListEventPatternCd(List listEventPatternCd) {
        this.listEventPatternCd = listEventPatternCd;
    }
    
    
// 20060517 追加 end -------------------------------------------   

//  20060607 追加 start -----------------------------------------    
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
//  20060607 追加 end ------------------------------------------- 
     
    /**
     * サービス実施状況タブ 確認画面実施状況表示用
     * @return
     */
    public String getEventStatusName() {
        String name = "";

        if ("0".equals(getEventStatus())) {
            name = "未実施";
        }
        else if ("1".equals(getEventStatus())) {
            name = "実施";
        }
        else if ("2".equals(getEventStatus())) {
            name = "一部実施";
        }
        else if ("3".equals(getEventStatus())) {
            name = "未設定";
        }
        return name;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
	public String getFirstPgm() {
		return firstPgm;
	}
	public void setFirstPgm(String firstPgm) {
		this.firstPgm = firstPgm;
	}
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	public String getFirstUser() {
		return firstUser;
	}
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
}
