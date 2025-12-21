/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.action.LongserviceViewListConditionAction;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dto.LongserviceViewListRequestDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dto.LongserviceViewListSessionDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.LongserviceViewListConditionLogic;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.LongserviceViewListResultLogic;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl.LongserviceViewListConditionLogicImpl;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl.LongserviceViewListResultLogicImpl;
import jp.co.isid.mos.bird.entry.longserviceviewlist.util.LongserviceViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 永年勤続申請状況確認 条件画面 アクションクラス
 * 
 * @author xamaruyama
 */
public class LongserviceViewListConditionActionImpl implements LongserviceViewListConditionAction {
    
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = LongserviceViewListUtil.SCREEN_ID+"A01";
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = LongserviceViewListUtil.SCREEN_ID+"A02";
    /* アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = LongserviceViewListUtil.SCREEN_ID+"A03";
    /* アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = LongserviceViewListUtil.SCREEN_ID+"A04";
    
    /**
     * 画面の実行ボタンと再検索ボタンの切り替え
     * 
     * JIKKOU(実行):0　SAIKENSAKU(再検索):1
     */
    private static final String JIKKOU = "0";
    private static final String SAIKENSAKU = "1";
    
    /** 
     * 結果表示の切り替え
     * 
     * OK:表示 NG:非表示
     */
    private static final String OK = "0";
    private static final String NG = "1";
    
    /** Logic【イベント情報一覧】*/
    private LongserviceViewListConditionLogic longserviceViewListConditionLogic;
    /** Logic【オーナー別申請状況一覧】*/
    private LongserviceViewListResultLogic longserviceViewListResultLogic;
    /** 共通DTO */
    private EventListDto eventListDto;
    /** DTO【永年申請申込状況 セッション用】*/
    private LongserviceViewListSessionDto longserviceViewListSessionDto;
    /** DTO【永年申請申込状況 リクエスト用】*/
    private LongserviceViewListRequestDto longserviceViewListRequestDto;
    /** 共通DTO【SV検索】*/
    private SvSearchDto svSearchDto;

    /**
     * 初期化処理
     */
    public String initialize() {
        int windowId = getLongserviceViewListRequestDto().getWindowId();
        
        //１．共通DTO初期フラグが'1'の場合、下記の処理を行う。
        if (getEventListDto().getInitFlag() == EventListDto.INITFLAG_ON) {
            
            //１－１．複数WindowID設定
            windowId = getLongserviceViewListSessionDto().updateWindowid();
            getLongserviceViewListRequestDto().setWindowId(windowId);
            
            //結果表示をOFFにする。
            getLongserviceViewListSessionDto().setResultFlg(windowId, NG);
            getLongserviceViewListRequestDto().setResultFlg(NG);
            
            //１－２．DTOの中身を初期化する。
            getLongserviceViewListRequestDto().requestClear();
            //実行ボタンを｢実行｣にする。            
            getLongserviceViewListSessionDto().setButtonFlg(windowId, JIKKOU); 
            getLongserviceViewListRequestDto().setButtonFlg(JIKKOU); 
            
            //１－３．セッションのDTOに持たせる値をウィンドウIDを付けて取得し、   
            //　　　　それをリクエスト用のDTOにセットする。
            getLongserviceViewListSessionDto().setCompanyCd(windowId, getEventListDto().getCompanyCd());
            getLongserviceViewListRequestDto().setCompanyCd(getEventListDto().getCompanyCd());
            //念のためオーナーコードもセットしておく。
            getLongserviceViewListSessionDto().setOnerCd(windowId, getEventListDto().getOnerCd());
            
            //１－４．フレームワークより値を取得
            getLongserviceViewListSessionDto().setLimit(getBirdUserInfo().isLimit());
            getLongserviceViewListSessionDto().setUserId(getBirdUserInfo().getUserID());
            getLongserviceViewListSessionDto().setSysdate(getBirdDateInfo().getSysDate());
            
            //１－５．ロジック【条件項目の取得】を実行する。
            Map params = new HashMap();
            //パラメータ
            params.put(LongserviceViewListConditionLogicImpl.PK_USERINFO, getBirdUserInfo());
            params.put(LongserviceViewListConditionLogicImpl.PK_COMPANY_CD, getEventListDto().getCompanyCd());
            params.put(LongserviceViewListConditionLogicImpl.PK_ENTRY_CD, getEventListDto().getEntryCd());
            params.put(LongserviceViewListConditionLogicImpl.PK_ENTRY_YEAR, getEventListDto().getEntryYear());
            params.put(LongserviceViewListConditionLogicImpl.PK_ENTRY_KAI, getEventListDto().getEntryKai());
            params.put(LongserviceViewListConditionLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
            params.put(LongserviceViewListConditionLogicImpl.PK_USER_ID, getBirdUserInfo().getUserID());
            Map logigMap = getLongserviceViewListConditionLogic().execute(params);
            
            //１－６．１－５の戻り値（logigMap）からヘッダ部情報を取得し、【DTO】永年勤続申込状況エンティティーへ設定する。
            List event = (List)logigMap.get(LongserviceViewListConditionLogicImpl.RK_LIST_STATUS_INFO);
            
            UILSViewListEvent uILSViewListEvent = null;
            if (event != null && event.size() > 0) {
                uILSViewListEvent = (UILSViewListEvent)event.get(0);
            }
            getLongserviceViewListSessionDto().setentityUILSViewListEvent(windowId, uILSViewListEvent);
            getLongserviceViewListRequestDto().setEntityUILSViewListEvent(uILSViewListEvent);
            
            //１－７．１－５の戻り値（logigMap）から支部情報を取得し、DTOへセットする。
            getLongserviceViewListSessionDto().setSibu((List)logigMap.get(LongserviceViewListConditionLogicImpl.RK_SIBU));
            
            //１－８．対象条件プルダウン作成
            getLongserviceViewListSessionDto().setTaishoJokenPull(ConditionTaishoJoken.getPullDownList());
            
            //１－９．区分プルダウン作成
            getLongserviceViewListSessionDto().setKbn(ConditionKbn.getPullDownListSinsei());
            
            //１－１０． 共通DTOの初期起動フラグを初期値に設定する。
            getEventListDto().setInitFlag(EventListDto.INITFLAG_OFF);
        }
        //２．SV選択画面から遷移してきた場合。
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            
            windowId = getNewSvSearchDto().getWindowId();
            
            //２－１．画面遷移時にウィンドウID毎の条件部を出力する。
            getLongserviceViewListRequestDto().setEntityUILSViewListEvent(
                    getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId)); 
            getLongserviceViewListRequestDto().setTaishouJokenChoice("2");
            
            //２－２．【DTO】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
            getLongserviceViewListRequestDto().setWindowId(windowId);
            
            //２－３．SVを選択後遷移してきた場合。
            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                //１．受け取ったSVコードをリクエストに設定する。
                getLongserviceViewListRequestDto().setSvCd(getNewSvSearchDto().getSvCd());
            } else {
                //２．セッションのSVコードをリクエストに設定する。
                getLongserviceViewListRequestDto().setSvCd(getLongserviceViewListSessionDto().getSvCd(windowId));
            }
            
            //２－４．ボタンフラグをセットする
            getLongserviceViewListRequestDto().setButtonFlg(getLongserviceViewListSessionDto().getButtonFlg(windowId));
            
            //まだ検索が成功していない場合、結果セットと再検索を行わない（結果の存在はボタンフラグで判断）。
            if (getLongserviceViewListSessionDto().getButtonFlg(windowId).equals(SAIKENSAKU)) {
                
                //２－５．結果がある場合、結果をセットし、なければ再検索をする。
                if (getLongserviceViewListSessionDto().getMapApplyOner().containsKey(new Integer(windowId))) {
                    
                    getLongserviceViewListRequestDto().setTable(getLongserviceViewListSessionDto().getTable(windowId));
                    
                } else {
                    
                    Map params = new HashMap();
                    
                    params.put(LongserviceViewListResultLogicImpl.PK_ENTITY_COURSE, getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
                    params.put(LongserviceViewListResultLogicImpl.PK_COMPANY_CD, getLongserviceViewListSessionDto().getCompanyCd(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_TAISHOUJOKEN, getLongserviceViewListSessionDto().getTaishouJokenChoice(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SIBU_CD, getLongserviceViewListSessionDto().getSibuChoice(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SV_CD, getLongserviceViewListSessionDto().getResearchSvCd(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_LIMIT, new Boolean(getBirdUserInfo().isLimit()));
                    
                    Map rparams = getLongserviceViewListResultLogic().execute(params);
                    
                    List listApplyOner = (List)rparams.get(LongserviceViewListResultLogicImpl.RK_LIST_APPLY_ONER);
                    
                    if (listApplyOner != null && listApplyOner.size() > 0) {
                        getLongserviceViewListRequestDto().setTable(listApplyOner);
                        getLongserviceViewListSessionDto().setTable(windowId, listApplyOner);
                    }
                }
            }
            
            //２－６．DTO【SV選択】.遷移区分を初期値に戻す。
            getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
            
            //２－７．DTO【SV選択】.クリア処理を実行する。
            getNewSvSearchDto().clear();
        }
        return null;
    }
    
    /**
     * SV検索フォーム呼び出し処理
     * 
     * @param viewId 遷移元情報
     * @return SV検索共通画面ID
     */
    public String callSvForm() {
        
        int windowId = getLongserviceViewListRequestDto().getWindowId();
        
        //１．遷移元情報を設定
        getNewSvSearchDto().setNavigationCase(LongserviceViewListUtil.VIEW_ID_CONDITION);
        
        //２．初期化
        getNewSvSearchDto().setInitFlag(true);
        
        //３．複数WindowID
        getNewSvSearchDto().setWindowId(windowId);
        
        //４．会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getLongserviceViewListSessionDto().getCompanyCd(windowId));
        getNewSvSearchDto().setRCompanyCdList(listCompany);
        
        //５．入力されている条件値をセッションDTOに保持する。
        getLongserviceViewListSessionDto().setSvCd(windowId, getLongserviceViewListRequestDto().getSvCd());

        //６．画面遷移情報設定
        return LongserviceViewListUtil.VIEW_ID_SVSEARCH;
    }
    
    /**
     * 画面の｢戻る｣ボタンを押した場合
     */
    public String back(){
        //１．ウィンドウIDを設定する。
        int windowId = getLongserviceViewListRequestDto().getWindowId();
        //２．セッション、リクエストの値をクリアする。
        getLongserviceViewListSessionDto().sessionClear(windowId);
        getLongserviceViewListRequestDto().requestClear();        
        getEventListDto().setReturnKind(EventListDto.RETURNKIND_BACK);
        
        return LongserviceViewListUtil.VIEW_ID_COMMONCONDITION;
    }
    
    /**
     * 実行ボタン処理
     * 
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    パラメータuserIdを追加し、対応しました。
     */
    public String search() {
        
        int windowId = getLongserviceViewListRequestDto().getWindowId();
        Map params = new HashMap();
        Map rparams = new HashMap();
        
        try {
            //１．通常の処理
            //１－１．セッションのTableをクリアする。
            getLongserviceViewListSessionDto().setTable(windowId, null);
            
            //１－２．選択された対象条件、支部、SVコードをリクエストとセッションのDTOにセットする。            
            getLongserviceViewListRequestDto().setEntityUILSViewListEvent(
                    getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId));
            getLongserviceViewListRequestDto().setButtonFlg(getLongserviceViewListSessionDto().getButtonFlg(windowId));
            
            //１－３．SVコードの数値妥当性チェックと対象条件、支部コードのセット
            String reqTaishoJoken = getLongserviceViewListRequestDto().getTaishouJokenChoice();
            if(reqTaishoJoken.equals(LongserviceViewListUtil.TAISHO_JOKEN_SV)) {
                validate(windowId);
            }
            
            //１－４．出欠席確認画面出力データ検索処理を実行する。
            params.put(LongserviceViewListResultLogicImpl.PK_USER_ID, getBirdUserInfo().getUserID());
            params.put(LongserviceViewListResultLogicImpl.PK_LIMIT, new Boolean(getBirdUserInfo().isLimit()));
            params.put(LongserviceViewListResultLogicImpl.PK_ENTITY_COURSE, getLongserviceViewListRequestDto().getEntityUILSViewListEvent());
            params.put(LongserviceViewListResultLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
            params.put(LongserviceViewListResultLogicImpl.PK_COMPANY_CD, getLongserviceViewListSessionDto().getCompanyCd(windowId));
            params.put(LongserviceViewListResultLogicImpl.PK_TAISHOUJOKEN, reqTaishoJoken);
            params.put(LongserviceViewListResultLogicImpl.PK_SIBU_CD, getLongserviceViewListRequestDto().getSibuChoice());
            params.put(LongserviceViewListResultLogicImpl.PK_SV_CD, getLongserviceViewListRequestDto().getSvCd());
            
            //１－５．ロジック[オーナー別申請状況一覧]処理を行い、オーナー別申請状況一覧を取得する。
            rparams = getLongserviceViewListResultLogic().execute(params);
            
            //対象条件、支部コードのセット
            getLongserviceViewListSessionDto().setTaishouJokenChoice(windowId, reqTaishoJoken);
            getLongserviceViewListSessionDto().setSibuChoice(windowId, getLongserviceViewListRequestDto().getSibuChoice());
            //SVコードをセッション、再検索用のDTO両方にセットする。
            getLongserviceViewListSessionDto().setSvCd(windowId, getLongserviceViewListRequestDto().getSvCd());
            getLongserviceViewListSessionDto().setResearchSvCd(windowId, getLongserviceViewListRequestDto().getSvCd());
            
            //１－６．処理５の戻り値から[オーナー別申請状況一覧]を取得し、取得結果がない場合は下記の処理を行う。
            List listApplyOner = (List)rparams.get(LongserviceViewListResultLogicImpl.RK_LIST_APPLY_ONER);
            
            if (listApplyOner == null || listApplyOner.size() == 0) {
                getLongserviceViewListSessionDto().setTable(windowId, null);
                //２．Exception　MSG【E0102】を発生させる。
                throw new NotExistException("該当データ");
            }
    
            //１－７．処理６の戻り値から[オーナー別出欠席情報]を取得する。
            getLongserviceViewListSessionDto().setTable(windowId, listApplyOner);
            getLongserviceViewListRequestDto().setTable(listApplyOner);
            
            //１－８．実行ボタンを再検索ボタンにする。
            getLongserviceViewListRequestDto().setButtonFlg(SAIKENSAKU);
            getLongserviceViewListSessionDto().setButtonFlg(windowId, SAIKENSAKU);
            
            //１－９．検索が成功したのでフラグをtrueにする。
            getLongserviceViewListRequestDto().setResultFlg(OK);
            getLongserviceViewListSessionDto().setResultFlg(windowId, OK);
        }
        
        catch (ApplicationException appEx) {
            //２．例外発生時
            //２－１．例外がNotExistExceptionの場合。
            if (appEx instanceof NotExistException) {
                //結果表示をOFFにする。
                getLongserviceViewListSessionDto().setResultFlg(windowId, NG);
                getLongserviceViewListRequestDto().setResultFlg(NG);
            } else {
            //２－２．NotExistException以外の例外処理時、ウィンドウID毎の結果部を出力する。           
                if (!getLongserviceViewListSessionDto().getMapApplyOner().isEmpty() && 
                        getLongserviceViewListSessionDto().getResultFlg(windowId).equals(OK)) {
                    
                    if (getLongserviceViewListSessionDto().getMapApplyOner().containsKey(new Integer(windowId))) {
                        getLongserviceViewListRequestDto().setTable(
                                getLongserviceViewListSessionDto().getTable(windowId));
                    } else {
                //２－３．２－２で結果部を出力できなかった場合、結果部を再検索する。
                        params = new HashMap();
                        
                        params.put(LongserviceViewListResultLogicImpl.PK_USER_ID, getBirdUserInfo().getUserID());
                        params.put(LongserviceViewListResultLogicImpl.PK_LIMIT, new Boolean(getBirdUserInfo().isLimit()));
                        params.put(LongserviceViewListResultLogicImpl.PK_ENTITY_COURSE, getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId));
                        params.put(LongserviceViewListResultLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
                        params.put(LongserviceViewListResultLogicImpl.PK_COMPANY_CD, getLongserviceViewListSessionDto().getCompanyCd(windowId));
                        params.put(LongserviceViewListResultLogicImpl.PK_TAISHOUJOKEN, getLongserviceViewListSessionDto().getTaishouJokenChoice(windowId));
                        params.put(LongserviceViewListResultLogicImpl.PK_SIBU_CD, getLongserviceViewListSessionDto().getSibuChoice(windowId));
                        params.put(LongserviceViewListResultLogicImpl.PK_SV_CD, getLongserviceViewListSessionDto().getResearchSvCd(windowId));
                        
                        rparams = getLongserviceViewListResultLogic().execute(params);
                        List listApplyOner = (List)rparams.get(LongserviceViewListResultLogicImpl.RK_LIST_APPLY_ONER);
                        
                //２－４．listApplyOnerがnullだった場合に値をセットしない。
                        if (listApplyOner != null && listApplyOner.size() != 0) {
                            getLongserviceViewListRequestDto().setTable(listApplyOner);
                        }
                        
                    }
                }
            }
            throw appEx;
        }

        //３－２．画面VIEW_IDをリターンする。
        return LongserviceViewListUtil.VIEW_ID_CONDITION;
    }

    /**
     * 事前条件処理
     * 
     * １．SVコード設定の場合
     */
    private void validate(int windowId) {
        //１．前0付加処理準備をする。
        CodeFormatter cdf = new CodeFormatter(8, "00000000");
        cdf.setFormatPattern("00000000");
        String svCd = getLongserviceViewListRequestDto().getSvCd();
        
        //２．SVコードのnullチェックをする。
        if (svCd == null || "".equals(svCd)) {
            throw new NoInputException("SVコード"); 
        }
        
        //３．SVコードの妥当性チェックをする。
        HankakuVerifier hankakuVerifier = new HankakuVerifier();
        if(!hankakuVerifier.validate(svCd) || svCd.length() > 8){
            throw new GenericMessageException("SVコードは半角英数字8桁以内で入力してください。", "svCd", 0);               
        }
        
        //４．前0付加処理をする。
        if(svCd != null && svCd.length() > 0){
            svCd = cdf.format(svCd, true);
        }
        
        //５．前0処理されたSVコードをリクエストのDTOにセットする。
        getLongserviceViewListRequestDto().setSvCd(svCd);
    }
    
    public LongserviceViewListSessionDto getLongserviceViewListSessionDto() {
        return longserviceViewListSessionDto;
    }

    public void setLongserviceViewListSessionDto(LongserviceViewListSessionDto longserviceViewListSessionDto) {
        this.longserviceViewListSessionDto = longserviceViewListSessionDto;
    }
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    
    /**
     * SV検索DTO取得処理
     * 
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
    
    public LongserviceViewListConditionLogic getLongserviceViewListConditionLogic() {
        return longserviceViewListConditionLogic;
    }
    
    public void setLongserviceViewListConditionLogic(
            LongserviceViewListConditionLogic longserviceViewListConditionLogic) {
        this.longserviceViewListConditionLogic = longserviceViewListConditionLogic;
    }
    
    public LongserviceViewListResultLogic getLongserviceViewListResultLogic() {
        return longserviceViewListResultLogic;
    }
    
    public void setLongserviceViewListResultLogic(
            LongserviceViewListResultLogic longserviceViewListResultLogic) {
        this.longserviceViewListResultLogic = longserviceViewListResultLogic;
    }
    
    public EventListDto getEventListDto() {
        return eventListDto;
    }
    
    public void setEventListDto(EventListDto eventListDto) {
        this.eventListDto = eventListDto;
    }

    public LongserviceViewListRequestDto getLongserviceViewListRequestDto() {
        return longserviceViewListRequestDto;
    }

    public void setLongserviceViewListRequestDto(
            LongserviceViewListRequestDto longserviceViewListRequestDto) {
        this.longserviceViewListRequestDto = longserviceViewListRequestDto;
    }
}