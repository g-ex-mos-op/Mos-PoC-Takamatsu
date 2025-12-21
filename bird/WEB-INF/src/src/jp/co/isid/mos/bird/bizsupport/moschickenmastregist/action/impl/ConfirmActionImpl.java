package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.ConfirmAction;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl.RegistLogicImpl;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * モスチキン管理マスタ登録
 * 確認or参照画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A51";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A52";
    /* アクションID：登録 */
    public static final String regist_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A53";
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenMstRegistDto mosChickenMstRegistDto = null;
    /* ロジック【モスチキン管理マスタ情報の登録】*/
    private RegistLogic mosChickenMstRegistLogic = null;
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
     * @return mosChickenMstRegistLogic を戻します。
     */
    public RegistLogic getMosChickenMstRegistLogic() {
        return mosChickenMstRegistLogic;
    }
    /**
     * @param mosChickenMstRegistLogic 設定する mosChickenMstRegistLogic。
     */
    public void setMosChickenMstRegistLogic(RegistLogic mosChickenMstRegistLogic) {
        this.mosChickenMstRegistLogic = mosChickenMstRegistLogic;
    }
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
        String modeType = getMosChickenMstRegistDto().getModeType();
        if(MosChickenMstRegistUtil.MODETYPE_INSERT.equals(modeType)
                || MosChickenMstRegistUtil.MODETYPE_UPDATE.equals(modeType)){
            //
            return MosChickenMstRegistUtil.VIEW_ID_EDIT;
        }
        return MosChickenMstRegistUtil.VIEW_ID_CONDITION;
    }
    /**
     * 登録 アクション
     * 
     * @return 初期画面ID
     */
    public String regist(){
        //１．ロジック【モスチキンマスタ管理の登録】を実行する。
        Map params = new HashMap();
        params.put(RegistLogicImpl.PK_USER_ID,  getMosChickenMstRegistDto().getUserId());
        params.put(RegistLogicImpl.PK_MODE_TYPE,  getMosChickenMstRegistDto().getModeType());
        params.put(RegistLogicImpl.PK_LIST_KIKAN, getMosChickenMstRegistDto().getListKanriKikan());
        params.put(RegistLogicImpl.PK_LIST_MENU_GROUP, getMosChickenMstRegistDto().getListKanriMenuGroup());
        params.put(RegistLogicImpl.PK_LIST_MENU, getMosChickenMstRegistDto().getListKanriMenu());
        params.put(RegistLogicImpl.PK_NENDO, DateManager.getCurrentYear(getMosChickenMstRegistDto().getSysDate()));
        getMosChickenMstRegistLogic().execute(params);
        return MosChickenMstRegistUtil.VIEW_ID_CONDITION;
    }
}
