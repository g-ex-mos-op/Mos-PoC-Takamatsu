/*
 * 2007/02/09 オーナー選択時に解約済オーナーの場合、エラーにする。（本部ユーザーのみ）
 */
package jp.co.isid.mos.bird.entry.eventlist.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.entry.eventlist.action.EventListEntryAction;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListConditionDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListResultDto;
import jp.co.isid.mos.bird.entry.eventlist.logic.GetEntryMasterListLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author xyuchida
 *
 */
public class EventListEntryActionImpl implements EventListEntryAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN091A01";
    public static final String callOwnerSearch_ACTION_ID = "BEN091A02";
    public static final String selectOwner_ACTION_ID = "BEN091A03";
    public static final String selectEvent_ACTION_ID = "BEN091A04";
    public static final String back_ACTION_ID = "BEN091A05";

    /** 定数定義 */
    private static final String VIEW_ID_EVENT_LIST_ENTRY = "BEN091V01";
    private static final String VIEW_ID_ONER_SEARCH = "BCO006V01";
    private static final String COMPANY_CD_DEFAULT = "00";
    private static final String USERTYPE_CD_ONER = "02";

    /** Logic */
    private GetEntryMasterListLogic getEntryMasterListLogic;
    private GetOnerLogic getOnerLogic;

    /** DTO */
    private EventListDto eventListDto;
    private EventListConditionDto eventListConditionDto;
    private EventListResultDto eventListResultDto;
    private PullDownMenuDto pullDownMenuDto;
    private OwnerSearchDto ownerSearchDto;

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

            // オーナーコード初期値決定
            String onerCd = null;
            int viewMode = EventListConditionDto.VIEWMODE_ONERINPUT;
            String usertypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
            if (usertypeCd.equals(USERTYPE_CD_ONER)) {
                viewMode = EventListConditionDto.VIEWMODE_EVENTLIST;
                // 自オーナーコード取得
                for (Iterator it = getBirdUserInfo().getUserOner().iterator(); it.hasNext();) {
                    UIUserOner uIUserOner = (UIUserOner) it.next();
                    if (uIUserOner.getCompanyCd().equals(companyCd)) {
                        onerCd = uIUserOner.getOnerCd();
                        break;
                    }
                }
            }

            // 条件項目初期化
            getEventListConditionDto().setCompanyCd(companyCd);
            getEventListConditionDto().setOnerCd(onerCd);
            getEventListConditionDto().setBunrui(getBunrui());

            // 画面表示モード設定
            getEventListConditionDto().setViewMode(viewMode);

            if (usertypeCd.equals(USERTYPE_CD_ONER)) {
                // 検索
                getEventListResultDto().setEventList(doSearch());
            }

        } else if (getEventListDto().getReturnKind() > 0) {
            // 各画面からの戻り処理
            getEventListDto().setReturnKind(EventListDto.RETURNKIND_INIT);

            // ウインドウID復元
            getEventListConditionDto().setWindowId(getEventListDto().getWindowId());

            // 検索
            getEventListResultDto().setEventList(doSearch());

        } else if (getOwnerSearchDto().getReturnKind() > 0) {
            // オーナー選択からの戻り処理
            getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);

            // ウインドウID復元
            getEventListConditionDto().setWindowId(getOwnerSearchDto().getWindowId());

            // 決定ボタン押下
            if (getOwnerSearchDto().isActionFlag()) {
                getOwnerSearchDto().setActionFlag(false);
                // 選択オーナーコード取得
                getEventListConditionDto().setOnerCd(getOwnerSearchDto().getOnerCd());
                // オーナーコードチェック ---2007/02/13 解約済みチェック追加
                validateOnerCd(getEventListConditionDto().getCompanyCd(), getEventListConditionDto().getOnerCd());
                // 画面表示モード変更
                getEventListConditionDto().setViewMode(EventListConditionDto.VIEWMODE_EVENTLIST);
                // 検索
                getEventListResultDto().setEventList(doSearch());
            }

        } else if (getEventListConditionDto().getViewMode() == EventListConditionDto.VIEWMODE_EVENTLIST) {
            // 検索
            getEventListResultDto().setEventList(doSearch());
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * オーナー選択呼出
     * 
     * @return 画面遷移情報
     */
    public String callOwnerSearch() {
        // オーナー選択呼出パラメータ設定
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setInitFlag(true);
        List companyCdList = new ArrayList();
        companyCdList.add(getEventListConditionDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(companyCdList);
        getOwnerSearchDto().setNavigationCase(VIEW_ID_EVENT_LIST_ENTRY);
        getOwnerSearchDto().setNeedReturnKind(true);
        getOwnerSearchDto().setWindowId(getEventListConditionDto().getWindowId());
        // オーナー選択へ遷移
        return VIEW_ID_ONER_SEARCH;
    }

    /**
     * オーナー選択
     * 
     * @return 画面遷移情報
     */
    public String selectOwner() {
        // オーナーコードチェック
        validateOnerCd(getEventListConditionDto().getCompanyCd(), getEventListConditionDto().getOnerCd());
        // 画面表示モード変更
        getEventListConditionDto().setViewMode(EventListConditionDto.VIEWMODE_EVENTLIST);
        // 検索処理はinitialize()にて実行する
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
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {
        // 画面表示モード変更
        getEventListConditionDto().setViewMode(EventListConditionDto.VIEWMODE_ONERINPUT);
        return null;
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

    /**
     * 入力チェック
     * 
     * @param onerCd オーナーコード
     */
    private void validateOnerCd(String companyCd, String onerCd) {
        // 必須チェック
        if (onerCd == null || onerCd.trim().length() == 0) {
            throw new NoInputException("オーナーコード", "onerCd", 0);
        }
        // フォーマットチェック
        CodeVerifier codeVerifier = new CodeVerifier(5, false);
        codeVerifier.setNullable(false);
        if (!codeVerifier.validate(onerCd)) {
            throw new GenericMessageException("対象オーナーは半角数字5桁以内で入力して下さい。", "onerCd", 0);
        }
//--- update start 2007/02/09 解約日のチェックを追加
//        // 存在チェック
//        if (getGetOnerLogic().execute(companyCd, onerCd) == null) {
//            throw new NotExistException("オーナーコード", "onerCd", 0);
//        }
        MstOner mstOner = getGetOnerLogic().execute(companyCd, onerCd);
        // 存在チェック
        if (mstOner == null) {
            throw new NotExistException("オーナーコード", "onerCd", 0);
        }
        // 解約チェック
        if (getBirdDateInfo().getSysDate().compareTo(mstOner.getKeiyakuEnd()) > 0) {
            throw new CannotExecuteException("解約済みオーナーは選択");
        }
//--- update end
    }

    public GetEntryMasterListLogic getGetEntryMasterListLogic() {
        return getEntryMasterListLogic;
    }

    public void setGetEntryMasterListLogic(
            GetEntryMasterListLogic getEntryMasterListLogic) {
        this.getEntryMasterListLogic = getEntryMasterListLogic;
    }

    public GetOnerLogic getGetOnerLogic() {
        return getOnerLogic;
    }

    public void setGetOnerLogic(GetOnerLogic getOnerLogic) {
        this.getOnerLogic = getOnerLogic;
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

    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
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
