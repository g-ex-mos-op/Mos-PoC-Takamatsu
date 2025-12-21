package jp.co.isid.mos.bird.entry.hanyoviewlist.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.entry.hanyoviewlist.action.HanyoViewListSelectAction;
import jp.co.isid.mos.bird.entry.hanyoviewlist.dto.HanyoViewListDto;
import jp.co.isid.mos.bird.entry.hanyoviewlist.logic.GetHanyoListLogic;
import jp.co.isid.mos.bird.entry.hanyoviewlist.logic.GetHanyoEntryListLogic;


import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Converter;
import jp.co.isid.mos.bird.framework.util.DateManager;

import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoListDataInfo;
import jp.co.isid.mos.bird.framework.action.CommonAction;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class HanyoViewListSelectActionImpl implements CommonAction, HanyoViewListSelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID        = "BEN006A01";
    public static String execute_ACTION_ID           = "BEN006A02";
    public static String callMiseInfo_ACTION_ID      = "BEN006A03";
    public static String callMiseInfoResult_ACTION_ID = "BEN006A04";
    
    /* ビューID */
    private static final String VIEWID_RESULT       = "BEN006V02"; //結果画面
    

    /* ACTION */
    
    /* LOGIC */
    // ベーシック研修一覧取得Logic //
    private GetHanyoListLogic getHanyoListLogic;
    // ベーシック研修エントリー者一覧取得Logic //
    private GetHanyoEntryListLogic getHanyoEntryListLogic;
    
    /* DTO */
    // ベーシック研修申込状況確認Dto //
    private HanyoViewListDto hanyoViewListDto;
    // ベーシック研修申込状況確認CommonDto //
    private HanyoViewListDto hanyoViewListCommonDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;    
    
   
       
    ////////////////////
    /*     ACTION     */
    ////////////////////

    
    /////////////////
    /*     DTO     */
    /////////////////
    /**
     * 研修申込状況確認dtoの設定
     * @return hanyoViewListDto を戻します。
     */
    public HanyoViewListDto getHanyoViewListDto() {
        return hanyoViewListDto;
    }
    /**
     * 研修申込状況確認dtoの設定
     * @param hanyoViewListDto を設定。
     */
    public void setHanyoViewListDto(HanyoViewListDto hanyoViewListDto) {
        this.hanyoViewListDto = hanyoViewListDto;
    }
    
    /**
     * 研修申込状況確認CommonDtoの設定
     * @return hanyoViewListCommonDto を戻します。
     */
    public HanyoViewListDto getHanyoViewListCommonDto() {
        return hanyoViewListCommonDto;
    }
    /**
     * 研修申込状況確認CommonDtoの設定
     * @param hanyoViewListCommonDto を設定。
     */
    public void setHanyoViewListCommonDto(HanyoViewListDto hanyoViewListCommonDto) {
        this.hanyoViewListCommonDto = hanyoViewListCommonDto;
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
    
    ///////////////////
    /*     LOGIC     */
    ///////////////////
    /**
     * ベーシック研修一覧取得Logicの設定
     * @return getHanyoListLogic を戻します。
     */
    public GetHanyoListLogic getHanyoListLogic() {
        return getHanyoListLogic;
    }
    /**
     * ベーシック研修一覧取得Logicの設定
     * @param getHanyoListLogic を設定。
     */
    public void setGetHanyoListLogic(GetHanyoListLogic getHanyoListLogic) {
        this.getHanyoListLogic = getHanyoListLogic;
    }
    
    /**
     * ベーシック研修エントリー者一覧取得Logicの設定
     * @return getHanyoEntryListLogic を戻します。
     */
    public GetHanyoEntryListLogic getHanyoEntryListLogic() {
        return getHanyoEntryListLogic;
    }
    /**
     * ベーシック研修エントリー者一覧取得Logicの設定
     * @param getHanyoEntryListLogic を設定。
     */
    public void setGetHanyoEntryListLogic(GetHanyoEntryListLogic getHanyoEntryListLogic) {
        this.getHanyoEntryListLogic = getHanyoEntryListLogic;
    }
    

    ////////////////////
    /*   INITIALIZE   */
    ////////////////////
    /**
     * 研修(出張/更新)申込状況照会 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        

        if (pullDownMenuDto.isClearFlg()) {

            //// 初期処理 ////
            
            // DTO
            hanyoViewListDto = getHanyoViewListDto();
            
            hanyoViewListCommonDto.setEntryCd("ZZ");//エントリーコード
            hanyoViewListCommonDto.setEntryYear("");//エントリー年
            hanyoViewListCommonDto.setEntryKai("");//エントリー回
            hanyoViewListCommonDto.setEntryTitle("");
            hanyoViewListCommonDto.setFromDt("");
            hanyoViewListCommonDto.setToDt("");
            hanyoViewListCommonDto.setHonbuInputDtFrom("");
            hanyoViewListCommonDto.setHonbuInputDtTo("");
            hanyoViewListCommonDto.setOnerInputDtFrom("");
            hanyoViewListCommonDto.setOnerInputDtTo("");
            hanyoViewListCommonDto.setNumberLimit("");
            hanyoViewListCommonDto.setEntryNum("");
            
            hanyoViewListCommonDto.setBirdUserInfo(null);
            hanyoViewListCommonDto.setHanyoListData(null);//研修(出張/更新)一覧データ
            hanyoViewListCommonDto.setUserId("");
            hanyoViewListCommonDto.setLimitFlg(false);
            hanyoViewListCommonDto.setIndexFlg("0");//ラジオボタンの初期化
            hanyoViewListCommonDto.setSysDate("");//システム日付
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
            
            // SV制限区分
            boolean limitFlg = getBirdUserInfo().isLimit();
            
            // システム日付をセット
            hanyoViewListCommonDto.setSysDate(sysdate);
            // システム日付の翌日日付をセット
            hanyoViewListCommonDto.setSysNextDate(sysnextdate);
            // ユーザー判定区分をセット
            hanyoViewListCommonDto.setUserTypeCd(userType);
            // ユーザーIDをセット
            hanyoViewListCommonDto.setUserId(mstUser.getUser_id());
            // SV制限区分をセット
            hanyoViewListCommonDto.setLimitFlg(limitFlg);
            
                  
            // 研修(出張/更新)一覧の取得
            changePullDown();
            
            //hanyoViewListCommonDto.setHanyoListData(getHanyoListLogic.execute(sysdate, sysnextdate, hanyoViewListCommonDto.getEntryCd()));
            //if(hanyoViewListCommonDto.getHanyoListData() == null){
                // 表示期間中のベーシック研修がない場合
                // 何もしない
            //}
            
        }
        
        return null;
    }
    
    
    /////////////////////////
    /*   CHANGE PULLDOWN   */
    /////////////////////////
    /**
     * プルダウン変更
     * @return 画面遷移情報
     */
    public String changePullDown() throws ApplicationException{

        // ラジオボタンの初期化
        hanyoViewListCommonDto.setIndexFlg("0");
        
        // 前回の検索結果をクリア
        hanyoViewListCommonDto.setHanyoListData(null);
        
        // １．ロジック【エントリーマスタ管理の検索】を実行する。
        // パラメータ　：　プルダウンで選択されたエントリーコード（05：出張特別研修   30：更新研修）
        hanyoViewListCommonDto.setHanyoListData(getHanyoListLogic().execute(hanyoViewListCommonDto));

        if(hanyoViewListCommonDto.getHanyoListData() == null || hanyoViewListCommonDto.getHanyoListData().size() == 0){
            throw new NotExistException("選択可能な研修データ");
        }
        // ２．１で取得したデータを初期画面にセットする。ラジオボタンは未選択状態とする。

        // ３．研修選択プルダウンにフォーカスをセットする。
        
        return null;
    }
    
       
    
    /////////////////
    /*   EXECUTE   */
    /////////////////
    /**
     * 研修(出張/更新)申込状況照会 実行処理
     * @return 画面遷移情報
     */
    public String execute() {

        // 選択チェック
        check();
        
        // 研修(出張/更新)エントリー者一覧取得Logic
        getHanyoEntryListLogic = getHanyoEntryListLogic();
        
        // 選択行インデックス
        int selectIndex = Integer.valueOf(hanyoViewListCommonDto.getIndexFlg()).intValue();
        
        // 選択された行のキー情報を取得
        UIHanyoListDataInfo hanyoListData = (UIHanyoListDataInfo) hanyoViewListCommonDto.getHanyoListData().get(selectIndex);
        hanyoViewListCommonDto.setKenshuKbn("");
        if(hanyoViewListCommonDto.getEntryCd().equals("ZZ")){
            hanyoViewListCommonDto.setKenshuKbn("ALL");
        }
        hanyoViewListCommonDto.setEntryCd(hanyoListData.getEntryCd());
        hanyoViewListCommonDto.setEntryYear(hanyoListData.getEntryYear());
        hanyoViewListCommonDto.setEntryKai(hanyoListData.getEntryKai());
        hanyoViewListCommonDto.setEntryTitle(hanyoListData.getEntryTitle());
        hanyoViewListCommonDto.setEntryPlace(hanyoListData.getEntryPlace());
        hanyoViewListCommonDto.setEntryNameRyaku(hanyoListData.getEntryNameRyaku());
        hanyoViewListCommonDto.setFromDt(hanyoListData.getFromDt());
        hanyoViewListCommonDto.setToDt(hanyoListData.getToDt());
        hanyoViewListCommonDto.setHonbuInputDtFrom(hanyoListData.getHonbuInputDtFrom());
        hanyoViewListCommonDto.setHonbuInputDtTo(hanyoListData.getHonbuInputDtTo());
        hanyoViewListCommonDto.setOnerInputDtFrom(hanyoListData.getOnerInputDtFrom());
        hanyoViewListCommonDto.setOnerInputDtTo(hanyoListData.getOnerInputDtTo());
        hanyoViewListCommonDto.setNumberLimit(hanyoListData.getNumberLimit());
        hanyoViewListCommonDto.setEntryNum(hanyoListData.getEntryNum());
        
        
        // エントリー者数チェック
        if(hanyoViewListCommonDto.getEntryNum().equals("0")){
            // 対象研修コンボボックス設定
            if(hanyoViewListCommonDto.getKenshuKbn().equals("ALL")){
                hanyoViewListCommonDto.setEntryCd("ZZ");
            }
            // 選択されたベーシック研修の参加者データがない場合
            throw new NotExistException("申込者", "", "");
        }

        
        String sysdate   = hanyoViewListCommonDto.getSysDate();
        String entryCd   = hanyoViewListCommonDto.getEntryCd();
        String entryYear = hanyoViewListCommonDto.getEntryYear();
        String entryKai  = hanyoViewListCommonDto.getEntryKai();
        String kbn       = "";
        
        // 選択された行の照会情報を取得
        hanyoViewListCommonDto.setHanyoEntryList(getHanyoEntryListLogic.execute(sysdate, entryCd, entryYear, entryKai, kbn));
        if(hanyoViewListCommonDto.getHanyoEntryList() == null){
            // 選択されたベーシック研修の参加者データがない場合

        }
        
        return VIEWID_RESULT;
    }
    
    
    /**
     * 入力チェック
     */
    private void check() {
        
        // 必須選択チェック
        
        String indexFlg = hanyoViewListCommonDto.getIndexFlg();
        if (indexFlg == null || indexFlg.equals("")) {
            throw new NotNullException("研修の選択", "indexFlg", "1");
        }
        
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
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

}