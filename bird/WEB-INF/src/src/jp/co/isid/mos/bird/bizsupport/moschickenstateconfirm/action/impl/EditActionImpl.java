package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.action.EditAction;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dto.MosChickenStateConfirmDto;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.UIMCStatusInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl.RegistLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodShokuhouzaiList;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 確認画面用アクションクラス
 * 
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A010";
    /* アクションID：タイトル変更処理 */
    public static final String changeTitle_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A11";
    /* アクションID：再検索処理 */
    public static final String search_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A12";
    /* アクションID：計算処理 */
    public static final String regist_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A13";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = MosChickenStateConfirmUtil.SCREEN_ID+"A14";
    /* DTO【予約・在庫状況確認】 */
    private MosChickenStateConfirmDto mosChickenStateConfirmDto;
    /* 【DTO】プルダウンメニュー */
    private PullDownMenuDto pullDownMenuDto;
    /*【DTO】オーナー検索DTO */
    private OwnerSearchDto ownerSearchDto;
    /* LOGIC【検索結果の取得】ロジック */
    private SearchLogic mosChickenStateConfirmSearchLogic;
    /* LOGIC【予約在庫状況情報の更新】ロジック */
    private RegistLogic mosChickenStateConfirmRegistLogic;
//add 2019/08/12 USI張 begin
    /* LOGIC【条件項目情報の取得】ロジック */
    private ConditionLogic mosChickenStateConfirmConditionLogic;
//add 2019/08/12 USI張 end

    /*  */
    MakeSessionKey mkSession = new MakeSessionKey();
    
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize() {
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
        //遷移元情報を設定
        getOwnerSearchDto().setNavigationCase(MosChickenStateConfirmUtil.VIEW_ID_CONDITION);
        //初期化
        getOwnerSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMosChickenStateConfirmDto().getTargetCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        return MosChickenStateConfirmUtil.VIEW_ID_ONERSEARCH;
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
     * 再検索 アクション
     * 
     * @return null 画面ID
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
//add 2019/08/12 USI張 begin
        String shokuCd = getMosChickenStateConfirmDto().getTargetShokuCd();
//add 2019/08/12 USI張 end
        //パラメーター：会社コード
        params.put(SearchLogicImpl.PK_COMPANY_CD, companyCd);
        //パラメーター：管理番号
        params.put(SearchLogicImpl.PK_CKANRI_NO, cKanriNo);
        //パラメーター：対象店舗
        params.put(SearchLogicImpl.PK_MISE_CD, miseCd);
        String dateYmd = getMosChickenStateConfirmDto().getTargetDate();
        //パラメーター：対象日付
        params.put(SearchLogicImpl.PK_DATE, dateYmd);
//add 2019/08/12 USI張 begin
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
        
        return null;
    }

    /**
     * 再計算 アクション
     * 
     * @return メニュー追加画面ID
     */
    public String regist() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return MosChickenStateConfirmUtil.operationErr_VIEW_ID;
		}
        //１．入力項目の妥当性チェックを行う。
        MosChickenStateConfirmUtil.checkInputParam(getMosChickenStateConfirmDto());
        //２．ロジック【予約在庫状況情報の更新】を実行する。
        List regList = getMosChickenStateConfirmDto().getListSearchData();
        Map params = new HashMap();
        params.put(RegistLogicImpl.PK_USERINFO, getBirdUserInfo());
        params.put(RegistLogicImpl.PK_REGLIST, regList);
        String dateYmd = getMosChickenStateConfirmDto().getRegTargetDate();
        //パラメーター：対象日付
        params.put(SearchLogicImpl.PK_DATE, dateYmd);
        getMosChickenStateConfirmRegistLogic().execute(params);
        
        //３．ロジック【予約在庫状況情報の検索】を実行する。
        UIMCStatusInfo entity = (UIMCStatusInfo)regList.get(0);
        String companyCd = entity.getCompanyCd();
        String cKanriNo= entity.getCkanriNo();
        String miseCd = entity.getMiseCd();
//modify 2019/08/12 USI張 begin
        String shokuCd = getMosChickenStateConfirmDto().getRegTargetshokuCd();
//modify 2019/08/12 USI張 end
        //パラメーター：会社コード
        params.put(SearchLogicImpl.PK_COMPANY_CD, companyCd);
        //パラメーター：管理番号
        params.put(SearchLogicImpl.PK_CKANRI_NO, cKanriNo);
        //パラメーター：対象店舗
        params.put(SearchLogicImpl.PK_MISE_CD, miseCd);
//modify 2019/08/12 USI張 begin
        //パラメーター：対象食包材
        params.put(SearchLogicImpl.PK_SHOKU_CD, shokuCd);
//modify 2019/08/12 USI張 end
        //
        List listReSearch = getMosChickenStateConfirmSearchLogic().execute(params);
        //４．処理３の再建策結果をDTOへ設定する。
        getMosChickenStateConfirmDto().setListSearchData(listReSearch);
        //５．DTO【予約在庫状況一覧】.対象管理番号へDTO【予約在庫状況一覧】.計算対象管理番号を設定する。
        getMosChickenStateConfirmDto().setTargetCkanriNo(getMosChickenStateConfirmDto().getRegTargetCkanriNo());
        //６．タイトル変更アクションメソッドを実行する。
        changeTitle();
        //７．DTO【予約在庫状況一覧】.対象店舗コードへDTO【予約在庫状況一覧】.計算対象店舗コードを設定する。
        getMosChickenStateConfirmDto().setTargetMiseCd(getMosChickenStateConfirmDto().getRegTargetMiseCd());
        //８．DTO【予約在庫状況一覧】.対象日付へDTO【予約在庫状況一覧】.計算対象日付を設定する。
        getMosChickenStateConfirmDto().setTargetDate(getMosChickenStateConfirmDto().getRegTargetDate());
//modify 2019/08/12 USI張 begin
        //9．DTO【予約在庫状況一覧】.対象食包材へDTO【予約在庫状況一覧】.計算対象食包材を設定する。
        getMosChickenStateConfirmDto().setTargetShokuCd(getMosChickenStateConfirmDto().getRegTargetshokuCd());
//modify 2019/08/12 USI張 end

        return null;
    }

    public MosChickenStateConfirmDto getMosChickenStateConfirmDto() {
        return mosChickenStateConfirmDto;
    }

    public void setMosChickenStateConfirmDto(
            MosChickenStateConfirmDto mosChickenStateConfirmDto) {
        this.mosChickenStateConfirmDto = mosChickenStateConfirmDto;
    }

    public RegistLogic getMosChickenStateConfirmRegistLogic() {
        return mosChickenStateConfirmRegistLogic;
    }

    public void setMosChickenStateConfirmRegistLogic(
            RegistLogic mosChickenStateConfirmRegistLogic) {
        this.mosChickenStateConfirmRegistLogic = mosChickenStateConfirmRegistLogic;
    }

    public SearchLogic getMosChickenStateConfirmSearchLogic() {
        return mosChickenStateConfirmSearchLogic;
    }

    public void setMosChickenStateConfirmSearchLogic(
            SearchLogic mosChickenStateConfirmSearchLogic) {
        this.mosChickenStateConfirmSearchLogic = mosChickenStateConfirmSearchLogic;
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
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
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
                     getMosChickenStateConfirmDto().getNowSessionKey()
                  ,  getMosChickenStateConfirmDto().getSessionKey() );
    }

//add 2019/08/12 USI張 begin
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
//add 2019/08/12 USI張 end

}
