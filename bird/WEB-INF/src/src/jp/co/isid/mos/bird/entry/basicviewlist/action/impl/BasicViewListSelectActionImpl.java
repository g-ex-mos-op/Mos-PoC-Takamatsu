package jp.co.isid.mos.bird.entry.basicviewlist.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.entry.basicviewlist.action.BasicViewListSelectAction;
import jp.co.isid.mos.bird.entry.basicviewlist.dto.BasicViewListDto;
import jp.co.isid.mos.bird.entry.basicviewlist.logic.GetBasicListLogic;
import jp.co.isid.mos.bird.entry.basicviewlist.logic.GetBasicEntryListLogic;


import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Converter;
import jp.co.isid.mos.bird.framework.util.DateManager;

import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicListDataInfo;
import jp.co.isid.mos.bird.framework.action.CommonAction;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class BasicViewListSelectActionImpl implements CommonAction, BasicViewListSelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID        = "BEN003A01";
    public static String execute_ACTION_ID           = "BEN003A02";

    
    /* ビューID */
    private static final String VIEWID_RESULT       = "BEN003V02"; //結果画面
    

    /* ACTION */
    
    /* LOGIC */
    // ベーシック研修一覧取得Logic //
    private GetBasicListLogic getBasicListLogic;
    // ベーシック研修エントリー者一覧取得Logic //
    private GetBasicEntryListLogic getBasicEntryListLogic;
    
    /* DTO */
    // ベーシック研修申込状況確認Dto //
    private BasicViewListDto basicViewListDto;
    // ベーシック研修申込状況確認CommonDto //
    private BasicViewListDto basicViewListCommonDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;    
    
   
       
    ////////////////////
    /*     ACTION     */
    ////////////////////

    
    /////////////////
    /*     DTO     */
    /////////////////
    /**
     * ベーシック研修申込状況確認dtoの設定
     * @return basicViewListDto を戻します。
     */
    public BasicViewListDto getBasicViewListDto() {
        return basicViewListDto;
    }
    /**
     * ベーシック研修申込状況確認dtoの設定
     * @param basicViewListDto を設定。
     */
    public void setBasicViewListDto(BasicViewListDto basicViewListDto) {
        this.basicViewListDto = basicViewListDto;
    }
    
    /**
     * ベーシック研修申込状況確認CommonDtoの設定
     * @return basicViewListCommonDto を戻します。
     */
    public BasicViewListDto getBasicViewListCommonDto() {
        return basicViewListCommonDto;
    }
    /**
     * ベーシック研修申込状況確認CommonDtoの設定
     * @param basicViewListCommonDto を設定。
     */
    public void setBasicViewListCommonDto(BasicViewListDto basicViewListCommonDto) {
        this.basicViewListCommonDto = basicViewListCommonDto;
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
     * @return getBasicListLogic を戻します。
     */
    public GetBasicListLogic getBasicListLogic() {
        return getBasicListLogic;
    }
    /**
     * ベーシック研修一覧取得Logicの設定
     * @param getBasicListLogic を設定。
     */
    public void setGetBasicListLogic(GetBasicListLogic getBasicListLogic) {
        this.getBasicListLogic = getBasicListLogic;
    }
    
    /**
     * ベーシック研修エントリー者一覧取得Logicの設定
     * @return getBasicEntryListLogic を戻します。
     */
    public GetBasicEntryListLogic getBasicEntryListLogic() {
        return getBasicEntryListLogic;
    }
    /**
     * ベーシック研修エントリー者一覧取得Logicの設定
     * @param getBasicEntryListLogic を設定。
     */
    public void setGetBasicEntryListLogic(GetBasicEntryListLogic getBasicEntryListLogic) {
        this.getBasicEntryListLogic = getBasicEntryListLogic;
    }
    

    ////////////////////
    /*   INITIALIZE   */
    ////////////////////
    /**
     * ベーシック研修申込状況確認 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        

        if (pullDownMenuDto.isClearFlg()) {

            //// 初期処理 ////
            
            // Dto
            basicViewListDto = getBasicViewListDto();
            // ベーシック研修一覧取得Logic
            getBasicListLogic = getBasicListLogic();
            
            basicViewListCommonDto.setEntryCd("01");//エントリーコード
            basicViewListCommonDto.setEntryYear("");//エントリー年
            basicViewListCommonDto.setEntryKai("");//エントリー回
            basicViewListCommonDto.setEntryTitle("");
            basicViewListCommonDto.setBasicDtFrom("");
            basicViewListCommonDto.setBasicDtTo("");
            basicViewListCommonDto.setRintenDtFrom("");
            basicViewListCommonDto.setRintenDtTo("");
            basicViewListCommonDto.setHonbuInputDtFrom("");
            basicViewListCommonDto.setHonbuInputDtTo("");
            basicViewListCommonDto.setOnerInputDtFrom("");
            basicViewListCommonDto.setOnerInputDtTo("");
            basicViewListCommonDto.setNumberLimit("");
            basicViewListCommonDto.setNote("");
            basicViewListCommonDto.setEntryNum("");
            
            basicViewListCommonDto.setBirdUserInfo(null);
            basicViewListCommonDto.setBasicListData(null);//ベーシック研修一覧データ
            basicViewListCommonDto.setUserId("");
            basicViewListCommonDto.setLimitFlg(false);
            basicViewListCommonDto.setIndexFlg("0");//ラジオボタンの初期化
            basicViewListCommonDto.setSysDate("");//システム日付
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
            
            //システム日付をセット
            basicViewListCommonDto.setSysDate(sysdate);
            // ユーザー判定区分をセット
            basicViewListCommonDto.setUserTypeCd(userType);
            // ユーザーIDをセット
            basicViewListCommonDto.setUserId(mstUser.getUser_id());
            // SV制限区分をセット
            basicViewListCommonDto.setLimitFlg(limitFlg);
            
                  
            // ベーシック研修一覧の取得
            basicViewListCommonDto.setBasicListData(getBasicListLogic.execute(sysdate, sysnextdate, basicViewListCommonDto.getEntryCd()));
            if(basicViewListCommonDto.getBasicListData() == null){
                // 表示期間中のベーシック研修がない場合
                // 何もしない
            }
            
        }
        
        return null;
    }
    
    
    /////////////////
    /*   EXECUTE   */
    /////////////////
    /**
     * ベーシック研修状況確認 実行処理
     * @return 画面遷移情報
     */
    public String execute() {

        // システム日付取得
        String sysdate = getBirdDateInfo().getSysDate();
        
        // 選択チェック
        check();
        
        // ベーシック研修エントリー者一覧取得Logic
        getBasicEntryListLogic = getBasicEntryListLogic();
        
        // 選択行インデックス
        int selectIndex = Integer.valueOf(basicViewListCommonDto.getIndexFlg()).intValue();
        // 選択された行のキー情報を取得
        UIBasicListDataInfo basicListData = (UIBasicListDataInfo) basicViewListCommonDto.getBasicListData().get(selectIndex);
        basicViewListCommonDto.setEntryCd(basicListData.getEntryCd());
        basicViewListCommonDto.setEntryYear(basicListData.getEntryYear());
        basicViewListCommonDto.setEntryKai(basicListData.getEntryKai());
        basicViewListCommonDto.setEntryTitle(basicListData.getEntryTitle());
        basicViewListCommonDto.setBasicDtFrom(basicListData.getBasicDtFrom());
        basicViewListCommonDto.setBasicDtTo(basicListData.getBasicDtTo());
        basicViewListCommonDto.setRintenDtFrom(basicListData.getRintenDtFrom());
        basicViewListCommonDto.setRintenDtTo(basicListData.getRintenDtTo());
        basicViewListCommonDto.setHonbuInputDtFrom(basicListData.getHonbuInputDtFrom());
        basicViewListCommonDto.setHonbuInputDtTo(basicListData.getHonbuInputDtTo());
        basicViewListCommonDto.setOnerInputDtFrom(basicListData.getOnerInputDtFrom());
        basicViewListCommonDto.setOnerInputDtTo(basicListData.getOnerInputDtTo());
        basicViewListCommonDto.setNumberLimit(basicListData.getNumberLimit());
        basicViewListCommonDto.setNote(basicListData.getNote());
        basicViewListCommonDto.setEntryNum(basicListData.getEntryNum());
        
        // エントリー者数チェック
        if(basicViewListCommonDto.getEntryNum().equals("0")){
            // 選択されたベーシック研修の参加者データがない場合
            throw new NotExistException("申込者", "", "");
        }

        
        String entryCd   = basicViewListCommonDto.getEntryCd();
        String entryYear = basicViewListCommonDto.getEntryYear();
        String entryKai  = basicViewListCommonDto.getEntryKai();
        String kbn       = "";
        
        // 選択された行の照会情報を取得
        basicViewListCommonDto.setBasicEntryList(getBasicEntryListLogic.execute(sysdate, entryCd, entryYear, entryKai, kbn));
        if(basicViewListCommonDto.getBasicEntryList() == null){
            // 選択されたベーシック研修の参加者データがない場合

        }
        
        return VIEWID_RESULT;
    }   
    
    
    /**
     * 入力チェック
     */
    private void check() {
        
        // 必須選択チェック
        
        String indexFlg = basicViewListCommonDto.getIndexFlg();
        if (indexFlg == null || indexFlg.equals("")) {
            throw new NotNullException("ベーシック研修の選択", "indexFlg", "1");
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