package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.ChoiseShokuhouzaiAction;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShokuhouzaiDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.GetHacchuBnrListLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchShokuhouzaiLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl.SearchShokuhouzaiLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * モスチキン管理マスタ登録
 * 食包材選択画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class ChoiseShokuhouzaiActionImpl implements ChoiseShokuhouzaiAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A41";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A42";
    /* アクションID：検索 */
    public static final String search_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A43";
    /* アクションID：決定 */
    public static final String choise_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A44";
    
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenMstRegistDto mosChickenMstRegistDto = null;
    /* DTO【モスチキン管理マスタ登録 食包材選択】*/
    private MosChickenShokuhouzaiDto mosChickenShokuhouzaiDto = null;
    /* ロジック【発注分類マスタ情報の取得】*/
    private GetHacchuBnrListLogic mosChickenMstGetHacchuBnrListLogic = null;
    /* ロジック【食包材マスタの検索】*/
    private SearchShokuhouzaiLogic mosChickenMstSearchShokuhouzaiLogic = null;

    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize(){
        //１．ロジック【発注分類マスタ情報の取得】を実行する。
        List listHacchuBnrMst=getMosChickenMstGetHacchuBnrListLogic().execute(null);
        //２．結果をDTOへ設定する。
        getMosChickenShokuhouzaiDto().setListHacchuBnr(listHacchuBnrMst);
        return null;
    }
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back(){
         return MosChickenMstRegistUtil.VIEW_ID_EDIT;
    }
    /**
     * 検索 アクション
     * 
     * @return null 画面ID
     */
    public String search(){
        //１．ロジック【食包材情報の検索】を実行する。
        Map params = new HashMap();
        params.put(SearchShokuhouzaiLogicImpl.PK_CD_FROM, getMosChickenShokuhouzaiDto().getCdFrom());
        params.put(SearchShokuhouzaiLogicImpl.PK_CD_TO, getMosChickenShokuhouzaiDto().getCdTo());
        params.put(SearchShokuhouzaiLogicImpl.PK_SHOKUHOUZAI_NAME, getMosChickenShokuhouzaiDto().getName());
        params.put(SearchShokuhouzaiLogicImpl.PK_HACCHU_BNR_CD, getMosChickenShokuhouzaiDto().getHacchuBnrCd());
        List listShokuhouzaiMst = null;
        try{
            listShokuhouzaiMst = getMosChickenMstSearchShokuhouzaiLogic().execute(params);
        }catch(NotExistException notExist){
            getMosChickenShokuhouzaiDto().setListShokuhouzaiMst(listShokuhouzaiMst);
            throw notExist;
        }
        finally{
            //正常終了・異常終了でもコードの値をDTOへ再設定する。
            getMosChickenShokuhouzaiDto().setCdFrom((String)params.get(SearchShokuhouzaiLogicImpl.PK_CD_FROM));
            getMosChickenShokuhouzaiDto().setCdTo((String)params.get(SearchShokuhouzaiLogicImpl.PK_CD_TO));
        }
        getMosChickenShokuhouzaiDto().setListShokuhouzaiMst(listShokuhouzaiMst);
        return null;
    }
    /**
     * 決定 アクション
     * 
     * @return 編集画面ID
     */
    public String choise(){
        String ckanriNo = getMosChickenMstRegistDto().getTargetCkanriNo();
        String menuGroupCd = getMosChickenMstRegistDto().getTargetMenuGroupCd();
        String menuCd = getMosChickenMstRegistDto().getTargetMenuCd();
        String shokuCd = getMosChickenShokuhouzaiDto().getTargetShokuCd();
        String shokuName = getMosChickenShokuhouzaiDto().getTargetShokuName();
        List listTargetMenu = getMosChickenMstRegistDto().getListKanriMenu();
        Map params = new HashMap();
        if(getMosChickenMstRegistDto().getCompliteActionType().equals("lumpChoise")){
            //１．ユーティル【static 処理保持クラス】一括食包材選択処理
            params = MosChickenMstRegistUtil.updateShokuhouzaiAll(ckanriNo, menuGroupCd, shokuCd, shokuName, listTargetMenu);
        }
        else if(getMosChickenMstRegistDto().getCompliteActionType().equals("choise")){
            //１．ユーティル【static 処理保持クラス】食包材選択処理
            MosChickenMstRegistUtil.updateShokuhouzai(params, ckanriNo, menuGroupCd, shokuCd, shokuName, listTargetMenu, menuCd);
        }
        if(params != null){
            //２．メニュー追加済み[[対象管理メニュー]]をDTOへ設定する。
            getMosChickenMstRegistDto().setListKanriMenu((List)params.get(MosChickenMstRegistUtil.RPK_KANRI_MENU));
            if(params.containsKey(MosChickenMstRegistUtil.RPK_DUPLICATE_EX)){
                //重複エラーをDTOへ設定。
                getMosChickenShokuhouzaiDto().setDuplicateEx(
                        (GenericMessageException)params.get(MosChickenMstRegistUtil.RPK_DUPLICATE_EX));
            }
            getMosChickenMstRegistDto().setMenuStartIndex((String)params.get(MosChickenMstRegistUtil.RPK_MENUSTARTINDEX));
        }
        return MosChickenMstRegistUtil.VIEW_ID_EDIT;
    }

    /**
     * @return mosChickenMstRegistDto を戻します。
     */
    public MosChickenMstRegistDto getMosChickenMstRegistDto() {
        return mosChickenMstRegistDto;
    }
    /**
     * @param mosChickenMstRegistDto 設定する mosChickenMstRegistDto。
     */
    public void setMosChickenMstRegistDto(
            MosChickenMstRegistDto mosChickenMstRegistDto) {
        this.mosChickenMstRegistDto = mosChickenMstRegistDto;
    }
    /**
     * @return mosChickenShokuhouzaiDto を戻します。
     */
    public MosChickenShokuhouzaiDto getMosChickenShokuhouzaiDto() {
        return mosChickenShokuhouzaiDto;
    }
    /**
     * @param mosChickenShokuhouzaiDto 設定する mosChickenShokuhouzaiDto。
     */
    public void setMosChickenShokuhouzaiDto(
            MosChickenShokuhouzaiDto mosChickenShokuhouzaiDto) {
        this.mosChickenShokuhouzaiDto = mosChickenShokuhouzaiDto;
    }
    /**
     * @return mosChickenMstGetHacchuBnrListLogic を戻します。
     */
    public GetHacchuBnrListLogic getMosChickenMstGetHacchuBnrListLogic() {
        return mosChickenMstGetHacchuBnrListLogic;
    }
    /**
     * @param mosChickenMstGetHacchuBnrListLogic 設定する mosChickenMstGetHacchuBnrListLogic。
     */
    public void setMosChickenMstGetHacchuBnrListLogic(
            GetHacchuBnrListLogic mosChickenMstGetHacchuBnrListLogic) {
        this.mosChickenMstGetHacchuBnrListLogic = mosChickenMstGetHacchuBnrListLogic;
    }
    /**
     * @return mosChickenMstSearchShokuhouzaiLogic を戻します。
     */
    public SearchShokuhouzaiLogic getMosChickenMstSearchShokuhouzaiLogic() {
        return mosChickenMstSearchShokuhouzaiLogic;
    }
    /**
     * @param mosChickenMstSearchShokuhouzaiLogic 設定する mosChickenMstSearchShokuhouzaiLogic。
     */
    public void setMosChickenMstSearchShokuhouzaiLogic(
            SearchShokuhouzaiLogic mosChickenMstSearchShokuhouzaiLogic) {
        this.mosChickenMstSearchShokuhouzaiLogic = mosChickenMstSearchShokuhouzaiLogic;
    }
}
