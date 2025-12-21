package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dto.MosChickenStoreStateConfirmDto;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodShokuhouzaiList;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodSibuList;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.ChangeTitleLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl.ChangeTitleLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.util.MosChickenStoreStateConfirmUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 店別予約状況一覧(モスチキン用画面）
 * 初期画面用アクションクラス
 * 
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"A01";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"A02";
    /* アクションID：タイトル変更処理 */
    public static final String changeTitle_ACTION_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"A03";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"A04";
    /* 【DTO】店別予約状況一覧 */
    private MosChickenStoreStateConfirmDto mosChickenStoreStateConfirmDto;
    /* 【DTO】プルダウンメニュー */
    private PullDownMenuDto pullDownMenuDto;
    /* LOGIC【条件項目情報の取得】ロジック */
    private ConditionLogic mosChickenStoreStateConfirmConditionLogic;
    /* LOGIC【指定管理番号条件項目情報の取得】ロジック */
    private ChangeTitleLogic mosChickenStoreStateConfirmChangeTitleLogic;
    /* LOGIC【検索結果の取得】ロジック */
    private SearchLogic mosChickenStoreStateConfirmSearchLogic;

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
     * ２．nullをリターンする。
     * 
     * @return null 画面ID
     */
    public String initialize() {
        //１．【DTO】プルダウンメニュー.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //【DTO】プルダウンメニュー.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
            //１－１．複数WindowID設定
            getMosChickenStoreStateConfirmDto().updateWindowid();
            //１－２．DTOへユーザー情報を設定
            getMosChickenStoreStateConfirmDto().setUserId(getBirdUserInfo().getUserID());
            getMosChickenStoreStateConfirmDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            getMosChickenStoreStateConfirmDto().setSysDate(getBirdDateInfo().getSysDate());
            getMosChickenStoreStateConfirmDto().setAppDate(getBirdDateInfo().getAppDate());
            getMosChickenStoreStateConfirmDto().clearConditionData();
            //１．ロジック【検索結果の取得】を実行
            Map params = new HashMap();
            params.put(ConditionLogicImpl.PK_COMPANY_CD, getMosChickenStoreStateConfirmDto().getTargetCompanyCd());
            params.put(ConditionLogicImpl.PK_SYS_DATE, getMosChickenStoreStateConfirmDto().getSysDate());
            params.put(ConditionLogicImpl.PK_USERINFO, getBirdUserInfo());
            List lists = getMosChickenStoreStateConfirmConditionLogic().execute(params);
            if(lists.size()>0){
                
                //２．結果をDTOにセットする。
                //[[対象食包材]]
                List listShoku = (List)lists.get(0);
                getMosChickenStoreStateConfirmDto().setListShokuhou(listShoku);
                if(listShoku != null && listShoku.size() >= 1){
                    getMosChickenStoreStateConfirmDto().setTargetShokuCd((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuCd());
                    getMosChickenStoreStateConfirmDto().setTargetShokuNameKna((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuNameKna());
                }
                //[[対象期間]]
                List listDate = (List)lists.get(1);
                getMosChickenStoreStateConfirmDto().setListDate(listDate);
                if(listDate != null){
                    if(listDate.size() > 1){
                        getMosChickenStoreStateConfirmDto().setTargetDateFrom(getBirdDateInfo().getSysDate());
                        getMosChickenStoreStateConfirmDto().setTargetDateTo(getBirdDateInfo().getSysDate());
                     }else if(listDate.size() == 1){
                         getMosChickenStoreStateConfirmDto().setTargetDateFrom((String)((SelectItem)listDate.get(0)).getValue());
                         getMosChickenStoreStateConfirmDto().setTargetDateTo((String)((SelectItem)listDate.get(0)).getValue());
                     }
                }
                List listTitle = (List)lists.get(2);
                getMosChickenStoreStateConfirmDto().setListTitle(listTitle);
                if(listTitle != null && listTitle.size() >= 1){
                    getMosChickenStoreStateConfirmDto().setTargetCkanriNo((String)((MstKanriKikan)listTitle.get(0)).getCkanriNo());
                    getMosChickenStoreStateConfirmDto().setTargetTitle((String)((MstKanriKikan)listTitle.get(0)).getTitle());
                }
                //[[対象支部]]
                List listSibu = (List)lists.get(3);
                getMosChickenStoreStateConfirmDto().setListSibu(listSibu);
                if(listSibu != null && listSibu.size() >= 1){
                    getMosChickenStoreStateConfirmDto().setTargetSibuCd((String)((CodSibuList)listSibu.get(0)).getSibuCd());
                    getMosChickenStoreStateConfirmDto().setTargetSibuName((String)((CodSibuList)listSibu.get(0)).getSibuName());
                }
                if(listShoku.size() <= 0){
                    getMosChickenStoreStateConfirmDto().setExecDisabledFlg(true);
                    throw new GenericMessageException("このタイトルには対象食包材が存在しません。");
                }
                getMosChickenStoreStateConfirmDto().setExecDisabledFlg(false);

            }
            else{
                getMosChickenStoreStateConfirmDto().clearConditionData();
                getMosChickenStoreStateConfirmDto().setListTitle(null);
                getMosChickenStoreStateConfirmDto().setListShokuhou(null);
                getMosChickenStoreStateConfirmDto().setListSibu(null);
                getMosChickenStoreStateConfirmDto().setListDate(null);
                getMosChickenStoreStateConfirmDto().setExecDisabledFlg(true);
            }
            
        }
        return null;
    }
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back() {
        getMosChickenStoreStateConfirmDto().clear();
        // TODO 自動生成されたメソッド・スタブ
        return MosChickenStoreStateConfirmUtil.VIEW_ID_CONDITION;
    }
    /**
     * 検索 アクション
     * 
     * １．ロジック【検索結果の取得】を実行
     * ２．結果をDTOにセットする。
     * @return 確認(or照会)画面ID
     */
    public String search() {
        //１．ロジック【検索結果の取得】を実行
        Map params = new HashMap();
        String companyCd = getMosChickenStoreStateConfirmDto().getTargetCompanyCd();
        String cKanriNo=getMosChickenStoreStateConfirmDto().getTargetCkanriNo();
        String shokuCd = getMosChickenStoreStateConfirmDto().getTargetShokuCd();
        String sibuCd = getMosChickenStoreStateConfirmDto().getTargetSibuCd();
        String dateFrom = getMosChickenStoreStateConfirmDto().getTargetDateFrom();
        String dateTo = getMosChickenStoreStateConfirmDto().getTargetDateTo();
        String sysDate = getMosChickenStoreStateConfirmDto().getSysDate();
        //パラメーター：会社コード
        params.put(SearchLogicImpl.PK_COMPANY_CD, companyCd);
        //パラメーター：管理番号
        params.put(SearchLogicImpl.PK_CKANRI_NO, cKanriNo);
        //パラメーター：対象食包材
        params.put(SearchLogicImpl.PK_SHOKU_CD, shokuCd);
        //パラメーター：対象支部
        params.put(SearchLogicImpl.PK_SIBU_CD, sibuCd);
        //パラメーター：対象期間From
        params.put(SearchLogicImpl.PK_DATE_FROM, dateFrom);
        //パラメーター：対象期間To
        params.put(SearchLogicImpl.PK_DATE_TO, dateTo);
        //パラメーター：システム日付
        params.put(SearchLogicImpl.PK_SYSDATE, sysDate);
        
        List listSearchData = getMosChickenStoreStateConfirmSearchLogic().execute(params);
        if(listSearchData.size() == 0){
            throw new NoResultException();
        }
        //２．結果をDTOにセットする。
        getMosChickenStoreStateConfirmDto().setIchiranSibuCd(sibuCd);
        getMosChickenStoreStateConfirmDto().setIchiranSibuName(getMosChickenStoreStateConfirmDto().getListSibuName(sibuCd));
        getMosChickenStoreStateConfirmDto().setListSearchData(listSearchData);
        
        return MosChickenStoreStateConfirmUtil.VIEW_ID_EDIT;
    }
    /**
     * タイトル変更アクション
     * 
     * １．ロジック【指定管理番号条件項目情報の取得】を実行する。
     * ２．結果をDTOへ設定する。
     * @return
     */
    public String changeTitle(){
        Map params = new HashMap();
        params.put(ChangeTitleLogicImpl.PK_COMPANY_CD, getMosChickenStoreStateConfirmDto().getTargetCompanyCd());
        params.put(ChangeTitleLogicImpl.PK_USERINFO, getBirdUserInfo());
        params.put(ChangeTitleLogicImpl.PK_CKANRI_NO, getMosChickenStoreStateConfirmDto().getTargetCkanriNo());
        params.put(ChangeTitleLogicImpl.PK_LIST_KIKAN, getMosChickenStoreStateConfirmDto().getListTitle());
        //１．ロジック【指定管理番号条件項目情報の取得】を実行する。
        List lists = getMosChickenStoreStateConfirmChangeTitleLogic().execute(params);
        //２．結果をDTOにセットする。
        //[[対象食包材]]
        List listShoku = (List)lists.get(0);
        getMosChickenStoreStateConfirmDto().setListShokuhou(listShoku);
        if(listShoku != null && listShoku.size() >= 1){
            getMosChickenStoreStateConfirmDto().setTargetShokuCd((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuCd());
            getMosChickenStoreStateConfirmDto().setTargetShokuNameKna((String)((CodShokuhouzaiList)listShoku.get(0)).getShokuNameKna());
        }
        //[[対象期間]]
        List listDate = (List)lists.get(1);
        getMosChickenStoreStateConfirmDto().setListDate(listDate);
        if(listDate != null){
            if(listDate.size() > 1){
                getMosChickenStoreStateConfirmDto().setTargetDateFrom(getBirdDateInfo().getSysDate());
                getMosChickenStoreStateConfirmDto().setTargetDateTo(getBirdDateInfo().getSysDate());
             }else if(listDate.size() == 1){
                 getMosChickenStoreStateConfirmDto().setTargetDateFrom((String)((SelectItem)listDate.get(0)).getValue());
                 getMosChickenStoreStateConfirmDto().setTargetDateTo((String)((SelectItem)listDate.get(0)).getValue());
             }
        }
        if(listShoku.size() <= 0){
            getMosChickenStoreStateConfirmDto().setExecDisabledFlg(true);
            throw new GenericMessageException("このタイトルには対象食包材が存在しません。");
        }
        else{
            getMosChickenStoreStateConfirmDto().setExecDisabledFlg(false);
        }
        return null;
    }
    public SearchLogic getMosChickenStoreStateConfirmSearchLogic() {
        return mosChickenStoreStateConfirmSearchLogic;
    }

    public void setMosChickenStoreStateConfirmSearchLogic(
            SearchLogic mosChickenStoreStateConfirmSearchLogic) {
        this.mosChickenStoreStateConfirmSearchLogic = mosChickenStoreStateConfirmSearchLogic;
    }

    public MosChickenStoreStateConfirmDto getMosChickenStoreStateConfirmDto() {
        return mosChickenStoreStateConfirmDto;
    }

    public void setMosChickenStoreStateConfirmDto(
            MosChickenStoreStateConfirmDto mosChickenStoreStateConfirmDto) {
        this.mosChickenStoreStateConfirmDto = mosChickenStoreStateConfirmDto;
    }


    /**
     * @return mosChickenStoreStateConfirmConditionLogic を戻します。
     */
    public ConditionLogic getMosChickenStoreStateConfirmConditionLogic() {
        return mosChickenStoreStateConfirmConditionLogic;
    }

    /**
     * @param mosChickenStoreStateConfirmConditionLogic 設定する mosChickenStoreStateConfirmConditionLogic。
     */
    public void setMosChickenStoreStateConfirmConditionLogic(
            ConditionLogic mosChickenStoreStateConfirmConditionLogic) {
        this.mosChickenStoreStateConfirmConditionLogic = mosChickenStoreStateConfirmConditionLogic;
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
     * @return mosChickenStoreStateConfirmChangeTitleLogic を戻します。
     */
    public ChangeTitleLogic getMosChickenStoreStateConfirmChangeTitleLogic() {
        return mosChickenStoreStateConfirmChangeTitleLogic;
    }
    /**
     * @param mosChickenStoreStateConfirmChangeTitleLogic 設定する mosChickenStoreStateConfirmChangeTitleLogic。
     */
    public void setMosChickenStoreStateConfirmChangeTitleLogic(
            ChangeTitleLogic mosChickenStoreStateConfirmChangeTitleLogic) {
        this.mosChickenStoreStateConfirmChangeTitleLogic = mosChickenStoreStateConfirmChangeTitleLogic;
    }

}
