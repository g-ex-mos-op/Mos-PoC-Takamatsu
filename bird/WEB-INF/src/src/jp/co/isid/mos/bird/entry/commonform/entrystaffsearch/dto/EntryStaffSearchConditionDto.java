package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * スタッフ選択 内部用DTO
 * @author xnkusama
 */
public class EntryStaffSearchConditionDto {

    private Map companyCd = new HashMap();
    private Map onerCd = new HashMap();
    private Map mode = new HashMap();
    private Map entryYear = new HashMap();
    private Map entryKai = new HashMap();

    /* ウインドウID */
    private int windowId;
    /* 最大ウインドウID */
    private int maxWindowId;
    private int maxSize;
    /* 呼出元画面ウインドウID */
    private Map parentViewWindowId = new HashMap();
    /* 遷移元ページ */
    private Map navigationCase = new HashMap();

    public String getCompanyCd() {
        return (String) companyCd.get(new Integer(getWindowId()));
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd.put(new Integer(getWindowId()), companyCd);
    }

    public String getOnerCd() {
        return (String) onerCd.get(new Integer(getWindowId()));
    }

    public void setOnerCd(String onerCd) {
        this.onerCd.put(new Integer(getWindowId()), onerCd);
    }

    public String getEntryYear() {
        return (String) entryYear.get(new Integer(getWindowId()));
    }

    public void setEntryYear(String entryYear) {
        this.entryYear.put(new Integer(getWindowId()), entryYear);
    }

    public String getEntryKai() {
        return (String) entryKai.get(new Integer(getWindowId()));
    }

    public void setEntryKai(String entryKai) {
        this.entryKai.put(new Integer(getWindowId()), entryKai);
    }

    public int getMode() {
        return ((Integer)mode.get(new Integer(getWindowId()))).intValue();
    }

    public void setMode(int mode) {
        this.mode.put(new Integer(getWindowId()), new Integer(mode));
    }
    
    public void clear() {

        setCompanyCd(null);
        setOnerCd(null);
        setMode(0);
        setEntryYear(null);
        setEntryKai(null);
        
        setParentViewWindowId(0);
        setNavigationCase(null);
    }

    /**
     * ウインドウID取得
     * @return ウインドウID
     */
    public int getWindowId() {
        return windowId;
    }
    /**
     * ウインドウID設定
     * @param windowId ウインドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    /**
     * 最大ウインドウID取得
     * @return 最大ウインドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    /**
     * 最大ウインドウID設定
     * @param maxWindowId 最大ウインドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    /**
     * 最大データ保持件数取得
     * @return 最大データ保持件数
     */
    public int getMaxSize() {
        return maxSize;
    }
    /**
     * 最大データ保持件数設定
     * @param maxSize 最大データ保持件数
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    /**
     * ウインドウID生成
     * @return 採番されたウインドウID
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    /**
     * ウインドウID更新
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    /**
     * 呼出元画面ウインドウID取得
     * @return
     */
    public int getParentViewWindowId() {
        return ((Integer) parentViewWindowId.get(new Integer(getWindowId()))).intValue();
    }
    /**
     * 呼出元画面ウインドウID設定
     * @param parentViewWindowId
     */
    public void setParentViewWindowId(int parentViewWindowId) {
        this.parentViewWindowId.put(new Integer(getWindowId()), new Integer(parentViewWindowId));
    }
    /**
     * 遷移元ページ取得
     * @return 遷移元ページ
     */
    public String getNavigationCase() {
        return (String) navigationCase.get(new Integer(getWindowId()));
    }
    /**
     * 遷移元ページ設定
     * @param navigationCase 遷移元ページ
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase.put(new Integer(getWindowId()), navigationCase);
    }
}