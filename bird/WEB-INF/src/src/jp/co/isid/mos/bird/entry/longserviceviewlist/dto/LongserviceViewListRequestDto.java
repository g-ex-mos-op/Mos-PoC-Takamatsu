/*
 * 作成日: 2006/12/22
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 永年勤続申請状況確認Dto
 * 
 * @author xamaruyama
 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
 *                    SQLでuserIdのパラメータありきで設定されているため、
 *                    常に支部制限のユーザは該当データ無しになっていました。
 *                    パラメータuserIdを追加し、対応しました。
 */
public class LongserviceViewListRequestDto implements DownloadDto, CsvOutputDto{

    // システム日付(初期化しない)
    private String sysdate;
    
    // ログインユーザーID
    private String userId;
    
    // 制限ユーザ判別用フラグ(初期化しない)
    private boolean limit;

    // エントリーコード
    private String entryCd;
    
    // エントリー年
    private String entryYear;
    
    // エントリー回
    private String entryKai;
    
    // 企業コード
    private String companyCd;
        
    // 支部コード
    private String sibuCd;

    // 区分プルダウンで選ばれたもの
    private String KbnChoice;
    
    // 結果画面
    private List Table;
    
    // 対象条件プルダウンで選ばれたもの
    private String taishouJokenChoice;
    
    // SVコードを入力した場合
    private String svCd;
    
    //実行ボタンを再検索ボタンを判別するフラグ
    private String buttonFlg;
    
    /**
     * 永年勤続イベント情報エンティティー
     */
    private UILSViewListEvent entityUILSViewListEvent;
    
    // 支部プルダウンで選ばれたもの
    private String sibuChoice;
    
    private String resultFlg;
    
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    
    /**
     * 値の初期化処理
     */
    public void requestClear() {
        setSibuCd(null);
        setKbnChoice(null);
        setTable(null);
    }
    
    public String getKbnChoice() {
        return KbnChoice;
    }

    public void setKbnChoice(String KbnChoice) {
        this.KbnChoice = KbnChoice;
    }

    // 支部コードのsetterとgetter
    public String getSibuCd() {
        return sibuCd;
    }

    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public List getTable() {
        return Table;
    }
    
    public void setTable(List Table) {
        this.Table = Table;
    }
    
    public String getTaishouJokenChoice() {
        return taishouJokenChoice;
    }

    public void setTaishouJokenChoice(String taishouJokenChoice) {
        this.taishouJokenChoice = taishouJokenChoice;
    }

    public String getSvCd() {
        return svCd;
    }

    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }

    public String getButtonFlg() {
        return buttonFlg;
    }

    public void setButtonFlg(String buttonFlg) {
        this.buttonFlg = buttonFlg;
    }

    public UILSViewListEvent getEntityUILSViewListEvent() {
        return entityUILSViewListEvent;
    }

    public void setEntityUILSViewListEvent(UILSViewListEvent entityUILSViewListEvent) {
        this.entityUILSViewListEvent = entityUILSViewListEvent;
    }

    public String getSibuChoice() {
        return sibuChoice;
    }

    public void setSibuChoice(String sibuChoice) {
        this.sibuChoice = sibuChoice;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getEntryCd() {
        return entryCd;
    }

    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }

    public String getEntryKai() {
        return entryKai;
    }

    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }
    
    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public String getResultFlg() {
        return resultFlg;
    }

    public void setResultFlg(String resultFlg) {
        this.resultFlg = resultFlg;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}