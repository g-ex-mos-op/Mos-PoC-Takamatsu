/*
 * 作成日: 2006/12/22
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.action.impl;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.action.ConfirmAction;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListReqDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 全国大会申込状況確認 
 * 確認画面アクション
 * 
 * @author xkinu
 *   
 */
public class ConfirmActionImpl implements ConfirmAction {
    
    /** アクションID：初期処理 */
    public static final String initialize_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A11";
    /** アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A12";
    /** アクションID：戻る処理 */
    public static final String back_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A13";
    /** アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A14";
    /** 共通DTO【SV検索】 */
    private SvSearchDto svSearchDto;
    /** 共通DTO【各種イベント申込状況確認】*/
    private EventListDto eventListDto;
    /** DTO【全国大会申込状況確認】*/
    private NationalViewListDto nationalViewListDto;
    /** DTO【申込状況確認リクエスト用】*/
    private NationalViewListReqDto nationalViewListReqDto;
    /**
     * ロジック【申込状況検索】
     */
    private SearchLogic nationalViewListSearchLogic;
    /**
     * @return nationalViewListSearchLogic を戻します。
     */
    public SearchLogic getNationalViewListSearchLogic() {
        return nationalViewListSearchLogic;
    }
    /**
     * @param nationalViewListSearchLogic 設定する nationalViewListSearchLogic。
     */
    public void setNationalViewListSearchLogic(
            SearchLogic nationalViewListSearchLogic) {
        this.nationalViewListSearchLogic = nationalViewListSearchLogic;
    }
    /**
     * @return nationalViewListDto を戻します。
     */
    public NationalViewListDto getNationalViewListDto() {
        return nationalViewListDto;
    }
    /**
     * @param nationalViewListDto 設定する nationalViewListDto。
     */
    public void setNationalViewListDto(
            NationalViewListDto nationalViewListDto) {
        this.nationalViewListDto = nationalViewListDto;
    }
    /**
     * 初期化処理
     * １．SV選択画面から遷移してきた場合。
     * １－１．DTO【申込状況確認】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
     * １－２．SVを選択後遷移してきた場合。
     * １－３．DTO【SV選択】.遷移区分を初期値に戻す。
     * １－４．DTO【SV選択】.クリア処理を実行する。
     * ２．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
     */
    public String initialize() {
        NationalViewListUtil.actionInitialize(getNationalViewListDto(), getNationalViewListReqDto(), getNewSvSearchDto());
        int windowId = getNationalViewListReqDto().getWindowId();
        //再表示対象の検索結果データが存在しない可能性があるため、
        //表示対象の検索結果データが存在チェックを行い、無い場合は再検索を行う。
        if(!getNationalViewListDto().isExistSearchData(windowId)) {
            //すでにダウンロードの対象検索データが削除されている場合、再度検索処理を実行する。
            NationalViewListUtil.actionSearchCsvData(getBirdUserInfo(), getBirdDateInfo()
                        , getNationalViewListDto(), getNationalViewListReqDto(), getNationalViewListSearchLogic());
        }
        //２．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
        getNationalViewListDto().copyData(getNationalViewListReqDto());
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
                NationalViewListUtil.VIEW_ID_CONFIRM
                , getNationalViewListDto(), getNationalViewListReqDto(), getNewSvSearchDto());
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
     * 再検索ボタン処理
     */
    public String search() {
        //変数WindowID設定
        int windowId = getNationalViewListReqDto().getWindowId();
        try{
            NationalViewListUtil.actionSearch(getBirdUserInfo(), getBirdDateInfo()
                    , getNationalViewListDto(), getNationalViewListReqDto(), getNationalViewListSearchLogic());
        } catch (ApplicationException appEx) {
            if (appEx instanceof NoResultException) {
            }else{
                //該当データがない場合、複数ウィンドウ機能の各情報量制御機能で
                //再表示対象の検索結果データが存在しない可能性があるため、
                //表示対象の検索結果データが存在チェックを行い、無い場合は再検索を行う。
                if(!getNationalViewListDto().isExistSearchData(windowId)) {
                    //すでにダウンロードの対象検索データが削除されている場合、再度検索処理を実行する。
                    try {
                        NationalViewListUtil.actionSearchCsvData(getBirdUserInfo(), getBirdDateInfo()
                                , getNationalViewListDto(), getNationalViewListReqDto(), getNationalViewListSearchLogic());
                    }catch(NoResultException e) {                       
                    }
                }
            }
            throw appEx;
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
     * @return eventListDto を戻します。
     */
    public EventListDto getEventListDto() {
        return eventListDto;
    }
    /**
     * @param eventListDto 設定する eventListDto。
     */
    public void setEventListDto(EventListDto eventListDto) {
        this.eventListDto = eventListDto;
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