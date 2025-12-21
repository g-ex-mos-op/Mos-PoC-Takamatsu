/**
 * 
 */
package jp.co.isid.mos.bird.entry.eventlist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.eventlist.action.EventListRefAction;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListConditionDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListResultDto;
import jp.co.isid.mos.bird.entry.eventlist.logic.GetEntryMasterListLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author xyuchida
 *
 */
public class EventListRefActionImpl implements EventListRefAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN092A01";
    public static final String selectEvent_ACTION_ID = "BEN092A02";

    /** 定数定義 */
    private static final String COMPANY_CD_DEFAULT = "00";

    /** Logic */
    private GetEntryMasterListLogic getEntryMasterListLogic;

    /** DTO */
    private EventListDto eventListDto;
    private EventListConditionDto eventListConditionDto;
    private EventListResultDto eventListResultDto;
    private PullDownMenuDto pullDownMenuDto;

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;

    /** param */
    private String bunrui;
    private String entryCd;
    private String entryYear;
    private String entryKai;
    private String viewId;
    private int entryTermKind;

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        if (getPullDownMenuDto().isClearFlg()) {
            // 画面初期表示処理
            getPullDownMenuDto().setClearFlg(false);

            // 共通情報取得
            S2Container container = SingletonS2ContainerFactory.getContainer();
            setBirdUserInfo((BirdUserInfo) container.getComponent(BirdUserInfo.class));
            setBirdDateInfo((BirdDateInfo) container.getComponent(BirdDateInfo.class));

            // ウインドウID生成
            getEventListConditionDto().updateWindowid();

            // 会社コード固定
            String companyCd = COMPANY_CD_DEFAULT;

            // 条件項目初期化
            getEventListConditionDto().setCompanyCd(companyCd);
            getEventListConditionDto().setOnerCd(null);
            getEventListConditionDto().setBunrui(getBunrui());

        } else if (getEventListDto().getReturnKind() > 0) {
            // 各画面からの戻り処理
            getEventListDto().setReturnKind(EventListDto.RETURNKIND_INIT);

            // ウインドウID復元
            getEventListConditionDto().setWindowId(getEventListDto().getWindowId());
        }

        // 検索
        getEventListResultDto().setEventList(doSearch());

        // 自画面へ遷移
        return null;
    }

    /**
     * イベント選択
     * 
     * @return 画面遷移情報
     */
    public String selectEvent() {
        // 各画面起動パラメータ設定
        getEventListDto().clear();
        getEventListDto().setInitFlag(EventListDto.INITFLAG_ON);
        getEventListDto().setCompanyCd(getEventListConditionDto().getCompanyCd());
        getEventListDto().setOnerCd(getEventListConditionDto().getOnerCd());
        getEventListDto().setEntryCd(getEntryCd());
        getEventListDto().setEntryYear(getEntryYear());
        getEventListDto().setEntryKai(getEntryKai());
        getEventListDto().setEntryTermKind(getEntryTermKind());
        getEventListDto().setWindowId(getEventListConditionDto().getWindowId());
        return getViewId();
    }

    /**
     * 検索
     * 
     * @return イベントリスト
     */
    private List doSearch() {
        // 検索条件設定
        List entryCdList = getEventListConditionDto().getEntryCdList();
        String bunrui = getEventListConditionDto().getBunrui();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String sysDate = getBirdDateInfo().getSysDate();
        // イベントリスト取得
        return getGetEntryMasterListLogic().execute(entryCdList, bunrui, userTypeCd, sysDate);
    }

    public GetEntryMasterListLogic getGetEntryMasterListLogic() {
        return getEntryMasterListLogic;
    }

    public void setGetEntryMasterListLogic(
            GetEntryMasterListLogic getEntryMasterListLogic) {
        this.getEntryMasterListLogic = getEntryMasterListLogic;
    }

    public EventListDto getEventListDto() {
        return eventListDto;
    }

    public void setEventListDto(EventListDto eventListDto) {
        this.eventListDto = eventListDto;
    }

    public EventListConditionDto getEventListConditionDto() {
        return eventListConditionDto;
    }

    public void setEventListConditionDto(EventListConditionDto eventListConditionDto) {
        this.eventListConditionDto = eventListConditionDto;
    }

    public EventListResultDto getEventListResultDto() {
        return eventListResultDto;
    }

    public void setEventListResultDto(EventListResultDto eventListResultDto) {
        this.eventListResultDto = eventListResultDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public String getBunrui() {
        return bunrui;
    }

    public void setBunrui(String bunrui) {
        this.bunrui = bunrui;
    }

    public String getEntryCd() {
        return entryCd;
    }

    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public String getEntryKai() {
        return entryKai;
    }

    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public int getEntryTermKind() {
        return entryTermKind;
    }

    public void setEntryTermKind(int entryTermKind) {
        this.entryTermKind = entryTermKind;
    }
}
