/*
 * 作成日: 2006/06/30
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.MssOnerPointRankRefAction;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dto.MssOnerPointRankCsvDto;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dto.MssOnerPointRankDto;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.ChangeCompanyLogic;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.CsvDataConstructionLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 条件画面アクション
 * 
 * @author xkinu
 */
public class MssOnerPointRankRefActionImpl 
    implements MssOnerPointRankRefAction {
    private static final String VIEWID_ONERSEARCH   = "BCO006V01";//オーナー選択
    private static final String VIEWID_MISESEARCH   = "BCO008V01";//店選択
    private static final String VIEWID_SVSEARCH   = "BCO011V01";//SV選択
    /** 画面遷移区分：初期値 [-1] */
    public static final int SCENECHANGE_KBN_INIT = -1;
    /** 画面遷移区分：現行画面からの遷移時 [0] */
    public static final int SCENECHANGE_KBN_SELF = 0;
    /** 画面遷移区分：照会画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_ONERINFO = 10;
    /** 画面遷移区分：照会画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_MISEINFO = 20;
    /** 画面遷移区分：照会画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_SVINFO = 30;
    
    /** 対象オーナー：支部 */
    public static final String SEARCH_SIBU = "SIBU";
    /** 対象オーナー：オーナー */
    public static final String SEARCH_ONER = "ONER";
    /** 対象オーナー：個店 */
    public static final String SEARCH_MISE = "MISE";
    /*【ロジック】条件画面出力データ検索ロジック */
    private CsvOutputAction csvPointRankDownloadAction;
    /*【ロジック】条件画面出力データ検索ロジック */
    private ConditionLogic conditionLogic;
    /*【LOGIC】会社コード変更時ロジック */
    private ChangeCompanyLogic changeCompanyLogic;
    /*【LOGIC】会社コード変更時ロジック */
    private CsvDataConstructionLogic csvDataConstructionLogic;
    /*【DTO】*/
    private MssOnerPointRankCsvDto dtoCsv;
    /*【DTO】SV検索DTO */
    private SvSearchDto newSvSearchDto;
    /*【DTO】オーナー検索DTO */
    private OwnerSearchDto onerSearchDto;
    /*【DTO】店検索DTO */
    private MiseSearchDto miseSearchDto;
    /**
     * 初期処理
     * @return null
     */
    public String initialize() throws Exception {
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        if(!MssOnerPointRankDto.USERTYPE_HONBU.equals(userTypeCd)){
            //本部以外のユーザーは使用権限のExceptionを発生
            throw new CannotAccessException();
        }
        int kbn  = getMssOnerPointRankCsvDto().getScenechangedKbn();
        if(kbn==SCENECHANGE_KBN_ONERINFO) {
            //オーナー索画面から戻ってきたとき
            getMssOnerPointRankCsvDto().setSearchType(SEARCH_ONER);
            getMssOnerPointRankCsvDto().setOnerCd(getOwnerSearchDto().getOnerCd());
            getMssOnerPointRankCsvDto().setOnerName(getOwnerSearchDto().getOnerNameKj());
            
        }else if(kbn==SCENECHANGE_KBN_MISEINFO) {
            //店検索画面から戻ってきたとき
            getMssOnerPointRankCsvDto().setSearchType(SEARCH_MISE);
            getMssOnerPointRankCsvDto().setMiseCd(getMiseSearchDto().getMiseCd());
            
        }else if(kbn==SCENECHANGE_KBN_SVINFO) {
            //SV検索画面から戻ってきたとき
            getMssOnerPointRankCsvDto().setSvCd(getNewSvSearchDto().getSvCd());
            getMssOnerPointRankCsvDto().setSvName(getNewSvSearchDto().getSvNameKj());
            //getMssOnerPointRankCsvDto().setSibuCd(getSvSearchDto().getSibuCd());
            
        }else if(kbn!=SCENECHANGE_KBN_SELF) {
            //Dto初期化処理
            getMssOnerPointRankCsvDto().initClear();
            //Dtoへシステム日付設定
            getMssOnerPointRankCsvDto().setToday(getBirdDateInfo().getSysDate());
            //Dtoへアプリ日付設定
            getMssOnerPointRankCsvDto().setAppDate(getBirdDateInfo().getAppDate());
            //Dtoへユーザータイプ設定(JSF内判断処理用)
            getMssOnerPointRankCsvDto().setUserId(getBirdUserInfo().getUserID());
            //Dtoへユーザータイプ設定(JSF内判断処理用)
            getMssOnerPointRankCsvDto().setUsertypeCd(userTypeCd);
            //Dtoへユーザータイプ設定(JSF内判断処理用)
            getMssOnerPointRankCsvDto().setLimit(getBirdUserInfo().isLimit());
            /*
             * ロジック【条件画面出力データ検索】
             */
            getMssOnerPointRankConditionLogic().execute(getMssOnerPointRankCsvDto());
            //Dtoへアプリ日付設定
            getMssOnerPointRankCsvDto().setSearchType(MssOnerPointRankDto.TAISHO_SIBU);
        }
        getMssOnerPointRankCsvDto().setViewId(VIEWID_CONDITION);
        getMssOnerPointRankCsvDto().setScenechangedKbn(SCENECHANGE_KBN_INIT);
        return null;
    }
    /**
     * 会社プルダウン変更処理
     * 
     * 1.対象条件プルダウンリスト内容変更
     * 2.表示対象プルダウンリスト内容変更
     */
    public String changeCompany()  throws Exception {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssOnerPointRankCsvDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        /* ロジック【条件画面出力データ検索】*/
        getMssOnerPointRankChangeCompanyLogic().execute(getMssOnerPointRankCsvDto());
        return null;
    }
    /**
     * 店検索フォーム呼び出し処理
     * @return 店検索フォームViewID
     */
    public String callOnerForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssOnerPointRankCsvDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        //遷移元情報を設定
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        //初期化
        getOwnerSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssOnerPointRankCsvDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssOnerPointRankCsvDto().setScenechangedKbn(SCENECHANGE_KBN_ONERINFO);
        return VIEWID_ONERSEARCH;
    }
    /**
     * 店検索フォーム呼び出し処理
     * @return 店検索フォームViewID
     */
    public String callMiseForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssOnerPointRankCsvDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
       //遷移元情報を設定
        getMiseSearchDto().setNavigationCase(VIEWID_CONDITION);
        //初期化
        getMiseSearchDto().setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssOnerPointRankCsvDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssOnerPointRankCsvDto().setScenechangedKbn(SCENECHANGE_KBN_MISEINFO);
        return VIEWID_MISESEARCH;
    }
    /**
     * SV検索フォーム呼び出し処理
     * @return SV検索フォームViewID
     */
    public String callSvForm() throws Exception  {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssOnerPointRankCsvDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        //遷移元情報を設定
        getNewSvSearchDto().setNavigationCase(VIEWID_CONDITION);
        //初期化
        getNewSvSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMssOnerPointRankCsvDto().getCompanyCd());
        getNewSvSearchDto().setRCompanyCdList(listCompany);
        //画面遷移情報設定
        getMssOnerPointRankCsvDto().setScenechangedKbn(SCENECHANGE_KBN_SVINFO);
        return VIEWID_SVSEARCH;
    }
    /**
     * CSVダウンロード処理処理
     *
     */
    public String downloadCsv() throws Exception {
        // 条件画面-->条件画面の遷移区分を立てる
        getMssOnerPointRankCsvDto().setScenechangedKbn(
                SCENECHANGE_KBN_SELF);
        Map paramJoken = getMssOnerPointRankCsvDto().createMapJokenParam();
        List csvList = getMssOnerPointRankCsvDataConstructionLogic().execute(paramJoken);
        getMssOnerPointRankCsvDto().setCsvList(csvList);
        //【ACTION】CSVダウンロード実行
        getCsvPointRankDownloadAction().downloadCsv();
        return null;
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
     * CSVダウンロードアクション取得処理
     * @return csvPointRankDownloadAction を戻します。
     */
    public CsvOutputAction getCsvPointRankDownloadAction() {
        return csvPointRankDownloadAction;
    }
    /**
     * CSVダウンロードアクション設定処理
     * @param csvPointRankDownloadAction を設定。
     */
    public void setCsvPointRankDownloadAction(CsvOutputAction action) {
        this.csvPointRankDownloadAction = action;
    }
    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public ConditionLogic getMssOnerPointRankConditionLogic() {
        return conditionLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setMssOnerPointRankConditionLogic(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }
    /**
     * 会社コード変更時ロジック取得処理
     * @return changeCompanyLogic を戻します。
     */
    public ChangeCompanyLogic getMssOnerPointRankChangeCompanyLogic() {
        return changeCompanyLogic;
    }
    /**
     * 会社コード変更時ロジック設定処理
     * @param changeCompanyLogic を設定。
     */
    public void setMssOnerPointRankChangeCompanyLogic(ChangeCompanyLogic logic) {
        this.changeCompanyLogic = logic;
    }
    /**
     * CSVデータ構築ロジック取得処理
     * @return csvDataConstructionLogic を戻します。
     */
    public CsvDataConstructionLogic getMssOnerPointRankCsvDataConstructionLogic() {
        return csvDataConstructionLogic;
    }
    /**
     * CSVデータ構築ロジック設定処理
     * @param csvDataConstructionLogic を設定。
     */
    public void setMssOnerPointRankCsvDataConstructionLogic(CsvDataConstructionLogic logic) {
        this.csvDataConstructionLogic = logic;
    }
   /**
     * @return dtoCsv を戻します。
     */
    public MssOnerPointRankCsvDto getMssOnerPointRankCsvDto() {
        return this.dtoCsv;
    }
    /**
     * @param dtoCsv を設定。
     */
    public void setMssOnerPointRankCsvDto(MssOnerPointRankCsvDto dto) {
        this.dtoCsv = dto;
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