package jp.co.isid.mos.bird.entry.mlviewlist.action.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.entry.mlviewlist.action.MlViewListSelectAction;
import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlEntryInfo;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlListDataInfo;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.GetMlEntryListLogic;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.GetMlListLogic;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class MlViewListSelectActionImpl implements MlViewListSelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID          = "BEN009A01";
    public static String execute_ACTION_ID             = "BEN009A02";
    public static String callMiseInfo_ACTION_ID        = "BEN009A03";
    public static String callMiseInfoResult_ACTION_ID  = "BEN009A04";
    public static String downloadCsv_ACTION_ID         = "BEN009A05";
    
    /* ビューID */
    private static final String VIEWID_RESULT         = "BEN009V02"; //結果画面
    

    /* ACTION */
    
    /* LOGIC */
    // マスターライセンス一覧取得Logic //
    private GetMlListLogic getMlListLogic;
    // マスターライセンスエントリー者一覧取得Logic //
    private GetMlEntryListLogic getMlEntryListLogic;
    
    /* DTO */
    // マスターライセンス申込状況確認Dto //
    private MlViewListDto mlViewListDto;
    // マスターライセンス申込状況確認CommonDto //
    private MlViewListDto mlViewListCommonDto;
    
    
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    
    /* 支部取得 */
    private GetSibuTorikomiLogic getSibuTorikomiLogic;
    /* 店舗選択 */
    private MiseSearchDto miseSearchDto;
    /* オーナー選択 */
    private OwnerSearchDto ownerSearchDto;
   
       
    ////////////////////
    /*     ACTION     */
    ////////////////////
    private CsvOutputAction mlViewListCsvOutputAction;
    
    /////////////////
    /*     DTO     */
    /////////////////
    /**
     * マスターライセンス申込状況確認dtoの設定
     * @return mlViewListDto を戻します。
     */
    public MlViewListDto getMlViewListDto() {
        return mlViewListDto;
    }
    /**
     * マスターライセンス申込状況確認dtoの設定
     * @param mlViewListDto を設定。
     */
    public void setMlViewListDto(MlViewListDto mlViewListDto) {
        this.mlViewListDto = mlViewListDto;
    }
    
    /**
     * マスターライセンス申込状況確認CommonDtoの設定
     * @return mlViewListEntryCommonDto を戻します。
     */
    public MlViewListDto getMlViewListCommonDto() {
        return mlViewListCommonDto;
    }
    /**
     * マスターライセンス申込状況確認CommonDtoの設定
     * @param mlViewListCommonDto を設定。
     */
    public void setMlViewListCommonDto(MlViewListDto mlViewListCommonDto) {
        this.mlViewListCommonDto = mlViewListCommonDto;
    }
    
    
    /**
     * 初期処理判定Dtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * 初期処理判定Dtoの設定
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    
    /**
     * @return miseSearchDto を戻します。
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * @param miseSearchDto miseSearchDto を設定。
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
     * @param ownerSearchDto ownerSearchDto を設定。
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }
    
    ///////////////////
    /*     LOGIC     */
    ///////////////////
    /**
     * マスターライセンス一覧取得Logicの設定
     * @return getMlListLogic を戻します。
     */
    public GetMlListLogic getMlListLogic() {
        return getMlListLogic;
    }
    /**
     * マスターライセンス一覧取得Logicの設定
     * @param getMlListLogic を設定。
     */
    public void setGetMlListLogic(GetMlListLogic getMlListLogic) {
        this.getMlListLogic = getMlListLogic;
    }
    
    /**
     * マスターライセンスエントリー者一覧取得Logicの設定
     * @return getMlEntryListLogic を戻します。
     */
    public GetMlEntryListLogic getMlEntryListLogic() {
        return getMlEntryListLogic;
    }
    /**
     * マスターライセンスエントリー者一覧取得Logicの設定
     * @param getMlEntryListLogic を設定。
     */
    public void setGetMlEntryListLogic(GetMlEntryListLogic getMlEntryListLogic) {
        this.getMlEntryListLogic = getMlEntryListLogic;
    }
    /**
     * @return getSibuTorikomiLogic を戻します。
     */
    public GetSibuTorikomiLogic getGetSibuTorikomiLogic() {
        return getSibuTorikomiLogic;
    }
    /**
     * @param getSibuTorikomiLogic 設定する getSibuTorikomiLogic。
     */
    public void setGetSibuTorikomiLogic(GetSibuTorikomiLogic getSibuTorikomiLogic) {
        this.getSibuTorikomiLogic = getSibuTorikomiLogic;
    }
    

    ////////////////////
    /*   INITIALIZE   */
    ////////////////////
    /**
     * マスターライセンス申込状況照会 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {

        if (pullDownMenuDto.isClearFlg()) {
            //// 初期処理 ////
            
            // DTO
            mlViewListDto = getMlViewListDto();

            mlViewListCommonDto.setEntryCd("10");//エントリーコード
            mlViewListCommonDto.setEntryYear("");//エントリー年
            mlViewListCommonDto.setEntryKai("");//エントリー回
            mlViewListCommonDto.setMlEntryTitle("");
            mlViewListCommonDto.setMlFromDt("");
            mlViewListCommonDto.setMlToDt("");
            mlViewListCommonDto.setMlHonbuInputDtFrom("");
            mlViewListCommonDto.setMlHonbuInputDtTo("");
            mlViewListCommonDto.setMlOnerInputDtFrom("");
            mlViewListCommonDto.setMlOnerInputDtTo("");
            mlViewListCommonDto.setMlNumberLimit("");
            mlViewListCommonDto.setMlEntryNum("");
            
            mlViewListCommonDto.setMlListData(null);//ライセンス一覧データ
            mlViewListCommonDto.setMlListDataSize();//ライセンス一覧件数
            mlViewListCommonDto.setUserId("");
            mlViewListCommonDto.setIndexFlg("0");//ラジオボタンの初期化
            mlViewListCommonDto.setSysDate("");//システム日付

            // TODO 検索条件初期化
            String searchSelectFlg = null;
            String searchCompanyCd = "00";
            String searchOnerCd = null;
            if (getBirdUserInfo().getMstUser().getUserTypeCd().equals("01")) {
                searchSelectFlg = "3";
                searchOnerCd = null;
            } else if (getBirdUserInfo().getMstUser().getUserTypeCd().equals("02")) {
                searchSelectFlg = "1";
                searchOnerCd = getOnerCd(searchCompanyCd, getBirdUserInfo().getUserOner());
            }
            mlViewListCommonDto.setSearchSelectFlg(searchSelectFlg);
            mlViewListCommonDto.setSearchCompanyCd(searchCompanyCd);
            mlViewListCommonDto.setSearchSibuCd(null);
            mlViewListCommonDto.setSearchOnerCd(searchOnerCd);
            mlViewListCommonDto.setSearchMiseCd(null);

            getPullDownMenuDto().setClearFlg(false);
            
            // MstUser取得
            MstUser mstUser = getBirdUserInfo().getMstUser();
            // ユーザータイプ判別
            String userType = mstUser.getUserTypeCd();
            // システム日付取得
            String sysdate = getBirdDateInfo().getSysDate();
            
            // システム日付の翌日日付取得
            String sysnextdate = "";
            try{
                sysnextdate = DateManager.getNextDate(sysdate, 1);
            }catch (Exception e) {
                throw new FtlSystemException("翌日日付取得時に");
            }
            
            // システム日付をセット
            mlViewListCommonDto.setSysDate(sysdate);
            // システム日付の翌日日付をセット
            mlViewListCommonDto.setSysNextDate(sysnextdate);
            // ユーザー判定区分をセット
            mlViewListCommonDto.setUserTypeCd(userType);
            // ユーザーIDをセット
            mlViewListCommonDto.setUserId(mstUser.getUser_id());
            // 支部取得
            List sibuTorikomiList = getGetSibuTorikomiLogic().execute(
                    mlViewListCommonDto.getSearchCompanyCd(), getBirdUserInfo().getUserID(), getBirdUserInfo().isLimit());
            mlViewListCommonDto.setSearchSibuList(sibuTorikomiList);
            
            // マスターライセンス一覧の取得
            getMasterLicenceList();
            
        }
        /* 店情報取得 */
        if (getMiseSearchDto().isActionFlg()) {
            mlViewListCommonDto.setSearchMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
                    
        /*オーナーコード取得*/
        if(getOwnerSearchDto().isActionFlag()){
            mlViewListCommonDto.setSearchOnerCd(getOwnerSearchDto().getOnerCd());
            getOwnerSearchDto().setActionFlag(false);
        }
        
        return null;
    }
    
    

    /**
     * マスターライセンス一覧を取得する。
     * ※初期表示にのみ実行
     * @return
     */
    private String getMasterLicenceList() throws ApplicationException{
        
        mlViewListCommonDto.setMlListData(null);
        mlViewListCommonDto.setMlListData(getMlListLogic().execute(mlViewListCommonDto));
        mlViewListCommonDto.setMlListDataSize();

        if(mlViewListCommonDto.getMlListData() == null || mlViewListCommonDto.getMlListData().size() == 0){
            throw new NotExistException("選択可能なマスターライセンスデータ");
        }
        return null;
    }
    
       
    
    /////////////////
    /*   EXECUTE   */
    /////////////////
    /**
     * マスターライセンス申込状況照会 実行処理
     * @return 画面遷移情報
     */
    public String execute() {

        // 選択チェック
        check();
        
        // 選択行インデックス
        int selectIndex = Integer.valueOf(mlViewListCommonDto.getIndexFlg()).intValue();
        
        // 選択された行のキー情報を取得
        UIMlListDataInfo mlListData = (UIMlListDataInfo) mlViewListCommonDto.getMlListData().get(selectIndex);
        mlViewListCommonDto.setEntryCd(mlListData.getEntryCd());
        mlViewListCommonDto.setEntryYear(mlListData.getEntryYear());
        mlViewListCommonDto.setEntryKai(mlListData.getEntryKai());
        mlViewListCommonDto.setMlEntryTitle(mlListData.getEntryTitle());
        mlViewListCommonDto.setMlEntryPlace(mlListData.getEntryPlace());
        mlViewListCommonDto.setMlEntryNameRyaku(mlListData.getEntryNameRyaku());
        mlViewListCommonDto.setMlFromDt(mlListData.getFromDt());
        mlViewListCommonDto.setMlToDt(mlListData.getToDt());
        mlViewListCommonDto.setMlHonbuInputDtFrom(mlListData.getHonbuInputDtFrom());
        mlViewListCommonDto.setMlHonbuInputDtTo(mlListData.getHonbuInputDtTo());
        mlViewListCommonDto.setMlOnerInputDtFrom(mlListData.getOnerInputDtFrom());
        mlViewListCommonDto.setMlOnerInputDtTo(mlListData.getOnerInputDtTo());
        mlViewListCommonDto.setMlNumberLimit(mlListData.getNumberLimit());
        mlViewListCommonDto.setMlEntryNum(mlListData.getEntryNum());
        mlViewListCommonDto.setNote(mlListData.getNote());
        mlViewListCommonDto.setExecutableSaiban(mlListData.isExecutableSaiban());
        
        String sysdate   = mlViewListCommonDto.getSysDate();
        String entryCd   = mlViewListCommonDto.getEntryCd();
        String entryYear = mlViewListCommonDto.getEntryYear();
        String entryKai  = mlViewListCommonDto.getEntryKai();
        
        String searchSelectFlg = mlViewListCommonDto.getSearchSelectFlg();
        String sibuCd = mlViewListCommonDto.getSearchSibuCd();
        String onerCd = mlViewListCommonDto.getSearchOnerCd();
        String miseCd = mlViewListCommonDto.getSearchMiseCd();
        
        // マスターライセンスエントリー者一覧取得Logic
        getMlEntryListLogic = getMlEntryListLogic();
        
        //検索処理
        mlViewListCommonDto.setEntryList(
                getMlEntryListLogic
                        .execute("",//CSV出力ではないため空
                                sysdate,
                                entryCd,
                                entryYear,
                                entryKai,
                                searchSelectFlg,
                                sibuCd,
                                onerCd,
                                miseCd)
        );
        
        
        if(mlViewListCommonDto.getEntryList() == null || mlViewListCommonDto.getEntryListSize() <= 0){
            // 選択されたマスターライセンスの参加者データがない場合
            throw new NotExistException("申込者", "", "");
        }
        else{
            //エントリーリスト修正
            createEntryList();            
        }
        
        return VIEWID_RESULT;
    }
    
    /** 
     * エントリーリストデータ修正
     */
    private void createEntryList(){
        List entryList = mlViewListCommonDto.getEntryList();
        UIMlEntryInfo util;
        for(int i=0; i<entryList.size(); i++){
            util = (UIMlEntryInfo)entryList.get(i);

            util.setAbilityFlg(util.getAbilityChk().equals("0") || util.getAbilityChk().equals("1"));
            util.setExamFlg(util.getExamChk().equals("0") || util.getExamChk().equals("1"));
            util.setInterviewFlg(util.getInterviewChk().equals("0") || util.getInterviewChk().equals("1"));
            
            entryList.set(i,util);
        }
        mlViewListCommonDto.setEntryList(entryList);
    }
    
    
    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {

        // CSVダウンロード
        try {
            getMlViewListCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    public CsvOutputAction getMlViewListCsvOutputAction() {
        return mlViewListCsvOutputAction;
    }
    public void setHanyoViewListCsvOutputAction(
            CsvOutputAction mlViewListCsvOutputAction) {
        this.mlViewListCsvOutputAction = mlViewListCsvOutputAction;
    }
    
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    
    /**
     *  オーナー選択処理
     */
    public String onerSearch(){
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNavigationCase(MlViewListDto.condition_VIEW_ID);
        return MlViewListDto.onerSearch_VIEW_ID;
    }
    
    /**
     *  店舗選択処理
     */
    public String miseSrearch(){
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNavigationCase(MlViewListDto.condition_VIEW_ID);
        return MlViewListDto.miseSearch_VIEW_ID;
    }
    
    /**
     * 
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    
    /**
     * 入力チェック
     */
    private void check() {
        String indexFlg = mlViewListCommonDto.getIndexFlg();
        if (indexFlg == null || indexFlg.equals("")) {
            throw new NotNullException("研修の選択", "indexFlg", "1");
        }
    }

    private String getOnerCd(String companyCd, List ownerList) {
        String ownerCd = null;
        for (Iterator it = ownerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            if (uIUserOner.getCompanyCd().equals(companyCd)) {
                ownerCd = uIUserOner.getOnerCd();
                break;
            }
        }
        return ownerCd;
    }
}