package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dto.MosChickenStateConfirmDto;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodShokuhouzaiList;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 初期画面用アクションクラス
 * 
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A01";
    /* アクションID：オーナー検索画面遷移処理 */
    public static final String callOnerForm_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A02";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A03";
    /* アクションID：実行処理 */
    public static final String selectOner_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A03";
    /* アクションID：タイトル変更処理 */
    public static final String changeTitle_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A05";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A06";
    /* DTO【予約・在庫状況確認】 */
    private MosChickenStateConfirmDto mosChickenStateConfirmDto;
    /* DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;
    /* DTO【オーナー選択】 */
    private OwnerSearchDto ownerSearchDto;
    /* LOGIC【条件項目情報の取得】ロジック */
    private ConditionLogic mosChickenStateConfirmConditionLogic;
    /* LOGIC【検索結果の取得】ロジック */
    private SearchLogic mosChickenStateConfirmSearchLogic;
    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();
    
    /**
     * 初期処理 アクション
     * 
     * １．【DTO】プルダウンメニュー.クリアフラグがtrueの場合下記の処理を行う。
     *     １－１．複数ウィンドウ用のWindowID設定する。
     *     １－２．DTOへユーザー情報を設定する。
     *     １－３．ユーザータイプコードが本部以外の場合は、下記の処理を行う。
     *     　　１）.ユーザー情報からオーナーコードを取得し、DTOへ設定する。
     *         ２）.ロジック【条件項目情報の取得】を実行
     *         ３）.結果をDTOにセットする。
     *     １－４．【DTO】プルダウンメニュー.クリアフラグをfalseに設定する。
     * 
     * ２．オーナー選択画面から遷移した場合、下記の処理を行う。
     * 　　２－１．DTOへオーナーコード設定
     * 　　２－２．DTOへオーナー名称設定
     * 　　２－３．DTO【オーナー選択画面】アクションフラグをリセットする。
     * 
     * ３．nullをリターンする。
     * 
     * @return null 画面ID
     */
    public String initialize() {
        //１．【DTO】プルダウンメニュー.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //【DTO】プルダウンメニュー.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
        	//複数WINDOW対応、以前のオーナーコードが残るのを防止
            getMosChickenStateConfirmDto().clear();
            getMosChickenStateConfirmDto().updateWindowid();
        	
            //１－２．DTOへユーザー情報を設定
            getMosChickenStateConfirmDto().setOnerSelectFlg(false);
            getMosChickenStateConfirmDto().setUserId(getBirdUserInfo().getUserID());
            getMosChickenStateConfirmDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            getMosChickenStateConfirmDto().setSysDate(getBirdDateInfo().getSysDate());
            getMosChickenStateConfirmDto().setAppDate(getBirdDateInfo().getAppDate());
            String userTypeCd = getMosChickenStateConfirmDto().getUserTypeCd();
            String targetOnerCd = "";
            
			// 【セッションKey生成】
			String key = mkSession._makeSessionKey();
			getMosChickenStateConfirmDto().setNowSessionKey(key);
			getMosChickenStateConfirmDto().setSessionKey(key);
            
            //１－３．ユーザータイプコードが本部以外の場合は、下記の処理を行う。
            if(!MosChickenStateConfirmUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
                if(MosChickenStateConfirmUtil.USER_TYPE_CD_ONER.equals(userTypeCd)){
                    targetOnerCd = ((UIUserOner)(getBirdUserInfo().getUserOner().get(0))).getOnerCd();
                }
                else if(MosChickenStateConfirmUtil.USER_TYPE_CD_MISE.equals(userTypeCd)){
                    targetOnerCd = ((UIUserMise)(getBirdUserInfo().getUserMise().get(0))).getMiseCd();
                }
                getMosChickenStateConfirmDto().setTargetOnerCd(targetOnerCd);
                //実行 アクション処理を実行する。
                selectOner();
            }            
        }
        //２．オーナー選択画面から遷移した場合、下記の処理を行う。
        else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT){
            //２－１．DTO【オーナー選択】.遷移区分を初期値に設定する。
            getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            //２－２．DTO【予約・在庫状況確認】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
            getMosChickenStateConfirmDto().setWindowId(getOwnerSearchDto().getWindowId());
            //２－３．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
            if (!isValidSessionKey()) {
                return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
            }
            //２－４．オーナーを選択後遷移した場合は下記の処理を行う。
            if(getOwnerSearchDto().isActionFlag()){
                //DTOへオーナーコード設定
                getMosChickenStateConfirmDto().setTargetOnerCd(getOwnerSearchDto().getOnerCd());
                //DTOへオーナー名称設定
                getMosChickenStateConfirmDto().setTargetOnerName(getOwnerSearchDto().getOnerNameKj());
                //DTO【オーナー選択画面】アクションフラグをリセットする。
                getOwnerSearchDto().setActionFlag(false);
                if(!MosChickenStateConfirmUtil.isNull(getMosChickenStateConfirmDto().getTargetOnerCd())){
                    //実行 アクション処理を実行する。
                    selectOner();
                }
            }
            //２－５．DTO【オーナー選択】のクリア処理を実行する。
            getOwnerSearchDto().clear();
            
        } 
        
        return null;
    }
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!isValidSessionKey()) {
			return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
		}
        getMosChickenStateConfirmDto().clear();
        getMosChickenStateConfirmDto().setOnerSelectFlg(false);
        // TODO 自動生成されたメソッド・スタブ
        return MosChickenStateConfirmUtil.VIEW_ID_CONDITION;
    }

    /**
     * オーナー検索画面呼び出し アクション
     * 
     * @return 初期画面ID
     */
    public String callOnerForm() {
        getMosChickenStateConfirmDto().setOnerSelectFlg(false);
        //遷移元情報を設定
        getOwnerSearchDto().setNavigationCase(MosChickenStateConfirmUtil.VIEW_ID_CONDITION);
        //初期化
        getOwnerSearchDto().setInitFlag(true);
        //複数WindowID
        getOwnerSearchDto().setWindowId(getMosChickenStateConfirmDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
                // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMosChickenStateConfirmDto().getTargetCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        
        return MosChickenStateConfirmUtil.VIEW_ID_ONERSEARCH;
    }
    /**
     * 検索 アクション
     * 
     * １．ロジック【検索結果の取得】を実行
     * ２．結果をDTOにセットする。
     * @return 確認(or照会)画面ID
     */
    public String search() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!isValidSessionKey()) {
			return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
		}
        //１．ロジック【検索結果の取得】を実行
        Map params = new HashMap();
        String companyCd = getMosChickenStateConfirmDto().getTargetCompanyCd();
        String cKanriNo=getMosChickenStateConfirmDto().getTargetCkanriNo();
        String miseCd = getMosChickenStateConfirmDto().getTargetMiseCd();
        String dateYmd = getMosChickenStateConfirmDto().getTargetDate();
//add 2019/08/12 USI張 begin
        String shokuCd = getMosChickenStateConfirmDto().getTargetShokuCd();
//add 2019/08/12 USI張 end
        //パラメーター：会社コード
        params.put(SearchLogicImpl.PK_COMPANY_CD, companyCd);
        //パラメーター：管理番号
        params.put(SearchLogicImpl.PK_CKANRI_NO, cKanriNo);
        //パラメーター：対象店舗
        params.put(SearchLogicImpl.PK_MISE_CD, miseCd);
        //パラメーター：対象日付
        params.put(SearchLogicImpl.PK_DATE, dateYmd);
//add 2019/08/12 USI張 begin
        //パラメーター：対象食包材
        params.put(SearchLogicImpl.PK_SHOKU_CD, shokuCd);
//add 2019/08/12 USI張 end
        List listSearchData = getMosChickenStateConfirmSearchLogic().execute(params);
        if(listSearchData.size() == 0){
            getMosChickenStateConfirmDto().setRegTargetCkanriNo(null);
            getMosChickenStateConfirmDto().setRegTargetMiseCd(null);
            getMosChickenStateConfirmDto().setRegTargetDate(null);
//add 2019/08/12 USI張 begin
            getMosChickenStateConfirmDto().setRegTargetshokuCd(null);;
//add 2019/08/12 USI張 end
            getMosChickenStateConfirmDto().setListSearchData(null);
            throw new NoResultException();
        }
        //２．結果をDTOにセットする。
        getMosChickenStateConfirmDto().setRegTargetCkanriNo(cKanriNo);
        getMosChickenStateConfirmDto().setRegTargetMiseCd(miseCd);
        getMosChickenStateConfirmDto().setRegTargetDate(dateYmd);
//add 2019/08/12 USI張 begin
        getMosChickenStateConfirmDto().setRegTargetshokuCd(shokuCd);;
//add 2019/08/12 USI張 end
        getMosChickenStateConfirmDto().setListSearchData(listSearchData);
        
        return MosChickenStateConfirmUtil.VIEW_ID_EDIT;
    }
    /**
     * タイトル変更アクション
     * 
     * １．ユーティル【Staticメソッドクラス】.条件項目の対象日付生成処理を実行する。
     * ２．結果をDTOへ設定する。
     * @return
     */
    public String changeTitle(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!isValidSessionKey()) {
			return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
		}
        //対象管理番号
        String ckanriNo = getMosChickenStateConfirmDto().getTargetCkanriNo();
        //１．ユーティル【Staticメソッドクラス】.条件項目の対象日付生成処理を実行する。
        List lists = MosChickenStateConfirmUtil.createListJokenData(
                ckanriNo, getMosChickenStateConfirmDto().getListTitle());
        //２．結果をDTOへ設定する。
        List listDate = (List)lists.get(0);
        getMosChickenStateConfirmDto().setListDate(listDate);
        if(listDate != null){
            if(listDate.size() == 1){
                //初期値日付
                getMosChickenStateConfirmDto().setTargetDate((String)((SelectItem)listDate.get(0)).getValue());
            }else if(listDate.size() > 1){
                //初期値システム日付
                getMosChickenStateConfirmDto().setTargetDate(getMosChickenStateConfirmDto().getSysDate());
            }
        }
        //初期値システム日付
        getMosChickenStateConfirmDto().setTargetDate(getMosChickenStateConfirmDto().getSysDate());
//add 2019/08/12 USI張 begin
        List ListShoku = getMosChickenStateConfirmConditionLogic().getShokuList(ckanriNo);
        getMosChickenStateConfirmDto().setListShokuhou(ListShoku);
        if(ListShoku.size() >= 2){
            getMosChickenStateConfirmDto().setTargetShokuCd((String)((CodShokuhouzaiList)ListShoku.get(0)).getShokuCd());
            getMosChickenStateConfirmDto().setTargetShokuNameKna((String)((CodShokuhouzaiList)ListShoku.get(0)).getShokuNameKna());
        }else {
            //「全て」データのみ
            getMosChickenStateConfirmDto().setTargetShokuCd(null);
            getMosChickenStateConfirmDto().setTargetShokuNameKna(null);
            throw new NotExistException("このタイトルには対象食包材");
        }
//add 2019/08/12 USI張 end
        return null;
    }
    /**
     * 実行 アクション
     * 
     * 本部用のアクションです。
     * 対象オーナーを選択した時に呼ばれるアクションです。
     * 
     * @return null
     */
    public String selectOner() {
		if (!mkSession.isValidSessionKey(getMosChickenStateConfirmDto()
                // 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
				.getSessionKey(), getMosChickenStateConfirmDto().getSessionKey())) {
			return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
		}    	
        //１．ロジック【検索結果の取得】を実行
        String onerCd = getMosChickenStateConfirmDto().getTargetOnerCd();
        getMosChickenStateConfirmDto().setTargetOnerCd(MosChickenStateConfirmUtil.checkOnerCd(onerCd));
        Map params = new HashMap();
        params.put(ConditionLogicImpl.PK_COMPANY_CD, getMosChickenStateConfirmDto().getTargetCompanyCd());
        params.put(ConditionLogicImpl.PK_SYS_DATE, getMosChickenStateConfirmDto().getSysDate());
        params.put(ConditionLogicImpl.PK_ONER_CD, getMosChickenStateConfirmDto().getTargetOnerCd());
        params.put(ConditionLogicImpl.PK_USERINFO, getBirdUserInfo());
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        List lists = getMosChickenStateConfirmConditionLogic().execute(params);
        String ckanriNo = "";
        if(lists.size()>0){
            //２．結果をDTOにセットする。
            List listTitle = (List)lists.get(0);
            getMosChickenStateConfirmDto().setListTitle(listTitle);
            if(listTitle != null && listTitle.size() >= 1){
                ckanriNo = ((MstKanriKikan)listTitle.get(0)).getCkanriNo();
                getMosChickenStateConfirmDto().setTargetCkanriNo(ckanriNo);
                getMosChickenStateConfirmDto().setTargetTitle(((MstKanriKikan)listTitle.get(0)).getTitle());
            }
            List listMise = (List)lists.get(1);
            getMosChickenStateConfirmDto().setListOnerMise(listMise);
            if(listMise != null && listMise.size() >= 1){
                getMosChickenStateConfirmDto().setTargetMiseCd(((CodMiseList)listMise.get(0)).getMiseCd());
                getMosChickenStateConfirmDto().setTargetMiseNameKj(((CodMiseList)listMise.get(0)).getMiseNameKj());
            }
            else{
                if(MosChickenStateConfirmUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
                    if(listMise.size() == 0){
                        throw new NotExistException("対象店舗", "onerCd",0);
                    }
                }
            }
//modify 2019/08/12 USI張 begin
//            List listDate = (List)lists.get(2);
            //食包材
            List listShoku = (List)lists.get(2);
            getMosChickenStateConfirmDto().setListShokuhou(listShoku);
            if(listShoku.size() >= 2){
                getMosChickenStateConfirmDto().setTargetShokuCd((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuCd());
                getMosChickenStateConfirmDto().setTargetShokuNameKna((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuNameKna());
            }else {
                //「全て」データのみ
                throw new NotExistException("このタイトルには対象食包材");
            }
            List listDate = (List)lists.get(3);
//modify 2019/08/12 USI張 end
            getMosChickenStateConfirmDto().setListDate(listDate);
            if(listDate != null){
                if(listDate.size() == 1){
                    //初期値日付
                    getMosChickenStateConfirmDto().setTargetDate((String)((SelectItem)listDate.get(0)).getValue());
                }else if(listDate.size() > 1){
                    //初期値システム日付
                    getMosChickenStateConfirmDto().setTargetDate(getMosChickenStateConfirmDto().getSysDate());
                }
            }
            if(listMise.size() == 0){
                throw new NotExistException("対象店舗");
            }
        }
        else{
            getMosChickenStateConfirmDto().clearConditionData();
            getMosChickenStateConfirmDto().setListTitle(null);
            getMosChickenStateConfirmDto().setListOnerMise(null);
            getMosChickenStateConfirmDto().setListDate(null);
//add 2019/08/12 USI張 begin
            getMosChickenStateConfirmDto().setListShokuhou(null);
//add 2019/08/12 USI張 end
        }
        getMosChickenStateConfirmDto().setOnerSelectFlg(true);
        return null;
    }
    
    
    public SearchLogic getMosChickenStateConfirmSearchLogic() {
        return mosChickenStateConfirmSearchLogic;
    }

    public void setMosChickenStateConfirmSearchLogic(
            SearchLogic mosChickenStateConfirmSearchLogic) {
        this.mosChickenStateConfirmSearchLogic = mosChickenStateConfirmSearchLogic;
    }

    public MosChickenStateConfirmDto getMosChickenStateConfirmDto() {
        return mosChickenStateConfirmDto;
    }

    public void setMosChickenStateConfirmDto(
            MosChickenStateConfirmDto mosChickenStateConfirmDto) {
        this.mosChickenStateConfirmDto = mosChickenStateConfirmDto;
    }


    /**
     * @return mosChickenStateConfirmConditionLogic を戻します。
     */
    public ConditionLogic getMosChickenStateConfirmConditionLogic() {
        return mosChickenStateConfirmConditionLogic;
    }

    /**
     * @param mosChickenStateConfirmConditionLogic 設定する mosChickenStateConfirmConditionLogic。
     */
    public void setMosChickenStateConfirmConditionLogic(
            ConditionLogic mosChickenStateConfirmConditionLogic) {
        this.mosChickenStateConfirmConditionLogic = mosChickenStateConfirmConditionLogic;
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
    
    ///////////////
    //複数Window対応
    ///////////////
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey( 
                     getMosChickenStateConfirmDto().getNowSessionKey()
                  ,  getMosChickenStateConfirmDto().getSessionKey() );
    }
}
