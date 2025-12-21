package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.MenuAddAction;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShanaiMenuDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchShanaiMenuLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl.SearchShanaiMenuLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * モスチキン管理マスタ登録
 * メニュー追加画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class MenuAddActionImpl implements MenuAddAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A31";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A32";
    /* アクションID：検索 */
    public static final String search_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A33";
    /* アクションID：決定 */
    public static final String fix_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A34";
    
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenMstRegistDto mosChickenMstRegistDto = null;
    /* DTO【モスチキン管理マスタ登録 メニュー追加D】*/
    private MosChickenShanaiMenuDto mosChickenShanaiMenuDto = null;
    /* ロジック【社内メニューマスタの検索】*/
    private SearchShanaiMenuLogic mosChickenMstSearchShanaiMenuLogic = null;
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize(){
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
        //ロジック【社内メニューマスタの検索】を実行する。
        Map params = new HashMap();
        params.put(SearchShanaiMenuLogicImpl.PK_CD_FROM, getMosChickenShanaiMenuDto().getMenuCdFrom());
        params.put(SearchShanaiMenuLogicImpl.PK_CD_TO, getMosChickenShanaiMenuDto().getMenuCdTo());
        params.put(SearchShanaiMenuLogicImpl.PK_MENU_NAME, getMosChickenShanaiMenuDto().getMenuName());
        params.put(SearchShanaiMenuLogicImpl.PK_SYSDATE, getMosChickenMstRegistDto().getSysDate());
        List listShanaiMenu = null;
        try{
            listShanaiMenu = getMosChickenMstSearchShanaiMenuLogic().execute(params);
        }catch(NotExistException notExist){
            getMosChickenShanaiMenuDto().setListMenuMst(listShanaiMenu);
            throw notExist;
        }
        finally{
            //正常終了・異常終了でもコードの値をDTOへ再設定する。
            getMosChickenShanaiMenuDto().setMenuCdFrom((String)params.get(SearchShanaiMenuLogicImpl.PK_CD_FROM));
            getMosChickenShanaiMenuDto().setMenuCdTo((String)params.get(SearchShanaiMenuLogicImpl.PK_CD_TO));
        }
        getMosChickenShanaiMenuDto().setListMenuMst(listShanaiMenu);
        return null;
    }
    /**
     * 決定 アクション
     * 
     * @return 編集画面ID
     */
    public String fix(){
        //対象管理番号
        String ckanriNo = getMosChickenMstRegistDto().getTargetCkanriNo();
        //対象メニューグループコード
        String targetMenuGroup = getMosChickenMstRegistDto().getTargetMenuGroupCd();
        //メニュー検索結果リスト
        List listShanaiMenu = getMosChickenShanaiMenuDto().getListMenuMst();
        //対象管理メニュー
        List listMenu = getMosChickenMstRegistDto().getListKanriMenu();
        //ユーティル【static 処理保持クラス】.選択メニュー設定可能判断処理を実行する。
        MosChickenMstRegistUtil.checkMenuCnt(targetMenuGroup, listShanaiMenu, listMenu);
        //ユーティル【static 処理保持クラス】.メニュー追加処理を実行する。
        Map params = MosChickenMstRegistUtil.addMenu(ckanriNo, targetMenuGroup, listShanaiMenu, listMenu);
        if(params != null){
            //メニュー追加済み[[対象管理メニュー]]をDTOへ設定する。
            getMosChickenMstRegistDto().setListKanriMenu((List)params.get(MosChickenMstRegistUtil.RPK_KANRI_MENU));
            getMosChickenShanaiMenuDto().setDuplicateEx(null);
/*
            if(params.containsKey(MosChickenMstRegistUtil.RPK_DUPLICATE_EX)){
                //重複エラーをDTOへ設定。
                getMosChickenShanaiMenuDto().setDuplicateEx(
                        (GenericMessageException)params.get(MosChickenMstRegistUtil.RPK_DUPLICATE_EX));
            }
*/
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
     * @return mosChickenMstSearchShanaiMenuLogic を戻します。
     */
    public SearchShanaiMenuLogic getMosChickenMstSearchShanaiMenuLogic() {
        return mosChickenMstSearchShanaiMenuLogic;
    }
    /**
     * @param mosChickenMstSearchShanaiMenuLogic 設定する mosChickenMstSearchShanaiMenuLogic。
     */
    public void setMosChickenMstSearchShanaiMenuLogic(
            SearchShanaiMenuLogic mosChickenMstSearchShanaiMenuLogic) {
        this.mosChickenMstSearchShanaiMenuLogic = mosChickenMstSearchShanaiMenuLogic;
    }
    /**
     * @return mosChickenShanaiMenuDto を戻します。
     */
    public MosChickenShanaiMenuDto getMosChickenShanaiMenuDto() {
        return mosChickenShanaiMenuDto;
    }
    /**
     * @param mosChickenShanaiMenuDto 設定する mosChickenShanaiMenuDto。
     */
    public void setMosChickenShanaiMenuDto(
            MosChickenShanaiMenuDto mosChickenShanaiMenuDto) {
        this.mosChickenShanaiMenuDto = mosChickenShanaiMenuDto;
    }
}
