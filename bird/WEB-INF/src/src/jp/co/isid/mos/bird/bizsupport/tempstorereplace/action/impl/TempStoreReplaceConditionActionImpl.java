package jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.TempStoreReplaceConditionAction;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dto.TempStoreReplaceDto;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UIJigyoList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UISibuList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListJigyoLogic;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListNendoLogic;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListSibuLogic;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListTargetSearchLogic;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListCreateLogic;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;



/**
 * 仮店舗置換えメンテナンスAction
 * 
 * @author Aspac
 */
public class TempStoreReplaceConditionActionImpl implements TempStoreReplaceConditionAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS025A01";
    
    /** ViewID定義 */
    public static final String condition_VIEW_ID = "BBS025V01";//検索画面
    public static final String stateEdit_VIEW_ID = "BBS025V02";//編集画面(次画面)

    
    /**
     * ユーザ関連情報
     */
    private BirdUserInfo birdUserInfo;
    
    
    /**
     * S2コンテナを取得します。
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザ関連情報を取得します。
     * @return birdUserInfo
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRDユーザ関連情報を設定します。
     * @param birdUserInfo
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    
    
    
    
    /**
     * テンプレートCSVダウンロードAction
     */
    private CsvOutputAction tempStroeStateCsvOutputAction;
    
    
    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;
    /**
     * 仮店舗置換えメンテナンスDTO
     */
    private TempStoreReplaceDto tempStoreReplaceDto;
    
    
    /**
     * 会社情報取得ロジック
     */
    private GetUserCompanyLogic getUserCompanyLogic;
    
    /**
     * 年度リスト生成ロジック
     */
    private ListNendoLogic listNendoLogic;
    
    /**
     * 対象条件リスト生成ロジック
     */
    private ListTargetSearchLogic listTargetSearchLogic;
    
    /**
     * 事業リスト生成ロジック
     */
    private ListJigyoLogic listJigyoLogic;
    
    /**
     * 支部リスト生成ロジック
     */
    private ListSibuLogic listSibuLogic; 
    
    /**
     * 仮店舗置換え状況リスト生成ロジック
     */
    private StateListCreateLogic stateListCreateLogic;
    
    
    
    /**
     * 仮店舗置換えメンテナンスを取得します。
     * @return 仮店舗置換えメンテナンス
     */
    public TempStoreReplaceDto getTempStoreReplaceDto() {
        return tempStoreReplaceDto;
    }
    /**
     * 仮店舗置換えメンテナンスを設定します。
     * @param 仮店舗置換えメンテナンス
     */
    public void setTempStoreReplaceDto(TempStoreReplaceDto tempStoreReplaceDto) {
        this.tempStoreReplaceDto = tempStoreReplaceDto;
    }
    
    
    
    

    /**
     * メニューDTOを取得します。
     * @return
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * メニューDTOを設定します
     * @param pullDownMenuDto
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    
    

    /**
     * テンプレートCSVダウンロードActionを取得します。
     * @return テンプレートCSVダウンロードAction
     */
    public CsvOutputAction getTempStroeStateCsvOutputAction() {
        return tempStroeStateCsvOutputAction;
    }
    /**
     * テンプレートCSVダウンロードActionを設定します。
     * @param テンプレートCSVダウンロードAction
     */
    public void setTempStroeStateCsvOutputAction(
            CsvOutputAction tempStroeStateCsvOutputAction) {
        this.tempStroeStateCsvOutputAction = tempStroeStateCsvOutputAction;
    }
    
    
    

    /**
     * 会社情報取得ロジックを取得します。
     * @return 会社情報取得ロジック
     */
    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }
    /**
     * 会社情報取得ロジックを設定します。
     * @return 会社情報取得ロジック
     */
    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }
    
    

    /**
     * 年度リスト生成ロジックを取得する
     * @return 年度リスト生成ロジック
     */
    public ListNendoLogic getListNendoLogic() {
        return listNendoLogic;
    }
    /**
     * 年度リスト生成ロジックを設定する
     * @param 年度リスト生成ロジック
     */
    public void setListNendoLogic(ListNendoLogic listNendoLogic) {
        this.listNendoLogic = listNendoLogic;
    }
    
    

    /**
     * 対象条件リスト生成ロジックを取得します。
     * @return 対象条件リスト生成ロジック
     */
    public ListTargetSearchLogic getListTargetSearchLogic() {
        return listTargetSearchLogic;
    }
    /**
     * 対象条件リスト生成ロジックを設定します。
     * @param 対象条件リスト生成ロジック
     */
    public void setListTargetSearchLogic(ListTargetSearchLogic listTargetSearchLogic) {
        this.listTargetSearchLogic = listTargetSearchLogic;
    }
    
    
    /**
     * 事業リスト生成ロジックを取得します。
     * @return 事業リスト生成ロジック
     */
    public ListJigyoLogic getListJigyoLogic() {
        return listJigyoLogic;
    }
    /**
     * 事業リスト生成ロジックを設定します。
     * @param 事業リスト生成ロジック
     */
    public void setListJigyoLogic(ListJigyoLogic listJigyoLogic) {
        this.listJigyoLogic = listJigyoLogic;
    }
    
    
    /**
     * 支部リスト生成ロジックを取得します。
     * @return 支部リスト生成ロジック
     */
    public ListSibuLogic getListSibuLogic() {
        return listSibuLogic;
    }
    /**
     * 支部リスト生成ロジックを設定します。
     * @param 支部リスト生成ロジック
     */
    public void setListSibuLogic(ListSibuLogic listSibuLogic) {
        this.listSibuLogic = listSibuLogic;
    }
    
    
    /**
     * 仮店舗置換え状況リスト生成ロジックを取得します。
     * @return 仮店舗置換え状況リスト
     */
    public StateListCreateLogic getStateListCreateLogic() {
        return stateListCreateLogic;
    }
    /**
     * 仮店舗置換え状況リスト生成ロジックを設定します。
     * @param 仮店舗置換え状況リスト
     */
    public void setStateListLogic(StateListCreateLogic stateListCreateLogic) {
        this.stateListCreateLogic = stateListCreateLogic;
    }
    
    
    /**
     * 初期表示
     * @return
     */
    public String initialize() {
        
        
        if (getPullDownMenuDto().isClearFlg()) {
            
            
            getTempStoreReplaceDto().reset();
            getTempStoreReplaceDto().setUserId(birdUserInfo.getUserID());
            
            // ユーザタイプ判定
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            
            if (userTypeCd.equals("01")) {
                
                //会社プルダウンを生成する
                List listCmpny = getGetUserCompanyLogic().execute(birdUserInfo.getUserID());
                getTempStoreReplaceDto().setListCompany(listCmpny);
                getTempStoreReplaceDto().setCondCompanyCd("00");
                
                //年度プルダウンを生成する
                //※グループ事業計画予算(月次)BT44MSJYにデータない場合には検索条件を非表示
                List listNendo = getListNendoLogic().execute();
                if(listNendo.size() <= 0) {
                    getTempStoreReplaceDto().setProcessFlg(false);
                    throw new GenericMessageException("予算が設定されていません。");
                }
                getTempStoreReplaceDto().setListNendo(listNendo);
                
                //対象条件プルダウンリストを生成する
                List listTarget = getListTargetSearchLogic().execute("00");
                getTempStoreReplaceDto().setListTargetSearch(listTarget);
                
                //事業プルダウンリストを生成する
                List listJigyo = getListJigyoLogic().execute("00");
                getTempStoreReplaceDto().setListJigyo(listJigyo);            
                
                //支部プルダウンリストを生成する
                List listSibu = getListSibuLogic().execute("00");
                getTempStoreReplaceDto().setListSibu(listSibu);
                
            } else {
                throw new CannotAccessException();
                
            }
            
            pullDownMenuDto.setClearFlg(false);
        }
        
        return null;
    }

    
    /**
     * 会社プルダウン変更
     * @return
     */
    public String onChangeCompany(){
        
        String companyCd = getTempStoreReplaceDto().getCondCompanyCd(); 
        
        //対象条件プルダウンリストを生成する
        List listTarget = getListTargetSearchLogic().execute(companyCd);
        getTempStoreReplaceDto().setListTargetSearch(listTarget);
        getTempStoreReplaceDto().setCondTargetCd("0");//初期値を『全社』に設定
        
        //事業プルダウンリストを生成する
        List listJigyo = getListJigyoLogic().execute(companyCd);
        getTempStoreReplaceDto().setListJigyo(listJigyo);            

        //支部プルダウンリストを生成する
        List listSibu = getListSibuLogic().execute(companyCd);
        getTempStoreReplaceDto().setListSibu(listSibu);
        
        return null;
    }
    
    
    
    /**
     * CSVダウンロード
     * @return
     */
    public void downloadCsv() {
        
        //仮店舗置換え状況リストを取得する
        getStateList();
        setSearchName();
        
        // CSVダウンロード
        try {
            getTempStroeStateCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        
    }
    
    /**
     * 実行
     * @return
     */
    public String doSearchTempStroe() {
        
        //仮店舗置換え状況リストを取得する
        getStateList();
        setSearchName();
        
        return stateEdit_VIEW_ID;
    }

    
    
    /**
     * 事業名称・支部名称を取得する
     */
    private void setSearchName() {

        String targetCd = getTempStoreReplaceDto().getCondTargetCd();
        
        //事業名称を取得
        if(targetCd.equals("1")) {
            List listJigyo = getTempStoreReplaceDto().getListJigyo();
            String codeJigyo = getTempStoreReplaceDto().getCondJigyouCd();
            for (Iterator ite = listJigyo.iterator(); ite.hasNext();) {
                UIJigyoList jigyo = (UIJigyoList) ite.next();
                if(jigyo.getJigyouCd().equals(codeJigyo)) {
                    this.getTempStoreReplaceDto().setCondJigyoName(jigyo.getJigyouName());
                }
            }
        }
        //支部名称を取得
        else if(targetCd.equals("2")) {
            List listSibu = getTempStoreReplaceDto().getListSibu();
            String codeSibu = getTempStoreReplaceDto().getCondSibuCd();
            for (Iterator ite = listSibu.iterator(); ite.hasNext();) {
                UISibuList sibu = (UISibuList) ite.next();
                if(sibu.getSibuCd().equals(codeSibu)) {
                    this.getTempStoreReplaceDto().setCondSibuName(sibu.getSibuName());
                }
            }
        }
    }
    
    /**
     * 仮店舗置換え状況リストを取得する
     * @return List 仮店舗置換え状況リスト
     */
    private void getStateList() {

        //システム日付けを取得する
        String sysdate = getBirdDateInfo().getSysDate();
        getTempStoreReplaceDto().setSysdate(sysdate);
        
        String companyCd = getTempStoreReplaceDto().getCondCompanyCd();
        String nendo = getTempStoreReplaceDto().getCondNendo();
        String targetCd = getTempStoreReplaceDto().getCondTargetCd();
        String fixedKind = getTempStoreReplaceDto().getFixedKind();
        
        String code = "";
        if(targetCd.equals("1")) {
            code = getTempStoreReplaceDto().getCondJigyouCd();
        }
        else if(targetCd.equals("2")) {
            code = getTempStoreReplaceDto().getCondSibuCd();
        }
        
        
        //仮店舗置換え状況リストを取得する
        List listState = getStateListCreateLogic().execute(companyCd, nendo, targetCd, code, fixedKind, sysdate);
        
        getTempStoreReplaceDto().setListState(listState);
        
        return;
    }
    

}
