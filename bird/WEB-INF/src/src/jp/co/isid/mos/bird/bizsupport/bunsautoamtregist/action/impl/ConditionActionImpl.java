/*
 * 作成日: 2006/12/20
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto.BunsAutoAmtRegistDto;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto.BunsAutoAmtRegistReqDto;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.CheckCodeExistLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * バンズ自動設定数量変更
 * 
 * 条件画面 アクションクラス
 * 
 * @author xkinu
 */
public class ConditionActionImpl implements ConditionAction {
    
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A01";
    /* アクションID：SV検索処理 */
    public static final String callOnerForm_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A02";
    /* アクションID：SV検索処理 */
    public static final String callMiseForm_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A03";
    /* アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A04";
    /* アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"A05";
    
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【オーナー選択】 */
    private OwnerSearchDto ownerSearchDto;
    /** 共通DTO【店選択】 */
    private MiseSearchDto miseSearchDto;
    /** 共通DTO【SV検索】 */
    private SvSearchDto svSearchDto;
    /** DTO【自画面セッション用】*/
    private BunsAutoAmtRegistDto bunsAutoAmtRegistDto;
    /** DTO【自画面リクエスト用】*/
    private BunsAutoAmtRegistReqDto bunsAutoAmtRegistReqDto;
    /**
     * ロジック【条件項目の取得】
     */
    private ConditionLogic bunsAutoAmtRegistConditionLogic;
    /**
     * ロジック【申込状況検索】
     */
    private SearchLogic bunsAutoAmtRegistSearchLogic;
    /**
     *  ロジック【コード存在チェック】
     */
    private CheckCodeExistLogic bunsAutoAmtRegistCheckCodeExistLogic;
    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();
    /* 共通DTO【ユーザーロール情報検索用】 */
    private GamenRoleDto gamenRoleDto;


    /**
     * 初期化処理
     * １．変数【遷移先VIEW_DI】 ＝ null　を生成する。
     * ２．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
     *    １－１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
     *    １－２．【DTO】WindowIdの更新を実行する。
     *    １－３．複数ウィンドウ制御用セッションKey生成
     *    １－４．処理１－３で生成した複数ウィンドウ制御用セッションKeyを【DTO】へ設定する。
     *    １－５．ロジック【条件項目の取得】を実行する。
     *    １－６．処理１－５の戻り値から、[[会社情報]][[対象条件]]を取得しDTO【自画面Request用】へ設定する。
     *    １－７．
     *    １－８．
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
        //１．変数【遷移先VIEW_DI】 ＝ null　を生成する。
        String viewId = null;
        //変数WindowID設定
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        //DTO【自画面Request用】.遷移元画面VIEW_IDへ初期画面VIEWIDを設定する。
        getBunsAutoAmtRegistReqDto().setBackViewId(BunsAutoAmtRegistUtil.VIEW_ID_CONDITION);
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){            
            //１－１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
            //１－２．DTO【自画面Session用】MaxウィンドウIDを更新する。
            windowId = getBunsAutoAmtRegistDto().createWindowId();
            getBunsAutoAmtRegistReqDto().setWindowId(windowId);
            //初期データ設定処理を行います。
            settingInitdata(windowId);
            //１－９．ユーザータイプコードの値別に下記の処理を行います。
            if(getBunsAutoAmtRegistDto().isHonbuUser()) {
                //本部ユーザーの場合
                //DTO【自画面Session用】.対象条件へ初期値として’オーナー’を設定する。
                getBunsAutoAmtRegistDto().setJokenTaishoJoken(windowId, ConditionTaishoJoken.VALUE_ONER);
            } else {
                if(getBunsAutoAmtRegistDto().isRegist()) {
                    viewId = BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
                }
                else {
                    viewId = BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM;
               }
            }
        }
        //２．オーナー選択画面から遷移したきた場合。
        else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
            try {
                //１．【DTO】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
                windowId = getOwnerSearchDto().getWindowId();
                getBunsAutoAmtRegistReqDto().setWindowId(windowId);
                //２．オーナーを選択後遷移してきた場合。
                if(getOwnerSearchDto().getReturnKind() == OwnerSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getOwnerSearchDto().getOnerCd();
                    //２－１．オーナーコードを設定する。
                    getBunsAutoAmtRegistDto().setJokenOnerCd(windowId, selectedCd);
                    //２－２．DTO【自画面Session用】.対象条件へ初期値として’オーナー’を設定する。
                    getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_ONER);
                    //２－３．DTO【自画面Session用】.対象条件へ初期値としてユーザーオーナーコードを設定する。
                    getBunsAutoAmtRegistReqDto().setTargetOnerCd(selectedCd);
                    //実行ボタン処理を実行する。
                    viewId = search();
                }
            } finally {
                //３．DTO【オーナー選択】.遷移区分を初期値に戻す。
                getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
                //４．DTO【オーナー選択】.クリア処理を実行する。
                getOwnerSearchDto().clear();
            }
        }
        //３．店舗選択画面から遷移したきた場合。
        else if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
            try {
                //１．【DTO】ウィンドウIDにDTO【店舗選択】.ウィンドウIDを設定する。
                windowId = getMiseSearchDto().getWindowId();
                getBunsAutoAmtRegistReqDto().setWindowId(windowId);
                //２．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //２－１．店舗コードを設定する。
                    getBunsAutoAmtRegistDto().setJokenMiseCd(windowId, selectedCd);
                    //２－２．DTO【自画面Session用】.対象条件へ初期値として’店舗’を設定する。
                    getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_MISE);
                    //２－３．DTO【自画面Session用】.対象条件へ初期値としてユーザー店舗コードを設定する。
                    getBunsAutoAmtRegistReqDto().setTargetMiseCd(selectedCd);
                    //実行ボタン処理を実行する。
                    viewId = search();
                }
            } finally {
                //３．DTO【店舗選択】.遷移区分を初期値に戻す。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //４．DTO【店舗選択】.クリア処理を実行する。
                getMiseSearchDto().clear();
            }
        }
        // ４．SV選択画面から遷移してきた場合。
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            try {
                //１．【DTO】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
                windowId = getNewSvSearchDto().getWindowId();
                getBunsAutoAmtRegistReqDto().setWindowId(windowId);
                //２．SVを選択後遷移してきた場合。
                if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getNewSvSearchDto().getSvCd();
                    //２－１．SVコードを設定する。
                    getBunsAutoAmtRegistDto().setJokenSvCd(windowId, selectedCd);
                    //２－２．DTO【自画面Session用】.対象条件へ初期値として’店舗’を設定する。
                    getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_SV);
                    //２－３．DTO【自画面Session用】.対象条件へ初期値としてユーザー店舗コードを設定する。
                    getBunsAutoAmtRegistReqDto().setTargetSvCd(selectedCd);
                    //実行ボタン処理を実行する。
                    viewId = search();
               }
            } finally {
                //３．DTO【SV選択】.遷移区分を初期値に戻す。
                getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
                //４．DTO【SV選択】.クリア処理を実行する。
                getNewSvSearchDto().clear();
            }
        }
        //５．個店ポータル画面から遷移してきた場合。
        else if(getCommonCodeDto().getUseCommonDto()){
            //１－２．DTO【自画面Session用】MaxウィンドウIDを更新する。
            windowId = getBunsAutoAmtRegistDto().createWindowId();
            getBunsAutoAmtRegistReqDto().setWindowId(windowId);
            //初期データ設定処理を行います。
            settingInitdata(windowId);
            //１－９．ユーザータイプコードの値別に下記の処理を行います。
            if(getBunsAutoAmtRegistDto().isHonbuUser()) {
                String selectedCd = null;
                //本部ユーザーの場合
                if (!BunsAutoAmtRegistUtil.isNull(getCommonCodeDto().getOnerCd())) {
                    //オーナーコードが設定されている場合はオーナー照会から遷移してきた場合なので、
                    //オーナーコードを自画面DTOへ設定する。
                    //DTO【自画面Session用】.対象条件へ初期値として’店舗’を設定する。
                    getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_ONER);
                    selectedCd = getCommonCodeDto().getOnerCd();
                    //DTO【自画面Session用】.店舗コードへDTO【個店ポータル】.店舗コードを設定する。
                    getBunsAutoAmtRegistDto().setTargetCompanyCd(getCommonCodeDto().getCompanyCd());
                    getBunsAutoAmtRegistDto().setJokenOnerCd(windowId, selectedCd);
                    //DTO【自画面Request用】.店舗コードへDTO【個店ポータル】.店舗コードを設定する。
                    getBunsAutoAmtRegistReqDto().setTargetOnerCd(selectedCd);
                }
                else if (!BunsAutoAmtRegistUtil.isNull(getCommonCodeDto().getMiseCd())) {
                    //DTO【自画面Session用】.対象条件へ初期値として’店舗’を設定する。
                    getBunsAutoAmtRegistReqDto().setTargetTaishoJoken(ConditionTaishoJoken.VALUE_MISE);
                    selectedCd = getCommonCodeDto().getMiseCd();
                    //DTO【自画面Session用】.店舗コードへDTO【個店ポータル】.店舗コードを設定する。
                    getBunsAutoAmtRegistDto().setTargetCompanyCd(getCommonCodeDto().getCompanyCd());
                    getBunsAutoAmtRegistDto().setJokenMiseCd(windowId, selectedCd);
                    //DTO【自画面Request用】.店舗コードへDTO【個店ポータル】.店舗コードを設定する。
                    getBunsAutoAmtRegistReqDto().setTargetMiseCd(selectedCd);
                }
                if(selectedCd != null) {
                    //実行ボタン処理を実行する。
                    viewId = search();
                }
            } else {
                if(getBunsAutoAmtRegistDto().isRegist()) {
                    viewId = BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
                }
                else {
                    viewId = BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM;
               }
            }
       }
        //３．現ウィンドウID の保管データからDTO【自画面セッション用】検索対象条件項目をDTO【自画面リクエスト用】条件項目値への設定を行う。
        getBunsAutoAmtRegistDto().copyJokenData(getBunsAutoAmtRegistReqDto());
        return viewId;
    }
    /**
     * オーナー選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callOnerForm() {
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        //１．共通DTO【オーナー選択】遷移元情報へ初期画面VIEWIDを設定する。
        getOwnerSearchDto().setNavigationCase(BunsAutoAmtRegistUtil.VIEW_ID_CONDITION);
        //２．共通DTO【オーナー選択】初期化フラグへtrueを設定する。
        getOwnerSearchDto().setInitFlag(true);
        //３．共通DTO【オーナー選択】遷移区分要否フラグへtrueを設定する。
        getOwnerSearchDto().setNeedReturnKind(true);
        //４．共通DTO【オーナー選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定する。
        getOwnerSearchDto().setWindowId(windowId);
        //５．共通DTO【オーナー選択】会社コードリストへ対象会社コードを保持したListを設定する。
        List listCompany = new ArrayList();
        listCompany.add(getBunsAutoAmtRegistDto().getTargetCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //６．現ウィンドウID の検索対象条件項目値の保管を行う。
        getBunsAutoAmtRegistDto().holdJokenParam(getBunsAutoAmtRegistReqDto());
        //７．選択画面遷移VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_ONERSEARCH;
    }
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm() {
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定する。
        getMiseSearchDto().setNavigationCase(BunsAutoAmtRegistUtil.VIEW_ID_CONDITION);
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定する。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定する。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定する。
        getMiseSearchDto().setWindowId(windowId);
        //５．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定する。
        List listCompany = new ArrayList();
        listCompany.add(getBunsAutoAmtRegistDto().getTargetCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //６．現ウィンドウID の検索対象条件項目値の保管を行う。
        getBunsAutoAmtRegistDto().holdJokenParam(getBunsAutoAmtRegistReqDto());
        //７．選択画面遷移VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_MISESEARCH;
    }
    /**
     * SV検索ボタン処理
     * 
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        //１．遷移元情報を設定
        svSearchDto.setNavigationCase(BunsAutoAmtRegistUtil.VIEW_ID_CONDITION);
        //２．初期化
        svSearchDto.setInitFlag(true);
        //３．複数WindowID
        svSearchDto.setWindowId(windowId);
        //４．会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getBunsAutoAmtRegistDto().getTargetCompanyCd());
        svSearchDto.setRCompanyCdList(listCompany);
        
        //５．現ウィンドウID の検索対象条件項目値の保管を行う。
        getBunsAutoAmtRegistDto().holdJokenParam(getBunsAutoAmtRegistReqDto());
        //６．選択画面遷移VIEWIDをリターンする。
        return BunsAutoAmtRegistUtil.VIEW_ID_SVSEARCH;
    } 
    /**
     * 実行ボタン処理
     * 
     */
    public String search() {
        //変数WindowID設定
        int windowId = getBunsAutoAmtRegistReqDto().getWindowId();
        try{
            if(getBunsAutoAmtRegistDto().isRegist()) {
                //１． sessionKey有効チェックを行う。
                if (!isValidSessionKey()) {
                    //１－１． 有効でない場合は操作エラー画面に遷移
                    return BunsAutoAmtRegistUtil.operationErr_VIEW_ID;
                }
            }
            //１．現ウィンドウID の検索対象条件項目値の保管を行う。
            getBunsAutoAmtRegistDto().holdJokenParam(getBunsAutoAmtRegistReqDto());
            //２．ロジック【検索対象報取得】を実行し、[[バンズ自動設定数量データ]]を取得する。            
            //２－１．ロジック【検索対象報取得】用パラメーターを生成する。
            Map params = new HashMap();
            params.put(SearchLogic.PK_USERINFO, getBirdUserInfo());
            params.put(SearchLogic.PK_SYSDATE, getBirdDateInfo().getSysDate());
            params.put(SearchLogic.PK_COMPANY_CD, getBunsAutoAmtRegistDto().getTargetCompanyCd());
            params.put(SearchLogic.PK_TAISHOJOKEN, getBunsAutoAmtRegistReqDto().getTargetTaishoJoken());
            params.put(SearchLogic.PK_ONER_CD, getBunsAutoAmtRegistReqDto().getTargetOnerCd());
            params.put(SearchLogic.PK_MISE_CD, getBunsAutoAmtRegistReqDto().getTargetMiseCd());
            params.put(SearchLogic.PK_SV_CD, getBunsAutoAmtRegistReqDto().getTargetSvCd());
            //３．ロジック【検索対象報取得】を実行する。
            Map rparams = getBunsAutoAmtRegistSearchLogic().execute(params);
            //３－１．処理２.結果戻り値から[[バンズ自動設定数量データ]]を取得する。
            List listSearchData = (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA);
            //３－２．処理２－１の[[バンズ自動設定数量データ]]の取得結果が該当データなしの場合は、下記の処理を行う。
            if (listSearchData == null || listSearchData.size() == 0) {
                throw new NoResultException();
            }
            String targetNameKj = (String)rparams.get(SearchLogic.RK_TAEGETCD_NAME);
            //４．対象条件で指定したコードの名称をDTO【自画面Session用.条件名称へ設定する。
            getBunsAutoAmtRegistDto().setJokenNameKj(windowId, targetNameKj);
            
            if(getBunsAutoAmtRegistDto().isRegist()) {
                //２－７．DTO【自画面Session用】.検索結果リストへ処理２－５[[バンズ自動設定数量データ]]の値を設定する。    
                getBunsAutoAmtRegistDto().setListRegData(listSearchData);
                //２－８．DTO【自画面Session用】.検索結果の店舗一覧リストへ処理２－５[[検索結果の店舗一覧]]の値を設定する。    
                getBunsAutoAmtRegistDto().setListRegDataMiseList((List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA_MISELIST));
                return BunsAutoAmtRegistUtil.VIEW_ID_EDIT;
            }
            else {
                //２－７．DTO【自画面Session用】.検索結果リストへ処理２－５[[バンズ自動設定数量データ]]の値を設定する。    
                getBunsAutoAmtRegistDto().setListSearchData(windowId, listSearchData);
                //２－８．DTO【自画面Session用】.検索結果の店舗一覧リストへ処理２－５[[検索結果の店舗一覧]]の値を設定する。    
                getBunsAutoAmtRegistDto().setListSearchDataMiseList(windowId, (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA_MISELIST));
                return BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM;
           }
        }
        catch (NoResultException noRltEx){
            //Ⅰ．前回検索結果データをクリアするため、DTO【自画面Session用】検索結果クリア処理を実行する。
            getBunsAutoAmtRegistDto().searchDataClear(windowId);
            //Ⅱ．Exception　MSG【E0102】を発生させる。
            throw noRltEx;
        }
        finally{
            //３．DTO【リクエスト用】へ対象データを設定する。
            getBunsAutoAmtRegistDto().copyData(getBunsAutoAmtRegistReqDto());
        }
    }
    /**
     * 初期データ設定処理
     * 
     * @param windowId
     */
    private void settingInitdata(int windowId) {
        //１－３．複数ウィンドウ制御用セッションKey生成
        String key = mkSession._makeSessionKey();
        //１－４．処理１－３で生成した複数ウィンドウ制御用セッションKeyをDTO【自画面Session用】へ設定する。
        getBunsAutoAmtRegistDto().setNowSessionKey(key);
        getBunsAutoAmtRegistDto().setSessionKey(windowId, key);
        getBunsAutoAmtRegistDto().setBirdDateInfo(getBirdDateInfo());
        getBunsAutoAmtRegistDto().setBirdUserInfo(getBirdUserInfo());

        //１－５．ロジック【条件項目の取得】を実行する。
        //パラメーター    BIRDユーザー情報
        //                システム日付
        //                汎用画面ロール情報
        Map params = new HashMap();
        params.put(ConditionLogic.PK_USERINFO, getBirdUserInfo());
        params.put(ConditionLogic.PK_SYSDATE, getBirdDateInfo().getSysDate());
        params.put(ConditionLogic.PK_DTO_GAMENROE, getGamenRoleDto());
        Map logigMap = getBunsAutoAmtRegistConditionLogic().execute(params);
                    
        //１－６．処理１－５の戻り値から、ダウンロード許可フラグを取得し、DTO【自画面Session用】へ設定する。
        getBunsAutoAmtRegistDto().setFlgDownload((String)logigMap.get(ConditionLogic.RK_FLG_DOWNLOAD));
        //１－７．処理１－５の戻り値から、ダウンロード許可フラグを取得する。
        getBunsAutoAmtRegistDto().setFlgRegist((String)logigMap.get(ConditionLogic.RK_FLG_REGIST));
        
        //１－８．処理１－５の戻り値から、[[会社情報]][[対象条件]]を取得しDTO【自画面Session用】へ設定する。
        getBunsAutoAmtRegistDto().setListCompanyCd((List)logigMap.get(ConditionLogic.RK_LIST_COMPANY));
        getBunsAutoAmtRegistDto().setListTaishoJoken((List)logigMap.get(ConditionLogic.RK_LIST_TAISHOJOKEN));
        
    }
    /**
     * 自画面Session用DTO取得処理
     * @return
     */
    public BunsAutoAmtRegistDto getBunsAutoAmtRegistDto() {
        return bunsAutoAmtRegistDto;
    }
    /**
     * 自画面Session用DTO設定処理
     * @param sessionDto
     */
    public void setBunsAutoAmtRegistDto(BunsAutoAmtRegistDto sessionDto) {
        this.bunsAutoAmtRegistDto = sessionDto;
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
     * @return bunsAutoAmtRegistConditionLogic を戻します。
     */
    public ConditionLogic getBunsAutoAmtRegistConditionLogic() {
        return bunsAutoAmtRegistConditionLogic;
    }


    /**
     * @param conditionLogic 設定する bunsAutoAmtRegistConditionLogic。
     */
    public void setBunsAutoAmtRegistConditionLogic(
            ConditionLogic conditionLogic) {
        this.bunsAutoAmtRegistConditionLogic = conditionLogic;
    }
    /**
     * @return bunsAutoAmtRegistSearchLogic を戻します。
     */
    public SearchLogic getBunsAutoAmtRegistSearchLogic() {
        return bunsAutoAmtRegistSearchLogic;
    }
    /**
     * @param searchLogic を設定する bunsAutoAmtRegistSearchLogic。
     */
    public void setBunsAutoAmtRegistSearchLogic(
            SearchLogic searchLogic) {
        this.bunsAutoAmtRegistSearchLogic = searchLogic;
    }
    /**
     * @return bunsAutoAmtRegistReqDto を戻します。
     */
    public BunsAutoAmtRegistReqDto getBunsAutoAmtRegistReqDto() {
        return bunsAutoAmtRegistReqDto;
    }
    /**
     * @param requestDto 設定する bunsAutoAmtRegistReqDto。
     */
    public void setBunsAutoAmtRegistReqDto(
            BunsAutoAmtRegistReqDto requestDto) {
        this.bunsAutoAmtRegistReqDto = requestDto;
    }
    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto 設定する pullDownMenuDto。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    /**
     * コード存在チェックロジック取得処理
     * @return bunsAutoAmtRegistCheckCodeExistLogic を戻します。
     */
    public CheckCodeExistLogic getBunsAutoAmtRegistCheckCodeExistLogic() {
        return bunsAutoAmtRegistCheckCodeExistLogic;
    }
    /**
     * コード存在チェックロジック設定処理
     * @param checkCodeExistLogic 設定する bunsAutoAmtRegistCheckCodeExistLogic。
     */
    public void setBunsAutoAmtRegistCheckCodeExistLogic(
            CheckCodeExistLogic checkCodeExistLogic) {
        this.bunsAutoAmtRegistCheckCodeExistLogic = checkCodeExistLogic;
    }
    /**
     * @return miseSearchDto を戻します。
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * @param miseSearchDto 設定する miseSearchDto。
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    /**
     * @return ownerSearchDto を戻します。
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }
    /**
     * @param ownerSearchDto 設定する ownerSearchDto。
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }
    /**
     * @return gamenRoleDto を戻します。
     */
    public GamenRoleDto getGamenRoleDto() {
        return gamenRoleDto;
    }
    /**
     * @param gamenRoleDto 設定する gamenRoleDto。
     */
    public void setGamenRoleDto(GamenRoleDto gamenRoleDto) {
        this.gamenRoleDto = gamenRoleDto;
    }
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey( 
                getBunsAutoAmtRegistDto().getNowSessionKey()
                  ,  getBunsAutoAmtRegistDto().getSessionKey(getBunsAutoAmtRegistReqDto().getWindowId()) );
    }
    /**
     * 個店ポータルからの遷移時保持DTO取得処理
     * @return
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) getS2Container().getComponent(CommonCodeDto.class);
    }
}