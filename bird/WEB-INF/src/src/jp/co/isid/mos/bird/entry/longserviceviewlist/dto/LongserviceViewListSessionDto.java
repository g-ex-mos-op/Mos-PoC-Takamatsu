/*
 * 作成日: 2006/12/22
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 永年勤続申請状況確認Dto
 * 
 * @author xamaruyama
 */
public class LongserviceViewListSessionDto implements DownloadDto, CsvOutputDto{

    // 制限ユーザ判別用フラグ(初期化しない)
    private boolean limit;

    // システム日付(初期化しない)
    private String sysdate;

    // ユーザID(初期化しない)
    private String userId;

    // エントリーコード
    private Map entryCd = new LinkedHashMap();
    
    // エントリー年
    private Map entryYear = new LinkedHashMap();
    
    // エントリー回
    private Map entryKai = new LinkedHashMap();
    
    // 企業コード
    private Map companyCd = new LinkedHashMap();

    // 支部コード
    private String sibuCd;

    // 支部プルダウンで選ばれたもの
    private Map sibuChoice = new LinkedHashMap();
    
    /**
     * 永年勤続イベント情報エンティティー
     */
    private Map entityUILSViewListEvent = new LinkedHashMap();
    
    // 対象条件プルダウンで選ばれたもの
    private Map taishouJokenChoice = new LinkedHashMap();

    // SVコードを入力した場合
    private Map svCd = new LinkedHashMap();
    
    //共通画面から遷移してきたことを表すフラグ
    private int initFlg;

    //実行ボタンを再検索ボタンを判別するフラグ
    private Map buttonFlg = new LinkedHashMap();
    
    //複数ウィンドウになった際に消去するかどうかの結果画面
    private Map listApplyOner = new LinkedHashMap();

    // 対象条件プルダウンの内容
    private List taishouJokenPull = new ArrayList();
    
    // 支部プルダウンの内容
    private List sibu = new ArrayList();
    
    // 区分プルダウン
    private List Kbn = new ArrayList();
    
    // オーナーコード
    private Map onerCd = new LinkedHashMap();
    
    private Map resultFlg = new LinkedHashMap();
    
    // 再検索用SVコード
    private Map researchSvCd = new LinkedHashMap();
    
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    
    /**
     * 最大データ保持件数
     */
    private int maxSize;
    
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    
    /**
     * 値の初期化処理
     */
    public void sessionClear(int windowId) {
        setCompanyCd(windowId, null);
        setSibuCd(windowId, null);
        setSibuChoice(windowId, null);
        setTaishouJokenChoice(windowId, null);
        setSvCd(windowId, null);
        setentityUILSViewListEvent(windowId, null);
        setTable(windowId, null);
        setOnerCd(windowId, null);
        setResultFlg(windowId, null);
        setResearchSvCd(windowId, null);
    }

    // SVコードのsetterとgetter
    public String getSvCd(int windowId) {
        return (String)svCd.get(new Integer(windowId));
    }

    public void setSvCd(int windowId, String svCd) {
        this.svCd.put(new Integer(windowId), svCd);
    }

    public String getTaishouJokenChoice(int windowId) {
        return (String)taishouJokenChoice.get(new Integer(windowId));
    }

    public void setTaishouJokenChoice(int windowId, String taishouJokenChoice) {
        this.taishouJokenChoice.put(new Integer(windowId), taishouJokenChoice);
    }

    // 会社コードのsetterとgetter
    public String getCompanyCd(int windowId) {
        return (String)companyCd.get(new Integer(windowId));
    }

    public void setCompanyCd(int windowId, String companyCd) {
        this.companyCd.put(new Integer(windowId), companyCd);
    }

    // 支部コードのsetterとgetter
    public String getSibuCd(int windowId) {
        return sibuCd;
    }

    public void setSibuCd(int windowId, String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    public String getSibuChoice(int windowId) {
        return (String)sibuChoice.get(new Integer(windowId));
    }

    public void setSibuChoice(int windowId, String sibuChoice) {
        this.sibuChoice.put(new Integer(windowId), sibuChoice);
    }

    public int getInitFlg(int windowId) {
        return initFlg;
    }

    public void setInitFlg(int initFlg) {
        this.initFlg = initFlg;
    }
    
    /**
     * @return maxWindowId を戻します。
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }

    /**
     * @param maxWindowId 設定する maxWindowId。
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    /**
     * @return windowId を戻します。
     */
    public int getWindowId() {
        return windowId;
    }

    /**
     * @param windowId 設定する windowId。
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public int updateWindowid() {
       setWindowId(createWindowId());
       return getWindowId();
    }

    public boolean isEmptyentityUILSViewListEvent(int windowId) {
        if(getentityUILSViewListEvent(windowId) == null) {
            return true;
        }
        return false;
    }
    
    /**
     * 6世代目の結果部を消去するかの判断
     * @param List listApplyOner
     */
    public void setTable(int windowId, List listApplyOner) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listApplyOner.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listApplyOner.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listApplyOner.size() > getMaxSize()) {
            // 最古データを削除
            this.listApplyOner.remove(this.listApplyOner.keySet().toArray()[0]);
        }
        // リスト設定
        this.listApplyOner.put(new Integer(windowId), listApplyOner);
    }
    
    /**
     * @param entityUILSViewListEventを設定します。
     */
    public UILSViewListEvent getentityUILSViewListEvent(int windowId) {
        return (UILSViewListEvent)entityUILSViewListEvent.get(new Integer(windowId));
    }

    /**
     * @param entityUILSViewListEventを設定します。
     */
    public void setentityUILSViewListEvent(int windowId, UILSViewListEvent entityUIStatusInfo) {
        this.entityUILSViewListEvent.put(new Integer(windowId), entityUIStatusInfo);
    }
        
    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public List getTable(int windowId) {
        return (List)listApplyOner.get(new Integer(windowId));
    }
    
    public String getButtonFlg(int windowId) {
        return (String)buttonFlg.get(new Integer(windowId));
    }

    public void setButtonFlg(int windowId, String buttonFlg) {
        this.buttonFlg.put(new Integer(windowId), buttonFlg);
    }
    
    public String getEntryCd(int windowId) {
        return (String)entryCd.get(new Integer(windowId));
    }

    public void setEntryCd(int windowId, String entryCd) {
        this.entryCd.put(new Integer(windowId), entryCd);
    }

    public String getEntryKai(int windowId) {
        return (String)entryKai.get(new Integer(windowId));
    }

    public void setEntryKai(int windowId, String entryKai) {
        this.entryKai.put(new Integer(windowId), entryKai);
    }

    public String getEntryYear(int windowId) {
        return (String)entryYear.get(new Integer(windowId));
    }

    public void setEntryYear(int windowId, String entryYear) {
        this.entryYear.put(new Integer(windowId), entryYear);
    }
 
    /**
     * 最大データ保持件数を取得する<br>
     * @return int 最大データ保持件数
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * 最大データ保持件数を設定する<br>
     * @param maxSize 最大データ保持件数
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List getTaishouJokenPull() {
        return taishouJokenPull;
    }

    public void setTaishoJokenPull(List taishouJokenPull) {
        this.taishouJokenPull = taishouJokenPull;
    }
    
    // 支部プルダウン情報
    public List getSibu() {
        return sibu;
    }

    public void setSibu(List sibu) {
        this.sibu = sibu;
    }
    
    public List getKbn() {
        return Kbn;
    }

    public void setKbn(List Kbn) {
        this.Kbn = Kbn;
    }

    public String getOnerCd(int windowId) {
        return (String)onerCd.get(new Integer(windowId));
    }

    public void setOnerCd(int windowId, String onerCdint) {
        this.onerCd.put(new Integer(windowId), onerCd);
    }

    public Map getMapApplyOner() {
        return listApplyOner;
    }
    
    public String getResultFlg(int windowId) {
        return (String)resultFlg.get(new Integer(windowId));
    }

    public void setResultFlg(int windowId, String resultFlg) {
        this.resultFlg.put(new Integer(windowId), resultFlg);
    }

    public String getResearchSvCd(int windowId) {
        return (String)researchSvCd.get(new Integer(windowId));
    }

    public void setResearchSvCd(int windowId, String researchSvCd) {
        this.researchSvCd.put(new Integer(windowId), researchSvCd);
    }
    
}