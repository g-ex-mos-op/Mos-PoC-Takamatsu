package jp.co.isid.mos.bird.entry.projectplanmstregist.action.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.action.ConfirmAction;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dto.ProjectPlanMstRegistDto;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.RegistLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl.RegistLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業方針説明会マスタ登録
 * 確認or参照画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A21";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A22";
    /* アクションID：登録 */
    public static final String regist_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A23";
    /* DTO【事業方針説明会マスタ登録】*/
    private ProjectPlanMstRegistDto dto = null;
    /* ロジック【事業方針説明会マスタ情報の登録】*/
    private RegistLogic registLogic = null;
    /** セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();
    /**
     * @return dto を戻します。
     */
    public ProjectPlanMstRegistDto getProjectPlanMstRegistDto() {
        return dto;
    }
    /**
     * @param dto 設定する dto。
     */
    public void setProjectPlanMstRegistDto(
            ProjectPlanMstRegistDto dto) {
        this.dto = dto;
    }
    /**
     * @return registLogic を戻します。
     */
    public RegistLogic getProjectPlanMstRegistRegistLogic() {
        return registLogic;
    }
    /**
     * @param registLogic 設定する registLogic。
     */
    public void setProjectPlanMstRegistRegistLogic(RegistLogic registLogic) {
        this.registLogic = registLogic;
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
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     * 
     * ２．新規登録or更新の場合は、編集画面のVIEWIDをリターンする。
     * 
     * ３．処理２以外の場合は初期画面のVIEWIDをリターンする。
     * 
     * @return 画面ID
     */
    public String back(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        String modeType = getProjectPlanMstRegistDto().getModeType();
        if(ProjectPlanMstRegistUtil.MODETYPE_INSERT.equals(modeType)
                || ProjectPlanMstRegistUtil.MODETYPE_UPDATE.equals(modeType)){
            //２．新規登録or更新の場合は、編集画面のVIEWIDをリターンする。
            return ProjectPlanMstRegistUtil.VIEW_ID_EDIT;
        }
        //３．処理２以外の場合は初期画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_CONDITION;
    }
    /**
     * 登録 アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     * 
     * ２．ロジック【事業方針説明会マスタ管理の登録】を実行する。
     * 
     * ３．【DTO】クリア処理を実行する。
     * 
     * ４．初期画面のVIEWIDをリターンする。
     * 
     * @return 初期画面ID
     */
    public String regist(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．ロジック【事業方針説明会マスタ管理の登録】を実行する。
        //パラメーター
        Map params = new HashMap();
        params.put(RegistLogicImpl.PK_USERINFO,  getBirdUserInfo());
        params.put(RegistLogicImpl.PK_MODE_TYPE,  getProjectPlanMstRegistDto().getModeType());
        params.put(RegistLogicImpl.PK_ENTITY_COURSE, getProjectPlanMstRegistDto().getEntityMstData());
        params.put(RegistLogicImpl.PK_LIST_MST, getProjectPlanMstRegistDto().getListMst());
        params.put(RegistLogicImpl.PK_LIST_MST_DATE, getProjectPlanMstRegistDto().getListMstDate());
        params.put(RegistLogicImpl.PK_LIST_MST_NOTICE, getProjectPlanMstRegistDto().getListMstNotice());
        //ロジック実行
        getProjectPlanMstRegistRegistLogic().execute(params);
        //３．【DTO】クリア処理を実行する。
        getProjectPlanMstRegistDto().clear();
        //４．初期画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_CONDITION;
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
                getProjectPlanMstRegistDto().getNowSessionKey()
                  ,  getProjectPlanMstRegistDto().getSessionKey() );
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
}
