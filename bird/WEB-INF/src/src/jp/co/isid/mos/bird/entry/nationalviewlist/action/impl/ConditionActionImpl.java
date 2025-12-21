/*
 * 作成日: 2006/12/20
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.action.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.action.ConditionAction;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListReqDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.CheckSvExistLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 申込状況確認
 * 条件画面 アクションクラス
 * 
 * @author xkinu
 */
public class ConditionActionImpl implements ConditionAction {
    
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A01";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A02";
    /* アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A03";
    /* アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A04";
    
    /** 共通DTO【各種イベント申込状況確認】*/
    private EventListDto eventListDto;
    /** 共通DTO【SV検索】 */
    private SvSearchDto svSearchDto;
    /** DTO【申込状況確認】*/
    private NationalViewListDto nationalViewListDto;
    /** DTO【申込状況確認リクエスト用】*/
    private NationalViewListReqDto nationalViewListReqDto;
    /**
     * ロジック【条件項目の取得】
     */
    private ConditionLogic nationalViewListConditionLogic;
    /**
     * ロジック【申込状況検索】
     */
    private SearchLogic nationalViewListSearchLogic;
    /**
     *  ロジック【SV存在チェック】
     */
    private CheckSvExistLogic nationalViewListCheckSvExistLogic;


    /**
     * 初期化処理
     * 
     * １．共通DTO【各種イベント申込状況確認】初期フラグが'1'の場合、下記の処理を行う。
     * 
     * ２．処理１以外の場合、下記の処理を行う。
     * 条件：SV選択画面から遷移してきた場合。
     * ２－１．DTO【申込状況確認】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
     * ２－２．SVを選択後遷移してきた場合。
     * ２－３．DTO【SV選択】.遷移区分を初期値に戻す。
     * ２－４．DTO【SV選択】.クリア処理を実行する。
     * 
     * ３．現ウィンドウID の保管データからDTO【申込状況確認】検索対象条件項目へ値の設定を行う。
     */
    public String initialize() {
        
        //変数WindowID設定
        int windowId = getNationalViewListReqDto().getWindowId();
        //１．共通DTO【各種イベント申込状況確認】初期フラグが'1'の場合、下記の処理を行う。
        if (getEventListDto().getInitFlag() == EventListDto.INITFLAG_ON) {
            
            //１－１． 複数WindowID設定
            windowId = getNationalViewListDto().createWindowId();           
            //１－３．共通画面から値を取得
            getNationalViewListDto().setTargetCompanyCd(getEventListDto().getCompanyCd());
            getNationalViewListDto().setTargetOnerCd(getEventListDto().getOnerCd());
            
            //１－４．フレームワークより値を取得
            getNationalViewListDto().setUserId(getBirdUserInfo().getUserID());
            getNationalViewListDto().setLimit(getBirdUserInfo().isLimit());
            getNationalViewListDto().setSysDate(getBirdDateInfo().getSysDate());
            
            //１－５．ロジック【条件項目の取得】を実行する。
            Map params = new HashMap();
            //パラメーター
            params.put(ConditionLogicImpl.PK_USERINFO, getBirdUserInfo());
            params.put(ConditionLogicImpl.PK_ENTRY_CD, getEventListDto().getEntryCd());
            params.put(ConditionLogicImpl.PK_ENTRY_YEAR, getEventListDto().getEntryYear());
            params.put(ConditionLogicImpl.PK_ENTRY_KAI, getEventListDto().getEntryKai());
            params.put(ConditionLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
            Map logigMap = getNationalViewListConditionLogic().execute(params);
            
            //１－６．処理２－３の戻り値から、[[会社情報]][[支部情報]]を取得しDTO【申込状況確認】へ設定する。
            getNationalViewListDto().setListCompanyCd((List)logigMap.get(ConditionLogicImpl.RK_LIST_COMPANY));
            getNationalViewListDto().setListSibuCd((List)logigMap.get(ConditionLogicImpl.RK_LIST_SIBU));
            
            //１－７．処理２－３の戻り値から、[[対象全国大会申込状況情報]]を取得する。
            List sqlKekka = (List)logigMap.get(ConditionLogicImpl.RK_LIST_STATUS_INFO);
            
            //１－８．処理２－５の結果の値を【DTO】対象全国大会申込状況情報エンティティーへ設定する。
            UIStatusInfo uIStatusInfo = null;
            if (sqlKekka != null && sqlKekka.size() > 0) {
                uIStatusInfo = (UIStatusInfo)sqlKekka.get(0);
            }
            getNationalViewListDto().setEntityStatusInfo(windowId, uIStatusInfo);
            
            //１－９．対象条件プルダウン作成
            List listTaishoJoken = ConditionTaishoJoken.getPullDownList();
            getNationalViewListDto().setListTaishoJoken(listTaishoJoken);
            
            //１－１０．区分プルダウン作成
            List kbnList = ConditionKbn.getPullDownListSanka();
            getNationalViewListDto().setListKbn(kbnList);

            //１－１１． 共通DTO【各種イベント申込状況確認】.初期起動フラグを初期値に設定する。
            getEventListDto().setInitFlag(EventListDto.INITFLAG_OFF);
            
        }
        // ２．SV選択画面から遷移してきた場合。
        else if(svSearchDto.getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            windowId = NationalViewListUtil.actionInitialize(getNationalViewListDto(), getNationalViewListReqDto(), getNewSvSearchDto());
        }
        //３．現ウィンドウID の保管データからDTO【申込状況確認】検索対象条件項目へ値の設定を行う。
        getNationalViewListDto().copyInitData(windowId, getNationalViewListReqDto());
        return null;
    }
    /**
     * SV検索ボタン処理
     * 
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．SV検索フォームViewIDをリターン
        return NationalViewListUtil.actionCallSvForm(
                NationalViewListUtil.VIEW_ID_CONDITION
                , getNationalViewListDto(), getNationalViewListReqDto()
                , getNewSvSearchDto());
    } 
    /**
     * 画面の｢戻る｣ボタンを押した場合
     * 
     * １．DTO【申込状況確認】クリア処理を行う。
     * ２．共通DTO【各種イベント申込状況確認】リターンタイプに’戻る’の値を設定する。
     * ３．共通各種イベント申込状況確認画面VIEWIDをリターンする。
     */
    public String back(){
        return NationalViewListUtil.actionBack(getNationalViewListDto(), getNationalViewListReqDto(), getEventListDto());
    }
    /**
     * 実行ボタン処理
     * 
     */
    public String search() {
        //変数WindowID設定
        int windowId = getNationalViewListReqDto().getWindowId();
        try{
            NationalViewListUtil.actionSearch(getBirdUserInfo(), getBirdDateInfo()
                    , getNationalViewListDto(), getNationalViewListReqDto(),  getNationalViewListSearchLogic());
        }
        finally{
            //３．区分プルダウンをデフォルトとして『参加』に設定し、DTO【リクエスト用】へ対象データを設定する。
            getNationalViewListDto().setCsvKbn(windowId, ConditionKbn.VALUE_ENTRY);
            getNationalViewListDto().copyData(getNationalViewListReqDto());
        }
        //４．照会画面VIEWIDをリターンする。
        return NationalViewListUtil.VIEW_ID_CONFIRM;
    }
    /**
     * 
     * @return
     */
    public NationalViewListDto getNationalViewListDto() {
        return nationalViewListDto;
    }
    
    public void setNationalViewListDto(NationalViewListDto nationalViewListDto) {
        this.nationalViewListDto = nationalViewListDto;
    }
    /**
     * Seaser2Containaer取得処理
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    /**
     * BIRDユーザー情報取得処理
     * 
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     * 
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }


    public EventListDto getEventListDto() {
        return eventListDto;
    }


    public void setEventListDto(EventListDto eventListDto) {
        this.eventListDto = eventListDto;
    }
    /**
     * SV検索DTO取得処理
     * @return svSearchDto
     */
    public SvSearchDto getNewSvSearchDto() {
        return svSearchDto;
    }
    
    /**
     * SV検索DTO設定処理
     * 
     * @param svSearchDto
     */
    public void setNewSvSearchDto(SvSearchDto dto) {
        this.svSearchDto = dto;
    }


    /**
     * @return nationalViewListConditionLogic を戻します。
     */
    public ConditionLogic getNationalViewListConditionLogic() {
        return nationalViewListConditionLogic;
    }


    /**
     * @param nationalViewListConditionLogic 設定する nationalViewListConditionLogic。
     */
    public void setNationalViewListConditionLogic(
            ConditionLogic nationalViewListConditionLogic) {
        this.nationalViewListConditionLogic = nationalViewListConditionLogic;
    }
    /**
     * @return nationalViewListSearchLogic を戻します。
     */
    public SearchLogic getNationalViewListSearchLogic() {
        return nationalViewListSearchLogic;
    }
    /**
     * @param searchLogic を設定する nationalViewListSearchLogic。
     */
    public void setNationalViewListSearchLogic(
            SearchLogic searchLogic) {
        this.nationalViewListSearchLogic = searchLogic;
    }
    /**
     * SV存在チェックロジック取得処理
     * 
     * @return nationalViewListCheckSvExistLogic を戻します。
     */
    public CheckSvExistLogic getNationalViewListCheckSvExistLogic() {
        return nationalViewListCheckSvExistLogic;
    }
    /**
     * SV存在チェックロジック設定処理
     * 
     * @param logic 設定するロジック。
     */
    public void setNationalViewListCheckSvExistLogic(CheckSvExistLogic logic) {
        this.nationalViewListCheckSvExistLogic = logic;
    }
    /**
     * @return nationalViewListReqDto を戻します。
     */
    public NationalViewListReqDto getNationalViewListReqDto() {
        return nationalViewListReqDto;
    }
    /**
     * @param nationalViewListReqDto 設定する nationalViewListReqDto。
     */
    public void setNationalViewListReqDto(
            NationalViewListReqDto nationalViewListReqDto) {
        this.nationalViewListReqDto = nationalViewListReqDto;
    }
}