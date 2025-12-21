package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShanaiMenuDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShokuhouzaiDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * モスチキン管理マスタ登録
 * 初期画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A01";
    /* アクションID：参照 */
    public static final String view_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A02";
    /* アクションID：新規登録 */
    public static final String insert_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A03";
    /* アクションID：編集 */
    public static final String update_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A04";
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenMstRegistDto mosChickenMstRegistDto = null;
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenShanaiMenuDto mosChickenShanaiMenuDto = null;
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenShokuhouzaiDto mosChickenShokuhouzaiDto = null;
    /* ロジック【条件項目情報の取得】*/
    private ConditionLogic mosChickenMstConditionLogic = null;
    /* ロジック【モスチキン管理マスタ情報の検索】*/
    private SearchLogic mosChickenMstSearchLogic = null;

    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize() {
        //１．DTOのクリア処理を実行する。
        getMosChickenMstRegistDto().clear();
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //２．ロジック【条件項目情報の取得】を実行する。
        Map params = new HashMap();
        //アプリ日付の年度を取得
        String thisNendo = DateManager.getCurrentYear(getBirdDateInfo().getAppDate());
        //アプリ年度含めて２年度前の年度
        String last2Nendo = "9999";
        try{
            last2Nendo = DateManager.getPrevYear(thisNendo, 2);
        }catch(Exception e){
            
        }
        //対象開始日
        String targetStartDt = last2Nendo+"0401";    
        params.put(ConditionLogicImpl.PK_START_DT, targetStartDt);
        List list = getMosChickenMstConditionLogic().execute(params);
        //３．結果をDTOへセットする。
        getMosChickenMstRegistDto().setUserId(getBirdUserInfo().getUserID());
        getMosChickenMstRegistDto().setSysDate(getBirdDateInfo().getSysDate());
        getMosChickenMstRegistDto().setAppDate(getBirdDateInfo().getAppDate());
        getMosChickenMstRegistDto().setTargetStartDt(targetStartDt);
        getMosChickenMstRegistDto().setListCamp(list);
        
        return null;
    }

    /**
     * 参照 アクション
     * 
     * @return 確認(or照会)画面ID
     */
    public String view() {
        getMosChickenMstRegistDto().clear();
        //１．ロジック【モスチキン管理マスタ情報の検索】を実行する。
        Map params = new HashMap();
        //パラメーター：管理番号
        params.put(SearchLogicImpl.PK_CKANRI_NO, getMosChickenMstRegistDto().getTargetCkanriNo());
        List lists = getMosChickenMstSearchLogic().execute(params);
        //２．処理１の結果[[管理対象期間]]をDTOへ設定する。
        List listKikan = (List)lists.get(0);
        getMosChickenMstRegistDto().setListKanriKikan(listKikan);
        if(lists.size() > 1 && lists.get(1) != null){
            //３．処理１の結果[[管理対象メニューグループ]]をDTOへ設定する。
            List listMenuGroup =  (List)lists.get(1);     
            getMosChickenMstRegistDto().setListKanriMenuGroup(listMenuGroup);
            if(lists.size() > 2 && lists.get(2) != null){
                //４．処理１の結果[[管理対象メニュー]]をDTOへ設定する。
                List listMenu =  (List)lists.get(2);     
                getMosChickenMstRegistDto().setListKanriMenu(listMenu);
            }
        }
        // TODO 自動生成されたメソッド・スタブ
        return MosChickenMstRegistUtil.VIEW_ID_CONFIRM;

    }

    /**
     * 新規登録 アクション
     * 
     * @return 編集画面ID
     */
    public String insert() {
        
        getMosChickenMstRegistDto().clear();
        //１．ユーティル【static 処理保持クラス】.管理対象期間リスト生成取得処理を実行し、DTOへ設定する。
        getMosChickenMstRegistDto().setListKanriKikan(MosChickenMstRegistUtil.createKikan());
        //２．ユーティル【static 処理保持クラス】.メニューグループ追加処理を実行する。
        MosChickenMstRegistUtil.addMenuGroup(getMosChickenMstRegistDto());
        // TODO 自動生成されたメソッド・スタブ
        return MosChickenMstRegistUtil.VIEW_ID_EDIT;
    }

    /**
     * 編集 アクショ4
     * 
     * @return 編集画面ID
     */
    public String update() {
        //１．アクション【初期画面用アクション実行クラス】.参照 を実行する。
        view();
        //２．DTOの[[管理対象メニューグループ]]を取得する。
        List listMenuGroup =getMosChickenMstRegistDto().getListKanriMenuGroup();
        if(listMenuGroup == null || listMenuGroup.size() <= 0){
            //２．ユーティル【static 処理保持クラス】.メニューグループ追加処理を実行する。
            MosChickenMstRegistUtil.addMenuGroup(getMosChickenMstRegistDto());
        }
        // TODO 自動生成されたメソッド・スタブ
        return MosChickenMstRegistUtil.VIEW_ID_EDIT;
    }
    /**
     * DTO【モスチキン管理マスタ登録】取得処理
     * @return
     */
    public MosChickenMstRegistDto getMosChickenMstRegistDto() {
        return mosChickenMstRegistDto;
    }

    /**
     * DTO【モスチキン管理マスタ登録】設定処理
     * @return
     */
    public void setMosChickenMstRegistDto(MosChickenMstRegistDto dto) {
        this.mosChickenMstRegistDto = dto;
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
     * @return mosChickenMstConditionLogic を戻します。
     */
    public ConditionLogic getMosChickenMstConditionLogic() {
        return mosChickenMstConditionLogic;
    }

    /**
     * @param mosChickenMstConditionLogic 設定する mosChickenMstConditionLogic。
     */
    public void setMosChickenMstConditionLogic(
            ConditionLogic mosChickenMstConditionLogic) {
        this.mosChickenMstConditionLogic = mosChickenMstConditionLogic;
    }
    /**
     * @return mosChickenMstSearchLogic を戻します。
     */
    public SearchLogic getMosChickenMstSearchLogic() {
        return mosChickenMstSearchLogic;
    }

    /**
     * @param mosChickenMstSearchLogic 設定する mosChickenMstSearchLogic。
     */
    public void setMosChickenMstSearchLogic(SearchLogic mosChickenMstSearchLogic) {
        this.mosChickenMstSearchLogic = mosChickenMstSearchLogic;
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
}
