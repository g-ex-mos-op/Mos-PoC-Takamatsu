/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.action.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.logic.UriMainteHeaderLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.action.UriMainteViewAction;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewReqDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.UriMainteInfoLogic;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 売上修正確認 画面アクション
 * @author xwatanabe
 */
public class UriMainteViewActionImpl implements UriMainteViewAction {

    /* アクションID定義 */
    public static final String initialize_ACTION_ID    = "BBR009A01";
    public static final String execute_ACTION_ID       = "BBR009A02";
    public static final String changeTab_ACTION_ID     = "BBR009A03";

    /* DTO */
    /** 売上修正確認リクエストDTO */
    private UriMainteViewReqDto uriMainteViewReqDto;
    /** 売上修正確認セッションDTO */
    private UriMainteViewSesDto uriMainteViewSesDto;
    /** プルダウンメニューDto */
    private PullDownMenuDto pullDownMenuDto;

    /** 売上修正情報取得ロジック */
    private UriMainteInfoLogic uriMainteInfoLogic;
    /** 売上修正共通ヘッダー取得ロジック */
    private UriMainteHeaderLogic uriMainteHeaderLogic;
    /** 初期情報取得ロジック */
    private ConditionLogic conditionLogic;

    /* BIRD共通 */
    /** ユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

    /**
     * 初期処理
     */
    public String initialize() {

        // 画面操作しているユーザーのユーザー情報取得。
        String userId   = birdUserInfo.getUserID();
        String userType = birdUserInfo.getMstUser().getUserTypeCd();
        String sysDate  = birdDateInfo.getSysDate();

        int windowId;

        //------------------------------------
        // メニュー画面から遷移してきた場合
        //------------------------------------
        if(pullDownMenuDto.isClearFlg()){
            pullDownMenuDto.setClearFlg(false);

            // ウインドウID生成・セット。
            windowId = uriMainteViewSesDto.createWindowId();
            uriMainteViewReqDto.setWindowId(windowId);

            //DTOクリア
            uriMainteViewReqDto.clear();

            //SessionDTOにセット
            uriMainteViewSesDto.setUserId(userId);                      //ユーザID
            uriMainteViewSesDto.setUserTypeCd(userType);                //ユーザタイプ
            uriMainteViewSesDto.setSysDate(sysDate);                    //システム日付

            // ロジック【初期情報取得】を実行する。
            conditionLogic.execute(uriMainteViewSesDto);
            
            //プルダウン先頭の会社情報を取得して設定
            MstUserCompany comp = (MstUserCompany)uriMainteViewSesDto.getCompanyList().get(0); 
            String companyCd   = comp.getCompanyCd();
            uriMainteViewReqDto.setCompanyCd(companyCd);
            
            // 修正日の先頭を取得して設定
            String syuseiDate = "";
            List syuseiDateList = uriMainteViewSesDto.getSyuseDateList();
            if(syuseiDateList != null && syuseiDateList.size() > 0){
                //プルダウンの先頭の年月日を取得
                SelectItem a = (SelectItem)syuseiDateList.get(0);
                syuseiDate = (String)a.getValue();
            }
            uriMainteViewReqDto.setSyuseiDate(syuseiDate);          //修正日
            

            //----------------------------------------
            // ロジック【売上修正共通ヘッダー取得】実行
            //----------------------------------------
            UriMainteHeader uriMainteHeader = uriMainteHeaderLogic.execute(companyCd);
            uriMainteViewSesDto.setHeader(windowId, uriMainteHeader);

        }
        //------------------------------------
        // 自画面からの遷移の時
        //------------------------------------
        else{
            //SessionDtoよりデータを取得、RequestDtoにセット
            copySessionToRequest();
        }
        
        return null;
    }

    /**
     * 実行（検索処理）
     */
    public String execute() {

        int windowId    = uriMainteViewReqDto.getWindowId();

        //現状の検索条件をSessionDtoに退避
        uriMainteViewSesDto.setCompanyCd(windowId, uriMainteViewReqDto.getCompanyCd());
        uriMainteViewSesDto.setSyuseiDate(windowId, uriMainteViewReqDto.getSyuseiDate());

        //--------------
        // 検索条件取得
        //--------------
        String companyCd  = uriMainteViewReqDto.getCompanyCd();
        String syuseiDate = uriMainteViewReqDto.getSyuseiDate();
        String sysDate    = uriMainteViewSesDto.getSysDate();

        //--------------
        // 検索
        //--------------
        search(windowId, companyCd, syuseiDate, sysDate);

        //自画面に遷移
        return null;
    }

    /**
     * 表示タブを切替
     */
    public String changeTab() {

        int windowId = uriMainteViewReqDto.getWindowId();

        //現状の検索条件をSessionDtoに退避
        uriMainteViewSesDto.setCompanyCd(windowId, uriMainteViewReqDto.getCompanyCd());
        uriMainteViewSesDto.setSyuseiDate(windowId, uriMainteViewReqDto.getSyuseiDate());

        int indexMain = uriMainteViewReqDto.getTabIndexMain();     //タブインデックス(メイン)
        int indexSub1 = uriMainteViewReqDto.getTabIndexSub1();  //タブインデックス(サブ)
        int indexSub2 = uriMainteViewReqDto.getTabIndexSub2();  //タブインデックス(サブ)
        int indexSub3 = uriMainteViewReqDto.getTabIndexSub3();  //タブインデックス(サブ)

        //既存データを保持しているか
        boolean flg = uriMainteViewSesDto.containsWindowId(windowId);

        //---------------------------
        // 既にデータを保持している時
        //---------------------------
        if(uriMainteViewReqDto.isExistDataFlg() && flg){
            //表示タブインデックスをセット
            uriMainteViewSesDto.setTabIndexMain(windowId, indexMain);
            uriMainteViewSesDto.setTabIndexSub1(windowId, indexSub1);
            uriMainteViewSesDto.setTabIndexSub2(windowId, indexSub2);
            uriMainteViewSesDto.setTabIndexSub3(windowId, indexSub3);
        }
        //---------------------------
        // データを保持していない時
        //---------------------------
        else{
            //前回検索条件を取得
            String companyCdZen  = uriMainteViewReqDto.getCompanyCdZen();
            String syuseiDateZen = uriMainteViewReqDto.getSyuseiDateZen();
            String sysDate       = uriMainteViewSesDto.getSysDate();

            //検索
            search(windowId, companyCdZen, syuseiDateZen, sysDate);

            //表示タブインデックスをセット
            uriMainteViewSesDto.setTabIndexMain(windowId, indexMain);
            uriMainteViewSesDto.setTabIndexSub1(windowId, indexSub1);
            uriMainteViewSesDto.setTabIndexSub2(windowId, indexSub2);
            uriMainteViewSesDto.setTabIndexSub3(windowId, indexSub3);
        }

        //自画面へ遷移
        return null;
    }
    
    /**
     * 指定した検索条件にて検索処理を行い、SessionDTOにwindowIdをキーに格納する。
     * @param windowId
     * @param sessionDto
     * @param companyCd
     * @param syuseiDate
     * @param sysDate
     */
    private void search(int windowId, String companyCd,String syuseiDate, String sysDate) {

        boolean existData = false;

        //----------------------------------------
        // ロジック【売上修正確認情報取得】実行。 
        //----------------------------------------
        List uriMainteList = uriMainteInfoLogic.execute(companyCd,syuseiDate,sysDate);
        if(uriMainteList == null || uriMainteList.size()==0){
            existData = false;
        }else{
            existData = true;
        }

        //------------------------------
        // データが取得できた時
        //------------------------------
        if(existData){

            uriMainteViewSesDto.setExistDataFlg(windowId, true);
            uriMainteViewSesDto.setResearchFlg (windowId, true);
            uriMainteViewSesDto.setUriageSyuseiRirekiList(windowId, uriMainteList);
            uriMainteViewSesDto.setTabIndexMain(windowId, UriMainteViewConstants.TAB_INDEX_URIAGE);
            uriMainteViewSesDto.setTabIndexSub1(windowId, UriMainteViewConstants.TAB_INDEX_SUB_MAEUKE);
            uriMainteViewSesDto.setTabIndexSub2(windowId, UriMainteViewConstants.TAB_INDEX_SUB_HANBAI);
            uriMainteViewSesDto.setTabIndexSub3(windowId, UriMainteViewConstants.TAB_INDEX_SUB_NEBIKI);

            uriMainteViewSesDto.setCompanyCdZen(windowId, companyCd);
            uriMainteViewSesDto.setSyuseiDateZen(windowId, syuseiDate);
        }
        //------------------------------
        // データが取得できなかった時
        //------------------------------
        else{
            //結果クリア
            uriMainteViewSesDto.resultClear(windowId);

            //エラーメッセージ
            throw new NotExistException("該当データ");
        }
    }

    /**
     * SessionDTOより表示内容をRequestDTOにセット
     */
    private void copySessionToRequest() {

        int winId = uriMainteViewReqDto.getWindowId();

        uriMainteViewReqDto.setCompanyCd(uriMainteViewSesDto.getCompanyCd(winId));          //検索条件
        uriMainteViewReqDto.setSyuseiDate(uriMainteViewSesDto.getSyuseiDate(winId));        //検索条件

        uriMainteViewReqDto.setCompanyCdZen(uriMainteViewSesDto.getCompanyCdZen(winId));    //前回検索条件：会社コード
        uriMainteViewReqDto.setSyuseiDateZen(uriMainteViewSesDto.getSyuseiDateZen(winId));  //前回検索条件：修正日

        uriMainteViewReqDto.setExistDataFlg(uriMainteViewSesDto.getExistDataFlg(winId));    //データ存在フラグ
        uriMainteViewReqDto.setResearchFlg(uriMainteViewSesDto.isResearchFlg(winId));       //再検索フラグ

        uriMainteViewReqDto.setUriageSyuseiRirekiList(uriMainteViewSesDto.getUriageSyuseiRirekiList(winId));

        uriMainteViewReqDto.setTabIndexMain(uriMainteViewSesDto.getTabIndexMain(winId));        //メインタブ
        uriMainteViewReqDto.setTabIndexSub1(uriMainteViewSesDto.getTabIndexSub1(winId));        //サブ１タブ
        uriMainteViewReqDto.setTabIndexSub2(uriMainteViewSesDto.getTabIndexSub2(winId));        //サブ２タブ
        uriMainteViewReqDto.setTabIndexSub3(uriMainteViewSesDto.getTabIndexSub3(winId));        //サブ３タブ
        uriMainteViewReqDto.setHeader(uriMainteViewSesDto.getHeader(winId));                    //ヘッダー
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
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

    public UriMainteViewReqDto getUriMainteViewReqDto() {
        return uriMainteViewReqDto;
    }

    public void setUriMainteViewReqDto(UriMainteViewReqDto dto) {
        this.uriMainteViewReqDto = dto;
    }

    public UriMainteViewSesDto getUriMainteViewSesDto() {
        return uriMainteViewSesDto;
    }

    public void setUriMainteViewSesDto(UriMainteViewSesDto dto) {
        this.uriMainteViewSesDto = dto;
    }

    /**
     * @return uriMainteHeaderLogic を戻します。
     */
    public UriMainteHeaderLogic getUriMainteHeaderLogic() {
        return uriMainteHeaderLogic;
    }

    /**
     * @param uriMainteHeaderLogic 設定する uriMainteHeaderLogic。
     */
    public void setUriMainteHeaderLogic(UriMainteHeaderLogic uriMainteHeaderLogic) {
        this.uriMainteHeaderLogic = uriMainteHeaderLogic;
    }

    /**
     * @return uriMainteInfoLogic を戻します。
     */
    public UriMainteInfoLogic getUriMainteInfoLogic() {
        return uriMainteInfoLogic;
    }

    /**
     * @param uriMainteInfoLogic 設定する uriMainteInfoLogic。
     */
    public void setUriMainteInfoLogic(UriMainteInfoLogic uriMainteInfoLogic) {
        this.uriMainteInfoLogic = uriMainteInfoLogic;
    }

    /**
     * 初期情報取得ロジックを戻します。
     * @return conditionLogic 初期情報取得ロジック
     */
    public ConditionLogic getConditionLogic() {
        return conditionLogic;
    }

    /**
     * 初期情報取得ロジックを設定します。
     * @param conditionLogic 初期情報取得ロジック
     */
    public void setConditionLogic(ConditionLogic conditionLogic) {
        this.conditionLogic = conditionLogic;
    }
}