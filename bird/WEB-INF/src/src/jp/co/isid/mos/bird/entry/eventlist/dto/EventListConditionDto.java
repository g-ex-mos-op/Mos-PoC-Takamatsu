package jp.co.isid.mos.bird.entry.eventlist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xyuchida
 *
 */
public class EventListConditionDto {

    /**
     * 画面表示モード定数
     */
    public static final int VIEWMODE_EVENTLIST = 0;
    public static final int VIEWMODE_ONERINPUT = 1;

    /**
     * 会社コード
     */
    private Map companyCd = new HashMap();
    /**
     * オーナーコード
     */
    private Map onerCd = new HashMap();
    /**
     * エントリーコードリスト
     */
    private List entryCdList;
    /**
     * 画面分類
     */
    private Map bunrui = new HashMap();
    /**
     * 画面表示モード
     *  = 0 : イベント一覧表示
     *  = 1 : オーナーコード入力フォーム
     */
    private Map viewMode = new HashMap();

    /**
     * ウインドウID
     */
    private int windowId;
    /**
     * 最大ウインドウID
     */
    private int maxWindowId;

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
    public List getEntryCdList() {
        return entryCdList;
    }
    public void setEntryCdList(List entryCdList) {
        this.entryCdList = entryCdList;
    }
    public String getBunrui() {
        return (String) bunrui.get(new Integer(getWindowId()));
    }
    public void setBunrui(String bunrui) {
        this.bunrui.put(new Integer(getWindowId()), bunrui);
    }
    public int getViewMode() {
        return ((Integer) viewMode.get(new Integer(getWindowId()))).intValue();
    }
    public void setViewMode(int viewMode) {
        this.viewMode.put(new Integer(getWindowId()), new Integer(viewMode));
    }

    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public int getMaxWindowId() {
        return maxWindowId;
    }
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
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
}
