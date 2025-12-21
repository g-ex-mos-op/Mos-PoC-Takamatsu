/*
 * 作成日: 2006/06/30
 */
package jp.co.isid.mos.bird.storemanage.msstantorankref.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.MssTantotenRankingRefAction;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dto.MssTantotenRankingRefDto;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UIRankingUnionData;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ConstructionRankingDataLogic;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.SearchLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ミステリーショッパーズ 担当店ランキング
 * 画面アクション
 * 
 * @author xkinu
 */
public class MssTantotenRankingRefActionImpl 
    implements MssTantotenRankingRefAction {
    private static final String VIEWID_ONERSEARCH   = "BCO006V01";//オーナー選択
    private static final String VIEWID_MISESEARCH   = "BCO008V01";//店選択
    private static final String VIEWID_SVSEARCH   = "BCO011V01";//SV選択
    /** 画面遷移区分：初期値 [-1] */
    public static final int SCENECHANGE_KBN_INIT = -1;
    /** 画面遷移区分：現行画面からの遷移時 [0] */
    public static final int SCENECHANGE_KBN_SELF = 0;
    /** 画面遷移区分：オーナー検索画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_ONERINFO = 10;
    /** 画面遷移区分：店検索画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_MISEINFO = 20;
    /** 画面遷移区分：SV検索画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_SVINFO = 30;
    /** 画面遷移区分：照会画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_VIEW = 1;
    
    /** 対象SV：SV */
    public static final String SEARCH_SV = "SV";
    /** 対象オーナー：オーナー */
    public static final String SEARCH_ONER = "ONER";
    /** 対象オーナー：個店 */
    public static final String SEARCH_MISE = "MISE";
    /*【ロジック】条件画面出力データ検索ロジック */
    private ConditionLogic conditionLogic;
    /*【LOGIC】検索処理時ロジック */
    private SearchLogic searchLogic;
    /*【LOGIC】検索処理時ロジック */
    private ConstructionRankingDataLogic constructionRankingDataLogic;
    /*【DTO】*/
    private MssTantotenRankingRefDto dto;
    /*【DTO】オーナー検索DTO */
    private OwnerSearchDto onerSearchDto;
    /*【DTO】SV検索DTO */
    private SvSearchDto newSvSearchDto;
    /*【DTO】店検索DTO */
    private MiseSearchDto miseSearchDto;
    /**
     * 初期処理
     * @return null
     */
    public String initialize() throws Exception {
        int kbn  = getMssTantotenRankingRefDto().getScenechangedKbn();
        if(kbn==SCENECHANGE_KBN_ONERINFO) {
            //オーナー索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_ONER);
            getMssTantotenRankingRefDto().setOnerCd(getOwnerSearchDto().getOnerCd());
            getMssTantotenRankingRefDto().setOnerName(getOwnerSearchDto().getOnerNameKj());
            
        }else if(kbn==SCENECHANGE_KBN_MISEINFO) {
            //店検索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_MISE);
            getMssTantotenRankingRefDto().setMiseCd(getMiseSearchDto().getMiseCd());
            
        }else if(kbn==SCENECHANGE_KBN_SVINFO) {
            //SV検索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_SV);
            getMssTantotenRankingRefDto().setSvCd(getNewSvSearchDto().getSvCd());
            getMssTantotenRankingRefDto().setSvName(getNewSvSearchDto().getSvNameKj());
            
        }else if(kbn!=SCENECHANGE_KBN_SELF) {
            //Dto初期化処理
            getMssTantotenRankingRefDto().initClear();
            //Dtoへシステム日付設定
            getMssTantotenRankingRefDto().setToday(getBirdDateInfo().getSysDate());
            //Dtoへアプリ日付設定
            getMssTantotenRankingRefDto().setAppDate(getBirdDateInfo().getAppDate());
            //DtoへユーザーID設定(JSF内判断処理用)
            getMssTantotenRankingRefDto().setUserId(getBirdUserInfo().getUserID());
            //Dtoへユーザータイプ設定(JSF内判断処理用)
            getMssTantotenRankingRefDto().setUsertypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            //Dtoへユーザー支部制限判断フラグ設定
            getMssTantotenRankingRefDto().setLimit(getBirdUserInfo().isLimit());
            /*
             * ロジック【条件画面出力データ検索】
             */
            getMssTantotenRankingConditionLogic().execute(getMssTantotenRankingRefDto());
            //Dtoへアプリ日付設定
            getMssTantotenRankingRefDto().setSearchType(SEARCH_SV);
            getMssTantotenRankingRefDto().setViewId(VIEWID_CONDITION);
        }
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_INIT);
        return null;
    }
    /**
     * 初期処理
     * @return null
     */
    public String viewinitialize() throws Exception {
        int kbn  = getMssTantotenRankingRefDto().getScenechangedKbn();
        if(kbn==SCENECHANGE_KBN_ONERINFO) {
            //オーナー索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_ONER);
            getMssTantotenRankingRefDto().setOnerCd(getOwnerSearchDto().getOnerCd());
            getMssTantotenRankingRefDto().setOnerName(getOwnerSearchDto().getOnerNameKj());
            
        }else if(kbn==SCENECHANGE_KBN_MISEINFO) {
            //店検索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_MISE);
            getMssTantotenRankingRefDto().setMiseCd(getMiseSearchDto().getMiseCd());
            
        }else if(kbn==SCENECHANGE_KBN_SVINFO) {
            //SV検索画面から戻ってきたとき
            getMssTantotenRankingRefDto().setSearchType(SEARCH_SV);
            getMssTantotenRankingRefDto().setSvCd(getNewSvSearchDto().getSvCd());
            getMssTantotenRankingRefDto().setSvName(getNewSvSearchDto().getSvNameKj());
            
        }else if(kbn!=SCENECHANGE_KBN_SELF) {
            getMssTantotenRankingRefDto().setViewId(VIEWID_REF);
        }
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_INIT);
        return null;
    }
    /**
     * 照会画面呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * ２．ユーザータイプコードが「オーナー」の場合以下の処理を行う。
     *   １)．DTOの検索対象値へ「ONER」を設定
     *   ２)．DTOのオーナーコードへユーザーオーナーコードを設定
     *  
     * ３．ロジック【照会画面出力データ検索】の実行。
     * ４．処理３の戻り値を引数にロジック【ランキングデータ構築】を実行。
     * ５．画面表示用にデータの構築済みランキングデータをDTOへ設定を行う。
     * ６．処理３の戻り値のマップから構成比データを取り出し、DTOへ設定を行う。
     * ７．画面遷移区分へ照会画面遷移区分の設定を行う。
     * ８．ビューID:照会画面 BSM010V01のStringをリターンする。
     * 
     * @return 照会画面ViewID
     */
    public String execute() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssTantotenRankingRefDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        /*
         * ユーザータイプコードが「オーナー」の場合以下の処理を行う。
         * １．DTOの検索対象値へ「ONER」を設定
         * ２．DTOのオーナーコードへユーザーオーナーコードを設定
         *  
         */
        if(userTypeCd.equals(MssTantotenRankingRefDto.USERTYPE_ONER)){
            //1.検索対象に「ONER」を設定
            getMssTantotenRankingRefDto().setSearchType(SEARCH_ONER);
            List listUserOner = getBirdUserInfo().getUserOner();
            UIUserOner userOner = (UIUserOner)listUserOner.get(0);
            //ログインユーザーのオーナーコードをDTOへ設定
            getMssTantotenRankingRefDto().setOnerCd(userOner.getOnerCd());
        }
        Map paramJoken = getMssTantotenRankingRefDto().createMapJokenParam();
        /* ロジック【照会画面出力データ検索】*/
        Map mapdata = (Map)getMssTantotenRankingSearchLogic().execute(paramJoken);
        /* ロジック【ランキングデータ構築】*/
        mapdata.put("nendo", paramJoken.get("nendo"));
        mapdata.put("kai", paramJoken.get("kai"));
        /*   検索処理がここで終了します。*/
        
        /*DTOへ照会データ対象の値を設定します。*/
        List rankingList = (List)getMssTantotenRankingConstructionRankingDataLogic().execute(mapdata);
        UIRankingUnionData entity = (UIRankingUnionData)rankingList.get(0);
        String searchType = getMssTantotenRankingRefDto().getSearchType();
        if(SEARCH_SV.equals(searchType)){
            getMssTantotenRankingRefDto().setMiseCd(null);
            getMssTantotenRankingRefDto().setMiseName(null);
            getMssTantotenRankingRefDto().setOnerCd(null);
            getMssTantotenRankingRefDto().setOnerName(null);
            getMssTantotenRankingRefDto().setTaishoCd(entity.getSvCd());
            getMssTantotenRankingRefDto().setTaishoName(entity.getSvNameKj());
        }else if(SEARCH_ONER.equals(searchType)){
            getMssTantotenRankingRefDto().setSvCd(null);
            getMssTantotenRankingRefDto().setSvName(null);
            getMssTantotenRankingRefDto().setMiseCd(null);
            getMssTantotenRankingRefDto().setMiseName(null);
            getMssTantotenRankingRefDto().setTaishoCd(entity.getOnerCd());
            getMssTantotenRankingRefDto().setTaishoName(entity.getOnerNameKj());
        }else if(SEARCH_MISE.equals(searchType)){
            getMssTantotenRankingRefDto().setSvCd(null);
            getMssTantotenRankingRefDto().setSvName(null);
            getMssTantotenRankingRefDto().setOnerCd(null);
            getMssTantotenRankingRefDto().setOnerName(null);
            getMssTantotenRankingRefDto().setTaishoCd(entity.getOnerCd());
            getMssTantotenRankingRefDto().setTaishoName(entity.getOnerNameKj());
        }
        getMssTantotenRankingRefDto().setTaishoSearchType(searchType);
        getMssTantotenRankingRefDto().setTaishoNendo(entity.getNendo());
        getMssTantotenRankingRefDto().setTaishoKai(entity.getKai());
        /* 画面表示用にデータの構築済みランキングデータをDTOへ設定する。*/
        getMssTantotenRankingRefDto().setRankingList(rankingList);
        /* 処理３の戻り値のマップから構成比データを取り出し、DTOへ設定を行う。*/
        List kouseiHiList =(List)mapdata.get("kouseiHiDatalist");
        List kouseiHiZenkokuDatalist =(List)mapdata.get("kouseiHiZenkokuDatalist");
        /*担当店データ保持Listへ全国平均構成比データを挿入します。*/
        kouseiHiList.add(1, kouseiHiZenkokuDatalist.get(0));
        getMssTantotenRankingRefDto().setKouseiHiList(kouseiHiList);
        //画面遷移情報設定
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_VIEW);
        getMssTantotenRankingRefDto().setViewId(VIEWID_REF);
        return VIEWID_REF;
    }
    /**
     * オーナー検索フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callOnerForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssTantotenRankingRefDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        //遷移元情報を設定
        getOwnerSearchDto().setNavigationCase(dto.getViewId());
        //初期化
        getOwnerSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssTantotenRankingRefDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_ONERINFO);
        return VIEWID_ONERSEARCH;
    }
    /**
     * 店検索フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssTantotenRankingRefDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
       //遷移元情報を設定
        getMiseSearchDto().setNavigationCase(dto.getViewId());
        //初期化
        getMiseSearchDto().setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssTantotenRankingRefDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_MISEINFO);
        return VIEWID_MISESEARCH;
    }
    /**
     * SV検索フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callSvForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssTantotenRankingRefDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        //遷移元情報を設定
        getNewSvSearchDto().setNavigationCase(dto.getViewId());
        //初期化
        getNewSvSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssTantotenRankingRefDto().getCompanyCd());
        getNewSvSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssTantotenRankingRefDto().setScenechangedKbn(SCENECHANGE_KBN_SVINFO);
        return VIEWID_SVSEARCH;
    }
    /**
     * 戻る処理
     * 
     * @return
     * @throws Exception
     */
    public String back() throws Exception {
        // 照会画面-->条件画面の遷移区分を立てる
        getMssTantotenRankingRefDto().setScenechangedKbn(
                SCENECHANGE_KBN_INIT);
        return VIEWID_CONDITION;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public ConditionLogic getMssTantotenRankingConditionLogic() {
        return conditionLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setMssTantotenRankingConditionLogic(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }
    /**
     * 検索処理時ロジック取得処理
     * @return changeCompanyLogic を戻します。
     */
    public SearchLogic getMssTantotenRankingSearchLogic() {
        return searchLogic;
    }
    /**
     * 検索処理時ロジック設定処理
     * @param changeCompanyLogic を設定。
     */
    public void setMssTantotenRankingSearchLogic(SearchLogic logic) {
        this.searchLogic = logic;
    }
    /**
     * データ構築処理時ロジック取得処理
     * @return constructionRankingDataLogic を戻します。
     */
    public ConstructionRankingDataLogic getMssTantotenRankingConstructionRankingDataLogic() {
        return constructionRankingDataLogic;
    }
    /**
     * データ構築処理時ロジック設定処理
     * @param constructionRankingDataLogic を設定。
     */
    public void setMssTantotenRankingConstructionRankingDataLogic(ConstructionRankingDataLogic logic) {
        this.constructionRankingDataLogic = logic;
    }
    /**
     * @return dtoCsv を戻します。
     */
    public MssTantotenRankingRefDto getMssTantotenRankingRefDto() {
        return this.dto;
    }
    /**
     * @param dtoCsv を設定。
     */
    public void setMssTantotenRankingRefDto(MssTantotenRankingRefDto dto) {
        this.dto = dto;
    }
    /**
     * オーナー検索DTO取得処理
     * @return
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return onerSearchDto;
    }
    /**
     * オーナー検索DTO設定処理
     * 
     * @param miseSearchDto
     */
    public void setOwnerSearchDto(OwnerSearchDto dto) {
        this.onerSearchDto = dto;
    }
    /**
     * SV検索DTO取得処理
     * @return svSearchDto
     */
    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }
    /**
     * SV検索DTO設定処理
     * 
     * @param newSvSearchDto
     */
    public void setNewSvSearchDto(SvSearchDto dto) {
        this.newSvSearchDto = dto;
    }
    /**
     * 店検索DTO取得処理
     * @return
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * 店検索DTO設定処理
     * 
     * @param miseSearchDto
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
}